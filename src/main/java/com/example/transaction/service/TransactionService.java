package com.example.transaction.service;

import com.example.transaction.dao.InstallmentDao;
import com.example.transaction.dao.TransactionDao;
import com.example.transaction.model.Installment;
import com.example.transaction.model.Transaction;
import com.example.transaction.model.TransactionDetails;
import com.example.transaction.model.TransactionsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionService {
    @Autowired
    TransactionDao transactionDao;

    @Autowired
    InstallmentDao installmentDao;

    public ResponseEntity<TransactionsResponse> getAllTransactions(Long pageNumber, String sort) {
        try {
            return new ResponseEntity<>(transactionDao.findAll(pageNumber,sort), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new TransactionsResponse( new ArrayList<>(), 0L), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<TransactionDetails> getTransactionData(Long transactionId) {
        try {
            List<Installment> installments = installmentDao.find(transactionId);
            Transaction transaction = transactionDao.find(transactionId);
            return new ResponseEntity<>(new TransactionDetails(transaction, installments), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }
}
