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
INSERT INTO `datos_persona` VALUES (1,'carlos','ss','vera','','2020-04-07','qqqq','Masculino','qqq','qqqq','qqqqq',_binary '\0�a\�*\�s\\�A7	�q0�U�BĿ�&\�BKE\��x8-�v�\����ݎ�ɍ\��כ\�Xk�ɽ�,j5�s+I˩j�\"?��Oj񅭖��<.Y���Nt*\�\�\�\"�L���棾^��\�\�	8��	Y�PV\�)�!_�p@\�\'\�4<\�b��<���x��M��C\�a\�0uKy�_+��0\�ue��\�j�[��5Dh�K���E\�(\�\�\�/�vB�\�\�%�P�i�@ѩV@�T�s\�\��\�5x.\�҈y\�\�\�Htf��p\\0}\��\�\ru�=���t���g�DJ�$�\��gR����c(TE8d\��3X��\�*s�\�\\�5�\r�I\�5�\�~\0~\�Zm��nv�?[io�o��U<���\�Ĭ&o\0�T\�*\�s\\�A7	�q�U��_o��\�+\��+drg�*�Z6\��$\�o^,�)��$�/�-g\�o�!�H��RAa�FcF�s���r�,,�\�cCߑ\�EMGkG�\�|C\�\�;G\�4ޔ\�b�\�q�\�9�zzr�fP;ݱ���`!E3 2�Π��hb\�b���o�\�\\\�\�$[��B��2�=!\">$��VG\�	�T�z\Z\�Hb�\�\�m\�2­w��9\n�#u�^,RL\ZYѾ�2\�\�\Zy�\Z�>�A�s���b�W�\\\�|����a�J�k՘\�sᐼ�d3\�m|	�\��:J	\�+�k�q�2��P\�A���]В\��H�\����A���g�n��w&+\��\�PBd�#F�z\� o\0��\�*\�s\\�A7	�qp�U�wg}m\�[S:���0�/\�ٓ`D|�\�J��M;\�\�?�<O\���5\�\Z^�G\�Lazg8薆\�q[\\s3\�\�\�C�ܙu#��#w�}\��\�\�c�M\�#L2\�\�\�Z\r�\�Ʌ��u%B��\�{��\�֨ `��\�(Z+	���\�#R\�II�uyZ�9G9$\0��~�q`�\��F\���ݏ~X��Z\��\�\����g�Z9V\�MJV[ã��0�\n^�~�/\0\�\�3\��\"l,��T\�7$Y\��}1�F\�\�tfH�����tE\�\�\\\'\�%\�c�d1\���W(b΁w`͢�\�9v�,_|��\�7\�\�F�$\�9�;\0�{�\�\�x�\�\�v��wمz�}\n\�Gߍ�=sR1ɮ�^q��u�:u�i0|�}��Ek�H\�o\0\�E\�*\�s\\�A7	�q0�U�1D*�\�Pî\���s�&�N\�8>\�e\�\�\"��΍;�S���\\\�u�$\�\�\�G�C_,\Z};�dO^\�);\�\�ٰn{�7h3\� m\�%~��/�\��<\�$-�F���?v!l\�k\�\n}\�\'\Z�v-v8<���r*�A>��x�ZE!���\�;t\�\�*�\�T+H\�ԛ�!1�Ӿ�%��E��2��B�p�x\�v!7���T\"�pD4(�I\�*�]\��$��21\nƴ��\�\�E\��=J-P����O\��Rg��D؝!c�ｶ\�br~{[n\\\�>ԭ[��zI۞��\�U+d��L\�-z\��\'d�\�P��T\�<����X%o\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0',_binary '1632',1,1),(15327400,'Nelson','Giovanni','Salazar','Roldan','1973-08-26','calle 78A # 67-54','Masculino','no','no','Cuarentena	',_binary '\0��\�*\�s\\�A7	�q0�U�����A�\�!\�����DTy6t������j��\Z8?V\�6\�Mtb\��=��U\�T\�D_���\n.����\r�qDl�*\�b$��]h�z,�^\�T�72N2UHO\�\�%���\�]>�m�\�\Z����\�[�b�y\�p�\�D�Sj����\�|��Vo\��i3J\�\�\n\�����\�\\\�ţ�Q\��\����!�\��_Ӵc\�zK�`\�8l����g\�,vC\r�&\�G�\�\�Q\'�a��+m��\���\�\��!H�(#J�$�P\��~ M�.�q/ɫ���\�/C\��u�\�lD\"\����l3[ȍ8�j�y���tUu\�\"_\r��@��%\�[~d���s�\�=��g\\x֑�\�\��ϛ@)D�\�\�U�\'\�Q\�\�\�W~\�\�;�\�o\0�~\�*\�s\\�A7	�q��U��E�=\�l/�Y��2�ɾ�$�\"\�\�gZ\�n\�ˤo���\���z{�h.=��\�\0��\�\�\0�\�sCY5\�ß�\�7.�%\�\�t-��b�\�y\�\�c��=\��,ӊ�*A\�\��<n\�\'T�\�[n�&B�O!��d2��P���ub\Z)��B{��V\���\�\�\��(e�\�)$t\�d�f\�\�?���\�:̀\�\�\��6}*D��,d\�I�l��(\�4JB�׶�\�ס@l�\�rIJ\����u%�s\�\�ih[�\�\�����\�\Z)\�\Zr\�F\�]��ǡj+\�6�rid\�ix��\�ͩIn½���>J�\�k�6\�Λ�-\�HG\\\�	\�/\\`��H�\�[\�&)J�m���\�S\�Az�(\�I\�Z�u�b\�ܮ��|x�o\0�J\�*\�s\\�A7	�q�U�y\�\�3p�w���\�B���K\��_��F˫��\�\�Y6����;>}\�\�\�m\�X\\/ܚ�?\��a\��\'�\�+Rmq��\�w�!\�\��\�p\��\�y�g\�O}	��B��uU\'���26L?W\'W�~=\r�2\�,\����2�-ף\�jZ�tq�C���T\�j\�XD0H-�mb|�ȾY\�x2?K\�\��Ө����3\0��_>�Vo$\�)�%\�X\��=;rPL��X0�ũ�m�|\�\��К\�\�\�\�\Z�Ss��Ʀ2�\�ˋ)piT\�\��\0��[A�\�W��*\�]A�#r`j:��-g~��\�@�\�Y��z7�@��%9�o\0\�^\�*\�s\\�A7	�qp�U�~|\�\�\�1�ڬZ�V�UUl~:9\�\�\��F.3�\�b���S��@_���\�}��r$HvL�N1�q\�:HKϘ��4��x��\��~�ێ\���\��\�h8ͩ��{)\�{�\���=)+{����X\��h���]\"\�\�vZt�0xaB�b�IS\�*�\�Bj�q2D�8��N\�qo\�@r\�Ӣ\�\'\�5\��=�AO�wYZz�2]���6\�۹\�\�bl����@t;�T�\"�����g�ux��ҶcF|MP8���o�\��\�\�E�|%�\�DYr)E����&�@ͼl�zMN\�D�\��IG�~iHf\r�\�e�\�z*\�\��\�\�I�|\r\�Y\�sx�h�23�WiH��y��o\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0',_binary '1632',1,1),(1020303388,'Thomas','','Salazar','Osorio','2009-05-22','Bello','Masculino','nada','nada','EN Cuarentena',_binary '\0�\�*\�s\\�A7	�q�U�\�b<1T\�z�\n\�\�f\�l;�[\�U�Y��\Z�+\��r�d\0Q\n��3O1Er\�Jl��\��\�%��\�_O����\�*�i\�6�t�g�h\�oR��\�9K_&Q�\"\�|\�:��C�\���	a��\rЂC%�v�\�\�e2�_\�\�@߁��X�pf�+\�Rʅ;Ҧ\�j�p�e�*�\�C\�\�|ʄJ�����\�\��<����\��C\�S�k٫	\�d-u��?K7��\�]A�\�W�\n��\�_\�~\���*j�X�%�\�ú�8d\�\�\�3��\�\�\\D\�_3����H\\�\�ƹt��\��e�I-H+���T\�\Z�ϓ�:R��\�rF��V�$�.��˿>Z\0\�3p&IY��\�Qxg:\rjH�U\�[�|d�\�\0��\"��\�\����\�\����5�o\0�~\�*\�s\\�A7	�q�U�j$��[�r\rd\�@�\��\�\�\�FM�g�w�T��t��t�]\�L�m\"�L2i\�G���¸\�\�gj��w\�n��\��|���k��\�-N\�!c�p�\rV��6\��C=#78W\\YY�k��E�\ZN��S`ԝ\�R���_I\�ʅ��\0�\r�eRنv��\�\��x��\�=/�\�b��1��9?�\0�\���/\�:��\��\� Z����\n\�WD>[D��#x�|G��#\�G���\�\�\�\�C�Ek)B\�u?��\�Wۡz=�P�!\�\�F�\�\�Q��Y,23���yR\�\�G�\�\Zc닲�Q}l\\z�ߘ��0\�~�r\'/D8��7\�d`�52|pi\'&�\n�\�Q�\rǌ��˨;(�N/N�c���\�y�!\��\�wRc�\�\�o\0��\�*\�s\\�A7	�qp�U�z��\�\�\�x\r\�\�\'яI͏t\�lG�\r&9��p\"\�pQ��\�\�s��:�%~�\\u��\�@�=\�J\�\�(NS\�>F	U~��\nL��T�\��];&S�\�84\�VМ���<�\�ͳ\����Z�*�Z2\�)�m�\r�$\�Vdx\�\n�Y�t\0�>#�d\�H�0\�j\����qUdԃ	��f�>V �=Ͻ�KC���Y\�\�ȹ=d;�\�\�{\�ۢ��\rљU&<uG[JrhvKhh(�/�!0X)������\�Lz���\�\0&O{�Q\�&\r\�#6(� Ц\�7P�Bph���W\��1���\�O����\�_y-\�Bs\�ˎմȇ@f_P����Q8\�t:\�\�ſ-YTM�\�a>;�\�\�ĥ�QdJI�iRi�\�J�*V4\�V\�B��`J#��2�ϣy�ӹ\"��o\0\�~\�*\�s\\�A7	�q��U��#?\�\�\�\nANN��\�F\�<��)m�z[~K�\�1�\�\�\�����G&��n�\�e-aD\�oV�\r\�\�l�\�K�6\�)\�\��o���?���dY�+n�z���\�M]\� \�|N�\�fR\nN�X�<X���&��[>W�4w�hn]�\��cg&\�\�nl\\\�^Z�&\�S�\�o��\�n^\nj&\r�\�\0��fvT�c�\r}�	꿐A�<`�O\�g\�\�_�����mw�7퀸LMi>\�\��tH�M�\�/Ɖ�qxs�\�w�E[�\�j@��_�o�>c�WÍ\�>�\"�����\�{)�H\�T�@����\�N�&�E6K&Dp���\�_ ^ \Z؅�\��ԓ\�Y6i2yQ?���\�\�Df>]|��m%������hJZ�L\�DR�*\Z�l9\0حo\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0',_binary '1632',3,3);
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
INSERT INTO `eps` VALUES (1,'Sura','Cordoba',122334),(2,'Coomeva','Belen',3241657),(3,'MetroSalud','Sacatin',5117505),(4,'Medimas','San Juan',234412345);
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
  `nombre1` int(50) NOT NULL,
  `nombre2` int(50) DEFAULT NULL,
  `apellido1` int(50) NOT NULL,
  `apellido2` int(50) DEFAULT NULL,
  `sexo` int(20) NOT NULL,
  `telefono` int(20) NOT NULL,
  `email` int(50) NOT NULL,
  `tipoDocumento` int(2) NOT NULL,
  `cargo` varchar(15) NOT NULL,
  PRIMARY KEY (`idPersonal`),
  KEY `FK_personal_salud_tipo_de_documento` (`tipoDocumento`),
  KEY `FK_personal_salud_cargo` (`cargo`),
  CONSTRAINT `FK_personal_salud_cargo` FOREIGN KEY (`cargo`) REFERENCES `cargo` (`idCargo`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_personal_salud_tipo_de_documento` FOREIGN KEY (`tipoDocumento`) REFERENCES `tipo_de_documento` (`idTipoDocumento`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `personal_salud`
--

LOCK TABLES `personal_salud` WRITE;
/*!40000 ALTER TABLE `personal_salud` DISABLE KEYS */;
/*!40000 ALTER TABLE `personal_salud` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `personal_salud_titulo`
--

DROP TABLE IF EXISTS `personal_salud_titulo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `personal_salud_titulo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fechaTitulacion` date NOT NULL,
  `idPersonal` varchar(15) NOT NULL,
  `idTipoTitu` varchar(5) NOT NULL,
  `idInstitucion` varchar(5) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_personal_salud_titulo_personal_salud` (`idPersonal`),
  KEY `FK_personal_salud_titulo_tipo_titulo_academico` (`idTipoTitu`),
  KEY `FK_personal_salud_titulo_institucion_academica` (`idInstitucion`),
  CONSTRAINT `FK_personal_salud_titulo_institucion_academica` FOREIGN KEY (`idInstitucion`) REFERENCES `institucion_academica` (`idInstitucion`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_personal_salud_titulo_personal_salud` FOREIGN KEY (`idPersonal`) REFERENCES `personal_salud` (`idPersonal`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_personal_salud_titulo_tipo_titulo_academico` FOREIGN KEY (`idTipoTitu`) REFERENCES `tipo_titulo_academico` (`idTipoTitu`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `personal_salud_titulo`
--

LOCK TABLES `personal_salud_titulo` WRITE;
/*!40000 ALTER TABLE `personal_salud_titulo` DISABLE KEYS */;
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
INSERT INTO `tipo_de_documento` VALUES (1,'Cedula'),(2,'Tarjeta de Identidad'),(3,'Pasaporte'),(4,'Registro Civil'),(5,'Cedula de Extrangeria');
/*!40000 ALTER TABLE `tipo_de_documento` ENABLE KEYS */;
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

-- Dump completed on 2020-04-02 11:13:22
