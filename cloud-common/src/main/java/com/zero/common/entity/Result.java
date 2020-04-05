package com.zero.common.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * @Author: tobi
 * @Date: 2020/4/5 14:11
 **/
@Data
public class Result<T> {

    private Integer code;
    private String message;
    private T data;

    public Result (Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}

