package com.yxz.base.iam.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.yxz.base.common.constant.IamServerConstant;
import com.yxz.base.common.constant.MemberConstant;
import com.yxz.base.common.utils.R;
import com.yxz.base.common.vo.MemberResponseVo;
import com.yxz.base.iam.feign.MemberFeignService;
import com.yxz.base.iam.vo.MemberVo;
import com.yxz.base.iam.vo.UserLoginVo;

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
	public String login(@Valid UserLoginVo vo, 
			BindingResult result, 
			RedirectAttributes redirectAttributes, 
			HttpSession session) {
		
		if (result.hasErrors()) {			
			Map<String, String> errors = result.getFieldErrors().stream().collect(Collectors.toMap(
					fieldError -> fieldError.getField(), 
					fieldError -> fieldError.getDefaultMessage()));		
			redirectAttributes.addFlashAttribute("errors", errors); 			
			return "redirect:http://iam.edu.com/login.html";
		};
		
		
		R r = memberFeignService.login(vo);
		if (r.getCode() == 0) {
			String jsonString = JSON.toJSONString(r.get("memberEntity"));
			MemberResponseVo memberResponseVo = JSON.parseObject(jsonString, new TypeReference<MemberResponseVo>() {
			});
			session.setAttribute(IamServerConstant.LOGIN_USER, memberResponseVo);
			
			//teacher
			MemberVo member = (MemberVo)r.get("memberEntity");
			if(MemberConstant.AttrEnum.MEMBER_TEACHER.equals(member.getStatus())) {
				return "redirect:http://teacher.edu.com";
			}else {			
				return "redirect:http://studetn.edu.com";
			}
		} else {
			String msg = (String) r.get("msg");
			Map<String, String> errors = new HashMap<>();
			errors.put("msg", msg);
			redirectAttributes.addFlashAttribute("errors", errors);
			return "redirect:http://iam.edu.com/login.html";
		}
	}

	
}
