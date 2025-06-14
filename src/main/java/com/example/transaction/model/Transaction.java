package com.example.transaction.model;
import lombok.Data;

@Data
public class Transaction {
    private Long id;
    private String sender;
    private String receiver;
    private Long totalAmount;
    private Long totalPaidAmount;

    public Transaction(Long id, String sender, String receiver, Long totalAmount, Long totalPaidAmount){
        this.id = id;
        this.sender = sender;
        this.receiver = receiver;
        this.totalAmount = totalAmount;
        this.totalPaidAmount = totalPaidAmount;
    }

    public Long getId(){
        return this.id;
    }
}
