package com.jianwu.controller;


import com.jianwu.comm.Const;
import com.jianwu.domain.ZipManageUser;
import com.jianwu.domain.request.LoginRequest;
import com.jianwu.domain.result.ResultResponse;
import com.jianwu.service.UserService;
import com.jianwu.utils.ErrorCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;


@Api(value = "登录接口模块", description = "登录接口模块")
@RestController
@RequestMapping(value = "/zip/sys")
public class LoginController extends BaseController {

    @Autowired
    private UserService userService;


    @ApiOperation(value = "登录接口", notes = "登录接口", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "phone", defaultValue = "15117174450", value = "手机号", paramType = "query", dataType = "String", required = true),
            @ApiImplicitParam(name = "password", defaultValue = "e10adc3949ba59abbe56e057f20f883e", value = "密码", paramType = "query", dataType = "String", required = true),

    })
    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json")
    private ResultResponse login(@Valid LoginRequest loginRequest,HttpServletResponse response) {
        logger.debug("登陆zippo系统《login》 账号：{}；登陆密码：{}" , loginRequest.getPhone(),loginRequest.getPassword());
        ResultResponse<ZipManageUser> resultResponse= userService.login(loginRequest);
        if (resultResponse.getCode().equals("200")){
            Cookie cookie = new Cookie(Const.LOGIN_SESSION_KEY, cookieSign(resultResponse.getData().getId().toString()));
            cookie.setMaxAge(Const.COOKIE_TIMEOUT);
            cookie.setPath("/");
            response.addCookie(cookie);
            getSession().setAttribute(Const.LOGIN_SESSION_KEY, resultResponse.getData());
        }
        return resultResponse;
    }

    /**
     * 登出
     *
     * @return
     */
    @ApiOperation(value = "退出登录接口", notes = "退出登录接口", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "Cookie", value = "Cookie", paramType = "header", dataType = "String"),
    })
    @RequestMapping(value = "/loginOut", method = RequestMethod.POST, produces = "application/json")
    public ResultResponse loginOut() {
        try {
            removeUser();
            return ResultResponse.success(null);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultResponse.error(ErrorCode.LOGIN_OUT_FIRE, "登出失败");
        }
    }


    @ApiOperation(value = "登录接口", notes = "登录接口", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams(value = {
//            @ApiImplicitParam(name = "phone", defaultValue = "15117174450", value = "手机号", paramType = "query", dataType = "String", required = true),
//            @ApiImplicitParam(name = "password", defaultValue = "e10adc3949ba59abbe56e057f20f883e", value = "密码", paramType = "query", dataType = "String", required = true),
    })
    @RequestMapping(value = "/loginmn", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody
    ResultResponse login(HttpServletResponse response) {
        LoginRequest loginRequest=new LoginRequest();
        loginRequest.setId(9007);
        loginRequest.setName("测试用户");
        loginRequest.setPhone("15117174450");
        Cookie cookie = new Cookie(Const.LOGIN_SESSION_KEY, cookieSign(loginRequest.getId().toString()));
        cookie.setMaxAge(Const.COOKIE_TIMEOUT);
        cookie.setPath("/");
        response.addCookie(cookie);
        getSession().setAttribute(Const.LOGIN_SESSION_KEY, loginRequest);
        return ResultResponse.success("");

    }
}
