start transaction;

create database `Acme-Inmigrant`;

use `Acme-Inmigrant`;

create user 'acme-user'@'%' identified by password '*4F10007AADA9EE3DBB2CC36575DFC6F4FDE27577';
create user 'acme-manager'@'%' identified by password '*FDB8CD304EB2317D10C95D797A4BD7492560F55F';

grant select, insert, update, delete 
	on `Acme-Inmigrant`.* to 'acme-user'@'%';

grant select, insert, update, delete, create, drop, references, index, alter, 
        create temporary tables, lock tables, create view, create routine, 
        alter routine, execute, trigger, show view
    on `Acme-Inmigrant`.* to 'acme-manager'@'%';

-- MySQL dump 10.13  Distrib 5.5.29, for Win64 (x86)
--
-- Host: localhost    Database: Acme-Inmigrant
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
INSERT INTO `administrator` VALUES (9,0,'C/Admin','admin@admin.com','Administrator','+123456789','Acme Immigrant',8);
/*!40000 ALTER TABLE `administrator` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `answer`
--

DROP TABLE IF EXISTS `answer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `answer` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `moment` date DEFAULT NULL,
  `reply` varchar(255) DEFAULT NULL,
  `immigrant_id` int(11) DEFAULT NULL,
  `question_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_10g8xii7lw9kq0kcsobgmtw72` (`question_id`),
  KEY `FK_dyc3jxhu6avlksoymqox66v6n` (`immigrant_id`),
  CONSTRAINT `FK_10g8xii7lw9kq0kcsobgmtw72` FOREIGN KEY (`question_id`) REFERENCES `question` (`id`),
  CONSTRAINT `FK_dyc3jxhu6avlksoymqox66v6n` FOREIGN KEY (`immigrant_id`) REFERENCES `immigrant` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `answer`
--

LOCK TABLES `answer` WRITE;
/*!40000 ALTER TABLE `answer` DISABLE KEYS */;
/*!40000 ALTER TABLE `answer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `application`
--

DROP TABLE IF EXISTS `application`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `application` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `closed` bit(1) NOT NULL,
  `closedMoment` date DEFAULT NULL,
  `brandName` varchar(255) DEFAULT NULL,
  `cvv` int(11) DEFAULT NULL,
  `expirationMonth` int(11) DEFAULT NULL,
  `expirationYear` int(11) DEFAULT NULL,
  `holderName` varchar(255) DEFAULT NULL,
  `number` varchar(255) DEFAULT NULL,
  `openedMoment` date DEFAULT NULL,
  `statistics` double DEFAULT NULL,
  `ticker` varchar(255) DEFAULT NULL,
  `application_id` int(11) DEFAULT NULL,
  `immigrant_id` int(11) NOT NULL,
  `officer_id` int(11) DEFAULT NULL,
  `personalSection_id` int(11) NOT NULL,
  `visa_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_ljcrqvs9rm9fr4c9122g1xdwy` (`personalSection_id`),
  UNIQUE KEY `UK_lwjqty0esse0b2ud5s4d6itxb` (`ticker`),
  KEY `UK_p4kwbddd8xjd02nhgipowqjgt` (`immigrant_id`,`officer_id`,`visa_id`,`personalSection_id`),
  KEY `FK_63pa3foee0npeur7gsxunef2s` (`application_id`),
  KEY `FK_k8kn42d2a9geayum9vvcqnhpg` (`officer_id`),
  KEY `FK_87kxbeua8uijovhovrose2l98` (`visa_id`),
  CONSTRAINT `FK_87kxbeua8uijovhovrose2l98` FOREIGN KEY (`visa_id`) REFERENCES `visa` (`id`),
  CONSTRAINT `FK_63pa3foee0npeur7gsxunef2s` FOREIGN KEY (`application_id`) REFERENCES `application` (`id`),
  CONSTRAINT `FK_gn7hg7d529v5m51e0n2vf9mfi` FOREIGN KEY (`immigrant_id`) REFERENCES `immigrant` (`id`),
  CONSTRAINT `FK_k8kn42d2a9geayum9vvcqnhpg` FOREIGN KEY (`officer_id`) REFERENCES `officer` (`id`),
  CONSTRAINT `FK_ljcrqvs9rm9fr4c9122g1xdwy` FOREIGN KEY (`personalSection_id`) REFERENCES `personalsection` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `application`
--

LOCK TABLES `application` WRITE;
/*!40000 ALTER TABLE `application` DISABLE KEYS */;
/*!40000 ALTER TABLE `application` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `application_contactsection`
--

DROP TABLE IF EXISTS `application_contactsection`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `application_contactsection` (
  `Application_id` int(11) NOT NULL,
  `contactSection_id` int(11) NOT NULL,
  UNIQUE KEY `UK_8n04rbxtyjxxotvcjhwuusdr4` (`contactSection_id`),
  KEY `FK_gakl1dvvw1xw8h5mv52n5y1j5` (`Application_id`),
  CONSTRAINT `FK_gakl1dvvw1xw8h5mv52n5y1j5` FOREIGN KEY (`Application_id`) REFERENCES `application` (`id`),
  CONSTRAINT `FK_8n04rbxtyjxxotvcjhwuusdr4` FOREIGN KEY (`contactSection_id`) REFERENCES `contactsection` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `application_contactsection`
--

LOCK TABLES `application_contactsection` WRITE;
/*!40000 ALTER TABLE `application_contactsection` DISABLE KEYS */;
/*!40000 ALTER TABLE `application_contactsection` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `application_educationsection`
--

DROP TABLE IF EXISTS `application_educationsection`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `application_educationsection` (
  `Application_id` int(11) NOT NULL,
  `educationSection_id` int(11) NOT NULL,
  UNIQUE KEY `UK_qfwta0ykujfqrkw2ks25yck6t` (`educationSection_id`),
  KEY `FK_bftma5q3a28uek8tmvkxsacrs` (`Application_id`),
  CONSTRAINT `FK_bftma5q3a28uek8tmvkxsacrs` FOREIGN KEY (`Application_id`) REFERENCES `application` (`id`),
  CONSTRAINT `FK_qfwta0ykujfqrkw2ks25yck6t` FOREIGN KEY (`educationSection_id`) REFERENCES `educationsection` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `application_educationsection`
--

LOCK TABLES `application_educationsection` WRITE;
/*!40000 ALTER TABLE `application_educationsection` DISABLE KEYS */;
/*!40000 ALTER TABLE `application_educationsection` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `application_socialsection`
--

DROP TABLE IF EXISTS `application_socialsection`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `application_socialsection` (
  `Application_id` int(11) NOT NULL,
  `socialSection_id` int(11) NOT NULL,
  UNIQUE KEY `UK_67ls9efosgyn51cjfdh3vvy3x` (`socialSection_id`),
  KEY `FK_irpfx185v586w44l7mhdi4klc` (`Application_id`),
  CONSTRAINT `FK_irpfx185v586w44l7mhdi4klc` FOREIGN KEY (`Application_id`) REFERENCES `application` (`id`),
  CONSTRAINT `FK_67ls9efosgyn51cjfdh3vvy3x` FOREIGN KEY (`socialSection_id`) REFERENCES `socialsection` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `application_socialsection`
--

LOCK TABLES `application_socialsection` WRITE;
/*!40000 ALTER TABLE `application_socialsection` DISABLE KEYS */;
/*!40000 ALTER TABLE `application_socialsection` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `application_worksection`
--

DROP TABLE IF EXISTS `application_worksection`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `application_worksection` (
  `Application_id` int(11) NOT NULL,
  `workSection_id` int(11) NOT NULL,
  UNIQUE KEY `UK_tnto1uf2s9y8glrcos5f137lw` (`workSection_id`),
  KEY `FK_31re5rncibc390scjhq9pc6r1` (`Application_id`),
  CONSTRAINT `FK_31re5rncibc390scjhq9pc6r1` FOREIGN KEY (`Application_id`) REFERENCES `application` (`id`),
  CONSTRAINT `FK_tnto1uf2s9y8glrcos5f137lw` FOREIGN KEY (`workSection_id`) REFERENCES `worksection` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `application_worksection`
--

LOCK TABLES `application_worksection` WRITE;
/*!40000 ALTER TABLE `application_worksection` DISABLE KEYS */;
/*!40000 ALTER TABLE `application_worksection` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `category` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `rootCategory` bit(1) DEFAULT NULL,
  `categoryParent_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `UK_foei036ov74bv692o5lh5oi66` (`name`),
  KEY `FK_abhsim2d71sbfr6i3h672xqx` (`categoryParent_id`),
  CONSTRAINT `FK_abhsim2d71sbfr6i3h672xqx` FOREIGN KEY (`categoryParent_id`) REFERENCES `category` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (10,0,'root','',NULL),(11,0,'Gratuitas','\0',10),(12,0,'De pago','\0',10),(13,0,'Un solo pago','\0',12),(14,0,'Pago fraccionado','\0',12);
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category_category`
--

DROP TABLE IF EXISTS `category_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `category_category` (
  `Category_id` int(11) NOT NULL,
  `categories_id` int(11) NOT NULL,
  UNIQUE KEY `UK_3sf7ol15w8eboncl70fdmthsq` (`categories_id`),
  KEY `FK_90tievw3x18jv9f0n4ma0ethu` (`Category_id`),
  CONSTRAINT `FK_90tievw3x18jv9f0n4ma0ethu` FOREIGN KEY (`Category_id`) REFERENCES `category` (`id`),
  CONSTRAINT `FK_3sf7ol15w8eboncl70fdmthsq` FOREIGN KEY (`categories_id`) REFERENCES `category` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category_category`
--

LOCK TABLES `category_category` WRITE;
/*!40000 ALTER TABLE `category_category` DISABLE KEYS */;
INSERT INTO `category_category` VALUES (10,11),(10,12),(12,13),(12,14);
/*!40000 ALTER TABLE `category_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contactsection`
--

DROP TABLE IF EXISTS `contactsection`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `contactsection` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `pageNumber` int(11) DEFAULT NULL,
  `phoneNumber` varchar(255) DEFAULT NULL,
  `application_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_168bq3jp4a68wiaxpn0q1ayhg` (`application_id`),
  CONSTRAINT `FK_168bq3jp4a68wiaxpn0q1ayhg` FOREIGN KEY (`application_id`) REFERENCES `application` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contactsection`
--

LOCK TABLES `contactsection` WRITE;
/*!40000 ALTER TABLE `contactsection` DISABLE KEYS */;
/*!40000 ALTER TABLE `contactsection` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `country`
--

DROP TABLE IF EXISTS `country`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `country` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `flag` varchar(255) DEFAULT NULL,
  `isoCode` varchar(255) DEFAULT NULL,
  `link` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `country`
--

LOCK TABLES `country` WRITE;
/*!40000 ALTER TABLE `country` DISABLE KEYS */;
/*!40000 ALTER TABLE `country` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `decision`
--

DROP TABLE IF EXISTS `decision`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `decision` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `accept` bit(1) DEFAULT NULL,
  `comment` varchar(255) DEFAULT NULL,
  `moment` date DEFAULT NULL,
  `application_id` int(11) DEFAULT NULL,
  `officer_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_7jjwf8germf2kicmxd0p8bi4j` (`application_id`),
  KEY `FK_1hy1cb5j5e1ta6mkkdnf1gww5` (`officer_id`),
  CONSTRAINT `FK_1hy1cb5j5e1ta6mkkdnf1gww5` FOREIGN KEY (`officer_id`) REFERENCES `officer` (`id`),
  CONSTRAINT `FK_7jjwf8germf2kicmxd0p8bi4j` FOREIGN KEY (`application_id`) REFERENCES `application` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `decision`
--

LOCK TABLES `decision` WRITE;
/*!40000 ALTER TABLE `decision` DISABLE KEYS */;
/*!40000 ALTER TABLE `decision` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `educationsection`
--

DROP TABLE IF EXISTS `educationsection`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `educationsection` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `dateAwarded` date DEFAULT NULL,
  `institution` varchar(255) DEFAULT NULL,
  `level` varchar(255) DEFAULT NULL,
  `nameDegree` varchar(255) DEFAULT NULL,
  `application_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_9e25ebfx5mbdn8g81304cnewq` (`application_id`),
  CONSTRAINT `FK_9e25ebfx5mbdn8g81304cnewq` FOREIGN KEY (`application_id`) REFERENCES `application` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `educationsection`
--

LOCK TABLES `educationsection` WRITE;
/*!40000 ALTER TABLE `educationsection` DISABLE KEYS */;
/*!40000 ALTER TABLE `educationsection` ENABLE KEYS */;
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
-- Table structure for table `immigrant`
--

DROP TABLE IF EXISTS `immigrant`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `immigrant` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phoneNumber` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `userAccount_id` int(11) NOT NULL,
  `investigator_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_agy28n7ayxk2eo0jw49svx6fq` (`userAccount_id`),
  KEY `ImmigrantUK_cgls5lrufx91ufsyh467spwa3` (`userAccount_id`),
  KEY `UK_pmxjax5pid0rsv0rcftjvus3y` (`investigator_id`),
  CONSTRAINT `FK_agy28n7ayxk2eo0jw49svx6fq` FOREIGN KEY (`userAccount_id`) REFERENCES `useraccount` (`id`),
  CONSTRAINT `FK_pmxjax5pid0rsv0rcftjvus3y` FOREIGN KEY (`investigator_id`) REFERENCES `investigator` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `immigrant`
--

LOCK TABLES `immigrant` WRITE;
/*!40000 ALTER TABLE `immigrant` DISABLE KEYS */;
/*!40000 ALTER TABLE `immigrant` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `investigator`
--

DROP TABLE IF EXISTS `investigator`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `investigator` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phoneNumber` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `userAccount_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_di2p09ij3fudbrrhk1tx2ntm9` (`userAccount_id`),
  KEY `InvestigatorUK_cgls5lrufx91ufsyh467spwa3` (`userAccount_id`),
  CONSTRAINT `FK_di2p09ij3fudbrrhk1tx2ntm9` FOREIGN KEY (`userAccount_id`) REFERENCES `useraccount` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `investigator`
--

LOCK TABLES `investigator` WRITE;
/*!40000 ALTER TABLE `investigator` DISABLE KEYS */;
/*!40000 ALTER TABLE `investigator` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `investigator_immigrant`
--

DROP TABLE IF EXISTS `investigator_immigrant`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `investigator_immigrant` (
  `Investigator_id` int(11) NOT NULL,
  `immigrants_id` int(11) NOT NULL,
  UNIQUE KEY `UK_7egh30iom45er34d9q2j8u1rv` (`immigrants_id`),
  KEY `FK_51ojp1dvtmccccjkeqp18dx0f` (`Investigator_id`),
  CONSTRAINT `FK_51ojp1dvtmccccjkeqp18dx0f` FOREIGN KEY (`Investigator_id`) REFERENCES `investigator` (`id`),
  CONSTRAINT `FK_7egh30iom45er34d9q2j8u1rv` FOREIGN KEY (`immigrants_id`) REFERENCES `immigrant` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `investigator_immigrant`
--

LOCK TABLES `investigator_immigrant` WRITE;
/*!40000 ALTER TABLE `investigator_immigrant` DISABLE KEYS */;
/*!40000 ALTER TABLE `investigator_immigrant` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `law`
--

DROP TABLE IF EXISTS `law`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `law` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `abrogationTime` date DEFAULT NULL,
  `enactmentDate` date DEFAULT NULL,
  `text` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `country_id` int(11) NOT NULL,
  `lawParent_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_is49rdvlftjviqiryjy6tbb5a` (`country_id`),
  KEY `FK_d3ngkfr0nxku8acngmf9ulif3` (`lawParent_id`),
  CONSTRAINT `FK_d3ngkfr0nxku8acngmf9ulif3` FOREIGN KEY (`lawParent_id`) REFERENCES `law` (`id`),
  CONSTRAINT `FK_is49rdvlftjviqiryjy6tbb5a` FOREIGN KEY (`country_id`) REFERENCES `country` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `law`
--

LOCK TABLES `law` WRITE;
/*!40000 ALTER TABLE `law` DISABLE KEYS */;
/*!40000 ALTER TABLE `law` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `officer`
--

DROP TABLE IF EXISTS `officer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `officer` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phoneNumber` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `userAccount_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_j2oip9hkjxaqmvwiei4ocfght` (`userAccount_id`),
  KEY `OfficerUK_cgls5lrufx91ufsyh467spwa3` (`userAccount_id`),
  CONSTRAINT `FK_j2oip9hkjxaqmvwiei4ocfght` FOREIGN KEY (`userAccount_id`) REFERENCES `useraccount` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `officer`
--

LOCK TABLES `officer` WRITE;
/*!40000 ALTER TABLE `officer` DISABLE KEYS */;
/*!40000 ALTER TABLE `officer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `personalsection`
--

DROP TABLE IF EXISTS `personalsection`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `personalsection` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `birthDate` date DEFAULT NULL,
  `birthPlace` varchar(255) DEFAULT NULL,
  `picture` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `personalsection`
--

LOCK TABLES `personalsection` WRITE;
/*!40000 ALTER TABLE `personalsection` DISABLE KEYS */;
/*!40000 ALTER TABLE `personalsection` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `personalsection_names`
--

DROP TABLE IF EXISTS `personalsection_names`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `personalsection_names` (
  `PersonalSection_id` int(11) NOT NULL,
  `names` varchar(255) DEFAULT NULL,
  KEY `FK_9qx3p5d0fpr44c5qqf9mo94es` (`PersonalSection_id`),
  CONSTRAINT `FK_9qx3p5d0fpr44c5qqf9mo94es` FOREIGN KEY (`PersonalSection_id`) REFERENCES `personalsection` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `personalsection_names`
--

LOCK TABLES `personalsection_names` WRITE;
/*!40000 ALTER TABLE `personalsection_names` DISABLE KEYS */;
/*!40000 ALTER TABLE `personalsection_names` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `question`
--

DROP TABLE IF EXISTS `question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `question` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `moment` datetime DEFAULT NULL,
  `statement` bit(1) NOT NULL,
  `text` varchar(255) DEFAULT NULL,
  `application_id` int(11) DEFAULT NULL,
  `officer_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `UK_luujakgkx0iwdaqxg9ucnlsbe` (`officer_id`),
  KEY `FK_nrl5xpby9aq1g9mwclvouexvt` (`application_id`),
  CONSTRAINT `FK_luujakgkx0iwdaqxg9ucnlsbe` FOREIGN KEY (`officer_id`) REFERENCES `officer` (`id`),
  CONSTRAINT `FK_nrl5xpby9aq1g9mwclvouexvt` FOREIGN KEY (`application_id`) REFERENCES `application` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `question`
--

LOCK TABLES `question` WRITE;
/*!40000 ALTER TABLE `question` DISABLE KEYS */;
/*!40000 ALTER TABLE `question` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `report`
--

DROP TABLE IF EXISTS `report`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `report` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `picture` varchar(255) DEFAULT NULL,
  `text` varchar(255) DEFAULT NULL,
  `immigrant_id` int(11) DEFAULT NULL,
  `writer_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `UK_cq0si5egvex4qa8ikj7aj2oip` (`writer_id`),
  KEY `FK_fmyx1a88rrqolatfqtoudnfoe` (`immigrant_id`),
  CONSTRAINT `FK_cq0si5egvex4qa8ikj7aj2oip` FOREIGN KEY (`writer_id`) REFERENCES `investigator` (`id`),
  CONSTRAINT `FK_fmyx1a88rrqolatfqtoudnfoe` FOREIGN KEY (`immigrant_id`) REFERENCES `immigrant` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `report`
--

LOCK TABLES `report` WRITE;
/*!40000 ALTER TABLE `report` DISABLE KEYS */;
/*!40000 ALTER TABLE `report` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `requirement`
--

DROP TABLE IF EXISTS `requirement`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `requirement` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `abrogated` bit(1) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `law_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_1qwnx6c4ygc13a58uo6wp7x9i` (`law_id`),
  CONSTRAINT `FK_1qwnx6c4ygc13a58uo6wp7x9i` FOREIGN KEY (`law_id`) REFERENCES `law` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `requirement`
--

LOCK TABLES `requirement` WRITE;
/*!40000 ALTER TABLE `requirement` DISABLE KEYS */;
/*!40000 ALTER TABLE `requirement` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `socialsection`
--

DROP TABLE IF EXISTS `socialsection`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `socialsection` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `nickName` varchar(255) DEFAULT NULL,
  `profileLink` varchar(255) DEFAULT NULL,
  `socialNetwork` varchar(255) DEFAULT NULL,
  `application_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_a046wuekj8lqx898cb2584qio` (`application_id`),
  CONSTRAINT `FK_a046wuekj8lqx898cb2584qio` FOREIGN KEY (`application_id`) REFERENCES `application` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `socialsection`
--

LOCK TABLES `socialsection` WRITE;
/*!40000 ALTER TABLE `socialsection` DISABLE KEYS */;
/*!40000 ALTER TABLE `socialsection` ENABLE KEYS */;
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
INSERT INTO `useraccount` VALUES (8,0,'21232f297a57a5a743894a0e4a801fc3','admin');
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
INSERT INTO `useraccount_authorities` VALUES (8,'ADMIN');
/*!40000 ALTER TABLE `useraccount_authorities` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `visa`
--

DROP TABLE IF EXISTS `visa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `visa` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `classes` varchar(255) DEFAULT NULL,
  `brandName` varchar(255) DEFAULT NULL,
  `cvv` int(11) DEFAULT NULL,
  `expirationMonth` int(11) DEFAULT NULL,
  `expirationYear` int(11) DEFAULT NULL,
  `holderName` varchar(255) DEFAULT NULL,
  `number` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `invalidate` bit(1) DEFAULT NULL,
  `price` int(11) DEFAULT NULL,
  `category_id` int(11) NOT NULL,
  `country_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `UK_mnqiemwn87tjqvt4mtsaaogep` (`category_id`,`country_id`,`description`),
  KEY `FK_ofsf31wkhm8xa37cy1k00lpjm` (`country_id`),
  CONSTRAINT `FK_ofsf31wkhm8xa37cy1k00lpjm` FOREIGN KEY (`country_id`) REFERENCES `country` (`id`),
  CONSTRAINT `FK_dv6pkgoytrlh3ali5vxllr0tb` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `visa`
--

LOCK TABLES `visa` WRITE;
/*!40000 ALTER TABLE `visa` DISABLE KEYS */;
/*!40000 ALTER TABLE `visa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `worksection`
--

DROP TABLE IF EXISTS `worksection`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `worksection` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `endDate` date DEFAULT NULL,
  `nameCompany` varchar(255) DEFAULT NULL,
  `position` varchar(255) DEFAULT NULL,
  `startDate` date DEFAULT NULL,
  `application_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_e88ql9puakxa23goda4ubmt50` (`application_id`),
  CONSTRAINT `FK_e88ql9puakxa23goda4ubmt50` FOREIGN KEY (`application_id`) REFERENCES `application` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `worksection`
--

LOCK TABLES `worksection` WRITE;
/*!40000 ALTER TABLE `worksection` DISABLE KEYS */;
/*!40000 ALTER TABLE `worksection` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-09-14 19:20:52

commit;