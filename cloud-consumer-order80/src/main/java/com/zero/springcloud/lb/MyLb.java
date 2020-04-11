package com.zero.springcloud.lb;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: tobi
 * @Date: 2020/4/11 18:24
 **/
@Slf4j
@Component
public class MyLb implements LoadBalancer {
    //调用服务的下标
    private AtomicInteger atomicInteger = new AtomicInteger(0);

    public final int getAndIncrement () {
        int current;
        int next;
        //自旋锁
        do {
            //取得当前的值
            current = atomicInteger.get();
            log.info("in:current={}", current);
            //如果current >= 2147483647（最大整型数Integer.MAX_VALUE），则next重新计数，否则 = current + 1
            next = current >= 2147483647 ? 0 : current + 1;
            log.info("in:current={}, next={}", current, next);
        } while (!this.atomicInteger.compareAndSet(current, next));
        log.info("第几次访问:next={}", next);
        return next;
    }

    @Override
    public ServiceInstance instance(List<ServiceInstance> serviceInstances) {
        int index = this.getAndIncrement() % serviceInstances.size();
        return serviceInstances.get(index);
    }
}
