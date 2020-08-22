package com.info.overbooking.service;

import com.info.pojo.Order;
import com.info.pojo.User;

import java.sql.Timestamp;

public interface UserOrderService {

    /**
     * 插入结束时间
     * @param timestamp
     */
    public void insertFinishTime(Timestamp timestamp);

    /**
     * userid查找orderid
     * @param user_id
     * @return
     */
    public Long getOrderId(Long user_id);

    /**
     * 订单创建时间
     * @param order_id
     * @return
     */
    public Timestamp getStartTime(Long order_id);
    public Long insertUserOrder(Order order);

    public Long insertRelation(Long user_id, Long order_id);

    public Long insertUserTable(Long user_id, Long board_id);


    /**
     * 根据user_id查找用户
     * @param user_id
     * @return
     */
    public User findByUserId(Long user_id);
}
