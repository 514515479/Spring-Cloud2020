package com.zero.springcloud.feign;

import com.zero.common.entity.Result;
import com.zero.springcloud.feign.fallback.PaymentFeignServiceImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Author: tobi
 * @Date: 2020/4/11 21:03
 **/
@FeignClient(value = "CLOUD-PAYMENT-SERVICE", fallback = PaymentFeignServiceImpl.class)
public interface PaymentFeignService {

    @GetMapping("/payment/hystrix1")
    Result hystrix1();

    @GetMapping("/payment/hystrix2")
    Result hystrix2();
}
