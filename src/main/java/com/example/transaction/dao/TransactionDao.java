package com.example.transaction.dao;

import com.example.transaction.model.Transaction;
import com.example.transaction.model.TransactionsResponse;
import com.example.transaction.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class TransactionDao {
    @Autowired
    InstallmentDao installmentDao;

    @Autowired
    TransactionRepository transactionRepository;

    public TransactionsResponse findAll(Long pageNumber, String sort){
        List<Transaction> transactions = transactionRepository.findAll();
        for (Transaction transaction : transactions) {
            Long totalPaidAmount = installmentDao.findInstallmentSum(transaction.getId());
            transaction.setTotalPaidAmount(totalPaidAmount);
        }
        if(Objects.equals(sort, "des")){
            transactions.sort((d1, d2) -> Math.toIntExact(d2.getId() - d1.getId()));
        } else if (Objects.equals(sort, "asc")) {
            transactions.sort((d1, d2) -> Math.toIntExact(d1.getId() - d2.getId()));
        }
        int endPage = Math.min((int) ((pageNumber+1)*2), transactions.size());
        return new TransactionsResponse(transactions.subList((int) (pageNumber*2), endPage), (long) transactions.size());
    }

    public Transaction find(Long transactionId) {
        Transaction transaction = transactionRepository.find(transactionId);
        if (transaction != null) {
            Long totalPaidAmount = installmentDao.findInstallmentSum(transactionId);
            transaction.setTotalPaidAmount(totalPaidAmount);
        }
        return transaction;
    }
}
