package com.example.transaction.dao;

import com.example.transaction.model.Installment;
import com.example.transaction.model.Transaction;
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

public class InstallmentDao {
    public static List<Installment> find(Long transactionId){
        JSONParser parser = new JSONParser();
        List<Installment> installments = new ArrayList<>();
        try {
            Object obj = parser.parse(new FileReader("Child.json"));
            JSONObject jsonObject = (JSONObject) obj;

            JSONArray installmentList = (JSONArray) jsonObject.get("data");
            Iterator<JSONObject> iterator = installmentList.iterator();

            while (iterator.hasNext()) {
                JSONObject object = iterator.next();
                Long id = (Long) object.get("id");
                Long parentId = (Long) object.get("parentId");
                Long paidAmount = (Long) object.get("paidAmount");
                Installment installment = new Installment(id,parentId,paidAmount);
                if(Objects.equals(parentId, transactionId)){
                    installments.add(installment);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return installments;
    }
    public static Long findInstallmentSum(Long transactionId){
        JSONParser parser = new JSONParser();
        long totalPaidAmount = 0L;
        try {
            Object obj = parser.parse(new FileReader("Child.json"));
            JSONObject jsonObject = (JSONObject) obj;

            JSONArray installmentList = (JSONArray) jsonObject.get("data");
            Iterator<JSONObject> iterator = installmentList.iterator();

            while (iterator.hasNext()) {
                JSONObject object = iterator.next();
                Long parentId = (Long) object.get("parentId");
                Long paidAmount = (Long) object.get("paidAmount");
                if(Objects.equals(parentId, transactionId)){
                    totalPaidAmount = totalPaidAmount + paidAmount;
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return totalPaidAmount;
    }
}
