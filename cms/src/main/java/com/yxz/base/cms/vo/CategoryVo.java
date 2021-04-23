package com.yxz.base.cms.vo;

import java.util.Date;
import lombok.Data;

/**
 * 内容分类
 * 
 * @author XINZHONG YU
 * @email yxz20180810@gmail.com
 * @date 2021-03-18 09:12:43
 */
 //TODO 添加后端校验代码
@Data
public class CategoryVo {
	

	/**
	 * id
	 */
		private Long id;
	/**
	 * 教育机构id
	 */
	private Long institutionId;
	/**
	 * 父分类id
	 */
	private Long parentId;
	/**
	 * 分类代码
	 */
	private String code;
	/**
	 * 分类名称
	 */
	private String name;
	/**
	 * 层级
	 */
	private Integer level;
	/**
	 * 排序
	 */
	private Integer sort;
	/**
	 * 内容数量
	 */
	private Integer totalCount;
	/**
	 * 图标地址
	 */
	private String icon;
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
