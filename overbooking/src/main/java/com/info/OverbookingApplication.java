package com.info;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class OverbookingApplication {

    public static void main(String[] args) {
        SpringApplication.run(OverbookingApplication.class, args);
    }

}
