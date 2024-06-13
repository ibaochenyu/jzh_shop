/*
 Navicat Premium Data Transfer

 Source Server         : 本地win11--mysqlRoot
 Source Server Type    : MySQL
 Source Server Version : 80025
 Source Host           : 127.0.0.1:3306
 Source Schema         : jzh_shop

 Target Server Type    : MySQL
 Target Server Version : 80025
 File Encoding         : 65001

 Date: 12/06/2024 21:44:50
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_basic
-- ----------------------------
DROP TABLE IF EXISTS `t_basic`;
CREATE TABLE `t_basic`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT,
  `work_id` int NULL DEFAULT NULL COMMENT 'workId',
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `sex` int NULL DEFAULT NULL,
  `home_address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'homeAddress',
  `date` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1800434853942845441 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_basic
-- ----------------------------
INSERT INTO `t_basic` VALUES (1, 3, '黄小鑫', 1, '黄小鑫的家', '2024-06-06 19:00:40');
INSERT INTO `t_basic` VALUES (2, 60, '严小玲', 1, '严小玲的家', '2024-06-22 19:00:43');
INSERT INTO `t_basic` VALUES (1800434784585834498, 0, NULL, 0, NULL, '2024-06-11 15:47:57');
INSERT INTO `t_basic` VALUES (1800434853942845441, 0, NULL, 0, NULL, '2024-06-11 15:48:08');

-- ----------------------------
-- Table structure for t_commodity
-- ----------------------------
DROP TABLE IF EXISTS `t_commodity`;
CREATE TABLE `t_commodity`  (
  `id` bigint NOT NULL,
  `produce_date` datetime NULL DEFAULT NULL,
  `truth_factory_id` bigint NULL DEFAULT NULL,
  `truth_styler_id` bigint NULL DEFAULT NULL,
  `truth_worker_id` bigint NULL DEFAULT NULL,
  `commodity_status` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_commodity
-- ----------------------------
INSERT INTO `t_commodity` VALUES (1800059497388068865, '2024-06-10 16:14:51', 1, 82003, 14, 0);
INSERT INTO `t_commodity` VALUES (1800059497409040386, '2024-06-10 16:14:51', 1, 82003, 14, 0);
INSERT INTO `t_commodity` VALUES (1800059497409040387, '2024-06-10 16:14:51', 1, 82003, 14, 1);
INSERT INTO `t_commodity` VALUES (1800059497409040388, '2024-06-10 16:14:51', 1, 82003, 14, 1);
INSERT INTO `t_commodity` VALUES (1800059497409040389, '2024-06-10 16:14:51', 1, 82003, 14, 0);
INSERT INTO `t_commodity` VALUES (1800059497409040390, '2024-06-10 16:14:51', 1, 82003, 14, 0);
INSERT INTO `t_commodity` VALUES (1800059497409040391, '2024-06-10 16:14:51', 1, 82003, 14, 0);
INSERT INTO `t_commodity` VALUES (1800059497409040392, '2024-06-10 16:14:51', 1, 82003, 14, 0);
INSERT INTO `t_commodity` VALUES (1800059497409040393, '2024-06-10 16:14:51', 1, 82003, 14, 0);
INSERT INTO `t_commodity` VALUES (1800059497409040394, '2024-06-10 16:14:51', 1, 82003, 14, 0);
INSERT INTO `t_commodity` VALUES (1800059497409040395, '2024-06-10 16:14:51', 1, 82003, 14, 0);
INSERT INTO `t_commodity` VALUES (1800059497409040396, '2024-06-10 16:14:51', 1, 82003, 14, 0);
INSERT INTO `t_commodity` VALUES (1800059497409040397, '2024-06-10 16:14:51', 1, 82003, 11, 0);
INSERT INTO `t_commodity` VALUES (1800059497409040398, '2024-06-10 16:14:51', 1, 82003, 11, 0);
INSERT INTO `t_commodity` VALUES (1800059497409040399, '2024-06-10 16:14:51', 1, 82003, 11, 0);
INSERT INTO `t_commodity` VALUES (1800059497409040400, '2024-06-10 16:14:51', 1, 82003, 11, 0);
INSERT INTO `t_commodity` VALUES (1800059497476149249, '2024-06-10 16:14:51', 1, 82003, 11, 0);
INSERT INTO `t_commodity` VALUES (1800059497476149250, '2024-06-10 16:14:51', 1, 82003, 11, 0);
INSERT INTO `t_commodity` VALUES (1800059497476149251, '2024-06-10 16:14:51', 1, 82003, 12, 0);
INSERT INTO `t_commodity` VALUES (1800059497476149252, '2024-06-10 16:14:51', 1, 82003, 12, 0);
INSERT INTO `t_commodity` VALUES (1800059497476149253, '2024-06-10 16:14:51', 1, 82003, 12, 0);
INSERT INTO `t_commodity` VALUES (1800059497476149254, '2024-06-10 16:14:51', 1, 82003, 12, 0);
INSERT INTO `t_commodity` VALUES (1800059497476149255, '2024-06-10 16:14:51', 1, 82003, 12, 0);
INSERT INTO `t_commodity` VALUES (1800059497476149256, '2024-06-10 16:14:51', 1, 82003, 12, 0);
INSERT INTO `t_commodity` VALUES (1800059497476149257, '2024-06-10 16:14:51', 1, 82003, 12, 0);
INSERT INTO `t_commodity` VALUES (1800059497476149258, '2024-06-10 16:14:51', 1, 82003, 12, 0);
INSERT INTO `t_commodity` VALUES (1800059497476149259, '2024-06-10 16:14:51', 1, 82003, 13, 0);
INSERT INTO `t_commodity` VALUES (1800059497476149260, '2024-06-10 16:14:51', 1, 82003, 13, 0);
INSERT INTO `t_commodity` VALUES (1800059497476149261, '2024-06-10 16:14:51', 1, 82003, 13, 0);
INSERT INTO `t_commodity` VALUES (1800059497476149262, '2024-06-10 16:14:51', 2, 82003, 11, 0);
INSERT INTO `t_commodity` VALUES (1800059497476149263, '2024-06-10 16:14:51', 2, 82003, 11, 0);
INSERT INTO `t_commodity` VALUES (1800059497476149264, '2024-06-10 16:14:51', 2, 82003, 11, 0);
INSERT INTO `t_commodity` VALUES (1800059497476149265, '2024-06-10 16:14:51', 2, 82003, 11, 0);
INSERT INTO `t_commodity` VALUES (1800059497476149266, '2024-06-10 16:14:51', 2, 82003, 11, 0);
INSERT INTO `t_commodity` VALUES (1800059497476149267, '2024-06-10 16:14:51', 2, 82003, 11, 0);
INSERT INTO `t_commodity` VALUES (1800059497476149268, '2024-06-10 16:14:51', 2, 82003, 11, 0);
INSERT INTO `t_commodity` VALUES (1800059497476149269, '2024-06-10 16:14:51', 2, 82003, 11, 0);
INSERT INTO `t_commodity` VALUES (1800059497476149270, '2024-06-10 16:14:51', 2, 82003, 11, 0);
INSERT INTO `t_commodity` VALUES (1800059497476149271, '2024-06-10 16:14:51', 2, 82003, 11, 0);
INSERT INTO `t_commodity` VALUES (1800059497476149272, '2024-06-10 16:14:51', 2, 82003, 11, 0);
INSERT INTO `t_commodity` VALUES (1800059497476149273, '2024-06-10 16:14:51', 2, 82003, 11, 0);
INSERT INTO `t_commodity` VALUES (1800059497476149274, '2024-06-10 16:14:51', 2, 82003, 11, 0);
INSERT INTO `t_commodity` VALUES (1800059497476149275, '2024-06-10 16:14:51', 2, 82003, 11, 0);
INSERT INTO `t_commodity` VALUES (1800059497476149276, '2024-06-10 16:14:51', 2, 82003, 11, 0);
INSERT INTO `t_commodity` VALUES (1800059497476149277, '2024-06-10 16:14:51', 2, 82003, 11, 0);
INSERT INTO `t_commodity` VALUES (1800059497476149278, '2024-06-10 16:14:51', 2, 82003, 11, 0);
INSERT INTO `t_commodity` VALUES (1800059497476149279, '2024-06-10 16:14:51', 2, 82003, 11, 0);
INSERT INTO `t_commodity` VALUES (1800059497543258114, '2024-06-10 16:14:51', 2, 82003, 11, 0);
INSERT INTO `t_commodity` VALUES (1800059497543258115, '2024-06-10 16:14:51', 2, 82003, 11, 0);
INSERT INTO `t_commodity` VALUES (1800059497543258116, '2024-06-10 16:14:51', 2, 82003, 11, 0);
INSERT INTO `t_commodity` VALUES (1800059497543258117, '2024-06-10 16:14:51', 2, 82003, 11, 0);
INSERT INTO `t_commodity` VALUES (1800059497543258118, '2024-06-10 16:14:51', 2, 82003, 11, 0);
INSERT INTO `t_commodity` VALUES (1800059497543258119, '2024-06-10 16:14:51', 2, 82003, 11, 0);
INSERT INTO `t_commodity` VALUES (1800059497543258120, '2024-06-10 16:14:51', 2, 82003, 11, 0);
INSERT INTO `t_commodity` VALUES (1800059497543258121, '2024-06-10 16:14:51', 2, 82003, 11, 0);
INSERT INTO `t_commodity` VALUES (1800059497543258122, '2024-06-10 16:14:51', 2, 82003, 11, 0);
INSERT INTO `t_commodity` VALUES (1800059497543258123, '2024-06-10 16:14:51', 2, 82003, 11, 0);
INSERT INTO `t_commodity` VALUES (1800059497543258124, '2024-06-10 16:14:51', 3, 82003, 11, 0);
INSERT INTO `t_commodity` VALUES (1800059497543258125, '2024-06-10 16:14:51', 3, 82003, 11, 0);
INSERT INTO `t_commodity` VALUES (1800059497543258126, '2024-06-10 16:14:51', 3, 82003, 11, 0);
INSERT INTO `t_commodity` VALUES (1800059497543258127, '2024-06-10 16:14:51', 3, 82003, 11, 0);
INSERT INTO `t_commodity` VALUES (1800059497543258128, '2024-06-10 16:14:51', 3, 82003, 11, 0);
INSERT INTO `t_commodity` VALUES (1800059497543258129, '2024-06-10 16:14:51', 3, 82003, 11, 0);
INSERT INTO `t_commodity` VALUES (1800059497543258130, '2024-06-10 16:14:51', 3, 82003, 11, 0);
INSERT INTO `t_commodity` VALUES (1800059497543258131, '2024-06-10 16:14:51', 3, 82003, 11, 0);
INSERT INTO `t_commodity` VALUES (1800059497543258132, '2024-06-10 16:14:51', 3, 82003, 11, 0);
INSERT INTO `t_commodity` VALUES (1800059497543258133, '2024-06-10 16:14:51', 3, 82003, 11, 0);
INSERT INTO `t_commodity` VALUES (1800059497543258134, '2024-06-10 16:14:51', 3, 82003, 11, 0);
INSERT INTO `t_commodity` VALUES (1800059497543258135, '2024-06-10 16:14:51', 3, 82003, 11, 0);
INSERT INTO `t_commodity` VALUES (1800059497543258136, '2024-06-10 16:14:51', 3, 82003, 11, 0);
INSERT INTO `t_commodity` VALUES (1800059497543258137, '2024-06-10 16:14:51', 3, 82003, 11, 0);
INSERT INTO `t_commodity` VALUES (1800059497543258138, '2024-06-10 16:14:51', 3, 82003, 11, 0);
INSERT INTO `t_commodity` VALUES (1800059497543258139, '2024-06-10 16:14:51', 3, 82003, 11, 0);
INSERT INTO `t_commodity` VALUES (1800059497543258140, '2024-06-10 16:14:51', 3, 82003, 11, 0);
INSERT INTO `t_commodity` VALUES (1800059497543258141, '2024-06-10 16:14:51', 3, 82003, 11, 0);
INSERT INTO `t_commodity` VALUES (1800059497543258142, '2024-06-10 16:14:51', 3, 82003, 11, 0);
INSERT INTO `t_commodity` VALUES (1800059497543258143, '2024-06-10 16:14:51', 3, 82003, 11, 0);
INSERT INTO `t_commodity` VALUES (1800383562986233858, NULL, NULL, 12, NULL, 13);
INSERT INTO `t_commodity` VALUES (1800385474905194497, NULL, NULL, 12, NULL, 13);
INSERT INTO `t_commodity` VALUES (1800392053411000322, NULL, NULL, 12, NULL, 13);
INSERT INTO `t_commodity` VALUES (1800392081357647873, NULL, NULL, NULL, NULL, 13);

-- ----------------------------
-- Table structure for t_order_item
-- ----------------------------
DROP TABLE IF EXISTS `t_order_item`;
CREATE TABLE `t_order_item`  (
  `id` bigint NOT NULL,
  `truth_order_main_id` bigint NULL DEFAULT NULL,
  `truth_factory_id` bigint NULL DEFAULT NULL,
  `truth_styler_id` bigint NULL DEFAULT NULL,
  `product_count` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_order_item
-- ----------------------------

-- ----------------------------
-- Table structure for t_order_main
-- ----------------------------
DROP TABLE IF EXISTS `t_order_main`;
CREATE TABLE `t_order_main`  (
  `id` bigint NOT NULL,
  `create_date` datetime NULL DEFAULT NULL,
  `pay_date` datetime NULL DEFAULT NULL,
  `truth_user_id` bigint NULL DEFAULT NULL,
  `order_status` int NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_order_main
-- ----------------------------
INSERT INTO `t_order_main` VALUES (1800800585786732546, NULL, NULL, 1145146, 2);
INSERT INTO `t_order_main` VALUES (1800800798102401026, NULL, NULL, 1145146, 2);
INSERT INTO `t_order_main` VALUES (1800801052705042433, NULL, NULL, 1145146, 2);
INSERT INTO `t_order_main` VALUES (1800802923238805505, NULL, NULL, 1145146, 2);
INSERT INTO `t_order_main` VALUES (1800807405490163714, NULL, NULL, 1145146, 2);
INSERT INTO `t_order_main` VALUES (1800807525854105601, NULL, NULL, 1145146, 2);
INSERT INTO `t_order_main` VALUES (1800808446587740162, NULL, NULL, 1145146, 2);
INSERT INTO `t_order_main` VALUES (1800809177306816514, NULL, NULL, 1145146, 2);
INSERT INTO `t_order_main` VALUES (1800812042238775298, NULL, NULL, 1145146, 2);
INSERT INTO `t_order_main` VALUES (1800815032093802498, NULL, NULL, 1145146, 2);
INSERT INTO `t_order_main` VALUES (1800816650981945345, NULL, NULL, 1145146, 2);
INSERT INTO `t_order_main` VALUES (1800817572189507585, NULL, NULL, 1145146, 2);
INSERT INTO `t_order_main` VALUES (1800817651226972161, NULL, NULL, 1145146, 2);
INSERT INTO `t_order_main` VALUES (1800818336437796865, NULL, NULL, 1145146, 2);
INSERT INTO `t_order_main` VALUES (1800818387532808193, NULL, NULL, 1145146, 2);
INSERT INTO `t_order_main` VALUES (1800818463986581506, NULL, NULL, 1145146, 2);
INSERT INTO `t_order_main` VALUES (1800818521054281730, NULL, NULL, 1145146, 2);
INSERT INTO `t_order_main` VALUES (1800818646459777026, NULL, NULL, 1145146, 2);
INSERT INTO `t_order_main` VALUES (1800820803137372161, NULL, NULL, 1145146, 2);
INSERT INTO `t_order_main` VALUES (1800820830601674753, NULL, NULL, 1145146, 2);
INSERT INTO `t_order_main` VALUES (1800820864600702978, NULL, NULL, 1145146, 2);
INSERT INTO `t_order_main` VALUES (1800821846499889154, NULL, NULL, 1145146, 2);
INSERT INTO `t_order_main` VALUES (1800821907535400962, NULL, NULL, 1145146, 2);
INSERT INTO `t_order_main` VALUES (1800821964234002434, NULL, NULL, 1145146, 2);
INSERT INTO `t_order_main` VALUES (1800822158929399809, NULL, NULL, 1145146, 2);
INSERT INTO `t_order_main` VALUES (1800822559162396673, NULL, NULL, 1145146, 2);
INSERT INTO `t_order_main` VALUES (1800822616762773506, NULL, NULL, 1145146, 2);
INSERT INTO `t_order_main` VALUES (1800822967683411969, NULL, NULL, 1145146, 2);
INSERT INTO `t_order_main` VALUES (1800823826177748994, NULL, NULL, 1145146, 2);
INSERT INTO `t_order_main` VALUES (1800825075275075585, NULL, NULL, 1145146, 2);
INSERT INTO `t_order_main` VALUES (1800825126982455297, NULL, NULL, 1145146, 2);
INSERT INTO `t_order_main` VALUES (1800825353881718786, NULL, NULL, 1145146, 2);
INSERT INTO `t_order_main` VALUES (1800825446076715010, NULL, NULL, 1145146, 2);
INSERT INTO `t_order_main` VALUES (1800827733281009665, NULL, NULL, 1145146, 2);
INSERT INTO `t_order_main` VALUES (1800827775215661057, NULL, NULL, 1145146, 2);
INSERT INTO `t_order_main` VALUES (1800827820371537922, NULL, NULL, 1145146, 2);
INSERT INTO `t_order_main` VALUES (1800828647844794369, NULL, NULL, 1145146, 2);
INSERT INTO `t_order_main` VALUES (1800828684431708161, NULL, NULL, 1145146, 2);
INSERT INTO `t_order_main` VALUES (1800828731592462337, NULL, NULL, 1145146, 2);
INSERT INTO `t_order_main` VALUES (1800829395261378562, NULL, NULL, 1145146, 2);
INSERT INTO `t_order_main` VALUES (1800829778146807809, NULL, NULL, 1145146, 2);
INSERT INTO `t_order_main` VALUES (1800829785356816386, NULL, NULL, 1145146, 2);
INSERT INTO `t_order_main` VALUES (1800829875580489730, NULL, NULL, 1145146, 2);
INSERT INTO `t_order_main` VALUES (1800829879938371586, NULL, NULL, 1145146, 2);
INSERT INTO `t_order_main` VALUES (1800829883407060993, NULL, NULL, 1145146, 2);
INSERT INTO `t_order_main` VALUES (1800830992888532994, NULL, NULL, 1145146, 2);
INSERT INTO `t_order_main` VALUES (1800831028120686593, NULL, NULL, 1145146, 2);
INSERT INTO `t_order_main` VALUES (1800831113806123009, NULL, NULL, 1145146, 2);
INSERT INTO `t_order_main` VALUES (1800831303548047361, NULL, NULL, 1145146, 2);
INSERT INTO `t_order_main` VALUES (1800831450617122817, NULL, NULL, 1145146, 2);
INSERT INTO `t_order_main` VALUES (1800838210174251009, NULL, NULL, 1145146, 2);
INSERT INTO `t_order_main` VALUES (1800838320815796226, NULL, NULL, 1145146, 2);
INSERT INTO `t_order_main` VALUES (1800841756097556482, NULL, NULL, 1145146, 2);
INSERT INTO `t_order_main` VALUES (1800841799944810498, NULL, NULL, 1145146, 2);
INSERT INTO `t_order_main` VALUES (1800843048199061505, NULL, NULL, 1145146, 2);
INSERT INTO `t_order_main` VALUES (1800843099264712705, NULL, NULL, 1145146, 2);
INSERT INTO `t_order_main` VALUES (1800845296496947201, NULL, NULL, 1145146, 1);
INSERT INTO `t_order_main` VALUES (1800847383704240130, NULL, NULL, 1145146, 1);
INSERT INTO `t_order_main` VALUES (1800848648966373377, NULL, NULL, 1145146, 1);
INSERT INTO `t_order_main` VALUES (1800848892168896514, NULL, NULL, 1145146, 1);
INSERT INTO `t_order_main` VALUES (1800850528169811969, NULL, NULL, 1145146, 1);
INSERT INTO `t_order_main` VALUES (1800857330273935361, NULL, NULL, 1145146, 1);
INSERT INTO `t_order_main` VALUES (1800863254090821634, NULL, NULL, 1145146, 1);
INSERT INTO `t_order_main` VALUES (1800863987209023489, NULL, NULL, 1145146, 1);
INSERT INTO `t_order_main` VALUES (1800864105568088065, NULL, NULL, 1145146, 1);
INSERT INTO `t_order_main` VALUES (1800864780330864642, NULL, NULL, 1145146, 1);
INSERT INTO `t_order_main` VALUES (1800874981180239873, NULL, NULL, 1145146, 1);

-- ----------------------------
-- Table structure for t_produce
-- ----------------------------
DROP TABLE IF EXISTS `t_produce`;
CREATE TABLE `t_produce`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT,
  `produce_date` datetime NULL DEFAULT NULL,
  `truth_factory_id` bigint NULL DEFAULT NULL,
  `truth_styler_id` bigint NULL DEFAULT NULL,
  `truth_worker_id` bigint NULL DEFAULT NULL,
  `produce_count` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1800403307856801794 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_produce
-- ----------------------------
INSERT INTO `t_produce` VALUES (11, '1900-01-20 00:00:00', 1, 82013, 1, 9);
INSERT INTO `t_produce` VALUES (12, '1900-01-20 00:00:00', 1, 82015, 1, 10);
INSERT INTO `t_produce` VALUES (14, '1900-01-20 00:00:00', 1, 82017, 301, 11);
INSERT INTO `t_produce` VALUES (15, '1900-01-20 00:00:00', 1, 82017, 301, 12);
INSERT INTO `t_produce` VALUES (16, '1900-01-20 00:00:00', 1, 82011, 301, 13);
INSERT INTO `t_produce` VALUES (17, '1900-01-20 00:00:00', 1, 82011, 301, 23);
INSERT INTO `t_produce` VALUES (18, '1900-01-20 00:00:00', 1, 82011, 301, 43);
INSERT INTO `t_produce` VALUES (19, '1900-01-20 00:00:00', 1, 82011, 301, 24);
INSERT INTO `t_produce` VALUES (20, '1900-01-20 00:00:00', 2, 82018, 301, 23);
INSERT INTO `t_produce` VALUES (24, '1900-01-20 00:00:00', NULL, 82015, 1, 12);
INSERT INTO `t_produce` VALUES (25, '1900-01-20 00:00:00', NULL, 82013, 1, 34);
INSERT INTO `t_produce` VALUES (26, '1900-01-20 00:00:00', 1, 82002, 2, 12);
INSERT INTO `t_produce` VALUES (56, '2020-05-07 19:26:07', 1, 82002, 1, 2);
INSERT INTO `t_produce` VALUES (89, '1900-01-20 00:00:00', 1, 82005, 1, 3);
INSERT INTO `t_produce` VALUES (100, '2020-05-07 19:26:07', 2, 82001, 1, 1);
INSERT INTO `t_produce` VALUES (1799354986466029570, '1997-02-28 08:00:00', 1, 111, 222, 333);
INSERT INTO `t_produce` VALUES (1799459742207311873, '1997-02-28 08:00:00', 1, 111, 222, 334);
INSERT INTO `t_produce` VALUES (1799470610332700674, '1997-02-28 08:00:00', 2, 111, 222, 335);
INSERT INTO `t_produce` VALUES (1799627693006946305, '1997-02-28 08:00:00', 2, 111, 222, 336);
INSERT INTO `t_produce` VALUES (1799642769143320577, '1997-02-28 08:00:00', 1, 111, 222, 337);
INSERT INTO `t_produce` VALUES (1799681530858229761, '1997-02-28 08:00:00', 1, 82008, 1, 999);
INSERT INTO `t_produce` VALUES (1799684386927607809, '1997-02-28 08:00:00', 1, 82008, 1, 5544);
INSERT INTO `t_produce` VALUES (1800010261636210690, '1111-11-11 08:00:00', 1, 3, 5, 6);
INSERT INTO `t_produce` VALUES (1800011652597440514, '1111-11-11 08:00:00', 1, 12, 3, 34);
INSERT INTO `t_produce` VALUES (1800016439346868225, '1111-11-11 08:00:00', 1, 55, 55, 55);
INSERT INTO `t_produce` VALUES (1800017008325177346, '1111-11-11 08:00:00', 1, 22, 22, 22);
INSERT INTO `t_produce` VALUES (1800017127699263489, '1111-11-11 08:00:00', 1, 33, 33, 33);
INSERT INTO `t_produce` VALUES (1800017348630032386, '1111-11-11 08:00:00', 1, 4, 4, 4);
INSERT INTO `t_produce` VALUES (1800017869076049921, '1111-11-11 08:00:00', 1, 5, 5, 5);
INSERT INTO `t_produce` VALUES (1800018511072997378, '1111-11-11 08:00:00', 1, 9, 9, 9);
INSERT INTO `t_produce` VALUES (1800019066021359618, '1122-12-12 08:00:00', 1, 55, 55, 55);
INSERT INTO `t_produce` VALUES (1800024516846596098, '2222-11-11 08:00:00', 1, 3, 3, 3);
INSERT INTO `t_produce` VALUES (1800029821668044802, '1111-11-11 08:00:00', 1, 2324, 23, 234);
INSERT INTO `t_produce` VALUES (1800034130245910530, '1997-11-11 08:00:00', 1, 666, 1, 12);
INSERT INTO `t_produce` VALUES (1800034397066559490, '1999-11-11 08:00:00', 1, 82001, 1, 12);
INSERT INTO `t_produce` VALUES (1800034420764377089, '1999-11-11 08:00:00', 1, 82001, 1, 12);
INSERT INTO `t_produce` VALUES (1800034579158073346, '1999-11-11 08:00:00', 1, 82001, 1, 12);
INSERT INTO `t_produce` VALUES (1800034706149015553, '1999-11-11 08:00:00', 1, 82001, 12, 12);
INSERT INTO `t_produce` VALUES (1800035003097350145, '1999-11-11 08:00:00', 1, 82001, 123, 12);
INSERT INTO `t_produce` VALUES (1800038231876739073, '2023-01-01 08:00:00', NULL, 82999, 1, 12);
INSERT INTO `t_produce` VALUES (1800040107376586754, '2023-01-01 08:00:00', NULL, 82999, 2, 12);
INSERT INTO `t_produce` VALUES (1800041143843676161, '1992-11-11 08:00:00', 2, 82001, 1, 12);
INSERT INTO `t_produce` VALUES (1800041231903088642, '1992-11-11 08:00:00', 3, 82001, 3, 12);
INSERT INTO `t_produce` VALUES (1800041736502951937, '1992-11-11 08:00:00', 1, 82002, 3, 12);
INSERT INTO `t_produce` VALUES (1800041984877035521, '1992-11-11 08:00:00', 1, 82002, 4, 9);
INSERT INTO `t_produce` VALUES (1800044259871727618, '1999-11-11 08:00:00', 1, 82003, 7979, 12);
INSERT INTO `t_produce` VALUES (1800045432230039553, '1998-11-12 08:00:00', 3, 82001, 4455, 100);
INSERT INTO `t_produce` VALUES (1800045968870264833, '1998-11-12 08:00:00', 3, 82001, 4455, 99);
INSERT INTO `t_produce` VALUES (1800048072045256706, '2024-06-01 08:00:00', NULL, 83009, 14, 67);
INSERT INTO `t_produce` VALUES (1800051629247098881, '2005-01-01 08:00:00', NULL, 89999, 22, 54);
INSERT INTO `t_produce` VALUES (1800059491469905921, '1997-02-21 08:00:00', 1, 82003, 23, 77);
INSERT INTO `t_produce` VALUES (1800403307856801794, '2024-06-02 08:00:00', 12, 83009, 4, 12);

-- ----------------------------
-- Table structure for t_produce_salary
-- ----------------------------
DROP TABLE IF EXISTS `t_produce_salary`;
CREATE TABLE `t_produce_salary`  (
  `id` bigint NOT NULL,
  `produce_date` datetime NULL DEFAULT NULL,
  `truth_styler_id` bigint NULL DEFAULT NULL,
  `unit_price` decimal(10, 2) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `日期款号独立索引`(`produce_date`, `truth_styler_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_produce_salary
-- ----------------------------

-- ----------------------------
-- Table structure for t_produce_styler
-- ----------------------------
DROP TABLE IF EXISTS `t_produce_styler`;
CREATE TABLE `t_produce_styler`  (
  `id` bigint NOT NULL,
  `show_styler_id` int NULL DEFAULT NULL,
  `styler_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_produce_styler
-- ----------------------------

-- ----------------------------
-- Table structure for t_sys_log
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_log`;
CREATE TABLE `t_sys_log`  (
  `id` bigint NOT NULL,
  `date` datetime NULL DEFAULT NULL,
  `comment` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `ip` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `operator_class` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `operator_argv` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `duration` bigint NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_sys_log
-- ----------------------------
INSERT INTO `t_sys_log` VALUES (1799458949680988162, '2024-06-08 00:00:00', '请求searchPageResult', '0.0.0.0', 'cn.ibaochenyu.jzh_shop.controller.BasicController.getOneProduceRtResult()', 'null', 9296);
INSERT INTO `t_sys_log` VALUES (1799458999433822210, '2024-06-08 00:00:00', '请求searchPageResult', '0.0.0.0', 'cn.ibaochenyu.jzh_shop.controller.BasicController.getOneProduceRtResult()', 'null', 31);
INSERT INTO `t_sys_log` VALUES (1799459026281562113, '2024-06-08 00:00:00', '请求searchPageResult', '0.0.0.0', 'cn.ibaochenyu.jzh_shop.controller.BasicController.getOneProduceRtResult()', 'null', 32);
INSERT INTO `t_sys_log` VALUES (1799459530361405441, '2024-06-08 23:12:39', '请求searchPageResult', '0.0.0.0', 'cn.ibaochenyu.jzh_shop.controller.BasicController.getOneProduceRtResult()', 'null', 31);
INSERT INTO `t_sys_log` VALUES (1799459769860358145, '2024-06-08 23:13:30', 'saveProduce', '0.0.0.0', 'cn.ibaochenyu.jzh_shop.controller.BasicController.save()', '{\"id\":1799459742207311873,\"produceDate\":857088000000,\"truthItemId\":111,\"truthWorkerId\":222,\"produceCount\":334}', 0);
INSERT INTO `t_sys_log` VALUES (1799459785865822210, '2024-06-08 23:13:38', '请求searchPageResult', '0.0.0.0', 'cn.ibaochenyu.jzh_shop.controller.BasicController.getOneProduceRtResult()', 'null', 31);
INSERT INTO `t_sys_log` VALUES (1799468216119074817, '2024-06-08 23:47:10', '请求searchPageResult', '0.0.0.0', 'cn.ibaochenyu.jzh_shop.controller.BasicController.getOneProduceRtResult()', 'null', 1311);
INSERT INTO `t_sys_log` VALUES (1799468796740771841, '2024-06-08 23:49:28', '请求searchPageResult', '0.0.0.0', 'cn.ibaochenyu.jzh_shop.controller.BasicController.getOneProduceRtResult()', 'null', 1367);
INSERT INTO `t_sys_log` VALUES (1799469646448054273, '2024-06-08 23:52:51', '请求searchPageResult', '0.0.0.0', 'cn.ibaochenyu.jzh_shop.controller.BasicController.getOneProduceRtResult()', 'null', 1046);
INSERT INTO `t_sys_log` VALUES (1799469880817373185, '2024-06-08 23:53:47', '请求searchPageResult', '0.0.0.0', 'cn.ibaochenyu.jzh_shop.controller.BasicController.getOneProduceRtResult()', 'null', 33);
INSERT INTO `t_sys_log` VALUES (1799469885355610114, '2024-06-08 23:53:48', '请求searchPageResult', '0.0.0.0', 'cn.ibaochenyu.jzh_shop.controller.BasicController.getOneProduceRtResult()', 'null', 16);
INSERT INTO `t_sys_log` VALUES (1799470472344293377, '2024-06-08 23:56:08', '请求searchPageResult', '0.0.0.0', 'cn.ibaochenyu.jzh_shop.controller.BasicController.getOneProduceRtResult()', 'null', 1252);
INSERT INTO `t_sys_log` VALUES (1799470610399809538, '2024-06-08 23:56:41', 'saveProduce', '0.0.0.0', 'cn.ibaochenyu.jzh_shop.controller.BasicController.save()', '{\"id\":1799470610332700674,\"produceDate\":857088000000,\"truthItemId\":111,\"truthWorkerId\":222,\"produceCount\":335}', 16);
INSERT INTO `t_sys_log` VALUES (1799470616900980738, '2024-06-08 23:56:42', '请求searchPageResult', '0.0.0.0', 'cn.ibaochenyu.jzh_shop.controller.BasicController.getOneProduceRtResult()', 'null', 33);
INSERT INTO `t_sys_log` VALUES (1799471558547390466, '2024-06-09 00:00:27', '请求searchPageResult', '0.0.0.0', 'cn.ibaochenyu.jzh_shop.controller.BasicController.getOneProduceRtResult()', 'null', 897);
INSERT INTO `t_sys_log` VALUES (1799471603023790082, '2024-06-09 00:00:37', 'saveProduce', '0.0.0.0', 'cn.ibaochenyu.jzh_shop.controller.BasicController.save()', '{\"id\":1799471602696634369,\"produceDate\":857088000000,\"truthItemId\":111,\"truthWorkerId\":222,\"produceCount\":334}', 62);
INSERT INTO `t_sys_log` VALUES (1799471609537544194, '2024-06-09 00:00:39', '请求searchPageResult', '0.0.0.0', 'cn.ibaochenyu.jzh_shop.controller.BasicController.getOneProduceRtResult()', 'null', 33);
INSERT INTO `t_sys_log` VALUES (1799471680320618499, '2024-06-09 00:00:56', 'saveProduce', '0.0.0.0', 'cn.ibaochenyu.jzh_shop.controller.BasicController.save()', '{\"id\":1799471680320618498,\"produceDate\":857088000000,\"truthItemId\":111,\"truthWorkerId\":222,\"produceCount\":333}', 16);
INSERT INTO `t_sys_log` VALUES (1799471686796623874, '2024-06-09 00:00:57', '请求searchPageResult', '0.0.0.0', 'cn.ibaochenyu.jzh_shop.controller.BasicController.getOneProduceRtResult()', 'null', 16);
INSERT INTO `t_sys_log` VALUES (1799617079966613505, '2024-06-09 09:38:42', 'saveProduce', '0.0.0.0', 'cn.ibaochenyu.jzh_shop.controller.BasicController.save()', '{\"id\":1799617079765286914,\"produceDate\":857088000000,\"truthItemId\":111,\"truthWorkerId\":222,\"produceCount\":333}', 48);
INSERT INTO `t_sys_log` VALUES (1799617086606196737, '2024-06-09 09:38:43', '请求searchPageResult', '0.0.0.0', 'cn.ibaochenyu.jzh_shop.controller.BasicController.getOneProduceRtResult()', 'null', 48);
INSERT INTO `t_sys_log` VALUES (1799625293395296257, '2024-06-09 10:11:20', '请求searchPageResult', '0.0.0.0', 'cn.ibaochenyu.jzh_shop.controller.BasicController.getOneProduceRtResult()', 'null', 438);
INSERT INTO `t_sys_log` VALUES (1799626853357617153, '2024-06-09 10:17:32', '请求searchPageResult', '0.0.0.0', 'cn.ibaochenyu.jzh_shop.controller.BasicController.getOneProduceRtResult()', 'null', 79);
INSERT INTO `t_sys_log` VALUES (1799627484302573570, '2024-06-09 10:20:02', '请求searchPageResult', '0.0.0.0', 'cn.ibaochenyu.jzh_shop.controller.BasicController.getOneProduceRtResult()', 'null', 203);
INSERT INTO `t_sys_log` VALUES (1799627614154031106, '2024-06-09 10:20:33', '请求searchPageResult', '0.0.0.0', 'cn.ibaochenyu.jzh_shop.controller.BasicController.getOneProduceRtResult()', 'null', 112);
INSERT INTO `t_sys_log` VALUES (1799627693204078593, '2024-06-09 10:20:52', 'saveProduce', '0.0.0.0', 'cn.ibaochenyu.jzh_shop.controller.BasicController.save()', '{\"id\":1799627693006946305,\"produceDate\":857088000000,\"truthItemId\":111,\"truthWorkerId\":222,\"produceCount\":336}', 31);
INSERT INTO `t_sys_log` VALUES (1799627705073958913, '2024-06-09 10:20:55', '请求searchPageResult', '0.0.0.0', 'cn.ibaochenyu.jzh_shop.controller.BasicController.getOneProduceRtResult()', 'null', 31);
INSERT INTO `t_sys_log` VALUES (1799628118451978242, '2024-06-09 10:22:34', '请求searchPageResult', '0.0.0.0', 'cn.ibaochenyu.jzh_shop.controller.BasicController.getOneProduceRtResult()', 'null', 66);
INSERT INTO `t_sys_log` VALUES (1799638165059997697, '2024-06-09 11:02:29', '请求searchPageResult', '0.0.0.0', 'cn.ibaochenyu.jzh_shop.controller.BasicController.getOneProduceRtResult()', 'null', 1692);
INSERT INTO `t_sys_log` VALUES (1799642769210429442, '2024-06-09 11:20:47', 'saveProduce', '0.0.0.0', 'cn.ibaochenyu.jzh_shop.controller.BasicController.save()', '{\"id\":1799642769143320577,\"produceDate\":857088000000,\"truthItemId\":111,\"truthWorkerId\":222,\"produceCount\":337}', 16);
INSERT INTO `t_sys_log` VALUES (1799642784343478274, '2024-06-09 11:20:50', '请求searchPageResult', '0.0.0.0', 'cn.ibaochenyu.jzh_shop.controller.BasicController.getOneProduceRtResult()', 'null', 269);
INSERT INTO `t_sys_log` VALUES (1799644480247386113, '2024-06-09 11:27:34', '请求searchPageResult', '0.0.0.0', 'cn.ibaochenyu.jzh_shop.controller.BasicController.getOneProduceRtResult()', 'null', 277);
INSERT INTO `t_sys_log` VALUES (1799644799324868609, '2024-06-09 11:28:51', '请求searchPageResult', '0.0.0.0', 'cn.ibaochenyu.jzh_shop.controller.BasicController.getOneProduceRtResult()', 'null', 31);
INSERT INTO `t_sys_log` VALUES (1799646657305071618, '2024-06-09 11:36:14', '请求searchPageResult', '0.0.0.0', 'cn.ibaochenyu.jzh_shop.controller.BasicController.getOneProduceRtResult()', 'null', 155);
INSERT INTO `t_sys_log` VALUES (1799649502955786242, '2024-06-09 11:47:32', '请求searchPageResult', '0.0.0.0', 'cn.ibaochenyu.jzh_shop.controller.BasicController.getOneProduceRtResult()', 'null', 1254);
INSERT INTO `t_sys_log` VALUES (1799650917656776705, '2024-06-09 11:53:09', '请求searchPageResult', '0.0.0.0', 'cn.ibaochenyu.jzh_shop.controller.BasicController.getOneProduceRtResult()', 'null', 139);
INSERT INTO `t_sys_log` VALUES (1799651791279968258, '2024-06-09 11:56:38', '请求searchPageResult', '0.0.0.0', 'cn.ibaochenyu.jzh_shop.controller.BasicController.getOneProduceRtResult()', 'null', 63);
INSERT INTO `t_sys_log` VALUES (1799681493059162113, '2024-06-09 13:54:39', '请求searchPageResult', '0.0.0.0', 'cn.ibaochenyu.jzh_shop.controller.BasicController.getOneProduceRtResult()', 'null', 1288);
INSERT INTO `t_sys_log` VALUES (1799681530925338626, '2024-06-09 13:54:48', 'saveProduce', '0.0.0.0', 'cn.ibaochenyu.jzh_shop.controller.BasicController.save()', '{\"id\":1799681530858229761,\"produceDate\":857088000000,\"truthItemId\":82008,\"truthWorkerId\":1,\"produceCount\":999}', 16);
INSERT INTO `t_sys_log` VALUES (1799681537460064257, '2024-06-09 13:54:50', '请求searchPageResult', '0.0.0.0', 'cn.ibaochenyu.jzh_shop.controller.BasicController.getOneProduceRtResult()', 'null', 34);
INSERT INTO `t_sys_log` VALUES (1799682285086388226, '2024-06-09 13:57:48', '请求searchPageResult', '0.0.0.0', 'cn.ibaochenyu.jzh_shop.controller.BasicController.getOneProduceRtResult()', 'null', 283);
INSERT INTO `t_sys_log` VALUES (1799682341206175745, '2024-06-09 13:58:01', 'saveProduce', '0.0.0.0', 'cn.ibaochenyu.jzh_shop.controller.BasicController.save()', '{\"produceId\":0,\"produceDate\":857088000000,\"truthItemId\":82022,\"truthWorkerId\":1,\"produceCount\":9911}', 16);
INSERT INTO `t_sys_log` VALUES (1799682347749289985, '2024-06-09 13:58:03', '请求searchPageResult', '0.0.0.0', 'cn.ibaochenyu.jzh_shop.controller.BasicController.getOneProduceRtResult()', 'null', 47);
INSERT INTO `t_sys_log` VALUES (1799682908590608385, '2024-06-09 14:00:17', '请求searchPageResult', '0.0.0.0', 'cn.ibaochenyu.jzh_shop.controller.BasicController.getOneProduceRtResult()', 'null', 1392);
INSERT INTO `t_sys_log` VALUES (1799684333857079298, '2024-06-09 14:05:56', '请求searchPageResult', '0.0.0.0', 'cn.ibaochenyu.jzh_shop.controller.BasicController.getOneProduceRtResult()', 'null', 1348);
INSERT INTO `t_sys_log` VALUES (1799684386927607810, '2024-06-09 14:06:09', 'saveProduce', '0.0.0.0', 'cn.ibaochenyu.jzh_shop.controller.BasicController.save()', '{\"id\":1799684386927607809,\"produceDate\":857088000000,\"truthItemId\":82008,\"truthWorkerId\":1,\"produceCount\":5544}', 0);
INSERT INTO `t_sys_log` VALUES (1799684393521053697, '2024-06-09 14:06:11', '请求searchPageResult', '0.0.0.0', 'cn.ibaochenyu.jzh_shop.controller.BasicController.getOneProduceRtResult()', 'null', 33);
INSERT INTO `t_sys_log` VALUES (1799684772367368194, '2024-06-09 14:07:41', '请求searchPageResult', '0.0.0.0', 'cn.ibaochenyu.jzh_shop.controller.BasicController.getOneProduceRtResult()', 'null', 33);
INSERT INTO `t_sys_log` VALUES (1799684946640699393, '2024-06-09 14:08:22', '请求searchPageResult', '0.0.0.0', 'cn.ibaochenyu.jzh_shop.controller.BasicController.getOneProduceRtResult()', 'null', 46);
INSERT INTO `t_sys_log` VALUES (1799687100675833858, '2024-06-09 14:16:56', '请求searchPageResult', '0.0.0.0', 'cn.ibaochenyu.jzh_shop.controller.BasicController.getOneProduceRtResult()', 'null', 4621);
INSERT INTO `t_sys_log` VALUES (1799687336102117377, '2024-06-09 14:17:52', '请求searchPageResult', '0.0.0.0', 'cn.ibaochenyu.jzh_shop.controller.BasicController.getOneProduceRtResult()', 'null', 65);
INSERT INTO `t_sys_log` VALUES (1799687448454938626, '2024-06-09 14:18:19', '请求searchPageResult', '0.0.0.0', 'cn.ibaochenyu.jzh_shop.controller.BasicController.getOneProduceRtResult()', 'null', 32);
INSERT INTO `t_sys_log` VALUES (1799687613710516225, '2024-06-09 14:18:58', 'updateProduce', '0.0.0.0', 'cn.ibaochenyu.jzh_shop.controller.BasicController.update()', '{\"id\":6,\"produceDate\":857088000000,\"truthItemId\":82008,\"truthWorkerId\":1,\"produceCount\":5545}', 16);
INSERT INTO `t_sys_log` VALUES (1799687620173938689, '2024-06-09 14:19:00', '请求searchPageResult', '0.0.0.0', 'cn.ibaochenyu.jzh_shop.controller.BasicController.getOneProduceRtResult()', 'null', 15);

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `id` bigint NOT NULL,
  `user_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user
-- ----------------------------

-- ----------------------------
-- Table structure for t_warehouse
-- ----------------------------
DROP TABLE IF EXISTS `t_warehouse`;
CREATE TABLE `t_warehouse`  (
  `id` bigint NOT NULL,
  `truth_factory_id` bigint NULL DEFAULT NULL,
  `truth_styler_id` bigint NULL DEFAULT NULL,
  `stock_count` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `工厂和样式独立索引`(`truth_factory_id`, `truth_styler_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_warehouse
-- ----------------------------
INSERT INTO `t_warehouse` VALUES (1800394395451592706, 12, 23, 145);
INSERT INTO `t_warehouse` VALUES (1800394552687661057, 12, 83009, 46);
INSERT INTO `t_warehouse` VALUES (1800398985274298369, 10, 23, 45);

-- ----------------------------
-- Table structure for t_worker
-- ----------------------------
DROP TABLE IF EXISTS `t_worker`;
CREATE TABLE `t_worker`  (
  `id` bigint NOT NULL,
  `show_worker_id` int NULL DEFAULT NULL,
  `worker_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_worker
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
