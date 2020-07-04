package com.info.time_cost.service;

import com.info.pojo.Cost;
import com.info.pojo.User;

public interface CostService {

    public Cost findByCostId(Long cost_id);

    public Long insertCost(Cost cost);

    public void changeStatus(Long board_id);

    public String getlocation(Long board_id);

    public Long insertOrderCost(Long order_id,Long cost_id);

}
