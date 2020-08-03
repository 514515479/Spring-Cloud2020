package com.zero.springcloud.controller;

import com.zero.springcloud.service.IMessageProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: tobi
 * @Date: 2020/8/4 0:35
 **/
@RestController
@RequestMapping("/stream")
public class SendMessageController {

    @Autowired
    private IMessageProvider iMessageProvider;

    @GetMapping("/send")
    public String sendMessage() {
        return iMessageProvider.send();
    }
}
