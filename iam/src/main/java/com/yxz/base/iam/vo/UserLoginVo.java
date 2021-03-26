package com.yxz.base.iam.vo;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data
public class UserLoginVo {
	@NotEmpty(message = "email必须填写")
	@Email(message = "email填写有误")	
    private String email;
    
	@NotEmpty(message = "密码必须填写")
	@Length(min=6, max=18, message = "密码必须是6-18位字符")	
    private String password;
}
