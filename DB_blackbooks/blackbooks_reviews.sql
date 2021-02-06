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
-- Table structure for table `reviews`
--

DROP TABLE IF EXISTS `reviews`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reviews` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `book_id` int NOT NULL,
  `rating` decimal(2,1) DEFAULT NULL,
  `review_datetime` datetime NOT NULL,
  `comment` varchar(400) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `combined_user_book` (`user_id`,`book_id`),
  KEY `reviews_user_id_users_id_fk_idx` (`user_id`),
  KEY `reviews_book_id_books_id_fk_idx` (`book_id`),
  CONSTRAINT `reviews_book_id_books_id_fk` FOREIGN KEY (`book_id`) REFERENCES `books` (`id`),
  CONSTRAINT `reviews_user_id_users_id_fk` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reviews`
--

LOCK TABLES `reviews` WRITE;
/*!40000 ALTER TABLE `reviews` DISABLE KEYS */;
INSERT INTO `reviews` VALUES (9,22,1,4.8,'2021-01-28 04:59:15','Φοβερό βιβλίο! '),(10,22,3,5.0,'2021-01-28 05:00:09','Συγκλονιστικό!'),(11,22,4,5.0,'2021-01-28 05:01:38','Εξαιρετικά ευανάγνωστο για ιστορικό βιβλίο..'),(12,22,6,3.9,'2021-01-28 05:03:40','Οτι πρέπει για να ξεκινήσει κανείς..'),(13,23,1,4.7,'2021-01-28 05:06:47','Τι να πει κανείς για αυτό το βιβλίο..ΑΡΙΣΤΟΥΡΓΗΜΑ!!'),(14,23,6,4.9,'2021-01-28 05:07:53','Πάρτε μια βαθιά ανάσα,και ξεκινήστε..'),(15,23,5,4.6,'2021-01-28 05:09:28',' Ενα βιβλίο που θα έπρεπε να διαβασει οπωσδηποτε όποιος ενδιαφερεται για φιλοσοφία!'),(16,23,8,5.0,'2021-01-28 05:11:07','Μπρετόν..παντα εξαιρετικός..'),(17,24,4,5.0,'2021-01-28 05:25:18','Αξιζει να το διαβαζεις και να το ξαναδιαβαζεις..'),(18,24,8,2.4,'2021-01-28 05:26:11','Περιεργος τυπος ο Μπρετόν..'),(19,24,7,5.0,'2021-01-28 05:27:00','No words!!!!!!'),(20,24,3,5.0,'2021-01-28 05:27:37','Έπος...'),(21,25,1,4.0,'2021-01-28 05:29:18','Πολυ ωραίο βιβλίο,θα το πρότεινα.'),(22,25,6,3.0,'2021-01-28 05:31:07','Καλή τύχη'),(23,25,7,4.9,'2021-01-28 05:32:42','!Εξαιρετικός Orwell!'),(24,25,2,4.2,'2021-01-28 05:34:11','Καλύτερο και απο τον Ξένο ..');
/*!40000 ALTER TABLE `reviews` ENABLE KEYS */;
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
