# 创建视图 salesman，该视图只保存 employees 表中所有 job_title 为'Sales Representative'的雇员。
CREATE VIEW salesman AS
SELECT * FROM employees WHERE job_title='Sales Representative';
-- 查看
SELECT*FROM salesman;

#创建基于 salesman 的视图，该视图存储salesman 的联系方式。
CREATE VIEW salesman_contacts AS 
SELECT first_name, last_name, email, phone FROM salesman;
-- 查看
SELECT*FROM salesman_contacts;
