package com.yxz.base.member.Exception;

public class EmailNotExistedException extends RuntimeException {

	public EmailNotExistedException() {
		super("用户不存在！");
	}
}
