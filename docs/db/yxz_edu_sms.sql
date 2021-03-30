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
   campus_id            bigint comment '分校id',
   campus_schedule_id    bigint comment 'campus_schedule_id',  
   class_type_id        bigint comment '班级/课程类型id',     
   name                 varchar(64) comment '学生姓名',
   first_name  			varchar(64) comment 'FamilyName',
   last_name  			varchar(64) comment 'GivenName',
   cname                varchar(64)   comment '中文姓名',  
   birth                date comment '生日',
   gender               tinyint comment '性别',   
   email                varchar(64) comment '邮箱',
   mobile               varchar(20) comment '手机号码',
   suburb               varchar(64) comment 'suburb',
   postcode				varchar(4) comment 'postcode',
   contract_one         varchar(100) comment 'contract1',
   contract_two			varchar(100) comment 'contract2',
   school				varchar(100) comment 'school',
   current_level		varchar(100) comment 'current year level in day school',
   parent               varchar(64) comment 'parent or guardian name',
   registered           datetime comment '注册时间',
   year_level 			tinyint      comment '年级',
   note                 varchar(255) comment '备注',    
   field1				varchar(100) comment 'field1',
   field2				varchar(100) comment 'field2',
   field3				varchar(100) comment 'field3',
   field4				varchar(100) comment 'field4',
   field5				varchar(100) comment 'field5',
   field6				varchar(100) comment 'field6',   
   token 				varchar(255) comment 'token',   
   is_actived           tinyint      comment '是否激活',
   logic_deleted        tinyint comment '是否被删除[0-已删，1未删]',
   created          datetime comment '创建时间',
   updated          datetime comment '更改时间',  
   user_id              bigint comment '创建人',
   primary key (id)
);

alter table sms_student comment 'Student';

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

