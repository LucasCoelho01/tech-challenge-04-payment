package com.tech_challenge_04_payment.entity;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name = "payments")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String orderId;
    private boolean isPaymentOk;
}
