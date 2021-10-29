drop schema ims;

CREATE SCHEMA IF NOT EXISTS `ims`;

USE `ims` ;

CREATE TABLE IF NOT EXISTS `ims`.`customers` (
    `customers_id` INT(11) NOT NULL AUTO_INCREMENT,
    `first_name` VARCHAR(40) DEFAULT NULL,
    `surname` VARCHAR(40) DEFAULT NULL,
    PRIMARY KEY (`customers_id`)
);
CREATE TABLE `ims`.`items` (
    `item_id` INT(30) NOT NULL AUTO_INCREMENT,
    `item_name` VARCHAR(30),
    `price` DOUBLE(10,2),
    PRIMARY KEY (`item_id`)
    );
    
    CREATE TABLE `ims`.`orders` (
    `orders_id` INT(30) NOT NULL AUTO_INCREMENT,
    `quantity` INT(11),
    `total_price` DOUBLE(10,2),
    `fk_customers_id` INT(30) NOT NULL,
    PRIMARY KEY(`orders_id`),
    FOREIGN KEY(`fk_customers_id`) REFERENCES customers(`customers_id`)
    );
    
    CREATE TABLE `ims`.`order_items` (
    `order_items_id` INT(30) NOT NULL AUTO_INCREMENT,
    `fk_item_id` INT(30) NOT NULL,
    `fk_orders_id` INT(30) NOT NULL,
    PRIMARY KEY(`order_items_id`),
    FOREIGN KEY(`fk_item_id`) REFERENCES items(`item_id`),
    FOREIGN KEY(`fk_orders_id`) REFERENCES orders(`orders_id`)
    );
    
    
    
    

SELECT * FROM orders; 
    