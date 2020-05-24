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
INSERT INTO `datos_persona` VALUES ('1000332222','Pedro','','Navajas','','1954-09-25','Nueva York','Masculino','Las Prostitutas	','Robitis','Muerto',_binary '\0ø[\È*\ãs\\ÀA7	«q°ºUš!\Î?¾H½®\æ±~V\é\Û1\î‘ıˆQµ\Ã`$Ec%¶–Ì“\áŒ÷-\Ê;F¹\Ûn\ÂO§8•…¬ğqaµV0Gô–\n–<i\Ô<Š(^a8\ë\Ş]ö2¿„*\Ù4€A¹\Æ<§Äƒ˜«\ïôd‚i\Ï1_cók* ’l=\ÓöR\ìr\É#‰¨wº‹v›5…1ƒ7\0şC_U_ô\Òo9´ûÜ·™uÀ\Ğdú?3\Z&Oÿ2¢g+2\Şq`dm&\Ô\ŞN7H\rE;ü3«¥†\Ç\Î@Zƒ\ÄúW`Çş]<\ì€øù¿\æ\"=?r\â­\Õ+œLk\Çw\rn3o)Y\Üf\î/\Æ\"XüX‡%f?cÌ—f\ë³Q©\íL\ëU:òğF?\0KÅ¤»#;	¹Opj5Ä‹“h¼¨\'\ê±óf\ŞIÕ‚\\\Ù\Ì[9Y \Ñ9ú÷Co\0ø~\È*\ãs\\ÀA7	«q°_UšSn_fˆsªô;\ç\æ\æin›J&ß‚:\ÍQˆs$\×uep\Í8^\ÅÎ›\â¡Ò¦€a¥\ÛJ•şü\×øĞŸñ\É)•G1”fG)6\áR-•6º_}Ù±\Ã:_\Êön Ê–g³¨]];\ínWn–\Ì\ÄG\ÕU\î-3f\n,Z ü²7\ë/\rv®´Z5\'}½YEj\"x\Èi\Å\Ğşm\àc(\Î\Úã” ’\è\Ó\Ò]\İ\â€\çû—\äÿ\â©şŸô÷¾\á!ı\ä=—\ïODK\áJ\Z¡\ì¸l­oÙ¥\×u—>\0Š\Zø\n«‚¦\×\â\è\ì}­|š0‡ _tù\âf\n\ã¨Và®’0q\é8\Çw\æP»\Z¿²kI—±:	Qdh®ôº¿Ò„kk\Õ\ÚLI‹5M\Ø\ß6\á\Î\0¬—ı—1ö\ëšG}°^u„M%*ğ9¬—„….²9ôF\ã™şuDD·Kˆÿ6—AÔ´N@\Âgô?x\êrF¸£Áeo\0ø_\È*\ãs\\ÀA7	«qğ¿Uš\à$§ÀI\0\"vÊFƒf£r\à(†\ädH\Å12¬|´¬ZSKU\Ó\Í\âsPt\á››\\ö¡8ß™Z\è(\í8†¯2	\Ò\ïôI[ú¶\ã>*>9´/–§üúvaÀÏ˜›a#ú!	-#\ÄÊ˜\ä*\Üı—øŒM8ªí›½”4‰o…+ª\ê¨$\Ä\"‡6h/\è©0\Õa\Ê	\Ô\ÓşS\ìd”\Î\ØÈ¾\ã\ÙAh†\Ü\åñÁ\æx¨¥G\çƒ\ï³Ryv\Ø.¹ªN\Év\Ìü´a\Ìq/H\ËC±\è`şü\Ì\ï§„¦ùNT<yob0‹‘º[\\J\r\ìJ¤T\ã>\Í^E\è/r¥\Ù\ß,X\Ïü¸ œ-7Ïx ®(\Ä\ì»Á\Èû\ëjûN—®\ãøXq\'š\Åwˆ\Äõ\0¸“¶ğ²j4\"i\Ñ\ÓŠUd©­”ÿ+~ŒpÀš®o5x:ÿD¶¡o\0\è~\È*\ãs\\ÀA7	«qpUUš\Î\ÑC ±§V\İb\Ò]\n\è\É\ï…\Øt”\ì\Õ\âD\"\İW\Êõ»?bq÷.º’ú•iÄˆ†„\Ó\Ï\ØşEr\Ø+\ç¸c&>:´tow\êó™O\è‘\Zg-‹Ä£\İ¤˜Kq2\ç\ìq¸[0…\Çr\Äù\rÿnlR$Rfù\İIŠ@ˆ{NûqN$W±½¤g!¼3\Û^¿§\ê¤>¦1”¶~i\İ\É®•&\à³Ÿ`7Pğ·#\ÚP`6\Õö\ßÏµ\Ş\ì;S/x_[\ål\\=ıpÀ|£¤·\ç[GóR t\"¸S¨E=J5;¥Û›\"\ä5\ã)§bU8ŒKøƒ÷\Ñ73\'˜/“?¬m\ĞyfX6\Îó£jØŠ€0Û¶s\Ê.p]xd&¨¿ZĞ§*½€£±ºyI¡§òB¾·¦\íñş#´ò\ÜUY·Šú!sŠ[\ë\Îõ»\Û0‘ö´œ¢Sos’\ÌúU \Ñ,uÎ€\Å—R%ÁÃ²¢\Ëßo\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0',_binary '1632','01','01',1),('1020303388','Thomas','','Salazar','Osorio','2009-05-22','Bello','Masculino','no','no','no',_binary '\0ø\È*\ãs\\ÀA7	«q°DUšdošqu\Ï&­¶\Ît;¾°\æY™¬\å€ø!\Ğ\Êø\ÜTC3˜\0MP*A\×Ã­ş<–\æVQK\ì\çk“ğ\è\ìN\ï_\ÆM+F¾KD_\Şú+\ï\n\âo\æ®lBh‡\Ğ\Ğ.\Z B\Ñ;\î[8\r˜‡°\å|ÚúZû\"\ÒJ\Ê\æU,/\Õv\nc\ßp£E\ëR\ê<ÁD-®~´b\Âbfœ9\ZREeL\æ»+\ï/–KøV)\Zc\í‘\Ã\æ\Õ.X»\ë÷K´Qù\æ\Øl\'³ƒ@]\ÂVP\Û\ë\Åtş \È\âZ_\åj\å–<rÕ¹b\â\æ_\ßk2¥šø\î\ÑÀR\×\'ğ•×³q=óXS=\ÃIC¨Ÿ´dÔ \ÃrF‘&~Ÿ¿¹P\Ïù\ÒÀˆ§\İócò´K^…õ®Ÿ=œ‰ü¡\ZCK|\İ\æLVh\ç[>@úu`=w(\Z¦?D™>#\n~…Ö•\é?ğVM(>=\ÂX:\ÇeZø\Çşò\Åm)¢C‰¢f°I^?o\0øk\È*\ãs\\ÀA7	«qp½UšT¦…À V %\æ\å°ûÛ”÷Z\Ô„\Û&KN|:q¯f¶~FZ¦°µu†˜\èoŒ6K\ÙU-~“q\Ú Å”k3ˆ·~TU>ò\ã#‰€È¥Ÿq~¬­‰ğ­– «™R¸“Ø°·ö7Ô¶\Í/ÁCE\Ã\Â)Áœ\ÕU\É}½÷HŠ×‘Q\Åß‡\ŞW„\ß\Ø\Ù\ÂW\\\ß\Ç\ã\à>)2\"\Û\Ôa2\è¥\Ï\Ï@›Y\Èõ²şŒK\Ä\0‡6t‰\\h/³±úu\Ö\Ğ\0Ãğ\àCXZ\á\ÂJ.¢¯‘`Ìˆ%\r¼^e2\0U•½’b[\\\íR\"\é\Ü5X¿Dz£·\n<?\åt\"\Ôç¯‡\Û<\Ğiñ\äGRµ_Œ}\çD\å\Ş\ç=\ãQŒDuô+t\ØV #o ‚?Ày\"\Æ\Ú \ÛW\äfG´\ØOPd\îğX:\Ïñ•\ïf—şT‰·ûO¦\ÏÅ½â“¾‘F\0Ò²^/—‘\æğ\ïôD-ßõ\0ò,\Øo\0ø\È*\ãs\\ÀA7	«qğ@Ušch{\â|©B‹–\\„D‹’\ßt\é\Zœ\Ç>\è•D-dC\\¿\Ñ\'³º^3 7£\é5K\â	UMu©¼ø<°Äv4\åù§\ÃUô\Ã_S[÷m€œ\èW\Î\0n2\áŒù\âı#\Æ\É:E\İÿ7?@½š@V,!%\Ê¶3¿ó¼\Ä`\ë1pü©\Úa-\ÙF&H\Æô\ÏN\í§e(Pwû\Ëm¸1,ğŒ¾÷K\ÓÛ†Š“»°,\ëLş\ä@\Î-6€…ijd%\ï÷µz±ƒ(+[X\0£\îó©à²Ÿ1\à\à¼\Â>FcSS\Ç\Ù¼Û”Ÿ—Ùƒ»\Õõ\ëcb\Æh\Ëtª˜”eH.\Ó(¯Ûµh¡õşQ¨4‚«‚³™\é;Š¯\Æ\'\åG¤ô\æ‡\ãf\Äıd\Ù\Â#_\ï’ŠG\ÙQ)\Ú\'+\â<\è\nCpó<\ï[.@,-\á6# lµ8S\ßBj˜À\Ë\Ì\Ôz¸\àcs\ámyg\ß78I¹(öU@\Ò\àe‘JDZV‹µD!\ãŠo\0\èo\È*\ãs\\ÀA7	«qğ¿Uš\ì,¶‚k)øL ¤\"\æõ9\Í]—\äöh„€\ÙB1\æÍ–)‰\Ä_]\ÈmŸUqvª:B/^Ap\Æ<’™®\â«WÁi[\Ïd\ŞË‰”;f™tş^=¹2®ó›nºú5p;¼_î¼\È!\æ^\'xâ›‘±=\ê/Yû|\åoº\èø„‡¨x/‘ga3nU±|E\Ú\ë\ÊqYˆù\Å\å\è}‹]p\Çcòó‰a\":=L\áK ¹Kúó_„\Ë §=\ç&º\Åö=6Y~‡hBÿœ\É\Ûş¥@=ˆ·.iY´üœP\Ãl©Í°X\ç\æ\å?\ç©O‰gø±€\Û‘\Ù`\î¬ñy­K=[mp\r9špÉ\Ø>t`U”@‘Ü±‹\"\Î/|\×\ß\çA…µzaß•\Ö\Î\Í\êø273ù\ryf!Mc\áñDı>B™¸ºó-;m·•¿…\Ğ\Ö	\åvÅ€\n9*œ‹#$®\È\Âo\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0',_binary '1632','02','01',1),('1111','ss','ss','ss','ss','2020-05-23','sss','Masculino','s','s','s',_binary '\0ø~\È*\ãs\\ÀA7	«qp¤Ušu|\å\ë\Ôå¨‘~ş—¨;²\â¯Aœ\î\ÓGz{\Ü8ó`\Z¤ZXL/\Í\È/	õ\Ä6oCh\ïV–¶mğ•{ƒª\à*~Dš]³K\Z¶ ­¢Çÿô©8ª6G_R?	¡\Ü’¨³\ßuÿx‡RŠT\âˆ©\Ó7¥|†\Ü\î„\Ñô+¯\Æ\Ğaô\æ¸\rµE„\ìsŒ˜™/~ŒOP»\ÉJ\ÖF\ÕIuf>\éD®ng¶Ğ°®A†k„\Éomq\ã0“[F7şÀ~\ËÀaMA\Ùa\é	\Õ?F|²x®ú_\\(\ÜOS\éŸ0hôxôt\æ”	·Tóy«2]\È\Èõú{~c4>Ve¦‹\'ø„\æjÁˆ`»A¾}y@	Y&nrŸEŸÁ$õŒ{\â\Í­?\Ã3}%\×df¹@ˆ\Ş¾-Ë•˜H”p3xœ+¼¶\Äcğµ‘.1.súµ¦³\Ä@Ç•_T\Ä)z~|\rğ®aB\ã\âÕ™\×ÿ˜¯\ë±o\0ø€\È*\ãs\\ÀA7	«qğ©Uš\"e#L4\\!ùwñLò®j@{)E9­û•p?P;§Ï’ª\Ç H\Ø÷>’\î„^$}Ûº[bQ¸Š:XœsE\rK7ÁoH[\çƒ\Z¬6n’©ƒŒ+ü3a¥\Ûb\Ò\Îı\å9\Õmzp\"5†\í\åh\ä$\ÚWø^b„¼¨z\\¡\îC5\Ş÷=²\Ú}7œ$¥4]Ã®DAe\ï.´è€ğ#U-ÅF\Í10ŸÜ¸q\Ä&Qü\Ò\r‚İŠ¸S\'\æ(%7˜‡Iò›\n	\áO“lƒª`—15x\ï`{Gs\nÕ„Â€šù\Ï µ¯“&·\Ó}]!\'e¯\èzdh›\Õ(¹<÷	aº€\×¤´”i–c>T”\çx®\'h\Úm\Ñi\Ğ\Ü)\n.\á\ÂÄ‹!£\Æ\Z´FƒşõÄ¯™ÓµùŒ·.RB\'/Š\åz…ù»ÿ5(\Ò/«üşUG\Ë˜®;™yGUˆ\ê¶IN®©N\Õ\ê\Å1\ÈM9\\™®ó¸?!8óû”õo\0øi\È*\ãs\\ÀA7	«qğ Uš[°Á…\êU\Æ\ík7šy\æ@Q¹µuŸº\Â<\ÖCF,N¾*^¶‡Y<Ù¦\à\ë -¼Ns$\Ü\ßõ\æ¶‹)‚\ÒiTH\ç@õ!Ik¦J©\Â\àQ«\ÛA\È}\0ø\í°\áu& ƒ\Èy‹.ÿDÚ„|±§¯\r§°n\Ï*¤²\íÈ´\éHi¥C\ê¤Ft¦\ìm:¨\Øôó÷”¬ñ\ìIZ\Èpşm\ï¾ªoa8®R3GkPds³Z\Êh§-İ\ršô5ı±\Ü1ˆQE\Ô\äQ ?z8z\í\\½\Ô\Õ\'5\à€ó\ï\ì`v]·mü÷\ã|v¼$\É†X\Ï<¨\ìN\Æg…4ƒ$±\Â`\Ò$8\rÁñVFŒ™Jáª¡\0œ\Ü\îdÃ–\ë\Ï\Z¿aµ:3:f}¢)¥Ë#\ç\ã\0K,\Ş``ş[) “$ \Z2\á\ï G°ft”O*\Ïú\ÍØ¬Ö¼…/¾\Æ	ğ/eP½\å\"o\0\ès\È*\ãs\\ÀA7	«q°¹Ušk\Í\Æ*\á‡æˆˆòŠ\ÌO\Ã\0˜EöÍ«À\İ\r]s[DøH\Í\ŞmŠ\å¥\Ò>Ò´†’\ë÷\â\ÃSóö¥W)ñl\ã-ö—_ü\Æú¨\Æ3\\ˆ@œP·5K5WŒB¥ö°r§	\éôi‘–n‰ó¶œÕƒ‡?W\İ3Tu)d\áÜŸ\Ø3ºt•\Ì\Ãı¨¯Eù‘®š¿ˆrBÿI¤Œ\Ë=z2›@\"$‰m\ÑU\n$\Ìl\Ä\írŠ1»/-\î;CcT‹ºÿu™‡’\è„O‡¬5º\Ó›\Ù*\Åí´G8«\Ñ2«’´›s&£®\Å=)öEkª:…”şy8_]L\æax?/\ÔÁ¿ş8\Îñ;ƒ²\Ös½ğr|’Áb°!	É¯q\ZD@E)q{‘Á\Ë-ŒM`¢#\ìAqûû·¼ÀLJxt½­W$\Ô\n`[H¾‹ƒ#\ÃJ6Zo\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0',_binary '1632','01','01',1),('123456789','Maria','Angelica','de los Guardias','Hernandez','1975-12-31','Guadalajara','Femenino','no','no','no',_binary '\0ø\È*\ãs\\ÀA7	«qğ½Uš©\"«}¿\å\ê»ùh»\è»E\Õú\Â\ìIª›õYl1 üq†,-¯¥«0ONBŒŠ\ßË¥)X\İ\êN„ôĞ6\0Ú©\Ó0Šltl\"l•ÿJe|4¾Y\ÛÕ¥ˆÉ—²ê­¾d)\ÄP`…‡ß½¯°¦»ÿ!\ZŸe±\ç#>mg\npl°l¡\ãh^!´›*…?s@\Ü,%ø\ëD®	3\Ì\å¿Y_w¸\ÚNjT¸\Íp”v:€ûy\Ïq\r˜ì“§AÂ¡³%\Şü6\"‡¦\Ù\0¾¡:\Å\ÎD\ïhºü8–\ØT\ÇÕ•\\Rµ€\ÂG\ÎM3J‡@ <\Ó[\"\r.\Ã\ÉL.\é#è’¿¶`&\İ\ào¿%Œ\'÷(2ó>²j\ß·¬%½‡®¬©][!PE)’/ŞªoM÷/¿±¿°ñ¦ş4	ql”FF\İÊ„~¢r¾jô•\n3R--—°ş]Ç„½¸\ç+\ßwªJHH˜¿ør78n`‹c\È>Š@\Ä\r¹µEü“ñ1o\0ø\È*\ãs\\ÀA7	«q0JUš÷ a\Z`\ÌL\Ö\Ìôµ\Ä(\Ïl‘jõwö\É~«¸u›}–²cÑ¼K%xBNr;\Õ@.*x_¨ºu†óix¯”@¿\×mT\'\â@\\\Şvı3c\Ü\Ù[yœ2D\Ön_`§\Z·\ïB\İô>*W™ó+\ŞjCÜ±q å»„A	*V§ò\Ó\'\î\ÊF÷a´†ÿ–·‹\İ\0NÖ·k¥;R@)MA†x†×‹Ş†”X\Ô\ë‚\È\ç®&ÿö52U\æ0;—¸\ï\Ó\Ş{?B½Ê–l,c\ë|8C¹ÒÊœ)ôD¹\Ä\r¶0¦ >%Wa5™·÷\ÒI	›]ô\ë11˜kÉ»o¢\n$§À†®]	_]ütV5\\\ÆV6\Ñ>w€~­~V©\ÌúoPƒ ıô1È„\r ¬r\ÖFÅŠò·\ê£\0C–!‰]3\Új¸$4%rÿ«\ã\'\0*\ç\ËC^9\ÕÀÍ›ó7R)4K¢\Ä\âÑVö«¯\Å)š\\\Ïu†o\0ø\È*\ãs\\ÀA7	«qğIUš¬fZ‡\Ğõ\ÂZœ,%\â\Óú \íò7v# ôPÄ˜\ÒB¸\Ípi\Ã8˜ÿOEK\ç\Æ9q-c\ÑÉ¡Ñ¦Cc=jè°¹ÊŸ£\ç©\Ï\'\ç}+„c e¿,\Ñ3´$HûUÇ²+Û´ƒrFoM\äÿWÎ“\Èƒ‹Üq•\Ô\Æ\àÎ¿òd‰\ï\Ãú apiAyn\â\ß:”\×Ü °«Gf\â8g OŒ\èzE!h\ï:\ÜHdx¼\î(W±V«!\Ó\ÖØ¦½†ú\Ş,ôP¶R\ZTqiv››À¹˜†\ígs‰\êh“Zdy¢®ó«¦\İ\İ\ÜtH\Í5\Ê÷W\ã\Æ\'˜Ä›\ÓM}\é°Ë™œ\Í;\× \éö\Ü`Z\ï¦ÿ\î\rºŒ\Ş¯<4?‡ı–¶–´\ìx™œÿÿ—\Ç\n;ŒT\ZÊ¯$´^‚d€¡^\Z\ÛÆ”Ñ†’x6b»üÀ]\nœÜ\ÎcÜ\0J\ìdZ ±´U\Ïh\"8•\Ú&˜-¨\ïn¡\ZBë¿¿o\0\è~\È*\ãs\\ÀA7	«q°NUš\ËÁÏÀÁ…š\ĞN&À¼¥Òº?:(li5\×Xš\\‡e‚ö\İ\Õ\Ñ\Ê\\sG±µm>\Îõ´<Ik\ÉkO u7¯\âx Ÿ+H\0Zš\'-`ı¾À;+7muñ\ç—\Ë\ë§\ã\Ö\Ò1Ÿv±ù™9\0¦¥\Ä_O2+e\\¹®\Ù\Üø\Ï NE\ï6Y\Òn\Ì\Í\Ç&7V…ùrzdO–*N‚F+.½›•›8ˆc“øz#XĞ™\è]\ãŠû$`©j\Ì0°\ÏI¾úL\Éş+\Ò8$\Â¹¯%4Tf\n³\\½¡?\íNk\à\æí’Šˆò#•(&³§ôÙŠ\Úş¢š@!Gÿ#šˆ‡©¿\Z¾£\Äç‡—›\"¢e\rl\'•\rø&³\nrRr®tÆ’O¯§\ã˜2ƒò\Ö^”W|\Ş×§EWµ\â†?x\ê\×,IôOGœv\æ\Ã—7¸ko@Áº€ıE@\ĞJŞ¥r#™™o\"GIôV\"\ïRzo\Ü\ì¤o\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0',_binary '1632','01','03',1),('12347689','Juanito','','AlimaÃ±a','','1980-01-01','puerto rico','Masculino','no','no','no',_binary '\0ø\È*\ãs\\ÀA7	«q°MUšbYb\àÜ­.Õ·F\ã\É6ùºs\Èú¡p\áO5¡S½ÿÁ]ğw\å\"¹¿\ÆQX_õdT÷B\nÌ®(\æ®.<\"³Sjğ\\*k\Ø÷emÅƒ9^WÉ‘u|\Å\n¯{‹w€\Ë\ÖË¾ˆúA\ã.¼c½´ıP\n©.Î¬\r\ánÀ\êP\×Dúğ$=±¹\Ôa$\ÆZ‰\ÊG\É\Ç!£3¦,ù„¢j\æGÔ˜!He§;\Ñ.€d6Xw¶¶eÊœ\0­\â\ë9•°3\"|¼%1Tmšm—\è\Ê,cFş\Ì	\å\Ğ\Â\ÜM\Z\ãƒOĞˆœ7\äİŠã¬¥¦ g¤]U†òdZ=:Ü¶_\ÅDºFb›=\ÍÈ·\İÀ•Ù	N-HõVÊº²(/÷@öl7y\ë½\r£¼°\×õu¶H\ÃG9²‚\Ìv: ¯É”ğ ]\á\ëÿ6\Ê=q\ĞNÙ’\ÒJ¥(\ÑÈ“\Íuı¨/$¹@‘ğ«–d\ìù\åb/=Xºw5š/W\n(ÇŸtƒğ)4\Ïo\0ø\È*\ãs\\ÀA7	«q°¼Ušûö\Ä0Cdl^€b±ˆ1Ÿ\é÷w‹¦N\ZŸ\'aó\Ø{(\ÔqX 0{\ãe—\Ó\Ó+¹ö«j)s\Ø\é\Å=¶\ç¥\Û	\ØuZnyAf€„\\¨/\ã\×fX¾\É2\ÉQ3\âk—?\å\ĞC\ì\ËC\ÒPzJEz8gŠ|¬½V¶\Ûw\Ú\Ù\Í;Rû\Û\ÅdóT\İğÑ¬¶\Ä2ş¼¨Ÿ¼*ñ\à;o\Â9™UX\ë\Ú(\à«)\á \Å/4Ëlbñf´õ*Q¢F±±|´\Õ\Ùy5ª›d\áx¸€rÀ«ı|ú\Ü\Ğ\Õ)£	¯e\\¢Ì¶Aó›\Å\É\â(Ã”\â§\ß\Ïe£Ÿ—’%ş}ütt¯˜$‡+m\Øğğ\Èz:\rlY¾¾qJ\Ïû\Ö\"ÿxH/o%¦€sQ5P\Üw6B1s.²j78A,\'\Âm\í\æ\"xYa9j”…[³*2‹sª\ès	–bVE\Ò2\ÍÃ¿™\Âıi4\Ñ}9Œ‰ú°ÁõÌ›HXWP*\É/š‡ùI¦ğ‹ş5o\0ø~\È*\ãs\\ÀA7	«q°¿Uš½ğv¸\Ë\åx\Ô>ñM\åNş1ayW¸H\áıñ=\İb.Ş–(£|\Ë\r˜.M¶P¾®-©%Ø™N\Ñql·!¬$\\`¬û_ê¼½ÿıP†$(+iL¸\Ã:¢­\èo[…g¿\0·”d»ÿ[\ç¥õ\Ù\ë\ä\îƒ\Ç,=@½/ô~fä¾¸\Ì\'C7_´\ë¶xºF\Í\éù\îTö<\Å\í\ë€\ìô*S\îö\ßn¸5¬øğ\ä\Ëô…B\n¦x)¿%}Q0	\ÓÀQ\ë©\Ğ\Î5z\Õ{J:&Ò¾\İ@÷\Ío\á	aG\r¢s\Û\î\ä\íÊ…»\\ùJ5ø˜·\ÙrS‘\Ú\Ñ\âGdn·fşøa°‡E#{©fÀ{şEdX\×\éªF¿y´­\ã\Ë2û™ø±†¦4b—uºğ\Ş\ØÃ›W]\ì\Ùõù\áZ\Ôs\Ún\nğÀ\ëñ\Î\Ø\ïn<ÿ‹ß¢®½:Pş\ÛMkJÃ‹\Zk¥ˆ‹ú\Ø&\Ítº·†…Ë™\É¿²21f\êo\0\è~\È*\ãs\\ÀA7	«q0GUšÿVª\àd$ŒCú\\wL„Go=8\ß\Ø3§\á¦\È\×UpÌ¬FÛ\ÛI¤,Pôja€,úZ4r\æ=\È|\'1\ë¹Í–y\ål—ì¹ŒK\\/\0´\ê©ZZ€H\ÃR÷Œ©¨Q¸—Ş©u\Èg#Ú«¾\ŞÆ¼ ()‹_}ğ\Â«´_©c£\"/¥I‡êŸ° Vpù’OÜ®\ÓÙ»\ç\Ù´$	 @?S÷-‹\ĞS^— \è`#J`M\æU–	ŠŸ•xMSşØ®Ld¼·O€\é\rPšaoEú`òİ™)D´Ë³„R\ï^é®’s\Ò\Ï\Ú,\Ñ\â@k\ÕF9N½v\ßgT‚íœšõ\ë¸„\":ßµ!4`*”“s\ÆCƒ\Ô*[’2)C\Ú|±¼ó\î\Î*½]•\çğ¿*Ikü\ç\Û\ÂıV•ºT\Ó©§–x\Ñ-~À¿W‰ò¯u:\Ù=\â`o>35ú¹–\Æ_¡Å‘\çÀxµ€y9D{\í‡2\\ o\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0',_binary '1632','03','02',1),('15327400','Nelson','Giovanni','Salazar','Roldan','1973-08-26','Carrera 78 A # 67-54','Masculino','no','no','no',_binary '\0øZ\È*\ãs\\ÀA7	«qp¨Uš­wx\ïòÈ™gü/\Ï\â5‘z‘üe\è\á=¿J>/\ĞfV\ét\ŞbD_\Ñ;l\Ï2Ö›ûs¾&…VRø–Ê˜²|š\É)¡.	PAôú\èùü>µx®\×wø&4^\ÌôÄ¶7˜\ï\Â JtWZ^\Ût¦\î\rE\ŞN\Å0zñù\Z@3Üº4–4®	s4F)8\È;S\Î+lˆ¦ü¤_—\çU×“sN*^õ ş \Ï-ğ… D®f\Â(¨¨PZ†ƒ©®®ñ;	\İ<°\íf\×s\á\r7	.77/Ï„1,r\ÛPùò6+Ÿş\Ò$\Ü2ı!•@’º~—EÁÑ±L	)\na¾Îª\Ìu^\ê/¶¾\ÜF\ÆÌ„¨ w›‘\ßI°8un0¨¼#\Ó;\ìô\Ü#göbC\èø5DPCQ@Qs„«šaVg\ì\Ä\å\ĞÏ³QÀ¶\Óú¶–`¤%ZŸ¦0?¿.\ÄIo\0ød\È*\ãs\\ÀA7	«qğªUš\Ñó\ÑD:\ÕM4\Èeßše–\rûh:»[³\ãÌ²|«\Ø~HÁ·,ü¨Tö¯IÑ¯¾*[+p\í/vQıŠRú‹ı3‚VjV.ÿ\ÂsjZ€†j\Ú-·\æ\åö\Òqj|y8<7=œóI\Ë|^\Ş\à„üyLˆõ[¹‘\íeT\"0%Œ1\Ì\áWL*<\âşª\åQ\Z\nq\ê9^bk¹şvUJS÷(Y¶GX@Ÿ-üu‘C¿E\à!\"\'?h+\çI\èm‰,€ğ¹\\E\Ñ\êza%¾·«l|\Ä½±@\â+ùG\Ú_c²~@Ø˜gO*‡ø²“«P‚x¨\Ó\Ìù\Ê[\Z\Ğ¸\Æ\×S§\å†-Á~ÂŒ¾3}l:Qùf\Å6–óN=œ\'}v%3\\\áY†ç²ŸOb\Ğ0PÇ¦H’6b\Ú\á×¿³¹5¾)i-\æ\å >^aœöúŒµƒ\à“’rñÒ¶+­€ôvÿu\'9™o\0ø\\\È*\ãs\\ÀA7	«q0¤Ušk8[*Z1UŸ°{Ç˜õ\Ås\ä\'¨\'\åö#K\èj´x¶\ïbRl³z’‰\ÓG¬`\Ú\å•h\ĞH\Äc±R(9\n®Á«ş\Ö\í\âÿ%§Ö–\íıdÑ?t­\ÛC¡M-·Üµª\æ\0·@¢<Q\Ç\ËGö†\Òl<g#¿_\Ë[±h(\È\ê\Ñ\ã–{^-aÏ¹Aõsc\'i1>qñ\é)Ä¯%JYrÄŒ\ÔÁ—\ÕU”–­õlf \Ûøha‚Ï•˜—Gö‡@(\Ü	Œø\Ö\Ôô¬Fº6·(*˜`w¡xi)\Ä\èX\é13\ß\Í/\Îm½Ü©_	4\ä\ÌF2s‰hT5B\r§û\İ\Å\Ã\ÎEŠ\ä*¿•¶u²ß¹u_n9\Î\Ó*v\ßLM¢\\Yo¸j6\Ï\Ü[.Z\àÉ¿J‰l>À\ã\nioQ\ÖF-ûnS\î}\ïD‘0é²½x\æ-ö§#û=E^†•)o\0\è\È*\ãs\\ÀA7	«qp„UšÈ¤^%Bµ$\ä\ïû\Ç\ÇC\äÖŸYs\Â\á^!\âæŒ\ÊÛ…\n\Õ(Y\ë\áò\æ‚	öÄ¹m~\×±™\Ìn\Ó3°·M>\Ú\nec*<\Ù\ÖT:³»%\ÉI\Ä5’CU+O—ğx6au‹(\Î\ä‹1\Ö\ZKF„\ÕBey›¦	s\Ñ\ëO\Ê\'+¶\ç—;Vü\î%pGB:C½¾A0^\É¡F \Ï~w+ˆ·ø\Æ3¸ñ‚\Õø\Ù«\Ö0Vÿ/ †–ß¼¥‹4Àc8§Vş\Ë\ÇX¸ñß¦_>1\î\è{!\ÙYš¨cS¹”\'s\İ\Ü\Ê>ö\Úi\ávm‘³–f|É¯*\éª\ë;^ı«´{\Éù°|’\ã¤\Ù4;‘H³\ã\à\Ş%m4\Zo\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0',_binary '1632','01','01',1),('222222222','Paquita','Rosario','La del Barrio','Mendez','1990-01-01','Armenia Mantequilla','Femenino','no','no','no',_binary '\0ø\È*\ãs\\ÀA7	«qğ½Uš©\"«}¿\å\ê»ùh»\è»E\Õú\Â\ìIª›õYl1 üq†,-¯¥«0ONBŒŠ\ßË¥)X\İ\êN„ôĞ6\0Ú©\Ó0Šltl\"l•ÿJe|4¾Y\ÛÕ¥ˆÉ—²ê­¾d)\ÄP`…‡ß½¯°¦»ÿ!\ZŸe±\ç#>mg\npl°l¡\ãh^!´›*…?s@\Ü,%ø\ëD®	3\Ì\å¿Y_w¸\ÚNjT¸\Íp”v:€ûy\Ïq\r˜ì“§AÂ¡³%\Şü6\"‡¦\Ù\0¾¡:\Å\ÎD\ïhºü8–\ØT\ÇÕ•\\Rµ€\ÂG\ÎM3J‡@ <\Ó[\"\r.\Ã\ÉL.\é#è’¿¶`&\İ\ào¿%Œ\'÷(2ó>²j\ß·¬%½‡®¬©][!PE)’/ŞªoM÷/¿±¿°ñ¦ş4	ql”FF\İÊ„~¢r¾jô•\n3R--—°ş]Ç„½¸\ç+\ßwªJHH˜¿ør78n`‹c\È>Š@\Ä\r¹µEü“ñ1o\0ø\È*\ãs\\ÀA7	«q0JUš÷ a\Z`\ÌL\Ö\Ìôµ\Ä(\Ïl‘jõwö\É~«¸u›}–²cÑ¼K%xBNr;\Õ@.*x_¨ºu†óix¯”@¿\×mT\'\â@\\\Şvı3c\Ü\Ù[yœ2D\Ön_`§\Z·\ïB\İô>*W™ó+\ŞjCÜ±q å»„A	*V§ò\Ó\'\î\ÊF÷a´†ÿ–·‹\İ\0NÖ·k¥;R@)MA†x†×‹Ş†”X\Ô\ë‚\È\ç®&ÿö52U\æ0;—¸\ï\Ó\Ş{?B½Ê–l,c\ë|8C¹ÒÊœ)ôD¹\Ä\r¶0¦ >%Wa5™·÷\ÒI	›]ô\ë11˜kÉ»o¢\n$§À†®]	_]ütV5\\\ÆV6\Ñ>w€~­~V©\ÌúoPƒ ıô1È„\r ¬r\ÖFÅŠò·\ê£\0C–!‰]3\Új¸$4%rÿ«\ã\'\0*\ç\ËC^9\ÕÀÍ›ó7R)4K¢\Ä\âÑVö«¯\Å)š\\\Ïu†o\0ø\È*\ãs\\ÀA7	«qğIUš¬fZ‡\Ğõ\ÂZœ,%\â\Óú \íò7v# ôPÄ˜\ÒB¸\Ípi\Ã8˜ÿOEK\ç\Æ9q-c\ÑÉ¡Ñ¦Cc=jè°¹ÊŸ£\ç©\Ï\'\ç}+„c e¿,\Ñ3´$HûUÇ²+Û´ƒrFoM\äÿWÎ“\Èƒ‹Üq•\Ô\Æ\àÎ¿òd‰\ï\Ãú apiAyn\â\ß:”\×Ü °«Gf\â8g OŒ\èzE!h\ï:\ÜHdx¼\î(W±V«!\Ó\ÖØ¦½†ú\Ş,ôP¶R\ZTqiv››À¹˜†\ígs‰\êh“Zdy¢®ó«¦\İ\İ\ÜtH\Í5\Ê÷W\ã\Æ\'˜Ä›\ÓM}\é°Ë™œ\Í;\× \éö\Ü`Z\ï¦ÿ\î\rºŒ\Ş¯<4?‡ı–¶–´\ìx™œÿÿ—\Ç\n;ŒT\ZÊ¯$´^‚d€¡^\Z\ÛÆ”Ñ†’x6b»üÀ]\nœÜ\ÎcÜ\0J\ìdZ ±´U\Ïh\"8•\Ú&˜-¨\ïn¡\ZBë¿¿o\0\è~\È*\ãs\\ÀA7	«q°NUš\ËÁÏÀÁ…š\ĞN&À¼¥Òº?:(li5\×Xš\\‡e‚ö\İ\Õ\Ñ\Ê\\sG±µm>\Îõ´<Ik\ÉkO u7¯\âx Ÿ+H\0Zš\'-`ı¾À;+7muñ\ç—\Ë\ë§\ã\Ö\Ò1Ÿv±ù™9\0¦¥\Ä_O2+e\\¹®\Ù\Üø\Ï NE\ï6Y\Òn\Ì\Í\Ç&7V…ùrzdO–*N‚F+.½›•›8ˆc“øz#XĞ™\è]\ãŠû$`©j\Ì0°\ÏI¾úL\Éş+\Ò8$\Â¹¯%4Tf\n³\\½¡?\íNk\à\æí’Šˆò#•(&³§ôÙŠ\Úş¢š@!Gÿ#šˆ‡©¿\Z¾£\Äç‡—›\"¢e\rl\'•\rø&³\nrRr®tÆ’O¯§\ã˜2ƒò\Ö^”W|\Ş×§EWµ\â†?x\ê\×,IôOGœv\æ\Ã—7¸ko@Áº€ıE@\ĞJŞ¥r#™™o\"GIôV\"\ïRzo\Ü\ì¤o\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0',_binary '1632','03','01',1),('244224','fernando','','munevar','','2020-05-14','castilla','Masculino','a','aa','aaa',_binary '\0ø€\È*\ãs\\ÀA7	«q0MUš\ä\ëxI\ÎfÇ \ì¶\Ì%z\ß\È†l€E„\Û\ä;òo±3…\ë\Î\Ï¦ªq\ã\İ5&¬{a”\ÎÁ›\Şù7?ÿ‚\nB”(¸•\0¯b¦;[³jw¹²%º\Ù.J+@J\ÍY¿?\Ë	]\éGc9ÿ±\â­\Ã&³#<\r¯j\Ïã«…A{õ&³L €Ä­°´µ®¿4„ùKô÷\Òı1{ğq:¢ªg	 \ãf\é’\ß²AljN\è¼ÿ\Ñ%\rv\á¸I”¢½W\Õ\ë…l5—˜¢I”s)1ğP³QZ±\Ì\ä\ë]ójøFõ\à@KUŸò¬K‰\×uÊO\'J+¦Œ£\àAf\ÎAƒa’\\Œ(½ğõYE\íD,›	U\r\Ü\é—\"6\n†Ÿrˆ\×Ú‡9f\è8\Û\éÇ…¡Ş¸W6Úš\ß\Î\äğ\ØcN\ĞV·\0v\íùL-\à. @Ó„¤.\Ü%„+ke@Jµk‹\\mö`qœ(¥¦]\âÈ ğ}rG\ß—co\0ø\È*\ãs\\ÀA7	«q°¿Uš¾\ä=²\Ûë¹–g]Õš\ã¢K;\âÂ²f\è§z,_\n‹ñN;Eµ³Wpş\É|XC½øƒo0ßˆ\Ô	öa6Qò:^\İ)\å2úmr]Á_¬D\Zpfw\Ú\é\ãB*­Š\Çh\Ş\ßyY¼\ĞÉ \Zglh³…İ©D\ÏüX\àûUE\ÌPôls$L‘‘.ô·6ı\æC\İ×œ9”%*¥7T— ùÿş\åWõ{\Ñ\ì\ÑEv\0\Ñ[\ê\ÔUBR\êE-H*“”\Â^¥3E[Š4ª\Ì?\á\ï·\r}Á˜\Ì:d cmù Á\ßnñ\å6\'\ÑÆ±8«Xš¼6\\A¹\Î\Æ\ã14Ài%\Ævh~kÿm.\êUf%Zzl\Ôø\ç\Ú\İZ·«Ş<—\ÅòOı¸\rwdÿƒSùÚ»Éwc\ä\É\ì†@u$\Ò=>¥\×\ØM}\ïòø-`—öÇ³\Ğ:\ÚUWc^.L»|w‹\è{\Åğ¡$\ÔF‡\ÇR\ßK%±À\Î2\Æ\0ó‹t—0\å¢;ˆo\0ø\È*\ãs\\ÀA&-ü3N\èBF&#O\ë\à*2m²I&\"FS§÷ÿQ½k‚³›?¢ğ\ÉmÂ€`ş ióñös\0\ÌRt·Jü\í;Y‡\åp\ÌuŠ\çƒK†÷¯±×»\\·X\\³„Ü½³©‹—|¼\ÑÖ›‰8eRó@\ÃÇ˜)”@\rˆÅ—·ª//Pô˜ºù®\×-/V\â\Ş\ê—&t‡±udË \ëdg¼õ\0ûU±\Õ\Íj¾Šõ·“C^ûTú94|\"ro±tAŞƒ‡+\Å\ÜSµ“g\ÊÏ‰—3°šYf‘›S\Zõèœ¯l„2$n´¾±\é;‰³RNy3Ld*¼º\ã\Â\ï˜•†\Ê\0\Ïõ©¬ÿ´\Õ`R\Å&*”.}m‚}	ş\âg´KK\ĞFRAl¾ı5ö=´û<:kx{E•j]¨XF$Ÿ?ú£u9œóX\ë‚#<U\Ø&,¯<¿\íZÒŠš\î\0\Ä¹I>ôüÁúùfo\ÑVe¼\×EÁn”Eño\0\è~\È*\ãs\\ÀA7	«qğNUš›ôÙª9\ÕÑ¢³š\Æô™‰B‰N\Ğ\Ó\rÄ¥³<k™\ä\Å8J,?\Éy<œB\Ó`¼Q³\rqY5e‡v‘¡­\ÖümÇ§l=aÿ³Q@{æ“\'õ„¤b³e™­rE´‹\ZJ\Ê\İRHO=ø}½i\Å\â^puNYŠ¤U“}­º([C4ò\æ1\Ä\\a\Úd(z\æWjpû\Ş\Æ\Ï7\æˆ\İ\Ù €—\'ûwbo%©Œ”P\âù~\æJe°\ÛT‚­\âj_\ìf°ô#\è€\ä-?	J \ê\å\Í\ãƒ\á[%¡—§TwÁW­}¬g\å\İm¶›ş+ºü/¦rgzS;9Ä‹ªxÿ_\Ïr\åWz\ÌÿúqŒ¶9\îš\ßPøœ×ƒ\n3\rX9‚j\ÉNç·´½®v\ã\áy‡¤_x¨¥ÎˆM¶^$le>¹\ĞŠ™*³\Ôú\ÂUQIy’1JDK\êz–8Iûo\Ó\n¢uD\í\Â\ÒWùxö˜S@¶s\Ñ6o\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0',_binary '1632','01','01',1),('4444444','Stanislavo','Armando','Kiatosqui','Correa','1954-01-01','Ucrania','Masculino','no	','no','no',_binary '\0ø]\È*\ãs\\ÀA7	«qp¿Ušrbø{\Õ\"‘Ñ­¼\á\Î\Ä[”©õ¬:ü…ÿ­û^¦D•0¸\íJ¸(•$‰¸­,5\æğJ,u~(q‡\ŞslŠ¬}X\r/\æu—}\'\ãëœ¼\Ùğ\Í*€\Ò=Œ:J\Í,±q˜\âGÈ¯\ê>!jğ¼\Ï	Ûœ*˜û¼{¹],t°{Mˆñ}\Ó\0SÍ³uØ¡ù!\ï\ãŒb„ÉˆiD}n‘\ïQÄ»ø	\Ø1Zz½Y}>¹²#L,A_•hDØ„±\ZI³O98I\Ö7i@%¥Gf\ãÆ›\Ãôªg;À¿)˜•\ZB\ÃÊğf $z²‘‹7o+µ_\à£_À<»rm<¯ß±§0ş`\Ş?z\Z¹kk/ññto\'±\ëöv|\ïó3NŒGövòC|\Ôf`\ÏLü{+us»M4\îMi8P¹3õKó²R”9Ù¤)\nõ(Lo\0ø\\\È*\ãs\\ÀA7	«qğ¼UšÃº½bFş”j€(\Ìw(“\ê¦\Êm\È¹¡\ì¹\ê0ü]3ÿÈ¦e\"À¹\Ú\àkq\èb\Ê0y­–¹Àòr*<k°+¹\é\r\ç^õ…T¬>¦©=\Òü\à,7 \áss\è\ã…T\ä˜W¸\ë\n/\Ğ~’ &VRLğFKf\Z/Ÿ\è.¦$mdc\æº\Í	·\ÍW‡½\Z­5a6\ê¾¶]\Ã~\Ëşü\Ùÿ\Ç\æ\ìt>\á\×õ‘TJZEƒ\0ó\Ìº\æ¥/\æÁ®Wƒ\"C[\Â\×oL*‡	\Z¥\ÉU°{\Ó\ï\Û,}5C|S±ñ¬éˆ¤3<¡Y­KùşG¨÷ÉW ˜Å½¶g³ğI\ÈB\âÁ&fJ\Ì\Û&Jß…v2s\Üfè¹‰Y\ä\âV= ŒY7c‹\Ì!œ\å÷V¡ˆrŠ\İ2ÿ *cƒm‚_\Ï]ğ“:\ÅWùs\î]Š\ïndN…³\Îÿx÷o\0ø`\È*\ãs\\ÀA7	«qğ½Uš \"—Ge°\ï\"ƒr‚u½P\ÌÊ¤\Ë\Â\Ú1b©\×?h¿™—‘\İU\Ù=\î€\ës¦W‹ƒ«a½\ĞŠ\nD?œÇ\Ï=JÿZ\è#”–¼\Âye+\Âe£\Í\ítªöÿ»œ¨\áŠs¦€G¾\Ú\ÂÓ‹‹\ÊFA$@G¦x\Óí³ù\à\è‘Ä¥&y›\Ø%\Ø\æõUt hù\Ø\í{j‹9!coğ7##ü»j{\Æ\\$\Şpt}R=¬Ššû^\Ğr)o°À:…®T¨\ì©g‹‰\n\Ïc;e\Õ õ=Y\Ô~üõ\È7i\ë\0\ê\ÇQ\êùXIUt10…L^¥jŸÃ¯‘j=ş^#\'¾ó\êZ(\ì‹?.øÙˆ\0’©ÿy`bO\Öho;\n~\ÊÀu«œµ67’÷†ñ$Î¦Y\Ñ&E#VDè¬®@%¨\r\\I\"x\Z\àJ[òÔ•×š…¤\Ù\ê]Y„\r\è\"©\ÑRo\0\èT\È*\ãs\\ÀA7	«qğ½Uš¥\"’¿o˜\î(b«7ù\ØI«\ÓVš‡®<˜E‰cO\Å\Çğ¡½{%\Îe#ñMrEC“%µ\n~\Ê\ÈÏ³KjõZ?\Í*<–qMß(V$ noE˜œµ+Z\Û\ì8T\Õ	e”i\Ç\ã|#§µ\æô\ì6n˜‡«\â´u{‰»zõ\É\ZMJ« š\Ñ8Q·„B…h†F<)\í\ÜÁñ€ó<£TŸUAŠDŒ€´\Â9CTú\0a*+\æy\Ä,|.\Öt<…¡€!\é+©/aMA\'AuCb†\Ìg½—f¤>#|@$\à\ÚcœÙ½V+ù0pL·RsŒM\Ó(dŸ„<\Î\Ïö#,ôp9F—^KmÓ¨\Ş0	\à8Î¡yŠŒD¥´u\Æeÿ?—@;Oüõ\ÏÀ‘¼_5¸QL‡>UL~®VMûô-ˆ•RC’Ÿo\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0',_binary '1632','01','02',1),('5433456','Pedro','de jesus','Paramo','Cuellar','1965-08-24','Peru','Masculino','no','no','no',_binary '\0ø\È*\ãs\\ÀA7	«q°DUšdošqu\Ï&­¶\Ît;¾°\æY™¬\å€ø!\Ğ\Êø\ÜTC3˜\0MP*A\×Ã­ş<–\æVQK\ì\çk“ğ\è\ìN\ï_\ÆM+F¾KD_\Şú+\ï\n\âo\æ®lBh‡\Ğ\Ğ.\Z B\Ñ;\î[8\r˜‡°\å|ÚúZû\"\ÒJ\Ê\æU,/\Õv\nc\ßp£E\ëR\ê<ÁD-®~´b\Âbfœ9\ZREeL\æ»+\ï/–KøV)\Zc\í‘\Ã\æ\Õ.X»\ë÷K´Qù\æ\Øl\'³ƒ@]\ÂVP\Û\ë\Åtş \È\âZ_\åj\å–<rÕ¹b\â\æ_\ßk2¥šø\î\ÑÀR\×\'ğ•×³q=óXS=\ÃIC¨Ÿ´dÔ \ÃrF‘&~Ÿ¿¹P\Ïù\ÒÀˆ§\İócò´K^…õ®Ÿ=œ‰ü¡\ZCK|\İ\æLVh\ç[>@úu`=w(\Z¦?D™>#\n~…Ö•\é?ğVM(>=\ÂX:\ÇeZø\Çşò\Åm)¢C‰¢f°I^?o\0øk\È*\ãs\\ÀA7	«qp½UšT¦…À V %\æ\å°ûÛ”÷Z\Ô„\Û&KN|:q¯f¶~FZ¦°µu†˜\èoŒ6K\ÙU-~“q\Ú Å”k3ˆ·~TU>ò\ã#‰€È¥Ÿq~¬­‰ğ­– «™R¸“Ø°·ö7Ô¶\Í/ÁCE\Ã\Â)Áœ\ÕU\É}½÷HŠ×‘Q\Åß‡\ŞW„\ß\Ø\Ù\ÂW\\\ß\Ç\ã\à>)2\"\Û\Ôa2\è¥\Ï\Ï@›Y\Èõ²şŒK\Ä\0‡6t‰\\h/³±úu\Ö\Ğ\0Ãğ\àCXZ\á\ÂJ.¢¯‘`Ìˆ%\r¼^e2\0U•½’b[\\\íR\"\é\Ü5X¿Dz£·\n<?\åt\"\Ôç¯‡\Û<\Ğiñ\äGRµ_Œ}\çD\å\Ş\ç=\ãQŒDuô+t\ØV #o ‚?Ày\"\Æ\Ú \ÛW\äfG´\ØOPd\îğX:\Ïñ•\ïf—şT‰·ûO¦\ÏÅ½â“¾‘F\0Ò²^/—‘\æğ\ïôD-ßõ\0ò,\Øo\0ø\È*\ãs\\ÀA7	«qğ@Ušch{\â|©B‹–\\„D‹’\ßt\é\Zœ\Ç>\è•D-dC\\¿\Ñ\'³º^3 7£\é5K\â	UMu©¼ø<°Äv4\åù§\ÃUô\Ã_S[÷m€œ\èW\Î\0n2\áŒù\âı#\Æ\É:E\İÿ7?@½š@V,!%\Ê¶3¿ó¼\Ä`\ë1pü©\Úa-\ÙF&H\Æô\ÏN\í§e(Pwû\Ëm¸1,ğŒ¾÷K\ÓÛ†Š“»°,\ëLş\ä@\Î-6€…ijd%\ï÷µz±ƒ(+[X\0£\îó©à²Ÿ1\à\à¼\Â>FcSS\Ç\Ù¼Û”Ÿ—Ùƒ»\Õõ\ëcb\Æh\Ëtª˜”eH.\Ó(¯Ûµh¡õşQ¨4‚«‚³™\é;Š¯\Æ\'\åG¤ô\æ‡\ãf\Äıd\Ù\Â#_\ï’ŠG\ÙQ)\Ú\'+\â<\è\nCpó<\ï[.@,-\á6# lµ8S\ßBj˜À\Ë\Ì\Ôz¸\àcs\ámyg\ß78I¹(öU@\Ò\àe‘JDZV‹µD!\ãŠo\0\èo\È*\ãs\\ÀA7	«qğ¿Uš\ì,¶‚k)øL ¤\"\æõ9\Í]—\äöh„€\ÙB1\æÍ–)‰\Ä_]\ÈmŸUqvª:B/^Ap\Æ<’™®\â«WÁi[\Ïd\ŞË‰”;f™tş^=¹2®ó›nºú5p;¼_î¼\È!\æ^\'xâ›‘±=\ê/Yû|\åoº\èø„‡¨x/‘ga3nU±|E\Ú\ë\ÊqYˆù\Å\å\è}‹]p\Çcòó‰a\":=L\áK ¹Kúó_„\Ë §=\ç&º\Åö=6Y~‡hBÿœ\É\Ûş¥@=ˆ·.iY´üœP\Ãl©Í°X\ç\æ\å?\ç©O‰gø±€\Û‘\Ù`\î¬ñy­K=[mp\r9špÉ\Ø>t`U”@‘Ü±‹\"\Î/|\×\ß\çA…µzaß•\Ö\Î\Í\êø273ù\ryf!Mc\áñDı>B™¸ºó-;m·•¿…\Ğ\Ö	\åvÅ€\n9*œ‹#$®\È\Âo\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0',_binary '1632','01','02',1),('71660668','Santiago','de Dios','Higuita','Campo','1965-01-01','Girardota','Masculino','Penicilina	','HipertensiÃ³n','En Cuarentena',_binary '\0ø~\È*\ãs\\ÀA\"‚3\Z$ş\Ñÿ$f\Ä{Ë¦Z\×:L¿³P…wwfùm„m­\ÂwiH6\çu&||cóı\î<\éiR\Òh\ÕcS\Ü\ÇıK¼÷\ï=z½^eöC9ò+h•ˆ\íZ‰ÿ&HÇ€§\İ\Ôş)e\Ò?1Šğ\ÕGg’w¶D/DŞ<:S£(ô\Çm\ãMc\èÀúwx­Ÿ\Ë<TT\Ö\æ+\ÉÆp\ëiZ€·Za+\Å3P‹„\å=\Ã‚mpa!\Üò¡Jg\ä5Õ‹up‡°À÷xiIƒc\Ã\Ë	\ä–\æo\Ş<\É%c+xôb¹±¿\Çÿ\"W•—ıC¿+)B\î0ø4ic[\àÎ„\Èú\í\ÜeV­5\å\0˜øO\"• ÉŠy­ˆ\ÒòX\ÑM;rR»RO®½o03!jI¢›É•\0Ÿ­\ÍLPR=/\r\Îô\Ùı\í\íŒnpŒB\Ï\Üò\"#õ	6\"õòaF	¨n×¾©—º\è\Ó\rì½¶­\á{nJyzgUö)„(”ı¬‚\×y\ï\ÒcH7NW¼%{o\0ø\È*\ãs\\ÀA \Æ1c\nbAt§zñ.X\Ç\èEmõb”\íPÒ•C$!4D:U­\Z«`İ¨5]\"Æ¤‚\Ùøñˆ\ß\'V\Âı\"\ïM\êJtv·vÁõ¦-\êı€—EiTÛƒU~²Ë¯€º\Ë\è\àó\\ûûğg[7\Ñöoj)\Ñ\"8\Ù\ĞÑ›1b>­Å´”«?wt(‰ò\×\Ñı›\ê\ï±`,\âÙ¿E\Û9>¦l{O\Ç\Ó0ôñ\í)¥’\àö\Z‹½ú\rËˆ\Òz\íğOû\Çp)é´…–^^m\ì\Õ7A5ª¹s—#\nÊ‘¡=\"=ä€—/DB\rôÏ°5C;z2$¢0L\æûŸ‘­ú/§\Ù@zm‘(ôa\0<ô<\Åÿ»ô#\ærA—8\Ôÿö|T	8+\á\ã]ˆ`§qöf|şlğ>²K\ÎÀ(…\Äõ©\çA÷\Ú\è8DN\æƒ6”òû\È\Ûtõ\ã¥SPZ(V‘Q9õ§¶\Íò\ï®\Ãk9¡R]~6H\îE\ßgmO05\ÔAb©çœªŸ\Å(‰~‰o\0øY\È*\ãs\\ÀA7	«q0¯Uš>ñ»pt\í:©A®´¤1d×ö\Ô$S\ìf]˜P­l\Ğù®\éB¥\ÚT‘ğ»ö$‡«x9N7—¼‹0\ÒEİŠT\ãKÕ¯vn¤l¿»ÀóİºM5ô)#”M\è\ÓôÉ«\Ã\Òñ¹\çD\àILq\ëš¬\0x\Ê\Ü÷Z…\í\ê1¹\í÷±º¬$ø‰{ùœ\É\rğq¥|ób´ÿ}şó\\§l\Ù_–0\Â!;Ğ•k\Èùvn¬\áp’²~ miu[Xlöy7\ßû\ßÄ¡vFl\Ï\íO;Èˆ\äsm\Ån˜\Í\çKxZqƒb˜¦€…„3AH$¡\ÑC-õ\Æ<ú\"\é|¦ôwv°YE\ï\Ñ[‰_‰`·\0#r­\rò\Ş\Ãûu \ÊYR&U+\rH_øºZ6Ÿ\Î;w-\Ê\ØñŸ\Õ\âFğ´û?*$ø#\ìÜ¥x–È–‰WnT\Ãfò?¯4\ÍEVV\"©Ii›o\0\è]\È*\ãs\\ÀA7	«q°›Uš±ÿau¤ü‚\Â÷ˆ›w\Ş2\'³<]¸d¯ƒ§S^–1Ê ~eŸr3jı\èñ©\Û9Ÿ\Å\áŠT\Ü\Æ÷8—\Ö\â{\Ô\ë¢\"§>)	:Gò!\Ò\r¯\àœ7%‰VPÄ†;NoEÀô\êP\×c\Ğâ‰–a¸[#¡Ê¾xE‚ÿ”ÛŒ\Î\ì\Ä-\é(}Nn^ô$\Ô\Â\ÑTJ¿„\ÂIhO~¯ û\îp	¤@\Õó\Êôö\Í7@9wM\ÇûUñ¼\ã4s\ÛMâ¿•¸\É%”\'\Ë[‰\ïótV¶#AÊD:¾\äH\àıüh­¼ø49Ãª³ò›\é\Û^ ûHE2@\Èe‚ƒ\rë¢¼¸\Ï\0 ˆT6û–$;s\Ïbø–EªŸ\Í{\Í>Ôª¸“N}¡\æ5¶}@jN\r–\ÚÁZ@ä„†•e‡¶myƒÛ¸õ%J\ŞN.m·r¤ª\çf,’ËªS„I\è÷¶‰\æio\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0',_binary '1632','01','01',1);
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
INSERT INTO `documento_entrega` VALUES ('01','222222','sss','sss','sssss','qqq','01','2020-05-23','11:21:13','01','no firmaron la remisio',1),('01','123456789','Andrea','Maria','Echeerry','Garzon','01','2020-05-23','16:21:49','02','Paciente entregado sin ninguna novedad',1),('02','100001110','Felipe ','Antonio','Echeverry','Martinez','01','2020-05-23','16:23:40','03','',1),('03','23456789','Carmentea','Antonieta','Belalcazar','Pastrana','03','2020-05-23','16:28:54','04','No firmaron la RemisiÃ³n del paciente',1),('01','44444444','Maria','Antonieta','de las Nieves','Chilindrina','02','2020-05-23','16:32:23','05','',1),('04','800003456','Maluma','Carlos','Perez','Osorio','01','2020-05-23','16:35:51','06','',1),('01','21212121','jose','Hernesto','Balvin','Jbalvin','01','2020-05-23','16:38:33','07','Paciente entregado sin ninguna novedad',1),('01','333333','Antonio','Jose','Caballero','Campo','02','2020-05-23','16:41:58','08','',1),('01','222','dd','dd','dd','d','01','2020-05-22','07:32:15','1234','',1),('01','222222','ssss','sss','ss','ssss','01','2020-05-21','11:50:09','12345','',1),('01','222','22','22','22','22','01','2020-05-21','13:46:54','213','',1),('01','222','ww','ww','ww','ww','01','2020-05-21','14:04:10','23','',1),('01','444444444444','e','e','e','e','01','2020-05-21','22:22:53','324','',1),('01','33333','r','r','r','r','01','2020-05-22','20:28:56','6','',1),('01','444','444','44','44','444','01','2020-05-21','12:19:42','ss','',1),('01','2222222','sssss','ssssssss','ss','ssssss','01','2020-05-23','10:27:36','we34','No firmaron la remision',1);
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
INSERT INTO `institucion_referencia` VALUES ('01','Pablo Tobon Uribe','Pilarica','11111',1),('02','Hospital General de MedellÃ­n','San Diego','2345678',1),('03','Hospital San Vicente de Paul','Calle Barranquilla','234567',1);
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
INSERT INTO `registro_atencion_pacientes` VALUES ('2020-05-23','11:19:13','Vivo','10/15','presion Arterial','carrera 65 con calle 99','01','2','55555555','01','01','15327400','Nelson','Salazar',1),('2020-05-23','16:20:34','Vivo','10/15','Pulso','Calle San Juan con Carrera 80','01','2','55555555','01','02','1000332222','Pedro','Navajas',1),('2020-05-23','16:22:47','Vivo','15/15','presion Arterial','clle 54 # 56-63','01','5','2222','01','03','1000332222','Pedro','Navajas',1),('2020-05-23','16:27:26','Vivo','15/15','presion Arterial','diagonal 45 circular 1','01','1','55555555','02','04','1020303388','Thomas','Salazar',1),('2020-05-23','16:31:38','Vivo','15/15','presion Arterial','Transversal Inferior con Transversal Superior','01','3','22222','03','05','123456789','Maria','de los Guardias',1),('2020-05-23','16:34:35','Vivo','10/15','Pulso','Glorieta de la Minorista','01','8','15327400','01','06','123456789','Maria','de los Guardias',1),('2020-05-23','16:37:32','Vivo','15/15','presion Arterial','Carrera 46 con calle 50','01','3','111','02','07','12347689','Juanito','AlimaÃ±a',1),('2020-05-23','16:41:20','Vivo','15/15','presion Arterial','Calle 50 con Carrera 50','05','3','888888','02','08','12347689','Juanito','AlimaÃ±a',1),('2020-05-22','17:47:48','Vivo','15/15','presion Arterial','autopista norte','01','2','111','01','123','71660668','Santiago','Higuita',1),('2020-05-22','07:31:55','Vivo','10/15','presion Arterial','swe3','01','3','22222','01','1234','244224','fernando','munevar',1),('2020-05-21','11:49:55','Vivo','15/15','Pulso','65','01','5','2222','01','12345','15327400','Nelson','Salazar',1),('2020-05-21','13:46:39','Vivo','15/15','presion Arterial','22','01','2','2222','01','213','15327400','Nelson','Salazar',1),('2020-05-21','14:03:47','Vivo','15/15','presion Arterial','44','01','4','2222','01','23','15327400','Nelson','Salazar',1),('2020-05-21','22:22:26','Vivo','15/15','presion Arterial','rrrr','01','444','15327400','01','324','15327400','Nelson','Salazar',1),('2020-05-22','20:27:45','Vivo','15/15','presion Arterial','eee','01','1','55555555','01','6','71660668','Santiago','Higuita',1),('2020-05-21','12:19:28','Vivo','15/15','presion Arterial','ss','01','sss','2222','01','ss','15327400','Nelson','Salazar',1),('2020-05-23','10:27:00','Vivo','15/15','presion Arterial','carrera 65','01','2222','15327400','01','we34','1111','ss','ss',1);
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
INSERT INTO `tipo_documento` VALUES ('01','Cedula de Ciudadania',1),('02','Tarjeta de Identidad',1),('03','Pasaporte',1),('04','NIT',1);
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
INSERT INTO `usuario` VALUES ('1111','Pedro','','Paramo','','pedrito',_binary 'Thomas#2009','02',1),('15327400','Nelson','Giovanni','Salazar','Roldan','nesalaz@msn.com',_binary 'Thomas#2009','01',1);
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

-- Dump completed on 2020-05-23 17:01:15
