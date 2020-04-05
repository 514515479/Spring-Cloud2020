package com.zero.springcloud.controller;

import com.zero.common.entity.Payment;
import com.zero.common.entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @Author: tobi
 * @Date: 2020/4/5 17:24
 **/
@RestController
@RequestMapping("/consumerOrder")
public class ConsumeOrderController {

    private static final String PROVIDER_URL = "http://localhost:8001";

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/list")
    public Result list() {
        return restTemplate.getForObject(PROVIDER_URL + "/payment/list", Result.class);
    }

    @PostMapping("/add")
    public Result add(@RequestBody Payment entity){
        return restTemplate.postForObject(PROVIDER_URL + "/payment/add", entity, Result.class);
    }
}
