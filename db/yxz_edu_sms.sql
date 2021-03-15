drop table if exists sms_student;
drop table if exists sms_student_class;

drop table if exists sms_assess;

drop table if exists sms_assess_paper;

/*==============================================================*/
/* Table: sms_student                             */
/*==============================================================*/
create table sms_student
(
   id                   bigint not null auto_increment comment 'id',
   institution_id       bigint comment '教育机构id',
   name                 varchar(64) comment '学生姓名',
   password             varchar(64) comment '密码',
   gender               tinyint comment '性别',
   birth                date comment '生日',
   mobile               varchar(20) comment '手机号码',
   email                varchar(64) comment '邮箱',
   note                 varchar(255) comment '备注', 
   is_actived           tinyint      comment '是否激活',
   
   token 				varchar(255) comment 'token',   
   logic_deleted        tinyint comment '是否被删除[0-已删，1未删]',
   create_time          datetime comment '创建时间',
   update_time          datetime comment '更改时间',  
   user_id              bigint comment '创建人',
   primary key (id)
);

alter table sms_student comment '学生';

/*==============================================================*/
/* Table: sms_student_class                                     */
/*==============================================================*/
create table sms_student_class
(
   id                   bigint not null auto_increment comment 'id',
   class_id			    bigint comment '班级id',
   student_id           bigint comment '学生id',   
   note                 varchar(255) comment '备注',     
    
   logic_deleted        tinyint comment '是否被删除[0-未删，1-已删]',
   create_time          datetime comment '创建时间',
   update_time          datetime comment '更改时间',
   user_id              bigint comment '创建人',
   primary key (id)
);

alter table sms_student_class comment '班级学生';


/*==============================================================*/
/* Table: sms_assess                             */
/*==============================================================*/
create table sms_assess
(
   id                   bigint not null auto_increment comment 'id',
   student_id           bigint comment '学生id',  
   paper_id				bigint comment '试卷id',
   score 				bigint comment '得分',
 
   logic_deleted        tinyint comment '是否被删除[0-已删，1未删]',
   create_time          datetime comment '创建时间',
   update_time          datetime comment '更改时间',  
   user_id              bigint comment '创建人',   
   primary key (id)
);

alter table sms_assess comment '学生测试评估';

/*==============================================================*/
/* Table: sms_assess_paper                             */
/*==============================================================*/
create table sms_assess_paper
(
   id                   bigint not null auto_increment comment 'id',
   student_id           bigint comment '学生id',  
   paper_id				bigint comment '试卷id',
   anwser 				varchar(64) comment '单选，多选、填写',
   score 				bigint comment '得分',
   
   logic_deleted        tinyint comment '是否被删除[0-已删，1未删]',
   create_time          datetime comment '创建时间',
   update_time          datetime comment '更改时间',  
   user_id              bigint comment '创建人',   
   primary key (id)
);

alter table sms_assess_paper comment '学生测试评估试卷明细';

