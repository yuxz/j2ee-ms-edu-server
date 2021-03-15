package com.yxz.edu.exam.vo;

import java.util.Date;

import lombok.Data;

/**
 * 试题选项
 * 
 * @author XINZHONG YU
 * @email yxz20180810@gmail.com
 * @date 2021-03-12 10:06:00
 */
 //TODO
@Data

public class PaperItemOptionVo {

	/**
	 * id
	 */
	private Long id;
	/**
	 * 试题id
	 */
	private Long paperItemId;
	/**
	 * 选项名称
	 */
	private String name;
	/**
	 * 是否被删除[0-已删，1未删]
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
