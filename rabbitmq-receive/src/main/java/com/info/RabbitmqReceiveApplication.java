package com.info;

import com.info.service.ReceiveService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.ApplicationContext;

import java.util.HashSet;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class RabbitmqReceiveApplication {

    public static void main(String[] args) {
        ApplicationContext ac = SpringApplication.run(RabbitmqReceiveApplication.class, args);
        ReceiveService service= (ReceiveService) ac.getBean("receiveService");
//        service.receive();
    }

}
