CREATE TABLE regions(region_id NUMBER ,region_name VARCHAR2( 50 ) );
CREATE TABLE countries(country_id   CHAR( 2 ) ,country_name VARCHAR2( 40 ) ,region_id  NUMBER );
CREATE TABLE locations(
    location_id NUMBER ,
    address     VARCHAR2( 255 ),
    postal_code VARCHAR2( 20 ) ,
    city        VARCHAR2( 50 ) ,
    state       VARCHAR2( 50 )  ,
    country_id  CHAR( 2 )              
  );
  CREATE TABLE warehouses
  (
    warehouse_id NUMBER ,
    warehouse_name VARCHAR( 255 ) ,
    location_id    NUMBER( 12, 0 )
  );
 CREATE TABLE inventories
  (
    product_id   NUMBER( 12, 0 ) , 
    warehouse_id NUMBER( 12, 0 ) , 
    quantity     NUMBER( 8, 0 ) NOT NULL
  ); 
  CREATE TABLE employees
  (
    employee_id NUMBER ,
    first_name VARCHAR( 255 ) ,
    last_name  VARCHAR( 255 ) ,
    email      VARCHAR( 255 ) ,
    phone      VARCHAR( 50 ) ,
    hire_date  DATE  ,
    manager_id NUMBER( 12, 0 ) , 
    job_title  VARCHAR( 255 ) 
  );
  CREATE TABLE product_categories
  (
    category_id NUMBER ,
    category_name VARCHAR2( 255 ) 
  );
  CREATE TABLE products
  (
    product_id NUMBER ,
    product_name  VARCHAR2( 255 ) ,
    description   VARCHAR2( 2000 ),
    standard_cost NUMBER( 9, 2 ) ,
    list_price    NUMBER( 9, 2 ) ,
    category_id   NUMBER 
  );
  CREATE TABLE customers
  (
    customer_id NUMBER ,
    name         VARCHAR2( 255 ) ,
    address      VARCHAR2( 255 ),
    website      VARCHAR2( 255 ) ,
    credit_limit NUMBER( 8, 2 )
  );
  CREATE TABLE contacts
  (
    contact_id NUMBER ,
    first_name  VARCHAR2( 255 ) ,
    last_name   VARCHAR2( 255 ) ,
    email       VARCHAR2( 255 ) ,
    phone       VARCHAR2( 20 ) ,
    customer_id NUMBER  
  );
CREATE TABLE orders
  (
    order_id NUMBER ,
    customer_id NUMBER( 6, 0 ) , 
    status      VARCHAR( 20 )  ,
    salesman_id NUMBER( 6, 0 ) , 
    order_date  DATE
  );
  CREATE TABLE order_items
  (
    order_id   NUMBER( 12, 0 ) , 
    item_id    NUMBER( 12, 0 ) ,
    product_id NUMBER( 12, 0 ) , 
    quantity   NUMBER( 8, 2 ) ,
    unit_price NUMBER( 8, 2 ) 
  );
  
  
  
  
  
  
  
  
  
  
  
  