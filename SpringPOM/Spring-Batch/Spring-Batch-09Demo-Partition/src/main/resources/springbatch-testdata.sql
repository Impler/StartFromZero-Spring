/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50711
Source Host           : localhost:3306
Source Database       : springbatch

Target Server Type    : MYSQL
Target Server Version : 50711
File Encoding         : 65001

Date: 2019-07-27 16:07:06
*/
-- ----------------------------
-- Table structure for t_data_ext_status
-- ----------------------------
DROP TABLE IF EXISTS `t_data_ext_status`;
CREATE TABLE `t_data_ext_status` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `data_date` varchar(255) DEFAULT NULL COMMENT '数据日期',
  `assign_count` int(11) DEFAULT NULL COMMENT '已分配数量',
  `version` int(11) DEFAULT NULL COMMENT '版本号',
  `start_id` int(11) DEFAULT NULL COMMENT '起始编号',
  `end_id` int(11) DEFAULT NULL COMMENT '结束编号',
  `data_count` int(11) DEFAULT NULL COMMENT '数据总量',
  `last_data_id` int(11) DEFAULT NULL COMMENT '上一页最后一条数据编号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_data_ext_status
-- ----------------------------
INSERT INTO `t_data_ext_status` VALUES ('1', '2019-07-27', '0', '0', '1', '10', '10', null);

-- ----------------------------
-- Table structure for t_user_validate
-- ----------------------------
DROP TABLE IF EXISTS `t_user_validate`;
CREATE TABLE `t_user_validate` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(255) DEFAULT NULL COMMENT '姓名',
  `cert_no` varchar(255) DEFAULT NULL COMMENT '身份证号',
  `result_code` varchar(255) DEFAULT NULL COMMENT '结果码',
  `result_content` varchar(255) DEFAULT NULL COMMENT '结果描述',
  `deal_flag` int(11) DEFAULT NULL COMMENT '处理标识',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user_validate
-- ----------------------------
INSERT INTO `t_user_validate` VALUES ('1', '张三', '10001', null, null, null);
INSERT INTO `t_user_validate` VALUES ('2', '李四', '10002', null, null, null);
INSERT INTO `t_user_validate` VALUES ('3', '王五', '10003', null, null, null);
INSERT INTO `t_user_validate` VALUES ('4', '赵六', '10004', null, null, null);
INSERT INTO `t_user_validate` VALUES ('5', '刘欢', '10005', null, null, null);
INSERT INTO `t_user_validate` VALUES ('6', '汉姆斯', '10924', null, null, null);
INSERT INTO `t_user_validate` VALUES ('7', '刘备', '98727', null, null, null);
INSERT INTO `t_user_validate` VALUES ('8', '史努比', '29348', null, null, null);
INSERT INTO `t_user_validate` VALUES ('9', '刺猬', '83879', null, null, null);
INSERT INTO `t_user_validate` VALUES ('10', '七星瓢虫', '99099', null, null, null);
