package com.example.transaction.model;

import lombok.Data;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

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

    @NotNull
    private LocalDateTime createdAt;

    public Transaction(Long id, String sender, String receiver, Long totalAmount, Long totalPaidAmount, LocalDateTime createdAt){
        this.id = id;
        this.sender = sender;
        this.receiver = receiver;
        this.totalAmount = totalAmount;
        this.totalPaidAmount = totalPaidAmount;
        this.createdAt = createdAt;
    }

    public Long getId(){
        return this.id;
    }
}
