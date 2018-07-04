package com.jianwu.domain;

import java.io.Serializable;
import java.util.Date;

public class ZipTemplate implements Serializable {
    /**
     * 模板
     */
    private Integer id;

    /**
     * 模块 1 正面 2 背面
     */
    private Integer module;

    /**
     * 模板编号
     */
    private String templateOrder;

    /**
     * 模板名称
     */
    private String templateName;

    /**
     * 图片 模板缩略图
     */
    private String picture;

    /**
     * 0上架 1下架
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

    public Integer getModule() {
        return module;
    }

    public void setModule(Integer module) {
        this.module = module;
    }

    public String getTemplateOrder() {
        return templateOrder;
    }

    public void setTemplateOrder(String templateOrder) {
        this.templateOrder = templateOrder == null ? null : templateOrder.trim();
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName == null ? null : templateName.trim();
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture == null ? null : picture.trim();
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
        sb.append(", module=").append(module);
        sb.append(", templateOrder=").append(templateOrder);
        sb.append(", templateName=").append(templateName);
        sb.append(", picture=").append(picture);
        sb.append(", status=").append(status);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}