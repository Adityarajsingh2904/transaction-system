package com.example.transaction.repository;

import com.example.transaction.model.Transaction;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Repository;
import java.io.FileWriter;
import java.time.LocalDateTime;

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
                String createdAtStr = (String) object.get("createdAt");
                LocalDateTime createdAt = LocalDateTime.parse(createdAtStr);
                transactions.add(new Transaction(id, sender, receiver, totalAmount, 0L, createdAt));
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
                    String createdAtStr = (String) object.get("createdAt");
                    LocalDateTime createdAt = LocalDateTime.parse(createdAtStr);
                    return new Transaction(transId, sender, receiver, totalAmount, 0L, createdAt);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Transaction save(Transaction transaction) {
        try {
            // Load existing transactions
            List<Transaction> transactions = findAll();
            transactions.add(transaction);

            JSONArray array = new JSONArray();
            for (Transaction t : transactions) {
                JSONObject obj = new JSONObject();
                obj.put("id", t.getId());
                obj.put("sender", t.getSender());
                obj.put("receiver", t.getReceiver());
                obj.put("totalAmount", t.getTotalAmount());
                obj.put("createdAt", t.getCreatedAt().toString());
                array.add(obj);
            }

            JSONObject root = new JSONObject();
            root.put("data", array);

            FileWriter writer = new FileWriter(filePath);
            writer.write(root.toJSONString());
            writer.close();
            return transaction;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
