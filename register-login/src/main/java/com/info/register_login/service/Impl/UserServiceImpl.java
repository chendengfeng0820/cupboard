package com.info.register_login.service.Impl;

import com.info.pojo.User;
import com.info.register_login.mapper.UserMapper;
import com.info.register_login.service.UserService;
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
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public void saveUser(User user) {
        userMapper.saveUser(user);
    }

    @Override
    public void deleteUserByUserId(Long user_id) {
        userMapper.deleteUserByUserId(user_id);
    }

    /**
     * 更新用户策略：先更新数据表，成功之后，删除原来的缓存，再更新缓存
     */
    @Override
    public void updateUser(User user) {
        userMapper.updateUser(user);
    }

    @Override
    public User findByUserId(Long user_id) {
        return userMapper.findByUserId(user_id);
    }

    @Override
    public User findByTelephone(String user_telephone) {
        return userMapper.findByTelephone(user_telephone);
    }

    @Override
    public List<User> findAll() {
        return userMapper.findAll();
    }

}
