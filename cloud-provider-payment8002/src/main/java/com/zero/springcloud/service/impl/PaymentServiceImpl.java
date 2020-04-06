package com.zero.springcloud.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zero.common.entity.Payment;
import com.zero.springcloud.dao.PaymentDao;
import com.zero.springcloud.service.PaymentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: tobi
 * @Date: 2020/4/5 14:21
 **/
@Service
@Transactional(rollbackFor = Exception.class)
public class PaymentServiceImpl extends ServiceImpl<PaymentDao, Payment> implements PaymentService {
}
