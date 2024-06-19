package com.tech_challenge_04_payment.service;

import com.tech_challenge_04_payment.entity.Payment;
import com.tech_challenge_04_payment.entity.dto.CreatePaymentDto;
import com.tech_challenge_04_payment.repository.PaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PaymentServiceTest {

    @Mock
    private PaymentRepository paymentRepository;

    @InjectMocks
    private PaymentService paymentService;

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
        when(paymentRepository.save(any(Payment.class))).thenReturn(payment);

        Payment createdPayment = paymentService.createPayment(createPaymentDto);

        assertNotNull(createdPayment);
        assertEquals("1", createdPayment.getOrderId());
        assertTrue(createdPayment.isPaymentOk());

        verify(paymentRepository, times(1)).save(any(Payment.class));
    }

    @Test
    void getAllPayments_success() {
        when(paymentRepository.findAll()).thenReturn(List.of(payment));

        List<Payment> payments = paymentService.getAllPayments();

        assertNotNull(payments);
        assertFalse(payments.isEmpty());
        assertEquals(1, payments.size());
        verify(paymentRepository, times(1)).findAll();
    }
}
