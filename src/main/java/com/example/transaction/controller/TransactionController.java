package com.example.transaction.controller;

import com.example.transaction.model.Installment;
import com.example.transaction.model.Transaction;
import com.example.transaction.model.TransactionDetails;
import com.example.transaction.model.TransactionsResponse;
import com.example.transaction.service.TransactionService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import java.util.List;

@RestController
@Validated
@RequestMapping("transaction")
@CrossOrigin
public class TransactionController {

    @Autowired
    TransactionService transactionService;
    @GetMapping()
    public ResponseEntity<TransactionsResponse> getAllTransactions(
            @Valid @RequestParam(name = "pageNumber") Long pageNumber,
            @Valid @RequestParam(name = "sort") String sort
    ){
        return transactionService.getAllTransactions(pageNumber,sort);
    }

    @GetMapping("/{transactionId}")
    public ResponseEntity<TransactionDetails> getInstallmentsForTransactions(@Valid @PathVariable Long transactionId){
        return transactionService.getTransactionData(transactionId);
    }
}
