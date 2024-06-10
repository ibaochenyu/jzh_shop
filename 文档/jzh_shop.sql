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

 Date: 10/06/2024 15:58:08
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
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `sex` int NULL DEFAULT NULL,
  `home_address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'homeAddress',
  `date` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1800048009185222714 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_basic
-- ----------------------------
INSERT INTO `t_basic` VALUES (1, 3, '黄小鑫', 1, '黄小鑫的家', '2024-06-06 19:00:40');
INSERT INTO `t_basic` VALUES (2, 60, '严小玲', 1, '严小玲的家', '2024-06-22 19:00:43');
INSERT INTO `t_basic` VALUES (1800048072305303553, 30, '测试SaveBatch', 0, NULL, NULL);
INSERT INTO `t_basic` VALUES (1800048072305303554, 31, '测试SaveBatch', 0, NULL, NULL);
INSERT INTO `t_basic` VALUES (1800048072305303555, 32, '测试SaveBatch', 0, NULL, NULL);
INSERT INTO `t_basic` VALUES (1800048072305303556, 33, '测试SaveBatch', 0, NULL, NULL);
INSERT INTO `t_basic` VALUES (1800048072305303557, 34, '测试SaveBatch', 0, NULL, NULL);
INSERT INTO `t_basic` VALUES (1800048072305303558, 35, '测试SaveBatch', 0, NULL, NULL);
INSERT INTO `t_basic` VALUES (1800048072305303559, 36, '测试SaveBatch', 0, NULL, NULL);
INSERT INTO `t_basic` VALUES (1800048072305303560, 37, '测试SaveBatch', 0, NULL, NULL);
INSERT INTO `t_basic` VALUES (1800048072305303561, 38, '测试SaveBatch', 0, NULL, NULL);
INSERT INTO `t_basic` VALUES (1800048072305303562, 39, '测试SaveBatch', 0, NULL, NULL);
INSERT INTO `t_basic` VALUES (1800048072305303563, 40, '测试SaveBatch', 0, NULL, NULL);
INSERT INTO `t_basic` VALUES (1800048072305303564, 41, '测试SaveBatch', 0, NULL, NULL);
INSERT INTO `t_basic` VALUES (1800048072305303565, 42, '测试SaveBatch', 0, NULL, NULL);
INSERT INTO `t_basic` VALUES (1800048072305303566, 43, '测试SaveBatch', 0, NULL, NULL);
INSERT INTO `t_basic` VALUES (1800048072305303567, 44, '测试SaveBatch', 0, NULL, NULL);
INSERT INTO `t_basic` VALUES (1800048072305303568, 45, '测试SaveBatch', 0, NULL, NULL);
INSERT INTO `t_basic` VALUES (1800048072305303569, 46, '测试SaveBatch', 0, NULL, NULL);
INSERT INTO `t_basic` VALUES (1800048072305303570, 47, '测试SaveBatch', 0, NULL, NULL);
INSERT INTO `t_basic` VALUES (1800048072305303571, 48, '测试SaveBatch', 0, NULL, NULL);
INSERT INTO `t_basic` VALUES (1800048072305303572, 49, '测试SaveBatch', 0, NULL, NULL);
INSERT INTO `t_basic` VALUES (1800048072305303573, 50, '测试SaveBatch', 0, NULL, NULL);
INSERT INTO `t_basic` VALUES (1800048072305303574, 51, '测试SaveBatch', 0, NULL, NULL);
INSERT INTO `t_basic` VALUES (1800048072305303575, 52, '测试SaveBatch', 0, NULL, NULL);
INSERT INTO `t_basic` VALUES (1800048072305303576, 53, '测试SaveBatch', 0, NULL, NULL);
INSERT INTO `t_basic` VALUES (1800048072305303577, 54, '测试SaveBatch', 0, NULL, NULL);
INSERT INTO `t_basic` VALUES (1800048072305303578, 55, '测试SaveBatch', 0, NULL, NULL);
INSERT INTO `t_basic` VALUES (1800048072305303579, 56, '测试SaveBatch', 0, NULL, NULL);
INSERT INTO `t_basic` VALUES (1800048072305303580, 57, '测试SaveBatch', 0, NULL, NULL);
INSERT INTO `t_basic` VALUES (1800048072305303581, 58, '测试SaveBatch', 0, NULL, NULL);
INSERT INTO `t_basic` VALUES (1800048072305303582, 59, '测试SaveBatch', 0, NULL, NULL);
INSERT INTO `t_basic` VALUES (1800048072305303583, 60, '测试SaveBatch', 0, NULL, NULL);
INSERT INTO `t_basic` VALUES (1800048072305303584, 61, '测试SaveBatch', 0, NULL, NULL);
INSERT INTO `t_basic` VALUES (1800048072305303585, 62, '测试SaveBatch', 0, NULL, NULL);
INSERT INTO `t_basic` VALUES (1800048072305303586, 63, '测试SaveBatch', 0, NULL, NULL);
INSERT INTO `t_basic` VALUES (1800048072305303587, 64, '测试SaveBatch', 0, NULL, NULL);
INSERT INTO `t_basic` VALUES (1800048072305303588, 65, '测试SaveBatch', 0, NULL, NULL);
INSERT INTO `t_basic` VALUES (1800048072305303589, 66, '测试SaveBatch', 0, NULL, NULL);
INSERT INTO `t_basic` VALUES (1800048072305303590, 67, '测试SaveBatch', 0, NULL, NULL);
INSERT INTO `t_basic` VALUES (1800048072305303591, 68, '测试SaveBatch', 0, NULL, NULL);
INSERT INTO `t_basic` VALUES (1800048072305303592, 69, '测试SaveBatch', 0, NULL, NULL);
INSERT INTO `t_basic` VALUES (1800048072305303593, 70, '测试SaveBatch', 0, NULL, NULL);
INSERT INTO `t_basic` VALUES (1800048072305303594, 71, '测试SaveBatch', 0, NULL, NULL);
INSERT INTO `t_basic` VALUES (1800048072305303595, 72, '测试SaveBatch', 0, NULL, NULL);
INSERT INTO `t_basic` VALUES (1800048072305303596, 73, '测试SaveBatch', 0, NULL, NULL);
INSERT INTO `t_basic` VALUES (1800048072305303597, 74, '测试SaveBatch', 0, NULL, NULL);
INSERT INTO `t_basic` VALUES (1800048072305303598, 75, '测试SaveBatch', 0, NULL, NULL);
INSERT INTO `t_basic` VALUES (1800048072305303599, 76, '测试SaveBatch', 0, NULL, NULL);
INSERT INTO `t_basic` VALUES (1800048072305303600, 77, '测试SaveBatch', 0, NULL, NULL);
INSERT INTO `t_basic` VALUES (1800048072305303601, 78, '测试SaveBatch', 0, NULL, NULL);
INSERT INTO `t_basic` VALUES (1800048072305303602, 79, '测试SaveBatch', 0, NULL, NULL);
INSERT INTO `t_basic` VALUES (1800048072305303603, 80, '测试SaveBatch', 0, NULL, NULL);
INSERT INTO `t_basic` VALUES (1800048072305303604, 81, '测试SaveBatch', 0, NULL, NULL);
INSERT INTO `t_basic` VALUES (1800048072305303605, 82, '测试SaveBatch', 0, NULL, NULL);
INSERT INTO `t_basic` VALUES (1800048072305303606, 83, '测试SaveBatch', 0, NULL, NULL);
INSERT INTO `t_basic` VALUES (1800048072305303607, 84, '测试SaveBatch', 0, NULL, NULL);
INSERT INTO `t_basic` VALUES (1800048072305303608, 85, '测试SaveBatch', 0, NULL, NULL);
INSERT INTO `t_basic` VALUES (1800048072305303609, 86, '测试SaveBatch', 0, NULL, NULL);
INSERT INTO `t_basic` VALUES (1800048072305303610, 87, '测试SaveBatch', 0, NULL, NULL);
INSERT INTO `t_basic` VALUES (1800048072305303611, 88, '测试SaveBatch', 0, NULL, NULL);
INSERT INTO `t_basic` VALUES (1800048072305303612, 89, '测试SaveBatch', 0, NULL, NULL);
INSERT INTO `t_basic` VALUES (1800048072305303613, 90, '测试SaveBatch', 0, NULL, NULL);
INSERT INTO `t_basic` VALUES (1800048072305303614, 91, '测试SaveBatch', 0, NULL, NULL);
INSERT INTO `t_basic` VALUES (1800048072305303615, 92, '测试SaveBatch', 0, NULL, NULL);
INSERT INTO `t_basic` VALUES (1800048072305303616, 93, '测试SaveBatch', 0, NULL, NULL);
INSERT INTO `t_basic` VALUES (1800048072305303617, 94, '测试SaveBatch', 0, NULL, NULL);
INSERT INTO `t_basic` VALUES (1800048072305303618, 95, '测试SaveBatch', 0, NULL, NULL);
INSERT INTO `t_basic` VALUES (1800048072305303619, 96, '测试SaveBatch', 0, NULL, NULL);
INSERT INTO `t_basic` VALUES (1800051629771386881, 30, '测试SaveBatch', 0, NULL, NULL);
INSERT INTO `t_basic` VALUES (1800051629771386882, 31, '测试SaveBatch', 0, NULL, NULL);
INSERT INTO `t_basic` VALUES (1800051629771386883, 32, '测试SaveBatch', 0, NULL, NULL);
INSERT INTO `t_basic` VALUES (1800051629771386884, 33, '测试SaveBatch', 0, NULL, NULL);
INSERT INTO `t_basic` VALUES (1800051629771386885, 34, '测试SaveBatch', 0, NULL, NULL);
INSERT INTO `t_basic` VALUES (1800051629771386886, 35, '测试SaveBatch', 0, NULL, NULL);
INSERT INTO `t_basic` VALUES (1800051629771386887, 36, '测试SaveBatch', 0, NULL, NULL);
INSERT INTO `t_basic` VALUES (1800051629771386888, 37, '测试SaveBatch', 0, NULL, NULL);
INSERT INTO `t_basic` VALUES (1800051629834301441, 38, '测试SaveBatch', 0, NULL, NULL);
INSERT INTO `t_basic` VALUES (1800051629834301442, 39, '测试SaveBatch', 0, NULL, NULL);
INSERT INTO `t_basic` VALUES (1800051629834301443, 40, '测试SaveBatch', 0, NULL, NULL);
INSERT INTO `t_basic` VALUES (1800051629834301444, 41, '测试SaveBatch', 0, NULL, NULL);
INSERT INTO `t_basic` VALUES (1800051629834301445, 42, '测试SaveBatch', 0, NULL, NULL);
INSERT INTO `t_basic` VALUES (1800051629834301446, 43, '测试SaveBatch', 0, NULL, NULL);
INSERT INTO `t_basic` VALUES (1800051629834301447, 44, '测试SaveBatch', 0, NULL, NULL);
INSERT INTO `t_basic` VALUES (1800051629834301448, 45, '测试SaveBatch', 0, NULL, NULL);
INSERT INTO `t_basic` VALUES (1800051629834301449, 46, '测试SaveBatch', 0, NULL, NULL);
INSERT INTO `t_basic` VALUES (1800051629834301450, 47, '测试SaveBatch', 0, NULL, NULL);
INSERT INTO `t_basic` VALUES (1800051629834301451, 48, '测试SaveBatch', 0, NULL, NULL);
INSERT INTO `t_basic` VALUES (1800051629834301452, 49, '测试SaveBatch', 0, NULL, NULL);
INSERT INTO `t_basic` VALUES (1800051629834301453, 50, '测试SaveBatch', 0, NULL, NULL);
INSERT INTO `t_basic` VALUES (1800051629834301454, 51, '测试SaveBatch', 0, NULL, NULL);
INSERT INTO `t_basic` VALUES (1800051629834301455, 52, '测试SaveBatch', 0, NULL, NULL);
INSERT INTO `t_basic` VALUES (1800051629834301456, 53, '测试SaveBatch', 0, NULL, NULL);
INSERT INTO `t_basic` VALUES (1800051629834301457, 54, '测试SaveBatch', 0, NULL, NULL);
INSERT INTO `t_basic` VALUES (1800051629834301458, 55, '测试SaveBatch', 0, NULL, NULL);
INSERT INTO `t_basic` VALUES (1800051629834301459, 56, '测试SaveBatch', 0, NULL, NULL);
INSERT INTO `t_basic` VALUES (1800051629834301460, 57, '测试SaveBatch', 0, NULL, NULL);
INSERT INTO `t_basic` VALUES (1800051629834301461, 58, '测试SaveBatch', 0, NULL, NULL);
INSERT INTO `t_basic` VALUES (1800051629834301462, 59, '测试SaveBatch', 0, NULL, NULL);
INSERT INTO `t_basic` VALUES (1800051629834301463, 60, '测试SaveBatch', 0, NULL, NULL);
INSERT INTO `t_basic` VALUES (1800051629834301464, 61, '测试SaveBatch', 0, NULL, NULL);
INSERT INTO `t_basic` VALUES (1800051629834301465, 62, '测试SaveBatch', 0, NULL, NULL);
INSERT INTO `t_basic` VALUES (1800051629834301466, 63, '测试SaveBatch', 0, NULL, NULL);
INSERT INTO `t_basic` VALUES (1800051629834301467, 64, '测试SaveBatch', 0, NULL, NULL);
INSERT INTO `t_basic` VALUES (1800051629834301468, 65, '测试SaveBatch', 0, NULL, NULL);
INSERT INTO `t_basic` VALUES (1800051629834301469, 66, '测试SaveBatch', 0, NULL, NULL);
INSERT INTO `t_basic` VALUES (1800051629834301470, 67, '测试SaveBatch', 0, NULL, NULL);
INSERT INTO `t_basic` VALUES (1800051629834301471, 68, '测试SaveBatch', 0, NULL, NULL);
INSERT INTO `t_basic` VALUES (1800051629834301472, 69, '测试SaveBatch', 0, NULL, NULL);
INSERT INTO `t_basic` VALUES (1800051629834301473, 70, '测试SaveBatch', 0, NULL, NULL);
INSERT INTO `t_basic` VALUES (1800051629834301474, 71, '测试SaveBatch', 0, NULL, NULL);
INSERT INTO `t_basic` VALUES (1800051629834301475, 72, '测试SaveBatch', 0, NULL, NULL);
INSERT INTO `t_basic` VALUES (1800051629834301476, 73, '测试SaveBatch', 0, NULL, NULL);
INSERT INTO `t_basic` VALUES (1800051629901410305, 74, '测试SaveBatch', 0, NULL, NULL);
INSERT INTO `t_basic` VALUES (1800051629901410306, 75, '测试SaveBatch', 0, NULL, NULL);
INSERT INTO `t_basic` VALUES (1800051629901410307, 76, '测试SaveBatch', 0, NULL, NULL);
INSERT INTO `t_basic` VALUES (1800051629901410308, 77, '测试SaveBatch', 0, NULL, NULL);
INSERT INTO `t_basic` VALUES (1800051629901410309, 78, '测试SaveBatch', 0, NULL, NULL);
INSERT INTO `t_basic` VALUES (1800051629901410310, 79, '测试SaveBatch', 0, NULL, NULL);
INSERT INTO `t_basic` VALUES (1800051629901410311, 80, '测试SaveBatch', 0, NULL, NULL);
INSERT INTO `t_basic` VALUES (1800051629901410312, 81, '测试SaveBatch', 0, NULL, NULL);
INSERT INTO `t_basic` VALUES (1800051629901410313, 82, '测试SaveBatch', 0, NULL, NULL);
INSERT INTO `t_basic` VALUES (1800051629901410314, 83, '测试SaveBatch', 0, NULL, NULL);
INSERT INTO `t_basic` VALUES (1800059497807499266, 30, '测试SaveBatch', 0, NULL, NULL);
INSERT INTO `t_basic` VALUES (1800059497807499267, 31, '测试SaveBatch', 0, NULL, NULL);
INSERT INTO `t_basic` VALUES (1800059497807499268, 32, '测试SaveBatch', 0, NULL, NULL);
INSERT INTO `t_basic` VALUES (1800059497807499269, 33, '测试SaveBatch', 0, NULL, NULL);
INSERT INTO `t_basic` VALUES (1800059497874608130, 34, '测试SaveBatch', 0, NULL, NULL);
INSERT INTO `t_basic` VALUES (1800059497874608131, 35, '测试SaveBatch', 0, NULL, NULL);
INSERT INTO `t_basic` VALUES (1800059497874608132, 36, '测试SaveBatch', 0, NULL, NULL);
INSERT INTO `t_basic` VALUES (1800059497874608133, 37, '测试SaveBatch', 0, NULL, NULL);
INSERT INTO `t_basic` VALUES (1800059497874608134, 38, '测试SaveBatch', 0, NULL, NULL);
INSERT INTO `t_basic` VALUES (1800059497874608135, 39, '测试SaveBatch', 0, NULL, NULL);
INSERT INTO `t_basic` VALUES (1800059497874608136, 40, '测试SaveBatch', 0, NULL, NULL);
INSERT INTO `t_basic` VALUES (1800059497874608137, 41, '测试SaveBatch', 0, NULL, NULL);
INSERT INTO `t_basic` VALUES (1800059497874608138, 42, '测试SaveBatch', 0, NULL, NULL);
INSERT INTO `t_basic` VALUES (1800059497874608139, 43, '测试SaveBatch', 0, NULL, NULL);
INSERT INTO `t_basic` VALUES (1800059497874608140, 44, '测试SaveBatch', 0, NULL, NULL);
INSERT INTO `t_basic` VALUES (1800059497874608141, 45, '测试SaveBatch', 0, NULL, NULL);
INSERT INTO `t_basic` VALUES (1800059497874608142, 46, '测试SaveBatch', 0, NULL, NULL);
INSERT INTO `t_basic` VALUES (1800059497874608143, 47, '测试SaveBatch', 0, NULL, NULL);
INSERT INTO `t_basic` VALUES (1800059497874608144, 48, '测试SaveBatch', 0, NULL, NULL);
INSERT INTO `t_basic` VALUES (1800059497874608145, 49, '测试SaveBatch', 0, NULL, NULL);
INSERT INTO `t_basic` VALUES (1800059497874608146, 50, '测试SaveBatch', 0, NULL, NULL);
INSERT INTO `t_basic` VALUES (1800059497874608147, 51, '测试SaveBatch', 0, NULL, NULL);
INSERT INTO `t_basic` VALUES (1800059497874608148, 52, '测试SaveBatch', 0, NULL, NULL);
INSERT INTO `t_basic` VALUES (1800059497874608149, 53, '测试SaveBatch', 0, NULL, NULL);
INSERT INTO `t_basic` VALUES (1800059497874608150, 54, '测试SaveBatch', 0, NULL, NULL);
INSERT INTO `t_basic` VALUES (1800059497874608151, 55, '测试SaveBatch', 0, NULL, NULL);
INSERT INTO `t_basic` VALUES (1800059497874608152, 56, '测试SaveBatch', 0, NULL, NULL);
INSERT INTO `t_basic` VALUES (1800059497874608153, 57, '测试SaveBatch', 0, NULL, NULL);
INSERT INTO `t_basic` VALUES (1800059497874608154, 58, '测试SaveBatch', 0, NULL, NULL);
INSERT INTO `t_basic` VALUES (1800059497874608155, 59, '测试SaveBatch', 0, NULL, NULL);
INSERT INTO `t_basic` VALUES (1800059497937522689, 60, '测试SaveBatch', 0, NULL, NULL);
INSERT INTO `t_basic` VALUES (1800059497937522690, 61, '测试SaveBatch', 0, NULL, NULL);
INSERT INTO `t_basic` VALUES (1800059497937522691, 62, '测试SaveBatch', 0, NULL, NULL);
INSERT INTO `t_basic` VALUES (1800059497937522692, 63, '测试SaveBatch', 0, NULL, NULL);
INSERT INTO `t_basic` VALUES (1800059497937522693, 64, '测试SaveBatch', 0, NULL, NULL);
INSERT INTO `t_basic` VALUES (1800059497937522694, 65, '测试SaveBatch', 0, NULL, NULL);
INSERT INTO `t_basic` VALUES (1800059497937522695, 66, '测试SaveBatch', 0, NULL, NULL);
INSERT INTO `t_basic` VALUES (1800059497937522696, 67, '测试SaveBatch', 0, NULL, NULL);
INSERT INTO `t_basic` VALUES (1800059497937522697, 68, '测试SaveBatch', 0, NULL, NULL);
INSERT INTO `t_basic` VALUES (1800059497937522698, 69, '测试SaveBatch', 0, NULL, NULL);
INSERT INTO `t_basic` VALUES (1800059497937522699, 70, '测试SaveBatch', 0, NULL, NULL);
INSERT INTO `t_basic` VALUES (1800059497937522700, 71, '测试SaveBatch', 0, NULL, NULL);
INSERT INTO `t_basic` VALUES (1800059497937522701, 72, '测试SaveBatch', 0, NULL, NULL);
INSERT INTO `t_basic` VALUES (1800059497937522702, 73, '测试SaveBatch', 0, NULL, NULL);
INSERT INTO `t_basic` VALUES (1800059497937522703, 74, '测试SaveBatch', 0, NULL, NULL);
INSERT INTO `t_basic` VALUES (1800059497937522704, 75, '测试SaveBatch', 0, NULL, NULL);
INSERT INTO `t_basic` VALUES (1800059497937522705, 76, '测试SaveBatch', 0, NULL, NULL);
INSERT INTO `t_basic` VALUES (1800059497937522706, 77, '测试SaveBatch', 0, NULL, NULL);
INSERT INTO `t_basic` VALUES (1800059497937522707, 78, '测试SaveBatch', 0, NULL, NULL);
INSERT INTO `t_basic` VALUES (1800059497937522708, 79, '测试SaveBatch', 0, NULL, NULL);
INSERT INTO `t_basic` VALUES (1800059497937522709, 80, '测试SaveBatch', 0, NULL, NULL);
INSERT INTO `t_basic` VALUES (1800059497937522710, 81, '测试SaveBatch', 0, NULL, NULL);
INSERT INTO `t_basic` VALUES (1800059497937522711, 82, '测试SaveBatch', 0, NULL, NULL);
INSERT INTO `t_basic` VALUES (1800059497937522712, 83, '测试SaveBatch', 0, NULL, NULL);
INSERT INTO `t_basic` VALUES (1800059497937522713, 84, '测试SaveBatch', 0, NULL, NULL);
INSERT INTO `t_basic` VALUES (1800059497937522714, 85, '测试SaveBatch', 0, NULL, NULL);
INSERT INTO `t_basic` VALUES (1800059497937522715, 86, '测试SaveBatch', 0, NULL, NULL);
INSERT INTO `t_basic` VALUES (1800059497937522716, 87, '测试SaveBatch', 0, NULL, NULL);
INSERT INTO `t_basic` VALUES (1800059497937522717, 88, '测试SaveBatch', 0, NULL, NULL);
INSERT INTO `t_basic` VALUES (1800059497937522718, 89, '测试SaveBatch', 0, NULL, NULL);
INSERT INTO `t_basic` VALUES (1800059497937522719, 90, '测试SaveBatch', 0, NULL, NULL);
INSERT INTO `t_basic` VALUES (1800059497937522720, 91, '测试SaveBatch', 0, NULL, NULL);
INSERT INTO `t_basic` VALUES (1800059497937522721, 92, '测试SaveBatch', 0, NULL, NULL);
INSERT INTO `t_basic` VALUES (1800059497937522722, 93, '测试SaveBatch', 0, NULL, NULL);
INSERT INTO `t_basic` VALUES (1800059497937522723, 94, '测试SaveBatch', 0, NULL, NULL);
INSERT INTO `t_basic` VALUES (1800059497937522724, 95, '测试SaveBatch', 0, NULL, NULL);
INSERT INTO `t_basic` VALUES (1800059497937522725, 96, '测试SaveBatch', 0, NULL, NULL);
INSERT INTO `t_basic` VALUES (1800059497937522726, 97, '测试SaveBatch', 0, NULL, NULL);
INSERT INTO `t_basic` VALUES (1800059498000437250, 98, '测试SaveBatch', 0, NULL, NULL);
INSERT INTO `t_basic` VALUES (1800059498000437251, 99, '测试SaveBatch', 0, NULL, NULL);
INSERT INTO `t_basic` VALUES (1800059498000437252, 100, '测试SaveBatch', 0, NULL, NULL);
INSERT INTO `t_basic` VALUES (1800059498000437253, 101, '测试SaveBatch', 0, NULL, NULL);
INSERT INTO `t_basic` VALUES (1800059498000437254, 102, '测试SaveBatch', 0, NULL, NULL);
INSERT INTO `t_basic` VALUES (1800059498000437255, 103, '测试SaveBatch', 0, NULL, NULL);
INSERT INTO `t_basic` VALUES (1800059498000437256, 104, '测试SaveBatch', 0, NULL, NULL);
INSERT INTO `t_basic` VALUES (1800059498000437257, 105, '测试SaveBatch', 0, NULL, NULL);
INSERT INTO `t_basic` VALUES (1800059498000437258, 106, '测试SaveBatch', 0, NULL, NULL);

-- ----------------------------
-- Table structure for t_commodity
-- ----------------------------
DROP TABLE IF EXISTS `t_commodity`;
CREATE TABLE `t_commodity`  (
  `id` bigint NOT NULL,
  `truth_styler_id` bigint NULL DEFAULT NULL,
  `commodity_status` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_commodity
-- ----------------------------
INSERT INTO `t_commodity` VALUES (1800059497388068865, 82003, 0);
INSERT INTO `t_commodity` VALUES (1800059497409040386, 82003, 0);
INSERT INTO `t_commodity` VALUES (1800059497409040387, 82003, 1);
INSERT INTO `t_commodity` VALUES (1800059497409040388, 82003, 1);
INSERT INTO `t_commodity` VALUES (1800059497409040389, 82003, 0);
INSERT INTO `t_commodity` VALUES (1800059497409040390, 82003, 0);
INSERT INTO `t_commodity` VALUES (1800059497409040391, 82003, 0);
INSERT INTO `t_commodity` VALUES (1800059497409040392, 82003, 0);
INSERT INTO `t_commodity` VALUES (1800059497409040393, 82003, 0);
INSERT INTO `t_commodity` VALUES (1800059497409040394, 82003, 0);
INSERT INTO `t_commodity` VALUES (1800059497409040395, 82003, 0);
INSERT INTO `t_commodity` VALUES (1800059497409040396, 82003, 0);
INSERT INTO `t_commodity` VALUES (1800059497409040397, 82003, 0);
INSERT INTO `t_commodity` VALUES (1800059497409040398, 82003, 0);
INSERT INTO `t_commodity` VALUES (1800059497409040399, 82003, 0);
INSERT INTO `t_commodity` VALUES (1800059497409040400, 82003, 0);
INSERT INTO `t_commodity` VALUES (1800059497476149249, 82003, 0);
INSERT INTO `t_commodity` VALUES (1800059497476149250, 82003, 0);
INSERT INTO `t_commodity` VALUES (1800059497476149251, 82003, 0);
INSERT INTO `t_commodity` VALUES (1800059497476149252, 82003, 0);
INSERT INTO `t_commodity` VALUES (1800059497476149253, 82003, 0);
INSERT INTO `t_commodity` VALUES (1800059497476149254, 82003, 0);
INSERT INTO `t_commodity` VALUES (1800059497476149255, 82003, 0);
INSERT INTO `t_commodity` VALUES (1800059497476149256, 82003, 0);
INSERT INTO `t_commodity` VALUES (1800059497476149257, 82003, 0);
INSERT INTO `t_commodity` VALUES (1800059497476149258, 82003, 0);
INSERT INTO `t_commodity` VALUES (1800059497476149259, 82003, 0);
INSERT INTO `t_commodity` VALUES (1800059497476149260, 82003, 0);
INSERT INTO `t_commodity` VALUES (1800059497476149261, 82003, 0);
INSERT INTO `t_commodity` VALUES (1800059497476149262, 82003, 0);
INSERT INTO `t_commodity` VALUES (1800059497476149263, 82003, 0);
INSERT INTO `t_commodity` VALUES (1800059497476149264, 82003, 0);
INSERT INTO `t_commodity` VALUES (1800059497476149265, 82003, 0);
INSERT INTO `t_commodity` VALUES (1800059497476149266, 82003, 0);
INSERT INTO `t_commodity` VALUES (1800059497476149267, 82003, 0);
INSERT INTO `t_commodity` VALUES (1800059497476149268, 82003, 0);
INSERT INTO `t_commodity` VALUES (1800059497476149269, 82003, 0);
INSERT INTO `t_commodity` VALUES (1800059497476149270, 82003, 0);
INSERT INTO `t_commodity` VALUES (1800059497476149271, 82003, 0);
INSERT INTO `t_commodity` VALUES (1800059497476149272, 82003, 0);
INSERT INTO `t_commodity` VALUES (1800059497476149273, 82003, 0);
INSERT INTO `t_commodity` VALUES (1800059497476149274, 82003, 0);
INSERT INTO `t_commodity` VALUES (1800059497476149275, 82003, 0);
INSERT INTO `t_commodity` VALUES (1800059497476149276, 82003, 0);
INSERT INTO `t_commodity` VALUES (1800059497476149277, 82003, 0);
INSERT INTO `t_commodity` VALUES (1800059497476149278, 82003, 0);
INSERT INTO `t_commodity` VALUES (1800059497476149279, 82003, 0);
INSERT INTO `t_commodity` VALUES (1800059497543258114, 82003, 0);
INSERT INTO `t_commodity` VALUES (1800059497543258115, 82003, 0);
INSERT INTO `t_commodity` VALUES (1800059497543258116, 82003, 0);
INSERT INTO `t_commodity` VALUES (1800059497543258117, 82003, 0);
INSERT INTO `t_commodity` VALUES (1800059497543258118, 82003, 0);
INSERT INTO `t_commodity` VALUES (1800059497543258119, 82003, 0);
INSERT INTO `t_commodity` VALUES (1800059497543258120, 82003, 0);
INSERT INTO `t_commodity` VALUES (1800059497543258121, 82003, 0);
INSERT INTO `t_commodity` VALUES (1800059497543258122, 82003, 0);
INSERT INTO `t_commodity` VALUES (1800059497543258123, 82003, 0);
INSERT INTO `t_commodity` VALUES (1800059497543258124, 82003, 0);
INSERT INTO `t_commodity` VALUES (1800059497543258125, 82003, 0);
INSERT INTO `t_commodity` VALUES (1800059497543258126, 82003, 0);
INSERT INTO `t_commodity` VALUES (1800059497543258127, 82003, 0);
INSERT INTO `t_commodity` VALUES (1800059497543258128, 82003, 0);
INSERT INTO `t_commodity` VALUES (1800059497543258129, 82003, 0);
INSERT INTO `t_commodity` VALUES (1800059497543258130, 82003, 0);
INSERT INTO `t_commodity` VALUES (1800059497543258131, 82003, 0);
INSERT INTO `t_commodity` VALUES (1800059497543258132, 82003, 0);
INSERT INTO `t_commodity` VALUES (1800059497543258133, 82003, 0);
INSERT INTO `t_commodity` VALUES (1800059497543258134, 82003, 0);
INSERT INTO `t_commodity` VALUES (1800059497543258135, 82003, 0);
INSERT INTO `t_commodity` VALUES (1800059497543258136, 82003, 0);
INSERT INTO `t_commodity` VALUES (1800059497543258137, 82003, 0);
INSERT INTO `t_commodity` VALUES (1800059497543258138, 82003, 0);
INSERT INTO `t_commodity` VALUES (1800059497543258139, 82003, 0);
INSERT INTO `t_commodity` VALUES (1800059497543258140, 82003, 0);
INSERT INTO `t_commodity` VALUES (1800059497543258141, 82003, 0);
INSERT INTO `t_commodity` VALUES (1800059497543258142, 82003, 0);
INSERT INTO `t_commodity` VALUES (1800059497543258143, 82003, 0);

-- ----------------------------
-- Table structure for t_produce
-- ----------------------------
DROP TABLE IF EXISTS `t_produce`;
CREATE TABLE `t_produce`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT,
  `produce_date` datetime NULL DEFAULT NULL,
  `truth_styler_id` bigint NULL DEFAULT NULL,
  `truth_worker_id` bigint NULL DEFAULT NULL,
  `produce_count` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1800045968870264834 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_produce
-- ----------------------------
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
INSERT INTO `t_produce` VALUES (1800010261636210690, '1111-11-11 08:00:00', 3, 5, 6);
INSERT INTO `t_produce` VALUES (1800011652597440514, '1111-11-11 08:00:00', 12, 3, 34);
INSERT INTO `t_produce` VALUES (1800016439346868225, '1111-11-11 08:00:00', 55, 55, 55);
INSERT INTO `t_produce` VALUES (1800017008325177346, '1111-11-11 08:00:00', 22, 22, 22);
INSERT INTO `t_produce` VALUES (1800017127699263489, '1111-11-11 08:00:00', 33, 33, 33);
INSERT INTO `t_produce` VALUES (1800017348630032386, '1111-11-11 08:00:00', 4, 4, 4);
INSERT INTO `t_produce` VALUES (1800017869076049921, '1111-11-11 08:00:00', 5, 5, 5);
INSERT INTO `t_produce` VALUES (1800018511072997378, '1111-11-11 08:00:00', 9, 9, 9);
INSERT INTO `t_produce` VALUES (1800019066021359618, '1122-12-12 08:00:00', 55, 55, 55);
INSERT INTO `t_produce` VALUES (1800024516846596098, '2222-11-11 08:00:00', 3, 3, 3);
INSERT INTO `t_produce` VALUES (1800029821668044802, '1111-11-11 08:00:00', 2324, 23, 234);
INSERT INTO `t_produce` VALUES (1800034130245910530, '1997-11-11 08:00:00', 666, 1, 12);
INSERT INTO `t_produce` VALUES (1800034397066559490, '1999-11-11 08:00:00', 82001, 1, 12);
INSERT INTO `t_produce` VALUES (1800034420764377089, '1999-11-11 08:00:00', 82001, 1, 12);
INSERT INTO `t_produce` VALUES (1800034579158073346, '1999-11-11 08:00:00', 82001, 1, 12);
INSERT INTO `t_produce` VALUES (1800034706149015553, '1999-11-11 08:00:00', 82001, 12, 12);
INSERT INTO `t_produce` VALUES (1800035003097350145, '1999-11-11 08:00:00', 82001, 123, 12);
INSERT INTO `t_produce` VALUES (1800038231876739073, '2023-01-01 08:00:00', 82999, 1, 12);
INSERT INTO `t_produce` VALUES (1800040107376586754, '2023-01-01 08:00:00', 82999, 2, 12);
INSERT INTO `t_produce` VALUES (1800041143843676161, '1992-11-11 08:00:00', 82001, 1, 12);
INSERT INTO `t_produce` VALUES (1800041231903088642, '1992-11-11 08:00:00', 82001, 3, 12);
INSERT INTO `t_produce` VALUES (1800041736502951937, '1992-11-11 08:00:00', 82002, 3, 12);
INSERT INTO `t_produce` VALUES (1800041984877035521, '1992-11-11 08:00:00', 82002, 4, 9);
INSERT INTO `t_produce` VALUES (1800044259871727618, '1999-11-11 08:00:00', 82003, 7979, 12);
INSERT INTO `t_produce` VALUES (1800045432230039553, '1998-11-12 08:00:00', 82001, 4455, 100);
INSERT INTO `t_produce` VALUES (1800045968870264833, '1998-11-12 08:00:00', 82001, 4455, 99);
INSERT INTO `t_produce` VALUES (1800048072045256706, '2024-06-01 08:00:00', 83009, 14, 67);
INSERT INTO `t_produce` VALUES (1800051629247098881, '2005-01-01 08:00:00', 89999, 22, 54);
INSERT INTO `t_produce` VALUES (1800059491469905921, '1997-02-21 08:00:00', 82003, 23, 77);

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
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

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
  `styler_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

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
-- Table structure for t_worker
-- ----------------------------
DROP TABLE IF EXISTS `t_worker`;
CREATE TABLE `t_worker`  (
  `id` bigint NOT NULL,
  `show_worker_id` int NULL DEFAULT NULL,
  `worker_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_worker
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
