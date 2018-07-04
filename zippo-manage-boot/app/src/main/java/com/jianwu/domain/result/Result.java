package com.jianwu.domain.result;

import java.io.Serializable;

public class Result<T> implements Serializable {
    private static final long serialVersionUID = 8605165411381221253L;
    boolean success;
    String msg;
    T data;

    public Result() {
    }

    public Result(boolean success, String msg, T data) {
        this.success = success;
        this.msg = msg;
        this.data = data;
    }

    public boolean isSuccess() {
        return this.success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static <T> Result<T> success(T date) {
        return new Result(true, (String)null, date);
    }

    public static <T> Result<T> error(String errorMsg) {
        return new Result(false, errorMsg, (Object)null);
    }
}
