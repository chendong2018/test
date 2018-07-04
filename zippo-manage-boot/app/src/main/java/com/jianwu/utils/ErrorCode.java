package com.jianwu.utils;

/**
 * Created by tookbra on 2016/3/29.
 */
public interface ErrorCode {

    /**
     * 未知运行时异常
     */
    String UNKNOW_RUNTIME_ERROR = "10001";
    /**
     * 没有找到URL
     */
    String NOT_FOUND_URL = "10002";
    /**
     * Header中Username为空
     */
    String HEADER_USERNAME_IS_NULL = "10003";
    /**
     * 未定义的错误
     */
    String UNDEFINED_ERROR = "10099";
    /**
     * 标识无效
     */
    String ID_IS_ILLEGAL = "20001";
    /**
     * 无效的电话号码
     */
    String PHONE_IS_ILLEGAL = "20002";
    /**
     * 无效的密码
     */
    String OLDPWD_IS_ILLEGAL = "20003";
    /**
     * 无效的密码
     */
    String NEWPWD_IS_ILLEGAL = "20004";
    /**
     * 无效的验证码
     */
    String CAPTCHA_IS_ILLEGAL = "20005";
    /**
     * 短信发送失败
     */
    String SMS_SEND_FIRE = "30001";
    /**
     * 重置密码失败
     */
    String REST_PWD_FIRE = "30002";
    /**
     * 忘记密码失败
     */
    String FORGET_PWD_FIRE = "30003";
    /**
     * 意见反馈数据验证错误
     */
    String FEEDBACK_VALID_FIRE = "30100";
    /**
     * 意见反馈上传失败
     */
    String FEEDBACK_SEND_FIRE = "30101";
    /**
     * 用户未找到
     */
    String USER_NOT_FOUND = "30200";
    /**
     * 登陆参数验证错误
     */
    String LOGIN_VALID_FIRE = "30201";
    /**
     *  退出失败
     */
    String LOGIN_OUT_FIRE = "30300";
    /**
     * 短信参数错误
     */
    String SEND_SMS_ILLEGAL = "30400";
    /**
     * 短信组参数错误
     */
    String SMS_GROUPINFO_ILLEGAL = "30401";
    /**
     * 短信组参数查询错误
     */
    String SMS_GROUPINFO_QUERY = "30402";
    /**
     * 短信重发参数错误
     */
    String SMS_RESEND_ILLEGALL = "30403";
    /**
     * 短信重发失败
     */
    String SMS_RESEND_FAIL = "30404";
    /**
     * 短信列表查询失败
     */
    String SMS_LIST_QUERY = "30405";
    /**
     * 短信列表查询失败
     */
    String SMS_INFO_QUERY = "30406";
    /**
     * 预定短信参数错误
     */
    String PRE_SMS_ILLEGALL = "30407";
    /**
     * 删除预定短信错误
     */
    String DEL_PRE_SMS_FAIL = "30408";
    /**
     * 公告参数错误
     */
    String NOTICE_SEND_ILLEGALL = "31000";
    /**
     * 公告发送错误
     */
    String NOTICE_SEND_FAIL = "31001";
    /**
     * 公告短信发送错误
     */
    String NOTICE_SMS_FAIL = "31002";
    /**
     * 公告短信参数错误
     */
    String NOTICE_SMS_ILLEGALL = "31003";
    /***
     * 电话会议信息获取失败
     */
    String MEETING_INFO_FAIL = "40001";
    /***
     * 新增邮箱失败
     */
    String EMAIL_INFO_FAIL = "50001";
    /**
     * 登陆失效,需重新登陆
     */
    String LOGIN_TOKEN_FAIL = "90000";
    /**
     * 融云获取token错误
     */
    String RONGCLOUND_IS_ILLEGAL = "90001";
    /**
     * 七牛获取token错误
     */
    String QINIU_IS_ILLEGAL = "90002";
    

}
