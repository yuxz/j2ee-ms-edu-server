/*
SQLyog Community v12.09 (64 bit)
MySQL - 5.7.20-log 
*********************************************************************
*/
/*!40101 SET NAMES utf8 */;

create table `sms_student` (
	`id` bigint (20),
	`institution_id` bigint (20),
	`campus_id` bigint (20),
	`class_type_id` bigint (20),
	`name` varchar (192),
	`first_name` varchar (192),
	`last_name` varchar (192),
	`cname` varchar (192),
	`school` varchar (240),
	`password` varchar (192),
	`email` varchar (192),
	`mobile` varchar (60),
	`gender` tinyint (4),
	`birth` date ,
	`note` varchar (765),
	`registered` datetime ,
	`is_actived` tinyint (4),
	`token` varchar (765),
	`logic_deleted` tinyint (4),
	`created` datetime ,
	`updated` datetime ,
	`user_id` bigint (20)
); 
insert into `sms_student` (`id`, `institution_id`, `campus_id`, `class_type_id`, `name`, `first_name`, `last_name`, `cname`, `school`, `password`, `email`, `mobile`, `gender`, `birth`, `note`, `registered`, `is_actived`, `token`, `logic_deleted`, `created`, `updated`, `user_id`) values('1','1','3',NULL,'Johnson',NULL,NULL,NULL,NULL,NULL,'yu_xinzhong@163.com','0481982299','1','2000-03-07','',NULL,'0',NULL,NULL,NULL,NULL,NULL);
insert into `sms_student` (`id`, `institution_id`, `campus_id`, `class_type_id`, `name`, `first_name`, `last_name`, `cname`, `school`, `password`, `email`, `mobile`, `gender`, `birth`, `note`, `registered`, `is_actived`, `token`, `logic_deleted`, `created`, `updated`, `user_id`) values('2','1','2',NULL,'Alon',NULL,NULL,NULL,NULL,NULL,'yu_xinzhong@163.com','0481982299','2','2000-03-07','',NULL,'0',NULL,NULL,NULL,NULL,NULL);
insert into `sms_student` (`id`, `institution_id`, `campus_id`, `class_type_id`, `name`, `first_name`, `last_name`, `cname`, `school`, `password`, `email`, `mobile`, `gender`, `birth`, `note`, `registered`, `is_actived`, `token`, `logic_deleted`, `created`, `updated`, `user_id`) values('3','1','2',NULL,'Mary',NULL,NULL,NULL,NULL,NULL,'u_xinzhong@163.com','0481982200','2','2000-03-05','11',NULL,'0',NULL,NULL,NULL,NULL,NULL);
insert into `sms_student` (`id`, `institution_id`, `campus_id`, `class_type_id`, `name`, `first_name`, `last_name`, `cname`, `school`, `password`, `email`, `mobile`, `gender`, `birth`, `note`, `registered`, `is_actived`, `token`, `logic_deleted`, `created`, `updated`, `user_id`) values('4','1','1','1','Mike',NULL,NULL,NULL,NULL,NULL,'yxz20180810@gmail.com','','1','2021-03-16','',NULL,'0',NULL,NULL,NULL,NULL,NULL);
insert into `sms_student` (`id`, `institution_id`, `campus_id`, `class_type_id`, `name`, `first_name`, `last_name`, `cname`, `school`, `password`, `email`, `mobile`, `gender`, `birth`, `note`, `registered`, `is_actived`, `token`, `logic_deleted`, `created`, `updated`, `user_id`) values('5','1','1','1','John',NULL,NULL,NULL,NULL,NULL,'yxz20180810@gmail.com','','1','2021-03-28','',NULL,'0',NULL,NULL,NULL,NULL,NULL);
insert into `sms_student` (`id`, `institution_id`, `campus_id`, `class_type_id`, `name`, `first_name`, `last_name`, `cname`, `school`, `password`, `email`, `mobile`, `gender`, `birth`, `note`, `registered`, `is_actived`, `token`, `logic_deleted`, `created`, `updated`, `user_id`) values('6','1','1','1','Meacle',NULL,NULL,NULL,NULL,NULL,'yxz20180810@gmail.com','','1','2021-02-28','',NULL,'0',NULL,NULL,NULL,NULL,NULL);
insert into `sms_student` (`id`, `institution_id`, `campus_id`, `class_type_id`, `name`, `first_name`, `last_name`, `cname`, `school`, `password`, `email`, `mobile`, `gender`, `birth`, `note`, `registered`, `is_actived`, `token`, `logic_deleted`, `created`, `updated`, `user_id`) values('7','1','1','1','student1',NULL,NULL,NULL,NULL,NULL,'student@gmail.com','','1','2021-03-23','',NULL,'0',NULL,NULL,NULL,NULL,NULL);
insert into `sms_student` (`id`, `institution_id`, `campus_id`, `class_type_id`, `name`, `first_name`, `last_name`, `cname`, `school`, `password`, `email`, `mobile`, `gender`, `birth`, `note`, `registered`, `is_actived`, `token`, `logic_deleted`, `created`, `updated`, `user_id`) values('8',NULL,'1',NULL,'John',NULL,NULL,NULL,NULL,NULL,'yu_xinzhong@163.com','0481982299','1','2021-02-28','',NULL,'0',NULL,NULL,NULL,NULL,NULL);
insert into `sms_student` (`id`, `institution_id`, `campus_id`, `class_type_id`, `name`, `first_name`, `last_name`, `cname`, `school`, `password`, `email`, `mobile`, `gender`, `birth`, `note`, `registered`, `is_actived`, `token`, `logic_deleted`, `created`, `updated`, `user_id`) values('9',NULL,'3',NULL,'John',NULL,NULL,NULL,NULL,NULL,'yu_xinzhong@163.com','','2','2021-03-23','',NULL,'0',NULL,NULL,NULL,NULL,NULL);
