package com.yxz.base.common.constant;

import lombok.Data;

public class MemberConstant {


    public enum  AttrEnum{
    	MEMBER_INSTITUTION(1,"教師"), MEMBER_TEACHER(2,"教師"),MEMBER_STUDENT(3,"學生"),MEMBER_SIGNUP(4,"其他");
        private int code;
        private String msg;

        AttrEnum(int code,String msg){
            this.code = code;
            this.msg = msg;
        }

        public int getCode() {
            return code;
        }

        public String getMsg() {
            return msg;
        }
    }
}
