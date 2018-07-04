package com.jianwu.service.impl;


import com.jianwu.domain.result.Result;
import com.jianwu.manager.QiNiuManager;
import com.jianwu.service.QiNiuCloudService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by tookbra on 2016/3/29.
 */
@Service
public class QiNiuServiceImpl implements QiNiuCloudService {

    private static final Log log = LogFactory.getLog(QiNiuServiceImpl.class);

    @Autowired
    QiNiuManager qiNiuManager;

    @Override
    public Result getToken() {
        try {
            Map<String, String> map = qiNiuManager.getToken();
            return Result.success(map);
        } catch (Exception e) {
            log.warn("生成7牛token失败:" + e.getMessage());
            return Result.error("生成7牛token失败");
        }
    }

    @Override
    public boolean upload(String filePath, String fileName){
        return qiNiuManager.upload(filePath,fileName);
    }

    @Override
    public String getUpToken(){
        return qiNiuManager.getUpToken();
    }

    @Override
    public boolean download(String targetUrl, String localUrl, String fileName) {
        return qiNiuManager.download(targetUrl,localUrl,fileName);
    }

}
