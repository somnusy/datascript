package com.rynk.datascript.service;

import com.rynk.datascript.mongo.entity.GroupEntity;
import com.rynk.datascript.mongo.entity.GroupMemberEntity;
import com.rynk.datascript.mysql.domain.GroupDo;
import com.rynk.datascript.mysql.domain.GroupMembersDo;

import java.util.List;

/**
 * @description
 * @auther Somnus丶y
 * @date 2019/7/17 10:20
 */
public interface IGroupsService {

    List<GroupDo> selectGroups();

    String saveGroupsMongo(GroupEntity entitys);

    void batchSaveGroupsMogo(List<GroupEntity> entities);

    void mysql2Mongo4Groups();


    //groupMembers操作

    List<GroupMembersDo> selectGroupMembers(int start,int count);

    void batchSaveGroupMemberMogo(List<GroupMemberEntity> entities);

    void mysql2Mongo4GroupMember();


}
