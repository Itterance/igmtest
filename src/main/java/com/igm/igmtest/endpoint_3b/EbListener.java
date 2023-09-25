package com.igm.igmtest.endpoint_3b;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class EbListener {

    @RabbitListener(queues = "myQueue")
    public void listen(String in) {
        System.out.println("Received: " + in);
    }
}