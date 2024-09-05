package com.example.Notification.service;

import com.example.Notification.model.Notification;
import com.example.Notification.model.Transaction;
import com.example.Notification.model.User;
import com.example.Notification.repository.NotificationRepo;
import com.example.Notification.repository.TransactionRepo;
import com.example.Notification.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * TransactionConfirmationService class This service class handles the business logic for
 * processing transactions and managing notifications. It interacts with the
 * repository layer to perform CRUD operations on Transaction and Notification
 * entities.
 * 
 * @author Akhil Kholia
 * @date 2024-08-31
 */
@Service
public class TransactionConfirmationService {

	@Autowired
	NotificationRepo notificationRepo;

	@Autowired
	UserRepo userRepo;

	@Autowired
	TransactionRepo transactionRepo;
	

	/**
     * Processes a transaction by saving it to the database and generating a notification if the user has enabled notifications.
     * 
     * @param transaction The transaction object to be processed.
     */
	public void processTransaction(Transaction transaction) {
		// Extract the user ID from the transaction
		Long userId = transaction.getUserId();
//		System.out.println("UID: " + uid);
		User user = userRepo.findUserById(userId);
		if (user != null) {

			// Save the transaction to the database
			try {
				transactionRepo.save(transaction);
			} catch (Exception e) {
				System.out.println("Exception occured while saving Transaction: " + e);
			}

			// Check if notifications are enabled for the user
			if (user.isNotificationsEnabled()) {
				// Create and save a new notification for the transaction
				Notification notification = new Notification("Transaction " + transaction.getStatus() + " - Transaction ID: " + transaction.getId(), userId, transaction.getId());
                notificationRepo.save(notification);
			}
		} else {
			System.out.println("User not found with ID: " + userId);
		}
	}
	

	/**
     * Retrieves the list of notifications for a given user ID.
     * 
     * @param userId The ID of the user for whom to retrieve notifications.
     * @return A list of Notification objects for the user, or null if notifications are disabled or user not found.
     */
	public List<Notification> getNotificationsForUser(Long userId) {
		// Find user by ID
		User user = userRepo.findUserById(userId);
		if (user != null) {
			// Check if notifications are enabled for the user
			if (user.isNotificationsEnabled()) {
				return notificationRepo.findByUserId(userId);
			} else {
				System.out.println("Notifications are disabled for user with ID: " + userId);
				return null;
			}
		} else {
			System.out.println("User not found with ID: " + userId);
		}

		return null;
	}
}