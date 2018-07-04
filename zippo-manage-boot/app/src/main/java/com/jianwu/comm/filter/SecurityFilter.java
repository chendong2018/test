package com.jianwu.comm.filter;


import com.jianwu.comm.Const;;
import com.jianwu.domain.ZipManageUser;
import com.jianwu.domain.result.ResultResponse;
import com.jianwu.domain.result.ZipSysUserResponse;
import com.jianwu.manager.UserManager;
import com.jianwu.service.UserService;
import com.jianwu.utils.Des3EncryptionUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class SecurityFilter implements Filter {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());
    private static Set<String> GreenUrlSet = new HashSet<String>();

    @Autowired
    private UserManager userManager;

    @Override
    public void init(FilterConfig arg0) throws ServletException {
        // TODO Auto-generated method stub
        GreenUrlSet.add("/zip/wechat");
        GreenUrlSet.add("/zip/goodsOrder/saveByCart");
        GreenUrlSet.add("/zip/wepay/pay/toPay");
    }

    @Override
    public void doFilter(ServletRequest srequest, ServletResponse sresponse, FilterChain filterChain)
            throws IOException, ServletException {
        // TODO Auto-generated method stub
        HttpServletRequest request = (HttpServletRequest) srequest;
        String uri = request.getRequestURI();
        if (request.getSession().getAttribute(Const.LOGIN_SESSION_KEY) == null) {
            Cookie[] cookies = request.getCookies();
            if (containsSuffix(uri) || GreenUrlSet.contains(uri) || containsKey(uri)) {
                logger.debug("don't check  url , " + request.getRequestURI());
                filterChain.doFilter(srequest, sresponse);
                return;
            } else if (cookies != null) {
                boolean flag = true;
                for (int i = 0; i < cookies.length; i++) {
                    Cookie cookie = cookies[i];
                    if (cookie.getName().equals(Const.LOGIN_SESSION_KEY)) {
                        if (StringUtils.isNotBlank(cookie.getValue())) {
                            flag = false;
                        } else {
                            break;
                        }
                        String value = getUserId(cookie.getValue());
                        Integer userId = 0;
                        if (userManager == null) {
                            BeanFactory factory = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext());
                            userManager = factory.getBean(UserManager.class);
                        }
                        if (StringUtils.isNotBlank(value)) {
                            userId = Integer.parseInt(value);
                        }
                        ZipManageUser zipManageUser = userManager.findUserById(userId);
                        if (zipManageUser!=null) {
                            logger.info("userId :" + zipManageUser.getId());
                            request.getSession().setAttribute(Const.LOGIN_SESSION_KEY, zipManageUser);
                            filterChain.doFilter(srequest, sresponse);
                            return;
                        }
                    }
                }
                if (flag) {
                    HttpServletResponse response = (HttpServletResponse) sresponse;
                    response.sendRedirect("/#/login");
                }
            } else {
                //跳转到登陆页面

                HttpServletResponse response = (HttpServletResponse) sresponse;
                response.sendRedirect("/#/login");

            }
        } else {
            filterChain.doFilter(srequest, sresponse);
        }
    }


    /**
     * @param url
     * @return
     * @author neo
     * @date 2016-5-4
     */
    private boolean containsSuffix(String url) {
        if (url.endsWith(".js")
                || url.endsWith(".css")
                || url.endsWith(".jpg")
                || url.endsWith(".gif")
                || url.endsWith(".png")
                || url.endsWith(".html")
                || url.endsWith(".eot")
                || url.endsWith(".svg")
                || url.endsWith(".ttf")
                || url.endsWith(".woff")
                || url.endsWith(".ico")
                || url.endsWith(".woff2")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * @param url
     * @return
     * @author neo
     * @date 2016-5-4
     */
    private boolean containsKey(String url) {
        if (url.startsWith("/swagger")
                || url.startsWith("/v2/api-docs")
                || url.startsWith("/webjars")
                || url.startsWith("/configuration")
                || url.startsWith("/zip/sys/login")
                || url.startsWith("/zip/loginmn")
                || url.startsWith("/zip/wechat")) {
            return true;
        } else {
            return false;
        }
    }


    @Override
    public void destroy() {
        // TODO Auto-generated method stub
    }

    public String codeToString(String str) {
        String strString = str;
        try {
            byte tempB[] = strString.getBytes("ISO-8859-1");
            strString = new String(tempB);
            return strString;
        } catch (Exception e) {
            return strString;
        }
    }

    public String getRef(HttpServletRequest request) {
        String referer = "";
        String param = this.codeToString(request.getQueryString());
        if (StringUtils.isNotBlank(request.getContextPath())) {
            referer = referer + request.getContextPath();
        }
        if (StringUtils.isNotBlank(request.getServletPath())) {
            referer = referer + request.getServletPath();
        }
        if (StringUtils.isNotBlank(param)) {
            referer = referer + "?" + param;
        }
        request.getSession().setAttribute(Const.LAST_REFERER, referer);
        return referer;
    }

    public String getUserId(String value) {
        try {
            String userId = Des3EncryptionUtil.decode(Const.DES3_KEY, value);
            userId = userId.substring(0, userId.indexOf(Const.PASSWORD_KEY));
            return userId;
        } catch (Exception e) {
            logger.error("解析cookie异常：", e);
        }
        return null;
    }
}