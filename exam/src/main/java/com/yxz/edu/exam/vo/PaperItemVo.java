package com.yxz.edu.exam.vo;

import java.util.Date;
import java.util.List;


import com.yxz.edu.exam.entity.PaperItemOptionEntity;

import lombok.Data;

@Data
public class PaperItemVo {

	/**
	 * id
	 */
	private Long id;
	/**
	 * 试卷id
	 */
	private Long paperId;
	/**
	 * 试题名称
	 */
	private String name;
	/**
	 * 试题类型id
	 */
	private Long itemTypeId;
	/**
	 * 得分
	 */
	private Long score;
	/**
	 * 试题答案
	 */
	private String answer;
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
	
	
	private List<PaperItemOptionEntity> paperOptions;

}
