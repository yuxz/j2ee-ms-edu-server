package com.yxz.edu.exam.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yxz.edu.exam.entity.PaperItemEntity;

import org.apache.ibatis.annotations.Mapper;

/**
 * 试题
 * 
 * @author XINZHONG YU
 * @email yxz20180810@gmail.com
 * @date 2021-03-11 22:21:52
 */
@Mapper
public interface PaperItemDao extends BaseMapper<PaperItemEntity> {
	
}
