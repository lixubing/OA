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
) ENGINE=InnoDB DEFAULT CHARSET=utf8

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8

CREATE TABLE `score` (
  `student_no` varchar(16) NOT NULL,
  `student_name` varchar(16) NOT NULL,
  `course_no` varchar(16) NOT NULL,
  `course_name` varchar(16) NOT NULL,
  `teacher_no` varchar(16) NOT NULL,
  `teacher_name` varchar(16) NOT NULL,
  `score` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8

CREATE TABLE `sequence` (
  `sequence_id` varchar(32) NOT NULL COMMENT '序列的名称',
  `num` int(11) DEFAULT NULL COMMENT '当前序列值',
  `step` int(11) DEFAULT NULL COMMENT '步长',
  `min` int(11) DEFAULT NULL COMMENT '最小值',
  `max` int(11) DEFAULT NULL COMMENT '最大值',
  UNIQUE KEY `sequence_id` (`sequence_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8

CREATE TABLE `todo_avy_info` (
  `process_inst_id` varchar(32) NOT NULL,
  `avy_id` varchar(2) NOT NULL,
  `tpl_no` varchar(32) NOT NULL,
  `executor_id` varchar(32) DEFAULT NULL,
  `class_no` varchar(32) DEFAULT NULL,
  `tms` datetime DEFAULT NULL,
  `function_id` varchar(32) DEFAULT NULL,
  `can_withdraw` char(1) DEFAULT NULL,
  `can_retreat` char(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8

CREATE TABLE `tpl_avy_info` (
  `avy_id` varchar(2) NOT NULL,
  `tpl_no` varchar(32) NOT NULL,
  `executor_id` varchar(32) DEFAULT NULL,
  `class_no` varchar(32) DEFAULT NULL,
  `tms` datetime DEFAULT NULL,
  `function_id` varchar(32) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8

CREATE TABLE `tpl_mpng_rule` (
  `tpl_no` varchar(32) NOT NULL,
  `condition_desc` varchar(128) DEFAULT NULL,
  `class_no` varchar(32) DEFAULT NULL,
  `tms` datetime DEFAULT NULL,
  `function_id` varchar(32) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8
