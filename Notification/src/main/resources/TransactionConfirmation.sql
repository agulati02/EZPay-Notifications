create sequence notification_seq start with 1 increment by 1;
create sequence transaction_seq start with 1 increment by 1;
create sequence user_seq start with 1 increment by 1;

create table notification (
        id number(19,0) not null,
        transaction_id number(19,0),
        user_id number(19,0),
        notification_content varchar2(255 char),
        primary key (id)
    );

create table transaction (
        amount float(53) not null,
        date_of_transaction timestamp(6),
        id number(19,0) not null,
        user_id number(19,0),
        status varchar2(255 char),
        type varchar2(255 char),
        primary key (id)
    );

create table users (
        notifications_enabled number(1,0) not null check (notifications_enabled in (0,1)),
        id number(19,0) not null,
        registration_date timestamp(6),
        email varchar2(255 char),
        password varchar2(255 char),
        username varchar2(255 char),
        primary key (id)
    );



INSERT INTO users (id, notifications_enabled, username, password, email, registration_date) VALUES (1, 1, 'akhil', 'lihka', 'akhil@ak.in', TO_DATE('2023-05-08', 'YYYY-MM-DD'));
INSERT INTO users (id, notifications_enabled, username, password, email, registration_date) VALUES (2, 1, 'xyz', 'oiu', 'xyz@jjj.jj', TO_DATE('2013-05-18', 'YYYY-MM-DD'));
INSERT INTO users (id, notifications_enabled, username, password, email, registration_date) VALUES (3, 0, 'test', 'tre', 'fffl@ak.in', TO_DATE('2023-09-28', 'YYYY-MM-DD'));
INSERT INTO users (id, notifications_enabled, username, password, email, registration_date) VALUES (4, 1, 'mock', 'rrr', 'wwww@vv.in', TO_DATE('2010-02-08', 'YYYY-MM-DD'));
INSERT INTO users (id, notifications_enabled, username, password, email, registration_date) VALUES (5, 0, 'try', 'sss', 'bbb@ak.in', TO_DATE('2020-04-01', 'YYYY-MM-DD'));
