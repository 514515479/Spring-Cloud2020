package com.zero.springcloud.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zero.common.entity.Payment;
import com.zero.common.entity.Result;
import com.zero.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

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
    private DiscoveryClient discoveryClient;

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

    @GetMapping("discovery")
    public void getDisCoveryClient() {
        List<String > service = discoveryClient.getServices();
        service.forEach(s -> {
            log.info(s.toString());
        });

        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        instances.forEach(s -> {
            log.info("instanceId:" + s.getInstanceId() + "," + s.getHost() + "," + s.getPort());
        });
    }
/*
    所以某一个线程进入synchronized代码块前后，执行过程入如下：
    a.线程获得互斥锁
    b.清空工作内存
    c.从主内存拷贝共享变量最新的值到工作内存成为副本
    d.执行代码
    e.将修改后的副本的值刷新回主内存中
    f.线程释放锁
*/
    /*static class A implements Runnable{
        public Integer b = 1;
        @Override
        public void run() {
            while (true){
                //System.out.println(b);
                if(b.equals(2))
                    break;
            }
            System.out.println("A is finished!");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        A a = new A();
        new Thread(a).start();
        TimeUnit.SECONDS.sleep(1);
        a.b = 2;
        //阻塞住主线程
        while (true){}
    }*/
}
