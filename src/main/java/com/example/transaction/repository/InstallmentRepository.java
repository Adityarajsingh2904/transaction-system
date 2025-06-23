package com.example.transaction.repository;

import com.example.transaction.model.Installment;
import java.util.List;

public interface InstallmentRepository {
    List<Installment> findByParentId(Long parentId);
    Long sumByParentId(Long parentId);
}
