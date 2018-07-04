package com.jianwu.controller;



import com.jianwu.domain.request.ZipCustomerServiceUserRequest;
import com.jianwu.domain.result.Page;
import com.jianwu.domain.result.ResultResponse;
import com.jianwu.service.CustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

@Api(value = "客服模块接口", description = "客服模块接口")
@RestController
@RequestMapping("/zip/sys/customer")
public class CustomerServiceController extends BaseController {

    @Autowired
    private CustomerService customerService;


    @ApiOperation(value="客服列表", notes="客服列表",httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "page", value = "页数", paramType="query",dataType = "String"),
            @ApiImplicitParam(name = "pageSize", value = "每页数量", paramType="query",dataType = "String"),
            @ApiImplicitParam(name = "customerServiceName", value = "客服名字", paramType="query",dataType = "String"),
            @ApiImplicitParam(name = "wechatNumber", value = "微信号", paramType="query",dataType = "String"),
            @ApiImplicitParam(name = "status", value = "状态（0启用 1禁用 2全部）", paramType="query",dataType = "String"),
    })
    @RequestMapping(value = "/list",method = { RequestMethod.GET })
    public @ResponseBody
    ResultResponse list(@ApiIgnore Page page, @RequestParam("customerServiceName") String customerServiceName,
                        @RequestParam("wechatNumber")String wechatNumber, @RequestParam("status") Integer status) {
        if (getUser() == null){
            return ResultResponse.error("500","获取用户信息失败");
        }
        return customerService.list(page,customerServiceName,wechatNumber,status);
    }

    @ApiOperation(value="新增修改客服", notes="新增修改客服",httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "customerServiceName", value = "客服姓名", paramType="query",dataType = "String"),
            @ApiImplicitParam(name = "wechatNumber", value = "微信号", paramType="query",dataType = "String"),
            @ApiImplicitParam(name = "quickMark", value = "二维码", paramType="query",dataType = "String"),
            @ApiImplicitParam(name = "status", value = "状态（0启用 1禁用）", paramType="query",dataType = "String"),
            @ApiImplicitParam(name = "id", value = "用户id 为空 则新增", paramType="query",dataType = "String"),
    })
    @RequestMapping(value = "/addOrUpdate",method = { RequestMethod.POST })
    public @ResponseBody
    ResultResponse addOrUpdate(@ApiIgnore ZipCustomerServiceUserRequest zipCustomerServiceUserRequest) {
        if (getUser() == null){
            return ResultResponse.error("500","获取用户信息失败");
        }
        return customerService.addOrUpdate(zipCustomerServiceUserRequest);
    }

    @ApiOperation(value="删除用户", notes="删除用户",httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "id", value = "用户id", paramType="query",dataType = "String"),
    })
    @RequestMapping(value = "/delete",method = { RequestMethod.POST })
    public @ResponseBody
    ResultResponse delete(Integer id) {
        if (getUser()  == null){
            return ResultResponse.error("500","获取用户信息失败");
        }
        return customerService.delete(id);
    }

    @ApiOperation(value = "获取单条信息接口", notes = "获取单条信息接口", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/info",method = RequestMethod.GET)
    public ResultResponse getInfo(Integer id){
        if (getUser()  == null){
            return ResultResponse.error("500","获取用户信息失败");
        }
        return customerService.getInfo(id);
    }

    @ApiOperation(value="启用禁用", notes="启用禁用",httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "status", value = "0启用1禁用", paramType="query",dataType = "String"),
            @ApiImplicitParam(name = "id", value = "用户id 为空 则新增", paramType="query",dataType = "String"),
    })
    @RequestMapping(value = "/enable/disable",method = { RequestMethod.POST })
    public @ResponseBody
    ResultResponse enableDisable(@RequestParam("status") Integer status, @RequestParam("id") Integer id) {
        if (getUser() ==null){
            return ResultResponse.error("500","获取用户信息失败");
        }
        return customerService.enableDisable(status,id);
    }

}
