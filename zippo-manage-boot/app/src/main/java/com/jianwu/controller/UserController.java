package com.jianwu.controller;

import com.jianwu.domain.request.ZipSysUserRequest;
import com.jianwu.domain.result.Page;
import com.jianwu.domain.result.ResultResponse;
import com.jianwu.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

@Api(value = "用户模块接口", description = "用户模块接口")
@RestController
@RequestMapping("/zip/sys/user")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;


    @ApiOperation(value="用户列表", notes="用户列表",httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "page", value = "页数", paramType="query",dataType = "String"),
            @ApiImplicitParam(name = "pageSize", value = "每页数量", paramType="query",dataType = "String"),
    })
    @RequestMapping(value = "/list",method = { RequestMethod.GET })
    public @ResponseBody
    ResultResponse list(@ApiIgnore Page page) {
        return userService.list(getUserId(),page);
    }

    @ApiOperation(value="新增用户", notes="新增用户",httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "name", value = "用户名称", paramType="query",dataType = "String"),
            @ApiImplicitParam(name = "phone", value = "手机号", paramType="query",dataType = "String"),
            @ApiImplicitParam(name = "password", value = "密码", paramType="query",dataType = "String"),
            @ApiImplicitParam(name = "id", value = "用户id 为空 则新增", paramType="query",dataType = "String"),
    })
    @RequestMapping(value = "/addOrUpdate",method = { RequestMethod.POST })
    public @ResponseBody
    ResultResponse addOrUpdate(@ApiIgnore ZipSysUserRequest userRequest) {
        return userService.addOrUpdate(userRequest);
    }

    @ApiOperation(value="删除用户", notes="删除用户",httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "id", value = "用户id", paramType="query",dataType = "String"),
    })
    @RequestMapping(value = "/delete",method = { RequestMethod.POST })
    public @ResponseBody
    ResultResponse delete(@Param("id")Integer id) {
        return userService.delete(id);
    }

    @ApiOperation(value = "修改登录密码接口", notes = "修改登录密码接口", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "oldPwd", value = "旧密码", paramType = "query", dataType = "String", required = true),
            @ApiImplicitParam(name = "newPwd", value = "新密码", paramType = "query", dataType = "String", required = true),
            @ApiImplicitParam(name = "Cookie", value = "Cookie", paramType = "header", dataType = "String"),
    })
    @RequestMapping(value = "/editPwd", method = RequestMethod.POST)
    public ResultResponse editPwd(@RequestParam("oldPwd") String oldPwd,
                                  @RequestParam("newPwd") String newPwd ) {
        return userService.editPwd(getUserId(),oldPwd, newPwd);
    }

    @ApiOperation(value = "获取用户信息接口", notes = "获取用户信息接口", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/info",method = RequestMethod.GET)
    public ResultResponse getUserInfo(){
        return userService.getUserResponse(getUserId());
    }

    @ApiOperation(value="按条件查询用户列表", notes="用户列表",httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "page", value = "页数", paramType="query",dataType = "String"),
            @ApiImplicitParam(name = "pageSize", value = "每页数量", paramType="query",dataType = "String"),
            @ApiImplicitParam(name = "name", value = "姓名", paramType="query",dataType = "String"),
            @ApiImplicitParam(name = "phone", value = "账号", paramType="query",dataType = "String"),
    })
    @RequestMapping(value = "/queryLike",method = { RequestMethod.GET })
    public @ResponseBody
    ResultResponse queryLike(@ApiIgnore Page page,@RequestParam("name") String name, @RequestParam("phone") String phone) {

        return userService.queryLike(page,name,phone);
    }

}
