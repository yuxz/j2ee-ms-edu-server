package com.yxz.base.member.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yxz.base.member.entity.GrowthChangeHistoryEntity;

import org.apache.ibatis.annotations.Mapper;

/**
 * 成长值变化历史记录
 * 
 * @author leifengyang
 * @email leifengyang@gmail.com
 * @date 2019-10-08 09:47:05
 */
@Mapper
public interface GrowthChangeHistoryDao extends BaseMapper<GrowthChangeHistoryEntity> {
	
}
