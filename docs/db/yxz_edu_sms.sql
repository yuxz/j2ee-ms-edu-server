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
   institution_id       bigint comment '��������id',
   campus_id            bigint comment '��Уid',
   campus_schedule_id    bigint comment 'campus_schedule_id',  
   class_type_id        bigint comment '�༶/�γ�����id',     
   name                 varchar(64) comment 'ѧ������',
   first_name  			varchar(64) comment 'FamilyName',
   last_name  			varchar(64) comment 'GivenName',
   cname                varchar(64)   comment '��������',  
   birth                date comment '����',
   gender               tinyint comment '�Ա�',   
   email                varchar(64) comment '����',
   mobile               varchar(20) comment '�ֻ�����',
   suburb               varchar(64) comment 'suburb',
   postcode				varchar(4) comment 'postcode',
   contract_one         varchar(100) comment 'contract1',
   contract_two			varchar(100) comment 'contract2',
   school				varchar(100) comment 'school',
   current_level		varchar(100) comment 'current year level in day school',
   parent               varchar(64) comment 'parent or guardian name',
   registered           datetime comment 'ע��ʱ��',
   year_level 			tinyint      comment '�꼶',
   note                 varchar(255) comment '��ע',    
   field1				varchar(100) comment 'field1',
   field2				varchar(100) comment 'field2',
   field3				varchar(100) comment 'field3',
   field4				varchar(100) comment 'field4',
   field5				varchar(100) comment 'field5',
   field6				varchar(100) comment 'field6',   
   token 				varchar(255) comment 'token',   
   is_actived           tinyint      comment '�Ƿ񼤻�',
   logic_deleted        tinyint comment '�Ƿ�ɾ��[0-��ɾ��1δɾ]',
   created          datetime comment '����ʱ��',
   updated          datetime comment '����ʱ��',  
   user_id              bigint comment '������',
   primary key (id)
);

alter table sms_student comment 'Student';

/*==============================================================*/
/* Table: sms_student_class                                     */
/*==============================================================*/
create table sms_student_class
(
   id                   bigint not null auto_increment comment 'id',
   class_id			    bigint comment '�༶id',
   student_id           bigint comment 'ѧ��id',   
   note                 varchar(255) comment '��ע',     
    
   logic_deleted        tinyint comment '�Ƿ�ɾ��[0-δɾ��1-��ɾ]',
   create_time          datetime comment '����ʱ��',
   update_time          datetime comment '����ʱ��',
   user_id              bigint comment '������',
   primary key (id)
);

alter table sms_student_class comment '�༶ѧ��';


/*==============================================================*/
/* Table: sms_assess                             */
/*==============================================================*/
create table sms_assess
(
   id                   bigint not null auto_increment comment 'id',
   student_id           bigint comment 'ѧ��id',  
   paper_id				bigint comment '�Ծ�id',
   score 				bigint comment '�÷�',
 
   logic_deleted        tinyint comment '�Ƿ�ɾ��[0-��ɾ��1δɾ]',
   create_time          datetime comment '����ʱ��',
   update_time          datetime comment '����ʱ��',  
   user_id              bigint comment '������',   
   primary key (id)
);

alter table sms_assess comment 'ѧ����������';

/*==============================================================*/
/* Table: sms_assess_paper                             */
/*==============================================================*/
create table sms_assess_paper
(
   id                   bigint not null auto_increment comment 'id',
   student_id           bigint comment 'ѧ��id',  
   paper_id				bigint comment '�Ծ�id',
   anwser 				varchar(64) comment '��ѡ����ѡ����д',
   score 				bigint comment '�÷�',
   
   logic_deleted        tinyint comment '�Ƿ�ɾ��[0-��ɾ��1δɾ]',
   create_time          datetime comment '����ʱ��',
   update_time          datetime comment '����ʱ��',  
   user_id              bigint comment '������',   
   primary key (id)
);

alter table sms_assess_paper comment 'ѧ�����������Ծ���ϸ';

