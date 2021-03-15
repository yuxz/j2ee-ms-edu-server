package com.yxz.edu.exam.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yxz.edu.exam.entity.CategoryEntity;

import org.apache.ibatis.annotations.Mapper;

/**
 * 试卷类型
 * 
 * @author XINZHONG YU
 * @email yxz20180810@gmail.com
 * @date 2021-03-08 11:08:06
 */
@Mapper
public interface CategoryDao extends BaseMapper<CategoryEntity> {
	
}
