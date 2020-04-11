package com.zero.common.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private static final Integer FAILED = 500;

    private Integer code;
    private String message;
    private T data;

    public static <T> Result<T> success() {
        return new Result<>(SUCCESS, "操作成功", null);
    }

    public static <T> Result<T> success(T data) {
        return new Result<>(SUCCESS, "操作成功", data);
    }

    public static <T> Result<T> failed() {
        return new Result<>(FAILED, "操作失败", null);
    }

    public static <T> Result<T> failed(String message) {
        return new Result<>(FAILED, message, null);
    }

    @JsonIgnore
    public Boolean isSuccess() {
        return this.code.equals(SUCCESS);
    }
}

