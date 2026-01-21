-- MySQL dump 10.13  Distrib 8.0.42, for Win64 (x86_64)
--
-- Host: localhost    Database: flexifydb
-- ------------------------------------------------------
-- Server version	8.0.42
create database flexifydb;
use flexifydb;
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
  `feedback_id` int NOT NULL,
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `feedback`
--

LOCK TABLES `feedback` WRITE;
/*!40000 ALTER TABLE `feedback` DISABLE KEYS */;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `health_condition`
--

LOCK TABLES `health_condition` WRITE;
/*!40000 ALTER TABLE `health_condition` DISABLE KEYS */;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `med_info`
--

LOCK TABLES `med_info` WRITE;
/*!40000 ALTER TABLE `med_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `med_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `member_attendence`
--

DROP TABLE IF EXISTS `member_attendence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `member_attendence` (
  `attendence_id` int NOT NULL,
  `mid` int NOT NULL,
  `date` datetime NOT NULL,
  `status` enum('present','absent') NOT NULL,
  PRIMARY KEY (`attendence_id`),
  KEY `memid_idx` (`mid`),
  CONSTRAINT `memid` FOREIGN KEY (`mid`) REFERENCES `members` (`mid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member_attendence`
--

LOCK TABLES `member_attendence` WRITE;
/*!40000 ALTER TABLE `member_attendence` DISABLE KEYS */;
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
  `start_date` datetime NOT NULL,
  `end_date` datetime NOT NULL,
  `status` enum('active','inactive') NOT NULL,
  PRIMARY KEY (`membership_id`),
  KEY `fk_mid_idx` (`member_id`),
  KEY `fk_plan_id_idx` (`plan_id`),
  CONSTRAINT `member_id` FOREIGN KEY (`member_id`) REFERENCES `members` (`mid`),
  CONSTRAINT `mplan_id` FOREIGN KEY (`plan_id`) REFERENCES `plan` (`plan_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member_membership`
--

LOCK TABLES `member_membership` WRITE;
/*!40000 ALTER TABLE `member_membership` DISABLE KEYS */;
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
  `remark` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`progress_id`),
  KEY `m_id_idx` (`mid`),
  CONSTRAINT `m_id` FOREIGN KEY (`mid`) REFERENCES `members` (`mid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member_progress`
--

LOCK TABLES `member_progress` WRITE;
/*!40000 ALTER TABLE `member_progress` DISABLE KEYS */;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member_trainer_assignment`
--

LOCK TABLES `member_trainer_assignment` WRITE;
/*!40000 ALTER TABLE `member_trainer_assignment` DISABLE KEYS */;
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
  `dob` datetime NOT NULL,
  `height` int NOT NULL,
  `weight` int NOT NULL,
  `address` varchar(255) NOT NULL,
  `join_date` datetime NOT NULL,
  `status` enum('active','inactive') NOT NULL,
  `uid` int NOT NULL,
  PRIMARY KEY (`mid`),
  KEY `fk_uid_idx` (`uid`),
  CONSTRAINT `fk_uid` FOREIGN KEY (`uid`) REFERENCES `users` (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `members`
--

LOCK TABLES `members` WRITE;
/*!40000 ALTER TABLE `members` DISABLE KEYS */;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment`
--

LOCK TABLES `payment` WRITE;
/*!40000 ALTER TABLE `payment` DISABLE KEYS */;
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
  `fees` decimal(10,2) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `dis_id` int NOT NULL,
  PRIMARY KEY (`plan_id`),
  KEY `fk_disid_idx` (`dis_id`),
  CONSTRAINT `fk_disid` FOREIGN KEY (`dis_id`) REFERENCES `plan_discount` (`dis_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `plan`
--

LOCK TABLES `plan` WRITE;
/*!40000 ALTER TABLE `plan` DISABLE KEYS */;
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
  `discount` decimal(10,2) NOT NULL,
  PRIMARY KEY (`dis_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `plan_discount`
--

LOCK TABLES `plan_discount` WRITE;
/*!40000 ALTER TABLE `plan_discount` DISABLE KEYS */;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `plan_training`
--

LOCK TABLES `plan_training` WRITE;
/*!40000 ALTER TABLE `plan_training` DISABLE KEYS */;
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
  `rname` varchar(45) NOT NULL,
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trainer_specialization`
--

LOCK TABLES `trainer_specialization` WRITE;
/*!40000 ALTER TABLE `trainer_specialization` DISABLE KEYS */;
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
  `salary` decimal(10,2) NOT NULL,
  `uid` int NOT NULL,
  PRIMARY KEY (`tid`),
  KEY `uid_idx` (`uid`),
  CONSTRAINT `uid` FOREIGN KEY (`uid`) REFERENCES `users` (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trainers`
--

LOCK TABLES `trainers` WRITE;
/*!40000 ALTER TABLE `trainers` DISABLE KEYS */;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `training_table`
--

LOCK TABLES `training_table` WRITE;
/*!40000 ALTER TABLE `training_table` DISABLE KEYS */;
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
  `uname` varchar(45) NOT NULL,
  `password` varchar(15) NOT NULL,
  `fname` varchar(45) NOT NULL,
  `lname` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `contact` varchar(45) NOT NULL,
  `gender` enum('Male','Female','Others') NOT NULL,
  `rid` int NOT NULL,
  PRIMARY KEY (`uid`),
  UNIQUE KEY `uname_UNIQUE` (`uname`),
  KEY `rid_idx` (`rid`),
  CONSTRAINT `rid` FOREIGN KEY (`rid`) REFERENCES `roles` (`rid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `workout_schedule`
--

LOCK TABLES `workout_schedule` WRITE;
/*!40000 ALTER TABLE `workout_schedule` DISABLE KEYS */;
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

-- Dump completed on 2026-01-21 10:53:19
