package com.info.overbooking.service.Impl;

import com.info.overbooking.mapper.UserOrderMapper;
import com.info.overbooking.service.UserOrderService;
import com.info.pojo.Order;
import com.info.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

/**
 * @ClassName UserOrderServiceImpl
 * @Description TODO
 * @author: cdf
 * @Date: 2020-07-04 14:53
 **/
@Service
public class UserOrderServiceImpl implements UserOrderService {

    @Autowired
    private UserOrderMapper userOrderMapper;

    @Override
    public Long insertUserOrder(Order order) {
        return userOrderMapper.insertUserOrder(order);
    }

    @Override
    public Long insertRelation(Long user_id, Long order_id) {
        return userOrderMapper.insertRelation(user_id,order_id);
    }

    @Override
    public Long insertUserTable(Long user_id, Long board_id) {
        return userOrderMapper.insertUserTable(user_id,board_id);
    }

    @Override
    public void insertFinishTime(Timestamp timestamp) {
        userOrderMapper.insertFinishTime(timestamp);
    }

    @Override
    public Long getOrderId(Long user_id) {
        return userOrderMapper.getOrderId(user_id);
    }

    @Override
    public Timestamp getStartTime(Long order_id) {
        return null;
    }

    @Override
    public User findByUserId(Long user_id) {
        return userOrderMapper.findByUserId(user_id);
    }
}
