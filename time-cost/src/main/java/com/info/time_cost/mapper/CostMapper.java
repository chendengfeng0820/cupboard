package com.info.time_cost.mapper;

import com.info.pojo.Cost;
import com.info.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CostMapper {

    public Cost findByCostId(Long cost_id);

    public Long insertCost(Cost cost);

    public void changeStatus(Long board_id);

    public String getlocation(Long board_id);

    public Long insertOrderCost(@Param("order_id") Long order_id,@Param("cost_id") Long cost_id);

}
