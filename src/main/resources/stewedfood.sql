/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50644
Source Host           : localhost:3306
Source Database       : stewedfood

Target Server Type    : MYSQL
Target Server Version : 50644
File Encoding         : 65001

Date: 2019-06-23 22:22:29
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for am_product
-- ----------------------------
DROP TABLE IF EXISTS `am_product`;
CREATE TABLE `am_product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_date` datetime DEFAULT NULL,
  `is_del` int(11) DEFAULT NULL,
  `is_sale` int(11) DEFAULT NULL,
  `key_words` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `product_detail` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `product_html_address` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `title` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `product_synopsis` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `product_synopsis_img` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `price` int(11) DEFAULT NULL,
  `unit` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of am_product
-- ----------------------------
INSERT INTO `am_product` VALUES ('1', null, '0', '1', null, null, null, '鸭脖', null, null, '10', '个');
INSERT INTO `am_product` VALUES ('2', null, '0', '1', null, null, null, '鸭掌', null, null, '20', '个');
INSERT INTO `am_product` VALUES ('3', null, '0', '1', null, null, null, '鸭腿', null, null, '30', '个');
INSERT INTO `am_product` VALUES ('4', null, '0', '1', null, null, null, '鸭翅', null, null, '40', '个');
INSERT INTO `am_product` VALUES ('5', null, '0', '1', null, null, null, '鸭胸', null, null, '50', '个');
INSERT INTO `am_product` VALUES ('6', '2019-06-23 18:32:05', '0', '1', '', '', '', '鸡脖', '', '', '10', '个');
INSERT INTO `am_product` VALUES ('7', '2019-06-23 18:32:05', '0', '1', '', '', '', '鸡掌', '', '', '20', '个');
INSERT INTO `am_product` VALUES ('8', '2019-06-23 18:32:05', '0', '1', '', '', '', '鸡腿', '', '', '30', '个');
INSERT INTO `am_product` VALUES ('9', '2019-06-23 18:32:05', '0', '1', '', '', '', '鸡翅', '', '', '40', '个');
INSERT INTO `am_product` VALUES ('10', '2019-06-23 18:32:05', '0', '1', '', '', '', '鸡胸', '', '', '50', '个');

-- ----------------------------
-- Table structure for am_product_product_types
-- ----------------------------
DROP TABLE IF EXISTS `am_product_product_types`;
CREATE TABLE `am_product_product_types` (
  `product_id` int(11) NOT NULL,
  `product_type_id` int(11) NOT NULL,
  KEY `FKce8qew63k5c2r9r9hi2oieulw` (`product_type_id`),
  KEY `FKppqfydbcki1r4icsl1yvqdq8i` (`product_id`),
  CONSTRAINT `FKce8qew63k5c2r9r9hi2oieulw` FOREIGN KEY (`product_type_id`) REFERENCES `am_product_type` (`id`),
  CONSTRAINT `FKppqfydbcki1r4icsl1yvqdq8i` FOREIGN KEY (`product_id`) REFERENCES `am_product` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of am_product_product_types
-- ----------------------------
INSERT INTO `am_product_product_types` VALUES ('1', '1');
INSERT INTO `am_product_product_types` VALUES ('2', '1');
INSERT INTO `am_product_product_types` VALUES ('3', '1');
INSERT INTO `am_product_product_types` VALUES ('4', '1');
INSERT INTO `am_product_product_types` VALUES ('5', '1');
INSERT INTO `am_product_product_types` VALUES ('6', '2');
INSERT INTO `am_product_product_types` VALUES ('7', '2');
INSERT INTO `am_product_product_types` VALUES ('8', '2');
INSERT INTO `am_product_product_types` VALUES ('9', '2');
INSERT INTO `am_product_product_types` VALUES ('10', '2');

-- ----------------------------
-- Table structure for am_product_type
-- ----------------------------
DROP TABLE IF EXISTS `am_product_type`;
CREATE TABLE `am_product_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_date` datetime DEFAULT NULL,
  `is_del` int(11) DEFAULT NULL,
  `order_val` int(11) DEFAULT NULL,
  `product_synopsis` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `product_synopsis_img` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `product_type_name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of am_product_type
-- ----------------------------
INSERT INTO `am_product_type` VALUES ('1', '2019-06-13 10:19:59', '0', '10', null, null, '鸭货');
INSERT INTO `am_product_type` VALUES ('2', null, '0', '20', null, null, '鸡货');
INSERT INTO `am_product_type` VALUES ('3', null, '0', '30', null, null, '猪货');
INSERT INTO `am_product_type` VALUES ('4', null, '0', '40', null, null, '卤菜');

-- ----------------------------
-- Table structure for sf_member
-- ----------------------------
DROP TABLE IF EXISTS `sf_member`;
CREATE TABLE `sf_member` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL,
  `current_needs` int(11) DEFAULT NULL,
  `email` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `head_img` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `is_display_cartoon` int(11) DEFAULT NULL,
  `member_type` int(11) DEFAULT NULL,
  `name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `nick_name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `open_id` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `phone` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `remake` int(11) DEFAULT NULL,
  `sex` int(11) DEFAULT NULL,
  `union_id` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `we_cat_no` int(11) DEFAULT NULL,
  `work_address` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of sf_member
-- ----------------------------
INSERT INTO `sf_member` VALUES ('1', '2019-06-19 20:56:58', null, null, 'https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTK2nn8OicWfxZ3EvsnRqeZWDYcj1kCficmvJ9MSP5htsREF7ChnCtnQAt4033pXA2EZbauOgSFNLApw/132', '0', '1', null, 'geno', 'oLTSX5D7LGLzvCvZj48xkaXDZpQU', null, null, '1', null, null, null);
