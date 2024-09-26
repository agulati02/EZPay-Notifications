/* Author: Doneela Das
   Date: 20.08.2024
*/

-- Beginning a block to drop the existing table if it exists
begin
    execute immediate 'DROP TABLE payment_reminder';
exception
    when others then null; -- Suppressing errors if the table does not exist
end;
/

-- Create the payment_reminder table with the following columns:
-- reminder_id: Unique identifier for the payment reminder
-- user_id: Foreign key referencing the user associated with this reminder
-- amount: Amount due for the reminder
-- due_date: Due date for the payment
-- status: Status of the reminder (e.g., Pending, Completed)
CREATE TABLE payment_reminder (
    reminder_id VARCHAR2(50) PRIMARY KEY,
    user_id VARCHAR2(50) NOT NULL,
    amount NUMBER(10, 2) NOT NULL,
    due_date DATE NOT NULL,
    status VARCHAR2(20) NOT NULL,
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users(id)
);


INSERT INTO payment_reminder (reminder_id, user_id, amount, due_date, status) VALUES ('R01U01', 'U01', 150.75, TO_DATE('2024-09-17', 'YYYY-MM-DD'), 'Pending');
INSERT INTO payment_reminder (reminder_id, user_id, amount, due_date, status) VALUES ('R02U01', 'U01', 200.00, TO_DATE('2024-09-18', 'YYYY-MM-DD'), 'Pending');
INSERT INTO payment_reminder (reminder_id, user_id, amount, due_date, status) VALUES ('R03U01', 'U01', 320.50, TO_DATE('2024-09-19', 'YYYY-MM-DD'), 'Completed');
INSERT INTO payment_reminder (reminder_id, user_id, amount, due_date, status) VALUES ('R04U01', 'U01', 450.00, TO_DATE('2024-09-20', 'YYYY-MM-DD'), 'Pending');
INSERT INTO payment_reminder (reminder_id, user_id, amount, due_date, status) VALUES ('R01U02', 'U02', 75.20, TO_DATE('2024-09-22', 'YYYY-MM-DD'), 'Pending');
INSERT INTO payment_reminder (reminder_id, user_id, amount, due_date, status) VALUES ('R02U02', 'U02', 500.00, TO_DATE('2024-09-30', 'YYYY-MM-DD'), 'Pending');
INSERT INTO payment_reminder (reminder_id, user_id, amount, due_date, status) VALUES ('R03U02', 'U02', 620.75, TO_DATE('2024-09-25', 'YYYY-MM-DD'), 'Completed');
INSERT INTO payment_reminder (reminder_id, user_id, amount, due_date, status) VALUES ('R04U02', 'U02', 700.00, TO_DATE('2024-09-26', 'YYYY-MM-DD'), 'Pending');
INSERT INTO payment_reminder (reminder_id, user_id, amount, due_date, status) VALUES ('R01U03', 'U03', 850.30, TO_DATE('2024-09-28', 'YYYY-MM-DD'), 'Pending');
INSERT INTO payment_reminder (reminder_id, user_id, amount, due_date, status) VALUES ('R02U03', 'U03', 950.00, TO_DATE('2024-09-27', 'YYYY-MM-DD'), 'Completed');
INSERT INTO payment_reminder (reminder_id, user_id, amount, due_date, status) VALUES ('R03U03', 'U03', 150.00, TO_DATE('2024-09-26', 'YYYY-MM-DD'), 'Pending');
INSERT INTO payment_reminder (reminder_id, user_id, amount, due_date, status) VALUES ('R04U03', 'U03', 275.50, TO_DATE('2024-10-01', 'YYYY-MM-DD'), 'Pending');
INSERT INTO payment_reminder (reminder_id, user_id, amount, due_date, status) VALUES ('R01U04', 'U04', 360.00, TO_DATE('2024-09-25', 'YYYY-MM-DD'), 'Pending');
INSERT INTO payment_reminder (reminder_id, user_id, amount, due_date, status) VALUES ('R02U04', 'U04', 450.25, TO_DATE('2024-09-29', 'YYYY-MM-DD'), 'Completed');
INSERT INTO payment_reminder (reminder_id, user_id, amount, due_date, status) VALUES ('R03U04', 'U04', 525.75, TO_DATE('2024-09-26', 'YYYY-MM-DD'), 'Pending');
INSERT INTO payment_reminder (reminder_id, user_id, amount, due_date, status) VALUES ('R04U04', 'U04', 600.00, TO_DATE('2024-09-26', 'YYYY-MM-DD'), 'Pending');
INSERT INTO payment_reminder (reminder_id, user_id, amount, due_date, status) VALUES ('R01U05', 'U05', 750.20, TO_DATE('2024-09-25', 'YYYY-MM-DD'), 'Completed');
INSERT INTO payment_reminder (reminder_id, user_id, amount, due_date, status) VALUES ('R02U05', 'U05', 850.00, TO_DATE('2024-10-10', 'YYYY-MM-DD'), 'Pending');
INSERT INTO payment_reminder (reminder_id, user_id, amount, due_date, status) VALUES ('R03U05', 'U05', 925.75, TO_DATE('2024-10-05', 'YYYY-MM-DD'), 'Pending');
INSERT INTO payment_reminder (reminder_id, user_id, amount, due_date, status) VALUES ('R04U05', 'U05', 1000.00, TO_DATE('2024-10-01', 'YYYY-MM-DD'), 'Completed');