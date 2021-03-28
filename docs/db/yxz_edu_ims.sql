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
   name                 varchar(64) comment '������������',
   password             varchar(64) comment '����',
   leader               varchar(64) comment '����������',
   mobile               varchar(20) comment '�ֻ�����',
   address  			varchar(64) comment '��ַ',
   email                varchar(64) comment '����',
   url                  varchar(64) comment '��վ',
   photo                varchar(500) comment 'ͷ��',   
   note                 varchar(255) comment '��ע',  
   
   token 				varchar(255) comment 'token',
   is_actived           tinyint      comment '�Ƿ񼤻�',
   logic_deleted        tinyint comment '�Ƿ�ɾ��[0-��ɾ��1δɾ]',
   create_time          datetime comment '����ʱ��',
   update_time          datetime comment '����ʱ��',
   user_id              bigint comment '������',
   primary key (id)
);

alter table ims_institution comment '��������';

/*==============================================================*/
/* Table: ims_campus                                            */
/*==============================================================*/
create table ims_campus
(
   id                   bigint not null auto_increment comment 'id',
   institution_id       bigint comment '��������id',
   name                 varchar(64) comment '��У����',
   address  			varchar(64) comment '��ַ',
   leader               varchar(64) comment '����������',
   mobile               varchar(20) comment '�ֻ�����',
   email                varchar(64) comment '����',
   url                  varchar(64) comment '��վ',   
   note                 varchar(255) comment '��ע',  
   
    
   logic_deleted        tinyint comment '�Ƿ�ɾ��[0-��ɾ��1δɾ]',
   create_time          datetime comment '����ʱ��',
   update_time          datetime comment '����ʱ��',
   user_id              bigint comment '������',
   primary key (id)
);

alter table ims_campus comment 'У��';

/*==============================================================*/
/* Table: ims_campus_training_schedule                                            */
/*==============================================================*/
create table ims_campus_training_schedule
(
   id                   bigint not null auto_increment comment 'id',
   institution_id       bigint comment '��������id',
   campus_id			bigint comment 'У��id',
   name                 varchar(100) comment 'week',  
   started			    varchar(20) comment 'Start time',  
   ended                 varchar(20) comment 'End  time',  
   note                 varchar(255) comment '��ע',     
   logic_deleted        tinyint comment '�Ƿ�ɾ��[0-��ɾ��1δɾ]',
   created              datetime comment '����ʱ��',
   updated              datetime comment '����ʱ��',
   user_id              bigint comment '������',
   primary key (id)
);

alter table ims_campus_training_schedule comment 'campus training schedule';

/*==============================================================*/
/* Table: ims_class                                             */
/*==============================================================*/
create table ims_class
(
   id                   bigint not null auto_increment comment 'id',
   institution_id       bigint comment '��������id',
   campus_id			bigint comment 'У��id',
   class_type_id		bigint comment '�༶����id',
   classroom_id  	    bigint comment '����id', 
   name                 varchar(64) comment '�༶����', 
   start_time          datetime comment '����ʱ��',   
   end_time          datetime comment '���ʱ��', 
   is_finished tinyint comment '�Ƿ��ҵ',
   note                 varchar(255) comment '��ע',     
    
   logic_deleted        tinyint comment '�Ƿ�ɾ��[0-��ɾ��1δɾ]',
   create_time          datetime comment '����ʱ��',
   update_time          datetime comment '����ʱ��',
   user_id              bigint comment '������',
   primary key (id)
);

alter table ims_class comment '�༶';


/*==============================================================*/
/* Table: ims_classroom                                         */
/*==============================================================*/
create table ims_classroom
(
   id                   bigint not null auto_increment comment 'id',
   institution_id       bigint comment '��������id',
   campus_id			bigint comment 'У��id',
   name                 varchar(64) comment '��������',
   classroom  			varchar(64) comment '����', 
   note                 varchar(255) comment '��ע',     
    
   logic_deleted        tinyint comment '�Ƿ�ɾ��[0-��ɾ��1δɾ]',
   create_time          datetime comment '����ʱ��',
   update_time          datetime comment '����ʱ��',
   user_id              bigint comment '������',
   primary key (id)
);

alter table ims_classroom comment '����';

/*==============================================================*/
/* Table: ims_class_type                                             */
/*==============================================================*/
create table ims_class_type
(
   id                   bigint not null auto_increment comment 'id',
   institution_id       bigint comment '��������id',
   campus_id			bigint comment 'У��id',
   name                 varchar(64) comment '�༶/�γ���������',   
   note                 varchar(255) comment '��ע',     
    
   logic_deleted        tinyint comment '�Ƿ�ɾ��[0-��ɾ��1δɾ]',
   create_time          datetime comment '����ʱ��',
   update_time          datetime comment '����ʱ��',
   user_id              bigint comment '������',
   primary key (id)
);

alter table ims_class_type comment '�༶/�γ�����';