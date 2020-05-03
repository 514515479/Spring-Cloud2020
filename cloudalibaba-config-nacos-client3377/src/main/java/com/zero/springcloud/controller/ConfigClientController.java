package com.zero.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: tobi
 * @Date: 2020/5/3 19:03
 **/
@Slf4j
@RestController
@RequestMapping("/configClient")
public class ConfigClientController {

    @Value("${config.info}")
    private String info;

    @GetMapping("/info")
    public String info(){
        return info;
    }
}
