/*
 Navicat Premium Data Transfer

 Source Server         : 腾讯云Mysql
 Source Server Type    : MySQL
 Source Server Version : 50718
 Source Host           : db.wtu.hll520.cn:10105
 Source Schema         : test

 Target Server Type    : MySQL
 Target Server Version : 50718
 File Encoding         : 65001

 Date: 19/12/2020 12:01:35
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for SignIn
-- ----------------------------
DROP TABLE IF EXISTS `SignIn`;
CREATE TABLE `SignIn`
(
    `_id`  int(11)                                                       NOT NULL AUTO_INCREMENT,
    `id`   varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL,
    `date` date                                                          NOT NULL,
    `time` datetime(0)                                                   NOT NULL,
    `ip`   varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    PRIMARY KEY (`_id`, `date`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 4593
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for Student
-- ----------------------------
DROP TABLE IF EXISTS `Student`;
CREATE TABLE `Student`
(
    `id`         varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL,
    `name`       varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
    `college`    varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `vocational` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `className`  varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for log
-- ----------------------------
DROP TABLE IF EXISTS `log`;
CREATE TABLE `log`
(
    `_id`   int(11)                                                       NOT NULL AUTO_INCREMENT,
    `id`    varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL DEFAULT NULL,
    `time`  datetime(0)                                                   NULL DEFAULT NULL,
    `event` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `ip`    varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    PRIMARY KEY (`_id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 478
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Function structure for get_avg
-- ----------------------------
DROP FUNCTION IF EXISTS `get_avg`;
delimiter ;;
CREATE FUNCTION `get_avg`(`@stu_id` int)
    RETURNS float
BEGIN
    DECLARE avg FLOAT;
    SELECT COUNT(id) into avg FROM SignIn WHERE id = `@stu_id`;
    SET avg = avg * 100.0 / 24;
    RETURN avg;
END
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table SignIn
-- ----------------------------
DROP TRIGGER IF EXISTS `log`;
delimiter ;;
CREATE TRIGGER `log`
    AFTER INSERT
    ON `SignIn`
    FOR EACH ROW
BEGIN
    INSERT INTO log(id, time, event, ip) values (new.id, new.time, "签到成功", new.ip);
END
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table SignIn
-- ----------------------------
DROP TRIGGER IF EXISTS `log_up`;
delimiter ;;
CREATE TRIGGER `log_up`
    AFTER UPDATE
    ON `SignIn`
    FOR EACH ROW
BEGIN
    INSERT INTO log(id, time, event, ip)
    values (new.id, new.time, concat("更新了签到！原签到IP", old.ip, "原签到时间为", old.time), new.ip);
END
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
