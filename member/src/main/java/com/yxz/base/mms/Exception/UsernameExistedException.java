package com.yxz.base.mms.Exception;

public class UsernameExistedException extends RuntimeException {

	public UsernameExistedException() {
		super("用户名存在");
	}
}
