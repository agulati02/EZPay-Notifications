package com.ezpay.notifications.payment_reminder.service;

/* 
 * Author : Doneela Das
 * Date: 11.08.2024
 */


import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.ezpay.notifications.payment_reminder.repo.PaymentReminderRepo;

// Test class for PaymentReminderService
public class PaymentReminderServiceTest {

    private PaymentReminderService paymentReminderService;

    // Simple in-memory repository to simulate the real PaymentReminderRepo
    class InMemoryPaymentReminderRepo extends PaymentReminderRepo {
        private Map<String, Map<String, Object>> reminders = new HashMap<>();

        @Override
        public boolean addPaymentReminder(String reminderId, String userId, Double amount, Date dueDate, String status) {
            if (reminderId == null || userId == null) return false;
            Map<String, Object> reminderDetails = new HashMap<>();
            reminderDetails.put("userId", userId);
            reminderDetails.put("amount", amount);
            reminderDetails.put("dueDate", dueDate);
            reminderDetails.put("status", status);
            reminders.put(reminderId, reminderDetails);
            return true;
        }

        @Override
        public boolean deletePaymentReminder(String reminderId) {
            if (reminderId == null || !reminders.containsKey(reminderId)) return false;
            reminders.remove(reminderId);
            return true;
        }

        @Override
        public Integer deleteAllUserReminders(String userId) {
            if (userId == null) return 0;
            int count = 0;
            ArrayList<String> keysToRemove = new ArrayList<>();
            for (String reminderId : reminders.keySet()) {
                if (reminders.get(reminderId).get("userId").equals(userId)) {
                    keysToRemove.add(reminderId);
                    count++;
                }
            }
            for (String key : keysToRemove) {
                reminders.remove(key);
            }
            return count;
        }

        @Override
        public ArrayList<String> fetchPaymentReminders(String userId) {
            ArrayList<String> userReminders = new ArrayList<>();
            if (userId == null) return userReminders;
            for (String reminderId : reminders.keySet()) {
                if (reminders.get(reminderId).get("userId").equals(userId)) {
                    userReminders.add(reminderId);
                }
            }
            return userReminders;
        }
    }

    @Before
    public void setUp() {
        // Initialize the service with the in-memory repository
        paymentReminderService = new PaymentReminderService(new InMemoryPaymentReminderRepo());
    }

    @Test
    public void testAddPaymentReminderService() {
        // Test adding a valid payment reminder
        boolean result = paymentReminderService.addPaymentReminderService("R001", "U001", 100.0, new Date(), "Pending");
        assertTrue(result);

        // Test adding a payment reminder with null inputs
        result = paymentReminderService.addPaymentReminderService(null, "U001", 100.0, new Date(), "Pending");
        assertFalse(result);
    }

    @Test
    public void testDeletePaymentReminder() {
        // Add a reminder to delete
        paymentReminderService.addPaymentReminderService("R001", "U001", 100.0, new Date(), "Pending");

        // Test deleting the reminder
        boolean result = paymentReminderService.deletePaymentReminderService("R001");
        assertTrue(result);

        // Test deleting a non-existing reminder
        result = paymentReminderService.deletePaymentReminderService("R002");
        assertFalse(result);

        // Test deleting with a null ID
        result = paymentReminderService.deletePaymentReminderService(null);
        assertFalse(result);
    }

    @Test
    public void testDeleteAllUserReminders() {
        // Add multiple reminders for the same user
        paymentReminderService.addPaymentReminderService("R001", "U001", 100.0, new Date(), "Pending");
        paymentReminderService.addPaymentReminderService("R002", "U001", 200.0, new Date(), "Pending");

        // Test deleting all reminders for the user
        int result = paymentReminderService.deleteAllUserRemindersService("U001");
        assertEquals(2, result);

        // Test deleting reminders for a user with no reminders
        result = paymentReminderService.deleteAllUserRemindersService("U002");
        assertEquals(0, result);

        // Test deleting reminders with a null userId
        result = paymentReminderService.deleteAllUserRemindersService(null);
        assertEquals(0, result);
    }

    @Test
    public void testFetchPaymentReminders() {
        // Add reminders for a user
        paymentReminderService.addPaymentReminderService("R001", "U001", 100.0, new Date(), "Pending");
        paymentReminderService.addPaymentReminderService("R002", "U001", 200.0, new Date(), "Pending");

        // Test fetching reminders for the user
        ArrayList<String> reminders = paymentReminderService.fetchPaymentRemindersService("U001");
        assertEquals(2, reminders.size());
        assertTrue(reminders.contains("R001"));
        assertTrue(reminders.contains("R002"));

        // Test fetching reminders for a user with no reminders
        reminders = paymentReminderService.fetchPaymentRemindersService("U002");
        assertTrue(reminders.isEmpty());

        // Test fetching reminders with a null userId
        reminders = paymentReminderService.fetchPaymentRemindersService(null);
        assertTrue(reminders.isEmpty());
    }
}

