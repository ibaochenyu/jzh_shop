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

 Date: 18/06/2024 16:01:51
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_warehouse
-- ----------------------------
DROP TABLE IF EXISTS `t_warehouse`;
CREATE TABLE `t_warehouse`  (
  `id` bigint NOT NULL,
  `truth_factory_id` bigint NULL DEFAULT NULL,
  `truth_styler_id` bigint NULL DEFAULT NULL,
  `stock_count` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_warehouse
-- ----------------------------
INSERT INTO `t_warehouse` VALUES (1800394395451592706, 1, 82001, 145);
INSERT INTO `t_warehouse` VALUES (1800394395451592707, 1, 82002, 23);
INSERT INTO `t_warehouse` VALUES (1800394395451592708, 1, 82003, 77);
INSERT INTO `t_warehouse` VALUES (1800394552687661057, 2, 82001, 46);
INSERT INTO `t_warehouse` VALUES (1800398985274298369, 2, 82002, 45);
INSERT INTO `t_warehouse` VALUES (1800398985274298370, 2, 82003, 56);
INSERT INTO `t_warehouse` VALUES (1800398985274298371, 3, 82001, 32);
INSERT INTO `t_warehouse` VALUES (1800398985274298372, 3, 82002, 88);
INSERT INTO `t_warehouse` VALUES (1800398985274298373, 3, 82003, 185972);
INSERT INTO `t_warehouse` VALUES (1800398985274298374, 4, 82001, 11);

SET FOREIGN_KEY_CHECKS = 1;
