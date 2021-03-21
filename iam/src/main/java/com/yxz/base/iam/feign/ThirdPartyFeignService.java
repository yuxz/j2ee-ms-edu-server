package com.yxz.base.iam.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.yxz.base.common.utils.R;

@FeignClient("base-third-party")
public interface ThirdPartyFeignService {

	@GetMapping("/sms/send")
	public R sendCode(@RequestParam("phone") String phone, @RequestParam("code") String code);
}
