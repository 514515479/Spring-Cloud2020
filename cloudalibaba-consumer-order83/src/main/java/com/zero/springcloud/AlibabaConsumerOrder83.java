package com.zero.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Author: tobi
 * @Date: 2020/5/3 15:03
 **/

//SpringBootApplication默认会自动加载数据源，这里给它除外
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
@EnableDiscoveryClient
public class AlibabaConsumerOrder83 {
    public static void main(String[] args) {
        SpringApplication.run(AlibabaConsumerOrder83.class, args);
    }
}
