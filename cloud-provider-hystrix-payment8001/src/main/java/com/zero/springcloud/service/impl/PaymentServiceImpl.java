package com.zero.springcloud.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.zero.common.entity.Payment;
import com.zero.springcloud.dao.PaymentDao;
import com.zero.springcloud.service.PaymentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.TimeUnit;

/**
 * @Author: tobi
 * @Date: 2020/4/5 14:21
 **/
@Service
@Transactional(rollbackFor = Exception.class)
public class PaymentServiceImpl extends ServiceImpl<PaymentDao, Payment> implements PaymentService {

    @Override
    public String hystrix1() {
        return "服务调用成功";
    }

    @Override
    @HystrixCommand(fallbackMethod = "fallBackMethod", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
    })
    public String hystrix2() {
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "服务调用延时5秒";
    }

    public String fallBackMethod () {
        return "hystrix服务降级";
    }
}
