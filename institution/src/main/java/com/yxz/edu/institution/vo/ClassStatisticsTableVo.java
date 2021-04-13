package com.yxz.edu.institution.vo;

import com.baomidou.mybatisplus.annotation.TableField;

import lombok.Data;

@Data
public class ClassStatisticsTableVo {
	/*
	 * classTypeName
	 */
	private String classTypeName;
	
	/*
	 * classroomName
	 */
	private String startYear;
	
	/**
	 * 
	 */
	private String quarter;/**
	 * 
	 */
	private Integer totalCount;
}
