package com.info.time_cost.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.info.pojo.Board;
import com.info.pojo.Cost;
import com.info.pojo.Order;
import com.info.pojo.User;
import com.info.service.preserve.BoardUsingService;
import com.info.time_cost.service.CostService;
import com.info.time_cost.service.UserOrderService;
import com.info.utils.RedisUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

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
    private CostService costService;

    @Autowired
    private UserOrderService userOrderService;

    @RequestMapping("/order")
    public String order(@RequestBody JSONObject jsonObject) {
        Board board = JSON.parseObject(jsonObject.toString(), Board.class);
        User user = JSON.parseObject(jsonObject.toString(), User.class);
        Long board_id = board.getBoard_id();
        Long user_id = user.getUser_id();
        redisUtil.select(1);
        redisUtil.set(String.valueOf(board_id),1);

        //修改boardlocation表所选柜子的状态
        costService.changeStatus(board_id);
        //获取柜子id的location
        String location = costService.getlocation(board_id);

        //添加useorder表中下单的信息 orderid oderphone orderusername ordercreatetime orderlocation
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        //simpleDateFormat返回string  sql  datetime
//        String simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis());

        //得到user——id对应的user信息
        User user1 = userOrderService.findByUserId(user_id);

        Order order = new Order();
        Cost cost = new Cost();
        cost.setCost_createtime(timestamp);
        order.setOrder_telephone(user1.getUser_telephone())
                .setOrder_username(user1.getUser_username())
                .setOrder_createtime(timestamp)
                .setOrder_location(location);

        //获取自增的orderid
        Long order_id = userOrderService.insertUserOrder(order);

        //获取自增的costid
        Long insertCostId = costService.insertCost(cost);

        //userid costid插入到Order_Cost关联表
        costService.insertOrderCost(user_id,insertCostId);
        System.out.println(user_id);

        //userid orderid插入到user——order关联表
        userOrderService.insertRelation(user_id,order_id);

        //userid boardid插入到user——board关联表
        userOrderService.insertUserTable(user_id,board_id);

        return "ok";
    }






}
