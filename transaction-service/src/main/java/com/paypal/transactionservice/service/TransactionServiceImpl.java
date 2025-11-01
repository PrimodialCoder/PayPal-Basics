package com.paypal.transactionservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.paypal.transactionservice.entity.Transaction;
import com.paypal.transactionservice.kafka.KafkaEventProducer;
import com.paypal.transactionservice.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;
@Service
public class TransactionServiceImpl implements TransactionService {
    private TransactionRepository transactionRepository;
    private ObjectMapper objectMapper;
    private KafkaEventProducer kafkaEventProducer;

    @Autowired
    private RestTemplate restTemplate;

    public TransactionServiceImpl(TransactionRepository transactionRepository, ObjectMapper objectMapper, KafkaEventProducer kafkaEventProducer) {
        this.transactionRepository = transactionRepository;
        this.objectMapper = objectMapper;
        this.kafkaEventProducer = kafkaEventProducer;
    }

    @Override
    public Transaction createTransaction(Transaction transaction) {

        transaction.setTimestamp(LocalDateTime.now());
        transaction.setStatus("SUCCESS");

        Transaction saved = transactionRepository.save(transaction);
        System.out.println("Saved Transaction: " + saved);

        try{
            String key = String.valueOf(saved.getId());
            kafkaEventProducer.sendTransactionEvent(key, saved);
            System.out.println("Sent Transaction: " + saved);
        }catch(Exception e){
            System.out.println("Failed to send kafka event");
            e.printStackTrace();
        }
        return saved;
    }


    @Override
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }
}
