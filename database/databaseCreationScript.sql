-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema chat
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema chat
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `chat` DEFAULT CHARACTER SET utf8 ;
USE `chat` ;

-- -----------------------------------------------------
-- Table `chat`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `chat`.`user` (
  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `login` VARCHAR(16) NULL,
  `password` VARCHAR(128) NULL,
  `nickname` VARCHAR(16) NOT NULL,
  `creationTime` DATETIME NOT NULL,
  `isActive` TINYINT(1) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `user_id_UNIQUE` (`id` ASC),
  UNIQUE INDEX `login_UNIQUE` (`login` ASC),
  UNIQUE INDEX `nickname_UNIQUE` (`nickname` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `chat`.`chat_room`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `chat`.`chat_room` (
  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `creatorNickname` VARCHAR(16) NOT NULL,
  `creationTime` DATETIME NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `room_id_UNIQUE` (`id` ASC),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `chat`.`message`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `chat`.`message` (
  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `room_id` BIGINT UNSIGNED NOT NULL,
  `creationTime` DATETIME NOT NULL,
  `user_nickname` VARCHAR(16) NOT NULL,
  `message_body` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `message_id_UNIQUE` (`id` ASC),
  INDEX `fk_message_1_idx` (`room_id` ASC),
  CONSTRAINT `fk_message`
    FOREIGN KEY (`room_id`)
    REFERENCES `chat`.`chat_room` (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `chat`.`user_in_room`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `chat`.`user_in_room` (
  `user_id` BIGINT UNSIGNED NOT NULL,
  `room_id` BIGINT UNSIGNED NOT NULL,
  PRIMARY KEY (`user_id`, `room_id`),
  INDEX `fk_user_has_chat_room_1_idx` (`user_id` ASC),
  CONSTRAINT `fk_user`
    FOREIGN KEY (`user_id`)
    REFERENCES `chat`.`user` (`id`),
  CONSTRAINT `fk_chat_room`
    FOREIGN KEY (`room_id`)
    REFERENCES `chat`.`chat_room` (`id`))
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
