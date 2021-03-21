package com.yxz.base.mms.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yxz.base.mms.entity.MemberEntity;

import org.apache.ibatis.annotations.Mapper;

/**
 * 会员
 * 
 * @author leifengyang
 * @email leifengyang@gmail.com
 * @date 2019-10-08 09:47:05
 */
@Mapper
public interface MemberDao extends BaseMapper<MemberEntity> {
	
}
