package com.yxz.edu.student.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class StudentListVo extends StudentVo {


	private Long studentClassId;
	
	/**
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
	 * Class Type Name
	 */
	@JsonProperty("classTypeName")
	private String classTypeName;
	
	/**
	 * Class Level Name
	 */
	@JsonProperty("classLevelName")
	private String classLevelName;
	
	/**
	 * classroom 
	 */
	@JsonProperty("classroomName")
    private String classroomName;
}
