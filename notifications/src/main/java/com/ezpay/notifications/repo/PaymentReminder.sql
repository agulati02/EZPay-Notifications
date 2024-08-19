/*Author: Doneela Das
Date: 20.08.2024
*/


CREATE TABLE PaymentReminder (
    reminderId VARCHAR2(50) PRIMARY KEY,
    userId VARCHAR2(50) NOT NULL,
    amount NUMBER(10, 2) NOT NULL,
    dueDate DATE NOT NULL,
    status VARCHAR2(20) NOT NULL,
    CONSTRAINT fk_user FOREIGN KEY (userId) REFERENCES Users(userId)
);



INSERT INTO PaymentReminder (reminderId, userId, amount, dueDate, status) VALUES ('R001', 'U001', 150.75, TO_DATE('2024-08-15', 'YYYY-MM-DD'), 'Pending');
INSERT INTO PaymentReminder (reminderId, userId, amount, dueDate, status) VALUES ('R002', 'U002', 200.00, TO_DATE('2024-08-20', 'YYYY-MM-DD'), 'Pending');
INSERT INTO PaymentReminder (reminderId, userId, amount, dueDate, status) VALUES ('R003', 'U003', 320.50, TO_DATE('2024-08-25', 'YYYY-MM-DD'), 'Completed');
INSERT INTO PaymentReminder (reminderId, userId, amount, dueDate, status) VALUES ('R004', 'U004', 450.00, TO_DATE('2024-08-30', 'YYYY-MM-DD'), 'Pending');
INSERT INTO PaymentReminder (reminderId, userId, amount, dueDate, status) VALUES ('R005', 'U005', 75.20, TO_DATE('2024-09-05', 'YYYY-MM-DD'), 'Pending');
INSERT INTO PaymentReminder (reminderId, userId, amount, dueDate, status) VALUES ('R006', 'U006', 500.00, TO_DATE('2024-09-10', 'YYYY-MM-DD'), 'Pending');
INSERT INTO PaymentReminder (reminderId, userId, amount, dueDate, status) VALUES ('R007', 'U007', 620.75, TO_DATE('2024-09-15', 'YYYY-MM-DD'), 'Completed');
INSERT INTO PaymentReminder (reminderId, userId, amount, dueDate, status) VALUES ('R008', 'U008', 700.00, TO_DATE('2024-09-20', 'YYYY-MM-DD'), 'Pending');
INSERT INTO PaymentReminder (reminderId, userId, amount, dueDate, status) VALUES ('R009', 'U009', 850.30, TO_DATE('2024-09-25', 'YYYY-MM-DD'), 'Pending');
INSERT INTO PaymentReminder (reminderId, userId, amount, dueDate, status) VALUES ('R010', 'U010', 950.00, TO_DATE('2024-09-30', 'YYYY-MM-DD'), 'Completed');

INSERT INTO PaymentReminder (reminderId, userId, amount, dueDate, status) VALUES ('R011', 'U011', 150.00, TO_DATE('2024-10-05', 'YYYY-MM-DD'), 'Pending');
INSERT INTO PaymentReminder (reminderId, userId, amount, dueDate, status) VALUES ('R012', 'U012', 275.50, TO_DATE('2024-10-10', 'YYYY-MM-DD'), 'Pending');
INSERT INTO PaymentReminder (reminderId, userId, amount, dueDate, status) VALUES ('R013', 'U013', 360.00, TO_DATE('2024-10-15', 'YYYY-MM-DD'), 'Pending');
INSERT INTO PaymentReminder (reminderId, userId, amount, dueDate, status) VALUES ('R014', 'U014', 450.25, TO_DATE('2024-10-20', 'YYYY-MM-DD'), 'Completed');
INSERT INTO PaymentReminder (reminderId, userId, amount, dueDate, status) VALUES ('R015', 'U015', 525.75, TO_DATE('2024-10-25', 'YYYY-MM-DD'), 'Pending');
INSERT INTO PaymentReminder (reminderId, userId, amount, dueDate, status) VALUES ('R016', 'U016', 600.00, TO_DATE('2024-10-30', 'YYYY-MM-DD'), 'Pending');
INSERT INTO PaymentReminder (reminderId, userId, amount, dueDate, status) VALUES ('R017', 'U017', 750.20, TO_DATE('2024-11-05', 'YYYY-MM-DD'), 'Completed');
INSERT INTO PaymentReminder (reminderId, userId, amount, dueDate, status) VALUES ('R018', 'U018', 850.00, TO_DATE('2024-11-10', 'YYYY-MM-DD'), 'Pending');
INSERT INTO PaymentReminder (reminderId, userId, amount, dueDate, status) VALUES ('R019', 'U019', 925.75, TO_DATE('2024-11-15', 'YYYY-MM-DD'), 'Pending');
INSERT INTO PaymentReminder (reminderId, userId, amount, dueDate, status) VALUES ('R020', 'U020', 1000.00, TO_DATE('2024-11-20', 'YYYY-MM-DD'), 'Completed');

