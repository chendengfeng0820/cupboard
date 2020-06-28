package com.info.preserve.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.info.pojo.Board;
import com.info.pojo.User;
import com.info.preserve.service.Impl.BoardUsingServiceImpl;
import com.info.preserve.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName BoardUsingController
 * @Description 柜子的可以情况
 * @author: cdf
 * @Date: 2020-06-28 23:36
 **/
@RestController
public class BoardUsingController {

    @Autowired
    private RedisUtil redisUtil ;

    @Autowired
    private BoardUsingServiceImpl boardUsingService;

    @RequestMapping("/boardusing")
    public String boardUsing(@RequestBody JSONObject jsonObject){
        Board board = JSON.parseObject(jsonObject.toString(), Board.class);
        Long board_id=board.getBoard_id();
        List<Board> availableList = boardUsingService.available();
        redisUtil.select(1);
        Set<String> keys = redisUtil.keys();
        availableList.remove(keys);
        String result = JSON.toJSONString(availableList);
        return result;
    }

    @RequestMapping("/subscribe")
    public String  subscribe(@RequestBody JSONObject jsonObject){
        Board board = JSON.parseObject(jsonObject.toString(), Board.class);
        User user   = JSONObject.parseObject(jsonObject.toString(), User.class);
        Long board_id=board.getBoard_id();
        //redis   1 数据库
        redisUtil.select(1);
        //预约存进redis，设置过期时间2小时
        redisUtil.set(String.valueOf(board_id),0, TimeUnit.HOURS.toHours(2));
        return "subscribe success";
    }

    @RequestMapping("/ttl")
    public String ttl(@RequestBody JSONObject jsonObject){
        Board board = JSON.parseObject(jsonObject.toString(), Board.class);
        Long board_id=board.getBoard_id();
        Long ttl = redisUtil.ttl(String.valueOf(board_id));
        return JSON.toJSONString(ttl);
    }
}
