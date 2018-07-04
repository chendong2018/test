package com.jianwu.controller;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.bean.WxMaPhoneNumberInfo;
import cn.binarywang.wx.miniapp.bean.WxMaUserInfo;

import com.google.gson.Gson;
import com.jianwu.domain.ZipWechatUser;
import com.jianwu.domain.request.WxUser;
import com.jianwu.domain.request.ZipWechatUserRequest;
import com.jianwu.manager.ClientManager;
import com.jianwu.utils.JsonUtils;
import com.jianwu.utils.StringUtil;
import me.chanjar.weixin.common.exception.WxErrorException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 微信小程序用户接口
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
@RestController
@RequestMapping("/zip/wechat/user")
public class WxMaUserController  extends BaseController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private WxMaService wxService;

    @Autowired
    ClientManager clientManager;

    /**
     * 登陆接口
     */
    @GetMapping("/login")
    public String login(String jsCode,  String userInfo) {
        logger.info("login-->{},{}",jsCode,userInfo);
        if (StringUtils.isBlank(jsCode)) {
            return "empty jscode";
        }
        try {
            WxMaJscode2SessionResult session = this.wxService.getUserService().getSessionInfo(jsCode);
            this.logger.info(session.getSessionKey());
            this.logger.info(session.getOpenid());
            //TODO 可以增加自己的逻辑，关联业务相关数据 保存用户信息
            ZipWechatUserRequest zipWechatUser=new ZipWechatUserRequest();
            zipWechatUser.setOpenId(session.getOpenid());
            if(userInfo != null){
                WxUser wxUser= new Gson().fromJson(userInfo,WxUser.class);
                if(wxUser != null){
                    zipWechatUser.setSex(wxUser.getGender());
//            zipWechatUser.setNickName(wxUser.getNickName());
                    zipWechatUser.setHeadPortrait(wxUser.getAvatarUrl());
                }
            }
            clientManager.addWeChatUser(zipWechatUser);
            return JsonUtils.toJson(session);
        } catch (WxErrorException e) {
            this.logger.error(e.getMessage(), e);
            return e.toString();
        }
    }

    /**
     * <pre>
     * 获取用户信息接口
     * </pre>
     */
    @GetMapping("/info")
    public String info(String sessionKey, String signature, String rawData, String encryptedData, String iv) {
        // 用户信息校验
        if (!this.wxService.getUserService().checkUserInfo(sessionKey, rawData, signature)) {
            return "user check failed";
        }

        // 解密用户信息
        WxMaUserInfo userInfo = this.wxService.getUserService().getUserInfo(sessionKey, encryptedData, iv);

        return JsonUtils.toJson(userInfo);
    }

    /**
     * <pre>
     * 获取用户绑定手机号信息
     * </pre>
     */
    @GetMapping("/phone")
    public String phone(String sessionKey, String signature, String rawData, String encryptedData, String iv) {
        // 用户信息校验
        if (!this.wxService.getUserService().checkUserInfo(sessionKey, rawData, signature)) {
            return "user check failed";
        }

        // 解密
        WxMaPhoneNumberInfo phoneNoInfo = this.wxService.getUserService().getPhoneNoInfo(sessionKey, encryptedData, iv);

        return JsonUtils.toJson(phoneNoInfo);
    }

}
