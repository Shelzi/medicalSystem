-- MySQL dump 10.13  Distrib 8.0.22, for Win64 (x86_64)
--
-- Host: localhost    Database: medicalsystem
-- ------------------------------------------------------
-- Server version	8.0.22

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
-- Table structure for table `cities`
--

DROP TABLE IF EXISTS `cities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cities` (
  `city_id` int NOT NULL AUTO_INCREMENT,
  `city` varchar(45) NOT NULL,
  PRIMARY KEY (`city_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cities`
--

LOCK TABLES `cities` WRITE;
/*!40000 ALTER TABLE `cities` DISABLE KEYS */;
INSERT INTO `cities` VALUES (1,'Test'),(2,'МИНСК');
/*!40000 ALTER TABLE `cities` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `patients`
--

DROP TABLE IF EXISTS `patients`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `patients` (
  `patientId` bigint NOT NULL AUTO_INCREMENT,
  `firstName` varchar(50) NOT NULL,
  `lastName` varchar(45) NOT NULL,
  `middleName` varchar(45) DEFAULT NULL,
  `gender` int NOT NULL,
  `birthday` date NOT NULL,
  `homeTownId_fk` int NOT NULL,
  `homeAddress` varchar(256) NOT NULL,
  `homeNumber` varchar(10) NOT NULL,
  `apartmentNumber` varchar(10) DEFAULT NULL,
  `phoneNumber` varchar(20) NOT NULL,
  PRIMARY KEY (`patientId`),
  KEY `homeTownId_fk_idx` (`homeTownId_fk`),
  CONSTRAINT `homeTownId_fk` FOREIGN KEY (`homeTownId_fk`) REFERENCES `cities` (`city_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patients`
--

LOCK TABLES `patients` WRITE;
/*!40000 ALTER TABLE `patients` DISABLE KEYS */;
INSERT INTO `patients` VALUES (1,'Test','Test','Test',1,'2011-11-20',1,'Test','Test','Test','Test'),(2,'хуй','хуй','хуй',1,'1111-11-11',1,'ччч','555','вфв','вфывф'),(3,'Anton','Nemtsov','Arturovich',1,'1111-03-12',2,'ул. Хуйная','555','12','+375336128028');
/*!40000 ALTER TABLE `patients` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `procedures`
--

DROP TABLE IF EXISTS `procedures`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `procedures` (
  `procedureId` int NOT NULL,
  `procedureName` varchar(200) NOT NULL,
  PRIMARY KEY (`procedureId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `procedures`
--

LOCK TABLES `procedures` WRITE;
/*!40000 ALTER TABLE `procedures` DISABLE KEYS */;
/*!40000 ALTER TABLE `procedures` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `procedures_schedule`
--

DROP TABLE IF EXISTS `procedures_schedule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `procedures_schedule` (
  `procedures_scheduleId` int NOT NULL,
  `visitId_fk` bigint NOT NULL,
  `procedureId_fk` int NOT NULL,
  PRIMARY KEY (`procedures_scheduleId`),
  KEY `visitId_fk_idx` (`visitId_fk`),
  KEY `procedureId_fk_idx` (`procedureId_fk`),
  CONSTRAINT `procedureId_fk` FOREIGN KEY (`procedureId_fk`) REFERENCES `procedures` (`procedureId`),
  CONSTRAINT `visitId_fk` FOREIGN KEY (`visitId_fk`) REFERENCES `visits` (`visitId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `procedures_schedule`
--

LOCK TABLES `procedures_schedule` WRITE;
/*!40000 ALTER TABLE `procedures_schedule` DISABLE KEYS */;
/*!40000 ALTER TABLE `procedures_schedule` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_role` (
  `role_id` tinyint NOT NULL,
  `role` varchar(20) NOT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='			';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` VALUES (1,'NURSE'),(2,'DOCTOR'),(3,'LABMEM'),(4,'REGISTRAR'),(5,'ADMIN');
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `userId` bigint NOT NULL AUTO_INCREMENT,
  `firstName` varchar(20) NOT NULL,
  `lastName` varchar(20) NOT NULL,
  `middleName` varchar(20) DEFAULT NULL,
  `email` varchar(50) NOT NULL,
  `password` varchar(100) NOT NULL,
  `userRoleId` tinyint NOT NULL,
  `isBanned` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`userId`),
  KEY `userRoleId2_idx` (`userRoleId`),
  CONSTRAINT `userRoleIdFk` FOREIGN KEY (`userRoleId`) REFERENCES `user_role` (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (20,'Admin','Admin','Admn','admin@admin.com','$2a$10$tNAL8RsXqu4u.9xlLk3Giu0NXq8G1eFakqTTmob3hAQbk04RcH9.q',5,0),(21,'Doctor','Doctor','Doctor','doctor@doctor.com','$2a$10$.gCCpzvaHdbbSJwGvVwNsuW63/YaDuQaHg5fFb8Fe8Nmsh1cItn/u',2,0),(22,'Nurse','Nurse','Nurse','nurse@nurse.com','$2a$10$sRTHZevswNRZBaAl4N7UcuscTZOLLdE4LM27IAGzVJzU5MrF3rWba',1,0),(23,'Test','Test','Test','fsfks@dfsd.sdf','$2a$10$LTbrFMIlf.xSHl/a3q1n1eZIcU31IgzERkF8N1ZvxbKwMqBJBsYKq',2,1),(24,'dsafa','sdfsdf','sdfsdf','nemantsdasd@gmail.com','$2a$10$Mr6H0j9AJLOktGz0IX/HkuW8bwjnRgKAkdmVnzt84wzeGkat0VYQq',2,1),(25,'dasdasd','asdasdas','sadasd','nemant3adsas8@gmail.com','$2a$10$hiO31j8.TTcG1mzGczdg/uUYyhHTDe99s/R5f7JcYQIdqlr5O2Tdi',2,1),(26,'dadas','asdasd','sadasd','nemantasdasd40@gmail.com','$2a$10$Ix8byF2Lrmn.sfF5Em7UCuBu/o7YDAwxP2eNUP9XWlGp2ilt7CUfy',2,1),(27,'adasd','adas','saddd','a123456@admin.com','$2a$10$Ucz6LCMVsWU8x/SoUAOjEedMQwBE50sDEtejjm6IOF6KwRHtmY8LK',2,1),(28,'asdasd','asdas','asdas','admsadasdasdin@admin.com','$2a$10$WNB6MBsX3ibozkR51rRG8.4pHvUwQODvKIApBcUq7UiuUQ6y.Smn2',2,1),(29,'REG','REG','REG','reg@reg.com','$2a$10$MCBHdG39gWaa1QYGXg0Qhu6Z/3ltlER9APPH0EF957vng0pe8utNu',4,0);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `visits`
--

DROP TABLE IF EXISTS `visits`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `visits` (
  `visitId` bigint NOT NULL AUTO_INCREMENT,
  `visitTime` datetime NOT NULL,
  `patientId_fk` bigint NOT NULL,
  `doctorName` varchar(45) NOT NULL,
  `anamnesis` text NOT NULL,
  `complaints` text NOT NULL,
  `diagnosis` text NOT NULL,
  `medicines` text NOT NULL,
  `nextVisitDate` datetime DEFAULT NULL,
  PRIMARY KEY (`visitId`),
  KEY `patientId_fk_idx` (`patientId_fk`),
  CONSTRAINT `patientId_fk` FOREIGN KEY (`patientId_fk`) REFERENCES `patients` (`patientId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `visits`
--

LOCK TABLES `visits` WRITE;
/*!40000 ALTER TABLE `visits` DISABLE KEYS */;
INSERT INTO `visits` VALUES (1,'2007-05-08 12:35:29',1,'name','ana','nan','anan','anan','2007-05-08 12:35:29'),(2,'2007-05-08 12:35:29',1,'name2','adjasdj','lasdjnalsjd','dakdnkasjdn','kjsadnaksdn','2007-05-08 12:35:29');
/*!40000 ALTER TABLE `visits` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-02-28 23:17:49
