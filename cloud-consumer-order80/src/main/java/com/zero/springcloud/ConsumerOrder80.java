package com.zero.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @Author: tobi
 * @Date: 2020/4/5 17:12
 **/
@SpringBootApplication
@EnableEurekaClient
public class ConsumerOrder80 {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerOrder80.class, args);
    }
}
