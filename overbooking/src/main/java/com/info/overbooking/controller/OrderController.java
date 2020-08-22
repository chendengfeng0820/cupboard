package com.info.overbooking.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.info.overbooking.service.CostService;
import com.info.overbooking.service.UserOrderService;
import com.info.pojo.Board;
import com.info.pojo.Cost;
import com.info.pojo.Order;
import com.info.pojo.User;
import com.info.register_login.utils.CodeUtil;
import com.info.service.preserve.BoardUsingService;
import com.info.utils.RedisUtil;
import com.info.utils.SnowFlake;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

/**
 * @ClassName OrderController
 * @Description TODO
 * @author: cdf
 * @Date: 2020-07-01 22:30
 **/
@RestController
@Slf4j
public class OrderController {

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private CostService costService;

    @Autowired
    private SnowFlake snowFlake;

    @Autowired
    private CodeUtil codeUtil;

    @Autowired
    private BoardUsingService boardUsingService;

    @Autowired
    private UserOrderService userOrderService;

    /**
     * 用户下单
     * @param jsonObject
     * @return
     */
    @RequestMapping("/order")
    public String order(@RequestBody JSONObject jsonObject) {
        Board board = JSON.parseObject(jsonObject.toString(), Board.class);
        User user = JSON.parseObject(jsonObject.toString(), User.class);
        Long board_id = board.getBoard_id();
        Long user_id = user.getUser_id();

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
        long l1 = snowFlake.nextId();
        long l2 = snowFlake.nextId();
        order.setOrder_id(l1)
                .setOrder_telephone(user1.getUser_telephone())
                .setOrder_username(user1.getUser_username())
                .setOrder_createtime(timestamp)
                .setOrder_location(location);

        String code = codeUtil.generateCode();
        redisUtil.select(2);
        redisUtil.hset(String.valueOf(board_id),String.valueOf(user_id),code);
        log.info("柜子号：" + board_id + "取件码：" + code);

        //orderid
        Long order_id = l1; Long cost_id = l2;

        //userid costid插入到Order_Cost关联表
        costService.insertOrderCost(user_id,cost_id);
        System.out.println(user_id);

        //userid orderid插入到user——order关联表
        userOrderService.insertRelation(user_id,order_id);

        //userid boardid插入到user——board关联表
        userOrderService.insertUserTable(user_id,board_id);

        return "ok";
    }

    /**
     * 结束显示时间花费相关信息
     * @param jsonObject
     * @return
     */
    @RequestMapping("/over")
    public String over(@RequestBody JSONObject jsonObject) throws ParseException {
        Board board = JSON.parseObject(jsonObject.toString(), Board.class);
        User user = JSON.parseObject(jsonObject.toString(), User.class);
        Long board_id = board.getBoard_id();
        Long user_id = user.getUser_id();
        Object o = null;
        //结束时间并插入
        Timestamp finishtime = new Timestamp(System.currentTimeMillis());
        userOrderService.insertFinishTime(finishtime);

        //创建时间获取
        Long orderId = userOrderService.getOrderId(user_id);
        Timestamp startTime = userOrderService.getStartTime(orderId);
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-DD HH:mm:ss");
        Date date1 = sdf.parse(String.valueOf(startTime));
        Date date2 = sdf.parse(String.valueOf(finishtime));
        long timeDifference = date1.getTime() - date2.getTime();
        log.info("保管时间：" + timeDifference);

        //调用花费计算服务进行计算

        Map<String,Object> map = new HashMap<>();

        map.put("用户：" , user_id);
        map.put("柜子号：" , board_id);
        map.put("保管时间", timeDifference);
        map.put("花费：" , o);
        return map.toString();
    }

    /**
     * 确认取包
     * @param jsonObject
     * @return
     */
    @RequestMapping("/confirmover")
    public String confirmOver(@RequestBody JSONObject jsonObject){
        Board board = JSON.parseObject(jsonObject.toString(), Board.class);
        User user = JSON.parseObject(jsonObject.toString(), User.class);
        Long board_id = board.getBoard_id();
        Long user_id = user.getUser_id();
        redisUtil.select(2);
        String hash = (String) redisUtil.getHash(String.valueOf(board_id), String.valueOf(user_id));
        return hash;
    }

}
