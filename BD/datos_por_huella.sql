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
-- Table structure for table `cargo`
--

DROP TABLE IF EXISTS `cargo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cargo` (
  `idCargo` varchar(15) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  PRIMARY KEY (`idCargo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cargo`
--

LOCK TABLES `cargo` WRITE;
/*!40000 ALTER TABLE `cargo` DISABLE KEYS */;
INSERT INTO `cargo` VALUES ('1','Aph'),('2','Medico'),('3','Camillero'),('4','conductot'),('654','Enfermero');
/*!40000 ALTER TABLE `cargo` ENABLE KEYS */;
UNLOCK TABLES;

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
  `direccion` varchar(50) COLLATE utf32_spanish_ci NOT NULL,
  `sexo` varchar(19) COLLATE utf32_spanish_ci NOT NULL,
  `alergicoA` varchar(255) COLLATE utf32_spanish_ci NOT NULL,
  `enfermedadSufre` varchar(255) COLLATE utf32_spanish_ci NOT NULL,
  `observaciones` varchar(255) COLLATE utf32_spanish_ci NOT NULL,
  `huella` blob NOT NULL,
  `huella1` tinyblob NOT NULL,
  `idTipoDocumento` varchar(5) COLLATE utf32_spanish_ci NOT NULL,
  `idEps` int(10) NOT NULL,
  PRIMARY KEY (`idpersona`),
  KEY `idEps` (`idEps`),
  KEY `idTipoDocumento` (`idTipoDocumento`),
  CONSTRAINT `datos_persona_ibfk_1` FOREIGN KEY (`idEps`) REFERENCES `eps` (`idEps`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `datos_persona_ibfk_2` FOREIGN KEY (`idTipoDocumento`) REFERENCES `tipo_documento` (`idTipoDocumento`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf32 COLLATE=utf32_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `datos_persona`
--

LOCK TABLES `datos_persona` WRITE;
/*!40000 ALTER TABLE `datos_persona` DISABLE KEYS */;
/*!40000 ALTER TABLE `datos_persona` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `documento_referencia`
--

DROP TABLE IF EXISTS `documento_referencia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `documento_referencia` (
  `idPersonaRecibe` varchar(15) NOT NULL,
  `nombre1` varchar(50) NOT NULL,
  `nombre2` varchar(50) DEFAULT NULL,
  `apellido1` varchar(50) NOT NULL,
  `apellido2` varchar(50) DEFAULT NULL,
  `fecha` date NOT NULL,
  `observaciones` varchar(200) NOT NULL,
  `idPersona` int(11) NOT NULL,
  `idcargo` varchar(15) NOT NULL,
  `idInstiRefe` varchar(15) NOT NULL,
  PRIMARY KEY (`idPersonaRecibe`),
  KEY `FK_documento_referencia_datos_persona` (`idPersona`),
  KEY `FK_documento_referencia_cargo` (`idcargo`),
  KEY `FK_documento_referencia_institucion_referencia` (`idInstiRefe`),
  CONSTRAINT `FK_documento_referencia_cargo` FOREIGN KEY (`idcargo`) REFERENCES `cargo` (`idCargo`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_documento_referencia_datos_persona` FOREIGN KEY (`idPersona`) REFERENCES `datos_persona` (`idpersona`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_documento_referencia_institucion_referencia` FOREIGN KEY (`idInstiRefe`) REFERENCES `institucion_referencia` (`idInstiRefe`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `documento_referencia`
--

LOCK TABLES `documento_referencia` WRITE;
/*!40000 ALTER TABLE `documento_referencia` DISABLE KEYS */;
/*!40000 ALTER TABLE `documento_referencia` ENABLE KEYS */;
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
INSERT INTO `eps` VALUES (2,'Coomeva','la 80',123456),(5,'Colsanitas','La 80',324567);
/*!40000 ALTER TABLE `eps` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `familiar_paciente`
--

DROP TABLE IF EXISTS `familiar_paciente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `familiar_paciente` (
  `idFamiliar` int(11) NOT NULL,
  `nombre1` varchar(45) COLLATE utf32_spanish_ci NOT NULL,
  `nombre2` varchar(45) COLLATE utf32_spanish_ci DEFAULT NULL,
  `apellido1` varchar(45) COLLATE utf32_spanish_ci NOT NULL,
  `apellido2` varchar(45) COLLATE utf32_spanish_ci DEFAULT NULL,
  `direccion` varchar(35) COLLATE utf32_spanish_ci DEFAULT NULL,
  `telefono` varchar(50) COLLATE utf32_spanish_ci NOT NULL DEFAULT '',
  PRIMARY KEY (`idFamiliar`)
) ENGINE=InnoDB DEFAULT CHARSET=utf32 COLLATE=utf32_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `familiar_paciente`
--

LOCK TABLES `familiar_paciente` WRITE;
/*!40000 ALTER TABLE `familiar_paciente` DISABLE KEYS */;
INSERT INTO `familiar_paciente` VALUES (32413,'kakfka','kakjfja','kakfaña','akkñjkd','kakdañkfj','23445');
/*!40000 ALTER TABLE `familiar_paciente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `institucion_academica`
--

DROP TABLE IF EXISTS `institucion_academica`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `institucion_academica` (
  `idInstitucion` varchar(5) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `direccion` varchar(50) NOT NULL,
  `telefono` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`idInstitucion`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `institucion_academica`
--

LOCK TABLES `institucion_academica` WRITE;
/*!40000 ALTER TABLE `institucion_academica` DISABLE KEYS */;
INSERT INTO `institucion_academica` VALUES ('2','UNIREMINGTO','PARQUE BERRIO','3333333'),('3','Luis Amigo','Calle Colombia','22222'),('A234','San Luis ','Los LLanos','6666666');
/*!40000 ALTER TABLE `institucion_academica` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `institucion_referencia`
--

DROP TABLE IF EXISTS `institucion_referencia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `institucion_referencia` (
  `idInstiRefe` varchar(15) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `direccion` varchar(50) NOT NULL,
  `telefono` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`idInstiRefe`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `institucion_referencia`
--

LOCK TABLES `institucion_referencia` WRITE;
/*!40000 ALTER TABLE `institucion_referencia` DISABLE KEYS */;
INSERT INTO `institucion_referencia` VALUES ('1','Pablo Tobon','cordoba','56566');
/*!40000 ALTER TABLE `institucion_referencia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `medicamento`
--

DROP TABLE IF EXISTS `medicamento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `medicamento` (
  `idMedicamento` varchar(15) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  PRIMARY KEY (`idMedicamento`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `medicamento`
--

LOCK TABLES `medicamento` WRITE;
/*!40000 ALTER TABLE `medicamento` DISABLE KEYS */;
INSERT INTO `medicamento` VALUES ('3','ranitidina'),('ss','ss');
/*!40000 ALTER TABLE `medicamento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `perfil`
--

DROP TABLE IF EXISTS `perfil`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `perfil` (
  `idperfil` varchar(5) COLLATE utf32_spanish_ci NOT NULL,
  `nombre` varchar(50) COLLATE utf32_spanish_ci NOT NULL,
  `estado` tinyint(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`idperfil`)
) ENGINE=InnoDB DEFAULT CHARSET=utf32 COLLATE=utf32_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `perfil`
--

LOCK TABLES `perfil` WRITE;
/*!40000 ALTER TABLE `perfil` DISABLE KEYS */;
/*!40000 ALTER TABLE `perfil` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `persona_familiar`
--

DROP TABLE IF EXISTS `persona_familiar`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `persona_familiar` (
  `idPersona` int(11) NOT NULL,
  `idFamiliar` int(11) NOT NULL,
  `fechaIngreso` date NOT NULL,
  KEY `idPersona` (`idPersona`),
  KEY `idFamiliar` (`idFamiliar`),
  CONSTRAINT `persona_familiar_ibfk_1` FOREIGN KEY (`idPersona`) REFERENCES `datos_persona` (`idpersona`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `persona_familiar_ibfk_2` FOREIGN KEY (`idFamiliar`) REFERENCES `familiar_paciente` (`idFamiliar`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf32 COLLATE=utf32_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `persona_familiar`
--

LOCK TABLES `persona_familiar` WRITE;
/*!40000 ALTER TABLE `persona_familiar` DISABLE KEYS */;
/*!40000 ALTER TABLE `persona_familiar` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `personal_salud`
--

DROP TABLE IF EXISTS `personal_salud`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `personal_salud` (
  `idPersonal` varchar(15) NOT NULL,
  `nombre1` varchar(50) NOT NULL,
  `nombre2` varchar(50) DEFAULT NULL,
  `apellido1` varchar(50) NOT NULL,
  `apellido2` varchar(50) DEFAULT NULL,
  `sexo` varchar(20) NOT NULL,
  `telefono` varchar(20) NOT NULL,
  `email` varchar(50) NOT NULL,
  `idTipoDocumento` varchar(5) CHARACTER SET utf32 COLLATE utf32_spanish_ci NOT NULL,
  `cargo` varchar(15) NOT NULL,
  PRIMARY KEY (`idPersonal`),
  KEY `FK_personal_salud_cargo` (`cargo`),
  KEY `personal_salud_ibfk_1` (`idTipoDocumento`),
  CONSTRAINT `FK_personal_salud_cargo` FOREIGN KEY (`cargo`) REFERENCES `cargo` (`idCargo`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `personal_salud_ibfk_1` FOREIGN KEY (`idTipoDocumento`) REFERENCES `tipo_documento` (`idTipoDocumento`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `personal_salud`
--

LOCK TABLES `personal_salud` WRITE;
/*!40000 ALTER TABLE `personal_salud` DISABLE KEYS */;
INSERT INTO `personal_salud` VALUES ('1234567','Thomas','','Salazar','Osorio','Masculino','2334456','thoma@gmail.com','2','2');
/*!40000 ALTER TABLE `personal_salud` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `personal_salud_titulo`
--

DROP TABLE IF EXISTS `personal_salud_titulo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `personal_salud_titulo` (
  `idPst` int(11) NOT NULL AUTO_INCREMENT,
  `idPersonal` varchar(15) NOT NULL,
  `idTipoTitu` varchar(5) NOT NULL,
  `idInstitucion` varchar(5) NOT NULL,
  `fechaTitulacion` date NOT NULL,
  PRIMARY KEY (`idPst`),
  KEY `FK_personal_salud_titulo_personal_salud` (`idPersonal`),
  KEY `FK_personal_salud_titulo_tipo_titulo_academico` (`idTipoTitu`),
  KEY `FK_personal_salud_titulo_institucion_academica` (`idInstitucion`),
  CONSTRAINT `FK_personal_salud_titulo_institucion_academica` FOREIGN KEY (`idInstitucion`) REFERENCES `institucion_academica` (`idInstitucion`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_personal_salud_titulo_personal_salud` FOREIGN KEY (`idPersonal`) REFERENCES `personal_salud` (`idPersonal`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_personal_salud_titulo_tipo_titulo_academico` FOREIGN KEY (`idTipoTitu`) REFERENCES `tipo_titulo_academico` (`idTipoTitu`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `personal_salud_titulo`
--

LOCK TABLES `personal_salud_titulo` WRITE;
/*!40000 ALTER TABLE `personal_salud_titulo` DISABLE KEYS */;
INSERT INTO `personal_salud_titulo` VALUES (53,'1234567','2','2','2020-04-02'),(54,'1234567','2','2','2020-04-02');
/*!40000 ALTER TABLE `personal_salud_titulo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `registro_atencion_paciente`
--

DROP TABLE IF EXISTS `registro_atencion_paciente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `registro_atencion_paciente` (
  `huella` varbinary(50) NOT NULL DEFAULT '',
  `fecha` date NOT NULL,
  `estado` varchar(50) NOT NULL,
  `glasglow` varchar(10) NOT NULL,
  `signosVitales` varchar(10) NOT NULL,
  `lugarAccidente` varchar(50) NOT NULL,
  `idpersona` int(11) NOT NULL,
  `idMedicamento` varchar(50) NOT NULL,
  `idPersonaRecibe` varchar(50) NOT NULL,
  `idPersonalSalud` varchar(50) NOT NULL,
  PRIMARY KEY (`huella`),
  KEY `FK_registro_atencion_paciente_datos_persona` (`idpersona`),
  KEY `FK_registro_atencion_paciente_medicamento` (`idMedicamento`),
  KEY `FK_registro_atencion_paciente_documento_referencia` (`idPersonaRecibe`),
  KEY `FK_registro_atencion_paciente_personal_salud` (`idPersonalSalud`),
  CONSTRAINT `FK_registro_atencion_paciente_datos_persona` FOREIGN KEY (`idpersona`) REFERENCES `datos_persona` (`idpersona`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_registro_atencion_paciente_documento_referencia` FOREIGN KEY (`idPersonaRecibe`) REFERENCES `documento_referencia` (`idPersonaRecibe`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_registro_atencion_paciente_medicamento` FOREIGN KEY (`idMedicamento`) REFERENCES `medicamento` (`idMedicamento`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_registro_atencion_paciente_personal_salud` FOREIGN KEY (`idPersonalSalud`) REFERENCES `personal_salud` (`idPersonal`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `registro_atencion_paciente`
--

LOCK TABLES `registro_atencion_paciente` WRITE;
/*!40000 ALTER TABLE `registro_atencion_paciente` DISABLE KEYS */;
/*!40000 ALTER TABLE `registro_atencion_paciente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipo_documento`
--

DROP TABLE IF EXISTS `tipo_documento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tipo_documento` (
  `idTipoDocumento` varchar(5) COLLATE utf32_spanish_ci NOT NULL,
  `nombreTipoDocumento` varchar(20) COLLATE utf32_spanish_ci NOT NULL,
  PRIMARY KEY (`idTipoDocumento`)
) ENGINE=InnoDB DEFAULT CHARSET=utf32 COLLATE=utf32_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipo_documento`
--

LOCK TABLES `tipo_documento` WRITE;
/*!40000 ALTER TABLE `tipo_documento` DISABLE KEYS */;
INSERT INTO `tipo_documento` VALUES ('1','Cedula de Ciudadania'),('2','Tarjeta de Identidad');
/*!40000 ALTER TABLE `tipo_documento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipo_titulo_academico`
--

DROP TABLE IF EXISTS `tipo_titulo_academico`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tipo_titulo_academico` (
  `idTipoTitu` varchar(5) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  PRIMARY KEY (`idTipoTitu`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipo_titulo_academico`
--

LOCK TABLES `tipo_titulo_academico` WRITE;
/*!40000 ALTER TABLE `tipo_titulo_academico` DISABLE KEYS */;
INSERT INTO `tipo_titulo_academico` VALUES ('2','Conductor'),('4','Medico'),('5','Tecnologo en Atencion'),('6','Auxiliar de Enfermeria'),('ha','erty'),('yyyy','tttt');
/*!40000 ALTER TABLE `tipo_titulo_academico` ENABLE KEYS */;
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
  `idperfil` varchar(5) COLLATE utf32_spanish_ci DEFAULT NULL,
  PRIMARY KEY (`idUsuario`),
  KEY `FK_usuario_perfil` (`idperfil`),
  CONSTRAINT `FK_usuario_perfil` FOREIGN KEY (`idperfil`) REFERENCES `perfil` (`idperfil`)
) ENGINE=InnoDB DEFAULT CHARSET=utf32 COLLATE=utf32_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (111,'','','','','a',_binary '1',NULL),(1010,'nelson','giovani','salazar','','nelson',_binary '1234',NULL);
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

-- Dump completed on 2020-04-16 15:34:20
