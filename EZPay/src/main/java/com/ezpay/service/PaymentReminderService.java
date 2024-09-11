package com.ezpay.service;

import com.ezpay.entity.PaymentReminder;
import com.ezpay.repository.PaymentReminderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * PaymentReminderService class
 * This service class handles the business logic for managing payment reminders.
 * It interacts with the repository layer to perform CRUD operations on
 * PaymentReminder entities.
 * 
 * Author: Doneela Das
 * Date: 02-09-2024
 */
@Service
public class PaymentReminderService {

    @Autowired
    private PaymentReminderRepository paymentReminderRepository;

    /**
     * Adds a new payment reminder.
     * 
     * @param reminderId Unique reminder ID.
     * @param userId     Unique user identification code.
     * @param amount     Amount due in the payment.
     * @param dueDate    Due date of the payment.
     * @param status     Reminder status.
     * @return Boolean Acknowledging the addition of the payment reminder.
     */
    public boolean addPaymentReminderService(String reminderId, String userId, Double amount, Date dueDate,
            String status) {
        if (reminderId != null && userId != null) {
            return paymentReminderRepository.addPaymentReminder(reminderId, userId, amount, dueDate, status);
        }
        return false;
    }

    /**
     * Deletes a payment reminder based on the unique reminder ID.
     * 
     * @param reminderId Unique reminder ID to identify the record to be deleted.
     * @return Boolean Acknowledging the deletion of the payment reminder.
     */
    public boolean deletePaymentReminderService(String reminderId) {
        if (reminderId != null) {
            return paymentReminderRepository.deletePaymentReminder(reminderId);
        }
        return false;
    }

    /**
     * Deletes all payment reminders for a specific user.
     * 
     * @param userId Unique key to find and delete all reminders for a user.
     * @return Integer Number of reminders deleted as an acknowledgement.
     */
    public int deleteAllUserRemindersService(String userId) {
        if (userId != null) {
            return paymentReminderRepository.deleteAllByUserId(userId);
        }
        return 0;
    }

    /**
     * Fetches all payment reminders for a specific user.
     * 
     * @param userId Unique key to identify the user.
     * @return List<PaymentReminder> List of payment reminders for the given user.
     */
    public List<PaymentReminder> fetchPaymentRemindersService(String userId) {
        if (userId != null) {
            return paymentReminderRepository.findRemindersByUserId(userId);
        }
        return List.of(); // Return an empty list if userId is null
    }
}
