package com.jianwu.domain.request;

import java.io.Serializable;
import java.util.Date;

public class ZipCustomerServiceUserRequest implements Serializable {
    /**
     * 客服用户
     */
    private Integer id;

    /**
     * 客服名字
     */
    private String customerServiceName;

    /**
     * 微信号
     */
    private String wechatNumber;

    /**
     * 二维码
     */
    private String quickMark;

    /**
     * 0启用 1停用
     */
    private Integer status;

    private Date createTime;

    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCustomerServiceName() {
        return customerServiceName;
    }

    public void setCustomerServiceName(String customerServiceName) {
        this.customerServiceName = customerServiceName == null ? null : customerServiceName.trim();
    }

    public String getWechatNumber() {
        return wechatNumber;
    }

    public void setWechatNumber(String wechatNumber) {
        this.wechatNumber = wechatNumber == null ? null : wechatNumber.trim();
    }

    public String getQuickMark() {
        return quickMark;
    }

    public void setQuickMark(String quickMark) {
        this.quickMark = quickMark == null ? null : quickMark.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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
        sb.append(", customerServiceName=").append(customerServiceName);
        sb.append(", wechatNumber=").append(wechatNumber);
        sb.append(", quickMark=").append(quickMark);
        sb.append(", status=").append(status);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}