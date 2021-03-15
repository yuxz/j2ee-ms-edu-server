package com.yxz.edu.tms.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 班级老师
 * 
 * @author XINZHONG YU
 * @email yxz20180810@gmail.com
 * @date 2021-03-15 16:32:08
 */

@Data
@TableName("tms_teacher_class")
public class TeacherClassEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	
@TableId
	private Long id;
	/**
	 * 班级id
	 */
	private Long classId;
	/**
	 * 老师id
	 */
	private Long teacherId;
	/**
	 * 备注
	 */
	private String note;
	/**
	 * 是否被删除[0-未删，1已删]
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
