/*
Navicat MySQL Data Transfer

Source Server         : gms
Source Server Version : 50560
Source Host           : 39.108.215.89:3306
Source Database       : gms

Target Server Type    : MYSQL
Target Server Version : 50560
File Encoding         : 65001

Date: 2018-11-04 21:16:02
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for site_manager
-- ----------------------------
DROP TABLE IF EXISTS `admin_announcement`;
CREATE TABLE `admin_announcement` (
  `id` varchar(40) NOT NULL COMMENT 'ID',
  `title` varchar(40) NOT NULL COMMENT '标题',
  `sort` varchar(10) NOT NULL COMMENT '排序',
  `content` varchar(100) DEFAULT NULL COMMENT '内容',
  `create_user` varchar(20) NOT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_user` varchar(20) DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `version` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '版本',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB;

DROP TABLE IF EXISTS `admin_cooperatebusiness`;
CREATE TABLE `admin_cooperatebusiness` (
  `id` varchar(40) NOT NULL COMMENT 'ID',
  `name` varchar(40) NOT NULL COMMENT '名称',
  `qq` varchar(10) NOT NULL COMMENT 'QQ',
  `create_user` varchar(20) NOT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_user` varchar(20) DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `version` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '版本',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB;