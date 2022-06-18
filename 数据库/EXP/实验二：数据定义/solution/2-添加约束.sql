-- regions table
ALTER TABLE regions ALTER region_name SET NOT NULL;
ALTER TABLE regions ADD CONSTRAINT pk PRIMARY KEY(region_id);
ALTER TABLE regions ALTER region_id SET NOT NULL;

-- countries table
ALTER TABLE countries ALTER country_name SET NOT NULL;
ALTER TABLE countries ADD CONSTRAINT ctpk PRIMARY KEY(country_id);
ALTER TABLE countries ADD CONSTRAINT fk_countries_regions FOREIGN KEY( region_id )
	 REFERENCES regions( region_id ) 
	 ON DELETE CASCADE;

-- location
ALTER TABLE locations ADD CONSTRAINT loctpk PRIMARY KEY(location_id);
ALTER TABLE locations ALTER address SET NOT NULL;
ALTER TABLE locations ADD CONSTRAINT fk_locations_countries 
      FOREIGN KEY( country_id )
      REFERENCES countries( country_id ) 
      ON DELETE CASCADE;
      
-- employees
ALTER TABLE employees ADD CONSTRAINT employeepk PRIMARY KEY(employee_id);
ALTER TABLE employees ALTER first_name SET NOT NULL;
ALTER TABLE employees ALTER last_name SET NOT NULL;
ALTER TABLE employees ALTER email SET NOT NULL;
ALTER TABLE employees ALTER phone SET NOT NULL;
ALTER TABLE employees ALTER hire_date SET NOT NULL;
ALTER TABLE employees ALTER job_title SET NOT NULL;
ALTER TABLE employees ADD CONSTRAINT fk_employees_manager 
        FOREIGN KEY( manager_id )
        REFERENCES employees( employee_id )
        ON DELETE CASCADE;

-- warehouses
ALTER TABLE warehouses ADD CONSTRAINT warepk PRIMARY KEY(warehouse_id);
ALTER TABLE warehouses ADD  CONSTRAINT fk_warehouses_locations 
      FOREIGN KEY( location_id )
      REFERENCES locations( location_id ) 
      ON DELETE CASCADE;
	
-- product category
ALTER TABLE product_categories ADD CONSTRAINT category_idpk PRIMARY KEY(category_id);
 ALTER TABLE product_categories ALTER category_name SET NOT NULL;     
 
-- products table
ALTER TABLE products ADD CONSTRAINT product_idpk PRIMARY KEY(product_id);
ALTER TABLE products ALTER product_name SET NOT NULL;
ALTER TABLE products ALTER category_id SET NOT NULL;
ALTER TABLE products ADD  CONSTRAINT fk_products_categories 
      FOREIGN KEY( category_id )
      REFERENCES product_categories( category_id ) 
      ON DELETE CASCADE;
      
-- customers
ALTER TABLE customers ADD CONSTRAINT customers_idpk PRIMARY KEY(customer_id);
ALTER TABLE customers ALTER name SET NOT NULL;
      
-- contacts
ALTER TABLE contacts ADD CONSTRAINT contactspk PRIMARY KEY(contact_id);
ALTER TABLE contacts ALTER first_name SET NOT NULL;
ALTER TABLE contacts ALTER last_name SET NOT NULL;
ALTER TABLE contacts ALTER email SET NOT NULL;      
ALTER TABLE contacts ADD CONSTRAINT fk_contacts_customers 
      FOREIGN KEY( customer_id )
      REFERENCES customers( customer_id ) 
      ON DELETE CASCADE;

-- orders table
ALTER TABLE orders ADD CONSTRAINT orderspk PRIMARY KEY(order_id);
ALTER TABLE orders ALTER customer_id SET NOT NULL;
ALTER TABLE orders ALTER status SET NOT NULL;
ALTER TABLE orders ALTER order_date SET NOT NULL;     
ALTER TABLE orders ADD CONSTRAINT fk_orders_customers 
      FOREIGN KEY( customer_id )
      REFERENCES customers( customer_id )
      ON DELETE CASCADE;
ALTER TABLE orders ADD CONSTRAINT fk_orders_employees 
      FOREIGN KEY( salesman_id )
      REFERENCES employees( employee_id ) 
      ON DELETE SET NULL;

-- order items
ALTER TABLE order_items ALTER product_id SET NOT NULL;
ALTER TABLE order_items ALTER quantity SET NOT NULL;
ALTER TABLE order_items ALTER unit_price SET NOT NULL;     
ALTER TABLE order_items ADD  CONSTRAINT pk_order_items 
      PRIMARY KEY( order_id, item_id );
ALTER TABLE order_items ADD CONSTRAINT fk_order_items_products 
      FOREIGN KEY( product_id )
      REFERENCES products( product_id ) 
      ON DELETE CASCADE;
ALTER TABLE order_items ADD CONSTRAINT fk_order_items_orders 
      FOREIGN KEY( order_id )
      REFERENCES orders( order_id ) 
      ON DELETE CASCADE;

-- inventories
ALTER TABLE inventories ALTER quantity SET NOT NULL;     
ALTER TABLE inventories ADD  CONSTRAINT pk_inventories 
      PRIMARY KEY( product_id, warehouse_id );
ALTER TABLE inventories ADD  CONSTRAINT fk_inventories_products 
      FOREIGN KEY( product_id )
      REFERENCES products( product_id ) 
      ON DELETE CASCADE;
ALTER TABLE inventories ADD   CONSTRAINT fk_inventories_warehouses 
      FOREIGN KEY( warehouse_id )
      REFERENCES warehouses( warehouse_id ) 
      ON DELETE CASCADE; 

      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      

































