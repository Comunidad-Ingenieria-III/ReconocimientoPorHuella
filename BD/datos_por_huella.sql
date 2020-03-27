-- MySQL dump 10.13  Distrib 5.7.23, for Win64 (x86_64)
--
-- Host: localhost    Database: datos_por_huella
-- ------------------------------------------------------
-- Server version	5.7.23

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
-- Current Database: `datos_por_huella`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `datos_por_huella` /*!40100 DEFAULT CHARACTER SET utf32 COLLATE utf32_spanish_ci */;

USE `datos_por_huella`;

--
-- Table structure for table `datos_persona`
--

DROP TABLE IF EXISTS `datos_persona`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `datos_persona` (
  `idpersona` int(11) NOT NULL,
  `primerNombre` varchar(45) CHARACTER SET utf32 COLLATE utf32_spanish2_ci NOT NULL,
  `segundoNombre` varchar(45) CHARACTER SET utf32 COLLATE utf32_spanish2_ci DEFAULT NULL,
  `primerApellido` varchar(45) CHARACTER SET utf32 COLLATE utf32_spanish2_ci NOT NULL,
  `segundoApellido` varchar(45) CHARACTER SET utf32 COLLATE utf32_spanish2_ci DEFAULT NULL,
  `fechaNacimiento` varchar(45) COLLATE utf32_spanish_ci NOT NULL,
  `sexo` varchar(19) COLLATE utf32_spanish_ci NOT NULL,
  `alergicoA` varchar(255) COLLATE utf32_spanish_ci NOT NULL,
  `enfermedadSufre` varchar(255) COLLATE utf32_spanish_ci NOT NULL,
  `observaciones` varchar(255) COLLATE utf32_spanish_ci NOT NULL,
  `idTipoDocumento` int(2) NOT NULL,
  `idEps` int(10) NOT NULL,
  PRIMARY KEY (`idpersona`),
  KEY `idEps` (`idEps`),
  KEY `idTipoDocumento` (`idTipoDocumento`),
  CONSTRAINT `datos_persona_ibfk_1` FOREIGN KEY (`idEps`) REFERENCES `eps` (`idEps`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `datos_persona_ibfk_2` FOREIGN KEY (`idTipoDocumento`) REFERENCES `tipo_de_documento` (`idTipoDocumento`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf32 COLLATE=utf32_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `datos_persona`
--

LOCK TABLES `datos_persona` WRITE;
/*!40000 ALTER TABLE `datos_persona` DISABLE KEYS */;
INSERT INTO `datos_persona` VALUES (234,'wwwwww','www','www','www','2020-03-25','Femenino','wwww','www','wwww',2,2),(1111,'Emiliano','','salazar','','2019-08-04','Masculino','','','',1,1),(22222,'Julian','','salazar','','1996-02-11','Masculino','No		','no	','soy Musico',1,1),(15327400,'Nelson','Giovanni','Salazar','Roldan','1973-08-26','Masculino','no','no','El Creador de Este Programa',1,1);
/*!40000 ALTER TABLE `datos_persona` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `eps`
--

DROP TABLE IF EXISTS `eps`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `eps` (
  `idEps` int(10) NOT NULL,
  `nombreEps` varchar(45) COLLATE utf32_spanish_ci NOT NULL,
  `direccionEps` varchar(45) COLLATE utf32_spanish_ci NOT NULL,
  `telEps` int(10) NOT NULL,
  PRIMARY KEY (`idEps`)
) ENGINE=InnoDB DEFAULT CHARSET=utf32 COLLATE=utf32_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `eps`
--

LOCK TABLES `eps` WRITE;
/*!40000 ALTER TABLE `eps` DISABLE KEYS */;
INSERT INTO `eps` VALUES (1,'Sura','cordoba',123),(2,'coomeva','pilarica',2345);
/*!40000 ALTER TABLE `eps` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `familiar_paciente`
--

DROP TABLE IF EXISTS `familiar_paciente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `familiar_paciente` (
  `idFamiliar` varchar(15) COLLATE utf32_spanish_ci NOT NULL,
  `primerNombreFamiliar` varchar(45) COLLATE utf32_spanish_ci NOT NULL,
  `segundoNombreFamiliar` varchar(45) COLLATE utf32_spanish_ci DEFAULT NULL,
  `primerApellidoFamiliar` varchar(45) COLLATE utf32_spanish_ci NOT NULL,
  `segundoApellidoFamiliar` varchar(45) COLLATE utf32_spanish_ci DEFAULT NULL,
  `direccionFamiliar` varchar(35) COLLATE utf32_spanish_ci DEFAULT NULL,
  `telefonoFamiliar` int(15) NOT NULL,
  PRIMARY KEY (`idFamiliar`)
) ENGINE=MyISAM DEFAULT CHARSET=utf32 COLLATE=utf32_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `familiar_paciente`
--

LOCK TABLES `familiar_paciente` WRITE;
/*!40000 ALTER TABLE `familiar_paciente` DISABLE KEYS */;
/*!40000 ALTER TABLE `familiar_paciente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `persona_familiar`
--

DROP TABLE IF EXISTS `persona_familiar`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `persona_familiar` (
  `idPersona` varchar(15) COLLATE utf32_spanish_ci NOT NULL,
  `idFamiliar` varchar(15) COLLATE utf32_spanish_ci NOT NULL,
  `fechaIngreso` date NOT NULL,
  KEY `idPersona` (`idPersona`),
  KEY `idFamiliar` (`idFamiliar`)
) ENGINE=MyISAM DEFAULT CHARSET=utf32 COLLATE=utf32_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `persona_familiar`
--

LOCK TABLES `persona_familiar` WRITE;
/*!40000 ALTER TABLE `persona_familiar` DISABLE KEYS */;
/*!40000 ALTER TABLE `persona_familiar` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipo_de_documento`
--

DROP TABLE IF EXISTS `tipo_de_documento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tipo_de_documento` (
  `idTipoDocumento` int(2) NOT NULL,
  `nombreTipoDocumento` varchar(45) COLLATE utf32_spanish_ci NOT NULL,
  PRIMARY KEY (`idTipoDocumento`)
) ENGINE=InnoDB DEFAULT CHARSET=utf32 COLLATE=utf32_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipo_de_documento`
--

LOCK TABLES `tipo_de_documento` WRITE;
/*!40000 ALTER TABLE `tipo_de_documento` DISABLE KEYS */;
INSERT INTO `tipo_de_documento` VALUES (1,'Cedula'),(2,'Tarjeta de Identidad');
/*!40000 ALTER TABLE `tipo_de_documento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuario` (
  `idUsuario` int(11) NOT NULL,
  `primerNombre` varchar(45) COLLATE utf32_spanish_ci NOT NULL,
  `segundoNombre` varchar(45) COLLATE utf32_spanish_ci NOT NULL,
  `primerApellido` varchar(45) COLLATE utf32_spanish_ci NOT NULL,
  `segundoApellido` varchar(45) COLLATE utf32_spanish_ci NOT NULL,
  `username` varchar(45) COLLATE utf32_spanish_ci NOT NULL,
  `contrasena` longblob,
  PRIMARY KEY (`idUsuario`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=utf32 COLLATE=utf32_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (11111,'','','','','nelson',_binary '1234');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-03-25  6:16:37
