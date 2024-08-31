package com.example.Notification.controller;

import com.example.Notification.model.Notification;
import com.example.Notification.model.Transaction;
import com.example.Notification.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * NotificationController class Handles REST API requests for notifications and
 * transactions.
 * 
 * @author Akhil Kholia
 * @date 2024-08-31
 */

@RestController
@RequestMapping("/api")
public class NotificationController {
	@Autowired
	NotificationService notificationService;

//    @GetMapping("/test")
//    public String test(){
//        return "testing";
//    }

	/**
	 * Processes a transaction and triggers notification logic.
	 * 
	 * @param transaction Transaction object containing details of the transaction
	 *                    to process.
	 */
	@PostMapping("/processTransaction")
	public void processTransaction(@RequestBody Transaction transaction) {
		// Log transaction details for debugging
//		System.out.println(transaction.getId() + "---------" + transaction.getUserId());
		notificationService.processTransaction(transaction);
	}

	/**
	 * Retrieves notifications for a specific user.
	 * 
	 * @param uid User ID for which notifications are fetched.
	 * @return List of Notification objects.
	 */
	@GetMapping("/notifications/{uid}")
	public List<Notification> getUserNotification(@PathVariable Long uid) {
		return notificationService.getNotificationsForUser(uid);
	}
}
