package com.info.time_cost.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.info.pojo.Board;
import com.info.time_cost.service.OrderService;
import com.info.time_cost.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName OrderController
 * @Description TODO
 * @author: cdf
 * @Date: 2020-07-01 22:30
 **/
@RestController
public class OrderController {

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private OrderService orderService;

    @RequestMapping("/change")
    public String order(@RequestBody JSONObject jsonObject) {
        Board board = JSON.parseObject(jsonObject.toString(), Board.class);
        Long board_id = board.getBoard_id();
        redisUtil.select(1);
        redisUtil.set(String.valueOf(board_id),1);
        orderService.changeStatus(board_id);
        return "ok";
    }



}
