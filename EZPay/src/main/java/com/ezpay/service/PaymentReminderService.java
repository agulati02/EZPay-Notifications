package com.ezpay.service;
import com.ezpay.entity.PaymentReminder;
import com.ezpay.repository.PaymentReminderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
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
    private JavaMailSender mailSender;


    // Constructor injection for PaymentReminderRepo and JavaMailSender
    public PaymentReminderService(PaymentReminderRepository paymentReminderRepository, JavaMailSender mailSender) {
        this.paymentReminderRepo = paymentReminderRepo;
        this.mailSender = mailSender;
    }
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

    public String getReminderEmail(String reminderId) {
        PaymentReminder reminder = paymentReminderRepository.findById(reminderId);
        if (reminder != null) {
            String userEmail = userRepository.findById(reminder.getUserId()).getEmail(); 
            return userEmail;
        }
        return null; // Or handle accordingly if reminder not found.
    }

    /**
     * Send reminder emails for payments due within the next 3 days or on the due date.
     */
    public void sendReminderEmails() {
        List<PaymentReminder> reminders = fetchPaymentRemindersWithinNext3Days();
        
        for (PaymentReminder reminder : reminders) {
            String recipientEmail = reminder.getUserEmail();  // Assuming this field exists in PaymentReminder
            String subject = "Payment Reminder: Payment Due Soon";
            String message = generateEmailMessage(reminder);
            
            try {
                sendEmail(recipientEmail, subject, message);
            } catch (MessagingException e) {
                // Handle email sending exception (log it, notify admin, etc.)
                System.err.println("Failed to send email to " + recipientEmail);
                e.printStackTrace();
            }
        }
    }

    /**
     * Generate email message content based on the reminder.
     */
    private String generateEmailMessage(PaymentReminder reminder) {
        return "Dear " + reminder.getUserName() + ",\n\n"
                + "This is a reminder that your payment of " + reminder.getAmountDue()
                + " is due on " + reminder.getDueDate() + ". Please make your payment on or before the due date.\n\n"
                + "Thank you,\nYour Payment Service";
    }

    /**
     * Send email using Spring's JavaMailSender and MimeMessageHelper.
     */
    private void sendEmail(String to, String subject, String content) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(content);

        mailSender.send(mimeMessage);
        System.out.println("Sent message successfully to " + to);
    }
}
