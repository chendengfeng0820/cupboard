package com.info.register_login.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.info.pojo.User;
import com.info.register_login.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: cdf
 * @create: 2020-01-13 00:33
 **/
@RestController
public class UserController {

	@Autowired
	private IUserService userService;

	@RequestMapping("/findAll")
	public String findAll() {
		List<User> users = userService.findAll();
		String Json = JSON.toJSONString(users);
		return Json;
//		return userService.findAll();
	}

	@RequestMapping("/findById/{id1}")
	public String findByNumber(@RequestBody JSONObject jsonObject,@PathVariable("id1") Integer id1) {
		User user = JSON.parseObject(jsonObject.toString(), User.class);
		int id = user.getUser_id();
		User user1 = userService.findById(id);
		String Json = JSON.toJSONString(user1);
		return Json;
	}

	@RequestMapping("/saveUser")
	public String saveUser(@RequestBody JSONObject jsonObject) {
		User user = JSON.parseObject(jsonObject.toString(), User.class);
		user.setUser_id(user.getUser_id())
				.setUser_username(user.getUser_username())
				.setUser_password(user.getUser_password())
				.setUser_telephone(user.getUser_telephone())
				.setUser_faceinfo(user.getUser_faceinfo())
				.setUser_createtime(user.getUser_createtime());
		userService.saveUser(user);
		return "t";
	}


	@RequestMapping("/deleteUserById")
	public String deleteUserById(@RequestBody JSONObject jsonObject) {
		User user = JSON.parseObject(jsonObject.toString(), User.class);
		int id = user.getUser_id();
		userService.deleteUserById(id);
		return "t";
	}

	@RequestMapping("/deleteUserByTelephone")
	public String deleteUserByTelephone(@RequestBody JSONObject jsonObject) {
		User user = JSON.parseObject(jsonObject.toString(), User.class);
		String telephone = user.getUser_telephone();
		userService.deleteUserByTelephone(telephone);
		return "t";
	}

	@RequestMapping("/updateUser")
	public String updateUser(@RequestBody JSONObject jsonObject) {
		User user = JSON.parseObject(jsonObject.toString(), User.class);
		user.setUser_id(user.getUser_id())
				.setUser_username(user.getUser_username())
				.setUser_password(user.getUser_password())
				.setUser_telephone(user.getUser_telephone())
				.setUser_faceinfo(user.getUser_faceinfo())
				.setUser_createtime(user.getUser_createtime());
		userService.updateUser(user);

		return "t";
	}
}




