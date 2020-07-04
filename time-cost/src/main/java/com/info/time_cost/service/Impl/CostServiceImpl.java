package com.info.time_cost.service.Impl;

import com.info.pojo.Cost;
import com.info.pojo.User;
import com.info.time_cost.mapper.CostMapper;
import com.info.time_cost.service.CostService;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName CostServiceImpl
 * @Description TODO
 * @author: cdf
 * @Date: 2020-07-04 14:52
 **/
@Service
public class CostServiceImpl implements CostService {

    @Autowired
    private CostMapper costMapper;

    @Override
    public Cost findByCostId(Long cost_id) {
        return costMapper.findByCostId(cost_id);
    }

    @Override
    public Long insertCost(Cost cost) {
        return costMapper.insertCost(cost);
    }

    @Override
    public void changeStatus(Long board_id) {
        costMapper.changeStatus(board_id);
    }

    @Override
    public String getlocation(Long board_id) {
        return costMapper.getlocation(board_id);
    }

    @Override
    public Long insertOrderCost(Long order_id, Long cost_id) {
        return costMapper.insertOrderCost(order_id,cost_id);
    }
}
