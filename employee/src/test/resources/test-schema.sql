drop table employee if exists;
drop sequence if exists hibernate_sequence;
create sequence hibernate_sequence start with 1 increment by 1;
create table employee (id bigint not null, email varchar(255), employee_number varchar(255), first_name varchar(255), image_url varchar(255), last_name varchar(255), office varchar(255), title varchar(255), primary key (id));
