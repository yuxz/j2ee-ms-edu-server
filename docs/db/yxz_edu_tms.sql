drop table if exists tms_teacher;
drop table if exists tms_teacher_experience;
drop table if exists tms_teacher_class;
/*==============================================================*/
/* Table: tms_teacher                             */
/*==============================================================*/
create table tms_teacher
(
   id                   bigint not null auto_increment comment 'id',
   institution_id       bigint comment '教育机构id',
   campus_id            bigint(20)   comment '分校id',   
   first_name  			varchar(64) comment 'FamilyName',
   last_name  			varchar(64) comment 'GivenName',
   name                 varchar(64) comment '英文姓名',
   cname                varchar(64) comment '中文姓名',   
   gender               tinyint comment '性别',
   birth                date comment '生日',
   mobile               varchar(20) comment '手机号码',
   email                varchar(64) comment '邮箱', 
   qq                   varchar(64) comment '邮箱', 
   post_id 				bigint comment '职位',      
   hired                datetime comment '入职时间',
   leaved               datetime comment '离职时间',                           
   is_leaved             tinyint(4)    comment '是否离职',
   is_actived           tinyint      comment '是否激活',
   note                 varchar(255) comment '备注',
   field1				varchar(100) comment 'field1',
   field2				varchar(100) comment 'field2',
   field3				varchar(100) comment 'field3',
   field4				varchar(100) comment 'field4',
   field5				varchar(100) comment 'field5',
   field6				varchar(100) comment 'field6',   
   logic_deleted        tinyint comment '是否被删除[0-已删，1未删]',
   created              datetime comment '创建时间',
   updated          	datetime comment '更改时间',  
   user_id              bigint comment '创建人',
   primary key (id)
);

alter table tms_teacher comment 'Teacher';

/*==============================================================*/
/* Table: tms_teacher_background                                     */
/*==============================================================*/
create table tms_teacher_background
(
   id                   bigint not null auto_increment comment 'id',
   teacher_id           bigint comment '老师', 
   started            datetime comment 'start time',
   ended              datetime comment 'end time',  
   name             varchar(64) comment 'College',  
   quali_major   varchar(64) comment 'Qualification&major', 
   note                 varchar(255) comment '备注',  
   logic_deleted        tinyint comment '是否被删除[0-已删，1未删]',
   created          datetime comment '创建时间',
   updated          datetime comment '更改时间',  
   user_id              bigint comment '创建人',
   primary key (id)
);
alter table tms_teacher_background comment 'Education Background';
/*==============================================================*/
/* Table: tms_teacher_experience                                     */
/*==============================================================*/
create table tms_teacher_experience
(
   id                   bigint not null auto_increment comment 'id',
   teacher_id           bigint comment '老师', 
   started            datetime comment 'start time',
   ended              datetime comment 'end time',  
   name             varchar(64) comment 'Company Name',  
   position   varchar(64) comment 'Position', 
   note                 varchar(255) comment '备注',  
   logic_deleted        tinyint comment '是否被删除[0-已删，1未删]',
   created          datetime comment '创建时间',
   updated          datetime comment '更改时间',  
   user_id              bigint comment '创建人',
   primary key (id)
);
alter table tms_teacher_experience comment 'Working Experience';

/*==============================================================*/
/* Table: tms_teacher_class                                     */
/*==============================================================*/
create table tms_teacher_class
(
   id                   bigint not null auto_increment comment 'id',
   class_id			    bigint comment '班级id',
   teacher_id           bigint comment '老师id',   
   note                 varchar(255) comment '备注',     
    
   logic_deleted        tinyint comment '是否被删除[0-未删，1已删]',
   create_time          datetime comment '创建时间',
   update_time          datetime comment '更改时间',
   user_id              bigint comment '创建人',
   primary key (id)
);

alter table tms_teacher_class comment '班级老师';



