/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : springboot

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2017-12-20 17:14:25
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_sale_task
-- ----------------------------
DROP TABLE IF EXISTS `t_sale_task`;
CREATE TABLE `t_sale_task` (
  `task_id` varchar(20) NOT NULL COMMENT '任务id',
  `task_name` varchar(20) NOT NULL COMMENT '任务名称',
  `branch_total` double NOT NULL COMMENT '分部总计',
  `store_name` varchar(50) NOT NULL COMMENT '门店名称',
  `store_id` varchar(15) NOT NULL COMMENT '门店id',
  `store_sale` double DEFAULT NULL COMMENT '门店销售任务'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='销售任务';

-- ----------------------------
-- Records of t_sale_task
-- ----------------------------
INSERT INTO `t_sale_task` VALUES ('1', '9月任务', '100.54', '河北世纪商城', 'A20A', '0');
INSERT INTO `t_sale_task` VALUES ('1', '9月任务', '100.54', '河北空中花园店', 'A21T', '0');
INSERT INTO `t_sale_task` VALUES ('1', '9月任务', '100.54', '河北世纪商城保定望都店', 'A22N', '0');
INSERT INTO `t_sale_task` VALUES ('2', '10月任务', '100.53', '河北世纪商城', 'A20A', '20.5');
INSERT INTO `t_sale_task` VALUES ('2', '10月任务', '100.53', '河北空中花园店', 'A21T', '20.6');
INSERT INTO `t_sale_task` VALUES ('2', '10???', '100.53', '??????', 'A20A', '20.5');
INSERT INTO `t_sale_task` VALUES ('2', '10???', '100.53', '???????', 'A21T', '20.6');
INSERT INTO `t_sale_task` VALUES ('2', '10???', '100.53', '??????', 'A20A', '20.5');
INSERT INTO `t_sale_task` VALUES ('2', '10???', '100.53', '???????', 'A21T', '20.6');
INSERT INTO `t_sale_task` VALUES ('2', '10???', '100.53', '??????', 'A20A', '20.5');
INSERT INTO `t_sale_task` VALUES ('2', '10???', '100.53', '???????', 'A21T', '20.6');
