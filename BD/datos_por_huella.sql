-- MySQL dump 10.16  Distrib 10.1.38-MariaDB, for Win64 (AMD64)
--
-- Host: localhost    Database: datos_por_huella
-- ------------------------------------------------------
-- Server version	10.1.38-MariaDB

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
INSERT INTO `cargo` VALUES ('01','APH',1),('02','Medico',1),('03','Enfermera Jefe',1);
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
INSERT INTO `datos_persona` VALUES ('1000332222','Pedro','','Navajas','','1954-09-25','Nueva York','Masculino','Las Prostitutas	','Robitis','Muerto','\0�[�*�s\\�A7	�q��U�!�?�H���~V��1���Q��`$Ec%��̓���-�;F��n�O�8����qa�V0G���\n��<i�<�(^�a8��]�2��*�4�A��<��ă����d�i�1_c�k*��l=��R�r��#��w��v�5�1�7\0�C_U_��o9��ܷ�u���d�?3\Z&O�2�g+�2�q`dm&��N7H\rE;�3�����@Z���W`ǝ�]<������\"=?r��+�Lk�w\rn3o)Y�f�/�\"X�X�%f?�c̗f�Q��L�U�:��F?\0KŤ�#;	�Opj5ċ�h��\'��f�IՂ\\��[9Y��9��Co\0�~�*�s\\�A7	�q�_U�Sn_f�s��;���in�J&߂:�Q�s$�uep�8^�Λ��Ҧ���a��J�����П��)��G1�fG)6�R-�6�_}ٱ�:_��n�ʖg��]];�nW�n���G�U�-3f\n,�Z���7�/\r��v��Z5\'}�YEj\"x�i���m�c(��㔠����]��������������!��=��ODK�J\Z��l�o٥�u�>\0�\Z�\n�������}�|�0� _t��f\n�Vஒ0q�8�w�P�\Z��kI��:	Qdh����҄kk��L�I�5M��6��\0����1��G}�^u�M%*�9����.�9�F���uDD�K��6�AԴN@�g�?x�rF���eo\0�_�*�s\\�A7	�q�U��$���I\0�\"vʍF�f�r�(��dH�12�|���ZSK�U���sPt���\\��8ߙZ��(�8��2	���I[���>*>9��/����va�Ϙ�a#�!	-#�ʘ�*������M8�훽�4�o�+��$�\"�6h/��0�a�	���S�d���Ⱦ��Ah������x��G��Ryv�.��N�v���a�q/H��C��`�������NT<yob0���[\\J\r�J�T�>�^E�/r���,X�����-7ρx���(�������j�N�����Xq\'��w���\0����j4\"i���Ud����+~�p���o5x:�D��o\0�~�*�s\\�A7	�qpUU���C���V�b�]\n����t����D\"�W���?bq�.����iĈ������Er�+�c&>:�tow��O��\Zg-�ģ���Kq2��q�[0��r��\r�nlR$Rf��I�@�{N�qN$W����g�!��3�^���>�1��~i����&��`7P�#�P`6���ϵ��;S/x_[�l\\=�p�|����[G�R t\"�S�E=J5;�ۛ\"�5�)�bU8�K����73\'�/�?�m�yfX6��j؊�0۶s�.p]xd&��ZО�*�����yI���B������#���UY���!s�[�����0�����Sos���U��,u΀��R%��ò��ߐo\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0','1632','01','01',1),('1020303388','Thomas','','Salazar','Osorio','2009-05-22','Bello','Masculino','no','no','no','\0���*�s\\�A7	�q�DU�do�qu�&���t;����Y������!����TC3�\0MP*A�í�<��VQK��k����N�_�M+F�KD_��+�\n�o��lBh���.\Z B�;�[8\r�����|ڐ�Z�\"�J��U,/�v\nc�p�E�R�<�D-�~�b�bf�9\ZREeL�+�/�K�V)\Zc����.X���K�Q���l\'��@]�VP���t����Z_�j�<�rչb��_�k2������R��\'�׳q=�XS=�IC���dԎ��rF�&~���P���������c�K^����=����\ZCK|��LVh�[>@�u`=w(\Z��?D�>�#\n~�֕�?�VM(>=�X:�eZ�����m)�C��f�I�^?o\0�k�*�s\\�A7	�qp�U�T��� V %���۔�Z����&KN|:q��f�~FZ���u���o�6K�U-~�q� Ŕk3��~TU>��#��ȥ�q~���𭖠��R��ذ��7Զ�/�CE��)���U�}��H�בQ�߇�W�����W\\����>)2\"��a2����@�Y�����K�\0�6t�\\h/���u��\0Í��CXZ��J.���`̈%\r�^e2\0U���b[\\�R\"��5X�Dz��\n<?�t\"�篇�<�i��GR�_�}�D���=�Q�Du�+t�V�#o��?�y\"�� �W�fG��OPd��X:���f��T���O��Ž⓾�F\0Ҳ^/������D-ߝ�\0�,�o\0���*�s\\�A7	�q�@U�ch{�|�B��\\�D���t�\Z��>�D-dC\\��\'��^3 7��5K�	UMu���<�Ďv4����U��_S[�m���W�\0n2����#��:E��7?@��@V,!�%��3���`�1p���a-�F&H���N�e(Pw��m�1,����K�ۆ����,�L��@�-6��ijd%���z��(+[X\0���ಟ1���>FcSS����۔��ك����cb�h�t���eH.�(�۵h���Q�4������;���\'�G����f��d��#_���G�Q)�\'+�<�\nCp�<�[.@,-�6#�l�8S�Bj�����z��cs�myg�78I�(�U@��e�JDZV��D!�o\0�o�*�s\\�A7	�q�U��,��k)�L �\"��9�]���h����B1�͖)��_�]�m�Uqv�:B/^Ap�<����W�i[�d�ˉ�;f�t�^�=�2��n��5p;�_�!�^\'x⛑�=�/Y�|�o������x/�ga3nU�|E���qY�����}�]p�c���a�\":=L�K ��K��_����=�&���=6Y~�hB������@=��.iY���P�l�ͰX���?�O�g������`����y�K=[mp\r9�pɞ�>t�`U�@�ܱ�\"�/|���A��zaߕ�����273�\r�yf!Mc��D�>B����-;m�������	�vŀ\n9*��#$���o\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0','1632','02','01',1),('1111','ss','ss','ss','ss','2020-05-23','sss','Masculino','s','s','s','\0�~�*�s\\�A7	�qp�U�u|���娑~���;��A���Gz{�8�`\Z�ZXL/��/	��6oCh�V��m�{����*~D�]�K\Z� ��ǁ���8�6G_R?	������u�x�R�T���7�|������+���a��\r�E��s���/~�OP��J�F�Iuf>�D�ng�а�A�k��omq�0�[F7��~��aMA�a�	�?F|�x���_\\(�OS��0h�x�t��	�T�y�2]����{~c4>Ve��\'���j��`�A�}�y@	Y&nr�E��$��{����?�3}%�df�@���-˕�H�p3x�+����c��.1.s�����@Ǖ_T�)z~|\r�aB���ՙ������o\0���*�s\\�A7	�q�U�\"e#L4\\!�w�L�j@{)E9���p?P;�ϒ��� H��>��^$}ۺ[bQ��:X�sE\rK7�oH[��\Z�6n����+�3a��b����9�mzp\"5���h�$�W�^b���z\\��C�5��=��}7�$�4]îDAe�.�聀�#U-ŝF�10�ܸq�&Q��\r�ݍ��S\'�(%7��I�\n	�O�l��`�15x�`{Gs\nՄ��� ���&��}]!\'e��zdh��(�<�	a������i�c>T��x�\'h�m�i��)\n.��ċ!��\Z�F���į�ӵ���.RB\'/��z����5(�/���UG���;�yGU��IN��N���1�M9\\���?!8����o\0�i�*�s\\�A7	�q�U�[����U��k7�y�@Q��u�����<�CF,N�*^��Y<٦�� -�Ns$������)���iTH�@�!Ik�J���Q��A�}\0���u& ��y�.�Dڄ|����\r��n�*���ȴ�Hi�C�Ft��m:���������IZ�p�m��oa8�R3Gk�Pds�Z�h�-ݝ\r��5���1�Q�E��Q��?z8z�\\���\'5�����`v]�m���|v�$��X��<��N�g�4�$��`�$8\r��VF��J᪡\0���dÖ��\Z�a��:3:f}�)�˝#��\0K,�``��[)��$�\Z2�� G�ft�O*���جּ�/��	�/eP��\"o\0�s�*�s\\�A7	�q��U��k��*��戈��O�\0�E�ͫ��\r]s[D�H��m���>Ҵ������S����W)�l�-��_������3\\�@�P�5K5W�B���r�	��i��n�󝶜Ճ�?W�3Tu)d�ܟ�3��t������E��������rB�I���=z2�@\"$�m�U\n$�l��r�1�/-�;CcT���u����O��5����*�흴G8��2����s&���=)�E�k�:���y8_]L�ax?/�����8��;���s��r|��b�!	ɯq\ZD@E)q{���-�M`���#�Aq������LJxt��W$�\n`[H���#�J6Zo\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0','1632','01','01',1),('123456789','Maria','Angelica','de los Guardias','Hernandez','1975-12-31','Guadalajara','Femenino','no','no','no','\0���*�s\\�A7	�q�U��\"�}����h��E����I���Yl1��q�,-���0ONB���˥)X��N��А6\0ک��0�ltl\"l��J�e|4�Y�ե�ɗ�ꭾd)�P`��߽������!\Z�e��#>mg\npl�l��h^!��*�?s@�,%��D�	3��Y_w��NjT��p�v:��y�q\r�쓞�A¡�%��6\"���\0��:��D�h��8��T�Օ�\\R���G�M3J�@ <�[\"\r.��L.�#蒿�`&��o�%�\'�(2�>�j���%�����][!PE)�/ުoM�/�������4	ql�FF�ʄ~�r�j���\n3R--���]Ǆ����+�w��JHH���r78n`�c�>�@�\r��E���1o\0���*�s\\�A7	�q0JU�� a\Z`�L�����(�l�j�w��~��u��}���cѼK%xBNr;�@.*x_��u��ix��@��mT\'�@\\�v�3c��[y�2D�n_`�\Z��B��>*W��+�jCܱq 廄A	*V���\'��F��a���������\0Nַk�;R@)MA�x�א�ކ�X����&��52U�0;�����{?B�ʖl,c�|8C�ҝ�ʜ)�D��\r�0��>%Wa5����I	�]��11�kɻo�\n$�����]	_]�tV5\\��V6�>w�~�~V���oP� ��1Ȅ\r �r�FŊ��\0C�!�]3�j�$4%r���\'\0*��C^9��͛�7R)4K���ѐV�����)�\\�u�o\0��*�s\\�A7	�q�IU��fZ����Z�,%������7v#��PĘ�B��pi�8��OEK��9q-c�ɡѦCc=j谹ʟ���\'�}+�c e�,�3�$H�Uǎ�+۴�rFoM��WΓ���܏q����ο�d�����apiAyn��:��ܠ��Gf�8g�O��zE!h�:�Hdx��(W�V�!��ئ����,�P�R\ZTqiv��������gs��h�Zdy�������tH�5��W��\'�ě�M}�˙��;� ���`Z���\r����<4?�������x������\n;�T\Zʯ$�^�d��^\Z�Ɣц��x6b���]\n�܎�cܐ\0J�dZ���U�h\"8��&�-��n�\ZB덿��o\0�~�*�s\\�A7	�q�NU���ϝ�����N&���Һ?:(li5�X�\\�e������\\sG��m>���<Ik�kO���u7��x �+H\0Z�\'-`���;+�7mu�������1�v���9\0���_O2+e\\������� NE�6Y�n���&7V��rzdO�*N�F+.����8�c��z#XЙ�]���$`�j�0��I��L��+�8$���%4Tf\n�\\��?�Nk��풊��#�(&���ي����@!G�#�����\Z����燗�\"�e�\rl\'�\r�&�\nrRr�tƒO���2���^�W|�קEW��?x��,I�OG�v���7�ko@����E@�Jޥr#��o\"GI�V\"�Rzo��o\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0','1632','01','03',1),('12347689','Juanito','','Alimaña','','1980-01-01','puerto rico','Masculino','no','no','no','\0���*�s\\�A7	�q�MU�bYb�ܭ.շF��6��s���p�O5�S���]�w�\"���QX�_�dT�B\n̮�(�.<\"�Sj�\\*k��emŃ9^Wɑu|�\n�{�w���˾��A�.�c���P\n�.ά\r�n��P�D��$=���a$�Z���G��!�3�,���j��GԘ!He�;�.�d6Xw��eʜ\0���9��3\"|�%1Tm�m���,cF��	����M\Z�OЈ�7�݊㬥��g�]U��dZ=:ܶ_�D�Fb�=�ȷ���ف	N-H�Vʺ�(/�@�l�7y�\r�����u�H�G9���v: �ɔ� ]���6�=q�Nْ�J�(�ȓ�u��/$�@��d���b/=X�w5��/W\n(ǟt��)4�o\0���*�s\\�A7	�q��U����0Cdl^��b��1���w��N\Z�\'a��{�(�qX 0{�e���+���j)s���=���	�uZnyAf��\\�/��fX��2�Q3�k�?��C��C�PzJEz8g���|��V��w���;R���d�T���Ѭ��2�����*��;o�9�UX��(�)��/4ˏlb�f��*Q�F��|���y5���d�x��r���|����)�	�e\\�̶A����(Ô���e����%�}�tt��$�+m����z:\rlY��qJ���\"�x�H/o%��sQ5P�w6B1s.�j78A,\'�m��\"xYa9j��[�*2�s��s	�bVE��2�ÿ����i4�}9������̛HXWP*�/���I����5o\0�~�*�s\\�A7	�q��U���v���x�>�M�N�1ayW�H���=�b.ޖ(�|�\r��.M�P��-�%ؙN�ql�!�$�\\`���_꼽��P�$(+iL��:���o[�g�\0��d��[�������,=@�/�~f侸��\'C7_��x�F����T�<�����*S���n�5������B\n�x)�%}Q0	��Q���5z�{J:&Ҿ�@��o�	�aG\r�s����ʅ�\\�J5����r�S����Gdn�f���a��E#{�f�{�EdX��F�y����2������4b�u����ÛW]������Z�s�n\n��������n<��ߢ��:P��MkJË\Zk�����&�t����˙���21f�o\0�~�*�s\\�A7	�q0GU��V���d$���C�\\wL�Go=8��3����Up̬Fۍ�I�,P�ja�,�Z4r�=�|\'1�͖y�l�칌K\\/\0��ZZ�H�R�����Q��ީu�g#ځ���Ƽ ()�_}����_�c�\"/�I�꟰�Vp��Oܮ�ٻ���$	 @?S�-��S^���`#J`M�U�	���xMS�خLd���O��\rP�aoE�`�ݙ)D�ˎ��R�^鮒s���,��@k�F9N�v�gT�휚����\":ߵ�!4`*��s�C��*[�2)C�|�����*�]���*Ik������V��T����x�-~��W��u:�=�`o>35����_�ő��x��y�9D{�2�\\�o\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0','1632','03','02',1),('15327400','Nelson','Giovanni','Salazar','Roldan','1973-08-26','Carrera 78 A # 67-54','Masculino','no','no','no','\0�Z�*�s\\�A7	�qp�U��wx��șg�/���5�z��e��=�J>/�fV�t�bD_�;l�2֛�s�&�VR��ʘ��|��)�.	PA�����>�x��w�&4^��Ķ7��� JtWZ^�t��\rE�N�0z��\Z@3ܺ4�4�	s4F)8�;S�+l����_��Uד�sN*^��� �-�� D�f�(��PZ������;	�<��f�s�\r7	.77/τ1,r�P��6+���$�2�!�@��~�E�ѱL	)�\na�Ϊ�u^�/���F�̄��w���I�8un0��#�;���#g�bC��5DPCQ�@Qs���aVg����ϳ�Q������`�%Z��0?�.�Io\0�d�*�s\\�A7	�q�U����D:�M4�eߚ�e�\r�h:�[��̲|��~H��,��T��Iѯ�*[+p�/vQ��R���3�VjV.��sjZ��j�-�����qj|y8<7=��I�|^����yL��[���eT\"0�%�1��WL*<����Q\Z\nq�9^bk��vUJS�(Y�GX�@�-�u�C�E�!\"\'?h+�I�m�,��\\E��za%����l|����@�+�G�_c��~@ؘgO*�����P�x�����[\Z����S��-�~�3}l:Q�f�6��N=�\'}v%�3\\�Y�瞲�Ob�0P�ǦH�6b��׿��5�)i-�� >^a���������r�Ҷ+���v�u\'9�o\0�\\�*�s\\�A7	�q0�U�k8[*Z1U��{ǘ��s�\'�\'��#K�j�x��bRl�z���G�`��h�H�c�R(9\n��������%�֖��dю?t��C�M�-�ܵ��\0�@�<Q��G���l<g#�_�[�h(�����{^-aϹA�sc\'i1>q��)į%JYrČ����U����lf���ha�ϕ��G���@(�	������F�6�(*�`w�xi)��X�13��/�m�ܩ_	4��F2s�hT5B\r������E��*���u�߹u_n9��*v�LM�\\Yo�j6��[.Z�ɿJ�l>��\nioQ�F-�nS�}�D�0鲽x�-��#�=E^��)o\0��*�s\\�A7	�qp�U�Ȥ^%B�$�����C�֟Ys��^!�挎�ۅ\n�(Y����	�Ĺm�~����n�3��M>�\nec*<��T:��%�I�5�CU+O��x6au�(��1�\ZKF��Bey��	s��O�\'+���;V��%pGB:C��A0^���F���~w+����3�������0V�/����߼��4�c8�V���X��ߦ_>1��{!�Y��cS��\'s���>��i�vm���f|ɯ*��;^���{���|���4;�H����%m4\Zo\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0','1632','01','01',1),('222222222','Paquita','Rosario','La del Barrio','Mendez','1990-01-01','Armenia Mantequilla','Femenino','no','no','no','\0���*�s\\�A7	�q�U��\"�}����h��E����I���Yl1��q�,-���0ONB���˥)X��N��А6\0ک��0�ltl\"l��J�e|4�Y�ե�ɗ�ꭾd)�P`��߽������!\Z�e��#>mg\npl�l��h^!��*�?s@�,%��D�	3��Y_w��NjT��p�v:��y�q\r�쓞�A¡�%��6\"���\0��:��D�h��8��T�Օ�\\R���G�M3J�@ <�[\"\r.��L.�#蒿�`&��o�%�\'�(2�>�j���%�����][!PE)�/ުoM�/�������4	ql�FF�ʄ~�r�j���\n3R--���]Ǆ����+�w��JHH���r78n`�c�>�@�\r��E���1o\0���*�s\\�A7	�q0JU�� a\Z`�L�����(�l�j�w��~��u��}���cѼK%xBNr;�@.*x_��u��ix��@��mT\'�@\\�v�3c��[y�2D�n_`�\Z��B��>*W��+�jCܱq 廄A	*V���\'��F��a���������\0Nַk�;R@)MA�x�א�ކ�X����&��52U�0;�����{?B�ʖl,c�|8C�ҝ�ʜ)�D��\r�0��>%Wa5����I	�]��11�kɻo�\n$�����]	_]�tV5\\��V6�>w�~�~V���oP� ��1Ȅ\r �r�FŊ��\0C�!�]3�j�$4%r���\'\0*��C^9��͛�7R)4K���ѐV�����)�\\�u�o\0��*�s\\�A7	�q�IU��fZ����Z�,%������7v#��PĘ�B��pi�8��OEK��9q-c�ɡѦCc=j谹ʟ���\'�}+�c e�,�3�$H�Uǎ�+۴�rFoM��WΓ���܏q����ο�d�����apiAyn��:��ܠ��Gf�8g�O��zE!h�:�Hdx��(W�V�!��ئ����,�P�R\ZTqiv��������gs��h�Zdy�������tH�5��W��\'�ě�M}�˙��;� ���`Z���\r����<4?�������x������\n;�T\Zʯ$�^�d��^\Z�Ɣц��x6b���]\n�܎�cܐ\0J�dZ���U�h\"8��&�-��n�\ZB덿��o\0�~�*�s\\�A7	�q�NU���ϝ�����N&���Һ?:(li5�X�\\�e������\\sG��m>���<Ik�kO���u7��x �+H\0Z�\'-`���;+�7mu�������1�v���9\0���_O2+e\\������� NE�6Y�n���&7V��rzdO�*N�F+.����8�c��z#XЙ�]���$`�j�0��I��L��+�8$���%4Tf\n�\\��?�Nk��풊��#�(&���ي����@!G�#�����\Z����燗�\"�e�\rl\'�\r�&�\nrRr�tƒO���2���^�W|�קEW��?x��,I�OG�v���7�ko@����E@�Jޥr#��o\"GI�V\"�Rzo��o\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0','1632','03','01',1),('244224','fernando','','munevar','','2020-05-14','castilla','Masculino','a','aa','aaa','\0���*�s\\�A7	�q0MU���xI�fǠ��%z���l�E���;�o�3������q��5&��{a������7?��\nB�(��\0��b�;[�jw���%��.J+@J�Y�?�	]�Gc9�����&�#<\r�j�㫅A{�&�L �ĭ�����4��K����1{�q:��g	 �f���AljN���%\rv�I���W��l5���I�s)1�P�QZ����]�j�F��@KU��K��uʞO\'J+����Af�A�a�\\�(����YE�D,��	U\r��\"6\n��r��ڇ9f�8��ǅ�޸W6�ښ�����cN�V�\0v��L-�.�@ӄ�.��%�+k�e@J�k�\\m�`q�(��]�Ƞ�}rG���co\0��*�s\\�A7	�q��U���=��빖g]՚�K;�²f�z,_�\n��N;E���Wp���|XC���o0߈�	�a6Q�:^��)�2�mr]�_�D\Zpfw���B*���h��yY��ɠ\Zglh��ݩD��X��UE�P�ls$L��.��6��C�ל9��%*�7T� ����W�{���Ev\0�[��UBR�E-H*���^�3E[�4��?��\r}���:d�cm����n��6\'�Ʊ8�X��6\\A����14�i%�vh~k�m.�Uf%Zzl�����Z��ގ<���O��\rwd��S�ڻɏ�wc���@u$�=>���M}���-`��ǳ�:�UWc^.L�|w��{��$�F��R�K%���2�\0�t�0�;�o\0���*�s\\�A&-��3N�BF�&#O���*2m�I&\"FS���Q��k���?���m`��i���s\0�Rt�J��;Y��p�u��K����׻\\�X\\��ܽ����|��֛��8eR�@�ǘ)�@\r�ŗ��//P�������-/V���&t��udˎ��dg��\0�U���j�����C^�T�94|\"ro�tAރ�+��S��g�ω�3��Yf��S\Z�蜯l�2$n����;��RNy3Ld*��������\0�������`R�&*�.}m��}	��g�KK�FRAl��5�=��<:kx{E�j]�XF$�?��u9��X�#<U�&,�<��ZҊ��\0���I>�����fo��Ve��E�n�E�o\0�~�*�s\\�A7	�q�NU���٪9�Ѣ������B�N��\rĥ�<k���8J,�?�y<�B�`�Q�\rqY5e�v�����mǧl=a��Q@{恓\'���b�e��rE��\ZJ��RHO=�}�i��^puN�Y��U�}��([C4��1�\\a�d(�z�Wjp����7��� ��\'�wbo%����P��~�Je��T���j_��f��#��-?	J ������[%���T�w�W�}�g��m���+��/�rg�zS;9ċ�x�_�r�Wz���q��9��P���׃\n3\rX9�j�N練��v��y��_x��ΈM�^$le>����*����UQIy�1JDK�z�8I�o�\n�uD���W�x��S@�s�6o\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0','1632','01','01',1),('4444444','Stanislavo','Armando','Kiatosqui','Correa','1954-01-01','Ucrania','Masculino','no	','no','no','\0�]�*�s\\�A7	�qp�U�rb�{�\"�ѭ����[����:�����^�D�0��J�(�$���,5���J,u~(q��sl��}X\r/�u�}\'�랜����*��=�:J�,�q��Gȯ�>!j��	ۜ*���{�],�t�{M��}�\0Sͳuء�!��b�ɈiD}n��QĻ�	�1Zz�Y}>��#L,A_�hD؁��\ZI�O98I�7i@%�Gf�ƛ���g;��)��\ZB�ʝ�f $z���7o+�_��_�<�rm<�߱�0�`�?z\Z�kk/��to\'���v|��3N�G�v�C|�f`�L�{+us�M4�Mi8P�3�K�R�9٤)\n��(Lo\0�\\�*�s\\�A7	�q�U�ú�bF��j�(�w(���m�����0�]3�Ȧe�\"����kq�b�0y�����r*<k�+��\r�^��T�>��=���,7��ss��T�W��\n/�~� &VRL�FKf\Z/��.�$mdc�����	��W��\Z�5�a6���]�~��������t>����TJZE�\0����/���W�\"�C[��oL*�	\Z��U�{���,}5C|S��鈤3<�Y�K��G��ɍW��Ž�g��I�B��&fJ��&J߅v2s�f蹉Y��V= �Y7c��!���V��r��2��*c�m�_�]�:�W�s�]��ndN����x�o\0�`�*�s\\�A7	�q�U��\"�Ge��\"�r�u�P�ʁ�����1b��?h�����U�=���s��W����a���\nD?�ǎ�=J�Z�#����ye+�e���t�������s��G���Ӌ��FA$@G�x�폳����ĥ&y��%���Ut�h���{j�9!co�7##��j{�\\$�pt}R=����^�r)o��:��T��g��\n��c;e� �=Y�~���7i�\0��Q��XIUt10�L^�j��ï�j=�^#\'���Z(�?.�و\0����y`bO�ho;\n~��u���67�����$ΦY�&E#VD謮@%�\r\\I\"x\Z�J[�ԕך����]Y�\r�\"��Ro\0�T�*�s\\�A7	�q�U��\"��o��(b�7��I��V���<�E�cO���{%�e#�MrEC�%�\n~��ϳKj�Z?�*<�qMߝ(V$��noE����+Z��8T�	e��i��|#�����6n����u{��z��\ZMJ� ��8Q��B�h�F<)�����<�T�UA�D����9CT�\0a*+�y�,|.�t�<���!�+�/aMA\'AuCb��g��f�>�#|@$��c�ٽV+�0pL�Rs��M�(d��<���#,�p9F�^KmӨ�0	�8Ρy��D��u�e�?�@;O������_5�QL�>UL~�VM��-���RC��o\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0','1632','01','02',1),('5433456','Pedro','de jesus','Paramo','Cuellar','1965-08-24','Peru','Masculino','no','no','no','\0���*�s\\�A7	�q�DU�do�qu�&���t;����Y������!����TC3�\0MP*A�í�<��VQK��k����N�_�M+F�KD_��+�\n�o��lBh���.\Z B�;�[8\r�����|ڐ�Z�\"�J��U,/�v\nc�p�E�R�<�D-�~�b�bf�9\ZREeL�+�/�K�V)\Zc����.X���K�Q���l\'��@]�VP���t����Z_�j�<�rչb��_�k2������R��\'�׳q=�XS=�IC���dԎ��rF�&~���P���������c�K^����=����\ZCK|��LVh�[>@�u`=w(\Z��?D�>�#\n~�֕�?�VM(>=�X:�eZ�����m)�C��f�I�^?o\0�k�*�s\\�A7	�qp�U�T��� V %���۔�Z����&KN|:q��f�~FZ���u���o�6K�U-~�q� Ŕk3��~TU>��#��ȥ�q~���𭖠��R��ذ��7Զ�/�CE��)���U�}��H�בQ�߇�W�����W\\����>)2\"��a2����@�Y�����K�\0�6t�\\h/���u��\0Í��CXZ��J.���`̈%\r�^e2\0U���b[\\�R\"��5X�Dz��\n<?�t\"�篇�<�i��GR�_�}�D���=�Q�Du�+t�V�#o��?�y\"�� �W�fG��OPd��X:���f��T���O��Ž⓾�F\0Ҳ^/������D-ߝ�\0�,�o\0���*�s\\�A7	�q�@U�ch{�|�B��\\�D���t�\Z��>�D-dC\\��\'��^3 7��5K�	UMu���<�Ďv4����U��_S[�m���W�\0n2����#��:E��7?@��@V,!�%��3���`�1p���a-�F&H���N�e(Pw��m�1,����K�ۆ����,�L��@�-6��ijd%���z��(+[X\0���ಟ1���>FcSS����۔��ك����cb�h�t���eH.�(�۵h���Q�4������;���\'�G����f��d��#_���G�Q)�\'+�<�\nCp�<�[.@,-�6#�l�8S�Bj�����z��cs�myg�78I�(�U@��e�JDZV��D!�o\0�o�*�s\\�A7	�q�U��,��k)�L �\"��9�]���h����B1�͖)��_�]�m�Uqv�:B/^Ap�<����W�i[�d�ˉ�;f�t�^�=�2��n��5p;�_�!�^\'x⛑�=�/Y�|�o������x/�ga3nU�|E���qY�����}�]p�c���a�\":=L�K ��K��_����=�&���=6Y~�hB������@=��.iY���P�l�ͰX���?�O�g������`����y�K=[mp\r9�pɞ�>t�`U�@�ܱ�\"�/|���A��zaߕ�����273�\r�yf!Mc��D�>B����-;m�������	�vŀ\n9*��#$���o\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0','1632','01','02',1),('71660668','Santiago','de Dios','Higuita','Campo','1965-01-01','Girardota','Masculino','Penicilina	','Hipertensión','En Cuarentena','\0�~�*�s\\�A\"�3\Z$���$f�{˦Z�:L��P�wwf�m�m��wiH6�u&||c����<�iR�h�cS���K���=z�^e�C9�+h���Z��&Hǀ����)e�?1���Gg�w�D/D�ގ<:S�(��m�Mc���wx���<TT��+�ƞp�iZ��Za+�3P���=��mpa!��Jg�5Ջup�����xiI��c��	��o�<�%c+x�b�����\"W���C�+)B�0�4ic[�΄����eV�5�\0��O\"� Ɋy����X��M;rR�RO��o03!jI��ɕ\0���LPR=/\r������np�B���\"#�	6\"��aF	�n׾�����\r콶��{nJyzgU�)�(�����y��cH7NW�%{o\0��*�s\\�A �1c\nbAt�z�.X��Em�b��PҕC$!4D:U�\Z�`ݨ5]\"Ƥ�����\'V��\"�M�Jtv�v���-����EiTۃU~�˯�������\\���g[�7��oj)�\"8���ћ1b>�ŏ���?wt(��������`,�ُ�E��9>��l{O��0���)����\Z���\rˈ�z��O��p)鴅�^^m��7A5���s�#\nʑ�=\"=䀗/DB\r�ϰ5C;z2$�0L������/��@zm�(�a\0�<�<����#�rA�8���|T	8+��]�`�q�f|�l�>��K��(�����A���8DN�6�����t��SPZ(V�Q9�������k9��R]~6H�E�gmO05�Ab�眪��(�~�o\0�Y�*�s\\�A7	�q0�U�>�pt�:�A���1dא��$S�f]�P�l����B��T���$��x9N7���0�E݊T�Kկvn�l����ݺM5�)#�M���ɫ����D�ILq��\0x���Z���1������$��{���\r��q�|�b���}��\\�l�_�0�!;Еk��vn��p���~�miu[Xl��y7���ġvFl��O;Ȉ�sm�n���KxZq�b������3AH$��C-��<�\"�|��wv�YE��[�_�`�\0#r�\r����u �YR&U+\rH_��Z6��;w-�����F��?*$�#�ܥx�Ȗ�WnT�f�?�4�EVV\"�I�i�o\0�]�*�s\\�A7	�q��U���au�������w�2\'�<]�d���S^�1ʠ~e�r3j����9���T���8���{��\"�>)	:G�!�\r����7%�VPĆ;NoE���P�c�≖a�[#��ʾxE���ی���-�(}Nn^�$���TJ���IhO~� ��p	�@������7@9wM��U��4s�M�⿕��%�\'�[���tV�#AʞD:��H���h���49ê����^ �HE2@�e��\r뢼��\0 �T6��$;�s�b��E����{�>Ԫ��N}��5�}@jN\r���Z@�䄆�e��my�۸�%J�N.m�r���f,�˪S�I�����io\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0','1632','01','01',1);
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
INSERT INTO `documento_entrega` VALUES ('01','222222','sss','sss','sssss','qqq','01','2020-05-23','11:21:13','01','no firmaron la remisio',1),('01','123456789','Andrea','Maria','Echeerry','Garzon','01','2020-05-23','16:21:49','02','Paciente entregado sin ninguna novedad',1),('02','100001110','Felipe ','Antonio','Echeverry','Martinez','01','2020-05-23','16:23:40','03','',1),('03','23456789','Carmentea','Antonieta','Belalcazar','Pastrana','03','2020-05-23','16:28:54','04','No firmaron la Remisión del paciente',1),('01','44444444','Maria','Antonieta','de las Nieves','Chilindrina','02','2020-05-23','16:32:23','05','',1),('04','800003456','Maluma','Carlos','Perez','Osorio','01','2020-05-23','16:35:51','06','',1),('01','21212121','jose','Hernesto','Balvin','Jbalvin','01','2020-05-23','16:38:33','07','Paciente entregado sin ninguna novedad',1),('01','333333','Antonio','Jose','Caballero','Campo','02','2020-05-23','16:41:58','08','',1),('01','222','dd','dd','dd','d','01','2020-05-22','07:32:15','1234','',1),('01','222222','ssss','sss','ss','ssss','01','2020-05-21','11:50:09','12345','',1),('01','222','22','22','22','22','01','2020-05-21','13:46:54','213','',1),('01','222','ww','ww','ww','ww','01','2020-05-21','14:04:10','23','',1),('01','444444444444','e','e','e','e','01','2020-05-21','22:22:53','324','',1),('01','33333','r','r','r','r','01','2020-05-22','20:28:56','6','',1),('01','444','444','44','44','444','01','2020-05-21','12:19:42','ss','',1),('01','2222222','sssss','ssssssss','ss','ssssss','01','2020-05-23','10:27:36','we34','No firmaron la remision',1);
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
INSERT INTO `eps` VALUES ('01','Sura','Cordoba','3333',1),('02','Coomeva','La 80','2345678',1),('03','Medimas','San Juan','3455667',1);
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
INSERT INTO `familiar_paciente` VALUES ('13245367','Maria','','Del Barrio','','Mexico DF','333333333333',1),('2222','aaaaaaa','aaaaa','aa','a','aaaaaaa','22222222',1);
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
INSERT INTO `institucion_referencia` VALUES ('01','Pablo Tobon Uribe','Pilarica','11111',1),('02','Hospital General de Medellín','San Diego','2345678',1),('03','Hospital San Vicente de Paul','Calle Barranquilla','234567',1);
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
INSERT INTO `medicamento` VALUES ('01','Acetaminofen',1),('02','Morfina',1),('03','Adrenalina',1),('04','Harman',1),('05','Dextrosa',1);
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
INSERT INTO `perfil` VALUES ('01','Admininstrador',1),('02','APH',1);
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
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `persona_familiar`
--

LOCK TABLES `persona_familiar` WRITE;
/*!40000 ALTER TABLE `persona_familiar` DISABLE KEYS */;
INSERT INTO `persona_familiar` VALUES (6,'15327400','2222','2020-05-21',1),(7,'244224','2222','2020-05-22',1),(8,'71660668','2222','2020-05-22',1),(9,'1111','2222','2020-05-23',1),(10,'1000332222','2222','2020-05-23',1),(11,'12347689','13245367','2020-05-23',1),(12,'123456789','13245367','2020-05-23',1),(13,'222222222','2222','2020-05-23',1),(14,'5433456','2222','2020-05-23',1),(15,'1020303388','2222','2020-05-23',1),(16,'4444444','13245367','2020-05-23',1);
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
INSERT INTO `personal_salud` VALUES ('111','ss','ss','ss','sss','Masculino','22','@ww.','01','01',1),('15327400','Nelson','Giovanni','Salazar','Roldan','Masculino','3136850154','nesalaz56@gmail.com','01','01',1),('2222','SS','SS','SS','SS','Masculino','3333','@.','01','01',1),('22222','sss','ss','ss','ss','Masculino','222','@.','01','01',1),('55555555','tttttttttt','tttttttt','ttt','tttt','Masculino','6666','tt','01','01',1),('888888','sss','ssss','sss','ssss','Masculino','33','222','02','01',1);
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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `personal_salud_titulo`
--

LOCK TABLES `personal_salud_titulo` WRITE;
/*!40000 ALTER TABLE `personal_salud_titulo` DISABLE KEYS */;
INSERT INTO `personal_salud_titulo` VALUES (1,'55555555','01','01','2020-05-21',1),(2,'15327400','01','01','2020-04-29',1);
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
INSERT INTO `registro_atencion_pacientes` VALUES ('2020-05-23','11:19:13','Vivo','10/15','presion Arterial','carrera 65 con calle 99','01','2','55555555','01','01','15327400','Nelson','Salazar',1),('2020-05-23','16:20:34','Vivo','10/15','Pulso','Calle San Juan con Carrera 80','01','2','55555555','01','02','1000332222','Pedro','Navajas',1),('2020-05-23','16:22:47','Vivo','15/15','presion Arterial','clle 54 # 56-63','01','5','2222','01','03','1000332222','Pedro','Navajas',1),('2020-05-23','16:27:26','Vivo','15/15','presion Arterial','diagonal 45 circular 1','01','1','55555555','02','04','1020303388','Thomas','Salazar',1),('2020-05-23','16:31:38','Vivo','15/15','presion Arterial','Transversal Inferior con Transversal Superior','01','3','22222','03','05','123456789','Maria','de los Guardias',1),('2020-05-23','16:34:35','Vivo','10/15','Pulso','Glorieta de la Minorista','01','8','15327400','01','06','123456789','Maria','de los Guardias',1),('2020-05-23','16:37:32','Vivo','15/15','presion Arterial','Carrera 46 con calle 50','01','3','111','02','07','12347689','Juanito','Alimaña',1),('2020-05-23','16:41:20','Vivo','15/15','presion Arterial','Calle 50 con Carrera 50','05','3','888888','02','08','12347689','Juanito','Alimaña',1),('2020-05-22','17:47:48','Vivo','15/15','presion Arterial','autopista norte','01','2','111','01','123','71660668','Santiago','Higuita',1),('2020-05-22','07:31:55','Vivo','10/15','presion Arterial','swe3','01','3','22222','01','1234','244224','fernando','munevar',1),('2020-05-21','11:49:55','Vivo','15/15','Pulso','65','01','5','2222','01','12345','15327400','Nelson','Salazar',1),('2020-05-21','13:46:39','Vivo','15/15','presion Arterial','22','01','2','2222','01','213','15327400','Nelson','Salazar',1),('2020-05-21','14:03:47','Vivo','15/15','presion Arterial','44','01','4','2222','01','23','15327400','Nelson','Salazar',1),('2020-05-21','22:22:26','Vivo','15/15','presion Arterial','rrrr','01','444','15327400','01','324','15327400','Nelson','Salazar',1),('2020-05-22','20:27:45','Vivo','15/15','presion Arterial','eee','01','1','55555555','01','6','71660668','Santiago','Higuita',1),('2020-05-21','12:19:28','Vivo','15/15','presion Arterial','ss','01','sss','2222','01','ss','15327400','Nelson','Salazar',1),('2020-05-23','10:27:00','Vivo','15/15','presion Arterial','carrera 65','01','2222','15327400','01','we34','1111','ss','ss',1);
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
INSERT INTO `tipo_documento` VALUES ('01','Cédula de Ciudadania',1),('02','Tarjeta de Identidad',1),('03','Pasaporte',1),('04','NIT',0),('05','Cédula de Extranjería',1);
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
  `permitir` varchar(10) COLLATE utf8_spanish_ci DEFAULT NULL,
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
INSERT INTO `usuario` VALUES ('1088539048','José','Martín','Campo','Campo','jcampoca','Colombia2018++','01',1,'PERMITIR'),('1111','Pedro','','Paramo','','pedrito','Thomas#2009','02',1,''),('15327400','Nelson','Giovanni','Salazar','Roldan','nesalaz@msn.com','Thomas#2009','01',1,''),('21758804','aura','rosa','campo','campo','campo','Colombia2020++','02',1,'PERMITIR');
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

-- Dump completed on 2020-05-25 15:42:56
