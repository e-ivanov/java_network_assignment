-- Adminer 3.6.2 MySQL dump

SET NAMES utf8;
SET foreign_key_checks = 0;
SET time_zone = 'SYSTEM';
SET sql_mode = 'NO_AUTO_VALUE_ON_ZERO';

CREATE DATABASE `photoshare` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `photoshare`;

DROP TABLE IF EXISTS `Category`;
CREATE TABLE `Category` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `isActive` tinyint(4) DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `Category` (`id`, `name`, `isActive`) VALUES
(1,	'Природа',	1),
(2,	'Спорт',	1),
(3,	'Портрети',	1);

DROP TABLE IF EXISTS `Collection`;
CREATE TABLE `Collection` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `user_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_Collection_User1` (`user_id`),
  CONSTRAINT `fk_Collection_User1` FOREIGN KEY (`user_id`) REFERENCES `User` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `Image`;
CREATE TABLE `Image` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(10) unsigned NOT NULL,
  `fileName` varchar(155) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `category_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_Image_User1` (`user_id`),
  CONSTRAINT `fk_Image_User1` FOREIGN KEY (`user_id`) REFERENCES `User` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `ImageCategory`;
CREATE TABLE `ImageCategory` (
  `category_id` int(10) unsigned NOT NULL,
  `image_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`category_id`,`image_id`),
  KEY `fk_Category_has_Image_Image1` (`image_id`),
  CONSTRAINT `fk_Category_has_Image_Category1` FOREIGN KEY (`category_id`) REFERENCES `Category` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Category_has_Image_Image1` FOREIGN KEY (`image_id`) REFERENCES `Image` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `ImageCollection`;
CREATE TABLE `ImageCollection` (
  `image_id` int(10) unsigned NOT NULL,
  `collection_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`image_id`,`collection_id`),
  KEY `fk_Image_has_ImageCollection_ImageCollection1` (`collection_id`),
  KEY `fk_Image_has_ImageCollection_Image` (`image_id`),
  CONSTRAINT `fk_Image_has_ImageCollection_Image` FOREIGN KEY (`image_id`) REFERENCES `Image` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Image_has_ImageCollection_ImageCollection1` FOREIGN KEY (`collection_id`) REFERENCES `Collection` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `User`;
CREATE TABLE `User` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `firstName` varchar(45) NOT NULL,
  `lastName` varchar(45) NOT NULL,
  `avatar` varchar(255) DEFAULT NULL,
  `username` varchar(60) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `UserRoles`;
CREATE TABLE `UserRoles` (
  `username` varchar(60) NOT NULL,
  `rolename` varchar(20) NOT NULL,
  KEY `username` (`username`),
  CONSTRAINT `UserRoles_ibfk_1` FOREIGN KEY (`username`) REFERENCES `User` (`username`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- 2015-07-09 08:34:26
