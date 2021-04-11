/*==============================================================*/
/* database: yxz_base_cms                                       */
/* description:  内容发布系统                                   */
/*==============================================================*/

drop table if exists cms_category;

drop table if exists cms_content;





/*==============================================================*/
/* Table: cms_category                                             */
/*==============================================================*/

create table cms_category
(

   id                   bigint not null auto_increment comment 'id',
   institution_id       bigint comment '教育机构id', 
   parent_id           bigint comment '父分类id',     
   name                 varchar(50) comment '分类名称',   
   cat_level            int comment '层级',
   sort                 int comment '排序',
   goods_count          int comment '内容数量',
   icon                 char(255) comment '图标地址',   
   logic_deleted        tinyint comment '是否被删除[0-已删，1未删]',
   created          datetime comment '创建时间',
   updated          datetime comment '更改时间',
   user_id              bigint comment '创建人',
   primary key (id)
);

alter table cms_category comment '内容分类';


/*==============================================================*/
/* Table: cms_content                             */
/*==============================================================*/
create table cms_content
(
   id                   bigint not null auto_increment comment 'id',
   catagory_id          bigint comment '分类id',
   content_type_id		bigint comment '内容类型id',   
   title                varchar(200) comment '标题',
   subtitle             varchar(200) comment '副标题',
   title_desc           varchar(500) comment '描述',
   url                  varchar(500) DEFAULT NULL comment 'url',
   content              text         DEFAULT NULL comment '内容',
 
   logic_deleted        tinyint comment '是否被删除[0-已删，1未删]',
   created              datetime comment '创建时间',
   updated              datetime comment '更改时间',  
   user_id              bigint comment '创建人',   
   primary key (id)
);

alter table cms_content comment '文章';

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