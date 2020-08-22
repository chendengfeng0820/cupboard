package com.info.overbooking.mapper;

import com.info.pojo.Order;
import com.info.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Mapper
@Component
public interface UserOrderMapper {

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

    public Long insertRelation(@Param("user_id") Long user_id, @Param("order_id") Long order_id);

    /**
     * 根据user_id查找用户
     * @param user_id
     * @return
     */
    public User findByUserId(Long user_id);

    public Long insertUserTable(@Param("user_id") Long user_id, @Param("board_id") Long board_id);
}
