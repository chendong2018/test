package com.jianwu.controller;

import com.jianwu.domain.request.ZipOrderFormRequest;
import com.jianwu.domain.result.Page;
import com.jianwu.domain.result.ResultResponse;
import com.jianwu.service.OrderFormService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

@Api(value = "订单模块接口", description = "订单模块接口")
@RestController
@RequestMapping("/zip/sys/order")
public class OrderFormController extends BaseController {

    @Autowired
    private OrderFormService orderFormService;


    @ApiOperation(value="订单列表", notes="订单列表",httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "page", value = "页数", paramType="query",dataType = "String"),
            @ApiImplicitParam(name = "pageSize", value = "每页数量", paramType="query",dataType = "String"),
            @ApiImplicitParam(name = "startTime", value = "开始时间", paramType="query",dataType = "String"),
            @ApiImplicitParam(name = "endTime", value = "结束时间", paramType="query",dataType = "String"),
            @ApiImplicitParam(name = "openId", value = "客户id", paramType="query",dataType = "String"),
            @ApiImplicitParam(name = "wechatUserName", value = "客户名称", paramType="query",dataType = "String"),
            @ApiImplicitParam(name = "orderNumber", value = "订单编号", paramType="query",dataType = "String"),
            @ApiImplicitParam(name = "status", value = "状态（1待付款，2待发货，3待收货，4已收货，5全部）", paramType="query",dataType = "String"),
    })
    @RequestMapping(value = "/list",method = { RequestMethod.GET })
    public @ResponseBody
    ResultResponse list(@ApiIgnore Page page, @RequestParam("startTime") String startTime,
                        @RequestParam("endTime")String endTime, @RequestParam("status") Integer status, @RequestParam("wechatUserId")Integer wechatUserId, @RequestParam("wechatUserName") String wechatUserName, @RequestParam("orderNumber") String orderNumber) {
        if (getUser() == null){
            return ResultResponse.error("500","获取用户信息失败");
        }
        return orderFormService.list(page,startTime,endTime,status,wechatUserId,wechatUserName,orderNumber,null);
    }

    @ApiOperation(value="新增修改订单", notes="新增修改订单",httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "orderDate", value = "订单日期", paramType="query",dataType = "String"),
            @ApiImplicitParam(name = "commodityId", value = "商品id", paramType="query",dataType = "String"),
            @ApiImplicitParam(name = "wechatUserId", value = "客户id", paramType="query",dataType = "String"),
            @ApiImplicitParam(name = "addressId", value = "选择的收获地址", paramType="query",dataType = "String"),
            @ApiImplicitParam(name = "address", value = "选择的收获地址详情", paramType="query",dataType = "String"),
            @ApiImplicitParam(name = "number", value = "购买数量", paramType="query",dataType = "String"),
            @ApiImplicitParam(name = "price", value = "单价", paramType="query",dataType = "String"),
            @ApiImplicitParam(name = "express", value = "邮费", paramType="query",dataType = "String"),
            @ApiImplicitParam(name = "orderNumber", value = "订单编号", paramType="query",dataType = "String"),
            @ApiImplicitParam(name = "trackingNumber", value = "快递单号", paramType="query",dataType = "String"),
            @ApiImplicitParam(name = "expressType", value = "是否包邮 1 包邮 0 不包邮", paramType="query",dataType = "String"),
            @ApiImplicitParam(name = "status", value = "订单状态（1待付款，2待发货，3待收货，4已收货）", paramType="query",dataType = "String"),
            @ApiImplicitParam(name = "textureId", value = "型号即材质id", paramType="query",dataType = "String"),
            @ApiImplicitParam(name = "technology", value = "工艺 1 彩印", paramType="query",dataType = "String"),
            @ApiImplicitParam(name = "optimalScale", value = "优标 0是 1否", paramType="query",dataType = "String"),
            @ApiImplicitParam(name = "id", value = "用户id 为空 则新增", paramType="query",dataType = "String"),
    })
    @RequestMapping(value = "/addOrUpdate",method = { RequestMethod.POST })
    public @ResponseBody
    ResultResponse addOrUpdate(@ApiIgnore ZipOrderFormRequest zipOrderFormRequest) {
        if (getUser()  == null){
            return ResultResponse.error("500","获取用户信息失败");
        }
        return orderFormService.addOrUpdate(zipOrderFormRequest);
    }

    @ApiOperation(value = "获取单条信息接口", notes = "获取单条信息接口", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/info",method = RequestMethod.GET)
    public ResultResponse getInfo(Integer id){
        if (getUser() == null){
            return ResultResponse.error("500","获取用户信息失败");
        }
        return orderFormService.getInfo(id);
    }

}
