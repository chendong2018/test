package com.jianwu.service;


import com.jianwu.domain.result.Result;

/**
 * Created by tookbra on 2016/3/29.
 */
public interface QiNiuCloudService {

    /**
     * 获取七牛token
     * @return
     */
    Result getToken();

    /**
     * @Author chenDong
     * @Date 2017/5/19 10:42
     *  文件上传
     */
    boolean upload(String filePath, String fileName);

    /**v
     * 获取上传token
     * @return
     */
    String getUpToken();

    boolean download(String targetUrl, String localUrl, String fileName);
}
