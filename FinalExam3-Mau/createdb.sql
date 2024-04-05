create database  if not exists medical ;
create table patient (
patient_id int primary key auto_increment,
name varchar(50)
);
create table medical_record (
id int primary key auto_increment,
record_id varchar(50),
start_date date,
end_date date,
description varchar(200),
patient_id int,
foreign key (patient_id) references patient(patient_id)
);
insert into patient(patient_id, name ) values
(1, "Nguyễn Văn A"),
(2,"Trần Văn B");
insert into medical_record(id, record_id, start_date,end_date, patient_id) values
(1,"BA-001",'2020-01-12','2020-01-15', 1),
(2,"BA-002",'2020-01-12','2020-02-20',2),
(3,"BA-003",'2020-01-12','2020-04-18',1)







