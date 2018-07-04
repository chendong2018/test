package com.jianwu.domain.request;

/**
 * @Author:lijin
 * @Date:11:05 2018/6/15
 * @Remark:
 */
public class WxUser {


    /**
     * nickName : Sunny羽艳
     * gender : 2
     * language : zh_CN
     * city : 宁波
     * province : 浙江
     * country : 中国
     * avatarUrl : https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTIo7DHrEnbgaXNXFMibU2NF1btc0s8wGmWZB6sc4u8XdzVVzOOsXtNARibToIgmXenE69wf2Kh0YAsg/132
     */

    private String nickName;
    private int gender;
    private String language;
    private String city;
    private String province;
    private String country;
    private String avatarUrl;

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }
}
