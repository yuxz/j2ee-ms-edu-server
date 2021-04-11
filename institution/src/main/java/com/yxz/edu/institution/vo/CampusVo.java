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
 * 校区
 * 
 * @author XINZHONG YU
 * @email yxz20180810@gmail.com
 * @date 2021-03-15 09:31:08
 */
 //TODO 添加后端校验代码
@Data
public class CampusVo {
	

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
	 * 分校名称
	 */
	@NotBlank(message = "分校名称必须提交", groups = { AddGroup.class, UpdateGroup.class })
	private String name;
	/**
	 * 地址
	 */
	private String address;
	/**
	 * 机构负责人
	 */
	private String leader;
	/**
	 * 手机号码
	 */
	private String mobile;
	/**
	 * 邮箱
	 */
	private String email;
	/**
	 * 网站
	 */
	private String url;
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
