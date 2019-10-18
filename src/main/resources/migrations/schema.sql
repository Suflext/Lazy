--liquibase formatted sql


--changeset db:schema-init
CREATE TABLE Department (
    id IDENTITY PRIMARY KEY,
    name VARCHAR (40)
);

CREATE TABLE Job_Position (
    id IDENTITY PRIMARY KEY,
    month_Hours INT,
    start_Time TIMESTAMP,
    salory INT
);

CREATE TABLE Employee (
    id IDENTITY PRIMARY KEY,
    role VARCHAR (40) NOT NULL,
    login VARCHAR (40) NOT NULL,
    password VARCHAR (40) NOT NULL,
    first_Name VARCHAR (40),
    last_Name VARCHAR (40),
    department SMALLINT,
    job_Position INT,
    FOREIGN KEY (job_Position) REFERENCES Job_Position (id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (department) REFERENCES Department (id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE WorkLog (
    id IDENTITY PRIMARY KEY,
    employee INT,
    start_Time TIMESTAMP,
    end_Time TIMESTAMP,
    duration TIME,
    FOREIGN KEY (userId) REFERENCES Employee (id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE Work_Log_Analytics (
    id IDENTITY PRIMARY KEY,
    employee INT,
    dates DATE,
    duration TIME,
    type VARCHAR (40),
    FOREIGN KEY (userId) REFERENCES Employee (id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE Department_Bonus (
    id IDENTITY PRIMARY KEY,
    department INT,
    dates DATE,
    payout INT,
    FOREIGN KEY (departmentId) REFERENCES Department (id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE System_Properties (
    id IDENTITY PRIMARY KEY,
    KEY VARCHAR (40),
    val VARCHAR (40),
    description VARCHAR (40)
);

--changeset db:schema-add
insert into Job_Position (id, month_Hours, start_Time, salory) values (1, 160, null, 5000);
insert into Job_Position (id, month_Hours, start_Time, salory) values (2, 100, null, 2000);
insert into Department (id, name, department_bonus) values (1, 'firstDepartment', 1);
insert into Employee (role, login, password, first_Name, last_Name, department, job_Position) values ('USER', 'user1', 'us', 'Piter', 'Yann', 1, 1);

