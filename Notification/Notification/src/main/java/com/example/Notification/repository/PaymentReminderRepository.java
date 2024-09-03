package com.example.Notification.repository;

import com.example.Notification.model.PaymentReminder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * PaymentReminderRepo interface Repository interface for PaymentReminder
 * entity, providing CRUD operations. This interface allows for interaction with
 * the database for PaymentReminder-related queries.
 * 
 * Author: Doneela Das Date: 02-09-2024
 */
@Repository
public interface PaymentReminderRepository extends JpaRepository<PaymentReminder, String> {

	/**
	 * Adds a new payment reminder.
	 * 
	 * @param reminderId the unique reminder ID.
	 * @param userId     the user ID associated with the reminder.
	 * @param amount     the amount to be reminded about.
	 * @param dueDate    the due date of the payment.
	 * @param status     the status of the reminder.
	 * @return the created PaymentReminder object.
	 */
	default boolean addPaymentReminder(String reminderId, String userId, Double amount, Date dueDate, String status) {
		PaymentReminder reminder = new PaymentReminder(reminderId, userId, amount, dueDate, status);
		return save(reminder) != null;
	}

	/**
	 * Deletes a payment reminder by reminder ID.
	 * 
	 * @param reminderId the unique reminder ID.
	 * @return true if the reminder was deleted, false otherwise.
	 */
	default boolean deletePaymentReminder(String reminderId) {
		if (existsById(reminderId)) {
			deleteById(reminderId);
			return true;
		}
		return false;
	}

	/**
	 * Deletes all payment reminders for a specific user.
	 * 
	 * @param userId the unique user ID.
	 * @return the number of reminders deleted.
	 */
	default int deleteAllUserReminders(String userId) {
		List<PaymentReminder> reminders = findByUserId(userId);
		int count = reminders.size();
		deleteAll(reminders);
		return count;
	}

	/**
	 * Fetches payment reminders for a specific user that are due within the next 3
	 * days.
	 * 
	 * @param userId the unique user ID.
	 * @return a list of PaymentReminder objects.
	 */
	@Query("SELECT pr FROM PaymentReminder pr WHERE pr.userId = :userId AND pr.dueDate BETWEEN CURRENT_DATE AND CURRENT_DATE + 3")
	List<PaymentReminder> findByUserId(@Param("userId") String userId);

}