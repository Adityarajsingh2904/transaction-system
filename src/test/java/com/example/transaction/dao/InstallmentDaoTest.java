package com.example.transaction.dao;

import com.example.transaction.model.Installment;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class InstallmentDaoTest {

    @Test
    void findReturnsInstallments() {
        List<Installment> list = InstallmentDao.find(1L);
        assertEquals(3, list.size());
        assertEquals(1L, list.get(0).getParentId());
    }

    @Test
    void findInstallmentSumReturnsTotal() {
        Long sum = InstallmentDao.findInstallmentSum(1L);
        assertEquals(100L, sum);
    }
}
