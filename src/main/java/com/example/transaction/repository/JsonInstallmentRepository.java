package com.example.transaction.repository;

import com.example.transaction.model.Installment;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Repository;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

@Repository
public class JsonInstallmentRepository implements InstallmentRepository {
    private final JSONParser parser = new JSONParser();
    private final String filePath = "Child.json";

    @Override
    public List<Installment> findByParentId(Long parentId) {
        List<Installment> installments = new ArrayList<>();
        try {
            Object obj = parser.parse(new FileReader(filePath));
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray installmentList = (JSONArray) jsonObject.get("data");
            Iterator<JSONObject> iterator = installmentList.iterator();
            while (iterator.hasNext()) {
                JSONObject object = iterator.next();
                Long pid = (Long) object.get("parentId");
                if (Objects.equals(pid, parentId)) {
                    Long id = (Long) object.get("id");
                    Long paidAmount = (Long) object.get("paidAmount");
                    installments.add(new Installment(id, pid, paidAmount));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return installments;
    }

    @Override
    public Long sumByParentId(Long parentId) {
        long totalPaidAmount = 0L;
        try {
            Object obj = parser.parse(new FileReader(filePath));
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray installmentList = (JSONArray) jsonObject.get("data");
            Iterator<JSONObject> iterator = installmentList.iterator();
            while (iterator.hasNext()) {
                JSONObject object = iterator.next();
                Long pid = (Long) object.get("parentId");
                Long paidAmount = (Long) object.get("paidAmount");
                if (Objects.equals(pid, parentId)) {
                    totalPaidAmount += paidAmount;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return totalPaidAmount;
    }
}
