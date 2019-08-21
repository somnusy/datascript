package com.rynk.datascript.mongo.dao.impl;

import com.rynk.datascript.mongo.dao.GroupMongoDao;
import com.rynk.datascript.mongo.entity.GroupEntity;
import com.rynk.datascript.mongo.entity.GroupMemberEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @description
 * @auther Somnus丶y
 * @date 2019/7/17 10:53
 */
@Repository
public class GroupMongoDaoImpl implements GroupMongoDao {

    @Autowired
    private MongoTemplate mongoTemplate;


    @Override public String saveGroup(GroupEntity groupEntity) {
        return mongoTemplate.save(groupEntity).getGroupId();
    }

    @Override public void batchSaveGroupsMogo(List<GroupEntity> entities) {
        mongoTemplate.insertAll(entities);
    }


    @Override public String saveGroupMember(GroupMemberEntity groupMemberEntity) {
        return mongoTemplate.save(groupMemberEntity).getId();
    }

    @Override public void batchSaveGroupMembersMogo(List<GroupMemberEntity> entities) {
        mongoTemplate.insertAll(entities);
    }

}
