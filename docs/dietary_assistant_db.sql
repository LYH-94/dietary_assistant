CREATE DATABASE 'dietary_assistant_db' CHARACTER SET 'utf8mb4';

USE dietary_assistant_db;

/* 根據 E-R 模型創建各實體的數據表。 */

/* 用戶 - t_user */
CREATE TABLE IF NOT EXISTS `t_user`(
	`id` INT NOT NULL AUTO_INCREMENT,
	`account` VARCHAR(50) UNIQUE NOT NULL,
	`password` VARCHAR(50) UNIQUE NOT NULL,
	PRIMARY KEY(`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4;

/* 新增 t_user 數據表的數據。 */
INSERT INTO `t_user`(`id`,`account`,`password`) VALUES
(0,'user001','pwd001'),
(0,'user002','pwd002'),
(0,'user003','pwd003'),
(0,'user004','pwd004'),
(0,'user005','pwd005');

/* 用戶資訊 - t_user_info */
CREATE TABLE IF NOT EXISTS `t_user_info`(
	`id` INT NOT NULL AUTO_INCREMENT,
	`owner` INT UNIQUE NOT NULL,
	`nickName` VARCHAR(20) NOT NULL,
	`email` VARCHAR(30) NOT NULL,
	`targetCalories` SMALLINT UNSIGNED NOT NULL DEFAULT 1800,
	`carbohydrateRatio` TINYINT UNSIGNED NOT NULL DEFAULT 50,
	`fatRatio` TINYINT UNSIGNED NOT NULL DEFAULT 20,
	`proteinRatio` TINYINT UNSIGNED NOT NULL DEFAULT 30,
	PRIMARY KEY(`id`),
	CONSTRAINT `FK_user_info_user` FOREIGN KEY (`owner`) REFERENCES `t_user` (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4;

/* 新增 t_user_info 數據表的數據。 */
INSERT INTO `t_user_info`(`id`,`owner`,`nickName`,`email`,`targetCalories`,`carbohydrateRatio`,`fatRatio`,`proteinRatio`) VALUES
(0,1,'nickName_A','nickName_A_abc@gmail.com',1750,50,25,25),
(0,2,'nickName_B','nickName_B_abc@gmail.com',1800,50,20,30),
(0,3,'nickName_C','nickName_C_abc@gmail.com',1630,40,20,40),
(0,4,'nickName_D','nickName_D_abc@gmail.com',1700,50,25,25),
(0,5,'nickName_E','nickName_E_abc@gmail.com',1520,50,25,25);

/* 食品 - t_food */
CREATE TABLE IF NOT EXISTS `t_food`(
	`id` INT NOT NULL AUTO_INCREMENT,
	`owner` INT NOT NULL,
	`foodName` VARCHAR(50) NOT NULL,
	`calories` FLOAT UNSIGNED NOT NULL DEFAULT 0,
	`carbohydrate` FLOAT UNSIGNED NOT NULL DEFAULT 0,
	`fat` FLOAT UNSIGNED NOT NULL DEFAULT 0,
	`protein` FLOAT UNSIGNED NOT NULL DEFAULT 0,
	`portionSize` SMALLINT UNSIGNED NOT NULL DEFAULT 1,
	PRIMARY KEY(`id`),
	CONSTRAINT `FK_food_user` FOREIGN KEY (`owner`) REFERENCES `t_user` (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4;

/* 新增 t_food 數據表的數據。 */
INSERT INTO `t_food`(`id`,`owner`,`foodName`,`calories`,`carbohydrate`,`fat`,`protein`,`portionSize`) VALUES
(0,1,'appale',52,14,0.2,0.3,100),
(0,1,'banana',88.7,23,0.3,1.1,100),
(0,1,'蛋餅',261,34,9,11,117),
(0,1,'燒餅',257,41,7,7,80),
(0,2,'肉包',477,53,23,15,153),
(0,2,'皮蛋瘦肉粥',166,22,4,10,390);

/* 飲食日記 - t_diet_diary */
CREATE TABLE IF NOT EXISTS `t_diet_diary`(
	`id` INT NOT NULL AUTO_INCREMENT,
	`owner` INT NOT NULL,
	`date` DATE NOT NULL,
	`food` INT NOT NULL,
	`portionSize` SMALLINT UNSIGNED NOT NULL DEFAULT 1,
	`threeMeals` CHAR(6) NOT NULL DEFAULT '早餐',
	PRIMARY KEY(`id`),
	CONSTRAINT `FK_diet_diary_user` FOREIGN KEY (`owner`) REFERENCES `t_user` (`id`),
	CONSTRAINT `FK_diet_diary_food` FOREIGN KEY (`food`) REFERENCES `t_food` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4;

/* 新增 t_diet_diary 數據表的數據。 */
INSERT INTO `t_diet_diary`(`id`,`owner`,`date`,`food`,`portionSize`,`threeMeals`) VALUES
(0,1,NOW(),3,1,'早餐'),
(0,1,NOW(),4,1,'午餐'),
(0,1,NOW(),1,1,'晚餐');

/* 體態數據 - t_body_composition_data */
CREATE TABLE IF NOT EXISTS `t_body_composition_data`(
	`id` INT NOT NULL AUTO_INCREMENT,
	`owner` INT NOT NULL,
	`date` DATE NOT NULL,
	`weight` FLOAT UNSIGNED NOT NULL DEFAULT 0,
	`bodyFat` FLOAT UNSIGNED NOT NULL DEFAULT 0,
	`skeletalMuscleMass` FLOAT UNSIGNED NOT NULL DEFAULT 0,
	PRIMARY KEY(`id`),
	CONSTRAINT `FK_body_composition_data_user` FOREIGN KEY (`owner`) REFERENCES `t_user` (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4;

/* 新增 t_body_composition_data 數據表的數據。 */
INSERT INTO `t_body_composition_data`(`id`,`owner`,`date`,`weight`,`bodyFat`,`skeletalMuscleMass`) VALUES
(0,1,'2024-07-27',75.3,25.3,36),
(0,1,'2024-07-26',75.8,25.8,35.8),
(0,1,'2024-07-25',76,26,35.5),
(0,2,'2024-07-27',55.3,28.1,31.5),
(0,2,'2024-07-26',56,28.7,30.5);





