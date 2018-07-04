package com.jianwu.domain;

import java.io.Serializable;
import java.util.Date;

public class ZipTemplateCustom implements Serializable {
    /**
     * id
     */
    private Integer id;

    /**
     * 微信id
     */
    private String openId;

    private Integer templateId;

    /**
     * 材料id
     */
    private Integer templateDetailsId;

    /**
     * 上传图片保存地址
     */
    private String imgUrl;

    /**
     * 合成的文字内容
     */
    private String content;

    private Date createTime;

    private Integer textureId;

    /**
     * 自定义模板唯一标识
     */
    private String templateNum;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId == null ? null : openId.trim();
    }

    public Integer getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Integer templateId) {
        this.templateId = templateId;
    }

    public Integer getTemplateDetailsId() {
        return templateDetailsId;
    }

    public void setTemplateDetailsId(Integer templateDetailsId) {
        this.templateDetailsId = templateDetailsId;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl == null ? null : imgUrl.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getTextureId() {
        return textureId;
    }

    public void setTextureId(Integer textureId) {
        this.textureId = textureId;
    }

    public String getTemplateNum() {
        return templateNum;
    }

    public void setTemplateNum(String templateNum) {
        this.templateNum = templateNum == null ? null : templateNum.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", openId=").append(openId);
        sb.append(", templateId=").append(templateId);
        sb.append(", templateDetailsId=").append(templateDetailsId);
        sb.append(", imgUrl=").append(imgUrl);
        sb.append(", content=").append(content);
        sb.append(", createTime=").append(createTime);
        sb.append(", textureId=").append(textureId);
        sb.append(", templateNum=").append(templateNum);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}