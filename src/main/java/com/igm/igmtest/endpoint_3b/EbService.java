package com.igm.igmtest.endpoint_3b;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@Service
public class EbService {
    private final WebClient webClient;
    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public EbService(WebClient.Builder webClientBuilder, RabbitTemplate rabbitTemplate) {
        this.webClient = webClientBuilder.baseUrl("http://3rdp-api.com").build();
        this.rabbitTemplate = rabbitTemplate;
    }

    @ExponentialBackoff(maxAttempts = 5, delay = 2000)
    public String getData() throws Exception {
        try {
            String data = this.webClient.get()
                    .uri("/endpoint")
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();

            if (data != null) {
                this.rabbitTemplate.convertAndSend("myExchange", "myRoutingKey", data);
            }
            return data;
        } catch (WebClientResponseException.TooManyRequests e) {
            throw new Exception("Too many requests");
        }
    }
}