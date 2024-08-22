package com.ezpay.notifications.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import com.ezpay.notifications.model.PaymentReminder;
import com.ezpay.notifications.repo.PaymentReminderRepo;
import com.ezpay.notifications.service.PaymentReminderService;

/**
 * Controller module to interact with the underlying
 * service layer of payment reminders.
 * 
 * Author: Anurag Gulati
 * Date: 11.08.2024
 */
public class PaymentReminderController {

	/**
	 * Display the list of available functionalities to the user.
	 */
	public void displayMenu() {
		System.out.println("Payment Reminder Menu");
		System.out.println("1. Add a new payment reminder.");
		System.out.println("2. Delete a payment reminder.");
		System.out.println("3. Display all payment reminders.");
		System.out.println("4. Delete my payment reminders.");		
		System.out.println("5. Exit the program.");
	}
	
	/**
	 * Prompt the user for reminder details and add a new
	 * payment reminder.
	 */
	public void addPaymentReminderController(PaymentReminderService paymentReminderService, BufferedReader bufReader) throws IOException, ParseException {
		String reminderId = Integer.toString((new Random()).nextInt(Integer.MAX_VALUE));
		System.out.print("Enter your user ID: ");
		String userId = bufReader.readLine();
		System.out.print("Enter the amount (Rs.): ");
		Double amount = Double.parseDouble(bufReader.readLine());
		System.out.print("Enter the due date (DD-MM-YYYY): ");
		Date dueDate = (new SimpleDateFormat("dd-MM-yyyy")).parse(bufReader.readLine());
		System.out.print("Enter the status: ");
		String status = bufReader.readLine();
		
		boolean addStatus = paymentReminderService.addPaymentReminderService(reminderId, userId, amount, dueDate, status);
		if(addStatus) {
			System.out.println("Reminder added successfully!");
		} else {
			System.out.println("Reminder addition failed!");
		}
	}
	
	/**
	 * Prompt the user for reminder ID to be deleted.
	 */
	public void deletePaymentReminderController(PaymentReminderService paymentReminderService, BufferedReader bufReader) throws IOException {
		System.out.print("Enter the reminder ID to be deleted: ");
		String reminderId = bufReader.readLine();
		boolean deleteStatus = paymentReminderService.deletePaymentReminderService(reminderId);
		if(deleteStatus) {
			System.out.println("Reminder deleted successfully!");
		} else {
			System.out.println("Reminder deletion failed!");
		}
	}
	
	/**
	 * Prompt the user for their details to remove all reminders.
	 */
	public void deleteAllUserRemindersController(PaymentReminderService paymentReminderService, BufferedReader bufReader) throws IOException {
		System.out.print("Enter your user ID: ");
		String userId = bufReader.readLine();
		Integer deleteCount = paymentReminderService.deleteAllUserRemindersService(userId);
		System.out.println(deleteCount + " payment reminders were deleted!");
	}
	
	/**
	 * Prompt the user for their details to display all the 
	 * upcoming or pending payments within the next 3 days.
	 */
	public void fetchPaymentRemindersController(PaymentReminderService paymentReminderService, BufferedReader bufReader) throws IOException {
		System.out.print("Enter your user ID: ");
		String userId = bufReader.readLine();
		ArrayList<PaymentReminder> listOfReminders = paymentReminderService.fetchPaymentRemindersService(userId);
		if(listOfReminders.isEmpty()) {
			System.out.println("No payment reminders were found!");
		} else {
			for(PaymentReminder reminder : listOfReminders) {
				System.out.println(reminder);
			}
		}
	}
	
	public static void main(String[] args) throws IOException, ParseException {
		
		BufferedReader bufReader = new BufferedReader(new InputStreamReader(System.in));
		PaymentReminderController paymentReminderController = new PaymentReminderController();
		
		PaymentReminderRepo paymentReminderRepo = new PaymentReminderRepo();
		PaymentReminderService paymentReminderService = new PaymentReminderService(paymentReminderRepo);
		
		while(true) {
			paymentReminderController.displayMenu();
			System.out.print("Choose an option (1/2/3/4): ");
			Integer choice = Integer.parseInt(bufReader.readLine());
			
			switch(choice) {
			case 1:	// CASE: Add a new payment reminder
			{
				paymentReminderController.addPaymentReminderController(paymentReminderService, bufReader);
				break;
			}
			case 2:	// CASE: Delete a unique payment reminder
			{
				paymentReminderController.deletePaymentReminderController(paymentReminderService, bufReader);
				break;
			}
			case 3:	// CASE: Display all reminders of a user (due within 3 days)
			{
				paymentReminderController.fetchPaymentRemindersController(paymentReminderService, bufReader);
				break;
			}
			case 4:	// CASE: Delete all reminders of a user
			{
				paymentReminderController.deleteAllUserRemindersController(paymentReminderService, bufReader);
				break;
			}
			case 5: // CASE: Exit the program
			{
				return;
			}
			default: // CASE: Invalid choice
			{
				System.out.println("Invalid choice. Try again.");
				break;
			}
			}
			System.out.println();
		}

	}

}
