package com.info.register_login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName Login
 * @Description TODO
 * @author: cdf
 * @Date: 2020-06-12 15:20
 **/
@RestController
public class Login {

	@Autowired
	private RedisTemplate redisTemplate;


}
