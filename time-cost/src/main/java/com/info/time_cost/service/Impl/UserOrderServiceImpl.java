package com.info.time_cost.service.Impl;

import com.info.pojo.Order;
import com.info.pojo.User;
import com.info.time_cost.mapper.UserOrderMapper;
import com.info.time_cost.service.UserOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public User findByUserId(Long user_id) {
        return userOrderMapper.findByUserId(user_id);
    }
}
