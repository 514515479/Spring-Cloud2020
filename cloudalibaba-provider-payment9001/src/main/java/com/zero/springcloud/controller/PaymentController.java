package com.zero.springcloud.controller;

import com.zero.common.entity.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: tobi
 * @Date: 2020/4/5 14:40
 **/
@RestController
@RequestMapping("/payment")
@Slf4j
public class PaymentController {

    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/port")
    public Result getPort(){
        return Result.success(serverPort);
    }
}
