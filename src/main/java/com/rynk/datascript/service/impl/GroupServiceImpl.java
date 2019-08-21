package com.rynk.datascript.service.impl;

import com.rynk.datascript.config.GroupConvert;
import com.rynk.datascript.mongo.dao.GroupMongoDao;
import com.rynk.datascript.mongo.entity.GroupEntity;
import com.rynk.datascript.mongo.entity.GroupMemberEntity;
import com.rynk.datascript.mysql.dao.GroupMapper;
import com.rynk.datascript.mysql.domain.GroupDo;
import com.rynk.datascript.mysql.domain.GroupMembersDo;
import com.rynk.datascript.service.IGroupsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * @description
 * @auther Somnus丶y
 * @date 2019/7/17 10:20
 */
@Service
@Slf4j
public class GroupServiceImpl implements IGroupsService, CommandLineRunner {

    @Resource
    private GroupMapper groupMapper;

    @Autowired
    private GroupMongoDao groupMongoDao;

    @Resource
    private ThreadPoolExecutor convertWorker;


    private AtomicInteger dataSequence = new AtomicInteger(0);



    @Override public List<GroupDo> selectGroups() {
        return groupMapper.selectAllGroupDao();
    }

    @Override public String saveGroupsMongo(GroupEntity entitys) {
        return groupMongoDao.saveGroup(entitys);
    }

    @Override public void batchSaveGroupsMogo(List<GroupEntity> entities) {
        groupMongoDao.batchSaveGroupsMogo(entities);
    }

    @Override public void mysql2Mongo4Groups() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        List<GroupDo> groupDos = selectGroups();
        //查询所有有效群
        List<Integer> validGroups = groupMapper.selectAllValidGroup();
        List<GroupEntity> entitys = groupDos.stream()
                .filter(item->{
                    if(validGroups.contains(item.getGroupId())){
                        return true;
                    }else {
                        return false;
                    }
                })
                .map(item -> {
            GroupEntity entity = new GroupEntity();
            BeanUtils.copyProperties(item, entity);
            entity.setGroupId(item.getGroupId()+"");
            entity.setMute(item.getMute()+"");
            entity.setAvatarUrl(item.getPortrait());
            entity.setGroupOwnerUserId(item.getUserId()+"");
            entity.setCreatedUserId(item.getUserId() + "");
            entity.setModifyUserId(item.getUserId()+"");
            entity.setCreatedTime(GroupConvert.dateFormat(item.getCreatedAt()));
            entity.setModifyTime(GroupConvert.dateFormat(item.getUpdatedAt()));
            entity.setValidTime(GroupConvert.dateFormat(item.getLvlExpiresAt()));
            return entity;
        }).collect(Collectors.toList());
        batchSaveGroupsMogo(entitys);
        stopWatch.stop();
        log.info("导groups执行结束！总耗时{{}}",stopWatch.getTotalTimeSeconds());
    }




//    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    @Override public List<GroupMembersDo> selectGroupMembers(int start,int count) {
        return groupMapper.limitGroupMember(start,count);
    }

    @Override public void batchSaveGroupMemberMogo(List<GroupMemberEntity> entities) {
        groupMongoDao.batchSaveGroupMembersMogo(entities);
    }



    @Override public void mysql2Mongo4GroupMember(){
        long begin = System.currentTimeMillis();
        //计算count
        int now = groupMapper.countGroupMember();
        log.info("计算count耗时{{}},count:{{}}",(System.currentTimeMillis()-begin)/1000,now);
        int count = 1000;
        while (now >= 1000){
            int start = now-count;
            convertWorker.execute(new Worker(start,count,begin));
            now-=1000;
        }
        if(now>0){
            convertWorker.execute(new Worker(0,now,begin));
        }

    }


    private class Worker implements Runnable{

        private int start;
        private int count;
        private long begin;

        public Worker(int start, int count,long begin) {
            this.start = start;
            this.count = count;
            this.begin = begin;
        }

        @Override public void run() {
            StopWatch watch = new StopWatch();
            watch.start();
            List<GroupMembersDo> groupMembersDos = GroupServiceImpl.this.selectGroupMembers(start, count);
            //去重
            List<String> tmp = new LinkedList<>();
            groupMembersDos = groupMembersDos.stream().filter(i->{
                String concat = i.getUserId()+"-"+i.getGroupId();
                if(tmp.contains(concat)){
                    return false;
                }else{
                    tmp.add(concat);
                    return true;
                }
            }).collect(Collectors.toList());
            watch.stop();
            log.info("批量导groupMembers数据库查询结束,执行到index={{}},查询执行时间{{}},目前总耗时{{}}",
                    start, watch.getLastTaskTimeMillis(), (System.currentTimeMillis() - begin) / 1000);
            watch.start();
            List<GroupMemberEntity> entitys = groupMembersDos.stream()
                    .filter(item->StringUtils.isEmpty(item.getDeletedAt()))
                    .map(item -> {
                GroupMemberEntity entity = new GroupMemberEntity();
                BeanUtils.copyProperties(item, entity);
                int role = item.getRole();
                entity.setRole(role==0?2:role==1?0:1);
                entity.setMute(item.getMute()+"");
                entity.setGroupId(item.getGroupId() + "");
                entity.setUserId(item.getUserId()+"");
                entity.setInGroupStatus(item.getInGroupStatus()==null?0:item.getInGroupStatus());
                entity.setCreatedTime(GroupConvert.dateFormat(item.getCreatedAt()));
                entity.setModifyTime(GroupConvert.dateFormat(item.getUpdatedAt()));
                return entity;
            }).collect(Collectors.toList());
            batchSaveGroupMemberMogo(entitys);
            watch.stop();
            log.info("批量导groupMembers执行结束,执行到index={{}},当前worker执行时间{{}},目前总耗时{{}}",
                    start,watch.getTotalTimeSeconds(),(System.currentTimeMillis()-begin)/1000);
        }


    }




    @Override public void run(String... args) throws Exception {
        mysql2Mongo4Groups();
        mysql2Mongo4GroupMember();
    }



}
