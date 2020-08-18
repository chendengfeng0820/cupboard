package com.info.fast_mail.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface DistributedMapper {

    public void insertCourier_package(@Param("courier_id")Long courier_id, @Param("package_id")Long package_id);

}
