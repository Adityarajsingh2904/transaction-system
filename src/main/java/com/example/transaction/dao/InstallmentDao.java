package com.example.transaction.dao;

import com.example.transaction.model.Installment;
import com.example.transaction.repository.InstallmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public class InstallmentDao {
    @Autowired
    InstallmentRepository installmentRepository;

    public List<Installment> find(Long transactionId){
        return installmentRepository.findByParentId(transactionId);
    }

    public Long findInstallmentSum(Long transactionId){
        return installmentRepository.sumByParentId(transactionId);
    }
}
