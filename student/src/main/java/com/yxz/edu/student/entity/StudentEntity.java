package com.yxz.edu.student.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 学生
 * 
 * @author XINZHONG YU
 * @email yxz20180810@gmail.com
 * @date 2021-03-28 16:27:28
 */

@Data
@TableName("sms_student")
public class StudentEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	
@TableId
	private Long id;
	/**
	 * 教育机构id
	 */
	private Long institutionId;
	/**
	 * 分校id
	 */
	private Long campusId;
	/**
	 * 分校schedule id
	 */
	private Long campusScheduleId;
	/**
	 * 班级/课程类型id
	 */
	private Long classTypeId;
	/**
	 * 班级级别id
	 */
	private Long classLevelId;
	/**
	 * 学生姓名
	 */
	private String name;
	/**
	 * FamilyName
	 */
	private String firstName;
	/**
	 * GivenName
	 */
	private String lastName;
	/**
	 * 中文姓名
	 */
	private String cname;
	/**
	 * 生日
	 */
	private Date birth;
	/**
	 * 性别
	 */
	private Integer gender;
	/**
	 * 邮箱
	 */
	private String email;
	/**
	 * 手机号码
	 */
	private String mobile;
	/**
	 * suburb
	 */
	private String suburb;
	/**
	 * postcode
	 */
	private String postcode;
	/**
	 * contract1
	 */
	private String contractOne;
	/**
	 * contract2
	 */
	private String contractTwo;
	/**
	 * day school
	 */
	private String school;
	/**
	 * current year level in day school
	 */
	private Integer currentLevel;
	/**
	 * parent or guardian name
	 */
	private String parent;
	/**
	 * 年级
	 */
	private Integer yearLevel;
	/**
	 * 注册时间
	 */
	private Date registered;
	/**
	 * 备注
	 */
	private String note;
	/**
	 * field1
	 */
	private String field1;
	/**
	 * field2
	 */
	private String field2;
	/**
	 * field3
	 */
	private String field3;
	/**
	 * field4
	 */
	private String field4;
	/**
	 * field5
	 */
	private String field5;
	/**
	 * field6
	 */
	private String field6;
	/**
	 * token
	 */
	private String token;
	/**
	 * 是否激活
	 */
	private Integer isActived;
	/**
	 * 是否被删除[0-已删，1未删]
	 */
	private Integer logicDeleted;
	/**
	 * 创建时间
	 */
	private Date created;
	/**
	 * 更改时间
	 */
	private Date updated;
	/**
	 * 创建人
	 */
	private Long userId;
	/**
	 * year 
	 */
	@TableField(exist = false)
	private String year;
	
	/**
	 * quarter 
	 */
	@TableField(exist = false)
	private String quarter;
	/**
	 * month 
	 */
	@TableField(exist = false)
	private String month;
	
	/**
	 * week 
	 */
	@TableField(exist = false)
	private String week;
	
	/**
	 * total count
	 */
	@TableField(exist = false)
	private Integer totalCount;

}
