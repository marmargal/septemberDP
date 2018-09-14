start transaction;

create database `Acme-Santiago`;

use `Acme-Santiago`;

create user 'acme-user'@'%' identified by password '*4F10007AADA9EE3DBB2CC36575DFC6F4FDE27577';
create user 'acme-manager'@'%' identified by password '*FDB8CD304EB2317D10C95D797A4BD7492560F55F';

grant select, insert, update, delete 
	on `Acme-Santiago`.* to 'acme-user'@'%';

grant select, insert, update, delete, create, drop, references, index, alter, 
        create temporary tables, lock tables, create view, create routine, 
        alter routine, execute, trigger, show view
    on `Acme-Santiago`.* to 'acme-manager'@'%';


-- MySQL dump 10.13  Distrib 5.5.29, for Win64 (x86)
--
-- Host: localhost    Database: Acme-Santiago
-- ------------------------------------------------------
-- Server version	5.5.29

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `administrator`
--

DROP TABLE IF EXISTS `administrator`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `administrator` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phoneNumber` varchar(255) DEFAULT NULL,
  `pictures` varchar(255) DEFAULT NULL,
  `postalAddress` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `userAccount_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_idt4b4u259p6vs4pyr9lax4eg` (`userAccount_id`),
  KEY `AdministratorUK_cgls5lrufx91ufsyh467spwa3` (`userAccount_id`),
  CONSTRAINT `FK_idt4b4u259p6vs4pyr9lax4eg` FOREIGN KEY (`userAccount_id`) REFERENCES `useraccount` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `administrator`
--

LOCK TABLES `administrator` WRITE;
/*!40000 ALTER TABLE `administrator` DISABLE KEYS */;
INSERT INTO `administrator` VALUES (13,0,'C/Admin','admin@admin.com','Administrator','654565656','http://www.google.es','1234','Acme Antenna',10);
/*!40000 ALTER TABLE `administrator` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chirp`
--

DROP TABLE IF EXISTS `chirp`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `chirp` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `moment` date DEFAULT NULL,
  `taboo` bit(1) DEFAULT NULL,
  `text` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `UK_eljsrqwp7xayilh8vqfqcw97q` (`user_id`,`taboo`),
  CONSTRAINT `FK_t10lk4j2g8uw7k7et58ytrp70` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chirp`
--

LOCK TABLES `chirp` WRITE;
/*!40000 ALTER TABLE `chirp` DISABLE KEYS */;
/*!40000 ALTER TABLE `chirp` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `comment` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `moment` date DEFAULT NULL,
  `rating` int(11) DEFAULT NULL,
  `taboo` bit(1) NOT NULL,
  `text` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `hike_id` int(11) DEFAULT NULL,
  `route_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `UK_bg3qhof1f0ox2t2m6rrx7qdif` (`taboo`),
  KEY `FK_ife7actoer5ky0yno2i6q80sd` (`hike_id`),
  KEY `FK_nm3gg9g7209rqs070e7tu7vxb` (`route_id`),
  KEY `FK_jhvt6d9ap8gxv67ftrmshdfhj` (`user_id`),
  CONSTRAINT `FK_jhvt6d9ap8gxv67ftrmshdfhj` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FK_ife7actoer5ky0yno2i6q80sd` FOREIGN KEY (`hike_id`) REFERENCES `hike` (`id`),
  CONSTRAINT `FK_nm3gg9g7209rqs070e7tu7vxb` FOREIGN KEY (`route_id`) REFERENCES `route` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comment_pictures`
--

DROP TABLE IF EXISTS `comment_pictures`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `comment_pictures` (
  `Comment_id` int(11) NOT NULL,
  `pictures` varchar(255) DEFAULT NULL,
  KEY `FK_jdjncr14fn9m3d20lwescoerc` (`Comment_id`),
  CONSTRAINT `FK_jdjncr14fn9m3d20lwescoerc` FOREIGN KEY (`Comment_id`) REFERENCES `comment` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment_pictures`
--

LOCK TABLES `comment_pictures` WRITE;
/*!40000 ALTER TABLE `comment_pictures` DISABLE KEYS */;
/*!40000 ALTER TABLE `comment_pictures` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `configuration`
--

DROP TABLE IF EXISTS `configuration`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `configuration` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `configuration`
--

LOCK TABLES `configuration` WRITE;
/*!40000 ALTER TABLE `configuration` DISABLE KEYS */;
INSERT INTO `configuration` VALUES (18,0);
/*!40000 ALTER TABLE `configuration` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `configuration_taboowords`
--

DROP TABLE IF EXISTS `configuration_taboowords`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `configuration_taboowords` (
  `Configuration_id` int(11) NOT NULL,
  `tabooWords` varchar(255) DEFAULT NULL,
  KEY `FK_jflyxemijdnhx9v7hc8aodm6g` (`Configuration_id`),
  CONSTRAINT `FK_jflyxemijdnhx9v7hc8aodm6g` FOREIGN KEY (`Configuration_id`) REFERENCES `configuration` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `configuration_taboowords`
--

LOCK TABLES `configuration_taboowords` WRITE;
/*!40000 ALTER TABLE `configuration_taboowords` DISABLE KEYS */;
INSERT INTO `configuration_taboowords` VALUES (18,'sex'),(18,'viagra'),(18,'cialis');
/*!40000 ALTER TABLE `configuration_taboowords` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequences`
--

DROP TABLE IF EXISTS `hibernate_sequences`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hibernate_sequences` (
  `sequence_name` varchar(255) DEFAULT NULL,
  `sequence_next_hi_value` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequences`
--

LOCK TABLES `hibernate_sequences` WRITE;
/*!40000 ALTER TABLE `hibernate_sequences` DISABLE KEYS */;
INSERT INTO `hibernate_sequences` VALUES ('DomainEntity',1);
/*!40000 ALTER TABLE `hibernate_sequences` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hike`
--

DROP TABLE IF EXISTS `hike`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hike` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `destinationCity` varchar(255) DEFAULT NULL,
  `dificultLevel` varchar(255) DEFAULT NULL,
  `length` double DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `originCity` varchar(255) DEFAULT NULL,
  `route_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `UK_d8wfys5f2ew82wxix2yb7h25o` (`route_id`,`name`),
  CONSTRAINT `FK_j44cid4t625b77xbicl6bawiy` FOREIGN KEY (`route_id`) REFERENCES `route` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hike`
--

LOCK TABLES `hike` WRITE;
/*!40000 ALTER TABLE `hike` DISABLE KEYS */;
/*!40000 ALTER TABLE `hike` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hike_pictures`
--

DROP TABLE IF EXISTS `hike_pictures`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hike_pictures` (
  `Hike_id` int(11) NOT NULL,
  `pictures` varchar(255) DEFAULT NULL,
  KEY `FK_h8o1xttufel3crqm4s2ciq0g5` (`Hike_id`),
  CONSTRAINT `FK_h8o1xttufel3crqm4s2ciq0g5` FOREIGN KEY (`Hike_id`) REFERENCES `hike` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hike_pictures`
--

LOCK TABLES `hike_pictures` WRITE;
/*!40000 ALTER TABLE `hike_pictures` DISABLE KEYS */;
/*!40000 ALTER TABLE `hike_pictures` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `inn`
--

DROP TABLE IF EXISTS `inn`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `inn` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `badge` varchar(255) DEFAULT NULL,
  `brandName` varchar(255) DEFAULT NULL,
  `cvv` int(11) NOT NULL,
  `expirationMonth` int(11) NOT NULL,
  `expirationYear` int(11) NOT NULL,
  `holderName` varchar(255) DEFAULT NULL,
  `number` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phoneNumber` varchar(255) DEFAULT NULL,
  `webSite` varchar(255) DEFAULT NULL,
  `innkeeper_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `UK_t2wflabn4wnkn5j8chjlaxcbn` (`expirationMonth`,`expirationYear`),
  KEY `FK_e9j3fc37tidd7e26c1glutynn` (`innkeeper_id`),
  CONSTRAINT `FK_e9j3fc37tidd7e26c1glutynn` FOREIGN KEY (`innkeeper_id`) REFERENCES `innkeeper` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inn`
--

LOCK TABLES `inn` WRITE;
/*!40000 ALTER TABLE `inn` DISABLE KEYS */;
INSERT INTO `inn` VALUES (16,0,'C/Edu','https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQYMinrf4pQgWsM5Cg8YtBO_Xv55cqY6vnn2hm2zhPwHCTSCj9K','MasterCard',123,5,20,'BBVA','4024007146545768','edferve@gmail.com','inn1','654565656','http://www.google.es',14),(17,0,'C/Edu','https://image.shutterstock.com/image-vector/star-badge-vector-icon-260nw-491672602.jpg','VISA',612,5,21,'Name creditCard1','4150902751004463','edferve@gmail.com','inn2','654565656','http://www.google.es',15);
/*!40000 ALTER TABLE `inn` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `innkeeper`
--

DROP TABLE IF EXISTS `innkeeper`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `innkeeper` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phoneNumber` varchar(255) DEFAULT NULL,
  `pictures` varchar(255) DEFAULT NULL,
  `postalAddress` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `userAccount_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_37y8ilwrhh7urqjqmq8olpabv` (`userAccount_id`),
  KEY `InnkeeperUK_cgls5lrufx91ufsyh467spwa3` (`userAccount_id`),
  CONSTRAINT `FK_37y8ilwrhh7urqjqmq8olpabv` FOREIGN KEY (`userAccount_id`) REFERENCES `useraccount` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `innkeeper`
--

LOCK TABLES `innkeeper` WRITE;
/*!40000 ALTER TABLE `innkeeper` DISABLE KEYS */;
INSERT INTO `innkeeper` VALUES (14,0,'C/Edu','edferve@gmail.com','Eduardo','654565656','http://www.google.es','1234','Fernández Velázquez',11),(15,0,'C/María','magarve@gmail.com','María','666666666','http://www.google.es','1234','García Velázquez',12);
/*!40000 ALTER TABLE `innkeeper` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `registry`
--

DROP TABLE IF EXISTS `registry`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `registry` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `date` date DEFAULT NULL,
  `inn_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_hlj9erpeb8s8igy96o169wwtf` (`inn_id`),
  CONSTRAINT `FK_hlj9erpeb8s8igy96o169wwtf` FOREIGN KEY (`inn_id`) REFERENCES `inn` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `registry`
--

LOCK TABLES `registry` WRITE;
/*!40000 ALTER TABLE `registry` DISABLE KEYS */;
/*!40000 ALTER TABLE `registry` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `route`
--

DROP TABLE IF EXISTS `route`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `route` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `length` double DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `UK_mrupw6l6pscn0dgytxo6r3f4s` (`name`,`description`),
  KEY `FK_kb9ojrd6392k3n42thv7xsu9c` (`user_id`),
  CONSTRAINT `FK_kb9ojrd6392k3n42thv7xsu9c` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `route`
--

LOCK TABLES `route` WRITE;
/*!40000 ALTER TABLE `route` DISABLE KEYS */;
/*!40000 ALTER TABLE `route` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `route_pictures`
--

DROP TABLE IF EXISTS `route_pictures`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `route_pictures` (
  `Route_id` int(11) NOT NULL,
  `pictures` varchar(255) DEFAULT NULL,
  KEY `FK_g7b1irg767rrocydhflogyetg` (`Route_id`),
  CONSTRAINT `FK_g7b1irg767rrocydhflogyetg` FOREIGN KEY (`Route_id`) REFERENCES `route` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `route_pictures`
--

LOCK TABLES `route_pictures` WRITE;
/*!40000 ALTER TABLE `route_pictures` DISABLE KEYS */;
/*!40000 ALTER TABLE `route_pictures` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phoneNumber` varchar(255) DEFAULT NULL,
  `pictures` varchar(255) DEFAULT NULL,
  `postalAddress` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `userAccount_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_o6s94d43co03sx067ili5760c` (`userAccount_id`),
  KEY `UserUK_cgls5lrufx91ufsyh467spwa3` (`userAccount_id`),
  CONSTRAINT `FK_o6s94d43co03sx067ili5760c` FOREIGN KEY (`userAccount_id`) REFERENCES `useraccount` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_user`
--

DROP TABLE IF EXISTS `user_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_user` (
  `followers_id` int(11) NOT NULL,
  `following_id` int(11) NOT NULL,
  KEY `FK_c1h4hh6d78lf7t6jkqn3yoi4l` (`following_id`),
  KEY `FK_ipxcfus1p41qgn9xbfhg2aa0r` (`followers_id`),
  CONSTRAINT `FK_ipxcfus1p41qgn9xbfhg2aa0r` FOREIGN KEY (`followers_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FK_c1h4hh6d78lf7t6jkqn3yoi4l` FOREIGN KEY (`following_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_user`
--

LOCK TABLES `user_user` WRITE;
/*!40000 ALTER TABLE `user_user` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `useraccount`
--

DROP TABLE IF EXISTS `useraccount`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `useraccount` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_csivo9yqa08nrbkog71ycilh5` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `useraccount`
--

LOCK TABLES `useraccount` WRITE;
/*!40000 ALTER TABLE `useraccount` DISABLE KEYS */;
INSERT INTO `useraccount` VALUES (10,0,'21232f297a57a5a743894a0e4a801fc3','admin'),(11,0,'86903e666824a841e9e3d50e4b1240bf','innkeeper1'),(12,0,'5b666146423e686ba85a21cbd55f0446','innkeeper2');
/*!40000 ALTER TABLE `useraccount` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `useraccount_authorities`
--

DROP TABLE IF EXISTS `useraccount_authorities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `useraccount_authorities` (
  `UserAccount_id` int(11) NOT NULL,
  `authority` varchar(255) DEFAULT NULL,
  KEY `FK_b63ua47r0u1m7ccc9lte2ui4r` (`UserAccount_id`),
  CONSTRAINT `FK_b63ua47r0u1m7ccc9lte2ui4r` FOREIGN KEY (`UserAccount_id`) REFERENCES `useraccount` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `useraccount_authorities`
--

LOCK TABLES `useraccount_authorities` WRITE;
/*!40000 ALTER TABLE `useraccount_authorities` DISABLE KEYS */;
INSERT INTO `useraccount_authorities` VALUES (10,'ADMIN'),(11,'INNKEEPER'),(12,'INNKEEPER');
/*!40000 ALTER TABLE `useraccount_authorities` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-09-14 20:12:44

commit;