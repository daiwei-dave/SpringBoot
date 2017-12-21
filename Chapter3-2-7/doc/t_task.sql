/*
Navicat MySQL Data Transfer

Source Server         : 10.112.101.161
Source Server Version : 50621
Source Host           : 10.112.101.161:3306
Source Database       : gome_store_analysis

Target Server Type    : MYSQL
Target Server Version : 50621
File Encoding         : 65001

Date: 2017-12-20 17:41:06
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_task
-- ----------------------------
DROP TABLE IF EXISTS `t_task`;
CREATE TABLE `t_task` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `branch_no` char(20) NOT NULL COMMENT '分部代码',
  `branch_name` char(50) NOT NULL COMMENT '分部名称',
  `task_no` varchar(20) DEFAULT NULL COMMENT '任务代码',
  `task_name` varchar(20) DEFAULT NULL COMMENT '任务名称',
  `branch_total` double DEFAULT NULL COMMENT '任务总计',
  `task_type` char(1) NOT NULL COMMENT '任务类型;0:阶段性任务，1:月度性任务',
  `start_date` varchar(50) DEFAULT NULL COMMENT '开始日期',
  `end_date` varchar(50) DEFAULT NULL COMMENT '结束日期',
  PRIMARY KEY (`id`),
  UNIQUE KEY `branch_no` (`branch_no`,`task_type`,`start_date`,`end_date`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8 COMMENT='任务表';

-- ----------------------------
-- Records of t_task
-- ----------------------------
INSERT INTO `t_task` VALUES ('8', 'HN01', '河南分部', '4', '任务test', '101.89', '1', '20171201', '20171231');
INSERT INTO `t_task` VALUES ('28', 'HB00', '河北分部', 'S3GZ8GLD', '12月任务', '101.89', '1', '20171201', '20171231');
INSERT INTO `t_task` VALUES ('30', 'HB00', '河北分部', 'kpTKQ8YC', '11月test', '101.89', '0', '20161102', '20161102');
INSERT INTO `t_task` VALUES ('39', 'HB00', '河北分部', 'sajxe1VI', '任务test', '101.89', '0', '20171202', '20171210');
INSERT INTO `t_task` VALUES ('40', 'HB00', '河北分部', 'qZMXpeFa', '任务test', '101.89', '0', '20171225', '20171228');
INSERT INTO `t_task` VALUES ('43', 'HB00', '河北分部', 'zskEKWbc', '任务test', '101.89', '1', '20171101', '20171130');
INSERT INTO `t_task` VALUES ('45', 'HB00', '河北分部', '4JoBs8Ox', 'test', '100.34', '1', '20170901', '20170930');
INSERT INTO `t_task` VALUES ('46', 'HB00', '河北分部', '2IYfSwGi', 'test', '100.34', '1', '20170801', '20170831');
INSERT INTO `t_task` VALUES ('47', 'HB00', '河北分部', '89q3lu0t', 'test', '100.34', '1', '20170701', '20170731');
