package com.info.register_login.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.info.register_login.pojo.User;
import com.info.register_login.utils.RedisUtil;
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
public class LoginController {

	@Autowired
	private RedisUtil redisUtil;

	@RequestMapping("/login")
	public String login(@RequestBody JSONObject jsonObject){
		String s = null;
		User user = JSON.parseObject(jsonObject.toString(), User.class);
		String telephone = user.getUser_telephone();
		String password = user.getUser_password();
		if (redisUtil.hashKey(telephone)){
			String passwordcode=String.valueOf(redisUtil.getHash(telephone,"password")) ;
			if (password.equals(passwordcode)){
				return s = "t" ;
			}else {
				return s = "对不起，密码错误" ;
			}
		}else {
			return s = "对不起，不存在此用户" ;
		}
	}
}
