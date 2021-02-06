CREATE DATABASE  IF NOT EXISTS `blackbooks` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `blackbooks`;
-- MySQL dump 10.13  Distrib 8.0.22, for Win64 (x86_64)
--
-- Host: localhost    Database: blackbooks
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
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `active` bit(1) DEFAULT NULL,
  `email` varchar(100) NOT NULL,
  `user_name` varchar(50) NOT NULL,
  `password` char(60) NOT NULL,
  `first_name` varchar(150) NOT NULL,
  `last_name` varchar(150) NOT NULL,
  `dateofbirth` date DEFAULT NULL,
  `country` varchar(100) DEFAULT NULL,
  `telephone` varchar(15) DEFAULT NULL,
  `total_payment_amount` decimal(10,2) DEFAULT NULL,
  `digit_wallet` decimal(10,2) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_name_UNIQUE` (`user_name`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (22,_binary '','giorgoskok@gmail.com','giorgos','$2a$10$ZO091xHN/ryTENgEpp4brOa7ypDDxHFCdQ6FcDTEBtUJwZMUBBk2q','Γιώργος','Κοκολάκης','1990-06-07','Ελλάδα','6983728332',0.00,4962.26),(23,_binary '','tasosanton@gmail.com','tasos','$2a$10$hPouKqPrWJKamS8tcYO6NudY8q4cLgy0Oe5Rj2JtUuuS8CyAFk4uC','Τάσος','Αντωνόπουλος','1994-07-14','Ελλάδα','6981111111',0.00,4951.00),(24,_binary '','anastmin@gmail.com','anastasia','$2a$10$NQyXt6Y2HCiAJyO9z2Zf6.MeDTInpi9AaUIQhwLJcwACIgrcJ5lYS','Αναστασία','Μιναίδου','1992-02-05','Ελλάδα','6984444444',0.00,4968.00),(25,_binary '','maihfost@gmail.com','maih','$2a$10$ZwtowS/QLN9lnfq90NxS5OZ/MStPkExar4h0x8PbYW6akz/6hdrNC','Μαρία','Φωστηροπούλου','1992-03-27','Ελλάδα','6970000000',0.00,4912.38),(30,_binary '','mike@gmail.com','mike','$2a$10$LPQ1zE97BYaJmvRPGjUTQ.qhaY35kdZEVULEWxWWSAaqOreFRyOn2','Mike','Mikitoο','2020-06-17','Greece','6981233457',0.00,3050.00),(33,_binary '','admin@gmail.com','admin','$2a$10$DYi/jbeNmCiX6hotwJObV.tj2GhSVWZjG1d9D2RwUCeBhzJe2Dmyi','admin','admin','2021-02-02','Greece','6994496237',0.00,5000.00);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-02-06 23:26:44
