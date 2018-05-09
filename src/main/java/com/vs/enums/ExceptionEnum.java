package com.vs.enums;


public enum ExceptionEnum {
        UNKNOW_ERROR(-1,"未知错误"),
        USER_NOT_FIND(101,"用户不存在"),
    ;
    //错误码
    private Integer code;
    //错误信息
    private String msg;

    ExceptionEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    ExceptionEnum(int i, String 用户不存在) {
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
