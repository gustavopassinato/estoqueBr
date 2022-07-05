CREATE DATABASE  IF NOT EXISTS `estoquebr` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `estoquebr`;
-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: estoquebr
-- ------------------------------------------------------
-- Server version	5.7.35-log

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
-- Table structure for table `custeio`
--

DROP TABLE IF EXISTS `custeio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `custeio` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `nome` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `nome_UNIQUE` (`nome`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `custeio`
--

LOCK TABLES `custeio` WRITE;
/*!40000 ALTER TABLE `custeio` DISABLE KEYS */;
INSERT INTO `custeio` VALUES (2,'Funape'),(4,'Novo centro de custeio'),(3,'Rtve'),(1,'UFG');
/*!40000 ALTER TABLE `custeio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `destino`
--

DROP TABLE IF EXISTS `destino`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `destino` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `nome` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `nome_UNIQUE` (`nome`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `destino`
--

LOCK TABLES `destino` WRITE;
/*!40000 ALTER TABLE `destino` DISABLE KEYS */;
INSERT INTO `destino` VALUES (6,'Destino teste base'),(1,'Estoque interno DTEL'),(2,'Projeto Anel'),(4,'Projeto Centros de aulas'),(5,'Projeto paineis solares'),(3,'Projeto Quadra 62');
/*!40000 ALTER TABLE `destino` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `entrada`
--

DROP TABLE IF EXISTS `entrada`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `entrada` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `quantidade` int(10) unsigned NOT NULL,
  `data_registro` datetime NOT NULL,
  `id_material` bigint(20) unsigned NOT NULL,
  `id_nota` bigint(20) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `entrada_fk1_idx` (`id_material`),
  KEY `entrada_fk0_idx` (`id_nota`),
  CONSTRAINT `entrada_fk0` FOREIGN KEY (`id_nota`) REFERENCES `nota_fiscal` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `entrada_fk1` FOREIGN KEY (`id_material`) REFERENCES `material` (`codigo`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `entrada`
--

LOCK TABLES `entrada` WRITE;
/*!40000 ALTER TABLE `entrada` DISABLE KEYS */;
INSERT INTO `entrada` VALUES (29,40,'2022-06-27 20:10:26',4,5),(30,50,'2022-06-27 20:11:00',4,4),(31,20,'2022-06-28 09:09:26',7,4),(32,20,'2022-06-28 09:14:49',7,3),(33,15,'2022-06-28 09:17:33',7,7);
/*!40000 ALTER TABLE `entrada` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER Trg_entrada_material AFTER INSERT
ON `estoqueBr`.`entrada`
FOR EACH ROW 
BEGIN 
	SET @DESTINO_ORIGEM_ID = (SELECT `estoqueBr`.`nota_fiscal`.`id_destino` 
            FROM `estoqueBr`.`nota_fiscal`
            WHERE `estoqueBr`.`nota_fiscal`.`id` = NEW.`id_nota`);
	IF (@DESTINO_ORIGEM_ID IS NOT NULL) THEN 
		SET @MATERIAL_ID = (SELECT `estoqueBr`.`estoque`.`id` 
		FROM `estoqueBr`.`estoque` 
		WHERE `estoqueBr`.`estoque`.`id_material` = NEW.`id_material`
			AND `estoqueBr`.`estoque`.`id_destino` = @DESTINO_ORIGEM_ID );
		IF (@MATERIAL_ID IS NOT NULL) THEN
			UPDATE `estoqueBr`.`estoque` 
            SET `estoqueBr`.`estoque`.`quantidade` = `estoqueBr`.`estoque`.`quantidade`+ NEW.`quantidade`
            WHERE `estoqueBr`.`estoque`.`id`= @MATERIAL_ID;
		ELSE
			INSERT INTO `estoqueBr`.`estoque` (`estoqueBr`.`estoque`.`id_material`, `estoqueBr`.`estoque`.`id_destino`, `estoqueBr`.`estoque`.`desvio`, `estoqueBr`.`estoque`.`quantidade`)
			VALUES (NEW.`id_material`, @DESTINO_ORIGEM_ID, 0, NEW.`quantidade`);
		END IF;
	END IF;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `estoque`
--

DROP TABLE IF EXISTS `estoque`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `estoque` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `id_material` bigint(20) unsigned NOT NULL,
  `id_destino` bigint(20) unsigned NOT NULL,
  `desvio` bit(1) NOT NULL,
  `id_destino_original` bigint(20) unsigned DEFAULT NULL,
  `quantidade` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `estoque_fk0_idx` (`id_material`),
  KEY `estoque_fk1_idx` (`id_destino`),
  KEY `estoque_fk2_idx` (`id_destino_original`),
  CONSTRAINT `estoque_fk0` FOREIGN KEY (`id_material`) REFERENCES `material` (`codigo`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `estoque_fk1` FOREIGN KEY (`id_destino`) REFERENCES `destino` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `estoque_fk2` FOREIGN KEY (`id_destino_original`) REFERENCES `destino` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estoque`
--

LOCK TABLES `estoque` WRITE;
/*!40000 ALTER TABLE `estoque` DISABLE KEYS */;
INSERT INTO `estoque` VALUES (10,4,2,_binary '\0',NULL,30),(11,4,3,_binary '',NULL,-30),(12,7,2,_binary '\0',NULL,10),(13,7,3,_binary '',NULL,25);
/*!40000 ALTER TABLE `estoque` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fabricante`
--

DROP TABLE IF EXISTS `fabricante`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `fabricante` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `nome` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `nome_UNIQUE` (`nome`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fabricante`
--

LOCK TABLES `fabricante` WRITE;
/*!40000 ALTER TABLE `fabricante` DISABLE KEYS */;
INSERT INTO `fabricante` VALUES (3,'AXXON'),(1,'Control ID'),(5,'Furokawa'),(2,'Intelbras'),(4,'komp');
/*!40000 ALTER TABLE `fabricante` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fornecedor`
--

DROP TABLE IF EXISTS `fornecedor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `fornecedor` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) NOT NULL,
  `cnpj` char(14) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `cnpj_UNIQUE` (`cnpj`),
  UNIQUE KEY `nome_UNIQUE` (`nome`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fornecedor`
--

LOCK TABLES `fornecedor` WRITE;
/*!40000 ALTER TABLE `fornecedor` DISABLE KEYS */;
INSERT INTO `fornecedor` VALUES (1,'Qualiti','42617914561134'),(2,'Avanti','12617932461134'),(3,'Rissati','12617932461896'),(4,'Fornecedor de teste','13846974671674');
/*!40000 ALTER TABLE `fornecedor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `material`
--

DROP TABLE IF EXISTS `material`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `material` (
  `codigo` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `nome` varchar(60) NOT NULL,
  `modelo` varchar(45) NOT NULL,
  `descricao` text,
  `id_fabricante` bigint(20) unsigned NOT NULL,
  `id_unid_med` bigint(20) unsigned NOT NULL,
  PRIMARY KEY (`codigo`),
  KEY `material_fk0_idx` (`id_fabricante`),
  KEY `material_fk2_idx` (`id_unid_med`),
  CONSTRAINT `material_fk0` FOREIGN KEY (`id_fabricante`) REFERENCES `fabricante` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `material_fk2` FOREIGN KEY (`id_unid_med`) REFERENCES `unidade_medida` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `material`
--

LOCK TABLES `material` WRITE;
/*!40000 ALTER TABLE `material` DISABLE KEYS */;
INSERT INTO `material` VALUES (1,'Controlador de acesso','Idflex',NULL,1,3),(2,'Controlador de acesso','IdBox',NULL,1,3),(3,'Catraca','IdBlock',NULL,1,3),(4,'Intercomunidador IP','Komp 101',NULL,4,3),(5,'Cabo UTP','Gigalan cat 6',NULL,5,1),(6,'Fonte alimentação','12 V 2 A',NULL,2,3),(7,'Switch 8 portas','sf800 q+',NULL,2,3);
/*!40000 ALTER TABLE `material` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nota_fiscal`
--

DROP TABLE IF EXISTS `nota_fiscal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `nota_fiscal` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `numero` varchar(9) NOT NULL,
  `id_destino` bigint(20) unsigned NOT NULL,
  `id_fornecedor` bigint(20) unsigned NOT NULL,
  `id_custeio` bigint(20) unsigned NOT NULL,
  `serie` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `nota_fiscal_const` (`numero`,`id_fornecedor`,`serie`),
  KEY `nota_fiscal_fk0_idx` (`id_destino`),
  KEY `nota_fiscal_fk1_idx` (`id_fornecedor`),
  KEY `nota_fiscal_fk2_idx` (`id_custeio`),
  CONSTRAINT `nota_fiscal_fk0` FOREIGN KEY (`id_destino`) REFERENCES `destino` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `nota_fiscal_fk1` FOREIGN KEY (`id_fornecedor`) REFERENCES `fornecedor` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `nota_fiscal_fk2` FOREIGN KEY (`id_custeio`) REFERENCES `custeio` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nota_fiscal`
--

LOCK TABLES `nota_fiscal` WRITE;
/*!40000 ALTER TABLE `nota_fiscal` DISABLE KEYS */;
INSERT INTO `nota_fiscal` VALUES (1,'7946178',1,1,1,0),(2,'7944379',2,2,2,0),(3,'0643413',3,3,3,0),(4,'1576948',2,3,1,0),(5,'1247937',2,1,1,0),(6,'1767943',2,1,2,0),(7,'1387649',3,3,1,0),(9,'123451',4,3,3,1),(10,'1991241',1,2,4,11),(11,'1234565',1,2,4,2),(12,'12345',5,1,4,1),(13,'19923424',2,2,4,2);
/*!40000 ALTER TABLE `nota_fiscal` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ordem_servico`
--

DROP TABLE IF EXISTS `ordem_servico`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ordem_servico` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `numero` varchar(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `numero_UNIQUE` (`numero`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ordem_servico`
--

LOCK TABLES `ordem_servico` WRITE;
/*!40000 ALTER TABLE `ordem_servico` DISABLE KEYS */;
INSERT INTO `ordem_servico` VALUES (3,'3913/2022'),(1,'4671/2022'),(2,'8913/2022');
/*!40000 ALTER TABLE `ordem_servico` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `saida`
--

DROP TABLE IF EXISTS `saida`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `saida` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `quantidade` int(10) NOT NULL,
  `descricao` text,
  `data_registro` datetime NOT NULL,
  `id_material` bigint(20) unsigned NOT NULL,
  `id_ordem_servico` bigint(20) unsigned NOT NULL,
  `id_destino` bigint(20) unsigned NOT NULL,
  `id_nota` bigint(20) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `saida_fk0_idx` (`id_ordem_servico`),
  KEY `saida_fk1_idx` (`id_material`),
  KEY `saida_fk3_idx` (`id_destino`),
  KEY `saida_fk2_idx` (`id_nota`),
  CONSTRAINT `saida_fk0` FOREIGN KEY (`id_ordem_servico`) REFERENCES `ordem_servico` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `saida_fk1` FOREIGN KEY (`id_material`) REFERENCES `material` (`codigo`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `saida_fk2` FOREIGN KEY (`id_nota`) REFERENCES `nota_fiscal` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `saida_fk3` FOREIGN KEY (`id_destino`) REFERENCES `destino` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `saida`
--

LOCK TABLES `saida` WRITE;
/*!40000 ALTER TABLE `saida` DISABLE KEYS */;
INSERT INTO `saida` VALUES (5,30,NULL,'2022-06-27 20:11:42',4,3,2,4),(6,30,NULL,'2022-06-27 20:15:49',4,1,3,4),(7,10,NULL,'2022-06-28 09:11:54',7,3,3,4);
/*!40000 ALTER TABLE `saida` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER Trg_saida_material AFTER INSERT 
ON `estoqueBr`.`saida`
FOR EACH ROW 
BEGIN
	SET @DESTINO_NOTA_ID = (SELECT `estoqueBr`.`nota_fiscal`.`id_destino` 
		FROM `estoqueBr`.`nota_fiscal`
		WHERE `estoqueBr`.`nota_fiscal`.`id` = NEW.`id_nota`);
    IF (@DESTINO_NOTA_ID != NEW.`id_destino`) THEN
		SET @EH_DESVIO = 1;
        INSERT INTO `estoqueBr`.`estoque` (`estoqueBr`.`estoque`.`id_material`, `estoqueBr`.`estoque`.`id_destino`, `estoqueBr`.`estoque`.`desvio`, `estoqueBr`.`estoque`.`quantidade`)
        VALUES (NEW.`id_material`, NEW.`id_destino`, @EH_DESVIO , -1*NEW.`quantidade`);
    END IF;
	
	SET @MATERIAL_ID = (SELECT `estoqueBr`.`estoque`.`id`  
		FROM `estoqueBr`.`estoque` 
		WHERE `estoqueBr`.`estoque`.`id_material` = NEW.`id_material`
			AND `estoqueBr`.`estoque`.`id_destino` = @DESTINO_NOTA_ID);
		 
	UPDATE `estoqueBr`.`estoque` 
	SET `estoqueBr`.`estoque`.`quantidade` = `estoqueBr`.`estoque`.`quantidade`- NEW.`quantidade`
    WHERE `estoqueBr`.`estoque`.`id`= @MATERIAL_ID;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `unidade_medida`
--

DROP TABLE IF EXISTS `unidade_medida`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `unidade_medida` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `nome` varchar(30) NOT NULL,
  `abreviacao` varchar(5) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `unidade_medida`
--

LOCK TABLES `unidade_medida` WRITE;
/*!40000 ALTER TABLE `unidade_medida` DISABLE KEYS */;
INSERT INTO `unidade_medida` VALUES (1,'metro','m'),(2,'caixa','cx'),(3,'unidade','un');
/*!40000 ALTER TABLE `unidade_medida` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'estoquebr'
--

--
-- Dumping routines for database 'estoquebr'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-07-05 19:21:34
