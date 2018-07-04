package com.jianwu.controller;


import com.jianwu.domain.result.Result;
import com.jianwu.domain.result.ResultResponse;
import com.jianwu.exception.MobileException;
import com.jianwu.service.QiNiuCloudService;
import com.jianwu.utils.ErrorCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "七牛接口模块", description = "七牛接口模块")
@RestController
@RequestMapping(value="/zip/qiniu")
public class QiniuController extends BaseController {
    @Autowired
    QiNiuCloudService qiNiuService;

    @ApiOperation(value = "获取七牛token", notes = "获取七牛token", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getToken",method = RequestMethod.GET)
    public ResultResponse getQiNiuToken() {
        try {
            Result result = qiNiuService.getToken();
            if(result.isSuccess()) {
                return ResultResponse.success(result.getData());
            } else {
                return ResultResponse.error(ErrorCode.QINIU_IS_ILLEGAL, "获取七牛token失败");
            }
        } catch (Exception e) {
            logger.error("获取七牛token失败:{}", e);
            throw new MobileException("获取七牛token失败", ErrorCode.QINIU_IS_ILLEGAL);
        }
    }


}
