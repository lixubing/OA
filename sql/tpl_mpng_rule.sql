/*
SQLyog Ultimate v8.32 
MySQL - 5.5.49 
*********************************************************************
*/
/*!40101 SET NAMES utf8 */;

insert into `tpl_mpng_rule` (`tpl_no`, `condition_desc`, `class_no`, `tms`, `function_id`) values('101','(1 < new Integer(days)) and (new Integer(days) <= 7)',NULL,NULL,'COMM_001');
insert into `tpl_mpng_rule` (`tpl_no`, `condition_desc`, `class_no`, `tms`, `function_id`) values('100','(0 < new Integer(days)) and (new Integer(days) <= 1)',NULL,NULL,'COMM_001');
insert into `tpl_mpng_rule` (`tpl_no`, `condition_desc`, `class_no`, `tms`, `function_id`) values('102','(7 < new Integer(days)) and (new Integer(days) <= 30)',NULL,NULL,'COMM_001');
insert into `tpl_mpng_rule` (`tpl_no`, `condition_desc`, `class_no`, `tms`, `function_id`) values('103','(30 < new Integer(days))',NULL,NULL,'COMM_001');
