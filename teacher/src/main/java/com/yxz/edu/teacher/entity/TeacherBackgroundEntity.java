package com.yxz.edu.teacher.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * Education Background
 * 
 * @author XINZHONG YU
 * @email yxz20180810@gmail.com
 * @date 2021-03-29 13:59:24
 */

@Data
@TableName("tms_teacher_background")
public class TeacherBackgroundEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	
@TableId
	private Long id;
	/**
	 * 老师
	 */
	private Long teacherId;
	/**
	 * start time
	 */
	private Date started;
	/**
	 * end time
	 */
	private Date ended;
	/**
	 * College
	 */
	private String name;
	/**
	 * Qualification&major
	 */
	private String qualiMajor;
	/**
	 * Memo
	 */
	private String note;
	/**
	 * whether be deleted[0-deleted，1.not deleted]
	 */
	private Integer logicDeleted;
	/**
	 * Created Time
	 */
	private Date created;
	/**
	 * Updated Time
	 */
	private Date updated;
	/**
	 * Creator
	 */
	private Long userId;

}
