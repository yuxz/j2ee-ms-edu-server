package com.yxz.edu.exam.vo;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import com.yxz.base.common.valid.AddGroup;
import com.yxz.base.common.valid.UpdateGroup;

import lombok.Data;

/**
 * 试卷类型
 * 
 * @author XINZHONG YU
 * @email yxz20180810@gmail.com
 * @date 2021-03-12 20:57:26
 */
 //TODO 添加后端校验代码
@Data
public class CategoryVo {
	

	/**
	 * id
	 */
	@NotNull(message = "修改必须指定id",groups = {UpdateGroup.class})
	@Null(message = "新增不能指定id",groups = {AddGroup.class})
	private Long id;
	/**
	 * 教育机构id
	 */
	private Long institutionId;
	/**
	 * 题库名称
	 */
	@NotBlank(message = "分类名称必须提交",groups = {AddGroup.class,UpdateGroup.class})
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

}
