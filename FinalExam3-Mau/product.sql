create database  if not exists medicalReCord ;
create table medicalReCord (
	id int primary key auto_increment,
    name varchar(50),
    price float,
    description varchar(200),
    patient int,
    foreign key (patient) references patient(id)
);
insert into medicalReCord(id, name, price, description,patient ) values
 (1, "Macbook", 200, "Macbook air 2023", 1),
 (2,"Iphone", 900, "Điện thoại", 1),
 (3,"Samsum S24", 800, "Điện thoại", 2),
 (4,"Xiaomi S20", 300, "Điện thoại", 3),
 (5,"Apple Watch", 800, "Đồng hồ", 1),
 (6,"LG Gram", 1300, "Laptop", 4);

 create table patient (
	id int primary key auto_increment,
    name varchar(50)  
);

insert into patient(id, name) values
 (1, "Apple"),
 (2, "Samsung"),
 (3, "Xiaomi"),
 (4, "Nokia"),
 (5,"LG");
 
SELECT medicalReCord.id, medicalReCord.name, price,description, patient.name AS patient
FROM medicalReCord
JOIN patient ON patient.id = medicalReCord.patient
WHERE medicalReCord.name like "%MA%" ;
select id from medicalReCord