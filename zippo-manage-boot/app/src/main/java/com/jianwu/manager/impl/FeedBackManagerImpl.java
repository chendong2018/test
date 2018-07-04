package com.jianwu.manager.impl;

import com.jianwu.dao.ZipFeedbackDao;
import com.jianwu.domain.ZipFeedback;
import com.jianwu.domain.ZipWechatUser;
import com.jianwu.domain.result.ResultResponse;
import com.jianwu.manager.ClientManager;
import com.jianwu.manager.FeedBackManager;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class FeedBackManagerImpl implements FeedBackManager {
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ZipFeedbackDao zipFeedbackDao;

    @Autowired
    private ClientManager clientManager;

    @Override
    @Transactional(readOnly = false)
    public ResultResponse add(String openId, String content) {
        if(StringUtils.isEmpty(content)){
            return ResultResponse.error("500","反馈内容为空");
        }
        if(openId == null){
            return ResultResponse.error("500","参数错误");
        }
        ZipWechatUser zipWechatUser = clientManager.findWeChatUserByOpenId(openId);
        if(zipWechatUser == null){
            return ResultResponse.error("500","用户不存在");
        }
        ZipFeedback zipFeedback = new ZipFeedback();
        zipFeedback.setContent(content);
        zipFeedback.setUserId(zipWechatUser.getId());
        zipFeedbackDao.insertSelective(zipFeedback);
        return ResultResponse.success("反馈成功");
    }
}
