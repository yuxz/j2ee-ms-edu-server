package com.yxz.base.member.Exception;

public class EmailExistedException extends RuntimeException {

	public EmailExistedException() {
		super("email存在");
	}
}
