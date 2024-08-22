-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.5.15


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema eassesment
--

CREATE DATABASE IF NOT EXISTS eassesment;
USE eassesment;

--
-- Definition of table `answer`
--

DROP TABLE IF EXISTS `answer`;
CREATE TABLE `answer` (
  `answer_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `question_id` bigint(20) unsigned DEFAULT NULL,
  `answer` varchar(255) DEFAULT NULL,
  `correctanswer_flag` tinyint(3) unsigned DEFAULT NULL,
  PRIMARY KEY (`answer_id`),
  KEY `FK_answer_1` (`question_id`),
  CONSTRAINT `FK_answer_1` FOREIGN KEY (`question_id`) REFERENCES `questions` (`question_id`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `answer`
--

/*!40000 ALTER TABLE `answer` DISABLE KEYS */;
INSERT INTO `answer` (`answer_id`,`question_id`,`answer`,`correctanswer_flag`) VALUES 
 (1,2,' public class ABC extends QWE extends Student',0),
 (2,2,' int i=\"A\";',0),
 (3,2,'String s=\"Hello\";',1),
 (4,2,' private class ABC',0),
 (5,3,'class',0),
 (6,3,'interface',0),
 (7,3,'extends',0),
 (8,3,'abstraction',1),
 (9,4,'Java is platform specific',0),
 (10,4,'Java does not support multiple inheritance',1),
 (11,4,'Java does not run on Linux and Mac',0),
 (12,4,'Java is not a multi-threaded language ',0),
 (13,5,'Thread',0),
 (14,5,'Runnable',1),
 (15,5,'Date',0),
 (16,5,'Calendar',0),
 (17,6,'Sun',0),
 (18,6,'Oracle',1),
 (19,6,'Adobe',0),
 (20,6,'Google',0),
 (21,7,'First Generation Languages',0),
 (22,7,'Second Generation Languages',0),
 (23,7,'Third Generation Languages',1),
 (24,7,'Fourth Generation Languages',0),
 (25,8,'java.net',0),
 (26,8,'javax.swing',0),
 (27,8,'java.io',0),
 (28,8,'java.lang',1),
 (29,9,'servlet-mapping',1),
 (30,9,'servlet-registration',0),
 (31,9,'servlet-entry',0),
 (32,9,'servlet-attachment',0),
 (33,10,'32 bit',1),
 (34,10,'16 bit',0),
 (35,10,'64 bit',0),
 (36,10,'Runtime specific',0),
 (37,11,'true',0),
 (38,11,'false',1),
 (39,11,'1',0),
 (40,11,'0',0);
/*!40000 ALTER TABLE `answer` ENABLE KEYS */;


--
-- Definition of table `candidate_competancy`
--

DROP TABLE IF EXISTS `candidate_competancy`;
CREATE TABLE `candidate_competancy` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) unsigned DEFAULT NULL,
  `category_id` bigint(20) unsigned DEFAULT NULL,
  `sub_category_id` bigint(20) unsigned DEFAULT NULL,
  `progress` float DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_candidate_competancy_1` (`user_id`),
  KEY `FK_candidate_competancy_2` (`category_id`),
  KEY `FK_candidate_competancy_3` (`sub_category_id`),
  CONSTRAINT `FK_candidate_competancy_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `FK_candidate_competancy_2` FOREIGN KEY (`category_id`) REFERENCES `category` (`category_id`),
  CONSTRAINT `FK_candidate_competancy_3` FOREIGN KEY (`sub_category_id`) REFERENCES `subcategory` (`subcategory_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `candidate_competancy`
--

/*!40000 ALTER TABLE `candidate_competancy` DISABLE KEYS */;
/*!40000 ALTER TABLE `candidate_competancy` ENABLE KEYS */;


--
-- Definition of table `category`
--

DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `category_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `category` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `category`
--

/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` (`category_id`,`category`) VALUES 
 (1,'java'),
 (2,'php');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;


--
-- Definition of table `question_category_subcategory`
--

DROP TABLE IF EXISTS `question_category_subcategory`;
CREATE TABLE `question_category_subcategory` (
  `question_id` bigint(20) unsigned DEFAULT NULL,
  `category_id` bigint(20) unsigned DEFAULT NULL,
  `subcategory_id` bigint(20) unsigned DEFAULT NULL,
  KEY `FK_question_test_category_1` (`question_id`),
  KEY `FK_question_test_category_2` (`category_id`),
  KEY `FK_question_test_category_3` (`subcategory_id`),
  CONSTRAINT `FK_question_test_category_1` FOREIGN KEY (`question_id`) REFERENCES `questions` (`question_id`),
  CONSTRAINT `FK_question_test_category_2` FOREIGN KEY (`category_id`) REFERENCES `category` (`category_id`),
  CONSTRAINT `FK_question_test_category_3` FOREIGN KEY (`subcategory_id`) REFERENCES `subcategory` (`subcategory_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `question_category_subcategory`
--

/*!40000 ALTER TABLE `question_category_subcategory` DISABLE KEYS */;
INSERT INTO `question_category_subcategory` (`question_id`,`category_id`,`subcategory_id`) VALUES 
 (2,1,1),
 (3,1,1),
 (4,1,1),
 (5,1,1),
 (6,1,1),
 (7,1,1),
 (8,1,1),
 (9,1,1),
 (10,1,1),
 (11,1,1);
/*!40000 ALTER TABLE `question_category_subcategory` ENABLE KEYS */;


--
-- Definition of table `question_competancy`
--

DROP TABLE IF EXISTS `question_competancy`;
CREATE TABLE `question_competancy` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `question_id` bigint(20) unsigned DEFAULT NULL,
  `total_attempts` bigint(30) unsigned DEFAULT NULL,
  `correct_attempts` bigint(30) unsigned DEFAULT NULL,
  `complecity` float DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_question_competancy_1` (`question_id`),
  CONSTRAINT `FK_question_competancy_1` FOREIGN KEY (`question_id`) REFERENCES `questions` (`question_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `question_competancy`
--

/*!40000 ALTER TABLE `question_competancy` DISABLE KEYS */;
/*!40000 ALTER TABLE `question_competancy` ENABLE KEYS */;


--
-- Definition of table `questions`
--

DROP TABLE IF EXISTS `questions`;
CREATE TABLE `questions` (
  `question_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `question` text,
  `questiontype` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`question_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `questions`
--

/*!40000 ALTER TABLE `questions` DISABLE KEYS */;
INSERT INTO `questions` (`question_id`,`question`,`questiontype`) VALUES 
 (2,'Which is the correct syntax ?','null'),
 (3,' Which of the following a is not a keyword in Java ?','null'),
 (4,'What is true about Java ?','null'),
 (5,'Which of the following is an interface ?','null'),
 (6,'Which company released Java Version 8 ?','null'),
 (7,'Java comes under which category of languages ?','null'),
 (8,'Which is the default package that is automatically visible to your program ?','null'),
 (9,'Which entry in WEB-INF is used to map a servlet ?','null'),
 (10,'What is the length of Java datatype int ?','null'),
 (11,'What is the default value of Java datatype boolean?','null');
/*!40000 ALTER TABLE `questions` ENABLE KEYS */;


--
-- Definition of table `result`
--

DROP TABLE IF EXISTS `result`;
CREATE TABLE `result` (
  `result_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) unsigned DEFAULT NULL,
  `test_type` varchar(45) DEFAULT NULL,
  `question_id` bigint(20) unsigned DEFAULT NULL,
  `user_selection` bigint(20) unsigned DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `test_id` bigint(20) unsigned DEFAULT NULL,
  PRIMARY KEY (`result_id`),
  KEY `FK_result_1` (`user_id`),
  KEY `FK_result_2` (`question_id`),
  KEY `FK_result_3` (`user_selection`) USING BTREE,
  CONSTRAINT `FK_result_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `FK_result_2` FOREIGN KEY (`question_id`) REFERENCES `questions` (`question_id`),
  CONSTRAINT `FK_result_3` FOREIGN KEY (`user_selection`) REFERENCES `answer` (`answer_id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `result`
--

/*!40000 ALTER TABLE `result` DISABLE KEYS */;
INSERT INTO `result` (`result_id`,`user_id`,`test_type`,`question_id`,`user_selection`,`created_date`,`test_id`) VALUES 
 (1,2,'Basic',2,3,'2016-12-16 00:00:00',1),
 (2,2,'Basic',3,8,'2016-12-16 00:00:00',1),
 (3,2,'Basic',4,10,'2016-12-16 00:00:00',1),
 (4,2,'Basic',5,14,'2016-12-16 00:00:00',1),
 (5,2,'Basic',6,18,'2016-12-16 00:00:00',1),
 (6,2,'Basic',7,23,'2016-12-16 00:00:00',1),
 (7,2,'Basic',8,28,'2016-12-16 00:00:00',1),
 (8,2,'Basic',9,29,'2016-12-16 00:00:00',1),
 (9,2,'Basic',10,35,'2016-12-16 00:00:00',1),
 (10,2,'Basic',2,3,'2016-12-16 00:00:00',2),
 (11,2,'Basic',3,8,'2016-12-16 00:00:00',2),
 (12,2,'Basic',4,11,'2016-12-16 00:00:00',2),
 (13,2,'Basic',5,14,'2016-12-16 00:00:00',2),
 (14,2,'Basic',6,18,'2016-12-16 00:00:00',2),
 (15,2,'Basic',7,23,'2016-12-16 00:00:00',2),
 (16,2,'Basic',8,28,'2016-12-16 00:00:00',2),
 (17,2,'Basic',9,29,'2016-12-16 00:00:00',2),
 (18,2,'Basic',10,34,'2016-12-16 00:00:00',2);
/*!40000 ALTER TABLE `result` ENABLE KEYS */;


--
-- Definition of table `subcategory`
--

DROP TABLE IF EXISTS `subcategory`;
CREATE TABLE `subcategory` (
  `subcategory_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `subcategory` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`subcategory_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `subcategory`
--

/*!40000 ALTER TABLE `subcategory` DISABLE KEYS */;
INSERT INTO `subcategory` (`subcategory_id`,`subcategory`) VALUES 
 (1,'Basic'),
 (2,'level two');
/*!40000 ALTER TABLE `subcategory` ENABLE KEYS */;


--
-- Definition of table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `phone_no` bigint(20) unsigned DEFAULT NULL,
  `user_type` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`user_id`,`user_name`,`password`,`email`,`phone_no`,`user_type`) VALUES 
 (1,'admin','admin','admin@gmail.com',9442559268,'Admin'),
 (2,'Manivel','mani','manivel.scube@gmail.com',97879123443,'Candidate');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
