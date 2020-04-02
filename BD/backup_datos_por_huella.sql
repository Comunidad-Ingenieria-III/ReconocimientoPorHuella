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
INSERT INTO `datos_persona` VALUES (1,'carlos','ss','vera','','2020-04-07','qqqq','Masculino','qqq','qqqq','qqqqq',_binary '\0¯a\»*\„s\\¿A7	´q0ûUöBƒøö&\ BKE\⁄Ùx8-öv˙\…¥äü›éΩ…ç\“∏◊õ\◊Xká…ΩÇ,j5Ûs+IÀ©j¢\"?∑πOjÒÖ≠ñû∫<.YÉáÇNt*\—\Ó\¬\"ΩLÜâùÊ£æ^¨Ü\‚Ø\ƒ	8•ç	Y•PV\≈)˘!_Ùp@\À\'\‰4<\–bçú<íìòxñØMÜÚΩC\–a\∆0uKyë_+πØ0\ÊueæÛ\–jí[µ¶5Dh˛K¡¯¸E\·(\‰¶\‰\Â/†vB¿\‹\‡%ÆPüi¢@—©V@≥TÑs\…\»¶\‰5x.\Ê“ày\„ô\‹\„HtfÉìp\\0}\⁄©\Í\ruà=Äøµt≥égìDJÛ™$¯\Ì¢gRíéÚ˛c(TE8d\Î§3X¥Ñ\◊*s¯\ÎΩ\\™5ı\r•I\„5ë\„~\0~\ﬂZm∏ÑnvÖ?[ioßoôúU<≥∫Æ\ﬁƒ¨&o\0¯T\»*\„s\\¿A7	´qõUö®_oíˇ\Ë+\⁄ˆ+drgü*óZ6\Êˇ$\ÿo^,˘)âÇ$∂/¸-g\ÿoÌ¢ü!©HΩìRAa°FcFós¸Éërú,,§\ÕcCﬂë\≈EMGkGï\Ë|C\œ\÷;G\Á4ﬁî\‡bÜ\‘qû\Ó9∑zzrˇfP;›±Çúå`!E3 2ΩŒ†¡Úhb\ƒbê¿˚o˝\¬\\\Õ\‹$[†ÜB¶ı2˛=!\">$ëöVG\„	∞Têz\Z\‚Hb†\«\Ìm\›2¬≠w˜ö9\n´#uñ^,RL\ZY—æΩ2\”\œ\Zyë\Zö>ûAüs≤˘æbºWó\\\›|ß≥Ø∑aôJÖk’ò\ƒs·êºµd3\‡m|	™\‡ê:J	\◊+ùk®q∫2û†P\«Aó©®]–í\·ÙÄHÛ±\‚Ü±ÚA∫ˆπgÉn®πw&+\«ı\ÈPBd¡#F˜z\‘ o\0¯Å\»*\„s\\¿A7	´qp§Uöwg}m\’[S:ûµ∏0ª/\◊Ÿì`D|•\ŸJπ¡M;\Â\ﬂ?ô<O\Â˘ö5\Ê\Z^®G\ÊçLazg8ËñÜ\“q[\\s3\€\⁄\ŒCù‹ôu#øé#wÄ}\·ˇ\À\ÃcˆM\»#L2\‘\…\ÕZ\rû\’…Ö™ˆu%B¯ù\„{±é\◊÷® `˙≠\·(Z+	∑ç≠\”#R\”IIÙuyZâ9G9$\0ëà~˘q`≥\Î∂F\Ó˛ó›è~XæùZ\—˚\›\Ã¸éõgòZ9V\◊MJV[√£çæ0Ò\n^Û~∑/\0\∆\„®3\›Ò\"l,´ÚT\‚í7$Y\Àê}1àF\Î\‚tfHâéÉµ¨tE\Ÿ\”\\\'\Ÿ%\€c∞d1\Í¯èW(bŒÅw`Õ¢•\‰9vÆ,_|ˇΩ\Ÿ7\‘\ÌFô$\À9æ;\0ª{˘\¬\∆x™\⁄\Èïv≠ùwŸÖzà}\n\–Gﬂçæ=sR1…Æ^qöæu±:uÑi0|¯}º•Ek˝H\Ëo\0\ËE\»*\„s\\¿A7	´q0öUö1D*µ\ËP√Æ\ﬂ˝Ús°&ºN\ﬂ8>\ÿe\·\”\"ˇ¥Œç;∏Sè¶Û\\\ÎuÛô$\„\Áã\ÎGäC_,\Z};ıdO^\∆);\ÓØ\„Ÿ∞n{©7h3\Í m\Í%~í≠/˚\ƒº<\”$-ôFÇ≥É?v!l\Êk\Ÿ\n}\Ó\'\ZΩv-v8<˙≠ˆr*˚A>±≤xåZE!ˆåá\Ê;t\Â≤\Á*ã\€T+H\„‘õ™!1ù”æ°%˜ˆE¢ˆ2ˇ˙Bïp∑x\Õv!7∫ÛÑ∞T\"§pD4(çI\‡*ú]\»ø$íª21\n∆¥™Ö\Î\€E\…˜=J-P¡áÆ•O\Á¿RgúãDÿù!c˜ÔΩ∂\ br~{[n\\\Ô>‘≠[ªÙzI€ûÉ˝\ËU+d≥ñL\ÊÑ-z\Ââ\'dˆ\‚P¯ùT\Œ<∞∏˘éX%o\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0',_binary '1632',1,1),(15327400,'Nelson','Giovanni','Salazar','Roldan','1973-08-26','calle 78A # 67-54','Masculino','no','no','Cuarentena	',_binary '\0¯Ä\»*\„s\\¿A7	´q0∞Uö≤≠ÅÖAó\Ã!\Âëı˘πëDTy6tû∑™˛¡jã¸\Z8?V\Ê6\ Mtb\÷˝=∞ïU\ÈT\ÀD_∑ˇ©\n.˜∞Öï\r°qDl≥*\÷b$˙ß]hÙz,ú^\‘T∫72N2UHO\‡¢\Ë%ÖâÒæÆ\‚]>ëm¿\‰\Z¢í˜à\Î[˚b¢y\œp≥\ÁDöSjíÇå¿\⁄|©ìVo\‚ùıi3J\„\‹\n\Èâæˇ∑\»\\\⁄≈£¯Q\‹Û\ÍÖ¯!Û\‘ö_”¥c\«zKî`\Ã8l˝¶¥Çg\Î¢,vC\rã&\ G†\¬\„µQ\'âa∏ï+m™™\–ˇ¯\ﬁ\„˚!Hç(#J´$Ú¨P\ÃÙÑä~ Mí.ãq/…´îÄô\‡/C\◊¸uª\»lD\"\‹¶ê˛l3[»ç8˘j†y¢˚¶Óµ•tUu\ÿ\"_\r¢≠@®ï%\‰[~dˇ˚Æs\‚=µûg\\x÷ëä\‹\◊œõ@)Dó\¬\”Uá\'\ËQ\‚\÷\–W~\€\”;∫\Ío\0¯~\»*\„s\\¿A7	´q∞óUöñEˆ=\·l/¥YûÉ2≤…æ≠$û\"\◊\◊gZ\„n\ÏöÀ§o¨©¢\√¯˝z{˙h.=Äï\Í\0óª\È\È\0â\ÈsCY5\Ì√ü˝\”7.ª%\Á\·t-¯˘bõ\‚y\–\Ãcõ˝=\◊˛,”ä≥*A\Ÿ\Ô†<n\Í\'T˚\⁄[nÆ&B¨O!¸˚d2ıïPØù¢ub\Z)ºàB{ØˆV\«Ù∫\Õ\„\Â≥ˆ(e¯\◊)$t\ÊdÙf\–\È?´ùì\‡:ÕÄ\È\Ï\Ó˜6}*D®∑,d\Ì®IålÆ™(\–4JBã◊∂ã\Ï◊°@l∫\ÊÑrIJ\Õ˚˛™u%ìs\“\≈ih[Û\ﬁ\›™üÅî\›\Z)\ﬁ\Zr\‹F\·]º∑«°j+\Ì6∑rid\ ix˚™\ÁÕ©In¬ΩóéÇ>J°\ﬂkò6\ŒŒõ¨-\›HG\\\„	\»/\\`øªH˝\Õ[\∆&)JÙm¸†¸\ÍS\‰Azî(\Â¨I\–ZØuµb\‰‹Æìˇ|x˘o\0¯J\»*\„s\\¿A7	´qñUöy\‡\À3pıwºï˜\ƒB¸ÆÉK\»Û_äáFÀ´É®\Ê\◊Y6çÑπ∂;>}\ﬁ\”\‚m\›X\\/‹öë?\„a\œû\'˝\ﬁ+Rmq£ä\Âw¡!\ﬂ\–∏\Íp\–¸\’yâg\€O}	∏áBì™uU\'£®ã26L?W\'Wı~=\r•2\‰,\—Ü¯˘2¨-◊£\ÎjZòtqÅCÖø˝T\Âûj\∆XD0H-£mb|ñ»æY\Ìx2?K\Ë\Â”®û¢üà3\0ñÙ_>Vo$\„)¡%\ÕX\◊õ=;rPLΩ£X0ó≈©≤m´|\Ã\»å–ö\‡\ÿ\’\…\ZêSsõö∆¶2ö\ÏÀã)piT\‰ê\Ë˚\0π˛[AÙÉ\ŒWÄ∫*\”]AÉ#r`j:≤∑-g~≠Æ\’@ï\ÃY∑üz7ı@¨ü%9Ço\0\Ë^\»*\„s\\¿A7	´qp§Uö~|\Í\Î\”1©⁄¨ZäVπUUl~:9\«\Ì\ÂÒF.3≠\‰b¶¯ßSˇÙ@_µ¸˛\¬}âúr$HvLıN1ˆq\€:HKœòêü4∞°x±Ò\¬Û~Ö€é\Ãç¨\√˙\›h8Õ©§π{)\√{º\”˝ß=)+{®∫ëêX\÷˝h∑ä˜]\"\Áè\ÎvZtØ0xaB˘béIS\“*ˇ\‚®Bjæq2Dã8êêN\ÎÇqo\Ë@r\Áç”¢\ﬁ\'\Ã5\Ïå=∂AOøwYZz¥2]ÆÒˆ6\ƒ€π\„\œblò≥ó¨@t;°T´\"ï£˜áÉgˇuxÉı“∂cF|MP8ßè£oˆ\Ïµˆ\◊\«Eé|%Ä\ËDYr)Eèú˜&é@ÕºlìzMN\ÏDÙ\Á∑≥IG¶~iHf\rÄ\ŒeÆ\¬z*\„£\ÌÚ\Í\ÂI†|\r\ÊY\–sxπhÙ23¯WiHº˝yΩΩo\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0',_binary '1632',1,1),(1020303388,'Thomas','','Salazar','Osorio','2009-05-22','Bello','Masculino','nada','nada','EN Cuarentena',_binary '\0¯\»*\„s\\¿A7	´qßUö\ƒb<1T\ÿzï\n\Í\·f\‰l;ª[\¬UèY∂Æ\Zá+\Ír˜d\0Q\n∂Ä3O1Er\ËèJlΩõ\Õ≥\‚ì%ó˜\“_O˚¯ØÖ\‹*Òi\ﬂ6±t®gûh\—oRòß\‡9K_&Q§\"\Ã|\‚ñ:ó¸Cˆ\‡˘Ü	a¨∞\r–ÇC%ávï\»\◊e2∑_\÷\Ã@ﬂÅ˘ïXõpf¿+\ÎR Ö;“¶\ﬁjÉp¢eñ*Ü\‘C\‰\›| ÑJ≥ı˙çΩ\À\»∏<ø˝®â\ﬁ¡C\ÀS¢kŸ´	\»d-u≤â?K7ëï\Á]Aù\œW§\n˙Ç\Î´_\»~\€Ñí*jßX¢%â\¬√∫π8d\‰\¬\¬3Øö\Ì\ƒ\\D\—_3Ωú∏ÆH\\˚\…∆πtèù\Ô˛eîI-H+¢¡®T\Œ\Z∞œìû:R˙ë\„rF¿øV¢$∞.à˙Àø>Z\0\‚3p&IYÇ˙\ÂQxg:\rjHÛU\Ô[ë|dµ\‡\0ù˚\"Üò\“\»¿û¿\À\»ÙïÇ5©o\0¯~\»*\„s\\¿A7	´q•Uöj$ÖΩ[≠r\rd\·@ê\›Û\‹\…\ËFMçgæwüTì£tâ¯të]\›Lëm\"˝L2i\ÎG¯˛è¬∏\œ\Âgj±öw\≈n≠Ü\‡ü|ì¡îk˙˛\ -N\Ô!c±pÉ\rV¡Å6\ŸâC=#78W\\YYÛk˛¶Eö\ZNˆöS`‘ù\”R∏ÉÆ_I\‡ ÖÄí\0ı\räeRŸÜvæ˚\‚\‡öxÛê\÷=/î\‡b˘∫1ßÉ9?ç\0ù\Ãˆô/\È:´§\Ôü\› Zê†£¸\n\÷WD>[DΩ≤#x˙|G˙Ñ#\·úGø∑ø\Ìú\–\ \ÏíCßEk)B\»u?¶ˇ\“W€°z=¯Pö!\Ì\ÊöF∑\—\–QãùY,23ùÒîyR\Ï\‰G¸\Á\ZcÎã≤˚Q}l\\zÚﬂòà∫0\Ë~òr\'/D8§®7\’d`ˇ52|pi\'&¨\nπ\ÍQÑ\r«åÙùÀ®;(ÒN/N∫cˇ¿ö\√yú!\‡Ω≠\ÁwRcô\ﬂ\»o\0¯Å\»*\„s\\¿A7	´qp£Uöz¶á\ﬂ\·\Âx\r\‡\‡\'—èIÕèt\ÃlGÅ\r&9∏™p\"\›pQû¯\√\€s±™:˛%~â\\uå˚\ﬁ@=\‡J\‰ª\’(NS\’>F	U~îª\nL∫˝Té\–˝];&SÒ\ 84\ÌV–ú´Ñ©<Ü\ÕÕ≥\Ì∏àèZá*˘Z2\Ê)∫mÄ\rê$\ÍVdx\∆\nòYÑt\0™>#¨d\›Hæ0\Ïöj\‡ˆáíqUd‘É	¯°fí>V Ú=œΩ¿KCÛ˜îY\Î\Ó»π=d;É\ﬂ\‚¨{\Â∂€¢Ωå\r—ôU&<uG[JrhvKhh(≠/∫!0X)Ñäíüõ¥\…LzìÅ©\√\0&O{˘Q\‹&\r\…#6(ö –¶\¬7PûBphÙôïW\Õü1ö¨ì\‰Oá™†Å\‚_y-\‚àBs\√Àé’¥»á@f_P∫¡ù∏Q8\‹t:\œ\„≈ø-YTMÒ\√a>;Ú\Ÿ\¬ƒ•ûQdJIæiRi¸\–J¶*V4\‹V\ËB¶Ö`J#Ø¸2Ûœ£y£”π\"¸˝o\0\Ë~\»*\„s\\¿A7	´q∞îUöâ#?\Â\Î\◊\nANNàú\Í∫F\Ÿ<ıˇ)múz[~K˙\⁄1ú\ƒ\‡\–˙Ú°≥G&§≤nÑ\◊e-aD\ÿoV∏\r\”\◊lö\≈Kµ6\ÂÑ)\ƒ\‰ÛoøÜ°?ù˚˛dYÛ+n¥z∞¸ª\◊M]\‰õ \Êé|Nì\√fR\nNºXè<X°ºÜ&¿•[>W∑4wΩhn]©\ƒÒcg&\–\»nl\\\È^Z∏&\ÓSè\Âo¿˛\Ôn^\nj&\rß\ﬁ\0ÉífvTÉc≥\r}±	ÍøêA°<`ùO\ﬂg\’\‰_£°ÆøçmwÄ7ÌÄ∏LMi>\‡\ËÚêºtHéM£\‚/∆âÚqxs£\√wˇE[Ñ\Àj@˘•_πoô>cçWIÃÅ\ >ˆ\"°üîö˙\Ï{)ˇH\¬T@íû§®\ÈNº&†E6K&Dp±†∂\Ã_ ^ \ZÿÖ†\‘˜‘ì\ŒY6i2yQ?ˇåÄ\≈\¬Df>]|ü≥m%àöõì™ühJZëL\ÏDRÑ*\Zål9\0ÿ≠o\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0',_binary '1632',3,3);
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
