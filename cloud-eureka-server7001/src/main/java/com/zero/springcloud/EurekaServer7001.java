package com.zero.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @Author: tobi
 * @Date: 2020/4/5 21:00
 **/
@SpringBootApplication
@EnableEurekaServer
//如果pom配置了数据库相关，需要添加这个这个，使启动时不自动连接数据库
//@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
public class EurekaServer7001 {
    public static void main(String[] args) {
        SpringApplication.run(EurekaServer7001.class, args);
    }
}
