drop table if exists ims_institution;

drop table if exists ims_campus;

drop table if exists ims_campus_training_schedule;

drop table if exists ims_class;

drop table if exists ims_class_type;

drop table if exists ims_classroom;

/*==============================================================*/
/* Table: ims_institution                                       */
/*==============================================================*/
create table ims_institution
(
   id                   bigint not null auto_increment comment 'id',
   name                 varchar(64) comment '教育机构名称',
   password             varchar(64) comment '密码',
   leader               varchar(64) comment '机构负责人',
   mobile               varchar(20) comment '手机号码',
   address  			varchar(64) comment '地址',
   email                varchar(64) comment '邮箱',
   url                  varchar(64) comment '网站',
   photo                varchar(500) comment '头像',   
   note                 varchar(255) comment '备注',  
   
   token 				varchar(255) comment 'token',
   is_actived           tinyint      comment '是否激活',
   logic_deleted        tinyint comment '是否被删除[0-已删，1未删]',
   create_time          datetime comment '创建时间',
   update_time          datetime comment '更改时间',
   user_id              bigint comment '创建人',
   primary key (id)
);

alter table ims_institution comment '教育机构';

/*==============================================================*/
/* Table: ims_campus                                            */
/*==============================================================*/
create table ims_campus
(
   id                   bigint not null auto_increment comment 'id',
   institution_id       bigint comment '教育机构id',
   name                 varchar(64) comment '分校名称',
   address  			varchar(64) comment '地址',
   leader               varchar(64) comment '机构负责人',
   mobile               varchar(20) comment '手机号码',
   email                varchar(64) comment '邮箱',
   url                  varchar(64) comment '网站',   
   note                 varchar(255) comment '备注',  
   
    
   logic_deleted        tinyint comment '是否被删除[0-已删，1未删]',
   create_time          datetime comment '创建时间',
   update_time          datetime comment '更改时间',
   user_id              bigint comment '创建人',
   primary key (id)
);

alter table ims_campus comment '校区';

/*==============================================================*/
/* Table: ims_campus_training_schedule                                            */
/*==============================================================*/
create table ims_campus_training_schedule
(
   id                   bigint not null auto_increment comment 'id',
   institution_id       bigint comment '教育机构id',
   campus_id			bigint comment '校区id',
   name                 varchar(100) comment 'week',  
   started			    varchar(20) comment 'Start time',  
   ended                 varchar(20) comment 'End  time',  
   note                 varchar(255) comment '备注',     
   logic_deleted        tinyint comment '是否被删除[0-已删，1未删]',
   created              datetime comment '创建时间',
   updated              datetime comment '更改时间',
   user_id              bigint comment '创建人',
   primary key (id)
);

alter table ims_campus_training_schedule comment 'campus training schedule';

/*==============================================================*/
/* Table: ims_class                                             */
/*==============================================================*/
create table ims_class
(
   id                   bigint not null auto_increment comment 'id',
   institution_id       bigint comment '教育机构id',
   campus_id			bigint comment '校区id',
   class_type_id		bigint comment '班级类型id',
   classroom_id  	    bigint comment '教室id', 
   name                 varchar(64) comment '班级名称', 
   start_time          datetime comment '开班时间',   
   end_time          datetime comment '结班时间', 
   is_finished tinyint comment '是否结业',
   note                 varchar(255) comment '备注',     
    
   logic_deleted        tinyint comment '是否被删除[0-已删，1未删]',
   create_time          datetime comment '创建时间',
   update_time          datetime comment '更改时间',
   user_id              bigint comment '创建人',
   primary key (id)
);

alter table ims_class comment '班级';


/*==============================================================*/
/* Table: ims_classroom                                         */
/*==============================================================*/
create table ims_classroom
(
   id                   bigint not null auto_increment comment 'id',
   institution_id       bigint comment '教育机构id',
   campus_id			bigint comment '校区id',
   name                 varchar(64) comment '教室名称',
   classroom  			varchar(64) comment '教室', 
   note                 varchar(255) comment '备注',     
    
   logic_deleted        tinyint comment '是否被删除[0-已删，1未删]',
   create_time          datetime comment '创建时间',
   update_time          datetime comment '更改时间',
   user_id              bigint comment '创建人',
   primary key (id)
);

alter table ims_classroom comment '教室';

/*==============================================================*/
/* Table: ims_class_type                                             */
/*==============================================================*/
create table ims_class_type
(
   id                   bigint not null auto_increment comment 'id',
   institution_id       bigint comment '教育机构id',
   campus_id			bigint comment '校区id',
   name                 varchar(64) comment '班级/课程类型名称',   
   note                 varchar(255) comment '备注',     
    
   logic_deleted        tinyint comment '是否被删除[0-已删，1未删]',
   create_time          datetime comment '创建时间',
   update_time          datetime comment '更改时间',
   user_id              bigint comment '创建人',
   primary key (id)
);

alter table ims_class_type comment '班级/课程类型';