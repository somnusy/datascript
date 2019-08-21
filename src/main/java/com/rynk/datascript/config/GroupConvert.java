package com.rynk.datascript.config;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;

/**
 * @description 群组实例对象转换
 * @auther Somnus丶y
 * @date 2019/7/17 11:22
 */
@Component
public class GroupConvert {


    public static Long dateFormat(String text){
        if(StringUtils.isEmpty(text)){
            return null;
        }
        TemporalAccessor parse = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").parse(text);
        LocalDateTime from = LocalDateTime.from(parse);
        return Timestamp.valueOf(from).getTime();
    }
}
