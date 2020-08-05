package com.zero.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: tobi
 * @Date: 2020/8/4 21:43
 **/
@RestController
public class FlowLimitController {

    @GetMapping("/testA")
    public String testA() {
        return "----------testA";
    }

    @GetMapping("/testB")
    public String testB() {
        return "----------testB";
    }

    @GetMapping("/testHotKey")
    //热点key限流（sentinel热点规则），页面配置中的资源名为 /testHotKey 或 testHotKey blockHandler是fallback方法
    @SentinelResource(value = "testHotKey", blockHandler = "dealTestHotKey")
    public String testHotKey(@RequestParam(value = "P1", required = false) String p1,
                             @RequestParam(value = "P2", required = false) String p2) {
        return "----------testHotKey";
    }

    public String dealTestHotKey(String p1, String p2, BlockException e) {
        return "dealTestHotKey" + e.getRule();
    }
}
