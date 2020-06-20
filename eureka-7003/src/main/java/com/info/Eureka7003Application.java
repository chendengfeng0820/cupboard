package com.info;

import org.apache.commons.configuration.DatabaseConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication (exclude = {DataSourceAutoConfiguration.class})
@EnableEurekaServer
@EnableDiscoveryClient
public class Eureka7003Application {

    public static void main(String[] args) {
        SpringApplication.run(Eureka7003Application.class, args);
    }

}
