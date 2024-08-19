# EZPAY Notifications System (SECURE AND EFFICIENT DIGITAL PAYMENT SOLUTION)

EzPay aims to develop a secure and efficient digital payment application that allows users to make
UPI payments and bank transfers, manage their transactions and receive timely notifications. The
app will provide a user-friendly interface accessible across various devices, ensuring a seamless
payment experience for users while maintaining high-security standards and regulatory compliance.
---
## Key Features:

User registration and authentication
Profile management
UPI payments
Bank transfers
Transaction history and status tracking
Notification system for confirmations and reminders
Responsive design for multi-device accessibility
Robust security measures including data encryption and fraud prevention
Compliance with financial regulations 

## Key Assumptions and Constraints
### Assumptions
- Users have access to smartphones or computers with internet connectivity.
- Users have valid bank accounts and/or UPI IDs for making transactions.
- The necessary APIs and integrations with banks and UPI services are available.
- Users will provide accurate personal information during registration.

### Constraints:
- The app must comply with relevant financial regulations and data protection laws.
- The system must integrate securely with existing banking and UPI infrastructures.
- The app must maintain high performance and low latency even with a large user base.
- Development must be completed within the specified timeframe and budget.
- The app must comply with relevant financial regulations and data protection laws.

---

## Notification System
### User story 1: Transaction Confirmations

- Actors: User, System
- Description: The system sends transaction confirmation to the user.
- Preconditions: User has completed a transaction and has enabled notifications.
- Normal Course:
  1. The system processes a completed transaction.
  2. The system generates a confirmation message with transaction details.
  3. The system sends the confirmation to the user via push notification and email.
  4. The user receives and views the confirmation.

### User story 2: Payment Reminders

- Actors: User, System
- Description: The system sends payment reminders to the user.
- Preconditions: The user has scheduled payments or recurring bills in the system and has enabled reminders.
- Normal Course:
  1. The system identifies upcoming payments based on the user's schedule.
  2. The system generates reminder messages for payments due within the next 3 days.
  3. The system sends reminders to the user via push notification and email.
  4. The user receives and views the reminders.

## Architecture Diagram

 ![image](https://github.com/user-attachments/assets/43160711-927a-4032-8d9a-8bb7d5f26b4b)



__By__ Doneela Das, Geethapriya, Akhil Kholia, Anurag Gulati, Jai Singh
<hr/>
