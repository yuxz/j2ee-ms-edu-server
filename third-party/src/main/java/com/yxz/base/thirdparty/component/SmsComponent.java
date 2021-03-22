package com.yxz.base.thirdparty.component;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@ConfigurationProperties(prefix = "spring.cloud.aws.sms")
@Data
@Component
public class SmsComponent {
	
	private String host;
	private String path;
	private String sign;
	private String skin;
	private String appcode;
	
//	TODO
	public void sendSms(String phone, String code) {
//		ng method
		
	}

}
