package com.ezpay.service;

import com.ezpay.entity.PaymentReminder;
import com.ezpay.repository.PaymentReminderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * PaymentReminderService class
 * This service class handles the business logic for managing payment reminders
 * and sending email notifications for upcoming payments.
 * 
 * Author: Doneela Das
 * Date: 12-09-2024
 */
@Service
public class PaymentReminderService {

    @Autowired
    private PaymentReminderRepository paymentReminderRepository;

    @Autowired
    private JavaMailSender mailSender;

    /**
     * Adds a new payment reminder.
     * 
     * @param reminderId Unique reminder ID.
     * @param userId     Unique user identification code.
     * @param amount     Amount due in the payment.
     * @param dueDate    Due date of the payment.
     * @param status     Reminder status.
     * @return Boolean Acknowledging the addition of the payment reminder.
     * 
     *         Adds a new payment reminder.
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
     */
    public List<PaymentReminder> fetchPaymentRemindersService(String userId) {
        if (userId != null) {
            return paymentReminderRepository.findRemindersByUserId(userId);
        }
        return List.of(); // Return an empty list if userId is null
    }

    /**
     * Scheduled task to send email notifications for payment reminders.
     * This method runs once a day.
     */
    @Scheduled(cron = "0 0 9 * * ?") // Runs daily at 9 AM
    public void sendReminderEmails() {
        List<PaymentReminder> reminders = paymentReminderRepository.findAll(); // Fetch all reminders

        Date today = new Date();
        for (PaymentReminder reminder : reminders) {
            long daysDifference = calculateDaysDifference(today, reminder.getDueDate());
            if (daysDifference == 0) {
                sendReminderEmail(reminder, "Today is the payment deadline.");
            } else if (daysDifference == 1) {
                sendReminderEmail(reminder, "Your payment is due tomorrow.");
            } else if (daysDifference == 3) {
                sendReminderEmail(reminder, "Your payment is due in 3 days.");
            }
        }
    }

    /**
     * Helper method to calculate the difference in days between two dates.
     */
    private long calculateDaysDifference(Date currentDate, Date dueDate) {
        long diffInMillies = Math.abs(dueDate.getTime() - currentDate.getTime());
        return TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
    }

    /**
     * Sends the reminder email to the user.
     */
    private void sendReminderEmail(PaymentReminder reminder, String message) {
        try {
            MimeMessage email = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(email, true, "UTF-8");
            helper.setTo(reminder.getUserEmail());
            helper.setSubject("EZPay : Payment Reminder");

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String formattedDueDate = formatter.format(reminder.getDueDate());

            String content = "<p>Dear " + reminder.getUserId() + ",</p>"
                    + "<p>" + message + " Please find the details below:</p>"
                    + "<p><strong>Reminder ID:</strong> " + reminder.getReminderId() + "<br>"
                    + "<strong>Amount:</strong> ₹" + String.format("%.2f", reminder.getAmount()) + "<br>"
                    + "<strong>Due Date:</strong> " + formattedDueDate + "</p>"
                    + "<p>If you have already made this payment, please disregard this reminder.</p>"
                    + "<p>Thank you for using our services!</p>"
                    + "<p>Best regards,<br>EZPay Customer Support</p>";

            helper.setText(content, true); // Set to true for HTML content
            mailSender.send(email);
        } catch (MessagingException e) {
            System.out.println("Failed to send reminder email: " + e.getMessage());
        }
    }
}
