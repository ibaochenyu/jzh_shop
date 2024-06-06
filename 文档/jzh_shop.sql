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

 Date: 06/06/2024 20:43:37
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_basic
-- ----------------------------
DROP TABLE IF EXISTS `t_basic`;
CREATE TABLE `t_basic`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `work_id` int NULL DEFAULT NULL COMMENT 'workId',
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `sex` int NULL DEFAULT NULL,
  `home_address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'homeAddress',
  `date` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_basic
-- ----------------------------
INSERT INTO `t_basic` VALUES (1, 3, '黄小鑫', 1, '黄小鑫的家', '2024-06-06 19:00:40');
INSERT INTO `t_basic` VALUES (2, 6, '严小玲', 1, '严小玲的家', '2024-06-22 19:00:43');

-- ----------------------------
-- Table structure for t_item
-- ----------------------------
DROP TABLE IF EXISTS `t_item`;
CREATE TABLE `t_item`  (
  `id` bigint NOT NULL,
  `truth_item_id` int NULL DEFAULT NULL,
  `show_item_id` int NULL DEFAULT NULL,
  `item_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_item
-- ----------------------------

-- ----------------------------
-- Table structure for t_produce
-- ----------------------------
DROP TABLE IF EXISTS `t_produce`;
CREATE TABLE `t_produce`  (
  `id` bigint NOT NULL,
  `produce_date` datetime NULL DEFAULT NULL,
  `truth_item_id` int NULL DEFAULT NULL,
  `truth_worker_id` int NULL DEFAULT NULL,
  `produce_count` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_produce
-- ----------------------------
INSERT INTO `t_produce` VALUES (1, '2020-05-07 19:26:07', 82001, 1, 1);
INSERT INTO `t_produce` VALUES (5, '2020-05-07 19:26:07', 82002, 1, 2);

-- ----------------------------
-- Table structure for t_produce_salary
-- ----------------------------
DROP TABLE IF EXISTS `t_produce_salary`;
CREATE TABLE `t_produce_salary`  (
  `id` bigint NOT NULL,
  `date` datetime NULL DEFAULT NULL,
  `truth_item_id` int NULL DEFAULT NULL,
  `unit_price` decimal(10, 2) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_produce_salary
-- ----------------------------

-- ----------------------------
-- Table structure for t_worker
-- ----------------------------
DROP TABLE IF EXISTS `t_worker`;
CREATE TABLE `t_worker`  (
  `id` bigint NOT NULL,
  `truth_worker_id` int NULL DEFAULT NULL,
  `show_worker_id` int NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_worker
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
