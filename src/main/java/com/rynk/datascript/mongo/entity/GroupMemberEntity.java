package com.rynk.datascript.mongo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @description
 * @auther Somnus丶y
 * @date 2019/7/17 12:35
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Document(collection = "group_members")
@CompoundIndexes(
        {
                @CompoundIndex(def = "{'userId':1,'groupId':1}")
        })
public class GroupMemberEntity {

    @Id
    private String id;

    //群id
    private String groupId;

    // 成员Id
    private String userId;

    // 成员乐信号
    private String number;

    // 成员群昵称
    private String nickname;

    //成员头像
    private String avatarUrl;

    // 成员角色：0：群主 1管理员 2普通群成员
    private Integer role;

    //消息免打扰，0关闭，1开启，默认关闭
    private Integer notDisturb = 0;

    //保存到通讯录0关闭，1开启，默认关闭  todo 无
    private Integer savePhoneBook = 0;

    //显示群成员昵称，0关闭，1开启 todo 无
    private Integer displayGroupNickName = 1;

    //聊天背景图片 todo 无
    private String chatBackGroundUrl;

    //邀请者id
    private String invitedUserId;

    //邀请者昵称
    private String invitedUserNickName;

    //成员禁言 0.关闭 1 开启
    private String mute = "0";

    //用户进群状态（0，进群；1，待审核；2，已拒绝）
    private Integer inGroupStatus;

    //审核者id todo 无
    private String auditingUserId;

    //审核者昵称 todo 无
    private String auditingUserNickName;

    //进群确认来源（1，入群审核；2、自己确认） todo 无
    private Integer inGroupConfirmType;


    /**
     * 创建时间
     */
    protected Long createdTime;

    /**
     * 更新时间
     */
    protected Long modifyTime;

    /**
     * 创建人
     */
    protected String createdUserId;

    /**
     * 更新人
     */
    protected String modifyUserId;

}
