/*
SQLyog Community v12.09 (64 bit)
MySQL - 5.7.20-log 
*********************************************************************
*/
/*!40101 SET NAMES utf8 */;

create table `ems_category` (
	`id` bigint (20),
	`institution_id` bigint (20),
	`name` varchar (150),
	`parent_cid` bigint (20),
	`cat_level` int (11),
	`sort` int (11),
	`goods_count` int (11),
	`icon` char (765),
	`logic_deleted` tinyint (4),
	`create_time` datetime ,
	`update_time` datetime ,
	`user_id` bigint (20)
); 
insert into `ems_category` (`id`, `institution_id`, `name`, `parent_cid`, `cat_level`, `sort`, `goods_count`, `icon`, `logic_deleted`, `create_time`, `update_time`, `user_id`) values('1',NULL,'私校奖学金计划','0','1','1','0',NULL,'0','2021-03-08 10:46:54','2021-03-08 10:46:54',NULL);
insert into `ems_category` (`id`, `institution_id`, `name`, `parent_cid`, `cat_level`, `sort`, `goods_count`, `icon`, `logic_deleted`, `create_time`, `update_time`, `user_id`) values('2',NULL,'VCE备考冲刺','0','1','2','0',NULL,'0','2021-03-08 10:46:54','2021-03-08 10:46:54',NULL);
insert into `ems_category` (`id`, `institution_id`, `name`, `parent_cid`, `cat_level`, `sort`, `goods_count`, `icon`, `logic_deleted`, `create_time`, `update_time`, `user_id`) values('3',NULL,'单科基础强化','0','1','3','0',NULL,'0','2021-03-08 10:46:54','2021-03-08 10:46:54',NULL);
insert into `ems_category` (`id`, `institution_id`, `name`, `parent_cid`, `cat_level`, `sort`, `goods_count`, `icon`, `logic_deleted`, `create_time`, `update_time`, `user_id`) values('4',NULL,'学术英文培优','0','1','4','0',NULL,'0','2021-03-08 10:46:54','2021-03-08 10:46:54',NULL);
insert into `ems_category` (`id`, `institution_id`, `name`, `parent_cid`, `cat_level`, `sort`, `goods_count`, `icon`, `logic_deleted`, `create_time`, `update_time`, `user_id`) values('5',NULL,'aeas私校入学考试','0','1','5','0',NULL,'0','2021-03-08 10:46:54','2021-03-08 10:46:54',NULL);
insert into `ems_category` (`id`, `institution_id`, `name`, `parent_cid`, `cat_level`, `sort`, `goods_count`, `icon`, `logic_deleted`, `create_time`, `update_time`, `user_id`) values('6',NULL,'一年级','3','2','1','0',NULL,'0','2021-03-08 10:46:54','2021-03-08 10:46:54',NULL);
insert into `ems_category` (`id`, `institution_id`, `name`, `parent_cid`, `cat_level`, `sort`, `goods_count`, `icon`, `logic_deleted`, `create_time`, `update_time`, `user_id`) values('7',NULL,'二年级','3','2','2','0',NULL,'0','2021-03-08 10:46:54','2021-03-08 10:46:54',NULL);
insert into `ems_category` (`id`, `institution_id`, `name`, `parent_cid`, `cat_level`, `sort`, `goods_count`, `icon`, `logic_deleted`, `create_time`, `update_time`, `user_id`) values('8',NULL,'三年级','3','2','3','0',NULL,'0','2021-03-08 10:46:54','2021-03-08 10:46:54',NULL);
insert into `ems_category` (`id`, `institution_id`, `name`, `parent_cid`, `cat_level`, `sort`, `goods_count`, `icon`, `logic_deleted`, `create_time`, `update_time`, `user_id`) values('9',NULL,'四年级','3','2','4','0',NULL,'0','2021-03-08 10:46:54','2021-03-08 10:46:54',NULL);
insert into `ems_category` (`id`, `institution_id`, `name`, `parent_cid`, `cat_level`, `sort`, `goods_count`, `icon`, `logic_deleted`, `create_time`, `update_time`, `user_id`) values('10',NULL,'四年级','1','2','1','0',NULL,'0','2021-03-08 10:46:54','2021-03-08 10:46:54',NULL);
insert into `ems_category` (`id`, `institution_id`, `name`, `parent_cid`, `cat_level`, `sort`, `goods_count`, `icon`, `logic_deleted`, `create_time`, `update_time`, `user_id`) values('11',NULL,'五年级','1','2','2',NULL,NULL,'0','2021-03-08 10:46:54','2021-03-08 10:46:54',NULL);
insert into `ems_category` (`id`, `institution_id`, `name`, `parent_cid`, `cat_level`, `sort`, `goods_count`, `icon`, `logic_deleted`, `create_time`, `update_time`, `user_id`) values('12',NULL,'六年级','1','2','3',NULL,NULL,'0','2021-03-08 10:46:54','2021-03-08 10:46:54',NULL);
insert into `ems_category` (`id`, `institution_id`, `name`, `parent_cid`, `cat_level`, `sort`, `goods_count`, `icon`, `logic_deleted`, `create_time`, `update_time`, `user_id`) values('13',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0','2021-03-08 10:46:54','2021-03-08 10:46:54',NULL);
