package com.yxz.base.thirdparty.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yxz.base.common.utils.R;
import com.yxz.base.thirdparty.component.SmsComponent;

@RestController
@RequestMapping("/sms")
public class SmsController {
	
	@Autowired
	SmsComponent smsComponent;
	
	@GetMapping("/send")
	public R sendCode(@RequestParam("phone") String phone, @RequestParam("code") String code) {
		
		smsComponent.sendSms(phone, code);
		//TODE sendSms
		return R.ok();
	}
}
