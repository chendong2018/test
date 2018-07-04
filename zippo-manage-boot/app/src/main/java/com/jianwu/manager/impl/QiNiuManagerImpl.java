package com.jianwu.manager.impl;


import com.jianwu.manager.QiNiuManager;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import static com.jianwu.utils.HttpUtil.readInputStream;


/**
 * 七牛业务类
 * Created by tookbra on 2016/3/23.
 */
@Service
public class QiNiuManagerImpl implements QiNiuManager {

    /**
     *
     */
    @Value("${qiniu.access.key}")
    private String accessKey;
    /**
     * 服务ip
     */
    @Value("${qiniu.secret.key}")
    private String secretKey;
    /**
     * 服务端口
     */
    @Value("${qiniu.bucket.name}")
    private String bucketName;
    /**
     *
     */
    @Value("${qiniu.token.url}")
    private String url;

    private static Zone z = Zone.zone0();
    private static Configuration c = new Configuration(z);


    @Override
    public Map<String, String> getToken() {
        Map<String,String> map = new HashMap<>();
        map.put("token", getUpToken(bucketName, false, ""));
        map.put("domain", String.format("https://%s/",url));
        return map;
    }

    @Override
    public String getUpToken(String bucketName, boolean scope, String key) {
        Auth auth = Auth.create(accessKey, secretKey);
        return scope ? auth.uploadToken(bucketName,key) : auth.uploadToken(bucketName);
    }

    public String getUpToken(boolean scope, String key) {
        return getUpToken(bucketName, scope, key);
    }

    @Override
    public String getUpToken() {
        return getUpToken(bucketName, false, "");
    }

    @Override
    public boolean upload(String filePath, String fileName) {
        UploadManager uploadManager = new UploadManager(c);
        try {
            Response res = uploadManager.put(filePath, fileName, getUpToken());
            System.out.println(res.bodyString());
        } catch (QiniuException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean upload(byte [] date, String fileName, StringMap params, String mime, boolean checkCrc) {
        UploadManager uploadManager = new UploadManager(c);
        try {
            Response res = uploadManager.put(date, fileName, getUpToken(), params,mime, checkCrc);
            System.out.println(res.bodyString());
        } catch (QiniuException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }



    @Override
    public String getFileUri(String fileName) {
        return url.concat("/").concat(fileName);
    }

    @Override
    public Auth getAuth() {
        return Auth.create(accessKey, secretKey);
    }

    @Override
    public boolean download(String targetUrl,String localUrl,String fileName) {
        Auth auth = this.getAuth();
        String downloadUrl = auth.privateDownloadUrl(targetUrl);

        OkHttpClient client = new OkHttpClient();
        System.out.println(url);
        Request req = new Request.Builder().url(downloadUrl).build();
        okhttp3.Response resp = null;
        try {
            resp = client.newCall(req).execute();
            System.out.println(resp.isSuccessful());
            if(resp.isSuccessful()) {
                ResponseBody body = resp.body();
                InputStream is = body.byteStream();
                byte[] data = readInputStream(is);
                File file = new File(localUrl,fileName);
                FileOutputStream fops = new FileOutputStream(file);
                fops.write(data);
                fops.close();
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Unexpected code " + resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
