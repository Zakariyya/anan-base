/*
SQLyog Trial v12.4.1 (64 bit)
MySQL - 5.7.17-enterprise-commercial-advanced-log : Database - springboot
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`springboot` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_bin */;

USE `springboot`;

/*Table structure for table `auth_permission` */

DROP TABLE IF EXISTS `auth_permission`;

CREATE TABLE `auth_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `pid` int(11) NOT NULL COMMENT '父节点id',
  `name` varchar(32) COLLATE utf8mb4_bin NOT NULL COMMENT '名称',
  `url` varchar(256) COLLATE utf8mb4_bin NOT NULL COMMENT 'url',
  `remark` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='权限表';

/*Data for the table `auth_permission` */

/*Table structure for table `auth_role` */

DROP TABLE IF EXISTS `auth_role`;

CREATE TABLE `auth_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(64) COLLATE utf8mb4_bin NOT NULL COMMENT '名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='角色表';

/*Data for the table `auth_role` */

/*Table structure for table `auth_user` */

DROP TABLE IF EXISTS `auth_user`;

CREATE TABLE `auth_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `account` varchar(64) COLLATE utf8mb4_bin NOT NULL COMMENT '账户',
  `password` varchar(256) COLLATE utf8mb4_bin NOT NULL COMMENT '密码',
  PRIMARY KEY (`id`),
  UNIQUE KEY `account` (`account`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='用户表';

/*Data for the table `auth_user` */

/*Table structure for table `auth_user_role` */

DROP TABLE IF EXISTS `auth_user_role`;

CREATE TABLE `auth_user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` int(11) NOT NULL COMMENT 'auth_user的主键',
  `role_id` int(11) NOT NULL COMMENT 'auth_role的主键',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

/*Data for the table `auth_user_role` */

/*Table structure for table `authshiro_user` */

DROP TABLE IF EXISTS `authshiro_user`;

CREATE TABLE `authshiro_user` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `LOGIN_NAME` varchar(20) NOT NULL DEFAULT '' COMMENT '全名',
  `NAME` varchar(20) NOT NULL DEFAULT '' COMMENT '名字',
  `PASSWORD` varchar(255) NOT NULL DEFAULT '' COMMENT '密码',
  `SALT` varchar(255) NOT NULL COMMENT '加盐值',
  `BIRTHDAY` varchar(32) DEFAULT NULL COMMENT '生日',
  `GENDER` smallint(6) DEFAULT NULL COMMENT '性别',
  `EMAIL` varchar(128) DEFAULT NULL COMMENT '邮箱',
  `PHONE` varchar(20) DEFAULT NULL COMMENT '电话',
  `CREATE_DATE` varchar(32) DEFAULT NULL COMMENT '建立时间',
  `LOGIN_COUNT` int(11) DEFAULT NULL COMMENT '登录次数',
  `PREVIOUS_VISIT` varchar(32) DEFAULT NULL COMMENT '上次访问时间',
  `LAST_VISIT` varchar(32) DEFAULT NULL COMMENT '最后登录时间',
  `ROLE` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=139481 DEFAULT CHARSET=utf8;

/*Data for the table `authshiro_user` */

insert  into `authshiro_user`(`ID`,`LOGIN_NAME`,`NAME`,`PASSWORD`,`SALT`,`BIRTHDAY`,`GENDER`,`EMAIL`,`PHONE`,`CREATE_DATE`,`LOGIN_COUNT`,`PREVIOUS_VISIT`,`LAST_VISIT`,`ROLE`) values 
(98,'admin','ADMIN','cbb5d07ea0d7a9d11d74c636002655f5','3af23c18-c953-458f-b2cb-7ea83ccb34ff','1998-01-08',1,'2925012005@qq.com','15178967275','2018-01-26 16:09:55',NULL,NULL,NULL,'ADMIN'),
(139456,'yaokunyi','姚坤逸','4ddffeeebe1fe8abc5898979310990dd','3701be05-6cf5-447a-80f6-0eb47f402fff','2018-08-08',1,'yaokunyi@urun.com','13714022222','2018-12-09 21:27:31',NULL,NULL,NULL,'USER'),
(139469,'chenzhou','陈周','cbb5d07ea0d7a9d11d74c636002655f5','3af23c18-c953-458f-b2cb-7ea83ccb34ff','1998-01-08',1,'2925012005@qq.com','15178967275','2018-01-26 16:09:55',NULL,NULL,NULL,'ADMIN'),
(139470,'yanganwen','阳安文','cbb5d07ea0d7a9d11d74c636002655f5','3af23c18-c953-458f-b2cb-7ea83ccb34ff','1998-01-08',1,'2925012005@qq.com','15178967275','2018-01-26 16:09:55',NULL,NULL,NULL,'USER'),
(139471,'chenjiandeng','陈建登','cbb5d07ea0d7a9d11d74c636002655f5','3af23c18-c953-458f-b2cb-7ea83ccb34ff','1998-01-08',1,'2925012005@qq.com','15178967275','2018-01-26 16:09:55',NULL,NULL,NULL,'USER'),
(139472,'fuyan','付艳','cbb5d07ea0d7a9d11d74c636002655f5','3af23c18-c953-458f-b2cb-7ea83ccb34ff','1998-01-08',1,'2925012005@qq.com','15178967275','2018-01-26 16:09:55',NULL,NULL,NULL,'USER'),
(139473,'yuanguangyi','袁广义','cbb5d07ea0d7a9d11d74c636002655f5','3af23c18-c953-458f-b2cb-7ea83ccb34ff','1998-01-08',1,'2925012005@qq.com','15178967275','2018-01-26 16:09:55',NULL,NULL,NULL,'USER'),
(139474,'huanghaipeng','黄海鹏','cbb5d07ea0d7a9d11d74c636002655f5','3af23c18-c953-458f-b2cb-7ea83ccb34ff','1998-01-08',1,'2925012005@qq.com','15178967275','2018-01-26 16:09:55',NULL,NULL,NULL,'USER'),
(139475,'liusiyuan','刘思源','cbb5d07ea0d7a9d11d74c636002655f5','3af23c18-c953-458f-b2cb-7ea83ccb34ff','1998-01-08',1,'2925012005@qq.com','15178967275','2018-01-26 16:09:55',NULL,NULL,NULL,'USER'),
(139476,'liunaifeng','刘乃风','cbb5d07ea0d7a9d11d74c636002655f5','3af23c18-c953-458f-b2cb-7ea83ccb34ff','1998-01-08',1,'2925012005@qq.com','15178967275','2018-01-26 16:09:55',NULL,NULL,NULL,'USER'),
(139477,'zhuxiangyu','朱祥玉','cbb5d07ea0d7a9d11d74c636002655f5','3af23c18-c953-458f-b2cb-7ea83ccb34ff','1998-01-08',1,'2925012005@qq.com','15178967275','2018-01-26 16:09:55',NULL,NULL,NULL,'USER'),
(139478,'dulangxian','杜朗贤','cbb5d07ea0d7a9d11d74c636002655f5','3af23c18-c953-458f-b2cb-7ea83ccb34ff','1998-01-08',1,'2925012005@qq.com','15178967275','2018-01-26 16:09:55',NULL,NULL,NULL,'USER'),
(139479,'linminling','林敏玲','cbb5d07ea0d7a9d11d74c636002655f5','3af23c18-c953-458f-b2cb-7ea83ccb34ff','1998-01-08',1,'2925012005@qq.com','15178967275','2018-01-26 16:09:55',NULL,NULL,NULL,'USER'),
(139480,'shijingyong','石锦勇','cbb5d07ea0d7a9d11d74c636002655f5','3af23c18-c953-458f-b2cb-7ea83ccb34ff','1998-01-08',1,'2925012005@qq.com','15178967275','2018-01-26 16:09:55',NULL,NULL,NULL,'USER');

/*Table structure for table `authshiro_usergroup` */

DROP TABLE IF EXISTS `authshiro_usergroup`;

CREATE TABLE `authshiro_usergroup` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(64) NOT NULL COMMENT '用户组名称',
  `p_id` int(11) NOT NULL COMMENT '父级id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

/*Data for the table `authshiro_usergroup` */

insert  into `authshiro_usergroup`(`id`,`name`,`p_id`) values 
(1,'云润大数据',0);

/*Table structure for table `authshiro_usergroup_user` */

DROP TABLE IF EXISTS `authshiro_usergroup_user`;

CREATE TABLE `authshiro_usergroup_user` (
  `usergroup_id` int(11) NOT NULL,
  `user` varchar(64) NOT NULL,
  PRIMARY KEY (`usergroup_id`,`user`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `authshiro_usergroup_user` */

insert  into `authshiro_usergroup_user`(`usergroup_id`,`user`) values 
(1,'lbb'),
(1,'test');

/*Table structure for table `comment` */

DROP TABLE IF EXISTS `comment`;

CREATE TABLE `comment` (
  `id` varchar(64) COLLATE utf8mb4_bin NOT NULL COMMENT '主键UUID',
  `parent_id` varchar(64) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '父节点ID',
  `content` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '评论内容',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='评论表';

/*Data for the table `comment` */

insert  into `comment`(`id`,`parent_id`,`content`,`create_time`,`update_time`) values 
('a0ee8e67-09bb-48bf-a4ce-51b28beeebe9',NULL,'11111','2018-08-23 10:36:37','2018-08-23 15:18:46'),
('db0d934b-58a6-44ac-83b3-af2f179a191f','a0ee8e67-09bb-48bf-a4ce-51b28beeebe9','aaaaaaaaaaaa','2018-08-23 10:37:42','2018-08-23 15:32:23'),
('f2d0f445-6122-466f-ada9-3a13926079d3',NULL,'aaaaaaaaaaaa','2018-08-23 10:39:42','2018-08-23 10:39:42');

/*Table structure for table `content_category` */

DROP TABLE IF EXISTS `content_category`;

CREATE TABLE `content_category` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `parent_id` int(11) DEFAULT NULL COMMENT '父节点id',
  `category_type` int(11) NOT NULL COMMENT '类别（读取字段表）',
  `name` varchar(128) COLLATE utf8mb4_bin NOT NULL COMMENT '名称',
  `remark` varchar(256) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '备注',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='类别表-cms模块';

/*Data for the table `content_category` */

/*Table structure for table `content_content` */

DROP TABLE IF EXISTS `content_content`;

CREATE TABLE `content_content` (
  `id` varchar(64) COLLATE utf8mb4_bin NOT NULL COMMENT 'uuid，主键',
  `category_id` int(11) NOT NULL COMMENT '关联类别的id',
  `title` varchar(128) COLLATE utf8mb4_bin NOT NULL COMMENT '标题',
  `title2` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '标题2',
  `content` text COLLATE utf8mb4_bin COMMENT '内容1',
  `content2` varchar(256) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '内容2',
  `content3` varchar(256) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '内容3',
  `content4` varchar(256) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '内容4',
  `remark` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '备注',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='内容表-cms模块';

/*Data for the table `content_content` */

/*Table structure for table `dict_option` */

DROP TABLE IF EXISTS `dict_option`;

CREATE TABLE `dict_option` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dict_type` varchar(64) NOT NULL COMMENT '字典类型',
  `option_value` varchar(64) NOT NULL COMMENT '选项值',
  `label` varchar(64) NOT NULL COMMENT '选项文本',
  `k1` varchar(64) DEFAULT NULL COMMENT '扩展字段a（用来归类）',
  `k2` varchar(64) DEFAULT NULL COMMENT '扩展字段b',
  `option_remark` varchar(256) DEFAULT NULL COMMENT '选项备注',
  `show_order` int(11) DEFAULT NULL COMMENT '排序',
  `editable` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否可编辑(含删除)',
  PRIMARY KEY (`id`),
  UNIQUE KEY `dict_type` (`dict_type`,`option_value`),
  FULLTEXT KEY `sys_dict_option` (`dict_type`)
) ENGINE=InnoDB AUTO_INCREMENT=103 DEFAULT CHARSET=utf8 COMMENT='选项字典';

/*Data for the table `dict_option` */

insert  into `dict_option`(`id`,`dict_type`,`option_value`,`label`,`k1`,`k2`,`option_remark`,`show_order`,`editable`) values 
(1,'ac_menu_type','SYSTEM','系统',NULL,NULL,NULL,1,1),
(2,'ac_menu_type','MODULE','模块',NULL,NULL,NULL,2,1),
(3,'ac_menu_type','PAGE','页面',NULL,NULL,NULL,3,1),
(4,'ac_menu_type','BUTTON','按钮',NULL,NULL,NULL,4,1),
(12,'mgr_back_order_status','DRAFT','草稿',NULL,NULL,NULL,1,0),
(13,'mgr_back_order_status','DONE','完成',NULL,NULL,NULL,2,0),
(14,'mgr_back_order_status','INVALID','作废',NULL,NULL,NULL,3,0),
(34,'mgr_check_type','NORMAL','正常',NULL,NULL,NULL,1,1),
(35,'mgr_check_type','LATE','迟到',NULL,NULL,NULL,3,1),
(37,'mgr_check_type','EARLY','早退',NULL,NULL,NULL,4,1),
(38,'mgr_check_type','OUT','外勤',NULL,NULL,NULL,7,1),
(39,'mgr_check_type','REPAIR','补卡',NULL,NULL,NULL,8,1),
(40,'mgr_check_type','LACK','缺卡',NULL,NULL,NULL,2,1),
(48,'mgr_holiday_type','STATUTORY','法定节假日',NULL,NULL,NULL,1,1),
(49,'mgr_holiday_type','PUBLIC','公休',NULL,NULL,NULL,2,1),
(101,'file_file_type','FILE','文件',NULL,NULL,NULL,2,1),
(102,'file_file_type','DIR','文件夹',NULL,NULL,NULL,1,1);

/*Table structure for table `file_file` */

DROP TABLE IF EXISTS `file_file`;

CREATE TABLE `file_file` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(128) COLLATE utf8mb4_bin NOT NULL COMMENT '名称',
  `parent_id` int(11) DEFAULT NULL COMMENT '父节点id',
  `file_path` varchar(256) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '文件路径',
  `size` bigint(20) DEFAULT NULL COMMENT '大小（文件夹没有大小）',
  `md5` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT 'md5值',
  `file_file_type` varchar(64) COLLATE utf8mb4_bin NOT NULL DEFAULT '0' COMMENT '读取字典表',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `remark` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  UNIQUE KEY `md5` (`md5`) COMMENT 'md5唯一值，减少重复文件上传次数'
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='文件管理表';

/*Data for the table `file_file` */

insert  into `file_file`(`id`,`name`,`parent_id`,`file_path`,`size`,`md5`,`file_file_type`,`create_time`,`update_time`,`remark`) values 
(1,'testFile',NULL,'aaa',1,'1111','102','2018-08-19 02:28:35','2018-08-21 10:02:37',NULL),
(2,'ttt',1,'qqqq',22,'22','102','2018-08-19 02:29:58','2018-08-21 10:02:35',NULL),
(3,'文件夹',NULL,NULL,NULL,NULL,'102','2018-08-21 00:19:21','2018-08-21 10:02:29','文件夹'),
(4,'文件夹',NULL,NULL,NULL,NULL,'102','2018-08-21 00:20:07','2018-08-21 10:02:29','文件夹'),
(8,'文件夹',NULL,NULL,NULL,NULL,'102','2018-08-21 08:36:46','2018-08-21 10:02:33','文件夹'),
(9,'file_DIR',3,NULL,NULL,NULL,'102','2018-08-21 10:01:13','2018-08-21 10:01:13','file_DIR'),
(10,'file_DIR',4,NULL,NULL,NULL,'102','2018-08-21 10:17:59','2018-08-21 10:17:59','file_DIR'),
(11,'file_DIR',12,NULL,NULL,NULL,'102','2018-08-21 11:40:49','2018-08-21 12:06:06','testtest'),
(12,'file_DIR',4,NULL,NULL,NULL,'102','2018-08-21 10:19:30','2018-08-21 10:19:30','file_DIR'),
(13,'file_DIR',4,NULL,NULL,NULL,'102','2018-08-21 10:20:08','2018-08-21 10:20:08','file_DIR'),
(14,'file_DIR',4,NULL,NULL,NULL,'102','2018-08-21 10:23:38','2018-08-21 10:23:38','file_DIR'),
(15,'file_DIR',4,NULL,NULL,NULL,'102','2018-08-21 10:26:08','2018-08-21 10:26:08','file_DIR'),
(16,'file_DIR',4,NULL,NULL,NULL,'102','2018-08-21 10:27:23','2018-08-21 10:27:23','file_DIR'),
(17,'file_DIR',4,NULL,NULL,NULL,'102','2018-08-21 10:28:26','2018-08-21 10:28:26','file_DIR'),
(18,'tttt',17,NULL,NULL,NULL,'102','2018-08-21 10:29:48','2018-08-24 15:38:28',NULL),
(19,'file_DIR',4,NULL,NULL,NULL,'102','2018-08-21 10:31:35','2018-08-21 10:31:35','file_DIR'),
(20,'file_DIR',4,NULL,NULL,NULL,'102','2018-08-21 10:33:49','2018-08-21 10:33:49','file_DIR'),
(21,'file_DIR',10,NULL,NULL,NULL,'102','2018-08-21 11:33:38','2018-08-21 11:33:38','file_DIR'),
(22,'file_DIR',10,NULL,NULL,NULL,'102','2018-08-21 11:34:45','2018-08-21 11:34:45','file_DIR'),
(23,'aaaaaaas',NULL,NULL,NULL,NULL,'102','2018-08-22 17:35:22','2018-08-22 17:35:22','aaaaaaas'),
(24,'aaaa',NULL,NULL,NULL,NULL,'102','2018-08-22 17:47:00','2018-08-22 17:47:00','aaaa');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
