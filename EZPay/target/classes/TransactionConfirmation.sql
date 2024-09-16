begin
    execute immediate 'DROP TABLE notification';
    execute immediate 'DROP TABLE transaction';
    execute immediate 'DROP TABLE users';
    execute immediate 'DROP SEQUENCE notification_seq';
    execute immediate 'DROP SEQUENCE transaction_seq';
exception
    when others then null;
end;
/

create sequence notification_seq start with 1 increment by 1;
create sequence transaction_seq start with 1 increment by 1;

create table notification (
        id number(19,0) not null,
        transaction_id number(19,0),
        user_id varchar2(255 char),
        notification_content varchar2(255 char),
        primary key (id)
    );

create table transaction (
        amount float(53) not null,
        date_of_transaction DATE not null ,
        id number(19,0) not null,
        user_id varchar2(255 char),
        status varchar2(255 char),
        type varchar2(255 char),
        primary key (id)
    );

create table users (
        notifications_enabled number(1,0) not null check (notifications_enabled in (0,1)),
        id varchar2(255 char) not null,
        registration_date timestamp(6),
        email varchar2(255 char),
        password varchar2(255 char),
        username varchar2(255 char),
        primary key (id)
    );



INSERT INTO users (id, notifications_enabled, username, password, email, registration_date) VALUES ('U01', 1, 'akhil', 'lihka', 'sjai48578@gmail.com', TO_DATE('2023-05-08', 'YYYY-MM-DD'));
INSERT INTO users (id, notifications_enabled, username, password, email, registration_date) VALUES ('U02', 1, 'xyz', 'oiu', 'sjai48578@gmail.com', TO_DATE('2013-05-18', 'YYYY-MM-DD'));
INSERT INTO users (id, notifications_enabled, username, password, email, registration_date) VALUES ('U03', 0, 'test', 'tre', 'sjai48578@gmail.com', TO_DATE('2023-09-28', 'YYYY-MM-DD'));
INSERT INTO users (id, notifications_enabled, username, password, email, registration_date) VALUES ('U04', 1, 'mock', 'rrr', 'sjai48578@gmail.com', TO_DATE('2010-02-08', 'YYYY-MM-DD'));
INSERT INTO users (id, notifications_enabled, username, password, email, registration_date) VALUES ('U05', 0, 'try', 'sss', 'sjai48578@gmail.com', TO_DATE('2020-04-01', 'YYYY-MM-DD'));
