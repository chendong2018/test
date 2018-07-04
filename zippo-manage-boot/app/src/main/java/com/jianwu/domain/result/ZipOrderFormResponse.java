package com.jianwu.domain.result;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

public class ZipOrderFormResponse implements Serializable {

    @ApiModelProperty(value = "客户昵称")
    private String nickName;

    @ApiModelProperty(value = "微信id")
    private String openId;

    @ApiModelProperty(value = "商品状态(1商家商品，2自定义商品)")
    private Integer commodityStatus;

    @ApiModelProperty(value = "商品状态(1 商品下架，0 商品下架)")
    private Integer commodityType;

    public Integer getCommodityStatus() {
        return commodityStatus;
    }

    public void setCommodityStatus(Integer commodityStatus) {
        this.commodityStatus = commodityStatus;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }


    public Integer getCommodityType() {
        return commodityType;
    }

    public void setCommodityType(Integer commodityType) {
        this.commodityType = commodityType;
    }

    /**
     * 订单
     */
    private Integer id;

    @ApiModelProperty(value = "订单日期")
    private Date orderDate;

    @ApiModelProperty(value = "商品id")
    private Integer commodityId;

    @ApiModelProperty(value = "客户id")
    private Integer wechatUserId;

    @ApiModelProperty(value = "选择的收获地址")
    private Integer addressId;

    @ApiModelProperty(value = "详细地址")
    private String address;

    @ApiModelProperty(value = "商品主图")
    private String commodityPicture;

    @ApiModelProperty(value = "商品名称")
    private String commodityName;

    @ApiModelProperty(value = "购买数量")
    private Integer number;

    @ApiModelProperty(value = "单价")
    private Double price;

    @ApiModelProperty(value = "总价")
    private Double totalAmount;

    @ApiModelProperty(value = "邮费")
    private Double express;

    @ApiModelProperty(value = "订单编号")
    private String orderNumber;

    @ApiModelProperty(value = "快递单号")
    private String trackingNumber;

    @ApiModelProperty(value = "快递代码类型")
    private String trackingType;

    @ApiModelProperty(value = "是否包邮 1 包邮 0 不包邮")
    private Integer expressType;

    @ApiModelProperty(value = "订单状态（1待付款，2待发货，3待收货，4已收货）邮")
    private Integer status;

    @ApiModelProperty(value = "付款时间")
    private Date payTime;

    @ApiModelProperty(value = "发货时间")
    private Date sentTime;

    @ApiModelProperty(value = "优标 0是 1否")
    private Integer optimalScale;

    @ApiModelProperty(value = "材质id")
    private Integer textureId;

    @ApiModelProperty(value = "材质名称")
    private String textureName;

    @ApiModelProperty(value = "工艺 1彩印")
    private Integer technology;

    private Date createTime;

    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public String getTrackingType() {
        return trackingType;
    }

    public void setTrackingType(String trackingType) {
        this.trackingType = trackingType;
    }

    public String getCommodityName() {
        return commodityName;
    }

    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName;
    }

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    public Date getSentTime() {
        return sentTime;
    }

    public void setSentTime(Date sentTime) {
        this.sentTime = sentTime;
    }

    public String getTextureName() {
        return textureName;
    }

    public void setTextureName(String textureName) {
        this.textureName = textureName;
    }

    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCommodityPicture() {
        return commodityPicture;
    }

    public void setCommodityPicture(String commodityPicture) {
        this.commodityPicture = commodityPicture;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(Integer commodityId) {
        this.commodityId = commodityId;
    }

    public Integer getWechatUserId() {
        return wechatUserId;
    }

    public void setWechatUserId(Integer wechatUserId) {
        this.wechatUserId = wechatUserId;
    }

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Double getExpress() {
        return express;
    }

    public void setExpress(Double express) {
        this.express = express;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber == null ? null : orderNumber.trim();
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber == null ? null : trackingNumber.trim();
    }

    public Integer getExpressType() {
        return expressType;
    }

    public void setExpressType(Integer expressType) {
        this.expressType = expressType;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
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

    public Integer getOptimalScale() {
        return optimalScale;
    }

    public void setOptimalScale(Integer optimalScale) {
        this.optimalScale = optimalScale;
    }

    public Integer getTextureId() {
        return textureId;
    }

    public void setTextureId(Integer textureId) {
        this.textureId = textureId;
    }

    public Integer getTechnology() {
        return technology;
    }

    public void setTechnology(Integer technology) {
        this.technology = technology;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", commodityId=").append(commodityId);
        sb.append(", wechatUserId=").append(wechatUserId);
        sb.append(", addressId=").append(addressId);
        sb.append(", number=").append(number);
        sb.append(", price=").append(price);
        sb.append(", totalAmount=").append(totalAmount);
        sb.append(", express=").append(express);
        sb.append(", orderNumber=").append(orderNumber);
        sb.append(", trackingNumber=").append(trackingNumber);
        sb.append(", expressType=").append(expressType);
        sb.append(", status=").append(status);
        sb.append(", payTime=").append(payTime);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}