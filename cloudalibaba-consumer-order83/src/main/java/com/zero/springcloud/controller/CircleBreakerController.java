package com.zero.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.zero.common.entity.Payment;
import com.zero.common.entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @Author: tobi
 * @Date: 2020/4/5 17:24
 **/
@RestController
@RequestMapping("/consumerOrder")
public class CircleBreakerController {

    @Value("${service-url.nacos-user-service}")
    private String PROVIDER_URL;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/fallback/{id}")
    //@SentinelResource(value = "fallback")
    //@SentinelResource(value = "fallback", fallback = "handlerFallback") //fallback只负责业务异常
    @SentinelResource(value = "fallback", blockHandler = "blockHandler") //blockHandler只负责sentinel控制台配置的违规条件
    public Result fallback(@PathVariable("id") Long id) {
        Result result =  restTemplate.getForObject(PROVIDER_URL + "/payment/paymentSQL/" + id, Result.class, id);
        if (id == 4) {
            throw new IllegalArgumentException("IllegalArgumentException，非法参数异常");
        } else if (result.getData() == null) {
            throw  new NullPointerException("NullPointerException, id没有对应的记录");
        }
        return result;
    }

    //fallback方法
    public Result handlerFallback(@PathVariable("id") Long id, Throwable e) {
        Payment payment = new Payment(id, null);
        return new Result(444, "handlerFallback兜底:" + e.getMessage(), payment);
    }

    //blockHandler方法
    public Result blockHandler(@PathVariable("id") Long id, BlockException e) {
        Payment payment = new Payment(id, null);
        return new Result(444, "blockHandler限流:" + e.getMessage(), payment);
    }
}
