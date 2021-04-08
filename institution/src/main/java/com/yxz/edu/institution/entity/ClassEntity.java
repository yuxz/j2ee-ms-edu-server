package com.yxz.edu.institution.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
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
	 * 班级类型id
	 */
	private Long classTypeId;
	/**
	 * 教室id
	 */
	private Long classroomId;
	/**
	 * 班级名称
	 */
	private String name;
	/**
	 * 开班时间
	 */
	private Date startTime;
	/**
	 * 结班时间
	 */
	private Date endTime;
	/**
	 * 是否结业
	 */
	private Integer isFinished;
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
	
	/**
	 * 
	 */
	@TableField(exist = false)
	private Integer totalCount;
	
	/**
	 * 
	 */
	@TableField(exist = false)
	private String startYear;

}
