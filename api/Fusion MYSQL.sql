CREATE DATABASE  IF NOT EXISTS `beertech` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `beertech`;


LOCK TABLES `beertech.roles` WRITE;
INSERT INTO `beertech.roles` VALUES (1,'ROLE_USER'),(2,'ROLE_MODERATOR');
UNLOCK TABLES;