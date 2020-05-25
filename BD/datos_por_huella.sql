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
INSERT INTO `datos_persona` VALUES ('1000332222','Pedro','','Navajas','','1954-09-25','Nueva York','Masculino','Las Prostitutas	','Robitis','Muerto','\0ø[È*ãs\\ÀA7	«q°ºUš!Î?¾H½®æ±~VéÛ1î‘ıˆQµÃ`$Ec%¶–Ì“áŒ÷-Ê;F¹ÛnÂO§8•…¬ğqaµV0Gô–\n–<iÔ<Š(^a8ëŞ]ö2¿„*Ù4€A¹Æ<§Äƒ˜«ïôd‚iÏ1_cók* ’l=ÓöRìrÉ#‰¨wº‹v›5…1ƒ7\0şC_U_ôÒo9´ûÜ·™uÀĞdú?3\Z&Oÿ2¢g+2Şq`dm&ÔŞN7H\rE;ü3«¥†ÇÎ@ZƒÄúW`Çş]<ì€øù¿æ\"=?râ­Õ+œLkÇw\rn3o)YÜfî/Æ\"XüX‡%f?cÌ—fë³Q©íLëU:òğF?\0KÅ¤»#;	¹Opj5Ä‹“h¼¨\'ê±ófŞIÕ‚\\ÙÌ[9Y Ñ9ú÷Co\0ø~È*ãs\\ÀA7	«q°_UšSn_fˆsªô;çææin›J&ß‚:ÍQˆs$×uepÍ8^ÅÎ›â¡Ò¦€a¥ÛJ•şü×øĞŸñÉ)•G1”fG)6áR-•6º_}Ù±Ã:_Êön Ê–g³¨]];ínWn–ÌÄGÕUî-3f\n,Z ü²7ë/\rv®´Z5\'}½YEj\"xÈiÅĞşmàc(ÎÚã” ’èÓÒ]İâ€çû—äÿâ©şŸô÷¾á!ıä=—ïODKáJ\Z¡ì¸l­oÙ¥×u—>\0Š\Zø\n«‚¦×âèì}­|š0‡ _tùâf\nã¨Và®’0qé8ÇwæP»\Z¿²kI—±:	Qdh®ôº¿Ò„kkÕÚLI‹5MØß6áÎ\0¬—ı—1öëšG}°^u„M%*ğ9¬—„….²9ôFã™şuDD·Kˆÿ6—AÔ´N@Âgô?xêrF¸£Áeo\0ø_È*ãs\\ÀA7	«qğ¿Ušà$§ÀI\0\"vÊFƒf£rà(†ädHÅ12¬|´¬ZSKUÓÍâsPtá››\\ö¡8ß™Zè(í8†¯2	ÒïôI[ú¶ã>*>9´/–§üúvaÀÏ˜›a#ú!	-#ÄÊ˜ä*Üı—øŒM8ªí›½”4‰o…+ªê¨$Ä\"‡6h/è©0ÕaÊ	ÔÓşSìd”ÎØÈ¾ãÙAh†ÜåñÁæx¨¥Gçƒï³RyvØ.¹ªNÉvÌü´aÌq/HËC±è`şüÌï§„¦ùNT<yob0‹‘º[\\J\rìJ¤Tã>Í^Eè/r¥Ùß,XÏü¸ œ-7Ïx ®(Äì»ÁÈûëjûN—®ãøXq\'šÅwˆÄõ\0¸“¶ğ²j4\"iÑÓŠUd©­”ÿ+~ŒpÀš®o5x:ÿD¶¡o\0è~È*ãs\\ÀA7	«qpUUšÎÑC ±§VİbÒ]\nèÉï…Øt”ìÕâD\"İWÊõ»?bq÷.º’ú•iÄˆ†„ÓÏØşErØ+ç¸c&>:´towêó™Oè‘\Zg-‹Ä£İ¤˜Kq2çìq¸[0…ÇrÄù\rÿnlR$RfùİIŠ@ˆ{NûqN$W±½¤g!¼3Û^¿§ê¤>¦1”¶~iİÉ®•&à³Ÿ`7Pğ·#ÚP`6ÕößÏµŞì;S/x_[ål\\=ıpÀ|£¤·ç[GóR t\"¸S¨E=J5;¥Û›\"ä5ã)§bU8ŒKøƒ÷Ñ73\'˜/“?¬mĞyfX6Îó£jØŠ€0Û¶sÊ.p]xd&¨¿ZĞ§*½€£±ºyI¡§òB¾·¦íñş#´òÜUY·Šú!sŠ[ëÎõ»Û0‘ö´œ¢Sos’ÌúU Ñ,uÎ€Å—R%ÁÃ²¢Ëßo\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0','1632','01','01',1),('1020303388','Thomas','','Salazar','Osorio','2009-05-22','Bello','Masculino','no','no','no','\0øÈ*ãs\\ÀA7	«q°DUšdošquÏ&­¶Ît;¾°æY™¬å€ø!ĞÊøÜTC3˜\0MP*A×Ã­ş<–æVQKìçk“ğèìNï_ÆM+F¾KD_Şú+ï\nâoæ®lBh‡ĞĞ.\Z BÑ;î[8\r˜‡°å|ÚúZû\"ÒJÊæU,/Õv\ncßp£EëRê<ÁD-®~´bÂbfœ9\ZREeLæ»+ï/–KøV)\Zcí‘ÃæÕ.X»ë÷K´QùæØl\'³ƒ@]ÂVPÛëÅtş ÈâZ_åjå–<rÕ¹bâæ_ßk2¥šøîÑÀR×\'ğ•×³q=óXS=ÃIC¨Ÿ´dÔ ÃrF‘&~Ÿ¿¹PÏùÒÀˆ§İócò´K^…õ®Ÿ=œ‰ü¡\ZCK|İæLVhç[>@úu`=w(\Z¦?D™>#\n~…Ö•é?ğVM(>=ÂX:ÇeZøÇşòÅm)¢C‰¢f°I^?o\0økÈ*ãs\\ÀA7	«qp½UšT¦…À V %æå°ûÛ”÷ZÔ„Û&KN|:q¯f¶~FZ¦°µu†˜èoŒ6KÙU-~“qÚ Å”k3ˆ·~TU>òã#‰€È¥Ÿq~¬­‰ğ­– «™R¸“Ø°·ö7Ô¶Í/ÁCEÃÂ)ÁœÕUÉ}½÷HŠ×‘QÅß‡ŞW„ßØÙÂW\\ßÇãà>)2\"ÛÔa2è¥ÏÏ@›YÈõ²şŒKÄ\0‡6t‰\\h/³±úuÖĞ\0ÃğàCXZáÂJ.¢¯‘`Ìˆ%\r¼^e2\0U•½’b[\\íR\"éÜ5X¿Dz£·\n<?åt\"Ôç¯‡Û<ĞiñäGRµ_Œ}çDåŞç=ãQŒDuô+tØV #o ‚?Ày\"ÆÚ ÛWäfG´ØOPdîğX:Ïñ•ïf—şT‰·ûO¦ÏÅ½â“¾‘F\0Ò²^/—‘æğïôD-ßõ\0ò,Øo\0øÈ*ãs\\ÀA7	«qğ@Ušch{â|©B‹–\\„D‹’ßté\ZœÇ>è•D-dC\\¿Ñ\'³º^3 7£é5Kâ	UMu©¼ø<°Äv4åù§ÃUôÃ_S[÷m€œèWÎ\0n2áŒùâı#ÆÉ:Eİÿ7?@½š@V,!%Ê¶3¿ó¼Ä`ë1pü©Úa-ÙF&HÆôÏNí§e(PwûËm¸1,ğŒ¾÷KÓÛ†Š“»°,ëLşä@Î-6€…ijd%ï÷µz±ƒ(+[X\0£îó©à²Ÿ1àà¼Â>FcSSÇÙ¼Û”Ÿ—Ùƒ»ÕõëcbÆhËtª˜”eH.Ó(¯Ûµh¡õşQ¨4‚«‚³™é;Š¯Æ\'åG¤ôæ‡ãfÄıdÙÂ#_ï’ŠGÙQ)Ú\'+â<è\nCpó<ï[.@,-á6# lµ8SßBj˜ÀËÌÔz¸àcsámygß78I¹(öU@Òàe‘JDZV‹µD!ãŠo\0èoÈ*ãs\\ÀA7	«qğ¿Ušì,¶‚k)øL ¤\"æõ9Í]—äöh„€ÙB1æÍ–)‰Ä_]ÈmŸUqvª:B/^ApÆ<’™®â«WÁi[ÏdŞË‰”;f™tş^=¹2®ó›nºú5p;¼_î¼È!æ^\'xâ›‘±=ê/Yû|åoºèø„‡¨x/‘ga3nU±|EÚëÊqYˆùÅåè}‹]pÇcòó‰a\":=LáK ¹Kúó_„Ë §=ç&ºÅö=6Y~‡hBÿœÉÛş¥@=ˆ·.iY´üœPÃl©Í°Xçæå?ç©O‰gø±€Û‘Ù`î¬ñy­K=[mp\r9špÉØ>t`U”@‘Ü±‹\"Î/|×ßçA…µzaß•ÖÎÍêø273ù\ryf!McáñDı>B™¸ºó-;m·•¿…ĞÖ	åvÅ€\n9*œ‹#$®ÈÂo\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0','1632','02','01',1),('1111','ss','ss','ss','ss','2020-05-23','sss','Masculino','s','s','s','\0ø~È*ãs\\ÀA7	«qp¤Ušu|åëÔå¨‘~ş—¨;²â¯AœîÓGz{Ü8ó`\Z¤ZXL/ÍÈ/	õÄ6oChïV–¶mğ•{ƒªà*~Dš]³K\Z¶ ­¢Çÿô©8ª6G_R?	¡Ü’¨³ßuÿx‡RŠTâˆ©Ó7¥|†Üî„Ñô+¯ÆĞaôæ¸\rµE„ìsŒ˜™/~ŒOP»ÉJÖFÕIuf>éD®ng¶Ğ°®A†k„Éomqã0“[F7şÀ~ËÀaMAÙaé	Õ?F|²x®ú_\\(ÜOSéŸ0hôxôtæ”	·Tóy«2]ÈÈõú{~c4>Ve¦‹\'ø„æjÁˆ`»A¾}y@	Y&nrŸEŸÁ$õŒ{âÍ­?Ã3}%×df¹@ˆŞ¾-Ë•˜H”p3xœ+¼¶Äcğµ‘.1.súµ¦³Ä@Ç•_TÄ)z~|\rğ®aBãâÕ™×ÿ˜¯ë±o\0ø€È*ãs\\ÀA7	«qğ©Uš\"e#L4\\!ùwñLò®j@{)E9­û•p?P;§Ï’ªÇ HØ÷>’î„^$}Ûº[bQ¸Š:XœsE\rK7ÁoH[çƒ\Z¬6n’©ƒŒ+ü3a¥ÛbÒÎıå9Õmzp\"5†íåhä$ÚWø^b„¼¨z\\¡îC5Ş÷=²Ú}7œ$¥4]Ã®DAeï.´è€ğ#U-ÅFÍ10ŸÜ¸qÄ&QüÒ\r‚İŠ¸S\'æ(%7˜‡Iò›\n	áO“lƒª`—15xï`{Gs\nÕ„Â€šùÏ µ¯“&·Ó}]!\'e¯èzdh›Õ(¹<÷	aº€×¤´”i–c>T”çx®\'hÚmÑiĞÜ)\n.áÂÄ‹!£Æ\Z´FƒşõÄ¯™ÓµùŒ·.RB\'/Šåz…ù»ÿ5(Ò/«üşUGË˜®;™yGUˆê¶IN®©NÕêÅ1ÈM9\\™®ó¸?!8óû”õo\0øiÈ*ãs\\ÀA7	«qğ Uš[°Á…êUÆík7šyæ@Q¹µuŸºÂ<ÖCF,N¾*^¶‡Y<Ù¦àë -¼Ns$Üßõæ¶‹)‚ÒiTHç@õ!Ik¦J©ÂàQ«ÛAÈ}\0øí°áu& ƒÈy‹.ÿDÚ„|±§¯\r§°nÏ*¤²íÈ´éHi¥Cê¤Ft¦ìm:¨Øôó÷”¬ñìIZÈpşmï¾ªoa8®R3GkPds³ZÊh§-İ\ršô5ı±Ü1ˆQEÔäQ ?z8zí\\½ÔÕ\'5à€óïì`v]·mü÷ã|v¼$É†XÏ<¨ìNÆg…4ƒ$±Â`Ò$8\rÁñVFŒ™Jáª¡\0œÜîdÃ–ëÏ\Z¿aµ:3:f}¢)¥Ë#çã\0K,Ş``ş[) “$ \Z2áï G°ft”O*ÏúÍØ¬Ö¼…/¾Æ	ğ/eP½å\"o\0èsÈ*ãs\\ÀA7	«q°¹UškÍÆ*á‡æˆˆòŠÌOÃ\0˜EöÍ«Àİ\r]s[DøHÍŞmŠå¥Ò>Ò´†’ë÷âÃSóö¥W)ñlã-ö—_üÆú¨Æ3\\ˆ@œP·5K5WŒB¥ö°r§	éôi‘–n‰ó¶œÕƒ‡?Wİ3Tu)dáÜŸØ3ºt•ÌÃı¨¯Eù‘®š¿ˆrBÿI¤ŒË=z2›@\"$‰mÑU\n$ÌlÄírŠ1»/-î;CcT‹ºÿu™‡’è„O‡¬5ºÓ›Ù*Åí´G8«Ñ2«’´›s&£®Å=)öEkª:…”şy8_]Læax?/ÔÁ¿ş8Îñ;ƒ²Ös½ğr|’Áb°!	É¯q\ZD@E)q{‘ÁË-ŒM`¢#ìAqûû·¼ÀLJxt½­W$Ô\n`[H¾‹ƒ#ÃJ6Zo\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0','1632','01','01',1),('123456789','Maria','Angelica','de los Guardias','Hernandez','1975-12-31','Guadalajara','Femenino','no','no','no','\0øÈ*ãs\\ÀA7	«qğ½Uš©\"«}¿åê»ùh»è»EÕúÂìIª›õYl1 üq†,-¯¥«0ONBŒŠßË¥)XİêN„ôĞ6\0Ú©Ó0Šltl\"l•ÿJe|4¾YÛÕ¥ˆÉ—²ê­¾d)ÄP`…‡ß½¯°¦»ÿ!\ZŸe±ç#>mg\npl°l¡ãh^!´›*…?s@Ü,%øëD®	3Ìå¿Y_w¸ÚNjT¸Íp”v:€ûyÏq\r˜ì“§AÂ¡³%Şü6\"‡¦Ù\0¾¡:ÅÎDïhºü8–ØTÇÕ•\\Rµ€ÂGÎM3J‡@ <Ó[\"\r.ÃÉL.é#è’¿¶`&İào¿%Œ\'÷(2ó>²jß·¬%½‡®¬©][!PE)’/ŞªoM÷/¿±¿°ñ¦ş4	ql”FFİÊ„~¢r¾jô•\n3R--—°ş]Ç„½¸ç+ßwªJHH˜¿ør78n`‹cÈ>Š@Ä\r¹µEü“ñ1o\0øÈ*ãs\\ÀA7	«q0JUš÷ a\Z`ÌLÖÌôµÄ(Ïl‘jõwöÉ~«¸u›}–²cÑ¼K%xBNr;Õ@.*x_¨ºu†óix¯”@¿×mT\'â@\\Şvı3cÜÙ[yœ2DÖn_`§\Z·ïBİô>*W™ó+ŞjCÜ±q å»„A	*V§òÓ\'îÊF÷a´†ÿ–·‹İ\0NÖ·k¥;R@)MA†x†×‹Ş†”XÔë‚Èç®&ÿö52Uæ0;—¸ïÓŞ{?B½Ê–l,cë|8C¹ÒÊœ)ôD¹Ä\r¶0¦ >%Wa5™·÷ÒI	›]ôë11˜kÉ»o¢\n$§À†®]	_]ütV5\\ÆV6Ñ>w€~­~V©ÌúoPƒ ıô1È„\r ¬rÖFÅŠò·ê£\0C–!‰]3Új¸$4%rÿ«ã\'\0*çËC^9ÕÀÍ›ó7R)4K¢ÄâÑVö«¯Å)š\\Ïu†o\0øÈ*ãs\\ÀA7	«qğIUš¬fZ‡ĞõÂZœ,%âÓú íò7v# ôPÄ˜ÒB¸ÍpiÃ8˜ÿOEKçÆ9q-cÑÉ¡Ñ¦Cc=jè°¹ÊŸ£ç©Ï\'ç}+„c e¿,Ñ3´$HûUÇ²+Û´ƒrFoMäÿWÎ“Èƒ‹Üq•ÔÆàÎ¿òd‰ïÃú apiAynâß:”×Ü °«Gfâ8g OŒèzE!hï:ÜHdx¼î(W±V«!ÓÖØ¦½†úŞ,ôP¶R\ZTqiv››À¹˜†ígs‰êh“Zdy¢®ó«¦İİÜtHÍ5Ê÷WãÆ\'˜Ä›ÓM}é°Ë™œÍ;× éöÜ`Zï¦ÿî\rºŒŞ¯<4?‡ı–¶–´ìx™œÿÿ—Ç\n;ŒT\ZÊ¯$´^‚d€¡^\ZÛÆ”Ñ†’x6b»üÀ]\nœÜÎcÜ\0JìdZ ±´UÏh\"8•Ú&˜-¨ïn¡\ZBë¿¿o\0è~È*ãs\\ÀA7	«q°NUšËÁÏÀÁ…šĞN&À¼¥Òº?:(li5×Xš\\‡e‚öİÕÑÊ\\sG±µm>Îõ´<IkÉkO u7¯âx Ÿ+H\0Zš\'-`ı¾À;+7muñç—Ëë§ãÖÒ1Ÿv±ù™9\0¦¥Ä_O2+e\\¹®ÙÜøÏ NEï6YÒnÌÍÇ&7V…ùrzdO–*N‚F+.½›•›8ˆc“øz#XĞ™è]ãŠû$`©jÌ0°ÏI¾úLÉş+Ò8$Â¹¯%4Tf\n³\\½¡?íNkàæí’Šˆò#•(&³§ôÙŠÚş¢š@!Gÿ#šˆ‡©¿\Z¾£Äç‡—›\"¢e\rl\'•\rø&³\nrRr®tÆ’O¯§ã˜2ƒòÖ^”W|Ş×§EWµâ†?xê×,IôOGœvæÃ—7¸ko@Áº€ıE@ĞJŞ¥r#™™o\"GIôV\"ïRzoÜì¤o\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0','1632','01','03',1),('12347689','Juanito','','AlimaÃ±a','','1980-01-01','puerto rico','Masculino','no','no','no','\0øÈ*ãs\\ÀA7	«q°MUšbYbàÜ­.Õ·FãÉ6ùºsÈú¡páO5¡S½ÿÁ]ğwå\"¹¿ÆQX_õdT÷B\nÌ®(æ®.<\"³Sjğ\\*kØ÷emÅƒ9^WÉ‘u|Å\n¯{‹w€ËÖË¾ˆúAã.¼c½´ıP\n©.Î¬\ránÀêP×Dúğ$=±¹Ôa$ÆZ‰ÊGÉÇ!£3¦,ù„¢jæGÔ˜!He§;Ñ.€d6Xw¶¶eÊœ\0­âë9•°3\"|¼%1Tmšm—èÊ,cFşÌ	åĞÂÜM\ZãƒOĞˆœ7äİŠã¬¥¦ g¤]U†òdZ=:Ü¶_ÅDºFb›=ÍÈ·İÀ•Ù	N-HõVÊº²(/÷@öl7yë½\r£¼°×õu¶HÃG9²‚Ìv: ¯É”ğ ]áëÿ6Ê=qĞNÙ’ÒJ¥(ÑÈ“Íuı¨/$¹@‘ğ«–dìùåb/=Xºw5š/W\n(ÇŸtƒğ)4Ïo\0øÈ*ãs\\ÀA7	«q°¼UšûöÄ0Cdl^€b±ˆ1Ÿé÷w‹¦N\ZŸ\'aóØ{(ÔqX 0{ãe—ÓÓ+¹ö«j)sØéÅ=¶ç¥Û	ØuZnyAf€„\\¨/ã×fX¾É2ÉQ3âk—?åĞCìËCÒPzJEz8gŠ|¬½V¶ÛwÚÙÍ;RûÛÅdóTİğÑ¬¶Ä2ş¼¨Ÿ¼*ñà;oÂ9™UXëÚ(à«)á Å/4Ëlbñf´õ*Q¢F±±|´ÕÙy5ª›dáx¸€rÀ«ı|úÜĞÕ)£	¯e\\¢Ì¶Aó›ÅÉâ(Ã”â§ßÏe£Ÿ—’%ş}ütt¯˜$‡+mØğğÈz:\rlY¾¾qJÏûÖ\"ÿxH/o%¦€sQ5PÜw6B1s.²j78A,\'Âmíæ\"xYa9j”…[³*2‹sªès	–bVEÒ2ÍÃ¿™Âıi4Ñ}9Œ‰ú°ÁõÌ›HXWP*É/š‡ùI¦ğ‹ş5o\0ø~È*ãs\\ÀA7	«q°¿Uš½ğv¸ËåxÔ>ñMåNş1ayW¸Háıñ=İb.Ş–(£|Ë\r˜.M¶P¾®-©%Ø™NÑql·!¬$\\`¬û_ê¼½ÿıP†$(+iL¸Ã:¢­èo[…g¿\0·”d»ÿ[ç¥õÙëäîƒÇ,=@½/ô~fä¾¸Ì\'C7_´ë¶xºFÍéùîTö<Åíë€ìô*Sîößn¸5¬øğäËô…B\n¦x)¿%}Q0	ÓÀQë©ĞÎ5zÕ{J:&Ò¾İ@÷Íoá	aG\r¢sÛîäíÊ…»\\ùJ5ø˜·ÙrS‘ÚÑâGdn·fşøa°‡E#{©fÀ{şEdX×éªF¿y´­ãË2û™ø±†¦4b—uºğŞØÃ›W]ìÙõùáZÔsÚn\nğÀëñÎØïn<ÿ‹ß¢®½:PşÛMkJÃ‹\Zk¥ˆ‹úØ&Ítº·†…Ë™É¿²21fêo\0è~È*ãs\\ÀA7	«q0GUšÿVªàd$ŒCú\\wL„Go=8ßØ3§á¦È×UpÌ¬FÛÛI¤,Pôja€,úZ4ræ=È|\'1ë¹Í–yål—ì¹ŒK\\/\0´ê©ZZ€HÃR÷Œ©¨Q¸—Ş©uÈg#Ú«¾ŞÆ¼ ()‹_}ğÂ«´_©c£\"/¥I‡êŸ° Vpù’OÜ®ÓÙ»çÙ´$	 @?S÷-‹ĞS^— è`#J`MæU–	ŠŸ•xMSşØ®Ld¼·O€é\rPšaoEú`òİ™)D´Ë³„Rï^é®’sÒÏÚ,Ñâ@kÕF9N½vßgT‚íœšõë¸„\":ßµ!4`*”“sÆCƒÔ*[’2)CÚ|±¼óîÎ*½]•çğ¿*IküçÛÂıV•ºTÓ©§–xÑ-~À¿W‰ò¯u:Ù=â`o>35ú¹–Æ_¡Å‘çÀxµ€y9D{í‡2\\ o\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0','1632','03','02',1),('15327400','Nelson','Giovanni','Salazar','Roldan','1973-08-26','Carrera 78 A # 67-54','Masculino','no','no','no','\0øZÈ*ãs\\ÀA7	«qp¨Uš­wxïòÈ™gü/Ïâ5‘z‘üeèá=¿J>/ĞfVétŞbD_Ñ;lÏ2Ö›ûs¾&…VRø–Ê˜²|šÉ)¡.	PAôúèùü>µx®×wø&4^ÌôÄ¶7˜ïÂ JtWZ^Ût¦î\rEŞNÅ0zñù\Z@3Üº4–4®	s4F)8È;SÎ+lˆ¦ü¤_—çU×“sN*^õ ş Ï-ğ… D®fÂ(¨¨PZ†ƒ©®®ñ;	İ<°íf×sá\r7	.77/Ï„1,rÛPùò6+ŸşÒ$Ü2ı!•@’º~—EÁÑ±L	)\na¾ÎªÌu^ê/¶¾ÜFÆÌ„¨ w›‘ßI°8un0¨¼#Ó;ìôÜ#göbCèø5DPCQ@Qs„«šaVgìÄåĞÏ³QÀ¶Óú¶–`¤%ZŸ¦0?¿.ÄIo\0ødÈ*ãs\\ÀA7	«qğªUšÑóÑD:ÕM4Èeßše–\rûh:»[³ãÌ²|«Ø~HÁ·,ü¨Tö¯IÑ¯¾*[+pí/vQıŠRú‹ı3‚VjV.ÿÂsjZ€†jÚ-·æåöÒqj|y8<7=œóIË|^Şà„üyLˆõ[¹‘íeT\"0%Œ1ÌáWL*<âşªåQ\Z\nqê9^bk¹şvUJS÷(Y¶GX@Ÿ-üu‘C¿Eà!\"\'?h+çIèm‰,€ğ¹\\EÑêza%¾·«l|Ä½±@â+ùGÚ_c²~@Ø˜gO*‡ø²“«P‚x¨ÓÌùÊ[\ZĞ¸Æ×S§å†-Á~ÂŒ¾3}l:QùfÅ6–óN=œ\'}v%3\\áY†ç²ŸObĞ0PÇ¦H’6bÚá×¿³¹5¾)i-æå >^aœöúŒµƒà“’rñÒ¶+­€ôvÿu\'9™o\0ø\\È*ãs\\ÀA7	«q0¤Ušk8[*Z1UŸ°{Ç˜õÅsä\'¨\'åö#Kèj´x¶ïbRl³z’‰ÓG¬`Úå•hĞHÄc±R(9\n®Á«şÖíâÿ%§Ö–íıdÑ?t­ÛC¡M-·Üµªæ\0·@¢<QÇËGö†Òl<g#¿_Ë[±h(ÈêÑã–{^-aÏ¹Aõsc\'i1>qñé)Ä¯%JYrÄŒÔÁ—ÕU”–­õlf Ûøha‚Ï•˜—Gö‡@(Ü	ŒøÖÔô¬Fº6·(*˜`w¡xi)ÄèXé13ßÍ/Îm½Ü©_	4äÌF2s‰hT5B\r§ûİÅÃÎEŠä*¿•¶u²ß¹u_n9ÎÓ*vßLM¢\\Yo¸j6ÏÜ[.ZàÉ¿J‰l>Àã\nioQÖF-ûnSî}ïD‘0é²½xæ-ö§#û=E^†•)o\0èÈ*ãs\\ÀA7	«qp„UšÈ¤^%Bµ$äïûÇÇCäÖŸYsÂá^!âæŒÊÛ…\nÕ(Yëáòæ‚	öÄ¹m~×±™ÌnÓ3°·M>Ú\nec*<ÙÖT:³»%ÉIÄ5’CU+O—ğx6au‹(Îä‹1Ö\ZKF„ÕBey›¦	sÑëOÊ\'+¶ç—;Vüî%pGB:C½¾A0^É¡F Ï~w+ˆ·øÆ3¸ñ‚ÕøÙ«Ö0Vÿ/ †–ß¼¥‹4Àc8§VşËÇX¸ñß¦_>1îè{!ÙYš¨cS¹”\'sİÜÊ>öÚiávm‘³–f|É¯*éªë;^ı«´{Éù°|’ã¤Ù4;‘H³ãàŞ%m4\Zo\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0','1632','01','01',1),('222222222','Paquita','Rosario','La del Barrio','Mendez','1990-01-01','Armenia Mantequilla','Femenino','no','no','no','\0øÈ*ãs\\ÀA7	«qğ½Uš©\"«}¿åê»ùh»è»EÕúÂìIª›õYl1 üq†,-¯¥«0ONBŒŠßË¥)XİêN„ôĞ6\0Ú©Ó0Šltl\"l•ÿJe|4¾YÛÕ¥ˆÉ—²ê­¾d)ÄP`…‡ß½¯°¦»ÿ!\ZŸe±ç#>mg\npl°l¡ãh^!´›*…?s@Ü,%øëD®	3Ìå¿Y_w¸ÚNjT¸Íp”v:€ûyÏq\r˜ì“§AÂ¡³%Şü6\"‡¦Ù\0¾¡:ÅÎDïhºü8–ØTÇÕ•\\Rµ€ÂGÎM3J‡@ <Ó[\"\r.ÃÉL.é#è’¿¶`&İào¿%Œ\'÷(2ó>²jß·¬%½‡®¬©][!PE)’/ŞªoM÷/¿±¿°ñ¦ş4	ql”FFİÊ„~¢r¾jô•\n3R--—°ş]Ç„½¸ç+ßwªJHH˜¿ør78n`‹cÈ>Š@Ä\r¹µEü“ñ1o\0øÈ*ãs\\ÀA7	«q0JUš÷ a\Z`ÌLÖÌôµÄ(Ïl‘jõwöÉ~«¸u›}–²cÑ¼K%xBNr;Õ@.*x_¨ºu†óix¯”@¿×mT\'â@\\Şvı3cÜÙ[yœ2DÖn_`§\Z·ïBİô>*W™ó+ŞjCÜ±q å»„A	*V§òÓ\'îÊF÷a´†ÿ–·‹İ\0NÖ·k¥;R@)MA†x†×‹Ş†”XÔë‚Èç®&ÿö52Uæ0;—¸ïÓŞ{?B½Ê–l,cë|8C¹ÒÊœ)ôD¹Ä\r¶0¦ >%Wa5™·÷ÒI	›]ôë11˜kÉ»o¢\n$§À†®]	_]ütV5\\ÆV6Ñ>w€~­~V©ÌúoPƒ ıô1È„\r ¬rÖFÅŠò·ê£\0C–!‰]3Új¸$4%rÿ«ã\'\0*çËC^9ÕÀÍ›ó7R)4K¢ÄâÑVö«¯Å)š\\Ïu†o\0øÈ*ãs\\ÀA7	«qğIUš¬fZ‡ĞõÂZœ,%âÓú íò7v# ôPÄ˜ÒB¸ÍpiÃ8˜ÿOEKçÆ9q-cÑÉ¡Ñ¦Cc=jè°¹ÊŸ£ç©Ï\'ç}+„c e¿,Ñ3´$HûUÇ²+Û´ƒrFoMäÿWÎ“Èƒ‹Üq•ÔÆàÎ¿òd‰ïÃú apiAynâß:”×Ü °«Gfâ8g OŒèzE!hï:ÜHdx¼î(W±V«!ÓÖØ¦½†úŞ,ôP¶R\ZTqiv››À¹˜†ígs‰êh“Zdy¢®ó«¦İİÜtHÍ5Ê÷WãÆ\'˜Ä›ÓM}é°Ë™œÍ;× éöÜ`Zï¦ÿî\rºŒŞ¯<4?‡ı–¶–´ìx™œÿÿ—Ç\n;ŒT\ZÊ¯$´^‚d€¡^\ZÛÆ”Ñ†’x6b»üÀ]\nœÜÎcÜ\0JìdZ ±´UÏh\"8•Ú&˜-¨ïn¡\ZBë¿¿o\0è~È*ãs\\ÀA7	«q°NUšËÁÏÀÁ…šĞN&À¼¥Òº?:(li5×Xš\\‡e‚öİÕÑÊ\\sG±µm>Îõ´<IkÉkO u7¯âx Ÿ+H\0Zš\'-`ı¾À;+7muñç—Ëë§ãÖÒ1Ÿv±ù™9\0¦¥Ä_O2+e\\¹®ÙÜøÏ NEï6YÒnÌÍÇ&7V…ùrzdO–*N‚F+.½›•›8ˆc“øz#XĞ™è]ãŠû$`©jÌ0°ÏI¾úLÉş+Ò8$Â¹¯%4Tf\n³\\½¡?íNkàæí’Šˆò#•(&³§ôÙŠÚş¢š@!Gÿ#šˆ‡©¿\Z¾£Äç‡—›\"¢e\rl\'•\rø&³\nrRr®tÆ’O¯§ã˜2ƒòÖ^”W|Ş×§EWµâ†?xê×,IôOGœvæÃ—7¸ko@Áº€ıE@ĞJŞ¥r#™™o\"GIôV\"ïRzoÜì¤o\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0','1632','03','01',1),('244224','fernando','','munevar','','2020-05-14','castilla','Masculino','a','aa','aaa','\0ø€È*ãs\\ÀA7	«q0MUšäëxIÎfÇ ì¶Ì%zßÈ†l€E„Ûä;òo±3…ëÎÏ¦ªqãİ5&¬{a”ÎÁ›Şù7?ÿ‚\nB”(¸•\0¯b¦;[³jw¹²%ºÙ.J+@JÍY¿?Ë	]éGc9ÿ±â­Ã&³#<\r¯jÏã«…A{õ&³L €Ä­°´µ®¿4„ùKô÷Òı1{ğq:¢ªg	 ãfé’ß²AljNè¼ÿÑ%\rvá¸I”¢½WÕë…l5—˜¢I”s)1ğP³QZ±Ìäë]ójøFõà@KUŸò¬K‰×uÊO\'J+¦Œ£àAfÎAƒa’\\Œ(½ğõYEíD,›	U\rÜé—\"6\n†Ÿrˆ×Ú‡9fè8ÛéÇ…¡Ş¸W6ÚšßÎäğØcNĞV·\0víùL-à. @Ó„¤.Ü%„+ke@Jµk‹\\mö`qœ(¥¦]âÈ ğ}rGß—co\0øÈ*ãs\\ÀA7	«q°¿Uš¾ä=²Ûë¹–g]Õšã¢K;âÂ²fè§z,_\n‹ñN;Eµ³WpşÉ|XC½øƒo0ßˆÔ	öa6Qò:^İ)å2úmr]Á_¬D\ZpfwÚéãB*­ŠÇhŞßyY¼ĞÉ \Zglh³…İ©DÏüXàûUEÌPôls$L‘‘.ô·6ıæCİ×œ9”%*¥7T— ùÿşåWõ{ÑìÑEv\0Ñ[êÔUBRêE-H*“”Â^¥3E[Š4ªÌ?áï·\r}Á˜Ì:d cmù Áßnñå6\'ÑÆ±8«Xš¼6\\A¹ÎÆã14Ài%Ævh~kÿm.êUf%ZzlÔøçÚİZ·«Ş<—ÅòOı¸\rwdÿƒSùÚ»ÉwcäÉì†@u$Ò=>¥×ØM}ïòø-`—öÇ³Ğ:ÚUWc^.L»|w‹è{Åğ¡$ÔF‡ÇRßK%±ÀÎ2Æ\0ó‹t—0å¢;ˆo\0øÈ*ãs\\ÀA&-ü3NèBF&#Oëà*2m²I&\"FS§÷ÿQ½k‚³›?¢ğÉmÂ€`ş ióñös\0ÌRt·Jüí;Y‡åpÌuŠçƒK†÷¯±×»\\·X\\³„Ü½³©‹—|¼ÑÖ›‰8eRó@ÃÇ˜)”@\rˆÅ—·ª//Pô˜ºù®×-/VâŞê—&t‡±udË ëdg¼õ\0ûU±ÕÍj¾Šõ·“C^ûTú94|\"ro±tAŞƒ‡+ÅÜSµ“gÊÏ‰—3°šYf‘›S\Zõèœ¯l„2$n´¾±é;‰³RNy3Ld*¼ºãÂï˜•†Ê\0Ïõ©¬ÿ´Õ`RÅ&*”.}m‚}	şâg´KKĞFRAl¾ı5ö=´û<:kx{E•j]¨XF$Ÿ?ú£u9œóXë‚#<UØ&,¯<¿íZÒŠšî\0Ä¹I>ôüÁúùfoÑVe¼×EÁn”Eño\0è~È*ãs\\ÀA7	«qğNUš›ôÙª9ÕÑ¢³šÆô™‰B‰NĞÓ\rÄ¥³<k™äÅ8J,?Éy<œBÓ`¼Q³\rqY5e‡v‘¡­ÖümÇ§l=aÿ³Q@{æ“\'õ„¤b³e™­rE´‹\ZJÊİRHO=ø}½iÅâ^puNYŠ¤U“}­º([C4òæ1Ä\\aÚd(zæWjpûŞÆÏ7æˆİÙ €—\'ûwbo%©Œ”Pâù~æJe°ÛT‚­âj_ìf°ô#è€ä-?	J êåÍãƒá[%¡—§TwÁW­}¬gåİm¶›ş+ºü/¦rgzS;9Ä‹ªxÿ_ÏråWzÌÿúqŒ¶9îšßPøœ×ƒ\n3\rX9‚jÉNç·´½®vãáy‡¤_x¨¥ÎˆM¶^$le>¹ĞŠ™*³ÔúÂUQIy’1JDKêz–8IûoÓ\n¢uDíÂÒWùxö˜S@¶sÑ6o\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0','1632','01','01',1),('4444444','Stanislavo','Armando','Kiatosqui','Correa','1954-01-01','Ucrania','Masculino','no	','no','no','\0ø]È*ãs\\ÀA7	«qp¿Ušrbø{Õ\"‘Ñ­¼áÎÄ[”©õ¬:ü…ÿ­û^¦D•0¸íJ¸(•$‰¸­,5æğJ,u~(q‡ŞslŠ¬}X\r/æu—}\'ãëœ¼ÙğÍ*€Ò=Œ:JÍ,±q˜âGÈ¯ê>!jğ¼Ï	Ûœ*˜û¼{¹],t°{Mˆñ}Ó\0SÍ³uØ¡ù!ïãŒb„ÉˆiD}n‘ïQÄ»ø	Ø1Zz½Y}>¹²#L,A_•hDØ„±\ZI³O98IÖ7i@%¥GfãÆ›Ãôªg;À¿)˜•\ZBÃÊğf $z²‘‹7o+µ_à£_À<»rm<¯ß±§0ş`Ş?z\Z¹kk/ññto\'±ëöv|ïó3NŒGövòC|Ôf`ÏLü{+us»M4îMi8P¹3õKó²R”9Ù¤)\nõ(Lo\0ø\\È*ãs\\ÀA7	«qğ¼UšÃº½bFş”j€(Ìw(“ê¦ÊmÈ¹¡ì¹ê0ü]3ÿÈ¦e\"À¹ÚàkqèbÊ0y­–¹Àòr*<k°+¹é\rç^õ…T¬>¦©=Òüà,7 ássèã…Tä˜W¸ë\n/Ğ~’ &VRLğFKf\Z/Ÿè.¦$mdcæºÍ	·ÍW‡½\Z­5a6ê¾¶]Ã~ËşüÙÿÇæìt>á×õ‘TJZEƒ\0óÌºæ¥/æÁ®Wƒ\"C[Â×oL*‡	\Z¥ÉU°{ÓïÛ,}5C|S±ñ¬éˆ¤3<¡Y­KùşG¨÷ÉW ˜Å½¶g³ğIÈBâÁ&fJÌÛ&Jß…v2sÜfè¹‰YäâV= ŒY7c‹Ì!œå÷V¡ˆrŠİ2ÿ *cƒm‚_Ï]ğ“:ÅWùsî]ŠïndN…³Îÿx÷o\0ø`È*ãs\\ÀA7	«qğ½Uš \"—Ge°ï\"ƒr‚u½PÌÊ¤ËÂÚ1b©×?h¿™—‘İUÙ=î€ës¦W‹ƒ«a½ĞŠ\nD?œÇÏ=JÿZè#”–¼Âye+Âe£Íítªöÿ»œ¨áŠs¦€G¾ÚÂÓ‹‹ÊFA$@G¦xÓí³ùàè‘Ä¥&y›Ø%ØæõUt hùØí{j‹9!coğ7##ü»j{Æ\\$Şpt}R=¬Ššû^Ğr)o°À:…®T¨ì©g‹‰\nÏc;eÕ õ=YÔ~üõÈ7ië\0êÇQêùXIUt10…L^¥jŸÃ¯‘j=ş^#\'¾óêZ(ì‹?.øÙˆ\0’©ÿy`bOÖho;\n~ÊÀu«œµ67’÷†ñ$Î¦YÑ&E#VDè¬®@%¨\r\\I\"x\ZàJ[òÔ•×š…¤Ùê]Y„\rè\"©ÑRo\0èTÈ*ãs\\ÀA7	«qğ½Uš¥\"’¿o˜î(b«7ùØI«ÓVš‡®<˜E‰cOÅÇğ¡½{%Îe#ñMrEC“%µ\n~ÊÈÏ³KjõZ?Í*<–qMß(V$ noE˜œµ+ZÛì8TÕ	e”iÇã|#§µæôì6n˜‡«â´u{‰»zõÉ\ZMJ« šÑ8Q·„B…h†F<)íÜÁñ€ó<£TŸUAŠDŒ€´Â9CTú\0a*+æyÄ,|.Öt<…¡€!é+©/aMA\'AuCb†Ìg½—f¤>#|@$àÚcœÙ½V+ù0pL·RsŒMÓ(dŸ„<ÎÏö#,ôp9F—^KmÓ¨Ş0	à8Î¡yŠŒD¥´uÆeÿ?—@;OüõÏÀ‘¼_5¸QL‡>UL~®VMûô-ˆ•RC’Ÿo\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0','1632','01','02',1),('5433456','Pedro','de jesus','Paramo','Cuellar','1965-08-24','Peru','Masculino','no','no','no','\0øÈ*ãs\\ÀA7	«q°DUšdošquÏ&­¶Ît;¾°æY™¬å€ø!ĞÊøÜTC3˜\0MP*A×Ã­ş<–æVQKìçk“ğèìNï_ÆM+F¾KD_Şú+ï\nâoæ®lBh‡ĞĞ.\Z BÑ;î[8\r˜‡°å|ÚúZû\"ÒJÊæU,/Õv\ncßp£EëRê<ÁD-®~´bÂbfœ9\ZREeLæ»+ï/–KøV)\Zcí‘ÃæÕ.X»ë÷K´QùæØl\'³ƒ@]ÂVPÛëÅtş ÈâZ_åjå–<rÕ¹bâæ_ßk2¥šøîÑÀR×\'ğ•×³q=óXS=ÃIC¨Ÿ´dÔ ÃrF‘&~Ÿ¿¹PÏùÒÀˆ§İócò´K^…õ®Ÿ=œ‰ü¡\ZCK|İæLVhç[>@úu`=w(\Z¦?D™>#\n~…Ö•é?ğVM(>=ÂX:ÇeZøÇşòÅm)¢C‰¢f°I^?o\0økÈ*ãs\\ÀA7	«qp½UšT¦…À V %æå°ûÛ”÷ZÔ„Û&KN|:q¯f¶~FZ¦°µu†˜èoŒ6KÙU-~“qÚ Å”k3ˆ·~TU>òã#‰€È¥Ÿq~¬­‰ğ­– «™R¸“Ø°·ö7Ô¶Í/ÁCEÃÂ)ÁœÕUÉ}½÷HŠ×‘QÅß‡ŞW„ßØÙÂW\\ßÇãà>)2\"ÛÔa2è¥ÏÏ@›YÈõ²şŒKÄ\0‡6t‰\\h/³±úuÖĞ\0ÃğàCXZáÂJ.¢¯‘`Ìˆ%\r¼^e2\0U•½’b[\\íR\"éÜ5X¿Dz£·\n<?åt\"Ôç¯‡Û<ĞiñäGRµ_Œ}çDåŞç=ãQŒDuô+tØV #o ‚?Ày\"ÆÚ ÛWäfG´ØOPdîğX:Ïñ•ïf—şT‰·ûO¦ÏÅ½â“¾‘F\0Ò²^/—‘æğïôD-ßõ\0ò,Øo\0øÈ*ãs\\ÀA7	«qğ@Ušch{â|©B‹–\\„D‹’ßté\ZœÇ>è•D-dC\\¿Ñ\'³º^3 7£é5Kâ	UMu©¼ø<°Äv4åù§ÃUôÃ_S[÷m€œèWÎ\0n2áŒùâı#ÆÉ:Eİÿ7?@½š@V,!%Ê¶3¿ó¼Ä`ë1pü©Úa-ÙF&HÆôÏNí§e(PwûËm¸1,ğŒ¾÷KÓÛ†Š“»°,ëLşä@Î-6€…ijd%ï÷µz±ƒ(+[X\0£îó©à²Ÿ1àà¼Â>FcSSÇÙ¼Û”Ÿ—Ùƒ»ÕõëcbÆhËtª˜”eH.Ó(¯Ûµh¡õşQ¨4‚«‚³™é;Š¯Æ\'åG¤ôæ‡ãfÄıdÙÂ#_ï’ŠGÙQ)Ú\'+â<è\nCpó<ï[.@,-á6# lµ8SßBj˜ÀËÌÔz¸àcsámygß78I¹(öU@Òàe‘JDZV‹µD!ãŠo\0èoÈ*ãs\\ÀA7	«qğ¿Ušì,¶‚k)øL ¤\"æõ9Í]—äöh„€ÙB1æÍ–)‰Ä_]ÈmŸUqvª:B/^ApÆ<’™®â«WÁi[ÏdŞË‰”;f™tş^=¹2®ó›nºú5p;¼_î¼È!æ^\'xâ›‘±=ê/Yû|åoºèø„‡¨x/‘ga3nU±|EÚëÊqYˆùÅåè}‹]pÇcòó‰a\":=LáK ¹Kúó_„Ë §=ç&ºÅö=6Y~‡hBÿœÉÛş¥@=ˆ·.iY´üœPÃl©Í°Xçæå?ç©O‰gø±€Û‘Ù`î¬ñy­K=[mp\r9špÉØ>t`U”@‘Ü±‹\"Î/|×ßçA…µzaß•ÖÎÍêø273ù\ryf!McáñDı>B™¸ºó-;m·•¿…ĞÖ	åvÅ€\n9*œ‹#$®ÈÂo\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0','1632','01','02',1),('71660668','Santiago','de Dios','Higuita','Campo','1965-01-01','Girardota','Masculino','Penicilina	','HipertensiÃ³n','En Cuarentena','\0ø~È*ãs\\ÀA\"‚3\Z$şÑÿ$fÄ{Ë¦Z×:L¿³P…wwfùm„m­ÂwiH6çu&||cóıî<éiRÒhÕcSÜÇıK¼÷ï=z½^eöC9ò+h•ˆíZ‰ÿ&HÇ€§İÔş)eÒ?1ŠğÕGg’w¶D/DŞ<:S£(ôÇmãMcèÀúwx­ŸË<TTÖæ+ÉÆpëiZ€·Za+Å3P‹„å=Ã‚mpa!Üò¡Jgä5Õ‹up‡°À÷xiIƒcÃË	ä–æoŞ<É%c+xôb¹±¿Çÿ\"W•—ıC¿+)Bî0ø4ic[àÎ„ÈúíÜeV­5å\0˜øO\"• ÉŠy­ˆÒòXÑM;rR»RO®½o03!jI¢›É•\0Ÿ­ÍLPR=/\rÎôÙıííŒnpŒBÏÜò\"#õ	6\"õòaF	¨n×¾©—ºèÓ\rì½¶­á{nJyzgUö)„(”ı¬‚×yïÒcH7NW¼%{o\0øÈ*ãs\\ÀA Æ1c\nbAt§zñ.XÇèEmõb”íPÒ•C$!4D:U­\Z«`İ¨5]\"Æ¤‚Ùøñˆß\'VÂı\"ïMêJtv·vÁõ¦-êı€—EiTÛƒU~²Ë¯€ºËèàó\\ûûğg[7Ñöoj)Ñ\"8ÙĞÑ›1b>­Å´”«?wt(‰ò×Ñı›êï±`,âÙ¿EÛ9>¦l{OÇÓ0ôñí)¥’àö\Z‹½ú\rËˆÒzíğOûÇp)é´…–^^mìÕ7A5ª¹s—#\nÊ‘¡=\"=ä€—/DB\rôÏ°5C;z2$¢0LæûŸ‘­ú/§Ù@zm‘(ôa\0<ô<Åÿ»ô#ærA—8Ôÿö|T	8+áã]ˆ`§qöf|şlğ>²KÎÀ(…Äõ©çA÷Úè8DNæƒ6”òûÈÛtõã¥SPZ(V‘Q9õ§¶Íòï®Ãk9¡R]~6HîEßgmO05ÔAb©çœªŸÅ(‰~‰o\0øYÈ*ãs\\ÀA7	«q0¯Uš>ñ»ptí:©A®´¤1d×öÔ$Sìf]˜P­lĞù®éB¥ÚT‘ğ»ö$‡«x9N7—¼‹0ÒEİŠTãKÕ¯vn¤l¿»ÀóİºM5ô)#”MèÓôÉ«ÃÒñ¹çDàILqëš¬\0xÊÜ÷Z…íê1¹í÷±º¬$ø‰{ùœÉ\rğq¥|ób´ÿ}şó\\§lÙ_–0Â!;Ğ•kÈùvn¬áp’²~ miu[Xlöy7ßûßÄ¡vFlÏíO;ÈˆäsmÅn˜ÍçKxZqƒb˜¦€…„3AH$¡ÑC-õÆ<ú\"é|¦ôwv°YEïÑ[‰_‰`·\0#r­\ròŞÃûu ÊYR&U+\rH_øºZ6ŸÎ;w-ÊØñŸÕâFğ´û?*$ø#ìÜ¥x–È–‰WnTÃfò?¯4ÍEVV\"©Ii›o\0è]È*ãs\\ÀA7	«q°›Uš±ÿau¤ü‚Â÷ˆ›wŞ2\'³<]¸d¯ƒ§S^–1Ê ~eŸr3jıèñ©Û9ŸÅáŠTÜÆ÷8—Öâ{Ôë¢\"§>)	:Gò!Ò\r¯àœ7%‰VPÄ†;NoEÀôêP×cĞâ‰–a¸[#¡Ê¾xE‚ÿ”ÛŒÎìÄ-é(}Nn^ô$ÔÂÑTJ¿„ÂIhO~¯ ûîp	¤@ÕóÊôöÍ7@9wMÇûUñ¼ã4sÛMâ¿•¸É%”\'Ë[‰ïótV¶#AÊD:¾äHàıüh­¼ø49Ãª³ò›éÛ^ ûHE2@Èe‚ƒ\rë¢¼¸Ï\0 ˆT6û–$;sÏbø–EªŸÍ{Í>Ôª¸“N}¡æ5¶}@jN\r–ÚÁZ@ä„†•e‡¶myƒÛ¸õ%JŞN.m·r¤ªçf,’ËªS„Iè÷¶‰æio\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0','1632','01','01',1);
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
INSERT INTO `tipo_documento` VALUES ('01','CÃ©dula de Ciudadania',1),('02','Tarjeta de Identidad',1),('03','Pasaporte',1),('04','NIT',0),('05','CÃ©dula de ExtranjerÃ­a',1);
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
INSERT INTO `usuario` VALUES ('1088539048','JosÃ©','MartÃ­n','Campo','Campo','jcampoca','Colombia2018++','01',1,'PERMITIR'),('1111','Pedro','','Paramo','','pedrito','Thomas#2009','02',1,''),('15327400','Nelson','Giovanni','Salazar','Roldan','nesalaz@msn.com','Thomas#2009','01',1,''),('21758804','aura','rosa','campo','campo','campo','Colombia2020++','02',1,'PERMITIR');
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
