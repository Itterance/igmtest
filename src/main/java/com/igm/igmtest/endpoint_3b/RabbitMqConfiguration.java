package com.igm.igmtest.endpoint_3b;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfiguration {

    @Bean
    public Queue myQueue() {
        return new Queue("myQueue", false);
    }

    @Bean
    public DirectExchange myExchange() {
        return new DirectExchange("myExchange");
    }

    @Bean
    public Binding binding(Queue myQueue, DirectExchange myExchange) {
        return BindingBuilder.bind(myQueue).to(myExchange).with("myRoutingKey");
    }
}