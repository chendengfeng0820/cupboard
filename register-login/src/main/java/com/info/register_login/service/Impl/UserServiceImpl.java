package com.info.register_login.service.Impl;

import com.info.register_login.mapper.UserMapper;
import com.info.register_login.pojo.User;
import com.info.register_login.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName UserServiceImpl
 * @Description TODO
 * @author: cdf
 * @Date: 2020-06-12 16:03
 **/
@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private UserMapper userMapper;

	@Override
	public void saveUser(User user) {
		userMapper.saveUser(user);
	}

	@Override
	public void deleteUserById(Integer user_id) {
		userMapper.deleteUserById(user_id);
	}

	@Override
	public void deleteUserByTelephone(String user_telephone) {
		userMapper.deleteUserByTelephone(user_telephone);
	}

	@Override
	public void updateUser(User user) {
		userMapper.updateUser(user);
	}

	@Override
	public User findById(Integer user_id) {
		return userMapper.findById(user_id);
	}

	@Override
	public User findByTelephone(Integer user_telephone) {
		return userMapper.findByTelephone(user_telephone);
	}

	@Override
	public List<User> findAll() {
		return userMapper.findAll();
	}

	@Override
	public User showInformation(String user_telephone) {
		return userMapper.showInformation(user_telephone);
	}
}
