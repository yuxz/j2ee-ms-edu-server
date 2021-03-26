package com.yxz.edu.institution.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yxz.edu.institution.entity.ClassTypeEntity;

import org.apache.ibatis.annotations.Mapper;

/**
 * 班级/课程类型
 * 
 * @author XINZHONG YU
 * @email yxz20180810@gmail.com
 * @date 2021-03-15 09:31:08
 */
@Mapper
public interface ClassTypeDao extends BaseMapper<ClassTypeEntity> {
	
}
