package com.yxz.edu.institution.vo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.yxz.base.common.valid.AddGroup;
import com.yxz.base.common.valid.ListValue;
import com.yxz.base.common.valid.UpdateGroup;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

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
public class ClassListVo {
	

	/**
	 * id
	 */
	@NotNull(message = "修改必须指定id", groups = { UpdateGroup.class })
	@Null(message = "新增不能指定id", groups = { AddGroup.class })
		private Long id;
	/**
	 * 教育机构id
	 */
	private Long institutionId;
	/**
	 * 校区id
	 */
	private Long campusId;
	/**
	 * 班级类型id
	 */
	private Long classTypeId;
	/**
	 * 年级id
	 */
	private Long classLevelId;
	/**
	 * 教室id
	 */
	private Long classroomId;
	/**
	 * 班级名称
	 */
	@NotBlank(message = "班級名称必须提交", groups = { AddGroup.class, UpdateGroup.class })
	private String name;
	/**
	 * 班级最多人數
	 */
	private Integer maximum;
	/**
	 * 开班时间
	 */
	private Date startTime;
	/**
	 * 结班时间
	 */
	private Date endTime;
	/**
	 * 是否结业
	 */
	private Integer isFinished;
	/**
	 * 备注
	 */
	private String note;
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
