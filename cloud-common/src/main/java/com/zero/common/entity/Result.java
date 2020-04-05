package com.zero.common.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * @Author: tobi
 * @Date: 2020/4/5 14:11
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result<T> {

    private static final Integer SUCCESS = 200;
    private static final Integer FAIL = 500;

    private Integer code;
    private String message;
    private T data;

    public static <T> Result<T> success() {
        return new Result<>(SUCCESS, null, null);
    }

    public static <T> Result<T> success(String message) {
        return new Result<>(SUCCESS, message, null);
    }

    public static <T> Result<T> success(T data) {
        return new Result<>(SUCCESS, null, data);
    }

    public static <T> Result<T> success(String message, T data) {
        return new Result<>(FAIL, message, data);
    }

    public static <T> Result<T> fail() {
        return new Result<>(FAIL, null, null);
    }

    public static <T> Result<T> fail(String message) {
        return new Result<>(FAIL, message, null);
    }
}

