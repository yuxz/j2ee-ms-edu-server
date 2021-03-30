package com.yxz.edu.teacher.vo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.yxz.base.common.valid.AddGroup;
import com.yxz.base.common.valid.ListValue;
import com.yxz.base.common.valid.UpdateGroup;

import java.util.Date;
import lombok.Data;

/**
 * Working Experience
 * 
 * @author XINZHONG YU
 * @email yxz20180810@gmail.com
 * @date 2021-03-29 13:59:24
 */
 //TODO 添加后端校验代码
@Data
public class TeacherExperienceVo {
	

	/**
	 * id
	 */
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
