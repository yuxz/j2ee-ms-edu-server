package com.yxz.base.common.to;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import com.yxz.base.common.valid.AddGroup;
import com.yxz.base.common.valid.UpdateGroup;

import lombok.Data;

@Data
public class ClassTo {

	/**
	 * id
	 */	
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
	 * Maxinum
	 */
	private Integer maxinum;
}
