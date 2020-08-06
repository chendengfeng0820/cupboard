package com.info.preserve.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.info.pojo.Board;
import com.info.pojo.User;
import com.info.preserve.service.Impl.BoardUsingServiceImpl;
import com.info.preserve.utils.TimeShift;
import com.info.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @ClassName BoardUsingController
 * @Description 柜子的使用情况(预订)
 * @author: cdf
 * @Date: 2020-06-28 23:36
 **/
@RestController
public class BoardUsingController {

    @Autowired
    private RedisUtil redisUtil ;

    @Autowired
    private TimeShift timeShift;

    @Autowired
    private BoardUsingServiceImpl boardUsingService;

    @RequestMapping("/boardusing")
    public String boardUsing(){

        //数据库中查到所有没有存放东西的柜子
        List<Board> availableList = boardUsingService.available();
        ArrayList<String> list = new ArrayList<>();
        for (Board board:availableList) {
            list.add(String.valueOf(board.getBoard_id()));
        }
        System.out.println(list);

        //切换redis redis所有的key 获取到被预订的柜子
        redisUtil.select(1);
        Set<String> keys = redisUtil.keys();

        //set转为list
        //ArrayList<String> keyslist = new ArrayList<>(keys);
        System.out.println(keys);

        //list与keylist差集  获取到所有没有被预订且空的柜子
        list.removeAll(keys);
        System.out.println(list);
        String result = JSON.toJSONString(list);
        return result;
    }

    @RequestMapping("/subscribe")
    public String  subscribe(@RequestBody JSONObject jsonObject){
        Board board = JSON.parseObject(jsonObject.toString(), Board.class);
        User user   = JSONObject.parseObject(jsonObject.toString(), User.class);
        Long board_id=board.getBoard_id();
        //切换redis 1 数据库
        redisUtil.select(1);

        //预约柜子号存进redis，设置过期时间2小时
        redisUtil.set(String.valueOf(board_id),0,TimeUnit.HOURS.toSeconds(2));
        if (redisUtil.hashKey(String.valueOf(board_id))){
            return "subscribe success";
        }else {
            return "subscribe fail";
        }

    }

    //查看预约柜子的剩余预约到期时间
    @RequestMapping("/ttl")
    public String ttl(@RequestBody JSONObject jsonObject){
        Board board = JSON.parseObject(jsonObject.toString(), Board.class);
        Long board_id=board.getBoard_id();
        //获取预约剩余过期时间
        Long ttl = redisUtil.ttl(String.valueOf(board_id));
        return JSON.toJSONString(timeShift.conversion(ttl));
    }

}
