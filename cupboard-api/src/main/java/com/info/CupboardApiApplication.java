package com.info;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.info"})

public class CupboardApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(CupboardApiApplication.class, args);
    }

}
