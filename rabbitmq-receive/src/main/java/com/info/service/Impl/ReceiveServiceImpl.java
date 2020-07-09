package com.info.service.Impl;

import com.info.service.ReceiveService;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName SendServiceImpl
 * @Description TODO
 * @author: cdf
 * @Date: 2020-07-05 22:04
 **/
@Service("receiveService")
public class ReceiveServiceImpl implements ReceiveService {

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Override
    public void receive() {
        String message = (String) amqpTemplate.receiveAndConvert("bootDirectQueue");
        System.out.println(message);
    }
}
