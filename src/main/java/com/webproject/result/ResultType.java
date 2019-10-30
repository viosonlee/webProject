package com.webproject.result;

public enum ResultType {
    success(200, "success"),
    duplicate_account(101, "该用户名已被注册"),
    unfill_required_param(102, "缺失必要参数"),
    invaid_token(103, "登录信息失效"),
    not_find_account(104, "用户不存在"),
    unkown_error(199, "未知系统错误"),
    ;

    private int code;
    private String message;

    ResultType(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
