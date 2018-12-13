/*
Navicat MariaDB Data Transfer

Source Server         : www.1016.cc
Source Server Version : 50560
Source Host           : www.1016.cc:3306
Source Database       : gms

Target Server Type    : MariaDB
Target Server Version : 50560
File Encoding         : 65001

Date: 2018-12-12 10:07:17
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for admin_announcement
-- ----------------------------
DROP TABLE IF EXISTS `admin_announcement`;
CREATE TABLE `admin_announcement` (
  `id` bigint(40) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `title` varchar(40) COLLATE utf8_unicode_ci NOT NULL COMMENT '标题',
  `sort` varchar(10) COLLATE utf8_unicode_ci NOT NULL COMMENT '排序',
  `content` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '内容',
  `create_user` bigint(20) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user` bigint(20) DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `version` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '版本',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for admin_cardpassword
-- ----------------------------
DROP TABLE IF EXISTS `admin_cardpassword`;
CREATE TABLE `admin_cardpassword` (
  `id` bigint(20) NOT NULL COMMENT 'id',
  `card_type` int(255) DEFAULT NULL COMMENT '卡类型,1:10元会员卡，2:20元会员卡，3:30元会员卡，4:红钻会员卡',
  `card_id` varchar(40) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '卡id',
  `card_password` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '卡密码',
  `owner` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '卡所属者',
  `status` int(255) DEFAULT NULL COMMENT '状态；1未充值，2已充值,3已注销',
  `recharge_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '兑换人名称',
  `recharge_id` varchar(40) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '兑奖id',
  `start_time` datetime DEFAULT NULL COMMENT '生成兑换时间',
  `end_time` datetime DEFAULT NULL COMMENT '使用注销时间',
  `create_user` bigint(255) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for admin_cardtype
-- ----------------------------
DROP TABLE IF EXISTS `admin_cardtype`;
CREATE TABLE `admin_cardtype` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `denomination` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '金额',
  `card_type` int(255) DEFAULT NULL COMMENT '卡类型1:充值卡,2:兑换卡,3:会员卡',
  `generate_by_proxy` int(11) DEFAULT NULL COMMENT '充值卡代理可生成',
  `golden_bean` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '充值卡金豆',
  `experience` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '充值卡经验',
  `card_head` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '自定义卡头',
  `random_figure` int(255) DEFAULT NULL COMMENT '随机位数',
  `random_num_str` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '根据位数生成的随机数字串',
  `card_id` varchar(40) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '卡头和随机数拼接的卡号',
  `gold_coin_every_day` double(255,0) DEFAULT '0' COMMENT '会员卡每日获得金币',
  `draw_duration` bigint(20) DEFAULT '0' COMMENT '会员卡领取时长',
  `icon` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '会员卡图标',
  `create_user` bigint(255) DEFAULT NULL COMMENT '创建者',
  `member_id` bigint(20) DEFAULT NULL COMMENT '使用者',
  `use_interval` int(11) DEFAULT '0' COMMENT '充值卡使用间隔',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user` bigint(20) DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for admin_chartranking
-- ----------------------------
DROP TABLE IF EXISTS `admin_chartranking`;
CREATE TABLE `admin_chartranking` (
  `id` bigint(20) NOT NULL COMMENT 'id',
  `ranking` int(255) DEFAULT NULL COMMENT '排行',
  `chart` int(255) DEFAULT NULL COMMENT '奖励',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for admin_chartrankingsetting
-- ----------------------------
DROP TABLE IF EXISTS `admin_chartrankingsetting`;
CREATE TABLE `admin_chartrankingsetting` (
  `id` bigint(20) NOT NULL COMMENT 'id',
  `ranking_count` int(255) DEFAULT NULL COMMENT '排行榜名次',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for admin_chartsrewards
-- ----------------------------
DROP TABLE IF EXISTS `admin_chartsrewards`;
CREATE TABLE `admin_chartsrewards` (
  `id` bigint(2) NOT NULL AUTO_INCREMENT,
  `type` int(255) DEFAULT NULL COMMENT '类型 1：排行榜奖励，2：虚拟排行榜奖励',
  `member_id` bigint(20) NOT NULL COMMENT '会员id',
  `member_nick_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '会员昵称',
  `rewards` decimal(20,0) DEFAULT NULL COMMENT '奖励',
  `time` date DEFAULT NULL COMMENT '时间',
  `status` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1067585625793789955 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for admin_chipinwage
-- ----------------------------
DROP TABLE IF EXISTS `admin_chipinwage`;
CREATE TABLE `admin_chipinwage` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `member_id` bigint(20) DEFAULT NULL COMMENT '会员id',
  `member_nick_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '昵称',
  `effective_flow` double(255,0) DEFAULT NULL COMMENT '有效流水',
  `wage` double(255,0) DEFAULT NULL COMMENT '工资',
  `date` date DEFAULT NULL COMMENT '日期',
  `status` int(255) DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1067585496756027395 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for admin_cooperatebusiness
-- ----------------------------
DROP TABLE IF EXISTS `admin_cooperatebusiness`;
CREATE TABLE `admin_cooperatebusiness` (
  `id` bigint(40) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(40) COLLATE utf8_unicode_ci NOT NULL COMMENT '名称',
  `qq` varchar(20) COLLATE utf8_unicode_ci NOT NULL COMMENT 'QQ',
  `create_user` bigint(20) NOT NULL COMMENT '创建人',
  `create_time` date NOT NULL COMMENT '创建时间',
  `update_user` bigint(20) DEFAULT NULL COMMENT '更新人',
  `update_time` date DEFAULT NULL COMMENT '更新时间',
  `version` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '版本',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1066968959493988355 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for admin_exchargecardpwd
-- ----------------------------
DROP TABLE IF EXISTS `admin_exchargecardpwd`;
CREATE TABLE `admin_exchargecardpwd` (
  `order_id` int(10) NOT NULL AUTO_INCREMENT COMMENT '订单id',
  `member_id` bigint(20) DEFAULT NULL COMMENT '会员id',
  `member_nick_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '会员昵称',
  `card_type` int(255) DEFAULT NULL COMMENT '卡类型',
  `card_id` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '卡id',
  `card_password` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '卡密码',
  `status` int(255) DEFAULT NULL COMMENT '状态；1未兑换，2冻结、3解冻，4已兑换',
  `start_time` datetime DEFAULT NULL COMMENT '生成兑换时间',
  `end_time` datetime DEFAULT NULL COMMENT '处理结束时间',
  `withdraw_proxy_id` bigint(20) DEFAULT NULL COMMENT '回收代理id',
  `withdraw_proxy_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '回收代理名称',
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10000003 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for admin_exchargeorder
-- ----------------------------
DROP TABLE IF EXISTS `admin_exchargeorder`;
CREATE TABLE `admin_exchargeorder` (
  `order_id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '订单id',
  `member_id` bigint(60) DEFAULT NULL COMMENT '会员id',
  `member_nick_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '会员昵称',
  `prize_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '奖品名称',
  `exchange_num` int(11) DEFAULT NULL COMMENT '兑换数量',
  `exchange_single_price` decimal(10,2) DEFAULT NULL COMMENT '兑换单价',
  `start_time` datetime DEFAULT NULL COMMENT '兑换时间',
  `status` int(255) DEFAULT NULL COMMENT '订单状态，1未发货，2已发货，3已取消',
  `processor_id` bigint(255) DEFAULT NULL COMMENT '最终处理人id',
  `processor_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '最终处理人名称',
  `end_time` datetime DEFAULT NULL COMMENT '最终处理时间',
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10000007 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for admin_gamesetting
-- ----------------------------
DROP TABLE IF EXISTS `admin_gamesetting`;
CREATE TABLE `admin_gamesetting` (
  `id` bigint(20) NOT NULL COMMENT 'id',
  `game_name` varchar(255) COLLATE utf8_unicode_ci NOT NULL COMMENT '游戏名称',
  `chipin_lower` double(255,0) DEFAULT NULL COMMENT '游戏名称',
  `chipin_upper` double(255,0) DEFAULT NULL COMMENT '投注下限',
  `stop_chipin_time` int(11) DEFAULT NULL COMMENT '投注上限',
  `draw_monitor_value` double(255,0) DEFAULT NULL COMMENT '停止投注时间',
  `return_rate` int(255) DEFAULT NULL COMMENT '开奖监控值',
  `open_game` int(255) DEFAULT NULL COMMENT '返奖率',
  `draw_game` int(255) DEFAULT NULL COMMENT '开始游戏，1开启，0关闭',
  `compute_turnover` int(255) DEFAULT NULL COMMENT '游戏开奖，1开启，0关闭',
  `turnover_num_count` int(255) DEFAULT NULL COMMENT '计算流水，1开启，0关闭',
  `time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '时间戳',
  PRIMARY KEY (`id`,`game_name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for admin_juniorcommission
-- ----------------------------
DROP TABLE IF EXISTS `admin_juniorcommission`;
CREATE TABLE `admin_juniorcommission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `member_id` bigint(20) DEFAULT NULL COMMENT '会员id',
  `member_nick_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '昵称',
  `ytd_junior_commission_flow` double(255,0) DEFAULT NULL COMMENT '有效流水',
  `wage` double(255,0) DEFAULT NULL COMMENT '工资',
  `date` date DEFAULT NULL COMMENT '日期',
  `status` int(255) DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1067585526527197187 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for admin_levelsetting
-- ----------------------------
DROP TABLE IF EXISTS `admin_levelsetting`;
CREATE TABLE `admin_levelsetting` (
  `id` int(11) NOT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL COMMENT '名称',
  `experience` int(11) DEFAULT NULL COMMENT '经验',
  `relieve_golden_coin` int(11) DEFAULT NULL COMMENT '救济金币',
  `draw_times` int(11) DEFAULT NULL COMMENT '领取次数',
  `draw_condition` int(11) DEFAULT NULL COMMENT '领取条件',
  `recharge_commission` int(11) DEFAULT NULL COMMENT '充值提成',
  `cash_prize_discount` int(11) DEFAULT NULL COMMENT '兑奖折扣',
  PRIMARY KEY (`name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for admin_lossrabate
-- ----------------------------
DROP TABLE IF EXISTS `admin_lossrabate`;
CREATE TABLE `admin_lossrabate` (
  `id` bigint(3) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `member_id` bigint(20) NOT NULL COMMENT '会员id',
  `member_nick_name` varchar(255) COLLATE utf8_unicode_ci NOT NULL COMMENT '会员昵称',
  `loss` decimal(60,0) NOT NULL COMMENT '亏损',
  `rabate` decimal(60,0) NOT NULL COMMENT '返利',
  `status` int(255) DEFAULT NULL COMMENT '状态：1:已领取、2未领取、3已逾期',
  `time` date DEFAULT NULL COMMENT '日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1067585366812295171 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for admin_prize
-- ----------------------------
DROP TABLE IF EXISTS `admin_prize`;
CREATE TABLE `admin_prize` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '名称',
  `classify` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '类目',
  `auto_dispatch` int(255) DEFAULT NULL COMMENT '自动发货',
  `card_type` int(255) DEFAULT NULL COMMENT '卡类型',
  `basic_price` decimal(10,2) DEFAULT NULL COMMENT '基准价格',
  `icon` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '图片',
  `stock` int(255) DEFAULT NULL COMMENT '库存',
  `converted` int(255) DEFAULT NULL COMMENT '已兑出',
  `details` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '详情',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1068343805914136579 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for admin_prizeclassify
-- ----------------------------
DROP TABLE IF EXISTS `admin_prizeclassify`;
CREATE TABLE `admin_prizeclassify` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '类目名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1071767161584320515 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for admin_proxyoperation
-- ----------------------------
DROP TABLE IF EXISTS `admin_proxyoperation`;
CREATE TABLE `admin_proxyoperation` (
  `id` bigint(20) NOT NULL COMMENT 'id',
  `proxy_id` bigint(20) DEFAULT NULL COMMENT '代理id',
  `proxy_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '代理名称',
  `money_changed` decimal(40,0) DEFAULT NULL COMMENT '变动金额',
  `account` decimal(40,0) DEFAULT NULL COMMENT '余额',
  `type` int(255) DEFAULT NULL COMMENT '类型',
  `description` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '描述',
  `time` datetime DEFAULT NULL COMMENT '时间',
  `ip` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'ip',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for admin_saledetail
-- ----------------------------
DROP TABLE IF EXISTS `admin_saledetail`;
CREATE TABLE `admin_saledetail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `proxy_id` bigint(20) NOT NULL DEFAULT '1' COMMENT '代理ID',
  `proxy_name` varchar(255) COLLATE utf8_unicode_ci NOT NULL COMMENT '代理名称',
  `time` date DEFAULT NULL COMMENT '时间',
  `substitute_money` double DEFAULT NULL COMMENT '代充金额',
  `callback_money` double DEFAULT NULL COMMENT '回收金额',
  `income` double(255,0) DEFAULT NULL COMMENT '收益',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1000010 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for admin_substitute
-- ----------------------------
DROP TABLE IF EXISTS `admin_substitute`;
CREATE TABLE `admin_substitute` (
  `id` bigint(6) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `proxy_id` bigint(20) NOT NULL COMMENT '代理id',
  `proxy_name` varchar(255) COLLATE utf8_unicode_ci NOT NULL COMMENT '代理名称',
  `member_id` bigint(20) NOT NULL COMMENT '会员id',
  `member_nick_name` varchar(255) COLLATE utf8_unicode_ci NOT NULL COMMENT '会员名称',
  `money` double(255,0) NOT NULL COMMENT '金额',
  `status` varchar(255) COLLATE utf8_unicode_ci NOT NULL COMMENT '状态：成功、失败、已撤销、提交',
  `time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1067056370391478274 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for admin_userlogin
-- ----------------------------
DROP TABLE IF EXISTS `admin_userlogin`;
CREATE TABLE `admin_userlogin` (
  `id` bigint(20) NOT NULL COMMENT 'id',
  `type` int(11) NOT NULL,
  `member_id` bigint(20) DEFAULT NULL COMMENT '会员id',
  `member_nick_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '会员昵称',
  `status` int(255) DEFAULT NULL COMMENT '状态',
  `ip` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'ip',
  `addr` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '地址',
  `time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for admin_vchartranking
-- ----------------------------
DROP TABLE IF EXISTS `admin_vchartranking`;
CREATE TABLE `admin_vchartranking` (
  `id` bigint(20) NOT NULL COMMENT 'id',
  `ranking` int(255) DEFAULT NULL COMMENT '排行',
  `chart` int(255) DEFAULT NULL COMMENT '奖励',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for admin_vchartrankingsetting
-- ----------------------------
DROP TABLE IF EXISTS `admin_vchartrankingsetting`;
CREATE TABLE `admin_vchartrankingsetting` (
  `id` bigint(20) NOT NULL COMMENT 'id',
  `ranking_count` int(255) DEFAULT NULL COMMENT '排行名次',
  `base_chart` int(255) DEFAULT NULL COMMENT '奖励',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for admin_verificationcode
-- ----------------------------
DROP TABLE IF EXISTS `admin_verificationcode`;
CREATE TABLE `admin_verificationcode` (
  `id` int(40) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `inf_name` varchar(40) COLLATE utf8_unicode_ci NOT NULL COMMENT '接口名称',
  `dest` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `type` int(10) NOT NULL COMMENT '类型',
  `v_code` varchar(20) COLLATE utf8_unicode_ci NOT NULL COMMENT '验证码',
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '版本',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10000070 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for admin_websetting
-- ----------------------------
DROP TABLE IF EXISTS `admin_websetting`;
CREATE TABLE `admin_websetting` (
  `id` bigint(10) NOT NULL DEFAULT '1',
  `name` varchar(40) DEFAULT NULL COMMENT '网站名称',
  `key_word` varchar(255) DEFAULT NULL COMMENT '网站关键字',
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  `exchange_propor` bigint(255) DEFAULT NULL COMMENT '金币和人民币兑换比例',
  `enable_exchange` tinyint(10) DEFAULT NULL COMMENT '兑换开关',
  `proxy_recharge_discount` int(255) unsigned DEFAULT NULL COMMENT '代理充值折扣',
  `enable24_recharge` tinyint(10) DEFAULT NULL COMMENT '是否能24小时兑换',
  `forbid_recharge_begin_time` varchar(40) DEFAULT NULL COMMENT '禁止兑换起始时间',
  `forbid_recharge_end_time` varchar(40) DEFAULT NULL COMMENT '禁止兑换结束时间',
  `regist_offer_gold` int(255) DEFAULT NULL COMMENT '注册赠送金币',
  `serial_cal_days` int(255) DEFAULT NULL COMMENT '流水计算天数',
  `recharge_experience_propor` double(255,0) DEFAULT NULL COMMENT '充值经验兑换比例',
  `free_exchange_times` int(11) DEFAULT NULL COMMENT '免费兑换倍数',
  `gain_grow_propor` double(255,0) DEFAULT NULL COMMENT '获得成长值比例',
  `overranging_fee` double(255,0) DEFAULT NULL COMMENT '超出手续费',
  `subordinate_cathectic_wages_propor` double(255,0) DEFAULT NULL COMMENT '下线投注工资比例',
  `cathectic_wages_propor` double(255,0) DEFAULT NULL COMMENT '投注工资比例',
  `loss_rebate_propor` double(255,0) DEFAULT NULL COMMENT '亏损返利比例',
  `first_fill_rebate_propor` double(255,0) DEFAULT NULL COMMENT '首充返利比例',
  `entry_fee` double(255,0) DEFAULT NULL COMMENT '报名费',
  `virtual_corn_available_count` int(255) DEFAULT NULL COMMENT '可得虚拟币',
  `virtual_corn_available_times` int(11) DEFAULT NULL COMMENT '虚拟币可得到次数',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `create_user` bigint(20) DEFAULT NULL COMMENT '创建用户',
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `update_user` bigint(20) DEFAULT NULL COMMENT '更新用户',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for gms_site_action
-- ----------------------------
DROP TABLE IF EXISTS `gms_site_action`;
CREATE TABLE `gms_site_action` (
  `id` bigint(20) NOT NULL,
  `action_type_id` bigint(20) NOT NULL,
  `action_time` datetime DEFAULT NULL,
  `action_ip` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `action_user` bigint(255) DEFAULT NULL,
  `action_content` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `action_result` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for gms_user
-- ----------------------------
DROP TABLE IF EXISTS `gms_user`;
CREATE TABLE `gms_user` (
  `id` bigint(20) unsigned NOT NULL,
  `nick_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '昵称',
  `qq` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'QQ号',
  `balance` decimal(10,0) DEFAULT '0' COMMENT '余额',
  `bank` decimal(10,0) DEFAULT '0' COMMENT '银行',
  `experience` int(10) unsigned DEFAULT '0' COMMENT '经验值',
  `distrib_money` decimal(10,0) DEFAULT NULL COMMENT '铺货金额',
  `referee_id` bigint(20) unsigned DEFAULT NULL COMMENT '推荐人ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for gms_useraccount
-- ----------------------------
DROP TABLE IF EXISTS `gms_useraccount`;
CREATE TABLE `gms_useraccount` (
  `id` bigint(20) NOT NULL,
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `user_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '用户名称',
  `account` double(255,0) DEFAULT NULL COMMENT '余额',
  `time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for gms_user_balance_change
-- ----------------------------
DROP TABLE IF EXISTS `gms_user_balance_change`;
CREATE TABLE `gms_user_balance_change` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '序号',
  `type` int(10) unsigned NOT NULL DEFAULT '6' COMMENT '类型：0-登录；1-充值；2-兑奖；3-游戏；4-银行；5-推广；6-其他',
  `descp` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '描述',
  `balance_change` decimal(10,0) NOT NULL COMMENT '余额变动',
  `balance` decimal(10,0) NOT NULL COMMENT '余额',
  `bank` decimal(10,0) NOT NULL COMMENT '银行',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `update_user` bigint(20) NOT NULL COMMENT '更新人',
  `user_id` bigint(20) NOT NULL DEFAULT '-1' COMMENT '余额所属账号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for proxy_cardpwdwithdraw
-- ----------------------------
DROP TABLE IF EXISTS `proxy_cardpwdwithdraw`;
CREATE TABLE `proxy_cardpwdwithdraw` (
  `id` bigint(20) NOT NULL,
  `proxy_id` bigint(20) DEFAULT NULL,
  `proxy_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `member_id` bigint(20) DEFAULT NULL,
  `member_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `count` int(11) DEFAULT NULL,
  `amount` decimal(10,0) DEFAULT NULL,
  `time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for proxy_firstchargerebate
-- ----------------------------
DROP TABLE IF EXISTS `proxy_firstchargerebate`;
CREATE TABLE `proxy_firstchargerebate` (
  `id` bigint(20) NOT NULL COMMENT 'id',
  `member_id` bigint(20) DEFAULT NULL COMMENT '会员id',
  `member_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '会员名称',
  `ytd_firstcharge` decimal(65,0) DEFAULT NULL COMMENT '昨日首充金额',
  `rebate` decimal(65,0) DEFAULT NULL COMMENT '返利额',
  `date` datetime DEFAULT NULL COMMENT '日期',
  `status` int(255) DEFAULT NULL COMMENT '状态,1未领取，2已领取',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for proxy_fundexchange
-- ----------------------------
DROP TABLE IF EXISTS `proxy_fundexchange`;
CREATE TABLE `proxy_fundexchange` (
  `id` bigint(20) NOT NULL COMMENT 'id',
  `type` int(255) DEFAULT NULL COMMENT '1：转出，2：转入',
  `money` decimal(65,0) DEFAULT NULL COMMENT '金额',
  `opposite` bigint(255) DEFAULT NULL COMMENT '对方',
  `proxy_id` bigint(255) DEFAULT NULL COMMENT '代理ID',
  `time` datetime DEFAULT NULL COMMENT '时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for proxy_gold
-- ----------------------------
DROP TABLE IF EXISTS `proxy_gold`;
CREATE TABLE `proxy_gold` (
  `id` bigint(20) NOT NULL COMMENT 'id',
  `member_id` bigint(20) DEFAULT NULL COMMENT '会员id',
  `gold` decimal(65,0) DEFAULT NULL COMMENT '金币',
  `time` datetime DEFAULT NULL COMMENT '时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for proxy_goldenrecharge
-- ----------------------------
DROP TABLE IF EXISTS `proxy_goldenrecharge`;
CREATE TABLE `proxy_goldenrecharge` (
  `id` bigint(20) NOT NULL DEFAULT '1' COMMENT 'id',
  `proxy_id` bigint(20) DEFAULT NULL COMMENT '代理ID',
  `proxy_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '代理名称',
  `member_id` bigint(20) DEFAULT NULL COMMENT '会员ID',
  `member_nick_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '会员昵称',
  `amount` decimal(30,0) DEFAULT NULL COMMENT '金额',
  `time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '时间',
  `status` int(255) DEFAULT NULL COMMENT '状态',
  `withdraw` int(255) DEFAULT NULL COMMENT '撤销',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for proxy_rechargewithdraw
-- ----------------------------
DROP TABLE IF EXISTS `proxy_rechargewithdraw`;
CREATE TABLE `proxy_rechargewithdraw` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `proxy_id` bigint(20) DEFAULT NULL COMMENT '代理ID',
  `proxy_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '代理名称',
  `type` int(255) DEFAULT NULL COMMENT '类型，1充值，2提现',
  `money` decimal(65,0) DEFAULT NULL COMMENT '金额',
  `balance` decimal(65,0) DEFAULT NULL COMMENT '余额',
  `distribution` decimal(65,0) DEFAULT NULL COMMENT '铺货',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `finish_time` datetime DEFAULT NULL COMMENT '处理时间',
  `status` int(255) DEFAULT NULL COMMENT '状态，1待处理，2已处理，3取消',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1069084794186448899 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for qrtz_blob_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_blob_triggers`;
CREATE TABLE `qrtz_blob_triggers` (
  `SCHED_NAME` varchar(120) COLLATE utf8_unicode_ci NOT NULL,
  `TRIGGER_NAME` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `BLOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  KEY `SCHED_NAME` (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_blob_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for qrtz_calendars
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_calendars`;
CREATE TABLE `qrtz_calendars` (
  `SCHED_NAME` varchar(120) COLLATE utf8_unicode_ci NOT NULL,
  `CALENDAR_NAME` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `CALENDAR` blob NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`CALENDAR_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for qrtz_cron_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_cron_triggers`;
CREATE TABLE `qrtz_cron_triggers` (
  `SCHED_NAME` varchar(120) COLLATE utf8_unicode_ci NOT NULL,
  `TRIGGER_NAME` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `CRON_EXPRESSION` varchar(120) COLLATE utf8_unicode_ci NOT NULL,
  `TIME_ZONE_ID` varchar(80) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_cron_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for qrtz_fired_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_fired_triggers`;
CREATE TABLE `qrtz_fired_triggers` (
  `SCHED_NAME` varchar(120) COLLATE utf8_unicode_ci NOT NULL,
  `ENTRY_ID` varchar(95) COLLATE utf8_unicode_ci NOT NULL,
  `TRIGGER_NAME` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `INSTANCE_NAME` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `FIRED_TIME` bigint(13) NOT NULL,
  `SCHED_TIME` bigint(13) NOT NULL,
  `PRIORITY` int(11) NOT NULL,
  `STATE` varchar(16) COLLATE utf8_unicode_ci NOT NULL,
  `JOB_NAME` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `JOB_GROUP` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `IS_NONCONCURRENT` varchar(1) COLLATE utf8_unicode_ci DEFAULT NULL,
  `REQUESTS_RECOVERY` varchar(1) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`ENTRY_ID`),
  KEY `IDX_QRTZ_FT_TRIG_INST_NAME` (`SCHED_NAME`,`INSTANCE_NAME`),
  KEY `IDX_QRTZ_FT_INST_JOB_REQ_RCVRY` (`SCHED_NAME`,`INSTANCE_NAME`,`REQUESTS_RECOVERY`),
  KEY `IDX_QRTZ_FT_J_G` (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_FT_JG` (`SCHED_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_FT_T_G` (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  KEY `IDX_QRTZ_FT_TG` (`SCHED_NAME`,`TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for qrtz_job_details
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_job_details`;
CREATE TABLE `qrtz_job_details` (
  `SCHED_NAME` varchar(120) COLLATE utf8_unicode_ci NOT NULL,
  `JOB_NAME` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `JOB_GROUP` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `DESCRIPTION` varchar(250) COLLATE utf8_unicode_ci DEFAULT NULL,
  `JOB_CLASS_NAME` varchar(250) COLLATE utf8_unicode_ci NOT NULL,
  `IS_DURABLE` varchar(1) COLLATE utf8_unicode_ci NOT NULL,
  `IS_NONCONCURRENT` varchar(1) COLLATE utf8_unicode_ci NOT NULL,
  `IS_UPDATE_DATA` varchar(1) COLLATE utf8_unicode_ci NOT NULL,
  `REQUESTS_RECOVERY` varchar(1) COLLATE utf8_unicode_ci NOT NULL,
  `JOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_J_REQ_RECOVERY` (`SCHED_NAME`,`REQUESTS_RECOVERY`),
  KEY `IDX_QRTZ_J_GRP` (`SCHED_NAME`,`JOB_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for qrtz_locks
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_locks`;
CREATE TABLE `qrtz_locks` (
  `SCHED_NAME` varchar(120) COLLATE utf8_unicode_ci NOT NULL,
  `LOCK_NAME` varchar(40) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`LOCK_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for qrtz_paused_trigger_grps
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_paused_trigger_grps`;
CREATE TABLE `qrtz_paused_trigger_grps` (
  `SCHED_NAME` varchar(120) COLLATE utf8_unicode_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for qrtz_scheduler_state
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_scheduler_state`;
CREATE TABLE `qrtz_scheduler_state` (
  `SCHED_NAME` varchar(120) COLLATE utf8_unicode_ci NOT NULL,
  `INSTANCE_NAME` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `LAST_CHECKIN_TIME` bigint(13) NOT NULL,
  `CHECKIN_INTERVAL` bigint(13) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`INSTANCE_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for qrtz_simple_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_simple_triggers`;
CREATE TABLE `qrtz_simple_triggers` (
  `SCHED_NAME` varchar(120) COLLATE utf8_unicode_ci NOT NULL,
  `TRIGGER_NAME` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `REPEAT_COUNT` bigint(7) NOT NULL,
  `REPEAT_INTERVAL` bigint(12) NOT NULL,
  `TIMES_TRIGGERED` bigint(10) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_simple_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for qrtz_simprop_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_simprop_triggers`;
CREATE TABLE `qrtz_simprop_triggers` (
  `SCHED_NAME` varchar(120) COLLATE utf8_unicode_ci NOT NULL,
  `TRIGGER_NAME` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `STR_PROP_1` varchar(512) COLLATE utf8_unicode_ci DEFAULT NULL,
  `STR_PROP_2` varchar(512) COLLATE utf8_unicode_ci DEFAULT NULL,
  `STR_PROP_3` varchar(512) COLLATE utf8_unicode_ci DEFAULT NULL,
  `INT_PROP_1` int(11) DEFAULT NULL,
  `INT_PROP_2` int(11) DEFAULT NULL,
  `LONG_PROP_1` bigint(20) DEFAULT NULL,
  `LONG_PROP_2` bigint(20) DEFAULT NULL,
  `DEC_PROP_1` decimal(13,4) DEFAULT NULL,
  `DEC_PROP_2` decimal(13,4) DEFAULT NULL,
  `BOOL_PROP_1` varchar(1) COLLATE utf8_unicode_ci DEFAULT NULL,
  `BOOL_PROP_2` varchar(1) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_simprop_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for qrtz_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_triggers`;
CREATE TABLE `qrtz_triggers` (
  `SCHED_NAME` varchar(120) COLLATE utf8_unicode_ci NOT NULL,
  `TRIGGER_NAME` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `JOB_NAME` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `JOB_GROUP` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `DESCRIPTION` varchar(250) COLLATE utf8_unicode_ci DEFAULT NULL,
  `NEXT_FIRE_TIME` bigint(13) DEFAULT NULL,
  `PREV_FIRE_TIME` bigint(13) DEFAULT NULL,
  `PRIORITY` int(11) DEFAULT NULL,
  `TRIGGER_STATE` varchar(16) COLLATE utf8_unicode_ci NOT NULL,
  `TRIGGER_TYPE` varchar(8) COLLATE utf8_unicode_ci NOT NULL,
  `START_TIME` bigint(13) NOT NULL,
  `END_TIME` bigint(13) DEFAULT NULL,
  `CALENDAR_NAME` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `MISFIRE_INSTR` smallint(2) DEFAULT NULL,
  `JOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  KEY `IDX_QRTZ_T_J` (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_T_JG` (`SCHED_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_T_C` (`SCHED_NAME`,`CALENDAR_NAME`),
  KEY `IDX_QRTZ_T_G` (`SCHED_NAME`,`TRIGGER_GROUP`),
  KEY `IDX_QRTZ_T_STATE` (`SCHED_NAME`,`TRIGGER_STATE`),
  KEY `IDX_QRTZ_T_N_STATE` (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`,`TRIGGER_STATE`),
  KEY `IDX_QRTZ_T_N_G_STATE` (`SCHED_NAME`,`TRIGGER_GROUP`,`TRIGGER_STATE`),
  KEY `IDX_QRTZ_T_NEXT_FIRE_TIME` (`SCHED_NAME`,`NEXT_FIRE_TIME`),
  KEY `IDX_QRTZ_T_NFT_ST` (`SCHED_NAME`,`TRIGGER_STATE`,`NEXT_FIRE_TIME`),
  KEY `IDX_QRTZ_T_NFT_MISFIRE` (`SCHED_NAME`,`MISFIRE_INSTR`,`NEXT_FIRE_TIME`),
  KEY `IDX_QRTZ_T_NFT_ST_MISFIRE` (`SCHED_NAME`,`MISFIRE_INSTR`,`NEXT_FIRE_TIME`,`TRIGGER_STATE`),
  KEY `IDX_QRTZ_T_NFT_ST_MISFIRE_GRP` (`SCHED_NAME`,`MISFIRE_INSTR`,`NEXT_FIRE_TIME`,`TRIGGER_GROUP`,`TRIGGER_STATE`),
  CONSTRAINT `qrtz_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) REFERENCES `qrtz_job_details` (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for ty_global_permission
-- ----------------------------
DROP TABLE IF EXISTS `ty_global_permission`;
CREATE TABLE `ty_global_permission` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '序号',
  `path` varchar(255) COLLATE utf8_unicode_ci NOT NULL COMMENT '鉴权路径',
  `perms` varchar(255) COLLATE utf8_unicode_ci NOT NULL COMMENT '权限标识',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for ty_oper_log
-- ----------------------------
DROP TABLE IF EXISTS `ty_oper_log`;
CREATE TABLE `ty_oper_log` (
  `id` bigint(20) NOT NULL COMMENT '编号',
  `start_time` datetime DEFAULT NULL COMMENT '开始时间',
  `end_time` datetime DEFAULT NULL COMMENT '结束时间',
  `ip` varchar(15) COLLATE utf8_unicode_ci DEFAULT NULL,
  `user_id` bigint(20) DEFAULT '0' COMMENT '操作用户编号',
  `module` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '操作模块',
  `type` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '操作类型',
  `request` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '请求报文',
  `response` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '返回报文',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for ty_permission
-- ----------------------------
DROP TABLE IF EXISTS `ty_permission`;
CREATE TABLE `ty_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '权限ID',
  `name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '权限名称',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父权限ID',
  `resource_type` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '资源类型',
  `url` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '资源地址',
  `permission` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '权限',
  `order` int(10) unsigned NOT NULL COMMENT '排序',
  `enabled` tinyint(255) NOT NULL DEFAULT '1' COMMENT '0-无效;1-有效',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for ty_role
-- ----------------------------
DROP TABLE IF EXISTS `ty_role`;
CREATE TABLE `ty_role` (
  `id` bigint(255) NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '角色名称',
  `desc` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '角色描述',
  `enabled` tinyint(2) unsigned NOT NULL DEFAULT '1' COMMENT '0-无效;1-有效',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for ty_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `ty_role_permission`;
CREATE TABLE `ty_role_permission` (
  `permission_id` bigint(20) NOT NULL COMMENT '权限ID',
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`permission_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for ty_user
-- ----------------------------
DROP TABLE IF EXISTS `ty_user`;
CREATE TABLE `ty_user` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `username` varchar(20) COLLATE utf8_unicode_ci NOT NULL COMMENT '登录名',
  `password` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '密码',
  `name` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '真实姓名',
  `phone` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '手机号',
  `create_user` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建人',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_user` bigint(20) DEFAULT NULL COMMENT '更新人',
  `update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '状态：0-初始;1-正常;2-锁定;3-过期',
  `type` int(10) unsigned DEFAULT NULL COMMENT '账号类型',
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for ty_user_role
-- ----------------------------
DROP TABLE IF EXISTS `ty_user_role`;
CREATE TABLE `ty_user_role` (
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for t_chipin_game
-- ----------------------------
DROP TABLE IF EXISTS `t_chipin_game`;
CREATE TABLE `t_chipin_game` (
  `game_code` varchar(40) COLLATE utf8_unicode_ci NOT NULL COMMENT '游戏编码',
  `game_name` varchar(128) COLLATE utf8_unicode_ci NOT NULL COMMENT '游戏名称',
  PRIMARY KEY (`game_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for t_chipin_game_datano
-- ----------------------------
DROP TABLE IF EXISTS `t_chipin_game_datano`;
CREATE TABLE `t_chipin_game_datano` (
  `dateno` varchar(40) COLLATE utf8_unicode_ci NOT NULL COMMENT '期号',
  `game_code` varchar(40) COLLATE utf8_unicode_ci NOT NULL COMMENT '游戏名称',
  `opem_time` datetime NOT NULL COMMENT '开奖时间',
  PRIMARY KEY (`dateno`,`game_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for t_chipin_game_detail
-- ----------------------------
DROP TABLE IF EXISTS `t_chipin_game_detail`;
CREATE TABLE `t_chipin_game_detail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '投注详情ID',
  `member_id` bigint(20) NOT NULL COMMENT '会员ID',
  `dateno` varchar(40) COLLATE utf8_unicode_ci NOT NULL COMMENT '期号',
  `game_code` varchar(20) COLLATE utf8_unicode_ci NOT NULL COMMENT '游戏编码',
  `open_code` int(11) NOT NULL COMMENT '投注号码',
  `chipin_amount` decimal(10,0) NOT NULL COMMENT '投注额',
  `winning_amount` decimal(10,0) NOT NULL COMMENT '中奖额',
  `chipin_time` datetime NOT NULL COMMENT '投注时间',
  `chipin_type` int(11) NOT NULL COMMENT '投注方式',
  `status` int(11) NOT NULL COMMENT '状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- View structure for v_ty_today_statistics
-- ----------------------------
DROP VIEW IF EXISTS `v_ty_today_statistics`;
CREATE ALGORITHM=UNDEFINED DEFINER=`gmsdev`@`%` SQL SECURITY DEFINER VIEW `v_ty_today_statistics` AS select date_format(now(),'%Y-%m-%d') AS `today`,count(1) AS `user_count`,(select sum(`gms_user`.`balance`) AS `all_balance` from `gms_user`) AS `user_balance`,(select sum(`g`.`balance`) from `gms_user` `g` where exists(select 1 from `ty_user` `t` where ((`t`.`type` = 2) and (`g`.`id` = `t`.`id`)))) AS `proxy_balance`,0 AS `game_balance`,0 AS `cash_balance`,(select sum(`proxy_goldenrecharge`.`amount`) from `proxy_goldenrecharge` where (date_format(`proxy_goldenrecharge`.`time`,'%Y-%m-%d') = date_format(now(),'%Y-%m-%d'))) AS `recharg_balance`,0 AS `cash_cost`,(select sum(`proxy_cardpwdwithdraw`.`amount`) from `proxy_cardpwdwithdraw` where (date_format(`proxy_cardpwdwithdraw`.`time`,'%Y-%m-%d') = date_format(now(),'%Y-%m-%d'))) AS `card_balance`,0 AS `ranking_balance`,0 AS `betting_commission`,0 AS `first_reward`,0 AS `loss_rebate`,0 AS `betting_wage` from `ty_user` where (date_format(`ty_user`.`create_time`,'%Y-%m-%d') = date_format(now(),'%Y-%m-%d')) ;
