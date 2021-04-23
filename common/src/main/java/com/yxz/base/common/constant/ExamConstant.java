package com.yxz.base.common.constant;

public class ExamConstant {


    public enum  AttrEnum{
        PA_TYPE_BASE(1,"单选"),deATTR_TYPE_SALE(0,"多选");
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
