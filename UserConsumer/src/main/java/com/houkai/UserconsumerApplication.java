package com.houkai;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class UserconsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserconsumerApplication.class, args);
    }

}
