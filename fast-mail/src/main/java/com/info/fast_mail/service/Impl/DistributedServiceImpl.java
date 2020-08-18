package com.info.fast_mail.service.Impl;

import com.info.fast_mail.mapper.DistributedMapper;
import com.info.fast_mail.service.DistributedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName DistributedServiceImpl
 * @Description TODO
 * @author: cdf
 * @Date: 2020-08-19 05:42
 **/
@Service
public class DistributedServiceImpl implements DistributedService {

    @Autowired
    private DistributedMapper distributedMapper;
    @Override
    public void insertCourier_package(Long courier_id, Long package_id) {
        distributedMapper.insertCourier_package(courier_id,package_id);
    }
}
