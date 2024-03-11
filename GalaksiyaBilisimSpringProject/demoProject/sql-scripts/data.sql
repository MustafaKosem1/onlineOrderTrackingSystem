INSERT INTO `galaksiya_db`.`role` (`name`) VALUES ('ROLE_MANAGER');
INSERT INTO `galaksiya_db`.`role` (`name`) VALUES ('ROLE_CUSTOMER');


INSERT INTO `galaksiya_db` (`username`,`name`,`surname`,`phone`,`mail`,`password`,`enabled`,`role`) VALUES ('mehmet','mehmet','Yılmaz','1234567','abc@gmail.com','$2a$10$iuXyOh5DXiFvQFU2mjzuXO7MMMycKNjgipOv9X2JJc5IJcQhqu7Zm','1','ROLE_CUSTOMER');
INSERT INTO `galaksiya_db` (`username`,`name`,`surname`,`phone`,`mail`,`password`,`enabled`,`role`) VALUES ('manager','manager','Yılmaz','1234','yenimail@gmail.com','$2a$10$QXJaIU93nJpcR5UQwCvZe.3l4fylryRC7.eWSLjLgeP24mHuhZHGm','1','ROLE_MANAGER');

INSERT INTO `galaksiya_db` (`delivery_time`,`stock`,`name`,`category`,`description`,`price`) VALUES (3,5,'Telefon','Elektronik','Çok güzel bir telefon.',150);
INSERT INTO `galaksiya_db` (`delivery_time`,`stock`,`name`,`category`,`description`,`price`) VALUES (2,10,'Cin Ali','Kitap','Çok keyifli bir kitap.',10);
INSERT INTO `galaksiya_db` (`delivery_time`,`stock`,`name`,`category`,`description`,`price`) VALUES (1,7,'Kurşun Kalem','Kalem','Mükemmel.',5);