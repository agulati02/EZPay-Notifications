package com.ezpay.notifications.service;

import com.ezpay.notifications.repo.PaymentReminderRepo;
import com.ezpay.notifications.model.PaymentReminder;

import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

/**
 * This module is to test the payment reminder system from its
 * service layer. Parameterized tests are generated and tested for each method.
 * 
 * Author: Anurag Gulati
 * Date: 21.08.2024
 * */

@RunWith(Parameterized.class)
public class PaymentReminderServiceTest {

    private PaymentReminderService paymentReminderService;

    // Parameters for the test cases
    private String reminderId;
    private String userId;
    private Double amount;
    private Date dueDate;
    private String status;
    private boolean expectedResult;
    private int expectedDeleteCount;
    private int expectedFetchSize;

    public PaymentReminderServiceTest(String reminderId, String userId, Double amount, Date dueDate, String status, boolean expectedResult, int expectedDeleteCount, int expectedFetchSize) {
        this.reminderId = reminderId;
        this.userId = userId;
        this.amount = amount;
        this.dueDate = dueDate;
        this.status = status;
        this.expectedResult = expectedResult;
        this.expectedDeleteCount = expectedDeleteCount;
        this.expectedFetchSize = expectedFetchSize;
    }

    @Before
    public void setUp() {
        paymentReminderService = new PaymentReminderService(new PaymentReminderRepo());
    }

    // Helper method to parse dates
    private Date parseDate(String date) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(date);
        } catch (ParseException e) {
            return null;
        }
    }

    @Parameterized.Parameters
    public static Collection<Object[]> testData() {
        return Arrays.asList(new Object[][]{
            // Test case for adding payment reminders
            {"R05U01", "U001", 500.00, new Date(), "Pending", true, 0, 3}, // Valid addition
            {null, "U001", 100.00, new Date(), "Pending", false, 0, 0}, // Invalid addition

            // Test case for deleting payment reminder
            {"R01U02", "U02", 0.0, null, null, true, 1, 0}, // Valid delete by reminder ID
            {"R100U01", "U100", 0.0, null, null, false, 0, 0}, // Invalid delete (non-existing ID)

            // Test case for deleting all reminders for a user
            {null, "U03", 0.0, null, null, true, 4, 0}, // Delete all reminders for U03
            {null, "U300", 0.0, null, null, false, 0, 0}, // Delete all for non-existing user ID

            // Test case for fetching payment reminders
            {null, "U04", 0.0, null, null, true, 0, 4}, // Fetch all reminders for U04
            {null, "U400", 0.0, null, null, false, 0, 0} // Fetch reminders for non-existing user ID
        });
    }

    @Test
    public void testAddPaymentReminderService() {
    	Assume.assumeTrue(userId.equals("U001"));
    	
        boolean result = paymentReminderService.addPaymentReminderService(reminderId, userId, amount, dueDate, status);
        assertEquals(expectedResult, result);
    }

    @Test
    public void testDeletePaymentReminderService() {
    	Assume.assumeTrue(userId.equals("U02") || userId.equals("U100"));
    	
        boolean result = paymentReminderService.deletePaymentReminderService(reminderId);
        assertEquals(expectedResult, result);
    }

    @Test
    public void testDeleteAllUserRemindersService() {
    	Assume.assumeTrue(userId.equals("U03") || userId.equals("U300"));
    	
        int result = paymentReminderService.deleteAllUserRemindersService(userId);
        assertEquals(expectedDeleteCount, result);
    }

    @Test
    public void testFetchPaymentRemindersService() {
    	Assume.assumeTrue(userId.equals("U04") || userId.equals("U400"));
    	
        int result = paymentReminderService.fetchPaymentRemindersService(userId).size();
        assertEquals(expectedFetchSize, result);
    }
}
