package com.zero.common.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Author: tobi
 * @Date: 2020/4/5 1:37
 **/
@Data
@Accessors(chain = true)
@TableName("t_payment")
public class Payment implements Serializable {

    private Long id;
    private String serial;
}
