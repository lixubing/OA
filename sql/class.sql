/*
SQLyog Ultimate v8.32 
MySQL - 5.5.49 
*********************************************************************
*/
/*!40101 SET NAMES utf8 */;

create table `class` (
	`class_no` varchar (48),
	`major_no` varchar (48),
	`major_name` varchar (48),
	`academy_no` varchar (48),
	`academy_name` varchar (48),
	`counselor_no` varchar (48),
	`counselor_name` varchar (48),
	`monitor_no` varchar (36),
	`monitor_name` varchar (48)
); 
insert into `class` (`class_no`, `major_no`, `major_name`, `academy_no`, `academy_name`, `counselor_no`, `counselor_name`, `monitor_no`, `monitor_name`) values('2020001001','001','信息技术','001','工程学院','2016001901','马化腾','202000100102','李四');
