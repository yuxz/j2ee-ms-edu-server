package com.yxz.base.member.Exception;

public class UsernameExistedException extends RuntimeException {

	public UsernameExistedException() {
		super("用户名存在");
	}
}
