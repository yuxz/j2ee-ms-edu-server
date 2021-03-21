package com.yxz.base.iam.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.yxz.base.common.constant.IamServerConstant;
import com.yxz.base.common.exception.BizCodeEnume;
import com.yxz.base.common.utils.R;
import com.yxz.base.common.vo.MemberResponseVo;
import com.yxz.base.iam.feign.MemberFeignService;
import com.yxz.base.iam.feign.ThirdPartyFeignService;
import com.yxz.base.iam.vo.UserLoginVo;
import com.yxz.base.iam.vo.UserSignUpVo;

@Controller
public class LoginController {

	

	@Autowired
	private MemberFeignService memberFeignService;

	@RequestMapping("/login.html")
	public String loginPage(HttpSession session) {
		if (session.getAttribute(IamServerConstant.LOGIN_USER) != null) {
			return "redirect:http://edu.com/";
		} else {
			return "login";
		}
	}

	@RequestMapping("/login")
	public String login(UserLoginVo vo, RedirectAttributes attributes, HttpSession session) {
		R r = memberFeignService.login(vo);
		if (r.getCode() == 0) {
			String jsonString = JSON.toJSONString(r.get("memberEntity"));
			MemberResponseVo memberResponseVo = JSON.parseObject(jsonString, new TypeReference<MemberResponseVo>() {
			});
			session.setAttribute(IamServerConstant.LOGIN_USER, memberResponseVo);
			return "redirect:http://edu.com/";
		} else {
			String msg = (String) r.get("msg");
			Map<String, String> errors = new HashMap<>();
			errors.put("msg", msg);
			attributes.addFlashAttribute("errors", errors);
			return "redirect:http://iam.edu.com/login.html";
		}
	}

	
}
