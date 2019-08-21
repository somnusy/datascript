package com.rynk.datascript.mongo.dao;

import com.rynk.datascript.mongo.entity.GroupEntity;
import com.rynk.datascript.mongo.entity.GroupMemberEntity;

import java.util.List;

/**
 * @description
 * @auther Somnus丶y
 * @date 2019/7/17 10:53
 */
public interface GroupMongoDao {

    String saveGroup(GroupEntity groupEntity);

    void batchSaveGroupsMogo(List<GroupEntity> entities);



    String saveGroupMember(GroupMemberEntity groupMemberEntity);

    void batchSaveGroupMembersMogo(List<GroupMemberEntity> entities);

}
