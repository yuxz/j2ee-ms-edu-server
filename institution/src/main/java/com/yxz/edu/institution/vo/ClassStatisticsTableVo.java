package com.yxz.edu.institution.vo;

import com.yxz.base.common.utils.echarts.BaseEchartsStatisticsVo;

import lombok.Data;

@Data
public class ClassStatisticsTableVo<T> extends BaseEchartsStatisticsVo<T>{
	/*
	 * classTypeName
	 */
	private String classTypeName;
	
	/*
	 * classroomName
	 */
	private String startYear;
	
//	/**
//	 * 
//	 */
//	private String quarter;/**
//	 * 
//	 */
//	private Integer totalCount;
}
