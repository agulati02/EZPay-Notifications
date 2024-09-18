package com.ezpay.repository;

import com.ezpay.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * TransactionRepo interface
 * Repository interface for Transaction entity, providing CRUD operations.
 * This interface allows for interaction with the database for
 * Transaction-related queries.
 * No external module used
 * 
 * @author Akhil Kholia
 * @date 2024-08-31
 */
@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
	// Using default CRUD operations provided by JpaRepository
}
