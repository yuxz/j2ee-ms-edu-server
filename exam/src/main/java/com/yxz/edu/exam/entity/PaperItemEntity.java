package com.yxz.edu.exam.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

/**
 * 试题
 * 
 * @author XINZHONG YU
 * @email yxz20180810@gmail.com
 * @date 2021-03-11 22:21:52
 */
 //TODO
@Data
@TableName("ems_paper_item")
public class PaperItemEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */

	@TableId
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

}
