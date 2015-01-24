-- MySQL dump 10.13  Distrib 5.6.17, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: softeng
-- ------------------------------------------------------
-- Server version	5.6.22-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `customers`
--

DROP TABLE IF EXISTS `customers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customers` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fname` varchar(25) DEFAULT NULL,
  `sname` varchar(55) DEFAULT NULL,
  `telephone` int(11) DEFAULT NULL,
  `mobile` int(11) DEFAULT NULL,
  `afm` int(11) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `city` varchar(45) DEFAULT NULL,
  `transactions` int(11) DEFAULT '0',
  `debit` int(11) DEFAULT '0',
  `discount` int(11) DEFAULT '0',
  `amount` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customers`
--

LOCK TABLES `customers` WRITE;
/*!40000 ALTER TABLE `customers` DISABLE KEYS */;
INSERT INTO `customers` VALUES (1,'ATEI PEIRAIA','ATEI',2130123123,987676443,12312344,'Thivwn Petrou Ralli','Aigaleo',0,0,0,0),(2,'ATEI AΘΗΝΑΣ','ΑΤΕΙ',2017868576,98745452,9876543,'ΑΙΓΑΛΕΟ',NULL,0,10,0,0),(3,'ATEI ΧΑΛΚΙΔΑΣ','ΑΤΕΙ',2017868576,98745452,9876543,'ΑΙΓΑΛΕΟ',NULL,0,10,0,0),(4,'ΜΕΤΣΟΒΙΟ','ΑΤΕΙ',2017868576,98745452,985217785,'ΑΙΓΑΛΕΟ',NULL,0,100,0,0);
/*!40000 ALTER TABLE `customers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `products` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `serial` int(11) DEFAULT NULL,
  `count` int(11) DEFAULT '0',
  `price` int(11) DEFAULT NULL,
  `vat` int(11) DEFAULT NULL,
  `seller_id` int(11) DEFAULT NULL,
  `seller_name` varchar(55) DEFAULT NULL,
  `status` int(11) DEFAULT '1',
  `warehouse` int(11) DEFAULT NULL,
  `barcode` varchar(255) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` VALUES (1,'Gigabyte mitriki',123123,1231,100,2,NULL,'',1,1,'12312312'),(2,'ASUS MOtherBoarad',12312,1100,100,3,NULL,'info quest',1,1,'12312312'),(4,'Gigabyte GTX760 2gb',123654,254,750,3,NULL,'',1,1,'12312312'),(8,'Intel CPu i7',1234,1,121,2,NULL,'12',1,1,'123123'),(9,'Intel CPu i7',1234,125,121,3,NULL,'info Quest',1,1,'123123'),(11,'CoolerMaster Stacker 830',987123,25,200,3,NULL,'CoolerMaster',1,1,'258625698');
/*!40000 ALTER TABLE `products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sales`
--

DROP TABLE IF EXISTS `sales`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sales` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `product_num` int(11) DEFAULT NULL,
  `product_name` varchar(55) DEFAULT NULL,
  `user` int(11) DEFAULT NULL,
  `warehouse` int(11) DEFAULT NULL,
  `items` int(11) DEFAULT NULL,
  `amount` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sales`
--

LOCK TABLES `sales` WRITE;
/*!40000 ALTER TABLE `sales` DISABLE KEYS */;
INSERT INTO `sales` VALUES (1,12312,'asus motherboard',2,1,3,10),(2,12312,'asus board',2,1,5,15);
/*!40000 ALTER TABLE `sales` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `status`
--

DROP TABLE IF EXISTS `status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `status` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `condition` varchar(45) DEFAULT NULL,
  `flag` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `status`
--

LOCK TABLES `status` WRITE;
/*!40000 ALTER TABLE `status` DISABLE KEYS */;
INSERT INTO `status` VALUES (1,'Ενεργο Ειδος',''),(2,'Ανενεργο Ειδος ','');
/*!40000 ALTER TABLE `status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `userlevel`
--

DROP TABLE IF EXISTS `userlevel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `userlevel` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ulevel` int(11) DEFAULT '1',
  `level_name` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userlevel`
--

LOCK TABLES `userlevel` WRITE;
/*!40000 ALTER TABLE `userlevel` DISABLE KEYS */;
INSERT INTO `userlevel` VALUES (1,1,'Χρηστης'),(2,2,'Διαχειριστης');
/*!40000 ALTER TABLE `userlevel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(25) DEFAULT NULL,
  `fname` varchar(25) DEFAULT NULL,
  `sname` varchar(55) DEFAULT NULL,
  `password` varchar(25) DEFAULT NULL,
  `level` int(11) DEFAULT NULL,
  `login` datetime DEFAULT NULL,
  `login_day` date DEFAULT NULL,
  `sales` int(11) DEFAULT '0',
  `sales_amount` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'sysadmin','SysAdmin','Admin','19896',2,'0000-00-00 00:00:00','0000-00-00',0,0),(2,'user2','Kwstas','Prixas','12345',1,NULL,NULL,0,0),(3,'user3','Kwstas','malakas','1234',1,NULL,NULL,0,0),(4,'user4','karagiozhs','malakas','1234',1,NULL,NULL,0,0),(5,'antonog','George','Antono','1111',2,NULL,NULL,0,0),(6,'Stiv','Stefanos','Maroulis','2222',1,NULL,NULL,0,0),(7,'Tzeni','tzeni','Tzeni','1',1,NULL,NULL,0,0);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vat`
--

DROP TABLE IF EXISTS `vat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `vat` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `VatDisplay` varchar(45) DEFAULT NULL,
  `VatNumber` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vat`
--

LOCK TABLES `vat` WRITE;
/*!40000 ALTER TABLE `vat` DISABLE KEYS */;
INSERT INTO `vat` VALUES (1,'6%',6),(2,'13%',13),(3,'23%',23),(4,'36%',36),(5,'0%',0),(6,'9%',9),(7,'19%',19);
/*!40000 ALTER TABLE `vat` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `warehouse`
--

DROP TABLE IF EXISTS `warehouse`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `warehouse` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(55) DEFAULT NULL,
  `products` int(11) DEFAULT '0',
  `product_in` int(11) DEFAULT '0',
  `product_out` int(11) DEFAULT '0',
  `max_prod` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `warehouse`
--

LOCK TABLES `warehouse` WRITE;
/*!40000 ALTER TABLE `warehouse` DISABLE KEYS */;
INSERT INTO `warehouse` VALUES (1,'Main Warehouse',1115,0,0,10000),(2,'Suburb Warehouse',100,0,0,20000);
/*!40000 ALTER TABLE `warehouse` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'softeng'
--

--
-- Dumping routines for database 'softeng'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-01-25  0:44:09
