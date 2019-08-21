package com.rynk.datascript.mysql.dao;

import com.rynk.datascript.mysql.domain.GroupDo;
import com.rynk.datascript.mysql.domain.GroupMembersDo;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

/**
 * @description
 * @auther Somnus丶y
 * @date 2019/7/17 10:11
 */
@Mapper
public interface GroupMapper {


    @Select("select * from groups g left join users u on g.user_id = u.id left join "
            + "user_profile up on g.user_id = up.user_id where g.deleted_at is null")
    @Results({
            @Result(column="id", property="groupId", jdbcType= JdbcType.INTEGER, id=true),
            @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
            @Result(column="bulletin", property="bulletin", jdbcType=JdbcType.VARCHAR),
            @Result(column="user_id", property="userId", jdbcType=JdbcType.INTEGER),
            @Result(column="member_count", property="memberSize", jdbcType=JdbcType.INTEGER),
            @Result(column="max_member_count", property="maxMemberSize", jdbcType=JdbcType.INTEGER),
            @Result(column="review", property="review", jdbcType=JdbcType.INTEGER),
            @Result(column="lvl", property="lvl", jdbcType=JdbcType.INTEGER),
            @Result(column="lvl_expires_at", property="lvlExpiresAt", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="deleted_at", property="deletedAt", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="created_at", property="createdAt", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="updated_at", property="updatedAt", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="portrait", property="portrait", jdbcType=JdbcType.VARCHAR),
            @Result(column="mute", property="mute", jdbcType=JdbcType.INTEGER),
            @Result(column="account", property="groupOwnerNumber", jdbcType=JdbcType.INTEGER),
            @Result(column="nickname", property="groupOwnerNickname", jdbcType=JdbcType.INTEGER)
    })
    List<GroupDo> selectAllGroupDao();





    /*@Select("select * from group_members g left join users u on g.user_id = u.id left join "
            + " user_profile up on g.user_id = up.user_id left join new_friends n on "
            + " g.user_id = n.target_user_id and g.group_id = n.group_id")*/
    @Select("select * from group_members g left join users u on g.user_id = u.id left join "
            + " user_profile up on g.user_id = up.user_id")
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "nickname", property = "nickname", jdbcType = JdbcType.VARCHAR),
            @Result(column = "group_id", property = "groupId", jdbcType = JdbcType.INTEGER),
            @Result(column = "role", property = "role", jdbcType = JdbcType.INTEGER),
            @Result(column = "dont_disturb", property = "notDisturb", jdbcType = JdbcType.INTEGER),
            @Result(column="deleted_at", property="deletedAt", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="created_at", property="createdAt", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="updated_at", property="updatedAt", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="mute", property="mute", jdbcType=JdbcType.INTEGER),
            @Result(column="account", property="number", jdbcType=JdbcType.INTEGER),
    })
    List<GroupMembersDo> selectAllGroupMenber();


    //GroupMember数据量过大，分批次查询


    /*@Select("select * from group_members g left join users u on g.user_id = u.id left join "
            + " user_profile up on g.user_id = up.user_id limit #{start},#{count}")*/
    /*@Select("select * from (SELECT * FROM group_members WHERE id >= "
            + "(SELECT id FROM group_members LIMIT 0, 1) LIMIT 1000) g "
            + " left join users u on g.user_id = u.id left join user_profile up on g.user_id = up.user_id")*/

    @Select("select "
            + "g.nickname as nickname,g.user_id as user_id,g.group_id as group_id, g.role as role,"
            + "g.dont_disturb as dont_disturb,g.deleted_at as deleted_at,g.created_at as created_at,"
            + "g.updated_at as updated_at,g.mute as mute,u.account as account,n.source_user_id as invitedUserId,"
            + "n.`status` as inGroupStatus,up.nickname as invitedUserNickName,up1.portrait as avatarUrl"
            + " from group_members g "
            + " left join users u on g.user_id = u.id "
            + " left join new_friends n on g.user_id = n.join_user_id and g.group_id = n.group_id "
            + " left join user_profile up on n.source_user_id = up.user_id "
            + " left join user_profile up1 on g.user_id = up1.user_id"
            + " limit #{start},#{count}")
    @Results({
            @Result(column = "nickname", property = "nickname", jdbcType = JdbcType.VARCHAR),
            @Result(column = "user_id", property = "userId", jdbcType = JdbcType.INTEGER),
            @Result(column = "group_id", property = "groupId", jdbcType = JdbcType.INTEGER),
            @Result(column = "role", property = "role", jdbcType = JdbcType.INTEGER),
            @Result(column = "dont_disturb", property = "notDisturb", jdbcType = JdbcType.INTEGER),
            @Result(column="deleted_at", property="deletedAt", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="created_at", property="createdAt", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="updated_at", property="updatedAt", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="mute", property="mute", jdbcType=JdbcType.INTEGER),
            @Result(column="account", property="number", jdbcType=JdbcType.INTEGER),
            @Result(column="invitedUserId", property="invitedUserId", jdbcType=JdbcType.INTEGER),
            @Result(column="inGroupStatus", property="inGroupStatus", jdbcType=JdbcType.INTEGER),
            @Result(column="invitedUserNickName", property="invitedUserNickName", jdbcType=JdbcType.VARCHAR),
            @Result(column="avatarUrl", property="avatarUrl", jdbcType=JdbcType.VARCHAR),
    })
    List<GroupMembersDo> limitGroupMember(@Param("start") int start, @Param("count") int count);


    @Select("select count(1) "
            + " from group_members g "
            + " left join users u on g.user_id = u.id "
            + " left join new_friends n on g.user_id = n.join_user_id and g.group_id = n.group_id "
            + " left join user_profile up on n.source_user_id = up.user_id "
            + " left join user_profile up1 on g.user_id = up1.user_id")
    int countGroupMember();




    /**
     * 查询所有有群组的群id
     */

    @Select("select DISTINCT(group_id) from group_members where role = 1")
    List<Integer> selectAllValidGroup();


}
