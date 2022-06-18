CREATE TABLE Emp(
    Eid CHAR(5) NOT NULL,
    Ename VARCHAR2( 10 ),
    WorkID  CHAR( 3 ) ,
    Salary  NUMBER( 8,2 ) ,
    Phone CHAR(11) NOT NULL              
);

CREATE TABLE Work(
    WorkID CHAR(3) NOT NULL,
    LowerSalary  NUMBER( 8,2 ) ,
    UpperSalary  NUMBER( 8,2 )               
);


Insert into  Emp values ('10001', 'Smith', '001', 2000, '13800010001');
Insert into  Emp values ('10001', 'Jonny', '001', 3000,'13600010002');
Insert into  Emp values ('10002', 'Mary', '002', 2500, '13800020002');
Insert into  Work values  ('001', 1000, 5000);
Insert into  Work values ('002', 2000, 8000);

UPDATE Emp SET Eid = '10004' WHERE (Ename='Jonny');
ALTER TABLE Emp ADD CONSTRAINT eid_pk PRIMARY KEY(Eid);

ALTER TABLE Emp RENAME CONSTRAINT eid_pk to pk_eid;
ALTER TABLE Emp ADD CONSTRAINT phoneunique UNIQUE(phone);

Insert into  Emp values ('10003','Amy','002', 3000,'13800020003');

ALTER TABLE Work ADD CONSTRAINT work_pk PRIMARY KEY(WorkID);
ALTER TABLE Emp ADD CONSTRAINT fk_emp_work  FOREIGN KEY(WorkID)
REFERENCES Work(WorkID);
Insert into  Emp values ('10003','Amy', '003', 3000, '13800020003');

ALTER TABLE Emp ADD CONSTRAINT checksalary CHECK (Salary>=1000);
Insert into  Emp values ('10003','Robert','002',500,'13800020003');

ALTER TABLE Work  ADD CONSTRAINT lowerupper CHECK (LowerSalary<=UpperSalary);
Insert into  Work values ('002',4000,3000);

select oid, conname from pg_constraint; -- conname是约束名 --
select pg_get_constraintdef(16856);
\d+ Emp
\d+ Work

ALTER TABLE Emp DROP CONSTRAINT pk_eid;
ALTER TABLE Emp DROP CONSTRAINT phoneunique;
ALTER TABLE Emp DROP CONSTRAINT fk_emp_work;
ALTER TABLE Emp DROP CONSTRAINT checksalary;
ALTER TABLE Work DROP CONSTRAINT lowerupper;
ALTER TABLE Work DROP CONSTRAINT work_pk;
