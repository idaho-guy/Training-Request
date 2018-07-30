drop table training_request if exists;
drop sequence if exists hibernate_sequence;
create sequence hibernate_sequence start with 1 increment by 1;
create table training_request (id bigint not null, approved_by varchar(255), approved_date timestamp, cost decimal(19,2) not null, description varchar(255) not null, employee_id bigint not null, location varchar(255) not null, requested_by_first_name varchar(255) not null, requested_by_last_name varchar(255) not null, primary key (id));
