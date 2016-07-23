--<ScriptOptions statementTerminator=";"/>
--<sql script>

ALTER TABLE `capstore`.`user_role_assoc` DROP PRIMARY KEY;

ALTER TABLE `capstore`.`role` DROP PRIMARY KEY;

ALTER TABLE `capstore`.`user` DROP PRIMARY KEY;

ALTER TABLE `capstore`.`contact` DROP PRIMARY KEY;

ALTER TABLE `capstore`.`user_role_assoc` DROP INDEX `capstore`.`FK_USER_NAME_idx`;

ALTER TABLE `capstore`.`contact` DROP INDEX `capstore`.`contact_id_UNIQUE`;

ALTER TABLE `capstore`.`user_role_assoc` DROP INDEX `capstore`.`FK_ROLE_idx`;

ALTER TABLE `capstore`.`contact` DROP INDEX `capstore`.`FK_userName_idx`;

ALTER TABLE `capstore`.`role` DROP INDEX `capstore`.`role_type_UNIQUE`;

DROP TABLE `capstore`.`user_role_assoc`;

DROP TABLE `capstore`.`contact`;

DROP TABLE `capstore`.`role`;

DROP TABLE `capstore`.`user`;

CREATE TABLE `capstore`.`user_role_assoc` (
	`assoc_id` BIGINT NOT NULL,
	`user_name` VARCHAR(15) NOT NULL,
	`role_id` INT NOT NULL,
	`upd_tsp` TIMESTAMP DEFAULT 'CURRENT_TIMESTAMP',
	PRIMARY KEY (`assoc_id`)
) ENGINE=InnoDB;

CREATE TABLE `capstore`.`contact` (
	`contact_id` BIGINT NOT NULL,
	`contact_name` VARCHAR(45) NOT NULL,
	`addressLine1` VARCHAR(45) NOT NULL,
	`addressLine2` VARCHAR(45),
	`city` VARCHAR(45) NOT NULL,
	`state` CHAR(3) NOT NULL,
	`zip` INT NOT NULL,
	`upd_tsp` TIMESTAMP DEFAULT 'CURRENT_TIMESTAMP' NOT NULL,
	`user_name` VARCHAR(45) NOT NULL,
	PRIMARY KEY (`contact_id`)
) ENGINE=InnoDB;

CREATE TABLE `capstore`.`role` (
	`role_id` INT NOT NULL,
	`role_type` VARCHAR(45) NOT NULL,
	PRIMARY KEY (`role_id`)
) ENGINE=InnoDB;

CREATE TABLE `capstore`.`user` (
	`user_name` VARCHAR(15) NOT NULL,
	`first_name` VARCHAR(45),
	`last_name` VARCHAR(45),
	`email` VARCHAR(45) NOT NULL,
	`password` VARCHAR(20) NOT NULL,
	`security_question` VARCHAR(100),
	`security_answer` VARCHAR(100),
	`date_joined` TIMESTAMP DEFAULT 'CURRENT_TIMESTAMP' NOT NULL,
	`status` CHAR(2) NOT NULL,
	`phone_number` BIGINT,
	`upd_tsp` TIMESTAMP DEFAULT 'CURRENT_TIMESTAMP' NOT NULL,
	PRIMARY KEY (`user_name`)
) ENGINE=InnoDB;

CREATE INDEX `FK_USER_NAME_idx` ON `capstore`.`user_role_assoc` (`user_name` ASC);

CREATE UNIQUE INDEX `contact_id_UNIQUE` ON `capstore`.`contact` (`contact_id` ASC);

CREATE INDEX `FK_ROLE_idx` ON `capstore`.`user_role_assoc` (`role_id` ASC);

CREATE INDEX `FK_userName_idx` ON `capstore`.`contact` (`user_name` ASC);

CREATE UNIQUE INDEX `role_type_UNIQUE` ON `capstore`.`role` (`role_type` ASC);

