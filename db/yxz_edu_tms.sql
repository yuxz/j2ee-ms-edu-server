drop table if exists tms_teacher;
drop table if exists tms_teacher_class;
/*==============================================================*/
/* Table: tms_teacher                             */
/*==============================================================*/
create table tms_teacher
(
   id                   bigint not null auto_increment comment 'id',
   institution_id       bigint comment '教育机构id',
   name                 varchar(64) comment '教师姓名',
   password             varchar(64) comment '密码',
   gender               tinyint comment '性别',
   birth                date comment '生日',
   mobile               varchar(20) comment '手机号码',
   email                varchar(64) comment '邮箱',    
   hire_time            datetime comment '入职时间',
   leave_time            datetime comment '离职时间',
    is_actived           tinyint      comment '是否激活',
   note                 varchar(255) comment '备注',
   token 				varchar(255) comment 'token',  
   logic_deleted        tinyint comment '是否被删除[0-已删，1未删]',
   create_time          datetime comment '创建时间',
   update_time          datetime comment '更改时间',  
   user_id              bigint comment '创建人',
   primary key (id)
);

alter table tms_teacher comment '教师';

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



