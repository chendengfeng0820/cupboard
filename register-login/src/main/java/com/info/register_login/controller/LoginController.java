package com.info.register_login.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.info.pojo.User;
import com.info.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName Login
 * @Description  登录
 * @author: cdf
 * @Date: 2020-06-12 15:20
 **/
@RestController
@Slf4j
public class LoginController {

	@Autowired
	private RedisUtil redisUtil;

	@RequestMapping("/login")
	public String login(@RequestBody JSONObject jsonObject){
        User user = JSON.parseObject(jsonObject.toString(), User.class);
		String telephone = user.getUser_telephone();
		String password = user.getUser_password();
		//判断是否存在key 并且 value相等
		if (redisUtil.hashKey(telephone)){
			String passwordcode=String.valueOf(redisUtil.getHash(telephone,"password")) ;
			if (password.equals(passwordcode)){
				return "t" ;
			}else {
				return "对不起，密码错误" ;
			}
		}else {
			return "对不起，不存在此用户" ;
		}
	}
}
