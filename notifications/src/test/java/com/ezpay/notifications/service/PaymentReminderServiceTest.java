package com.ezpay.notifications.service;

import com.ezpay.notifications.repo.PaymentReminderRepo;
import com.ezpay.notifications.model.PaymentReminder;
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
            {"R05U01", "U01", 500.00, new Date(), "Pending", true, 0, 4},
            {null, "U01", 100.00, new Date(), "Pending", false, 0, 0}, // Invalid addition

            // Test case for deleting payment reminder
            {"R01U01", "U01", 0.0, null, null, true, 1, 0}, // Valid delete by reminder ID
            {"R100U01", "U01", 0.0, null, null, false, 0, 0}, // Invalid delete (non-existing ID)

            // Test case for deleting all reminders for a user
            {null, "U01", 0.0, null, null, true, 4, 0}, // Delete all reminders for U01
            {null, "U100", 0.0, null, null, false, 0, 0}, // Delete all for non-existing user ID

            // Test case for fetching payment reminders
            {null, "U01", 0.0, null, null, true, 0, 4}, // Fetch all reminders for U01
            {null, "U100", 0.0, null, null, false, 0, 0} // Fetch reminders for non-existing user ID
        });
    }

    @Test
    public void testAddPaymentReminderService() {
        boolean result = paymentReminderService.addPaymentReminderService(reminderId, userId, amount, dueDate, status);
        assertEquals(expectedResult, result);
    }

    @Test
    public void testDeletePaymentReminderService() {
        boolean result = paymentReminderService.deletePaymentReminderService(reminderId);
        assertEquals(expectedResult, result);
    }

    @Test
    public void testDeleteAllUserRemindersService() {
        int result = paymentReminderService.deleteAllUserRemindersService(userId);
        assertEquals(expectedDeleteCount, result);
    }

    @Test
    public void testFetchPaymentRemindersService() {
        int result = paymentReminderService.fetchPaymentRemindersService(userId).size();
        assertEquals(expectedFetchSize, result);
    }
}
