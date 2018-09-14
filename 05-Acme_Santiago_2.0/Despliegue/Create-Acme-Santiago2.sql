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
-- Table structure for table `advertisement`
--

DROP TABLE IF EXISTS `advertisement`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `advertisement` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `activeDays` int(11) DEFAULT NULL,
  `banner` varchar(255) DEFAULT NULL,
  `brandName` varchar(255) DEFAULT NULL,
  `cvv` int(11) NOT NULL,
  `expirationMonth` int(11) NOT NULL,
  `expirationYear` int(11) NOT NULL,
  `holderName` varchar(255) DEFAULT NULL,
  `number` varchar(255) DEFAULT NULL,
  `taboo` bit(1) NOT NULL,
  `targetPage` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `hike_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `UK_4va7qff9brrxpx6n5s92ok65w` (`hike_id`,`taboo`),
  CONSTRAINT `FK_7r0l4333lkuinp6tt864vfjmc` FOREIGN KEY (`hike_id`) REFERENCES `hike` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `advertisement`
--

LOCK TABLES `advertisement` WRITE;
/*!40000 ALTER TABLE `advertisement` DISABLE KEYS */;
/*!40000 ALTER TABLE `advertisement` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `agent`
--

DROP TABLE IF EXISTS `agent`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `agent` (
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
  UNIQUE KEY `UK_5cg6nedtnilfs6spfq209syse` (`userAccount_id`),
  KEY `AgentUK_cgls5lrufx91ufsyh467spwa3` (`userAccount_id`),
  CONSTRAINT `FK_5cg6nedtnilfs6spfq209syse` FOREIGN KEY (`userAccount_id`) REFERENCES `useraccount` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `agent`
--

LOCK TABLES `agent` WRITE;
/*!40000 ALTER TABLE `agent` DISABLE KEYS */;
/*!40000 ALTER TABLE `agent` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `agent_advertisement`
--

DROP TABLE IF EXISTS `agent_advertisement`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `agent_advertisement` (
  `Agent_id` int(11) NOT NULL,
  `advertisements_id` int(11) NOT NULL,
  UNIQUE KEY `UK_bl5b0f680yxis3lmllhnxlvhy` (`advertisements_id`),
  KEY `FK_617pj66qan7hxumwp351e38ro` (`Agent_id`),
  CONSTRAINT `FK_617pj66qan7hxumwp351e38ro` FOREIGN KEY (`Agent_id`) REFERENCES `agent` (`id`),
  CONSTRAINT `FK_bl5b0f680yxis3lmllhnxlvhy` FOREIGN KEY (`advertisements_id`) REFERENCES `advertisement` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `agent_advertisement`
--

LOCK TABLES `agent_advertisement` WRITE;
/*!40000 ALTER TABLE `agent_advertisement` DISABLE KEYS */;
/*!40000 ALTER TABLE `agent_advertisement` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `amenity`
--

DROP TABLE IF EXISTS `amenity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `amenity` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `inn_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `UK_ftssldd3um5h16w3lwtmediwm` (`inn_id`),
  CONSTRAINT `FK_ftssldd3um5h16w3lwtmediwm` FOREIGN KEY (`inn_id`) REFERENCES `inn` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `amenity`
--

LOCK TABLES `amenity` WRITE;
/*!40000 ALTER TABLE `amenity` DISABLE KEYS */;
/*!40000 ALTER TABLE `amenity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `amenity_pictures`
--

DROP TABLE IF EXISTS `amenity_pictures`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `amenity_pictures` (
  `Amenity_id` int(11) NOT NULL,
  `pictures` varchar(255) DEFAULT NULL,
  KEY `FK_1098jnr7nyv8k3x09sy2k51xp` (`Amenity_id`),
  CONSTRAINT `FK_1098jnr7nyv8k3x09sy2k51xp` FOREIGN KEY (`Amenity_id`) REFERENCES `amenity` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `amenity_pictures`
--

LOCK TABLES `amenity_pictures` WRITE;
/*!40000 ALTER TABLE `amenity_pictures` DISABLE KEYS */;
/*!40000 ALTER TABLE `amenity_pictures` ENABLE KEYS */;
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
  `moment` datetime DEFAULT NULL,
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
  `moment` datetime DEFAULT NULL,
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
-- Table structure for table `compostela`
--

DROP TABLE IF EXISTS `compostela`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `compostela` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `date` datetime DEFAULT NULL,
  `decision` bit(1) NOT NULL,
  `finallyDecision` bit(1) NOT NULL,
  `justification` varchar(255) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `walk_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `UK_hhy5n5ri6lqnxertxxkgtuyes` (`finallyDecision`,`walk_id`),
  KEY `FK_mnploihmlxb08o8urixs6wcrc` (`user_id`),
  KEY `FK_6fd0t1ewa8el3bj8c3wxtaky2` (`walk_id`),
  CONSTRAINT `FK_6fd0t1ewa8el3bj8c3wxtaky2` FOREIGN KEY (`walk_id`) REFERENCES `walk` (`id`),
  CONSTRAINT `FK_mnploihmlxb08o8urixs6wcrc` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `compostela`
--

LOCK TABLES `compostela` WRITE;
/*!40000 ALTER TABLE `compostela` DISABLE KEYS */;
/*!40000 ALTER TABLE `compostela` ENABLE KEYS */;
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
  KEY `UK_325em27ujikxiiuwosgr4v6lc` (`route_id`,`name`,`destinationCity`),
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
INSERT INTO `inn` VALUES (16,0,'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQYMinrf4pQgWsM5Cg8YtBO_Xv55cqY6vnn2hm2zhPwHCTSCj9K','MasterCard',123,5,20,'BBVA','4024007146545768','edferve@gmail.com','inn1','654565656','http://www.google.es',14),(17,0,'https://image.shutterstock.com/image-vector/star-badge-vector-icon-260nw-491672602.jpg','VISA',612,5,21,'Name creditCard1','4150902751004463','edferve@gmail.com','inn2','654565656','http://www.google.es',15);
/*!40000 ALTER TABLE `inn` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `inn_address`
--

DROP TABLE IF EXISTS `inn_address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `inn_address` (
  `Inn_id` int(11) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  KEY `FK_2ntgjsvqsuw2ebu8mvpv3pf37` (`Inn_id`),
  CONSTRAINT `FK_2ntgjsvqsuw2ebu8mvpv3pf37` FOREIGN KEY (`Inn_id`) REFERENCES `inn` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inn_address`
--

LOCK TABLES `inn_address` WRITE;
/*!40000 ALTER TABLE `inn_address` DISABLE KEYS */;
INSERT INTO `inn_address` VALUES (16,'C/Edu'),(16,'5'),(16,'destinationCity1'),(17,'C/Edu'),(17,'5'),(17,'destinationCity1');
/*!40000 ALTER TABLE `inn_address` ENABLE KEYS */;
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
-- Table structure for table `innkeeper_amenity`
--

DROP TABLE IF EXISTS `innkeeper_amenity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `innkeeper_amenity` (
  `Innkeeper_id` int(11) NOT NULL,
  `amenities_id` int(11) NOT NULL,
  UNIQUE KEY `UK_6yayscw1o0bll7ogcuc7ojp52` (`amenities_id`),
  KEY `FK_jhf651hx6lnvxi3w7kk1pwo16` (`Innkeeper_id`),
  CONSTRAINT `FK_jhf651hx6lnvxi3w7kk1pwo16` FOREIGN KEY (`Innkeeper_id`) REFERENCES `innkeeper` (`id`),
  CONSTRAINT `FK_6yayscw1o0bll7ogcuc7ojp52` FOREIGN KEY (`amenities_id`) REFERENCES `amenity` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `innkeeper_amenity`
--

LOCK TABLES `innkeeper_amenity` WRITE;
/*!40000 ALTER TABLE `innkeeper_amenity` DISABLE KEYS */;
/*!40000 ALTER TABLE `innkeeper_amenity` ENABLE KEYS */;
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
  `hike_id` int(11) NOT NULL,
  `inn_id` int(11) DEFAULT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `UK_ggtpvvqk8ho742d801025dswy` (`date`,`inn_id`,`user_id`,`hike_id`),
  KEY `FK_sksa7jasvgtiigmqd18yosbfc` (`hike_id`),
  KEY `FK_hlj9erpeb8s8igy96o169wwtf` (`inn_id`),
  KEY `FK_p21f1sti4q3kp67l1a4crm80u` (`user_id`),
  CONSTRAINT `FK_p21f1sti4q3kp67l1a4crm80u` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FK_hlj9erpeb8s8igy96o169wwtf` FOREIGN KEY (`inn_id`) REFERENCES `inn` (`id`),
  CONSTRAINT `FK_sksa7jasvgtiigmqd18yosbfc` FOREIGN KEY (`hike_id`) REFERENCES `hike` (`id`)
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
  KEY `UK_916nd0a9clx6oabkvqohlhi63` (`name`,`description`,`user_id`),
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

--
-- Table structure for table `walk`
--

DROP TABLE IF EXISTS `walk`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `walk` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `title` varchar(255) DEFAULT NULL,
  `inn_id` int(11) DEFAULT NULL,
  `route_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `UK_jju0k5lcrumc23ov3qsacc6i7` (`route_id`),
  KEY `FK_fsi51ckv8lii97bnjjahd2yof` (`inn_id`),
  CONSTRAINT `FK_jju0k5lcrumc23ov3qsacc6i7` FOREIGN KEY (`route_id`) REFERENCES `route` (`id`),
  CONSTRAINT `FK_fsi51ckv8lii97bnjjahd2yof` FOREIGN KEY (`inn_id`) REFERENCES `inn` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `walk`
--

LOCK TABLES `walk` WRITE;
/*!40000 ALTER TABLE `walk` DISABLE KEYS */;
/*!40000 ALTER TABLE `walk` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `walk_daysofeachhike`
--

DROP TABLE IF EXISTS `walk_daysofeachhike`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `walk_daysofeachhike` (
  `Walk_id` int(11) NOT NULL,
  `daysOfEachHike` datetime DEFAULT NULL,
  KEY `FK_nq4lupi6kakvnijtn2l3ndihq` (`Walk_id`),
  CONSTRAINT `FK_nq4lupi6kakvnijtn2l3ndihq` FOREIGN KEY (`Walk_id`) REFERENCES `walk` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `walk_daysofeachhike`
--

LOCK TABLES `walk_daysofeachhike` WRITE;
/*!40000 ALTER TABLE `walk_daysofeachhike` DISABLE KEYS */;
/*!40000 ALTER TABLE `walk_daysofeachhike` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-09-14 20:40:26

commit;