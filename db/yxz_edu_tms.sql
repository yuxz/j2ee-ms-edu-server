drop table if exists tms_teacher;
drop table if exists tms_teacher_class;
/*==============================================================*/
/* Table: tms_teacher                             */
/*==============================================================*/
create table tms_teacher
(
   id                   bigint not null auto_increment comment 'id',
   institution_id       bigint comment '��������id',
   name                 varchar(64) comment '��ʦ����',
   password             varchar(64) comment '����',
   gender               tinyint comment '�Ա�',
   birth                date comment '����',
   mobile               varchar(20) comment '�ֻ�����',
   email                varchar(64) comment '����',    
   hire_time            datetime comment '��ְʱ��',
   leave_time            datetime comment '��ְʱ��',
    is_actived           tinyint      comment '�Ƿ񼤻�',
   note                 varchar(255) comment '��ע',
   token 				varchar(255) comment 'token',  
   logic_deleted        tinyint comment '�Ƿ�ɾ��[0-��ɾ��1δɾ]',
   create_time          datetime comment '����ʱ��',
   update_time          datetime comment '����ʱ��',  
   user_id              bigint comment '������',
   primary key (id)
);

alter table tms_teacher comment '��ʦ';

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



