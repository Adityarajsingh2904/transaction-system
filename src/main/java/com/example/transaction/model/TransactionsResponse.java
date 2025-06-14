package com.example.transaction.model;
import lombok.Data;

import java.util.List;

@Data
public class TransactionsResponse {
    private List<Transaction> transactions;
    private Long totalCount;

    public TransactionsResponse(List<Transaction> transactions,Long totalCount){
        this.transactions = transactions;
        this.totalCount = totalCount;
    }
}
