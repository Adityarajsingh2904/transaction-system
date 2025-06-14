package com.example.transaction.dao;

import com.example.transaction.model.Transaction;
import com.example.transaction.model.TransactionsResponse;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

import java.io.DataInput;
import java.io.FileReader;
import java.util.*;

@Repository
public class TransactionDao {
    @Autowired
    InstallmentDao installmentDao;

    public TransactionsResponse findAll(Long pageNumber, String sort){
        JSONParser parser = new JSONParser();
        List<Transaction> transactions = new ArrayList<>();
        try {
            Object obj = parser.parse(new FileReader("Parent.json"));
            JSONObject jsonObject = (JSONObject) obj;

            JSONArray transactionList = (JSONArray) jsonObject.get("data");
            Iterator<JSONObject> iterator = transactionList.iterator();

            while (iterator.hasNext()) {
                JSONObject object = iterator.next();
                Long id = (Long) object.get("id");
                String sender = (String) object.get("sender");
                String receiver = (String) object.get("receiver");
                Long totalAmount = (Long) object.get("totalAmount");
                Long totalPaidAmount = InstallmentDao.findInstallmentSum(id);
                Transaction transaction = new Transaction(id,receiver,sender,totalAmount,totalPaidAmount);
                transactions.add(transaction);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(Objects.equals(sort, "des")){
            transactions.sort((d1, d2) -> Math.toIntExact(d2.getId() - d1.getId()));
        } else if (Objects.equals(sort, "asc")) {
            transactions.sort((d1, d2) -> Math.toIntExact(d1.getId() - d2.getId()));
        }
        int endPage = Math.min((int) ((pageNumber+1)*2), transactions.size());
        return new TransactionsResponse(transactions.subList((int) (pageNumber*2), endPage), (long) transactions.size());
    }

    public Transaction find(Long transactionId) {
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader("Parent.json"));
            JSONObject jsonObject = (JSONObject) obj;

            JSONArray transactionList = (JSONArray) jsonObject.get("data");
            Iterator<JSONObject> iterator = transactionList.iterator();

            while (iterator.hasNext()) {
                JSONObject object = iterator.next();
                Long id = (Long) object.get("id");
                String sender = (String) object.get("sender");
                String receiver = (String) object.get("receiver");
                Long totalAmount = (Long) object.get("totalAmount");
                Long totalPaidAmount = InstallmentDao.findInstallmentSum(id);
                Transaction transaction = new Transaction(id,receiver,sender,totalAmount,totalPaidAmount);
                if(Objects.equals(id, transactionId)){
                    return transaction;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
