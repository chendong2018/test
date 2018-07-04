package com.jianwu.controller;

import com.jianwu.comm.Const;
import com.jianwu.domain.ZipManageUser;
import com.jianwu.domain.result.ExceptionMsg;
import com.jianwu.domain.result.ResultResponse;
import com.jianwu.utils.Des3EncryptionUtil;
import com.jianwu.utils.MD5Util;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class BaseController {

    protected Logger logger =  LoggerFactory.getLogger(this.getClass());
    
    protected ResultResponse result(ExceptionMsg msg){
    	return new ResultResponse();
    }
    protected ResultResponse result(){
    	return new ResultResponse();
    }
    
    protected HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }
    
    protected HttpSession getSession() {
        return getRequest().getSession();
    }

    
    protected ZipManageUser getUser() {
        return (ZipManageUser) getSession().getAttribute(Const.LOGIN_SESSION_KEY);
    }

    protected void removeUser(){
        getSession().removeAttribute(Const.LOGIN_SESSION_KEY);
    }
    
    protected Integer getUserId() {
        Integer id=0;
        ZipManageUser user=getUser();
    	if(user!=null){
    		id=user.getId();
    	}
        return id;
    }
    
    protected String getUserName() {
    	String userName="";
        ZipManageUser user=getUser();
    	if(user!=null){
    		userName=user.getName();
    	}
        return userName;
    }
    
    protected String getUserIp() {
        String value = getRequest().getHeader("X-Real-IP");
        if (StringUtils.isNotBlank(value) && !"unknown".equalsIgnoreCase(value)) {
            return value;
        } else {
            return getRequest().getRemoteAddr();
        }
    }
    
    protected String getPwd(String salt,String password){
    	try {
    		String pwd = MD5Util.digest(password,salt);
    		return pwd;
		} catch (Exception e) {
			logger.error("密码加密异常：",e);
		}
    	return null;
    }

    protected String cookieSign(String value){
        try{
            value = value + Const.PASSWORD_KEY;
            String sign = Des3EncryptionUtil.encode(Const.DES3_KEY,value);
            return sign;
        }catch (Exception e){
            logger.error("cookie签名异常：",e);
        }
        return null;
    }
}
