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

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `datos_por_huella` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_spanish_ci */;

USE `datos_por_huella`;

--
-- Table structure for table `cargo`
--

DROP TABLE IF EXISTS `cargo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cargo` (
  `idCargo` varchar(5) COLLATE utf8_spanish_ci NOT NULL,
  `nombre` varchar(45) COLLATE utf8_spanish_ci NOT NULL,
  `estado` tinyint(1) NOT NULL,
  PRIMARY KEY (`idCargo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cargo`
--

LOCK TABLES `cargo` WRITE;
/*!40000 ALTER TABLE `cargo` DISABLE KEYS */;
INSERT INTO `cargo` VALUES ('01','APH',1);
/*!40000 ALTER TABLE `cargo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `datos_persona`
--

DROP TABLE IF EXISTS `datos_persona`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `datos_persona` (
  `idpersona` varchar(15) COLLATE utf8_spanish_ci NOT NULL,
  `primerNombre` varchar(45) COLLATE utf8_spanish_ci NOT NULL,
  `segundoNombre` varchar(45) COLLATE utf8_spanish_ci DEFAULT NULL,
  `primerApellido` varchar(45) COLLATE utf8_spanish_ci NOT NULL,
  `segundoApellido` varchar(45) COLLATE utf8_spanish_ci DEFAULT NULL,
  `fechaNacimiento` date NOT NULL,
  `direccion` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `sexo` varchar(19) COLLATE utf8_spanish_ci NOT NULL,
  `alergicoA` varchar(255) COLLATE utf8_spanish_ci NOT NULL,
  `enfermedadSufre` varchar(255) COLLATE utf8_spanish_ci NOT NULL,
  `observaciones` varchar(255) COLLATE utf8_spanish_ci NOT NULL,
  `huella` blob NOT NULL,
  `huella1` tinyblob NOT NULL,
  `idTipoDocumento` varchar(5) COLLATE utf8_spanish_ci NOT NULL,
  `idEps` varchar(5) COLLATE utf8_spanish_ci NOT NULL,
  `estado` tinyint(1) NOT NULL,
  PRIMARY KEY (`idpersona`),
  KEY `Fk_datos_persona_tipo_documento` (`idTipoDocumento`),
  KEY `Fk_datos_persona_eps` (`idEps`),
  CONSTRAINT `Fk_datos_persona_eps` FOREIGN KEY (`idEps`) REFERENCES `eps` (`idEps`) ON UPDATE CASCADE,
  CONSTRAINT `Fk_datos_persona_tipo_documento` FOREIGN KEY (`idTipoDocumento`) REFERENCES `tipo_documento` (`idTipoDocumento`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `datos_persona`
--

LOCK TABLES `datos_persona` WRITE;
/*!40000 ALTER TABLE `datos_persona` DISABLE KEYS */;
INSERT INTO `datos_persona` VALUES ('15327400','Nelson','Giovanni','Salazar','Roldan','1973-08-26','Carrera 78 A # 67-54','Masculino','no','no','no',_binary '\0¯Z\»*\„s\\¿A7	´qp®Uö≠wx\ÔÚ»ôg¸/ê\œ\‚5ëzë¸e\Ë\·=øJ>/\–fV\Èt\ﬁbD_\—;l\œ2÷õ˚sæ&ÖVR¯ñ ò≤û|ö\…)°.	PAÙ˙\Ë˘¸>µxÆ\◊w¯&4^\ÃÙƒ∂7ò\Ô\¬ JtWZ^\€t¶\Ó\rE\ﬁN\≈0zÒ˘\Z@3‹∫4ñ4Æ	s4F)8\»;S\Œ+là¶¸§_ó\ÁU◊ìùsN*^ı†˛ \œ-Ö DÆf\¬(®®PZÜÉ©ÆÆÒ;	\›<∞\Ìf\◊s\·\r7	.77/œÑ1,r\€P˘Ú6+ü˛\“$\‹2˝!ï@í∫~óE¡—±L	)é\naæŒ™\Ãu^\Í/∂æ\‹F\∆ÃÑ®†wõë\ﬂI∞8un0®º#\”;\ÏÅÙ\‹#gˆbC\Ë¯5DPCQû@QsÑ´öaVg\Ï\ƒ\Â\–œ≥èQ¿∂\”˙∂ñ`§%Zü¶0?ø.\ƒIo\0¯d\»*\„s\\¿A7	´q™Uö\—Û\—D:\’M4\»eﬂöèeñ\r˚h:ª[≥\„Ã≤|´\ÿ~H¡∑,¸®TˆØI—Øæ*[+p\Ì/vQ˝äR˙ã˝3ÇVjV.ˇ\¬sjZÄÜj\⁄-∑\Ê\Âˆ\“qj|y8<7=úÛI\À|^\ﬁ\‡Ñ¸yLàı[πë\ÌeT\"0ç%å1\Ã\·WL*<\‚˛™\ÂQ\Z\nq\Í9^bkπ˛vUJS˜(Y∂GXÅ@ü-¸uëCøE\‡!\"\'?h+\ÁI\Ëmâ,Äπ\\E\—\Íza%æ∑é´l|ê\ƒΩ±@\‚+˘G\⁄_c≤ê~@ÿògO*á¯≤ì´PÇx®\”\Ã˘\ [\Z\–∏\∆\◊Sß\ÂÜ-¡~¬åæ3}l:Q˘f\≈6ñÛN=ú\'}v%è3\\\·YÜÁû≤üOb\–0Pû«¶Hí6b\⁄\·◊ø≥π5æ)i-\Ê\Â >^aúˆ˙åµÉ\‡ìírÒ“∂+≠ÄÙvˇu\'9ôo\0¯\\\»*\„s\\¿A7	´q0§Uök8[*Z1Uü∞{«òı\≈s\‰\'®\'\Âˆ#K\Ëj¥x∂\ÔbRl≥zíâ\”G¨`\⁄\Âïh\–H\ƒc±R(9\nÆ¡´˛\÷\Ì\‚ˇ%ß÷ñ\Ì˝d—é?t≠\€C°Mù-∑‹µ™\Êê\0∑@¢<Q\«\ÀGˆÜ\“l<g#ø_\À[±h(\»\Í\—ê\„ñ{^-aœπAısc\'i1>qÒ\È)ƒØ%JYrƒå\‘¡ó\’Uîñ≠ılf†\€¯haÇœïòóGˆÅá@(\‹	å¯\÷\‘Ù¨F∫6∑(*ò`w°xi)\ƒ\ËX\È13\ﬂ\Õ/\ŒmΩ‹©_	4\‰\ÃF2sâhT5B\rß˚\›\≈\√\ŒEä\‰*øï∂u≤ﬂπu_n9\Œ\”*v\ﬂLM¢\\Yo∏j6\œ\‹[.Z\‡…øJâl>¿\„\nioQ\÷F-˚nS\Ó}\ÔDë0È≤Ωx\Ê-ˆß#˚=E^Üï)o\0\Ë\»*\„s\\¿A7	´qpÑUö»§^%Bµ$\‰\Ô˚\«\«C\‰÷üYs\¬\·^!\‚Êåé\ €Ö\n\’(Y\Î\·Ú\ÊÇ	ˆƒπmÅ~\◊±ô\Ãn\”3∞∑M>\⁄\nec*<\Ÿ\÷T:≥ª%\…I\ƒ5íCU+Oóx6auã(\Œ\‰ã1\÷\ZKFÑ\’Beyõ¶	s\—\ÎO\ \'+∂\Áó;V¸\Ó%pGB:CΩæA0^ê\…°F†û\œ~w+à∑¯\∆3∏ÒÇ\’¯\Ÿ´\÷0Vˇ/†éÜñﬂº•ã4¿c8ßV˛\À\«X∏Òﬂ¶_>1\Ó\Ë{!\ŸYö®cSπî\'s\›\‹\ >ˆ\⁄i\·vmë≥ñf|…Ø*\È™\Î;^˝´¥{\…˘∞|í\„§\Ÿ4;ëH≥\„\‡\ﬁ%m4\Zo\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0',_binary '1632','01','01',1);
/*!40000 ALTER TABLE `datos_persona` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `documento_entrega`
--

DROP TABLE IF EXISTS `documento_entrega`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `documento_entrega` (
  `idTipoDocumento` varchar(5) COLLATE utf8_spanish_ci NOT NULL,
  `idPersonaRecibe` varchar(15) COLLATE utf8_spanish_ci NOT NULL,
  `nombre1` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `nombre2` varchar(50) COLLATE utf8_spanish_ci DEFAULT NULL,
  `apellido1` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `apellido2` varchar(50) COLLATE utf8_spanish_ci DEFAULT NULL,
  `idCargo` varchar(15) COLLATE utf8_spanish_ci NOT NULL,
  `fechaRecepcionPaciente` date NOT NULL,
  `horaRecepcionPaciente` time NOT NULL,
  `codigoRemision` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `observaciones` varchar(200) COLLATE utf8_spanish_ci NOT NULL,
  `estado` tinyint(1) NOT NULL,
  PRIMARY KEY (`codigoRemision`),
  KEY `FK_documento_entrega_tipo_documento` (`idTipoDocumento`),
  KEY `Fk_documento_entrega_cargo` (`idCargo`),
  KEY `codigoRemision` (`codigoRemision`),
  CONSTRAINT `FK_documento_entrega_tipo_documento` FOREIGN KEY (`idTipoDocumento`) REFERENCES `tipo_documento` (`idTipoDocumento`) ON UPDATE CASCADE,
  CONSTRAINT `Fk_documento_entrega_cargo` FOREIGN KEY (`idCargo`) REFERENCES `cargo` (`idCargo`) ON UPDATE CASCADE,
  CONSTRAINT `documento_entrega_ibfk_1` FOREIGN KEY (`codigoRemision`) REFERENCES `registro_atencion_pacientes` (`codigoRemision`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `documento_entrega`
--

LOCK TABLES `documento_entrega` WRITE;
/*!40000 ALTER TABLE `documento_entrega` DISABLE KEYS */;
INSERT INTO `documento_entrega` VALUES ('01','222222','ssss','sss','ss','ssss','01','2020-05-21','11:50:09','12345','',1),('01','222','22','22','22','22','01','2020-05-21','13:46:54','213','',1),('01','222','ww','ww','ww','ww','01','2020-05-21','14:04:10','23','',1),('01','444','444','44','44','444','01','2020-05-21','12:19:42','ss','',1);
/*!40000 ALTER TABLE `documento_entrega` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `eps`
--

DROP TABLE IF EXISTS `eps`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `eps` (
  `idEps` varchar(5) COLLATE utf8_spanish_ci NOT NULL,
  `nombreEps` varchar(45) COLLATE utf8_spanish_ci NOT NULL,
  `direccionEps` varchar(45) COLLATE utf8_spanish_ci NOT NULL,
  `telEps` varchar(15) COLLATE utf8_spanish_ci NOT NULL,
  `estado` tinyint(1) NOT NULL,
  PRIMARY KEY (`idEps`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `eps`
--

LOCK TABLES `eps` WRITE;
/*!40000 ALTER TABLE `eps` DISABLE KEYS */;
INSERT INTO `eps` VALUES ('01','Sura','Cordoba','3333',1);
/*!40000 ALTER TABLE `eps` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `familiar_paciente`
--

DROP TABLE IF EXISTS `familiar_paciente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `familiar_paciente` (
  `idFamiliar` varchar(15) COLLATE utf8_spanish_ci NOT NULL,
  `nombre1` varchar(20) COLLATE utf8_spanish_ci NOT NULL,
  `nombre2` varchar(45) COLLATE utf8_spanish_ci DEFAULT NULL,
  `apellido1` varchar(45) COLLATE utf8_spanish_ci NOT NULL,
  `apellido2` varchar(45) COLLATE utf8_spanish_ci DEFAULT NULL,
  `direccion` varchar(35) COLLATE utf8_spanish_ci DEFAULT NULL,
  `telefono` varchar(15) COLLATE utf8_spanish_ci NOT NULL DEFAULT '',
  `estado` tinyint(1) NOT NULL,
  PRIMARY KEY (`idFamiliar`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `familiar_paciente`
--

LOCK TABLES `familiar_paciente` WRITE;
/*!40000 ALTER TABLE `familiar_paciente` DISABLE KEYS */;
INSERT INTO `familiar_paciente` VALUES ('2222','aaaaaaa','aaaaa','aa','a','aaaaaaa','22222222',1);
/*!40000 ALTER TABLE `familiar_paciente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `institucion_academica`
--

DROP TABLE IF EXISTS `institucion_academica`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `institucion_academica` (
  `idInstitucion` varchar(5) COLLATE utf8_spanish_ci NOT NULL,
  `nombre` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `direccion` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `telefono` varchar(15) COLLATE utf8_spanish_ci DEFAULT NULL,
  `estado` tinyint(1) NOT NULL,
  PRIMARY KEY (`idInstitucion`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `institucion_academica`
--

LOCK TABLES `institucion_academica` WRITE;
/*!40000 ALTER TABLE `institucion_academica` DISABLE KEYS */;
INSERT INTO `institucion_academica` VALUES ('01','San Luis','Yarumal','777777',1);
/*!40000 ALTER TABLE `institucion_academica` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `institucion_referencia`
--

DROP TABLE IF EXISTS `institucion_referencia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `institucion_referencia` (
  `idInstiRefe` varchar(5) COLLATE utf8_spanish_ci NOT NULL,
  `nombre` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `direccion` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `telefono` varchar(20) COLLATE utf8_spanish_ci DEFAULT NULL,
  `estado` tinyint(1) NOT NULL,
  PRIMARY KEY (`idInstiRefe`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `institucion_referencia`
--

LOCK TABLES `institucion_referencia` WRITE;
/*!40000 ALTER TABLE `institucion_referencia` DISABLE KEYS */;
INSERT INTO `institucion_referencia` VALUES ('01','Pablo Tobon Uribe','Pilarica','11111',1);
/*!40000 ALTER TABLE `institucion_referencia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `medicamento`
--

DROP TABLE IF EXISTS `medicamento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `medicamento` (
  `idMedicamento` varchar(5) COLLATE utf8_spanish_ci NOT NULL,
  `nombre` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `estado` tinyint(1) NOT NULL,
  PRIMARY KEY (`idMedicamento`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `medicamento`
--

LOCK TABLES `medicamento` WRITE;
/*!40000 ALTER TABLE `medicamento` DISABLE KEYS */;
INSERT INTO `medicamento` VALUES ('01','Acetaminofen',1);
/*!40000 ALTER TABLE `medicamento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `perfil`
--

DROP TABLE IF EXISTS `perfil`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `perfil` (
  `idperfil` varchar(15) COLLATE utf8_spanish_ci NOT NULL,
  `nombre` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `estado` tinyint(1) NOT NULL,
  PRIMARY KEY (`idperfil`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
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
  `idpersona_familiar` int(11) NOT NULL AUTO_INCREMENT,
  `idpersona` varchar(15) COLLATE utf8_spanish_ci NOT NULL,
  `idFamiliar` varchar(15) COLLATE utf8_spanish_ci NOT NULL,
  `fechaIngreso` date NOT NULL,
  `estado` tinyint(1) NOT NULL,
  PRIMARY KEY (`idpersona_familiar`),
  KEY `FK_persona_familiar_datos_persona` (`idpersona`),
  KEY `Fk_persona_familiar_familiar_paciente` (`idFamiliar`),
  CONSTRAINT `FK_persona_familiar_datos_persona` FOREIGN KEY (`idpersona`) REFERENCES `datos_persona` (`idpersona`) ON UPDATE CASCADE,
  CONSTRAINT `Fk_persona_familiar_familiar_paciente` FOREIGN KEY (`idFamiliar`) REFERENCES `familiar_paciente` (`idFamiliar`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `persona_familiar`
--

LOCK TABLES `persona_familiar` WRITE;
/*!40000 ALTER TABLE `persona_familiar` DISABLE KEYS */;
INSERT INTO `persona_familiar` VALUES (6,'15327400','2222','2020-05-21',1);
/*!40000 ALTER TABLE `persona_familiar` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `personal_salud`
--

DROP TABLE IF EXISTS `personal_salud`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `personal_salud` (
  `idPersonal` varchar(15) COLLATE utf8_spanish_ci NOT NULL,
  `nombre1` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `nombre2` varchar(50) COLLATE utf8_spanish_ci DEFAULT NULL,
  `apellido1` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `apellido2` varchar(50) COLLATE utf8_spanish_ci DEFAULT NULL,
  `sexo` varchar(20) COLLATE utf8_spanish_ci NOT NULL,
  `telefono` varchar(20) COLLATE utf8_spanish_ci NOT NULL,
  `email` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `idTipoDocumento` varchar(5) COLLATE utf8_spanish_ci NOT NULL,
  `idCargo` varchar(5) COLLATE utf8_spanish_ci NOT NULL,
  `estado` tinyint(1) NOT NULL,
  PRIMARY KEY (`idPersonal`),
  KEY `FK_personal_salud_tipo_tipo_documento` (`idTipoDocumento`),
  KEY `FK_personal_salud_cargo` (`idCargo`),
  CONSTRAINT `FK_personal_salud_cargo` FOREIGN KEY (`idCargo`) REFERENCES `cargo` (`idCargo`) ON UPDATE CASCADE,
  CONSTRAINT `FK_personal_salud_tipo_tipo_documento` FOREIGN KEY (`idTipoDocumento`) REFERENCES `tipo_documento` (`idTipoDocumento`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `personal_salud`
--

LOCK TABLES `personal_salud` WRITE;
/*!40000 ALTER TABLE `personal_salud` DISABLE KEYS */;
INSERT INTO `personal_salud` VALUES ('111','ss','ss','ss','sss','Masculino','22','@ww.','01','01',1),('2222','SS','SS','SS','SS','Masculino','3333','@.','01','01',1),('22222','sss','ss','ss','ss','Masculino','222','@.','01','01',1),('55555555','tttttttttt','tttttttt','ttt','tttt','Masculino','6666','tt','01','01',1),('888888','sss','ssss','sss','ssss','Masculino','33','222','02','01',1);
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
  `idPersonal` varchar(15) COLLATE utf8_spanish_ci NOT NULL,
  `idTipoTitu` varchar(5) COLLATE utf8_spanish_ci NOT NULL,
  `idInstitucion` varchar(5) COLLATE utf8_spanish_ci NOT NULL,
  `fechaTitulacion` date NOT NULL,
  `estado` tinyint(1) NOT NULL,
  PRIMARY KEY (`idPst`),
  KEY `FK_personal_salud_titulo_personal_salud` (`idPersonal`),
  KEY `FK_personal_salud_titulo_tipo_titulo_academico` (`idTipoTitu`),
  KEY `FK_personal_salud_titulo_institucion_academica` (`idInstitucion`),
  CONSTRAINT `FK_personal_salud_titulo_institucion_academica` FOREIGN KEY (`idInstitucion`) REFERENCES `institucion_academica` (`idInstitucion`) ON UPDATE CASCADE,
  CONSTRAINT `FK_personal_salud_titulo_personal_salud` FOREIGN KEY (`idPersonal`) REFERENCES `personal_salud` (`idPersonal`) ON UPDATE CASCADE,
  CONSTRAINT `FK_personal_salud_titulo_tipo_titulo_academico` FOREIGN KEY (`idTipoTitu`) REFERENCES `tipo_titulo_academico` (`idTipoTitu`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `personal_salud_titulo`
--

LOCK TABLES `personal_salud_titulo` WRITE;
/*!40000 ALTER TABLE `personal_salud_titulo` DISABLE KEYS */;
INSERT INTO `personal_salud_titulo` VALUES (1,'55555555','01','01','2020-05-21',1);
/*!40000 ALTER TABLE `personal_salud_titulo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `registro_atencion_pacientes`
--

DROP TABLE IF EXISTS `registro_atencion_pacientes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `registro_atencion_pacientes` (
  `fechaAtencionPaciente` date NOT NULL,
  `horaAtencionPaciente` time NOT NULL,
  `condicionPaciente` varchar(20) COLLATE utf8_spanish_ci NOT NULL,
  `glasgow` varchar(20) COLLATE utf8_spanish_ci NOT NULL,
  `signosVitales` varchar(20) COLLATE utf8_spanish_ci NOT NULL,
  `lugarAccidente` varchar(45) COLLATE utf8_spanish_ci NOT NULL,
  `idMedicamento` varchar(5) COLLATE utf8_spanish_ci NOT NULL,
  `dosis` varchar(15) COLLATE utf8_spanish_ci NOT NULL,
  `idPersonal` varchar(15) COLLATE utf8_spanish_ci NOT NULL,
  `idInstiRefe` varchar(5) COLLATE utf8_spanish_ci NOT NULL,
  `codigoRemision` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `idpersona` varchar(15) COLLATE utf8_spanish_ci NOT NULL,
  `nombrePaciente` varchar(30) COLLATE utf8_spanish_ci NOT NULL,
  `apellidoPaciente` varchar(30) COLLATE utf8_spanish_ci NOT NULL,
  `estado` tinyint(1) NOT NULL,
  PRIMARY KEY (`codigoRemision`),
  KEY `idMedicamento` (`idMedicamento`,`idPersonal`,`idInstiRefe`,`idpersona`),
  KEY `idPersonal` (`idPersonal`),
  KEY `idpersona` (`idpersona`),
  KEY `idInstiRefe` (`idInstiRefe`),
  CONSTRAINT `registro_atencion_pacientes_ibfk_1` FOREIGN KEY (`idMedicamento`) REFERENCES `medicamento` (`idMedicamento`) ON UPDATE CASCADE,
  CONSTRAINT `registro_atencion_pacientes_ibfk_2` FOREIGN KEY (`idPersonal`) REFERENCES `personal_salud` (`idPersonal`) ON UPDATE CASCADE,
  CONSTRAINT `registro_atencion_pacientes_ibfk_3` FOREIGN KEY (`idpersona`) REFERENCES `datos_persona` (`idpersona`) ON UPDATE CASCADE,
  CONSTRAINT `registro_atencion_pacientes_ibfk_4` FOREIGN KEY (`idInstiRefe`) REFERENCES `institucion_referencia` (`idInstiRefe`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `registro_atencion_pacientes`
--

LOCK TABLES `registro_atencion_pacientes` WRITE;
/*!40000 ALTER TABLE `registro_atencion_pacientes` DISABLE KEYS */;
INSERT INTO `registro_atencion_pacientes` VALUES ('2020-05-21','11:49:55','Vivo','15/15','Pulso','65','01','5','2222','01','12345','15327400','Nelson','Salazar',1),('2020-05-21','13:46:39','Vivo','15/15','presion Arterial','22','01','2','2222','01','213','15327400','Nelson','Salazar',1),('2020-05-21','14:03:47','Vivo','15/15','presion Arterial','44','01','4','2222','01','23','15327400','Nelson','Salazar',1),('2020-05-21','12:19:28','Vivo','15/15','presion Arterial','ss','01','sss','2222','01','ss','15327400','Nelson','Salazar',1);
/*!40000 ALTER TABLE `registro_atencion_pacientes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipo_documento`
--

DROP TABLE IF EXISTS `tipo_documento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tipo_documento` (
  `idTipoDocumento` varchar(5) COLLATE utf8_spanish_ci NOT NULL,
  `nombreTipoDocumento` varchar(45) COLLATE utf8_spanish_ci NOT NULL,
  `estado` tinyint(1) NOT NULL,
  PRIMARY KEY (`idTipoDocumento`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipo_documento`
--

LOCK TABLES `tipo_documento` WRITE;
/*!40000 ALTER TABLE `tipo_documento` DISABLE KEYS */;
INSERT INTO `tipo_documento` VALUES ('01','Cedula de Ciudadania',1),('02','Tarjeta de Identidad',1);
/*!40000 ALTER TABLE `tipo_documento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipo_titulo_academico`
--

DROP TABLE IF EXISTS `tipo_titulo_academico`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tipo_titulo_academico` (
  `idTipoTitu` varchar(5) COLLATE utf8_spanish_ci NOT NULL,
  `nombre` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `estado` tinyint(1) NOT NULL,
  PRIMARY KEY (`idTipoTitu`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipo_titulo_academico`
--

LOCK TABLES `tipo_titulo_academico` WRITE;
/*!40000 ALTER TABLE `tipo_titulo_academico` DISABLE KEYS */;
INSERT INTO `tipo_titulo_academico` VALUES ('01','APH',1),('02','Enfermera Jefe',1),('03','Medico',1),('04','Urgentologo',1);
/*!40000 ALTER TABLE `tipo_titulo_academico` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuario` (
  `idUsuario` varchar(15) COLLATE utf8_spanish_ci NOT NULL,
  `primerNombre` varchar(45) COLLATE utf8_spanish_ci NOT NULL,
  `segundoNombre` varchar(45) COLLATE utf8_spanish_ci NOT NULL,
  `primerApellido` varchar(45) COLLATE utf8_spanish_ci NOT NULL,
  `segundoApellido` varchar(45) COLLATE utf8_spanish_ci NOT NULL,
  `username` varchar(45) COLLATE utf8_spanish_ci NOT NULL,
  `contrasena` longblob,
  `idperfil` varchar(15) COLLATE utf8_spanish_ci DEFAULT NULL,
  `estado` tinyint(1) NOT NULL,
  PRIMARY KEY (`idUsuario`),
  KEY `FK_usuario_perfil` (`idperfil`),
  CONSTRAINT `FK_usuario_perfil` FOREIGN KEY (`idperfil`) REFERENCES `perfil` (`idperfil`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES ('111','','','','','a',_binary '1',NULL,0);
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

-- Dump completed on 2020-05-21 14:49:15
