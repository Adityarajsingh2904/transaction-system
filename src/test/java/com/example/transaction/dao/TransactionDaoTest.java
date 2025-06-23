package com.example.transaction.dao;

import com.example.transaction.model.TransactionsResponse;
import com.example.transaction.model.Transaction;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TransactionDaoTest {

    @Autowired
    private TransactionDao transactionDao;

    @Test
    void findAllReturnsPagedTransactions() {
        TransactionsResponse response = transactionDao.findAll(0L, "asc");
        assertEquals(2, response.getTransactions().size());
        assertEquals(7L, response.getTotalCount());
        assertEquals(1L, response.getTransactions().get(0).getId());
        assertEquals(2L, response.getTransactions().get(1).getId());
    }

    @Test
    void findByIdReturnsTransaction() {
        Transaction t = transactionDao.find(3L);
        assertNotNull(t);
        assertEquals(3L, t.getId());
    }
}
