package com.info.service.Impl;

import com.info.service.SendService;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName SendServiceImpl
 * @Description TODO
 * @author: cdf
 * @Date: 2020-07-05 22:04
 **/
@Service("sendService")
public class SendServiceImpl implements SendService {

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Override
    public void sendMessage(String message) {
        /**
         * 发送消息
         * 参数 1 为交换机名
         * 参数 2 为RoutingKey
         * 参数 3 为我们的具体发送的消息数据
         */
        amqpTemplate.convertAndSend("bootDirectExchange","bootDirectRoutingKey",message);
    }

    @Override
    public void sendFanoutMessage(String message) {
        amqpTemplate.convertAndSend("fanoutExchange","",message);
    }

    @Override
    public void sendtopicMessage(String message) {
        amqpTemplate.convertAndSend("topicExchange","aa",message);
    }

}
