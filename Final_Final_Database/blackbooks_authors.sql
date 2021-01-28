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
-- Table structure for table `authors`
--

DROP TABLE IF EXISTS `authors`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `authors` (
  `id` int NOT NULL AUTO_INCREMENT,
  `first_name` varchar(150) NOT NULL,
  `last_name` varchar(150) NOT NULL,
  `Biography` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `authors`
--

LOCK TABLES `authors` WRITE;
/*!40000 ALTER TABLE `authors` DISABLE KEYS */;
INSERT INTO `authors` VALUES (1,'Albert','Camus','Ο Αλμπέρ Καμύ (Albert Camus, 7 Νοεμβρίου 1913 – 4 Ιανουαρίου 1960) ήταν Γάλλος φιλόσοφος, λογοτέχνης και συγγραφέας, ένας από τους πιο δημοφιλείς φιλόσοφους του 20ου αιώνα, και ένας από τους ιδρυτές του παραλογισμού. Ήταν επίσης ιδρυτής του Theatre du Travail (1935), για το οποίο δούλεψε ως σκηνοθέτης, διασκευαστής και ηθοποιός. Χρωστά σχεδόν εξίσου τη φήμη του στα μυθιστορήματά του Ο Ξένος και Η Πανούκλα, στα θεατρικά του έργα Καλιγούλας και Οι δίκαιοι και τέλος στα φιλοσοφικά του δοκίμια Ο Μύθος του Σίσυφου και Ο επαναστατημένος άνθρωπος. Τιμήθηκε το 1957 με το Βραβείο Νόμπελ Λογοτεχνίας.'),(2,'Fyodor Mikhailovich','Dostoevsky','Ο Φιόντορ Μιχάηλοβιτς Ντοστογιέφσκι (11 Νοεμβρίου 1821 - 9 Φεβρουαρίου 1881) ήταν Ρώσος συγγραφέας, κορυφαία μορφή της παγκόσμιας λογοτεχνίας.'),(3,'Albert','Hourani','Albert Habib Hourani CBE (March 1915 – 17 January 1993) was a British historian, specialising in the Middle East. He was of Lebanese descent'),(4,'Bertrand Arthur William','Russell','Ο Μπέρτραντ Άρθουρ Γουίλιαμ Ράσελ (Bertrand Arthur William Russell, 18 Μαΐου 1872 – 2 Φεβρουαρίου 1970) ήταν Bρετανός φιλόσοφος, μαθηματικός και ειρηνιστής. Ο Ράσελ ως συγγραφέας και άνθρωπος συνέχισε την οικογενειακή παράδοση στην πολιτική σκέψη και δράση, με αντιπολεμική δράση. Γεννήθηκε στην ακμή της Βρεταννικής Αυτοκρατορίας. Πέθανε από γρίπη, έναν αιώνα αργότερα, όταν η Βρετανία είχε περάσει δύο παγκόσμιους πολέμους. Έκανε κριτική στα πυρηνικά καθώς και την εισβολή των ΗΠΑ στο Βιετνάμ. Το 1950 κέρδισε το Νόμπελ Λογοτεχνίας. Υπήρξε  Κόμης, μέλος του Τάγματος της Αξίας και μέλος της Βασιλικής Εταιρείας.'),(5,'Cay S.',' Horstmann','Cay Horstmann grew up in Northern Germany and attended the Christian-Albrechts-Universität in Kiel, a harbor town at the Baltic sea. He received a M.S. in computer science from Syracuse University, and a Ph.D. in mathematics from the University of Michigan in Ann Arbor. For four years, he was VP and CTO of an Internet startup that went from 3 people in a tiny office to a public company. He now teaches computer science at San Jose State University. Cay also writes books and articles on programming languages and computer science education.'),(6,'Orwell','George','O Έρικ Άρθουρ Μπλαιρ ( 25 Ιουνίου 1903 – 21 Ιανουαρίου 1950), γνωστός περισσότερο με το συγγραφικό του ψευδώνυμο Τζωρτζ Όργουελ, ήταν Βρετανός συγγραφέας, λογοτέχνης και δημοσιογράφος. Το έργο του χαρακτηρίζεται από ξεκάθαρο πεζό λόγο, συνειδητότητα των κοινωνικών ανισοτήτων, αντίθεση στα ολοκληρωτικά καθεστώτα και αφοσίωση στο δημοκρατικό σοσιαλισμό.]'),(7,'André','Breton','O Αντρέ Μπρετόν (André Breton, 19 Φεβρουαρίου 1896 - 28 Σεπτεμβρίου 1966) ήταν Γάλλος συγγραφέας, αναρχικός και αντιφασίστας. Είναι ο θεμελιωτής και κύριος θεωρητικός του υπερρεαλιστικού κινήματος και εμπνευστής του μανιφέστου του υπερρεαλισμού. Ουσιαστικά είναι η προσωποποίηση του λεγόμενου ορθόδοξου υπερρεαλισμού.'),(8,'Jack','Kerouac','O Τζακ Κέρουακ (12 Μαρτίου 1922 - 21 Οκτωβρίου 1969) ήταν Αμερικανός λογοτέχνης, εικονοκλάστης του γραπτού λόγου, ένας από τους κύριους εκπροσώπους, μαζί με τους Γουίλιαμ Μπάροουζ και Άλλεν Γκίνσμπεργκ, της Μπιτ γενιάς  και εισηγητής του ομώνυμου όρου. Ανάμεσα στα διασημότερα έργα του ανήκουν τα μυθιστορήματα Οι αλήτες του Ντάρμα, Οι υποχθόνιοι και πάνω απ΄όλα το Στο δρόμο.'),(9,'Charles','Dickens','Ο Κάρολος Ντίκενς υπήρξε ένας από τους πιο διάσημους Άγγλους μυθιστοριογράφους και κριτικούς της κοινωνίας. Επινόησε ορισμένους από τους γνωστότερους διεθνώς φανταστικούς χαρακτήρες και θεωρείται από πολλούς ως ο σπουδαιότερος συγγραφέας της Βικτωριανής Εποχής. Τα έργα του έχαιραν άνευ προηγουμένου δημοτικότητας κατά τη διάρκεια της ζωής του, ενώ αυτή η δημοφιλία διατηρείται και σήμερα τόσο για τα μυθιστορήματα όσο και για τα διηγήματά του. Οι κριτικοί της λογοτεχνίας του 20ου αιώνα όπως και οι ακαδημαϊκοί τον έχουν αναγνωρίσει ως μια λογοτεχνική διάνοια'),(10,'Κωστής','Παλαμάς','Ο Κωστής Παλαμάρης (Πάτρα, 13 Ιανουαρίου 1859 - Αθήνα, 27 Φεβρουαρίου 1943) ήταν Έλληνας ποιητής, πεζογράφος, θεατρικός συγγραφέας, ιστορικός και κριτικός της λογοτεχνίας. Θεωρείται ένας από τους σημαντικότερους Έλληνες ποιητές, με σημαντική συνεισφορά στην εξέλιξη και ανανέωση της νεοελληνικής ποίησης. Αποτέλεσε κεντρική μορφή της λογοτεχνικής γενιάς του 1880, πρωτοπόρος, μαζί με τον Νίκο Καμπά και τον Γεώργιο Δροσίνη, της αποκαλούμενης Νέας Αθηναϊκής (ή Παλαμικής) σχολής.'),(11,'Pierce','Brown','Ο Pierce Brown (28 Ιανουαρίου 1988) είναι ένας Αμερικανός συγγραφέας επιστημονικής φαντασίας που γράφει τη σειρά Red Rising , που αποτελείται από τους Red Rising (2014), Golden Son (2015), Morning Star (2016), Iron Gold (2018) και Dark Age ( 2019)'),(12,'Michael ','Moorcock','Ο Μάικλ Μούρκοκ (γενν. 18 Δεκεμβρίου 1939) είναι Άγγλος συγγραφέας της φανταστικής λογοτεχνίας. Έγινε διάσημος με τις νουβέλες του Έλρικ του Μελνιμπονέ κατά τις δεκαετίες του \'60 και του \'70, ενώ παράλληλα ήταν εκδότης του περιοδικού New Worlds από το 1964 ως το 1996, με μία πενταετή διακοπή στο πρώτο μισό της δεκαετίας του \'70.'),(13,'Μαρίνα','Ζορμπαλά','Δίπλωμα ΠΤΥΧΙΟ ΠΙΑΝΟΥ, Σχολή Πιάνου, Εθνικό Ωδείο Αθηνών (κεντρικό), 1986'),(14,'Γιάννη','Καραγιαννάκη','Είναι επισκέπτης καθηγητής στο Πανεπιστήμιο της Μάλτας (University of Malta), διδάσκων του Εθνικού & Καποδιστριακού Πανεπιστημίου Αθηνών (ΕΚΠΑ) και διεξάγει μεταδιδακτορική έρευνα στο  Ινστιτούτο Έρευνας Επιστημών Ψυχολογίας του Καθολικού Πανεπιστημίου της Λουβένης (Catholic University of Leuven). '),(15,'Michel','Foucault',' 15 Οκτωβρίου του 1926 - 25 Ιουνίου, 1984 ) ήταν Γάλλος φιλόσοφος, ιστορικός ιδεών , συγγραφέας, πολιτικός ακτιβιστής και λογοτεχνικός κριτικός .');
/*!40000 ALTER TABLE `authors` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-01-28  7:59:31
