package com.project.libhub.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionResponse {
    private int transactionId;
    private String title;
    private String itemBarcode;
    private int bibNumber;
    private LocalDateTime returnDateTime;
}
