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
 * @description groups for mongodb entity
 * @auther Somnus丶y
 * @date 2019/7/17 10:51
 */
@Document(value = "groups")
@CompoundIndexes(
        {
                @CompoundIndex(def = "{'groupId':1}")
        })
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class GroupEntity {

    /**
     * 群id
     */
    @Id
    private String groupId;

    /**
     * 群名称
     */
    private String name;

    /**
     * 人数
     */
    private Integer memberSize;


    /**
     * 群最大容量
     */
    private Integer maxMemberSize;

    /**
     * 群等级
     */
    private Integer lvl = 0;

    /**
     * 进群审核开关0关闭，1开启
     */
    private Integer review = 0;

    /**
     * 群禁言 0.关闭 1 开启
     */
    private String mute = "0";

    /**
     * 群主Id
     */
    private String groupOwnerUserId;

    /**
     * 群主乐信号
     */
    private String groupOwnerNumber;

    /**
     * 群主昵称
     */
    private String groupOwnerNickname;

    /**
     * 群公告
     */
    private String bulletin;

    /**
     * 状态
     */
    private Integer status = 0;

    /**
     * 群头像
     */
    private String avatarUrl = "";

    /**
     * 失效时间
     */
    private Long validTime;

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
