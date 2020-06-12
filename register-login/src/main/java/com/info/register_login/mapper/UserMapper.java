package com.info.register_login.mapper;

import com.info.register_login.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface UserMapper {
	/**
	 * 增加用户
	 * @param user
	 */
	void saveUser(User user);


	/**
	 * 根据id删除用户
	 * @param user_id
	 */
	void deleteUserById(Integer user_id);


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
	public User findById(Integer user_id);


	/**
	 * 根据user_telephone查找用户
	 * @param user_telephone
	 * @return
	 */
	public User findByTelephone(Integer user_telephone);


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
