package com.yxz.base.cms.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import lombok.Data;

/**
 * 文章
 * 
 * @author XINZHONG YU
 * @email yxz20180810@gmail.com
 * @date 2021-03-18 16:22:17
 */
 //TODO 添加后端校验代码
@Data
public class ContentVo {
	

	/**
	 * id
	 */
		private Long id;
	/**
	 * 分类id
	 */
	private Long categoryId;
	/**
	 * 内容类型id
	 */
	private Long contentTypeId;
	/**
	 * 标题
	 */
	private String title;
	/**
	 * 副标题
	 */
	private String subtitle;
	/**
	 * 描述
	 */
	private String titleDesc;
	/**
	 * url
	 */
	private String url;
	/**
	 * 内容
	 */
	private String content;
	/**
	 * 是否发布[0-未发  1-已发]
	 */
	private Integer ispublished;
	/**
	 * 发布时间
	 */
	private Date published;
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
	
	
	/**
	 * category完整路径 [2, 32, 191]
	 */
    @JsonProperty("categoryFullPath")
	private Long[] categoryFullPath;

}
