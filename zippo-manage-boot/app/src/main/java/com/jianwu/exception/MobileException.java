package com.jianwu.exception;

import java.io.Serializable;
import java.util.Arrays;

/**
 * Created by tookbra on 2016/4/6.
 */
public class MobileException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = -6833988708009992751L;

    /**
     * 错误代码.
     */
    private String errorCode;
    /**
     * 填充error msg参数.
     */
    private Object[] args;

    public MobileException(String exceptionMsg) {
        super(exceptionMsg);
    }

    /**
     * SmartCityException构造函数.
     *
     * @param exceptionMsg
     * @param errorCode
     */
    public MobileException(String exceptionMsg, String errorCode) {
        super(exceptionMsg);
        this.errorCode = errorCode;
    }

    /**
     * SmartCityException构造函数.
     *
     * @param exceptionMsg
     * @param errorCode
     * @param inputArgs 填充errormessage中的参数数组
     */
    public MobileException(String exceptionMsg, String errorCode, Object[] inputArgs) {
        super(exceptionMsg);
        this.errorCode = errorCode;
        if (args == null) {
            this.args = new Object[0];
        } else {
            this.args = Arrays.copyOf(inputArgs, inputArgs.length);
        }
    }

    /**
     * SmartCityException构造函数.
     *
     * @param exceptionMsg
     * @param errorCode
     * @param inputArgs
     *            填充errormessage中的参数数组
     * @param e
     */
    public MobileException(String exceptionMsg, String errorCode,
                           Object[] inputArgs, Exception e) {
        super(exceptionMsg, e);
        this.errorCode = errorCode;
        if (args == null) {
            this.args = new Object[0];
        } else {
            this.args = Arrays.copyOf(inputArgs, inputArgs.length);
        }
    }

    /**
     * @return the errorCode
     */
    public String getErrorCode() {
        return errorCode;
    }

    /**
     * @param errorCode
     *            the errorCode to set
     */
    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    /**
     * @return the args
     */
    public Object[] getArgs() {
        return args;
    }

    /**
     * @param inputArgs
     *            the args to set
     */
    public void setArgs(final Object[] inputArgs) {
        if (args == null) {
            this.args = new Object[0];
        } else {
            this.args = Arrays.copyOf(inputArgs, inputArgs.length);
        }
    }
}
