create database PayrollSystem;
use PayrollSystem;
create table PSData(
Employee_ID int primary key auto_increment,
Days_Worked int not null,
Rate int not null,
Fixed_Deductions int not null,
Gross_Pay int not null,
Tax int not null,
Net_Pay int not null
);
select * from PSData;
