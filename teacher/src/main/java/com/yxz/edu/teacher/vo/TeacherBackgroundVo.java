package com.yxz.edu.teacher.vo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.yxz.base.common.valid.AddGroup;
import com.yxz.base.common.valid.ListValue;
import com.yxz.base.common.valid.UpdateGroup;

import java.util.Date;
import lombok.Data;

/**
 * Education Background
 * 
 * @author XINZHONG YU
 * @email yxz20180810@gmail.com
 * @date 2021-03-29 13:59:24
 */
 //TODO 添加后端校验代码
@Data
public class TeacherBackgroundVo {
	

	/**
	 * id
	 */
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
