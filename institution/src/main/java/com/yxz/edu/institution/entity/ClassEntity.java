package com.yxz.edu.institution.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 班级
 * 
 * @author XINZHONG YU
 * @email yxz20180810@gmail.com
 * @date 2021-03-15 13:06:01
 */

@Data
@TableName("ims_class")
public class ClassEntity implements Serializable {
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
	 * schedule id
	 */
	private Long campusScheduleId;
	/**
	 * 班级类型id
	 */
	private Long classTypeId;
	/**
	 * 年级id
	 */
	private Long classLevelId;
	/**
	 * 教室id
	 */
	private Long classroomId;
	/**
	 * 班级名称
	 */
	private String name;
	/**
	 * 班级最多人數
	 */
	private Integer maximum;
	/**
	 * 开班时间
	 */
	private Date started;
	/**
	 * 结班时间
	 */
	private Date ended;
	/**
	 * 是否结业
	 */
	private Integer status;
	/**
	 * 备注
	 */
	private String note;
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
	
	/**
	 * 
	 */
	@TableField(exist = false)
	private Integer totalCount;
	
	/**
	 * 
	 */
	@TableField(exist = false)
	private String quarter;
	/**
	 * 
	 */
	@TableField(exist = false)
	private String startYear;

}
