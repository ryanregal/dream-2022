/*查询顾客表中的顾客号（customer_id）、顾客名（name）和信用卡额度（credit_limit）*/
SELECT customer_id,name,credit_limit FROM customers;
 SELECT * FROM customers;

 /*查询订单表中的订单号，顾客号，状态，订单日期，并按订单日期降序显示结果。*/
 SELECT order_id,customer_id,status,order_date FROM orders ORDER BY order_date Desc;

 /*查询联系表中的名（first name）和姓（last name），并按名升序，姓降序显示*/
 SELECT first_name,last_name FROM contacts ORDER BY first_name Asc,last_name Desc;

/*空格的位置*/ 
SELECT country_id, city,state FROM locations ORDER BY city,state; 
SELECT country_id, city,state FROM locations ORDER BY state ASC NULLS FIRST ; 
SELECT country_id, city,state FROM locations ORDER BY state ASC NULLS LAST;

/*查询订单细节表中（order_items）的产品号和数量，查询结果应无重复元组*/
 SELECT DISTINCT product_id,quantity FROM order_items;
 
/*查询产品表中的产品名为‘Kingston’的产品名，产品描述和价格*/
SELECT product_name,description,list_price FROM products WHERE product_name='Kingston';

/*查询产品表中所有价格大于 500 且 category_id 为 4 的产品名和价格*/
SELECT product_name,list_price FROM products WHERE category_id=4 AND list_price>500;

/*查询产品表中所有价格在 650 和 680 之间的产品名和价格并按价格升序显示结果*/
SELECT product_name,list_price FROM products WHERE list_price between 650 AND 680 order by list_price asc;

/*查询雇员表中的名和姓，名和姓的字段分别显示为"First Name"和"Family Name"*/
SELECT first_name "First Name",last_name "Family Name" FROM employees;

/*查询产品表中的产品名及毛利，并按毛利结果降序显示，毛利名为 gross_profit，毛利=
list_price - standard_cost*/
SELECT product_name,list_price - standard_cost "gross_profit" FROM products order by gross_profit DESC;

/*查询雇员表中每个雇员对应的经理名，要求第一列字段名为 employee，第二列字段名为manager，无其他字段*/
/*employees表连接到自身。这种技术称为INNER JOIN。因为一个表只能在查询中出现一次，
所以必须使用表别名为员工提供两个不同的名称，即e员工和m经理。*/
SELECT
  e.first_name employee,
  m.first_name manager
FROM employees e
INNER JOIN employees m ON  m.employee_id = e.manager_id;

/*查询产品表中所有以 Asus 开头的产品名和价格，并以价格降序显示*/
SELECT product_name,list_price FROM products WHERE product_name LIKE 'Asus%' ORDER BY list_price DESC;

/*查询联系表中电话号码不是以‘+1’开头的名、姓和电话号码，并以名升序显示*/
SELECT first_name,last_name,phone FROM contacts WHERE phone NOT LIKE '+1%' ORDER BY first_name ASC;

/*查询联系表中的电话号码和电子邮件，要求名中包含‘Je_i’且以名升序显示*/
SELECT first_name,phone,email FROM contacts WHERE first_name LIKE 'Je_i' ORDER BY first_name ASC;

/*查询联系表中所有以开头'Je'的名，且至少包含 3 个字符的名，姓，电子邮件和电话*/
SELECT first_name,last_name,email,phone FROM contacts WHERE first_name LIKE 'Je_%'; 

/*查询订单表中所有没有销售员负责的订单（i.e., query all sales orders that do not have a responsible 
salesman）*/
SELECT *FROM orders WHERE salesman_id is null;

/*统计每个顾客的订单总数（查询订单表）
按customers对order进行分组，使用该COUNT()函数返回每组的订单数量
然后将orders表与customers表连接起来，customer_id是外键*/
SELECT name, COUNT( order_id ) FROM orders
INNER JOIN customers USING(customer_id)
GROUP BY name ORDER BY name;

/*统计每个订单的总价格大于 1000000 的订单号和总价格，并按总价格降序显示结果
（查询订单细节表 order_items，总价格=unit_price*quantity）*/
SELECT order_id, SUM(unit_price*quantity) amount FROM orders
INNER JOIN order_items USING(order_id) 
GROUP BY ROLLUP(order_id) HAVING (amount>1000000)
ORDER BY amount DESC ;

/*创建一个折扣表 discounts*/
CREATE TABLE discounts
 ( product_id NUMBER, --产品号，主码
 discount_message VARCHAR2( 255 ) NOT NULL, --折扣信息
 PRIMARY KEY( product_id ) );
INSERT INTO discounts(product_id, discount_message) VALUES(1, 'Buy 1 and Get 25% OFF on 2nd ');
INSERT INTO discounts(product_id, discount_message) VALUES(2, 'Buy 2 and Get 50% OFF on 3rd ');
INSERT INTO discounts(product_id, discount_message) VALUES(3, 'Buy 3 Get 1 free');
 
 /*查询折扣表中折扣信息出现“25%”的产品号和折扣信息。*/
 -- ESCAPE定义转义符
SELECT product_id, discount_message FROM discounts
WHERE discount_message LIKE '%25!%%' ESCAPE '!';

/*错误示范1：在 SELECT 子句中书写聚合键之外的列名会发生错误
列名status并没有包含在 GROUP BY 子句当中。因此，该列名也不能书写在 SELECT 子句之中。
不支持这种语法的原因，通过某个聚合键将表分组之后，结果中的一行数据就代表一组。
而如果SELECT其他列名，可能出现不是一对一的情况。*/
SELECT order_id, status,SUM(unit_price*quantity) amount FROM orders
INNER JOIN order_items USING(order_id) 
GROUP BY ROLLUP(order_id) HAVING (amount>1000000);

/*错误示范2：在 WHERE 子句中不能使用聚合函数
只有 SELECT 子句和 HAVING 子句（以及ORDER BY ）中能够使用 COUNT 等聚合函数。*/
SELECT order_id as wrong, SUM(unit_price*quantity) amount FROM orders
INNER JOIN order_items USING(order_id) WHERE amount>1000000
GROUP BY ROLLUP(order_id);





