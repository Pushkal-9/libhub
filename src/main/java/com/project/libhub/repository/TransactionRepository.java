package com.project.libhub.repository;

import com.project.libhub.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    List<Transaction> findAllByUserIdIs(int userId);

    @Query(value = "SELECT DATE(checkout_date_time) as date, COUNT(*) as count " +
            "FROM Transaction " +
            "WHERE checkout_date_time >= DATE_SUB(CURRENT_DATE, INTERVAL 30 DAY) " +
            "GROUP BY DATE(checkout_date_time) " +
            "ORDER BY date ASC", nativeQuery = true)
    List<Object[]> countTransactionsByDayForLast30Days();
}

