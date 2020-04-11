package com.zero.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

/**
 * @Author: tobi
 * @Date: 2020/4/11 18:22
 **/
public interface LoadBalancer {

    ServiceInstance instance (List<ServiceInstance> serviceInstances);
}
