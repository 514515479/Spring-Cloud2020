package com.zero.springcloud.controller;

import com.zero.common.entity.Result;
import com.zero.springcloud.feign.PaymentFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: tobi
 * @Date: 2020/4/5 17:24
 **/
@RestController
@RequestMapping("/consumerOrder")
public class ConsumeOrderController {

    @Autowired
    private PaymentFeignService paymentFeignService;

    @GetMapping("/hystrix1")
    public Result hystrix1() {
        return paymentFeignService.hystrix1();
    }

    @GetMapping("/hystrix2")
    public Result hystrix2() {
        return paymentFeignService.hystrix2();
    }

    @GetMapping("/hystrix3/{id}")
    public Result hystrix3(@PathVariable("id") Integer id) {
        return paymentFeignService.hystrix3(id);
    }
}
