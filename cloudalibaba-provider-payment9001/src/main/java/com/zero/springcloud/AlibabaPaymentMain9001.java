package com.zero.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Author: tobi
 * @Date: 2020/5/3 14:27
 **/
@SpringBootApplication
@EnableDiscoveryClient
public class AlibabaPaymentMain9001 {
    public static void main(String[] args) {
        SpringApplication.run(AlibabaPaymentMain9001.class, args);
    }
}
