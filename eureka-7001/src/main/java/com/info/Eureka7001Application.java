package com.info;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication (exclude = {DataSourceAutoConfiguration.class})
@EnableEurekaServer     //服务端启动类，可以接受别人注册进来
@EnableDiscoveryClient
public class Eureka7001Application {

    public static void main(String[] args) {
        SpringApplication.run(Eureka7001Application.class, args);
    }

}
