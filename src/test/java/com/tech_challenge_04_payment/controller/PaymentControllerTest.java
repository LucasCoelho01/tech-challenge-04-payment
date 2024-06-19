package com.tech_challenge_04_payment.controller;

import com.tech_challenge_04_payment.entity.Payment;
import com.tech_challenge_04_payment.entity.dto.CreatePaymentDto;
import com.tech_challenge_04_payment.service.PaymentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PaymentControllerTest {

    @Mock
    private PaymentService paymentService;

    @InjectMocks
    private PaymentController paymentController;

    private Payment payment;
    private CreatePaymentDto createPaymentDto;

    @BeforeEach
    void setUp() {
        createPaymentDto = new CreatePaymentDto("1");

        payment = new Payment();
        payment.setOrderId("1");
        payment.setPaymentOk(true);
    }

    @Test
    void createPayment_success() {
        when(paymentService.createPayment(any(CreatePaymentDto.class))).thenReturn(payment);

        ResponseEntity<Payment> responseEntity = paymentController.createPayment(createPaymentDto);

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(payment, responseEntity.getBody());

        verify(paymentService, times(1)).createPayment(any(CreatePaymentDto.class));
    }

    @Test
    void getAllPayments_success() {
        when(paymentService.getAllPayments()).thenReturn(List.of(payment));

        ResponseEntity<List<Payment>> responseEntity = paymentController.getAllPayments();

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(1, responseEntity.getBody().size());
        assertEquals(payment, responseEntity.getBody().get(0));

        verify(paymentService, times(1)).getAllPayments();
    }
}
