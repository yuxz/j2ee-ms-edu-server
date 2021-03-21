package com.yxz.base.common.exception;

/***
 * 错误码和错误信息定义类
 * 1. 错误码定义规则为5为数字
 * 2. 前两位表示业务场景，最后三位表示错误码。例如：100001。10:通用 001:系统未知异常
 * 3. 维护错误码后需要维护错误描述，将他们定义为枚举形式
 * 错误码列表：
 *  10: 通用
 *      001：参数格式校验
 *  11: 机构
 *  12: 老师
 *  13: 学生
 *  14: 考试
 *
 *
 */
public enum BizCodeEnume {
    UNKNOW_EXCEPTION(10000,"系统未知异常"),
    VAILD_EXCEPTION(10001,"参数格式校验失败"),
    SMS_CODE_EXCEPTION(10002,"短信验证码发送频率太高,请稍后再试"),
    TO_MANY_REQUEST(10003,"请求流量过大，请稍后再试"),
    READ_TIME_OUT_EXCEPTION(10004,"远程调用服务超时，请重新再试"),
	EXAM_EXCEPTION(14001,"考试参数格式校验失败"),
    USER_EXISTED_EXCEPTION(15001,"存在相同的用户"),
    PHONE_EXISTED_EXCEPTION(15002,"存在相同的手机号"),
    EMAIL_EXISTED_EXCEPTION(15002,"存在相同的邮箱"),
    LOGINACCT_PASSWORD_EXCEPTION(15003,"账号或密码错误");
	
    private int code;
    private String msg;
    BizCodeEnume(int code,String msg){
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
