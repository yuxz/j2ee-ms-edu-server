package com.yxz.edu.institution.feign;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.yxz.base.common.utils.R;
import com.yxz.edu.institution.feign.fallback.MemberFallbackService;
import com.yxz.edu.institution.vo.UserSignUpVo;

//TODO MEMBER
@FeignClient(value = "base-member",fallback = MemberFallbackService.class)
public interface MemberFeignService {

    @PostMapping("/mms/member/signup")
    R signup(@RequestBody UserSignUpVo registerVo);



}
