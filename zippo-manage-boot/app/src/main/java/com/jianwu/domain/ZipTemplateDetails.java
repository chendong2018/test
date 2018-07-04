package com.jianwu.domain;

import java.io.Serializable;
import java.util.Date;

public class ZipTemplateDetails implements Serializable {
    /**
     * 模板详情
     */
    private Integer id;

    /**
     * 模板id
     */
    private Integer templateId;

    /**
     * 位置
     */
    private String place;

    /**
     * 0图片 1文字
     */
    private Integer type;

    /**
     * 像素
     */
    private Integer height;

    /**
     * 像素
     */
    private Integer width;

    /**
     * 颜色插件
     */
    private String colorPlugIn;

    /**
     * 字体插件
     */
    private String fontPlugIn;

    /**
     * 字号插件
     */
    private String sizePlugIn;

    /**
     * 文字长度 字数限制
     */
    private Integer length;

    private Date createTime;

    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Integer templateId) {
        this.templateId = templateId;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place == null ? null : place.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public String getColorPlugIn() {
        return colorPlugIn;
    }

    public void setColorPlugIn(String colorPlugIn) {
        this.colorPlugIn = colorPlugIn == null ? null : colorPlugIn.trim();
    }

    public String getFontPlugIn() {
        return fontPlugIn;
    }

    public void setFontPlugIn(String fontPlugIn) {
        this.fontPlugIn = fontPlugIn == null ? null : fontPlugIn.trim();
    }

    public String getSizePlugIn() {
        return sizePlugIn;
    }

    public void setSizePlugIn(String sizePlugIn) {
        this.sizePlugIn = sizePlugIn == null ? null : sizePlugIn.trim();
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
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
        sb.append(", templateId=").append(templateId);
        sb.append(", place=").append(place);
        sb.append(", type=").append(type);
        sb.append(", height=").append(height);
        sb.append(", width=").append(width);
        sb.append(", colorPlugIn=").append(colorPlugIn);
        sb.append(", fontPlugIn=").append(fontPlugIn);
        sb.append(", sizePlugIn=").append(sizePlugIn);
        sb.append(", length=").append(length);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}