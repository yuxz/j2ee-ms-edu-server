package com.yxz.base.common.constant;

import lombok.Data;

public class MemberConstant {


    public enum  AttrEnum{
        TEACHER_MEMBER(1,"教師"),STUDENT_MEMBER(2,"學生"),SIGNUP_MEMBER(3,"注冊賬號");
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
