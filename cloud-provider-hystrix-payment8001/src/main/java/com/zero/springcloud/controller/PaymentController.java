package com.zero.springcloud.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zero.common.entity.Payment;
import com.zero.common.entity.Result;
import com.zero.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Author: tobi
 * @Date: 2020/4/5 14:40
 **/
@RestController
@RequestMapping("/payment")
@Slf4j
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @GetMapping("/hystrix1")
    public Result hystrix1(){
        return Result.success(paymentService.hystrix1());
    }

    @GetMapping("/hystrix2")
    public Result hystrix2(){
        return Result.success(paymentService.hystrix2());
    }

    @GetMapping("/hystrix3/{id}")
    public Result hystrix3(@PathVariable("id") Integer id){
        return Result.success(paymentService.hystrix3(id));
    }
}
