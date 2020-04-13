package com.zero.springcloud.feign;

import com.zero.common.entity.Result;
import com.zero.springcloud.feign.fallback.PaymentFeignServiceImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Author: tobi
 * @Date: 2020/4/11 21:03
 **/
@FeignClient(value = "CLOUD-PAYMENT-SERVICE", fallback = PaymentFeignServiceImpl.class)
//@FeignClient(value = "CLOUD-PAYMENT-SERVICE")
public interface PaymentFeignService {

    @GetMapping("/payment/hystrix1")
    Result hystrix1();

    @GetMapping("/payment/hystrix2")
    Result hystrix2();

    @GetMapping("/payment/hystrix3/{id}")
    Result hystrix3(@PathVariable("id") Integer id);
}
