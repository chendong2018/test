package com.jianwu.manager;

import com.jianwu.domain.result.ResultResponse;

public interface FeedBackManager {

    /**
     * @ClassName: FeedBackManager
     * @Description: 新增反馈信息
     * @Author: chenDong
     * @Date: 2018/6/2 16:32
     * @Remark:
     */
    ResultResponse add(String userId, String content);
}
