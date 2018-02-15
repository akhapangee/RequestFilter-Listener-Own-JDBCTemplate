create database enroll_system;
use enroll_system;

create table courses(id int primary key auto_increment, name varchar(100), code varchar(3), fees int,
added_date timestamp default CURRENT_TIMESTAMP, status tinyint(1));

create table enquiry_status(id int primary key auto_increment, name varchar(20), color varchar(20),
added_date timestamp default CURRENT_TIMESTAMP, status tinyint(1));

create table enquiries(id int primary key auto_increment, first_name varchar(100), last_name varchar(100), 
email varchar(100), contact_no varchar(100),course_id int, enquiry_date timestamp default CURRENT_TIMESTAMP, 
status int);

alter table enquiries add foreign key(course_id) references courses(id);

alter table enquiries add foreign key(status) references enquiry_status(id);


