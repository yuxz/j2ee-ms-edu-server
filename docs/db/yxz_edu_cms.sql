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