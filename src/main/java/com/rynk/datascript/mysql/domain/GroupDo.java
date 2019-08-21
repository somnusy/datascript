package com.rynk.datascript.mysql.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @description
 * @auther Somnus丶y
 * @date 2019/7/17 10:07
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class GroupDo {

    /**
     * 主键key
     */
    private int groupId;

    /**
     * 群名称
     */
    private String name;

    /**
     * 公告
     */
    private String bulletin;

    /**
     * 创建人id
     */
    private int userId;

    /**
     * 群主乐信号
     */
    private String groupOwnerNumber;

    /**
     * 群主昵称
     */
    private String groupOwnerNickname;

    /**
     * 群成员数量
     */
    private int memberSize;

    /**
     * 最大群成员数量
     */
    private int maxMemberSize;

    /**
     * 进群审核: 0 关闭, 1 开启   默认0
     */
    private int review;

    /**
     * 群等级  默认0
     */
    private int lvl;

    /**
     * 群等级失效时间
     */
    private String lvlExpiresAt;

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
     * 头像
     */
    private String portrait;

    /**
     * 禁言  默认0
     */
    private int mute;

}
