package com.jianwu.exception;

/**
 * Created by tookbra on 2016/7/25.
 */
public class SmsException extends RuntimeException {

    public SmsException() {
        super();
    }

    public SmsException(String message) {
        super(message);
    }
}
