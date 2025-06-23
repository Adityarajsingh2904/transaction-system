package com.example.transaction.service;

import com.example.transaction.dao.InstallmentDao;
import com.example.transaction.model.Installment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InstallmentService {
    @Autowired
    InstallmentDao installmentDao;
    public ResponseEntity<List<Installment>> getInstallmentsForTransactions(Long parentId) {
        try {
            return new ResponseEntity<>(installmentDao.find(parentId), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }
}
