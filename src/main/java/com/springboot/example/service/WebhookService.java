package com.springboot.example.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.example.model.Transaction;
import com.springboot.example.repository.TransactionRepository;

@Service
public class WebhookService {

    @Autowired
    private TransactionRepository transactionRepository;

    private ObjectMapper mapper = new ObjectMapper();

    public void processEvent(String payload) throws Exception {

        JsonNode root = mapper.readTree(payload);

        String eventType = root.get("type").asText();

        JsonNode payloadNode = root.get("payload");

        String transactionId = payloadNode.get("id").asText();
        String orderId = payloadNode
                .get("clientReferenceInformation")
                .get("code").asText();

        String status = payloadNode.get("status").asText();

        System.out.println("Event: " + eventType);
        System.out.println("Transaction ID: " + transactionId);
        System.out.println("Order ID: " + orderId);

        if (transactionRepository.existsByTransactionId(transactionId)) {
            System.out.println("Already processed. Skipping.");
            return;
        }

        Transaction txn = new Transaction();
        txn.setTransactionId(transactionId);
        txn.setOrderId(orderId);
        txn.setEventType(eventType);
        txn.setStatus(status);
        txn.setCreatedAt(LocalDateTime.now());

        transactionRepository.save(txn);
    }
}
