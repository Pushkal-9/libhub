package com.project.libhub.controllers;

import com.project.libhub.models.Transaction;
import com.project.libhub.payload.response.TransactionResponse;
import com.project.libhub.services.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/transactions")
@RequiredArgsConstructor
public class TransactionController {


    private final TransactionService transactionService;

    @GetMapping("/count-per-day-last-30-days")
    public Map<String, Long> getTransactionCountsPerDay() {
        return transactionService.getTransactionsCountByDayForLast30Days();
    }

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

    @GetMapping("/{userId}")
    public List<TransactionResponse> getTransactionResponse(@PathVariable int userId) {
        return transactionService.findAllTransactionsByUserId(userId);
    }
}

