package com.zero.springcloud.service.impl;

import com.zero.springcloud.service.IMessageProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;

import java.util.UUID;

/**
 * @Author: tobi
 * @Date: 2020/8/4 0:28
 **/
@EnableBinding(Source.class)  //绑定消息的管道，这个Source是自带的，里面的 @Output 定义了输出通道
public class MessageProvider implements IMessageProvider {

    @Autowired
    private MessageChannel output; //消息发送管道

    @Override
    public String send() {
        String serial = UUID.randomUUID().toString();
        output.send(MessageBuilder.withPayload(serial).build());
        System.out.println("********serial: " + serial);
        return serial;
    }
}
