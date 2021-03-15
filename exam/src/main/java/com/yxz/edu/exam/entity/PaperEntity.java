package com.yxz.edu.exam.entity;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.yxz.base.common.valid.AddGroup;
import com.yxz.base.common.valid.ListValue;
import com.yxz.base.common.valid.UpdateGroup;
import com.yxz.base.common.valid.UpdateStatusGroup;

import lombok.Data;

/**
 * 试卷
 * 
 * @author XINZHONG YU
 * @email yxz20180810@gmail.com
 * @date 2021-03-08 11:08:07
 */
@Data
@TableName("ems_paper")
public class PaperEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@NotNull(message = "修改必须指定id",groups = {UpdateGroup.class})
	@Null(message = "新增不能指定id",groups = {AddGroup.class})
	@TableId
	private Long id;
	/**
	 * 题库id
	 */
	private Long categoryId;
	/**
	 * 试卷类型id
	 */
	private Long paperTypeId;
	/**
	 * 试卷编号
	 */
	private String paperNo;	
	/**
	 * 试卷名称
	 */
	@NotBlank(message = "试卷名称必须提交",groups = {AddGroup.class,UpdateGroup.class})
	private String name;
	/**
	 * 总分
	 */
	private Long score;
	/**
	 * 试题总数
	 */
	private Long items;
	/**
	 * 答题时限
	 */
	private Long limitedTime;
	/**
	 * 浏览量
	 */
	private Long browseNum;
	/**
	 * 测试量
	 */
	private Long testNum;
	/**
	 * 是否被删除[0-已删，1未删]
	 */
	@NotNull(groups = {AddGroup.class, UpdateStatusGroup.class})
  	@ListValue(vals={0,1},groups = {AddGroup.class, UpdateStatusGroup.class})	
	private Integer logicDeleted;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 更改时间
	 */
	private Date updateTime;
	/**
	 * 创建人
	 */
	private Long userId;
	
	/** 
	 * category完整路径
	 */
	@TableField(exist = false)
	private Long[] categoryFullPath;
}
