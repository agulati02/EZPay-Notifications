# EZPAY Notifications System 
<hr>

#### Table of Contents
1. [Introduction to EZpay](#1-introduction-to-ezpay) <br>
  1.1. [Key features](#11-key-features) <br>
  1.2. [Assumptions](#12-assumptions) <br>
  1.3. [Constraints](#13-constraints) <br>
2. [Notifications System](#2-notifications-system) <br>
  2.1. [User Story 1: Transaction Confirmation](#21-user-story-1-transaction-confirmation) <br>
  2.2. [User Story 2: Payment Reminders](#22-user-story-2-payment-reminders) <br>
3. [Backend](#3-backend) <br>
  3.1. [Setting up the database](#31-setting-up-the-database) <br>
  3.2. [Launching the backend](#32-launching-the-backend) <br>
4. [User Interface](#4-user-interface) <br>
  4.1. [Launching the UI](#41-launching-the-ui) <br>

<hr> 

#### 1. Introduction to EZPay

__EZPay__ aims to develop a secure and efficient digital payment application that allows users to make UPI payments and bank transfers, manage their transactions and receive timely notifications. The app will provide a user-friendly interface accessible across various devices, ensuring a seamless payment experience for users while maintaining high-security standards and regulatory compliance.

#### 1.1. Key Features
<ul>
  <li>User registration and authentication</li>
  <li>Profile management</li>
  <li>UPI payments</li>
  <li>Bank transfers</li>
  <li>Transaction history and status tracking</li>
  <li>Notification system for confirmations and reminders</li>
  <li>Responsive design for multi-device accessibility</li>
  <li>Robust security measures including data encryption and fraud prevention</li>
  <li>Compliance with financial regulations</li>
</ul> 

#### 1.2. Assumptions
- Users have access to smartphones or computers with internet connectivity.
- Users have valid bank accounts and/or UPI IDs for making transactions.
- The necessary APIs and integrations with banks and UPI services are available.
- Users will provide accurate personal information during registration.

#### 1.3. Constraints
- The app must comply with relevant financial regulations and data protection laws.
- The system must integrate securely with existing banking and UPI infrastructures.
- The app must maintain high performance and low latency even with a large user base.
- Development must be completed within the specified timeframe and budget.
- The app must comply with relevant financial regulations and data protection laws.

---

#### 2. Notifications System
#### 2.1. User story 1: Transaction Confirmation

- __Actors:__ User, System
- __Description:__ The system sends transaction confirmation to the user.
- __Preconditions:__ User has completed a transaction and has enabled notifications.
- __Normal Course:__
  1. The system processes a completed transaction.
  2. The system generates a confirmation message with transaction details.
  3. The system sends the confirmation to the user via push notification and email.
  4. The user receives and views the confirmation.

#### 2.2. User story 2: Payment Reminders

- __Actors:__ User, System
- __Description:__ The system sends payment reminders to the user.
- __Preconditions:__ The user has scheduled payments or recurring bills in the system and has enabled reminders.
- __Normal Course:__
  1. The system identifies upcoming payments based on the user's schedule.
  2. The system generates reminder messages for payments due within the next 3 days.
  3. The system sends reminders to the user via push notification and email.
  4. The user receives and views the reminders.

<hr/>

#### 3. Backend 
The backend application has been designed with Spring Boot framework in Java and Spring Data JPA for database connectivity. We have used the Oracle 11g XE database to store all data. The complete working code for the backend is located in the `sprint-3-backend-v3` branch of this repository. To clone the same, please execute the following git command:

```shell
git clone -b sprint-3-backend-v3 git@github.com:agulati02/EZPay-Notifications.git
```

#### 3.1 Setting up the database
In order to set up the database, open the Oracle SQL Command line and execute the following set of commands:

```
> @ "path-to-local-repo/EZPay/src/main/resources/TransactionConfirmation.sql";
> @ "path-to-local-repo/EZPay/src/main/resources/PaymentReminderApp.sql;
> commit;
```

The first command will set up the tables for `users`, `transaction`, and `notification` tables, while the second command will establish the `payment_reminder` table. The final command will commit the changes.

#### 3.2. Launching the backend
To get the backend up and running, open the project `EZPay` in Spring Tool Suite. Ensure that all the dependencies from `pom.xml` are resolved successfully. Run the project as "Spring Boot App".

__Note:__ Please ensure to change the DB URL, username, and password in the `application.properties` file.

#### 4. User Interface
We developed the frontend in React JS with a blend of React Bootstrap and custom CSS to style the components. The complete working code for UI is located in the `Sprint-3-UI` branch of this repository. To clone the same locally, please execute the following git command:

```shell
git clone -b Sprint-3-UI git@github.com:agulati02/EZPay-Notifications.git
```

#### 4.1. Launching the UI
To launch the UI, move to the directory containing the local repo:

```shell
cd path-to-local-repo/ezpay_notifications
```

Now, start the UI by calling the Node package manager:

```shell
npm start
```

<hr>

__Created By__ 
- Doneela Das
- Geethapriya Thandavamurthi
- Akhil Kholia
- Anurag Gulati
- Jai Singh
