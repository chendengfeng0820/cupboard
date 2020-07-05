package com.info.time_cost.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.info.pojo.Board;
import com.info.pojo.Courier;
import com.info.pojo.User;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName ExpressController
 * @Description TODO
 * @author: cdf
 * @Date: 2020-07-05 16:07
 **/
@RestController
public class ExpressController {


    @RequestMapping("/express")
    public String finish(@RequestBody JSONObject jsonObject){
        Courier courier = JSON.parseObject(jsonObject.toString(),Courier.class);
        User user = JSON.parseObject(jsonObject.toString(),User.class);
        Board board = JSON.parseObject(jsonObject.toString(),Board.class);
        Long board_id = board.getBoard_id();
        Long user_id = user.getUser_id();

        /**
         * 数据库的状态操作以及算钱
         */


        return "";
    }
}
