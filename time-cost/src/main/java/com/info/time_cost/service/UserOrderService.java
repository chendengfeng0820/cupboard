package com.info.time_cost.service;

import com.info.pojo.Order;
import com.info.pojo.User;
import org.apache.ibatis.annotations.Param;

public interface UserOrderService {

    public Long insertUserOrder(Order order);

    public Long insertRelation(Long user_id,Long order_id);

    public Long insertUserTable(Long user_id,Long board_id);

    /**
     * 根据user_id查找用户
     * @param user_id
     * @return
     */
    public User findByUserId(Long user_id);
}
