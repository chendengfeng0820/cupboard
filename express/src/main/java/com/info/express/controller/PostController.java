package com.info.express.controller;

import com.alibaba.fastjson.JSONObject;
import com.info.express.service.PostService;
import com.info.pojo.*;
import com.info.pojo.Package;
import com.info.utils.SnowFlake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.HashMap;

/**
 * @ClassName PostController
 * @Description TODO
 * @author: cdf
 * @Date: 2020-07-18 18:44
 **/
@RestController
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private SnowFlake snowFlake;

    @RequestMapping("/post")
    public String post(@RequestBody JSONObject jsonObject){
        User user   = JSONObject.parseObject(jsonObject.toString(), User.class);
        Package pack  = JSONObject.parseObject(jsonObject.toString(),Package.class);
        Board board = JSONObject.parseObject(jsonObject.toString(),Board.class);
        Long user_id = user.getUser_id();
        Long board_id =board.getBoard_id();

        //包裹数据存入表
        Board boardInfo = postService.getBoardInfo(board_id);
//        Location location = boardInfo.getLocation();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println(timestamp);
        Package packageInfo = new Package();
        long package_id = snowFlake.nextId();
        packageInfo.setPackage_id(package_id)
                   .setPackage_createtime(timestamp)
                   .setPackage_from(pack.getPackage_from())
                   .setPackage_to(pack.getPackage_to());
        postService.insertPackage(packageInfo);
        User userInfo = postService.getUserInfo(user_id);
        postService.insertUser_Package(user_id,package_id);

        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("用户名",userInfo.getUser_username());
        hashMap.put("用户手机号",userInfo.getUser_telephone());
        hashMap.put("快递包裹订单编号",package_id);
        hashMap.put("柜子位置",boardInfo.getLocation());
        hashMap.put("柜子编号",board_id);
        hashMap.put("发到地址",packageInfo.getPackage_to());
        hashMap.put("发出地址",packageInfo.getPackage_from());
        hashMap.put("下单时间",packageInfo.getPackage_createtime());

        System.out.println(hashMap);

        return hashMap.toString();

    }
}
