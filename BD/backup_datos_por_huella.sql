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
INSERT INTO `datos_persona` VALUES (1111,'','aaaaaaaa','aaaaaaaaaaaa','aaaaaaaaaaaa','2020-04-03','aaaaaaaaaaaaa','Masculino','aaaaaaaaa','aaaaaaaa','aaaaaa',_binary '\0øB\È*\ãs\\ÀA7	«qp®Uš8\'\ï\ï\ÇkA¬ô\ÖCò³Mñf\ÚXzL\Ô—j_\éK*³R}I°#5\îG\ëE\Û^W]6²q¼—˜\ÎQ\ZO¢\ÍEnIk´‘°¾8$€\Ò\Û\Î8€°4Ö²¶_9\ÙM\'v\îQºÇ³8+\Çø¡Pv\Í\r·\'$\Ói” \â\ìù¸m¬~\ã\ÏÄ€’rc\ê™\Þ\çYõHû«\È>U‚R‚ÿõ-\×p‘\ÇC\Æ[þ›\Åû\Ý\'/s2og/[›;w­\åfü%\Ûd!7&@\Ç\Â®\\Y\à™\Ê\Ô®_¼dV\×X ö¦÷p(F}:6¹\Ühª¹µð\0k¤!g¤cÖŠ‹\Ë\í¬,™˜\Ö^i»:‡=‚\Ð4\íkø§\'µ\nŒ‡»”([T‘2”\Ð(­\ÈL\ÓC2\Z¨\ë	\ßÁ*o\0ø;\È*\ãs\\ÀA7	«q0¯Uš&ñ¾ƒs9Hû&¹”Á6)“Ì»\Äó\ÓQ:$÷GkKše·•IJ>r\è@@\Ä.°\Ìu\ãˆ\Ð\ÄDk\äa a\ÛÍŒa\Íjˆ\Ð\æ\\\ÐMô\Ö\æ>‚ý—oÁ‹¨.a¹¢Ad\\|úZb¿¿v­V\áÒqVòÃ±\ÍM\Ä\r#£\ë„e®9I\è/\"‚U¿þ´»\ålh•d¬ñwº\êtoJƒ¡Ë›\Î$av4\áw.¸Hi\×`~TƒˆŸ–$/e\Õñ;Ÿ¶‡\Íüv¥(V,ž}O`<^sJ\èBkˆ\\Qˆ]/|\à›Utü#p~4¨7§«½ºº\×î±Š…R­¡h‡¤>s’«LÎ”w+Lû£O–J¼ªXgM¸¼™ýó’•8\n\Ì\×ö\ÅãŠ¬)|‚o\0ø?\È*\ãs\\ÀA7	«qð‘Uš\ÔpøoWö¶vÀ™Ã¶‡À£\Ø^•7\Âû‘–\Ó\Ö\ëø\n$´\r[,¢Y¤\î`ªŒ·|e®\î9(ž\ìo*Z1þ\Ây\Âl›‰\æû\Ö÷cŠ\\\'\ÖeÐžQ¯}At\æ>ö\0Y)šh\Ù‘\â\Ã\Ó!mcY^\â¹\ë¹3º•f«§“\Ðœò\ÇøôËµ:?\ê{Lw…\"\×ñÁK9\à=²¬\í`%ôd\ÌX «\ÞOD£¿\Éø¡\æ\nššÀ¢Rö•:¶Ä•ÿ\àÿƒÙ†+°v%m*n5³8– \Ï\Ñ`M(\Z\ëi&\nk…g£\à\êðqh°#K\íw\â *\Â~\ä”©L\áA€\"J=›m¦C\áþ\Î\Ó\Ì5¡b¥~ecH\Ãò\'kòN<²ùI°E\Ç]{\ÞÓŒ[©\Ùfö¤óvG\Ý\ã™o\0\è8\È*\ãs\\ÀA7	«q0ªUš\ï€\ÏûP³;©r\â{‹W\r(^\'¤7v\âO\å†£-	\ßZÁiš€KÞƒ‰6,\Ãg\r\Æ)§(\Ò?\ÊC-\rYoü›\Õ 7¡–â¿žG¼¹\É\ÂJ\ï\Êq&“\ÊY=µÒ¹›h\ìuˆó\á›ôpnJÍƒ\×}·Æ•Ã•8Ot\é‘z³g^‰È‡€\é|Nˆ-k„÷s­º®7“ø5\0 \ã\Ê \å>%\"t.\ì¼8€pW™[‰y\ÇaQ\ÈM.Ç•\æ)?·þ\î¹\Í\n¤ROºœón3QUgÕŒD[(\×Â¶\ÃmG Dfó\ëlb\ï%_œ¿ñ\0\â²Ò¢§Ç”\ä9)U¡ò\Ô0Z\\Öºq\'¤r¸\ÛL7\î\æ™s–¶S h`‰#ªx…\"a²\Z&yèµ‹U€”o\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0',_binary '1632',3,2),(1111111,'qqqqqqqqqqq','qqqqqqq','qq','qqqq','2020-04-02','qqqq','Femenino','qqqq','qqqq','qqqqqqqqq',_binary '\0ø&\È*\ãs\\ÀA7	«q°‘Uš¸,Æ…HE¥xh+\Ñ1’]·lÏ©§ôc\ÕiF÷N:]ªqç¥ÿ3\âAŸL>„kFR\ìŠh(ƒÀ\ÉS±E$pªUÔ´\×ô»˜±´Xƒƒ\ÏL\ËYþºŒ÷¾ $Ÿ\ÜMl‡ðó\ÓÜ¹n\áo\Õ8sqžÅœ:szum\ï¹®J›\ç¾}\Ú7>žýVZ>ó•{÷AUcM¶Ê«\æœI-“|\Ãmß—:%´¼ƒâ£œ\'p2¡ ³;8\06	8xô‚\ânW\ÄiQaZtÍ½T!\Þj,²RÏ–S\×\\¦1-[´¥b}C\Ögõ®Î¹Vè§—W\Ú>\Z·˜NúaI…5\êY\ÄÿðI¦HŠ—\ÑÀ\á^ok–\ÄGQo\0øL\È*\ãs\\ÀA7	«qðƒUš\ØóúF´zW>\ÈI¥®Î¨ }¼+›ryVÛ±Xyªs\Ïb© øòZ»\È\ÂWi?Ÿ_ÿ³O_VV8#1ò=\íKòc&\"‘:Bç­¾¬!ºü\n¤\êaW\ïp^[\æTŒƒ\Òôl_òù!}rže\Ä=ù\ï·p!Vª¬Õ»\Ô_ý\Í\âBž\Ý\Ç\×O¹™…P¶:\èÚ‰3ay©m-ºo%\äLp„!\"<Õ½ÆºU\ïŽ\Ø\ÉWÖ®\È4\Â\ÊN[_Ä™tn\Ú\ÝÌ’t1v	\Ê\\Ú­Ak°z\Ä\ã	[ƒ\Å!\ÆZ9£¦\â\äO\"\é#Q©°\â \ßð\Ûù0\ÔUÍ¥\ÞE¥h†P\Ê\êkR\ÍÍ &\î\ÂRC\'ÁE\æô\ÌSÙ™ˆÒ‚g£.³4X§6÷{”@\ßXYÈ– O¶ˆ7Ô™N^§ˆ\'8xY±˜lo\0ø5\È*\ãs\\ÀA7	«qð—Uš\ÂY÷ð¡\ß}­À\åš£z\ÔyŸù\n1)]œõ˜\Û\n\Ú*\È8RöD\Ýb½ÿùˆ¹szž<\èúh`\ëÀ(VV±q3jd\×\Û\nPz\ãÿY ð˜\Äe½ð˜±0ò\å:ÔŒJ\ãRŒ0œ°\Í:0_—Ÿq_\éç„—8õ£ñAóDÿWú¥\Æ\íõ\í¯;0\éÀS\Îþ¹\á‰¨ó¢÷W\Ô_sÂ ¼J\ëÉ’¦/µŸ*Sœ\ß7ô\ß\Ï\Ç\rb\'ŠcƒDE\ÐV¤<Os\àe\Æ*&¦c\ÞR$až\ã|ð}Gµ\éYRR\Üc\â\Ë\"Ž\n_z\Üú­R\"^ªWu\å7q\ÄÁ[-£\Ç5}\ëò4D›Ò®}G+Y<$™P\Þ*3h:\å\ßrZ\Øql+k\Í1\ëÚ¡\ÔAº\ì\Û\×o\0\èX\È*\ãs\\ÀA7	«qp¨Uš½öú8•˜\ã #’U±s½œÿ“xÆ¤¹\íQrý‰\âk”\ÈC\ÐT¥*wq\În\Öû\Ì[Z^º¡u×§}­&-Þ”°\Äyöý\ÒøŠk²\ê\Ìgþ¾Z\Ñ)„_ö~\ÃLd7‹H‘\ìŒ?\Êo²:¨Á\'\íÁ@OI`ÝŒM\Ùú(W\Õ.\Ù\Ëf=l\è6\á£89¨\n\0zV\Ö²\ã:D\ßz_²z&,AaN\È.Œñ 4\éX÷…\Ð÷\Þ,pvþ†X­ü£.®þx€\ëœ \Ísk£\Ðz¥^z´X3n\éF(;T{a¿ÿ¦\Z<.`\æoð”ª\Õ97o7\îo\Ø\Ô\Âp†\"³û6\ía$\Û\ío­ö‹Y\èô³H•²\ÛL\Õdji\î+ûUS„Jv´\×\Þ%o†T\íx»T[BWû\ä|Ý·\ÉzEL°ÿü’·H\nªú¼Y< ›\n\Ù\\y!P\Ç#±T\ro\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0',_binary '1632',3,5),(6454433,'nnnnnnnn','aaaa','','aaaaaaa','2020-04-03','aaaa','Masculino','aaaaa','aaaaaaaa','aaaaaaaaa',_binary '\0øH\È*\ãs\\ÀA7	«q0”UšÎ¢¹U;W0¢µ&m#­\Óq(I¦aÿ†\Æ<´Lª\ÛPqPø¬}¬fq/’6@’Ž	±ZŒœq¾—W\ÖH´¦¶\á\ÍRa\\\ÒAA‰#’˜×ž>\ë¢p\ã¹V\ë*l+×¸BÙª*Qÿ\Þ\ÓÒ£’.\Þ÷ˆú\'·™\îôf•‰¥õ}2x¸{¸h¤·Ì§w\r.‚¡TÁ—WA#+]¿®Bpwe\n\Â&Áå·¤´ÿ/+»±r²\Ê],ðZÈ¦\Û-3Ž\ÃÉ®ïºª70Ÿ…@\×«…ÿW\ï#o,š\æ1{S+;S¡hC\ÄzT\ÌF²\Ë[\é¶Â”?C\ì™\ìºX\ê\Ñ\â@žl¹Z™í»¨F>\ßn\ç4ICHf\Âp]\Ó\Â\â\Õ\n™kÑMj{•€+ÐŠN·Œ¸Wi5\âk|Zƒ±o\0øq\È*\ãs\\ÀA7	«qpšUš\åÀ¡÷šÑ†œ2E\î[Ä¹ˆb!÷-k¡¦B™\È\Z\nD±SºL;ÄŒð80›” ºT4øI\Z¨¦ ›/}\0\â«\í`¤&¹vRš‘u\ÊAd\Äxf\Ï\ãµ\Ã+:\\\æUT\ã·Pº´AzÖ¬¾)\èrF\Ï|\ç|ygU<Š¿x‚H\×Ö«\à™r\ßÁT²\È\Ép¿þC%J\×(¨÷ˆm˜€f,kª\Ý\às§­\æ(*X¹©D\ÖL\î\Éò¥\03Ž<dôõþS;>Cß¼68?¿‹HQøF9hÌ‘jX7ò¨ô`;Cð~f]4^§\Û\äA”W·D­¼VhŒ«Á44\é¤F¿ÁÚ°K	*0:\Ö\Øw/ø),\r“QSúvµhˆ‡\ìõ´\ÔPý\Ñ`\âþ“Š õK}Vn²Y£*\å¯\â®\Æe!÷\ÖuYJVKr¹×¬\\O)\ëþz´”\Ð {ƒÂ´¹\êaS\Þz\ï‹R†\\{ö[e@ g\ëo\0øE\È*\ãs\\ÀA7	«qð­UšÖ¹\ã?´^\âˆiºT_gkLBeL\rñRµ”Õ‡ðÒ§Y˜)\ç\î‰g:~nô\Ý&‡Šfó7ƒ˜))7‡#uñ7\æfÉŠN_\Í4c·ù\Z‘]I÷\ãG#œÊ…¹4‹;œ7±\Ë\Þ\äÝ·\Ã.£\ïÿsVû@	Ã—6\Ð\ÊkM’¯\Ø(í–‚\Þ\Äü\íMk¬\ÆJ}\élf\ï¸ï‘Š\Ë»ûPŠ·fjk6–[œ\"\Æ\0œ\Ã\×3Iõk\ÈkBM\æF¤s[ÿ„œ5stƒAp7\\J‚I°xyÀ¤cx\àá‚õðK~±\n±ja™T\r¤Z£D&]f# \ÜV\Ë_\nL\r\Ì]\Û\êó«¶›\ÔI÷LƒŽR#\Ê_Wœ…\æ®.@F „\Íf?\ä´\ïõM\ZQ\ßh­\Û\Ò-¡}ôo\0\è€\È*\ãs\\ÀA7	«q°¬UšK¥c÷«‰W‘«[x\Ü\\#»…f€\é\ëVaIKc°x\ÇQ-$[\í\n6¨r/C`V¹\0TŽ¢ó×º€1’óG\ìS\ßsF¿	½\å\ØJµ|—Ê®^	\é‚o\ã,þ\ãòšeu£byøˆ§­[õXS¯\ì‘[\á=g~±?7\ê2þufIŒd>U7\æ‘\á‘\ì|>\0\Ì-2g=7\Ú\Ó\Ýö)»\ãº”Bf=x@c¢‚\Þ\ç|\íz\Ë\Î5RÀ2\'\Ñ_æ®6a,;Xw\×=,ˆ½RÀ\ÙbÅ¤öNm­>“p\ß3	,¿\ÚBŸ=Lö\Õð\Ã7:Y\î\ä\ÔšIduœGr\ZŸ-8ð´y(\ê\"\ìSoYN29P-bf7\èh¡þkß†ž,ƒ§1Wñ\Ó\Æ\ë T\ÜNÿ,˜\çT2Y• ö\ê£¿\å\n<!s>0XÞ©dÀQW²§APô]µÛž\\U3sŸf\Ëx\Õ _^ü\\\Ôr\Ã±ª‹5\îšBWó\Öo\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0',_binary '1632',2,2),(55555555,'gggggggg','ggggggg','ggggggg','ggggggg','2020-04-02','gggggggggg','Masculino','ggggggggggg','ggggggggg','ggggggggggggg',_binary '\0øI\È*\ãs\\ÀA7	«qp˜Uš\èe\éž5x°–\ç|¶(…óXÀ-õ	Ž\Ù\ã\Ý$\Ë\Ê5\ã\äMsZH*ý·SN­pÿX\Ñ\'b%H?Ê<j\Ê÷3JA3\á\æ%S¼Ï„\ØAÃž$Àu\ØE\à*9\Æ\ârŸ–Oe\éa(þ\â;\ì\Õ|7(S·¿h§gM\Â\àT—)\"œ­Šd\Û	Y¯þ\ÓþPžhW™\è+¯,3{\0ƒ\0õ²®¨Œ„ž\0ª50\Ù\äø¥yD%Ì¢ýi\Ê4\ÅI:–Š˜KÓ¤ƒ©f¡ dª\'>{\ç[€X¸eO‰\î—jœ1\ïKS¹A\à¯û~Eºblƒd_ªsT\ÜWõ\äþ+\0•Œð¦\âE\'£Qõ&µù=$9\" ²y	·-ûT‡3øC¿8pü½——\ìô@®<1$r`ò$±ñ\n\äŒ\è÷\Ëöo\0ø0\È*\ãs\\ÀA7	«q0™Uš¢¢\ÊQýw\Ö;î„¹\é{6)\Ûr/ý\Z\íSº~Þ‰a:‡cl\Î$MPHE\0 ô\Å\'\Ö\éaµNC\Ö6_Ø¾24<>%²Q4ºQ\Ì*{Ï¥®²_‰ž±\Z“\Ú\Út×ˆ=e¬±\ÄN,“n6•‚\Ä\ä¯\ä\ÖkF·³\0÷N\æU+ Ä©s’Fa`š\"oh\á«:²\Íú\Æle\ÉRK?p?ñû\0¯©‚š\r2©±\Êml>U¬\Çq\ÎPÂŠ(ù]›d>õ¦¡\×99G\Ä\äˆ\Ñ&‰wƒc>\Æ#G\ã¡õ¾\é\ç…\ëF	[\Ò\ØXþ?\Z¼Þ &vÏ˜¨ù#O\Ô=5>…0ª.*Í‰AûŽ\áF\ÖO*wuþ.mxjö\Ð\ÌSÊ±Æœ¢ø¼G‹\ï\Ã\rC¢MN\éo\0øU\È*\ãs\\ÀA7	«qpƒUš\ÃLÖ€\Ó?\ÑrZ«÷Æ›÷9[¿\Ê”[G\Ò^¼”¢\åcšø’\ZN›|“Í‚\é<³ï›…@ˆ&s3“±³\Ã\r†Ü¨w\ê—\Z’\Z)M^9ð\r\È~tB\ì\Ü[QT¬Ø®!ŽbŠ¬·\Ö\r!‡\0ñ¡ÕI)m\Ón\ï\Í7!:RñVD&\Ú\0Q\Z@§+¥7,\0 þ®\ås£1\Ý\Ù\ÚbJº\ãš†‹}4S¸›Wt=²9¢*‚’j\×ó\Ò[]	­!?cþªº„‡\Æa!¨£]˜gu­óI‡9\ÇžRÓ£ZIŽ\Øðõ³O\ÏDóy\î\ç¡ß‹\ÓY‚»e@¸¨\Z>-JCü»Yóý\×)S\Ï”j”ë‘¼X\ÈgjšB~3–£«‡c«~ga‹U\Ä]¾\Óâ²r=°|‘\áóÙ¥7+`\Æ÷«ä›¯\"gªt¯–{o\0\è-\È*\ãs\\ÀA7	«q0žUš\\tÁa\ËS¯Š4u\Ö!P}p{\àõ¢c’¼6g¶úi+-2£, Q=“m‰	c¿|ñ°º\ÈnAF#œ¬ÿ—M\Þ\è½\ë%§šn“m\ÍK¾«5{5ˆ\îY@^r\ï\ÙW\Ê`šo‚=Yn\Ú_­‘¯aA¿ýU!ø\Ù3¢\"ó\áÕ‚Õ®¶ø‹\×\Çpó\Û\ày5”•±7H±ùv¥\0Eú’m´(\é›z¢L\Î\ê!\ëT\ÍvÿV\'µ`\Øoû\ë\nqˆ<µ“	Mm2M¤C|¬1\Û\rq~u°Ot-¦uz„\à® ø	mJ\æ§	;~\\\Û?\åtˆ	10e”e‡\ÓI\à,—¶¤·3…JÇ•¶\ØúWy\á\ä\åC\èM8Á÷\ÏWJµ\ËPo\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0',_binary '1632',3,2);
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
INSERT INTO `familiar_paciente` VALUES (32413,'kakfka','kakjfja','kakfaÃ±a','akkÃ±jkd','kakdaÃ±kfj','23445');
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
INSERT INTO `personal_salud` VALUES ('','','','','','Masculino','','',1,'4'),('111111','sssssssssss','sssssssss','sssssssss','sssss','Masculino','ssssssss','ssssssss',1,'1'),('111111111','qqqqqq','qq','qqqqqqq','qqqqqqqq','Masculino','qq','qqqq',1,'1'),('12345','ss','sssssssss','ssssssss','ssssssss','Masculino','3333333','eeeeeeee',1,'1'),('153274','dd','ddd','bbb','bbbbbb','Seleccione','77777','hhh',1,'1'),('15327400','Nelson','Giovanni','Salazar','Roldan','Masculino','3136850154','nesalaz@msn.com',1,'4'),('22222','ssss','sssssssssssss','sssssssssss','sssssss','Masculino','sssssss','sssssss',1,'1'),('223333','22','22','222','222','Masculino','222','22',2,'2'),('231455','RRR','RRRRRRRR','RRRRRRR','RRRRR','Masculino','5555','gDGSJS',1,'1'),('23234','wwwwww','wwwww','wwwww','wwwwwww','Masculino','333333','eeeeeeee',4,'1'),('2345654','ttttttt','qqqqqq','qqq','tt','Masculino','3424','dsefr',1,'1'),('23459','ffffff','ffffffff','yyyyyyy','fffffffff','Masculino','ffffffff','55555',2,'2'),('332211','aaa','aaaaa','aaaaaa','aa','Masculino','3333','qqqqqqqq',2,'1'),('33333','dddddddd','ddddddddd','dddddddddd','ddddd','Masculino','rrrrr','ddddd',2,'2'),('3333333','33333','33333','3333','33','Masculino','3','333333',1,'1'),('341234','gddd','ddd','ddd','dddd','Masculino','33445','swhdgdh',2,'2'),('432567','Ã±Ã±Ã±Ã±Ã±Ã±Ã±Ã±Ã±','jjjjjjjjjj','jjjjjjjj','jjjjjjjjjjj','Masculino','888888888','yyyyyyyyyyy',1,'2'),('44444','zzzzzzzz','zzzzzz','zzzzzzzzz','zzzzzzzz','Masculino','33333','jeuddljf',1,'1'),('4444444','ffffff','fffffff','ffffffff','ffffffff','Masculino','55555555','rrrrrrrr',1,'1'),('54637','aaaaa','aaaaaaaa','aaaaaaaa','aaaaaaa','Masculino','aaaaa','aaaaaa',1,'1'),('54678','hhhhhh','hhhhh','h','hhhhhh','Masculino','yyyyy','yyyyyyy',2,'2'),('55555','ffffff','fffffffff','ffffffffffff','fffffffffff','Seleccione','fff','fff',3,'2'),('65798','uuuuuuuuuuuuu','uuuuuuuuuuu','uuuuuuuuuuu','uuuuuuuuuuuuu','Masculino','66666666','yyyyyy',1,'1'),('67890','vv','vvv','vvvvv','vvvvvvv','Masculino','vv','vvvv',3,'2'),('7654321','ddddddd','dddddddd','ddd','ddd','Femenino','dddddd','dddd',1,'1'),('7654356','Guiller','','Prez','','Masculino','4444','ffffff',1,'2'),('889900','wwwww','wwwwwww','ww','wwwwwwwwwwww','Masculino','4444444','ddffggg',3,'3');
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
