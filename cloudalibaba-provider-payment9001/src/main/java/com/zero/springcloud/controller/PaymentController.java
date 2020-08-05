package com.zero.springcloud.controller;

import com.zero.common.entity.Payment;
import com.zero.common.entity.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * @Author: tobi
 * @Date: 2020/4/5 14:40
 **/
@RestController
@RequestMapping("/payment")
@Slf4j
public class PaymentController {

    public static HashMap<Long, Payment> map = new HashMap<>();
    static {
        map.put(1L, new Payment(1L, "111111111111"));
        map.put(2L, new Payment(2L, "222222222222"));
        map.put(3L, new Payment(3L, "333333333333"));
    }

    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/port")
    public Result getPort(){
        return Result.success(serverPort);
    }

    @GetMapping("paymentSQL/{id}")
    public Result paymentSQL(@PathVariable("id") Long id) {
        Payment payment = map.get(id);
        return new Result(200, "port: " + serverPort, payment);
    }
}
