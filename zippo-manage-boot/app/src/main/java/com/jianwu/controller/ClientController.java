package com.jianwu.controller;


import com.jianwu.domain.result.Page;
import com.jianwu.domain.result.ResultResponse;
import com.jianwu.service.ClientService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

@Api(value = "客户模块接口", description = "客户模块接口")
@RestController
@RequestMapping("/zip/sys/client")
public class ClientController extends BaseController {

    @Autowired
    private ClientService clientService;


    @ApiOperation(value="客户列表", notes="客户列表",httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "page", value = "页数", paramType="query",dataType = "String"),
            @ApiImplicitParam(name = "pageSize", value = "每页数量", paramType="query",dataType = "String"),
            @ApiImplicitParam(name = "nickName", value = "昵称", paramType="query",dataType = "String"),
    })
    @RequestMapping(value = "/list",method = { RequestMethod.GET })
    public @ResponseBody
    ResultResponse list(@ApiIgnore Page page, @RequestParam("nickName") String niceName) {
        if (getUser() == null){
            return ResultResponse.error("500","获取用户信息失败");
        }
        return clientService.list(page,niceName);
    }

}
