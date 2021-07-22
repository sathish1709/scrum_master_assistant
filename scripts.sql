# Drop the database if it's lready present
drop database if exists citi_scrum_master;
# creating a Database
create database citi_scrum_master;
# make use of the database
USE citi_scrum_master;

#Creating a table employee with 6 variables having the soeid as primary key
create table employee(
soeid VARCHAR(10),
emp_first_name VARCHAR(40),
emp_last_name VARCHAR(40),
email_id varchar(50),
emp_designation  VARCHAR(30),
scrum_master VARCHAR(10),
PRIMARY KEY (soeid));


#Inserting some values to the variable of table employee
insert into employee values('sm51850','Sathish','Mohan','satiz.it@gmail.com','Project Manager','Yes');
insert into employee values('sm51851','Yash','Jain','jain.yash660@gmail.com','Developer','No');
insert into employee values('sm51852','karishma','kumar','karishma@gmail.com','Developer','No');
insert into employee values('sm51853','andy','robson','andy@gmail.com','Release Manager','No');
insert into employee values('sm51854','kevin','Durant','kevin@gmail.com','release coordinator','No');
insert into employee values('superuser','admin','admin','admin@gmail.com','Administrator','No');
insert into employee values('sm51856','Hari','Jain','satiz.it@gmail.com','Developer','No');
insert into employee values('sm51857','Swati','Mohan','jain.yash660"gmail.com','Project Manager','Yes');
insert into employee values('sm51858','Yash','Jain','satiz.it@gmail.com','Developer','No');
insert into employee values('sm51859','karishma','kumar','karishma@gmail.com','Developer','No');
insert into employee values('sm51861','andy','robson','sathish.mohan"ucdconnect.ie','Release Manager','No');
insert into employee values('sm51862','kevin','Durant','kevin@gmail.com','release coordinator','No');
insert into employee values('sm51863','Yash','Jain','satiz.it@gmail.com','Developer','No');


#team_info table with 3 variables with a relation with the employee table via soeid variable
create table team_info(
soeid VARCHAR(10),
manager_soeid VARCHAR(10),
secondary_scrum_master VARCHAR(10),
FOREIGN KEY (soeid) REFERENCES employee(soeid),
FOREIGN KEY (manager_soeid) REFERENCES employee(soeid));

#Inserting the values to the table team_info
insert into team_info values('sm51851','sm51850','No');
insert into team_info values('sm51852','sm51850','Yes');
insert into team_info values('sm51854','sm51853','Yes');
insert into team_info values('sm51856','sm51850','Yes');

#employee_auth table to authorize the user 
create table employee_auth(
soeid VARCHAR(10),
password_info varchar(40),
FOREIGN KEY (soeid) REFERENCES employee(soeid));

#inserting the values into the employee_auth table
insert into employee_auth values('sm51850','Welcome@123');
insert into employee_auth values('sm51851','Welcome@121');
insert into employee_auth values('sm51852','Welcome@122');
insert into employee_auth values('sm51853','Welcome@123');
insert into employee_auth values('sm51854','Welcome@124');
insert into employee_auth values('superuser','password');

#creating the table project to hold the details of the project
create table project(
p_name VARCHAR(100),
p_id INTEGER auto_increment,
dept_name enum('MSST','TTS'),
p_owner_soeid VARCHAR(10),
PRIMARY KEY (p_id),
FOREIGN KEY (p_owner_soeid) REFERENCES employee(soeid)
);

ALTER TABLE project AUTO_INCREMENT = 1000;

#Inserting some values into the table project
insert into project values('Citi Direct Platform',1000, 'TTS','sm51850');
insert into project values('Citi Direct BSI',1001, 'MSST' ,'sm51850');
insert into project values('Citi Direct Payments',1002,'TTS','sm51850');

#Creating the table project_release_info to hold the information of the releases of the projects
create table project_release_info(
p_name VARCHAR(100),
p_id INT,
release_no VARCHAR(10) ,
release_status VARCHAR(40),
FOREIGN KEY (p_id) REFERENCES project(p_id));

#Inserting the values into the table project_release_info
insert into project_release_info values('Citi Direct Platform',1000, 'CD100','Development');
insert into project_release_info values('Citi Direct Payments',1001, 'CD101','Released');
insert into project_release_info values('Citi Direct BSI',1002, 'CD102','Development');
insert into project_release_info values('Citi Direct BSI',1002, 'CD102','Released');
insert into project_release_info values('Citi Direct BSI',1002, 'CD102','Released');
insert into project_release_info values('Citi Direct Payments',1002,'CD102', 'Development');

#Creating the table jira_ticket_info to hold the information about the task with jira_no as the unique key
create table jira_ticket_info(
jira_no INTEGER(25) auto_increment,
p_id INT ,
task_name VARCHAR(200),
task_owner VARCHAR(10),
task_status VARCHAR(100),
uat_date  VARCHAR(100),
update_space VARCHAR(200),
primary key(jira_no),
FOREIGN KEY (p_id) REFERENCES project(p_id),
FOREIGN KEY (task_owner) REFERENCES employee(soeid));

#creating the table user_login_audit to validate the first time login
create table user_login_audit(
soeid VARCHAR(10),
login_time timestamp);
