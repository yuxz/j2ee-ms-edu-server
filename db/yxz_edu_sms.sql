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
   name                 varchar(64) comment 'ѧ������',
   password             varchar(64) comment '����',
   gender               tinyint comment '�Ա�',
   birth                date comment '����',
   mobile               varchar(20) comment '�ֻ�����',
   email                varchar(64) comment '����',
   note                 varchar(255) comment '��ע', 
   is_actived           tinyint      comment '�Ƿ񼤻�',
   
   token 				varchar(255) comment 'token',   
   logic_deleted        tinyint comment '�Ƿ�ɾ��[0-��ɾ��1δɾ]',
   create_time          datetime comment '����ʱ��',
   update_time          datetime comment '����ʱ��',  
   user_id              bigint comment '������',
   primary key (id)
);

alter table sms_student comment 'ѧ��';

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

