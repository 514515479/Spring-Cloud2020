package com.zero.springcloud.controller;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

/**
 * @Author: tobi
 * @Date: 2020/8/4 1:20
 **/
@Component
@EnableBinding(Sink.class)  //绑定消息的管道，这个Sink是自带的，里面的 @Input 定义了输入通道
public class MessageListenerController {

    @StreamListener(Sink.INPUT)
    public void input(Message<String> message) {
        System.out.println("消费者1号 ---> 接收到消息：" + message.getPayload());
    }
}
