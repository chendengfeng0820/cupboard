package com.info.register_login.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.info.register_login.config.SendSmsConfig;
import com.info.register_login.pojo.User;
import com.info.register_login.utils.CodeUtil;
import com.info.register_login.utils.RedisUtil;
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
 * @Description TODO
 * @author: cdf
 * @Date: 2020-06-12 12:10
 **/
@RestController
public class Register {

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private CodeUtil codeUtil;

    @Autowired
    private SendSmsConfig sendSmsConfig;


    @RequestMapping("/register")
    public String register(@RequestBody JSONObject jsonObject) {
        User user = new User();
        JSON.toJSONString(user);
        sendSmsConfig.sendSms(user.getUser_telephone());

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
        return null;
    }


}
