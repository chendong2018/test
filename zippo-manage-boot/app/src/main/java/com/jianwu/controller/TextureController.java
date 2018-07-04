package com.jianwu.controller;

import com.jianwu.domain.request.ZipTextureRequest;
import com.jianwu.domain.result.Page;
import com.jianwu.domain.result.ResultResponse;
import com.jianwu.service.TextureService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

@Api(value = "材质模块接口", description = "材质模块接口")
@RestController
@RequestMapping("/zip/sys/texture")
public class TextureController extends BaseController {

    @Autowired
    private TextureService textureService;


    @ApiOperation(value="材质列表", notes="材质列表",httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "page", value = "页数", paramType="query",dataType = "String"),
            @ApiImplicitParam(name = "pageSize", value = "每页数量", paramType="query",dataType = "String"),
            @ApiImplicitParam(name = "textureOrder", value = "材质编号", paramType="query",dataType = "String"),
            @ApiImplicitParam(name = "textureName", value = "材质名称", paramType="query",dataType = "String"),
            @ApiImplicitParam(name = "status", value = "状态（0上架 1下架 2全部）", paramType="query",dataType = "String"),
    })
    @RequestMapping(value = "/list",method = { RequestMethod.GET })
    public @ResponseBody
    ResultResponse list(@ApiIgnore Page page,@RequestParam("textureOrder") String textureOrder,
                        @RequestParam("textureName")String textureName, @RequestParam("status") Integer status) {

        return textureService.list(page,textureOrder,textureName,status);
    }

    @ApiOperation(value="新增修改材质", notes="新增修改材质",httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "textureOrder", value = "材质编号", paramType="query",dataType = "String"),
            @ApiImplicitParam(name = "sort", value = "排序", paramType="query",dataType = "String"),
            @ApiImplicitParam(name = "textureName", value = "材质名称", paramType="query",dataType = "String"),
            @ApiImplicitParam(name = "frontPicture", value = "正面图片", paramType="query",dataType = "String"),
            @ApiImplicitParam(name = "backPicture", value = "背面图片", paramType="query",dataType = "String"),
            @ApiImplicitParam(name = "sidePicture", value = "侧面", paramType="query",dataType = "String"),
            @ApiImplicitParam(name = "noSidePicture", value = "非侧面", paramType="query",dataType = "String"),
            @ApiImplicitParam(name = "topPicture", value = "顶图", paramType="query",dataType = "String"),
            @ApiImplicitParam(name = "smallPicture", value = "小图", paramType="query",dataType = "String"),
            @ApiImplicitParam(name = "status", value = "状态（0启用 1禁用）", paramType="query",dataType = "String"),
            @ApiImplicitParam(name = "id", value = "用户id 为空 则新增", paramType="query",dataType = "String"),
    })
    @RequestMapping(value = "/addOrUpdate",method = { RequestMethod.POST })
    public @ResponseBody
    ResultResponse addOrUpdate(@ApiIgnore ZipTextureRequest zipTextureRequest) {
        if (getUser()  == null){
            return ResultResponse.error("500","获取用户信息失败");
        }
        return textureService.addOrUpdate(zipTextureRequest);
    }

    @ApiOperation(value="删除材质", notes="删除材质",httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "id", value = "用户id", paramType="query",dataType = "String"),
    })
    @RequestMapping(value = "/delete",method = { RequestMethod.POST })
    public @ResponseBody
    ResultResponse delete(Integer id) {
        if (getUser()  == null){
            return ResultResponse.error("500","获取用户信息失败");
        }
        return textureService.delete(id);
    }

    @ApiOperation(value = "获取单条信息接口", notes = "获取单条信息接口", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/info",method = RequestMethod.GET)
    public ResultResponse getInfo(Integer id){
        if (getUser()  == null){
            return ResultResponse.error("500","获取用户信息失败");
        }
        return textureService.getInfo(id);
    }



}
