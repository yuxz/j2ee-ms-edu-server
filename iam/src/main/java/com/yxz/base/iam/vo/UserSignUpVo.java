package com.yxz.base.iam.vo;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data
public class UserSignUpVo {
	@NotEmpty(message = "用户名必须填写")
	@Length(min=6, max=18, message = "用户名必须是6-18位字符")	
	private String userName;
	
	@NotEmpty(message = "密码必须填写")
	@Length(min=6, max=18, message = "密码必须是6-18位字符")	
	private String password;
	
	@NotEmpty(message = "手机号必须填写")
	@Pattern(regexp = "^[0-9] {10}$",message = "手机号不能为空")	
	private String phone;
	
//TODO UserSignUpVo EMAIL 
//	@NotEmpty(message = "email必须填写")
//	@Pattern(regexp = "^[0-9] {10}$",message = "email不能为空")	
	private String email;
	
	@NotEmpty(message = "验证码必须填写")
	private String code;

}
