package com.yxz.edu.institution.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ClassDetailVo extends ClassVo{
	
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
	 * Post Name
	 */
	@JsonProperty("postName")
	private String postName;
	
}
