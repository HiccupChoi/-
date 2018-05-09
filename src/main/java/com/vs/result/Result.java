package com.vs.result;

public class Result<T> {

    //status 状态值：0:成功，其他数值代表失败
    private Integer status;

    //success true:代表成功  false:失败
    private boolean success;

    //msg 错误信息
    private String msg;

    //data 返回体报文的出参，使用泛型兼容不同的类型
    private T data;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
