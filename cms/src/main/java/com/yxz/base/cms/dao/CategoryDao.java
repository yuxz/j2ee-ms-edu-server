package com.yxz.base.cms.dao;

import com.yxz.base.cms.entity.CategoryEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 内容分类
 * 
 * @author XINZHONG YU
 * @email yxz20180810@gmail.com
 * @date 2021-03-18 09:12:43
 */
@Mapper
public interface CategoryDao extends BaseMapper<CategoryEntity> {
	
}
