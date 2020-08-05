package com.zero.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.zero.common.entity.Payment;
import com.zero.common.entity.Result;
import com.zero.springcloud.myHandler.CustomBlockHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: tobi
 * @Date: 2020/8/5 14:34
 **/
@RestController
@RequestMapping("/rate")
public class RateLimitController {

    @GetMapping("/byResource")
    @SentinelResource(value = "byResource", blockHandler = "handleException")
    public Result byResource() {
        return new Result(200, "按资源名称限流", new Payment(2020L, "serial0101"));
    }
    public Result handleException(BlockException e) {
        return new Result(444, e.getClass().getCanonicalName() + "  服务不可用", null);
    }

    @GetMapping("/byUrl")
    //全局自定义限流BlockException
    @SentinelResource(value = "byUrl",
            blockHandlerClass = CustomBlockHandler.class,
            blockHandler = "handlerException")
    public Result byUrl() {
        return new Result(200, "按资源名称限流", new Payment(2020L, "serial0101"));
    }
}
