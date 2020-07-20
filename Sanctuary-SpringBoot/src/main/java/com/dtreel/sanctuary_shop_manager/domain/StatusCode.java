package com.dtreel.sanctuary_shop_manager.domain;

/**
 * @Description TODO
 * @Author DtreeL
 * @Date 2020/5/2
 **/
public enum StatusCode {
    SUCCESS("00000", "一切ok"),
    ERROR("A0500","用户请求服务异常"),
    LOGIN_FAILED("A0202","用户账户被冻结"),
    PASSWORD_ERROR("A0210","用户密码错误"),
    NOT_OPERATE_AUTH("A0301", "该用户无操作权限"),
    DATABASE_ERROR("C0300","数据库服务出错");



    private String code;
    private String message;

    private StatusCode(String code, String message) {
        this.code =code;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public String getCode() {
        return code;
    }
}
