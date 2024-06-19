package com.tech_challenge_04_payment.service;

import com.tech_challenge_04_payment.entity.CreatePaymentDto;
import com.tech_challenge_04_payment.entity.Payment;
import com.tech_challenge_04_payment.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    public Payment createPayment(CreatePaymentDto createPaymentDto) {
        var payment = new Payment();
        payment.setOrderId(createPaymentDto.orderId());
        payment.setPaymentOk(true);

        return paymentRepository.save(payment);
    }

    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

}
