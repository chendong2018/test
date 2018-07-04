package com.jianwu.controller;

import com.jianwu.domain.request.ZipTemplateRequest;
import com.jianwu.domain.result.Page;
import com.jianwu.domain.result.ResultResponse;
import com.jianwu.manager.TemplateManager;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

@Api(value = "后台模板接口", description = "后台模板接口")
@RestController
@RequestMapping("/zip/template")
public class TemplateController extends BaseController {

    @Autowired
    private TemplateManager templateManager;


    @ApiOperation(value="模板列表", notes="模板列表",httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "page", value = "页数", paramType="query",dataType = "String"),
            @ApiImplicitParam(name = "pageSize", value = "每页数量", paramType="query",dataType = "String"),
            @ApiImplicitParam(name = "templateOrder", value = "模板编号", paramType="query",dataType = "String"),
            @ApiImplicitParam(name = "templateName", value = "模板名称", paramType="query",dataType = "String"),
            @ApiImplicitParam(name = "module", value = "模块  1 正面 2 背面", paramType="query",dataType = "String"),
            @ApiImplicitParam(name = "status", value = "状态 0上架 1下架", paramType="query",dataType = "String"),
    })
    @RequestMapping(value = "/list",method = { RequestMethod.GET })
    public ResultResponse list(@ApiIgnore Page page,
                              String templateOrder,
                               String templateName,
                                Integer module,
                                Integer status ) {
        return templateManager.list(page,templateOrder,templateName,module,status);
    }

    @ApiOperation(value="新增模板", notes="新增模板",httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/add",method = { RequestMethod.POST })
    public ResultResponse add(@RequestBody @ApiParam(name="模板对象",value="传入json格式",required=true) ZipTemplateRequest zipTemplateRequest) {
        return templateManager.add(zipTemplateRequest);
    }

    @ApiOperation(value="修改模板", notes="修改模板",httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/update",method = { RequestMethod.POST })
    public @ResponseBody
    ResultResponse update(@RequestBody @ApiParam(name="模板对象",value="传入json格式",required=true) ZipTemplateRequest zipTemplateRequest) {
        return templateManager.update(zipTemplateRequest);
    }

    @ApiOperation(value="删除模板", notes="删除模板",httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "id", value = "模板id", paramType="query",dataType = "String"),
    })
    @RequestMapping(value = "/delete",method = { RequestMethod.POST })
    public @ResponseBody
    ResultResponse delete(Integer id) {
        return templateManager.delete(id);
    }

    @ApiOperation(value = "下架模板", notes = "下架模板", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "id", value = "模板id", paramType="query",dataType = "String"),
            @ApiImplicitParam(name = "status", value = "状态 0上架 1下架 -1 删除", paramType="query",dataType = "String"),
    })
    @RequestMapping(value = "/editTemplate", method = RequestMethod.POST)
    public ResultResponse editTemplate(@RequestParam(value="id") Integer id,
                                  @RequestParam(value="status") Integer status) {
        return templateManager.editTemplate(id, status);
    }

    /**
     * @ClassName: TemplateController
     * @Description: 获取修改详情信息
     * @Author: chenDong
     * @Date: 2018/6/1 18:11
     * @Remark:
     */
    @ApiOperation(value = "获取修改模板详情信息", notes = "获取修改模板详情信息", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "id", value = "模板id", paramType="query",dataType = "String"),
    })
    @RequestMapping(value = "/findTemplate", method = RequestMethod.GET)
    public ResultResponse findTemplate(@RequestParam(value="id") Integer id ) {
        return templateManager.findTemplate(id);
    }



}
