package com.yxz.edu.institution.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yxz.edu.institution.entity.InstitutionEntity;

import org.apache.ibatis.annotations.Mapper;

/**
 * 教育机构
 * 
 * @author XINZHONG YU
 * @email yxz20180810@gmail.com
 * @date 2021-03-15 09:31:08
 */
@Mapper
public interface InstitutionDao extends BaseMapper<InstitutionEntity> {
	
}
