package com.example.transaction.model;
import lombok.Data;

@Data
public class Installment {
    private Long id;
    private Long parentId;
    private Long paidAmount;

    public Installment(Long id, Long parentId, Long paidAmount){
        this.id = id;
        this.parentId = parentId;
        this.paidAmount = paidAmount;
    }
}
