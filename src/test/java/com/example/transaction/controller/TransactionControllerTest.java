package com.example.transaction.controller;

import com.example.transaction.model.Installment;
import com.example.transaction.model.Transaction;
import com.example.transaction.model.TransactionDetails;
import com.example.transaction.model.TransactionsResponse;
import com.example.transaction.service.TransactionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.StreamUtils;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TransactionController.class)
class TransactionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TransactionService transactionService;

    private String readFixture(String name) throws Exception {
        return StreamUtils.copyToString(
                getClass().getClassLoader().getResourceAsStream("fixtures/" + name),
                StandardCharsets.UTF_8);
    }

    @Test
    void getAllTransactionsReturnsResponse() throws Exception {
        List<Transaction> transactions = Arrays.asList(
                new Transaction(1L, "ABC", "XYZ", 200L, 100L),
                new Transaction(2L, "XYZ", "MNP", 100L, 100L)
        );
        TransactionsResponse response = new TransactionsResponse(transactions, 2L);
        when(transactionService.getAllTransactions(anyLong(), anyString()))
                .thenReturn(ResponseEntity.ok(response));

        String expectedJson = readFixture("transactions_response.json");
        mockMvc.perform(get("/transaction")
                        .param("pageNumber", "0")
                        .param("sort", "asc"))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJson));
    }

    @Test
    void getTransactionDetailsReturnsResponse() throws Exception {
        Transaction transaction = new Transaction(1L, "ABC", "XYZ", 200L, 100L);
        List<Installment> installments = Arrays.asList(
                new Installment(1L, 1L, 10L),
                new Installment(2L, 1L, 50L),
                new Installment(3L, 1L, 40L)
        );
        TransactionDetails details = new TransactionDetails(transaction, installments);
        when(transactionService.getTransactionData(anyLong()))
                .thenReturn(ResponseEntity.ok(details));

        String expectedJson = readFixture("transaction_details.json");
        mockMvc.perform(get("/transaction/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJson));
    }
}
