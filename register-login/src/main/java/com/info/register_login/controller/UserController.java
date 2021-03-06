package com.info.register_login.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.info.pojo.User;
import com.info.register_login.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author: cdf
 * @Description 用户后台管理
 * @create: 2020-01-13 00:33
 **/
@RestController
@Slf4j
public class UserController {

	@Autowired
	private UserService userService;

	/**
	 * 查询所有用户
	 * @return
	 */
	@RequestMapping("/findAll")
	public String findAll() {
		List<User> users = userService.findAll();
		String json = JSON.toJSONString(users);
		return json;
	}


	/**
	 * 根据id查找用户
	 * @return
	 */
	@RequestMapping("/findById")
	public String findByNumber(@RequestBody JSONObject jsonObject) {
		User user = JSON.parseObject(jsonObject.toString(), User.class);
		Long id = user.getUser_id();
		User user1 = userService.findByUserId(id);
		String Json = JSON.toJSONString(user1);
		return Json;
	}

	/**
	 * 添加用户
	 * @param jsonObject
	 * @return
	 */
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


	/**
	 * 根据id删除用户
	 * @param jsonObject
	 * @return
	 */
	@RequestMapping("/deleteUserById")
	public String deleteUserById(@RequestBody JSONObject jsonObject) {
		User user = JSON.parseObject(jsonObject.toString(), User.class);
		Long id = user.getUser_id();
		userService.deleteUserByUserId(id);
		return "t";
	}


	/**
	 * 更新用户
	 * @param jsonObject
	 * @return
	 */
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




