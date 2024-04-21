package com.project.libhub.services;

import com.project.libhub.models.Item;
import com.project.libhub.models.Transaction;
import com.project.libhub.payload.response.TransactionResponse;
import com.project.libhub.repository.ItemRepository;
import com.project.libhub.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransactionService {

    private final TransactionRepository transactionRepository;


    private final ItemService itemService;

    private final ItemRepository itemRepository;

    public Map<String, Long> getTransactionsCountByDayForLast30Days() {
        List<Object[]> results = transactionRepository.countTransactionsByDayForLast30Days();
        Map<String, Long> transactionsPerDay = new LinkedHashMap<>();
        for (Object[] result : results) {
            transactionsPerDay.put(result[0].toString(), (Long) result[1]);
        }
        return transactionsPerDay;
    }

    public List<TransactionResponse> findAllTransactionsByUserId(int userId){
        List<TransactionResponse> transactionResponses = new ArrayList<>();
        List<Transaction> transactions = transactionRepository.findAllByUserIdIs(userId);

        for(Transaction transaction : transactions){
            transactionResponses.add(getTransactionResponse(transaction.getTransactionId()));
        }

        return transactionResponses;
    }

    public TransactionResponse getTransactionResponse(int transactionId){
        Optional<Transaction> transactionOptional = transactionRepository.findById(transactionId);
        if(transactionOptional.isPresent()) {
            Transaction transaction = transactionOptional.get();
            log.info("Transaction Id : {}",transaction.getTransactionId());
            Optional<Item> itemOptional = itemRepository.findById(transaction.getItemBarcode());
            if(itemOptional.isPresent()) {
                Item item = itemOptional.get();
                log.info("Item Id : {}",item.getItemBarcode());

                TransactionResponse transactionResponse = new TransactionResponse();
                transactionResponse.setTransactionId(transaction.getTransactionId());
                transactionResponse.setTitle(item.getBook().getTitle());
                transactionResponse.setItemBarcode(item.getItemBarcode());
                transactionResponse.setBibNumber(item.getBook().getBibNumber());
                transactionResponse.setReturnDateTime(transaction.getReturnDateTime());
                return transactionResponse;
            }
        }
        return new TransactionResponse();
    }

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

