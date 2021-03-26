package com.yxz.base.member.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yxz.base.member.entity.MemberReceiveAddressEntity;

import org.apache.ibatis.annotations.Mapper;

/**
 * 会员收货地址
 * 
 * @author leifengyang
 * @email leifengyang@gmail.com
 * @date 2019-10-08 09:47:05
 */
@Mapper
public interface MemberReceiveAddressDao extends BaseMapper<MemberReceiveAddressEntity> {
	
}
