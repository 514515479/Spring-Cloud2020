package com.zero.springcloud.myHandler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.zero.common.entity.Result;

/**
 * @Author: tobi
 * @Date: 2020/8/5 14:53
 *
 * 全局自定义限流处理类，全局的sentinel BlockException
 **/
public class CustomBlockHandler {
    public static Result handlerException(BlockException e) {
        return new Result(444, "全局自定义限流处理类的handlerException---1", null);
    }
    public static Result handlerException2(BlockException e) {
        return new Result(444, "全局自定义限流处理类的handlerException---2", null);
    }
}
