package com.info;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableEurekaClient
@ComponentScan(basePackages = {"com.info.*"})
@EnableCircuitBreaker
@EnableDiscoveryClient //服务发现
public class PreserveApplication {

    public static void main(String[] args) {
        SpringApplication.run(PreserveApplication.class, args);
    }

}
