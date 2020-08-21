package com.info.register_login.mapper;

import com.info.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * 用户后台管理
 */
@Mapper
@Repository
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
	void deleteUserByUserId(Long user_id);


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
	public User findByUserId(Long user_id);


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

}
