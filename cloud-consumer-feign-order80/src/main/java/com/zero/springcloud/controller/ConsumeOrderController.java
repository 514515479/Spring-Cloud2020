package com.zero.springcloud.controller;

import com.zero.common.entity.Payment;
import com.zero.common.entity.Result;
import com.zero.springcloud.feign.PaymentFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

/**
 * @Author: tobi
 * @Date: 2020/4/5 17:24
 **/
@RestController
@RequestMapping("/consumerOrder")
public class ConsumeOrderController {

    private static final String PROVIDER_URL = "http://CLOUD-PAYMENT-SERVICE";

    @Autowired
    private PaymentFeignService paymentFeignService;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/list")
    public Result list() {
        return restTemplate.getForObject(PROVIDER_URL + "/payment/list", Result.class);
    }

    @GetMapping("/port")
    public Result port() {
        return paymentFeignService.list();
    }
}
