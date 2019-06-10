package com.hxgz.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class ListenerTest {

    @RabbitListener(queues="itheima")
    public void showMessage3(String message){
        System.out.println("itheima接收到消息："+message);
    }
}
