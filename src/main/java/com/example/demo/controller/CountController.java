package com.example.demo.controller;

import com.example.demo.util.DateUtils;
import com.example.demo.util.RedisOperateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.crypto.Data;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by zrf on 2020/4/13.
 **/
@Slf4j
@RestController
@RequestMapping("/count")
public class CountController {




    @Autowired
    private RedisOperateUtil redisOperateUtil;




    @RequestMapping("/doCount")
    public String doCount(String url,int rand){
        String  data = DateUtils.formatEightDigitsDate(new Date());
        String key = "yongying-"+ url + "-" +   data + "-user:"  + Math.floor((Math.random()*(rand))) ;

        String key2 = "yongying-"+ url +"-viewCount:";

        String count = redisOperateUtil.get(key);
        if(count == null){
            redisOperateUtil.set(key,"true");
            redisOperateUtil.incr(key2);
        }
        return redisOperateUtil.get(key2);
    }

    @RequestMapping("/delete")
    public Set<Object> delete(){

        Set<Object> keys = redisOperateUtil.getKeys("yongying-zengfen*");
        log.info("查询到昨日数据：{}" + keys.size());

        redisOperateUtil.deleteKeys(keys);
        log.info("删除成功");
        return keys;
    }



    @RequestMapping("/addList")
    public List<Object> addList(String i){
        String key = "zhongying-formId-" +  "10086";

        return  redisOperateUtil.rrange(key,0,redisOperateUtil.getListSize(key));
    }

    @RequestMapping("/deleteList")
    public List<Object> deleteList(){
        String key = "zhongying-formId-" +  "10086";
        String string = (String)redisOperateUtil.leftPop(key);
        log.info(string);
        return  redisOperateUtil.rrange(key,0,redisOperateUtil.getListSize(key));
    }

    public static void main(String[] args) throws  Exception{
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-M-d H:m:s");
        // 指定一个日期
        Date date = dateFormat.parse("2020-5-21 0:24:16");
        Date award = dateFormat.parse("2020-5-21 0:0:0");

        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.DAY_OF_MONTH,21);
        c.set(Calendar.HOUR_OF_DAY, 0);
        // 分
        c.set(Calendar.MINUTE, 0);
        // 秒
        c.set(Calendar.SECOND, 0);
        // 毫秒
        c.set(Calendar.MILLISECOND, 0);


        Date endDate = c.getTime();
        c.add(Calendar.MONTH, -1);
        Date beginDate = c.getTime();

        int a = award.compareTo(beginDate);

        int b = award.compareTo(endDate);

        if( a>-1 && b <0){
            System.out.println("在里面");
        }

    }





}
