package com.yxz.edu.exam.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 试题选项
 * 
 * @author XINZHONG YU
 * @email yxz20180810@gmail.com
 * @date 2021-03-12 09:38:44
 */
 //TODO
@Data
@TableName("ems_paper_item_option")
public class PaperItemOptionEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */

@TableId
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
