package com.tech_challenge_04_payment.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    public static final String QUEUE_PAYMENT_REQUEST = "paymentRequest";
    public static final String QUEUE_PAYMENT_RESPONSE = "paymentResponse";

    @Bean
    public Queue queue_paymentRequest() { return new Queue(QUEUE_PAYMENT_REQUEST, true); }

    @Bean
    public Queue queue_paymentResponse() { return new Queue(QUEUE_PAYMENT_RESPONSE, true); }
}
