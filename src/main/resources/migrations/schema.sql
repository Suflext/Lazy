--liquibase formatted sql


--changeset db:schema-init
CREATE TABLE DEPARTMENT (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR (40)
);

CREATE TABLE JOB_POSITION (
    ID BIGSERIAL PRIMARY KEY,
    WEEK_HOURS INT,
    START_TIME TIME,
    SALARY INT
);

CREATE TABLE EMPLOYEE (
    ID BIGSERIAL PRIMARY KEY,
    ROLE VARCHAR(40) NOT NULL,
    LOGIN VARCHAR(40) NOT NULL,
    PASSWORD VARCHAR(40) NOT NULL,
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
    DAILY DATE,
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
INSERT INTO JOB_POSITION (WEEK_HOURS, START_TIME, SALARY) VALUES (40, NULL, 5000);
INSERT INTO JOB_POSITION (WEEK_HOURS, START_TIME, SALARY) VALUES (35, NULL, 2000);
INSERT INTO DEPARTMENT (NAME) VALUES ('FIRST DEPARTMENT');
INSERT INTO EMPLOYEE (ROLE, LOGIN, PASSWORD, FIRST_NAME, LAST_NAME, DEPARTMENT, JOB_POSITION) VALUES ('USER', 'user1', 'us', 'Piter', 'Yann', 1, 1);
INSERT INTO EMPLOYEE (ROLE, LOGIN, PASSWORD, FIRST_NAME, LAST_NAME, DEPARTMENT, JOB_POSITION) VALUES ('ADMIN', 'admin', 'admin', 'Joe', 'Yambl', 1, 1);
INSERT INTO EMPLOYEE (ROLE, LOGIN, PASSWORD, FIRST_NAME, LAST_NAME, DEPARTMENT, JOB_POSITION) VALUES ('USER', 'user2', 'us', 'Bill', 'Jel', 1, 1);
INSERT INTO EMPLOYEE (ROLE, LOGIN, PASSWORD, FIRST_NAME, LAST_NAME, DEPARTMENT, JOB_POSITION) VALUES ('USER', 'user3', 'us', 'John', 'Kit', 1, 1);
INSERT INTO WORK_LOG (DAILY, DURATION, EMPLOYEE, START_TIME, END_TIME) VALUES ('2019-10-24', 5000, 1, '10:35:01', '11:58:21');
INSERT INTO WORK_LOG (DAILY, DURATION, EMPLOYEE, START_TIME, END_TIME) VALUES ('2019-10-20', 4990, 1, '10:34:51', '11:58:21');
INSERT INTO WORK_LOG (DAILY, DURATION, EMPLOYEE, START_TIME, END_TIME) VALUES ('2019-10-28', 12000, 2, '10:30:30', '13:50:30');
INSERT INTO WORK_LOG (DAILY, DURATION, EMPLOYEE, START_TIME, END_TIME) VALUES ('2019-10-28', 1000, 1, '08:20:45', '08:37:25');

INSERT INTO WORK_LOG (DAILY, DURATION, EMPLOYEE, START_TIME, END_TIME) VALUES ('2019-10-28', 1000, 4, '09:20:45', '09:38:25');
INSERT INTO WORK_LOG (DAILY, DURATION, EMPLOYEE, START_TIME, END_TIME) VALUES ('2019-12-20', 1000, 1, '09:20:45', '09:38:25');
INSERT INTO WORK_LOG (DAILY, DURATION, EMPLOYEE, START_TIME, END_TIME) VALUES ('2020-01-03', 1060, 3, '09:20:45', '09:39:25');
INSERT INTO WORK_LOG (DAILY, DURATION, EMPLOYEE, START_TIME, END_TIME) VALUES ('2020-01-04', 1050, 2, '09:20:25', '09:38:55');

INSERT INTO WORK_LOG (DAILY, DURATION, EMPLOYEE, START_TIME, END_TIME) VALUES ('2020-01-13', 1050, 2, '09:00:25', '09:18:55');
INSERT INTO WORK_LOG (DAILY, DURATION, EMPLOYEE, START_TIME, END_TIME) VALUES ('2020-01-13', 1050, 2, '18:40:25', '18:58:55');
INSERT INTO WORK_LOG (DAILY, DURATION, EMPLOYEE, START_TIME, END_TIME) VALUES ('2020-01-14', 1050, 2, '09:30:25', '09:48:55');
INSERT INTO WORK_LOG (DAILY, DURATION, EMPLOYEE, START_TIME, END_TIME) VALUES ('2020-01-14', 1050, 2, '17:30:25', '17:48:55');

INSERT INTO WORK_LOG (DAILY, DURATION, EMPLOYEE, START_TIME, END_TIME) VALUES ('2020-01-13', 1050, 1, '09:00:25', '09:18:55');
INSERT INTO WORK_LOG (DAILY, DURATION, EMPLOYEE, START_TIME, END_TIME) VALUES ('2020-01-13', 1050, 1, '18:40:25', '18:58:55');
INSERT INTO WORK_LOG (DAILY, DURATION, EMPLOYEE, START_TIME, END_TIME) VALUES ('2020-01-14', 1050, 1, '09:30:25', '09:48:55');
INSERT INTO WORK_LOG (DAILY, DURATION, EMPLOYEE, START_TIME, END_TIME) VALUES ('2020-01-14', 1050, 1, '17:30:25', '17:48:55');

INSERT INTO WORK_LOG (DAILY, DURATION, EMPLOYEE, START_TIME, END_TIME) VALUES ('2020-01-14', 1, 1, '00:00:00', '23:59:59');
INSERT INTO WORK_LOG (DAILY, DURATION, EMPLOYEE, START_TIME, END_TIME) VALUES ('2020-01-14', 1, 1, '00:00:00', '23:59:59');
INSERT INTO WORK_LOG (DAILY, DURATION, EMPLOYEE, START_TIME, END_TIME) VALUES ('2020-01-14', 1, 1, '00:00:00', '23:59:59');
INSERT INTO WORK_LOG (DAILY, DURATION, EMPLOYEE, START_TIME, END_TIME) VALUES ('2020-01-14', 1, 1, '00:00:00', '23:59:59');

