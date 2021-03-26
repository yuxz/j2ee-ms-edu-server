package com.yxz.edu.institution.feign.fallback;


import org.springframework.stereotype.Service;

import com.yxz.base.common.exception.BizCodeEnume;
import com.yxz.base.common.utils.R;
import com.yxz.edu.institution.feign.MemberFeignService;
import com.yxz.edu.institution.vo.UserSignUpVo;

@Service
public class MemberFallbackService implements MemberFeignService {
    @Override
    public R signup(UserSignUpVo registerVo) {
        return R.error(BizCodeEnume.READ_TIME_OUT_EXCEPTION.getCode(), BizCodeEnume.READ_TIME_OUT_EXCEPTION.getMsg());
    }


}
