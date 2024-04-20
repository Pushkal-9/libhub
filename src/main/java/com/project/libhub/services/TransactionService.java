package com.project.libhub.services;

import com.project.libhub.models.Item;
import com.project.libhub.models.Transaction;
import com.project.libhub.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;


    private final ItemService itemService;

    public Transaction createCheckoutTransaction(int userId, String itemBarcode, LocalDateTime checkoutDateTime) {
        Transaction transaction = new Transaction();
        transaction.setUserId(userId);
        transaction.setItemBarcode(itemBarcode);
        transaction.setCheckoutDateTime(checkoutDateTime);
        transaction.setReturnDateTime(null);

        Item item = itemService.getItemByBarcode(itemBarcode);
        if (item != null) {
            item.setStatus("Unavailable");
            itemService.saveItem(item);
        }

        return transactionRepository.save(transaction);
    }

    public Transaction completeReturnTransaction(int transactionId, LocalDateTime returnDateTime) {
        Optional<Transaction> transactionOptional = transactionRepository.findById(transactionId);
        if (transactionOptional.isPresent()) {
            Transaction transaction = transactionOptional.get();
            transaction.setReturnDateTime(returnDateTime);

            Item item = itemService.getItemByBarcode(transaction.getItemBarcode());
            if (item != null) {
                item.setStatus("Available");
                itemService.saveItem(item);
            }

            return transactionRepository.save(transaction);
        }
        return null;
    }
}

