package com.yxz.base.mms.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yxz.base.common.utils.PageUtils;
import com.yxz.base.common.utils.Query;
import com.yxz.base.common.vo.MemberSignUpVo;
import com.yxz.base.mms.Exception.EmailExistedException;
import com.yxz.base.mms.Exception.MobileExistedException;
import com.yxz.base.mms.Exception.UsernameExistedException;
import com.yxz.base.mms.dao.MemberDao;
import com.yxz.base.mms.dao.MemberLevelDao;
import com.yxz.base.mms.entity.MemberEntity;
import com.yxz.base.mms.entity.MemberLevelEntity;
import com.yxz.base.mms.service.MemberService;

@Service("memberService")
public class MemberServiceImpl extends ServiceImpl<MemberDao, MemberEntity> implements MemberService {

	@Autowired
	MemberLevelDao memberLevelDao;

	@Override
	public PageUtils queryPage(Map<String, Object> params) {
		IPage<MemberEntity> page = this.page(new Query<MemberEntity>().getPage(params),
				new QueryWrapper<MemberEntity>());

		return new PageUtils(page);
	}

	@Override
	public void SignUp(MemberSignUpVo vo) {
		// TODO Auto-generated method stub
		
		MemberEntity memberEntity= new MemberEntity();		
		
		MemberLevelEntity defaultLevel = memberLevelDao.getDefaultLevel();
		memberEntity.setLevelId(defaultLevel.getId());
		
		//check username unique
		checkEmailUnique(vo.getUserName());
		memberEntity.setUsername(vo.getUserName());	
		
		//check phone unique
		checkMobileUnique(vo.getPhone());
		memberEntity.setMobile(vo.getPhone());
		
		//check email unique
		checkEmailUnique(vo.getEmail());
		memberEntity.setEmail(vo.getEmail());
		
		//crypt password
		BCryptPasswordEncoder passwordEncoded = new BCryptPasswordEncoder();		
		memberEntity.setPassword(passwordEncoded.encode(vo.getPassword()));
		
		baseMapper.insert(memberEntity);
		
	}

	@Override
	public void checkEmailUnique(String email) throws EmailExistedException {

		Integer count = baseMapper.selectCount(new QueryWrapper<MemberEntity>().eq("email", email));
		if (count > 0) {
			throw new EmailExistedException();
		}

	}

	@Override
	public void checkMobileUnique(String phone) throws MobileExistedException {
		Integer count = baseMapper.selectCount(new QueryWrapper<MemberEntity>().eq("mobile", phone));
		if (count > 0) {
			throw new MobileExistedException();
		}
	}

	@Override
	public void checkUsernameUnique(String userName) throws UsernameExistedException {
		Integer count = baseMapper.selectCount(new QueryWrapper<MemberEntity>().eq("userName", userName));
		if (count > 0) {
			throw new UsernameExistedException();
		}
	}

	@Override
	public PageUtils queryPageBySourceType(Map<String, Object> params, String sourceType) {
		IPage<MemberEntity> page = this.page(new Query<MemberEntity>().getPage(params),
				new QueryWrapper<MemberEntity>().eq("source_type", sourceType));

		return new PageUtils(page);
	}

}