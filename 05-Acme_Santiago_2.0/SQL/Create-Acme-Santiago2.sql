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
INSERT INTO `administrator` VALUES (73,0,NULL,'admin@admin.com','Administrator','654565656','http://www.google.es','C/Admin','Acme Antenna',66);
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
INSERT INTO `advertisement` VALUES (120,0,12,'https://k32.kn3.net/taringa/A/5/D/C/F/D/TototomixLol/92B.png','MasterCard',123,5,20,'BBVA','4024007146545768','\0','http://www.google.es','Presentemonos',80),(121,0,15,'https://i.ytimg.com/vi/RXeVgZHEK2Q/maxresdefault.jpg','MasterCard',123,5,20,'BBVA','4024007146545768','\0','http://www.youtube.es','Youtube',84);
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
INSERT INTO `agent` VALUES (118,0,NULL,'manugoteras@gmail.com','Manuel','654565656','http://www.google.es','C/Goteras','Goteras Bermúdez',71),(119,0,NULL,'techopisado@gmail.com','Pablo','654565656','http://www.google.es','C/Pisado','Techo Pisado',72);
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
INSERT INTO `agent_advertisement` VALUES (118,120),(119,121);
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
INSERT INTO `amenity` VALUES (128,0,'Este es el primer amenity','Amenity 1',114),(129,0,'Este es el segundo amenity','Amenity 2',116);
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
INSERT INTO `amenity_pictures` VALUES (128,'http://cdn.luxuo.com/2011/10/Waldorf-Astoria-Salvatore-Ferragamo-amenity-kits.jpg\n				'),(128,'https://s3.amazonaws.com/waterline-prod/uploads/2017/01/02215529/WL3_Amenity_534X300.jpg\n				');
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
INSERT INTO `chirp` VALUES (112,0,'2017-06-01 00:00:00','\0','text1','title1',74),(113,0,'2017-06-01 00:00:00','\0','text2','title2',75);
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
INSERT INTO `comment` VALUES (79,0,'2017-06-01 00:00:00',1,'','text1','title1',NULL,78,74),(81,0,'2017-06-01 00:00:00',2,'\0','text3','title3',80,NULL,74),(83,0,'2017-06-01 00:00:00',2,'\0','text2','title2',NULL,82,75),(85,0,'2017-06-01 00:00:00',2,'\0','text4','title4',84,NULL,75);
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
INSERT INTO `comment_pictures` VALUES (79,'http://www.coordinadora.com/wp-content/uploads/sidebar_usuario-corporativo.png\n				'),(79,'http://www.imagen.com.mx/assets/img/imagen_share.png'),(81,'http://www.coordinadora.com/wp-content/uploads/sidebar_usuario-corporativo.png\n				'),(81,'http://www.imagen.com.mx/assets/img/imagen_share.png'),(83,'http://www.coordinadora.com/wp-content/uploads/sidebar_usuario-corporativo.png\n				'),(83,'http://www.imagen.com.mx/assets/img/imagen_share.png'),(85,'http://www.coordinadora.com/wp-content/uploads/sidebar_usuario-corporativo.png\n				'),(85,'http://www.imagen.com.mx/assets/img/imagen_share.png');
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
INSERT INTO `compostela` VALUES (122,1,'2014-10-09 00:00:00','\0','\0',NULL,74,126),(123,1,'2015-01-02 00:00:00','\0','\0',NULL,75,127),(124,1,'2014-10-09 00:00:00','','\0',NULL,74,126),(125,1,'2015-01-02 00:00:00','','',NULL,75,127);
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
INSERT INTO `configuration` VALUES (130,0);
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
INSERT INTO `configuration_taboowords` VALUES (130,'sex'),(130,'viagra'),(130,'cialis');
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
INSERT INTO `hike` VALUES (80,0,'description1','destinationCity1','EASY',30,'hike1','originCity1',78),(84,0,'description2','destinationCity2','EASY',30,'hike2','originCity2',82);
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
INSERT INTO `hike_pictures` VALUES (80,'http://www.coordinadora.com/wp-content/uploads/sidebar_usuario-corporativo.png\n				'),(80,'http://www.imagen.com.mx/assets/img/imagen_share.png'),(84,'http://www.coordinadora.com/wp-content/uploads/sidebar_usuario-corporativo.png\n				'),(84,'http://www.imagen.com.mx/assets/img/imagen_share.png');
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
INSERT INTO `inn` VALUES (114,0,'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQYMinrf4pQgWsM5Cg8YtBO_Xv55cqY6vnn2hm2zhPwHCTSCj9K','MasterCard',123,5,20,'BBVA','4024007146545768','edferve@gmail.com','inn1','654565656','http://www.google.es',76),(116,0,'https://image.shutterstock.com/image-vector/star-badge-vector-icon-260nw-491672602.jpg','VISA',612,5,21,'Name creditCard1','4150902751004463','edferve@gmail.com','inn2','654565656','http://www.google.es',77);
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
INSERT INTO `inn_address` VALUES (114,'C/Edu'),(114,'5'),(114,'destinationCity1'),(116,'C/Edu'),(116,'5'),(116,'destinationCity1');
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
INSERT INTO `innkeeper` VALUES (76,0,NULL,'edferve@gmail.com','Eduardo','654565656','http://www.google.es','C/Edu','Fernández Velázquez',69),(77,0,NULL,'magarve@gmail.com','María','666666666','http://www.google.es','C/María','García Velázquez',70);
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
INSERT INTO `innkeeper_amenity` VALUES (76,128),(77,129);
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
INSERT INTO `registry` VALUES (115,0,'2012-08-05',80,114,74),(117,0,'2011-08-05',84,116,75);
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
INSERT INTO `route` VALUES (78,0,'description1',300,'route1',74),(82,0,'description2',300,'route2',75),(86,0,'description3',300,'route3',74),(87,0,'description4',300,'route4',74),(88,0,'description5',300,'route5',74),(89,0,'description6',300,'route6',74),(90,0,'description7',300,'route7',74),(91,0,'description8',250,'route8',74),(92,0,'description9',350,'route9',74),(93,0,'description10',300,'route10',74),(94,0,'description1',300,'route11',74),(95,0,'description1',300,'route12',74),(96,0,'description1',300,'route13',74),(97,0,'description1',300,'route14',74),(98,0,'description1',300,'route15',74),(99,0,'description1',300,'route16',74),(100,0,'description1',300,'route17',74),(101,0,'description1',300,'route18',74),(102,0,'description1',300,'route19',74),(103,0,'description1',300,'route20',74),(104,0,'description1',300,'route21',74),(105,0,'description1',300,'route22',74),(106,0,'description1',300,'route23',74),(107,0,'description1',300,'route24',74),(108,0,'description1',300,'route25',74),(109,0,'description1',300,'route26',74),(110,0,'description1',300,'route27',74),(111,0,'description1',300,'route28',74);
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
INSERT INTO `route_pictures` VALUES (78,'http://www.coordinadora.com/wp-content/uploads/sidebar_usuario-corporativo.png\n				'),(78,'http://www.imagen.com.mx/assets/img/imagen_share.png'),(82,'http://www.coordinadora.com/wp-content/uploads/sidebar_usuario-corporativo.png\n				'),(82,'http://www.imagen.com.mx/assets/img/imagen_share.png'),(86,'http://www.coordinadora.com/wp-content/uploads/sidebar_usuario-corporativo.png\n				'),(86,'http://www.imagen.com.mx/assets/img/imagen_share.png'),(87,'http://www.coordinadora.com/wp-content/uploads/sidebar_usuario-corporativo.png\n				'),(87,'http://www.imagen.com.mx/assets/img/imagen_share.png'),(88,'http://www.coordinadora.com/wp-content/uploads/sidebar_usuario-corporativo.png\n				'),(88,'http://www.imagen.com.mx/assets/img/imagen_share.png'),(89,'http://www.coordinadora.com/wp-content/uploads/sidebar_usuario-corporativo.png\n				'),(89,'http://www.imagen.com.mx/assets/img/imagen_share.png'),(90,'http://www.coordinadora.com/wp-content/uploads/sidebar_usuario-corporativo.png\n				'),(90,'http://www.imagen.com.mx/assets/img/imagen_share.png'),(91,'http://www.coordinadora.com/wp-content/uploads/sidebar_usuario-corporativo.png\n				'),(91,'http://www.imagen.com.mx/assets/img/imagen_share.png'),(92,'http://www.coordinadora.com/wp-content/uploads/sidebar_usuario-corporativo.png\n				'),(92,'http://www.imagen.com.mx/assets/img/imagen_share.png'),(93,'http://www.coordinadora.com/wp-content/uploads/sidebar_usuario-corporativo.png\n				'),(93,'http://www.imagen.com.mx/assets/img/imagen_share.png'),(94,'http://www.coordinadora.com/wp-content/uploads/sidebar_usuario-corporativo.png\n				'),(94,'http://www.imagen.com.mx/assets/img/imagen_share.png'),(95,'http://www.coordinadora.com/wp-content/uploads/sidebar_usuario-corporativo.png\n				'),(95,'http://www.imagen.com.mx/assets/img/imagen_share.png'),(96,'http://www.coordinadora.com/wp-content/uploads/sidebar_usuario-corporativo.png\n				'),(96,'http://www.imagen.com.mx/assets/img/imagen_share.png'),(97,'http://www.coordinadora.com/wp-content/uploads/sidebar_usuario-corporativo.png\n				'),(97,'http://www.imagen.com.mx/assets/img/imagen_share.png'),(98,'http://www.coordinadora.com/wp-content/uploads/sidebar_usuario-corporativo.png\n				'),(98,'http://www.imagen.com.mx/assets/img/imagen_share.png'),(99,'http://www.coordinadora.com/wp-content/uploads/sidebar_usuario-corporativo.png\n				'),(99,'http://www.imagen.com.mx/assets/img/imagen_share.png'),(100,'http://www.coordinadora.com/wp-content/uploads/sidebar_usuario-corporativo.png\n				'),(100,'http://www.imagen.com.mx/assets/img/imagen_share.png'),(101,'http://www.coordinadora.com/wp-content/uploads/sidebar_usuario-corporativo.png\n				'),(101,'http://www.imagen.com.mx/assets/img/imagen_share.png'),(102,'http://www.coordinadora.com/wp-content/uploads/sidebar_usuario-corporativo.png\n				'),(102,'http://www.imagen.com.mx/assets/img/imagen_share.png'),(103,'http://www.coordinadora.com/wp-content/uploads/sidebar_usuario-corporativo.png\n				'),(103,'http://www.imagen.com.mx/assets/img/imagen_share.png'),(104,'http://www.coordinadora.com/wp-content/uploads/sidebar_usuario-corporativo.png\n				'),(104,'http://www.imagen.com.mx/assets/img/imagen_share.png'),(105,'http://www.coordinadora.com/wp-content/uploads/sidebar_usuario-corporativo.png\n				'),(105,'http://www.imagen.com.mx/assets/img/imagen_share.png'),(106,'http://www.coordinadora.com/wp-content/uploads/sidebar_usuario-corporativo.png\n				'),(106,'http://www.imagen.com.mx/assets/img/imagen_share.png'),(107,'http://www.coordinadora.com/wp-content/uploads/sidebar_usuario-corporativo.png\n				'),(107,'http://www.imagen.com.mx/assets/img/imagen_share.png'),(108,'http://www.coordinadora.com/wp-content/uploads/sidebar_usuario-corporativo.png\n				'),(108,'http://www.imagen.com.mx/assets/img/imagen_share.png'),(109,'http://www.coordinadora.com/wp-content/uploads/sidebar_usuario-corporativo.png\n				'),(109,'http://www.imagen.com.mx/assets/img/imagen_share.png'),(110,'http://www.coordinadora.com/wp-content/uploads/sidebar_usuario-corporativo.png\n				'),(110,'http://www.imagen.com.mx/assets/img/imagen_share.png'),(111,'http://www.coordinadora.com/wp-content/uploads/sidebar_usuario-corporativo.png\n				'),(111,'http://www.imagen.com.mx/assets/img/imagen_share.png');
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
INSERT INTO `user` VALUES (74,0,NULL,'edferve@gmail.com','Eduardo','+64565656','http://www.repuestosparamotos.co/page/images/user.png','C/Edu','Fernández Velázquez',67),(75,0,NULL,'magarve@gmail.com','María','666666666','http://www.repuestosparamotos.co/page/images/user.png','C/María','García Velázquez',68);
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
INSERT INTO `user_user` VALUES (74,75),(75,74);
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
INSERT INTO `useraccount` VALUES (66,0,'21232f297a57a5a743894a0e4a801fc3','admin'),(67,0,'24c9e15e52afc47c225b757e7bee1f9d','user1'),(68,0,'7e58d63b60197ceb55a1c487989a3720','user2'),(69,0,'86903e666824a841e9e3d50e4b1240bf','innkeeper1'),(70,0,'5b666146423e686ba85a21cbd55f0446','innkeeper2'),(71,0,'83a87fd756ab57199c0bb6d5e11168cb','agent1'),(72,0,'b1a4a6b01cc297d4677c4ca6656e14d7','agent2');
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
INSERT INTO `useraccount_authorities` VALUES (66,'ADMIN'),(67,'USER'),(68,'USER'),(69,'INNKEEPER'),(70,'INNKEEPER'),(71,'AGENT'),(72,'AGENT');
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
INSERT INTO `walk` VALUES (126,0,'Moonwalk',114,78),(127,0,'Sunwalk',116,82);
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
INSERT INTO `walk_daysofeachhike` VALUES (126,'2011-05-20 00:00:00'),(127,'2011-05-20 00:00:00');
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

-- Dump completed on 2018-09-12 18:33:37
