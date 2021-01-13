/*
SQLyog Ultimate v8.32 
MySQL - 5.5.49 : Database - oa
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`oa` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `oa`;

/*Table structure for table `avy_info` */

DROP TABLE IF EXISTS `avy_info`;

CREATE TABLE `avy_info` (
  `process_inst_id` varchar(32) NOT NULL COMMENT '流程实例ID',
  `avy_id` varchar(2) NOT NULL COMMENT '节点id',
  `tpl_no` varchar(32) NOT NULL COMMENT '流程模板ID',
  `executor_id` varchar(32) DEFAULT NULL COMMENT '当前节点执行人',
  `class_no` varchar(32) DEFAULT NULL COMMENT '班级ID',
  `tms` datetime DEFAULT NULL COMMENT '执行时间',
  `function_id` varchar(32) DEFAULT NULL COMMENT '功能编号',
  `firstUser` varchar(32) DEFAULT NULL COMMENT '流程发起人'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `avy_info` */

/*Table structure for table `biz_info` */

DROP TABLE IF EXISTS `biz_info`;

CREATE TABLE `biz_info` (
  `process_inst_id` varchar(32) NOT NULL COMMENT '流程实例id',
  `owner` varchar(32) DEFAULT NULL COMMENT '流程发起人',
  `owner_id` varchar(32) DEFAULT NULL COMMENT '流程发起人id',
  `tpl_no` varchar(32) NOT NULL COMMENT '流程模板id',
  `class_no` varchar(32) DEFAULT NULL COMMENT '班级',
  `tms` datetime DEFAULT NULL COMMENT '时间戳',
  `function_id` varchar(32) DEFAULT NULL COMMENT '功能编号',
  `apply_date` varchar(16) DEFAULT NULL COMMENT '申请日期',
  `start_date` varchar(16) DEFAULT NULL COMMENT '请假开始日期',
  `end_date` varchar(16) DEFAULT NULL COMMENT '请假结束日期',
  `days` int(11) DEFAULT NULL COMMENT '请假天数',
  `description` varchar(512) DEFAULT NULL COMMENT '请假原因',
  `course_name` varchar(32) DEFAULT NULL COMMENT '涉及课程名称',
  `teacher_name` varchar(32) DEFAULT NULL COMMENT '涉及老师姓名',
  `ids` varchar(1024) DEFAULT NULL COMMENT '涉及老师id'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `biz_info` */

insert  into `biz_info`(`process_inst_id`,`owner`,`owner_id`,`tpl_no`,`class_no`,`tms`,`function_id`,`apply_date`,`start_date`,`end_date`,`days`,`description`,`course_name`,`teacher_name`,`ids`) values ('2021010323250078500032','张三','202000100101','100',NULL,NULL,NULL,NULL,'2021-01-01','2021-01-01',1,'1',NULL,NULL,'001002|'),('2021010323260485400033','张三','202000100101','101',NULL,NULL,NULL,NULL,'2021-01-01','2021-01-02',2,'2',NULL,NULL,'001002|'),('2021010323351849200034','张三','202000100101','101',NULL,NULL,NULL,NULL,'2021-01-01','2021-01-02',2,'2',NULL,NULL,'001001|'),('2021010822465090700035','张三','202000100101','100',NULL,NULL,NULL,NULL,'2021-01-01','2021-01-01',1,'1天',NULL,NULL,'001001|001002|'),('2021010822564186300036','张三','202000100101','100',NULL,NULL,NULL,NULL,'2021-01-01','2021-01-01',1,'请假',NULL,NULL,'001001|001002|'),('2021010823375915700038','张三','202000100101','100',NULL,NULL,NULL,NULL,'2021-01-01','2021-01-01',1,'有事',NULL,NULL,'001001|001002|'),('2021010823474071000039','张三','202000100101','100',NULL,'2021-01-12 22:04:11',NULL,NULL,'2021-01-01','2021-01-01',1,'去去去','语文','朱自清','2000001001|2000001002|'),('2021011016050769900040','张三','202000100101','100',NULL,'2021-01-12 22:08:58',NULL,NULL,'2021-01-01','2021-01-01',1,'new','高数,语文,','高斯,朱自清,','2000001001|2000001002|'),('2021011023470950700043','张三','202000100101','100',NULL,'2021-01-12 21:34:22',NULL,NULL,'2021-01-01','2021-01-01',1,'去去去123','高数,语文,','高斯,朱自清,','2000001001|2000001002|'),('2021011023520720100044','张三','202000100101','100',NULL,NULL,NULL,NULL,'2021-01-01','2021-01-01',1,'去去去','高数,语文,','高斯,朱自清,','2000001001|2000001002|'),('2021011023533371000045','张三','202000100101','100',NULL,NULL,NULL,NULL,'2021-01-01','2021-01-01',1,'去去去','高数,语文,','高斯,朱自清,','2000001001|2000001002|'),('2021011100010019600048','张三','202000100101','100',NULL,'2021-01-12 22:19:23',NULL,NULL,'2021-01-01','2021-01-01',1,'new','高数,语文,','高斯,朱自清,','2000001001|2000001002|'),('2021011100023207200049','张三','202000100101','100',NULL,'2021-01-12 21:48:32',NULL,NULL,'2021-01-01','2021-01-01',1,'去去去123','高数,语文,','高斯,朱自清,','2000001001|2000001002|');

/*Table structure for table `class` */

DROP TABLE IF EXISTS `class`;

CREATE TABLE `class` (
  `class_no` varchar(16) NOT NULL COMMENT '班级ID',
  `major_no` varchar(16) NOT NULL COMMENT '专业ID',
  `major_name` varchar(16) NOT NULL COMMENT '专业名称',
  `academy_no` varchar(16) NOT NULL COMMENT '学院ID',
  `academy_name` varchar(16) NOT NULL COMMENT '学院名称',
  `counselor_no` varchar(16) NOT NULL COMMENT '辅导员ID',
  `counselor_name` varchar(16) NOT NULL COMMENT '辅导员姓名',
  `monitor_no` varchar(12) NOT NULL COMMENT '班长ID',
  `monitor_name` varchar(16) NOT NULL COMMENT '班长姓名'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `class` */

insert  into `class`(`class_no`,`major_no`,`major_name`,`academy_no`,`academy_name`,`counselor_no`,`counselor_name`,`monitor_no`,`monitor_name`) values ('2020001001','001','信息技术','001','工程学院','2016001901','马化腾','202000100102','李四');

/*Table structure for table `hist_avy` */

DROP TABLE IF EXISTS `hist_avy`;

CREATE TABLE `hist_avy` (
  `process_inst_id` varchar(32) NOT NULL,
  `avy_id` varchar(2) NOT NULL,
  `tpl_no` varchar(32) NOT NULL,
  `executor_id` varchar(32) DEFAULT NULL,
  `class_no` varchar(32) DEFAULT NULL,
  `tms` datetime DEFAULT NULL,
  `function_id` varchar(32) DEFAULT NULL,
  `decision` char(1) DEFAULT NULL,
  `decision_desc` varchar(128) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `hist_avy` */

insert  into `hist_avy`(`process_inst_id`,`avy_id`,`tpl_no`,`executor_id`,`class_no`,`tms`,`function_id`,`decision`,`decision_desc`) values ('2021010823474071000039','1','100','202000100101','2020001001','2021-01-12 22:04:11','COMM_001',NULL,NULL),('2021011016050769900040','1','100','202000100101','2020001001','2021-01-12 22:08:58','COMM_001',NULL,NULL),('2021011100010019600048','1','100','202000100101','2020001001','2021-01-12 22:19:23','COMM_001',NULL,NULL);

/*Table structure for table `process_info` */

DROP TABLE IF EXISTS `process_info`;

CREATE TABLE `process_info` (
  `process_inst_id` varchar(32) NOT NULL,
  `avy_id` varchar(2) NOT NULL,
  `tpl_no` varchar(32) NOT NULL,
  `executor_id` varchar(32) DEFAULT NULL,
  `process_tpcd` char(2) DEFAULT NULL,
  `class_no` varchar(32) DEFAULT NULL,
  `tms` datetime DEFAULT NULL,
  `function_id` varchar(32) DEFAULT NULL,
  `decision` char(1) DEFAULT NULL,
  `decision_desc` varchar(128) DEFAULT NULL,
  `owner_id` varchar(32) DEFAULT NULL COMMENT '流程发起人id'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `process_info` */

insert  into `process_info`(`process_inst_id`,`avy_id`,`tpl_no`,`executor_id`,`process_tpcd`,`class_no`,`tms`,`function_id`,`decision`,`decision_desc`,`owner_id`) values ('2021010823474071000039','1','100','001','10','2020001001','2021-01-12 22:04:11','COMM_001',NULL,NULL,'202000100101'),('2021011016050769900040','1','100','001','10','2020001001','2021-01-12 22:08:58','COMM_001',NULL,NULL,'202000100101'),('2021011023470950700043','1','100','001','11','2020001001','2021-01-11 00:02:16','COMM_001',NULL,NULL,'202000100101'),('2021011023520720100044','1','100','001','11','2020001001','2021-01-11 00:02:16','COMM_001',NULL,NULL,'202000100101'),('2021011023533371000045','1','100','001','11','2020001001','2021-01-11 00:02:17','COMM_001',NULL,NULL,'202000100101'),('2021011100010019600048','1','100','001','10','2020001001','2021-01-12 22:19:23','COMM_001',NULL,NULL,'202000100101'),('2021011100023207200049','1','100','001','11','2020001001','2021-01-12 21:17:52','COMM_001',NULL,NULL,'202000100101');

/*Table structure for table `score` */

DROP TABLE IF EXISTS `score`;

CREATE TABLE `score` (
  `student_no` varchar(16) NOT NULL,
  `student_name` varchar(16) NOT NULL,
  `course_no` varchar(16) NOT NULL,
  `course_name` varchar(16) NOT NULL,
  `teacher_no` varchar(16) NOT NULL,
  `teacher_name` varchar(16) NOT NULL,
  `score` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `score` */

insert  into `score`(`student_no`,`student_name`,`course_no`,`course_name`,`teacher_no`,`teacher_name`,`score`) values ('202000100101','张三','001','高数','2000001001','高斯',NULL),('202000100101','张三','002','语文','2000001002','朱自清',NULL);

/*Table structure for table `sequence` */

DROP TABLE IF EXISTS `sequence`;

CREATE TABLE `sequence` (
  `sequence_id` varchar(32) NOT NULL COMMENT '序列的名称',
  `num` int(11) DEFAULT NULL COMMENT '当前序列值',
  `step` int(11) DEFAULT NULL COMMENT '步长',
  `min` int(11) DEFAULT NULL COMMENT '最小值',
  `max` int(11) DEFAULT NULL COMMENT '最大值',
  UNIQUE KEY `sequence_id` (`sequence_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sequence` */

insert  into `sequence`(`sequence_id`,`num`,`step`,`min`,`max`) values ('process_inst_id',50,1,1,99999);

/*Table structure for table `teacher_course` */

DROP TABLE IF EXISTS `teacher_course`;

CREATE TABLE `teacher_course` (
  `teacher_no` varchar(32) NOT NULL,
  `teacher_name` varchar(32) DEFAULT NULL,
  `course_no` varchar(32) DEFAULT NULL,
  `course_name` varchar(32) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `teacher_course` */

insert  into `teacher_course`(`teacher_no`,`teacher_name`,`course_no`,`course_name`) values ('2000001002','朱自清','002','语文'),('2000001001','高斯','001','高数');

/*Table structure for table `teacher_leave` */

DROP TABLE IF EXISTS `teacher_leave`;

CREATE TABLE `teacher_leave` (
  `teacher_no` varchar(32) NOT NULL,
  `process_inst_id` varchar(32) NOT NULL,
  `teacher_name` varchar(32) DEFAULT NULL,
  `course_no` varchar(32) DEFAULT NULL,
  `course_name` varchar(32) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `teacher_leave` */

insert  into `teacher_leave`(`teacher_no`,`process_inst_id`,`teacher_name`,`course_no`,`course_name`) values ('2000001001','2021010823474071000039','高斯','001','高数'),('2000001002','2021010823474071000039','朱自清','002','语文'),('2000001001','2021011016050769900040','高斯','001','高数'),('2000001002','2021011016050769900040','朱自清','002','语文'),('2000001001','2021011023470950700043','高斯','001','高数'),('2000001002','2021011023470950700043','朱自清','002','语文'),('2000001001','2021011023520720100044','高斯','001','高数'),('2000001002','2021011023520720100044','朱自清','002','语文'),('2000001001','2021011023533371000045','高斯','001','高数'),('2000001002','2021011023533371000045','朱自清','002','语文'),('2000001001','2021011100010019600048','高斯','001','高数'),('2000001002','2021011100010019600048','朱自清','002','语文'),('2000001001','2021011100023207200049','高斯','001','高数'),('2000001002','2021011100023207200049','朱自清','002','语文');

/*Table structure for table `todo_avy_info` */

DROP TABLE IF EXISTS `todo_avy_info`;

CREATE TABLE `todo_avy_info` (
  `process_inst_id` varchar(32) NOT NULL,
  `avy_id` varchar(2) NOT NULL,
  `tpl_no` varchar(32) NOT NULL,
  `executor_id` varchar(32) DEFAULT NULL,
  `class_no` varchar(32) DEFAULT NULL,
  `tms` datetime DEFAULT NULL,
  `function_id` varchar(32) DEFAULT NULL,
  `can_withdraw` char(1) DEFAULT NULL,
  `can_retreat` char(1) DEFAULT NULL,
  `withdrew` char(1) DEFAULT NULL COMMENT '已收回标志'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `todo_avy_info` */

insert  into `todo_avy_info`(`process_inst_id`,`avy_id`,`tpl_no`,`executor_id`,`class_no`,`tms`,`function_id`,`can_withdraw`,`can_retreat`,`withdrew`) values ('2021011023470950700043','2','100','202000100101','2020001001','2021-01-10 23:47:09','COMM_001','0','0','1'),('2021011023520720100044','2','100','202000100101','2020001001','2021-01-10 23:52:07','COMM_001','0','0','1'),('2021011023533371000045','2','100','202000100101','2020001001','2021-01-10 23:53:33','COMM_001','0','0','1'),('2021011100023207200049','2','100','202000100101','2020001001','2021-01-11 00:02:32','COMM_001','0','0','1'),('2021011100010019600048','2','100','202000100102','2020001001','2021-01-12 22:19:23','COMM_001','0','1',NULL),('2021011100010019600048','2','100','202000100101','2020001001','2021-01-12 22:19:23','COMM_001','1','0',NULL);

/*Table structure for table `tpl_avy_info` */

DROP TABLE IF EXISTS `tpl_avy_info`;

CREATE TABLE `tpl_avy_info` (
  `avy_id` varchar(2) NOT NULL,
  `tpl_no` varchar(32) NOT NULL,
  `executor_id` varchar(32) DEFAULT NULL,
  `class_no` varchar(32) DEFAULT NULL,
  `tms` datetime DEFAULT NULL,
  `function_id` varchar(32) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tpl_avy_info` */

insert  into `tpl_avy_info`(`avy_id`,`tpl_no`,`executor_id`,`class_no`,`tms`,`function_id`) values ('1','100','001',NULL,NULL,'COMM_001'),('1','101','001',NULL,NULL,'COMM_001'),('2','101','002',NULL,NULL,'COMM_001'),('1','102','001',NULL,NULL,'COMM_001'),('2','102','002',NULL,NULL,'COMM_001'),('3','102','003',NULL,NULL,'COMM_001'),('1','103','001',NULL,NULL,'COMM_001'),('2','103','002',NULL,NULL,'COMM_001'),('3','103','003',NULL,NULL,'COMM_001'),('4','103','004',NULL,NULL,'COMM_001'),('5','103','005',NULL,NULL,'COMM_001'),('2','100','002',NULL,NULL,'COMM_001'),('3','101','003',NULL,NULL,'COMM_001'),('4','102','004',NULL,NULL,'COMM_001');

/*Table structure for table `tpl_mpng_rule` */

DROP TABLE IF EXISTS `tpl_mpng_rule`;

CREATE TABLE `tpl_mpng_rule` (
  `tpl_no` varchar(32) NOT NULL,
  `condition_desc` varchar(128) DEFAULT NULL,
  `class_no` varchar(32) DEFAULT NULL,
  `tms` datetime DEFAULT NULL,
  `function_id` varchar(32) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tpl_mpng_rule` */

insert  into `tpl_mpng_rule`(`tpl_no`,`condition_desc`,`class_no`,`tms`,`function_id`) values ('101','(1 < new Integer(days)) and (new Integer(days) <= 7)',NULL,NULL,'COMM_001'),('100','(0 < new Integer(days)) and (new Integer(days) <= 1)',NULL,NULL,'COMM_001'),('102','(7 < new Integer(days)) and (new Integer(days) <= 30)',NULL,NULL,'COMM_001'),('103','(30 < new Integer(days))',NULL,NULL,'COMM_001');

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `user_id` varchar(32) NOT NULL COMMENT '用户id',
  `username` varchar(32) NOT NULL COMMENT '用户姓名',
  `password` varchar(128) DEFAULT NULL COMMENT '密码',
  `age` varchar(3) DEFAULT NULL COMMENT '年龄',
  `tel` varchar(16) DEFAULT NULL COMMENT '电话',
  `email` varchar(32) DEFAULT NULL COMMENT '邮箱',
  `address` varchar(32) DEFAULT NULL COMMENT '地址',
  `class_no` varchar(32) DEFAULT NULL COMMENT '班级id',
  `major_no` varchar(32) DEFAULT NULL COMMENT '专业id',
  `academy_no` varchar(32) DEFAULT NULL COMMENT '学院id',
  `executor_id` varchar(32) DEFAULT NULL COMMENT '流程执行的角色id',
  UNIQUE KEY `user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`user_id`,`username`,`password`,`age`,`tel`,`email`,`address`,`class_no`,`major_no`,`academy_no`,`executor_id`) values ('1985009001','张主任','123','48',NULL,NULL,NULL,NULL,NULL,NULL,'005'),('1985009002','王院长','123','35',NULL,NULL,NULL,NULL,NULL,'001','004'),('2000001001','高斯','123','45',NULL,NULL,NULL,NULL,NULL,NULL,'100'),('2000001002','朱自清','123',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'100'),('2015009003','唐三藏','123','28',NULL,NULL,NULL,NULL,NULL,NULL,'003'),('202000100101','张三','123','18','18808880888','168@qq.com','北京市','2020001001','001001','001','001'),('202000100102','李四','123','18','18800010001','169@qq.com','保定市','2020001001','001001','001','002');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
