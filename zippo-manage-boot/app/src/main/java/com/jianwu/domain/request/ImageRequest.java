package com.jianwu.domain.request;

import java.io.Serializable;

/**
 * 图片合成对象
 *
 * @Author chendong
 * @Create 2018/6/5
 * @Time 14:38
 **/
public class ImageRequest implements Serializable {
    /**
     * 模板详细id
     */
    private Integer templateDetailId;

    /**
     * 文字内容
     */
    private String content;

    private String templateNum;

    private String openId;

    public Integer getTemplateDetailId() {
        return templateDetailId;
    }

    public void setTemplateDetailId(Integer templateDetailId) {
        this.templateDetailId = templateDetailId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTemplateNum() {
        return templateNum;
    }

    public void setTemplateNum(String templateNum) {
        this.templateNum = templateNum;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    @Override
    public String toString() {
        return "ImageRequest{" +
                "templateDetailId=" + templateDetailId +
                ", content='" + content + '\'' +
                ", templateNum='" + templateNum + '\'' +
                ", openId='" + openId + '\'' +
                '}';
    }
}
