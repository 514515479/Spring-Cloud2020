package com.zero.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Author: tobi
 * @Date: 2020/5/3 18:44
 **/
@SpringBootApplication
@EnableDiscoveryClient
public class ConfigNacosClient3377 {
    public static void main(String[] args) {
        SpringApplication.run(ConfigNacosClient3377.class, args);
    }
}
