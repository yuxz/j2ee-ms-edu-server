package com.yxz.base.iam.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.yxz.base.common.constant.IamServerConstant;
import com.yxz.base.common.exception.BizCodeEnume;
import com.yxz.base.common.utils.R;
import com.yxz.base.iam.feign.MemberFeignService;
import com.yxz.base.iam.feign.ThirdPartyFeignService;
import com.yxz.base.iam.vo.UserSignUpVo;


@Controller
public class SignUpController {

	@Autowired
	ThirdPartyFeignService thirdPartyFeignService;

	@Autowired
	StringRedisTemplate redisTemplate;
	
	@Autowired
	MemberFeignService memberFeignService;
	
	@GetMapping("/sms/send")
	public R sendCode(@RequestParam("phone") String phone) {

		// TODO 1.接口防刷
		String redisCode = redisTemplate.opsForValue()
				.get(IamServerConstant.SMS_CODE_CACHE_PREFIX_STRING + phone);
		if (!StringUtils.isEmpty(redisCode)) {
			long sendTime = Long.parseLong(redisCode.split("_")[1]);
			if ((System.currentTimeMillis() - sendTime) < 60000) {
				return R.error(BizCodeEnume.SMS_CODE_EXCEPTION.getCode(), BizCodeEnume.SMS_CODE_EXCEPTION.getMsg());
			}
		}
//		2. 验证码再次校验 存储到redis
		String code = UUID.randomUUID().toString().substring(0, 5) + "_" + System.currentTimeMillis();
		// redis 缓存验证码
		redisTemplate.opsForValue().set(IamServerConstant.SMS_CODE_CACHE_PREFIX_STRING + phone, code, 10,
				TimeUnit.MINUTES);
		thirdPartyFeignService.sendCode(phone, code);

		return R.ok();

	}

	@PostMapping("/signup")
	public String signUp(@Valid UserSignUpVo vo, 
			BindingResult result,
			RedirectAttributes redirectAttributes,
			HttpSession httpSession) {
		
		if (result.hasErrors()) {			
			Map<String, String> errors = result.getFieldErrors().stream().collect(Collectors.toMap(
					fieldError -> fieldError.getField(), 
					fieldError -> fieldError.getDefaultMessage()));		
			redirectAttributes.addFlashAttribute("errors", errors); 			
			return "redirect:http://iam.edu.com/signup.html";
		};
		
		R r = memberFeignService.signup(vo);
		if(r.getCode() == 0) {					
			return "redirect:http://iam.edu.com/login.html";
		}
		else {					
			
			Map<String, String> errors = new HashMap<>();
			errors.put("msg", r.getData());//TODO
			redirectAttributes.addFlashAttribute("errors",errors);
			return "redirect:http://iam.edu.com/signup.html";
		}
		
		//手机验证码注册---------start
		/*
		//1. check phone verification code
		String code = vo.getCode();
		String existedCode = redisTemplate.opsForValue().get(IamServerConstant.SMS_CODE_CACHE_PREFIX_STRING + vo.getPhone());
		if(!StringUtils.isEmpty(existedCode)) {			
			//check passed
			if (code.equals(existedCode.split("_")[0])) {
				//delete code
				redisTemplate.delete(IamServerConstant.SMS_CODE_CACHE_PREFIX_STRING + vo.getPhone());
				
				//signup :revoke memeber service
				//R r = memberFeignService.signup(vo);
				if(r.getCode() == 0) {					
					return "redirect:/login.html";
				}
				else {					
					
					Map<String, String> errors = new HashMap<>();
					//errors.put("msg", r.getData(new TypeReference<String>(){}));//TODO
					redirectAttributes.addFlashAttribute("errors",errors);
					return "redirect:http://iam.edu.com/signup.html";
				}
				
				
			}
			// check not passed		
			else {
				Map<String, String> errors = new HashMap<>();
				errors.put("code", "验证码错误");
				redirectAttributes.addFlashAttribute("errors", errors); 			
				return "redirect:http://iam.edu.com/signup.html";
			}
		}else {
			Map<String, String> errors = new HashMap<>();
			errors.put("code", "验证码错误");
			redirectAttributes.addFlashAttribute("errors", errors); 			
			return "redirect:http://iam.edu.com/signup.html";
		}
		*/
	}
}
