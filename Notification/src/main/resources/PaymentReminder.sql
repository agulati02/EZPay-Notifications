/*Author: Doneela Das
Date: 20.08.2024
*/


begin
    execute immediate 'DROP TABLE payment_reminder';
exception
    when others then null;
end;
/

CREATE TABLE payment_reminder (
    reminder_id VARCHAR2(50) PRIMARY KEY,
    user_id VARCHAR2(50) NOT NULL,
    amount NUMBER(10, 2) NOT NULL,
    due_date DATE NOT NULL,
    status VARCHAR2(20) NOT NULL
);    

INSERT INTO payment_reminder (reminder_id, user_id, amount, due_date, status) VALUES ('R01U01', 'U01', 150.75, TO_DATE('2024-09-05', 'YYYY-MM-DD'), 'Pending');
INSERT INTO payment_reminder (reminder_id, user_id, amount, due_date, status) VALUES ('R02U01', 'U01', 200.00, TO_DATE('2024-09-11', 'YYYY-MM-DD'), 'Pending');
INSERT INTO payment_reminder (reminder_id, user_id, amount, due_date, status) VALUES ('R03U01', 'U01', 320.50, TO_DATE('2024-09-15', 'YYYY-MM-DD'), 'Completed');
INSERT INTO payment_reminder (reminder_id, user_id, amount, due_date, status) VALUES ('R04U01', 'U01', 450.00, TO_DATE('2024-09-03', 'YYYY-MM-DD'), 'Pending');

INSERT INTO payment_reminder (reminder_id, user_id, amount, due_date, status) VALUES ('R01U02', 'U02', 75.20, TO_DATE('2024-08-22', 'YYYY-MM-DD'), 'Pending');
INSERT INTO payment_reminder (reminder_id, user_id, amount, due_date, status) VALUES ('R02U02', 'U02', 500.00, TO_DATE('2024-09-10', 'YYYY-MM-DD'), 'Pending');
INSERT INTO payment_reminder (reminder_id, user_id, amount, due_date, status) VALUES ('R03U02', 'U02', 620.75, TO_DATE('2024-09-15', 'YYYY-MM-DD'), 'Completed');
INSERT INTO payment_reminder (reminder_id, user_id, amount, due_date, status) VALUES ('R04U02', 'U02', 700.00, TO_DATE('2024-09-20', 'YYYY-MM-DD'), 'Pending');

INSERT INTO payment_reminder (reminder_id, user_id, amount, due_date, status) VALUES ('R01U03', 'U03', 850.30, TO_DATE('2024-08-13', 'YYYY-MM-DD'), 'Pending');
INSERT INTO payment_reminder (reminder_id, user_id, amount, due_date, status) VALUES ('R02U03', 'U03', 950.00, TO_DATE('2024-08-19', 'YYYY-MM-DD'), 'Completed');
INSERT INTO payment_reminder (reminder_id, user_id, amount, due_date, status) VALUES ('R03U03', 'U03', 150.00, TO_DATE('2024-08-21', 'YYYY-MM-DD'), 'Pending');
INSERT INTO payment_reminder (reminder_id, user_id, amount, due_date, status) VALUES ('R04U03', 'U03', 275.50, TO_DATE('2024-10-10', 'YYYY-MM-DD'), 'Pending');

INSERT INTO payment_reminder (reminder_id, user_id, amount, due_date, status) VALUES ('R01U04', 'U04', 360.00, TO_DATE('2024-09-05', 'YYYY-MM-DD'), 'Pending');
INSERT INTO payment_reminder (reminder_id, user_id, amount, due_date, status) VALUES ('R02U04', 'U04', 450.25, TO_DATE('2024-09-11', 'YYYY-MM-DD'), 'Completed');
INSERT INTO payment_reminder (reminder_id, user_id, amount, due_date, status) VALUES ('R03U04', 'U04', 525.75, TO_DATE('2024-09-06', 'YYYY-MM-DD'), 'Pending');
INSERT INTO payment_reminder (reminder_id, user_id, amount, due_date, status) VALUES ('R04U04', 'U04', 600.00, TO_DATE('2024-09-06', 'YYYY-MM-DD'), 'Pending');

INSERT INTO payment_reminder (reminder_id, user_id, amount, due_date, status) VALUES ('R01U05', 'U05', 750.20, TO_DATE('2024-11-05', 'YYYY-MM-DD'), 'Completed');
INSERT INTO payment_reminder (reminder_id, user_id, amount, due_date, status) VALUES ('R02U05', 'U05', 850.00, TO_DATE('2024-11-10', 'YYYY-MM-DD'), 'Pending');
INSERT INTO payment_reminder (reminder_id, user_id, amount, due_date, status) VALUES ('R03U05', 'U05', 925.75, TO_DATE('2024-11-15', 'YYYY-MM-DD'), 'Pending');
INSERT INTO payment_reminder (reminder_id, user_id, amount, due_date, status) VALUES ('R04U05', 'U05', 1000.00, TO_DATE('2024-11-20', 'YYYY-MM-DD'), 'Completed');
