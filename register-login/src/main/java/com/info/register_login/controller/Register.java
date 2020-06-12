package com.info.register_login.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName Register
 * @Description TODO
 * @author: cdf
 * @Date: 2020-06-12 12:10
 **/
@RestController
public class Register {

	@Autowired
	private RedisTemplate redisTemplate;


	@RequestMapping("/")
	public String register(@RequestBody JSONObject jsonObject){

		return null;
	}




}
