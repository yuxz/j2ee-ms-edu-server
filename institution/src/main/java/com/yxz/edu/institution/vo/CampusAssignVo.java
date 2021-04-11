package com.yxz.edu.institution.vo;

import java.util.List;

import lombok.Data;
@Data
public class CampusAssignVo {
	/**
	 * id
	 */
		private Long id;	
	/**
	 * campus Name
	 */
	private String name;
	
	
	/**
	 *  "active": ["1", "2"],
	 */
	private List<Long> active;
	
	/**
	 * classes of a campus
	 */
	private List<ClassAssignVo> classes;
	
	/**
	 * unassigned students
	   
	   "student": [{
			"name": "T",
			"id": "25"
		}],
	 */
	private List<StudentAssignVo> student;
	
}
