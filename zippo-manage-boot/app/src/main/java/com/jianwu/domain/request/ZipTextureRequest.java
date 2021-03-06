package com.jianwu.domain.request;

import java.io.Serializable;
import java.util.Date;

public class ZipTextureRequest implements Serializable {
    /**
     * 材质表
     */
    private Integer id;

    /**
     * 材质编号
     */
    private String textureOrder;

    /**
     * 材质名称
     */
    private String textureName;

    /*
    背面透明图片
     */
    private String backLucencyPicture;

    /*
    正面透明图片
     */
    private String lucencyPicture;

    /**
     * 正面图片
     */
    private String frontPicture;

    /**
     * 背面图片
     */
    private String backPicture;

    /**
     * 侧面
     */
    private String sidePicture;

    /**
     * 非侧面
     */
    private String noSidePicture;

    /**
     * 顶图
     */
    private String topPicture;

    /**
     * 小图
     */
    private String smallPicture;

    /**
     * 0上架 1下架
     */
    private Integer status;

    /**
     * 价格
     */
    private Double price;

    /**
     * 排序
     */
    private Integer sort;

    private Date createTime;

    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public String getBackLucencyPicture() {
        return backLucencyPicture;
    }

    public void setBackLucencyPicture(String backLucencyPicture) {
        this.backLucencyPicture = backLucencyPicture;
    }

    public String getLucencyPicture() {
        return lucencyPicture;
    }

    public void setLucencyPicture(String lucencyPicture) {
        this.lucencyPicture = lucencyPicture;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getFrontPicture() {
        return frontPicture;
    }

    public void setFrontPicture(String frontPicture) {
        this.frontPicture = frontPicture;
    }

    public String getBackPicture() {
        return backPicture;
    }

    public void setBackPicture(String backPicture) {
        this.backPicture = backPicture;
    }

    public String getSidePicture() {
        return sidePicture;
    }

    public void setSidePicture(String sidePicture) {
        this.sidePicture = sidePicture;
    }

    public String getNoSidePicture() {
        return noSidePicture;
    }

    public void setNoSidePicture(String noSidePicture) {
        this.noSidePicture = noSidePicture;
    }

    public String getTopPicture() {
        return topPicture;
    }

    public void setTopPicture(String topPicture) {
        this.topPicture = topPicture;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTextureOrder() {
        return textureOrder;
    }

    public void setTextureOrder(String textureOrder) {
        this.textureOrder = textureOrder == null ? null : textureOrder.trim();
    }

    public String getTextureName() {
        return textureName;
    }

    public void setTextureName(String textureName) {
        this.textureName = textureName == null ? null : textureName.trim();
    }

    public String getSmallPicture() {
        return smallPicture;
    }

    public void setSmallPicture(String smallPicture) {
        this.smallPicture = smallPicture == null ? null : smallPicture.trim();
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
        sb.append(", textureOrder=").append(textureOrder);
        sb.append(", textureName=").append(textureName);
        sb.append(", smallPicture=").append(smallPicture);
        sb.append(", status=").append(status);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}