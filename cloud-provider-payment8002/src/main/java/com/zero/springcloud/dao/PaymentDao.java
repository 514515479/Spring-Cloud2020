package com.zero.springcloud.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zero.common.entity.Payment;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: tobi
 * @Date: 2020/4/5 14:19
 **/
@Mapper
public interface PaymentDao extends BaseMapper<Payment> {
}
