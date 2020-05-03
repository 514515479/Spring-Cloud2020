package com.zero.springcloud.controller;

import com.zero.common.entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @Author: tobi
 * @Date: 2020/4/5 17:24
 **/
@RestController
@RequestMapping("/consumerOrder")
public class ConsumeOrderController {

    @Value("${service-url.nacos-user-service}")
    private String PROVIDER_URL;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/port")
    public Result getPort() {
        return restTemplate.getForObject(PROVIDER_URL + "/payment/port", Result.class);
    }

}
