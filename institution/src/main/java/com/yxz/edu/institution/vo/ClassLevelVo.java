package com.yxz.edu.institution.vo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.yxz.base.common.valid.AddGroup;
import com.yxz.base.common.valid.ListValue;
import com.yxz.base.common.valid.UpdateGroup;

import java.util.Date;
import lombok.Data;

/**
 * 年级
 * 
 * @author XINZHONG YU
 * @email yxz20180810@gmail.com
 * @date 2021-04-11 12:29:23
 */
 //TODO 添加后端校验代码
@Data
public class ClassLevelVo {
	

	/**
	 * id
	 */
		private Long id;
	/**
	 * 教育机构id
	 */
	private Long institutionId;
	/**
	 * 名称
	 */
	private String name;
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

}
