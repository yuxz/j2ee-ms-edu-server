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

/*==============================================================*/
/* Table: cms_like                                              */
/*==============================================================*/
CREATE TABLE cms_like (
  id                            bigint  NOT NULL AUTO_INCREMENT comment 'id', 
  sent                          DATETIME(6) NOT NULL,
  user_id                       bigint NOT NULL,
  object_id                     bigint UNSIGNED NOT NULL,
  content_type_id               bigint NOT NULL, 
  PRIMARY KEY (id),
  UNIQUE KEY cms_like_sender_id_receiver_conte_bae4f43c_uniq (user_id,ontent_type_id,object_id),
  KEY cms_like_receiver_content_typ_9e4c743c_fk_django_co (content_type_id)  
) ;
alter table cms_like comment 'like';


/*==============================================================*/
/* Table: cms_comment                                              */
/*==============================================================*/

CREATE TABLE cms_comment (
  id                            bigint  NOT NULL AUTO_INCREMENT comment 'id',
  name                          varchar(100) NOT NULL,
  email                         varchar(255) NOT NULL,
  website                       varchar(255) NOT NULL, 
  comment                       longtext NOT NULL,
  submitted                     datetime(6) NOT NULL,
  ip_address                    char(39) DEFAULT NULL,
  public                        tinyint(1) NOT NULL,
  user_id                       bigint  DEFAULT NULL,
  object_id                     bigint NOT NULL,
  content_type_id               bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY cms_comment_author_id_efdf5c7b_fk_auth_user_id (user_id),
  KEY cms_comment_content_type_id_98f3da4b_fk_django_co (content_type_id)  
);

alter table cms_comment comment 'comment';


/*==============================================================*/
/* Table: cms_favorite                                              */
/*==============================================================*/

CREATE TABLE cms_favorite (
  id                            bigint  NOT NULL AUTO_INCREMENT comment 'id',   
  submited                      datetime(6) NOT NULL, 
  user_id                       bigint  DEFAULT NULL,
  object_id                     bigint NOT NULL,
  content_type_id               bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY cms_favorite_author_id_efdf5c7b_fk_auth_user_id (user_id),
  KEY cms_favorite_content_type_id_98f3da4b_fk_django_co (content_type_id)  
);

alter table cms_favorite comment 'favorite';

/*==============================================================*/
/* Table: cms_click                                              */
/*==============================================================*/
CREATE TABLE cms_click (
  id                            bigint  NOT NULL AUTO_INCREMENT comment 'id',   
  view_count                    bigint NOT NULL, 
  object_id                     bigint NOT NULL,
  content_type_id               bigint NOT NULL,
  PRIMARY KEY (`id`), 
  KEY cms_click_content_type_id_98f3da4b_fk_django_co (content_type_id)  
);

alter table cms_click comment 'click';

/*==============================================================*/
/* Table: cms_content_type                                              */
/*==============================================================*/

CREATE TABLE cms_content_type (
  id                            bigint  NOT NULL AUTO_INCREMENT comment 'id',
  app_label                     varchar(100) NOT NULL,
  model                         varchar(100) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY content_type_app_label_model_76bd3d3b_uniq (app_label,model)
) 

alter table cms_content_type comment 'content type';