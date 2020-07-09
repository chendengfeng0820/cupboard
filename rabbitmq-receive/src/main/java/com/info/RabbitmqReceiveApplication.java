package com.info;

import com.info.service.ReceiveService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class RabbitmqReceiveApplication {

    public static void main(String[] args) {
        ApplicationContext ac = SpringApplication.run(RabbitmqReceiveApplication.class, args);
        ReceiveService service= (ReceiveService) ac.getBean("sendService");
        service.receive();
    }

}
