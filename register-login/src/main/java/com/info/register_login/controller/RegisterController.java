package com.info.register_login.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;
import com.info.pojo.User;
import com.info.register_login.config.SendSmsConfig;
import com.info.register_login.utils.CodeUtil;
import com.info.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.bouncycastle.asn1.x500.style.RFC4519Style.telephoneNumber;

/**
 * @ClassName Register
 * @Description 注册
 * @author: cdf
 * @Date: 2020-06-12 12:10
 **/
@RestController
@Slf4j
public class RegisterController {

    @Autowired
    private SendSmsConfig sendSmsConfig;


    @RequestMapping("/register")
    public String register(@RequestBody JSONObject jsonObject) {
        User user = JSON.parseObject(jsonObject.toString(), User.class);
        String s = sendSmsConfig.sendSms(user.getUser_telephone());
        //解析json  获取阿里云接口发送成功标志进行下一步操作
        JSONObject jsonObject1=JSON.parseObject(s);
        //获取接口data状态信息
        JSONObject jsonObject2=JSONObject.parseObject(jsonObject1.get("data").toString());
        String message = jsonObject2.get("Message").toString();
        if (("OK").equals(message)) {
            log.info("发送成功：" + user.getUser_telephone());
            return JSON.toJSONString("已发送");
        } else {
            return JSON.toJSONString("对不起，发送失败，请稍候重试");


            /**
             * 反射方法调用
             */
//        try {
//            Class<?> aClass = Class.forName("com.info.register_login.config.SendSmsConfig");
//            Method method = aClass.getMethod("sendSms", String.class);
//            Object instance = aClass.newInstance();
//            method.invoke(instance, user.getUser_telephone())
//        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
//            e.printStackTrace();
//        }
        }


    }
}
