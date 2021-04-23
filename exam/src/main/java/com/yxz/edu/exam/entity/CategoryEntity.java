package com.yxz.edu.exam.entity;

import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 试卷类型
 * 
 * @author XINZHONG YU
 * @email yxz20180810@gmail.com
 * @date 2021-03-08 11:08:06
 */
@Data
@TableName("ems_category")
public class CategoryEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId
	private Long id;
	/**
	 * 教育机构id
	 */
	private Long institutionId;
	/**
	 * 题库名称
	 */
	private String name;
	/**
	 * 父分类id
	 */
	private Long parentId;
	/**
	 * 层级
	 */
	private Integer level;
	/**
	 * 排序
	 */
	private Integer sort;
	/**
	 * 试卷数量
	 */
	private Integer totalCount;
	/**
	 * 图标地址
	 */
	private String icon;
	/**
	 * 是否被删除[0-已删，1未删]
	 */
	@TableLogic(value = "0",delval = "1")
	private Integer logicDeleted;
	/**
	 * 创建时间
	 */
	private Date created;
	/**
	 * 更改时间
	 */
	private Date updated;
	/**
	 * 创建人
	 */
	private Long userId;
	
//	/**
//	 * 子分类
//	 */
//	//若数据为空，返回客户端json数据中不包含此字段
//	@JsonInclude(JsonInclude.Include.NON_EMPTY)
//	@TableField(exist = false)
//	private List<CategoryEntity> children;
//	
//	/**
//	 * 父级分类名字
//	 */
//	@TableField(exist = false)
//	private String parentCategoryName;
	
	

}
