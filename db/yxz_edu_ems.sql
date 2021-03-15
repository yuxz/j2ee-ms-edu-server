/*==============================================================*/
/* database: yxz_edu_ems                                       */
/* description:  题库                                          */
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
   institution_id       bigint comment '教育机构id',   
   name                 varchar(50) comment '题库名称',   
   parent_cid           bigint comment '父分类id',
   cat_level            int comment '层级',
   sort                 int comment '排序',
   goods_count          int comment '试卷数量',
   icon                 char(255) comment '图标地址',
   
   logic_deleted        tinyint comment '是否被删除[0-已删，1未删]',
   create_time          datetime comment '创建时间',
   update_time          datetime comment '更改时间',
   user_id              bigint comment '创建人',
   primary key (id)
);

alter table ems_category comment '试卷类型';


/*==============================================================*/
/* Table: ems_paper                             */
/*==============================================================*/
create table ems_paper
(
   id                   bigint not null auto_increment comment 'id',
   catagory_id          bigint comment '题库id',
   paper_type_id		bigint comment '试卷类型id',
   paper_no             varchar(64) comment '试卷编号',
   name                 varchar(64) comment '试卷名称',
   score 				bigint comment '总分',
   items 				bigint comment '试题总数',
   limited_time         bigint comment '答题时限',
   browse_num 			bigint comment '浏览量',
   test_num				bigint comment '测试量',
 
   logic_deleted        tinyint comment '是否被删除[0-已删，1未删]',
   create_time          datetime comment '创建时间',
   update_time          datetime comment '更改时间',  
   user_id              bigint comment '创建人',   
   primary key (id)
);

alter table ems_paper comment '试卷';


/*==============================================================*/
/* Table: ems_paper_item                             */
/*==============================================================*/
create table ems_paper_item
(
   id                   bigint not null auto_increment comment 'id',
   paper_id				bigint comment '试卷id',

   name                 varchar(64) comment '试题名称',
   item_type_id 		bigint comment '试题类型id',
   score 				bigint comment '得分',
   answer               varchar(64) comment '试题答案',
   
 
   logic_deleted        tinyint comment '是否被删除[0-已删，1未删]',
   create_time          datetime comment '创建时间',
   update_time          datetime comment '更改时间',  
   user_id              bigint comment '创建人',   
   primary key (id)
);

alter table ems_paper_item comment '试题';

/*==============================================================*/
/* Table: ems_paper_item_option                             */
/*==============================================================*/
create table ems_paper_item_option
(
   id                   bigint not null auto_increment comment 'id',
   paper_item_id		 bigint comment '试题id',
   name                 varchar(64) comment '选项名称', 
 
   logic_deleted        tinyint comment '是否被删除[0-已删，1未删]',
   create_time          datetime comment '创建时间',
   update_time          datetime comment '更改时间',  
   user_id              bigint comment '创建人',   
   primary key (id)
);

alter table ems_paper_item_option comment '试题选项';

/*==============================================================*/
/* Table: ems_paper_item_type                                   */
/*==============================================================*/
create table ems_paper_item_type
(
   id                   bigint not null auto_increment comment 'id',  
   name                 varchar(64) comment '单选，多选、填写',

 
   logic_deleted        tinyint comment '是否被删除[0-已删，1未删]',
   create_time          datetime comment '创建时间',
   update_time          datetime comment '更改时间',  
   user_id              bigint comment '创建人',   
   primary key (id)
);

alter table ems_paper_item_type comment '试题类型';

/*==============================================================*/
/* Table: ems_paper_item_images                                        */
/*==============================================================*/
create table ems_paper_item_images
(
   id                   bigint not null auto_increment comment 'id',
   paper_item_id        bigint comment 'paper_item',
   img_url              varchar(255) comment '图片地址',
   img_sort             int comment '排序',
   default_img          int comment '默认图[0 - 不是默认图，1 - 是默认图]',
   primary key (id)
);

alter table ems_paper_item_images comment '试题图片';
