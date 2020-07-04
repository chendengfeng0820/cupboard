package com.info.time_cost.mapper;

import com.info.pojo.Order;
import com.info.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface UserOrderMapper {

    public Long insertUserOrder(Order order);

    public Long insertRelation(@Param("user_id") Long user_id,@Param("order_id")  Long order_id);

    /**
     * 根据user_id查找用户
     * @param user_id
     * @return
     */
    public User findByUserId(Long user_id);

    public Long insertUserTable(@Param("user_id") Long user_id,@Param("board_id") Long board_id);
}
