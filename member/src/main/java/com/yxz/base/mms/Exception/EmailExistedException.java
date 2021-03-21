package com.yxz.base.mms.Exception;

public class EmailExistedException extends RuntimeException {

	public EmailExistedException() {
		super("email存在");
	}
}
