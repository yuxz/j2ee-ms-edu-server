package com.yxz.edu.teacher.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * Teacher
 * 
 * @author XINZHONG YU
 * @email yxz20180810@gmail.com
 * @date 2021-03-29 13:59:24
 */

@Data
@TableName("tms_teacher")
public class TeacherEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	
@TableId
	private Long id;
	/**
	 * Institution
	 */
	private Long institutionId;
	/**
	 * Campus
	 */
	private Long campusId;
	/**
	 * Family Name
	 */
	private String firstName;
	/**
	 * Given Name
	 */
	private String lastName;
	/**
	 * English Name
	 */
	private String name;
	/**
	 * Chinese Name
	 */
	private String cname;
	/**
	 * Gender
	 */
	private Integer gender;
	/**
	 * Date of Birth
	 */
	private Date birth;
	/**
	 * Mobile
	 */
	private String mobile;
	/**
	 * Email
	 */
	private String email;
	/**
	 * QQ
	 */
	private String qq;
	/**
	 * Position
	 */
	private Long postId;
	/**
	 * Hire Date
	 */
	private Date hired;
	/**
	 * Leaved Date
	 */
	private Date leaved;
	/**
	 * whether Leaved
	 */
	private Integer isLeaved;
	/**
	 * Whether to Active Account
	 */
	private Integer isActived;
	/**
	 * Memo
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
	 * whether be deleted[0-deleted，1.not deleted]
	 */
	private Integer logicDeleted;
	/**
	 * Created Time
	 */
	private Date created;
	/**
	 * Updated Time
	 */
	private Date updated;
	/**
	 * Creator
	 */
	private Long userId;
	
	/**
	 * total count
	 */
	@TableField(exist = false)
	private Integer totalCount;

}
