package com.yxz.edu.student.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
@Data
public class StatisticsTableVo {

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
	
	/*
	 * Institution
	 */
	@JsonProperty("institutionName")
	private String institutionName;
	
	/**
	 * Campus
	 */
	@JsonProperty("campusName")
	private String campusName;
	
	/**
	 * Campus
	 */
	@JsonProperty("campusscheduleName")
	private String campusscheduleName;
	/**
	 * classroom 
	 */
	@JsonProperty("classroomName")
    private String classroomName;
	
	/**
	 * Class Type Name
	 */
	@JsonProperty("classTypeName")
	private String classTypeName;
	
	/**
	 * Class Level Name
	 */
	@JsonProperty("classLevelName")
	private String classLevelName;
	
	private Integer totalCount;
}
