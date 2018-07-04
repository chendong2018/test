package com.jianwu.utils;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Strings;
import com.jianwu.domain.request.SdkHttpResult;

import javax.net.ssl.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 * Created by tookbra on 2016/3/29.
 */
public class HttpUtil {
    private static final String APPKEY = "RC-App-Key";
    private static final String NONCE = "RC-Nonce";
    private static final String TIMESTAMP = "RC-Timestamp";
    private static final String SIGNATURE = "RC-Signature";

    private static SSLContext sslCtx = null;

    static {

        try {
            sslCtx = SSLContext.getInstance("TLS");
            X509TrustManager tm = new X509TrustManager() {
                public void checkClientTrusted(X509Certificate[] xcs,
                                               String string) throws CertificateException {
                }

                public void checkServerTrusted(X509Certificate[] xcs,
                                               String string) throws CertificateException {
                }

                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
            };
            sslCtx.init(null, new TrustManager[]{tm}, null);
        } catch (Exception e) {
            e.printStackTrace();
        }

        HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {

            @Override
            public boolean verify(String arg0, SSLSession arg1) {
                // TODO Auto-generated method stub
                return true;
            }

        });

        HttpsURLConnection
                .setDefaultSSLSocketFactory(sslCtx.getSocketFactory());

    }

    // 设置body体
    public static void setBodyParameter(StringBuilder sb, HttpURLConnection conn)
            throws IOException {
        DataOutputStream out = new DataOutputStream(conn.getOutputStream());
        out.writeBytes(sb.toString());
        // fromUserId=j10000&toUserId=13385817510&objectName=JW%3AMeeting&content=%7B%22content%22%3A%22wfrwef%22%2C%22extra%22%3A%22%5C%22%7D%22%2C%22url%22%3A%22%22%7D
        //fromUserId=10000&toUserId=13385817510&objectName=RC%3ATxtMsg&content=%7B%22content%22%3A%22%22%2C%22extra%22%3A%22%5C%22%7D%22%7D
        out.flush();
        out.close();
    }

    // 添加签名header
    public static HttpURLConnection CreatePostHttpConnection(String appKey,
                                                             String appSecret, String uri) throws MalformedURLException,
            IOException, ProtocolException {
        return CreatePostHttpConnection(appKey, appSecret, uri,
                "application/x-www-form-urlencoded");
    }

    public static HttpURLConnection CreateJsonPostHttpConnection(String appKey,
                                                                 String appSecret, String uri) throws MalformedURLException,
            IOException, ProtocolException {
        return CreatePostHttpConnection(appKey, appSecret, uri,
                "application/json");
    }

    public static void setBodyParameter(String str, HttpURLConnection conn)
            throws IOException {
        DataOutputStream out = new DataOutputStream(conn.getOutputStream());
        out.write(str.getBytes("utf-8"));
        out.flush();
        out.close();
    }

    private static HttpURLConnection CreatePostHttpConnection(String appKey,
                                                              String appSecret, String uri, String contentType)
            throws MalformedURLException, IOException, ProtocolException {
        String nonce = String.valueOf(Math.random() * 1000000);
        String timestamp = String.valueOf(System.currentTimeMillis() / 1000);
        StringBuilder toSign = new StringBuilder(appSecret).append(nonce)
                .append(timestamp);
        String sign = CodeUtil.hexSHA1(toSign.toString());

        URL url = new URL(uri);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setUseCaches(false);
        conn.setDoInput(true);
        conn.setDoOutput(true);
        conn.setRequestMethod("POST");
        conn.setInstanceFollowRedirects(true);
        conn.setConnectTimeout(3000);
        conn.setReadTimeout(3000);

        conn.setRequestProperty(APPKEY, appKey);
        conn.setRequestProperty(NONCE, nonce);
        conn.setRequestProperty(TIMESTAMP, timestamp);
        conn.setRequestProperty(SIGNATURE, sign);
        conn.setRequestProperty("Content-Type", contentType);

        return conn;
    }

    public static byte[] readInputStream(InputStream inStream) throws Exception {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = inStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, len);
        }
        byte[] data = outStream.toByteArray();
        outStream.close();
        inStream.close();
        return data;
    }

    public static SdkHttpResult returnResult(HttpURLConnection conn)
            throws Exception, IOException {
        String result;
        InputStream input = null;
        if (conn.getResponseCode() == 200) {
            input = conn.getInputStream();
        } else {
            input = conn.getErrorStream();
        }
        result = new String(readInputStream(input), "UTF-8");
        return new SdkHttpResult(conn.getResponseCode(), result);
    }

    public static InputStream getRemoteStream(String uri) {
        if (Strings.isNullOrEmpty(uri)) {
            return null;
        }

        try {
            URL url = new URL("http://".concat(uri));
            HttpURLConnection httpUrl = null;
            httpUrl = (HttpURLConnection) url.openConnection();
            httpUrl.connect();
            InputStream inStream = httpUrl.getInputStream();
            return inStream;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getMobileCity(String urlStr) {
        StringBuffer sb = new StringBuffer();
        BufferedReader buffer;
        String jsonString = null;
        JSONArray array = null;
        JSONObject jsonObject = null;
        try {
            URL url = new URL(urlStr);
            InputStream in = url.openStream();
            // 解决乱码问题
            buffer = new BufferedReader(new InputStreamReader(in, "gb2312"));
            String line = null;
            while ((line = buffer.readLine()) != null) {
                sb.append(line);
            }
            in.close();
            buffer.close();
            // System.out.println(sb.toString());
            jsonString = sb.toString();
            jsonString = jsonString.replaceAll("^[__]\\w{14}+[_ = ]+", "[");

            String jsonString2 = jsonString + "]";
            // 把STRING转化为json对象
            array = JSONArray.parseArray(jsonString2);
            // 获取JSONArray的JSONObject对象，便于读取array里的键值对
            jsonObject = array.getJSONObject(0);
            String city = String.valueOf(jsonObject.get("province"));
            return city;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static void main(String[] args) {
        String x = "[{\"code\":500}]";
        JSONArray array = null;
        JSONObject jsonObject = null;
        array = JSONArray.parseArray(x);
        // 获取JSONArray的JSONObject对象，便于读取array里的键值对
        jsonObject = array.getJSONObject(0);
        String city = String.valueOf(jsonObject.get("code"));
        System.out.print(city);
    }
}
