/*
Navicat MySQL Data Transfer

Source Server         : MyConn
Source Server Version : 50173
Source Host           : localhost:3306
Source Database       : aiaiapp

Target Server Type    : MYSQL
Target Server Version : 50173
File Encoding         : 65001

Date: 2014-06-03 16:10:07
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `college`
-- ----------------------------
DROP TABLE IF EXISTS `college`;
CREATE TABLE `college` (
  `id` int(11) NOT NULL,
  `college` char(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of college
-- ----------------------------

-- ----------------------------
-- Table structure for `followinfo`
-- ----------------------------
DROP TABLE IF EXISTS `followinfo`;
CREATE TABLE `followinfo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `usrid` int(11) NOT NULL,
  `followid` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of followinfo
-- ----------------------------
INSERT INTO `followinfo` VALUES ('12', '52', '58');
INSERT INTO `followinfo` VALUES ('14', '52', '60');
INSERT INTO `followinfo` VALUES ('15', '53', '52');
INSERT INTO `followinfo` VALUES ('16', '53', '54');
INSERT INTO `followinfo` VALUES ('17', '53', '55');
INSERT INTO `followinfo` VALUES ('18', '53', '56');
INSERT INTO `followinfo` VALUES ('19', '53', '57');
INSERT INTO `followinfo` VALUES ('20', '53', '58');
INSERT INTO `followinfo` VALUES ('21', '53', '59');
INSERT INTO `followinfo` VALUES ('22', '53', '60');
INSERT INTO `followinfo` VALUES ('23', '54', '53');
INSERT INTO `followinfo` VALUES ('24', '52', '55');
INSERT INTO `followinfo` VALUES ('25', '52', '65');
INSERT INTO `followinfo` VALUES ('26', '52', '52');

-- ----------------------------
-- Table structure for `news`
-- ----------------------------
DROP TABLE IF EXISTS `news`;
CREATE TABLE `news` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` char(255) CHARACTER SET utf8 DEFAULT NULL,
  `content` char(255) CHARACTER SET utf8 NOT NULL,
  `time` datetime NOT NULL,
  `image` longblob,
  `url` char(255) CHARACTER SET utf8 DEFAULT NULL,
  `imgname` char(255) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of news
-- ----------------------------
INSERT INTO `news` VALUES ('43', '我们的端午', '安徽建筑大学端午祭屈原 传统祭礼展汉民族服装演变史', '2014-05-25 21:58:06', null, null, null);
INSERT INTO `news` VALUES ('44', '健康体检', '学校完成2014年度健康体检工作', '2014-06-01 21:58:45', null, null, null);
INSERT INTO `news` VALUES ('45', '学术沙龙第四期', '城市管理研究中心举行学术沙龙第四期', '2014-06-01 21:59:36', null, null, null);

-- ----------------------------
-- Table structure for `specialty`
-- ----------------------------
DROP TABLE IF EXISTS `specialty`;
CREATE TABLE `specialty` (
  `id` int(11) NOT NULL,
  `collegeid` int(11) NOT NULL,
  `specialty` char(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of specialty
-- ----------------------------

-- ----------------------------
-- Table structure for `usr`
-- ----------------------------
DROP TABLE IF EXISTS `usr`;
CREATE TABLE `usr` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` char(255) CHARACTER SET utf8 NOT NULL,
  `truename` char(255) DEFAULT NULL,
  `pwd` char(255) NOT NULL,
  `sex` char(11) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `mode` int(11) NOT NULL,
  `status` char(255) DEFAULT NULL,
  `place` char(255) DEFAULT NULL,
  `latitude` double DEFAULT NULL,
  `longitiude` double DEFAULT NULL,
  `addr` char(255) DEFAULT NULL,
  `ip` char(255) DEFAULT NULL,
  `college` char(255) DEFAULT NULL,
  `specialty` char(255) DEFAULT NULL,
  `qq` int(11) DEFAULT NULL,
  `userid` char(255) DEFAULT NULL,
  `channelid` char(255) DEFAULT NULL,
  `image` longblob,
  `imgname` char(255) DEFAULT NULL,
  `imgurl` char(255) DEFAULT NULL,
  PRIMARY KEY (`id`,`name`)
) ENGINE=InnoDB AUTO_INCREMENT=75 DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of usr
-- ----------------------------
INSERT INTO `usr` VALUES ('52', '华', '', 'c4ca4238a0b923820dcc509a6f75849b', 'man', '0', '1', null, null, null, null, null, null, '', '', '0', '620277308693527494', '3472338717949303330', null, '', 'u=1059086463,1380116758&fm=56.jpg');
INSERT INTO `usr` VALUES ('53', '李文才', '', 'c4ca4238a0b923820dcc509a6f75849b', 'man', '0', '1', null, null, null, null, null, null, '', '', '0', null, null, null, '', 'u=720179565,327311894&fm=56.jpg');
INSERT INTO `usr` VALUES ('54', '赵鲜华', null, 'c4ca4238a0b923820dcc509a6f75849b', null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null, null, 'u=4081246257,43071039&fm=56.jpg');
INSERT INTO `usr` VALUES ('55', '王朵', null, 'c4ca4238a0b923820dcc509a6f75849b', null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null, null, 'u=1059086463,1380116758&fm=56.jpg');
INSERT INTO `usr` VALUES ('56', '陈聪', null, 'c4ca4238a0b923820dcc509a6f75849b', null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null, null, 'u=1101856212,2102196708&fm=56.jpg');
INSERT INTO `usr` VALUES ('57', '陈杰', null, 'c4ca4238a0b923820dcc509a6f75849b', null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null, null, 'u=1398864697,1597039376&fm=56.jpg');
INSERT INTO `usr` VALUES ('58', '陈鹏飞', null, 'c4ca4238a0b923820dcc509a6f75849b', null, null, '0', null, null, null, null, null, null, null, null, null, null, null, null, null, 'u=1434032499,1552717561&fm=56.jpg');
INSERT INTO `usr` VALUES ('59', '陈一欣', null, 'c4ca4238a0b923820dcc509a6f75849b', null, null, '0', null, null, null, null, null, null, null, null, null, null, null, null, null, 'u=1436014546,1552703472&fm=56.jpg');
INSERT INTO `usr` VALUES ('60', '冯德政', null, 'c4ca4238a0b923820dcc509a6f75849b', null, null, '0', null, null, null, null, null, null, null, null, null, null, null, null, null, 'u=1647861255,2016088653&fm=56.jpg');
INSERT INTO `usr` VALUES ('61', '胡丽云', null, 'c4ca4238a0b923820dcc509a6f75849b', null, null, '0', null, null, null, null, null, null, null, null, null, null, null, null, null, 'u=1680210662,4197634385&fm=56.jpg');
INSERT INTO `usr` VALUES ('62', '李亚男', null, 'c4ca4238a0b923820dcc509a6f75849b', null, null, '0', null, null, null, null, null, null, null, null, null, null, null, null, null, 'u=1829610413,2704064190&fm=56.jpg');
INSERT INTO `usr` VALUES ('63', '陆羽', null, 'c4ca4238a0b923820dcc509a6f75849b', null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null, null, 'u=1923392577,1495689459&fm=56.jpg');
INSERT INTO `usr` VALUES ('64', '宋明月', null, 'c4ca4238a0b923820dcc509a6f75849b', null, null, '0', null, null, null, null, null, null, null, null, null, null, null, null, null, 'u=2215736144,4066089920&fm=56.jpg');
INSERT INTO `usr` VALUES ('65', '王超', null, 'c4ca4238a0b923820dcc509a6f75849b', null, null, '0', null, null, null, null, null, null, null, null, null, null, null, null, null, 'u=2570861414,461621199&fm=56.jpg');
INSERT INTO `usr` VALUES ('66', '易杨杨', null, 'c4ca4238a0b923820dcc509a6f75849b', null, null, '0', null, null, null, null, null, null, null, null, null, null, null, null, null, 'u=2660219338,3811958076&fm=56.jpg');
INSERT INTO `usr` VALUES ('67', '张亚军', null, 'c4ca4238a0b923820dcc509a6f75849b', null, null, '0', null, null, null, null, null, null, null, null, null, null, null, null, null, 'u=2888805917,4131193881&fm=56.jpg');
INSERT INTO `usr` VALUES ('68', '周文祥', null, 'c4ca4238a0b923820dcc509a6f75849b', null, null, '0', null, null, null, null, null, null, null, null, null, null, null, null, null, 'u=3033374093,637809262&fm=56.jpg');
INSERT INTO `usr` VALUES ('69', '朱丽', null, 'c4ca4238a0b923820dcc509a6f75849b', null, null, '0', null, null, null, null, null, null, null, null, null, null, null, null, null, 'u=3058607902,2095718741&fm=56.jpg');
INSERT INTO `usr` VALUES ('70', '科菲', null, 'c4ca4238a0b923820dcc509a6f75849b', null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null, null, 'u=3178877717,2457184931&fm=56.jpg');
INSERT INTO `usr` VALUES ('71', '王云龙', null, 'c4ca4238a0b923820dcc509a6f75849b', null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null, null, 'u=3975133930,2296200414&fm=56.jpg');
INSERT INTO `usr` VALUES ('72', '月宁选', null, 'c4ca4238a0b923820dcc509a6f75849b', null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null, null, 'u=3975648830,656998198&fm=56.jpg');
INSERT INTO `usr` VALUES ('73', 'Jason', null, 'c4ca4238a0b923820dcc509a6f75849b', null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null, null, 'u=4035073904,638868791&fm=56.jpg');
INSERT INTO `usr` VALUES ('74', 'Android', null, 'c4ca4238a0b923820dcc509a6f75849b', null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null, null, 'u=4081246257,43071039&fm=56.jpg');
