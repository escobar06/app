-- MySQL dump 10.13  Distrib 8.0.17, for Win64 (x86_64)
--
-- Host: localhost    Database: gdcprod
-- ------------------------------------------------------
-- Server version	8.0.17

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
-- Table structure for table `anio`
--

DROP TABLE IF EXISTS `anio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `anio` (
  `idanio` int(11) NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idanio`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `area`
--

DROP TABLE IF EXISTS `area`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `area` (
  `idarea` int(11) NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idarea`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `articulo`
--

DROP TABLE IF EXISTS `articulo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `articulo` (
  `idarticulo` int(11) NOT NULL AUTO_INCREMENT,
  `idcapitulo` int(11) DEFAULT NULL,
  `descripcion` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idarticulo`),
  KEY `fk_capitulo_articulo_idx` (`idcapitulo`),
  CONSTRAINT `fk_capitulo_articulo` FOREIGN KEY (`idcapitulo`) REFERENCES `capitulo` (`idcapitulo`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `capitulo`
--

DROP TABLE IF EXISTS `capitulo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `capitulo` (
  `idcapitulo` int(11) NOT NULL AUTO_INCREMENT,
  `idreglamento` int(11) DEFAULT NULL,
  `descripcion` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idcapitulo`),
  KEY `fk_reglamento_capitulo_idx` (`idreglamento`),
  CONSTRAINT `fk_reglamento_capitulo` FOREIGN KEY (`idreglamento`) REFERENCES `reglamento` (`idreglamento`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `cliente`
--

DROP TABLE IF EXISTS `cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cliente` (
  `id_cliente` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) DEFAULT NULL,
  `apellido` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `telefono` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_cliente`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `control`
--

DROP TABLE IF EXISTS `control`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `control` (
  `idcontrol` int(11) NOT NULL AUTO_INCREMENT,
  `idarea` int(11) DEFAULT NULL,
  `idarticulo` int(11) DEFAULT NULL,
  `idnivel` int(11) DEFAULT NULL,
  `descripcion` varchar(45) DEFAULT NULL,
  `implementa` int(11) DEFAULT NULL,
  `estado` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idcontrol`),
  KEY `fk_area_control_idx` (`idarticulo`),
  KEY `fk_articulo_control_idx` (`idarticulo`),
  KEY `fk_nivel_control_idx` (`idnivel`),
  KEY `fk_area_control` (`idarea`),
  CONSTRAINT `fk_area_control` FOREIGN KEY (`idarea`) REFERENCES `area` (`idarea`),
  CONSTRAINT `fk_articulo_control` FOREIGN KEY (`idarticulo`) REFERENCES `articulo` (`idarticulo`),
  CONSTRAINT `fk_nivel_control` FOREIGN KEY (`idnivel`) REFERENCES `nivel` (`idnivel`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `evaluacion`
--

DROP TABLE IF EXISTS `evaluacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `evaluacion` (
  `idevaluacion` int(11) NOT NULL AUTO_INCREMENT,
  `idcontrol` int(11) DEFAULT NULL,
  `idproyecto` int(11) DEFAULT NULL,
  `idperiodo` int(11) DEFAULT NULL,
  `idimpacto` int(11) DEFAULT NULL,
  `idsolucion` int(11) DEFAULT NULL,
  `hallazgo` varchar(45) DEFAULT NULL,
  `recomendacion` varchar(45) DEFAULT NULL,
  `usuario_evaluacion` varchar(45) DEFAULT NULL,
  `fecha_evaluacion` date DEFAULT NULL,
  PRIMARY KEY (`idevaluacion`),
  KEY `fk_control_evaluacion_idx` (`idcontrol`),
  KEY `fk_proyecto_evaluacion_idx` (`idproyecto`),
  KEY `fk_periodo_evaluacion_idx` (`idperiodo`),
  KEY `fk_impacto_evaluacion_idx` (`idimpacto`),
  KEY `fk_solucion_evaluacion_idx` (`idsolucion`),
  CONSTRAINT `fk_control_evaluacion` FOREIGN KEY (`idcontrol`) REFERENCES `control` (`idcontrol`),
  CONSTRAINT `fk_impacto_evaluacion` FOREIGN KEY (`idimpacto`) REFERENCES `impacto` (`idimpacto`),
  CONSTRAINT `fk_periodo_evaluacion` FOREIGN KEY (`idperiodo`) REFERENCES `periodo` (`idperiodo`),
  CONSTRAINT `fk_proyecto_evaluacion` FOREIGN KEY (`idproyecto`) REFERENCES `proyecto` (`idproyecto`),
  CONSTRAINT `fk_solucion_evaluacion` FOREIGN KEY (`idsolucion`) REFERENCES `solucion` (`idsolucion`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `impacto`
--

DROP TABLE IF EXISTS `impacto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `impacto` (
  `idimpacto` int(11) NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idimpacto`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `nivel`
--

DROP TABLE IF EXISTS `nivel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `nivel` (
  `idnivel` int(11) NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idnivel`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `periodo`
--

DROP TABLE IF EXISTS `periodo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `periodo` (
  `idperiodo` int(11) NOT NULL AUTO_INCREMENT,
  `idanio` int(11) DEFAULT NULL,
  `descripcion` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idperiodo`),
  KEY `fk_anio_periodo_idx` (`idanio`),
  CONSTRAINT `fk_anio_periodo` FOREIGN KEY (`idanio`) REFERENCES `anio` (`idanio`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `proyecto`
--

DROP TABLE IF EXISTS `proyecto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `proyecto` (
  `idproyecto` int(11) NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(45) DEFAULT NULL,
  `presupuesto` int(11) DEFAULT NULL,
  `tiempo` int(11) DEFAULT NULL,
  PRIMARY KEY (`idproyecto`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `reglamento`
--

DROP TABLE IF EXISTS `reglamento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reglamento` (
  `idreglamento` int(11) NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idreglamento`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `solucion`
--

DROP TABLE IF EXISTS `solucion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `solucion` (
  `idsolucion` int(11) NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idsolucion`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-09-03 21:49:08
