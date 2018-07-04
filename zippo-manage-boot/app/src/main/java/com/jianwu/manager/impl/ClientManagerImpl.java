package com.jianwu.manager.impl;


import com.jianwu.dao.ZipWechatUserDao;
import com.jianwu.domain.ZipWechatUser;

import com.jianwu.domain.request.ZipWechatUserRequest;
import com.jianwu.domain.result.Page;
import com.jianwu.domain.result.PageResult;
import com.jianwu.domain.result.ResultResponse;
import com.jianwu.domain.result.ZipWechatUserResponse;
import com.jianwu.example.ZipWechatUserExample;
import com.jianwu.manager.ClientManager;
import com.jianwu.utils.TransferUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class ClientManagerImpl implements ClientManager {
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    ZipWechatUserDao zipWechatUserDao;

    @Override
    public ResultResponse list(Page page, String nickName) {
        PageResult pageResult = new PageResult();
        Integer start = page.getStart();
        Integer end = page.getPageSize();
        List<ZipWechatUserResponse> list = new ArrayList<>();
        int total = zipWechatUserDao.queryLikeCount(nickName);
        if(total>0){
            list = zipWechatUserDao.queryLike(start,end,nickName);
        }
        pageResult.setSuccess(true);
        pageResult.setTotal(total);
        pageResult.setData(list);
        return ResultResponse.success(pageResult);
    }

    @Override
    public ZipWechatUser findWeChatUserById(Integer id) {
        ZipWechatUser zipWechatUser = new ZipWechatUser();
        if(null==id){
            return zipWechatUser;
        }
        ZipWechatUserExample example = new ZipWechatUserExample();
        example.or().andIdEqualTo(id);
        List<ZipWechatUser> list = zipWechatUserDao.selectByExample(example);
        if(CollectionUtils.isNotEmpty(list)){
            zipWechatUser = list.get(0);
        }
        return zipWechatUser;
    }

    @Override
    public ZipWechatUser findWeChatUserByOpenId(String openId) {
        ZipWechatUser zipWechatUser = null;
        if(StringUtils.isBlank(openId)){
            return zipWechatUser;
        }
        ZipWechatUserExample example = new ZipWechatUserExample();
        example.or().andOpenIdEqualTo(openId);
        List<ZipWechatUser> list = zipWechatUserDao.selectByExample(example);
        if(CollectionUtils.isNotEmpty(list)){
            zipWechatUser = list.get(0);
        }
        return zipWechatUser;
    }

    @Override
    @Transactional(readOnly = false)
    public ResultResponse addWeChatUser(ZipWechatUserRequest zipWechatUserRequest) {
        if(zipWechatUserRequest == null || StringUtils.isEmpty(zipWechatUserRequest.getOpenId())){
            return ResultResponse.error("500","参数错误");
        }
        ZipWechatUser zipWechatUser = this.findWeChatUserByOpenId(zipWechatUserRequest.getOpenId());
        if(zipWechatUser != null){
            return ResultResponse.success(zipWechatUser);
        }
        //新增微信用户
        ZipWechatUser wechatUser = new ZipWechatUser();
        TransferUtil.transfer(zipWechatUserRequest,wechatUser);
        zipWechatUserDao.insertSelective(wechatUser);
        return ResultResponse.success(wechatUser);
    }
}
