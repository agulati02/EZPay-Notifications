package com.example.Notification.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Transaction class
 * Represents a financial transaction entity.
 * 
 * @author Akhil Kholia
 * @date 2024-08-31
 */
@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transaction_seq")
    @SequenceGenerator(name = "transaction_seq", sequenceName = "TRANSACTION_SEQ", allocationSize = 1)
    private Long id;
    private Long userId;
    private String type;
    private double  amount;
    private Timestamp dateOfTransaction;
    private String status;
    
    public Transaction() {}

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double  getAmount() {
        return amount;
    }

    public void setAmount(double  amount) {
        this.amount = amount;
    }

    public Timestamp getDate() {
        return dateOfTransaction;
    }

    public void setDate(Timestamp dateOfTransaction) {
        this.dateOfTransaction = dateOfTransaction;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
    	this.id = id;
    }

    public Long getUserId() {
        return userId;
    }
    
    public void setUserId(Long userId) {
    	this.userId = userId;
    }

    
}
