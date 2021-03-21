CREATE TABLE `sys_code` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `code` varchar(50) COMMENT '代码',
  `name` varchar(50) COMMENT '代码名称',
  `note` varchar(255) comment '备注',   
  `logic_deleted`   tinyint comment '是否被删除[0-已删，1未删]',
  `create_time`     datetime comment '创建时间',
  `update_time`     datetime comment '更改时间',  
  `user_id`         bigint comment '创建人',  
  PRIMARY KEY (`id`)
) ENGINE=`InnoDB` DEFAULT CHARACTER SET utf8mb4 COMMENT='代码管理';

CREATE TABLE `sys_code_item` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `sys_code_id`  bigint comment '代码id',
  `code` varchar(50) COMMENT '代码项代码',
  `name` varchar(50) COMMENT '代码项名称', 
  `order_num` int COMMENT '排序',
  PRIMARY KEY (`id`)
) ENGINE=`InnoDB` DEFAULT CHARACTER SET utf8mb4 COMMENT='代码项管理';