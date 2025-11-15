package com.sam.eventhall.Model;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "Payments")
@Data

public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer paymentId;

    private Integer reservationId; // foreign Key
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    private PaymentMethod method;

    private LocalDateTime paymentDate = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    private PaymentStatus status;

    public enum PaymentMethod{
        Cash, Credit_Card, Bank_Transfer, Refunded
    }

    public enum PaymentStatus{
        Pending, Paid, Refunded
    }
}
