/*Author: Doneela Das
Date: 20.08.2024
*/

BEGIN
    EXECUTE IMMEDIATE 'DROP TABLE IF EXISTS PaymentReminder';
END;
/

CREATE TABLE PaymentReminder (
    reminderId VARCHAR(50) PRIMARY KEY,
    userId VARCHAR(50) NOT NULL,
    amount DECIMAL(10, 2) NOT NULL,
    dueDate DATE NOT NULL,
    status VARCHAR(20) NOT NULL
);

INSERT INTO PaymentReminder (reminderId, userId, amount, dueDate, status) VALUES ('R01U01', 'U01', 150.75, '2024-08-15', 'Pending');
INSERT INTO PaymentReminder (reminderId, userId, amount, dueDate, status) VALUES ('R02U01', 'U01', 200.00, '2024-08-21', 'Pending');
INSERT INTO PaymentReminder (reminderId, userId, amount, dueDate, status) VALUES ('R03U01', 'U01', 320.50, '2024-08-25', 'Completed');
INSERT INTO PaymentReminder (reminderId, userId, amount, dueDate, status) VALUES ('R04U01', 'U01', 450.00, '2024-08-30', 'Pending');

INSERT INTO PaymentReminder (reminderId, userId, amount, dueDate, status) VALUES ('R01U02', 'U02', 75.20, '2024-08-22', 'Pending');
INSERT INTO PaymentReminder (reminderId, userId, amount, dueDate, status) VALUES ('R02U02', 'U02', 500.00, '2024-09-10', 'Pending');
INSERT INTO PaymentReminder (reminderId, userId, amount, dueDate, status) VALUES ('R03U02', 'U02', 620.75, '2024-09-15', 'Completed');
INSERT INTO PaymentReminder (reminderId, userId, amount, dueDate, status) VALUES ('R04U02', 'U02', 700.00, '2024-09-20', 'Pending');

INSERT INTO PaymentReminder (reminderId, userId, amount, dueDate, status) VALUES ('R01U03', 'U03', 850.30, '2024-08-13', 'Pending');
INSERT INTO PaymentReminder (reminderId, userId, amount, dueDate, status) VALUES ('R02U03', 'U03', 950.00, '2024-08-19', 'Completed');
INSERT INTO PaymentReminder (reminderId, userId, amount, dueDate, status) VALUES ('R03U03', 'U03', 150.00, '2024-08-21', 'Pending');
INSERT INTO PaymentReminder (reminderId, userId, amount, dueDate, status) VALUES ('R04U03', 'U03', 275.50, '2024-10-10', 'Pending');

INSERT INTO PaymentReminder (reminderId, userId, amount, dueDate, status) VALUES ('R01U04', 'U04', 360.00, '2024-08-11', 'Pending');
INSERT INTO PaymentReminder (reminderId, userId, amount, dueDate, status) VALUES ('R02U04', 'U04', 450.25, '2024-08-21', 'Completed');
INSERT INTO PaymentReminder (reminderId, userId, amount, dueDate, status) VALUES ('R03U04', 'U04', 525.75, '2024-08-21', 'Pending');
INSERT INTO PaymentReminder (reminderId, userId, amount, dueDate, status) VALUES ('R04U04', 'U04', 600.00, '2024-08-23', 'Pending');

INSERT INTO PaymentReminder (reminderId, userId, amount, dueDate, status) VALUES ('R01U05', 'U05', 750.20, '2024-11-05', 'Completed');
INSERT INTO PaymentReminder (reminderId, userId, amount, dueDate, status) VALUES ('R02U05', 'U05', 850.00, '2024-11-10', 'Pending');
INSERT INTO PaymentReminder (reminderId, userId, amount, dueDate, status) VALUES ('R03U05', 'U05', 925.75, '2024-11-15', 'Pending');
INSERT INTO PaymentReminder (reminderId, userId, amount, dueDate, status) VALUES ('R04U05', 'U05', 1000.00, '2024-11-20', 'Completed');
