drop table if exists tms_teacher;
drop table if exists tms_teacher_experience;
drop table if exists tms_teacher_class;
/*==============================================================*/
/* Table: tms_teacher                             */
/*==============================================================*/
create table tms_teacher
(
   id                   bigint not null auto_increment comment 'id',
   institution_id       bigint comment '��������id',
   campus_id            bigint(20)   comment '��Уid',   
   first_name  			varchar(64) comment 'FamilyName',
   last_name  			varchar(64) comment 'GivenName',
   name                 varchar(64) comment 'Ӣ������',
   cname                varchar(64) comment '��������',   
   gender               tinyint comment '�Ա�',
   birth                date comment '����',
   mobile               varchar(20) comment '�ֻ�����',
   email                varchar(64) comment '����', 
   qq                   varchar(64) comment '����', 
   post_id 				bigint comment 'ְλ',      
   hired                datetime comment '��ְʱ��',
   leaved               datetime comment '��ְʱ��',                           
   is_leaved             tinyint(4)    comment '�Ƿ���ְ',
   is_actived           tinyint      comment '�Ƿ񼤻�',
   note                 varchar(255) comment '��ע',
   field1				varchar(100) comment 'field1',
   field2				varchar(100) comment 'field2',
   field3				varchar(100) comment 'field3',
   field4				varchar(100) comment 'field4',
   field5				varchar(100) comment 'field5',
   field6				varchar(100) comment 'field6',   
   logic_deleted        tinyint comment '�Ƿ�ɾ��[0-��ɾ��1δɾ]',
   created              datetime comment '����ʱ��',
   updated          	datetime comment '����ʱ��',  
   user_id              bigint comment '������',
   primary key (id)
);

alter table tms_teacher comment 'Teacher';

/*==============================================================*/
/* Table: tms_teacher_background                                     */
/*==============================================================*/
create table tms_teacher_background
(
   id                   bigint not null auto_increment comment 'id',
   teacher_id           bigint comment '��ʦ', 
   started            datetime comment 'start time',
   ended              datetime comment 'end time',  
   name             varchar(64) comment 'College',  
   quali_major   varchar(64) comment 'Qualification&major', 
   note                 varchar(255) comment '��ע',  
   logic_deleted        tinyint comment '�Ƿ�ɾ��[0-��ɾ��1δɾ]',
   created          datetime comment '����ʱ��',
   updated          datetime comment '����ʱ��',  
   user_id              bigint comment '������',
   primary key (id)
);
alter table tms_teacher_background comment 'Education Background';
/*==============================================================*/
/* Table: tms_teacher_experience                                     */
/*==============================================================*/
create table tms_teacher_experience
(
   id                   bigint not null auto_increment comment 'id',
   teacher_id           bigint comment '��ʦ', 
   started            datetime comment 'start time',
   ended              datetime comment 'end time',  
   name             varchar(64) comment 'Company Name',  
   position   varchar(64) comment 'Position', 
   note                 varchar(255) comment '��ע',  
   logic_deleted        tinyint comment '�Ƿ�ɾ��[0-��ɾ��1δɾ]',
   created          datetime comment '����ʱ��',
   updated          datetime comment '����ʱ��',  
   user_id              bigint comment '������',
   primary key (id)
);
alter table tms_teacher_experience comment 'Working Experience';

/*==============================================================*/
/* Table: tms_teacher_class                                     */
/*==============================================================*/
create table tms_teacher_class
(
   id                   bigint not null auto_increment comment 'id',
   class_id			    bigint comment '�༶id',
   teacher_id           bigint comment '��ʦid',   
   note                 varchar(255) comment '��ע',     
    
   logic_deleted        tinyint comment '�Ƿ�ɾ��[0-δɾ��1��ɾ]',
   create_time          datetime comment '����ʱ��',
   update_time          datetime comment '����ʱ��',
   user_id              bigint comment '������',
   primary key (id)
);

alter table tms_teacher_class comment '�༶��ʦ';



