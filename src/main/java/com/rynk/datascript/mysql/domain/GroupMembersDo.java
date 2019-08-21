package com.rynk.datascript.mysql.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @description
 * @auther Somnus丶y
 * @date 2019/7/17 10:11
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class GroupMembersDo {


    /**
     * 群昵称
     */
    private String nickname;

    /**
     * 群id
     */
    private int groupId;

    /**
     * 用户id
     */
    private int userId;

    /**
     * 用户乐信号  from users
     */
    private String number;

    /**
     * 成员头像
     */
    private String avatarUrl;

    /**
     * 角色: 0：群主 1管理员 2普通群成员
     */
    private int role;

    /**
     * 群消息免打扰: 0 关闭, 1 开启
     */
    private int notDisturb;

    /**
     * 邀请者id
     */
    private String invitedUserId;

    /**
     * 邀请者昵称
     */
    private String invitedUserNickName;


    /**
     * 用户进群状态（0，进群；1，待审核；2，已拒绝）
     */
    private Integer inGroupStatus;


    /**
     * 最后一次删除操作
     */
    private String deletedAt;

    /**
     * 最后一次创建操作
     */
    private String createdAt;

    /**
     * 最后一次更改操作
     */
    private String updatedAt;

    /**
     * 禁言  默认0
     */
    private int mute;

}
