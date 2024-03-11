CREATE DATABASE galaksiya_db;

CREATE TABLE `galaksiya_db`.`role` (
	`name` varchar(50) NOT NULL,
	PRIMARY KEY (`name`)
);

CREATE TABLE `galaksiya_db`.`user` (
`id` INT(11) NOT NULL AUTO_INCREMENT,
`username` varchar(50) NOT NULL,
`name` varchar(50) NOT NULL,
`surname` varchar(50) NOT NULL,
`phone` varchar(50) NOT NULL,
`mail` varchar(50) NOT NULL,
`password` char(50) NOT NULL,
`enabled` tinyint NOT NULL,
`role` varchar(50) NOT NULL,
PRIMARY KEY (`id`),
CONSTRAINT `role_fk_constraint` FOREIGN KEY (`role`) REFERENCES `role` (`name`)
ON DELETE NO ACTION ON UPDATE NO ACTION,
UNIQUE INDEX `username_UNIQUE` (`username` ASC) VISIBLE);

CREATE TABLE `galaksiya_db`.`product` (
`id` INT NOT NULL AUTO_INCREMENT,
`delivery_time` INT NOT NULL,
`stock` INT NOT NULL,
`name` VARCHAR(50) NOT NULL,
`category` VARCHAR(50) NOT NULL,
`description` LONGTEXT NOT NULL,
`price` FLOAT NOT NULL,
PRIMARY KEY (`id`),
UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE);

CREATE TABLE `galaksiya_db`.`order` (
`id` INT NOT NULL AUTO_INCREMENT,
`date` DATE NOT NULL,
`estimated_date` DATE NOT NULL,
`delivery_date` DATE,
`owner` INT(11) NOT NULL,
`status` VARCHAR(50) NOT NULL,
`total_price` FLOAT NOT NULL,
PRIMARY KEY (`id`),
UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
CONSTRAINT `owner_fk_constraint` FOREIGN KEY (`owner`) REFERENCES `user` (`id`)
ON DELETE NO ACTION ON UPDATE NO ACTION);

CREATE TABLE `galaksiya_db`.`order_product` (
`id` INT NOT NULL AUTO_INCREMENT,
`order_id` INT NOT NULL,
`product_id` INT NOT NULL,
PRIMARY KEY (`id`),
CONSTRAINT `order_fk_constraint` FOREIGN KEY (`order_id`) REFERENCES `order_table` (`id`)
ON DELETE NO ACTION ON UPDATE NO ACTION,
CONSTRAINT `product_fk_constraint` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
ON DELETE NO ACTION ON UPDATE NO ACTION
);