package com.zero.springcloud.service.impl;

import cn.hutool.core.util.IdUtil;
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

    @Override
    /**
     *这里比如10次失败超过6次，启动了断路器，后面就算输入id大于0 ，也会触发fallbackmethod
     *要隔一段时间，发现正确率上升了，错误率下降了，才会慢慢的恢复
     */
    @HystrixCommand(fallbackMethod = "fallBackMethod3", commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"), //是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"), //请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"), //时间窗口期（时间范围）
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60") //失败率达到多少后跳闸（这里是60%）
    })
    public String hystrix3(Integer id) {
        //如果id < 0，故意抛个异常
        if (id < 0) {
            throw new RuntimeException();
        }
        String uuid = IdUtil.simpleUUID();
        return Thread.currentThread().getName() + "调用流水号" + uuid;
    }

    public String fallBackMethod1 () {
        return "hystrix服务降级";
    }
    public String fallBackMethod2 () {
        return "全局的hystrix服务降级";
    }
    public String fallBackMethod3 (Integer id) {
        return "id：" + id +"为负数，启动服务熔断";
    }
}
