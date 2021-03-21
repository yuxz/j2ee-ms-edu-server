package com.yxz.base.mms.Exception;

public class MobileExistedException extends RuntimeException {
	
	public MobileExistedException() {
		super("手机号存在");
	}

}
