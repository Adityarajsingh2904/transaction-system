package com.example.transaction.controller;

import com.example.transaction.model.Installment;
import com.example.transaction.service.InstallmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("installment")
public class InstallmentController {

    @Autowired
    InstallmentService installmentService;
    @GetMapping("/{transactionId}")
    public ResponseEntity<List<Installment>> getInstallmentsForTransactions(@PathVariable Long transactionId){
        return installmentService.getInstallmentsForTransactions(transactionId);
    }
}