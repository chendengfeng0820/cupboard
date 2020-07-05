package com.info;

import com.info.service.SendService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ApplicationContext;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})

public class RabbitmqSendApplication {

    public static void main(String[] args) {
        ApplicationContext ac = SpringApplication.run(RabbitmqSendApplication.class, args);
        SendService service= (SendService) ac.getBean("sendService");
        service.sendMessage("Boot的测试数据");

    }

}
