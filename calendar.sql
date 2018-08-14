/*
 Navicat MySQL Data Transfer

 Source Server         : root
 Source Server Type    : MySQL
 Source Server Version : 50721
 Source Host           : localhost:3306
 Source Schema         : calendar

 Target Server Type    : MySQL
 Target Server Version : 50721
 File Encoding         : 65001

 Date: 14/08/2018 10:04:28
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for bluetooth
-- ----------------------------
DROP TABLE IF EXISTS `bluetooth`;
CREATE TABLE `bluetooth`  (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `date` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `school` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `bluetooth_brand` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `amount` int(10) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `blue_school`(`school`) USING BTREE,
  CONSTRAINT `blue_school` FOREIGN KEY (`school`) REFERENCES `school` (`school_alias`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of bluetooth
-- ----------------------------
INSERT INTO `bluetooth` VALUES (1, '2018-08-08', '卫校', '精伦', 40);
INSERT INTO `bluetooth` VALUES (2, '2018-08-06', '南洋', '森瑞', 40);
INSERT INTO `bluetooth` VALUES (3, '2018-08-08', '太湖', '森瑞', 80);
INSERT INTO `bluetooth` VALUES (4, '2018-08-25', '技师', '精伦', 40);
INSERT INTO `bluetooth` VALUES (5, '2018-08-08', '卫校', '森瑞', 60);
INSERT INTO `bluetooth` VALUES (6, '2018-08-06', '南洋', '森瑞', 80);
INSERT INTO `bluetooth` VALUES (8, '2018-06-06', '南洋', '精伦', 46);

-- ----------------------------
-- Table structure for calendar
-- ----------------------------
DROP TABLE IF EXISTS `calendar`;
CREATE TABLE `calendar`  (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `date` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `school` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `back_person` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `scene_person` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `student_predict` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `new_old` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `date`(`date`) USING BTREE,
  INDEX `cal_school`(`school`) USING BTREE,
  INDEX `cal_scene`(`scene_person`) USING BTREE,
  INDEX `cal_back`(`back_person`) USING BTREE,
  CONSTRAINT `cal_back` FOREIGN KEY (`back_person`) REFERENCES `worker` (`name`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `cal_scene` FOREIGN KEY (`scene_person`) REFERENCES `worker` (`name`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `cal_school` FOREIGN KEY (`school`) REFERENCES `school` (`school_alias`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 76 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of calendar
-- ----------------------------
INSERT INTO `calendar` VALUES (29, '2018-08-29', '江大', '唐淼', '陆俊琦', '5500', 'new');
INSERT INTO `calendar` VALUES (30, '2018-08-30', '江大', '唐淼', '浦希益', '5500', 'new');
INSERT INTO `calendar` VALUES (31, '2018-09-02', '卫校', '姚琳', '顾旭辉', '850', 'new');
INSERT INTO `calendar` VALUES (32, '2018-09-02', '机电', '姚琳', '秦王红', '800', 'new');
INSERT INTO `calendar` VALUES (33, '2018-09-03', '高技', '冯天从', '陆俊琦', '600', 'new');
INSERT INTO `calendar` VALUES (34, '2018-09-08', '电大', '李德利', '冯天从', '360', 'new');
INSERT INTO `calendar` VALUES (35, '2018-09-09', '电大', '顾旭辉', '浦希益', '360', 'new');
INSERT INTO `calendar` VALUES (36, '2018-09-03', '旅商', '冯天从', '秦王红', '1000', 'new');
INSERT INTO `calendar` VALUES (38, '2018-09-08', '梁溪职院', '李德利', '浦希益', '1200', 'new');
INSERT INTO `calendar` VALUES (40, '2018-09-09', '梁溪职院', '顾旭辉', '李德利', '1200', 'new');
INSERT INTO `calendar` VALUES (43, '2018-09-08', '滨湖职院', '李德利', '顾旭辉', '2500', 'new');
INSERT INTO `calendar` VALUES (44, '2018-09-09', '滨湖职院', '顾旭辉', '姚琳', '2500', 'new');
INSERT INTO `calendar` VALUES (45, '2018-09-02', '艺校', '姚琳', '唐淼', '100', 'new');
INSERT INTO `calendar` VALUES (46, '2018-09-08', '南洋', '李德利', '姚琳', '1200', 'new');
INSERT INTO `calendar` VALUES (47, '2018-09-09', '南洋', '顾旭辉', '秦王红', '1200', 'new');
INSERT INTO `calendar` VALUES (48, '2018-09-08', '太湖', '李德利', '秦王红', '4300', 'new');
INSERT INTO `calendar` VALUES (49, '2018-09-09', '太湖', '顾旭辉', '唐淼', '4300', 'new');
INSERT INTO `calendar` VALUES (50, '2018-09-09', '影视', '顾旭辉', '姚琳', '1920', 'new');
INSERT INTO `calendar` VALUES (51, '2018-09-08', '城市', '李德利', '顾旭辉', '1500', 'new');
INSERT INTO `calendar` VALUES (52, '2018-09-08', '信息', '李德利', '秦王红', '3150', 'new');
INSERT INTO `calendar` VALUES (53, '2018-08-25', '师范', '秦王红', '冯天从', '400', 'new');
INSERT INTO `calendar` VALUES (54, '2018-08-25', '技师', '秦王红', '陆俊琦', '850', 'new');
INSERT INTO `calendar` VALUES (55, '2018-09-14', '商校', '秦王红', '陆俊琦', '4100', 'new');
INSERT INTO `calendar` VALUES (56, '2018-09-10', '江阴职院', '秦王红', '冯天从', '1004', 'new');
INSERT INTO `calendar` VALUES (57, '2018-08-29', '中专', '唐淼', '浦希益', '800', 'new');
INSERT INTO `calendar` VALUES (58, '2018-08-30', '中专', '唐淼', '姚琳', '800', 'new');
INSERT INTO `calendar` VALUES (59, '2018-09-14', '工艺', '秦王红', '李德利', '2000', 'new');
INSERT INTO `calendar` VALUES (73, '2018-08-08', '太湖', '李德利', '冯天从', '1', 'new');
INSERT INTO `calendar` VALUES (74, '2018-08-06', '南洋', '秦王红', '李德利', '1', 'new');
INSERT INTO `calendar` VALUES (75, '2018-08-08', '卫校', '李德利', '浦希益', '1', 'new');

-- ----------------------------
-- Table structure for daynum
-- ----------------------------
DROP TABLE IF EXISTS `daynum`;
CREATE TABLE `daynum`  (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `date` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `total_num` int(10) NOT NULL COMMENT '当天发展总量',
  `xxb_num` int(10) NOT NULL COMMENT '行销宝发展总量',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2018225 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of daynum
-- ----------------------------
INSERT INTO `daynum` VALUES (1, '2018-07-27', 1100, 400);
INSERT INTO `daynum` VALUES (2, '2018-07-28', 900, 300);
INSERT INTO `daynum` VALUES (3, '2018-07-29', 600, 300);
INSERT INTO `daynum` VALUES (5, '2018-07-15', 100, 100);
INSERT INTO `daynum` VALUES (6, '2018-07-16', 200, 200);
INSERT INTO `daynum` VALUES (7, '2018-08-01', 300, 300);
INSERT INTO `daynum` VALUES (8, '2018-07-31', 400, 400);
INSERT INTO `daynum` VALUES (9, '2018-08-25', 500, 500);
INSERT INTO `daynum` VALUES (10, '2018-08-29', 600, 600);
INSERT INTO `daynum` VALUES (11, '2018-08-30', 700, 700);
INSERT INTO `daynum` VALUES (12, '2018-09-02', 800, 800);
INSERT INTO `daynum` VALUES (13, '2018-08-06', 700, 400);
INSERT INTO `daynum` VALUES (2018224, '2018-08-08', 900, 300);

-- ----------------------------
-- Table structure for oldstudent
-- ----------------------------
DROP TABLE IF EXISTS `oldstudent`;
CREATE TABLE `oldstudent`  (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `date` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `school` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `old_school`(`school`) USING BTREE,
  CONSTRAINT `old_school` FOREIGN KEY (`school`) REFERENCES `school` (`school_alias`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 30 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of oldstudent
-- ----------------------------
INSERT INTO `oldstudent` VALUES (1, '2018-09-02', '卫校');
INSERT INTO `oldstudent` VALUES (2, '2018-09-01', '江大');
INSERT INTO `oldstudent` VALUES (3, '2018-09-02', '机电');
INSERT INTO `oldstudent` VALUES (4, '2018-09-04', '高技');
INSERT INTO `oldstudent` VALUES (5, '2018-09-03', '旅商');
INSERT INTO `oldstudent` VALUES (6, '2018-09-01', '科院');
INSERT INTO `oldstudent` VALUES (7, '2018-09-09', '艺校');
INSERT INTO `oldstudent` VALUES (8, '2018-09-01', '太湖');
INSERT INTO `oldstudent` VALUES (10, '2018-09-01', '南洋');
INSERT INTO `oldstudent` VALUES (11, '2018-09-02', '汽车');
INSERT INTO `oldstudent` VALUES (12, '2018-09-02', '惠职');
INSERT INTO `oldstudent` VALUES (13, '2018-09-02', '技师');
INSERT INTO `oldstudent` VALUES (14, '2018-09-01', '信息');
INSERT INTO `oldstudent` VALUES (15, '2018-09-02', '师范');
INSERT INTO `oldstudent` VALUES (16, '2018-09-02', '工艺');
INSERT INTO `oldstudent` VALUES (17, '2018-09-03', '商校');
INSERT INTO `oldstudent` VALUES (18, '2018-09-02', '城市');
INSERT INTO `oldstudent` VALUES (19, '2018-09-02', '影视');
INSERT INTO `oldstudent` VALUES (20, '2018-08-29', '中专');
INSERT INTO `oldstudent` VALUES (21, '2018-08-30', '中专');
INSERT INTO `oldstudent` VALUES (22, '2018-09-02', '滨湖职院');
INSERT INTO `oldstudent` VALUES (23, '2018-09-02', '梁溪职院');
INSERT INTO `oldstudent` VALUES (24, '2018-09-08', '江阴职院');
INSERT INTO `oldstudent` VALUES (25, '2018-09-02', '太湖');
INSERT INTO `oldstudent` VALUES (26, '2018-09-02', '南洋');
INSERT INTO `oldstudent` VALUES (27, '2018-09-01', '梁溪职院');
INSERT INTO `oldstudent` VALUES (28, '2018-09-01', '滨湖职院');

-- ----------------------------
-- Table structure for property
-- ----------------------------
DROP TABLE IF EXISTS `property`;
CREATE TABLE `property`  (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `date` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `property` int(10) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of property
-- ----------------------------
INSERT INTO `property` VALUES (1, '2018-7-29', 1);
INSERT INTO `property` VALUES (2, '2018-7-30', 2);
INSERT INTO `property` VALUES (3, '2018-7-14', 3);
INSERT INTO `property` VALUES (4, '2018-7-8', 2);

-- ----------------------------
-- Table structure for school
-- ----------------------------
DROP TABLE IF EXISTS `school`;
CREATE TABLE `school`  (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `school_alias` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `name_2`(`name`) USING BTREE,
  INDEX `name`(`name`) USING BTREE,
  INDEX `school_alias`(`school_alias`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 37 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of school
-- ----------------------------
INSERT INTO `school` VALUES (1, '江南大学', '江大');
INSERT INTO `school` VALUES (2, '无锡市广播电视大学', '电大');
INSERT INTO `school` VALUES (3, '无锡卫生高等职业技术学校', '卫校');
INSERT INTO `school` VALUES (4, '无锡工业高级技工学校', '高技');
INSERT INTO `school` VALUES (5, '无锡市文化艺术学校', '艺校');
INSERT INTO `school` VALUES (11, '无锡科技职业学院', '科院');
INSERT INTO `school` VALUES (12, '无锡市科元技工学校', '科元');
INSERT INTO `school` VALUES (13, '无锡机电高等职业技术学校', '机电');
INSERT INTO `school` VALUES (15, '无锡旅游商贸高等职业学校', '旅商');
INSERT INTO `school` VALUES (16, '无锡职业技术学院', '滨湖职院');
INSERT INTO `school` VALUES (18, '无锡市曙光技工学校', '曙光');
INSERT INTO `school` VALUES (19, '无锡南洋职业技术学院', '南洋');
INSERT INTO `school` VALUES (20, '江苏省无锡交通高等职业技术学校', '交通');
INSERT INTO `school` VALUES (21, '太湖创意太湖学院', '太湖');
INSERT INTO `school` VALUES (23, '江南影视艺术学院', '影视');
INSERT INTO `school` VALUES (24, '无锡城市职业技术学院', '城市');
INSERT INTO `school` VALUES (25, '江苏信息职业技术学院', '信息');
INSERT INTO `school` VALUES (26, '无锡高等师范学校', '师范');
INSERT INTO `school` VALUES (27, '无锡技师学院', '技师');
INSERT INTO `school` VALUES (28, '无锡商业职业技术学院', '商校');
INSERT INTO `school` VALUES (29, '无锡汽车工程学校', '汽车');
INSERT INTO `school` VALUES (30, ' 惠山职业教育中心', '惠职');
INSERT INTO `school` VALUES (31, '江阴职业技术学院', '江阴职院');
INSERT INTO `school` VALUES (32, '江苏江阴中等专业学校', '中专');
INSERT INTO `school` VALUES (33, '无锡工艺职业技术学院', '工艺');
INSERT INTO `school` VALUES (34, '无锡职业技术学院梁溪校区', '梁溪职院');

-- ----------------------------
-- Table structure for worker
-- ----------------------------
DROP TABLE IF EXISTS `worker`;
CREATE TABLE `worker`  (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `phone` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `work_num` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `workunique`(`work_num`) USING BTREE,
  UNIQUE INDEX `name_2`(`name`) USING BTREE,
  INDEX `name`(`name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 80 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of worker
-- ----------------------------
INSERT INTO `worker` VALUES (1, '秦王红', '15306186231', '124');
INSERT INTO `worker` VALUES (2, '陆俊琦', '15306186261', '234');
INSERT INTO `worker` VALUES (3, '李德利', '15306188212', '345');
INSERT INTO `worker` VALUES (4, '冯天从', '15306188160', '456');
INSERT INTO `worker` VALUES (5, '姚琳', '15306188813', '567');
INSERT INTO `worker` VALUES (6, '唐淼', '15306188081', '678');
INSERT INTO `worker` VALUES (7, '顾旭辉', '13301515897', '789');
INSERT INTO `worker` VALUES (8, '浦希益', '18012390903', '890');

SET FOREIGN_KEY_CHECKS = 1;
