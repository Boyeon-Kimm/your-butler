CREATE DATABASE  IF NOT EXISTS `loan` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `loan`;
-- MySQL dump 10.13  Distrib 8.0.33, for Win64 (x86_64)
--
-- Host: j9a405.p.ssafy.io    Database: loan
-- ------------------------------------------------------
-- Server version	8.0.32

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
-- Table structure for table `bank`
--

DROP TABLE IF EXISTS `bank`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bank` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `bank_name` varchar(255) DEFAULT NULL,
  `bank_site` varchar(255) NOT NULL,
  `bank_tel` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=135 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `bookmark`
--

DROP TABLE IF EXISTS `bookmark`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bookmark` (
  `bookmark_id` bigint NOT NULL AUTO_INCREMENT,
  `realestate_id` bigint DEFAULT NULL,
  `member_id` bigint NOT NULL,
  PRIMARY KEY (`bookmark_id`),
  KEY `FK5bm7rup91j277mc7gg63akie2` (`member_id`),
  CONSTRAINT `FK5bm7rup91j277mc7gg63akie2` FOREIGN KEY (`member_id`) REFERENCES `member` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `chat_room`
--

DROP TABLE IF EXISTS `chat_room`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `chat_room` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `apt_id` bigint DEFAULT NULL,
  `member_id` bigint NOT NULL,
  `building_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK6x6htd2o9ba2r2wcrs72qau17` (`member_id`),
  CONSTRAINT `FK6x6htd2o9ba2r2wcrs72qau17` FOREIGN KEY (`member_id`) REFERENCES `member` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=309 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `credit_loan`
--

DROP TABLE IF EXISTS `credit_loan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `credit_loan` (
  `id` int NOT NULL AUTO_INCREMENT,
  `financial_company_name` varchar(50) DEFAULT NULL,
  `financial_product_name` varchar(50) DEFAULT NULL,
  `join_way` varchar(50) DEFAULT NULL,
  `credit_bureau_company_name` varchar(50) DEFAULT NULL,
  `credit_loan_type_name` varchar(50) DEFAULT NULL,
  `credit_loan_interest_rate_type_name` varchar(50) DEFAULT NULL,
  `loan_interest_rate_credit_score_above_900` float DEFAULT NULL,
  `loan_interest_rate_credit_score_801_900` float DEFAULT NULL,
  `loan_interest_rate_credit_score_701_800` float DEFAULT NULL,
  `loan_interest_rate_credit_score_601_700` float DEFAULT NULL,
  `loan_interest_rate_credit_score_501_600` float DEFAULT NULL,
  `loan_interest_rate_credit_score_401_500` float DEFAULT NULL,
  `loan_interest_rate_credit_score_301_400` float DEFAULT NULL,
  `loan_interest_rate_credit_score_below_300` float DEFAULT NULL,
  `average_credit_interest_rate` float DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=566 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `jeonse_loan`
--

DROP TABLE IF EXISTS `jeonse_loan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `jeonse_loan` (
  `id` int NOT NULL AUTO_INCREMENT,
  `financial_company_name` varchar(50) DEFAULT NULL,
  `financial_product_name` varchar(50) DEFAULT NULL,
  `join_way` varchar(1000) DEFAULT NULL,
  `loan_incidental_expenses` varchar(1000) DEFAULT NULL,
  `early_repayment_fee` varchar(1000) DEFAULT NULL,
  `delinquency_interest_rate` varchar(1000) DEFAULT NULL,
  `loan_limit` varchar(1000) DEFAULT NULL,
  `repayment_type` varchar(1000) DEFAULT NULL,
  `loan_interest_rate_type` varchar(1000) DEFAULT NULL,
  `loan_interest_rate_min` float DEFAULT NULL,
  `loan_interest_rate_max` float DEFAULT NULL,
  `previous_month_average_handled_interest_rate` float DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=153 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `member`
--

DROP TABLE IF EXISTS `member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `member` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `age` int DEFAULT NULL,
  `credit_rating` int DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `holding_asset` int DEFAULT NULL,
  `identifier` varchar(255) DEFAULT NULL,
  `monthly_available_asset` int DEFAULT NULL,
  `nickname` varchar(255) DEFAULT NULL,
  `number_of_houses` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  `social_type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `message`
--

DROP TABLE IF EXISTS `message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `message` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `is_bot` bit(1) NOT NULL,
  `loan_interest` double DEFAULT NULL,
  `loan_name` varchar(255) DEFAULT NULL,
  `message` varchar(10000) NOT NULL,
  `time_stamp` datetime(6) NOT NULL,
  `bank_id` bigint DEFAULT NULL,
  `chatroom_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK1h9a3r11vl8smxy8twm80bwct` (`bank_id`),
  KEY `FK3dp0e0jr98c8rye4whnei24j` (`chatroom_id`),
  CONSTRAINT `FK1h9a3r11vl8smxy8twm80bwct` FOREIGN KEY (`bank_id`) REFERENCES `bank` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK3dp0e0jr98c8rye4whnei24j` FOREIGN KEY (`chatroom_id`) REFERENCES `chat_room` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=611 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `mortgage_loan`
--

DROP TABLE IF EXISTS `mortgage_loan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mortgage_loan` (
  `id` int NOT NULL AUTO_INCREMENT,
  `financial_company_name` varchar(50) DEFAULT NULL,
  `financial_product_name` varchar(50) DEFAULT NULL,
  `join_way` varchar(50) DEFAULT NULL,
  `loan_incidental_expenses` varchar(1000) DEFAULT NULL,
  `early_repayment_fee` varchar(1000) DEFAULT NULL,
  `delinquency_interest_rate` varchar(100) DEFAULT NULL,
  `loan_limit` varchar(100) DEFAULT NULL,
  `mortgage_type` varchar(50) DEFAULT NULL,
  `repayment_type` varchar(50) DEFAULT NULL,
  `loan_interest_rate_type` varchar(50) DEFAULT NULL,
  `loan_interest_rate_min` float DEFAULT NULL,
  `loan_interest_rate_max` float DEFAULT NULL,
  `previous_month_average_handled_interest_rate` float DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=436 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-10-06 10:50:56
