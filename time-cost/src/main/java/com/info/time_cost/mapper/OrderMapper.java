package com.info.time_cost.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper {

    public void changeStatus(Long board_id);
}
