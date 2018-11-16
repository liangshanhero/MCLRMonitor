/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50719
Source Host           : localhost:3306
Source Database       : colorcheck

Target Server Type    : MYSQL
Target Server Version : 50719
File Encoding         : 65001

Date: 2018-11-11 01:43:57
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for check
-- ----------------------------
DROP TABLE IF EXISTS `check`;
CREATE TABLE `check` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `number` varchar(255) DEFAULT NULL,
  `project` bigint(255) DEFAULT NULL,
  `red` int(11) DEFAULT NULL,
  `green` int(11) DEFAULT NULL,
  `blue` int(11) DEFAULT NULL,
  `alpha` int(11) DEFAULT NULL,
  `result` float DEFAULT NULL,
  `time` datetime DEFAULT NULL,
  `memo` varchar(255) DEFAULT NULL COMMENT '检测的时间',
  PRIMARY KEY (`id`),
  KEY `project` (`project`),
  CONSTRAINT `check_ibfk_1` FOREIGN KEY (`project`) REFERENCES `project` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of check
-- ----------------------------

-- ----------------------------
-- Table structure for project
-- ----------------------------
DROP TABLE IF EXISTS `project`;
CREATE TABLE `project` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `memo` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of project
-- ----------------------------

-- ----------------------------
-- Table structure for rule
-- ----------------------------
DROP TABLE IF EXISTS `rule`;
CREATE TABLE `rule` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '定性或者定量，0表示定性，1表示定量',
  `name` varchar(255) DEFAULT NULL,
  `project` bigint(20) DEFAULT NULL,
  `type` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `project` (`project`),
  CONSTRAINT `rule_ibfk_1` FOREIGN KEY (`project`) REFERENCES `project` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of rule
-- ----------------------------

-- ----------------------------
-- Table structure for sample
-- ----------------------------
DROP TABLE IF EXISTS `sample`;
CREATE TABLE `sample` (
  `id` bigint(11) NOT NULL,
  `number` varchar(255) DEFAULT NULL COMMENT '编号',
  `project` bigint(255) DEFAULT NULL,
  `red` int(11) DEFAULT NULL,
  `green` int(11) DEFAULT NULL,
  `blue` int(11) DEFAULT NULL,
  `alpha` int(11) DEFAULT NULL,
  `result` float DEFAULT NULL,
  `time` datetime DEFAULT NULL,
  `memo` varchar(255) DEFAULT NULL COMMENT '检测的时间',
  PRIMARY KEY (`id`),
  KEY `project` (`project`),
  CONSTRAINT `sample_ibfk_1` FOREIGN KEY (`project`) REFERENCES `project` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sample
-- ----------------------------
