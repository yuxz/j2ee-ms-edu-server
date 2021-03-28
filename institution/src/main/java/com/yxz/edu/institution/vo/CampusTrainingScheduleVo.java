package com.yxz.edu.institution.vo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.yxz.base.common.valid.AddGroup;
import com.yxz.base.common.valid.ListValue;
import com.yxz.base.common.valid.UpdateGroup;

import java.util.Date;
import lombok.Data;

/**
 * 
 * 
 * @author XINZHONG YU
 * @email yxz20180810@gmail.com
 * @date 2021-03-28 17:50:49
 */
 //TODO 添加后端校验代码
@Data
public class CampusTrainingScheduleVo {
	

	/**
	 * id
	 */
		private Long id;
	/**
	 * Institution
	 */
	private Long institutionId;
	/**
	 * Campus
	 */
	private Long campusId;
	/**
	 * Which day of the week
	 */
	private String name;
	/**
	 * Start time
	 */
	private String started;
	/**
	 * End time
	 */
	private String ended;
	/**
	 * notes
	 */
	private String note;
	/**
	 * 是否被删除[0-已删，1未删]
	 */
	private Integer logicDeleted;
	/**
	 * Create date
	 */
	private Date created;
	/**
	 * Update date
	 */
	private Date updated;
	/**
	 * creator
	 */
	private Long userId;

}
