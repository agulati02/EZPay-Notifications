begin
   execute immediate 'drop table transaction_confirmation';
exception
   when others then null;
end;
/

CREATE TABLE transaction_confirmation (
    user_id INT NOT NULL,
    has_completed NUMBER(1), 
    enabled_notification NUMBER(1),  
    conf_message VARCHAR(255),
    transaction_id INT,
    has_received NUMBER(1),  
    PRIMARY KEY (transaction_id)
);


INSERT INTO transaction_confirmation (user_id, has_completed, enabled_notification, conf_message, transaction_id, has_received) VALUES (1, 0, 0, 'Transaction Incompleted', 2, 0);
INSERT INTO transaction_confirmation (user_id, has_completed, enabled_notification, conf_message, transaction_id, has_received) VALUES (2, 1, 1, 'Transaction Completed', 4, 1);
INSERT INTO transaction_confirmation (user_id, has_completed, enabled_notification, conf_message, transaction_id, has_received) VALUES (3, 0, 1,'Transaction Incompleted',7,1);
INSERT INTO transaction_confirmation (user_id, has_completed, enabled_notification, conf_message, transaction_id, has_received) VALUES (1, 1, 0, 'Transaction Completed', 5, 0);
INSERT INTO transaction_confirmation (user_id, has_completed, enabled_notification, conf_message, transaction_id, has_received) VALUES (1, 1, 0, 'Transaction Completed', 15, 1);

