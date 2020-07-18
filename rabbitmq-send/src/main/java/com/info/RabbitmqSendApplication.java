package com.info;

import com.info.service.SendService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class RabbitmqSendApplication {

    public static void main(String[] args) {
        ApplicationContext ac = SpringApplication.run(RabbitmqSendApplication.class, args);
//        SendService service= (SendService) ac.getBean("sendService");
//        service.sendFanoutMessage("Boot的测试数据");

    }

}
