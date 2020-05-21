--liquibase formatted sql


--changeset db:schema-init
CREATE TABLE DEPARTMENT (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR (40)
);

CREATE TABLE JOB_POSITION (
    ID BIGSERIAL PRIMARY KEY,
    NAME VARCHAR(100),
    WEEK_HOURS INT,
    START_TIME TIME,
    SALARY INT
);

CREATE TABLE EMPLOYEE (
    ID BIGSERIAL PRIMARY KEY,
    ROLE VARCHAR(40) NOT NULL,
    LOGIN VARCHAR(40) NOT NULL,
    PASSWORD VARCHAR(100) NOT NULL,
    FIRST_NAME VARCHAR(40),
    LAST_NAME VARCHAR(40),
    DEPARTMENT BIGINT,
    JOB_POSITION BIGINT,
    FOREIGN KEY (JOB_POSITION) REFERENCES JOB_POSITION (ID),
    FOREIGN KEY (DEPARTMENT) REFERENCES DEPARTMENT (ID)
);

CREATE TABLE WORK_LOG (
    ID BIGSERIAL PRIMARY KEY,
    EMPLOYEE BIGINT,
    DAY DATE,
    START_TIME TIME,
    END_TIME TIME,
    DURATION BIGINT,
    FOREIGN KEY (EMPLOYEE) REFERENCES EMPLOYEE(ID)
);

CREATE TABLE WORK_LOG_REPORT (
    ID BIGSERIAL PRIMARY KEY,
    EMPLOYEE BIGINT,
    START_DATE DATE,
    DURATION BIGINT,
    TYPE VARCHAR (40),
    FOREIGN KEY (EMPLOYEE) REFERENCES EMPLOYEE (ID)
);

CREATE TABLE DEPARTMENT_BONUS (
    ID BIGSERIAL PRIMARY KEY,
    DEPARTMENT BIGINT,
    DATES DATE,
    PAYOUT INT,
    FOREIGN KEY (DEPARTMENT) REFERENCES DEPARTMENT (ID)
);

CREATE TABLE SYSTEM_PROPERTY (
    ID BIGSERIAL PRIMARY KEY,
    KEY VARCHAR (40),
    VALUE VARCHAR (40),
    DESCRIPTION VARCHAR (40)
);

--changeset db:schema-add
INSERT INTO JOB_POSITION (NAME, WEEK_HOURS, START_TIME, SALARY) VALUES ('Manager', 40, '08:00:00', 50000);
INSERT INTO JOB_POSITION (NAME, WEEK_HOURS, START_TIME, SALARY) VALUES ('Developer', 35, NULL, 20000);
INSERT INTO DEPARTMENT (NAME) VALUES ('FIRST DEPARTMENT');
INSERT INTO DEPARTMENT (NAME) VALUES ('SECOND DEPARTMENT');
INSERT INTO SYSTEM_PROPERTY (KEY, VALUE) VALUES ('penalty', '2');
INSERT INTO SYSTEM_PROPERTY (KEY, VALUE) VALUES ('bonus', '1');
INSERT INTO EMPLOYEE (ROLE, LOGIN, PASSWORD, FIRST_NAME, LAST_NAME, DEPARTMENT, JOB_POSITION) VALUES ('USER', 'user1', '$2a$10$tBeMSLeQGQRW8pCOy09MC.UoC.zbM.JDetgL1YRFRJSbHeskSp0HK', 'Piter', 'Yann', 1, 1);
INSERT INTO EMPLOYEE (ROLE, LOGIN, PASSWORD, FIRST_NAME, LAST_NAME, DEPARTMENT, JOB_POSITION) VALUES ('ADMIN', 'admin', '$2a$10$8sagW52s4VpMunTut69NYOCWv92miiKHvjLiBgrMiNk6uEIoYemmu', 'Joe', 'Yambl', 1, 1);
INSERT INTO EMPLOYEE (ROLE, LOGIN, PASSWORD, FIRST_NAME, LAST_NAME, DEPARTMENT, JOB_POSITION) VALUES ('USER', 'user2', '$2a$10$DZCF6RhXpEWMJsjh.c70e.IriWXT4YbHakCAoCTltfSjR7YHMjHkW', 'Bill', 'Jel', 1, 1);
INSERT INTO EMPLOYEE (ROLE, LOGIN, PASSWORD, FIRST_NAME, LAST_NAME, DEPARTMENT, JOB_POSITION) VALUES ('USER', 'user3', '$2a$10$d/gU3PF.dhhgSRNUNZfYuOdmYS0WD/ya0rdnKWiiKJeU9rhc/9f26', 'John', 'Kit', 1, 1);

INSERT INTO WORK_LOG (DAY, DURATION, EMPLOYEE, START_TIME, END_TIME) VALUES (CURRENT_DATE - (dayofweek(CURRENT_DATE - 1 day) - 1) DAY, 5000, 1, '10:35:01', '17:58:21');
INSERT INTO WORK_LOG (DAY, DURATION, EMPLOYEE, START_TIME, END_TIME) VALUES (CURRENT_DATE - (dayofweek(CURRENT_DATE - 1 day) - 2) DAY, 4990, 1, '09:04:51', '16:28:21');
INSERT INTO WORK_LOG (DAY, DURATION, EMPLOYEE, START_TIME, END_TIME) VALUES (CURRENT_DATE - (dayofweek(CURRENT_DATE - 1 day) - 3) DAY, 4990, 1, '08:14:51', '20:12:21');
INSERT INTO WORK_LOG (DAY, DURATION, EMPLOYEE, START_TIME, END_TIME) VALUES (CURRENT_DATE - (dayofweek(CURRENT_DATE - 1 day) - 4) DAY, 4990, 1, '10:34:51', '16:23:21');
INSERT INTO WORK_LOG (DAY, DURATION, EMPLOYEE, START_TIME, END_TIME) VALUES (CURRENT_DATE - (dayofweek(CURRENT_DATE - 1 day) - 5) DAY, 4990, 1, '08:34:51', '17:18:21');
INSERT INTO WORK_LOG (DAY, DURATION, EMPLOYEE, START_TIME, END_TIME) VALUES (CURRENT_DATE - (dayofweek(CURRENT_DATE - 1 day) - 6) DAY, 4990, 1, '10:34:51', '11:58:21');

INSERT INTO WORK_LOG (DAY, DURATION, EMPLOYEE, START_TIME, END_TIME) VALUES (CURRENT_DATE - (dayofweek(CURRENT_DATE - 1 day) - 1) DAY, 5000, 2, '09:15:01', '19:48:21');
INSERT INTO WORK_LOG (DAY, DURATION, EMPLOYEE, START_TIME, END_TIME) VALUES (CURRENT_DATE - (dayofweek(CURRENT_DATE - 1 day) - 2) DAY, 4990, 2, '10:34:51', '11:58:21');
INSERT INTO WORK_LOG (DAY, DURATION, EMPLOYEE, START_TIME, END_TIME) VALUES (CURRENT_DATE - (dayofweek(CURRENT_DATE - 1 day) - 3) DAY, 4990, 2, '10:34:51', '11:58:21');
INSERT INTO WORK_LOG (DAY, DURATION, EMPLOYEE, START_TIME, END_TIME) VALUES (CURRENT_DATE - (dayofweek(CURRENT_DATE - 1 day) - 4) DAY, 4990, 2, '10:34:51', '11:58:21');
INSERT INTO WORK_LOG (DAY, DURATION, EMPLOYEE, START_TIME, END_TIME) VALUES (CURRENT_DATE - (dayofweek(CURRENT_DATE - 1 day) - 5) DAY, 4990, 2, '10:34:51', '11:58:21');
INSERT INTO WORK_LOG (DAY, DURATION, EMPLOYEE, START_TIME, END_TIME) VALUES (CURRENT_DATE - (dayofweek(CURRENT_DATE - 1 day) - 6) DAY, 4990, 2, '10:34:51', '11:58:21');

INSERT INTO WORK_LOG (DAY, DURATION, EMPLOYEE, START_TIME, END_TIME) VALUES (CURRENT_DATE - (dayofweek(CURRENT_DATE - 1 day) - 1) DAY, 5000, 3, '10:35:01', '19:58:21');
INSERT INTO WORK_LOG (DAY, DURATION, EMPLOYEE, START_TIME, END_TIME) VALUES (CURRENT_DATE - (dayofweek(CURRENT_DATE - 1 day) - 2) DAY, 4990, 3, '10:34:51', '11:58:21');
INSERT INTO WORK_LOG (DAY, DURATION, EMPLOYEE, START_TIME, END_TIME) VALUES (CURRENT_DATE - (dayofweek(CURRENT_DATE - 1 day) - 3) DAY, 4990, 3, '10:34:51', '11:58:21');
INSERT INTO WORK_LOG (DAY, DURATION, EMPLOYEE, START_TIME, END_TIME) VALUES (CURRENT_DATE - (dayofweek(CURRENT_DATE - 1 day) - 4) DAY, 4990, 3, '10:34:51', '11:58:21');
INSERT INTO WORK_LOG (DAY, DURATION, EMPLOYEE, START_TIME, END_TIME) VALUES (CURRENT_DATE - (dayofweek(CURRENT_DATE - 1 day) - 5) DAY, 4990, 3, '10:34:51', '11:58:21');
INSERT INTO WORK_LOG (DAY, DURATION, EMPLOYEE, START_TIME, END_TIME) VALUES (CURRENT_DATE - (dayofweek(CURRENT_DATE - 1 day) - 6) DAY, 4990, 3, '10:34:51', '11:58:21');

INSERT INTO WORK_LOG (DAY, DURATION, EMPLOYEE, START_TIME, END_TIME) VALUES (CURRENT_DATE - (dayofweek(CURRENT_DATE - 1 day) - 1) DAY, 5000, 4, '10:35:01', '11:58:21');
INSERT INTO WORK_LOG (DAY, DURATION, EMPLOYEE, START_TIME, END_TIME) VALUES (CURRENT_DATE - (dayofweek(CURRENT_DATE - 1 day) - 2) DAY, 4990, 4, '10:34:51', '11:58:21');
INSERT INTO WORK_LOG (DAY, DURATION, EMPLOYEE, START_TIME, END_TIME) VALUES (CURRENT_DATE - (dayofweek(CURRENT_DATE - 1 day) - 3) DAY, 4990, 4, '10:34:51', '11:58:21');
INSERT INTO WORK_LOG (DAY, DURATION, EMPLOYEE, START_TIME, END_TIME) VALUES (CURRENT_DATE - (dayofweek(CURRENT_DATE - 1 day) - 4) DAY, 4990, 4, '10:34:51', '11:58:21');
INSERT INTO WORK_LOG (DAY, DURATION, EMPLOYEE, START_TIME, END_TIME) VALUES (CURRENT_DATE - (dayofweek(CURRENT_DATE - 1 day) - 5) DAY, 4990, 4, '10:34:51', '11:58:21');
INSERT INTO WORK_LOG (DAY, DURATION, EMPLOYEE, START_TIME, END_TIME) VALUES (CURRENT_DATE - (dayofweek(CURRENT_DATE - 1 day) - 6) DAY, 4990, 4, '10:34:51', '11:58:21');
