/*创建两张表 palette_a 和 palette_b（结构相同，但表名不同，color 为颜色）*/
CREATE TABLE palette_a
( id INT PRIMARY KEY,
color VARCHAR2 (100) NOT NULL);
CREATE TABLE palette_b
( id INT PRIMARY KEY,
color VARCHAR2 (100) NOT NULL);

/*为表 palette_a 添加样例数据：{(1, 'Red'), (2, 'Green'), (3, 'Blue'), (4, 'Purple')}
为表 palette_b 添加样例数据：{(1, 'Green'), (2, 'Red'), (3, 'Cyan'), (4, 'Brown')}*/
INSERT INTO palette_a VALUES (1, 'Red');
INSERT INTO palette_a VALUES  (2, 'Green');
INSERT INTO palette_a VALUES (3, 'Blue');
INSERT INTO palette_a VALUES (4, 'Purple');
INSERT INTO palette_b VALUES (1, 'Green');
INSERT INTO palette_b VALUES (2, 'Red');
INSERT INTO palette_b VALUES (3, 'Cyan');
INSERT INTO palette_b VALUES (4, 'Brown');

/*查询两张表中相同颜色的所有信息*/
SELECT a.id a_id,a.color a_color,b.id b_id,b.color b_color FROM palette_a a 
INNER JOIN palette_b b ON a.color=b.color;

/*查询 palette_a 表中颜色不出现在 palette_b 表中的两张表的 id 和颜色（用左外连接）*/
SELECT a.id a_id,a.color a_color,b.id b_id,b.color b_color FROM palette_a a 
LEFT JOIN palette_b b ON a.color=b.color WHERE b.id IS null;

/*查询 palette_b 表中颜色不出现在 palette_a 表中的两张表的 id 和颜色（用右外连接）*/
SELECT a.id a_id,a.color a_color,b.id b_id,b.color b_color FROM palette_a a 
RIGHT JOIN palette_b b ON a.color=b.color WHERE a.id IS null;

/*查询（5）或（6）两种情况的信息（用（全）外连接）*/
SELECT a.id a_id,a.color a_color,b.id b_id,b.color b_color FROM palette_a a 
FULL OUTER JOIN palette_b b ON a.color=b.color;

/*查询产品表 products 中的 product_id, product_name, list_price 信息，要求产品定价 list_price 大于
其平均定价 list_price*/
/*聚合函数不允许直接在WHERE语句中，于是思路如下：首先子查询返回所有产品的平均标价。
其次，外部查询获取标价大于子查询返回的平均标价的产品。*/
SELECT product_id, product_name, list_price FROM products WHERE  list_price>
(SELECT  AVG(list_price) FROM  products) ORDER BY product_id;

/*查询没有一个订单的顾客姓名（实现要求：NOT IN（必须）+其它查询方法（如果找到））*/
-- 第一种：NOT IN
SELECT name FROM customers WHERE customer_id NOT IN(SELECT customer_id FROM orders) ORDER BY name ;
-- 第二种：NOT EXISTS
SELECT name FROM customers WHERE NOT EXISTS(SELECT * FROM orders
WHERE orders.customer_id=customers.customer_id) ORDER BY name;

/*查询产品表 products 中最便宜产品的 product_id, product_name, list_price。*/
SELECT product_id, product_name, list_price FROM products WHERE list_price=
(SELECT min(list_price) FROM products);

/*查询产品表 products 中产品的 product_id, product_name, list_price，
要求产品定价 list_price 大于其同类产品（可由 category_id 表达）的平均定价。
实现要求：相关子查询（必须）+基于派生表的查询（如果找到）*/
SELECT product_id, product_name, list_price FROM products a WHERE list_price>
(SELECT avg(list_price) FROM products b WHERE a.category_id=b.category_id) 

-- 查询有订单 order 的所有顾客 customer 姓名（查询涉及 customers 表和 orders 表） 
-- 第一种：EXISTS
SELECT DISTINCT name FROM customers WHERE EXISTS (SELECT *FROM orders WHERE customers.customer_id=orders.customer_id) order by name;
-- 第二种：IN
SELECT DISTINCT name FROM customers WHERE customer_id IN(SELECT customer_id FROM orders) order by name;
-- 第三种：不使用谓词
SELECT DISTINCT name FROM customers,orders WHERE customers.customer_id=orders.customer_id ORDER BY name;

-- 执行以下两条语句，观察有何不同，能否得出某些初步结论？
SELECT * FROM customers WHERE customer_id IN (NULL);
SELECT * FROM customers WHERE EXISTS (SELECT NULL);

-- 找出所有没有订单的顾客姓名（查询涉及 customers 表和 orders 表）
-- 实现要求：使用 NOT EXISTS（必须）+其它查询方法（如果找到）
-- 第一种 NOT EXISTS
SELECT DISTINCT name FROM customers WHERE NOT EXISTS  
(SELECT *FROM orders WHERE customers.customer_id=orders.customer_id) order by name;
-- 第二种 NOT IN
SELECT DISTINCT name FROM customers WHERE customers.customer_id NOT IN  (SELECT customer_id FROM orders) order by name;

-- 查询产品表 products 中的产品名 product_name 和定价 list_price，要求其定价高于产品种类 1 中的任何产品定价。
-- 实现要求：ANY（必须）+其它查询方法（如果找到）
-- 第一种：ALL
SELECT product_name,list_price FROM products a WHERE list_price>All 
(SELECT list_price FROM products WHERE category_id=1) order by product_name;
-- 第二种：ANY
SELECT product_name,list_price FROM products a WHERE NOT
(list_price<= ANY (SELECT list_price FROM products b WHERE category_id=1) )order by product_name;
-- 第三种：NOT EXISTS
SELECT product_name,list_price FROM products a WHERE NOT EXISTS
(SELECT b.list_price FROM products b WHERE b.category_id=1
AND b.list_price>=a.list_price) order by product_name;

-- 查询产品表 products 中的产品名 product_name 和定价 list_price
-- 要求其定价低于产品种类的所有平均定价。实现要求：ALL（必须）+其它查询方法（如果找到）
-- 第一种: ALL
SELECT product_name,list_price FROM products WHERE list_price<
ALL(SELECT AVG(list_price)FROM products GROUP BY category_id) order by product_name;
-- 第二种：ANY
SELECT product_name,list_price FROM products WHERE NOT 
(list_price>=ANY(SELECT AVG(list_price)FROM products GROUP BY category_id)) order by product_name;

-- 查询 contacts 表和 employees 表中的所有 last_name，并以 last_name 升序显示。
-- 实现要求：去重+UNION（必须）+其它查询方法（如果找到）
SELECT last_name FROM contacts UNION 
SELECT last_name FROM employees order by last_name ASC;

-- 查询 contacts 表和 employees 表中的所有 last_name，并以 last_name 升序显示。
-- 实现要求：保留重复+UNION（必须）+其它查询方法（如果找到）
SELECT last_name FROM contacts UNION ALL 
SELECT last_name FROM employees order by last_name ASC;

-- 查询同时出现在 contacts 表和 employees 表中的所有 last_name
-- 实现要求：INTERSECT（必须）+其它查询方法（如果找到）
-- 方法一：使用INTERSECT
SELECT last_name FROM contacts INTERSECT  SELECT last_name FROM employees order by last_name ASC;
-- 方法二：使用IN
SELECT DISTINCT last_name FROM contacts WHERE last_name IN(SELECT last_name FROM employees)order by last_name ASC;

-- 查询在产品表 products 中而不在库存表 inventories 中的产品号 product_id。
-- 实现要求：MINUS/EXCEPT（必须）+其它查询方法（如果找到）
-- 方法一：EXCEPT
SELECT product_id FROM products EXCEPT SELECT product_id FROM inventories ORDER BY product_id;
-- 方法二：NOT EXISTS
SELECT DISTINCT product_id FROM products p WHERE NOT EXISTS
(SELECT B.product_id FROM inventories B WHERE B.product_id=p.product_id) ORDER BY product_id;
-- 方法三：MINUS
SELECT product_id FROM products MINUS SELECT product_id FROM inventories ORDER BY product_id;

-- 子查询思考一
-- 正确
SELECT product_id,product_name,list_price
FROM products WHERE list_price > (SELECT AVG( list_price )
FROM products)ORDER BY product_name;
-- 错误
SELECT product_id,product_name,list_price
FROM products WHERE list_price > AVG( list_price ) ORDER BY product_name;

-- 子查询思考二
-- 正确
SELECT name FROM customers WHERE
customer_id NOT IN( SELECT customer_id FROM orders
WHERE EXTRACT(YEAR FROM order_date) = 2017 ) ORDER BY name; 

-- 子查询转化
-- 相干子查询
SELECT* FROM customers a WHERE EXISTS(
	SELECT* FROM orders AS c
    WHERE c.customer_id=a.customer_id
    AND(name like 'J%' OR name like 'R%'));
-- 不相干子查询
SELECT * FROM customers WHERE customer_id IN (
        SELECT customer_id FROM orders
        WHERE name like 'J%' OR name like 'R%');
