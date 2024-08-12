package com.ezpay.TransactionConfirmation.model;
/*
 * Author Name: Jai Singh 
 * Date Details: 10/08/2024 * Saturday 
 * */
/*
 Module Description
 *The TransactionConfirmationRepository class acts as a data repository for managing 
TransactionConfirmation objects. It maintains a list of transaction confirmations and provides methods 
to check whether a transaction has been completed, whether notifications have been enabled or received by 
the user, to retrieve confirmation messages, and to count or list completed transactions by a specific user.
 The repository uses in-memory storage, making it ideal for testing and prototyping.*/
public class TransactionConfirmation {
	/*
	 * Attributes 
	 * userId: User performing the transaction 
	 * hasCompleted: Whether the Transaction has Completed or Not 
	 * enabledNotification: Whether Notification has been enabled by the User or Not 
	 * confMessage: Confirmation Message sent by the System to the User 
	 * transactionDetail: Transaction Details sent by the System to the User
	 * hasReceived: Whether Notification has been received by the User or Not 
	 * */
	
	private Integer userId;
	private boolean hasCompleted;
	private boolean enabledNotification;
	private String confMessage;
	private Integer transactionId;
	private boolean hasReceived;
	
	/*Getter and Setter of the Attributes Defined Above*/
	public Integer getUserId() {
		return userId;
	}
	
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	
	public boolean getHasCompleted() {
		return hasCompleted;
	}
	public void setHasCompleted(boolean hasCompleted) {
		this.hasCompleted = hasCompleted;
	}
	
	
	public boolean getEnabledNotification() {
		return enabledNotification;
	}
	public void setEnabledNotification(boolean enabledNotification) {
		this.enabledNotification = enabledNotification;
	}
	
	
	public String getConfMessage() {
		return confMessage;
	}
	public void setConfMessage(String confMessage) {
		this.confMessage = confMessage;
	}
	
	
	public Integer getTransactionId() {
		return transactionId;
	}
	
	public void setTransactionId(Integer transactionId) {
		this.transactionId = transactionId;
	}
	
	
	public boolean getHasReceived() {
		return hasReceived;
	}
	
	public void setHasReceived(boolean hasReceived) {
		this.hasReceived = hasReceived;
	}
	
	/*Constructor for the Transaction Confirmation(i)
	 * 1: Default 
	 * 2: Parameters Based( different for different scenario)
	 */
	public TransactionConfirmation() {}
	
	public TransactionConfirmation(Integer userId,Integer transactionId) {
		super();
		setUserId(userId);
		setTransactionId(transactionId);
	}
	

	public TransactionConfirmation(Integer userId) {
		super();
		setUserId(userId);
	}
	
	public TransactionConfirmation(Integer userId, boolean hasCompleted, boolean enabledNotification, String confMessage, Integer transactionId, boolean hasReceived) {
		super();
		setUserId(userId);
		setHasCompleted(hasCompleted);
		setEnabledNotification(enabledNotification);
		setConfMessage(confMessage);
		setTransactionId(transactionId);
		setHasReceived(hasReceived);
	}

}
