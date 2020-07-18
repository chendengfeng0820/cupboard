package com.info.service.Impl;

import com.info.service.ReceiveService;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
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

    @Override
    @RabbitListener (queues = "bootDirectQueue")
    public void directReceive(String message) {
        System.out.println("监听器接受的消息：" + message);
    }

    @Override
    @RabbitListener (bindings = {//QueueBinding注解要完成队列和交换机的绑定
            @QueueBinding(value = @Queue(),// @Queue创建一个队列（没有指定就创建一个随机的队列）
            exchange = @Exchange(name = "fanoutExchange" , type = "fanout"))//创建一个交换机
    })
    public void fanoutReceive01(String message) {
        System.out.println("01监听器接受的消息：" + message);
    }

    @Override
    @RabbitListener (bindings = {//QueueBinding注解要完成队列和交换机的绑定
            @QueueBinding(value = @Queue(),// @Queue创建一个队列（没有指定就创建一个随机的队列）
                    exchange = @Exchange(name = "fanoutExchange" , type = "fanout"))//创建一个交换机
    })
    public void fanoutReceive02(String message) {
        System.out.println("02监听器接受的消息：" + message);
    }

    @Override
    @RabbitListener (bindings = {//QueueBinding注解要完成队列和交换机的绑定
            @QueueBinding(value = @Queue("topic01"), key = {"aa"},// @Queue创建一个队列（没有指定就创建一个随机的队列）
                    exchange = @Exchange(name = "topicExchange" , type = "topic"))//创建一个交换机
    })
    public void topicReceive01(String message) {
        System.out.println("topic01数据  aa ：" + message);
    }

    @Override
    @RabbitListener (bindings = {//QueueBinding注解要完成队列和交换机的绑定
            @QueueBinding(value = @Queue("topic02"), key = {"aa.*"},// @Queue创建一个队列（没有指定就创建一个随机的队列）
                    exchange = @Exchange(name = "topicExchange" , type = "topic"))//创建一个交换机
    })
    public void topicReceive02(String message) {
        System.out.println("topic02数据  aa.* ：" + message);
    }

    @Override
    @RabbitListener (bindings = {//QueueBinding注解要完成队列和交换机的绑定
            @QueueBinding(value = @Queue("topic03"), key = {"aa.#"},// @Queue创建一个队列（没有指定就创建一个随机的队列）
                    exchange = @Exchange(name = "topicExchange" , type = "topic"))//创建一个交换机
    })
    public void topicReceive03(String message) {
        System.out.println("topic03数据  aa.# ：" + message);
    }
}
