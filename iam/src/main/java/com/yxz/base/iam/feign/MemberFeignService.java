package com.yxz.base.iam.feign;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yxz.base.common.utils.R;
import com.yxz.base.iam.feign.fallback.MemberFallbackService;
import com.yxz.base.iam.vo.SocialUser;
import com.yxz.base.iam.vo.UserLoginVo;
import com.yxz.base.iam.vo.UserSignUpVo;

//TODO MEMBER
@FeignClient(value = "base-member",fallback = MemberFallbackService.class)
public interface MemberFeignService {

    @PostMapping("/mms/member/signup")
    R signup(@RequestBody UserSignUpVo registerVo);


    @RequestMapping("/mms/member/login")
     R login(@RequestBody UserLoginVo loginVo);

    @RequestMapping("/mms/member/oauth2/login")
    R login(@RequestBody SocialUser socialUser);
}
