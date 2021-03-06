package com.zero.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: tobi
 * @Date: 2020/5/2 23:39
 **/
@RestController
@RequestMapping("/configClient")
//配置文件自动刷新
@RefreshScope
public class ConfigClientController {

    @Value("${server.port}")
    private String port;

    @Value("${config.info}")
    private String configInfo;

    @GetMapping("/info")
    public String configInfo() {
        return "serverPort: " + port + "; configInfo: " + configInfo;
    }
}
