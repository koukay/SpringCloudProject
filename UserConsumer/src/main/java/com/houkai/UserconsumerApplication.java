package com.houkai;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * http://localhost:91/actuator
 * http://localhost:91/actuator/hystrix.stream
 * http://localhost:91/hystrix
 */
@SpringBootApplication // (scanBasePackages = "com.houkai")
@EnableCircuitBreaker
@EnableFeignClients
@EnableHystrixDashboard
public class UserconsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserconsumerApplication.class, args);
    }
    @Bean
    @LoadBalanced
    RestTemplate getRestTemplate() {

        RestTemplate restTemplate = new RestTemplate();
        //	restTemplate.getInterceptors().add(new LoggingClientHttpRequestInterceptor());
        return restTemplate;
    }
}
