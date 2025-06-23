package com.example.transaction.controller;

import com.example.transaction.model.Installment;
import com.example.transaction.service.InstallmentService;
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
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(InstallmentController.class)
class InstallmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private InstallmentService installmentService;

    private String readFixture(String name) throws Exception {
        return StreamUtils.copyToString(
                getClass().getClassLoader().getResourceAsStream("fixtures/" + name),
                StandardCharsets.UTF_8);
    }

    @Test
    void getInstallmentsReturnsList() throws Exception {
        List<Installment> installments = Arrays.asList(
                new Installment(1L, 1L, 10L),
                new Installment(2L, 1L, 50L),
                new Installment(3L, 1L, 40L)
        );
        when(installmentService.getInstallmentsForTransactions(anyLong()))
                .thenReturn(ResponseEntity.ok(installments));

        String expectedJson = readFixture("installments_list.json");
        mockMvc.perform(get("/installment/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJson));
    }
}
