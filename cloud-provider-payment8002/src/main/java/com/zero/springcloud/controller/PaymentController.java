package com.zero.springcloud.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zero.common.entity.Payment;
import com.zero.common.entity.Result;
import com.zero.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    private PaymentService paymentService;

    @GetMapping("/list")
    public Result list() {
        return Result.success(paymentService.list());
    }

    @GetMapping("/page")
    public Result page() {
        return Result.success(paymentService.page(new Page<>()));
    }

    @GetMapping("/{id}")
    public Result get(@PathVariable("id") Long id) {
        return Result.success(paymentService.getById(id));
    }

    @PostMapping("/add")
    public Result add (@RequestBody Payment entity) {
        return Result.success(paymentService.save(entity));
    }

    @GetMapping("/port")
    public Result getPort(){
        return Result.success(serverPort);
    }
}
