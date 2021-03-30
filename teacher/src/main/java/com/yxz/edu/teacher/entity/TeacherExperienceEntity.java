package com.yxz.edu.teacher.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * Working Experience
 * 
 * @author XINZHONG YU
 * @email yxz20180810@gmail.com
 * @date 2021-03-29 13:59:24
 */

@Data
@TableName("tms_teacher_experience")
public class TeacherExperienceEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	
@TableId
	private Long id;
	/**
	 * Teacher
	 */
	private Long teacherId;
	/**
	 * Start Time
	 */
	private Date started;
	/**
	 * End Time
	 */
	private Date ended;
	/**
	 * Company Name
	 */
	private String name;
	/**
	 * Position
	 */
	private String position;
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
