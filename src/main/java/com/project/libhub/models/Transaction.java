package com.project.libhub.models;

import java.time.LocalDateTime;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Transaction")
@Data
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TransactionID")
    private int transactionId;

    @Column(name = "ItemBarcode")
    private String itemBarcode;

    @Column(name = "UserID")
    private int userId;

    @Column(name = "CheckoutDateTime", nullable = false)
    private LocalDateTime checkoutDateTime;

    @Column(name = "ReturnDateTime")
    private LocalDateTime returnDateTime;

}

