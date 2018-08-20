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

/*Table structure for table `dict_option` */

DROP TABLE IF EXISTS `dict_option`;

CREATE TABLE `dict_option` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dict_type` varchar(64) NOT NULL COMMENT '字典类型',
  `option_value` varchar(64) NOT NULL COMMENT '选项值',
  `label` varchar(64) NOT NULL COMMENT '选项文本',
  `k1` varchar(64) DEFAULT NULL COMMENT '扩展字段a',
  `k2` varchar(64) DEFAULT NULL COMMENT '扩展字段b',
  `option_remark` varchar(256) DEFAULT NULL COMMENT '选项备注',
  `show_order` int(11) DEFAULT NULL COMMENT '排序',
  `editable` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否可编辑(含删除)',
  PRIMARY KEY (`id`),
  UNIQUE KEY `dict_type` (`dict_type`,`option_value`),
  FULLTEXT KEY `sys_dict_option` (`dict_type`)
) ENGINE=InnoDB AUTO_INCREMENT=101 DEFAULT CHARSET=utf8 COMMENT='选项字典';

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
(50,'mgr_holiday_type','ACTIVITY','活动',NULL,NULL,NULL,3,1),
(51,'mgr_nature_type','RELAX','休息',NULL,NULL,NULL,1,1),
(52,'mgr_nature_type','CLASS','补班',NULL,NULL,NULL,2,1),
(54,'mgr_leave_type','SICK','病假','','','',1,0),
(55,'mgr_leave_type','BUSY','事假','','','',2,0),
(58,'mgr_shift_type','ONTIME','上班',NULL,NULL,NULL,1,1),
(59,'mgr_shift_type','OFFTIME','下班',NULL,NULL,NULL,2,1),
(62,'mgr_duty_type','CHIEF','正级',NULL,NULL,NULL,1,1),
(63,'mgr_duty_type','DEPUTY','副级',NULL,NULL,NULL,2,1),
(68,'mgr_leave_type','FAMILY','探亲假',NULL,NULL,'',3,0),
(70,'mgr_approver_type','PASS','通过',NULL,NULL,NULL,1,1),
(71,'mgr_approver_type','FAIL','不通过',NULL,NULL,NULL,2,1),
(72,'mgr_fieldwork_time_period','AM','上午',NULL,NULL,NULL,1,1),
(73,'mgr_fieldwork_time_period','PM','下午',NULL,NULL,NULL,2,1),
(74,'mgr_fieldwork_time_period','ALL','全天',NULL,NULL,NULL,3,1),
(78,'mgr_sync_cron','0 30 9,21 * * ? ','每日9点半，21点半',NULL,NULL,NULL,NULL,0),
(79,'mgr_sync_cron1','0 0/1 * * * ?','每分钟',NULL,NULL,NULL,NULL,1),
(82,'mgr_leave_type','MARRY','婚假',NULL,NULL,'',1,1),
(84,'mgr_on_leave_type','MATERNITY','产假',NULL,NULL,NULL,1,0),
(85,'mgr_on_leave_type','ANNUAL','年假',NULL,NULL,NULL,2,0),
(86,'mgr_on_leave_type','OTHER','其他',NULL,NULL,NULL,3,0),
(87,'mgr_fieldwork_type','TRAVEL','出差',NULL,NULL,NULL,1,1),
(88,'mgr_fieldwork_type','OTHER','其他',NULL,NULL,NULL,2,1),
(89,'mgr_order_status','DRAFT','草稿',NULL,NULL,NULL,1,1),
(90,'mgr_order_status','UNAPP','待审批',NULL,NULL,NULL,2,1),
(91,'mgr_order_status','UNREV','待审核',NULL,NULL,NULL,3,1),
(92,'mgr_order_status','FAILED','不通过',NULL,NULL,NULL,4,1),
(93,'mgr_order_status','FINISHED','完成',NULL,NULL,NULL,5,1),
(94,'mgr_leave_order_status','DRAFT','草稿',NULL,NULL,NULL,1,1),
(95,'mgr_leave_order_status','UNAPP','待审批',NULL,NULL,NULL,2,1),
(96,'mgr_leave_order_status','UNREV','待审核',NULL,NULL,NULL,3,1),
(97,'mgr_leave_order_status','FAILED','不通过',NULL,NULL,NULL,4,1),
(98,'mgr_leave_order_status','FINISHED','完成',NULL,NULL,NULL,5,1),
(99,'mgr_check_type','ASKLEAVE','请假',NULL,NULL,NULL,5,1),
(100,'mgr_check_type','ONLEAVE','休假',NULL,NULL,NULL,6,1);

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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

/*Data for the table `file_file` */

insert  into `file_file`(`id`,`name`,`parent_id`,`file_path`,`size`,`md5`,`file_file_type`,`create_time`,`update_time`,`remark`) values 
(1,'testFile',NULL,'aaa',1,'1111','12','2018-08-19 02:28:35','2018-08-19 02:30:28',NULL),
(2,'ttt',1,'qqqq',22,'22','12','2018-08-19 02:29:58','2018-08-19 02:30:29',NULL);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
