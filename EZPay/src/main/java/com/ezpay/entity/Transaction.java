package com.ezpay.entity;

import java.sql.Timestamp;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.util.Date;

/**
 * Transaction class
 * Represents a financial transaction entity.
 * No external module used
 * @author Akhil Kholia
 * @date 2024-08-31
 */
@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transaction_seq")
    @SequenceGenerator(name = "transaction_seq", sequenceName = "TRANSACTION_SEQ", allocationSize = 1)
    private Long id;
    private String userId;
    private String type;
    private double  amount;
    @Temporal(TemporalType.DATE)
    private Date dateOfTransaction;
    private String status;
    
    public Transaction() {}

    //Getters and Setters
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


	  public Date getDateOfTransaction() {
		  return dateOfTransaction;
	}
	
	  public void setDateOfTransaction(Date dateOfTransaction) {
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

	  public String getUserId() {
		  return userId;
    }
    
	  public void setUserId(String userId) {
		  this.userId = userId;
    }

    
}
