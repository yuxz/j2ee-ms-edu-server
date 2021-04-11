package com.yxz.edu.institution.vo;

import java.util.List;

import lombok.Data;

@Data
public class ClassAssignVo {	
	
	/**
	 * id
	 */
	private Long id;	
	/**
	 * class Name
	 */
	private String name;

	/**
	 * teachers
	   
	   "teacher": [{
			"name": "T",
			"id": "25"
		}],
	 */
	private List<TeacherAssignVo> teacher;
	
	/**
	 * students
	   
	   "student": [{
			"name": "T",
			"id": "25"
		}],
	 */
	private List<StudentAssignVo> student;
	
	
	
}
/*
"id": "1",
	"name": "campus1",
	"active": ["1", "2"],
	"aclass": [{
		"id": "1",
		"name": "year1",
		"teacher": [{
			"name": "T",
			"id": "25"
		}],
		"student": [{
			"name": "S",
			"id": "25"
		}],
	}],
	"student": [{
		"name": "S ",
		"id": "1"
	}],
*/