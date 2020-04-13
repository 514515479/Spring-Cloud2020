package com.zero.springcloud.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zero.common.entity.Payment;

/**
 * @Author: tobi
 * @Date: 2020/4/5 14:21
 **/
public interface PaymentService extends IService<Payment> {

    String hystrix1();

    String hystrix2();

    String hystrix3(Integer id);
}
