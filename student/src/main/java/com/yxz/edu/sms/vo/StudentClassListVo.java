package com.yxz.edu.sms.vo;

import java.util.Date;

import lombok.Data;

@Data
public class StudentClassListVo extends StudentClassVo {
	/**
	 * 学生姓名
	 */
	private String studentName;	
	/**
	 * 性别
	 */
	private Integer gender;
	/**
	 * 生日
	 */
	private Date birth;
	/**
	 * 手机号码
	 */
	private String mobile;
	/**
	 * 邮箱
	 */
	private String email;
}
