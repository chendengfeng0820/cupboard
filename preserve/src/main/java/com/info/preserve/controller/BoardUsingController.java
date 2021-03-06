package com.info.preserve.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.info.pojo.Board;
import com.info.pojo.User;
import com.info.preserve.service.Impl.BoardUsingServiceImpl;
import com.info.preserve.utils.TimeShift;
import com.info.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.GeoCoordinate;
import redis.clients.jedis.GeoRadiusResponse;
import redis.clients.jedis.GeoUnit;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName BoardUsingController
 * @Description 柜子的使用情况(预订)
 * @author: cdf
 * @Date: 2020-06-28 23:36
 **/
@RestController
@Slf4j
public class BoardUsingController {

    @Autowired
    private RedisUtil redisUtil ;

    @Autowired
    private TimeShift timeShift;

    @Autowired
    private BoardUsingServiceImpl boardUsingService;


    /**
     * 可使用的柜子
     * @return
     */
    @RequestMapping("/boardusing")
    public String boardUsing(){

        //查到所有没有存放东西的柜子
        List<Board> availableList = boardUsingService.available();
        ArrayList<String> list = new ArrayList<>();
        for (Board board:availableList) {
            list.add(String.valueOf(board.getBoard_id()));
        }
        log.info("可存放的柜子：" + list);

        //切换redis redis所有的key 获取到被预订的柜子
        redisUtil.select(1);
        Set<String> keys = redisUtil.keys();

        //set转为list
        //ArrayList<String> keyslist = new ArrayList<>(keys);
        log.info("预订的柜子：" + keys);

        //list与keylist差集  获取到所有没有被预订且空的柜子
        list.removeAll(keys);
        log.info("可存放的柜子：" + list);
        String result = JSON.toJSONString(list);
        return result;
    }

    @RequestMapping("/subscribe")
    public String  subscribe(@RequestBody JSONObject jsonObject){
        Board board = JSON.parseObject(jsonObject.toString(), Board.class);
        User user   = JSONObject.parseObject(jsonObject.toString(), User.class);
        Long board_id = board.getBoard_id();
        Long user_id = user.getUser_id();
        //切换redis 1 数据库
        redisUtil.select(1);

        //预约柜子号存进redis，设置过期时间2小时
        redisUtil.set(String.valueOf(board_id),String.valueOf(user_id),TimeUnit.HOURS.toSeconds(2));
        if (redisUtil.hashKey(String.valueOf(board_id))){
            log.info("预订成功：" + board_id);
            return "subscribe success";
        }else {
            log.info("预订失败：" + board_id );
            return "subscribe fail";
        }

    }

    /**
     * 查看与当前位置相近20km内的柜子信息
     * @param jsonObject
     * @return
     */
    @RequestMapping("/getCoordinate")
    public String coordinate(@RequestBody JSONObject jsonObject){
        User user = JSON.parseObject(jsonObject.toString(), User.class);
        Long user_id = user.getUser_id();
        String user_coordinate = user.getUser_coordinate();

        Jedis jedis = new Jedis();
        //对经纬度进行截取操作
        int comma = user_coordinate.indexOf(",");
        String pre = user_coordinate.substring(0,comma);
        String last = user_coordinate.substring(comma+1 ,user_coordinate.length()-1);
        log.info("经度为：" + pre);    log.info("纬度为：" + last);
        //GeoCoordinate geoCoordinate = new GeoCoordinate(Double.valueOf(pre), Double.valueOf(last));
        //添加用户位置经纬度
        jedis.geoadd("china:city" , Double.valueOf(pre), Double.valueOf(last), String.valueOf(user_id));
        //用户20km范围内所有的柜子组
        List<GeoRadiusResponse> georadius = jedis.georadius("china:city", Double.valueOf(pre), Double.valueOf(last), 20, GeoUnit.KM);
        log.info("距离内的位置" + georadius);
        //移除用户位置信息
        jedis.zrem("china:city",String.valueOf(user_id));
        return georadius.toString();

    }

    //查看预约柜子的剩余预约到期时间
    @RequestMapping("/ttl")
    public String ttl(@RequestBody JSONObject jsonObject){
        Board board = JSON.parseObject(jsonObject.toString(), Board.class);
        Long board_id = board.getBoard_id();
        //获取预约剩余过期时间
        Long ttl = redisUtil.ttl(String.valueOf(board_id));
        log.info(board_id + "剩余时间：" + ttl);
        return JSON.toJSONString(timeShift.conversion(ttl));
    }

//    @GetMapping(value = "/payment/discovery")
//    public Object discovery()
//    {
//        List<String> services = discoveryClient.getServices();
//        for (String element : services) {
//            log.info("*****element: "+element);
//        }
//
//        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
//        for (ServiceInstance instance : instances) {
//            log.info(instance.getServiceId()+"\t"+instance.getHost()+"\t"+instance.getPort()+"\t"+instance.getUri());
//        }
//
//        return this.discoveryClient;
//    }

}
