package com.ezpay.controller;
import com.ezpay.entity.PaymentReminder;
import com.ezpay.service.PaymentReminderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
/**
 * PaymentReminderController class handles REST API requests for payment
 * reminders.
 * 
 * Author: Doneela Das
 * Date: 02.09.2024
 */
@RestController
@RequestMapping("/api/payment-reminders")
public class PaymentReminderController {
    @Autowired
    private PaymentReminderService paymentReminderService;
    /**
     * Adds a new payment reminder.
     * 
     * @param userId  User ID for the reminder.
     * @param amount  Amount to be reminded about.
     * @param dueDate Due date for the payment.
     * @param status  Status of the payment.
     * @return Success or failure message.
     */
    @PostMapping("/add")
    public String addPaymentReminder(@RequestParam String userId,
            @RequestParam Double amount,
            @RequestParam String dueDate,
            @RequestParam String status) throws ParseException {
        String reminderId = Integer.toString((new Random()).nextInt(Integer.MAX_VALUE));
        Date parsedDueDate = new SimpleDateFormat("dd-MM-yyyy").parse(dueDate);
        boolean addStatus = paymentReminderService.addPaymentReminderService(reminderId, userId, amount, parsedDueDate,
                status);
        return addStatus ? "Reminder added successfully!" : "Reminder addition failed!";
    }
    /**
     * Deletes a payment reminder by ID.
     * 
     * @param reminderId The ID of the reminder to be deleted.
     * @return Success or failure message.
     */
    @DeleteMapping("/delete/{reminderId}")
    public String deletePaymentReminder(@PathVariable String reminderId) {
        boolean deleteStatus = paymentReminderService.deletePaymentReminderService(reminderId);
        return deleteStatus ? "Reminder deleted successfully!" : "Reminder deletion failed!";
    }
    /**
     * Deletes all payment reminders for a specific user.
     * 
     * @param userId The user ID whose reminders are to be deleted.
     * @return The number of reminders deleted.
     */
    @DeleteMapping("/delete-all/{userId}")
    public String deleteAllUserReminders(@PathVariable String userId) {
        int deleteCount = paymentReminderService.deleteAllUserRemindersService(userId);
        return deleteCount + " payment reminders were deleted!";
    }
    /**
     * Fetches all payment reminders for a specific user that are due within the
     * next 3 days.
     * 
     * @param userId The user ID for which reminders are fetched.
     * @return List of PaymentReminder objects.
     */
    @GetMapping("/fetch/{userId}")
    public List<PaymentReminder> fetchPaymentReminders(@PathVariable String userId) {
        return paymentReminderService.fetchPaymentRemindersService(userId);
    }
}