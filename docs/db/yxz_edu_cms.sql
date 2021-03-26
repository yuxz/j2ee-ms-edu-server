/*==============================================================*/
/* database: yxz_base_cms                                       */
/* description:  ���ݷ���ϵͳ                                   */
/*==============================================================*/

drop table if exists cms_category;

drop table if exists cms_content;





/*==============================================================*/
/* Table: cms_category                                             */
/*==============================================================*/

create table cms_category
(

   id                   bigint not null auto_increment comment 'id',
   institution_id       bigint comment '��������id', 
   parent_id           bigint comment '������id',     
   name                 varchar(50) comment '��������',   
   cat_level            int comment '�㼶',
   sort                 int comment '����',
   goods_count          int comment '��������',
   icon                 char(255) comment 'ͼ���ַ',   
   logic_deleted        tinyint comment '�Ƿ�ɾ��[0-��ɾ��1δɾ]',
   created          datetime comment '����ʱ��',
   updated          datetime comment '����ʱ��',
   user_id              bigint comment '������',
   primary key (id)
);

alter table cms_category comment '���ݷ���';


/*==============================================================*/
/* Table: cms_content                             */
/*==============================================================*/
create table cms_content
(
   id                   bigint not null auto_increment comment 'id',
   catagory_id          bigint comment '����id',
   content_type_id		bigint comment '��������id',   
   title                varchar(200) comment '����',
   subtitle             varchar(200) comment '������',
   title_desc           varchar(500) comment '����',
   url                  varchar(500) DEFAULT NULL comment 'url',
   content              text         DEFAULT NULL comment '����',
 
   logic_deleted        tinyint comment '�Ƿ�ɾ��[0-��ɾ��1δɾ]',
   created              datetime comment '����ʱ��',
   updated              datetime comment '����ʱ��',  
   user_id              bigint comment '������',   
   primary key (id)
);

alter table cms_content comment '����';