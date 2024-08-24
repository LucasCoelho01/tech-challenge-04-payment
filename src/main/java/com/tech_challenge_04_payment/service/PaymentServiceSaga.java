package com.tech_challenge_04_payment.service;

import com.google.gson.Gson;
import com.tech_challenge_04_payment.entity.Payment;
import com.tech_challenge_04_payment.repository.PaymentRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tech_challenge_04_payment.config.RabbitMQConfig;

@Service
public class PaymentServiceSaga {
    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RabbitListener(queues = RabbitMQConfig.QUEUE_PAYMENT_REQUEST)
    public void handlePaymentMessage(String jsonRequest) {
        System.out.println("Recebido queue QUEUE_PAYMENT_REQUEST");
        Gson gson = new Gson();
        String orderId = gson.fromJson(jsonRequest, String.class);

        var payment = new Payment();
        payment.setOrderId(orderId);
        payment.setPaymentOk(true);

        paymentRepository.save(payment);

        String json = gson.toJson(payment);

        rabbitTemplate.convertAndSend(RabbitMQConfig.QUEUE_PAYMENT_RESPONSE, json);
        System.out.println("Enviado queue QUEUE_PRODUCT_RESPONSE");
    }
}
