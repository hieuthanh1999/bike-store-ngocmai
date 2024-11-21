CREATE DATABASE  IF NOT EXISTS `ngocmaidb` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `ngocmaidb`;
-- MySQL dump 10.13  Distrib 8.0.36, for macos14 (arm64)
--
-- Host: 127.0.0.1    Database: ngocmaidb
-- ------------------------------------------------------
-- Server version	9.1.0

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
-- Table structure for table `banner`
--

DROP TABLE IF EXISTS `banner`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `banner` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `detail` text NOT NULL,
  `img` varchar(200) NOT NULL,
  `title` varchar(45) NOT NULL,
  `url` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `banner`
--

LOCK TABLES `banner` WRITE;
/*!40000 ALTER TABLE `banner` DISABLE KEYS */;
INSERT INTO `banner` VALUES (1,'Mỗi lần đạp là gắn kết yêu thương','/user/img/banner/xe-dap-doi-pax-gia-dinh-1-1.jpg','Xe đôi','/'),(2,'Mạnh mẽ cá tính','/user/img/banner/bike-3D-BMX-sports-731083-wallhere.com.jpg','Xe địa hình','/'),(3,'Tăng cường hoạt động','./user/img/banner/England-bird-nature-robin-birds-bike-974131-wallhere.com.jpg','Xe trẻ em','/'),(4,'Rèn luyện sức khoẻ','./user/img/banner/street-night-bicycle-vehicle-wall-blue-634607-wallhere.com.jpg','Xe thể thao','/'),(5,'Phụ kiện','./user/img/banner/city-street-bicycle-vehicle-road-basket-558424-wallhere.com.jpg','Accessories','/'),(6,'Banner 6','/user/img/banner/people-city-street-car-bicycle-vehicle-790659-wallhere.com.jpg','sad','/'),(7,'Banner 7 ','/user/img/banner/bicycle-windmill-2196895-wallhere.com.jpg','Accessories','/'),(8,'Banner 8','/user/img/banner/sports-bicycle-vehicle-BMX-cycling-skatepark-592558-wallhere.com.jpg','Accessories','/'),(9,'Banner 9','/user/img/banner/sports-sport-bicycle-vehicle-men-outdoors-BMX-204491-wallhere.com.jpg','Accessories','/'),(10,'Banner 10','/user/img/banner/street-bicycle-vehicle-road-cycling-wheel-152517-wallhere.com.jpg','Accessories','/'),(11,'Banner 11','/user/img/banner/sunlight-landscape-forest-sunset-hill-bicycle-858450-wallhere.com.jpg','Accessories','/'),(12,'Banner 12','/user/img/banner/travel-Netherlands-windmill-Dutch-bicycle-nederland-933185-wallhere.com.jpg','Accessories','/');
/*!40000 ALTER TABLE `banner` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `detail` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'Xe thể thao','Hang vd 1'),(2,'Xe đôi','Hang vd 2'),(3,'Xe địa hình','Các loại xe vượt địa hình'),(4,'Xe trẻ em','Xe dành cho trẻ con');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `color`
--

DROP TABLE IF EXISTS `color`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `color` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `productid` int unsigned NOT NULL,
  `color` varchar(10) NOT NULL,
  `amount` int unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_size_1` (`productid`),
  CONSTRAINT `FK_size_1` FOREIGN KEY (`productid`) REFERENCES `product` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `color`
--

LOCK TABLES `color` WRITE;
/*!40000 ALTER TABLE `color` DISABLE KEYS */;
INSERT INTO `color` VALUES (2,21,'37',4),(4,22,'37',3),(5,22,'38',7),(6,22,'39',10),(7,21,'35',5),(8,21,'36',8),(9,21,'39',9);
/*!40000 ALTER TABLE `color` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contact`
--

DROP TABLE IF EXISTS `contact`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `contact` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `phone` varchar(45) NOT NULL,
  `address` varchar(150) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contact`
--

LOCK TABLES `contact` WRITE;
/*!40000 ALTER TABLE `contact` DISABLE KEYS */;
/*!40000 ALTER TABLE `contact` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `discount`
--

DROP TABLE IF EXISTS `discount`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `discount` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `percent` int unsigned NOT NULL,
  `amount` int unsigned NOT NULL,
  `isenable` tinyint(1) NOT NULL,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `discount`
--

LOCK TABLES `discount` WRITE;
/*!40000 ALTER TABLE `discount` DISABLE KEYS */;
INSERT INTO `discount` VALUES (1,5,10,1,'MIPAN'),(2,5,0,1,'HETMA');
/*!40000 ALTER TABLE `discount` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `new`
--

DROP TABLE IF EXISTS `new`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `new` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `title` varchar(45) NOT NULL,
  `img` varchar(45) NOT NULL,
  `detail` text NOT NULL,
  `newcategoryid` int unsigned NOT NULL,
  `oncreate` varchar(45) NOT NULL,
  `onupdate` varchar(45) NOT NULL,
  `status` varchar(45) NOT NULL,
  `view` int unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_new_1` (`newcategoryid`),
  CONSTRAINT `FK_new_1` FOREIGN KEY (`newcategoryid`) REFERENCES `newcategory` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `new`
--

LOCK TABLES `new` WRITE;
/*!40000 ALTER TABLE `new` DISABLE KEYS */;
/*!40000 ALTER TABLE `new` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `newcategory`
--

DROP TABLE IF EXISTS `newcategory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `newcategory` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `detail` text NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `newcategory`
--

LOCK TABLES `newcategory` WRITE;
/*!40000 ALTER TABLE `newcategory` DISABLE KEYS */;
/*!40000 ALTER TABLE `newcategory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order`
--

DROP TABLE IF EXISTS `order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order` (
  `id` varchar(100) NOT NULL,
  `userid` int NOT NULL,
  `fullname` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `phone` varchar(15) NOT NULL,
  `email` varchar(45) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `address` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `status` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `methodpayment` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `oncreate` date NOT NULL,
  `amount` int NOT NULL,
  `amountp` int NOT NULL,
  `moneycode` varchar(45) DEFAULT NULL,
  `discountname` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order`
--

LOCK TABLES `order` WRITE;
/*!40000 ALTER TABLE `order` DISABLE KEYS */;
INSERT INTO `order` VALUES ('04400a26-9fbf-42b7-a221-b8f3bc0cd488',1,'Bùi Minh Hiếu','0336465199','gacontapgayno1@gmail.com','Hạ Hòa','Hoàn thành','Thanh toán khi nhận hàng','2021-05-09',1800000,1,NULL,NULL),('b7836126-8d6c-47b6-8f16-570d6a6af6fd',0,'Bùi Minh Hiếu','0336465199','gacontapgayno1@gmail.com','Hạ Hòa','Đang chờ','Thanh toán khi nhận hàng','2021-05-12',1805000,1,'','MIPAN'),('f1d87c02-a24c-47f6-ad52-9befd646e7e0',1,'Bùi Minh Hiếu','0336465199','gacontapgayno1@gmail.com','Hạ Hòa','Hoàn thành','Thanh toán khi nhận hàng','2021-04-10',1900000,1,NULL,NULL);
/*!40000 ALTER TABLE `order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orderdetail`
--

DROP TABLE IF EXISTS `orderdetail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orderdetail` (
  `id` int NOT NULL AUTO_INCREMENT,
  `productid` int unsigned NOT NULL,
  `size` varchar(20) NOT NULL,
  `amount` int unsigned NOT NULL,
  `orderid` varchar(100) NOT NULL,
  `price` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fkproduct_idx` (`productid`),
  KEY `fk_idx` (`orderid`),
  CONSTRAINT `fk` FOREIGN KEY (`orderid`) REFERENCES `order` (`id`),
  CONSTRAINT `fkproduct` FOREIGN KEY (`productid`) REFERENCES `product` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orderdetail`
--

LOCK TABLES `orderdetail` WRITE;
/*!40000 ALTER TABLE `orderdetail` DISABLE KEYS */;
INSERT INTO `orderdetail` VALUES (41,21,'35',1,'f1d87c02-a24c-47f6-ad52-9befd646e7e0',1900000),(42,22,'37',1,'04400a26-9fbf-42b7-a221-b8f3bc0cd488',1800000),(43,21,'35',1,'b7836126-8d6c-47b6-8f16-570d6a6af6fd',1900000);
/*!40000 ALTER TABLE `orderdetail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `price` varchar(45) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `sale` varchar(45) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `img` varchar(200) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `detail` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `categoryid` int unsigned NOT NULL,
  `code` varchar(45) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `oncreate` date NOT NULL,
  `onupdate` date NOT NULL,
  `color` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `status` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `view` int unsigned NOT NULL,
  `img2` varchar(200) DEFAULT NULL,
  `img3` varchar(200) DEFAULT NULL,
  `img4` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_product_1` (`categoryid`),
  CONSTRAINT `FK_product_1` FOREIGN KEY (`categoryid`) REFERENCES `category` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (21,'Giant Rincon','6790000','0','/images/rincon2020-2.jpg','Mẫu xe đạp số 1',1,'170804C','2021-04-03','2024-11-20','Yellow','Còn hàng',0,'images/rincon2020-2.jpg','images/rincon2020-2.jpg','images/rincon2020-2.jpg'),(22,'ATX 660 2019','7450000','7000000','/images/atx6602019.jpg','Mẫu xe đạp số 2',1,'170802C','2021-04-03','2021-04-03','Grey','Còn hàng',58,NULL,NULL,NULL),(23,'ATX 1','10890000','10800000','/images/atx1.jpg','Mẫu xe đạp số 3',1,'170801C','2021-04-03','2021-04-03','Black','Hết hàng',2,NULL,NULL,NULL),(24,'ATX 2','9950000','9100000','/images/atx2.jpg','Mẫu xe đạp số 4',1,'170367C','2021-04-03','2021-04-03','Grey','Hết hàng',10,NULL,NULL,NULL),(25,'Escapse 2 City Disc','12350000','12000000','/images/escape-2-city-disc.jpg','Mẫu xe đạp số 5',1,'170869C','2021-04-03','2021-04-03','Grey','Hết hàng',1,NULL,NULL,NULL),(26,'Escape 3 Asia','2100000','1000','/images/escape-3-asia.jpg','Mẫu xe đạp số 6',1,'170868C','2021-04-03','2021-04-03','Black','Còn -   Trong kho',0,NULL,NULL,NULL),(27,'Rincon Disc','10350000','10100000','/images/rincon-disc.jpg','Mẫu xe đạp số 7',1,'170867C','2021-04-03','2021-04-03','Black','Còn -   Trong kho',0,NULL,NULL,NULL),(28,'Talon 3','12350000','12350000','/images/talon3.jpg','Mẫu xe đạp số 8',1,'171243C','2021-04-03','2021-04-03','Red','Hết hàng',0,NULL,NULL,NULL),(29,'XTC SLR 3','19850000','19850000','/images/xtc-slr-3.jpg','Mẫu xe đạp số 9',1,'171242C','2021-04-03','2021-04-03','Black','Hết hàng',0,NULL,NULL,NULL);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'ADMIN'),(2,'USER');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(45) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `password` varchar(100) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `fullname` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `phone` varchar(15) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `email` varchar(45) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `address` varchar(150) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `enable` tinyint DEFAULT '1',
  `lastlogined` date DEFAULT NULL,
  `logined` double DEFAULT '0',
  `oncreate` date DEFAULT NULL,
  `token` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'admin','$2a$10$Gw5DwHCbw2RB0UM00XZoGuNrGEWow/gT/Kz8sxCQSAGiXK34Y9OI2','Bùi Minh Hiếu','0336465199','gacontapgayno1@gmail.com','Hạ Hòa',1,'2024-11-20',202,'2021-05-08',NULL),(9,'user','$2a$10$Gw5DwHCbw2RB0UM00XZoGuNrGEWow/gT/Kz8sxCQSAGiXK34Y9OI2','Nguoi Dung','0365265496','gacontapgayno3@gmail.com','Cao Dien',1,'2021-05-12',7,NULL,NULL),(15,'hieuminpt','$2a$10$UtG3tkNYEdoANKpVxl52qu3HAOuZpldemQ8sWoTfLfsjdhTqnnz8m','Bùi Minh Hiếu','0336465199','gacontapgayno2@gmail.com','Hạ Hòa',0,NULL,0,'2021-05-27','d6600a63-b168-4cfc-8b00-1d21a7a4ff1b'),(16,'thainhtt','$2a$10$Q3KSOu8TnBuQXfVamPeHwOgtpDbUbIEfnWqHdu91aBzG3ZLsZ0f2C','Bùi Minh Hiếu','0336465199','gacontapgaynosd1@gmail.com','Hạ Hòa',0,NULL,0,'2022-10-23','4728ab7d-61d5-4137-b852-4be0d2e2549d'),(17,'minhhieupt','$2a$10$dTpQXZFkZ5aQF3qb5iWz2OU3RzBZpvd7UsnsyS1IK4qKBPk8BUvTu','Bùi Minh Hiếu','0336465199','gacontapgayno162@gmail.com','Hạ Hòa',1,'2022-11-09',2,'2022-11-09','785326d0-b49a-4f7f-a467-dd1e679d5eff'),(18,'demors','$2a$10$TTPKl5TpENmS6P8APFYy.e.CJIMJgVnekBf.m.nix7H7QMGoc5A96','demo rs','0336465199','buiminhhieu2017603559@gmail.com','kim chung dong anh ha noi',1,NULL,0,'2024-11-20','');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `userrole`
--

DROP TABLE IF EXISTS `userrole`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `userrole` (
  `userid` int NOT NULL,
  `roleid` int NOT NULL,
  KEY `role_fk_idx` (`roleid`),
  KEY `fk2_idx` (`userid`),
  CONSTRAINT `fk1` FOREIGN KEY (`roleid`) REFERENCES `role` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk2` FOREIGN KEY (`userid`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userrole`
--

LOCK TABLES `userrole` WRITE;
/*!40000 ALTER TABLE `userrole` DISABLE KEYS */;
INSERT INTO `userrole` VALUES (1,1),(17,2),(18,2);
/*!40000 ALTER TABLE `userrole` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wishlist`
--

DROP TABLE IF EXISTS `wishlist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `wishlist` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `productid` int unsigned NOT NULL,
  `amount` int NOT NULL,
  `userid` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_user_id_idx` (`userid`),
  KEY `fk2_idx` (`productid`),
  CONSTRAINT `fkwl1` FOREIGN KEY (`productid`) REFERENCES `product` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fkwl2` FOREIGN KEY (`userid`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wishlist`
--

LOCK TABLES `wishlist` WRITE;
/*!40000 ALTER TABLE `wishlist` DISABLE KEYS */;
INSERT INTO `wishlist` VALUES (4,21,1,9);
/*!40000 ALTER TABLE `wishlist` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-11-20 18:28:50
