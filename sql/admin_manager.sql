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
-- Table structure for admin_announcement
-- ----------------------------
DROP TABLE IF EXISTS `admin_announcement`;
CREATE TABLE `admin_announcement` (
  `id` bigint(40) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `title` varchar(40) COLLATE utf8_unicode_ci NOT NULL COMMENT '标题',
  `sort` varchar(10) COLLATE utf8_unicode_ci NOT NULL COMMENT '排序',
  `content` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '内容',
  `create_user` bigint(20) NOT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_user` bigint(20) DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `version` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '版本',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for admin_cooperatebusiness
-- ----------------------------
DROP TABLE IF EXISTS `admin_cooperatebusiness`;
CREATE TABLE `admin_cooperatebusiness` (
  `id` bigint(40) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(40) COLLATE utf8_unicode_ci NOT NULL COMMENT '名称',
  `qq` varchar(10) COLLATE utf8_unicode_ci NOT NULL COMMENT 'QQ',
  `create_user` bigint(20) NOT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_user` bigint(20) DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `version` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '版本',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for admin_verificationcode
-- ----------------------------
DROP TABLE IF EXISTS `admin_verificationcode`;
CREATE TABLE `admin_verificationcode` (
  `id` varchar(40) COLLATE utf8_unicode_ci NOT NULL COMMENT 'ID',
  `infName` varchar(40) COLLATE utf8_unicode_ci NOT NULL COMMENT '接口名称',
  `type` varchar(10) COLLATE utf8_unicode_ci NOT NULL COMMENT '类型',
  `vCode` varchar(20) COLLATE utf8_unicode_ci NOT NULL COMMENT '验证码',
  `time` varchar(20) COLLATE utf8_unicode_ci NOT NULL COMMENT '时间',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_user` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `version` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '版本',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;


CREATE OR REPLACE VIEW v_ty_today_statistics AS SELECT
    DATE_FORMAT( now( ), '%Y-%m-%d')  today,
    count(1) user_count,
    (SELECT sum( balance ) all_balance FROM gms_user ) user_balance,
    (SELECT sum( balance ) FROM gms_user g WHERE EXISTS ( SELECT 1 FROM ty_user t WHERE type = 2 AND g.id = t.id )) proxy_balance,
    0 game_balance,
    0 cash_balance,
    (SELECT sum( amount ) FROM proxy_goldenrecharge  WHERE DATE_FORMAT( time,'%Y-%m-%d') = DATE_FORMAT(now(), '%Y-%m-%d' )) recharg_balance,
    0 cash_cost,
    (SELECT sum( amount ) FROM proxy_cardpwdwithdraw WHERE DATE_FORMAT( time,'%Y-%m-%d')= DATE_FORMAT(now(), '%Y-%m-%d' ) ) card_balance,
    0 ranking_balance,
    0 betting_commission,
    0 first_reward,
    0 loss_rebate,
    0 betting_wage
  FROM
    ty_user
  WHERE
      DATE_FORMAT( create_time, '%Y-%m-%d' ) = DATE_FORMAT( now( ), '%Y-%m-%d')