package com.jianwu.controller;


import com.jianwu.domain.request.ZipCommodityRequest;
import com.jianwu.domain.result.Page;
import com.jianwu.domain.result.ResultResponse;
import com.jianwu.service.CommodityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

@Api(value = "商品模块接口", description = "商品模块接口")
@RestController
@RequestMapping("/zip/sys/commodity")
public class CommodityController extends BaseController {

    @Autowired
    private CommodityService commodityService;


    @ApiOperation(value="商品列表", notes="商品列表",httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "page", value = "页数", paramType="query",dataType = "String"),
            @ApiImplicitParam(name = "pageSize", value = "每页数量", paramType="query",dataType = "String"),
            @ApiImplicitParam(name = "commodityOrder", value = "商品编号", paramType="query",dataType = "String"),
            @ApiImplicitParam(name = "commodityName", value = "商品标题", paramType="query",dataType = "String"),
            @ApiImplicitParam(name = "status", value = "状态（0上架 1下架 2全部）", paramType="query",dataType = "String"),
            @ApiImplicitParam(name = "commodityStatus", value = "商品状态(1商家商品，2自定义商品)", paramType="query",dataType = "String"),
    })
    @RequestMapping(value = "/list",method = { RequestMethod.GET })
    public @ResponseBody
    ResultResponse list(@ApiIgnore Page page, @RequestParam("commodityOrder") String commodityOrder,
                        @RequestParam("commodityName")String commodityName, @RequestParam("status") Integer status, @RequestParam("commodityStatus") Integer commodityStatus) {
        if (getUser() == null){
            return ResultResponse.error("500","获取用户信息失败");
        }
        return commodityService.list(page,commodityOrder,commodityName,status,commodityStatus);
    }

    @ApiOperation(value="新增修改商品", notes="新增修改商品",httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "commodityOrder", value = "商品编号", paramType="query",dataType = "String"),
            @ApiImplicitParam(name = "commodityName", value = "产品名称", paramType="query",dataType = "String"),
            @ApiImplicitParam(name = "price", value = "价格", paramType="query",dataType = "String"),
            @ApiImplicitParam(name = "picture", value = "商品主图", paramType="query",dataType = "String"),
            @ApiImplicitParam(name = "pictures", value = "订单小图", paramType="query",dataType = "String"),
            @ApiImplicitParam(name = "textureId", value = "型号即材质id", paramType="query",dataType = "String"),
            @ApiImplicitParam(name = "technology", value = "工艺 1 彩印", paramType="query",dataType = "String"),
            @ApiImplicitParam(name = "status", value = "状态（0上架 1下架）", paramType="query",dataType = "String"),
            @ApiImplicitParam(name = "sort", value = "排序", paramType="query",dataType = "String"),
            @ApiImplicitParam(name = "commodityStatus", value = "商品状态(1商家商品，2自定义商品)", paramType="query",dataType = "String"),
            @ApiImplicitParam(name = "picturesDetails", value = "商品详情图", paramType="query",dataType = "String"),
            @ApiImplicitParam(name = "expressType", value = "是否包邮 1 包邮 0 不包邮", paramType="query",dataType = "String"),
            @ApiImplicitParam(name = "express", value = "邮费", paramType="query",dataType = "String"),
            @ApiImplicitParam(name = "id", value = "用户id 为空 则新增", paramType="query",dataType = "String"),
    })
    @RequestMapping(value = "/addOrUpdate",method = { RequestMethod.POST })
    public @ResponseBody
    ResultResponse addOrUpdate(@ApiIgnore ZipCommodityRequest zipCommodityRequest) {
        if (getUser() == null){
            return ResultResponse.error("500","获取用户信息失败");
        }
        return commodityService.addOrUpdate(zipCommodityRequest);
    }

    @ApiOperation(value="删除商品", notes="删除商品",httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "id", value = "用户id", paramType="query",dataType = "String"),
    })
    @RequestMapping(value = "/delete",method = { RequestMethod.POST })
    public @ResponseBody
    ResultResponse delete(Integer id) {
        if (getUser() == null){
            return ResultResponse.error("500","获取用户信息失败");
        }
        return commodityService.delete(id);
    }

    @ApiOperation(value = "获取单条信息接口", notes = "获取单条信息接口", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/info",method = RequestMethod.GET)
    public ResultResponse getInfo(Integer id){
        if (getUser() ==null){
            return ResultResponse.error("500","获取用户信息失败");
        }
        return commodityService.getInfo(id);
    }

}
