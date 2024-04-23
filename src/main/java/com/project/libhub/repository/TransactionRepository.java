package com.project.libhub.repository;

import com.project.libhub.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    List<Transaction> findAllByUserIdIs(int userId);

    @Query(value = "SELECT DATE(checkout_date_time) as date, COUNT(*) as count " +
            "FROM Transaction " +
            "WHERE checkout_date_time >= DATE_SUB(CURRENT_DATE, INTERVAL 30 DAY) " +
            "GROUP BY DATE(checkout_date_time) " +
            "ORDER BY date ASC", nativeQuery = true)
    List<Object[]> countTransactionsByDayForLast30Days();

    @Query("SELECT b.title as title, COUNT(b) as count FROM Transaction t JOIN Item i ON t.itemBarcode = i.itemBarcode JOIN Book b ON i.book.bibNumber = b.bibNumber WHERE t.checkoutDateTime >= :startDate GROUP BY b.title ORDER BY count DESC")
    List<Object[]> findPopularBooksSince(LocalDateTime startDate);
}

