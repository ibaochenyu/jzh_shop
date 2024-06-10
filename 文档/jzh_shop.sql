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

 Date: 09/06/2024 14:20:55
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
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_basic
-- ----------------------------
INSERT INTO `t_basic` VALUES (1, 3, '黄小鑫', 1, '黄小鑫的家', '2024-06-06 19:00:40');
INSERT INTO `t_basic` VALUES (2, 6, '严小玲', 1, '严小玲的家', '2024-06-22 19:00:43');

-- ----------------------------
-- Table structure for t_produce_template
-- ----------------------------
DROP TABLE IF EXISTS `t_produce_template`;
CREATE TABLE `t_produce_template`  (
  `id` bigint NOT NULL,
  `truth_template_id` int NULL DEFAULT NULL,
  `show_template_id` int NULL DEFAULT NULL,
  `item_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_produce_template
-- ----------------------------

-- ----------------------------
-- Table structure for t_produce
-- ----------------------------
DROP TABLE IF EXISTS `t_produce`;
CREATE TABLE `t_produce`  (
  `id` bigint NOT NULL,
  `produce_date` datetime NULL DEFAULT NULL,
  `truth_template_id` int NULL DEFAULT NULL,
  `truth_worker_id` int NULL DEFAULT NULL,
  `produce_count` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `测试冲突`(`truth_template_id`, `truth_worker_id`, `produce_count`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_produce
-- ----------------------------
INSERT INTO `t_produce` VALUES (6, '1997-02-28 08:00:00', 82008, 1, 5545);
INSERT INTO `t_produce` VALUES (7, '1900-01-20 00:00:00', 82008, 1, 5);
INSERT INTO `t_produce` VALUES (8, '1900-01-20 00:00:00', 82009, 1, 6);
INSERT INTO `t_produce` VALUES (9, '2020-05-07 19:26:07', 82002, 1, 7);
INSERT INTO `t_produce` VALUES (10, '1900-01-20 00:00:00', 82012, 1, 8);
INSERT INTO `t_produce` VALUES (11, '1900-01-20 00:00:00', 82013, 1, 9);
INSERT INTO `t_produce` VALUES (12, '1900-01-20 00:00:00', 82015, 1, 10);
INSERT INTO `t_produce` VALUES (14, '1900-01-20 00:00:00', 82017, 301, 11);
INSERT INTO `t_produce` VALUES (15, '1900-01-20 00:00:00', 82017, 301, 12);
INSERT INTO `t_produce` VALUES (16, '1900-01-20 00:00:00', 82011, 301, 13);
INSERT INTO `t_produce` VALUES (17, '1900-01-20 00:00:00', 82011, 301, 23);
INSERT INTO `t_produce` VALUES (18, '1900-01-20 00:00:00', 82011, 301, 43);
INSERT INTO `t_produce` VALUES (19, '1900-01-20 00:00:00', 82011, 301, 24);
INSERT INTO `t_produce` VALUES (20, '1900-01-20 00:00:00', 82018, 301, 23);
INSERT INTO `t_produce` VALUES (24, '1900-01-20 00:00:00', 82015, 1, 12);
INSERT INTO `t_produce` VALUES (25, '1900-01-20 00:00:00', 82013, 1, 34);
INSERT INTO `t_produce` VALUES (26, '1900-01-20 00:00:00', 82002, 2, 12);
INSERT INTO `t_produce` VALUES (56, '2020-05-07 19:26:07', 82002, 1, 2);
INSERT INTO `t_produce` VALUES (89, '1900-01-20 00:00:00', 82005, 1, 3);
INSERT INTO `t_produce` VALUES (100, '2020-05-07 19:26:07', 82001, 1, 1);
INSERT INTO `t_produce` VALUES (1799354986466029570, '1997-02-28 08:00:00', 111, 222, 333);
INSERT INTO `t_produce` VALUES (1799459742207311873, '1997-02-28 08:00:00', 111, 222, 334);
INSERT INTO `t_produce` VALUES (1799470610332700674, '1997-02-28 08:00:00', 111, 222, 335);
INSERT INTO `t_produce` VALUES (1799627693006946305, '1997-02-28 08:00:00', 111, 222, 336);
INSERT INTO `t_produce` VALUES (1799642769143320577, '1997-02-28 08:00:00', 111, 222, 337);
INSERT INTO `t_produce` VALUES (1799681530858229761, '1997-02-28 08:00:00', 82008, 1, 999);
INSERT INTO `t_produce` VALUES (1799684386927607809, '1997-02-28 08:00:00', 82008, 1, 5544);

-- ----------------------------
-- Table structure for t_produce_salary
-- ----------------------------
DROP TABLE IF EXISTS `t_produce_salary`;
CREATE TABLE `t_produce_salary`  (
  `id` bigint NOT NULL,
  `date` datetime NULL DEFAULT NULL,
  `truth_template_id` int NULL DEFAULT NULL,
  `unit_price` decimal(10, 2) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_produce_salary
-- ----------------------------

-- ----------------------------
-- Table structure for t_sys_log
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_log`;
CREATE TABLE `t_sys_log`  (
  `id` bigint NOT NULL,
  `date` datetime NULL DEFAULT NULL,
  `comment` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `ip` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `operator_class` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `operator_argv` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `duration` bigint NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_sys_log
-- ----------------------------
INSERT INTO `t_sys_log` VALUES (1799458949680988162, '2024-06-08 00:00:00', '请求searchPageResult', '0.0.0.0', 'cn.ibaochenyu.jzh_shop.controller.BasicController.getOneProduceRtResult()', 'null', 9296);
INSERT INTO `t_sys_log` VALUES (1799458999433822210, '2024-06-08 00:00:00', '请求searchPageResult', '0.0.0.0', 'cn.ibaochenyu.jzh_shop.controller.BasicController.getOneProduceRtResult()', 'null', 31);
INSERT INTO `t_sys_log` VALUES (1799459026281562113, '2024-06-08 00:00:00', '请求searchPageResult', '0.0.0.0', 'cn.ibaochenyu.jzh_shop.controller.BasicController.getOneProduceRtResult()', 'null', 32);
INSERT INTO `t_sys_log` VALUES (1799459530361405441, '2024-06-08 23:12:39', '请求searchPageResult', '0.0.0.0', 'cn.ibaochenyu.jzh_shop.controller.BasicController.getOneProduceRtResult()', 'null', 31);
INSERT INTO `t_sys_log` VALUES (1799459769860358145, '2024-06-08 23:13:30', 'saveProduce', '0.0.0.0', 'cn.ibaochenyu.jzh_shop.controller.BasicController.save()', '{\"id\":1799459742207311873,\"produceDate\":857088000000,\"truthTemplateId\":111,\"truthWorkerId\":222,\"produceCount\":334}', 0);
INSERT INTO `t_sys_log` VALUES (1799459785865822210, '2024-06-08 23:13:38', '请求searchPageResult', '0.0.0.0', 'cn.ibaochenyu.jzh_shop.controller.BasicController.getOneProduceRtResult()', 'null', 31);
INSERT INTO `t_sys_log` VALUES (1799468216119074817, '2024-06-08 23:47:10', '请求searchPageResult', '0.0.0.0', 'cn.ibaochenyu.jzh_shop.controller.BasicController.getOneProduceRtResult()', 'null', 1311);
INSERT INTO `t_sys_log` VALUES (1799468796740771841, '2024-06-08 23:49:28', '请求searchPageResult', '0.0.0.0', 'cn.ibaochenyu.jzh_shop.controller.BasicController.getOneProduceRtResult()', 'null', 1367);
INSERT INTO `t_sys_log` VALUES (1799469646448054273, '2024-06-08 23:52:51', '请求searchPageResult', '0.0.0.0', 'cn.ibaochenyu.jzh_shop.controller.BasicController.getOneProduceRtResult()', 'null', 1046);
INSERT INTO `t_sys_log` VALUES (1799469880817373185, '2024-06-08 23:53:47', '请求searchPageResult', '0.0.0.0', 'cn.ibaochenyu.jzh_shop.controller.BasicController.getOneProduceRtResult()', 'null', 33);
INSERT INTO `t_sys_log` VALUES (1799469885355610114, '2024-06-08 23:53:48', '请求searchPageResult', '0.0.0.0', 'cn.ibaochenyu.jzh_shop.controller.BasicController.getOneProduceRtResult()', 'null', 16);
INSERT INTO `t_sys_log` VALUES (1799470472344293377, '2024-06-08 23:56:08', '请求searchPageResult', '0.0.0.0', 'cn.ibaochenyu.jzh_shop.controller.BasicController.getOneProduceRtResult()', 'null', 1252);
INSERT INTO `t_sys_log` VALUES (1799470610399809538, '2024-06-08 23:56:41', 'saveProduce', '0.0.0.0', 'cn.ibaochenyu.jzh_shop.controller.BasicController.save()', '{\"id\":1799470610332700674,\"produceDate\":857088000000,\"truthTemplateId\":111,\"truthWorkerId\":222,\"produceCount\":335}', 16);
INSERT INTO `t_sys_log` VALUES (1799470616900980738, '2024-06-08 23:56:42', '请求searchPageResult', '0.0.0.0', 'cn.ibaochenyu.jzh_shop.controller.BasicController.getOneProduceRtResult()', 'null', 33);
INSERT INTO `t_sys_log` VALUES (1799471558547390466, '2024-06-09 00:00:27', '请求searchPageResult', '0.0.0.0', 'cn.ibaochenyu.jzh_shop.controller.BasicController.getOneProduceRtResult()', 'null', 897);
INSERT INTO `t_sys_log` VALUES (1799471603023790082, '2024-06-09 00:00:37', 'saveProduce', '0.0.0.0', 'cn.ibaochenyu.jzh_shop.controller.BasicController.save()', '{\"id\":1799471602696634369,\"produceDate\":857088000000,\"truthTemplateId\":111,\"truthWorkerId\":222,\"produceCount\":334}', 62);
INSERT INTO `t_sys_log` VALUES (1799471609537544194, '2024-06-09 00:00:39', '请求searchPageResult', '0.0.0.0', 'cn.ibaochenyu.jzh_shop.controller.BasicController.getOneProduceRtResult()', 'null', 33);
INSERT INTO `t_sys_log` VALUES (1799471680320618499, '2024-06-09 00:00:56', 'saveProduce', '0.0.0.0', 'cn.ibaochenyu.jzh_shop.controller.BasicController.save()', '{\"id\":1799471680320618498,\"produceDate\":857088000000,\"truthTemplateId\":111,\"truthWorkerId\":222,\"produceCount\":333}', 16);
INSERT INTO `t_sys_log` VALUES (1799471686796623874, '2024-06-09 00:00:57', '请求searchPageResult', '0.0.0.0', 'cn.ibaochenyu.jzh_shop.controller.BasicController.getOneProduceRtResult()', 'null', 16);
INSERT INTO `t_sys_log` VALUES (1799617079966613505, '2024-06-09 09:38:42', 'saveProduce', '0.0.0.0', 'cn.ibaochenyu.jzh_shop.controller.BasicController.save()', '{\"id\":1799617079765286914,\"produceDate\":857088000000,\"truthTemplateId\":111,\"truthWorkerId\":222,\"produceCount\":333}', 48);
INSERT INTO `t_sys_log` VALUES (1799617086606196737, '2024-06-09 09:38:43', '请求searchPageResult', '0.0.0.0', 'cn.ibaochenyu.jzh_shop.controller.BasicController.getOneProduceRtResult()', 'null', 48);
INSERT INTO `t_sys_log` VALUES (1799625293395296257, '2024-06-09 10:11:20', '请求searchPageResult', '0.0.0.0', 'cn.ibaochenyu.jzh_shop.controller.BasicController.getOneProduceRtResult()', 'null', 438);
INSERT INTO `t_sys_log` VALUES (1799626853357617153, '2024-06-09 10:17:32', '请求searchPageResult', '0.0.0.0', 'cn.ibaochenyu.jzh_shop.controller.BasicController.getOneProduceRtResult()', 'null', 79);
INSERT INTO `t_sys_log` VALUES (1799627484302573570, '2024-06-09 10:20:02', '请求searchPageResult', '0.0.0.0', 'cn.ibaochenyu.jzh_shop.controller.BasicController.getOneProduceRtResult()', 'null', 203);
INSERT INTO `t_sys_log` VALUES (1799627614154031106, '2024-06-09 10:20:33', '请求searchPageResult', '0.0.0.0', 'cn.ibaochenyu.jzh_shop.controller.BasicController.getOneProduceRtResult()', 'null', 112);
INSERT INTO `t_sys_log` VALUES (1799627693204078593, '2024-06-09 10:20:52', 'saveProduce', '0.0.0.0', 'cn.ibaochenyu.jzh_shop.controller.BasicController.save()', '{\"id\":1799627693006946305,\"produceDate\":857088000000,\"truthTemplateId\":111,\"truthWorkerId\":222,\"produceCount\":336}', 31);
INSERT INTO `t_sys_log` VALUES (1799627705073958913, '2024-06-09 10:20:55', '请求searchPageResult', '0.0.0.0', 'cn.ibaochenyu.jzh_shop.controller.BasicController.getOneProduceRtResult()', 'null', 31);
INSERT INTO `t_sys_log` VALUES (1799628118451978242, '2024-06-09 10:22:34', '请求searchPageResult', '0.0.0.0', 'cn.ibaochenyu.jzh_shop.controller.BasicController.getOneProduceRtResult()', 'null', 66);
INSERT INTO `t_sys_log` VALUES (1799638165059997697, '2024-06-09 11:02:29', '请求searchPageResult', '0.0.0.0', 'cn.ibaochenyu.jzh_shop.controller.BasicController.getOneProduceRtResult()', 'null', 1692);
INSERT INTO `t_sys_log` VALUES (1799642769210429442, '2024-06-09 11:20:47', 'saveProduce', '0.0.0.0', 'cn.ibaochenyu.jzh_shop.controller.BasicController.save()', '{\"id\":1799642769143320577,\"produceDate\":857088000000,\"truthTemplateId\":111,\"truthWorkerId\":222,\"produceCount\":337}', 16);
INSERT INTO `t_sys_log` VALUES (1799642784343478274, '2024-06-09 11:20:50', '请求searchPageResult', '0.0.0.0', 'cn.ibaochenyu.jzh_shop.controller.BasicController.getOneProduceRtResult()', 'null', 269);
INSERT INTO `t_sys_log` VALUES (1799644480247386113, '2024-06-09 11:27:34', '请求searchPageResult', '0.0.0.0', 'cn.ibaochenyu.jzh_shop.controller.BasicController.getOneProduceRtResult()', 'null', 277);
INSERT INTO `t_sys_log` VALUES (1799644799324868609, '2024-06-09 11:28:51', '请求searchPageResult', '0.0.0.0', 'cn.ibaochenyu.jzh_shop.controller.BasicController.getOneProduceRtResult()', 'null', 31);
INSERT INTO `t_sys_log` VALUES (1799646657305071618, '2024-06-09 11:36:14', '请求searchPageResult', '0.0.0.0', 'cn.ibaochenyu.jzh_shop.controller.BasicController.getOneProduceRtResult()', 'null', 155);
INSERT INTO `t_sys_log` VALUES (1799649502955786242, '2024-06-09 11:47:32', '请求searchPageResult', '0.0.0.0', 'cn.ibaochenyu.jzh_shop.controller.BasicController.getOneProduceRtResult()', 'null', 1254);
INSERT INTO `t_sys_log` VALUES (1799650917656776705, '2024-06-09 11:53:09', '请求searchPageResult', '0.0.0.0', 'cn.ibaochenyu.jzh_shop.controller.BasicController.getOneProduceRtResult()', 'null', 139);
INSERT INTO `t_sys_log` VALUES (1799651791279968258, '2024-06-09 11:56:38', '请求searchPageResult', '0.0.0.0', 'cn.ibaochenyu.jzh_shop.controller.BasicController.getOneProduceRtResult()', 'null', 63);
INSERT INTO `t_sys_log` VALUES (1799681493059162113, '2024-06-09 13:54:39', '请求searchPageResult', '0.0.0.0', 'cn.ibaochenyu.jzh_shop.controller.BasicController.getOneProduceRtResult()', 'null', 1288);
INSERT INTO `t_sys_log` VALUES (1799681530925338626, '2024-06-09 13:54:48', 'saveProduce', '0.0.0.0', 'cn.ibaochenyu.jzh_shop.controller.BasicController.save()', '{\"id\":1799681530858229761,\"produceDate\":857088000000,\"truthTemplateId\":82008,\"truthWorkerId\":1,\"produceCount\":999}', 16);
INSERT INTO `t_sys_log` VALUES (1799681537460064257, '2024-06-09 13:54:50', '请求searchPageResult', '0.0.0.0', 'cn.ibaochenyu.jzh_shop.controller.BasicController.getOneProduceRtResult()', 'null', 34);
INSERT INTO `t_sys_log` VALUES (1799682285086388226, '2024-06-09 13:57:48', '请求searchPageResult', '0.0.0.0', 'cn.ibaochenyu.jzh_shop.controller.BasicController.getOneProduceRtResult()', 'null', 283);
INSERT INTO `t_sys_log` VALUES (1799682341206175745, '2024-06-09 13:58:01', 'saveProduce', '0.0.0.0', 'cn.ibaochenyu.jzh_shop.controller.BasicController.save()', '{\"produceId\":0,\"produceDate\":857088000000,\"truthTemplateId\":82022,\"truthWorkerId\":1,\"produceCount\":9911}', 16);
INSERT INTO `t_sys_log` VALUES (1799682347749289985, '2024-06-09 13:58:03', '请求searchPageResult', '0.0.0.0', 'cn.ibaochenyu.jzh_shop.controller.BasicController.getOneProduceRtResult()', 'null', 47);
INSERT INTO `t_sys_log` VALUES (1799682908590608385, '2024-06-09 14:00:17', '请求searchPageResult', '0.0.0.0', 'cn.ibaochenyu.jzh_shop.controller.BasicController.getOneProduceRtResult()', 'null', 1392);
INSERT INTO `t_sys_log` VALUES (1799684333857079298, '2024-06-09 14:05:56', '请求searchPageResult', '0.0.0.0', 'cn.ibaochenyu.jzh_shop.controller.BasicController.getOneProduceRtResult()', 'null', 1348);
INSERT INTO `t_sys_log` VALUES (1799684386927607810, '2024-06-09 14:06:09', 'saveProduce', '0.0.0.0', 'cn.ibaochenyu.jzh_shop.controller.BasicController.save()', '{\"id\":1799684386927607809,\"produceDate\":857088000000,\"truthTemplateId\":82008,\"truthWorkerId\":1,\"produceCount\":5544}', 0);
INSERT INTO `t_sys_log` VALUES (1799684393521053697, '2024-06-09 14:06:11', '请求searchPageResult', '0.0.0.0', 'cn.ibaochenyu.jzh_shop.controller.BasicController.getOneProduceRtResult()', 'null', 33);
INSERT INTO `t_sys_log` VALUES (1799684772367368194, '2024-06-09 14:07:41', '请求searchPageResult', '0.0.0.0', 'cn.ibaochenyu.jzh_shop.controller.BasicController.getOneProduceRtResult()', 'null', 33);
INSERT INTO `t_sys_log` VALUES (1799684946640699393, '2024-06-09 14:08:22', '请求searchPageResult', '0.0.0.0', 'cn.ibaochenyu.jzh_shop.controller.BasicController.getOneProduceRtResult()', 'null', 46);
INSERT INTO `t_sys_log` VALUES (1799687100675833858, '2024-06-09 14:16:56', '请求searchPageResult', '0.0.0.0', 'cn.ibaochenyu.jzh_shop.controller.BasicController.getOneProduceRtResult()', 'null', 4621);
INSERT INTO `t_sys_log` VALUES (1799687336102117377, '2024-06-09 14:17:52', '请求searchPageResult', '0.0.0.0', 'cn.ibaochenyu.jzh_shop.controller.BasicController.getOneProduceRtResult()', 'null', 65);
INSERT INTO `t_sys_log` VALUES (1799687448454938626, '2024-06-09 14:18:19', '请求searchPageResult', '0.0.0.0', 'cn.ibaochenyu.jzh_shop.controller.BasicController.getOneProduceRtResult()', 'null', 32);
INSERT INTO `t_sys_log` VALUES (1799687613710516225, '2024-06-09 14:18:58', 'updateProduce', '0.0.0.0', 'cn.ibaochenyu.jzh_shop.controller.BasicController.update()', '{\"id\":6,\"produceDate\":857088000000,\"truthTemplateId\":82008,\"truthWorkerId\":1,\"produceCount\":5545}', 16);
INSERT INTO `t_sys_log` VALUES (1799687620173938689, '2024-06-09 14:19:00', '请求searchPageResult', '0.0.0.0', 'cn.ibaochenyu.jzh_shop.controller.BasicController.getOneProduceRtResult()', 'null', 15);

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
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_worker
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
