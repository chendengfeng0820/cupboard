package com.info.register_login;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient //服务发现

public class RegisterLoginApplication {

    public static void main(String[] args) {
        SpringApplication.run(RegisterLoginApplication.class, args);
    }

}
