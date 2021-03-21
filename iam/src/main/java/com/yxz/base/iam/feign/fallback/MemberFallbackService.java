package com.yxz.base.iam.feign.fallback;


import org.springframework.stereotype.Service;

import com.yxz.base.common.exception.BizCodeEnume;
import com.yxz.base.common.utils.R;
import com.yxz.base.iam.feign.MemberFeignService;
import com.yxz.base.iam.vo.SocialUser;
import com.yxz.base.iam.vo.UserLoginVo;
import com.yxz.base.iam.vo.UserSignUpVo;

@Service
public class MemberFallbackService implements MemberFeignService {
    @Override
    public R signup(UserSignUpVo registerVo) {
        return R.error(BizCodeEnume.READ_TIME_OUT_EXCEPTION.getCode(), BizCodeEnume.READ_TIME_OUT_EXCEPTION.getMsg());
    }

    @Override
    public R login(UserLoginVo loginVo) {
        return R.error(BizCodeEnume.READ_TIME_OUT_EXCEPTION.getCode(), BizCodeEnume.READ_TIME_OUT_EXCEPTION.getMsg());
    }

    @Override
    public R login(SocialUser socialUser) {
        return R.error(BizCodeEnume.READ_TIME_OUT_EXCEPTION.getCode(), BizCodeEnume.READ_TIME_OUT_EXCEPTION.getMsg());
    }

}
