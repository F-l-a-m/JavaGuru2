SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';


CREATE SCHEMA IF NOT EXISTS `chat` DEFAULT CHARACTER SET utf8 ;
USE `chat` ;

DROP TABLE IF EXISTS `chat`.`user`;

CREATE TABLE IF NOT EXISTS `chat`.`user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `login` VARCHAR(45) NULL,
  `password` VARCHAR(45) NULL,
  `nickname` VARCHAR(45) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `user_id_UNIQUE` (`id` ASC))
ENGINE = InnoDB;


DROP TABLE IF EXISTS `chat`.`chat_room`;

CREATE TABLE IF NOT EXISTS `chat`.`chat_room` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `room_id_UNIQUE` (`id` ASC))
ENGINE = InnoDB;


DROP TABLE IF EXISTS `chat`.`message`;

CREATE TABLE IF NOT EXISTS `chat`.`message` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `timestamp` VARCHAR(45) NULL,
  `message_body` VARCHAR(45) NULL,
  `user_id` INT NOT NULL,
  `room_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `message_id_UNIQUE` (`id` ASC),
  INDEX `fk_message_1_idx` (`room_id` ASC),
  CONSTRAINT `fk_message`
    FOREIGN KEY (`room_id`)
    REFERENCES `chat`.`chat_room` (`id`))
ENGINE = InnoDB;


DROP TABLE IF EXISTS `chat`.`user_in_room`;

CREATE TABLE IF NOT EXISTS `chat`.`user_in_room` (
  `user_id` INT NOT NULL,
  `room_id` INT NOT NULL,
  PRIMARY KEY (`user_id`, `room_id`),
  INDEX `fk_user_has_chat_room_1_idx` (`user_id` ASC),
  CONSTRAINT `fk_user`
    FOREIGN KEY (`user_id`)
    REFERENCES `chat`.`user` (`id`),
  CONSTRAINT `fk_chat_room`
    FOREIGN KEY (`room_id`)
    REFERENCES `chat`.`chat_room` (`id`))
ENGINE = InnoDB;

USE `chat` ;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
