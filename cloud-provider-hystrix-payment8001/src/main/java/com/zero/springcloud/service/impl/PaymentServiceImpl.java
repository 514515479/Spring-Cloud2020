package com.zero.springcloud.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
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
//全局的服务降级处理，接口中有单独配置的用自己配置的，没有的用这里的，但是接口必须加@HystrixCommand注解
@DefaultProperties(defaultFallback = "fallBackMethod2", commandProperties = {
        @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
})
public class PaymentServiceImpl extends ServiceImpl<PaymentDao, Payment> implements PaymentService {

    @Override
    public String hystrix1() {
        return "服务调用成功";
    }

    @Override
//    @HystrixCommand(fallbackMethod = "fallBackMethod1", commandProperties = {
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
//    })
    @HystrixCommand
    public String hystrix2() {
        //让直接报错，也是可以触发fallBackMethod（只要是服务不可用了）
        //int a = 10/0;
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "服务调用延时5秒";
    }

    public String fallBackMethod1 () {
        return "hystrix服务降级";
    }
    public String fallBackMethod2 () {
        return "全局的hystrix服务降级";
    }
}
