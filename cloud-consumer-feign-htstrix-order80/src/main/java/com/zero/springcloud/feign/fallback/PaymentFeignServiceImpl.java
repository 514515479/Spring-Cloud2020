package com.zero.springcloud.feign.fallback;

import com.zero.common.entity.Result;
import com.zero.springcloud.feign.PaymentFeignService;
import org.springframework.stereotype.Component;

/**
 * @Author: tobi
 * @Date: 2020/4/12 21:04
 **/
@Component
public class PaymentFeignServiceImpl implements PaymentFeignService {
    @Override
    public Result hystrix1() {
        return Result.success("PaymentFeignService---fallback");
    }

    @Override
    public Result hystrix2() {
        return Result.success("PaymentFeignService---fallback");
    }

    @Override
    public Result hystrix3(Integer id) {
        return Result.success("PaymentFeignService---fallback");
    }
}
