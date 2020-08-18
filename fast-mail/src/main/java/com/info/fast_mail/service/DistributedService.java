package com.info.fast_mail.service;

import org.springframework.data.repository.query.Param;

public interface DistributedService {

    public void insertCourier_package(@Param("courier_id")Long courier_id,@Param("package_id")Long package_id);

}
