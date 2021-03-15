/*==============================================================*/
/* database: yxz_edu_ems                                       */
/* description:  ���                                          */
/*==============================================================*/

drop table if exists ems_category;

drop table if exists ems_paper;

drop table if exists ems_paper_item;

drop table if exists ems_paper_item_images;

drop table if exists ems_paper_item_option;

drop table if exists ems_paper_item_type;



/*==============================================================*/
/* Table: ems_category                                             */
/*==============================================================*/
create table ems_category
(
   id                   bigint not null auto_increment comment 'id',
   institution_id       bigint comment '��������id',   
   name                 varchar(50) comment '�������',   
   parent_cid           bigint comment '������id',
   cat_level            int comment '�㼶',
   sort                 int comment '����',
   goods_count          int comment '�Ծ�����',
   icon                 char(255) comment 'ͼ���ַ',
   
   logic_deleted        tinyint comment '�Ƿ�ɾ��[0-��ɾ��1δɾ]',
   create_time          datetime comment '����ʱ��',
   update_time          datetime comment '����ʱ��',
   user_id              bigint comment '������',
   primary key (id)
);

alter table ems_category comment '�Ծ�����';


/*==============================================================*/
/* Table: ems_paper                             */
/*==============================================================*/
create table ems_paper
(
   id                   bigint not null auto_increment comment 'id',
   catagory_id          bigint comment '���id',
   paper_type_id		bigint comment '�Ծ�����id',
   paper_no             varchar(64) comment '�Ծ���',
   name                 varchar(64) comment '�Ծ�����',
   score 				bigint comment '�ܷ�',
   items 				bigint comment '��������',
   limited_time         bigint comment '����ʱ��',
   browse_num 			bigint comment '�����',
   test_num				bigint comment '������',
 
   logic_deleted        tinyint comment '�Ƿ�ɾ��[0-��ɾ��1δɾ]',
   create_time          datetime comment '����ʱ��',
   update_time          datetime comment '����ʱ��',  
   user_id              bigint comment '������',   
   primary key (id)
);

alter table ems_paper comment '�Ծ�';


/*==============================================================*/
/* Table: ems_paper_item                             */
/*==============================================================*/
create table ems_paper_item
(
   id                   bigint not null auto_increment comment 'id',
   paper_id				bigint comment '�Ծ�id',

   name                 varchar(64) comment '��������',
   item_type_id 		bigint comment '��������id',
   score 				bigint comment '�÷�',
   answer               varchar(64) comment '�����',
   
 
   logic_deleted        tinyint comment '�Ƿ�ɾ��[0-��ɾ��1δɾ]',
   create_time          datetime comment '����ʱ��',
   update_time          datetime comment '����ʱ��',  
   user_id              bigint comment '������',   
   primary key (id)
);

alter table ems_paper_item comment '����';

/*==============================================================*/
/* Table: ems_paper_item_option                             */
/*==============================================================*/
create table ems_paper_item_option
(
   id                   bigint not null auto_increment comment 'id',
   paper_item_id		 bigint comment '����id',
   name                 varchar(64) comment 'ѡ������', 
 
   logic_deleted        tinyint comment '�Ƿ�ɾ��[0-��ɾ��1δɾ]',
   create_time          datetime comment '����ʱ��',
   update_time          datetime comment '����ʱ��',  
   user_id              bigint comment '������',   
   primary key (id)
);

alter table ems_paper_item_option comment '����ѡ��';

/*==============================================================*/
/* Table: ems_paper_item_type                                   */
/*==============================================================*/
create table ems_paper_item_type
(
   id                   bigint not null auto_increment comment 'id',  
   name                 varchar(64) comment '��ѡ����ѡ����д',

 
   logic_deleted        tinyint comment '�Ƿ�ɾ��[0-��ɾ��1δɾ]',
   create_time          datetime comment '����ʱ��',
   update_time          datetime comment '����ʱ��',  
   user_id              bigint comment '������',   
   primary key (id)
);

alter table ems_paper_item_type comment '��������';

/*==============================================================*/
/* Table: ems_paper_item_images                                        */
/*==============================================================*/
create table ems_paper_item_images
(
   id                   bigint not null auto_increment comment 'id',
   paper_item_id        bigint comment 'paper_item',
   img_url              varchar(255) comment 'ͼƬ��ַ',
   img_sort             int comment '����',
   default_img          int comment 'Ĭ��ͼ[0 - ����Ĭ��ͼ��1 - ��Ĭ��ͼ]',
   primary key (id)
);

alter table ems_paper_item_images comment '����ͼƬ';
