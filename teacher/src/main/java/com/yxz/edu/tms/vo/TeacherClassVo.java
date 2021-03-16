package com.yxz.edu.tms.vo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.yxz.base.common.valid.AddGroup;
import com.yxz.base.common.valid.ListValue;
import com.yxz.base.common.valid.UpdateGroup;

import java.util.Date;
import lombok.Data;

/**
 * 班级老师
 * 
 * @author XINZHONG YU
 * @email yxz20180810@gmail.com
 * @date 2021-03-15 16:32:08
 */
 //TODO 添加后端校验代码
@Data
public class TeacherClassVo {
	

	/**
	 * id
	 */
	private Long id;
	/**
	 * 班级id
	 */
	private Long classId;
	/**
	 * 老师id
	 */
	private Long teacherId;
	/**
	 * 备注
	 */
	private String note;
	/**
	 * 是否被删除[0-未删，1已删]
	 */
	private Integer logicDeleted;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 更改时间
	 */
	private Date updateTime;
	/**
	 * 创建人
	 */
	private Long userId;

}
