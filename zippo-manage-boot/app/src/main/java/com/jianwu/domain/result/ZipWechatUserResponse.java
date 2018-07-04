package com.jianwu.domain.result;

import java.io.Serializable;
import java.util.Date;

public class ZipWechatUserResponse implements Serializable {
    /**
     * 微信用户表
     */
    private Integer id;

    /**
     * 头像
     */
    private String headPortrait;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 账号
     */
    private String accountNumber;

    /**
     * 性别（1:男，2:女）
     */
    private Integer sex;

    /**
     * 地区
     */
    private String region;

    /**
     * 签名
     */
    private String message;

    private Date createTime;

    private Date updateTime;

    /**
     * 微信id
     */
    private String openId;

    private static final long serialVersionUID = 1L;

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHeadPortrait() {
        return headPortrait;
    }

    public void setHeadPortrait(String headPortrait) {
        this.headPortrait = headPortrait == null ? null : headPortrait.trim();
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName == null ? null : nickName.trim();
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber == null ? null : accountNumber.trim();
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region == null ? null : region.trim();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message == null ? null : message.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", headPortrait=").append(headPortrait);
        sb.append(", nickName=").append(nickName);
        sb.append(", accountNumber=").append(accountNumber);
        sb.append(", sex=").append(sex);
        sb.append(", region=").append(region);
        sb.append(", message=").append(message);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}