package com.springboot.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.example.model.Transaction;

public interface TransactionRepository  extends JpaRepository<Transaction, Long>{

	boolean existsByTransactionId(String transactionId);
}
