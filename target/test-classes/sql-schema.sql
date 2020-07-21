create database if not exists ims_test;
create table if not exists ims_test.customers(id int primary key auto_increment, first_name varchar(40), surname varchar(40), address varchar(100), email varchar(100), mobile varchar(11));
