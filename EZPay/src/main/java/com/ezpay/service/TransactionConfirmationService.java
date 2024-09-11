package com.ezpay.service;

import com.ezpay.entity.Notification;
import com.ezpay.entity.Transaction;
import com.ezpay.entity.User;
import com.ezpay.repository.NotificationRepo;
import com.ezpay.repository.TransactionRepo;
import com.ezpay.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.text.SimpleDateFormat;

/**
 * TransactionConfirmationService class This service class handles the business
 * logic for
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
	 * Processes a transaction by saving it to the database and generating a
	 * notification if the user has enabled notifications.
	 * 
	 * @param transaction The transaction object to be processed.
	 */
	public void processTransaction(Transaction transaction) {
		// Extract the user ID from the transaction
		String userId = transaction.getUserId();
		// System.out.println("UID: " + uid);
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
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		        String formattedDate = formatter.format(transaction.getDateOfTransaction());
				Notification notification = new Notification(
					    "Transaction "+ transaction.getStatus() + "\n" +
					    "Transaction ID: " + transaction.getId() + "\n" +
					    "Amount: ₹" + String.format("%.2f", transaction.getAmount()) + "\n" +
					    "Type: " + transaction.getType() + "\n" +
					    "Date: " + formattedDate,
					    userId,
					    transaction.getId()
					);
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
	 * @return A list of Notification objects for the user, or null if notifications
	 *         are disabled or user not found.
	 */
	public List<Notification> getNotificationsForUser(String userId) {
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
	
	/**
     * Deletes all notification for a given user ID.
     * 
     * @param userID The ID of the user whose notifications are to be deleted.
     */
	public void deleteAllNotifications(String userId) {
		// Find notifications by userId
		List<Notification> notifications = notificationRepo.findByUserId(userId);
        if (notifications != null && !notifications.isEmpty()) {
        	// Delete all notifications
            notificationRepo.deleteAll(notifications);
        }
    }
	
	/**
     * Deletes a specific notification by its ID.
     * 
     * @param notificationId The ID of the notification to delete.
     * @return true if deletion is successful, false if not found.
     */
    public boolean deleteNotificationById(Long notificationId) {
        if (notificationRepo.existsById(notificationId)) {
            notificationRepo.deleteById(notificationId);
            return true;
        }
        return false;
    }
	
	
}