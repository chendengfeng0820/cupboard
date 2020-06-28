package com.info.register_login.service;

import com.info.pojo.User;

import java.util.List;

public interface IUserService {
	/**
	 * 增加用户
	 * @param user
	 */
	void saveUser(User user);


	/**
	 * 根据id删除用户
	 * @param user_id
	 */
	void deleteUserById(Long user_id);


	/**
	 * 根据手机号删除用户
	 */
	void deleteUserByTelephone(String user_telephone);


	/**
	 * 修改用户
	 * @param user
	 */
	void updateUser(User user);


	/**
	 * 根据user_id查找用户
	 * @param user_id
	 * @return
	 */
	public User findById(Long user_id);


	/**
	 * 根据user_telephone查找用户
	 * @param user_telephone
	 * @return
	 */
	public User findByTelephone(String user_telephone);


	/**
	 * 查找所有用户
	 * @return
	 */
	List<User> findAll();

	/**
	 * 显示用户详细信息
	 */
	User showInformation(String user_telephone);

}
