package com.example.transaction.model;

import lombok.Data;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class Transaction {
    @NotNull
    private Long id;

    @NotNull
    @Size(min = 1)
    private String sender;

    @NotNull
    @Size(min = 1)
    private String receiver;

    @NotNull
    @Min(0)
    private Long totalAmount;

    @NotNull
    @Min(0)
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
