package com.yxz.edu.teacher.vo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.yxz.base.common.valid.AddGroup;
import com.yxz.base.common.valid.ListValue;
import com.yxz.base.common.valid.UpdateGroup;

import java.util.Date;
import lombok.Data;

/**
 * 教师
 * 
 * @author XINZHONG YU
 * @email yxz20180810@gmail.com
 * @date 2021-03-15 16:32:08
 */
 //TODO 添加后端校验代码
@Data
public class TeacherVo {
	

	/**
	 * id
	 */
		private Long id;
	/**
	 * 教育机构id
	 */
	private Long institutionId;
	/**
	 * 教师姓名
	 */
	private String name;
	
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
	/**
	 * 入职时间
	 */
	private Date hired;
	/**
	 * 离职时间
	 */
	private Date leaved;
	/**
	 * 是否离职
	 */
	private Integer isLeaved;
	/**
	 * 备注
	 */
	private String note;
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

}
