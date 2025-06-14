package com.example.transaction.model;
import lombok.Data;

import java.util.List;

@Data
public class TransactionDetails {
    private Transaction transaction;
    private List<Installment> installments;

    public TransactionDetails(Transaction transaction, List<Installment> installments){
        this.transaction = transaction;
        this.installments = installments;
    }
}
