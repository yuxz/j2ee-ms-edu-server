package com.yxz.edu.institution.vo;

import lombok.Data;

/**
 * 
 * 
 * @author XINZHONG YU
 * @email yxz20180810@gmail.com
 * @date 2021-03-15 10:13:23
 */
 //TODO 添加后端校验代码
@Data
public class ClassListVo extends ClassVo{
	
	/*
	 * campusName
	 * 
	 */
	private String campusName;

	/*
	 * classTypeName
	 */
	private String classTypeName;
	
	/*
	 * classroomName
	 */
	private String classroomName;
	
	/**
	 * 
	 */
	private Integer totalCount;
}
