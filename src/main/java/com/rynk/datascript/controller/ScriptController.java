package com.rynk.datascript.controller;

import com.rynk.datascript.mongo.entity.GroupEntity;
import com.rynk.datascript.mysql.domain.GroupDo;
import com.rynk.datascript.mysql.domain.GroupMembersDo;
import com.rynk.datascript.service.IGroupsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @description
 * @auther Somnus丶y
 * @date 2019/7/17 10:18
 */
@RestController
@RequestMapping("/script")
public class ScriptController {



    @Autowired
    private IGroupsService groupsService;

    @RequestMapping(value = "/mysql/groups",method = RequestMethod.GET)
    public List<GroupDo> selectGroups(){
        return groupsService.selectGroups();
    }


    @RequestMapping(value = "/mongo/groups",method = RequestMethod.POST)
    public String saveMongoGroups(){
        GroupEntity entity = new GroupEntity();
        entity.setName("11");
        return groupsService.saveGroupsMongo(entity);
    }

    @RequestMapping(value = "/mysql2Mongo4Groups",method = RequestMethod.GET)
    public String mysql2Mongo4Groups(){
        try {
            groupsService.mysql2Mongo4Groups();
        }catch (Exception e){
            e.printStackTrace();
            return e.getMessage();
        }
        return "success";
    }

    @RequestMapping(value = "/mysql/groupMembers",method = RequestMethod.GET)
    public List<GroupMembersDo> selectGroupMembers(){
        return groupsService.selectGroupMembers(0,1000);
    }



    @RequestMapping(value = "/mysql2Mongo4GroupMembers",method = RequestMethod.GET)
    public String mysql2Mongo4GroupMembers(){
        try {
            groupsService.mysql2Mongo4GroupMember();
        }catch (Exception e){
            e.printStackTrace();
            return e.getMessage();
        }
        return "success";
    }




}
