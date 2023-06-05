-- MySQL dump 10.13  Distrib 8.0.33, for Win64 (x86_64)
--
-- Host: localhost    Database: iot-cloud
-- ------------------------------------------------------
-- Server version	8.0.33

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `device_info`
--

DROP TABLE IF EXISTS `device_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `device_info` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `rel_device_type_id` bigint NOT NULL COMMENT '设备类型表',
  `rel_user_info_id` bigint NOT NULL COMMENT '用户信息表',
  `name` varchar(20) NOT NULL COMMENT '设备名称',
  `code` varchar(20) NOT NULL COMMENT '设备标识符',
  `pwd` varchar(20) NOT NULL COMMENT '设备连接密码',
  `online_status` int NOT NULL DEFAULT '1' COMMENT '在线状态 1在线 2离线',
  `alarm_status` int NOT NULL DEFAULT '1' COMMENT '告警状态 1正常 2普通告警 3重要告警 3紧急告警',
  `active_status` int NOT NULL DEFAULT '0' COMMENT '活跃状态 0从未活跃过 1五分钟内 2一小时内 3一天内 4一天以上',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='设备信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `device_info`
--

LOCK TABLES `device_info` WRITE;
/*!40000 ALTER TABLE `device_info` DISABLE KEYS */;
INSERT INTO `device_info` VALUES (1,1,1,'1','1','1',0,1,0),(2,7,5,'a','A8cMLum','L6uD9h',1,2,0),(3,7,5,'b','63rmv5QKw','ifwAs5',2,2,1),(4,7,5,'d','TEmyXLpd','RVVikRnMw',1,2,2),(5,7,5,'d','JRQZT8Eh','Hd6BL9nk',2,2,3),(6,7,5,'fd','R433WJ9In','pzHOSnKdt',0,2,4),(7,7,5,'fsf','fKLVh5J','AXFQrM',0,2,0),(8,7,5,'1','JZQEMp6d','qYZepsz',0,2,0),(9,7,5,'sfs','RvEGGe','L9QpYwy',0,2,0),(10,7,5,'fsf方式','IA4ri4','L9MHkHhP',0,2,0),(11,18,5,'发生发射点','0Efi6i','cN3d3sJwX',0,2,0),(12,18,5,'fsdfs','4YlFO9be','3W2bKR',0,2,0),(13,18,5,'dfs','WgqbNDM','d4tnDnOS',0,4,0),(14,18,5,'fsdf','MTlWEIg','ppPcNv',0,3,0),(15,18,5,'fs','TQdGwQi','vi0v9H1z',0,4,0),(16,18,5,'fs','DPAfDDXq','hoWhCATXK',0,3,0),(17,18,5,'fs','D9sctHBJ','4vMS0BK',0,2,1),(18,18,5,'fs','XoVnVQ2','YXOsHbV',0,1,2),(19,18,5,'fs','ZHP07YI','1SOt6cv',0,3,0);
/*!40000 ALTER TABLE `device_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `device_type`
--

DROP TABLE IF EXISTS `device_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `device_type` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '名称',
  `type` int NOT NULL DEFAULT '1' COMMENT '类型 1直连设备 2网关 3网关子设备',
  `communication_type` int NOT NULL DEFAULT '1' COMMENT '通信方式 101WIFI 102以太网 103蜂窝网络2G 104蜂窝网络3G 105蜂窝网络4G 106蜂窝网络5G 107NB-IOT 199其他 201WIFI 202以太网 203蜂窝网络2G 204蜂窝网络3G 205蜂窝网络4G 206蜂窝网络5G 207NB-IOT 299其他 301WIFI 302以太网 303RS485 304RS232 305RS422 306LoRa 307Zigbee 308蓝牙/BLE 399其他',
  `protocol_type` int NOT NULL DEFAULT '1' COMMENT '协议类型 1MQTT 2TCP 3HTTP',
  `protocol_format` int NOT NULL DEFAULT '1' COMMENT '协议格式 101JSON 201ModbusRTU 202二进制 203普通文本 301JSON 302普通文本',
  `rel_user_info_id` bigint NOT NULL COMMENT '用户信息表',
  `bus_time_value` int DEFAULT NULL COMMENT 'bus轮询时间 当protocol_type=2,protocol_format=201有效',
  `bus_time_unit` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'bus轮询时间单位 s秒 m分钟 h小时',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='设备类型表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `device_type`
--

LOCK TABLES `device_type` WRITE;
/*!40000 ALTER TABLE `device_type` DISABLE KEYS */;
INSERT INTO `device_type` VALUES (17,'aa',1,101,1,101,5,0,'\0'),(18,'bb',1,101,2,201,5,10,'s'),(19,'cc',1,101,1,101,5,0,NULL),(20,'dd',2,206,2,201,5,20,'s'),(21,'ee',2,206,2,201,5,20,'s'),(22,'gg',2,206,2,201,5,10,'s'),(23,'hh',2,205,2,201,5,20,'s');
/*!40000 ALTER TABLE `device_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `device_type_attribute`
--

DROP TABLE IF EXISTS `device_type_attribute`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `device_type_attribute` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `rel_device_type_id` bigint NOT NULL COMMENT '设备类型表',
  `name` varchar(20) NOT NULL COMMENT '属性名称',
  `code` varchar(20) NOT NULL COMMENT '属性标识符',
  `type` int NOT NULL DEFAULT '1' COMMENT '类型 1设备上报 2云端下发 3设备云端共享 4云端私有',
  `data_type` int NOT NULL DEFAULT '1' COMMENT '数据类型 1数值 2开关 3文本',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='设备类型属性表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `device_type_attribute`
--

LOCK TABLES `device_type_attribute` WRITE;
/*!40000 ALTER TABLE `device_type_attribute` DISABLE KEYS */;
INSERT INTO `device_type_attribute` VALUES (1,18,'a','a',1,1),(2,18,'b','b',1,1),(3,18,'c','c',1,1),(4,18,'d','d',1,1),(5,18,'e','e',1,1),(6,18,'f','f',1,1),(7,18,'g','g',1,1),(8,18,'h','h',1,1);
/*!40000 ALTER TABLE `device_type_attribute` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `device_type_attribute_modbus`
--

DROP TABLE IF EXISTS `device_type_attribute_modbus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `device_type_attribute_modbus` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `rel_device_type_id` bigint NOT NULL COMMENT '设备类型表',
  `rel_device_type_attribute_id` bigint NOT NULL COMMENT '设备类型属性表',
  `slave_address` int NOT NULL DEFAULT '1' COMMENT '从机地址',
  `register_address` int NOT NULL DEFAULT '1' COMMENT '寄存器地址',
  `read_write_type` int NOT NULL DEFAULT '1' COMMENT '读写类型 1只读 2读写',
  `data_type` int NOT NULL DEFAULT '1' COMMENT '数据类型 0 位 1 16位无符号整数 2 16位有符号整数 3 32位有符号整数 4 32位无符号整数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='设备类型属性Modbus表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `device_type_attribute_modbus`
--

LOCK TABLES `device_type_attribute_modbus` WRITE;
/*!40000 ALTER TABLE `device_type_attribute_modbus` DISABLE KEYS */;
INSERT INTO `device_type_attribute_modbus` VALUES (1,18,1,1,1,1,1),(2,18,2,1,2,1,1),(3,18,3,1,3,1,1),(4,18,4,1,4,1,1),(5,18,5,1,5,1,1),(6,18,6,1,6,1,1),(7,18,7,1,7,1,1),(8,18,8,1,8,1,1);
/*!40000 ALTER TABLE `device_type_attribute_modbus` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `history_device_attribute`
--

DROP TABLE IF EXISTS `history_device_attribute`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `history_device_attribute` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `rel_device_info_id` bigint NOT NULL COMMENT '设备信息表',
  `rel_user_info_id` bigint NOT NULL COMMENT '用户信息表',
  `device_name` varchar(20) NOT NULL COMMENT '设备名称',
  `device_code` varchar(20) NOT NULL COMMENT '设备标识符',
  `device_type_name` varchar(20) NOT NULL COMMENT '设备类型名称',
  `device_type_attribute_name` varchar(20) NOT NULL COMMENT '设备类型属性名称',
  `device_type_attribute_code` varchar(20) NOT NULL COMMENT '设备类型属性标识符',
  `create_dt` datetime NOT NULL COMMENT '创建时间',
  `value` bigint NOT NULL COMMENT '数值',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='历史数据-设备属性';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `history_device_attribute`
--

LOCK TABLES `history_device_attribute` WRITE;
/*!40000 ALTER TABLE `history_device_attribute` DISABLE KEYS */;
/*!40000 ALTER TABLE `history_device_attribute` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `history_device_online`
--

DROP TABLE IF EXISTS `history_device_online`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `history_device_online` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `rel_device_info_id` bigint NOT NULL COMMENT '设备信息表',
  `rel_user_info_id` bigint NOT NULL COMMENT '用户信息表',
  `device_name` varchar(20) NOT NULL COMMENT '设备名称',
  `device_code` varchar(20) NOT NULL COMMENT '设备标识符',
  `create_dt` datetime NOT NULL COMMENT '创建时间',
  `status` int NOT NULL COMMENT '1上线 2下线',
  `status_reason` int NOT NULL COMMENT '1正常 21设备主动断开 22云端主动断开',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='历史数据-设备上下线';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `history_device_online`
--

LOCK TABLES `history_device_online` WRITE;
/*!40000 ALTER TABLE `history_device_online` DISABLE KEYS */;
INSERT INTO `history_device_online` VALUES (1,18,5,'fs','XoVnVQ2','2023-06-05 21:27:42',1,1),(2,18,5,'fs','XoVnVQ2','2023-06-05 21:27:48',2,1),(3,18,5,'fs','XoVnVQ2','2023-06-05 21:27:57',1,1),(4,18,5,'fs','XoVnVQ2','2023-06-05 21:28:02',2,1),(5,18,5,'fs','XoVnVQ2','2023-06-05 21:42:08',1,1),(6,18,5,'fs','XoVnVQ2','2023-06-05 21:42:16',2,1),(7,18,5,'fs','XoVnVQ2','2023-06-05 21:44:00',1,1),(8,18,5,'fs','XoVnVQ2','2023-06-05 21:44:09',2,1),(9,18,5,'fs','XoVnVQ2','2023-06-05 21:44:36',1,1),(10,18,5,'fs','XoVnVQ2','2023-06-05 21:44:56',2,1),(11,18,5,'fs','XoVnVQ2','2023-06-05 21:45:21',1,1),(12,18,5,'fs','XoVnVQ2','2023-06-05 21:46:31',2,1),(13,18,5,'fs','XoVnVQ2','2023-06-05 21:46:35',1,1),(14,18,5,'fs','XoVnVQ2','2023-06-05 21:48:26',2,1),(15,18,5,'fs','XoVnVQ2','2023-06-05 21:48:29',1,1),(16,18,5,'fs','XoVnVQ2','2023-06-05 21:48:32',2,1),(17,18,5,'fs','XoVnVQ2','2023-06-05 21:48:34',1,1),(18,18,5,'fs','XoVnVQ2','2023-06-05 21:48:35',2,1),(19,18,5,'fs','XoVnVQ2','2023-06-05 21:48:41',1,1),(20,18,5,'fs','XoVnVQ2','2023-06-05 21:48:50',2,1),(21,18,5,'fs','XoVnVQ2','2023-06-05 21:48:57',1,1),(22,18,5,'fs','XoVnVQ2','2023-06-05 21:51:53',2,1),(23,18,5,'fs','XoVnVQ2','2023-06-05 21:51:57',1,1),(24,18,5,'fs','XoVnVQ2','2023-06-05 21:51:59',2,1),(25,18,5,'fs','XoVnVQ2','2023-06-05 21:52:00',1,1),(26,18,5,'fs','XoVnVQ2','2023-06-05 21:52:02',2,1),(27,18,5,'fs','XoVnVQ2','2023-06-05 21:52:13',1,1),(28,18,5,'fs','XoVnVQ2','2023-06-05 22:02:01',2,1),(29,18,5,'fs','XoVnVQ2','2023-06-05 22:05:05',1,1),(30,18,5,'fs','XoVnVQ2','2023-06-05 22:05:16',2,1),(31,18,5,'fs','XoVnVQ2','2023-06-05 22:05:24',1,1),(32,18,5,'fs','XoVnVQ2','2023-06-05 22:05:29',2,1),(33,18,5,'fs','XoVnVQ2','2023-06-05 22:05:58',1,1),(34,18,5,'fs','XoVnVQ2','2023-06-05 22:07:58',2,1),(35,18,5,'fs','XoVnVQ2','2023-06-05 22:09:30',1,1),(36,18,5,'fs','XoVnVQ2','2023-06-05 22:09:52',2,1),(37,18,5,'fs','XoVnVQ2','2023-06-05 22:09:55',1,1),(38,18,5,'fs','XoVnVQ2','2023-06-05 22:11:09',2,1),(39,18,5,'fs','XoVnVQ2','2023-06-05 22:11:13',1,1),(40,18,5,'fs','XoVnVQ2','2023-06-05 22:14:51',2,1),(41,18,5,'fs','XoVnVQ2','2023-06-05 22:14:54',1,1),(42,18,5,'fs','XoVnVQ2','2023-06-05 22:22:34',2,1),(43,18,5,'fs','XoVnVQ2','2023-06-05 22:22:40',1,1),(44,18,5,'fs','XoVnVQ2','2023-06-05 22:32:15',2,1);
/*!40000 ALTER TABLE `history_device_online` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_info`
--

DROP TABLE IF EXISTS `user_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_info` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `email` varchar(100) NOT NULL COMMENT '邮箱',
  `account` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '账号',
  `secret` varchar(10) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_info_email_IDX` (`email`) USING BTREE,
  UNIQUE KEY `user_info_account_uindex` (`account`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_info`
--

LOCK TABLES `user_info` WRITE;
/*!40000 ALTER TABLE `user_info` DISABLE KEYS */;
INSERT INTO `user_info` VALUES (3,'cniiot1@163.com','gb8Fs6ZI3','1'),(4,'cniiot2@163.com','aUBN9p','1'),(5,'weichuang059@163.com','USPVnS4yA','1'),(6,'weichuang0591@163.com','pjwBe84','8uCYhoOUE');
/*!40000 ALTER TABLE `user_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'iot-cloud'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-06-05 23:51:06
