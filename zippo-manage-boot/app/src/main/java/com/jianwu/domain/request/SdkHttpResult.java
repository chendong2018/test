package com.jianwu.domain.request;

/**
 * 对server sdk返回的封装
 * Created by tookbra on 2016/3/29.
 */
public class SdkHttpResult {
    private int code;
    private String result;

    public SdkHttpResult() {
    }

    public SdkHttpResult(int code, String result) {
        this.code = code;
        this.result = result;
    }

    public int getHttpCode() {
        return code;
    }

    public String getResult() {
        return result;
    }

    @Override
    public String toString() {
        return String.format("{\"code\":\"%s\",\"result\":%s}", code,
                result);
    }
}
