package com.tech_challenge_04_payment.controller;

import com.tech_challenge_04_payment.entity.dto.CreatePaymentDto;
import com.tech_challenge_04_payment.entity.Payment;
import com.tech_challenge_04_payment.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/api/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping
    ResponseEntity<Payment> createPayment(@RequestBody CreatePaymentDto createPaymentDto) {
        return new ResponseEntity<>(paymentService.createPayment(createPaymentDto), HttpStatus.CREATED);
    }

    @GetMapping
    ResponseEntity<List<Payment>> getAllPayments() {
        return new ResponseEntity<>(paymentService.getAllPayments(), HttpStatus.OK);
    }
}
