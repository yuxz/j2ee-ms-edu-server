package com.yxz.base.member.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yxz.base.member.entity.MemberLoginLogEntity;

import org.apache.ibatis.annotations.Mapper;

/**
 * 会员登录记录
 * 
 * @author leifengyang
 * @email leifengyang@gmail.com
 * @date 2019-10-08 09:47:05
 */
@Mapper
public interface MemberLoginLogDao extends BaseMapper<MemberLoginLogEntity> {
	
}
