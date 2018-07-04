package com.jianwu.controller;

import com.google.gson.Gson;
import com.jianwu.domain.request.*;
import com.jianwu.domain.result.Expree;
import com.jianwu.domain.result.Page;
import com.jianwu.domain.result.Result;
import com.jianwu.domain.result.ResultResponse;
import com.jianwu.exception.MobileException;
import com.jianwu.manager.*;

import com.jianwu.service.QiNiuCloudService;
import com.jianwu.utils.ErrorCode;
import com.jianwu.utils.HttpUtil;
import com.jianwu.utils.StringUtil;
import io.swagger.annotations.*;
import javafx.scene.paint.Color;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author chendong
 */
@Api(value = "微信小程序接口", description = "微信小程序接口")
@Controller
@RequestMapping("/zip/wechat/")
public class WeChatTemplateController extends BaseController {

    @Autowired
    private TemplateManager templateManager;

    @Autowired
    CommodityManager commodityManager;

    @Autowired
    AddressManager addressManager;

    @Autowired
    OrderFormManager orderFormManager;

    @Autowired
    FeedBackManager feedBackManager;

    @Autowired
    CustomerManager customerManager;

    @Autowired
    TextureManager textureManager;

    @Autowired
    ClientManager clientManager;

    @Autowired
    TemplateCustomManager templateCustomManager;

    @Autowired
    QiNiuCloudService qiNiuService;

    @ApiOperation(value = "获取七牛token", notes = "获取七牛token", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getToken", method = RequestMethod.GET)
    @ResponseBody
    public ResultResponse getQiNiuToken() {
        try {
            Result result = qiNiuService.getToken();
            if (result.isSuccess()) {
                return ResultResponse.success(result.getData());
            } else {
                return ResultResponse.error(ErrorCode.QINIU_IS_ILLEGAL, "获取七牛token失败");
            }
        } catch (Exception e) {
            logger.error("获取七牛token失败:{}", e);
            throw new MobileException("获取七牛token失败", ErrorCode.QINIU_IS_ILLEGAL);
        }
    }

    @ApiOperation(value = "获取商品列表", notes = "获取商品列表", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/commodityList", method = {RequestMethod.GET})
    @ResponseBody
    public ResultResponse list() {
        Page page = new Page();
        page.setPageSize(100000000);
        page.setPage(1);
        // 上架
        Integer status = 0;
        // 公司商品
        Integer commodityStatus = 1;
        return commodityManager.list(page, null, null, status, commodityStatus);
    }

    @ApiOperation(value = "获取商品详细信息", notes = "获取商品详细信息", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "id", value = "商品id", paramType = "query", dataType = "String"),
    })
    @RequestMapping(value = "/commodityInfo", method = {RequestMethod.GET})
    @ResponseBody
    public ResultResponse commodityInfo(@Param("id") Integer id) {
        return commodityManager.findCommodityById(id);
    }

    @ApiOperation(value = "获取微信用户收货地址列表", notes = "获取微信用户收货地址列表", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "openId", value = "微信用户id", paramType = "query", dataType = "String"),
    })
    @RequestMapping(value = "/findAddress", method = {RequestMethod.GET})
    @ResponseBody
    public ResultResponse findAddress(@Param("openId") String openId) {
        return addressManager.findAddressByWeChatUserId(openId);
    }

    @ApiOperation(value = "新增地址", notes = "新增地址", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "consignee", value = "收货人", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "openId", value = "微信用户id", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "mobilePhone", value = "手机号码", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "region", value = "地区", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "detailAddress", value = "详细地址", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "status", value = "是否设为默认地址 1默认 0 否", paramType = "query", dataType = "String"),
    })
    @RequestMapping(value = "/addAddress", method = {RequestMethod.POST})
    @ResponseBody
    public ResultResponse addAddress(@ApiIgnore ZipAddressRequest zipAddressRequest) {
        return addressManager.addAddress(zipAddressRequest);
    }

    @ApiOperation(value = "修改地址", notes = "修改地址", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "id", value = "地址id", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "consignee", value = "收货人", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "mobilePhone", value = "手机号码", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "region", value = "地区", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "detailAddress", value = "详细地址", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "status", value = "是否设为默认地址 1默认 0 否", paramType = "query", dataType = "String"),
    })
    @RequestMapping(value = "/updateAddress", method = {RequestMethod.POST})
    @ResponseBody
    public ResultResponse updateAddress(@ApiIgnore ZipAddressRequest zipAddressRequest) {
        return addressManager.updateAddress(zipAddressRequest);
    }

    @ApiOperation(value = "删除地址或设置默认地址", notes = "删除地址或设置默认地址", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "id", value = "地址id", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "status", value = " 1默认 0 否 -1 删除", paramType = "query", dataType = "String"),
    })
    @RequestMapping(value = "/deleteAddress", method = {RequestMethod.POST})
    @ResponseBody
    public ResultResponse deleteAddress(Integer id, Integer status) {
        return addressManager.deleteAddress(id, status);
    }

    @ApiOperation(value = "反馈信息", notes = "反馈信息", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "openId", value = "反馈用户id", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "content", value = "反馈内容", paramType = "query", dataType = "String"),
    })
    @RequestMapping(value = "/feedBack", method = {RequestMethod.POST})
    @ResponseBody
    public ResultResponse feedBack(@Param("openId") String openId, @Param("status") String content) {
        return feedBackManager.add(openId, content);
    }

    @ApiOperation(value = "获取启用的客服信息", notes = "获取启用的客服信息", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/customerInfo", method = {RequestMethod.GET})
    @ResponseBody
    public ResultResponse customerInfo() {
        return customerManager.customerInfo();
    }

    @ApiOperation(value = "提交订单", notes = "提交订单", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "openId", value = "微信用户id", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "addressId", value = "地址的id", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "commodityId", value = "商品的id", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "number", value = "购买数量", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "totalAmount", value = "总金额", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "express", value = "邮费", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "express_type", value = "是否包邮 1 包邮 0 不包邮", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "commodityPicture", value = "商品图片地址", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "textureId", value = "材质id", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "technology", value = "工艺 1彩印", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "price", value = "单价", paramType = "query", dataType = "String"),
    })
    @RequestMapping(value = "/submitOrder", method = {RequestMethod.POST})
    @ResponseBody
    public ResultResponse delete(@ApiIgnore ZipOrderFormRequest zipOrderFormRequest) {
        return orderFormManager.submitOrder(zipOrderFormRequest);
    }

    @ApiOperation(value = "获取订单列表分页", notes = "获取订单列表分页", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "page", value = "当前页", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "pageSize", value = "行数", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "openId", value = "微信用户id", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "status", value = "订单状态（1待付款，2待发货，3待收货，4已收货）", paramType = "query", dataType = "String"),
    })
    @RequestMapping(value = "/orderList", method = RequestMethod.GET)
    @ResponseBody
    public ResultResponse orderList(@ApiIgnore Page page,
                                    @Param("status") Integer status,
                                    @Param("openId") String openId) {
        return orderFormManager.list(page, null, null, status, null, null, null, openId);
    }

    @ApiOperation(value = "获取订单数量", notes = "获取订单数量", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "openId", value = "微信用户id", paramType = "query", dataType = "String"),
    })
    @RequestMapping(value = "/orderNum", method = RequestMethod.GET)
    @ResponseBody
    public ResultResponse orderList(@Param("openId") String openId) {
        return orderFormManager.getOrderSumByOpenId(openId);
    }

    @ApiOperation(value = "删除订单或确认收货", notes = "删除订单", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "id", value = "订单的id", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "status", value = "订单状态（1待付款，2待发货，3待收货，4已收货） -1 删除", paramType = "query", dataType = "String"),
    })
    @RequestMapping(value = "/deleteOrder", method = {RequestMethod.POST})
    @ResponseBody
    public ResultResponse deleteOrder(@Param("id") Integer id,
                                      @Param("status") Integer status) {
        return orderFormManager.operationOrder(id, status);
    }

    /**
     * @ClassName: WeChatTemplateController
     * @Description: 获取模板列表
     * @Author: chenDong
     * @Date: 2018/6/4 9:23
     * @Remark:
     */
    @ApiOperation(value = "获取模板列表", notes = "获取模板列表", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/findTemplateList", method = RequestMethod.GET)
    @ResponseBody
    public ResultResponse findTemplateList() {
        return templateManager.findTemplateList();
    }

    /**
     * @ClassName: WeChatTemplateController
     * @Description: 获取材质列表
     * @Author: chenDong
     * @Date: 2018/6/4 10:22
     * @Remark:
     */
    @ApiOperation(value = "获取材质列表", notes = "获取材质列表", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams(value = {
    })
    @RequestMapping(value = "/findTextureList", method = RequestMethod.GET)
    @ResponseBody
    public ResultResponse findTextureList() {
        return textureManager.findTextureList();
    }

    /**
     * @ClassName: WeChatTemplateController
     * @Description: 文字转图片
     * @Author: chenDong
     * @Date: 2018/6/4 10:38
     * @Remark:
     */
    @ApiOperation(value = "文字合成all", notes = "图片合成all", httpMethod = "POST", produces = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "templateDetailId", value = "模板详情id", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "content", value = "文字内容", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "templateNum", value = "模板唯一数", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "openId", value = "微信id", paramType = "query", dataType = "String"),
    })
    @RequestMapping(value = "/wordToImg")
    public void wordToImg(@Param("templateDetailId") Integer templateDetailId, @Param("content") String content,
                          String templateNum,
                          String openId,
                          HttpServletRequest request,
                          HttpServletResponse response) {
        logger.info("进入文字合成all------------------------------------》" + content);
        ImageRequest imageRequest = new ImageRequest();
        imageRequest.setContent(content);
        imageRequest.setTemplateDetailId(templateDetailId);
        imageRequest.setTemplateNum(templateNum);
        imageRequest.setOpenId(openId);
        templateManager.wordToImg(imageRequest, request, response);
    }

    /**
     * @ClassName: WeChatTemplateController
     * @Description: 图片合成
     * @Author: chenDong
     * @Date: 2018/6/4 10:38
     * @Remark:
     */
    @ApiOperation(value = "图片合成", notes = "图片合成", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "templateDetailId", value = "模板详情id", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "templateNum", value = "模板唯一数", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "openId", value = "微信id", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "url", value = "七牛地址", paramType = "query", dataType = "String"),
    })
    @RequestMapping(value = "/imgToImg", method = RequestMethod.GET)
    public void imgToImg(@Param("templateDetailId") Integer templateDetailId,
                         String templateNum,
                         String openId,
                         String url,
                         HttpServletRequest request, HttpServletResponse response) {
        logger.info("进入图片合成------------------------------------》");
        templateManager.imgToImg(openId, templateNum, templateDetailId, response, request, url);
    }

    @ApiOperation(value = "图片合成上传功能", notes = "图片合成上传功能", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "templateDetailId", value = "模板详情id", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "templateNum", value = "模板唯一数", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "openId", value = "微信id", paramType = "query", dataType = "String"),
    })
    @RequestMapping(value = "/imgToUpload", method = RequestMethod.POST)
    @ResponseBody
    public ResultResponse imgToUpload(@Param("templateDetailId") Integer templateDetailId,
                                      String templateNum,
                                      String openId,
                                      HttpServletRequest request, HttpServletResponse response) {
        logger.info("图片合成上传功能------------------------------------》");

         return templateManager.imgToUpload(openId, templateNum, templateDetailId, response, request);
    }

    @RequestMapping(value = "/getImg", method = RequestMethod.GET)
    public void getImg(String key, HttpServletResponse response) {
        logger.info("获取图片------------------------------------》");
        templateManager.getImg(key, response);
    }

    /**
     * @ClassName: WeChatTemplateController
     * @Description: 购买商品 （自定义用到 新增商品）
     * @Author: chenDong
     * @Date: 2018/6/12 18:18
     * @Remark:
     */
    @ApiOperation(value = "购买商品", notes = "购买商品", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "commodityOrder", value = "商品编号", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "commodityName", value = "产品名称", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "price", value = "价格", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "picture", value = "商品主图", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "pictures", value = "订单小图", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "textureId", value = "型号即材质id", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "technology", value = "工艺 1 彩印", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "status", value = "状态（0上架 1下架）", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "sort", value = "排序", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "commodityStatus", value = "商品状态(1商家商品，2自定义商品)", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "picturesDetails", value = "商品详情图", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "expressType", value = "是否包邮 1 包邮 0 不包邮", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "express", value = "邮费", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "id", value = "用户id 为空 则新增", paramType = "query", dataType = "String"),
    })
    @RequestMapping(value = "/buyCommodity", method = RequestMethod.POST)
    @ResponseBody
    public ResultResponse buyCommodity(ZipCommodityRequest zipCommodityRequest) {
        logger.info("购买商品------------------------------------》");
        return commodityManager.buyCommodity(zipCommodityRequest);
    }

    /**
     * @ClassName: WeChatTemplateController
     * @Description: 新增微信用户信息
     * @Author: chenDong
     * @Date: 2018/6/4 17:16
     * @Remark:
     */
    @ApiOperation(value = "新增微信用户信息", notes = "新增微信用户信息", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "openId", value = "微信用户id", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "headPortrait", value = "头像", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "nickName", value = "昵称", paramType = "query", dataType = "String"),
    })
    @RequestMapping(value = "/addWeChatUser", method = {RequestMethod.POST})
    @ResponseBody
    public ResultResponse addWeChatUser(@ApiIgnore ZipWechatUserRequest zipWechatUserRequest) {
        return clientManager.addWeChatUser(zipWechatUserRequest);
    }

    /**
     * @ClassName: WeChatTemplateController
     * @Description: 生成自定义模板唯一标识
     * @Author: chenDong
     * @Date: 2018/6/6 16:18
     * @Remark:
     */
    @ApiOperation(value = " 生成自定义模板唯一标识", notes = " 生成自定义模板唯一标识", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/templateNum", method = {RequestMethod.GET})
    @ResponseBody
    public ResultResponse templateNum() {
        Random random = new Random();
        String base = "0123456789";
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 9; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return ResultResponse.success(System.currentTimeMillis() + sb.toString());
    }

    /**
     * @ClassName: WeChatTemplateController
     * @Description: 新增临时材料
     * @Author: chenDong
     * @Date: 2018/6/7 10:41
     * @Remark:
     */
    @ApiOperation(value = "新增自定义临时材料数据", notes = "新增自定义临时材料数据", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "openId", value = "微信用户id", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "templateNum", value = "自定义模板标识", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "textureId", value = "材料id", paramType = "query", dataType = "String"),
    })
    @RequestMapping(value = "/addTextureId", method = {RequestMethod.POST})
    @ResponseBody
    public ResultResponse addTextureId(String openId, String templateNum, Integer textureId) {
        Double num = templateCustomManager.insertTemplateCusstom(openId, null, null, null, null, templateNum, textureId);
        if (num == 0) {
            return ResultResponse.error("500", "新增失败");
        }
        return ResultResponse.success(num);
    }


//    public static  void main(String [] arg){
//        int number = 412/64;
//        System.out.println(number);
//    }

    /**
     * @param com 快递公司代码
     * @param nu  物流单号
     * @return
     * @author
     * @date
     */
    @ApiOperation(value = "查询物流", notes = "查询物流", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "com", value = "快递公司代码", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "nu", value = "订单编号", paramType = "query", dataType = "String"),
    })
    @RequestMapping(value = "/logistics", method = {RequestMethod.GET})
    @ResponseBody()
    public ResultResponse getWuLiu(String com, String nu) {
        if (StringUtils.isBlank(com) || StringUtils.isBlank(nu)) {
            return ResultResponse.error("500", "参数不正确");
        }
        String content = null;
        try {
            /*id:身份授权key，需要申请（此处的key为本人在网上查询）
            com:要查询的快递公司代码，不支持中文，自行查看官方文档
            nu:要查询的快递单号，请勿带特殊符号，不支持中文（大小写不敏感）
            show:返回类型：
                0：返回json字符串，
                1：返回xml对象，
                2：返回html对象，
                3：返回text文本。
                如果不填，默认返回json字符串。
            muti:返回信息数量：
                1:返回多行完整的信息，
                0:只返回一行信息。
                不填默认返回多行。
            order:排序：
                desc：按时间由新到旧排列，
                asc：按时间由旧到新排列。
                不填默认返回倒序（大小写不敏感）*/
            URL url = new URL("http://www.kuaidi100.com/query?type=" + com + "&postid=" + nu + "");
            URLConnection con = url.openConnection();
            con.setAllowUserInteraction(false);
            InputStream urlStream = url.openStream();
            String type = URLConnection.guessContentTypeFromStream(urlStream);
            String charSet = null;
            if (type == null) {
                type = con.getContentType();
            }
            //此处的“text/json”与您在show中选择的要一致！！！
            if (type == null || type.trim().length() == 0 || type.trim().indexOf("text/html") < 0) {
                return ResultResponse.error("500", "参数不正确");
            }
            if (type.indexOf("charset=") > 0) {
                charSet = type.substring(type.indexOf("charset=") + 8);
            }

            byte b[] = new byte[10000];
            int numRead = urlStream.read(b);
            content = new String(b, 0, numRead);
            while (numRead != -1) {
                numRead = urlStream.read(b);
                if (numRead != -1) {
                    String newContent = new String(b, 0, numRead, charSet);
                    content += newContent;
                }
            }
            urlStream.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Expree expree = new Gson().fromJson(content, Expree.class);
        return ResultResponse.success(expree);
    }


}
