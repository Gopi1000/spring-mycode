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
-- Create schema techboot
--

CREATE DATABASE IF NOT EXISTS techboot;
USE techboot;

--
-- Temporary table structure for view `campaign_sms_view`
--
DROP TABLE IF EXISTS `campaign_sms_view`;
DROP VIEW IF EXISTS `campaign_sms_view`;
CREATE TABLE `campaign_sms_view` (
  `mobile_number` bigint(20),
  `first_name` varchar(255),
  `message` text,
  `started_Time` datetime,
  `end_Time` datetime,
  `service_Name` varchar(255),
  `duration` varchar(255),
  `start_Date` datetime,
  `end_Date` datetime,
  `fees` double,
  `company_id` int(11),
  `companyName` varchar(50)
);

--
-- Definition of table `batch_job_execution`
--

DROP TABLE IF EXISTS `batch_job_execution`;
CREATE TABLE `batch_job_execution` (
  `JOB_EXECUTION_ID` bigint(20) NOT NULL,
  `VERSION` bigint(20) DEFAULT NULL,
  `JOB_INSTANCE_ID` bigint(20) NOT NULL,
  `CREATE_TIME` datetime NOT NULL,
  `START_TIME` datetime DEFAULT NULL,
  `END_TIME` datetime DEFAULT NULL,
  `STATUS` varchar(10) DEFAULT NULL,
  `EXIT_CODE` varchar(2500) DEFAULT NULL,
  `EXIT_MESSAGE` varchar(2500) DEFAULT NULL,
  `LAST_UPDATED` datetime DEFAULT NULL,
  `JOB_CONFIGURATION_LOCATION` varchar(2500) DEFAULT NULL,
  PRIMARY KEY (`JOB_EXECUTION_ID`),
  KEY `JOB_INST_EXEC_FK` (`JOB_INSTANCE_ID`),
  CONSTRAINT `JOB_INST_EXEC_FK` FOREIGN KEY (`JOB_INSTANCE_ID`) REFERENCES `batch_job_instance` (`JOB_INSTANCE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `batch_job_execution`
--

/*!40000 ALTER TABLE `batch_job_execution` DISABLE KEYS */;
INSERT INTO `batch_job_execution` (`JOB_EXECUTION_ID`,`VERSION`,`JOB_INSTANCE_ID`,`CREATE_TIME`,`START_TIME`,`END_TIME`,`STATUS`,`EXIT_CODE`,`EXIT_MESSAGE`,`LAST_UPDATED`,`JOB_CONFIGURATION_LOCATION`) VALUES 
 (1,2,1,'2020-02-14 18:43:48','2020-02-14 18:43:48','2020-02-14 19:10:46','COMPLETED','COMPLETED','','2020-02-14 19:10:46',NULL);
/*!40000 ALTER TABLE `batch_job_execution` ENABLE KEYS */;


--
-- Definition of table `batch_job_execution_context`
--

DROP TABLE IF EXISTS `batch_job_execution_context`;
CREATE TABLE `batch_job_execution_context` (
  `JOB_EXECUTION_ID` bigint(20) NOT NULL,
  `SHORT_CONTEXT` varchar(2500) NOT NULL,
  `SERIALIZED_CONTEXT` text,
  PRIMARY KEY (`JOB_EXECUTION_ID`),
  CONSTRAINT `JOB_EXEC_CTX_FK` FOREIGN KEY (`JOB_EXECUTION_ID`) REFERENCES `batch_job_execution` (`JOB_EXECUTION_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `batch_job_execution_context`
--

/*!40000 ALTER TABLE `batch_job_execution_context` DISABLE KEYS */;
INSERT INTO `batch_job_execution_context` (`JOB_EXECUTION_ID`,`SHORT_CONTEXT`,`SERIALIZED_CONTEXT`) VALUES 
 (1,'{\"map\":[\"\"]}',NULL);
/*!40000 ALTER TABLE `batch_job_execution_context` ENABLE KEYS */;


--
-- Definition of table `batch_job_execution_params`
--

DROP TABLE IF EXISTS `batch_job_execution_params`;
CREATE TABLE `batch_job_execution_params` (
  `JOB_EXECUTION_ID` bigint(20) NOT NULL,
  `TYPE_CD` varchar(6) NOT NULL,
  `KEY_NAME` varchar(100) NOT NULL,
  `STRING_VAL` varchar(250) DEFAULT NULL,
  `DATE_VAL` datetime DEFAULT NULL,
  `LONG_VAL` bigint(20) DEFAULT NULL,
  `DOUBLE_VAL` double DEFAULT NULL,
  `IDENTIFYING` char(1) NOT NULL,
  KEY `JOB_EXEC_PARAMS_FK` (`JOB_EXECUTION_ID`),
  CONSTRAINT `JOB_EXEC_PARAMS_FK` FOREIGN KEY (`JOB_EXECUTION_ID`) REFERENCES `batch_job_execution` (`JOB_EXECUTION_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `batch_job_execution_params`
--

/*!40000 ALTER TABLE `batch_job_execution_params` DISABLE KEYS */;
INSERT INTO `batch_job_execution_params` (`JOB_EXECUTION_ID`,`TYPE_CD`,`KEY_NAME`,`STRING_VAL`,`DATE_VAL`,`LONG_VAL`,`DOUBLE_VAL`,`IDENTIFYING`) VALUES 
 (1,'LONG','time','','1970-01-01 05:30:00',1581686027948,0,'Y');
/*!40000 ALTER TABLE `batch_job_execution_params` ENABLE KEYS */;


--
-- Definition of table `batch_job_execution_seq`
--

DROP TABLE IF EXISTS `batch_job_execution_seq`;
CREATE TABLE `batch_job_execution_seq` (
  `ID` bigint(20) NOT NULL,
  `UNIQUE_KEY` char(1) NOT NULL,
  UNIQUE KEY `UNIQUE_KEY_UN` (`UNIQUE_KEY`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `batch_job_execution_seq`
--

/*!40000 ALTER TABLE `batch_job_execution_seq` DISABLE KEYS */;
INSERT INTO `batch_job_execution_seq` (`ID`,`UNIQUE_KEY`) VALUES 
 (1,'0');
/*!40000 ALTER TABLE `batch_job_execution_seq` ENABLE KEYS */;


--
-- Definition of table `batch_job_instance`
--

DROP TABLE IF EXISTS `batch_job_instance`;
CREATE TABLE `batch_job_instance` (
  `JOB_INSTANCE_ID` bigint(20) NOT NULL,
  `VERSION` bigint(20) DEFAULT NULL,
  `JOB_NAME` varchar(100) NOT NULL,
  `JOB_KEY` varchar(32) NOT NULL,
  PRIMARY KEY (`JOB_INSTANCE_ID`),
  UNIQUE KEY `JOB_INST_UN` (`JOB_NAME`,`JOB_KEY`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `batch_job_instance`
--

/*!40000 ALTER TABLE `batch_job_instance` DISABLE KEYS */;
INSERT INTO `batch_job_instance` (`JOB_INSTANCE_ID`,`VERSION`,`JOB_NAME`,`JOB_KEY`) VALUES 
 (1,0,'techbootsms','07ca21e567cee3c139b071c1c4edeaa3');
/*!40000 ALTER TABLE `batch_job_instance` ENABLE KEYS */;


--
-- Definition of table `batch_job_seq`
--

DROP TABLE IF EXISTS `batch_job_seq`;
CREATE TABLE `batch_job_seq` (
  `ID` bigint(20) NOT NULL,
  `UNIQUE_KEY` char(1) NOT NULL,
  UNIQUE KEY `UNIQUE_KEY_UN` (`UNIQUE_KEY`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `batch_job_seq`
--

/*!40000 ALTER TABLE `batch_job_seq` DISABLE KEYS */;
INSERT INTO `batch_job_seq` (`ID`,`UNIQUE_KEY`) VALUES 
 (1,'0');
/*!40000 ALTER TABLE `batch_job_seq` ENABLE KEYS */;


--
-- Definition of table `batch_step_execution`
--

DROP TABLE IF EXISTS `batch_step_execution`;
CREATE TABLE `batch_step_execution` (
  `STEP_EXECUTION_ID` bigint(20) NOT NULL,
  `VERSION` bigint(20) NOT NULL,
  `STEP_NAME` varchar(100) NOT NULL,
  `JOB_EXECUTION_ID` bigint(20) NOT NULL,
  `START_TIME` datetime NOT NULL,
  `END_TIME` datetime DEFAULT NULL,
  `STATUS` varchar(10) DEFAULT NULL,
  `COMMIT_COUNT` bigint(20) DEFAULT NULL,
  `READ_COUNT` bigint(20) DEFAULT NULL,
  `FILTER_COUNT` bigint(20) DEFAULT NULL,
  `WRITE_COUNT` bigint(20) DEFAULT NULL,
  `READ_SKIP_COUNT` bigint(20) DEFAULT NULL,
  `WRITE_SKIP_COUNT` bigint(20) DEFAULT NULL,
  `PROCESS_SKIP_COUNT` bigint(20) DEFAULT NULL,
  `ROLLBACK_COUNT` bigint(20) DEFAULT NULL,
  `EXIT_CODE` varchar(2500) DEFAULT NULL,
  `EXIT_MESSAGE` varchar(2500) DEFAULT NULL,
  `LAST_UPDATED` datetime DEFAULT NULL,
  PRIMARY KEY (`STEP_EXECUTION_ID`),
  KEY `JOB_EXEC_STEP_FK` (`JOB_EXECUTION_ID`),
  CONSTRAINT `JOB_EXEC_STEP_FK` FOREIGN KEY (`JOB_EXECUTION_ID`) REFERENCES `batch_job_execution` (`JOB_EXECUTION_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `batch_step_execution`
--

/*!40000 ALTER TABLE `batch_step_execution` DISABLE KEYS */;
INSERT INTO `batch_step_execution` (`STEP_EXECUTION_ID`,`VERSION`,`STEP_NAME`,`JOB_EXECUTION_ID`,`START_TIME`,`END_TIME`,`STATUS`,`COMMIT_COUNT`,`READ_COUNT`,`FILTER_COUNT`,`WRITE_COUNT`,`READ_SKIP_COUNT`,`WRITE_SKIP_COUNT`,`PROCESS_SKIP_COUNT`,`ROLLBACK_COUNT`,`EXIT_CODE`,`EXIT_MESSAGE`,`LAST_UPDATED`) VALUES 
 (1,4,'step1',1,'2020-02-14 18:43:48','2020-02-14 19:10:46','COMPLETED',2,1,0,1,0,0,0,0,'COMPLETED','','2020-02-14 19:10:46');
/*!40000 ALTER TABLE `batch_step_execution` ENABLE KEYS */;


--
-- Definition of table `batch_step_execution_context`
--

DROP TABLE IF EXISTS `batch_step_execution_context`;
CREATE TABLE `batch_step_execution_context` (
  `STEP_EXECUTION_ID` bigint(20) NOT NULL,
  `SHORT_CONTEXT` varchar(2500) NOT NULL,
  `SERIALIZED_CONTEXT` text,
  PRIMARY KEY (`STEP_EXECUTION_ID`),
  CONSTRAINT `STEP_EXEC_CTX_FK` FOREIGN KEY (`STEP_EXECUTION_ID`) REFERENCES `batch_step_execution` (`STEP_EXECUTION_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `batch_step_execution_context`
--

/*!40000 ALTER TABLE `batch_step_execution_context` DISABLE KEYS */;
INSERT INTO `batch_step_execution_context` (`STEP_EXECUTION_ID`,`SHORT_CONTEXT`,`SERIALIZED_CONTEXT`) VALUES 
 (1,'{\"map\":[{\"entry\":[{\"string\":[\"batch.taskletType\",\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\"]},{\"string\":[\"batch.stepType\",\"org.springframework.batch.core.step.tasklet.TaskletStep\"]}]}]}',NULL);
/*!40000 ALTER TABLE `batch_step_execution_context` ENABLE KEYS */;


--
-- Definition of table `batch_step_execution_seq`
--

DROP TABLE IF EXISTS `batch_step_execution_seq`;
CREATE TABLE `batch_step_execution_seq` (
  `ID` bigint(20) NOT NULL,
  `UNIQUE_KEY` char(1) NOT NULL,
  UNIQUE KEY `UNIQUE_KEY_UN` (`UNIQUE_KEY`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `batch_step_execution_seq`
--

/*!40000 ALTER TABLE `batch_step_execution_seq` DISABLE KEYS */;
INSERT INTO `batch_step_execution_seq` (`ID`,`UNIQUE_KEY`) VALUES 
 (1,'0');
/*!40000 ALTER TABLE `batch_step_execution_seq` ENABLE KEYS */;


--
-- Definition of table `campaign`
--

DROP TABLE IF EXISTS `campaign`;
CREATE TABLE `campaign` (
  `campaignId` int(11) NOT NULL AUTO_INCREMENT,
  `createdBy` bigint(20) DEFAULT NULL,
  `createdTime` datetime DEFAULT NULL,
  `isDelete` tinyint(1) DEFAULT NULL,
  `modifiedBy` bigint(20) DEFAULT NULL,
  `modifiedTime` datetime DEFAULT NULL,
  `sending_status` tinyint(1) DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `campaign_Name` varchar(50) NOT NULL,
  `category` varchar(255) NOT NULL,
  `end_Time` datetime NOT NULL,
  `message` text NOT NULL,
  `started_Time` datetime NOT NULL,
  `company_id` int(11) DEFAULT NULL,
  `productServiceId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`campaignId`),
  KEY `FK_gp07r0rdjeu5irvpq7c2pesn1` (`company_id`),
  KEY `FK_78to2xtirgnbnd9d5hr688v2s` (`productServiceId`),
  CONSTRAINT `FK_78to2xtirgnbnd9d5hr688v2s` FOREIGN KEY (`productServiceId`) REFERENCES `service` (`service_id`),
  CONSTRAINT `FK_gp07r0rdjeu5irvpq7c2pesn1` FOREIGN KEY (`company_id`) REFERENCES `company` (`companyId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `campaign`
--

/*!40000 ALTER TABLE `campaign` DISABLE KEYS */;
INSERT INTO `campaign` (`campaignId`,`createdBy`,`createdTime`,`isDelete`,`modifiedBy`,`modifiedTime`,`sending_status`,`version`,`campaign_Name`,`category`,`end_Time`,`message`,`started_Time`,`company_id`,`productServiceId`) VALUES 
 (1,1,'2020-02-14 17:24:06',0,0,NULL,1,0,'Java Servlet Jsp ','Sms','2020-02-15 00:00:00','         Are you facing challengs in getting the job? We shall help you, Learn Java Full Stack with us in 90 days. Get your job.                                                                                                                                                                                                  Java @ INR 5000 / J2ee @ INR 7500 / Spring and Hibernate @ INR 10000 / Spring Boot @ INR 10000 / Angular @ INR 10000.                                                                                                                                                                                                                            Visit :              www.techbootsolutions.com ,                                Register your profile with www.myjobbie.com for your interview schedule.','2020-02-14 00:00:00',1,1);
/*!40000 ALTER TABLE `campaign` ENABLE KEYS */;


--
-- Definition of table `campaign_sms_tracking`
--

DROP TABLE IF EXISTS `campaign_sms_tracking`;
CREATE TABLE `campaign_sms_tracking` (
  `Tracking_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `company_name` varchar(255) DEFAULT NULL,
  `curent_date` date DEFAULT NULL,
  `customer_name` varchar(255) DEFAULT NULL,
  `message` text,
  `mobile_number` bigint(20) DEFAULT NULL,
  `service_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Tracking_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `campaign_sms_tracking`
--

/*!40000 ALTER TABLE `campaign_sms_tracking` DISABLE KEYS */;
INSERT INTO `campaign_sms_tracking` (`Tracking_id`,`company_name`,`curent_date`,`customer_name`,`message`,`mobile_number`,`service_name`) VALUES 
 (1,'Techboot Solutions','2020-02-14','Arunkumar','         Are you facing challengs in getting the job? We shall help you, Learn Java Full Stack with us in 90 days. Get your job.                                                                                                                                                                                                  Java @ INR 5000 / J2ee @ INR 7500 / Spring and Hibernate @ INR 10000 / Spring Boot @ INR 10000 / Angular @ INR 10000.                                                                                                                                                                                                                            Visit :              www.techbootsolutions.com ,                                Register your profile with www.myjobbie.com for your interview schedule.',7418971140,'Java Servlet Jsp'),
 (2,'Techboot Solutions','2020-02-14','Karthikeyan','         Are you facing challengs in getting the job? We shall help you, Learn Java Full Stack with us in 90 days. Get your job.                                                                                                                                                                                                  Java @ INR 5000 / J2ee @ INR 7500 / Spring and Hibernate @ INR 10000 / Spring Boot @ INR 10000 / Angular @ INR 10000.                                                                                                                                                                                                                            Visit :              www.techbootsolutions.com ,                                Register your profile with www.myjobbie.com for your interview schedule.',9043328416,'Java Servlet Jsp');
/*!40000 ALTER TABLE `campaign_sms_tracking` ENABLE KEYS */;


--
-- Definition of table `company`
--

DROP TABLE IF EXISTS `company`;
CREATE TABLE `company` (
  `companyId` int(11) NOT NULL AUTO_INCREMENT,
  `address` varchar(255) NOT NULL,
  `companyName` varchar(50) NOT NULL,
  `companyPersonName` varchar(50) NOT NULL,
  `companyType` varchar(255) NOT NULL,
  `companyWebSite` varchar(255) NOT NULL,
  `conformEmailAddress` varchar(50) NOT NULL,
  `conformPassword` varchar(255) NOT NULL,
  `contectNumber` bigint(20) NOT NULL,
  `createdBy` bigint(20) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `emailAddress` varchar(50) NOT NULL,
  `firstName` varchar(50) NOT NULL,
  `imageName` varchar(255) DEFAULT NULL,
  `industry` varchar(255) NOT NULL,
  `isDelete` tinyint(4) DEFAULT NULL,
  `lastName` varchar(50) NOT NULL,
  `modifiedBy` bigint(20) DEFAULT NULL,
  `modified_time` datetime DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `sending_status` tinyint(4) DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  PRIMARY KEY (`companyId`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `company`
--

/*!40000 ALTER TABLE `company` DISABLE KEYS */;
INSERT INTO `company` (`companyId`,`address`,`companyName`,`companyPersonName`,`companyType`,`companyWebSite`,`conformEmailAddress`,`conformPassword`,`contectNumber`,`createdBy`,`created_time`,`emailAddress`,`firstName`,`imageName`,`industry`,`isDelete`,`lastName`,`modifiedBy`,`modified_time`,`password`,`sending_status`,`version`) VALUES 
 (1,'3/1419 Ground Floor, 5th Cross Street, Annai Velanganni Nagar Phase-I, Madhanandhapuram, Chennai - 600125','Techboot Solutions','selvaKumar','JobSeeker','www.techbootsolutions.com','enquiry@techboot.com','AUIZw1GhGpOBkUMLIBrF5cRI8BaCRwvY+J0UdEZzyN4=',9790590476,1,'2019-12-23 10:06:55','enquiry@techboot.com','Selva','1.png','IT/Software',0,'S',0,NULL,'AUIZw1GhGpOBkUMLIBrF5cRI8BaCRwvY+J0UdEZzyN4=',1,0),
 (2,'3/1419 Ground Floor, 5th Cross Street, Annai Velanganni Nagar Phase-I, Guidy, Chennai - 60012','Green Technology','Selvakumar','JobSeeker','www.greentechnology.com','greentechnology@gmail.com','vRX20vS5Dt1JDm/SGPm6R9GAMBJMWDBcKU6zn+7hwVE=',9790590476,0,'2019-12-23 13:32:08','greentechnology@gmail.com','Selva','2.jpeg','IT/Software',1,'S',0,NULL,'vRX20vS5Dt1JDm/SGPm6R9GAMBJMWDBcKU6zn+7hwVE=',1,0),
 (4,'No. 3/1418, Annai Velakanai Nagar , Porur, Chennai - 600125 Landmark: NEAR  Southern Skies Tours & Travels','HCL','selva','product Based','www.hcl.com','HCL@gmail.com','AUIZw1GhGpOBkUMLIBrF5cRI8BaCRwvY+J0UdEZzyN4=',9874562124,1,'2020-02-10 15:09:15','HCL@gmail.com','selva','4.jpeg','IT/Software',1,'S',0,NULL,'AUIZw1GhGpOBkUMLIBrF5cRI8BaCRwvY+J0UdEZzyN4=',1,0);
/*!40000 ALTER TABLE `company` ENABLE KEYS */;


--
-- Definition of table `contact_details`
--

DROP TABLE IF EXISTS `contact_details`;
CREATE TABLE `contact_details` (
  `contact_id` int(11) NOT NULL AUTO_INCREMENT,
  `contactNumber` bigint(20) NOT NULL,
  `createdBy` int(11) DEFAULT NULL,
  `createdDate` datetime DEFAULT NULL,
  `email_address` varchar(255) NOT NULL,
  `isDelete` tinyint(1) NOT NULL,
  `message` varchar(255) NOT NULL,
  `modifiedBy` int(11) DEFAULT NULL,
  `modifiedDate` datetime DEFAULT NULL,
  `sending_status` tinyint(1) NOT NULL,
  `your_name` varchar(255) NOT NULL,
  PRIMARY KEY (`contact_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `contact_details`
--

/*!40000 ALTER TABLE `contact_details` DISABLE KEYS */;
/*!40000 ALTER TABLE `contact_details` ENABLE KEYS */;


--
-- Definition of table `course`
--

DROP TABLE IF EXISTS `course`;
CREATE TABLE `course` (
  `course_id` int(11) NOT NULL AUTO_INCREMENT,
  `createdBy` bigint(20) DEFAULT NULL,
  `createdTime` datetime DEFAULT NULL,
  `isDelete` tinyint(1) DEFAULT NULL,
  `modifiedBy` bigint(20) DEFAULT NULL,
  `modifiedTime` datetime DEFAULT NULL,
  `sending_status` tinyint(1) DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `author_name` varchar(255) NOT NULL,
  `course_name` varchar(255) NOT NULL,
  `course_url` varchar(255) DEFAULT NULL,
  `fees` float NOT NULL,
  `image_name` varchar(255) NOT NULL,
  `company_id` int(11) DEFAULT NULL,
  `courseCategoryId` int(11) DEFAULT NULL,
  PRIMARY KEY (`course_id`),
  KEY `FK_47lw7c2xllo11jaos14id9p10` (`company_id`),
  KEY `FK_7gcutmuw6n5hrix48fwrqwej6` (`courseCategoryId`),
  CONSTRAINT `FK_47lw7c2xllo11jaos14id9p10` FOREIGN KEY (`company_id`) REFERENCES `company` (`companyId`),
  CONSTRAINT `FK_7gcutmuw6n5hrix48fwrqwej6` FOREIGN KEY (`courseCategoryId`) REFERENCES `course_category` (`courseCategoryId`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `course`
--

/*!40000 ALTER TABLE `course` DISABLE KEYS */;
INSERT INTO `course` (`course_id`,`createdBy`,`createdTime`,`isDelete`,`modifiedBy`,`modifiedTime`,`sending_status`,`version`,`author_name`,`course_name`,`course_url`,`fees`,`image_name`,`company_id`,`courseCategoryId`) VALUES 
 (1,0,NULL,0,0,'2020-02-22 11:50:24',1,0,'Technical Team','Online Courses to Learn Java for Beginners',NULL,6000,'1.jpeg',1,1),
 (2,0,NULL,0,0,'2020-02-22 11:47:44',1,0,'Technical Team','Online Courses to Learn Java Servlet JSP',NULL,7500,'2.png',1,2),
 (3,0,NULL,0,0,'2020-02-22 13:34:00',1,0,'Technical Team','Online Courses to Learn Spring and HIbernate',NULL,15000,'3.jpeg',1,3),
 (4,0,NULL,0,0,'2020-02-22 13:54:45',1,0,'Technical Team','Online Courses to Learn  Spring Boot and Angular',NULL,20000,'4.png',1,4),
 (5,0,NULL,0,0,'2020-02-22 14:47:17',1,0,'Technical Team','Online Courses to Learn McroService and SpringBoot',NULL,20000,'5.png',1,5);
/*!40000 ALTER TABLE `course` ENABLE KEYS */;


--
-- Definition of table `course_category`
--

DROP TABLE IF EXISTS `course_category`;
CREATE TABLE `course_category` (
  `courseCategoryId` int(11) NOT NULL AUTO_INCREMENT,
  `courseCategoryName` varchar(255) DEFAULT NULL,
  `createdBy` int(11) DEFAULT NULL,
  `createdDate` datetime DEFAULT NULL,
  `isDelete` tinyint(1) DEFAULT NULL,
  `modifiedBy` int(11) DEFAULT NULL,
  `modifiedDate` datetime DEFAULT NULL,
  PRIMARY KEY (`courseCategoryId`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `course_category`
--

/*!40000 ALTER TABLE `course_category` DISABLE KEYS */;
INSERT INTO `course_category` (`courseCategoryId`,`courseCategoryName`,`createdBy`,`createdDate`,`isDelete`,`modifiedBy`,`modifiedDate`) VALUES 
 (1,'mobile development',0,'2020-03-04 11:59:28',0,0,'2020-03-03 23:07:29'),
 (2,'Mechine learning',0,'2020-03-04 11:59:28',0,0,'2020-03-04 11:16:23'),
 (3,'hibernate',0,'2020-03-04 11:59:28',0,0,'2020-03-04 11:12:28'),
 (4,'SpringBoot',0,'2020-03-04 11:59:28',0,0,NULL),
 (5,'microservice',0,'2020-03-04 12:02:49',0,0,NULL),
 (6,'core java',0,'2020-03-04 12:03:04',0,0,NULL);
/*!40000 ALTER TABLE `course_category` ENABLE KEYS */;


--
-- Definition of table `course_details`
--

DROP TABLE IF EXISTS `course_details`;
CREATE TABLE `course_details` (
  `course_detailsId` int(11) NOT NULL AUTO_INCREMENT,
  `classes_schedule` text,
  `createdBy` bigint(20) DEFAULT NULL,
  `createdTime` datetime DEFAULT NULL,
  `curriculum` text,
  `description` text,
  `designed` text,
  `isDelete` tinyint(1) DEFAULT NULL,
  `keyfeatures` text,
  `maxRecord` int(11) NOT NULL,
  `modifiedBy` bigint(20) DEFAULT NULL,
  `modifiedTime` datetime DEFAULT NULL,
  `price` float DEFAULT NULL,
  `recordIndex` int(11) NOT NULL,
  `sending_status` tinyint(1) DEFAULT NULL,
  `course_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`course_detailsId`),
  KEY `FK_k212j2ff44lgymvk5vrrg4i5u` (`course_id`),
  CONSTRAINT `FK_k212j2ff44lgymvk5vrrg4i5u` FOREIGN KEY (`course_id`) REFERENCES `course` (`course_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `course_details`
--

/*!40000 ALTER TABLE `course_details` DISABLE KEYS */;
INSERT INTO `course_details` (`course_detailsId`,`classes_schedule`,`createdBy`,`createdTime`,`curriculum`,`description`,`designed`,`isDelete`,`keyfeatures`,`maxRecord`,`modifiedBy`,`modifiedTime`,`price`,`recordIndex`,`sending_status`,`course_id`) VALUES 
 (1,'In the past, I have shared some great <a href=\"http://www.java67.com/2018/02/3-books-to-learn-java-from-scratch-in.html\">books</a> and <a href=\"http://www.java67.com/2018/01/top-10-java-9-tutorials-and-courses.html\">tutorials</a> to learn Java and in this article, I am going to share some of the <strong>best Java courses beginners</strong> can join to learn Java',0,NULL,'In the past, I have shared some great <a href=\"http://www.java67.com/2018/02/3-books-to-learn-java-from-scratch-in.html\">books</a> and <a href=\"http://www.java67.com/2018/01/top-10-java-9-tutorials-and-courses.html\">tutorials</a> to learn Java and in this article, I am going to share some of the <strong>best Java courses beginners</strong> can join to learn Java','One of the main problems with learning Java is to keep pace with the increasing number of releases. For example, <a href=\"http://www.java67.com/2014/09/top-10-java-8-tutorials-best-of-lot.html\">Java 8</a>\r\n was released in 2014 which completely changed how Java is written and \r\nnow within the last 6 month, we have two major Java releases in the form\r\n of <a href=\"http://javarevisited.blogspot.sg/2018/02/top-5-java-9-courses-to-learn-online.html#axzz5CdW6rG8R\">Java 9</a> and <a href=\"http://javarevisited.blogspot.sg/2018/03/java-10-released-10-new-features-java.html#axzz5ALJyiIAt\">Java 10</a>,\r\n Java 11, 12, and 13. But the good thing is that the core of Java is \r\nstill the same and all its releases are backward compatible.','In the past, I have shared some great <a href=\"http://www.java67.com/2018/02/3-books-to-learn-java-from-scratch-in.html\">books</a> and <a href=\"http://www.java67.com/2018/01/top-10-java-9-tutorials-and-courses.html\">tutorials</a> to learn Java and in this article, I am going to share some of the <strong>best Java courses beginners</strong> can join to learn Java',0,'In the past, I have shared some great <a href=\"http://www.java67.com/2018/02/3-books-to-learn-java-from-scratch-in.html\">books</a> and <a href=\"http://www.java67.com/2018/01/top-10-java-9-tutorials-and-courses.html\">tutorials</a> to learn Java and in this article, I am going to share some of the <strong>best Java courses beginners</strong> can join to learn Java',0,0,NULL,6000,0,1,1),
 (2,'A strong work ethic, willingness to learn, and plenty of excitement about the awesome new programs you?re about to build.',0,NULL,'The core java material you need to learn java development is covered in \r\nthe first seven sections (around 14 hours in total).&nbsp; The&nbsp;Java Basics \r\nare covered in those sections. The rest of the course covers \r\nintermediate, advanced and optional material you do not technically need\r\n to go through.','Java is a high-level <a href=\"https://techterms.com/definition/programming_language\">programming language</a>\r\n developed by Sun Microsystems.  It was originally designed for \r\ndeveloping programs for set-top boxes and handheld devices, but later \r\nbecame a popular choice for creating web <a href=\"https://techterms.com/definition/application\">applications</a>.','This course is designed to give you the Java skills you need to get a \r\njob as a Java developer.&nbsp; By the end of the course you will understand \r\nJava extremely well and be able to build your own Java apps and be \r\nproductive as a software developer. ',0,'java Servlet Jsp<br>',0,0,NULL,7500,0,1,2),
 (3,'<span class=\"ui_qtext_rendered_qtext\">Spring is a more broader framework\r\n and provides replacement to your standard JavaEE components including \r\nSession beans, Servlets and JSP?s. However there are many more projects \r\nin Spring ecosystems to support all kind of requirements.</span><br><strong></strong>',0,NULL,'<span class=\"ui_qtext_rendered_qtext\">Spring is a more broader framework\r\n and provides replacement to your standard JavaEE components including \r\nSession beans, Servlets and JSP?s. However there are many more projects \r\nin Spring ecosystems to support all kind of requirements.</span>','Spring is an enterprise Java framework. It was designed to simplify Java\r\n EE development and make developers more productive. Spring makes use of\r\n Inversion of Control and Dependency Injection to promote good software \r\ncoding practices and speed up development time.','<strong>This course includes mini-courses on Maven, Spring Security, \r\nSpring REST and Spring Boot. These mini-courses are designed to help you\r\n quickly get started with Maven, Spring Security,</strong><strong></strong>',0,'Java Spring Hibernate<br>',0,0,NULL,15000,0,1,3),
 (4,'<ul><li>I</li><li>ngular 8 (the previous version for Angular 6 is also available)</li><li>Scheduler UI built using DayPilot&nbsp;<a href=\"https://doc.daypilot.org/scheduler/angular-2/\">Angular Scheduler</a>&nbsp;component</li><li>Resources (rows) and events are loaded from the backend using REST HTTP call</li><li>Supports event creating using drag and drop</li><li>Event moving using drag and drop</li><li>Includes a trial version of&nbsp;<a href=\"https://javascript.daypilot.org/\">DayPilot Pro for JavaScript</a>&nbsp;(see License below)</li></ul>',0,NULL,'Spring Boot and Angular form a powerful tandem that works great for developing web applications with a minimal footprint. In this tutorial, we\'ll use Spring Boot for implementing a RESTful backend, and Angular for creating a JavaScript-based frontend<br>','<p>Welcome to this Amazing Course on <strong>Full Stack Web Development with Angular and Spring Boot</strong>. This course is designed to be a <strong>Perfect First Step as an Introduction to Angular and Full Stack Development </strong>for Java &amp; Spring Developers.</p><p><strong>Zero Experience</strong> with <strong>Angular, TypeScript and Modern&nbsp;JavaScript</strong>?&nbsp;&nbsp; &nbsp; <strong>No Problem. </strong>&nbsp;Start Learning Now!</p>','<span class=\"ILfuVd NA6bn\"><span class=\"e24Kjd\"><b>Spring boot</b> is a module of <b>spring</b> framework which is used to create stand-alone, production-grade <b>Spring</b> based Applications with minimum programmer\'s efforts. ... The main concept behind <b>spring boot</b> is to avoid lot of boilerplate code and configuration to improve development</span></span>',0,'<ul><li>Implemented in Java</li><li>Uses Spring Boot framework</li><li>Set of simple REST/JSON endpoints</li><li>In-memory H2 database that is initialized (schema) on startup automatically</li><li>Hibernate/JPA is used for ORM</li></ul>',0,0,NULL,20000,0,1,4),
 (5,'Independent means services don?t know about each other implementation. \r\nThey can get tested, deployed, and maintained independently.',0,NULL,'Instead of duplicating a full system in order to handle a higher demand,\r\n in microservices architectures, only the parts which need more \r\nresources need to be scaled. Software can be decoupled and maintenance \r\nof software is getting easier. Every developer who had to update a \r\nhibernate version in a monolithic application knows the pain of keeping a\r\n monolith up to date and reducing technical debt. With microservices, \r\nyou can do this step by step, service by service.','<p id=\"3d33\" class=\"hh hi ed hu hj b ew hk ey hl hm hn ho hp hq hr hs dv\" data-selectable-paragraph=\"\"><mark class=\"xd xe nh\">A microservice is an engineering approach focused on </mark><mark class=\"xd xe nh\"><strong class=\"hj ht\">decomposing</strong></mark><mark class=\"xd xe nh\"> applications into </mark><mark class=\"xd xe nh\"><strong class=\"hj ht\">single-function</strong></mark><mark class=\"xd xe nh\"> modules with </mark><mark class=\"xd xe nh\"><strong class=\"hj ht\">well-defined interfaces</strong></mark><mark class=\"xd xe nh\"> which are </mark><mark class=\"xd xe nh\"><strong class=\"hj ht\">independent</strong></mark><mark class=\"xd xe nh\">ly deployed and operated by </mark><mark class=\"xd xe nh\"><strong class=\"hj ht\">small teams</strong></mark><mark class=\"xd xe nh\"> who own the </mark><mark class=\"xd xe nh\"><strong class=\"hj ht\">entire lifecycle </strong></mark><mark class=\"xd xe nh\">of the service.</mark></p>','Microservices accelerate delivery by <strong class=\"hj ht\">minimizing communication</strong> and coordination between people while reducing<strong class=\"hj ht\"> the scope and risk of change</strong>.',0,'The team is responsible for the entire lifecycle of the service; from \r\ncoding, testing, staging, deploying, debugging, maintaining.',0,0,NULL,20000,0,1,5);
/*!40000 ALTER TABLE `course_details` ENABLE KEYS */;


--
-- Definition of table `course_metatag`
--

DROP TABLE IF EXISTS `course_metatag`;
CREATE TABLE `course_metatag` (
  `course_id` int(11) NOT NULL,
  `metatag_id` bigint(20) NOT NULL,
  KEY `FK_jvl3kj2emw3tesottg3l58r8t` (`metatag_id`),
  KEY `FK_1wlhx0xvm9beaqfn3jlr2h68t` (`course_id`),
  CONSTRAINT `FK_1wlhx0xvm9beaqfn3jlr2h68t` FOREIGN KEY (`course_id`) REFERENCES `course` (`course_id`),
  CONSTRAINT `FK_jvl3kj2emw3tesottg3l58r8t` FOREIGN KEY (`metatag_id`) REFERENCES `metatag` (`metatag_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `course_metatag`
--

/*!40000 ALTER TABLE `course_metatag` DISABLE KEYS */;
INSERT INTO `course_metatag` (`course_id`,`metatag_id`) VALUES 
 (1,1),
 (1,3),
 (1,4);
/*!40000 ALTER TABLE `course_metatag` ENABLE KEYS */;


--
-- Definition of table `course_register`
--

DROP TABLE IF EXISTS `course_register`;
CREATE TABLE `course_register` (
  `coursereg_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `candidatename` varchar(45) NOT NULL,
  `createdTime` datetime DEFAULT NULL,
  `emailaddress` varchar(255) NOT NULL,
  `isActive` tinyint(1) NOT NULL,
  `isDelete` tinyint(1) NOT NULL,
  `mobileNumber` bigint(20) NOT NULL,
  `modifiedTime` datetime DEFAULT NULL,
  `professional` varchar(255) NOT NULL,
  `scheme` varchar(255) NOT NULL,
  `course_id` int(11) DEFAULT NULL,
  `company_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`coursereg_id`),
  KEY `FK_tbjws3m0ukdgub265fvehi1lb` (`course_id`),
  KEY `FK_kc90d1vq8oj1v20exs35awu51` (`company_id`),
  CONSTRAINT `FK_kc90d1vq8oj1v20exs35awu51` FOREIGN KEY (`company_id`) REFERENCES `company` (`companyId`),
  CONSTRAINT `FK_tbjws3m0ukdgub265fvehi1lb` FOREIGN KEY (`course_id`) REFERENCES `course` (`course_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `course_register`
--

/*!40000 ALTER TABLE `course_register` DISABLE KEYS */;
/*!40000 ALTER TABLE `course_register` ENABLE KEYS */;


--
-- Definition of table `customer`
--

DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer` (
  `customer_Id` int(11) NOT NULL AUTO_INCREMENT,
  `createdBy` bigint(20) DEFAULT NULL,
  `createdTime` datetime DEFAULT NULL,
  `isDelete` tinyint(1) DEFAULT NULL,
  `modifiedBy` bigint(20) DEFAULT NULL,
  `modifiedTime` datetime DEFAULT NULL,
  `sending_status` tinyint(1) DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `address` varchar(255) NOT NULL,
  `customer_type` varchar(255) NOT NULL,
  `dateOf_birth` datetime NOT NULL,
  `email_id` varchar(255) NOT NULL,
  `first_name` varchar(255) NOT NULL,
  `industry` varchar(255) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `mobile_number` bigint(20) NOT NULL,
  `specialization` varchar(255) NOT NULL,
  `whatsapp_number` bigint(20) NOT NULL,
  `company_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`customer_Id`),
  KEY `FK_3p6aseus32dkj14j7akd84qif` (`company_id`),
  CONSTRAINT `FK_3p6aseus32dkj14j7akd84qif` FOREIGN KEY (`company_id`) REFERENCES `company` (`companyId`)
) ENGINE=InnoDB AUTO_INCREMENT=1307 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `customer`
--

/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` (`customer_Id`,`createdBy`,`createdTime`,`isDelete`,`modifiedBy`,`modifiedTime`,`sending_status`,`version`,`address`,`customer_type`,`dateOf_birth`,`email_id`,`first_name`,`industry`,`last_name`,`mobile_number`,`specialization`,`whatsapp_number`,`company_id`) VALUES 
 (1,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','akankshakhan1997@gmail.com','Akanksha Khan','IT/Software','Akanksha Khan',7358729054,'Technology Traning',7358729054,1),
 (2,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','shivamjha0306@gmail.com','Shivam Jha','IT/Software','Shivam Jha',7550170475,'Technology Traning',7550170475,1),
 (3,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','selvatopper8@gmail.com','Selvakumar P','IT/Software','Selvakumar P',8610185187,'Technology Traning',8610185187,1),
 (4,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','itsmesharma.utkarsh26@gmail.com','Utkarsh Sharma','IT/Software','Utkarsh Sharma',8947940671,'Technology Traning',8947940671,1),
 (5,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','chbmsivanagasai@gmail.com','Chunduru B M Siva Naga Sai','IT/Software','Chunduru B M Siva Naga Sai',9490787815,'Technology Traning',9490787815,1),
 (6,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','maddinaprudhviram@gmail.com','Maddina Prudhvi Ram','IT/Software','Maddina Prudhvi Ram',9493221501,'Technology Traning',9493221501,1),
 (7,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','nikhilgarakapati2829@gmail.com','Nikhil Garakapati','IT/Software','Nikhil Garakapati',9952907025,'Technology Traning',9952907025,1),
 (8,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','nikhil.kumaran.2011@gmail.com','Nikhil Kumaran','IT/Software','Nikhil Kumaran',9962142337,'Technology Traning',9962142337,1),
 (9,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','brighta.bta@gmail.com','S.Emerald Shirley Brighta','IT/Software','S.Emerald Shirley Brighta',8344363522,'Technology Traning',8344363522,1),
 (10,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','sowmiyap83@gmail.com','Sowmiya K','IT/Software','Sowmiya K',8940494365,'Technology Traning',8940494365,1),
 (11,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','chilukurineelima168@gmail.com','Neelima Chilukuri','IT/Software','Neelima Chilukuri',7032368383,'Technology Traning',7032368383,1),
 (12,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','alagualex888@gmail.com','P.L.Alagammai Palani','IT/Software','P.L.Alagammai Palani',9940359159,'Technology Traning',9940359159,1),
 (13,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','premalancy@gmail.com','A.Prema Kumari','IT/Software','A.Prema Kumari',9884855210,'Technology Traning',9884855210,1),
 (14,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Coimbatore','Jobseeker','2019-11-28 10:16:31','surekaece123@gmail.com','M.Sureka','IT/Software','M.Sureka',7358931967,'Technology Traning',7358931967,1),
 (15,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','nishal.gajendiran@gmail.com','Nishal .G','IT/Software','Nishal .G',9962279797,'Technology Traning',9962279797,1),
 (16,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','bagavathramachandran1996@gmail.com','Bagavath Kumar','IT/Software','Bagavath Kumar',9940248637,'Technology Traning',9940248637,1),
 (17,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Madurai','Jobseeker','2019-11-28 10:16:31','suriyadevi904@gmail.com','Suriyadevi T.R','IT/Software','Suriyadevi T.R',9942163418,'Technology Traning',9942163418,1),
 (18,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Coimbatore','Jobseeker','2019-11-28 10:16:31','psravani@karunya.edu.in','Pasupuleti Sravani','IT/Software','Pasupuleti Sravani',8122061729,'Technology Traning',8122061729,1),
 (19,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','r.srinivasan912@gmail.com','Srinivasan Rajendran','IT/Software','Srinivasan Rajendran',9159162176,'Technology Traning',9159162176,1),
 (20,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','poorneshk6@gmail.com','Ch.Poornesh Kumar','IT/Software','Ch.Poornesh Kumar',7032849283,'Technology Traning',7032849283,1),
 (21,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','venkatachalam4200@gmail.com','Kuppusamy Venkatachalam','IT/Software','Kuppusamy Venkatachalam',9788149193,'Technology Traning',9788149193,1),
 (22,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','ujjwalmishra777@gmail.com','Ujjwal Vats','IT/Software','Ujjwal Vats',8124551050,'Technology Traning',8124551050,1),
 (23,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','sharonmadras@yahoo.com','Sharon Mathew','IT/Software','Sharon Mathew',9840664363,'Technology Traning',9840664363,1),
 (24,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','abhinayjami@gmail.com','Jami Abhinay','IT/Software','Jami Abhinay',9494070422,'Technology Traning',9494070422,1),
 (25,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','snehal.maheshwari14@gmail.com','Snehal Maheshwari','IT/Software','Snehal Maheshwari',9003169724,'Technology Traning',9003169724,1),
 (26,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','jaffarsadik7358@gmail.com','MOHAMMED SOWBAR SADIK','IT/Software','MOHAMMED SOWBAR SADIK',7358418405,'Technology Traning',7358418405,1),
 (27,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','ajayjoe1398@gmail.com','Ajay Joe B','IT/Software','Ajay Joe B',9677145843,'Technology Traning',9677145843,1),
 (28,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','shobhitgoel_ra@srmuniv.edu.in','Shobhit Goel','IT/Software','Shobhit Goel',8754561771,'Technology Traning',8754561771,1),
 (29,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','bensonxaviertx@gmail.com','Benson T X','IT/Software','Benson T X',9489006159,'Technology Traning',9489006159,1),
 (30,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','suparthjain_su@srmuniv.edu.in','Suparth Jain','IT/Software','Suparth Jain',7987110035,'Technology Traning',7987110035,1),
 (31,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','suparthjain_su@srmuniv.edu.in','Vamshi Krishna','IT/Software','Vamshi Krishna',9677233988,'Technology Traning',9677233988,1),
 (32,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','ashutoshpanda_de@srmuniv.edu.in','Ashutosh Panda','IT/Software','Ashutosh Panda',9962253013,'Technology Traning',9962253013,1),
 (33,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','srivignesh08@yahoo.com','Srivignesh V','IT/Software','Srivignesh V',7010353402,'Technology Traning',7010353402,1),
 (34,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','aviyad1997@gmail.com','Avinash Kumar','IT/Software','Avinash Kumar',8267006990,'Technology Traning',8267006990,1),
 (35,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Coimbatore','Jobseeker','2019-11-28 10:16:31','muruga893@gmail.com','Murugananth M','IT/Software','Murugananth M',9003595127,'Technology Traning',9003595127,1),
 (36,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','mahesh30.wari@gmail.com','A.Essaki Rajan','IT/Software','A.Essaki Rajan',8903254938,'Technology Traning',8903254938,1),
 (37,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Coimbatore','Jobseeker','2019-11-28 10:16:31','sowndharya1143@gmail.com','Sowndharya Krishnasamy','IT/Software','Sowndharya Krishnasamy',9487647470,'Technology Traning',9487647470,1),
 (38,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','naveenkumarmano1997@gmail.com','Naveen Kumar.M','IT/Software','Naveen Kumar.M',9952551102,'Technology Traning',9952551102,1),
 (39,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','nandhinisk56@gmail.com','Nandhini S','IT/Software','Nandhini S',7904419892,'Technology Traning',7904419892,1),
 (40,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','sabeermohamed2696@gmail.com','Sabeer Mohamed Z','IT/Software','Sabeer Mohamed Z',8608060751,'Technology Traning',8608060751,1),
 (41,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','p1301sahay@gmail.com','Piyush Sahay','IT/Software','Piyush Sahay',8090922831,'Technology Traning',8090922831,1),
 (42,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','kaushikdhans@gmail.com','Kaushik D','IT/Software','Kaushik D',7305579655,'Technology Traning',7305579655,1),
 (43,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','sekard785@gmail.com','Dhana sekar Rajendran','IT/Software','Dhana sekar Rajendran',8300851948,'Technology Traning',8300851948,1),
 (44,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Coimbatore','Jobseeker','2019-11-28 10:16:31','viswanathang5298@gmail.com','Viswanathan G','IT/Software','Viswanathan G',9842974263,'Technology Traning',9842974263,1),
 (45,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','vickeyreddy598@gmail.com','Vivek Reddy','IT/Software','Vivek Reddy',9490161613,'Technology Traning',9490161613,1),
 (46,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','aishukeerthiar@gmail.com','Keerthi A.R','IT/Software','Keerthi A.R',9566108290,'Technology Traning',9566108290,1),
 (47,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','prabhu.mariappan.007@gmail.com','Prabhu Mariappan','IT/Software','Prabhu Mariappan',9629349316,'Technology Traning',9629349316,1),
 (48,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','Santhoshboxes96@gmail.com','Ragilla Santhosh Kumar','IT/Software','Ragilla Santhosh Kumar',9618790414,'Technology Traning',9618790414,1),
 (49,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','tdpavithra24@gmail.com','Pavithra D','IT/Software','Pavithra D',8610993729,'Technology Traning',8610993729,1),
 (50,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','roopendra.153@gmail.com','Roopendra Yadav','IT/Software','Roopendra Yadav',8077114904,'Technology Traning',8077114904,1),
 (51,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','jeevithag412@gmail.com','G Jeevitha','IT/Software','G Jeevitha',9710277957,'Technology Traning',9710277957,1),
 (52,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','rajtheleo47@gmail.com','Rajkumar S','IT/Software','Rajkumar S',8667264226,'Technology Traning',8667264226,1),
 (53,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','abhijeetkumar9470@gmail.com','Abhijit Kumar Gupta','IT/Software','Abhijit Kumar Gupta',8340483845,'Technology Traning',8340483845,1),
 (54,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','mchandru8998@gmail.com','Chandru M','IT/Software','Chandru M',7092085921,'Technology Traning',7092085921,1),
 (55,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','ashwinsashi98@gmail.com','Ashwin S','IT/Software','Ashwin S',8939208537,'Technology Traning',8939208537,1),
 (56,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','santoshk2820338@gmail.com','Santosh Pawar','IT/Software','Santosh Pawar',8790128228,'Technology Traning',8790128228,1),
 (57,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','yaitsmejoe2129@yahoo.com','Sasi Kumar','IT/Software','Sasi Kumar',9962784977,'Technology Traning',9962784977,1),
 (58,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Coimbatore','Jobseeker','2019-11-28 10:16:31','monarai1999@gmail.com','Mohanapriya M','IT/Software','Mohanapriya M',8124343656,'Technology Traning',8124343656,1),
 (59,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Coimbatore','Jobseeker','2019-11-28 10:16:31','15euit047@skcet.ac.in','Karthik T','IT/Software','Karthik T',9092865750,'Technology Traning',9092865750,1),
 (60,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','antara12.deb97@gmail.com','Antara Debnath','IT/Software','Antara Debnath',7005240367,'Technology Traning',7005240367,1),
 (61,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','rajendransiva220@gmail.com','Rajendran .S','IT/Software','Rajendran .S',9514427395,'Technology Traning',9514427395,1),
 (62,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','abhibuddyofficial@gmail.com','Abhishek Kumar','IT/Software','Abhishek Kumar',7903371522,'Technology Traning',7903371522,1),
 (63,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','brijeshpratap716@gmail.com','Brijesh Pratap Singh','IT/Software','Brijesh Pratap Singh',7017739637,'Technology Traning',7017739637,1),
 (64,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','aminar301@gmail.com','M.Aminal Rose','IT/Software','M.Aminal Rose',9944002444,'Technology Traning',9944002444,1),
 (65,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','vignesh.raj03@gmail.com','Vigneshwaran R','IT/Software','Vigneshwaran R',9444843651,'Technology Traning',9444843651,1),
 (66,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','mpriya1204@gmail.com','Mohana Priya','IT/Software','Mohana Priya',9940507255,'Technology Traning',9940507255,1),
 (67,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','amirthavenkat77@gmail.com','Amirtha Venkataramani','IT/Software','Amirtha Venkataramani',7010309614,'Technology Traning',7010309614,1),
 (68,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','priyareddykonatala9899@gmail.com','Konatala Pushpa','IT/Software','Konatala Pushpa',7358599328,'Technology Traning',7358599328,1),
 (69,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','santhanakrishnanstark@gmail.com','Santhanakrishnan M','IT/Software','Santhanakrishnan M',7708923153,'Technology Traning',7708923153,1),
 (70,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','yuvarani2301@hotmail.com','Yuvarani','IT/Software','Yuvarani',8778902619,'Technology Traning',8778902619,1),
 (71,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Madurai','Jobseeker','2019-11-28 10:16:31','harishmakm1997@gmail.com','Harishma K.M','IT/Software','Harishma K.M',9865362930,'Technology Traning',9865362930,1),
 (72,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','aamansrivastava25@gmail.com','Aman Srivastava','IT/Software','Aman Srivastava',9884214221,'Technology Traning',9884214221,1),
 (73,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','alkesh_mukesh@srmuniv.edu.in','Alkesh Kumrawat','IT/Software','Alkesh Kumrawat',9500166458,'Technology Traning',9500166458,1),
 (74,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','prakashprakasam116@gmail.com','Prakash S','IT/Software','Prakash S',9566040269,'Technology Traning',9566040269,1),
 (75,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Coimbatore','Jobseeker','2019-11-28 10:16:31','priyacbe155@gmail.com','Priyadharshini Sivakumar','IT/Software','Priyadharshini Sivakumar',9894708463,'Technology Traning',9894708463,1),
 (76,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','abipandiyaraj1996@gmail.com','Abinaya P','IT/Software','Abinaya P',8754555372,'Technology Traning',8754555372,1),
 (77,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','mhtkmr74@gmail.com','Mohit Kumar','IT/Software','Mohit Kumar',9551773563,'Technology Traning',9551773563,1),
 (78,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','madhu301297@gmail.com','Madhumitha V','IT/Software','Madhumitha V',9003194868,'Technology Traning',9003194868,1),
 (79,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','rohinselva01@gmail.com','Rohin Selva','IT/Software','Rohin Selva',9920703151,'Technology Traning',9920703151,1),
 (80,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','tamil260696@yahoo.com','S. Tamilarasi','IT/Software','S. Tamilarasi',9976696312,'Technology Traning',9976696312,1),
 (81,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','t4sdmiceshapna@gmail.com','Shabna raja','IT/Software','Shabna raja',9043705703,'Technology Traning',9043705703,1),
 (82,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','vencut7698@gmail.com','K Venkatesh','IT/Software','K Venkatesh',7401090808,'Technology Traning',7401090808,1),
 (83,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','saideep.chennupati@gmail.com','Chnnupati Moorthy','IT/Software','Chnnupati Moorthy',9566221594,'Technology Traning',9566221594,1),
 (84,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','buildinglifeforgiri@gmail.com','Giri Raj','IT/Software','Giri Raj',8667303282,'Technology Traning',8667303282,1),
 (85,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','nehasharma.te@gmail.com','Neha Sharma','IT/Software','Neha Sharma',9962255471,'Technology Traning',9962255471,1),
 (86,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','subramanian.ntwengr@gmail.com','Subramanian As','IT/Software','Subramanian As',9976340731,'Technology Traning',9976340731,1),
 (87,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','mohankumarchb11@gmail.com','Mohan Kumar Arunagiri','IT/Software','Mohan Kumar Arunagiri',8220530301,'Technology Traning',8220530301,1),
 (88,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','jeganuday23@gmail.com','Jegan Udayashankar','IT/Software','Jegan Udayashankar',7358272379,'Technology Traning',7358272379,1),
 (89,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','shreyasuman0703@gmail.com','Shreya Suman','IT/Software','Shreya Suman',9790721592,'Technology Traning',9790721592,1),
 (90,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','bhawnabhatt101094@gmail.com','Bhawna Bhatt','IT/Software','Bhawna Bhatt',9309803474,'Technology Traning',9309803474,1),
 (91,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','monicamadhu1997@gmail.com','B.Monica','IT/Software','B.Monica',7904073719,'Technology Traning',7904073719,1),
 (92,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','soundarya9791093107@gmail.com','Soundarya Dash','IT/Software','Soundarya Dash',9791093107,'Technology Traning',9791093107,1),
 (93,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','sarun0529@gmail.com','Arun Selvam','IT/Software','Arun Selvam',9962712134,'Technology Traning',9962712134,1),
 (94,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','mshekhar1002@gmail.com','Mayank Shekhar','IT/Software','Mayank Shekhar',9962259335,'Technology Traning',9962259335,1),
 (95,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','mail2saianand@gmail.com','Anand Krishnasamy','IT/Software','Anand Krishnasamy',9942211444,'Technology Traning',9942211444,1),
 (96,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','sushanthikamathivanan@gmail.com','Sushanthika M','IT/Software','Sushanthika M',9884807424,'Technology Traning',9884807424,1),
 (97,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','harikrish12498@gmail.com','Hari Krishnan.R','IT/Software','Hari Krishnan.R',8667493272,'Technology Traning',8667493272,1),
 (98,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','vishnuvarthanm11@gmail.com','Vishnuvarthan Manickam','IT/Software','Vishnuvarthan Manickam',8883357024,'Technology Traning',8883357024,1),
 (99,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Coimbatore','Jobseeker','2019-11-28 10:16:31','akhilgowri1998@gmail.com','Akhil Gowrisankar Menon','IT/Software','Akhil Gowrisankar Menon',8754134638,'Technology Traning',8754134638,1),
 (100,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','venkat71025@gmail.com','Venkatesh Harikrishnan','IT/Software','Venkatesh Harikrishnan',9600002307,'Technology Traning',9600002307,1),
 (101,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','fazilsha1197@gmail.com','Fazil Sha','IT/Software','Fazil Sha',7094892306,'Technology Traning',7094892306,1),
 (102,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','umamaheshwariethurajan@gmail.com','Umamaheshwari E','IT/Software','Umamaheshwari E',7092555157,'Technology Traning',7092555157,1),
 (103,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','yogeshab469@gmail.com','Yogeshwar Singh','IT/Software','Yogeshwar Singh',8667250982,'Technology Traning',8667250982,1),
 (104,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','khosla.himanshu96@gmail.com','Himanshu Khosla','IT/Software','Himanshu Khosla',9988773070,'Technology Traning',9988773070,1),
 (105,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','haripoornithra@gmail.com','Poornima Venkatraman','IT/Software','Poornima Venkatraman',9789020910,'Technology Traning',9789020910,1),
 (106,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','siranjeevi18997@gmail.com','Siranjeevi S','IT/Software','Siranjeevi S',8682843718,'Technology Traning',8682843718,1),
 (107,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','narenycj@gmail.com','Narendra Kumar M','IT/Software','Narendra Kumar M',9962199740,'Technology Traning',9962199740,1),
 (108,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','sharathshaihjhu16@gmail.com','Sarath Kumar M S','IT/Software','Sarath Kumar M S',7092332732,'Technology Traning',7092332732,1),
 (109,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','rahul.suresh307@gmail.com','Rahul S','IT/Software','Rahul S',9940657205,'Technology Traning',9940657205,1),
 (110,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Coimbatore','Jobseeker','2019-11-28 10:16:31','ssowndarya054@gmail.com','Sowndarya Suresh','IT/Software','Sowndarya Suresh',8012957794,'Technology Traning',8012957794,1),
 (111,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','ankitayaya@gmail.com','Divya Rani G','IT/Software','Divya Rani G',9080851851,'Technology Traning',9080851851,1),
 (112,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','rajashreekmr@gmail.com','Rajashree Kmr','IT/Software','Rajashree Kmr',8870082939,'Technology Traning',8870082939,1),
 (113,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Coimbatore','Jobseeker','2019-11-28 10:16:31','kowsigopal2911@gmail.com','Kausalya G','IT/Software','Kausalya G',8220773106,'Technology Traning',8220773106,1),
 (114,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','swarnalatha0823@gmail.com','Swarnalatha. V','IT/Software','Swarnalatha. V',9514727238,'Technology Traning',9514727238,1),
 (115,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','rkamnag@gmail.com','Kamnag R','IT/Software','Kamnag R',8608352226,'Technology Traning',8608352226,1),
 (116,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','karthicethi22@gmail.com','Karthkeyan E','IT/Software','Karthkeyan E',7305611437,'Technology Traning',7305611437,1),
 (117,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','yogeshnandha1997@yahoo.com','Yogesh N','IT/Software','Yogesh N',8015684676,'Technology Traning',8015684676,1),
 (118,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','maheshanand.media@gmail.com','Pedapati Mahesh Anand','IT/Software','Pedapati Mahesh Anand',9398137383,'Technology Traning',9398137383,1),
 (119,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','bandarutejaswini_ba@srmuniv.edu.in','Bandaru Tejaswini','IT/Software','Bandaru Tejaswini',8754561871,'Technology Traning',8754561871,1),
 (120,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Coimbatore','Jobseeker','2019-11-28 10:16:31','revathims1998@gmail.com','Revathi Ms','IT/Software','Revathi Ms',9047829584,'Technology Traning',9047829584,1),
 (121,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','krishnasridharan1993@gmail.com','Krishna Kumar S','IT/Software','Krishna Kumar S',8056564680,'Technology Traning',8056564680,1),
 (122,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','balaji222096@gmail.com','Balaji Balaraman','IT/Software','Balaji Balaraman',9976868922,'Technology Traning',9976868922,1),
 (123,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','safiyakayu@gmail.com','Kayathiri Chandrasekaran','IT/Software','Kayathiri Chandrasekaran',8098015778,'Technology Traning',8098015778,1),
 (124,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','poojas2897@gmail.com','Pooja S','IT/Software','Pooja S',8939491437,'Technology Traning',8939491437,1),
 (125,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','ritikjain51@gmail.com','Ritik Jain','IT/Software','Ritik Jain',9785225224,'Technology Traning',9785225224,1),
 (126,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','hariigokul1616@gmail.com','Hariharan Ganeshkumar','IT/Software','Hariharan Ganeshkumar',9003204979,'Technology Traning',9003204979,1),
 (127,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','lakshminagarjuna08@gmail.com','Lakshmi Prasanna U','IT/Software','Lakshmi Prasanna U',9791146520,'Technology Traning',9791146520,1),
 (128,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','jennifakarunya97@gmail.com','Jennifa J','IT/Software','Jennifa J',9710580804,'Technology Traning',9710580804,1),
 (129,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','tamilarasi337@gmail.com','A.Tamilarasi','IT/Software','A.Tamilarasi',7358509100,'Technology Traning',7358509100,1),
 (130,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','','Goutham B','IT/Software','Goutham B',7845237915,'Technology Traning',7845237915,1),
 (131,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','dhivyaharshu1@gmail.com','Dhivya M','IT/Software','Dhivya M',7358208425,'Technology Traning',7358208425,1),
 (132,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','priyadharshini199818@gmail.com','Priyadharshini A','IT/Software','Priyadharshini A',8778083062,'Technology Traning',8778083062,1),
 (133,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','aravindraina712917@gmail.com','Pethaperumal L','IT/Software','Pethaperumal L',8122719654,'Technology Traning',8122719654,1),
 (134,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','rohitsrivastav29198@gmail.com','Rohit Srivastav','IT/Software','Rohit Srivastav',8292322867,'Technology Traning',8292322867,1),
 (135,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','shahulhameed22497@gmail.com','Sahul Hameed Hasikeen','IT/Software','Sahul Hameed Hasikeen',9944542047,'Technology Traning',9944542047,1),
 (136,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','srilekaece18@gmail.com','Srileka Rajendren','IT/Software','Srileka Rajendren',9095368636,'Technology Traning',9095368636,1),
 (137,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Madurai','Jobseeker','2019-11-28 10:16:31','sathyasweet1357@gmail.com','Sathya K.','IT/Software','Sathya K.',9597137434,'Technology Traning',9597137434,1),
 (138,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Coimbatore','Jobseeker','2019-11-28 10:16:31','vigneshsankar761998@gmail.com','Vignesh S','IT/Software','Vignesh S',5296374190,'Technology Traning',5296374190,1),
 (139,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','mosesdurai1998@gmail.com','Muthudurai G','IT/Software','Muthudurai G',9087418863,'Technology Traning',9087418863,1),
 (140,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','nadhiya197@gmail.com','Nadhiya Namachivayam','IT/Software','Nadhiya Namachivayam',8667651601,'Technology Traning',8667651601,1),
 (141,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','naveen.prithw@gmail.com','Murugan P','IT/Software','Murugan P',7094829582,'Technology Traning',7094829582,1),
 (142,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','jsivaraman18@gmail.com','J.Sivaraman','IT/Software','J.Sivaraman',9842958544,'Technology Traning',9842958544,1),
 (143,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','achhint26oct@gmail.com','Achhint Kumar','IT/Software','Achhint Kumar',9962104592,'Technology Traning',9962104592,1),
 (144,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Coimbatore','Jobseeker','2019-11-28 10:16:31','sathriyansam@yahoo.com','Sathriyan V','IT/Software','Sathriyan V',8508402025,'Technology Traning',8508402025,1),
 (145,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','pdkoushik1998@gmail.com','Perumalla Durga Koushik','IT/Software','Perumalla Durga Koushik',9840311436,'Technology Traning',9840311436,1),
 (146,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','mdriyaz.vi@gmail.com','Riyaz B','IT/Software','Riyaz B',8248322913,'Technology Traning',8248322913,1),
 (147,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','divadiva2706@gmail.com','Divya Murugan','IT/Software','Divya Murugan',8015488473,'Technology Traning',8015488473,1),
 (148,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','goelrounak@gmail.com','Rounak Goel','IT/Software','Rounak Goel',8003699943,'Technology Traning',8003699943,1),
 (149,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','lokeshvenkat36@gmail.com','Lokesh Venkat','IT/Software','Lokesh Venkat',9551341991,'Technology Traning',9551341991,1),
 (150,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','pavimathavi@gmail.com','Pavithra Srinivasan','IT/Software','Pavithra Srinivasan',8778610846,'Technology Traning',8778610846,1),
 (151,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','oviyatamil1998@gmail.com','Tamiloviya T','IT/Software','Tamiloviya T',9840458220,'Technology Traning',9840458220,1),
 (152,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','gurumanoj27@gmail.com','Gurumanojkumar M','IT/Software','Gurumanojkumar M',7092249992,'Technology Traning',7092249992,1),
 (153,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','Kumarmechanical007@gmail.com','Prasanna Kumar.K.N.','IT/Software','Prasanna Kumar.K.N.',7502904742,'Technology Traning',7502904742,1),
 (154,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','praveenbabu717@gmail.com','Praveen Babu','IT/Software','Praveen Babu',7550119528,'Technology Traning',7550119528,1),
 (155,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','deepandeepan02@gmail.com','Deepan. G','IT/Software','Deepan. G',9790378978,'Technology Traning',9790378978,1),
 (156,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','aparnavsubash@gmail.com','Aparna Subash.V','IT/Software','Aparna Subash.V',8681873801,'Technology Traning',8681873801,1),
 (157,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','oviahkrishna18@gmail.com','Oviah Krishna','IT/Software','Oviah Krishna',7639899752,'Technology Traning',7639899752,1),
 (158,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','rsyvishnu@gmail.com','Yokeshwar Rs','IT/Software','Yokeshwar Rs',9445814024,'Technology Traning',9445814024,1),
 (159,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','devapriya251997@gmail.com','Devapriya Elumalai','IT/Software','Devapriya Elumalai',8190908811,'Technology Traning',8190908811,1),
 (160,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','prabasuganajish@gmail.com','Prabavathi M S','IT/Software','Prabavathi M S',9944397620,'Technology Traning',9944397620,1),
 (161,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','youvee31197@gmail.com','Yuvashree Kanniappan','IT/Software','Yuvashree Kanniappan',8124386584,'Technology Traning',8124386584,1),
 (162,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','shreeharini1998@gmail.com','Shree Harini','IT/Software','Shree Harini',9791137893,'Technology Traning',9791137893,1),
 (163,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','preethikab1997@gmail.com','Preethika Balasubramaniyan','IT/Software','Preethika Balasubramaniyan',9629175627,'Technology Traning',9629175627,1),
 (164,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','gokula22101997@gmail.com','Gokulapriya Jayakumar','IT/Software','Gokulapriya Jayakumar',8939699859,'Technology Traning',8939699859,1),
 (165,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','priyadharshinipd2123@gmail.com','Priya Dharshini','IT/Software','Priya Dharshini',8300832524,'Technology Traning',8300832524,1),
 (166,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','yogeswari.0718@gmail.com','Yogeswari Ganesan','IT/Software','Yogeswari Ganesan',9176464001,'Technology Traning',9176464001,1),
 (167,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','thana22dhana@gmail.com','Dhanalakshmi Jayakumar','IT/Software','Dhanalakshmi Jayakumar',7338931928,'Technology Traning',7338931928,1),
 (168,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','ponakila1998@gmail.com','Ponakila V','IT/Software','Ponakila V',7373337076,'Technology Traning',7373337076,1),
 (169,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','jayarashya@gmail.com','Jayasurya Tamilarasan','IT/Software','Jayasurya Tamilarasan',7871059336,'Technology Traning',7871059336,1),
 (170,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','madhuraidas00@gmail.com','Madhurai Das','IT/Software','Madhurai Das',7358623625,'Technology Traning',7358623625,1),
 (171,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','somasekhar903@gmail.com','P Somasekhar','IT/Software','P Somasekhar',9912276508,'Technology Traning',9912276508,1),
 (172,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','dherasatpet@gmail.com','Dherasa Durairaju','IT/Software','Dherasa Durairaju',8940346432,'Technology Traning',8940346432,1),
 (173,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','hullah17@gmail.com','H. Syed Ahmed Hussain','IT/Software','H. Syed Ahmed Hussain',9123550581,'Technology Traning',9123550581,1),
 (174,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','ajaytripuraneni1998@outlook.com','Tripuraneni Ajay','IT/Software','Tripuraneni Ajay',7981857539,'Technology Traning',7981857539,1),
 (175,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','saravanabhagyasree@gmail.com','A S Bhagya Sree','IT/Software','A S Bhagya Sree',7397368462,'Technology Traning',7397368462,1),
 (176,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','bhanupalagati@gmail.com','Palagati Bhanu Prakash Reddy','IT/Software','Palagati Bhanu Prakash Reddy',9493667363,'Technology Traning',9493667363,1),
 (177,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','jayntypasupathy@gmail.com','Nishanka Bharadwaj','IT/Software','Nishanka Bharadwaj',9094069598,'Technology Traning',9094069598,1),
 (178,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','maheshwer95@gmail.com','Seethalla Maheswar','IT/Software','Seethalla Maheswar',7036230054,'Technology Traning',7036230054,1),
 (179,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','varalakshmi9790@gmail.com','B Varalakshmi','IT/Software','B Varalakshmi',7401551742,'Technology Traning',7401551742,1),
 (180,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','azhageswari02@gmail.com','Azhageswari T','IT/Software','Azhageswari T',9710113419,'Technology Traning',9710113419,1),
 (181,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','fauzhiyafathima02@gmail.com','Fauzhiya Fathima','IT/Software','Fauzhiya Fathima',9551673731,'Technology Traning',9551673731,1),
 (182,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','shreeranjani435@gmail.com','Shree Ranjani B','IT/Software','Shree Ranjani B',9789910131,'Technology Traning',9789910131,1),
 (183,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','vamshi9677@gmail.com','Krishna Agulla','IT/Software','Krishna Agulla',8919858476,'Technology Traning',8919858476,1),
 (184,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','rkycse065@gmail.com','Ranjeet Kumar','IT/Software','Ranjeet Kumar',8144355898,'Technology Traning',8144355898,1),
 (185,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','jananiselva16@gmail.com','Janani S','IT/Software','Janani S',8072935264,'Technology Traning',8072935264,1),
 (186,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','prad3011@gmail.com','Pradeep Venugopal','IT/Software','Pradeep Venugopal',7339051744,'Technology Traning',7339051744,1),
 (187,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Madurai','Jobseeker','2019-11-28 10:16:31','divyaayyachamy@gmail.com','Divya A','IT/Software','Divya A',7708526907,'Technology Traning',7708526907,1),
 (188,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','patienceyadav@gmail.com','Dheeraj Yadav','IT/Software','Dheeraj Yadav',8954542929,'Technology Traning',8954542929,1),
 (189,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','santhosh4851141@gmail.com','Samuel Santhosh','IT/Software','Samuel Santhosh',9566279541,'Technology Traning',9566279541,1),
 (190,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','poovizhi0901@gmail.com','Poovizhi Ezhilarasan','IT/Software','Poovizhi Ezhilarasan',7092246292,'Technology Traning',7092246292,1),
 (191,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','azhagusundharam.s@gmail.com','Azhagusundharam Sanjeevikumar','IT/Software','Azhagusundharam Sanjeevikumar',9944613374,'Technology Traning',9944613374,1),
 (192,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','shubham.shubham.bajpayee@gmail.com','Shubham Bajpayee','IT/Software','Shubham Bajpayee',9566189204,'Technology Traning',9566189204,1),
 (193,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','venusandhiya@gmail.com','Sandhiya Venu','IT/Software','Sandhiya Venu',7904279492,'Technology Traning',7904279492,1),
 (194,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','','Tanuja Devi B','IT/Software','Tanuja Devi B',9791066045,'Technology Traning',9791066045,1),
 (195,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','synikhilkumar@gmail.com','Nikhil Kumar Saurav','IT/Software','Nikhil Kumar Saurav',9113104735,'Technology Traning',9113104735,1),
 (196,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','devadharsh.1610@gmail.com','Devadharshini R','IT/Software','Devadharshini R',7010023880,'Technology Traning',7010023880,1),
 (197,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','gayathris33@ymail.com','Gayathri S','IT/Software','Gayathri S',9940764761,'Technology Traning',9940764761,1),
 (198,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','mansoorb010997@gmail.com','Mansoor Basha Shaik','IT/Software','Mansoor Basha Shaik',7418571867,'Technology Traning',7418571867,1),
 (199,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','abhishekrajkumar0503@gmail.com','Abhishek Raj Kumar S','IT/Software','Abhishek Raj Kumar S',6380822417,'Technology Traning',6380822417,1),
 (200,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','joyfullcathrine@gmail.com','Emmanuel Rex.S','IT/Software','Emmanuel Rex.S',6379748043,'Technology Traning',6379748043,1),
 (201,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','barathidbr37@gmail.com','Barathidasan D','IT/Software','Barathidasan D',9159821655,'Technology Traning',9159821655,1),
 (202,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','honey.harika15@gmail.com','Vaishnavi Modem','IT/Software','Vaishnavi Modem',9566068381,'Technology Traning',9566068381,1),
 (203,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','sarathabavin@gmail.com','Sarathabavin M','IT/Software','Sarathabavin M',9944044218,'Technology Traning',9944044218,1),
 (204,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','99mickymouse99@gmail.com','Arunkumar Govindaraj','IT/Software','Arunkumar Govindaraj',9159312829,'Technology Traning',9159312829,1),
 (205,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','shalinilinisha.shalini@gmail.com','Deepak Kumar','IT/Software','Deepak Kumar',8807244769,'Technology Traning',8807244769,1),
 (206,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Coimbatore','Jobseeker','2019-11-28 10:16:31','sraj04429@gmail.com','Sachin Kumar','IT/Software','Sachin Kumar',7904254272,'Technology Traning',7904254272,1),
 (207,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','lokesh.8809@gmail.com','Lokesh Parthiban','IT/Software','Lokesh Parthiban',8124290459,'Technology Traning',8124290459,1),
 (208,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','keerthana.kpm2014@gmail.com','Keerthana.S','IT/Software','Keerthana.S',7358882711,'Technology Traning',7358882711,1),
 (209,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','953615104054@ritrjpm.ac.in','Vasugi G','IT/Software','Vasugi G',9566608884,'Technology Traning',9566608884,1),
 (210,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Coimbatore','Jobseeker','2019-11-28 10:16:31','p.n.chandru16@gmail.com','Chandra Sekar','IT/Software','Chandra Sekar',8072038018,'Technology Traning',8072038018,1),
 (211,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','mirunalinisehar@gmail.com','Mirunalini K S','IT/Software','Mirunalini K S',8678982442,'Technology Traning',8678982442,1),
 (212,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','mirunalinisehar@gmail.com','G.Yoga Lakshmi','IT/Software','G.Yoga Lakshmi',9551553906,'Technology Traning',9551553906,1),
 (213,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','praveenramulachu@gmail.com','Praveen Kumar M','IT/Software','Praveen Kumar M',9600368972,'Technology Traning',9600368972,1),
 (214,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','saikumarjay1729@gmail.com','Sai Kumar Rayankula','IT/Software','Sai Kumar Rayankula',9705530170,'Technology Traning',9705530170,1),
 (215,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','supratikdevm96@gmail.com','Supratik Chatterjee','IT/Software','Supratik Chatterjee',8939266175,'Technology Traning',8939266175,1),
 (216,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','muruganrekha53@gmail.com','Rekha Murugan','IT/Software','Rekha Murugan',8608974639,'Technology Traning',8608974639,1),
 (217,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','aarthi18feb@gmail.com','Aarthi Balakrishnan','IT/Software','Aarthi Balakrishnan',9551532344,'Technology Traning',9551532344,1),
 (218,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','suryasweety157@gmail.com','Surya Umapathy','IT/Software','Surya Umapathy',7708473792,'Technology Traning',7708473792,1),
 (219,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','janani.shanbaha@gmail.com','Janani Shanbaha Kanagaraj','IT/Software','Janani Shanbaha Kanagaraj',7550170430,'Technology Traning',7550170430,1),
 (220,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','arunmukundhan98@gmail.com','K Arun Kumar','IT/Software','K Arun Kumar',8610278208,'Technology Traning',8610278208,1),
 (221,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','rahulshindey04@gmail.com','Rahul Shindey','IT/Software','Rahul Shindey',9840353011,'Technology Traning',9840353011,1),
 (222,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','krishnakrishchinna@gmail.com','Venkata Krishna Reddy Peram','IT/Software','Venkata Krishna Reddy Peram',9703102043,'Technology Traning',9703102043,1),
 (223,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','archanabsc2504@gmail.com','Archana H','IT/Software','Archana H',9791514601,'Technology Traning',9791514601,1),
 (224,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','dwivedisumit943@gmail.com','Sumit Ranjan Dwivedi','IT/Software','Sumit Ranjan Dwivedi',9944845642,'Technology Traning',9944845642,1),
 (225,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','deepikaravi1216@gmail.com','Deepika R','IT/Software','Deepika R',9943657707,'Technology Traning',9943657707,1),
 (226,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Madurai','Jobseeker','2019-11-28 10:16:31','ajaytrr66@gmail.com','Ajay Tr','IT/Software','Ajay Tr',9585590450,'Technology Traning',9585590450,1),
 (227,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Coimbatore','Jobseeker','2019-11-28 10:16:31','manitilakan@gmail.com','K.A.Nandhini','IT/Software','K.A.Nandhini',9500787790,'Technology Traning',9500787790,1),
 (228,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','dmonishapreethi95@gmail.com','Monisha Preethi.D','IT/Software','Monisha Preethi.D',9940478436,'Technology Traning',9940478436,1),
 (229,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','karthikkevin83@gmail.com','Karthikeyan S','IT/Software','Karthikeyan S',9940190071,'Technology Traning',9940190071,1),
 (230,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','sathyak470@gmail.com','Sathya Narayanan.R','IT/Software','Sathya Narayanan.R',8939757290,'Technology Traning',8939757290,1),
 (231,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','reshma.ravi17698@gmail.com','Reshma R','IT/Software','Reshma R',9841149402,'Technology Traning',9841149402,1),
 (232,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','manimozhimsr@gmail.com','Manimozhi Rajendran','IT/Software','Manimozhi Rajendran',7418435557,'Technology Traning',7418435557,1),
 (233,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','sindhujatanuja1714@gmail.com','Gvs Sindhuja','IT/Software','Gvs Sindhuja',7010999068,'Technology Traning',7010999068,1),
 (234,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','smartsurender1@gmail.com','Surender . S','IT/Software','Surender . S',9894078414,'Technology Traning',9894078414,1),
 (235,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','santhoshselvaraj15@gmail.com','Santhosh S','IT/Software','Santhosh S',8807533656,'Technology Traning',8807533656,1),
 (236,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','dilip00192@gmail.com','D.Dilip Kumar','IT/Software','D.Dilip Kumar',8220881576,'Technology Traning',8220881576,1),
 (237,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','ranchana_elankumaran@srmuniv.edu.in','Ranchana Kumar','IT/Software','Ranchana Kumar',9940943733,'Technology Traning',9940943733,1),
 (238,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','mahesh.varma97@gmail.com','Mahesh Varma','IT/Software','Mahesh Varma',7010964871,'Technology Traning',7010964871,1),
 (239,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','nandhini16pavithra@gmail.com','Pavithra Nandhini R','IT/Software','Pavithra Nandhini R',8838045323,'Technology Traning',8838045323,1),
 (240,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','indhumathy3114@gmail.com','Indumathy M','IT/Software','Indumathy M',9941181852,'Technology Traning',9941181852,1),
 (241,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','gayathrirn3@gmail.com','Gayathri Ravichandran','IT/Software','Gayathri Ravichandran',9894234745,'Technology Traning',9894234745,1),
 (242,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Coimbatore','Jobseeker','2019-11-28 10:16:31','gaurav.kumar8462@gmail.com','Gaurav Kumar','IT/Software','Gaurav Kumar',9572343425,'Technology Traning',9572343425,1),
 (243,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','pskarthisri245@gmail.com','P Karthigha','IT/Software','P Karthigha',8056429207,'Technology Traning',8056429207,1),
 (244,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','saikamesh014@gmail.com','Sai Kamesh Darimadugu','IT/Software','Sai Kamesh Darimadugu',7358511658,'Technology Traning',7358511658,1),
 (245,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','aashish.raghunath2014@vit.ac.in','Aashish Raghunath','IT/Software','Aashish Raghunath',9500130966,'Technology Traning',9500130966,1),
 (246,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','divyachristydb@gmail.com','Divya Bharathi.R','IT/Software','Divya Bharathi.R',9710168774,'Technology Traning',9710168774,1),
 (247,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','merlin.rc.in@gmail.com','Merlin R','IT/Software','Merlin R',4651252352,'Technology Traning',4651252352,1),
 (248,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','ajay.g.2015.it@ritchennai.edu.in','Ajay G','IT/Software','Ajay G',8489231681,'Technology Traning',8489231681,1),
 (249,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','mspoornima30@gmail.com','Poornima Munuswamy','IT/Software','Poornima Munuswamy',9566095147,'Technology Traning',9566095147,1),
 (250,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','umayal04p1997@gmail.com','Umayal Veerappan','IT/Software','Umayal Veerappan',9445362180,'Technology Traning',9445362180,1),
 (251,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','harshitasaxena0404@gmail.com','Harshita Saxena','IT/Software','Harshita Saxena',8077725730,'Technology Traning',8077725730,1),
 (252,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','ppriya584@gmail.com','Priyanka Srinivasan','IT/Software','Priyanka Srinivasan',9962975518,'Technology Traning',9962975518,1),
 (253,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','rajesh91097@gmail.com','Rajesh P','IT/Software','Rajesh P',7358353600,'Technology Traning',7358353600,1),
 (254,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Coimbatore','Jobseeker','2019-11-28 10:16:31','divyadivi.n1996@gmail.com','Divyabharathi N','IT/Software','Divyabharathi N',7871513625,'Technology Traning',7871513625,1),
 (255,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','aashiqui481@gmail.com','Mohamed Aashiq A','IT/Software','Mohamed Aashiq A',9840134160,'Technology Traning',9840134160,1),
 (256,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','sathishmitnick@gmail.com','Sathish Kumar J','IT/Software','Sathish Kumar J',8248297084,'Technology Traning',8248297084,1),
 (257,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Coimbatore','Jobseeker','2019-11-28 10:16:31','ulaganathan.un56@gmail.com','Ulaganathan Palanisamy','IT/Software','Ulaganathan Palanisamy',8428797899,'Technology Traning',8428797899,1),
 (258,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','aparblack@gmail.com','Aparajitha Ramesh','IT/Software','Aparajitha Ramesh',9789006069,'Technology Traning',9789006069,1),
 (259,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','Stellaesake@gmail.com','Stella Mary. E','IT/Software','Stella Mary. E',8939386537,'Technology Traning',8939386537,1),
 (260,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','prabhuraj727@gmail.com','Prabhuraj P','IT/Software','Prabhuraj P',9677051487,'Technology Traning',9677051487,1),
 (261,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','amrithabaloo@gmail.com','Amritha Balasubramanian','IT/Software','Amritha Balasubramanian',8300169641,'Technology Traning',8300169641,1),
 (262,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','hashera97@gmail.com','Hashera Parveen','IT/Software','Hashera Parveen',6381093688,'Technology Traning',6381093688,1),
 (263,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Coimbatore','Jobseeker','2019-11-28 10:16:31','sivaec95@gmail.com','Sivasubbiramaniyan B','IT/Software','Sivasubbiramaniyan B',9626423773,'Technology Traning',9626423773,1),
 (264,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','acadheena@gmail.com','Adheena Chandran','IT/Software','Adheena Chandran',7338963956,'Technology Traning',7338963956,1),
 (265,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','meenumoni12@gmail.com','Meena N','IT/Software','Meena N',9551424702,'Technology Traning',9551424702,1),
 (266,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','tiwariswapnil97@gmail.com','Swapnil Tiwari','IT/Software','Swapnil Tiwari',7982193117,'Technology Traning',7982193117,1),
 (267,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','aishwaryaram18091997@gmail.com','Aishwarya Ramu','IT/Software','Aishwarya Ramu',7358628241,'Technology Traning',7358628241,1),
 (268,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','janaoct10seenu@gmail.com','Janardhanan Krishnamoorthy','IT/Software','Janardhanan Krishnamoorthy',8015277006,'Technology Traning',8015277006,1),
 (269,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','vishal0072009@gmail.com','Vishal Singh','IT/Software','Vishal Singh',8894364313,'Technology Traning',8894364313,1),
 (270,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','nkalyanchakravarthi640@gmail.com','Narne Kalyan Chakravarthy','IT/Software','Narne Kalyan Chakravarthy',8309274232,'Technology Traning',8309274232,1),
 (271,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','vkk07.v@gmail.com','Karthikeyan V','IT/Software','Karthikeyan V',9790863163,'Technology Traning',9790863163,1),
 (272,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','aanandvasanth@gmail.com','Vasanth V','IT/Software','Vasanth V',7094097094,'Technology Traning',7094097094,1),
 (273,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','raghavsankarv@gmail.com','Raghav Sanakar','IT/Software','Raghav Sanakar',9445898598,'Technology Traning',9445898598,1),
 (274,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','akhilmallidi.98@gmail.com','Mallidi Akhil Reddy','IT/Software','Mallidi Akhil Reddy',9840290129,'Technology Traning',9840290129,1),
 (275,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','aahelghosh@gmail.com','Aahel Ghosh','IT/Software','Aahel Ghosh',9790725883,'Technology Traning',9790725883,1),
 (276,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','kaustubhalpha@gmail.com','Kaustubh Khede','IT/Software','Kaustubh Khede',8327469385,'Technology Traning',8327469385,1),
 (277,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','kjeni97@gmail.com','Karthick U','IT/Software','Karthick U',9840179891,'Technology Traning',9840179891,1),
 (278,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','muthuanu2830@gmail.com','Anusiya .S','IT/Software','Anusiya .S',8870884390,'Technology Traning',8870884390,1),
 (279,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','sharmilaamaghaadevan@gmail.com','Sharmilaa Devi M','IT/Software','Sharmilaa Devi M',9003086778,'Technology Traning',9003086778,1),
 (280,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','cse2pasaga@gmail.com','Bharath Kumar.G','IT/Software','Bharath Kumar.G',9840356029,'Technology Traning',9840356029,1),
 (281,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','kallamramakrishna6@mail.com','Rama Krishna','IT/Software','Rama Krishna',8919103546,'Technology Traning',8919103546,1),
 (282,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','ragavaraaj@gmail.com','Ragavaraaj Dhurairaj','IT/Software','Ragavaraaj Dhurairaj',8939002148,'Technology Traning',8939002148,1);
INSERT INTO `customer` (`customer_Id`,`createdBy`,`createdTime`,`isDelete`,`modifiedBy`,`modifiedTime`,`sending_status`,`version`,`address`,`customer_type`,`dateOf_birth`,`email_id`,`first_name`,`industry`,`last_name`,`mobile_number`,`specialization`,`whatsapp_number`,`company_id`) VALUES 
 (283,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','divyaalakshmi.s.2015.cse@rajalakshmi.edu.in','Divyaa Lakshmi.S','IT/Software','Divyaa Lakshmi.S',9710279719,'Technology Traning',9710279719,1),
 (284,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','srbagavathkumar@gmail.com','Bagavath Kumar','IT/Software','Bagavath Kumar',7092269515,'Technology Traning',7092269515,1),
 (285,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','as082570@gmail.com','Abhishek Singh','IT/Software','Abhishek Singh',9043783161,'Technology Traning',9043783161,1),
 (286,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','nigheydhanakumar@gmail.com','Nighedhana K','IT/Software','Nighedhana K',8124440245,'Technology Traning',8124440245,1),
 (287,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','hildajames.h@gmail.com','Hilda James','IT/Software','Hilda James',9840710608,'Technology Traning',9840710608,1),
 (288,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','debpratimkar@gmail.com','Debapratim Kar','IT/Software','Debapratim Kar',8240729594,'Technology Traning',8240729594,1),
 (289,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','gnamit2727@gmail.com','Amit Gupta','IT/Software','Amit Gupta',9755809767,'Technology Traning',9755809767,1),
 (290,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','anantagupta_ra@srmuniv.edu.in','Ananta Gupta','IT/Software','Ananta Gupta',9840363028,'Technology Traning',9840363028,1),
 (291,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','amansmyles7@gmail.com','Aman Kumar','IT/Software','Aman Kumar',9962418536,'Technology Traning',9962418536,1),
 (292,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','amalaselvakumar17@gmail.com','Amala S','IT/Software','Amala S',8608440320,'Technology Traning',8608440320,1),
 (293,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','saravananvignesh2212@gmail.com','Vignesh Saravanan','IT/Software','Vignesh Saravanan',8939255990,'Technology Traning',8939255990,1),
 (294,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','sreenidhikalyan@gmail.com','Sreenidhi Kalyanaraman','IT/Software','Sreenidhi Kalyanaraman',9444818245,'Technology Traning',9444818245,1),
 (295,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','palakgupta_ni@srmuniv.edu.in','Palak Gupta','IT/Software','Palak Gupta',9566191975,'Technology Traning',9566191975,1),
 (296,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','merlinnrc@gmail.com','Merlin R','IT/Software','Merlin R',8883992308,'Technology Traning',8883992308,1),
 (297,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','vickyashrag@gmail.com','Vignesh Karthick','IT/Software','Vignesh Karthick',7010147781,'Technology Traning',7010147781,1),
 (298,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','lavan.sabgee@gmail.com','Lavanya Sabapathy','IT/Software','Lavanya Sabapathy',9841519390,'Technology Traning',9841519390,1),
 (299,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','sakchisingh77@gmail.com','Sakshi Singh','IT/Software','Sakshi Singh',7372816216,'Technology Traning',7372816216,1),
 (300,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','s.m.mukeshkumar1997@gmail.com','S.M.Mukesh Kumar','IT/Software','S.M.Mukesh Kumar',9789129881,'Technology Traning',9789129881,1),
 (301,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','bharat133996@gmail.com','Bharat Dhyani','IT/Software','Bharat Dhyani',7010348821,'Technology Traning',7010348821,1),
 (302,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','guptaprateek976@gmail.com','Prateek Gupta','IT/Software','Prateek Gupta',9962165492,'Technology Traning',9962165492,1),
 (303,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','amzadkcp@gmail.com','Pathan Amzad Khan','IT/Software','Pathan Amzad Khan',9952931286,'Technology Traning',9952931286,1),
 (304,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','mageshbala708@gmail.com','S. Balaji','IT/Software','S. Balaji',9791045500,'Technology Traning',9791045500,1),
 (305,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Coimbatore','Jobseeker','2019-11-28 10:16:31','meganishabalraj@gmail.com','B. Meganisha','IT/Software','B. Meganisha',9443843633,'Technology Traning',9443843633,1),
 (306,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','svgcpm@gmail.com','Niharika Salin','IT/Software','Niharika Salin',8124344165,'Technology Traning',8124344165,1),
 (307,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Madurai','Jobseeker','2019-11-28 10:16:31','parkaviaasaithambi@gmail.com','Parkavi A','IT/Software','Parkavi A',8098919726,'Technology Traning',8098919726,1),
 (308,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','gowtham.iss.008@gmail.com','Immaneni Satya Sai Gowtham','IT/Software','Immaneni Satya Sai Gowtham',9176515518,'Technology Traning',9176515518,1),
 (309,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','ezhilram23@gmail.com','Ezhilarasi R','IT/Software','Ezhilarasi R',8122414051,'Technology Traning',8122414051,1),
 (310,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','anderohith@gmail.com','Ande Rohith','IT/Software','Ande Rohith',9445243393,'Technology Traning',9445243393,1),
 (311,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','datchinamoorthy92@gmail.com','Datchina Moorthy R','IT/Software','Datchina Moorthy R',9988895763,'Technology Traning',9988895763,1),
 (312,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Coimbatore','Jobseeker','2019-11-28 10:16:31','sankaviksgs98@gmail.com','Sankavi Samiyappan','IT/Software','Sankavi Samiyappan',9566433909,'Technology Traning',9566433909,1),
 (313,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','swedhadhamodaran0810@gmail.com','Swedha D','IT/Software','Swedha D',8825872870,'Technology Traning',8825872870,1),
 (314,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','mail2likitha.ch@gmail.com','Likitha. Ch','IT/Software','Likitha. Ch',9514821228,'Technology Traning',9514821228,1),
 (315,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Coimbatore','Jobseeker','2019-11-28 10:16:31','geetha31101995@gmail.com','Geethanjali B','IT/Software','Geethanjali B',9894097906,'Technology Traning',9894097906,1),
 (316,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','rathirakesh7@gmail.com','Rakesh Rathi','IT/Software','Rakesh Rathi',9176764540,'Technology Traning',9176764540,1),
 (317,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','showkathbegum@gmail.com','Showkath Begum','IT/Software','Showkath Begum',9952917628,'Technology Traning',9952917628,1),
 (318,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','renusubbaraj1996@gmail.com','Renuka Devi S','IT/Software','Renuka Devi S',9840250176,'Technology Traning',9840250176,1),
 (319,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','mary.reshma@yahoo.in','Mary Beni Reshma A. B.','IT/Software','Mary Beni Reshma A. B.',9382210476,'Technology Traning',9382210476,1),
 (320,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','mathuchitra2@gmail.com','Mathangi N','IT/Software','Mathangi N',7401437736,'Technology Traning',7401437736,1),
 (321,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','tsmaditya@gmail.com','Tsm Aditya','IT/Software','Tsm Aditya',8940321883,'Technology Traning',8940321883,1),
 (322,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','balajianand1997@gmail.com','Balaji A','IT/Software','Balaji A',8190801220,'Technology Traning',8190801220,1),
 (323,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','karapareddybhargavi@gmail.com','Karapareddy Bhargavi','IT/Software','Karapareddy Bhargavi',9550996063,'Technology Traning',9550996063,1),
 (324,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','nagudholaanusha@gmail.com','Nagudhola Anusha','IT/Software','Nagudhola Anusha',8985662682,'Technology Traning',8985662682,1),
 (325,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','singh.prashantraj123@gmail.com','Prashant Raj','IT/Software','Prashant Raj',9962285032,'Technology Traning',9962285032,1),
 (326,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','sharmanikitanikki010@gmail.com','Nikita Sharma','IT/Software','Nikita Sharma',8179312394,'Technology Traning',8179312394,1),
 (327,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','venumadhav867@gmail.com','Venu Madhav Reddy Bavanam','IT/Software','Venu Madhav Reddy Bavanam',9908912150,'Technology Traning',9908912150,1),
 (328,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','deepikaramasani@gmail.com','Ramasani Deepika','IT/Software','Ramasani Deepika',7095941997,'Technology Traning',7095941997,1),
 (329,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','bab.ap204@gmail.com','Babloo Kumar','IT/Software','Babloo Kumar',7717781817,'Technology Traning',7717781817,1),
 (330,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','loki44025@gmail.com','Lokesh B','IT/Software','Lokesh B',9949828170,'Technology Traning',9949828170,1),
 (331,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','vinayseelam.98@gmail.com','Seelam Vinay Kumar Reddy','IT/Software','Seelam Vinay Kumar Reddy',9952903676,'Technology Traning',9952903676,1),
 (332,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','duraimax57@gmail.com','Duraipandian K','IT/Software','Duraipandian K',9551717317,'Technology Traning',9551717317,1),
 (333,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','sud_kan2004@yahoo.com','Sudhanshu Kanth','IT/Software','Sudhanshu Kanth',8754512435,'Technology Traning',8754512435,1),
 (334,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','vaddireddysandeep2826@gmail.com','Vadireddy Sandeep Reddy','IT/Software','Vadireddy Sandeep Reddy',9952098096,'Technology Traning',9952098096,1),
 (335,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','vdeepu607@gmail.com','Vayigandla Deepika','IT/Software','Vayigandla Deepika',7989109041,'Technology Traning',7989109041,1),
 (336,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','sandeepvillasandy@gmail.com','Villa Siva Naga Anand Sandeep','IT/Software','Villa Siva Naga Anand Sandeep',7871627696,'Technology Traning',7871627696,1),
 (337,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','lincyshajini@gmail.com','Lincy Jeba Shajini','IT/Software','Lincy Jeba Shajini',8056305764,'Technology Traning',8056305764,1),
 (338,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','rajasweet14@gmail.com','Raja Manikkam','IT/Software','Raja Manikkam',8220604327,'Technology Traning',8220604327,1),
 (339,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','saipreetikakuru@gmail.com','Sai Preeti Kakuru','IT/Software','Sai Preeti Kakuru',9940695509,'Technology Traning',9940695509,1),
 (340,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','kumarbhaskar2009@gmail.com','Adabala Kumar Sri Chandra Bhaskar','IT/Software','Adabala Kumar Sri Chandra Bhaskar',9032891448,'Technology Traning',9032891448,1),
 (341,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','soundarya2871997@gmail.com','Soundarya S','IT/Software','Soundarya S',7904202581,'Technology Traning',7904202581,1),
 (342,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','abiabinaya678@gmail.com','Abinaya. M','IT/Software','Abinaya. M',9715334655,'Technology Traning',9715334655,1),
 (343,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','gunashankar1091@gmail.com','Gunaseelan .J','IT/Software','Gunaseelan .J',9626130642,'Technology Traning',9626130642,1),
 (344,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','saiprasadtirumala.t@gmail.com','T Saiprasad','IT/Software','T Saiprasad',9492704294,'Technology Traning',9492704294,1),
 (345,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','balajisolaikumar@gmail.com','Balaji S','IT/Software','Balaji S',7550206976,'Technology Traning',7550206976,1),
 (346,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Coimbatore','Jobseeker','2019-11-28 10:16:31','poojasetty98@gmail.com','Pooja K','IT/Software','Pooja K',9790461958,'Technology Traning',9790461958,1),
 (347,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','gopalserma109@gmail.com','Sermaraj G','IT/Software','Sermaraj G',9176744647,'Technology Traning',9176744647,1),
 (348,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','dydeepak97@gmail.com','Deepak Yadav','IT/Software','Deepak Yadav',7027366125,'Technology Traning',7027366125,1),
 (349,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','gouthaman52@gmail.com','Gouthaman Cs','IT/Software','Gouthaman Cs',8883045945,'Technology Traning',8883045945,1),
 (350,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','prakharkhandelwal_ra@srmuniv.edu.in','Prakhar Khandelwal','IT/Software','Prakhar Khandelwal',9176104193,'Technology Traning',9176104193,1),
 (351,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','ssanthoshi1904@gmail.com','Santhoshi Sailapathi','IT/Software','Santhoshi Sailapathi',9962334753,'Technology Traning',9962334753,1),
 (352,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','sumithramurugan97@gmail.com','Sumithra. M','IT/Software','Sumithra. M',8754545939,'Technology Traning',8754545939,1),
 (353,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','poorneshwarig03@gmail.com','Poorneshwari .G.','IT/Software','Poorneshwari .G.',7358651365,'Technology Traning',7358651365,1),
 (354,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','vijayraj.1019@gmail.com','Vijay Raj D S','IT/Software','Vijay Raj D S',9940106465,'Technology Traning',9940106465,1),
 (355,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','moniyogesh1203@gmail.com','Monisha M','IT/Software','Monisha M',8056106903,'Technology Traning',8056106903,1),
 (356,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','suryakumar296@gmail.com','Suryakumar R','IT/Software','Suryakumar R',7904632594,'Technology Traning',7904632594,1),
 (357,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','varshitamandem@gmail.com','Varshitha mandem','IT/Software','Varshitha mandem',8056980239,'Technology Traning',8056980239,1),
 (358,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','pendrysahithi97@gmail.com','Pendry Sahithi','IT/Software','Pendry Sahithi',8190858332,'Technology Traning',8190858332,1),
 (359,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','chaitu.sriramaneni@gmail.com','Sriramaneni Chaitanya','IT/Software','Sriramaneni Chaitanya',9566215656,'Technology Traning',9566215656,1),
 (360,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','aravindshadow04@gmail.com','Aravind M','IT/Software','Aravind M',9710554645,'Technology Traning',9710554645,1),
 (361,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Tiruchirappalli','Jobseeker','2019-11-28 10:16:31','gludhayasurya@gmail.com','Udhayakumar G','IT/Software','Udhayakumar G',9787679845,'Technology Traning',9787679845,1),
 (362,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','shubhamsurana1300@gmail.com','Shubham Surana','IT/Software','Shubham Surana',7568333353,'Technology Traning',7568333353,1),
 (363,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','prabodhgupta97@gmail.com','Prabodh Gupta','IT/Software','Prabodh Gupta',9459685200,'Technology Traning',9459685200,1),
 (364,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','ashishbajpayee681@gmail.com','Ashish Bajpayee','IT/Software','Ashish Bajpayee',9566215195,'Technology Traning',9566215195,1),
 (365,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','harurkarthi1997@gmail.com','Karthikeyan M S','IT/Software','Karthikeyan M S',9597674185,'Technology Traning',9597674185,1),
 (366,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','saipraneethbrg19@gmail.com','Sai Praneeth','IT/Software','Sai Praneeth',9700459151,'Technology Traning',9700459151,1),
 (367,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','gayatriagarwal1997@gmail.com','Gayatri Agarwal','IT/Software','Gayatri Agarwal',7358467148,'Technology Traning',7358467148,1),
 (368,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','archanajayanth33@gmail.com','Archana Mathiazhagan','IT/Software','Archana Mathiazhagan',9655671048,'Technology Traning',9655671048,1),
 (369,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','bharathkishore1122@gmail.com','Bharath Kishore','IT/Software','Bharath Kishore',8122113223,'Technology Traning',8122113223,1),
 (370,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','suvalakshmi1188@gmail.com','Suvalakshmi Esaki Muthu','IT/Software','Suvalakshmi Esaki Muthu',9094348163,'Technology Traning',9094348163,1),
 (371,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','abhishek.suresh2503@gmail.com','Abhishek Suresh','IT/Software','Abhishek Suresh',8368104784,'Technology Traning',8368104784,1),
 (372,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','sakthiarumugama@gmail.com','Shaktivel A','IT/Software','Shaktivel A',6380350108,'Technology Traning',6380350108,1),
 (373,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Coimbatore','Jobseeker','2019-11-28 10:16:31','15eumt080@skcet.ac.in','Prabhakar S','IT/Software','Prabhakar S',8344870801,'Technology Traning',8344870801,1),
 (374,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','priyank22259@gmail.com','Priyank Tyagi','IT/Software','Priyank Tyagi',9566214475,'Technology Traning',9566214475,1),
 (375,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','srija7146@gmail.com','S K Srija','IT/Software','S K Srija',9498051612,'Technology Traning',9498051612,1),
 (376,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','dinakar14.ram@gmail.com','Dinakaran. R','IT/Software','Dinakaran. R',9003763810,'Technology Traning',9003763810,1),
 (377,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','shalinisrinivasan97@gmail.com','Shalinipriya Srinivas','IT/Software','Shalinipriya Srinivas',7094754111,'Technology Traning',7094754111,1),
 (378,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','goyalabhay97@gmail.com','Abhay Goyal','IT/Software','Abhay Goyal',9566009329,'Technology Traning',9566009329,1),
 (379,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','naveenrock293@gmail.com','Naveen Venkatesh K','IT/Software','Naveen Venkatesh K',9952011608,'Technology Traning',9952011608,1),
 (380,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','ashutosh123kumar456@gmail.com','Jagnath Shanker','IT/Software','Jagnath Shanker',8608289068,'Technology Traning',8608289068,1),
 (381,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','priyankapagadala25@gmail.com','Priyanka Pagadala','IT/Software','Priyanka Pagadala',8500986689,'Technology Traning',8500986689,1),
 (382,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','santhosh301097@gmail.com','Santhosh T','IT/Software','Santhosh T',7358459025,'Technology Traning',7358459025,1),
 (383,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','shruthi4598@gmail.com','Shruthi Bahavathimurugan','IT/Software','Shruthi Bahavathimurugan',7395871246,'Technology Traning',7395871246,1),
 (384,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','gokulsck12@gmail.com','Gokul Suresh','IT/Software','Gokul Suresh',9176758354,'Technology Traning',9176758354,1),
 (385,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','dlodhi123456@gmail.com','Deepak Lodhi','IT/Software','Deepak Lodhi',9566074726,'Technology Traning',9566074726,1),
 (386,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','n.subhasree@gmail.com','Subhashree N','IT/Software','Subhashree N',9444871519,'Technology Traning',9444871519,1),
 (387,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','naveendevarala@gmail.com','Naveen Babu D','IT/Software','Naveen Babu D',9176421504,'Technology Traning',9176421504,1),
 (388,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','ankitkishore12@gmail.com','Ankit Kishore','IT/Software','Ankit Kishore',9971508288,'Technology Traning',9971508288,1),
 (389,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','arungk784@gmail.com','GK GopalaKrishnan','IT/Software','GK GopalaKrishnan',8220733102,'Technology Traning',8220733102,1),
 (390,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','prkshkmr08@gmail.com','Prakash Kumar','IT/Software','Prakash Kumar',9031232866,'Technology Traning',9031232866,1),
 (391,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','dharini12698@gmail.com','Dharini Priya','IT/Software','Dharini Priya',7598113347,'Technology Traning',7598113347,1),
 (392,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','vigneshthiru519@gmail.com','Vignesh Thirugnanam','IT/Software','Vignesh Thirugnanam',9566219857,'Technology Traning',9566219857,1),
 (393,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','indhuek41197@gmail.com','Indhumathi E','IT/Software','Indhumathi E',7401093880,'Technology Traning',7401093880,1),
 (394,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Madurai','Jobseeker','2019-11-28 10:16:31','yamunaelangovan74@gmail.com','Yamuna E','IT/Software','Yamuna E',9487824905,'Technology Traning',9487824905,1),
 (395,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Madurai','Jobseeker','2019-11-28 10:16:31','vsanjini910@gmail.com','Sanjini Ramesh','IT/Software','Sanjini Ramesh',7094559916,'Technology Traning',7094559916,1),
 (396,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','sarveswarisri8@gmail.com','Sarveswari Sree N','IT/Software','Sarveswari Sree N',7358757644,'Technology Traning',7358757644,1),
 (397,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','crazy.cricky97@gmail.com','Poorvaj Subramaniam .P','IT/Software','Poorvaj Subramaniam .P',9894460937,'Technology Traning',9894460937,1),
 (398,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','shari.prasanth2015@vit.ac.in','S Hariprasanth','IT/Software','S Hariprasanth',9952476506,'Technology Traning',9952476506,1),
 (399,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','vatsaljain96@gmail.com','Vatsal Jain','IT/Software','Vatsal Jain',7550260860,'Technology Traning',7550260860,1),
 (400,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','nigamvaibhav97@gmail.com','Vaibhav Nigam','IT/Software','Vaibhav Nigam',9582020900,'Technology Traning',9582020900,1),
 (401,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','abhinavgupta422@gmail.com','Abhinav Gupta','IT/Software','Abhinav Gupta',9795930632,'Technology Traning',9795930632,1),
 (402,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','gaurav.k.sekhri@gmail.com','Gaurav Sekhri','IT/Software','Gaurav Sekhri',9646074146,'Technology Traning',9646074146,1),
 (403,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','keets5197@gmail.com','Keets M','IT/Software','Keets M',9953990647,'Technology Traning',9953990647,1),
 (404,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','dhanumeenu2607@gmail.com','Dhanswarya Mk','IT/Software','Dhanswarya Mk',7338752352,'Technology Traning',7338752352,1),
 (405,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','akshatakky1997@gmail.com','Akshat Kumar Singh','IT/Software','Akshat Kumar Singh',8056073808,'Technology Traning',8056073808,1),
 (406,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','chiragjain.udr27@gmail.com','Chirag Jain','IT/Software','Chirag Jain',8290755844,'Technology Traning',8290755844,1),
 (407,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','himanshisharma_sa@srmuniv.edu.in','Himanshi Sharma','IT/Software','Himanshi Sharma',9566201212,'Technology Traning',9566201212,1),
 (408,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','anauskadutta2@gmail.com','Anauska Dutta','IT/Software','Anauska Dutta',7358208617,'Technology Traning',7358208617,1),
 (409,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','varunvkgmahajan@gmail.com','Varun Mahajan','IT/Software','Varun Mahajan',9176403552,'Technology Traning',9176403552,1),
 (410,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','achintyapandey419@gmail.com','Achintya Pandey','IT/Software','Achintya Pandey',8754530030,'Technology Traning',8754530030,1),
 (411,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','sumitflk98@gmail.com','Sumit Kumar','IT/Software','Sumit Kumar',7091558499,'Technology Traning',7091558499,1),
 (412,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Coimbatore','Jobseeker','2019-11-28 10:16:31','15s126@psgitech.ac.in','Nikeshh V','IT/Software','Nikeshh V',9597292896,'Technology Traning',9597292896,1),
 (413,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','msg.gayathri@gmail.com','Gayathri . S','IT/Software','Gayathri . S',9840723345,'Technology Traning',9840723345,1),
 (414,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Coimbatore','Jobseeker','2019-11-28 10:16:31','nikithakrishnan555@gmail.com','Nikitha Balakrishnan','IT/Software','Nikitha Balakrishnan',9445998248,'Technology Traning',9445998248,1),
 (415,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','sgowtham033@gmail.com','Gowtham S','IT/Software','Gowtham S',8124861198,'Technology Traning',8124861198,1),
 (416,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','udhaya1198@gmail.com','Udhaya P','IT/Software','Udhaya P',9952946379,'Technology Traning',9952946379,1),
 (417,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','sati.mayank30@gmail.com','Mayank Sati','IT/Software','Mayank Sati',9871114902,'Technology Traning',9871114902,1),
 (418,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','anupallavi6@gmail.com','Pallavi Gujare','IT/Software','Pallavi Gujare',8951309909,'Technology Traning',8951309909,1),
 (419,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','monichandran@yahoo.com','Monika Ravichandran','IT/Software','Monika Ravichandran',9791038710,'Technology Traning',9791038710,1),
 (420,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','vns5733@gmail.com','Vishnu Narayanan S','IT/Software','Vishnu Narayanan S',7338947172,'Technology Traning',7338947172,1),
 (421,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','suraypratap_an@srmuniv.edu.in','Suray Pratap Singh','IT/Software','Suray Pratap Singh',7985344458,'Technology Traning',7985344458,1),
 (422,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','murrishetti.madhu@gmail.com','Madhu Babu','IT/Software','Madhu Babu',9989152039,'Technology Traning',9989152039,1),
 (423,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','sudhagarramesh02@gmail.com','Sudhagar Ramesh','IT/Software','Sudhagar Ramesh',9566952655,'Technology Traning',9566952655,1),
 (424,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','rohitsurya184@gmail.com','Rohit Surya G R','IT/Software','Rohit Surya G R',8883352639,'Technology Traning',8883352639,1),
 (425,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Madurai','Jobseeker','2019-11-28 10:16:31','armunavarhussain.be@gmail.com','Munavar Hussain','IT/Software','Munavar Hussain',9080863425,'Technology Traning',9080863425,1),
 (426,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','dharsajini@gmail.com','Dharsajini R','IT/Software','Dharsajini R',8098770143,'Technology Traning',8098770143,1),
 (427,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','jahnavijothi24@gmail.com','B.Jothi Jahnavi','IT/Software','B.Jothi Jahnavi',9551833062,'Technology Traning',9551833062,1),
 (428,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','shinysangeetha.a@gmail.com','Sangeetha Shiny','IT/Software','Sangeetha Shiny',9841804992,'Technology Traning',9841804992,1),
 (429,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','identifyashok@gmail.com','Ashok Kumar','IT/Software','Ashok Kumar',9444668667,'Technology Traning',9444668667,1),
 (430,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','ruddiran@gmail.com','Ruddiran R','IT/Software','Ruddiran R',9514491311,'Technology Traning',9514491311,1),
 (431,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','savivinay12@gmail.com','Savitha S','IT/Software','Savitha S',9840989745,'Technology Traning',9840989745,1),
 (432,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','kanishkraj1@gmail.com','Kanishk Raj','IT/Software','Kanishk Raj',8754507408,'Technology Traning',8754507408,1),
 (433,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','vmg.harshini@gmail.com','Geetha Harshini','IT/Software','Geetha Harshini',9443090591,'Technology Traning',9443090591,1),
 (434,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','slalithprasad_rama@srmuniv.edu.in','Lalithprasad S','IT/Software','Lalithprasad S',9701118218,'Technology Traning',9701118218,1),
 (435,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','rupikaganesan@gmail.com','R G Rupika','IT/Software','R G Rupika',9500025314,'Technology Traning',9500025314,1),
 (436,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','pabithamani19@gmail.com','Pabitha Mani','IT/Software','Pabitha Mani',9176633016,'Technology Traning',9176633016,1),
 (437,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','nitish0704@gmail.com','Nitish Chhabra','IT/Software','Nitish Chhabra',9631580866,'Technology Traning',9631580866,1),
 (438,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','devishanmugi1997@gmail.com','Devi Shanmugi','IT/Software','Devi Shanmugi',7871112184,'Technology Traning',7871112184,1),
 (439,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','umaretiyayash_ji@srmuniv.edu.in','Yash Umaretiya','IT/Software','Yash Umaretiya',9884208410,'Technology Traning',9884208410,1),
 (440,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','avulavineethchowdary@gmail.com','Avula Vineeth','IT/Software','Avula Vineeth',8374274292,'Technology Traning',8374274292,1),
 (441,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','arthi.krishnan98@gmail.com','Arthi Krishnan','IT/Software','Arthi Krishnan',8608189260,'Technology Traning',8608189260,1),
 (442,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','saxenaakshita30@gmail.com','Akshita Saxena','IT/Software','Akshita Saxena',9087737254,'Technology Traning',9087737254,1),
 (443,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','rajuranjan_sha@srmuniv.edu.in','Raju Ranjan','IT/Software','Raju Ranjan',8299002033,'Technology Traning',8299002033,1),
 (444,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','surya.ela97@gmail.com','Surya Elavazhagan','IT/Software','Surya Elavazhagan',9840363615,'Technology Traning',9840363615,1),
 (445,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','himanshukumawat_ma@srmuniv.edu.in','Himanshu Kumawat','IT/Software','Himanshu Kumawat',8946980895,'Technology Traning',8946980895,1),
 (446,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','harshseth242@gmail.com','Harsh Seth','IT/Software','Harsh Seth',9884206582,'Technology Traning',9884206582,1),
 (447,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','pinakisaha_ab@srmuniv.edu.in','Pinaki Saha','IT/Software','Pinaki Saha',9962260797,'Technology Traning',9962260797,1),
 (448,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','tiruvikraman1998@gmail.com','J Vigneshwaran','IT/Software','J Vigneshwaran',9176639699,'Technology Traning',9176639699,1),
 (449,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','venkatesh.b542@gmail.com','Venkatesh Vanaparthi','IT/Software','Venkatesh Vanaparthi',9492506196,'Technology Traning',9492506196,1),
 (450,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','kiruthikakpr24@gmail.com','R.Kiruthika Ramachandran','IT/Software','R.Kiruthika Ramachandran',9003711393,'Technology Traning',9003711393,1),
 (451,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','clenitiaa@gmail.com','Reni Clenitiaa','IT/Software','Reni Clenitiaa',9176651405,'Technology Traning',9176651405,1),
 (452,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','mteenamutha@gmail.com','Teena M','IT/Software','Teena M',9790339382,'Technology Traning',9790339382,1),
 (453,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','pskumarsrec88@gmail.com','Saravanakumar P','IT/Software','Saravanakumar P',9787577654,'Technology Traning',9787577654,1),
 (454,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','suryamugilan@outlook.com','S Dhamothiran','IT/Software','S Dhamothiran',9843854993,'Technology Traning',9843854993,1),
 (455,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','keshnick@gmail.com','Keshav Madhusudanan','IT/Software','Keshav Madhusudanan',9444073239,'Technology Traning',9444073239,1),
 (456,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','harithameenashi@gmail.com','Haritha Meenashi.S','IT/Software','Haritha Meenashi.S',8939575201,'Technology Traning',8939575201,1),
 (457,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','sagarikasundar1@gmail.com','Sagariga Sundar','IT/Software','Sagariga Sundar',8682026688,'Technology Traning',8682026688,1),
 (458,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','joseph.israel.98284@gmail.com','Isravel J.','IT/Software','Isravel J.',7092750345,'Technology Traning',7092750345,1),
 (459,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','wmrohith@gmail.com','Rohith Kumar W.M','IT/Software','Rohith Kumar W.M',9791123307,'Technology Traning',9791123307,1),
 (460,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','paviraghu_04@yahoo.com','Pavithra Raghavan','IT/Software','Pavithra Raghavan',9551145004,'Technology Traning',9551145004,1),
 (461,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','ritesh.anish@gmail.com','Ritesh Anish','IT/Software','Ritesh Anish',9789038887,'Technology Traning',9789038887,1),
 (462,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','lakshanth2@gmail.com','Lakshanth Thangarajah','IT/Software','Lakshanth Thangarajah',9751019998,'Technology Traning',9751019998,1),
 (463,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','akshayasahas3004@gmail.com','Akshaya S','IT/Software','Akshaya S',9445660672,'Technology Traning',9445660672,1),
 (464,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','haritha.jayaraman@gmail.com','Haritha Jayaraman','IT/Software','Haritha Jayaraman',8778204949,'Technology Traning',8778204949,1),
 (465,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','sowmyakannan97@gmail.com','Sowmya K','IT/Software','Sowmya K',9176445873,'Technology Traning',9176445873,1),
 (466,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','sharadhrajesh4@gmail.com','Sharadh Rajesh','IT/Software','Sharadh Rajesh',9840389751,'Technology Traning',9840389751,1),
 (467,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','iron.krishnan@gmail.com','Srikrishnan S','IT/Software','Srikrishnan S',7358483658,'Technology Traning',7358483658,1),
 (468,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','ronakshankar76@gmail.com','Ronak S R','IT/Software','Ronak S R',8667387492,'Technology Traning',8667387492,1),
 (469,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','nishanishu118@gmail.com','Nisha Nishu','IT/Software','Nisha Nishu',9786129336,'Technology Traning',9786129336,1),
 (470,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','priyarocks451@gmail.com','Shanmuga Priya. R','IT/Software','Shanmuga Priya. R',9080223573,'Technology Traning',9080223573,1),
 (471,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','manipandiya95@gmail.com','Rajeswari M','IT/Software','Rajeswari M',8056947586,'Technology Traning',8056947586,1),
 (472,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','poorvaja2212@gmail.com','Poorvaja Dilip','IT/Software','Poorvaja Dilip',9176035772,'Technology Traning',9176035772,1),
 (473,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','yuvaraninarayanan24@gmail.com','Yuva Rani.N','IT/Software','Yuva Rani.N',9176081839,'Technology Traning',9176081839,1),
 (474,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','premibalamurugan@gmail.com','Premi B','IT/Software','Premi B',9790068264,'Technology Traning',9790068264,1),
 (475,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','balachandarpriya007@gmail.com','Balachandar Dakshinamoorthy','IT/Software','Balachandar Dakshinamoorthy',9840737549,'Technology Traning',9840737549,1),
 (476,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','balasakthi7373@gmail.com','Balasakthi Saravanan S','IT/Software','Balasakthi Saravanan S',7373525525,'Technology Traning',7373525525,1),
 (477,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','aishuabi711@gmail.com','S Aishwarya','IT/Software','S Aishwarya',9840104852,'Technology Traning',9840104852,1),
 (478,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','vsuryakalavelusamy@gmail.com','Suryakala Velusamy','IT/Software','Suryakala Velusamy',9962723442,'Technology Traning',9962723442,1),
 (479,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','jnandini08@gmail.com','Nandita Jain','IT/Software','Nandita Jain',9962328782,'Technology Traning',9962328782,1),
 (480,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','maridossitvpk@gmail.com','Mari Doss','IT/Software','Mari Doss',9789683273,'Technology Traning',9789683273,1),
 (481,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','vr02vijay@gmail.com','Vijay Kannan R','IT/Software','Vijay Kannan R',7092704340,'Technology Traning',7092704340,1),
 (482,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','lokeshgowtham5@gmail.com','S Gowtham','IT/Software','S Gowtham',9884136564,'Technology Traning',9884136564,1),
 (483,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','ganeshmohanraj31@gmail.com','Ganesh Mohanraj','IT/Software','Ganesh Mohanraj',7871195538,'Technology Traning',7871195538,1),
 (484,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','sanjana2095@gmail.com','Sanjana R Joshi','IT/Software','Sanjana R Joshi',9940337233,'Technology Traning',9940337233,1),
 (485,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','ganpatirathia@hotmail.com','Ganpati Rathia','IT/Software','Ganpati Rathia',7358612553,'Technology Traning',7358612553,1),
 (486,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','roopesh.122@gmail.com','Maganti Roopesh','IT/Software','Maganti Roopesh',9894925672,'Technology Traning',9894925672,1),
 (487,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','borisborgohainraj@gmail.com','Boris Raj Borgohain','IT/Software','Boris Raj Borgohain',8471866243,'Technology Traning',8471866243,1),
 (488,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','ak24ashok@gmail.com','B.Ashok Kumar','IT/Software','B.Ashok Kumar',9524523575,'Technology Traning',9524523575,1),
 (489,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','mahyyham1994@gmail.com','U Mageswari','IT/Software','U Mageswari',8754474661,'Technology Traning',8754474661,1),
 (490,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','mayank.agarwala22@gmail.com','Mayank Agarwala','IT/Software','Mayank Agarwala',9566070212,'Technology Traning',9566070212,1),
 (491,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Madurai','Jobseeker','2019-11-28 10:16:31','balanagendran95@gmail.com','Bala Nagendran','IT/Software','Bala Nagendran',8148525301,'Technology Traning',8148525301,1),
 (492,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','sauravsameersahu@gmail.com','Saurav Sahu','IT/Software','Saurav Sahu',8108708691,'Technology Traning',8108708691,1),
 (493,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','jenifdsouzagg@gmail.com','Jenif Dsouza','IT/Software','Jenif Dsouza',8122543527,'Technology Traning',8122543527,1),
 (494,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','afn1614@outlook.com','Afnan Ahmed','IT/Software','Afnan Ahmed',8870616720,'Technology Traning',8870616720,1),
 (495,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','amirtan87@gmail.com','Amir M','IT/Software','Amir M',9047507671,'Technology Traning',9047507671,1),
 (496,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','mohamedrafeek1994@gmail.com','Mohamed Rafeek','IT/Software','Mohamed Rafeek',9677879228,'Technology Traning',9677879228,1),
 (497,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','sensachin84@gmail.com','Senthil Kumar S','IT/Software','Senthil Kumar S',9094111159,'Technology Traning',9094111159,1),
 (498,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','pradeep3093@gmail.com','Pradeep N','IT/Software','Pradeep N',9597810884,'Technology Traning',9597810884,1),
 (499,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Tiruchirappalli','Jobseeker','2019-11-28 10:16:31','aishuzain97@gmail.com','J.Aishwarya Sagaya Rani','IT/Software','J.Aishwarya Sagaya Rani',7010897771,'Technology Traning',7010897771,1),
 (500,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Tiruchirappalli','Jobseeker','2019-11-28 10:16:31','jayadevikrish1998@gmail.com','Jayadevi R','IT/Software','Jayadevi R',7598003306,'Technology Traning',7598003306,1),
 (501,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Tiruchirappalli','Jobseeker','2019-11-28 10:16:31','arunbalajik47@gmail.com','Arunbalaji K','IT/Software','Arunbalaji K',8056713039,'Technology Traning',8056713039,1),
 (502,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Tiruchirappalli','Jobseeker','2019-11-28 10:16:31','pavithrasounder@gmail.com','Pavithra S','IT/Software','Pavithra S',8098477561,'Technology Traning',8098477561,1),
 (503,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Tiruchirappalli','Jobseeker','2019-11-28 10:16:31','versatilerekha@gmail.com','Rekha P','IT/Software','Rekha P',8220568448,'Technology Traning',8220568448,1),
 (504,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Tiruchirappalli','Jobseeker','2019-11-28 10:16:31','sabarivasanloop@gmail.com','Sabarivasan S','IT/Software','Sabarivasan S',8248825133,'Technology Traning',8248825133,1),
 (505,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Tiruchirappalli','Jobseeker','2019-11-28 10:16:31','anushilvya@gmail.com','Anu Shilvya J','IT/Software','Anu Shilvya J',8428648873,'Technology Traning',8428648873,1),
 (506,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Tiruchirappalli','Jobseeker','2019-11-28 10:16:31','vigneshvikivv86@gmail.com','Vignesh Shanmugam','IT/Software','Vignesh Shanmugam',8760276111,'Technology Traning',8760276111,1),
 (507,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','lekshmanmj001@gmail.com','Lekshman M. J.','IT/Software','Lekshman M. J.',8903478729,'Technology Traning',8903478729,1),
 (508,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Coimbatore','Jobseeker','2019-11-28 10:16:31','vivekanandhkm2627@gmail.com','K.M.Vivek Anandh','IT/Software','K.M.Vivek Anandh',9003550930,'Technology Traning',9003550930,1),
 (509,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','','Umang Shah','IT/Software','Umang Shah',9426105627,'Technology Traning',9426105627,1),
 (510,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Coimbatore','Jobseeker','2019-11-28 10:16:31','dineshpk761998@gmail.com','Dinesh P','IT/Software','Dinesh P',9445934940,'Technology Traning',9445934940,1),
 (511,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Coimbatore','Jobseeker','2019-11-28 10:16:31','sudhakar.a.ips@gmail.com','Sudhakar A','IT/Software','Sudhakar A',9600905404,'Technology Traning',9600905404,1),
 (512,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','ameer.salman2627@gmail.com','Ameer Salman T','IT/Software','Ameer Salman T',9710242143,'Technology Traning',9710242143,1),
 (513,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','amrish.krish1@gmail.com','Amrish Krishnan','IT/Software','Amrish Krishnan',9833096804,'Technology Traning',9833096804,1),
 (514,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','rekhavenkat1206@gmail.com','Rekha Mv','IT/Software','Rekha Mv',9944109466,'Technology Traning',9944109466,1),
 (515,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Coimbatore','Jobseeker','2019-11-28 10:16:31','lovelysaravana143g@gmail.com','Saravanakumar J','IT/Software','Saravanakumar J',9944970143,'Technology Traning',9944970143,1),
 (516,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Tiruchirappalli','Jobseeker','2019-11-28 10:16:31','dmpj9.4.1998@gmail.com','Mathangi Poornima Jesuita','IT/Software','Mathangi Poornima Jesuita',9952121670,'Technology Traning',9952121670,1),
 (517,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Tiruchirappalli','Jobseeker','2019-11-28 10:16:31','kevindgeorge@gmail.com','Kevin George','IT/Software','Kevin George',9994374794,'Technology Traning',9994374794,1),
 (518,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','harshitagarwal386@gmail.com','Harshit Agarwal','IT/Software','Harshit Agarwal',8604197167,'Technology Traning',8604197167,1),
 (519,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','anusaanvi12@gmail.com','Anu Priya .K','IT/Software','Anu Priya .K',7639993376,'Technology Traning',7639993376,1),
 (520,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','harithas1998@gmail.com','Haritha Selvam','IT/Software','Haritha Selvam',9677326114,'Technology Traning',9677326114,1),
 (521,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','rdivya149@gmail.com','Divya R','IT/Software','Divya R',9442506379,'Technology Traning',9442506379,1),
 (522,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','vaishnavikumarpandian@gmail.com','Vaishnavi K','IT/Software','Vaishnavi K',8870692506,'Technology Traning',8870692506,1),
 (523,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','chn.santhoshkumar@gmail.com','Santhosh Kumar .M','IT/Software','Santhosh Kumar .M',8939313696,'Technology Traning',8939313696,1),
 (524,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','martinsrm011@gmail.com','Martin S R','IT/Software','Martin S R',8940050139,'Technology Traning',8940050139,1),
 (525,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','san229602@gmail.com','Sankaran S','IT/Software','Sankaran S',9940388709,'Technology Traning',9940388709,1),
 (526,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','anugrahkumar_a@srmuniv.edu.in','Anugrah Kumar Singhal','IT/Software','Anugrah Kumar Singhal',8565824887,'Technology Traning',8565824887,1),
 (527,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','rajricarp@gmail.com','Rajesh Pany','IT/Software','Rajesh Pany',9776734405,'Technology Traning',9776734405,1),
 (528,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','sukeshkorlepara@gmail.com','Korlepara Mani Venkata Sukesh','IT/Software','Korlepara Mani Venkata Sukesh',8341704170,'Technology Traning',8341704170,1),
 (529,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','basker9988@gmail.com','Baskar P','IT/Software','Baskar P',7299780195,'Technology Traning',7299780195,1),
 (530,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','jmrajeshsnkl@gmail.com','Rajesh Krishnan','IT/Software','Rajesh Krishnan',8056339206,'Technology Traning',8056339206,1),
 (531,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','nandhini4040@gmail.com','Nandhini . K','IT/Software','Nandhini . K',7904640958,'Technology Traning',7904640958,1),
 (532,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','sujanbhusal1@gmail.com','Sujan Bhusal','IT/Software','Sujan Bhusal',7358614609,'Technology Traning',7358614609,1),
 (533,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','aparajit.garg06@gmail.com','Aparajit Garg','IT/Software','Aparajit Garg',9087764219,'Technology Traning',9087764219,1),
 (534,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','muluqutlas97@gmail.com','Sai Surya','IT/Software','Sai Surya',7358503173,'Technology Traning',7358503173,1),
 (535,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','buland.shahrukh@gmail.com','S B Iqbal','IT/Software','S B Iqbal',7000336865,'Technology Traning',7000336865,1),
 (536,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','jayanrobert1@gmail.com','Jayan Robert','IT/Software','Jayan Robert',8124788022,'Technology Traning',8124788022,1),
 (537,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','maheshmuppidi17@gmail.com','Muppidi Mahesh Kumar','IT/Software','Muppidi Mahesh Kumar',8499888643,'Technology Traning',8499888643,1),
 (538,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','asmanazrin1@gmail.com','Asma Nazrin','IT/Software','Asma Nazrin',9940517973,'Technology Traning',9940517973,1),
 (539,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','kkarthikeyan613@gmail.com','Karthikeyan S','IT/Software','Karthikeyan S',9578209017,'Technology Traning',9578209017,1),
 (540,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','princepuneeth75@gmail.com','Prince Puneeth','IT/Software','Prince Puneeth',8331839413,'Technology Traning',8331839413,1),
 (541,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','nandhininandhu196@gmail.com','Nandhini N','IT/Software','Nandhini N',8056200194,'Technology Traning',8056200194,1),
 (542,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','sindhujatanuja1714@gmail.com','Gvs Sindhuja','IT/Software','Gvs Sindhuja',9789824023,'Technology Traning',9789824023,1),
 (543,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','megamaggi06@gmail.com','Meganathan K','IT/Software','Meganathan K',7092206422,'Technology Traning',7092206422,1),
 (544,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','palanivigneshwar@gmail.com','Palani Vigneshwar','IT/Software','Palani Vigneshwar',9566190410,'Technology Traning',9566190410,1),
 (545,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','vigneshthiru519@gmail.com','Vignesh Thirugnanam','IT/Software','Vignesh Thirugnanam',9840871068,'Technology Traning',9840871068,1),
 (546,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','lakshmeepriya19@gmail.com','Lakshmi Priya','IT/Software','Lakshmi Priya',9445670684,'Technology Traning',9445670684,1),
 (547,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','nishikr5@gmail.com','Nishanth Kr','IT/Software','Nishanth Kr',9789953653,'Technology Traning',9789953653,1),
 (548,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','sanchitachatterjee_sw@srmuniv.edu.','Sanchita Chatterjee','IT/Software','Sanchita Chatterjee',9566217498,'Technology Traning',9566217498,1),
 (549,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','mon.hardy19@gmail.com','Mohan Raj','IT/Software','Mohan Raj',9176842427,'Technology Traning',9176842427,1),
 (550,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Tiruchirappalli','Jobseeker','2019-11-28 10:16:31','aishuzain97@gmail.com','J.Aishwarya Sagaya Rani','IT/Software','J.Aishwarya Sagaya Rani',7010897171,'Technology Traning',7010897171,1),
 (551,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','premrasu74@gmail.com','Prem Kumar R','IT/Software','Prem Kumar R',8681918742,'Technology Traning',8681918742,1),
 (552,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','ammushiri@gmail.com','Ammu Shiri Krishnan','IT/Software','Ammu Shiri Krishnan',9445485530,'Technology Traning',9445485530,1),
 (553,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','durgadeviarasu777@gmail.com','Durga Devi. T','IT/Software','Durga Devi. T',9840101274,'Technology Traning',9840101274,1),
 (554,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','adibhosale06@gmail.com','Aditya Bhosale','IT/Software','Aditya Bhosale',9940470410,'Technology Traning',9940470410,1),
 (555,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','hari2u256@gmail.com','Hariharan R','IT/Software','Hariharan R',7871040686,'Technology Traning',7871040686,1),
 (556,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','jnkrishnamoorthy@gmail.com','KRISHNA MOORTHY J','IT/Software','KRISHNA MOORTHY J',8807683092,'Technology Traning',8807683092,1),
 (557,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','rishabhsaxena_sa@srmuniv.edu.in','Rishabh Saxena','IT/Software','Rishabh Saxena',6381257902,'Technology Traning',6381257902,1),
 (558,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','gowtham3897@gmail.com','Gowtham Kumar N','IT/Software','Gowtham Kumar N',9585030637,'Technology Traning',9585030637,1),
 (559,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','rashitiwary302@gmail.com','Rashi Tiwary','IT/Software','Rashi Tiwary',9408669561,'Technology Traning',9408669561,1),
 (560,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','kushalgupta_de@srmuniv.edu.in','Kushal Gupta','IT/Software','Kushal Gupta',9962259925,'Technology Traning',9962259925,1),
 (561,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','','Preetham Reddy','IT/Software','Preetham Reddy',8886408445,'Technology Traning',8886408445,1),
 (562,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','rudraraju0106@gmail.com','Rudraraju Mithileshvarma','IT/Software','Rudraraju Mithileshvarma',9100502057,'Technology Traning',9100502057,1),
 (563,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','afreen98.aa@gmail.com','Afreen J','IT/Software','Afreen J',9600144356,'Technology Traning',9600144356,1),
 (564,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','keerthiplessis@gmail.com','Keerthi Vasan.R','IT/Software','Keerthi Vasan.R',9789529561,'Technology Traning',9789529561,1),
 (565,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','vikashvvn2@gmail.com','Vikash A','IT/Software','Vikash A',8015670474,'Technology Traning',8015670474,1);
INSERT INTO `customer` (`customer_Id`,`createdBy`,`createdTime`,`isDelete`,`modifiedBy`,`modifiedTime`,`sending_status`,`version`,`address`,`customer_type`,`dateOf_birth`,`email_id`,`first_name`,`industry`,`last_name`,`mobile_number`,`specialization`,`whatsapp_number`,`company_id`) VALUES 
 (566,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','rohithjoc@gmail.com','Rohith Sivaraj','IT/Software','Rohith Sivaraj',8825556386,'Technology Traning',8825556386,1),
 (567,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','jvanush@gmail.com','Jv Anush','IT/Software','Jv Anush',9551210658,'Technology Traning',9551210658,1),
 (568,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','kvarun51@gmail.com','Kumar Varun','IT/Software','Kumar Varun',9566201248,'Technology Traning',9566201248,1),
 (569,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','kusu.sasikala0021@gmail.com','Kusu Sasikala','IT/Software','Kusu Sasikala',8247492958,'Technology Traning',8247492958,1),
 (570,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','meenachander222@gmail.com','Meena B','IT/Software','Meena B',9840556507,'Technology Traning',9840556507,1),
 (571,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','raysbond007@gmail.com','Baha Ur Rehaan','IT/Software','Baha Ur Rehaan',7358294122,'Technology Traning',7358294122,1),
 (572,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','aakashtrivedi5597@gmail.com','Aakash Trivedi','IT/Software','Aakash Trivedi',7355209927,'Technology Traning',7355209927,1),
 (573,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','patanshafi1998@gmail.com','Shaffi Pspk','IT/Software','Shaffi Pspk',9100167459,'Technology Traning',9100167459,1),
 (574,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','srani.ju2396@gmail.com','Abirami Sivasankar','IT/Software','Abirami Sivasankar',9941519342,'Technology Traning',9941519342,1),
 (575,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','sundarsai00@gmail.com','Shanmuga Sundar. A','IT/Software','Shanmuga Sundar. A',9941852715,'Technology Traning',9941852715,1),
 (576,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','jasveer0606@gmail.com','Jasveer Singh','IT/Software','Jasveer Singh',9884206091,'Technology Traning',9884206091,1),
 (577,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','aiswariyalenin@gmail.com','Aishwarya L','IT/Software','Aishwarya L',9176178621,'Technology Traning',9176178621,1),
 (578,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','preethilingeshwaran1997@gmail.com','Preethi. L','IT/Software','Preethi. L',8220728538,'Technology Traning',8220728538,1),
 (579,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','duraimurugan0954@gmail.com','P.Durai Murugan','IT/Software','P.Durai Murugan',9790107788,'Technology Traning',9790107788,1),
 (580,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','ezhilarun2013@gmail.com','Ezhilarasi D','IT/Software','Ezhilarasi D',9751099935,'Technology Traning',9751099935,1),
 (581,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','srivastava.anurita@gmail.com','Anurita Srivastava','IT/Software','Anurita Srivastava',9176442606,'Technology Traning',9176442606,1),
 (582,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','nathanlogan842@gmail.com','Logannathan M','IT/Software','Logannathan M',8524080155,'Technology Traning',8524080155,1),
 (583,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','rasuri.bhargavi@gmail.com','Rasuri Bhargavi','IT/Software','Rasuri Bhargavi',9705940072,'Technology Traning',9705940072,1),
 (584,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','brighta.bta@gmail.com','S.Emerald Shirley Brighta','IT/Software','S.Emerald Shirley Brighta',8667087139,'Technology Traning',8667087139,1),
 (585,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','pavithrabalamurugan2015@gmail.com','Pavithra Balamurugan','IT/Software','Pavithra Balamurugan',9566094076,'Technology Traning',9566094076,1),
 (586,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','rukmanireddy3110@gmail.com','Rukmani P','IT/Software','Rukmani P',9789980443,'Technology Traning',9789980443,1),
 (587,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','vagdevinamita@gmail.com','Namita Vagdevi Cherukuru','IT/Software','Namita Vagdevi Cherukuru',9789493648,'Technology Traning',9789493648,1),
 (588,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','vyshnavak47@gmail.com','Vyshnav A K','IT/Software','Vyshnav A K',9940514207,'Technology Traning',9940514207,1),
 (589,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','premkumar69230@gmail.com','Prem Kumar','IT/Software','Prem Kumar',9566129324,'Technology Traning',9566129324,1),
 (590,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','ashusasi1501@gmail.com','Ashwini S','IT/Software','Ashwini S',7639137873,'Technology Traning',7639137873,1),
 (591,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','anishaa606@gmail.com','Anisha Arul','IT/Software','Anisha Arul',9487152284,'Technology Traning',9487152284,1),
 (592,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','jiraj26@gmail.com','Md Ali Imam Ansari','IT/Software','Md Ali Imam Ansari',9534431491,'Technology Traning',9534431491,1),
 (593,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','jsathiya.priya2010@gmail.com','Sathiyapriya J','IT/Software','Sathiyapriya J',8637475745,'Technology Traning',8637475745,1),
 (594,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','jayendra.g.d@gmail.com','Jayendra Saraswathi G D','IT/Software','Jayendra Saraswathi G D',8072525720,'Technology Traning',8072525720,1),
 (595,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','deepikadhayalan21@gmail.com','Deepika D','IT/Software','Deepika D',9789861423,'Technology Traning',9789861423,1),
 (596,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','harsha8465@gmail.com','Harshavardhan Reddy','IT/Software','Harshavardhan Reddy',8465958447,'Technology Traning',8465958447,1),
 (597,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','ivansnehith2@gmail.com','Gali Ivan Snehith Reddy','IT/Software','Gali Ivan Snehith Reddy',7092892401,'Technology Traning',7092892401,1),
 (598,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','tejamusinana@gmail.com','Musinana Brahma Teja','IT/Software','Musinana Brahma Teja',8939328877,'Technology Traning',8939328877,1),
 (599,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','vijayj1997@gmail.com','Vijay J','IT/Software','Vijay J',7299253817,'Technology Traning',7299253817,1),
 (600,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','karunnyaselvan@gmail.com','Karunnya Selvan','IT/Software','Karunnya Selvan',9443491761,'Technology Traning',9443491761,1),
 (601,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','m.sivani98@gmail.com','Sivani Mandadi','IT/Software','Sivani Mandadi',9087976331,'Technology Traning',9087976331,1),
 (602,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','thamilmuththamil97@gmail.com','Muththamil Latchumi','IT/Software','Muththamil Latchumi',9940417238,'Technology Traning',9940417238,1),
 (603,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','deepakmikki18@gmail.com','Mikkilineni Deepak','IT/Software','Mikkilineni Deepak',9442164780,'Technology Traning',9442164780,1),
 (604,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','archu1598@gmail.com','Archana Suresh','IT/Software','Archana Suresh',9790848856,'Technology Traning',9790848856,1),
 (605,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','sakshisinghtanwar25@gmail.com','Sakshi Singh Tanwar','IT/Software','Sakshi Singh Tanwar',9884202819,'Technology Traning',9884202819,1),
 (606,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','aarthithangamaninatarajan@gmail.com','Aarthi Natarajan','IT/Software','Aarthi Natarajan',9500157687,'Technology Traning',9500157687,1),
 (607,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','official.srv.modak@gmail.com','Sourav Modak','IT/Software','Sourav Modak',9500166574,'Technology Traning',9500166574,1),
 (608,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','gsomeshwar1997@gmail.com','Someshwar G','IT/Software','Someshwar G',8056994589,'Technology Traning',8056994589,1),
 (609,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','sabarish1197@gmail.com','Sabarish S','IT/Software','Sabarish S',7200673425,'Technology Traning',7200673425,1),
 (610,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','batchubalakrishna9@gmail.com','Bala Krishna','IT/Software','Bala Krishna',8499851494,'Technology Traning',8499851494,1),
 (611,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','sreyabiswas.official@gmail.com','Sreya Biswas','IT/Software','Sreya Biswas',9833323854,'Technology Traning',9833323854,1),
 (612,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','s.chakrapani63@gmail.com','Chakrapani Suresh','IT/Software','Chakrapani Suresh',9940137054,'Technology Traning',9940137054,1),
 (613,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','soorapanenikesava_so@srmuniv.edu.in\n','Kesavanarayana Soorapaneni','IT/Software','Kesavanarayana Soorapaneni',8919625388,'Technology Traning',8919625388,1),
 (614,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','arshathmohamed432@gmail.com','Mohamed Arshath','IT/Software','Mohamed Arshath',9003467805,'Technology Traning',9003467805,1),
 (615,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','mmanimc@gmail.com','Manikandan M','IT/Software','Manikandan M',7395882660,'Technology Traning',7395882660,1),
 (616,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','nivethanks@gmail.com','Nivethitha K S','IT/Software','Nivethitha K S',9841011176,'Technology Traning',9841011176,1),
 (617,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','cvssrpatanjali13@gmail.com','C V S S R Patanjali','IT/Software','C V S S R Patanjali',7397401049,'Technology Traning',7397401049,1),
 (618,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','elamathikamali@gmail.com','Elamathi Kamali','IT/Software','Elamathi Kamali',9500574860,'Technology Traning',9500574860,1),
 (619,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','kavin220797@gmail.com','T Kavin','IT/Software','T Kavin',8056957327,'Technology Traning',8056957327,1),
 (620,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','kirutthikaingeniator@gmail.com','E.Kirutthika','IT/Software','E.Kirutthika',7339288597,'Technology Traning',7339288597,1),
 (621,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','gayathricaddy3@gmail.com','Gayathri Murali','IT/Software','Gayathri Murali',9841703111,'Technology Traning',9841703111,1),
 (622,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','dipanshukapoor_di@srmuniv.edu.in','Dipanshu Kapoor','IT/Software','Dipanshu Kapoor',9876371208,'Technology Traning',9876371208,1),
 (623,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','yashmishra_pr@srmuniv.edu.in','Yash Mishra','IT/Software','Yash Mishra',9962252466,'Technology Traning',9962252466,1),
 (624,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','hemanathaneswaran@gmail.com','Hemanathan Eswaran','IT/Software','Hemanathan Eswaran',7871761413,'Technology Traning',7871761413,1),
 (625,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','priyapravin97@gmail.com','Priya Dharsini','IT/Software','Priya Dharsini',9894289799,'Technology Traning',9894289799,1),
 (626,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','kushagra.jaiswal18@gmail.com','Kushagra Jaiswal','IT/Software','Kushagra Jaiswal',9566189700,'Technology Traning',9566189700,1),
 (627,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','babuyadhav21221@gmail.com','Babu J','IT/Software','Babu J',8892460154,'Technology Traning',8892460154,1),
 (628,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','nagarjunav1996@gmail.com','V.Nagarjuna Reddy','IT/Software','V.Nagarjuna Reddy',9505554262,'Technology Traning',9505554262,1),
 (629,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','aarthi.n97@gmail.com','Aarthi Natarajan','IT/Software','Aarthi Natarajan',8838619027,'Technology Traning',8838619027,1),
 (630,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','jkevinsamuel@gmail.com','J Kevin','IT/Software','J Kevin',8428258688,'Technology Traning',8428258688,1),
 (631,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','praveenrockz05@gmail.com','Praveen Kumar','IT/Software','Praveen Kumar',8098803933,'Technology Traning',8098803933,1),
 (632,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','jenijessy24@gmail.com','Jenifer J','IT/Software','Jenifer J',9176079889,'Technology Traning',9176079889,1),
 (633,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','Sukirdhanasubramaniam13@gmail.com','Varsa Sukirthana','IT/Software','Varsa Sukirthana',9894786376,'Technology Traning',9894786376,1),
 (634,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','gauravknp12@gmail.com','Gaurav Bajpai','IT/Software','Gaurav Bajpai',8840709708,'Technology Traning',8840709708,1),
 (635,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','praisetotheholy@gmail.com','David Vinith','IT/Software','David Vinith',9514661959,'Technology Traning',9514661959,1),
 (636,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','gopi.avengers@gmail.com','Gopinath P','IT/Software','Gopinath P',8754565479,'Technology Traning',8754565479,1),
 (637,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','kumar.sanjay.sanjay977@gmail.com','K L Sanjay','IT/Software','K L Sanjay',9597814602,'Technology Traning',9597814602,1),
 (638,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','vlavanya0903@gmail.com','V Lavanya','IT/Software','V Lavanya',9080872545,'Technology Traning',9080872545,1),
 (639,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','js1081996@gmail.com','Jagpreet Singh Thakur','IT/Software','Jagpreet Singh Thakur',9677218726,'Technology Traning',9677218726,1),
 (640,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','saikrishnasunny236@gmail.com','Thuraka Kumar Venkata Sai Krishna','IT/Software','Thuraka Kumar Venkata Sai Krishna',9952094290,'Technology Traning',9952094290,1),
 (641,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','yahiyarockz12@gmail.com','Mohammed Yahiya','IT/Software','Mohammed Yahiya',8610336015,'Technology Traning',8610336015,1),
 (642,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','ria.dey.2015@gmail.com','Garima De','IT/Software','Garima De',9884202280,'Technology Traning',9884202280,1),
 (643,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','chandrukorukkai@gmail.com','Chandru R','IT/Software','Chandru R',8608994731,'Technology Traning',8608994731,1),
 (644,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','sachinsurya470@gmail.com','Surya M','IT/Software','Surya M',6381139433,'Technology Traning',6381139433,1),
 (645,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','sangeeth.palanichamy@gmail.com','Sangeth Kumar','IT/Software','Sangeth Kumar',7358902154,'Technology Traning',7358902154,1),
 (646,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','1514072@saec.ac.in','Mohan Prabhu C. R.','IT/Software','Mohan Prabhu C. R.',8939168971,'Technology Traning',8939168971,1),
 (647,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','reenasai06@gmail.com','Reena Saravanan','IT/Software','Reena Saravanan',7358518405,'Technology Traning',7358518405,1),
 (648,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','harishvelu28@gmail.com','K . Harish Kumar','IT/Software','K . Harish Kumar',7010056494,'Technology Traning',7010056494,1),
 (649,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','meghapriya2312@gmail.com','S.Megha Priyadharshini','IT/Software','S.Megha Priyadharshini',9003050247,'Technology Traning',9003050247,1),
 (650,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','sanjaysanjith96@gmail.com','Sanjay Sanjith','IT/Software','Sanjay Sanjith',9994970346,'Technology Traning',9994970346,1),
 (651,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','monish23don@gmail.com','Monish T','IT/Software','Monish T',9629151672,'Technology Traning',9629151672,1),
 (652,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','vaibhavdwivedi77@gmail.com','Vaibhav Dwivedi','IT/Software','Vaibhav Dwivedi',9884206219,'Technology Traning',9884206219,1),
 (653,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','sjc.siva17@gmail.com','Jeyachitra S','IT/Software','Jeyachitra S',8098619833,'Technology Traning',8098619833,1),
 (654,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','snehamemane23@gmail.com','Sneha Namdeo Memane','IT/Software','Sneha Namdeo Memane',8208123031,'Technology Traning',8208123031,1),
 (655,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','dineshnivedha98@gmail.com','R. Nivedha Ravi','IT/Software','R. Nivedha Ravi',8939222650,'Technology Traning',8939222650,1),
 (656,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','rajaduraimrd47@gmail.com','Rajadurai Murugan','IT/Software','Rajadurai Murugan',9884197439,'Technology Traning',9884197439,1),
 (657,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','dhanushp1197@gmail.com','Dhanush P','IT/Software','Dhanush P',7010885565,'Technology Traning',7010885565,1),
 (658,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','saransh808@gmail.com','Saransh Agarwal','IT/Software','Saransh Agarwal',8800486048,'Technology Traning',8800486048,1),
 (659,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','v1e2n3u4@gmail.com','Gaddam Venu','IT/Software','Gaddam Venu',9445281062,'Technology Traning',9445281062,1),
 (660,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','d.venky1998@gmail.com','Venkatalakshmi Dhanasegeran','IT/Software','Venkatalakshmi Dhanasegeran',9941318876,'Technology Traning',9941318876,1),
 (661,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','niladrinandansarma@gmail.com','Niladri Nandan Sarma','IT/Software','Niladri Nandan Sarma',7381852501,'Technology Traning',7381852501,1),
 (662,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','divyanarendran05@gmail.com','Divya Narendran','IT/Software','Divya Narendran',9551205117,'Technology Traning',9551205117,1),
 (663,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','raghulkrish97@gmail.com','Raghul Kannan','IT/Software','Raghul Kannan',9789332545,'Technology Traning',9789332545,1),
 (664,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','nishanishchitha56@gmail.com','Nisha M P','IT/Software','Nisha M P',9171000900,'Technology Traning',9171000900,1),
 (665,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','m.nishapoovaiah@gmail.com','Nisha M P','IT/Software','Nisha M P',8838908886,'Technology Traning',8838908886,1),
 (666,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','murugan.manojkumar97@gmail.com','Manoj Kumar.M','IT/Software','Manoj Kumar.M',8608327002,'Technology Traning',8608327002,1),
 (667,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','ucs15233@rmd.ac.in','Rekha Velu','IT/Software','Rekha Velu',9600309537,'Technology Traning',9600309537,1),
 (668,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','parasuraman473@gmail.com','Parasuraman Jeyabalan','IT/Software','Parasuraman Jeyabalan',9487742377,'Technology Traning',9487742377,1),
 (669,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','srikanth24.m@gmail.com','Srikanth M','IT/Software','Srikanth M',9566506878,'Technology Traning',9566506878,1),
 (670,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','ucs15303@rmd.ac.in','Sindhu V S','IT/Software','Sindhu V S',9080567585,'Technology Traning',9080567585,1),
 (671,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','jayasurya.rajarajan@gmail.com','Jaya Surya.R','IT/Software','Jaya Surya.R',9677065162,'Technology Traning',9677065162,1),
 (672,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','blessygifta@gmail.com','Blessy Gifta','IT/Software','Blessy Gifta',9445664738,'Technology Traning',9445664738,1),
 (673,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','venkatdhev@gmail.com','Venkatesan N','IT/Software','Venkatesan N',7904414745,'Technology Traning',7904414745,1),
 (674,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','srismileplease02@gmail.com','Srilatha Chandrababu','IT/Software','Srilatha Chandrababu',8939365419,'Technology Traning',8939365419,1),
 (675,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','sardarsingh37star@gmail.com','Hemanta Saikia','IT/Software','Hemanta Saikia',7002440729,'Technology Traning',7002440729,1),
 (676,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','padmavathynarayananofzl@gmail.com','Padmavathy Kanaka','IT/Software','Padmavathy Kanaka',8190941988,'Technology Traning',8190941988,1),
 (677,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','sanghavivaishu001@gmail.com','Sanghavi Vaishu','IT/Software','Sanghavi Vaishu',9566243816,'Technology Traning',9566243816,1),
 (678,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','','Priya Kumari','IT/Software','Priya Kumari',7004429704,'Technology Traning',7004429704,1),
 (679,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','pallavipks9@gmail.com','Pallavi Kumari Sharma','IT/Software','Pallavi Kumari Sharma',8789840435,'Technology Traning',8789840435,1),
 (680,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','anitha3ravi@gmail.com','Anitha R','IT/Software','Anitha R',8754072244,'Technology Traning',8754072244,1),
 (681,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','arunvino619@gmail.com','Arunkumar J','IT/Software','Arunkumar J',7401326320,'Technology Traning',7401326320,1),
 (682,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','aravind.leo10@gmail.com','R Aravindan','IT/Software','R Aravindan',8148797918,'Technology Traning',8148797918,1),
 (683,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','nagammaisubbu5@gmail.com','Nagammai S','IT/Software','Nagammai S',9094704456,'Technology Traning',9094704456,1),
 (684,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','rathiyamary@gmail.com','Rathiya Mary Vedhanayagam','IT/Software','Rathiya Mary Vedhanayagam',9080111029,'Technology Traning',9080111029,1),
 (685,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','sugashinip98@gmail.com','Sugashini Purushothaman','IT/Software','Sugashini Purushothaman',8124429095,'Technology Traning',8124429095,1),
 (686,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','karthikstarsparks@gmail.com','Karthik','IT/Software','Karthik',9791982971,'Technology Traning',9791982971,1),
 (687,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','calvindavison111998@gmail.com','Calvin Jeyaraj.C','IT/Software','Calvin Jeyaraj.C',8610261614,'Technology Traning',8610261614,1),
 (688,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','priyanka.shivani@gmail.com','Priyanka P Shivani','IT/Software','Priyanka P Shivani',8754561541,'Technology Traning',8754561541,1),
 (689,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','priyaraman8576@gmail.com','K. Saipriya','IT/Software','K. Saipriya',9941242620,'Technology Traning',9941242620,1),
 (690,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','gopinathss.subramani@gmail.com','Gopinath Subramani','IT/Software','Gopinath Subramani',9715254540,'Technology Traning',9715254540,1),
 (691,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','rakshana5jav@gmail.com','Rakshana R','IT/Software','Rakshana R',9840171081,'Technology Traning',9840171081,1),
 (692,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','rahulkiron23@gmail.com','Rahul Kiron','IT/Software','Rahul Kiron',9940581681,'Technology Traning',9940581681,1),
 (693,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','saranyaeee1998@gmail.com','Saranya M','IT/Software','Saranya M',9843524854,'Technology Traning',9843524854,1),
 (694,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','swethaselvakavi@gmail.com','Swetha S','IT/Software','Swetha S',7397425872,'Technology Traning',7397425872,1),
 (695,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','uvashri19982@gmail.com','Uvashri Dhamodharan','IT/Software','Uvashri Dhamodharan',8608199446,'Technology Traning',8608199446,1),
 (696,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','shyamnaidu2204@gmail.com','Shyamu Dhonadhi','IT/Software','Shyamu Dhonadhi',9790882952,'Technology Traning',9790882952,1),
 (697,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','joicevimala74@gmail.com','Joice Vimala','IT/Software','Joice Vimala',7305481391,'Technology Traning',7305481391,1),
 (698,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','kaarthik.raghavan@gmail.com','Kaarthikeyan Raghavan','IT/Software','Kaarthikeyan Raghavan',9884759549,'Technology Traning',9884759549,1),
 (699,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','nandhu97n@gmail.com','N Nandhini','IT/Software','N Nandhini',9500167469,'Technology Traning',9500167469,1),
 (700,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','priyababu160598@gmail.com','Priya B','IT/Software','Priya B',9994976282,'Technology Traning',9994976282,1),
 (701,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','subeika3@gmail.com','Subeka S','IT/Software','Subeka S',9940379146,'Technology Traning',9940379146,1),
 (702,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','siddharthdevilliees524@gmail.com','Siddharth Gunasekaran','IT/Software','Siddharth Gunasekaran',8939078043,'Technology Traning',8939078043,1),
 (703,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','mathesh.rp@gmail.com','Mathesh M','IT/Software','Mathesh M',9500216957,'Technology Traning',9500216957,1),
 (704,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','aravind.it9996@gmail.com','Aravind C','IT/Software','Aravind C',8220616539,'Technology Traning',8220616539,1),
 (705,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','roshini2mail@gmail.com','Roshini S','IT/Software','Roshini S',7358656557,'Technology Traning',7358656557,1),
 (706,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','kiran1997ajay2000@gmail.com','Kiran Chander R','IT/Software','Kiran Chander R',7299476871,'Technology Traning',7299476871,1),
 (707,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','jeyait2016@gmail.com','Jaya S','IT/Software','Jaya S',9585739378,'Technology Traning',9585739378,1),
 (708,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','sundeep0412@gmail.com','Sundeep D','IT/Software','Sundeep D',9176223835,'Technology Traning',9176223835,1),
 (709,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','shivashamaa@gmail.com','Sivasankari Gopal','IT/Software','Sivasankari Gopal',9790785384,'Technology Traning',9790785384,1),
 (710,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','thulasiraman573.tr@gmail.com','Thulasi Raman N','IT/Software','Thulasi Raman N',8220767763,'Technology Traning',8220767763,1),
 (711,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','rajeshsachin010@gmail.com','Rajesh S','IT/Software','Rajesh S',7871642859,'Technology Traning',7871642859,1),
 (712,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','prashast.vaish@gmail.com','Prashast Vaish','IT/Software','Prashast Vaish',9818316642,'Technology Traning',9818316642,1),
 (713,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','anjanapk2198@gmail.com','Anjana Premkumar','IT/Software','Anjana Premkumar',9600086729,'Technology Traning',9600086729,1),
 (714,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','sanjayyuvi28@gmail.com','Sanjay Kumar','IT/Software','Sanjay Kumar',9962516765,'Technology Traning',9962516765,1),
 (715,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','roysoms@gmail.com','Saumya Roy','IT/Software','Saumya Roy',9566186176,'Technology Traning',9566186176,1),
 (716,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','manojkumar.goddu@yahoo.com','Manoj Kumar G','IT/Software','Manoj Kumar G',8500144720,'Technology Traning',8500144720,1),
 (717,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','kvmalathi03@gmail.com','Malathi. V','IT/Software','Malathi. V',9514216803,'Technology Traning',9514216803,1),
 (718,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','subhaveltech97@gmail.com','Subha Anandhi','IT/Software','Subha Anandhi',9840807329,'Technology Traning',9840807329,1),
 (719,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','sindhuja1898@gmail.com','Sindhuja Devendran','IT/Software','Sindhuja Devendran',7358573533,'Technology Traning',7358573533,1),
 (720,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','shashanksacheti12@gmail.com','Shashank Sacheti','IT/Software','Shashank Sacheti',8754519590,'Technology Traning',8754519590,1),
 (721,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','tarun.sarpanjeri@gmail.com','Tarun Sarpanjeri','IT/Software','Tarun Sarpanjeri',8008885355,'Technology Traning',8008885355,1),
 (722,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','aravindsai157@gmail.com','Sai Aravindhan K','IT/Software','Sai Aravindhan K',7708466042,'Technology Traning',7708466042,1),
 (723,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','vickyyuge@gmail.com','Yukendran K N','IT/Software','Yukendran K N',9790803357,'Technology Traning',9790803357,1),
 (724,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','g.ashokreddy9908@gmail.com','Ashokkumarreddy G','IT/Software','Ashokkumarreddy G',9908968225,'Technology Traning',9908968225,1),
 (725,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','aarihiydhiva5@gmail.com','Dhivagar Sethuraman','IT/Software','Dhivagar Sethuraman',8760278886,'Technology Traning',8760278886,1),
 (726,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','ksaran17vpk@gmail.com','Saran K','IT/Software','Saran K',9787516061,'Technology Traning',9787516061,1),
 (727,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','adityasinghal_mo@srmuniv.edu.in','Aditya Singhal','IT/Software','Aditya Singhal',8209563277,'Technology Traning',8209563277,1),
 (728,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','sakthisam8@gmail.com','S.Sakthi Prakash','IT/Software','S.Sakthi Prakash',9600091214,'Technology Traning',9600091214,1),
 (729,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','vyshali.chandramohan@gmail.com','Vyshali Chandramohan','IT/Software','Vyshali Chandramohan',9791023674,'Technology Traning',9791023674,1),
 (730,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','shaludd78@gmail.com','Lingampally Shalini','IT/Software','Lingampally Shalini',7338708317,'Technology Traning',7338708317,1),
 (731,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','ellaki.lucky118@gmail.com','Ellakya Sekar','IT/Software','Ellakya Sekar',8056172473,'Technology Traning',8056172473,1),
 (732,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','mohanapriya.narendran@gmail.com','N Mohana Priya','IT/Software','N Mohana Priya',8807347017,'Technology Traning',8807347017,1),
 (733,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','graishwarya1997@gmail.com','Aishwarya Ramesh','IT/Software','Aishwarya Ramesh',8807285406,'Technology Traning',8807285406,1),
 (734,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','raviperumal17.rr@gmail.com','Ravindran P','IT/Software','Ravindran P',9159015175,'Technology Traning',9159015175,1),
 (735,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','harichandanamone@gmail.com','Harichandana Mone','IT/Software','Harichandana Mone',9566182180,'Technology Traning',9566182180,1),
 (736,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','pavithrajeni28@gmail.com','Pavithra Irudyanathan','IT/Software','Pavithra Irudyanathan',8608821160,'Technology Traning',8608821160,1),
 (737,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','sindhuja.d2298@gmail.com','Sindhuja D','IT/Software','Sindhuja D',7708153225,'Technology Traning',7708153225,1),
 (738,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','krishnakumar27897@gmail.com','Krishna K','IT/Software','Krishna K',7904205039,'Technology Traning',7904205039,1),
 (739,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','parthibanchellappan@gmail.com','Parthiban Chellappan','IT/Software','Parthiban Chellappan',7358259316,'Technology Traning',7358259316,1),
 (740,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','yaksavimal@gmail.com','Vimaladevi Yogeshwaran','IT/Software','Vimaladevi Yogeshwaran',8526073261,'Technology Traning',8526073261,1),
 (741,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','hakshatha.9@gmail.com','Hakshatha Devi','IT/Software','Hakshatha Devi',9940148792,'Technology Traning',9940148792,1),
 (742,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','jebainbam02@gmail.com','Jebainbam Y','IT/Software','Jebainbam Y',7639751071,'Technology Traning',7639751071,1),
 (743,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','rajeshraji1158@gmail.com','Rajesh Sivakumar','IT/Software','Rajesh Sivakumar',7639572656,'Technology Traning',7639572656,1),
 (744,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','roshinig.19ec@licet.ac.in','Roshini Gopalakrishnan','IT/Software','Roshini Gopalakrishnan',7548897225,'Technology Traning',7548897225,1),
 (745,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','rniga29081977@gmail.com','R.Niga','IT/Software','R.Niga',9940056807,'Technology Traning',9940056807,1),
 (746,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','aditya.vm07@gmail.com','Aditya M','IT/Software','Aditya M',9962669238,'Technology Traning',9962669238,1),
 (747,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','krishna.sai221@gmail.com','Sai Krishna','IT/Software','Sai Krishna',9884208649,'Technology Traning',9884208649,1),
 (748,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','dubeyk1998@gmail.com','Krishankant Dubey','IT/Software','Krishankant Dubey',8072028903,'Technology Traning',8072028903,1),
 (749,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','sreeprabhakaran97@gmail.com','Greeshma Pr','IT/Software','Greeshma Pr',6381580515,'Technology Traning',6381580515,1),
 (750,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','naveen147priya@gmail.com','Naveen P','IT/Software','Naveen P',9943614785,'Technology Traning',9943614785,1),
 (751,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','ksneha9712@gmail.com','Sneha Karthikeyan','IT/Software','Sneha Karthikeyan',9445161201,'Technology Traning',9445161201,1),
 (752,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','viji97ch@gmail.com','V. Vijayalakshmi','IT/Software','V. Vijayalakshmi',8428333060,'Technology Traning',8428333060,1),
 (753,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','jclaudia0206@gmail.com','K.S. Claudia Jane','IT/Software','K.S. Claudia Jane',8428861997,'Technology Traning',8428861997,1),
 (754,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','abi26.in@gmail.com','Abitha S','IT/Software','Abitha S',7299479626,'Technology Traning',7299479626,1),
 (755,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','victoryjoy88@gmail.com','Navaneetha Priya.R','IT/Software','Navaneetha Priya.R',9791647962,'Technology Traning',9791647962,1),
 (756,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','aishwaryatwinkle06@gmail.com','Aishwarya Sivakumar','IT/Software','Aishwarya Sivakumar',9003231839,'Technology Traning',9003231839,1),
 (757,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','gayathrikrishnan.gayu@gmail.com','K Gayathri','IT/Software','K Gayathri',8681921605,'Technology Traning',8681921605,1),
 (758,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','monikaramesh02@gmail.com','Monika Shalini','IT/Software','Monika Shalini',9600879899,'Technology Traning',9600879899,1),
 (759,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','vigneshvicky.vv450@gmail.com','Vignesh S','IT/Software','Vignesh S',9965631115,'Technology Traning',9965631115,1),
 (760,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','suvaufo@gmail.com','Suvalakshmi. R','IT/Software','Suvalakshmi. R',8220057703,'Technology Traning',8220057703,1),
 (761,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','mahalakshmirajavel8@gmail.com','Mahalakshmi R','IT/Software','Mahalakshmi R',9994134335,'Technology Traning',9994134335,1),
 (762,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','snehasweety1997@gmail.com','Sneha . D','IT/Software','Sneha . D',9551686935,'Technology Traning',9551686935,1),
 (763,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','kvaishnavi1898@gmail.com','Vaishnavi K','IT/Software','Vaishnavi K',9566462908,'Technology Traning',9566462908,1),
 (764,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','rbhargav_rr@srmuniv.edu.in','R Bhargav','IT/Software','R Bhargav',9790706704,'Technology Traning',9790706704,1),
 (765,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','rlakshmipriya1997@gmail.com','R Lakshmi Priya','IT/Software','R Lakshmi Priya',7358254282,'Technology Traning',7358254282,1),
 (766,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','aishuselvamani@gmail.com','Aishwarya S','IT/Software','Aishwarya S',8619087534,'Technology Traning',8619087534,1),
 (767,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','pravallikaponnam97@gmail.com','Pravallika Ponnam','IT/Software','Pravallika Ponnam',8870344362,'Technology Traning',8870344362,1),
 (768,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','deepikakalai101197@gmail.com','Deepika N','IT/Software','Deepika N',7358591997,'Technology Traning',7358591997,1),
 (769,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','','Sridhar Swamy','IT/Software','Sridhar Swamy',9505925376,'Technology Traning',9505925376,1),
 (770,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','nishavelayudham1998@gmail.com','Monisha Velayudham','IT/Software','Monisha Velayudham',7339599099,'Technology Traning',7339599099,1),
 (771,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','mnsrivatsav1@gmail.com','Sri Vatsav','IT/Software','Sri Vatsav',8179974620,'Technology Traning',8179974620,1),
 (772,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','abhilashmishra1210@gmail.com','Abhilash Mishra','IT/Software','Abhilash Mishra',8754561884,'Technology Traning',8754561884,1),
 (773,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','sowmiyamuthuraman@gmail.com','Sowmiya. M','IT/Software','Sowmiya. M',7810058282,'Technology Traning',7810058282,1),
 (774,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','mukesh.s.kumawath@gmail.com','Mukesh Kumawath','IT/Software','Mukesh Kumawath',9003992086,'Technology Traning',9003992086,1),
 (775,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','sammed59@gmail.com','Sammed Kumar Jain','IT/Software','Sammed Kumar Jain',7358317857,'Technology Traning',7358317857,1),
 (776,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','sushilkumarng@gmail.com','Sushil Kumar Rai','IT/Software','Sushil Kumar Rai',9436830436,'Technology Traning',9436830436,1),
 (777,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','arvindnovem@gmail.com','Arvind S','IT/Software','Arvind S',7010181564,'Technology Traning',7010181564,1),
 (778,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','selvaduraiyuvi@gmail.com','Selvadurai K','IT/Software','Selvadurai K',9840954877,'Technology Traning',9840954877,1),
 (779,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','mihirjain_am@srmuniv.edu.in','Mihir Jain','IT/Software','Mihir Jain',8319370381,'Technology Traning',8319370381,1),
 (780,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','16mc61l@saec.ac.in','Sowmiya. J','IT/Software','Sowmiya. J',9677625944,'Technology Traning',9677625944,1),
 (781,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','ramchintu33@gmail.com','Naga Ramkumar Reddy','IT/Software','Naga Ramkumar Reddy',8179130061,'Technology Traning',8179130061,1),
 (782,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','16mc06l@saec.ac.in','Ayswarya Dasarathan','IT/Software','Ayswarya Dasarathan',9176757179,'Technology Traning',9176757179,1),
 (783,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','ravivignesh.b.r@gmail.com','Ravivignesh B R','IT/Software','Ravivignesh B R',9092315945,'Technology Traning',9092315945,1),
 (784,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','dafnynesintha5275@gmail.com','Anupriya. T','IT/Software','Anupriya. T',7550034377,'Technology Traning',7550034377,1),
 (785,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','ecnandhini1995@gmail.com','Nandhini. P','IT/Software','Nandhini. P',8248738304,'Technology Traning',8248738304,1),
 (786,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','alderadi@gmail.com','Aditya Venkataraman','IT/Software','Aditya Venkataraman',9566018547,'Technology Traning',9566018547,1),
 (787,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','vtaunkae@gmail.com','Vighnesh Taunk','IT/Software','Vighnesh Taunk',9566211331,'Technology Traning',9566211331,1),
 (788,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','karthickdepp@gmail.com','Karthick S','IT/Software','Karthick S',9688292737,'Technology Traning',9688292737,1),
 (789,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','abhilashreddy586@gmail.com','Abhilash Reddy','IT/Software','Abhilash Reddy',9000202844,'Technology Traning',9000202844,1),
 (790,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','sumeethreddy888@gmail.com','Sumith Reddy','IT/Software','Sumith Reddy',7675930509,'Technology Traning',7675930509,1),
 (791,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','abdulaazeez786@gmail.com','Abdul Azeez K','IT/Software','Abdul Azeez K',8148548958,'Technology Traning',8148548958,1),
 (792,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','abisha4medic@gnail.com','Abisha Arokia Mary R V','IT/Software','Abisha Arokia Mary R V',9566144147,'Technology Traning',9566144147,1),
 (793,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','sanran1997@gmail.com','Sree Ranjhani','IT/Software','Sree Ranjhani',8489639890,'Technology Traning',8489639890,1),
 (794,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','mallika.chowdhary1996@gmail.com','Mallika Chowdhary','IT/Software','Mallika Chowdhary',9790725006,'Technology Traning',9790725006,1),
 (795,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','praveen8852@gmail.com','Praveen Kumar Singh','IT/Software','Praveen Kumar Singh',8210564785,'Technology Traning',8210564785,1),
 (796,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','balamahendranmani@gmail.com','Mahendran Balagurusamy','IT/Software','Mahendran Balagurusamy',9865951441,'Technology Traning',9865951441,1),
 (797,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','gayathri.velmurugan.1997@gmail.com','Gayathri Velmurugan','IT/Software','Gayathri Velmurugan',9445801474,'Technology Traning',9445801474,1),
 (798,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','sneghadurai@gmail.com','D Snegha','IT/Software','D Snegha',9941752475,'Technology Traning',9941752475,1),
 (799,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','ankit.it97@gmail.com','Ankit Kumar','IT/Software','Ankit Kumar',9790726408,'Technology Traning',9790726408,1),
 (800,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','saikrishnareddy2308@gmail.com','Burugu Saikrishna Reddy','IT/Software','Burugu Saikrishna Reddy',8309078041,'Technology Traning',8309078041,1),
 (801,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','btechit2k15@gmail.com','Rahul Reddy','IT/Software','Rahul Reddy',8608063399,'Technology Traning',8608063399,1),
 (802,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','dipmallyasen_ma@srmuniv.edu.in','Dipmallya Sen','IT/Software','Dipmallya Sen',9840746157,'Technology Traning',9840746157,1),
 (803,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','kapilyadav1908@gmail.com','Kapil Yadav','IT/Software','Kapil Yadav',7827103331,'Technology Traning',7827103331,1),
 (804,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','abinayasaraswathi08@gmail.com','Abinayasaraswathi N','IT/Software','Abinayasaraswathi N',8526179719,'Technology Traning',8526179719,1),
 (805,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','atrayee.tripathi3@gmail.com','Atrayee Tripathi','IT/Software','Atrayee Tripathi',9784833303,'Technology Traning',9784833303,1),
 (806,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','akshayagsm12@gmail.com','Akshaya G','IT/Software','Akshaya G',8939061558,'Technology Traning',8939061558,1),
 (807,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','praveenreddy772@yahoo.com','Praveen Kumar Reddy G','IT/Software','Praveen Kumar Reddy G',8464820417,'Technology Traning',8464820417,1),
 (808,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','rainbowvarsha10@gmail.com','Varsha Ks','IT/Software','Varsha Ks',9840314607,'Technology Traning',9840314607,1),
 (809,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','percy.emili@gmail.com','Percy Emili','IT/Software','Percy Emili',8754565287,'Technology Traning',8754565287,1),
 (810,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','vinaykumargunda1998@gmail.com','Vinay Kumar','IT/Software','Vinay Kumar',7092068612,'Technology Traning',7092068612,1),
 (811,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','vanamamohith_va@srmuniv.edu.in','Vanama Mohith','IT/Software','Vanama Mohith',9566211918,'Technology Traning',9566211918,1),
 (812,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','gowthamgupta14@gmail.com','Cheedara Goutham Gupta','IT/Software','Cheedara Goutham Gupta',9840294205,'Technology Traning',9840294205,1),
 (813,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','golibabha12@gmail.com','Gopi Chowdary','IT/Software','Gopi Chowdary',9629087843,'Technology Traning',9629087843,1),
 (814,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','priyayuva1510@gmail.com','Priyanka.J','IT/Software','Priyanka.J',7871880904,'Technology Traning',7871880904,1),
 (815,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','surendramayaluru@gmail.com','Surendra Reddy','IT/Software','Surendra Reddy',8919130621,'Technology Traning',8919130621,1),
 (816,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','mundracharansai@gmail.com','Mundra Charan Sai','IT/Software','Mundra Charan Sai',9494650659,'Technology Traning',9494650659,1),
 (817,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','bhat_siddesh@yahoo.com','Siddesh Bhat','IT/Software','Siddesh Bhat',9981997810,'Technology Traning',9981997810,1),
 (818,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','sethan1999@gmail.com','Chethan Jesi','IT/Software','Chethan Jesi',9488392845,'Technology Traning',9488392845,1),
 (819,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','nitishjha3222@gmail.com','Nitish Jha','IT/Software','Nitish Jha',8428841115,'Technology Traning',8428841115,1),
 (820,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','nkvamsi20@gmail.com','Nekkanti Krishna Vamsi','IT/Software','Nekkanti Krishna Vamsi',9566211939,'Technology Traning',9566211939,1),
 (821,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','gaurav.oli@rediffmail.com','Gaurav Oli','IT/Software','Gaurav Oli',8851887159,'Technology Traning',8851887159,1),
 (822,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','srridhar005@gmail.com','Sridhar M','IT/Software','Sridhar M',9994159586,'Technology Traning',9994159586,1),
 (823,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','sukritigupta2611998@gmail.com','Sukriti Gupta','IT/Software','Sukriti Gupta',8124612446,'Technology Traning',8124612446,1),
 (824,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','anishghantasala1998@gmail.com','Anish Ghantasala','IT/Software','Anish Ghantasala',8519825911,'Technology Traning',8519825911,1),
 (825,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','priyadharshini.t98@gmail.com','Priyadharshini Thavasimani','IT/Software','Priyadharshini Thavasimani',8675123884,'Technology Traning',8675123884,1),
 (826,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','pankaj.prashantrawat@gmail.com','Prashant Singh Rawat','IT/Software','Prashant Singh Rawat',9557973108,'Technology Traning',9557973108,1),
 (827,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','mahaishu19@gmail.com','Mahalakshmi .S','IT/Software','Mahalakshmi .S',9840123556,'Technology Traning',9840123556,1),
 (828,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','sumithavelmani1@gmail.com','Sumitha V','IT/Software','Sumitha V',9042323613,'Technology Traning',9042323613,1),
 (829,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','peculiarpriyanka26@gmail.com','Priyanka Thirupathi','IT/Software','Priyanka Thirupathi',9944148448,'Technology Traning',9944148448,1),
 (830,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','tgaurav99@gmail.com','Gaurav Tiwari','IT/Software','Gaurav Tiwari',9962385482,'Technology Traning',9962385482,1),
 (831,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','rajprabhakar1804@gmail.com','Rajprabhakar Balasubramaniam','IT/Software','Rajprabhakar Balasubramaniam',9445610188,'Technology Traning',9445610188,1),
 (832,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','psaiteja120@gmail.com','Saiteja Paidimarri','IT/Software','Saiteja Paidimarri',9962418523,'Technology Traning',9962418523,1),
 (833,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','deenacse15@gmail.com','Deena Bandhu','IT/Software','Deena Bandhu',9790701322,'Technology Traning',9790701322,1),
 (834,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','nivedhasathya15@gmail.com','Nivedha Sathyanarayanan','IT/Software','Nivedha Sathyanarayanan',8144211664,'Technology Traning',8144211664,1),
 (835,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','ammar01996@gmail.com','Ammar Namliwala','IT/Software','Ammar Namliwala',8109383718,'Technology Traning',8109383718,1),
 (836,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','swethaswey98@gmail.com','Swetha N','IT/Software','Swetha N',8754613417,'Technology Traning',8754613417,1),
 (837,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','jviknesh02@gmail.com','J.Vignesh Kumar','IT/Software','J.Vignesh Kumar',8122935020,'Technology Traning',8122935020,1),
 (838,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','varshigs@gmail.com','Varshi Gupta','IT/Software','Varshi Gupta',9940942274,'Technology Traning',9940942274,1),
 (839,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','shanmugapriyan24.sp@gmail.com','Shanmuga Priyan','IT/Software','Shanmuga Priyan',7904334719,'Technology Traning',7904334719,1),
 (840,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','nishakars12@gmail.com','Nishakar Shanmugasundaram','IT/Software','Nishakar Shanmugasundaram',8248153775,'Technology Traning',8248153775,1),
 (841,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','tyagipragya99@gmail.com','Pragya Tagi','IT/Software','Pragya Tagi',9557866085,'Technology Traning',9557866085,1),
 (842,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','anukoolsriv@gmail.com','Anukool Srivastava','IT/Software','Anukool Srivastava',9884208903,'Technology Traning',9884208903,1),
 (843,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','shubhaangi04@gmail.com','Shubhaangi Verma','IT/Software','Shubhaangi Verma',9952090275,'Technology Traning',9952090275,1),
 (844,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','sekkrish95@gmail.com','Muralidharan Sekar','IT/Software','Muralidharan Sekar',8667895126,'Technology Traning',8667895126,1),
 (845,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','vishnuravichandran98@gmail.com','Vishnu Priya','IT/Software','Vishnu Priya',8939639493,'Technology Traning',8939639493,1),
 (846,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','jpukazh1998@gmail.com','Jaya Pukazhesh','IT/Software','Jaya Pukazhesh',9444303727,'Technology Traning',9444303727,1),
 (847,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','jerin496@gmail.com','Jerin John','IT/Software','Jerin John',9791116269,'Technology Traning',9791116269,1),
 (848,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','deepanshutiwari479@gmail.com','Deepanshu Tiwari','IT/Software','Deepanshu Tiwari',7007139324,'Technology Traning',7007139324,1);
INSERT INTO `customer` (`customer_Id`,`createdBy`,`createdTime`,`isDelete`,`modifiedBy`,`modifiedTime`,`sending_status`,`version`,`address`,`customer_type`,`dateOf_birth`,`email_id`,`first_name`,`industry`,`last_name`,`mobile_number`,`specialization`,`whatsapp_number`,`company_id`) VALUES 
 (849,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','aditya_anilkumarsinha@srmuniv.edu.in','Aditya Aditya','IT/Software','Aditya Aditya',9884206536,'Technology Traning',9884206536,1),
 (850,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','deepshikareddy998@gmail.com','Deepshika Reddy','IT/Software','Deepshika Reddy',8919417715,'Technology Traning',8919417715,1),
 (851,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','snehasuzanne7@gmail.com','Sneha Susan Thomas','IT/Software','Sneha Susan Thomas',7358002930,'Technology Traning',7358002930,1),
 (852,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','saranyaganeshkumar709@gmail.com','Saranya Ganesh','IT/Software','Saranya Ganesh',7338787467,'Technology Traning',7338787467,1),
 (853,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','sriramram898@gmail.com','Sri Ram','IT/Software','Sri Ram',9626533368,'Technology Traning',9626533368,1),
 (854,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','ankita010370@gmail.com','Ankita Panda','IT/Software','Ankita Panda',9884203192,'Technology Traning',9884203192,1),
 (855,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','priyankatn09@gmail.com','Priyanka Nalla','IT/Software','Priyanka Nalla',9884208285,'Technology Traning',9884208285,1),
 (856,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','17epmc511@skcet.ac.in','Drisya S','IT/Software','Drisya S',9633759244,'Technology Traning',9633759244,1),
 (857,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','geoffrina97@gmail.com','A.Grace Geoffrina','IT/Software','A.Grace Geoffrina',7598528700,'Technology Traning',7598528700,1),
 (858,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','adityasinhamajumdar@gmail.com','Aditya Sinha Majumdar','IT/Software','Aditya Sinha Majumdar',9566200674,'Technology Traning',9566200674,1),
 (859,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','prfctabhimanyu@gmail.com','Abhimanyu Mukherji','IT/Software','Abhimanyu Mukherji',9840355251,'Technology Traning',9840355251,1),
 (860,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','sabarish3bro@gmail.com','Sabarish Devaraju','IT/Software','Sabarish Devaraju',9940256285,'Technology Traning',9940256285,1),
 (861,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','itirkuna2824@gmail.com','Anukriti Rai','IT/Software','Anukriti Rai',8056094979,'Technology Traning',8056094979,1),
 (862,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','vamsicse0@gmail.com','Vamsi Krishna V','IT/Software','Vamsi Krishna V',8946073143,'Technology Traning',8946073143,1),
 (863,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','moinakbanerjee.98@gmail.com','Moinak Bandyopadhyay','IT/Software','Moinak Bandyopadhyay',9841873084,'Technology Traning',9841873084,1),
 (864,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','pavansrm999@gmail.com','Pavan Kumar','IT/Software','Pavan Kumar',9790758101,'Technology Traning',9790758101,1),
 (865,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','abhi97.suryavanshi@gmail.com','Abhishek Suryavanshi','IT/Software','Abhishek Suryavanshi',9962256987,'Technology Traning',9962256987,1),
 (866,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','adityavivek1998@gmail.com','Aditya Vivek Thota','IT/Software','Aditya Vivek Thota',9791500845,'Technology Traning',9791500845,1),
 (867,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','rishabh.modi.r5@gmail.com','Rishabh Modi','IT/Software','Rishabh Modi',9572130395,'Technology Traning',9572130395,1),
 (868,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','keerthijini@gmail.com','M Keerthana','IT/Software','M Keerthana',9791550602,'Technology Traning',9791550602,1),
 (869,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','janraks2015@gmail.com','Janani Ravishankar','IT/Software','Janani Ravishankar',8778159936,'Technology Traning',8778159936,1),
 (870,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','mohitgupta_ro@srmuniv.edu.in','Mohit Gupta','IT/Software','Mohit Gupta',9984184362,'Technology Traning',9984184362,1),
 (871,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','uniqueuday573@gmail.com','Uday Kumar Yadav','IT/Software','Uday Kumar Yadav',9551813160,'Technology Traning',9551813160,1),
 (872,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','divyabharathi175@gmail.com','Divya Bharathi','IT/Software','Divya Bharathi',8608243640,'Technology Traning',8608243640,1),
 (873,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','maik.maverick@gmail.com','Ishwar Kumar M.A','IT/Software','Ishwar Kumar M.A',9600022884,'Technology Traning',9600022884,1),
 (874,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','nihanair12345@gmail.com','Niha P','IT/Software','Niha P',7795977054,'Technology Traning',7795977054,1),
 (875,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','zaidsamad@gmail.com','Zaid Us Samad','IT/Software','Zaid Us Samad',9884206134,'Technology Traning',9884206134,1),
 (876,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','mewaramansi014@gmail.com','Mansi Mewara','IT/Software','Mansi Mewara',9840379291,'Technology Traning',9840379291,1),
 (877,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','yaminisekar20@gmail.com','Yamini Sekar','IT/Software','Yamini Sekar',8825481943,'Technology Traning',8825481943,1),
 (878,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','aparna2191997@gmail.com','Anjana Krishnan','IT/Software','Anjana Krishnan',9952065897,'Technology Traning',9952065897,1),
 (879,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','srivastava.varun19@gmail.com','Varun Srivastava','IT/Software','Varun Srivastava',9454009965,'Technology Traning',9454009965,1),
 (880,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','mubinabanu20@gmail.com','Mubeena Banu M','IT/Software','Mubeena Banu M',7397264640,'Technology Traning',7397264640,1),
 (881,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','jana66sana@gmail.com','Janani .S','IT/Software','Janani .S',7904537541,'Technology Traning',7904537541,1),
 (882,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','divyateja021197@gmail.com','Divya Devaraj','IT/Software','Divya Devaraj',9884992769,'Technology Traning',9884992769,1),
 (883,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','kamaliraghu98@gmail.com','Kamalaeswari .Ra','IT/Software','Kamalaeswari .Ra',8072387189,'Technology Traning',8072387189,1),
 (884,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','srinithip1998@gmail.com','Srinithi Pazhamalai','IT/Software','Srinithi Pazhamalai',8248068423,'Technology Traning',8248068423,1),
 (885,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','enjamdharmateja1997@gmail.com','Enjam Dharma Teja','IT/Software','Enjam Dharma Teja',8778339311,'Technology Traning',8778339311,1),
 (886,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','smagesh0605@gmail.com','Magesh S','IT/Software','Magesh S',9585241342,'Technology Traning',9585241342,1),
 (887,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','rrs1997r@gmail.com','Ramana Surriyan R','IT/Software','Ramana Surriyan R',9578129409,'Technology Traning',9578129409,1),
 (888,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','rachakonda.imp@gmail.com','Rachakonda Sai Vaishnavi','IT/Software','Rachakonda Sai Vaishnavi',9840282011,'Technology Traning',9840282011,1),
 (889,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','kesavram2908@gmail.com','Kesav Ram','IT/Software','Kesav Ram',9962779422,'Technology Traning',9962779422,1),
 (890,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','guptatanisha15@gmail.com','Tanisha Gupta','IT/Software','Tanisha Gupta',8210872488,'Technology Traning',8210872488,1),
 (891,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','archanagupta040@gmail.com','Archana Gupta','IT/Software','Archana Gupta',9884208985,'Technology Traning',9884208985,1),
 (892,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','kriti451gupta@gmail.com','Kriti Gupta','IT/Software','Kriti Gupta',6382239791,'Technology Traning',6382239791,1),
 (893,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','thilagagj@gmail.com','Thilagavathi J','IT/Software','Thilagavathi J',9677204217,'Technology Traning',9677204217,1),
 (894,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','ashwininvincible@gmail.com','Ashwin S','IT/Software','Ashwin S',9488192797,'Technology Traning',9488192797,1),
 (895,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','gingagopi@gmail.com','Gopi Krishnan','IT/Software','Gopi Krishnan',8939329988,'Technology Traning',8939329988,1),
 (896,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','efficiacarolin@gmail.com','Efficia Carolin','IT/Software','Efficia Carolin',9840822609,'Technology Traning',9840822609,1),
 (897,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','himanshubhandari546@gmail.com','Himanshu Bhandari','IT/Software','Himanshu Bhandari',8059245728,'Technology Traning',8059245728,1),
 (898,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','shalinisaravanan98@gmail.com','Shalini S','IT/Software','Shalini S',8838958300,'Technology Traning',8838958300,1),
 (899,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','supaindhu@gmail.com','Indhu S.P','IT/Software','Indhu S.P',9789247925,'Technology Traning',9789247925,1),
 (900,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','vinovisha23@gmail.com','Vinothini Muthu','IT/Software','Vinothini Muthu',9080835593,'Technology Traning',9080835593,1),
 (901,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','segusahab97@gmail.com','Segu Packeer Sahabudeen','IT/Software','Segu Packeer Sahabudeen',9443302274,'Technology Traning',9443302274,1),
 (902,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','karthik2781997@gmail.com','Pallagolla Karthik','IT/Software','Pallagolla Karthik',9502420561,'Technology Traning',9502420561,1),
 (903,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','priyanshu.srivastava1125@gmail.com','Priyanshu Srivastava','IT/Software','Priyanshu Srivastava',9566187279,'Technology Traning',9566187279,1),
 (904,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','patloladeepa.reddy313@gmail.com','Patlolla.Deepa Reddy','IT/Software','Patlolla.Deepa Reddy',9494714170,'Technology Traning',9494714170,1),
 (905,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','rohanarora03@gmail.com','Rohan Arora','IT/Software','Rohan Arora',9884209760,'Technology Traning',9884209760,1),
 (906,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','santhi.svks@gmail.com','Indhuja Parthiban','IT/Software','Indhuja Parthiban',9003032925,'Technology Traning',9003032925,1),
 (907,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','player.kushal@gmail.com','Kushal Matalia','IT/Software','Kushal Matalia',8051164101,'Technology Traning',8051164101,1),
 (908,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','vaibhav.pandey355@hotmail.com','Vaibhav Pandey','IT/Software','Vaibhav Pandey',8668193793,'Technology Traning',8668193793,1),
 (909,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','kavushikh@gmail.com','Kavushikh Anand','IT/Software','Kavushikh Anand',9566019553,'Technology Traning',9566019553,1),
 (910,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','niveramyaa@gmail.com','Niveda A','IT/Software','Niveda A',9789482498,'Technology Traning',9789482498,1),
 (911,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','tjbalachander97@gmail.com','Balachander T J','IT/Software','Balachander T J',7010405047,'Technology Traning',7010405047,1),
 (912,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','shaliniinisha@gmail.com','Shalini J','IT/Software','Shalini J',7200513536,'Technology Traning',7200513536,1),
 (913,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','hgoel97@gmail.com','Harshit Goel','IT/Software','Harshit Goel',9410321394,'Technology Traning',9410321394,1),
 (914,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','gauravbajpai_sa@srmuniv.edu.in','Gaurav Bajpai','IT/Software','Gaurav Bajpai',8957782922,'Technology Traning',8957782922,1),
 (915,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','ruparamachandran97@gmail.com','Rupa Ramachandran','IT/Software','Rupa Ramachandran',8072493219,'Technology Traning',8072493219,1),
 (916,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','thilakpraveen1123@gmail.com','Thilak Praveen.A','IT/Software','Thilak Praveen.A',9176811997,'Technology Traning',9176811997,1),
 (917,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','achuperumal98@gmail.com','Akshaya Dhevi K','IT/Software','Akshaya Dhevi K',9790197243,'Technology Traning',9790197243,1),
 (918,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','varalakshmiraji1620@gmail.com','Varalakshmi G','IT/Software','Varalakshmi G',8056199874,'Technology Traning',8056199874,1),
 (919,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','2496sg@gmail.com','Shivang Garg','IT/Software','Shivang Garg',8630215027,'Technology Traning',8630215027,1),
 (920,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','kali.pravallika@gmail.com','Pravallika Samayamanthula','IT/Software','Pravallika Samayamanthula',9110336562,'Technology Traning',9110336562,1),
 (921,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','jindal96david@gmail.com','David Jindal','IT/Software','David Jindal',8769945567,'Technology Traning',8769945567,1),
 (922,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','avskkrish80@gmail.com','A Venkata Sai Krishna','IT/Software','A Venkata Sai Krishna',9840363361,'Technology Traning',9840363361,1),
 (923,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','surajsgoel@gmail.com','Suraj Goel','IT/Software','Suraj Goel',9566190341,'Technology Traning',9566190341,1),
 (924,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','mohammedfahimullah_moh@srmuniv','Mohammed Fahimullah.A','IT/Software','Mohammed Fahimullah.A',7418943105,'Technology Traning',7418943105,1),
 (925,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','anjitha0304@gmail.com','Chinnu A','IT/Software','Chinnu A',8319938598,'Technology Traning',8319938598,1),
 (926,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','dhiyaneshgod@gmail.com','S Dhiyanesh','IT/Software','S Dhiyanesh',7010168091,'Technology Traning',7010168091,1),
 (927,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','jugnuk232@gmail.com','Jugnu Kumar','IT/Software','Jugnu Kumar',9884176408,'Technology Traning',9884176408,1),
 (928,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','gayathrimegvenkatesan@gmail.com','Gayathri V','IT/Software','Gayathri V',9443207915,'Technology Traning',9443207915,1),
 (929,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','v.priya.bhuvi19@gmail.com','Devaki Priya V','IT/Software','Devaki Priya V',7397353501,'Technology Traning',7397353501,1),
 (930,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','pavithra9863@gmail.com','Pavithra Dillibabu','IT/Software','Pavithra Dillibabu',7871914032,'Technology Traning',7871914032,1),
 (931,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','aravinth.vp88@gmail.com','Vengatesharavinth Pothiyappan','IT/Software','Vengatesharavinth Pothiyappan',9894278940,'Technology Traning',9894278940,1),
 (932,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','pal.harendra95@gmail.com','Harendra Singh','IT/Software','Harendra Singh',9790729403,'Technology Traning',9790729403,1),
 (933,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','deyatin@gmail.com','Atin Kumar Dey','IT/Software','Atin Kumar Dey',9962260211,'Technology Traning',9962260211,1),
 (934,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','sb.karthikeyan98@gmail.com','Karthikeyan S B','IT/Software','Karthikeyan S B',9087026643,'Technology Traning',9087026643,1),
 (935,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','vinishavinu.rayam@gmail.com','R.Vinisha Sri','IT/Software','R.Vinisha Sri',7448627681,'Technology Traning',7448627681,1),
 (936,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','samreenshazia786@gmail.com','Gn Samreen','IT/Software','Gn Samreen',7338793576,'Technology Traning',7338793576,1),
 (937,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','prathibhar004@gmail.com','Prathibha R','IT/Software','Prathibha R',8762063807,'Technology Traning',8762063807,1),
 (938,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','baaluganesh26@gmail.com','Baaluganesh M S','IT/Software','Baaluganesh M S',9994833273,'Technology Traning',9994833273,1),
 (939,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','m.priya4dharshini@gmail.com','Priyadharshini M','IT/Software','Priyadharshini M',9952951037,'Technology Traning',9952951037,1),
 (940,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','parkavib1997@gmail.com','Parkavi Balamurugan','IT/Software','Parkavi Balamurugan',9095910825,'Technology Traning',9095910825,1),
 (941,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','pramo1997@gmail.com','Pramodini R','IT/Software','Pramodini R',9789071199,'Technology Traning',9789071199,1),
 (942,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','aditirai_sa@srmuniv.edu.in','Aditi Rai','IT/Software','Aditi Rai',7005729429,'Technology Traning',7005729429,1),
 (943,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','sreevigneshsridhar@gmail.com','Sreevignesh Sridhar','IT/Software','Sreevignesh Sridhar',9894796101,'Technology Traning',9894796101,1),
 (944,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','balaji.jeyaseelan@gmail.com','Balaji J','IT/Software','Balaji J',8072607085,'Technology Traning',8072607085,1),
 (945,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','ashuparthi15@gmail.com','Ashwathi Parthiban','IT/Software','Ashwathi Parthiban',9629606655,'Technology Traning',9629606655,1),
 (946,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','kanmani.cvm28@gmail.com','Kanmani E','IT/Software','Kanmani E',7550110868,'Technology Traning',7550110868,1),
 (947,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','sushithalakshmi1997@gmail.com','Yandra Sanjeeva Sushitha Lakshmi','IT/Software','Yandra Sanjeeva Sushitha Lakshmi',9003835347,'Technology Traning',9003835347,1),
 (948,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','mohannaresh2013@gmail.com','M Narasimhan','IT/Software','M Narasimhan',7675984249,'Technology Traning',7675984249,1),
 (949,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','anupurushoth97@gmail.com','Mohana Purushothaman','IT/Software','Mohana Purushothaman',9940097609,'Technology Traning',9940097609,1),
 (950,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','rkhe.37@gmail.com','Radhika Khetarpal','IT/Software','Radhika Khetarpal',8754561676,'Technology Traning',8754561676,1),
 (951,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','manojprabhakaran97@gmail.com','M.Manoj Prabhakaran','IT/Software','M.Manoj Prabhakaran',9171494233,'Technology Traning',9171494233,1),
 (952,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','bhavyagaur97@yahoo.in','Bhavya Gaur','IT/Software','Bhavya Gaur',9013353635,'Technology Traning',9013353635,1),
 (953,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','boomija113@gmail.com','Boomija G','IT/Software','Boomija G',9551710031,'Technology Traning',9551710031,1),
 (954,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','tarunsinghani97@gmail.com','Tarun Singhani','IT/Software','Tarun Singhani',9884208785,'Technology Traning',9884208785,1),
 (955,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','araj2805@gmail.com','Ankit Raj','IT/Software','Ankit Raj',9500029436,'Technology Traning',9500029436,1),
 (956,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','sanjay.mayavaram@gmail.com','Sanjay Palanivel','IT/Software','Sanjay Palanivel',9488443058,'Technology Traning',9488443058,1),
 (957,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','priyabalasubramani0111@gmail.com','Shanmuga Priya','IT/Software','Shanmuga Priya',9884169769,'Technology Traning',9884169769,1),
 (958,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','ksathishk51025@gmail.com','Satheesh Kumar K','IT/Software','Satheesh Kumar K',8838927131,'Technology Traning',8838927131,1),
 (959,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','ganapathyg981@gmail.com','Ganapathy T','IT/Software','Ganapathy T',8056062154,'Technology Traning',8056062154,1),
 (960,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','srishtishreya1997.ss@gmail.com','Srishti Singh','IT/Software','Srishti Singh',9087490174,'Technology Traning',9087490174,1),
 (961,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','sakshitiwari1101@gmail.com','Sakshi Tiwari','IT/Software','Sakshi Tiwari',7397335304,'Technology Traning',7397335304,1),
 (962,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','dhanan1998@gmail.com','Dhananchezhiyan K','IT/Software','Dhananchezhiyan K',9543387247,'Technology Traning',9543387247,1),
 (963,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','preetchaudhary007@gmail.com','Preet Chaudhary','IT/Software','Preet Chaudhary',7983090055,'Technology Traning',7983090055,1),
 (964,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','aashish23gautam@gmail.com','Aashish Gautam','IT/Software','Aashish Gautam',9988756756,'Technology Traning',9988756756,1),
 (965,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','csenooglers.rocks@gmail.com','Asina M','IT/Software','Asina M',8939628289,'Technology Traning',8939628289,1),
 (966,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','pramitranjankole@gmail.com','Pramit Ranjan Kole','IT/Software','Pramit Ranjan Kole',9790710750,'Technology Traning',9790710750,1),
 (967,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','15tuit009@skct.edu.in','Arockia Sharmila','IT/Software','Arockia Sharmila',8667260417,'Technology Traning',8667260417,1),
 (968,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','vigneshvck28@gmail.com','Vignesh V','IT/Software','Vignesh V',9843994797,'Technology Traning',9843994797,1),
 (969,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','lavanyapalanisamy04feb@gmail.com','Lavany Palanisamy','IT/Software','Lavany Palanisamy',8072636021,'Technology Traning',8072636021,1),
 (970,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','vlekhashree7@gmail.com','Lekha Shree .V','IT/Software','Lekha Shree .V',8754754777,'Technology Traning',8754754777,1),
 (971,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','vedavyasthirunagari@gmail.com','Veda Vyas','IT/Software','Veda Vyas',9566074506,'Technology Traning',9566074506,1),
 (972,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','banerjeeheerok1997@gmail.com','Heerok Banerjee','IT/Software','Heerok Banerjee',9566213491,'Technology Traning',9566213491,1),
 (973,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','jayramsahusrm@gmail.com','Jay Sahu','IT/Software','Jay Sahu',8349426715,'Technology Traning',8349426715,1),
 (974,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','aash1dharani@gmail.com','A Aishwarya','IT/Software','A Aishwarya',7358308302,'Technology Traning',7358308302,1),
 (975,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','ashish_nilesh@srmuniv.edu.in','Ashish Bajpayee','IT/Software','Ashish Bajpayee',7358311328,'Technology Traning',7358311328,1),
 (976,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','asahithyaa1997@gmail.com','Sahithyaa Arulselvam','IT/Software','Sahithyaa Arulselvam',9962803118,'Technology Traning',9962803118,1),
 (977,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','kumar.adityav@gmail.com','Aditya Kumar','IT/Software','Aditya Kumar',9723196715,'Technology Traning',9723196715,1),
 (978,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','adityapratap_ra@srmuniv.edu.in','Aditya Pratap Singh Rajput','IT/Software','Aditya Pratap Singh Rajput',9790707569,'Technology Traning',9790707569,1),
 (979,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','vinaysahoo77@gmail.com','Vinay Sahoo U','IT/Software','Vinay Sahoo U',9789955961,'Technology Traning',9789955961,1),
 (980,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','monishapink27@gmail.com','Monisha Jotheeswaran','IT/Software','Monisha Jotheeswaran',9677029595,'Technology Traning',9677029595,1),
 (981,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','abbusk33@gmail.com','Shaik Sohid','IT/Software','Shaik Sohid',7995239729,'Technology Traning',7995239729,1),
 (982,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','shubhamsingh_gp@srmuniv.edu.in','Shubham Singh','IT/Software','Shubham Singh',7376652426,'Technology Traning',7376652426,1),
 (983,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','\naayushnama_ashokkumarsamariya1@gmail.com','Aayush Nama','IT/Software','Aayush Nama',7597722038,'Technology Traning',7597722038,1),
 (984,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','rishabhgoel9797@gmail.com','Rishabh Goel','IT/Software','Rishabh Goel',9790713934,'Technology Traning',9790713934,1),
 (985,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','yugandhar.thota@gmail.com','Thota Yugandhar','IT/Software','Thota Yugandhar',8639767974,'Technology Traning',8639767974,1),
 (986,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','balanhari7@gmail.com','Arumugam Haribalan','IT/Software','Arumugam Haribalan',9032147983,'Technology Traning',9032147983,1),
 (987,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','r.sujitha17@gmail.com','Sujitha Priyadharshini R','IT/Software','Sujitha Priyadharshini R',9597962973,'Technology Traning',9597962973,1),
 (988,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','shubhamkumar_ma@srmuniv.edu.in','Shubham Kumar','IT/Software','Shubham Kumar',8754539614,'Technology Traning',8754539614,1),
 (989,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','darshil98.shah@gmail.com','Darshil Shah','IT/Software','Darshil Shah',9962256834,'Technology Traning',9962256834,1),
 (990,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','lakshmitk98@gmail.com','Lakshmi T.K','IT/Software','Lakshmi T.K',9600154848,'Technology Traning',9600154848,1),
 (991,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','ambikaramesh001@gmail.com','Ambika Ramesh','IT/Software','Ambika Ramesh',6381589911,'Technology Traning',6381589911,1),
 (992,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','kaustubgupta_arvind1@srmuniv.edu.in','Kaustub Gupta','IT/Software','Kaustub Gupta',9618918862,'Technology Traning',9618918862,1),
 (993,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','sharmanagendra372@gmail.com','Nagendra Sharma','IT/Software','Nagendra Sharma',7358623462,'Technology Traning',7358623462,1),
 (994,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','ashutosh1631998@gmail.com','Ashutosh Kumar Singh','IT/Software','Ashutosh Kumar Singh',9092067345,'Technology Traning',9092067345,1),
 (995,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','mohana9716@gmail.com','Mohana Priya.R','IT/Software','Mohana Priya.R',8939420937,'Technology Traning',8939420937,1),
 (996,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','nandhu143divya@gmail.com','Nandhini Vaidyalingam','IT/Software','Nandhini Vaidyalingam',9566346932,'Technology Traning',9566346932,1),
 (997,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','lokeshs1997.cse15@veltechmultitech.','Lokesh S','IT/Software','Lokesh S',8056039020,'Technology Traning',8056039020,1),
 (998,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','dhanaraina389@gmail.com','Dhana Sekar R','IT/Software','Dhana Sekar R',9994859589,'Technology Traning',9994859589,1),
 (999,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','shashakchandel@gmail.com','Shashank Singh Chandel','IT/Software','Shashank Singh Chandel',9790710421,'Technology Traning',9790710421,1),
 (1000,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','akhilmaddula8@gmail.com','Akhil Maddula','IT/Software','Akhil Maddula',8341424303,'Technology Traning',8341424303,1),
 (1001,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','rkavitha1997@gmail.com','Kavitha R','IT/Software','Kavitha R',8939574265,'Technology Traning',8939574265,1),
 (1002,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','vanshkhanna17@gmail.com','Vansh Khanna','IT/Software','Vansh Khanna',7906487975,'Technology Traning',7906487975,1),
 (1003,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','preethi111197@gmail.com','Preethi Subbu','IT/Software','Preethi Subbu',9444518319,'Technology Traning',9444518319,1),
 (1004,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','tnvssv97@gmail.com','Saivani Tangirala','IT/Software','Saivani Tangirala',7981932745,'Technology Traning',7981932745,1),
 (1005,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','gvsk24997@gmail.com','Saikrishna G V','IT/Software','Saikrishna G V',9500270700,'Technology Traning',9500270700,1),
 (1006,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','manojrec138@gmail.com','Manojkumar B','IT/Software','Manojkumar B',9003154739,'Technology Traning',9003154739,1),
 (1007,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','jeevanreddy.jan9@gmail.com','Ayyagari Jeevan Reddy','IT/Software','Ayyagari Jeevan Reddy',7981599104,'Technology Traning',7981599104,1),
 (1008,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','divyab0409@gmail.com','Divya Babu','IT/Software','Divya Babu',8248253923,'Technology Traning',8248253923,1),
 (1009,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','achu1697@gmail.com','Akshay Kumar','IT/Software','Akshay Kumar',9566056647,'Technology Traning',9566056647,1),
 (1010,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','hemakavi3098@gmail.com','Hemavathy Kuppuswamy','IT/Software','Hemavathy Kuppuswamy',9940289384,'Technology Traning',9940289384,1),
 (1011,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','divya1997jayaraman@gmail.com','J.Vasuki Divya','IT/Software','J.Vasuki Divya',7395932231,'Technology Traning',7395932231,1),
 (1012,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','nivetavenkat@gmail.com','Niveta V','IT/Software','Niveta V',7395973205,'Technology Traning',7395973205,1),
 (1013,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','sahilansari5655@gmail.com','Sahil Na','IT/Software','Sahil Na',9818045794,'Technology Traning',9818045794,1),
 (1014,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','ananthadileep963@gmail.com','Anantha Dileep','IT/Software','Anantha Dileep',9701981182,'Technology Traning',9701981182,1),
 (1015,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','ashappy1210@gmail.com','Aishwarya V','IT/Software','Aishwarya V',8939139372,'Technology Traning',8939139372,1),
 (1016,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','likhithanidimusali@gmail.com','Nidimusali Likhitha','IT/Software','Nidimusali Likhitha',8121971632,'Technology Traning',8121971632,1),
 (1017,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','angelin9814@gmail.com','Nivedha Jenifer','IT/Software','Nivedha Jenifer',9940433652,'Technology Traning',9940433652,1),
 (1018,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','chtra98@gmail.com','Chitra Rajan','IT/Software','Chitra Rajan',9791129325,'Technology Traning',9791129325,1),
 (1019,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','adityahit1234@gmail.com','Aditya Kumar','IT/Software','Aditya Kumar',8825764217,'Technology Traning',8825764217,1),
 (1020,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','ponkumar45@gmail.com','Ponkumar Krishnan','IT/Software','Ponkumar Krishnan',9791024423,'Technology Traning',9791024423,1),
 (1021,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','annetteunice98@gmail.com','Annette Paul','IT/Software','Annette Paul',7397471886,'Technology Traning',7397471886,1),
 (1022,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','ssachinsuresh24@gmail.com','Sachin S','IT/Software','Sachin S',9600622534,'Technology Traning',9600622534,1),
 (1023,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','thilagavathyraja97@gmail.com','Thilagavathy Raja','IT/Software','Thilagavathy Raja',9840347431,'Technology Traning',9840347431,1),
 (1024,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','shamsafrinayesha@gmail.com','Shams Afrin Ayesha','IT/Software','Shams Afrin Ayesha',7358589317,'Technology Traning',7358589317,1),
 (1025,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','dashprat@gmail.com','Prateek Dash','IT/Software','Prateek Dash',9884206249,'Technology Traning',9884206249,1),
 (1026,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','moni2vaishnavi6@gmail.com','Monica Raghu','IT/Software','Monica Raghu',9551984877,'Technology Traning',9551984877,1),
 (1027,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','varshithabathala2862@gmail.com','Varshitha Bathala','IT/Software','Varshitha Bathala',9676123236,'Technology Traning',9676123236,1),
 (1028,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','arulvasu17598@gmail.com','Arul Arasi','IT/Software','Arul Arasi',8110011715,'Technology Traning',8110011715,1),
 (1029,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','divyanshu_ran@srmuniv.edu.in','Divyanshu .','IT/Software','Divyanshu .',9790708305,'Technology Traning',9790708305,1),
 (1030,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','lakshya.agarwal97@gmail.com','Lakshya Agarwal','IT/Software','Lakshya Agarwal',9667529350,'Technology Traning',9667529350,1),
 (1031,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','chandansinghavit@gmail.com','Chandan Kumar','IT/Software','Chandan Kumar',9871557768,'Technology Traning',9871557768,1),
 (1032,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','selvishakthi238@gmail.com','Mohanaselvi. R','IT/Software','Mohanaselvi. R',8056117198,'Technology Traning',8056117198,1),
 (1033,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','harshkumar_sha@srmuniv.edu.in','Harsh Kumar Daga','IT/Software','Harsh Kumar Daga',9085082008,'Technology Traning',9085082008,1),
 (1034,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','gashwin2511@gmail.com','Ashwin G','IT/Software','Ashwin G',9789083262,'Technology Traning',9789083262,1),
 (1035,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','swatipurna19@gmail.com','Swati Purna Sahoo','IT/Software','Swati Purna Sahoo',9962260411,'Technology Traning',9962260411,1),
 (1036,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','abhigyani.97@gmail.com','Abhigyani Anand','IT/Software','Abhigyani Anand',8056214677,'Technology Traning',8056214677,1),
 (1037,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','vaishnav_karthikeyan@yahoo.com','Vaishnav Karthikeyan','IT/Software','Vaishnav Karthikeyan',9629444314,'Technology Traning',9629444314,1),
 (1038,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','ayush804@gmail.com','Ayush A','IT/Software','Ayush A',9962252819,'Technology Traning',9962252819,1),
 (1039,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','shantamtandon96@gmail.com','Shantam Tandon','IT/Software','Shantam Tandon',9621125341,'Technology Traning',9621125341,1),
 (1040,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','venkatesh20001910@gmail.com','Venkatesh R','IT/Software','Venkatesh R',8610664737,'Technology Traning',8610664737,1),
 (1041,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','sauhardsaksham@gmail.com','Saksham Sauhard','IT/Software','Saksham Sauhard',9566019508,'Technology Traning',9566019508,1),
 (1042,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','gauravjitborah@gmail.com','Gauravjit Borah','IT/Software','Gauravjit Borah',7397443124,'Technology Traning',7397443124,1),
 (1043,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','vickyrvignesh22@gmail.com','Vignesh P','IT/Software','Vignesh P',8903121369,'Technology Traning',8903121369,1),
 (1044,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','poovizhi1797@gmail.com','Poovizhi Shanmugasundaram','IT/Software','Poovizhi Shanmugasundaram',9488228339,'Technology Traning',9488228339,1),
 (1045,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','17epmc509@skcet.ac.in','Deena Joseph','IT/Software','Deena Joseph',9074052733,'Technology Traning',9074052733,1),
 (1046,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','rithanyavenkatessan1997@gmail.com','Rithanya Venkatessan','IT/Software','Rithanya Venkatessan',9790321392,'Technology Traning',9790321392,1),
 (1047,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','dogra.dd@gmail.com','Dhananjay Dogra','IT/Software','Dhananjay Dogra',8056818867,'Technology Traning',8056818867,1),
 (1048,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','priyamadhan28@gmail.com','Poornashree Madhanraj','IT/Software','Poornashree Madhanraj',9884912152,'Technology Traning',9884912152,1),
 (1049,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','umayalmbau@gmail.com','Umayal Meyyappan','IT/Software','Umayal Meyyappan',9500108216,'Technology Traning',9500108216,1),
 (1050,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','17epmc508@skcet.ac.in','Bhavya K B','IT/Software','Bhavya K B',9495403257,'Technology Traning',9495403257,1),
 (1051,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','geethabommi139@gmail.com','Geetha P','IT/Software','Geetha P',8110864438,'Technology Traning',8110864438,1),
 (1052,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','madhurrajesh_ra@srmuniv.edu.in','Madhur Rajesh Shukla','IT/Software','Madhur Rajesh Shukla',9962258238,'Technology Traning',9962258238,1),
 (1053,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','sshriya1997@gmail.com','Shriya Suresh','IT/Software','Shriya Suresh',9042569734,'Technology Traning',9042569734,1),
 (1054,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','satyam978@gmail.com','Satyam Puniya','IT/Software','Satyam Puniya',9566187787,'Technology Traning',9566187787,1),
 (1055,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','raj90919299@gmail.com','Rahul Raj','IT/Software','Rahul Raj',7092929660,'Technology Traning',7092929660,1),
 (1056,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','jayeshhsharma@gmail.com','Jayeshh Sharma','IT/Software','Jayeshh Sharma',8887916325,'Technology Traning',8887916325,1),
 (1057,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','hemavid@gmail.com','Hemalakshmi M','IT/Software','Hemalakshmi M',9566109960,'Technology Traning',9566109960,1),
 (1058,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','rajabalaji47@gmail.com','Balaji S','IT/Software','Balaji S',7358686323,'Technology Traning',7358686323,1),
 (1059,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','sarasg2air@gmail.com','Sarala Devi','IT/Software','Sarala Devi',7358599355,'Technology Traning',7358599355,1),
 (1060,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','vigneshvicky.vicky18@gmail.com','Vignesh Vicky','IT/Software','Vignesh Vicky',8807897104,'Technology Traning',8807897104,1),
 (1061,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','lskapil0110@gmail.com','Ls Kapil','IT/Software','Ls Kapil',9818896254,'Technology Traning',9818896254,1),
 (1062,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','anjalipriya0522@gmail.com','Anjali Priya','IT/Software','Anjali Priya',9790618156,'Technology Traning',9790618156,1),
 (1063,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','agarwalsuyash0522@gmail.com','Suyash Agarwal','IT/Software','Suyash Agarwal',9790722576,'Technology Traning',9790722576,1),
 (1064,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','abhilasha_somani@yahoo.co.in','Abhilasha Somani','IT/Software','Abhilasha Somani',9854042761,'Technology Traning',9854042761,1),
 (1065,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','rohit1997agrawal@gmail.com','Rohit Agrawal','IT/Software','Rohit Agrawal',9790705165,'Technology Traning',9790705165,1),
 (1066,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','pranshu453@gmail.com','Pranshu Sharma','IT/Software','Pranshu Sharma',9459197900,'Technology Traning',9459197900,1),
 (1067,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','tanmaishukla1997@gmail.com','Tanmai Shukla','IT/Software','Tanmai Shukla',9453774991,'Technology Traning',9453774991,1),
 (1068,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','bishwaroop25@gmail.com','Bishwaroop Bhattacharjee','IT/Software','Bishwaroop Bhattacharjee',9566211627,'Technology Traning',9566211627,1),
 (1069,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','chittenisreedhar@gmail.com','Sreedhar C','IT/Software','Sreedhar C',9994699551,'Technology Traning',9994699551,1),
 (1070,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','aasthasingh_aja@srmuniv.edu.in','Aastha Singh','IT/Software','Aastha Singh',9884210057,'Technology Traning',9884210057,1),
 (1071,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','saivardhini.26.svg@gmail.com','Sai Vardhini','IT/Software','Sai Vardhini',8682052617,'Technology Traning',8682052617,1),
 (1072,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','kamalesh.1562@gmail.com','S Nandhini','IT/Software','S Nandhini',9514508233,'Technology Traning',9514508233,1),
 (1073,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','rishabhsuri1@gmail.com','Rishabh Suri','IT/Software','Rishabh Suri',9616323456,'Technology Traning',9616323456,1),
 (1074,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','prateekj777@gmail.com','Prateek Kumar Jha','IT/Software','Prateek Kumar Jha',8507783429,'Technology Traning',8507783429,1),
 (1075,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','hedasp@gmail.com','Sparsh Heda','IT/Software','Sparsh Heda',8503810920,'Technology Traning',8503810920,1),
 (1076,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','munuganti15@gmail.com','Surya Munuganti','IT/Software','Surya Munuganti',8801701755,'Technology Traning',8801701755,1),
 (1077,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','shivateja.m@gmail.com','Mylavarapu Shivateja','IT/Software','Mylavarapu Shivateja',9566200625,'Technology Traning',9566200625,1),
 (1078,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','shreyapalit6@gmail.com','Shreya Palit','IT/Software','Shreya Palit',7358552761,'Technology Traning',7358552761,1),
 (1079,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','shreyanimmagadda@gmail.com','Shreya Nimmagadda','IT/Software','Shreya Nimmagadda',7358594880,'Technology Traning',7358594880,1),
 (1080,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','jeswin.geo@live.com','Jeswin George','IT/Software','Jeswin George',9633159033,'Technology Traning',9633159033,1),
 (1081,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','archana.harinarayanan.1@gmail.com','Archana H','IT/Software','Archana H',8848327916,'Technology Traning',8848327916,1),
 (1082,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','mubashir5049@gmail.com','Md Mubashir','IT/Software','Md Mubashir',9884207364,'Technology Traning',9884207364,1),
 (1083,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','shanthini1412@gmail.com','Shanthini M','IT/Software','Shanthini M',9962207795,'Technology Traning',9962207795,1),
 (1084,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','andrewjerry007@gmail.com','Andrew Jerry','IT/Software','Andrew Jerry',8667207674,'Technology Traning',8667207674,1),
 (1085,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','preethibalu1997@gmail.com','Preethi B','IT/Software','Preethi B',9502038836,'Technology Traning',9502038836,1),
 (1086,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','rohitchowdary243@gmail.com','Narra Rohit','IT/Software','Narra Rohit',8309967724,'Technology Traning',8309967724,1),
 (1087,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','rishabhagarwal_r@srmuniv.edu.in','Rishabh Agarwal','IT/Software','Rishabh Agarwal',9820065733,'Technology Traning',9820065733,1),
 (1088,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','sohan.28sarma@gmail.com','Sohan Sarma Mazumder','IT/Software','Sohan Sarma Mazumder',8638874778,'Technology Traning',8638874778,1),
 (1089,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','nishamu23@gmail.com','Nisha Nm','IT/Software','Nisha Nm',9597032668,'Technology Traning',9597032668,1),
 (1090,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','pratapkriti@gmail.com','Kriti Pratap','IT/Software','Kriti Pratap',9884203221,'Technology Traning',9884203221,1),
 (1091,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','ragav.chevrolet@gmail.com','Rhaghav Madheswaran','IT/Software','Rhaghav Madheswaran',9600097533,'Technology Traning',9600097533,1),
 (1092,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','venkateshsaran3@gmail.com','Venkatesh R','IT/Software','Venkatesh R',9003173469,'Technology Traning',9003173469,1),
 (1093,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','abhi8673@gmail.com','Abhinav Sharma','IT/Software','Abhinav Sharma',8754517046,'Technology Traning',8754517046,1),
 (1094,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','tanvimahajan15@gmail.com','Tanvi Mahajan','IT/Software','Tanvi Mahajan',7508344768,'Technology Traning',7508344768,1),
 (1095,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','adityabommaraju97@gmail.com','Bmsss Aditya','IT/Software','Bmsss Aditya',9160995241,'Technology Traning',9160995241,1),
 (1096,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','gauravraj_kr@srmuniv.edu.in','Gaurav Raj','IT/Software','Gaurav Raj',9962256516,'Technology Traning',9962256516,1),
 (1097,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','aditikothari13@gmail.com','Aditi Kothari','IT/Software','Aditi Kothari',9176221802,'Technology Traning',9176221802,1),
 (1098,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','neelsattigiri@gmail.com','Neel Jitendra Sattigiri','IT/Software','Neel Jitendra Sattigiri',9176403473,'Technology Traning',9176403473,1),
 (1099,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','sahith2534@gmail.com','Sahith Maddipatla','IT/Software','Sahith Maddipatla',9626279352,'Technology Traning',9626279352,1),
 (1100,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','monishde6@gmail.com','Monish De','IT/Software','Monish De',7358098160,'Technology Traning',7358098160,1),
 (1101,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','vijayrohit.pvr@gmail.com','Pantam Sai Vijay Rohit','IT/Software','Pantam Sai Vijay Rohit',8639551125,'Technology Traning',8639551125,1),
 (1102,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','jagadeeshbose567@gmail.com','V. Jagadeesh Chandra Bose','IT/Software','V. Jagadeesh Chandra Bose',8939352689,'Technology Traning',8939352689,1),
 (1103,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','swesu1010@gmail.com','Swekchha Suman','IT/Software','Swekchha Suman',9471575464,'Technology Traning',9471575464,1),
 (1104,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','shubhishubhangi30@gmail.com','Shubhi Shubhangi','IT/Software','Shubhi Shubhangi',9566015166,'Technology Traning',9566015166,1),
 (1105,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','saket.bajaj1998@gmail.com','Saket Bajaj','IT/Software','Saket Bajaj',8420241000,'Technology Traning',8420241000,1),
 (1106,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','rinujohny24@gmail.com','Rinu Johny','IT/Software','Rinu Johny',9884206365,'Technology Traning',9884206365,1),
 (1107,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','ashishag29@gmail.com','Ashish Kumar Agarwal','IT/Software','Ashish Kumar Agarwal',8292707835,'Technology Traning',8292707835,1),
 (1108,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','abszee97@gmail.com','Abhilash Arun','IT/Software','Abhilash Arun',9566211946,'Technology Traning',9566211946,1),
 (1109,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','sinhabhanu12@gmail.com','Bhanu Sinha','IT/Software','Bhanu Sinha',9566192160,'Technology Traning',9566192160,1),
 (1110,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','saipranav786@gmail.com','Putta Sai Pranav','IT/Software','Putta Sai Pranav',7013570860,'Technology Traning',7013570860,1),
 (1111,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','shubham.malu30@gmail.com','Shubham Malu','IT/Software','Shubham Malu',6380944250,'Technology Traning',6380944250,1),
 (1112,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','sp.parasa@gmail.com','Parasa Sai Praneeth','IT/Software','Parasa Sai Praneeth',8608796946,'Technology Traning',8608796946,1),
 (1113,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','abhisheknath42@gmail.com','Abhishek Nath','IT/Software','Abhishek Nath',8895648572,'Technology Traning',8895648572,1),
 (1114,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','tejareddy111223@gmail.com','Vemireddy Teja Reddy','IT/Software','Vemireddy Teja Reddy',7358647847,'Technology Traning',7358647847,1),
 (1115,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','navaneethg96@gmail.com','G Navaneeth','IT/Software','G Navaneeth',7358168927,'Technology Traning',7358168927,1),
 (1116,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','chadarohankumar@gmail.com','Chada Rohan Kumar','IT/Software','Chada Rohan Kumar',7358331447,'Technology Traning',7358331447,1),
 (1117,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','vinishree.97@gmail.com','Vinitha Shree','IT/Software','Vinitha Shree',9600080779,'Technology Traning',9600080779,1),
 (1118,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','soumyaranjan_aj@srmuniv.edu.in','Soumya Ranjan Pradhan','IT/Software','Soumya Ranjan Pradhan',7978860619,'Technology Traning',7978860619,1),
 (1119,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','zeeshanshaikh0012@gmail.com','Zeeshan Shaikh','IT/Software','Zeeshan Shaikh',9500330907,'Technology Traning',9500330907,1),
 (1120,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','atulyasinghal101@gmail.com','Atulya Singhal','IT/Software','Atulya Singhal',9551874073,'Technology Traning',9551874073,1),
 (1121,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','abhilashadey_ab@srmuniv.edu.in','Abhilasha Dey','IT/Software','Abhilasha Dey',9884210132,'Technology Traning',9884210132,1),
 (1122,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','akashpal_a@srmuniv.edu.in','Akash Pal','IT/Software','Akash Pal',9884203632,'Technology Traning',9884203632,1),
 (1123,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','willchill05@gmail.com','Bharat Advani','IT/Software','Bharat Advani',9884201890,'Technology Traning',9884201890,1),
 (1124,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','shivanisingh0390@gmail.com','Shivani Shivani','IT/Software','Shivani Shivani',7358335583,'Technology Traning',7358335583,1),
 (1125,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','gautam_a@srmuniv.edu.in','Vishwajit Gautam','IT/Software','Vishwajit Gautam',7397423103,'Technology Traning',7397423103,1),
 (1126,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','suyashsingh_o@srmuniv.edu.in','Suyash Singh','IT/Software','Suyash Singh',9884214032,'Technology Traning',9884214032,1),
 (1127,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','tarkeshdewangan@gmail.com','Tarkesh Dewangan','IT/Software','Tarkesh Dewangan',8349498040,'Technology Traning',8349498040,1),
 (1128,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','bmotwani2296@gmail.com','Bharat Motwani','IT/Software','Bharat Motwani',9962256576,'Technology Traning',9962256576,1),
 (1129,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','mathiusz@outlook.com','Mathews Kunnil Mathew','IT/Software','Mathews Kunnil Mathew',7358186386,'Technology Traning',7358186386,1),
 (1130,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','mayank.mohit1122@gmail.com','Mayank Mohit','IT/Software','Mayank Mohit',8527150756,'Technology Traning',8527150756,1);
INSERT INTO `customer` (`customer_Id`,`createdBy`,`createdTime`,`isDelete`,`modifiedBy`,`modifiedTime`,`sending_status`,`version`,`address`,`customer_type`,`dateOf_birth`,`email_id`,`first_name`,`industry`,`last_name`,`mobile_number`,`specialization`,`whatsapp_number`,`company_id`) VALUES 
 (1131,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','bhuvan.bankai@gmail.com','Bhuvanesh Raghuraman','IT/Software','Bhuvanesh Raghuraman',9445861278,'Technology Traning',9445861278,1),
 (1132,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','sivakumar291197@gmail.com','Siva Kumar','IT/Software','Siva Kumar',7358627763,'Technology Traning',7358627763,1),
 (1133,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','surbrucee@gmail.com','Surenderan A','IT/Software','Surenderan A',7845720429,'Technology Traning',7845720429,1),
 (1134,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','smjayasurya1997@gmail.com','Sm Jayasurya','IT/Software','Sm Jayasurya',8015389917,'Technology Traning',8015389917,1),
 (1135,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','chitra9716@gmail.com','Chitra Ravikumar','IT/Software','Chitra Ravikumar',9597404292,'Technology Traning',9597404292,1),
 (1136,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','kanthsurya62@gmail.com','Surya Kanth','IT/Software','Surya Kanth',9629156196,'Technology Traning',9629156196,1),
 (1137,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','umayalveerappan97@gmail.com','Umayal Veerappan','IT/Software','Umayal Veerappan',8072796155,'Technology Traning',8072796155,1),
 (1138,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','kvignesh282@gmail.com','Vignesh Vikki','IT/Software','Vignesh Vikki',9176655118,'Technology Traning',9176655118,1),
 (1139,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','kalejhya1998@gmail.com','Alekhya K','IT/Software','Alekhya K',8639155573,'Technology Traning',8639155573,1),
 (1140,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','keerthanaa.su@gmail.com','Keerthanaa S U','IT/Software','Keerthanaa S U',9445689120,'Technology Traning',9445689120,1),
 (1141,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','sathishsizzy998@gmail.com','Sathish Sivakumar','IT/Software','Sathish Sivakumar',9940630498,'Technology Traning',9940630498,1),
 (1142,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','manoprakash158@gmail.com','Mano Prakash M','IT/Software','Mano Prakash M',8610889464,'Technology Traning',8610889464,1),
 (1143,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','hemapriyanandhinee@gmail.com','S.Hema Priya Nandhinee','IT/Software','S.Hema Priya Nandhinee',8608688551,'Technology Traning',8608688551,1),
 (1144,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','mounishprience@gmail.com','Chukkapalli Mounish','IT/Software','Chukkapalli Mounish',9440377862,'Technology Traning',9440377862,1),
 (1145,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','mahe15122.it@rmkec.ac.in','Maheswari Satheesh Babu','IT/Software','Maheswari Satheesh Babu',7449066254,'Technology Traning',7449066254,1),
 (1146,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','sindhuchowdary202@gmail.com','Boddapati Sindhupriya','IT/Software','Boddapati Sindhupriya',9962335805,'Technology Traning',9962335805,1),
 (1147,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','akarshrezi@gmail.com','Akarsh Reji Koshy','IT/Software','Akarsh Reji Koshy',8839276215,'Technology Traning',8839276215,1),
 (1148,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','rohithravi3@gmail.com','R Rohith','IT/Software','R Rohith',9600195348,'Technology Traning',9600195348,1),
 (1149,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','rhemanthecool23@gmail.com','Hemanth Kumar R','IT/Software','Hemanth Kumar R',8124571623,'Technology Traning',8124571623,1),
 (1150,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','nirmal9802@gmail.com','Nirmal P','IT/Software','Nirmal P',9790921998,'Technology Traning',9790921998,1),
 (1151,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','sniranjansp@gmail.com','Niranjan S','IT/Software','Niranjan S',8754589634,'Technology Traning',8754589634,1),
 (1152,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','jernas.preethi@gmail.com','Preethi Jasmine','IT/Software','Preethi Jasmine',9677230851,'Technology Traning',9677230851,1),
 (1153,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','dharsanuraj@gmail.com','Priya S','IT/Software','Priya S',9659628821,'Technology Traning',9659628821,1),
 (1154,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','aravindraghavan1@gmail.com','Sudarsan V','IT/Software','Sudarsan V',9789994312,'Technology Traning',9789994312,1),
 (1155,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','ashok.kumar1409@gmail.com','C.Ashok Kumar','IT/Software','C.Ashok Kumar',7010946678,'Technology Traning',7010946678,1),
 (1156,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','arun.thril@gmail.com','Arun Kumar R','IT/Software','Arun Kumar R',9629553450,'Technology Traning',9629553450,1),
 (1157,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','anishnair1997ch@gmail.com','Anish S','IT/Software','Anish S',9486764934,'Technology Traning',9486764934,1),
 (1158,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','suryaph34@gmail.com','Surya Prakash','IT/Software','Surya Prakash',8122128624,'Technology Traning',8122128624,1),
 (1159,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','rishikumar.siva@gmail.com','S Rishi Kumar','IT/Software','S Rishi Kumar',7502299775,'Technology Traning',7502299775,1),
 (1160,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','mail4prakashr@gmail.com','Prakash R','IT/Software','Prakash R',9840300195,'Technology Traning',9840300195,1),
 (1161,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','venkat121097@gmail.com','Venkatesh A','IT/Software','Venkatesh A',7358728869,'Technology Traning',7358728869,1),
 (1162,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','thiruvedagamdinesh97@gmail.com','Dinesh Kumar','IT/Software','Dinesh Kumar',8608803427,'Technology Traning',8608803427,1),
 (1163,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','gayathridilli27@gmail.com','Gayathri Dillibashyam.D','IT/Software','Gayathri Dillibashyam.D',9941287709,'Technology Traning',9941287709,1),
 (1164,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','ctmrnaveen92@gmail.com','Naveen Mr','IT/Software','Naveen Mr',8939281671,'Technology Traning',8939281671,1),
 (1165,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','bharathi143veera@gmail.com','Bharathi V','IT/Software','Bharathi V',9751739370,'Technology Traning',9751739370,1),
 (1166,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','praneethachaganti@gmail.com','Praneetha Chaganti','IT/Software','Praneetha Chaganti',9962746498,'Technology Traning',9962746498,1),
 (1167,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','pranuanu7@gmail.com','M J A Pranathi','IT/Software','M J A Pranathi',8939294860,'Technology Traning',8939294860,1),
 (1168,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','suchi.c02@gmail.com','Sucithra Chandrasekaran','IT/Software','Sucithra Chandrasekaran',9445742063,'Technology Traning',9445742063,1),
 (1169,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','kothasunil2807@gmail.com','K Suneel','IT/Software','K Suneel',7358520575,'Technology Traning',7358520575,1),
 (1170,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','nandhinisakthivel55@gmail.com','Nandhini S','IT/Software','Nandhini S',8681046422,'Technology Traning',8681046422,1),
 (1171,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','krishnan9543@gmail.com','Ramakrishnan N','IT/Software','Ramakrishnan N',8778951194,'Technology Traning',8778951194,1),
 (1172,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','harshahardy1997@gmail.com','Baddipudi Harshavardhan','IT/Software','Baddipudi Harshavardhan',7708771914,'Technology Traning',7708771914,1),
 (1173,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','nnishanth007@gmail.com','Nishanth N','IT/Software','Nishanth N',9789929993,'Technology Traning',9789929993,1),
 (1174,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','nithesh2712@gmail.com','Kg Nithesh','IT/Software','Kg Nithesh',9710506070,'Technology Traning',9710506070,1),
 (1175,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','priyanka.nalisetty@gmail.com','Nalisetty Lakshmi Priyanka','IT/Software','Nalisetty Lakshmi Priyanka',9492784656,'Technology Traning',9492784656,1),
 (1176,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','keshavkirubakaran@gmail.com','Keshav K','IT/Software','Keshav K',9884354101,'Technology Traning',9884354101,1),
 (1177,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','kothasumanreddy888@gmail.com','Kotha Suman Reddy','IT/Software','Kotha Suman Reddy',7013306051,'Technology Traning',7013306051,1),
 (1178,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','pavanreddyyeddala@gmail.com','Pavan Reddy','IT/Software','Pavan Reddy',9490430695,'Technology Traning',9490430695,1),
 (1179,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','dhilipkumar750@gmail.com','S.Dhilip Kumar','IT/Software','S.Dhilip Kumar',7418964929,'Technology Traning',7418964929,1),
 (1180,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','nikithasankarapu249@gmail.com','Nikhitha Sankarapu','IT/Software','Nikhitha Sankarapu',8309775385,'Technology Traning',8309775385,1),
 (1181,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','sekarpcp@gmail.com','Rajasekar P','IT/Software','Rajasekar P',8883583450,'Technology Traning',8883583450,1),
 (1182,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','korrapatisaikrishna@gmail.com','K Sai Krishna','IT/Software','K Sai Krishna',9440798639,'Technology Traning',9440798639,1),
 (1183,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','saisurya145@gmail.com','Sai Surya','IT/Software','Sai Surya',7013100656,'Technology Traning',7013100656,1),
 (1184,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','ekarthikeyan95@gmail.com','Lakshmi Raman','IT/Software','Lakshmi Raman',9487994509,'Technology Traning',9487994509,1),
 (1185,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','pranauvg@gmail.com','Pranauv Guru','IT/Software','Pranauv Guru',9790317017,'Technology Traning',9790317017,1),
 (1186,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','nikhilreddykoduru@gmail.com','Koduru Nikhil Reddy','IT/Software','Koduru Nikhil Reddy',7673982126,'Technology Traning',7673982126,1),
 (1187,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','vicky11.vgcm@gmail.com','Vignesh B','IT/Software','Vignesh B',8122325985,'Technology Traning',8122325985,1),
 (1188,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','vishaalsuriya@gmail.com','Vishaal S','IT/Software','Vishaal S',7708057563,'Technology Traning',7708057563,1),
 (1189,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','ajitmani1997@gmail.com','M.Ajit Kumar','IT/Software','M.Ajit Kumar',9789297653,'Technology Traning',9789297653,1),
 (1190,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','aravindraj569@gmail.com','Aravindraj A','IT/Software','Aravindraj A',9677965037,'Technology Traning',9677965037,1),
 (1191,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','sidharth935@gmail.com','Shreyanth S','IT/Software','Shreyanth S',8610220237,'Technology Traning',8610220237,1),
 (1192,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','jbhanuteja1998@gmail.com','Jairam Bhanu Teja','IT/Software','Jairam Bhanu Teja',7382994154,'Technology Traning',7382994154,1),
 (1193,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','priyadhanancheyan@gmail.com','Priya .D','IT/Software','Priya .D',7397005131,'Technology Traning',7397005131,1),
 (1194,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','seknandhini@gmail.com','Nandhini Sekar','IT/Software','Nandhini Sekar',8248571757,'Technology Traning',8248571757,1),
 (1195,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','afrin1497@gmail.com','Aamina Afrin','IT/Software','Aamina Afrin',9940181231,'Technology Traning',9940181231,1),
 (1196,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','dkarishma681@gmail.com','Karishma Das','IT/Software','Karishma Das',9573058014,'Technology Traning',9573058014,1),
 (1197,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','akshaya973g@gmail.com','Akshaya Balaji','IT/Software','Akshaya Balaji',8072751782,'Technology Traning',8072751782,1),
 (1198,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','mokshagnasaiteja517@gmail.com','Komatipolu Mokshagna Sai Teja','IT/Software','Komatipolu Mokshagna Sai Teja',9941989881,'Technology Traning',9941989881,1),
 (1199,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','almasadeeba474@gmail.com','Almas Banu M.I','IT/Software','Almas Banu M.I',9500389047,'Technology Traning',9500389047,1),
 (1200,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','sweswetha7669@gmail.com','Swetha M R','IT/Software','Swetha M R',9514402034,'Technology Traning',9514402034,1),
 (1201,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','manikanta2201@gmail.com','Sangadi Manikanta','IT/Software','Sangadi Manikanta',8074493602,'Technology Traning',8074493602,1),
 (1202,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','anuhana02@gmail.com','Mohana Purushoth','IT/Software','Mohana Purushoth',7358333140,'Technology Traning',7358333140,1),
 (1203,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','sandhiyasandi4@gmail.com','Sandhiya Jagadeesan','IT/Software','Sandhiya Jagadeesan',8807864765,'Technology Traning',8807864765,1),
 (1204,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','chithracse2015@gmail.com','Chithra Devi','IT/Software','Chithra Devi',9551557893,'Technology Traning',9551557893,1),
 (1205,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','gayathrikothandan97@gmail.com','Gayathri .Tk.','IT/Software','Gayathri .Tk.',9092139047,'Technology Traning',9092139047,1),
 (1206,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','mohana220397@gmail.com','Mohanapriya .T','IT/Software','Mohanapriya .T',9514145456,'Technology Traning',9514145456,1),
 (1207,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','kavitharamamoorthy97@gmail.com','Kavitha R','IT/Software','Kavitha R',8939524265,'Technology Traning',8939524265,1),
 (1208,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','kirthigasekar98@gmail.com','Kirthiga S','IT/Software','Kirthiga S',7358172154,'Technology Traning',7358172154,1),
 (1209,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','lgeethanjali97@gmail.com','Geethanjali Loganathan','IT/Software','Geethanjali Loganathan',9597405774,'Technology Traning',9597405774,1),
 (1210,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','mpavithra1898@gmail.com','Pavithra Muthumanickam','IT/Software','Pavithra Muthumanickam',9445936240,'Technology Traning',9445936240,1),
 (1211,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','sijithprithiv2@gmail.com','Sijith. K. S','IT/Software','Sijith. K. S',8056116624,'Technology Traning',8056116624,1),
 (1212,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','swethams1102@gmail.com','Monisha raj','IT/Software','Monisha raj',9445353033,'Technology Traning',9445353033,1),
 (1213,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','r.b.mouni20@gmail.com','R B Mounika','IT/Software','R B Mounika',9003546383,'Technology Traning',9003546383,1),
 (1214,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','chrisgokul7@gmail.com','Gokulan M','IT/Software','Gokulan M',9952404499,'Technology Traning',9952404499,1),
 (1215,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','georgesampras12@gmail.com','George Sampras','IT/Software','George Sampras',9962393594,'Technology Traning',9962393594,1),
 (1216,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','praveenmanikandan007@gmail.com','Manikandan S','IT/Software','Manikandan S',9944992506,'Technology Traning',9944992506,1),
 (1217,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','monidevi2019@gmail.com','N.Monica Devi','IT/Software','N.Monica Devi',9940091733,'Technology Traning',9940091733,1),
 (1218,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','lakshmidhana785@gmail.com','Dhanalakshmi J','IT/Software','Dhanalakshmi J',7867986178,'Technology Traning',7867986178,1),
 (1219,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','marksingh.p97@gmail.com','Mark Sundersingh','IT/Software','Mark Sundersingh',8508438575,'Technology Traning',8508438575,1),
 (1220,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','thirumalaibabu98@gmail.com','Thirumalai B','IT/Software','Thirumalai B',9444622989,'Technology Traning',9444622989,1),
 (1221,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','ranjithabhuvi@gmail.com','Buvaneswari S.R','IT/Software','Buvaneswari S.R',9894461117,'Technology Traning',9894461117,1),
 (1222,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','hemavathivel@gmail.com','Hemavathi V','IT/Software','Hemavathi V',7339057602,'Technology Traning',7339057602,1),
 (1223,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','bhoomraghu@gmail.com','Bhuvanesh Ragupathy','IT/Software','Bhuvanesh Ragupathy',8681045052,'Technology Traning',8681045052,1),
 (1224,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','aasthasingh_aja@srmuniv.edu.in','Takhellambam Sobhachandra Singh','IT/Software','Takhellambam Sobhachandra Singh',7358419338,'Technology Traning',7358419338,1),
 (1225,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','vineethavini1998@gmail.com','V.Vineetha Venkadesh','IT/Software','V.Vineetha Venkadesh',8754641364,'Technology Traning',8754641364,1),
 (1226,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','dvignesh12345@gmail.com','Vignesh D','IT/Software','Vignesh D',9094340096,'Technology Traning',9094340096,1),
 (1227,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','charumitra.ravi@gmail.com','Charumitra Ravi','IT/Software','Charumitra Ravi',9840704398,'Technology Traning',9840704398,1),
 (1228,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','nithyaanand905@gmail.com','Nithya Anand','IT/Software','Nithya Anand',9092030282,'Technology Traning',9092030282,1),
 (1229,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','poornieee98@gmail.com','Poornima.G','IT/Software','Poornima.G',8508720739,'Technology Traning',8508720739,1),
 (1230,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','sathishbalu303@gmail.com','Sathish B','IT/Software','Sathish B',7397305688,'Technology Traning',7397305688,1),
 (1231,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','thanigailee150897@gmail.com','Thanigai Prabhu Raj D','IT/Software','Thanigai Prabhu Raj D',9551527575,'Technology Traning',9551527575,1),
 (1232,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','sachinasghostrider@gmail.com','Sachin S','IT/Software','Sachin S',8680050999,'Technology Traning',8680050999,1),
 (1233,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','sharonroy97@gmail.com','Sharon Roy F','IT/Software','Sharon Roy F',7708837882,'Technology Traning',7708837882,1),
 (1234,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','thilak.gr1706@gmail.com','Thilak R','IT/Software','Thilak R',7358092884,'Technology Traning',7358092884,1),
 (1235,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','mkavya1998@gmail.com','Kavya M','IT/Software','Kavya M',8870008625,'Technology Traning',8870008625,1),
 (1236,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','saminbasekar@gmail.com','Sam Inbasekar','IT/Software','Sam Inbasekar',9791102010,'Technology Traning',9791102010,1),
 (1237,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','kanmanimalar291@gmail.com','Kanmani M','IT/Software','Kanmani M',8056106149,'Technology Traning',8056106149,1),
 (1238,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','divyarj33@gmail.com','Divya Rajamani','IT/Software','Divya Rajamani',9994147509,'Technology Traning',9994147509,1),
 (1239,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','123angeljohn123@gmail.com','G - Eazy','IT/Software','G - Eazy',9445960085,'Technology Traning',9445960085,1),
 (1240,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','krishan_bhardwaj@hotmail.com','Krishan Bhardwaj','IT/Software','Krishan Bhardwaj',7259431666,'Technology Traning',7259431666,1),
 (1241,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','priyalakshg22@gmail.com','Lakshmi Priya.G','IT/Software','Lakshmi Priya.G',8940024555,'Technology Traning',8940024555,1),
 (1242,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','hariprasathhp756@gmail.com','Hari Prasath','IT/Software','Hari Prasath',8973677240,'Technology Traning',8973677240,1),
 (1243,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','farhanaofficial97@gmail.com','Mohiddin Farhana','IT/Software','Mohiddin Farhana',7299342818,'Technology Traning',7299342818,1),
 (1244,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','gunasekaranpavithraa@gmail.com','Pavithraa G','IT/Software','Pavithraa G',9941555655,'Technology Traning',9941555655,1),
 (1245,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','adiashok12@gmail.com','Thalamudupula Ashok Kumar','IT/Software','Thalamudupula Ashok Kumar',9494948956,'Technology Traning',9494948956,1),
 (1246,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','aishuananya@gmail.com','Lakshmi Priya','IT/Software','Lakshmi Priya',9789837750,'Technology Traning',9789837750,1),
 (1247,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','cchowdary222@gmail.com','Chaitanya Chowdary','IT/Software','Chaitanya Chowdary',7418203173,'Technology Traning',7418203173,1),
 (1248,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','surya.gk96@gmail.com','Surya G','IT/Software','Surya G',9941555338,'Technology Traning',9941555338,1),
 (1249,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','bemohan97@gmail.com','Mohan Raj','IT/Software','Mohan Raj',9597618551,'Technology Traning',9597618551,1),
 (1250,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','hemanths8991@gmail.com','Hemanth S','IT/Software','Hemanth S',9677268362,'Technology Traning',9677268362,1),
 (1251,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','skowsy2@gmail.com','Kowsalya Sudhakar','IT/Software','Kowsalya Sudhakar',7871906828,'Technology Traning',7871906828,1),
 (1252,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','samrtsanthoshkumar13579@gmail.com','Santhosh Kumar','IT/Software','Santhosh Kumar',7358438870,'Technology Traning',7358438870,1),
 (1253,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','mynameisbaju@gmail.com','Sai Balaji','IT/Software','Sai Balaji',7373236860,'Technology Traning',7373236860,1),
 (1254,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','sarasaranyaraj@gmail.com','Saranya Rangaraj','IT/Software','Saranya Rangaraj',9003694995,'Technology Traning',9003694995,1),
 (1255,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','keertheswaran1998@gmail.com','Keertheawaran Thevarajan','IT/Software','Keertheawaran Thevarajan',8015307445,'Technology Traning',8015307445,1),
 (1256,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','coffeewithast@gmail.com','Theja Chow','IT/Software','Theja Chow',7337417203,'Technology Traning',7337417203,1),
 (1257,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','aravindhraju73134@gmail.com','Aravindhraju Kamavarapu','IT/Software','Aravindhraju Kamavarapu',9492661899,'Technology Traning',9492661899,1),
 (1258,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','venkataphaneendrasadhu@gmail.com','Venkata Phaneendra','IT/Software','Venkata Phaneendra',7871260384,'Technology Traning',7871260384,1),
 (1259,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','sharmilavishy@gmail.com','Sharmila V','IT/Software','Sharmila V',9629649331,'Technology Traning',9629649331,1),
 (1260,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','abhayromila@yahoo.com','Abhay Goyal','IT/Software','Abhay Goyal',9841877388,'Technology Traning',9841877388,1),
 (1261,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','jayashrisoundararajan@gmail.com','Jayashri S','IT/Software','Jayashri S',9952949196,'Technology Traning',9952949196,1),
 (1262,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','rafiudeen92@gmail.com','Rafiudeen Ziyaudeen','IT/Software','Rafiudeen Ziyaudeen',9944415892,'Technology Traning',9944415892,1),
 (1263,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','vedula_anusha@yahoo.com','Anusha Vedula','IT/Software','Anusha Vedula',9791194927,'Technology Traning',9791194927,1),
 (1264,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','akhilbusetty@gmail.com','Busetty.Akhil Sai Datta','IT/Software','Busetty.Akhil Sai Datta',7207777136,'Technology Traning',7207777136,1),
 (1265,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','sanjeevpathak078@gmail.com','Sanjeev Pathak','IT/Software','Sanjeev Pathak',9952096732,'Technology Traning',9952096732,1),
 (1266,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','vinkumar.k97@gmail.com','Vin Kumar','IT/Software','Vin Kumar',9884117271,'Technology Traning',9884117271,1),
 (1267,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','maddurichandra04@gmail.com','Chandra Madduri','IT/Software','Chandra Madduri',9642886967,'Technology Traning',9642886967,1),
 (1268,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','poovizhiselvamss@gmail.com','Poovizhi Selvam','IT/Software','Poovizhi Selvam',7200142397,'Technology Traning',7200142397,1),
 (1269,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','keyblaster1993@gmail.com','Mayank Kumar Gupta','IT/Software','Mayank Kumar Gupta',8954075178,'Technology Traning',8954075178,1),
 (1270,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','lakshminarayanan111297@gmail.com','Lakshmi Narayanan','IT/Software','Lakshmi Narayanan',8754400693,'Technology Traning',8754400693,1),
 (1271,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','misdaque.azdee960@gmail.com','Misdaque Azdee','IT/Software','Misdaque Azdee',7904352738,'Technology Traning',7904352738,1),
 (1272,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','hemukavigayu@gmail.com','Kavitha. K','IT/Software','Kavitha. K',7010355004,'Technology Traning',7010355004,1),
 (1273,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','simransatish21@gmail.com','Simran Satish','IT/Software','Simran Satish',9344401020,'Technology Traning',9344401020,1),
 (1274,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','sornakumari2398@gmail.com','Sornakumari M','IT/Software','Sornakumari M',8098722269,'Technology Traning',8098722269,1),
 (1275,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','sanchitatae@gmail.com','Sanchita Pal','IT/Software','Sanchita Pal',7838641494,'Technology Traning',7838641494,1),
 (1276,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','sarantejaksp@gmail.com','Kanda Saran Teja','IT/Software','Kanda Saran Teja',9133736926,'Technology Traning',9133736926,1),
 (1277,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','anandapriyawin@gmail.com','Ananda Priya T.R','IT/Software','Ananda Priya T.R',8072553940,'Technology Traning',8072553940,1),
 (1278,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','lavsiyppn@gmail.com','Lavanya Iyappan','IT/Software','Lavanya Iyappan',8072426502,'Technology Traning',8072426502,1),
 (1279,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','geekyboys247@gmail.com','Rachit Sharma','IT/Software','Rachit Sharma',9566193216,'Technology Traning',9566193216,1),
 (1280,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','gopinathchennai1997@gmail.com','Gopinath Dilli','IT/Software','Gopinath Dilli',8608080234,'Technology Traning',8608080234,1),
 (1281,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','pavansai4456@gmail.com','Vemula Pavansai','IT/Software','Vemula Pavansai',9566188641,'Technology Traning',9566188641,1),
 (1282,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','amansaxena35@yahoo.com','Aman Saxena','IT/Software','Aman Saxena',7217301514,'Technology Traning',7217301514,1),
 (1283,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','mahaveerdoss10@gmail.com','Mahaveerdoss Balajidoss','IT/Software','Mahaveerdoss Balajidoss',8940630093,'Technology Traning',8940630093,1),
 (1284,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','shervin96@gmail.com','Shervin Jesuraj','IT/Software','Shervin Jesuraj',9551698259,'Technology Traning',9551698259,1),
 (1285,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','arthis2403@gmail.com','Arthi Sunderraj','IT/Software','Arthi Sunderraj',7200403175,'Technology Traning',7200403175,1),
 (1286,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','teja.naidu751@gmail.com','Teja Lakkadasu','IT/Software','Teja Lakkadasu',9177875751,'Technology Traning',9177875751,1),
 (1287,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','arthysahana@gmail.com','Aarthy P C','IT/Software','Aarthy P C',7708508464,'Technology Traning',7708508464,1),
 (1288,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','sridevishankkar@gmail.com','Sridevi Shankar','IT/Software','Sridevi Shankar',9791029083,'Technology Traning',9791029083,1),
 (1289,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','anandhakumar.1503010@srec.ac.in','Anandha Kumar.R','IT/Software','Anandha Kumar.R',7010383582,'Technology Traning',7010383582,1),
 (1290,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','madhanraj3571@gmail.com','P.Madhan Raj','IT/Software','P.Madhan Raj',9841575120,'Technology Traning',9841575120,1),
 (1291,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','kishoremaddy6@gmail.com','Kishore N','IT/Software','Kishore N',9159168023,'Technology Traning',9159168023,1),
 (1292,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','santhosh8857@gmail.com','Santhosh T','IT/Software','Santhosh T',8667802699,'Technology Traning',8667802699,1),
 (1293,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','romyabhinav.love@gmail.com','Abhinav Kumar','IT/Software','Abhinav Kumar',9760335212,'Technology Traning',9760335212,1),
 (1294,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','gladiusgladix24@gmail.com','M.Aro Benila Gladies','IT/Software','M.Aro Benila Gladies',8778707419,'Technology Traning',8778707419,1),
 (1295,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','umatram@gmail.com','Uma Riya','IT/Software','Uma Riya',8939530508,'Technology Traning',8939530508,1),
 (1296,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','venku4555@gmail.com','Ummareddy Venkateswarareddy','IT/Software','Ummareddy Venkateswarareddy',7550197180,'Technology Traning',7550197180,1),
 (1297,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','mohanak49@gmail.com','Adhinarayana Swamy','IT/Software','Adhinarayana Swamy',9052373452,'Technology Traning',9052373452,1),
 (1298,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','saravanansmart13@gmail.com','Saravanan R','IT/Software','Saravanan R',9884177424,'Technology Traning',9884177424,1),
 (1299,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','abhidet313@outlook.com','Abhishek Pandey','IT/Software','Abhishek Pandey',7355326886,'Technology Traning',7355326886,1),
 (1300,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','ksaurav325@gmail.com','Saurav Kumar','IT/Software','Saurav Kumar',8210449452,'Technology Traning',8210449452,1),
 (1301,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','hoor.bushra420@gmail.com','Bushra H','IT/Software','Bushra H',9566008416,'Technology Traning',9566008416,1),
 (1302,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','parth.manaktala@gmail.com','Parth M','IT/Software','Parth M',8939620170,'Technology Traning',8939620170,1),
 (1303,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','goelvipul44@gmail.com','Vipul Goyal','IT/Software','Vipul Goyal',8439535323,'Technology Traning',8439535323,1),
 (1304,0,'2019-11-28 10:16:31',0,0,'2019-11-28 10:16:31',1,0,'Chennai','Jobseeker','2019-11-28 10:16:31','patanshafi1998@gmail.com','Shaffi Pspk','IT/Software','Shaffi Pspk',9100167459,'Technology Traning',9100167459,1),
 (1305,0,'2020-02-14 17:18:55',0,0,NULL,1,0,'No-356,3/923, Opposite to Siddharth Upscale, Near TNSC Bank, Bhel Nagar, Porur Kundrathur Main Road, Madhanandapuram, Porur, Chennai, Tamil Nadu 600116','Student','2020-02-14 00:00:00','rparun@gmail.com','Arunkumar','IT/Software','Pernanthan',7418971140,'New Student',7418971140,1),
 (1306,0,'2020-02-14 17:20:04',0,0,NULL,1,0,'No-356,3/923, Opposite to Siddharth Upscale, Near TNSC Bank, Bhel Nagar, Porur Kundrathur Main Road, Madhanandapuram, Porur, Chennai, Tamil Nadu 600116','Student','2020-02-14 00:00:00','karthi.scube@gmail.com','Karthikeyan','IT/Software','karthi',9043328416,'New Student',9600813394,1);
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;


--
-- Definition of table `event`
--

DROP TABLE IF EXISTS `event`;
CREATE TABLE `event` (
  `event_Id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createdBy` bigint(20) DEFAULT NULL,
  `createdTime` datetime DEFAULT NULL,
  `isDelete` tinyint(1) DEFAULT NULL,
  `modifiedBy` bigint(20) DEFAULT NULL,
  `modifiedTime` datetime DEFAULT NULL,
  `sending_status` tinyint(1) DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `address` varchar(255) NOT NULL,
  `Date` datetime NOT NULL,
  `imageName` varchar(255) NOT NULL,
  `timing` varchar(255) NOT NULL,
  `titleName` varchar(255) NOT NULL,
  `company_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`event_Id`),
  KEY `FK_gpf8dt5coprxfjt3ivpoqwvd4` (`company_id`),
  CONSTRAINT `FK_gpf8dt5coprxfjt3ivpoqwvd4` FOREIGN KEY (`company_id`) REFERENCES `company` (`companyId`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `event`
--

/*!40000 ALTER TABLE `event` DISABLE KEYS */;
INSERT INTO `event` (`event_Id`,`createdBy`,`createdTime`,`isDelete`,`modifiedBy`,`modifiedTime`,`sending_status`,`version`,`address`,`Date`,`imageName`,`timing`,`titleName`,`company_id`) VALUES 
 (1,0,'2020-02-22 12:15:07',0,0,NULL,1,0,'No. 3/1418, Annai Velakanai Nagar , Porur, Chennai - 600125 Landmark: NEAR  Southern Skies Tours & Travels','2020-03-02 00:00:00','1.jpeg','10am to 6pm','CoreJava',1),
 (2,0,'2020-02-22 12:17:56',0,0,NULL,1,0,'3/1419 Ground Floor, 5th Cross Street, Annai Velanganni Nagar Phase-I, Madhanandhapuram, Chennai - 600125','2020-03-05 00:00:00','2.jpeg','10am to 6pm','Servlet and JavaServerPage',1),
 (3,0,'2020-02-22 12:20:33',0,0,NULL,1,0,'3/1419 Ground Floor, 5th Cross Street, Annai Velanganni Nagar Phase-I, Madhanandhapuram, Chennai - 600125','2020-03-10 00:00:00','3.png','10am to 6pm','Spring and Hibernate',1),
 (4,0,'2020-02-22 12:23:42',0,0,NULL,1,0,'No. 3/1418, Annai Velakanai Nagar , Porur, Chennai - 600125 Landmark: NEAR  Southern Skies Tours & Travels','2020-03-20 00:00:00','4.jpeg','10am to 6pm','Spring Boot and Angular',1);
/*!40000 ALTER TABLE `event` ENABLE KEYS */;


--
-- Definition of table `event_register`
--

DROP TABLE IF EXISTS `event_register`;
CREATE TABLE `event_register` (
  `eventreg_id` int(11) NOT NULL AUTO_INCREMENT,
  `createdBy` bigint(20) DEFAULT NULL,
  `createdTime` datetime DEFAULT NULL,
  `isDelete` tinyint(1) DEFAULT NULL,
  `modifiedBy` bigint(20) DEFAULT NULL,
  `modifiedTime` datetime DEFAULT NULL,
  `sending_status` tinyint(1) DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `candidatename` varchar(45) NOT NULL,
  `emailaddress` varchar(255) NOT NULL,
  `mobileNumber` bigint(20) NOT NULL,
  `professional` varchar(255) NOT NULL,
  `qualification` varchar(255) NOT NULL,
  `company_id` int(11) DEFAULT NULL,
  `event_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`eventreg_id`),
  KEY `FK_bghhkjdw0xanxs3dcr7uyuhdd` (`company_id`),
  KEY `FK_10nk4scx8a4ct85qmvo79g0ku` (`event_id`),
  CONSTRAINT `FK_10nk4scx8a4ct85qmvo79g0ku` FOREIGN KEY (`event_id`) REFERENCES `event` (`event_Id`),
  CONSTRAINT `FK_bghhkjdw0xanxs3dcr7uyuhdd` FOREIGN KEY (`company_id`) REFERENCES `company` (`companyId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `event_register`
--

/*!40000 ALTER TABLE `event_register` DISABLE KEYS */;
/*!40000 ALTER TABLE `event_register` ENABLE KEYS */;


--
-- Definition of table `login`
--

DROP TABLE IF EXISTS `login`;
CREATE TABLE `login` (
  `login_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createdBy` bigint(20) DEFAULT NULL,
  `createdTime` datetime DEFAULT NULL,
  `isDelete` tinyint(1) DEFAULT NULL,
  `modifiedBy` bigint(20) DEFAULT NULL,
  `modifiedTime` datetime DEFAULT NULL,
  `sending_status` tinyint(1) DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `email_address` varchar(50) NOT NULL,
  `is_active` tinyint(4) NOT NULL,
  `password` varchar(255) NOT NULL,
  `user_type` varchar(255) NOT NULL,
  `company_id` int(11) DEFAULT NULL,
  `userRole_Id` int(11) DEFAULT NULL,
  PRIMARY KEY (`login_id`),
  KEY `FK_2ph7hvknu6lc59ag2cmyeu7ai` (`company_id`),
  KEY `FK_b5p8hmaweriff99d45qom7a1` (`userRole_Id`),
  CONSTRAINT `FK_2ph7hvknu6lc59ag2cmyeu7ai` FOREIGN KEY (`company_id`) REFERENCES `company` (`companyId`),
  CONSTRAINT `FK_b5p8hmaweriff99d45qom7a1` FOREIGN KEY (`userRole_Id`) REFERENCES `user_role` (`user_role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `login`
--

/*!40000 ALTER TABLE `login` DISABLE KEYS */;
INSERT INTO `login` (`login_id`,`createdBy`,`createdTime`,`isDelete`,`modifiedBy`,`modifiedTime`,`sending_status`,`version`,`email_address`,`is_active`,`password`,`user_type`,`company_id`,`userRole_Id`) VALUES 
 (1,1,'2019-11-28 10:16:31',0,1,'2019-11-28 10:16:31',0,1,'admin@gmail.com',1,'AUIZw1GhGpOBkUMLIBrF5cRI8BaCRwvY+J0UdEZzyN4=','admin',NULL,1),
 (2,1,'2019-12-23 10:06:55',0,0,NULL,0,0,'enquiry@techboot.com',1,'AUIZw1GhGpOBkUMLIBrF5cRI8BaCRwvY+J0UdEZzyN4=','company',1,2),
 (3,0,'2019-12-23 13:32:08',0,0,NULL,0,0,'greentechnology@gmail.com',1,'vRX20vS5Dt1JDm/SGPm6R9GAMBJMWDBcKU6zn+7hwVE=','company',2,2),
 (4,1,'2020-02-10 15:09:15',0,0,NULL,0,0,'HCL@gmail.com',1,'AUIZw1GhGpOBkUMLIBrF5cRI8BaCRwvY+J0UdEZzyN4=','company',4,2);
/*!40000 ALTER TABLE `login` ENABLE KEYS */;


--
-- Definition of table `metatag`
--

DROP TABLE IF EXISTS `metatag`;
CREATE TABLE `metatag` (
  `metatag_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `meta_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`metatag_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `metatag`
--

/*!40000 ALTER TABLE `metatag` DISABLE KEYS */;
INSERT INTO `metatag` (`metatag_id`,`meta_name`) VALUES 
 (1,'Core Java'),
 (2,'Java'),
 (3,'Advanced Java'),
 (4,'Java Course'),
 (5,'Java servlet JSP'),
 (6,'Spring and HIbernate'),
 (7,'Spring boot  and Angular'),
 (8,' McroService and SpringBoot');
/*!40000 ALTER TABLE `metatag` ENABLE KEYS */;


--
-- Definition of table `newsletters`
--

DROP TABLE IF EXISTS `newsletters`;
CREATE TABLE `newsletters` (
  `letters_id` int(11) NOT NULL AUTO_INCREMENT,
  `created_date` datetime NOT NULL,
  `emailAddress` varchar(255) DEFAULT NULL,
  `isDelete` tinyint(1) DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  `sending_status` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`letters_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `newsletters`
--

/*!40000 ALTER TABLE `newsletters` DISABLE KEYS */;
INSERT INTO `newsletters` (`letters_id`,`created_date`,`emailAddress`,`isDelete`,`modified_date`,`sending_status`) VALUES 
 (1,'2020-02-29 16:29:52','gangadaran2895@gmail.com',0,NULL,1),
 (2,'2020-02-29 16:31:09','stalinanbu0612@gmail.com',0,NULL,1),
 (3,'2020-02-29 16:43:02','ajithkumar2345@gmail.com',0,NULL,1),
 (4,'2020-02-29 16:44:57','gangadharan2345@gmail.com',0,NULL,1),
 (5,'2020-02-29 16:46:10','praveenraj23@gmail.com',0,NULL,1),
 (6,'2020-02-29 16:47:24','jackdharan2345@gmail.com',0,NULL,1),
 (7,'2020-02-29 16:55:25','gangadharan7898@gmail.com',0,NULL,1),
 (8,'2020-02-29 19:38:22','selva.sathri@gmail.com',0,NULL,1);
/*!40000 ALTER TABLE `newsletters` ENABLE KEYS */;


--
-- Definition of table `recentnews`
--

DROP TABLE IF EXISTS `recentnews`;
CREATE TABLE `recentnews` (
  `news_id` int(11) NOT NULL AUTO_INCREMENT,
  `authorname` varchar(255) NOT NULL,
  `createdBy` bigint(20) DEFAULT NULL,
  `createdTime` datetime DEFAULT NULL,
  `date` datetime NOT NULL,
  `descriptions` varchar(255) NOT NULL,
  `imageName` varchar(255) NOT NULL,
  `isDelete` tinyint(1) NOT NULL,
  `modifiedBy` bigint(20) DEFAULT NULL,
  `modifiedTime` datetime DEFAULT NULL,
  `sending_status` tinyint(1) NOT NULL,
  `titlename` varchar(255) NOT NULL,
  PRIMARY KEY (`news_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `recentnews`
--

/*!40000 ALTER TABLE `recentnews` DISABLE KEYS */;
INSERT INTO `recentnews` (`news_id`,`authorname`,`createdBy`,`createdTime`,`date`,`descriptions`,`imageName`,`isDelete`,`modifiedBy`,`modifiedTime`,`sending_status`,`titlename`) VALUES 
 (1,'Reid Carr',1,'2020-02-14 21:03:19','2020-02-14 00:00:00','From conducting a generic Web search to face recognition technologies, from home automation systems and self-d','1.jpeg',0,1,'2020-02-14 21:45:54',1,'5 reasons how AI will change careers and working set-ups '),
 (2,'Vanita Dsouza',1,'2020-02-14 21:06:42','2020-02-14 00:00:00','Twitter Inc. said on Monday it acquired London-based Magic Pony Technology, a machine-learning startup that sp','2.jpeg',0,1,'2020-02-14 21:29:43',1,'Twitter Buys Machine-Learning Firm Magic Pony to Boost Image Content'),
 (3,'Jeff Friesen',1,'2020-02-14 21:21:11','2020-02-14 00:00:00','I remember watching the iconic movie Back to the Future as a kid, marvelling over what kinds of innovations th','3.jpeg',0,1,'2020-02-14 21:35:56',1,'Top 3 Ways Machine Learning Will Create Jobs');
/*!40000 ALTER TABLE `recentnews` ENABLE KEYS */;


--
-- Definition of table `service`
--

DROP TABLE IF EXISTS `service`;
CREATE TABLE `service` (
  `service_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createdBy` bigint(20) DEFAULT NULL,
  `createdTime` datetime DEFAULT NULL,
  `isDelete` tinyint(1) DEFAULT NULL,
  `modifiedBy` bigint(20) DEFAULT NULL,
  `modifiedTime` datetime DEFAULT NULL,
  `sending_status` tinyint(1) DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `duration` varchar(255) NOT NULL,
  `end_Date` datetime NOT NULL,
  `fees` double NOT NULL,
  `service_Name` varchar(255) NOT NULL,
  `Service_Specification` varchar(255) NOT NULL,
  `start_Date` datetime NOT NULL,
  `company_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`service_id`),
  KEY `FK_8t4ctwn1x5m81dwt2jvsec80u` (`company_id`),
  CONSTRAINT `FK_8t4ctwn1x5m81dwt2jvsec80u` FOREIGN KEY (`company_id`) REFERENCES `company` (`companyId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `service`
--

/*!40000 ALTER TABLE `service` DISABLE KEYS */;
INSERT INTO `service` (`service_id`,`createdBy`,`createdTime`,`isDelete`,`modifiedBy`,`modifiedTime`,`sending_status`,`version`,`duration`,`end_Date`,`fees`,`service_Name`,`Service_Specification`,`start_Date`,`company_id`) VALUES 
 (1,1,'2020-02-14 17:20:45',0,0,NULL,1,0,'1 Month','2020-03-03 00:00:00',1000,'Java Servlet Jsp','New Student','2020-03-02 00:00:00',1);
/*!40000 ALTER TABLE `service` ENABLE KEYS */;


--
-- Definition of table `testimonial`
--

DROP TABLE IF EXISTS `testimonial`;
CREATE TABLE `testimonial` (
  `test_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createdBy` bigint(20) NOT NULL,
  `createdTime` datetime NOT NULL,
  `imageName` varchar(255) NOT NULL,
  `isDelete` tinyint(1) NOT NULL,
  `message` text NOT NULL,
  `modifiedBy` bigint(20) DEFAULT NULL,
  `modifiedTime` datetime DEFAULT NULL,
  `Name` varchar(255) NOT NULL,
  `profession` varchar(255) NOT NULL,
  `sending_status` tinyint(1) NOT NULL,
  `Title_Name` varchar(255) NOT NULL,
  PRIMARY KEY (`test_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `testimonial`
--

/*!40000 ALTER TABLE `testimonial` DISABLE KEYS */;
INSERT INTO `testimonial` (`test_id`,`createdBy`,`createdTime`,`imageName`,`isDelete`,`message`,`modifiedBy`,`modifiedTime`,`Name`,`profession`,`sending_status`,`Title_Name`) VALUES 
 (1,1,'2020-02-22 12:34:52','1.jpeg',0,'I came have to Techboot as a fresher without any knowledge in computer science. Techboot taught me technologies like Java, JSP, Servlet, Spring, Hibernate and now i become a Java Developer in an MNC.',0,NULL,'Karthik','Developer',1,'What People Say'),
 (2,1,'2020-02-22 12:36:24','2.jpeg',0,'I am a Mechanical Engineer, I was completely hesitated in entering the computer world. But the entry in Techboot made me the pridest son for my parents. Now, I am an MNC employee.',0,NULL,'ArunKumar','Developer',1,'What People Say');
/*!40000 ALTER TABLE `testimonial` ENABLE KEYS */;


--
-- Definition of table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `user_role_id` int(11) NOT NULL AUTO_INCREMENT,
  `createdBy` bigint(20) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `isDelete` tinyint(1) DEFAULT NULL,
  `modifiedBy` bigint(20) DEFAULT NULL,
  `modified_time` datetime DEFAULT NULL,
  `user_role_name` varchar(255) NOT NULL,
  PRIMARY KEY (`user_role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user_role`
--

/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` (`user_role_id`,`createdBy`,`created_time`,`isDelete`,`modifiedBy`,`modified_time`,`user_role_name`) VALUES 
 (1,1,'2019-10-23 15:36:46',0,1,'2019-10-23 15:36:46','admin'),
 (2,1,'2019-10-23 15:36:46',0,1,'2019-10-23 15:36:46','company'),
 (3,1,'2019-10-23 15:36:46',0,1,'2019-10-23 15:36:46','customer');
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;


--
-- Definition of view `campaign_sms_view`
--

DROP TABLE IF EXISTS `campaign_sms_view`;
DROP VIEW IF EXISTS `campaign_sms_view`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `campaign_sms_view` AS select `mo`.`mobile_number` AS `mobile_number`,`mo`.`first_name` AS `first_name`,`ms`.`message` AS `message`,`ms`.`started_Time` AS `started_Time`,`ms`.`end_Time` AS `end_Time`,`se`.`service_Name` AS `service_Name`,`se`.`duration` AS `duration`,`se`.`start_Date` AS `start_Date`,`se`.`end_Date` AS `end_Date`,`se`.`fees` AS `fees`,`se`.`company_id` AS `company_id`,`c`.`companyName` AS `companyName` from (((`campaign` `ms` join `service` `se`) join `customer` `mo`) join `company` `c` on((`c`.`companyId` = `ms`.`company_id`))) where ((`ms`.`productServiceId` = `se`.`service_id`) and (`se`.`Service_Specification` = `mo`.`specialization`));



/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
