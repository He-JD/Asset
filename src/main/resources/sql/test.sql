/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50713
Source Host           : 127.0.0.1:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50713
File Encoding         : 65001

Date: 2018-10-01 11:26:49:55
*/

DROP DATABASE  IF EXISTS `test`;
CREATE DATABASE test;
USE test;

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- demo test table
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(20) DEFAULT NULL,
  `pass_word` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log` (
  `id` bigint(50) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `type` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '请求类型',
  `title` varchar(255) COLLATE utf8_bin DEFAULT '' COMMENT '日志标题',
  `remote_addr` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '操作IP地址',
  `username` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '操作用户昵称',
  `request_uri` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '请求URI',
  `http_method` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '操作方式',
  `class_method` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '请求类型.方法',
  `params` text COLLATE utf8_bin COMMENT '操作提交的数据',
  `session_id` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT 'sessionId',
  `response` longtext COLLATE utf8_bin COMMENT '返回内容',
  `use_time` bigint(11) DEFAULT NULL COMMENT '方法执行时间',
  `browser` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '浏览器信息',
  `area` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '地区',
  `province` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '省',
  `city` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '市',
  `isp` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '网络服务提供商',
  `exception` text COLLATE utf8_bin COMMENT '异常信息',
  `create_by` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '创建者',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint(64) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `remarks` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `del_flag` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `sys_log_create_by` (`create_by`) USING BTREE,
  KEY `sys_log_request_uri` (`request_uri`) USING BTREE,
  KEY `sys_log_type` (`type`) USING BTREE,
  KEY `sys_log_create_date` (`create_date`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=223 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='系统日志';


-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(40) DEFAULT NULL COMMENT '菜单名称',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父菜单',
  `level` bigint(2) DEFAULT NULL COMMENT '菜单层级',
  `parent_ids` varchar(2000) DEFAULT NULL COMMENT '父菜单联集',
  `sort` smallint(6) DEFAULT NULL COMMENT '排序',
  `href` varchar(2000) DEFAULT NULL COMMENT '链接地址',
  `target` varchar(20) DEFAULT NULL COMMENT '打开方式',
  `icon` varchar(100) DEFAULT NULL COMMENT '菜单图标',
  `bg_color` varchar(255) DEFAULT NULL COMMENT '显示背景色',
  `is_show` tinyint(2) DEFAULT NULL COMMENT '是否显示',
  `permission` varchar(200) DEFAULT NULL COMMENT '权限标识',
  `create_by` bigint(20) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_by` bigint(20) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  `del_flag` tinyint(2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=63 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('1', '系统管理', null, '1', '1,', '20', '', null, '', null, '1', '', '1', '2018-01-16 11:29:46', '1', '2018-01-20 03:09:26', null, '0');
INSERT INTO `sys_menu` VALUES ('2', '系统用户管理', '1', '2', '1,2,', '9', '/admin/system/user/list', null, '', '#47e69c', '1', 'sys:user:list', '1', '2018-01-16 11:31:18', '1', '2018-01-20 05:59:20', null, '0');
INSERT INTO `sys_menu` VALUES ('3', '系统角色管理', '1', '2', '1,3,', '10', '/admin/system/role/list', null, '', '#c23ab9', '1', 'sys:role:list', '1', '2018-01-16 11:32:33', '1', '2018-01-20 05:58:58', null, '0');
INSERT INTO `sys_menu` VALUES ('4', '系统权限管理', '1', '2', '1,4,', '20', '/admin/system/menu/list', null, '', '#d4573b', '1', 'sys:menu:list', '1', '2018-01-16 11:33:19', '1', '2018-02-08 09:49:28', null, '0');
INSERT INTO `sys_menu` VALUES ('5', '系统资源管理', '1', '2', '1,5,', '30', '/admin/system/rescource/list', null, '', '#f5e42a', '1', 'sys:rescource:list', '1', '2018-01-16 11:34:48', '1', '2018-01-20 05:56:35', null, '0');
INSERT INTO `sys_menu` VALUES ('6', '系统日志管理', '1', '2', '1,6,', '40', '/admin/system/log/list', null, '', '#b56c18', '1', 'sys:log:list', '1', '2018-01-16 11:35:31', '1', '2018-01-20 05:12:17', null, '0');
INSERT INTO `sys_menu` VALUES ('7', '网站基本信息', '1', '2', '1,7,', '50', '/admin/system/site/show', null, '', '#95deb9', '1', 'sys:site:list', '1', '2018-01-16 11:36:50', '1', '2018-01-20 05:55:44', null, '0');
INSERT INTO `sys_menu` VALUES ('8', '数据库管理', '1', '2', '1,8,', '60', '/admin/system/table/list', null, '', '#369e16', '1', 'sys:table:list', '1', '2018-01-16 11:38:29', '1', '2018-01-20 03:14:23', null, '0');
INSERT INTO `sys_menu` VALUES ('9', '系统字典管理', '1', '2', '1,9,', '70', '/admin/system/dict/list', null, '', '#1bbcc2', '1', 'sys:dict:list', '1', '2018-01-16 14:51:52', '1', '2018-01-20 03:12:27', null, '0');
INSERT INTO `sys_menu` VALUES ('10', '示例父菜单', null, '1', '10,', '10', '', null, '', null, '1', '', '1', '2018-01-17 13:21:53', '1', '2018-01-22 00:28:28', null, '0');
INSERT INTO `sys_menu` VALUES ('11', '示例子菜单1', '10', '2', '10,11,', '6', '/admin/blogChannel/list', null, '', null, '1', 'blog:channel:list', '1', '2018-01-17 13:22:57', '1', '2018-02-08 10:20:54', null, '0');
INSERT INTO `sys_menu` VALUES ('12', '示例子菜单2', '10', '2', '10,12,', '7', '/admin/blogComment/list', null, '', '#c8e332', '1', 'blog:comment:list', '1', '2018-01-17 13:23:52', '1', '2018-02-08 10:25:26', null, '0');
INSERT INTO `sys_menu` VALUES ('13', '示例子菜单3', '10', '2', '10,13,', '8', '/admin/blogArticle/list', null, '', '#1962b5', '1', 'blog:article:list', '1', '2018-01-17 16:02:07', '1', '2018-02-08 10:26:13', null, '0');
INSERT INTO `sys_menu` VALUES ('14', '定时任务', null, '1', '14,', '5', '', null, '', null, '1', '', '1', '2018-01-26 22:39:35', '1', '2018-02-08 10:31:05', null, '0');
INSERT INTO `sys_menu` VALUES ('15', '任务列表', '14', '2', '14,15,', '15', '/admin/quartzTask/list', null, '', '#d6d178', '1', 'quartz:task:list', '1', '2018-01-26 22:41:25', '1', '2018-02-08 10:31:11', null, '0');
INSERT INTO `sys_menu` VALUES ('16', '任务执行日志', '14', '2', '14,16,', '10', '/admin/quartzTaskLog/list', null, '', '#5158d6', '1', 'quartz:log:list', '1', '2018-01-27 01:07:11', '1', '2018-02-08 10:37:51', null, '0');
INSERT INTO `sys_menu` VALUES ('17', '新增字典', '9', '3', '1,9,17,', '0', '', null, null, null, '0', 'sys:dict:add', '1', '2018-02-08 09:39:09', '1', '2018-02-08 09:39:19', null, '0');
INSERT INTO `sys_menu` VALUES ('18', '编辑字典', '9', '3', '1,9,18,', '10', '', null, null, null, '0', 'sys:dict:edit', '1', '2018-02-08 09:40:37', '1', '2018-02-08 09:40:46', null, '0');
INSERT INTO `sys_menu` VALUES ('19', '编辑字典类型', '9', '3', '1,9,19,', '20', '', null, null, null, '0', 'sys:dict:editType', '1', '2018-02-08 09:42:46', '1', '2018-02-08 09:54:07', null, '0');
INSERT INTO `sys_menu` VALUES ('20', '新增系统权限', '4', '3', '1,4,20,', '0', '', null, null, null, '0', 'sys:menu:add', '1', '2018-02-08 09:49:15', '1', '2018-02-08 09:49:38', null, '0');
INSERT INTO `sys_menu` VALUES ('21', '编辑系统权限', '4', '3', '1,4,21,', '10', '', null, null, null, '0', 'sys:menu:edit', '1', '2018-02-08 09:50:16', '1', '2018-02-08 09:50:25', null, '0');
INSERT INTO `sys_menu` VALUES ('22', '删除系统权限', '4', '3', '1,4,22,', '20', '', null, null, null, '0', 'sys:menu:delete', '1', '2018-02-08 09:51:53', '1', '2018-02-08 09:53:42', null, '0');
INSERT INTO `sys_menu` VALUES ('23', '删除系统资源', '5', '3', '1,5,23,', '0', '', null, null, null, '0', 'sys:rescource:delete', '1', '2018-02-08 09:56:44', '1', '2018-02-08 09:56:53', null, '0');
INSERT INTO `sys_menu` VALUES ('24', '新增系统角色', '3', '3', '1,3,24,', '0', '', null, null, null, '0', 'sys:role:add', '1', '2018-02-08 09:58:11', '1', '2018-02-08 09:58:11', null, '0');
INSERT INTO `sys_menu` VALUES ('25', '编辑菜单权限', '3', '3', '1,3,25,', '10', '', null, null, null, '0', 'sys:role:edit', '1', '2018-02-08 09:59:01', '1', '2018-02-08 09:59:01', null, '0');
INSERT INTO `sys_menu` VALUES ('26', '删除角色', '3', '3', '1,3,26,', '20', '', null, null, null, '0', 'sys:role:delete', '1', '2018-02-08 09:59:56', '1', '2018-02-08 09:59:56', null, '0');
INSERT INTO `sys_menu` VALUES ('27', '编辑系统信息', '7', '3', '1,7,27,', '0', '', null, null, null, '0', 'sys:site:edit', '1', '2018-02-08 10:01:40', '1', '2018-02-08 10:01:40', null, '0');
INSERT INTO `sys_menu` VALUES ('28', '数据库新增', '8', '3', '1,8,28,', '0', '', null, null, null, '0', 'sys:table:add', '1', '2018-02-08 10:02:51', '1', '2018-02-08 10:02:51', null, '0');
INSERT INTO `sys_menu` VALUES ('29', '编辑数据库', '8', '3', '1,8,29,', '10', '', null, null, null, '0', 'sys:table:edit', '1', '2018-02-08 10:03:58', '1', '2018-02-08 10:03:58', null, '0');
INSERT INTO `sys_menu` VALUES ('30', '新增数据库字段', '8', '3', '1,8,30,', '20', '', null, null, null, '0', 'sys:table:addField', '1', '2018-02-08 10:05:11', '1', '2018-02-08 10:05:11', null, '0');
INSERT INTO `sys_menu` VALUES ('31', '编辑数据库字段', '8', '3', '1,8,31,', '30', '', null, null, null, '0', 'sys:table:editField', '1', '2018-02-08 10:05:47', '1', '2018-02-08 10:05:47', null, '0');
INSERT INTO `sys_menu` VALUES ('32', '删除数据库字段', '8', '3', '1,8,32,', '40', '', null, null, null, '0', 'sys:table:deleteField', '1', '2018-02-08 10:07:29', '1', '2018-02-08 10:15:39', null, '0');
INSERT INTO `sys_menu` VALUES ('33', '删除数据库', '8', '3', '1,8,33,', '50', '', null, null, null, '0', 'sys:table:deleteTable', '1', '2018-02-08 10:08:16', '1', '2018-02-08 10:08:16', null, '0');
INSERT INTO `sys_menu` VALUES ('34', '下载源码', '8', '3', '1,8,34,', '60', '', null, null, null, '0', 'sys:table:download', '1', '2018-02-08 10:09:28', '1', '2018-02-08 10:09:28', null, '0');
INSERT INTO `sys_menu` VALUES ('35', '新增系统用户', '2', '3', '1,2,35,', '0', '', null, null, null, '0', 'sys:user:add', '1', '2018-02-08 10:10:32', '1', '2018-02-08 10:10:32', null, '0');
INSERT INTO `sys_menu` VALUES ('36', '编辑系统用户', '2', '3', '1,2,36,', '10', '', null, null, null, '0', 'sys:user:edit', '1', '2018-02-08 10:11:49', '1', '2018-02-08 10:11:49', null, '0');
INSERT INTO `sys_menu` VALUES ('37', '删除系统用户', '2', '3', '1,2,37,', '20', '', null, null, null, '0', 'sys:user:delete', '1', '2018-02-08 10:12:43', '1', '2018-02-08 10:12:43', null, '0');
INSERT INTO `sys_menu` VALUES ('38', '新增博客文章', '13', '3', '10,13,38,', '0', '', null, null, null, '0', 'blog:article:add', '1', '2018-02-08 10:16:59', '1', '2018-02-08 10:16:59', null, '0');
INSERT INTO `sys_menu` VALUES ('39', '博客文章编辑', '13', '3', '10,13,39,', '10', '', null, null, null, '0', 'blog:article:edit', '1', '2018-02-08 10:17:16', '1', '2018-02-08 10:17:16', null, '0');
INSERT INTO `sys_menu` VALUES ('40', '博客文章删除', '13', '3', '10,13,40,', '20', '', null, null, null, '0', 'blog:article:delete', '1', '2018-02-08 10:17:34', '1', '2018-02-08 10:44:02', null, '0');
INSERT INTO `sys_menu` VALUES ('41', '博客评论回复', '12', '3', '10,12,41,', '0', '', null, null, null, '0', 'blog:comment:reply', '1', '2018-02-08 10:19:29', '1', '2018-02-08 10:20:33', null, '0');
INSERT INTO `sys_menu` VALUES ('42', '新增博客栏目', '11', '3', '10,11,42,', '0', '', null, null, null, '0', 'blog:channel:add', '1', '2018-02-08 10:22:06', '1', '2018-02-08 10:22:06', null, '0');
INSERT INTO `sys_menu` VALUES ('43', '编辑博客栏目', '11', '3', '10,11,43,', '10', '', null, null, null, '0', 'blog:channel:edit', '1', '2018-02-08 10:23:31', '1', '2018-02-08 10:23:31', null, '0');
INSERT INTO `sys_menu` VALUES ('44', '删除博客栏目', '11', '3', '10,11,44,', '20', '', null, null, null, '0', 'blog:channel:delete', '1', '2018-02-08 10:23:57', '1', '2018-02-08 10:23:57', null, '0');
INSERT INTO `sys_menu` VALUES ('45', '删除博客评论', '12', '3', '10,12,45,', '10', '', null, null, null, '0', 'blog:comment:delete', '1', '2018-02-08 10:28:48', '1', '2018-02-08 10:28:48', null, '0');
INSERT INTO `sys_menu` VALUES ('46', '新增定时任务', '15', '3', '14,15,46,', '0', '', null, null, null, '0', 'quartz:task:add', '1', '2018-02-08 10:32:46', '1', '2018-02-08 10:32:46', null, '0');
INSERT INTO `sys_menu` VALUES ('47', '编辑定时任务', '15', '3', '14,15,47,', '10', '', null, null, null, '0', 'quartz:task:edit', '1', '2018-02-08 10:34:18', '1', '2018-02-08 10:34:18', null, '0');
INSERT INTO `sys_menu` VALUES ('48', '删除定时任务', '15', '3', '14,15,48,', '20', '', null, null, null, '0', 'quartz:task:delete', '1', '2018-02-08 10:35:07', '1', '2018-02-08 10:35:07', null, '0');
INSERT INTO `sys_menu` VALUES ('49', '暂停定时任务', '15', '3', '14,15,49,', '30', '', null, null, null, '0', 'quartz:task:paush', '1', '2018-02-08 10:35:43', '1', '2018-02-08 10:35:43', null, '0');
INSERT INTO `sys_menu` VALUES ('50', '恢复运行任务', '15', '3', '14,15,50,', '40', '', null, null, null, '0', 'quartz:task:resume', '1', '2018-02-08 10:36:26', '1', '2018-02-08 10:36:26', null, '0');
INSERT INTO `sys_menu` VALUES ('51', '立即执行运行任务', '15', '3', '14,15,51,', '50', '', null, null, null, '0', 'quartz:task:run', '1', '2018-02-08 10:36:55', '1', '2018-02-08 10:36:55', null, '0');
INSERT INTO `sys_menu` VALUES ('52', '删除定时任务日志', '16', '3', '14,16,52,', '0', '', null, null, null, '0', 'quartz:log:delete', '1', '2018-02-08 10:39:04', '1', '2018-02-08 10:39:04', null, '0');
INSERT INTO `sys_menu` VALUES ('53', '修改密码', '2', '3', '1,2,53,', '30', '', null, '', null, '0', 'sys:user:changePassword', '1', '2018-03-15 10:14:06', '1', '2018-03-15 10:14:06', null, '0');
INSERT INTO `sys_menu` VALUES ('54', '删除字典', '9', '3', '1,9,54,', '30', '', null, null, null, '0', 'sys:dict:delete', '1', '2018-03-15 10:16:55', '1', '2018-03-15 10:16:55', null, '0');
INSERT INTO `sys_menu` VALUES ('55', '系统日志删除', '6', '3', '1,6,55,', '0', '', null, null, null, '0', 'system:logs:delete', '1', '2018-06-16 04:30:32', '1', '2018-06-16 04:30:32', null, '0');
INSERT INTO `sys_menu` VALUES ('56', '示例子菜单4', '10', '2', '10,56,', '3', '/admin/blogTags/list', null, '', '#c3e8ce', '1', 'blog:tags:list', '1', '2018-06-16 04:42:15', '1', '2018-06-16 04:42:30', null, '0');
INSERT INTO `sys_menu` VALUES ('57', '博客标签新增', '56', '3', '10,56,57,', '0', '', null, null, null, '0', 'blog:tags:add', '1', '2018-06-16 04:43:02', '1', '2018-06-16 04:43:02', null, '0');
INSERT INTO `sys_menu` VALUES ('58', '博客标签编辑', '56', '3', '10,56,58,', '10', '', null, null, null, '0', 'blog:tags:edit', '1', '2018-06-16 04:43:26', '1', '2018-06-16 04:43:26', null, '0');
INSERT INTO `sys_menu` VALUES ('59', '博客标签删除', '56', '3', '10,56,59,', '20', '', null, null, null, '0', 'blog:tags:delete', '1', '2018-06-16 04:43:56', '1', '2018-06-16 04:43:56', null, '0');
INSERT INTO `sys_menu` VALUES ('60', 'Druid数据监控', '1', '2', '1,60,', '25', '/admin/druid/list', null, '', '#7e8755', '1', 'sys:druid:list', '1', '2018-06-16 05:06:17', '1', '2018-06-16 05:06:26', null, '0');
INSERT INTO `sys_menu` VALUES ('61', '七牛云存储信息', '7', '3', '1,7,61,', '10', '', null, null, null, '0', 'sys:site:editQiniu', '1', '2018-07-12 18:46:39', '1', '2018-07-12 18:46:39', null, '0');
INSERT INTO `sys_menu` VALUES ('62', '阿里云信息存储', '7', '3', '1,7,62,', '20', '', null, null, null, '0', 'sys:site:editOss', '1', '2018-07-12 18:47:05', '1', '2018-07-12 18:47:05', null, '0');


-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(40) DEFAULT NULL COMMENT '角色名称',
  `create_date` datetime DEFAULT NULL,
  `create_by` bigint(20) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `update_by` bigint(20) DEFAULT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  `del_flag` tinyint(2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '老司机', '2017-11-02 14:19:07', '1', '2018-02-08 13:24:35', '1', '', '0');
INSERT INTO `sys_role` VALUES ('2', '系统管理员', '2017-11-29 19:36:37', '1', '2018-07-12 18:47:20', '1', '', '0');

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `role_id` bigint(20) NOT NULL,
  `menu_id` bigint(20) NOT NULL,
  PRIMARY KEY (`role_id`,`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES ('1', '1');
INSERT INTO `sys_role_menu` VALUES ('1', '2');
INSERT INTO `sys_role_menu` VALUES ('1', '3');
INSERT INTO `sys_role_menu` VALUES ('1', '4');
INSERT INTO `sys_role_menu` VALUES ('1', '8');
INSERT INTO `sys_role_menu` VALUES ('1', '10');
INSERT INTO `sys_role_menu` VALUES ('1', '13');
INSERT INTO `sys_role_menu` VALUES ('2', '1');
INSERT INTO `sys_role_menu` VALUES ('2', '2');
INSERT INTO `sys_role_menu` VALUES ('2', '3');
INSERT INTO `sys_role_menu` VALUES ('2', '4');
INSERT INTO `sys_role_menu` VALUES ('2', '5');
INSERT INTO `sys_role_menu` VALUES ('2', '6');
INSERT INTO `sys_role_menu` VALUES ('2', '7');
INSERT INTO `sys_role_menu` VALUES ('2', '8');
INSERT INTO `sys_role_menu` VALUES ('2', '9');
INSERT INTO `sys_role_menu` VALUES ('2', '10');
INSERT INTO `sys_role_menu` VALUES ('2', '11');
INSERT INTO `sys_role_menu` VALUES ('2', '12');
INSERT INTO `sys_role_menu` VALUES ('2', '13');
INSERT INTO `sys_role_menu` VALUES ('2', '14');
INSERT INTO `sys_role_menu` VALUES ('2', '15');
INSERT INTO `sys_role_menu` VALUES ('2', '16');
INSERT INTO `sys_role_menu` VALUES ('2', '17');
INSERT INTO `sys_role_menu` VALUES ('2', '18');
INSERT INTO `sys_role_menu` VALUES ('2', '19');
INSERT INTO `sys_role_menu` VALUES ('2', '20');
INSERT INTO `sys_role_menu` VALUES ('2', '21');
INSERT INTO `sys_role_menu` VALUES ('2', '22');
INSERT INTO `sys_role_menu` VALUES ('2', '23');
INSERT INTO `sys_role_menu` VALUES ('2', '24');
INSERT INTO `sys_role_menu` VALUES ('2', '25');
INSERT INTO `sys_role_menu` VALUES ('2', '26');
INSERT INTO `sys_role_menu` VALUES ('2', '27');
INSERT INTO `sys_role_menu` VALUES ('2', '28');
INSERT INTO `sys_role_menu` VALUES ('2', '29');
INSERT INTO `sys_role_menu` VALUES ('2', '30');
INSERT INTO `sys_role_menu` VALUES ('2', '31');
INSERT INTO `sys_role_menu` VALUES ('2', '32');
INSERT INTO `sys_role_menu` VALUES ('2', '33');
INSERT INTO `sys_role_menu` VALUES ('2', '34');
INSERT INTO `sys_role_menu` VALUES ('2', '35');
INSERT INTO `sys_role_menu` VALUES ('2', '36');
INSERT INTO `sys_role_menu` VALUES ('2', '37');
INSERT INTO `sys_role_menu` VALUES ('2', '38');
INSERT INTO `sys_role_menu` VALUES ('2', '39');
INSERT INTO `sys_role_menu` VALUES ('2', '40');
INSERT INTO `sys_role_menu` VALUES ('2', '41');
INSERT INTO `sys_role_menu` VALUES ('2', '42');
INSERT INTO `sys_role_menu` VALUES ('2', '43');
INSERT INTO `sys_role_menu` VALUES ('2', '44');
INSERT INTO `sys_role_menu` VALUES ('2', '45');
INSERT INTO `sys_role_menu` VALUES ('2', '46');
INSERT INTO `sys_role_menu` VALUES ('2', '47');
INSERT INTO `sys_role_menu` VALUES ('2', '48');
INSERT INTO `sys_role_menu` VALUES ('2', '49');
INSERT INTO `sys_role_menu` VALUES ('2', '50');
INSERT INTO `sys_role_menu` VALUES ('2', '51');
INSERT INTO `sys_role_menu` VALUES ('2', '52');
INSERT INTO `sys_role_menu` VALUES ('2', '53');
INSERT INTO `sys_role_menu` VALUES ('2', '54');
INSERT INTO `sys_role_menu` VALUES ('2', '55');
INSERT INTO `sys_role_menu` VALUES ('2', '56');
INSERT INTO `sys_role_menu` VALUES ('2', '57');
INSERT INTO `sys_role_menu` VALUES ('2', '58');
INSERT INTO `sys_role_menu` VALUES ('2', '59');
INSERT INTO `sys_role_menu` VALUES ('2', '60');
INSERT INTO `sys_role_menu` VALUES ('2', '61');
INSERT INTO `sys_role_menu` VALUES ('2', '62');


-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `login_name` varchar(36) DEFAULT NULL COMMENT '登录名',
  `nick_name` varchar(40) DEFAULT NULL COMMENT '昵称',
  `icon` varchar(2000) DEFAULT NULL,
  `password` varchar(40) DEFAULT NULL COMMENT '密码',
  `salt` varchar(40) DEFAULT NULL COMMENT 'shiro加密盐',
  `tel` varchar(11) DEFAULT NULL COMMENT '手机号码',
  `email` varchar(200) DEFAULT NULL COMMENT '邮箱地址',
  `locked` tinyint(2) DEFAULT NULL COMMENT '是否锁定',
  `create_date` datetime DEFAULT NULL,
  `create_by` bigint(20) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `update_by` bigint(20) DEFAULT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  `del_flag` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'test', '我是管理员', 'https://static.mysiteforme.com/3c5b69f4-2e39-4f88-b302-a6eb48e4c43a.jpg', '810339f5426fe2dcaf92c5cac718acc6471a034b', '3fb62b5aeede1bbf', '13776055179', 'b@qq.com', '0', '2017-11-27 22:19:39', '1', '2018-06-16 04:27:15', '1', '', '0');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('1', '1');
INSERT INTO `sys_user_role` VALUES ('1', '2');

