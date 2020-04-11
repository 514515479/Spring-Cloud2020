package com.zero.springcloud.controller;

import com.baomidou.mybatisplus.extension.api.R;
import com.zero.common.entity.Payment;
import com.zero.common.entity.Result;
import com.zero.springcloud.lb.LoadBalancer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

/**
 * @Author: tobi
 * @Date: 2020/4/5 17:24
 **/
@RestController
@RequestMapping("/consumerOrder")
public class ConsumeOrderController {

    //private static final String PROVIDER_URL = "http://localhost:8001";
    //RestTemplate配置那边要加@LoadBanlance注解，开启负载均衡功能
    private static final String PROVIDER_URL = "http://CLOUD-PAYMENT-SERVICE";

    @Autowired
    private LoadBalancer loadBalancer;

    @Autowired
    private DiscoveryClient discoveryClient;

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

    @GetMapping("/port")
    public Result getPort() {
        return restTemplate.getForObject(PROVIDER_URL + "/payment/port", Result.class);
    }

    @GetMapping("/port2")
    public Result getPort2() {
        ResponseEntity<Result> entity = restTemplate.getForEntity(PROVIDER_URL + "/payment/port", Result.class);
        if (entity.getStatusCode().is2xxSuccessful()) {
            return Result.success(entity.getBody());
        } else {
            return Result.failed("RestTemplate调用失败！");
        }
    }

    @GetMapping("/lb")
    public String lb() {
        List<ServiceInstance> list = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        if (list == null || list.size() <= 0) {
            return null;
        }
        ServiceInstance serviceInstance = loadBalancer.instance(list);
        URI uri = serviceInstance.getUri();
        return restTemplate.getForObject(uri + "payment/port", String.class);
    }
}
