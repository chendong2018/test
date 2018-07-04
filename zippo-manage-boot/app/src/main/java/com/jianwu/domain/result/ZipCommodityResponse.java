package com.jianwu.domain.result;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

@ApiModel(description= "返回商品响应数据")
public class ZipCommodityResponse implements Serializable {
    /**
     * 商品表
     */
    private Integer id;

    @ApiModelProperty(value = " 商品编号")
    private String commodityOrder;

    @ApiModelProperty(value = " 产品名称")
    private String commodityName;

    @ApiModelProperty(value = " 价格")
    private Float price;

    @ApiModelProperty(value = "商品主图")
    private String picture;

    @ApiModelProperty(value = "订单小图")
    private String pictures;

    @ApiModelProperty(value = " 型号即材质id")
    private Integer textureId;

    @ApiModelProperty(value = " 材质名称")
    private String textureName;

    @ApiModelProperty(value = " 工艺 1 彩印")
    private Integer technology;

    @ApiModelProperty(value = " 0上架 1下架")
    private Integer status;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    @ApiModelProperty(value = "商品状态(1商家商品，2自定义商品)")
    private Integer commodityStatus;

    @ApiModelProperty(value = "商品详情图 （四张图片）")
    private String picturesDetails;

    @ApiModelProperty(value = "邮费")
    private Double express;

    @ApiModelProperty(value = "是否包邮 1包邮 0不包邮")
    private Integer expressType;

    @ApiModelProperty(value = "购买 人数")
    private String shopNum;

    private Date createTime;

    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public String getTextureName() {
        return textureName;
    }

    public void setTextureName(String textureName) {
        this.textureName = textureName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCommodityOrder() {
        return commodityOrder;
    }

    public void setCommodityOrder(String commodityOrder) {
        this.commodityOrder = commodityOrder == null ? null : commodityOrder.trim();
    }

    public String getCommodityName() {
        return commodityName;
    }

    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName == null ? null : commodityName.trim();
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture == null ? null : picture.trim();
    }

    public String getPictures() {
        return pictures;
    }

    public void setPictures(String pictures) {
        this.pictures = pictures == null ? null : pictures.trim();
    }

    public Integer getTechnology() {
        return technology;
    }

    public void setTechnology(Integer technology) {
        this.technology = technology;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getCommodityStatus() {
        return commodityStatus;
    }

    public void setCommodityStatus(Integer commodityStatus) {
        this.commodityStatus = commodityStatus;
    }

    public String getPicturesDetails() {
        return picturesDetails;
    }

    public void setPicturesDetails(String picturesDetails) {
        this.picturesDetails = picturesDetails == null ? null : picturesDetails.trim();
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

    public Integer getTextureId() {
        return textureId;
    }

    public void setTextureId(Integer textureId) {
        this.textureId = textureId;
    }

    public Double getExpress() {
        return express;
    }

    public void setExpress(Double express) {
        this.express = express;
    }

    public Integer getExpressType() {
        return expressType;
    }

    public void setExpressType(Integer expressType) {
        this.expressType = expressType;
    }

    public String getShopNum() {
        return shopNum;
    }

    public void setShopNum(String shopNum) {
        this.shopNum = shopNum;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", commodityOrder=").append(commodityOrder);
        sb.append(", commodityName=").append(commodityName);
        sb.append(", price=").append(price);
        sb.append(", picture=").append(picture);
        sb.append(", pictures=").append(pictures);
        sb.append(", technology=").append(technology);
        sb.append(", status=").append(status);
        sb.append(", sort=").append(sort);
        sb.append(", commodityStatus=").append(commodityStatus);
        sb.append(", picturesDetails=").append(picturesDetails);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}