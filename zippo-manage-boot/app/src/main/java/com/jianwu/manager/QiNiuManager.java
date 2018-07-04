package com.jianwu.manager;



import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;


import java.util.Map;

/**
 * Created by tookbra on 2016/3/23.
 */
public interface QiNiuManager {

    /**
     * 获取七牛token
     * @return
     */
    Map<String,String> getToken();

    /**
     * 获取上传token
     * @param bucketName 空间名称
     * @param scope 是否覆盖上传
     * @param key 文件名
     * @return
     */
    String getUpToken(String bucketName, boolean scope, String key);

    /**
     * 获取上传token
     * @return
     */
    String getUpToken();

    /**
     * 文件上传
     * @return
     */
    boolean upload(String filePath, String fileName);

    /**
     *
     * @param data
     * @param fileName 文件名
     * @return
     */
    /**
     * 文件上传
     * @param data 字节
     * @param fileName 文件名
     * @param params 参数
     * @param mime 文件类型
     * @param checkCrc
     * @return
     */
    boolean upload(byte[] data, String fileName, StringMap params, String mime, boolean checkCrc);

    String getFileUri(String fileName);

    Auth getAuth();

    boolean download(String targetUrl, String localUrl, String fileName);
}
