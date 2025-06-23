package com.example.transaction.model;

import lombok.Data;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class Installment {
    @NotNull
    private Long id;

    @NotNull
    private Long parentId;

    @NotNull
    @Min(0)
    private Long paidAmount;

    public Installment(Long id, Long parentId, Long paidAmount){
        this.id = id;
        this.parentId = parentId;
        this.paidAmount = paidAmount;
    }
}
