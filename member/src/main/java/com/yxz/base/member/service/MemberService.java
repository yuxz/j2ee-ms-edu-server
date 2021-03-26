package com.yxz.base.member.service;

import java.util.Map;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yxz.base.common.utils.PageUtils;
import com.yxz.base.common.vo.MemberLoginVo;
import com.yxz.base.common.vo.MemberSignUpVo;
import com.yxz.base.member.Exception.EmailExistedException;
import com.yxz.base.member.Exception.MobileExistedException;
import com.yxz.base.member.Exception.UsernameExistedException;
import com.yxz.base.member.entity.MemberEntity;

/**
 * 会员
 *
 * @author leifengyang
 * @email leifengyang@gmail.com
 * @date 2019-10-08 09:47:05
 */
public interface MemberService extends IService<MemberEntity> {

	PageUtils queryPage(Map<String, Object> params);

	void SignUp(MemberSignUpVo vo);

	void checkEmailUnique(String email) throws EmailExistedException;

	void checkMobileUnique(String phone) throws MobileExistedException;

	void checkUsernameUnique(String userName) throws UsernameExistedException;

	PageUtils queryPageBySourceType(Map<String, Object> params,String sourceType);

	MemberEntity login(MemberLoginVo vo);

}
