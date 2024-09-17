package com.ezpay.controller;

import com.ezpay.entity.Notification;
import com.ezpay.entity.Transaction;
import com.ezpay.entity.User;
import com.ezpay.service.TransactionConfirmationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * TransactionConfirmationController class Handles REST API requests for
 * notifications and transactions.
 * No external module used
 * 
 * @author Akhil Kholia
 * @date 2024-08-31
 */

@SuppressWarnings("unused")
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class TransactionConfirmationController {
	@Autowired
	TransactionConfirmationService transactionConfirmationService;

	/**
	 * Processes a transaction and triggers notification logic.
	 * 
	 * @param transaction Transaction object containing details of the transaction
	 *                    to process.
	 */
	@PostMapping("/processTransaction")
	public void processTransaction(@RequestBody Transaction transaction) {
		transactionConfirmationService.processTransaction(transaction);
	}

	/**
	 * Retrieves notifications for a specific user.
	 * 
	 * @param uid User ID for which notifications are fetched.
	 * @return List of Notification objects.
	 */
	@GetMapping("/notifications/{uid}")
	public List<Notification> getUserNotification(@PathVariable String uid) {
		return transactionConfirmationService.getNotificationsForUser(uid);
	}
	
	/**
	 * Deletes notifications of a specific user.
	 * 
	 * @param uid User ID for which notifications are fetched.
	 * @return String indicating if notifications are deleted.
	 */
	@DeleteMapping("/clear/{uid}")
    public String clearNotifications(@PathVariable String uid) {
        try {
            transactionConfirmationService.deleteAllNotifications(uid);
            return "Notifications cleared successfully.";
        } catch (Exception e) {
            return "Error clearing notifications.";
        }
    }
	
	/**
     * Deletes a specific notification by its ID.
     * 
     * @param notificationId The ID of the notification to delete.
     * @return A response indicating success or failure.
     */
    @DeleteMapping("/deleteNotification/{notificationId}")
    public String deleteNotification(@PathVariable Long notificationId) {
        boolean isDeleted = transactionConfirmationService.deleteNotificationById(notificationId);
        if (isDeleted) {
            return "Notification deleted successfully.";
        } else {
            return "Notification not found.";
        }
    }


}
