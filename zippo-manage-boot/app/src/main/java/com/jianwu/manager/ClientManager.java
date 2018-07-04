package com.jianwu.manager;


import com.jianwu.domain.ZipWechatUser;
import com.jianwu.domain.request.ZipWechatUserRequest;
import com.jianwu.domain.result.Page;
import com.jianwu.domain.result.ResultResponse;

/**
 * 用户业务逻辑接口
 *
 * @Author chendong
 * @Create 2018/3/5
 * @Time 14:14
 **/
public interface ClientManager {

    /*
    分页模糊查询客户信息
     */
    ResultResponse list(Page page, String nickName);

    /**
     * @ClassName: ClientManager
     * @Description: 获取微信用户信息更具id
     * @Author: chenDong
     * @Date: 2018/6/2 14:27
     * @Remark:
     */
    ZipWechatUser findWeChatUserById(Integer id);

    /**
     * @ClassName: ClientManager
     * @Description:
     * @Author: chenDong
     * @Date: 2018/6/4 15:58
     * @Remark:
     */
    ZipWechatUser findWeChatUserByOpenId(String openId);

    /**
     * @ClassName: ClientManager
     * @Description:  新增用户信息
     * @Author: chenDong
     * @Date: 2018/6/4 17:11
     * @Remark:
     */
    ResultResponse addWeChatUser(ZipWechatUserRequest zipWechatUserRequest);
}
