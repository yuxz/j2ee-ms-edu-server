package com.yxz.edu.exam.vo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
public class CategoryChildrenVo extends CategoryVo {
	
	/**
	 * 子分类
	 */
	//若数据为空，返回客户端json数据中不包含此字段
	@JsonInclude(JsonInclude.Include.NON_EMPTY)	
	private List<CategoryChildrenVo> children;
	
	/**
	 * 父级分类名字
	 */	
	private String parentCategoryName;

}
