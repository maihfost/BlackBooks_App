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
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (21,_binary '','admin@gmail.com','admin','$2a$10$Ktb0TbZBSD0WYspBnB3l5Od9z9iUQpjvMujBDxweVWAOBGmt2vl7S','Ευθαλία','Κρασά','1989-02-15','Ελλάδα','6981213144',0.00,25000.00),(22,_binary '','giorgoskok@gmail.com','giorgos','$2a$10$ZO091xHN/ryTENgEpp4brOa7ypDDxHFCdQ6FcDTEBtUJwZMUBBk2q','Γιώργος','Κοκολάκης','1990-06-07','Ελλάδα','6983728332',0.00,4962.26),(23,_binary '','tasosanton@gmail.com','tasos','$2a$10$OLlo3o6zwfwf9eMq0s2Pju4EKwJyJwM5xRcvSYsbjiZT7.fMohDBW','Τάσος','Αντωνόπουλος','1994-07-14','Ελλάδα','6987364733',0.00,4951.00),(24,_binary '','anastmin@gmail.com','anastasia','$2a$10$F4q5a87fLeiJMo5nRGmy2O2vrKOZs9Sz0pk/4A3BjIc4km7mNnK0G','Αναστασία','Μιναίδου','1992-02-05','Ελλάδα','6981432122',0.00,4968.00),(25,_binary '','maihfost@gmail.com','maih','$2a$10$zQhBjLsWWRcdJqHLHbRs5elbFq/Rmm91umjP7zMJ8mModcVRfgo8W','Μαρία','Φωστηροπούλου','1992-03-27','Ελλάδα','6972683523',0.00,4912.38);
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

-- Dump completed on 2021-01-28  7:59:29
