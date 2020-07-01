package com.info.time_cost.service.Impl;

import com.info.time_cost.mapper.OrderMapper;
import com.info.time_cost.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName OrderServiceImpl
 * @Description TODO
 * @author: cdf
 * @Date: 2020-07-01 22:34
 **/
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;
    @Override
    public void changeStatus(Long board_id) {
        orderMapper.changeStatus(board_id);
    }
}
