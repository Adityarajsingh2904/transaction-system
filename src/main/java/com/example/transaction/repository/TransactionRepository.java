package com.example.transaction.repository;

import com.example.transaction.model.Transaction;
import java.util.List;

public interface TransactionRepository {
    List<Transaction> findAll();
    Transaction find(Long id);
    Transaction save(Transaction transaction);
}
