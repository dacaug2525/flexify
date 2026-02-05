CREATE DATABASE  IF NOT EXISTS `flexifydb` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `flexifydb`;
-- MySQL dump 10.13  Distrib 8.0.42, for Win64 (x86_64)
--
-- Host: localhost    Database: flexifydb
-- ------------------------------------------------------
-- Server version	8.0.42

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `feedback`
--

DROP TABLE IF EXISTS `feedback`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `feedback` (
  `feedback_id` int NOT NULL AUTO_INCREMENT,
  `mid` int NOT NULL,
  `tid` int NOT NULL,
  `comment` varchar(255) DEFAULT NULL,
  `date` datetime NOT NULL,
  `rating` int NOT NULL,
  PRIMARY KEY (`feedback_id`),
  KEY `tra_id_idx` (`tid`),
  KEY `memb_id_idx` (`mid`),
  CONSTRAINT `memb_id` FOREIGN KEY (`mid`) REFERENCES `members` (`mid`),
  CONSTRAINT `tra_id` FOREIGN KEY (`tid`) REFERENCES `trainers` (`tid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `feedback`
--

LOCK TABLES `feedback` WRITE;
/*!40000 ALTER TABLE `feedback` DISABLE KEYS */;
INSERT INTO `feedback` VALUES (1,1,1,'Trainer is very supportive','2026-02-02 01:32:19',3),(2,1,1,'best trainer ','2026-02-02 01:38:58',4);
/*!40000 ALTER TABLE `feedback` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `health_condition`
--

DROP TABLE IF EXISTS `health_condition`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `health_condition` (
  `health_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`health_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `health_condition`
--

LOCK TABLES `health_condition` WRITE;
/*!40000 ALTER TABLE `health_condition` DISABLE KEYS */;
INSERT INTO `health_condition` VALUES (1,'Diabetes'),(2,'Backpain'),(3,'Asthma'),(4,'Nothing');
/*!40000 ALTER TABLE `health_condition` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `med_info`
--

DROP TABLE IF EXISTS `med_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `med_info` (
  `med_id` int NOT NULL AUTO_INCREMENT,
  `mid` int NOT NULL,
  `health_id` int NOT NULL,
  `remark` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`med_id`),
  KEY `fk_mid_idx` (`mid`),
  KEY `fk_hid_idx` (`health_id`),
  CONSTRAINT `fk_hid` FOREIGN KEY (`health_id`) REFERENCES `health_condition` (`health_id`),
  CONSTRAINT `fk_mid` FOREIGN KEY (`mid`) REFERENCES `members` (`mid`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `med_info`
--

LOCK TABLES `med_info` WRITE;
/*!40000 ALTER TABLE `med_info` DISABLE KEYS */;
INSERT INTO `med_info` VALUES (12,2,1,'Diabetes');
/*!40000 ALTER TABLE `med_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `member_attendence`
--

DROP TABLE IF EXISTS `member_attendence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `member_attendence` (
  `attendence_id` int NOT NULL AUTO_INCREMENT,
  `mid` int NOT NULL,
  `date` datetime NOT NULL,
  `status` enum('PRESENT','ABSENT') NOT NULL,
  PRIMARY KEY (`attendence_id`),
  KEY `memid_idx` (`mid`),
  CONSTRAINT `memid` FOREIGN KEY (`mid`) REFERENCES `members` (`mid`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member_attendence`
--

LOCK TABLES `member_attendence` WRITE;
/*!40000 ALTER TABLE `member_attendence` DISABLE KEYS */;
INSERT INTO `member_attendence` VALUES (1,1,'2026-01-31 23:56:01','ABSENT'),(2,1,'2026-01-31 23:56:08','PRESENT'),(3,1,'2026-01-31 23:56:14','ABSENT'),(4,1,'2026-02-01 15:20:14','PRESENT'),(5,1,'2026-02-01 15:20:26','ABSENT'),(6,1,'2026-02-01 15:20:31','PRESENT'),(7,1,'2026-02-01 15:20:36','PRESENT'),(8,1,'2026-02-01 15:20:40','PRESENT'),(9,1,'2026-02-01 15:20:43','PRESENT'),(10,2,'2026-02-01 15:51:23','PRESENT'),(11,1,'2026-02-02 13:55:23','PRESENT');
/*!40000 ALTER TABLE `member_attendence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `member_membership`
--

DROP TABLE IF EXISTS `member_membership`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `member_membership` (
  `membership_id` int NOT NULL AUTO_INCREMENT,
  `member_id` int NOT NULL,
  `plan_id` int NOT NULL,
  `start_date` date NOT NULL,
  `end_date` date NOT NULL,
  `status` enum('ACTIVE','INACTIVE') NOT NULL,
  PRIMARY KEY (`membership_id`),
  KEY `fk_mid_idx` (`member_id`),
  KEY `fk_plan_id_idx` (`plan_id`),
  CONSTRAINT `member_id` FOREIGN KEY (`member_id`) REFERENCES `members` (`mid`),
  CONSTRAINT `mplan_id` FOREIGN KEY (`plan_id`) REFERENCES `plan` (`plan_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member_membership`
--

LOCK TABLES `member_membership` WRITE;
/*!40000 ALTER TABLE `member_membership` DISABLE KEYS */;
INSERT INTO `member_membership` VALUES (1,1,2,'2026-02-01','2026-05-01','ACTIVE');
/*!40000 ALTER TABLE `member_membership` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `member_progress`
--

DROP TABLE IF EXISTS `member_progress`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `member_progress` (
  `progress_id` int NOT NULL AUTO_INCREMENT,
  `mid` int NOT NULL,
  `weight` double NOT NULL,
  `bmi` double NOT NULL,
  `recorded_date` datetime NOT NULL,
  `remark` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`progress_id`),
  KEY `m_id_idx` (`mid`),
  CONSTRAINT `m_id` FOREIGN KEY (`mid`) REFERENCES `members` (`mid`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member_progress`
--

LOCK TABLES `member_progress` WRITE;
/*!40000 ALTER TABLE `member_progress` DISABLE KEYS */;
INSERT INTO `member_progress` VALUES (1,1,68,22.7,'2026-02-02 11:32:38',NULL),(2,1,70,23.4,'2026-02-02 13:06:41',NULL),(3,1,49,21.8,'2026-02-02 17:17:46',NULL);
/*!40000 ALTER TABLE `member_progress` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `member_trainer_assignment`
--

DROP TABLE IF EXISTS `member_trainer_assignment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `member_trainer_assignment` (
  `assignment_id` int NOT NULL AUTO_INCREMENT,
  `tid` int NOT NULL,
  `mid` int NOT NULL,
  `assign_date` datetime NOT NULL,
  PRIMARY KEY (`assignment_id`),
  KEY `fk_tid_idx` (`tid`),
  KEY `fk_mid_idx` (`mid`),
  CONSTRAINT `fk_memid` FOREIGN KEY (`mid`) REFERENCES `members` (`mid`),
  CONSTRAINT `fk_tid` FOREIGN KEY (`tid`) REFERENCES `trainers` (`tid`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member_trainer_assignment`
--

LOCK TABLES `member_trainer_assignment` WRITE;
/*!40000 ALTER TABLE `member_trainer_assignment` DISABLE KEYS */;
INSERT INTO `member_trainer_assignment` VALUES (1,1,1,'2026-01-02 00:00:00'),(2,1,2,'2026-01-02 00:00:00'),(3,2,1,'2026-01-02 00:00:00');
/*!40000 ALTER TABLE `member_trainer_assignment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `members`
--

DROP TABLE IF EXISTS `members`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `members` (
  `mid` int NOT NULL AUTO_INCREMENT,
  `dob` date NOT NULL,
  `height` int NOT NULL,
  `weight` int NOT NULL,
  `address` varchar(255) NOT NULL,
  `join_date` datetime NOT NULL,
  `status` enum('active','inactive') NOT NULL,
  `uid` int NOT NULL,
  PRIMARY KEY (`mid`),
  KEY `fk_uid_idx` (`uid`),
  CONSTRAINT `fk_uid` FOREIGN KEY (`uid`) REFERENCES `users` (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `members`
--

LOCK TABLES `members` WRITE;
/*!40000 ALTER TABLE `members` DISABLE KEYS */;
INSERT INTO `members` VALUES (1,'1999-05-12',150,49,'Pune, Maharashtra','2026-01-30 20:54:10','active',1),(2,'2000-05-12',170,68,'mumbai, Maharashtra','2026-01-30 20:58:21','active',2),(3,'2026-02-01',168,68,'Mumbai, Maharashtra','2026-02-01 18:04:33','active',3),(4,'2026-02-02',160,60,'Pune, Maharashtra','2026-02-02 09:57:18','active',6);
/*!40000 ALTER TABLE `members` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payment`
--

DROP TABLE IF EXISTS `payment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `payment` (
  `payment_id` int NOT NULL AUTO_INCREMENT,
  `mid` int NOT NULL,
  `amount` decimal(10,2) NOT NULL,
  `payment_date` datetime NOT NULL,
  `payment_method` varchar(45) NOT NULL,
  `transaction_id` varchar(10) NOT NULL,
  PRIMARY KEY (`payment_id`),
  UNIQUE KEY `transaction_id_UNIQUE` (`transaction_id`),
  KEY `mem_id_idx` (`mid`),
  CONSTRAINT `mem_id` FOREIGN KEY (`mid`) REFERENCES `members` (`mid`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment`
--

LOCK TABLES `payment` WRITE;
/*!40000 ALTER TABLE `payment` DISABLE KEYS */;
INSERT INTO `payment` VALUES (1,1,3496.50,'2026-02-01 17:08:43','CARD','1914GSS77L'),(2,1,2498.75,'2026-02-01 18:07:53','CARD','RMJ8YP7UAK'),(3,1,2498.75,'2026-02-01 18:08:03','CARD','K0JJEF4DI1'),(4,1,2498.75,'2026-02-01 18:08:23','CARD','GQYB6BVKXL');
/*!40000 ALTER TABLE `payment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `plan`
--

DROP TABLE IF EXISTS `plan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `plan` (
  `plan_id` int NOT NULL AUTO_INCREMENT,
  `plan_name` varchar(45) NOT NULL,
  `plan_duration` int NOT NULL,
  `fees` decimal(38,2) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `dis_id` int NOT NULL,
  PRIMARY KEY (`plan_id`),
  KEY `fk_disid_idx` (`dis_id`),
  CONSTRAINT `fk_disid` FOREIGN KEY (`dis_id`) REFERENCES `plan_discount` (`dis_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `plan`
--

LOCK TABLES `plan` WRITE;
/*!40000 ALTER TABLE `plan` DISABLE KEYS */;
INSERT INTO `plan` VALUES (1,'standard',1,2500.00,'basic traing , take sessin daily, give workout plan , provide trainer ',1),(2,'Gold',3,3500.00,'give all exercise , session will take daily ',2),(3,'Elite',6,5000.00,'give all workout , trainer provided , session will take , personal guidance ',3),(4,'Premium',12,10000.00,'personal training provided, amenities access including steam , saunaand locker facilities, give premium experience',4);
/*!40000 ALTER TABLE `plan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `plan_discount`
--

DROP TABLE IF EXISTS `plan_discount`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `plan_discount` (
  `dis_id` int NOT NULL AUTO_INCREMENT,
  `duration` int NOT NULL,
  `discount` decimal(38,2) NOT NULL,
  PRIMARY KEY (`dis_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `plan_discount`
--

LOCK TABLES `plan_discount` WRITE;
/*!40000 ALTER TABLE `plan_discount` DISABLE KEYS */;
INSERT INTO `plan_discount` VALUES (1,1,0.05),(2,3,0.10),(3,6,0.15),(4,12,0.25);
/*!40000 ALTER TABLE `plan_discount` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `plan_training`
--

DROP TABLE IF EXISTS `plan_training`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `plan_training` (
  `ptid` int NOT NULL AUTO_INCREMENT,
  `tr_id` int NOT NULL,
  `plan_id` int NOT NULL,
  PRIMARY KEY (`ptid`),
  KEY `trid_idx` (`tr_id`),
  KEY `planid_idx` (`plan_id`),
  CONSTRAINT `planid` FOREIGN KEY (`plan_id`) REFERENCES `plan` (`plan_id`),
  CONSTRAINT `trid` FOREIGN KEY (`tr_id`) REFERENCES `training_table` (`tr_id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `plan_training`
--

LOCK TABLES `plan_training` WRITE;
/*!40000 ALTER TABLE `plan_training` DISABLE KEYS */;
INSERT INTO `plan_training` VALUES (1,8,1),(2,1,1),(3,4,2),(4,1,2),(5,8,2),(6,8,3),(7,8,4),(8,1,4),(9,2,4),(10,3,4),(11,4,4),(12,4,3),(13,2,3),(14,3,3),(15,6,3),(16,3,1);
/*!40000 ALTER TABLE `plan_training` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
  `rid` int NOT NULL AUTO_INCREMENT,
  `rname` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`rid`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,'admin'),(2,'trainer'),(3,'member');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `trainer_specialization`
--

DROP TABLE IF EXISTS `trainer_specialization`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `trainer_specialization` (
  `training_id` int NOT NULL AUTO_INCREMENT,
  `tid` int NOT NULL,
  `tr_id` int NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`training_id`),
  KEY `trai_id_idx` (`tid`),
  KEY `tr_id_idx` (`tr_id`),
  CONSTRAINT `tr_id` FOREIGN KEY (`tr_id`) REFERENCES `training_table` (`tr_id`),
  CONSTRAINT `trai_id` FOREIGN KEY (`tid`) REFERENCES `trainers` (`tid`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trainer_specialization`
--

LOCK TABLES `trainer_specialization` WRITE;
/*!40000 ALTER TABLE `trainer_specialization` DISABLE KEYS */;
INSERT INTO `trainer_specialization` VALUES (1,1,4,'give proper traing and take session '),(2,1,1,'take yoga session for mind and flexibility'),(3,2,8,'give daily basic workout training');
/*!40000 ALTER TABLE `trainer_specialization` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `trainers`
--

DROP TABLE IF EXISTS `trainers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `trainers` (
  `tid` int NOT NULL AUTO_INCREMENT,
  `experience` int NOT NULL,
  `salary` decimal(38,2) DEFAULT NULL,
  `uid` int NOT NULL,
  PRIMARY KEY (`tid`),
  KEY `uid_idx` (`uid`),
  CONSTRAINT `uid` FOREIGN KEY (`uid`) REFERENCES `users` (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trainers`
--

LOCK TABLES `trainers` WRITE;
/*!40000 ALTER TABLE `trainers` DISABLE KEYS */;
INSERT INTO `trainers` VALUES (1,5,30045.56,4),(2,6,23099.00,5);
/*!40000 ALTER TABLE `trainers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `training_table`
--

DROP TABLE IF EXISTS `training_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `training_table` (
  `tr_id` int NOT NULL AUTO_INCREMENT,
  `tr_name` varchar(45) NOT NULL,
  `desc` varchar(255) NOT NULL,
  PRIMARY KEY (`tr_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `training_table`
--

LOCK TABLES `training_table` WRITE;
/*!40000 ALTER TABLE `training_table` DISABLE KEYS */;
INSERT INTO `training_table` VALUES (1,'Yoga','Focused on improving flexibility and mental mindfulness through breadth-controlled movement.'),(2,'Muscle Building','Designed for hypertrophy and raw power using advanced weightlifting and progressive overload.'),(3,'Cardio','Used for Strengthening the heart and increasing lung capacity through high stamina aerobic routine.'),(4,'Primal Functional Training','Used to improve balance and coordination by mimicking daily real world movement.'),(5,'Core Scuplt & Pilates','Build a rock-solid midsection, improving posture and preventing back pain.'),(6,'Elite Powerlifting ','Build maximum strenght by big lifts - squat, bench, deadlift'),(7,'Low Impact Aerobics','focused on improving heart health without putting heavy stress on knee or ankles.'),(8,'Core Basics','used to activate and strengthen the abdominal muscle to support better postures and safety.');
/*!40000 ALTER TABLE `training_table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `uid` int NOT NULL AUTO_INCREMENT,
  `uname` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `fname` varchar(255) DEFAULT NULL,
  `lname` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `contact` varchar(255) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `rid` int NOT NULL,
  PRIMARY KEY (`uid`),
  UNIQUE KEY `uname_UNIQUE` (`uname`),
  KEY `rid_idx` (`rid`),
  CONSTRAINT `rid` FOREIGN KEY (`rid`) REFERENCES `roles` (`rid`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'nidhi','1234','Nidhi','Pokale','nidhi@gmail.com','1234567879','Female',3),(2,'gayu','1234','Gayatri','Sonawane','gayu@gmail.com','8965468383','Female',3),(3,'mohan','1234','Mohan','Sharma','mohan@gmail.com','9987766263','Male',3),(4,'john','1234','John','Doe','john@gmail.com','3576986333','Male',2),(5,'sudha','1234','Sudha','Kapoor','sudha@gmail.com','4764387632','Female',2),(6,'bhumika','1234','Bhumika','Mahale','bhumika@gmail.com','2374874548','Female',3);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `workout_schedule`
--

DROP TABLE IF EXISTS `workout_schedule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `workout_schedule` (
  `workout_id` int NOT NULL AUTO_INCREMENT,
  `trainer_id` int NOT NULL,
  `member_id` int NOT NULL,
  `workout_desc` varchar(255) DEFAULT NULL,
  `days` int NOT NULL,
  PRIMARY KEY (`workout_id`),
  KEY `trainer_id_idx` (`trainer_id`),
  KEY `member_id_idx` (`member_id`),
  CONSTRAINT `memberid` FOREIGN KEY (`member_id`) REFERENCES `members` (`mid`),
  CONSTRAINT `trainerid` FOREIGN KEY (`trainer_id`) REFERENCES `trainers` (`tid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `workout_schedule`
--

LOCK TABLES `workout_schedule` WRITE;
/*!40000 ALTER TABLE `workout_schedule` DISABLE KEYS */;
INSERT INTO `workout_schedule` VALUES (1,1,1,'do squat , yoga , suryanamskar 30 , push up 30*2 set',2);
/*!40000 ALTER TABLE `workout_schedule` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-02-02 20:59:18
