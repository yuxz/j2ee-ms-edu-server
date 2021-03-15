package com.yxz.edu.ims.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 班级/课程类型
 * 
 * @author XINZHONG YU
 * @email yxz20180810@gmail.com
 * @date 2021-03-15 09:31:08
 */

@Data
@TableName("ims_class_type")
public class ClassTypeEntity implements Serializable {
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
	 * 校区id
	 */
	private Long campusId;
	/**
	 * 班级/课程类型名称
	 */
	private String name;
	/**
	 * 备注
	 */
	private String note;
	/**
	 * 是否被删除[0-已删，1未删]
	 */
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

}