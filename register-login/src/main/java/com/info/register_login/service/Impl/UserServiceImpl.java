package com.info.register_login.service.Impl;

import com.info.pojo.User;
import com.info.register_login.mapper.UserMapper;
import com.info.register_login.service.IUserService;
import com.info.register_login.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

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

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public void saveUser(User user) {

        //添加到redis中
        redisUtil.hset(user.getUser_telephone(), "user_id", user.getUser_id());
        redisUtil.hset(user.getUser_telephone(), "user_username", user.getUser_username());
        redisUtil.hset(user.getUser_telephone(), "user_telephone", user.getUser_telephone());
        redisUtil.hset(user.getUser_telephone(), "user_password", user.getUser_password());
        redisUtil.hset(user.getUser_telephone(), "user_faceinfo", user.getUser_faceinfo());
        redisUtil.hset(user.getUser_telephone(), "user_createtime", user.getUser_createtime());

        //添加到mysql中
        userMapper.saveUser(user);
    }

    @Override
    public void deleteUserById(Integer user_id) {

        userMapper.deleteUserById(user_id);
    }

    @Override
    public void deleteUserByTelephone(String user_telephone) {

        //从redis中删除
        redisUtil.hdel(user_telephone);

        //从mysql中删除
        userMapper.deleteUserByTelephone(user_telephone);
    }


    /**
     * 更新用户策略：先更新数据表，成功之后，删除原来的缓存，再更新缓存
     */
    @Override
    public void updateUser(User user) {

        //修改mysql数据
        userMapper.updateUser(user);

        //删除redis中数据
        if (redisUtil.hashKey(user.getUser_telephone())) {
            redisUtil.hdel(user.getUser_telephone());
        }

        //添加到redis中
        redisUtil.hset(user.getUser_telephone(), "user_id", user.getUser_id());
        redisUtil.hset(user.getUser_telephone(), "user_username", user.getUser_username());
        redisUtil.hset(user.getUser_telephone(), "user_telephone", user.getUser_telephone());
        redisUtil.hset(user.getUser_telephone(), "user_password", user.getUser_password());
        redisUtil.hset(user.getUser_telephone(), "user_faceinfo", user.getUser_faceinfo());
        redisUtil.hset(user.getUser_telephone(), "user_createtime", user.getUser_createtime());

    }

    @Override
    public User findById(Integer user_id) {

        return userMapper.findById(user_id);
    }

    @Override
    public User findByTelephone(String user_telephone) {

        //redis中查找
        if (redisUtil.hashKey(user_telephone)) {
            Map<Object, Object> hmget = redisUtil.hmget(user_telephone);
            User user = new User();
            user.setUser_id((int) hmget.get("user_id"))
                    .setUser_telephone((String) hmget.get("user_telephone"))
                    .setUser_password((String) hmget.get("user_password"))
                    .setUser_faceinfo((String) hmget.get("user_faceinfo"))
                    .setUser_username((String) hmget.get("user_username"))
                    .setUser_createtime((Date) hmget.get("user_createtime"));
            return user;
        } else {
            return userMapper.findByTelephone(user_telephone);
        }
    }

    @Override
    public List<User> findAll() {
        return userMapper.findAll();
    }

    @Override
    public User showInformation(String user_telephone) {

        //redis中查找
        if (redisUtil.hashKey(user_telephone)) {
            Map<Object, Object> hmget = redisUtil.hmget(user_telephone);
            User user = new User();
            user.setUser_id((int) hmget.get("user_id"))
                    .setUser_telephone((String) hmget.get("user_telephone"))
                    .setUser_password((String) hmget.get("user_password"))
                    .setUser_faceinfo((String) hmget.get("user_faceinfo"))
                    .setUser_username((String) hmget.get("user_username"))
                    .setUser_createtime((Date) hmget.get("user_createtime"));
            return user;
        } else {
            return userMapper.showInformation(user_telephone);
        }
    }
}
