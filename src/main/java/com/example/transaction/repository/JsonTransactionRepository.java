package com.example.transaction.repository;

import com.example.transaction.model.Transaction;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Repository;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Repository
public class JsonTransactionRepository implements TransactionRepository {
    private final JSONParser parser = new JSONParser();
    private final String filePath = "Parent.json";

    @Override
    public List<Transaction> findAll() {
        List<Transaction> transactions = new ArrayList<>();
        try {
            Object obj = parser.parse(new FileReader(filePath));
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray transactionList = (JSONArray) jsonObject.get("data");
            Iterator<JSONObject> iterator = transactionList.iterator();
            while (iterator.hasNext()) {
                JSONObject object = iterator.next();
                Long id = (Long) object.get("id");
                String sender = (String) object.get("sender");
                String receiver = (String) object.get("receiver");
                Long totalAmount = (Long) object.get("totalAmount");
                transactions.add(new Transaction(id, sender, receiver, totalAmount, 0L));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return transactions;
    }

    @Override
    public Transaction find(Long id) {
        try {
            Object obj = parser.parse(new FileReader(filePath));
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray transactionList = (JSONArray) jsonObject.get("data");
            Iterator<JSONObject> iterator = transactionList.iterator();
            while (iterator.hasNext()) {
                JSONObject object = iterator.next();
                Long transId = (Long) object.get("id");
                if (transId.equals(id)) {
                    String sender = (String) object.get("sender");
                    String receiver = (String) object.get("receiver");
                    Long totalAmount = (Long) object.get("totalAmount");
                    return new Transaction(transId, sender, receiver, totalAmount, 0L);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
