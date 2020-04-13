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
INSERT INTO `datos_persona` VALUES (1111,'','aaaaaaaa','aaaaaaaaaaaa','aaaaaaaaaaaa','2020-04-03','aaaaaaaaaaaaa','Masculino','aaaaaaaaa','aaaaaaaa','aaaaaa',_binary '\0�B\�*\�s\\�A7	�qp�U�8\'\�\�\�kA��\�C�M�f\�XzL\��j_\��K*�R}I�#5\�G\�E\�^W]6�q���\�Q\ZO�\�EnIk����8$�\�\�\�8��4ֲ�_9\�M\'v\�Q�ǳ8+\���Pv\�\r�\'$\�i� \�\���m�~�\�\�ď��rc\�\�\�Y�H��\�>U�R���-\�p��\�C\�[��\��\�\'/s2og/[�;w�\�f�%\�d!7&@\�\���\\Y\��\�\��_�dV\�X����p(F}:6�\�h����\0k�!g�c֊�\�\�,��\�^i�:�=�\�4\�k��\'�\n����([T�2��\�(�\�L\�C2\Z�\�	\��*o\0�;\�*\�s\\�A7	�q0�U�&�s9H�&����6)�̻\��\�Q:$�GkK�e��IJ>r\�@@\�.�\�u\�\�\�Dk\�a a\�͌a\�j�\�\�\\\�M�\�\�>���o���.a��Ad\\|�Zb��v�V\�ҏqV�ñ\�M\�\r#�\�e�9I\�/\"�U����\�lh�d��w�\�toJ��˛\�$av4\�w.�Hi\�`~T�����$/e\��;�����\��v�(V,�}O`<^sJ\�Bk�\\Q�]/|\��Ut�#p~4�7�����\��R��h���>s��LΝ�w+L��O�J��XgM�����8\n\�\��\�㊬)|�o\0�?\�*\�s\\�A7	�q�U�\�p�oW��v��ö���\�^�7\����\�\�\��\n$�\r[,�Y�\�`���|e�\�9(�\�o*Z1�\�y\�l��\��\��c�\\\'\�eОQ�}At\�>��\0Y)�h\��\�\�\�!mcY^\�\�3��f���\���\���˵:?\�{Lw�\"\���K9\�=��\�`%�d�\�X �\�OD��\���\�\n����R��:�ĕ�\���ُ�+�v%m*n5�8��\�\�`M(\Z\�i&\nk�g�\�\��qh�#K\�w\�*\�~\��L\�A�\"J=�m�C\��\�\�\�5�b�~ecH\��\'k�N<��I�E�\�]{\�ӌ[��\�f���vG\�\�o\0\�8\�*\�s\\�A7	�q0�U�\��\��P�;�r\�{�W\r(^\'�7v\�O\��-	\�Z�i��Kރ�6,\�g\r\�)�(\�?\�C-�\rYo��\� 7��⿞G��\�\�J\�\�q&�\�Y=�ҹ�h\�u��\��pnJ�̓\�}�ƕÕ8O�t�\�z�g^�ȇ�\�|N�-k��s���7��5\0 \�\� \�>%\"t.\�8�pW��[�y\�aQ\�M.Ǖ\�)?��\�\�\n�RO���n3QUgՌD[(\��\�mG Df�\�lb\�%_���\0\�Ң�ǔ\�9)U��\�0Z\\ֺq\'�r�\�L7\�\�s��S h`�#�x�\"a�\Z&y赋U��o\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0',_binary '1632',3,2),(1111111,'qqqqqqqqqqq','qqqqqqq','qq','qqqq','2020-04-02','qqqq','Femenino','qqqq','qqqq','qqqqqqqqq',_binary '\0�&\�*\�s\\�A7	�q��U��,ƅHE�xh+\�1�]�lϩ��c\�iF�N:]�q祍�3\�A��L>�kFR\�h(��\�S�E$p�UԴ\�������X��\�L\�Y����� $�\�Ml���\�ܹn\�o\�8sq�Ŝ:szum\���J�\�}\�7>��VZ>�{�AUcM�ʫ\��I-�|\�mߗ:%���⣜\'p2� �;8\06	8x�\�nW\�iQaZtͽT!\�j,�RϖS\�\\�1-[��b}C\�g��ιV觗W\�>\Z��N�aI�5\�Y�\���I�H��\��\�^ok�\�GQo\0�L\�*\�s\\�A7	�q��U�\���F�zW>�\�I��Ψ }�+�ryV۱Xy�s\�b����Z�\�\�Wi?�_��O_VV8#1�=\�K�c&\"�:B签�!��\n�\�aW\�p^[\�T��\��l_��!}r��e\�=�\�p!V��ջ\�_�\�\�B�\�\�\�O���P�:\�ډ3ay�m-�o%\�L�p�!\"<ս�ƺU\�\�\�W֮\�4\�\�N[_ętn\�\�̒t�1v	\�\\ڭAk��z\�\�	[�\�!\�Z9��\��\�O\"\�#Q��\��\��\��0\�Uͥ\�E�h�P\�\�kR\�͠&\�\�RC\'�E\��\�Sٙ�҂g�.�4X�6�{�@\�XYȖ O��7ԙN^��\'8xY��lo\0�5\�*\�s\\�A7	�q�U�\�Y��\�}��\���z\�y��\n1)]���\�\n\�*\�8R�D\�b�����sz�<\��h`\��(VV�q3jd\�\�\nPz\��Y �\�e��0�\�:ԌJ\�R�0��\�:0_��q_\�焗8����A�D�W��\�\��\�;0\��S\���\�����W\�_s �J\�ɒ�/��*S�\�7�\�\�\�\rb\'�c�DE\�V�<Os\�e\�*&�c\�R$a�\�|�}G�\�YRR\�c\�\�\"�\n_z\���R\"^��Wu\�7q\��[-�\�5�}\��4D�Ү}G+�Y<�$�P\�*3h:\�\�rZ\�ql+k\�1\�ڡ\�A�\�\�\�o\0\�X\�*\�s\\�A7	�qp�U����8��\�#�U�s����xƤ�\�Qr��\�k�\�C\�T�*wq\�n\��\�[Z^��uק}�&-ޔ�\�y��\���k�\�\�g��Z\�)�_�~\�Ld7�H�\�?\�o�:��\'\��@OI`݌M\��(W\�.\�\�f=l\�6\�89�\n\0zV\��\�:D\�z_�z&,AaN\�.��4\�X��\��\�,pv��X���.��x�\� \�sk�\�z�^z�X3n\�F(;T{a���\Z<.`\�o�\�97o7\�o\�\�\�p�\"��6\�a$\�\�o���Y\���H��\�L\�dji\�+�US�Jv�\�\�%o�T\�x�T[BW�\�|ݷ\�zEL������H\n���Y<��\n\�\\y�!P\�#�T\ro\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0',_binary '1632',3,5),(6454433,'nnnnnnnn','aaaa','','aaaaaaa','2020-04-03','aaaa','Masculino','aaaaa','aaaaaaaa','aaaaaaaaa',_binary '\0�H\�*\�s\\�A7	�q0�U�΢�U;W0��&m#�\�q(I�a��\�<�L�\�PqP��}�fq/�6@��	�Z��q��W\�H���\�\�Ra\\\�AA�#��מ>\�p\�V\�*l+׸B٪*Q�\�\�ң�.\����\'��\��f����}2x�{�h��̧w\r.��T��WA#+]���Bpwe\n\�&�巤��/+��r�\�],�ZȦ\�-3�\�ɮﺪ70��@\����W\�#o,�\�1{S+;S�hC\�zT\�F�\�[\�?C\�\�X\�\�\�@��l�Z��F>\�n\�4ICHf\�p]\�\�\�\�\n�kяMj{��+ЊN���Wi5\�k|Z��o\0�q\�*\�s\\�A7	�qp�U�\�����ц�2E\�[Ĺ�b!�-k��B�\�\Z\nD�S�L;Č�80�� �T4�I\Z����/}\0\�\�`�&�vR���u\�Ad\�xf\�\�\�+:\\\�U�T\�P��Az֬�)\�r�F\�|\�|ygU<��x�H\�֫\��r\��T�\�\�p��C%J\�(���m��f,k�\�\�s��\�(*X��D\�L\�\���\03�<d�����S;>C߼68?��HQ�F9h̑�jX7��`;C�~f]4^�\�\�A�W��D��Vh����44\�F��ڰK	*0:\�\�w/�),\r�QS�v�h��\���\�P�\�`\������K}Vn�Y�*\�\�\�e!�\�uYJVKr�׬\\O)\��z��\� {�´�\�aS\�z\�R�\\{�[e@ g\�o\0�E\�*\�s\\�A7	�q�U�ֹ\�?�^\��i�T_gkLBeL\r�R��Շ�ҧY�)\�\�g:~n�\�&��f�7��))7�#u�7\�fɊN�_\�4c��\Z�]I�\�G#�ʅ�4�;�7�\�\�\�ݷ\�.�\��sV�@	×6\�\�kM��\�(햂\�\��\�Mk�\�J}\�lf\�\���P��fjk6�[�\"\�\0�\�\�3I�k\�kBM\�F�s[���5st�Ap7\\J�I�xy��cx\�၂��K~�\n�ja�T\r�Z�D&]f# \�V\�_\nL\r\�]\�\���\�I�L��R#\�_W��\��.@F� �\�f?\�\��M\ZQ\�h�\�\�-�}�o\0\�\�*\�s\\�A7	�q��U�K�c���W��[x\�\\#��f�\�\�VaIKc�x\�Q-$[\�\n6�r/C`V�\0T���׺�1��G\�S\�sF�	�\�\�J�|�ʮ^	\��o\�,�\��eu�by����[�XS�\�[\�=g~��?7\�2�ufI�d>U7\�\�\�|>\0�\�-2g=7\�\�\��)�\��Bf=x@c��\�\�|\�z\�\�5R��2\'\�_恮6a,;Xw\�=,��R�\�bŤ�Nm�>�p\�3	,�\�B�=L�\��\�7:Y\�\�\��Idu�Gr\Z�-8��y(\�\"\�S�oYN29P-bf7\�h��k߆�,��1W�\�\�\� T\�N�,�\�T2Y���\��\�\n<!s�>0Xީd�QW��AP�]��۞\\U3s�f\�x\��_^�\\\�r\����5\�BW�\�o\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0',_binary '1632',2,2),(55555555,'gggggggg','ggggggg','ggggggg','ggggggg','2020-04-02','gggggggggg','Masculino','ggggggggggg','ggggggggg','ggggggggggggg',_binary '\0�I\�*\�s\\�A7	�qp�U�\�e\�5x���\�|�(��X�-�	�\�\�\�$\�\�5\�\�MsZH*��SN�p�X\�\'b%H?ʏ<�j\��3JA3\�\�%S�τ\�AÞ�$�u\�E\�*9\�\�r��Oe\�a(�\�;\�\�|7(S��h�gM\�\�T�)\"���d\�	Y��\��P�hW�\�+�,3{\0�\0�������\0�50\�\���yD%̢�i\�4\�I:���KӤ��f� d�\'>{\�[�X�e�O�\�j�1\�KS�A\��~E�bl�d_��sT�\�W�\��+\0����\�E\'�Q�&��=$9\"��y	�-�T�3�C�8p����\��@�<1�$r�`�$��\n\�\��\��o\0�0\�*\�s\\�A7	�q0�U���\�Q�w\�;\�{6)\�r/�\Z\�S�~މa:�cl\�$MPHE\0��\�\'\�\�a�NC\�6_ؾ24<>%�Q4�Q\�*{ϥ��_����\Z�\�\�t׈=e��\�N,�n6��\�\�\�\�kF��\0�N\�U+�ĩs�Fa`�\"oh\�:�\��\�le\�RK?p?��\0����\r2��\�ml>U�\�q\�P(�]�d>���\�99G\�\��\�&�w�c>\�#G\���\�\�\�F	[\�\�X�?\Z�ޠ&vϘ��#O\�=5>�0�.*͉A��\�F\�O*wu�.mxj�\�\�SʱƜ���G�\�\�\rC�MN\�o\0�U\�*\�s\\�A7	�qp�U�\�Lր\�?\�rZ��ƛ�9[��\��[G\�^���\�c���\ZN��|�͂\�<��@�&s3���\�\r�ܨw\�\Z�\Z)M^9�\r\�~tB\�\�[QT�خ!�b���\�\r!�\0�ՁI)m\�n\�\�7!:R�VD&\�\0Q\Z@�+�7,\0 ��\�s��1\�\�\�bJ�\����}4S��Wt=�9�*��j\��\�[]	�!?c�����\�a!��]�gu��I�9\��RӣZI�\����O\�D�y\�\�ߋ\�Y��e@��\Z>-�JC��Y��\�)S\��j�둼X\�gj�B~3����c�~ga�U\�]�\�Ⲑr=�|�\��٥7+`\���䛯\"g��t��{o\0\�-\�*\�s\\�A7	�q0�U�\\t�a\�S��4u\�!P}p{\���c���6g��i+-2�,�Q=�m�	c�|�\�nAF#����M\�\��\�%��n�m�\�K��5{5�\�Y@^r\�\�W\�`�o��=Yn\�_���aA��U!�\�3�\"�\�Ղծ���\�\�p�\�\�y5���7H��v�\0E��m�(\�z�L\�\�!\�T\�v�V\'�`\�o�\�\nq�<��	Mm2M�C|�1\�\rq~u�Ot-�uz�\� �	mJ\�	;~\\\�?\�t�	10e�e�\�I\�,����3�JǕ�\��Wy\�\�\�C\�M8���\�WJ�\�Po\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0',_binary '1632',3,2);
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
INSERT INTO `institucion_academica` VALUES ('2','UNIREMINGTO','PARQUE BERRIO','3333333'),('A234','San Luis cvb','Los LLanos','6666666');
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
  `tipoDocumento` int(2) NOT NULL,
  `cargo` varchar(15) NOT NULL,
  PRIMARY KEY (`idPersonal`),
  KEY `FK_personal_salud_tipo_de_documento` (`tipoDocumento`),
  KEY `FK_personal_salud_cargo` (`cargo`),
  CONSTRAINT `FK_personal_salud_cargo` FOREIGN KEY (`cargo`) REFERENCES `cargo` (`idCargo`) ON UPDATE CASCADE,
  CONSTRAINT `FK_personal_salud_tipo_de_documento` FOREIGN KEY (`tipoDocumento`) REFERENCES `tipo_de_documento` (`idTipoDocumento`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `personal_salud`
--

LOCK TABLES `personal_salud` WRITE;
/*!40000 ALTER TABLE `personal_salud` DISABLE KEYS */;
INSERT INTO `personal_salud` VALUES ('','','','','','Masculino','','',1,'4'),('111111','sssssssssss','sssssssss','sssssssss','sssss','Masculino','ssssssss','ssssssss',1,'1'),('111111111','qqqqqq','qq','qqqqqqq','qqqqqqqq','Masculino','qq','qqqq',1,'1'),('12345','ss','sssssssss','ssssssss','ssssssss','Masculino','3333333','eeeeeeee',1,'1'),('153274','dd','ddd','bbb','bbbbbb','Seleccione','77777','hhh',1,'1'),('15327400','Nelson','Giovanni','Salazar','Roldan','Masculino','3136850154','nesalaz@msn.com',1,'4'),('22222','ssss','sssssssssssss','sssssssssss','sssssss','Masculino','sssssss','sssssss',1,'1'),('223333','22','22','222','222','Masculino','222','22',2,'2'),('231455','RRR','RRRRRRRR','RRRRRRR','RRRRR','Masculino','5555','gDGSJS',1,'1'),('23234','wwwwww','wwwww','wwwww','wwwwwww','Masculino','333333','eeeeeeee',4,'1'),('2345654','ttttttt','qqqqqq','qqq','tt','Masculino','3424','dsefr',1,'1'),('23459','ffffff','ffffffff','yyyyyyy','fffffffff','Masculino','ffffffff','55555',2,'2'),('332211','aaa','aaaaa','aaaaaa','aa','Masculino','3333','qqqqqqqq',2,'1'),('33333','dddddddd','ddddddddd','dddddddddd','ddddd','Masculino','rrrrr','ddddd',2,'2'),('3333333','33333','33333','3333','33','Masculino','3','333333',1,'1'),('341234','gddd','ddd','ddd','dddd','Masculino','33445','swhdgdh',2,'2'),('432567','ñññññññññ','jjjjjjjjjj','jjjjjjjj','jjjjjjjjjjj','Masculino','888888888','yyyyyyyyyyy',1,'2'),('44444','zzzzzzzz','zzzzzz','zzzzzzzzz','zzzzzzzz','Masculino','33333','jeuddljf',1,'1'),('4444444','ffffff','fffffff','ffffffff','ffffffff','Masculino','55555555','rrrrrrrr',1,'1'),('54637','aaaaa','aaaaaaaa','aaaaaaaa','aaaaaaa','Masculino','aaaaa','aaaaaa',1,'1'),('54678','hhhhhh','hhhhh','h','hhhhhh','Masculino','yyyyy','yyyyyyy',2,'2'),('55555','ffffff','fffffffff','ffffffffffff','fffffffffff','Seleccione','fff','fff',3,'2'),('65798','uuuuuuuuuuuuu','uuuuuuuuuuu','uuuuuuuuuuu','uuuuuuuuuuuuu','Masculino','66666666','yyyyyy',1,'1'),('67890','vv','vvv','vvvvv','vvvvvvv','Masculino','vv','vvvv',3,'2'),('7654321','ddddddd','dddddddd','ddd','ddd','Femenino','dddddd','dddd',1,'1'),('7654356','Guiller','','Prez','','Masculino','4444','ffffff',1,'2'),('889900','wwwww','wwwwwww','ww','wwwwwwwwwwww','Masculino','4444444','ddffggg',3,'3');
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
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `personal_salud_titulo`
--

LOCK TABLES `personal_salud_titulo` WRITE;
/*!40000 ALTER TABLE `personal_salud_titulo` DISABLE KEYS */;
INSERT INTO `personal_salud_titulo` VALUES (14,'33333','2','A234','2020-04-09'),(21,'15327400','2','2','2020-04-02'),(25,'15327400','2','A234','2020-04-09'),(26,'15327400','2','2','2020-04-09'),(28,'111111','2','2','2020-04-08'),(31,'15327400','2','A234','2020-04-01'),(36,'889900','2','2','2020-04-09'),(38,'15327400','2','2','2020-04-01'),(39,'33333','5','A234','2020-04-11');
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
INSERT INTO `tipo_de_documento` VALUES (1,'Cedula de Ciudadania'),(2,'Tarjeta de Identidad'),(3,'Registro Civil'),(4,'Pasaporte'),(5,'Cedula de Extrangeria'),(6,'peijr'),(10,'');
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
INSERT INTO `tipo_titulo_academico` VALUES ('2','Medico'),('4','pacinie'),('5','Tecnologo en Atencion'),('6','Auxiliar de Enfermeria'),('ha','erty'),('yyyy','tttt');
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

-- Dump completed on 2020-04-13 15:26:38
