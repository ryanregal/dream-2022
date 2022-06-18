
-- 题目6

CREATE TABLE DEPT
(Deptno NUMBER(2) PRIMARY KEY .
Deptname VARCHAR( 10),
Manager VARCHAR(IO),
PhoneNumber Char( 12)
);

CREATE TABLE EMP
(Empno NUMBER(4)PRIMARY KEY,
Ename VARCHAR( 10),
Age NUMBER(2),
Job VARCHAR(9),
Sal NUMBER(7,2),
Deptno NUMBER(2),
CONSTRAINT Cl CHECK( Age< = 60),
CONSTRAINT FK.DEPTNO FOREIGN KEY( Deptno) REFERENCES DEPT( Deptno));

-- 题目8

CREATE TABLE Male
(SerialNumber Smalllnt PRIMARY KEY,
Name Char( 8),
Age Smalllnt,
Occupation Char( 20)
);

CREATE TABLE Female
(SerialNumber Smalllnt PRIMARY KEY, Name Char( 8),
Age Smalllnt,
Occupation Char( 20)
);

CREATE ASSERTION Party	/ * 创建断言 PARTY * /
CHECK(( SELECT COUNT( * ) FROM Male)+( SELECT COUNT( * )FROM Female)
< =50);

