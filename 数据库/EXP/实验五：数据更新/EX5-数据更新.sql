Insert into regions values('5','Oceania');
Update countries SET region_id=5 where country_name='Austrialia';
Insert into countries values('NO','Norway','1'), ('ES','Spain','1'),
('SE','Sweden','1'), ('PT','Portugal','1'), ('NZ','New Zealand','5');

ALTER TABLE countries ADD CONSTRAINT uni_country_id UNIQUE (country_id);
ALTER TABLE countries ADD CONSTRAINT uni_country_name UNIQUE (country_name);
Create Table  Asia_countries(
    country_name VARCHAR2( 40 ) UNIQUE,
    country_id CHAR( 2 ) UNIQUE ,
    CONSTRAINT asia_countries_country_id_key_fk FOREIGN KEY(country_id) REFERENCES countries(country_id)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    CONSTRAINT asia_countries_country_name_key_fk FOREIGN KEY(country_name) REFERENCES countries(country_name)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

-- 插入子查询
INSERT INTO Asia_countries(country_id,country_name)
SELECT country_id,country_name FROM countries c,regions r 
WHERE r.region_id=c.region_id AND r.region_name='Asia';

-- 创建视图
CREATE VIEW order_total(order_id,total_price) AS
SELECT order_id,quantity*unit_price FROM order_items;
SELECT * FROM order_total;

CREATE VIEW total_price_97(order_id,total_price) AS
SELECT order_id,total_price FROM order_total WHERE order_id=97;
SELECT * FROM total_price_97;

UPDATE order_items SET unit_price=unit_price+4 WHERE PRODUCT_ID=99;
SELECT order_id,total_price FROM order_total WHERE ORDER_ID=97;

-- 使用 delete 命令删除 Asia_countries 表中 country_id 为 IN 的记录
DELETE FROM Asia_countries WHERE country_id='IN';
TRUNCATE TABLE Asia_countries;
DROP TABLE Asia_countries;
DROP VIEW total_price_97;

\d employees;

SELECT *FROM employees WHERE manager_id=1;

ALTER TABLE employees DROP CONSTRAINT fk_employees_manager;
ALTER TABLE employees ADD CONSTRAINT fk_employees_manager
FOREIGN KEY(manager_id) REFERENCES employees(employee_id);

DELETE FROM employees WHERE employee_id=1;

ALTER TABLE employees DROP CONSTRAINT fk_employees_manager;
ALTER TABLE employees ADD CONSTRAINT fk_employees_manager
FOREIGN KEY(manager_id) REFERENCES employees(employee_id)
ON DELETE CASCADE;

DELETE FROM employees WHERE employee_id = 1;

ALTER TABLE employees DROP CONSTRAINT fk_employees_manager;
ALTER TABLE employees ADD CONSTRAINT fk_employees_manager
FOREIGN KEY(manager_id) REFERENCES employees(employee_id)
ON DELETE CASCADE ON UPDATE CASCADE;
















