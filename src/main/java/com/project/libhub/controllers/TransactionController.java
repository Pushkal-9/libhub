package com.project.libhub.controllers;

import com.project.libhub.models.Transaction;
import com.project.libhub.services.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/transactions")
@RequiredArgsConstructor
public class TransactionController {


    private final TransactionService transactionService;

    @PostMapping("/checkout")
    public Transaction checkoutItem(@RequestParam int userId, @RequestParam String itemBarcode) {
        LocalDateTime checkoutDateTime = LocalDateTime.now();
        return transactionService.createCheckoutTransaction(userId, itemBarcode, checkoutDateTime);
    }

    @PostMapping("/return/{transactionId}")
    public Transaction returnItem(@PathVariable int transactionId) {
        LocalDateTime returnDateTime = LocalDateTime.now();
        return transactionService.completeReturnTransaction(transactionId, returnDateTime);
    }
}

