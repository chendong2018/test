package com.jianwu.domain;

import java.io.Serializable;
import java.util.Date;

public class ZipOrderForm implements Serializable {
    /**
     * 订单
     */
    private Integer id;

    /**
     * 订单日期
     */
    private Date orderDate;

    /**
     * 商品id
     */
    private Integer commodityId;

    /**
     * 商品名称
     */
    private String commodityName;

    /**
     * 商品主图片
     */
    private String commodityPicture;

    /**
     * 客户id
     */
    private Integer wechatUserId;

    /**
     * 选择的收获地址
     */
    private Integer addressId;

    /**
     * 地址详情
     */
    private String address;

    /**
     * 购买数量
     */
    private Integer number;

    /**
     * 单价
     */
    private Double price;

    /**
     * 总价
     */
    private Double totalAmount;

    /**
     * 邮费
     */
    private Double express;

    /**
     * 订单编号
     */
    private String orderNumber;

    /**
     * 快递代码类型
     */
    private String trackingType;

    /**
     * 快递单号
     */
    private String trackingNumber;

    /**
     * 是否包邮 1 包邮 0 不包邮
     */
    private Integer expressType;

    /**
     * 订单状态（1待付款，2待发货，3待收货，4已收货）
     */
    private Integer status;

    /**
     * 发货时间
     */
    private Date sentTime;

    /**
     * 付款时间
     */
    private Date payTime;

    /**
     * 材质id
     */
    private Integer textureId;

    /**
     * 工艺 1彩印
     */
    private Integer technology;

    /**
     * 优标 0是 1否
     */
    private Integer optimalScale;

    private Date createTime;

    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Integer getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(Integer commodityId) {
        this.commodityId = commodityId;
    }

    public String getCommodityName() {
        return commodityName;
    }

    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName == null ? null : commodityName.trim();
    }

    public String getCommodityPicture() {
        return commodityPicture;
    }

    public void setCommodityPicture(String commodityPicture) {
        this.commodityPicture = commodityPicture == null ? null : commodityPicture.trim();
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
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

    public String getTrackingType() {
        return trackingType;
    }

    public void setTrackingType(String trackingType) {
        this.trackingType = trackingType == null ? null : trackingType.trim();
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

    public Date getSentTime() {
        return sentTime;
    }

    public void setSentTime(Date sentTime) {
        this.sentTime = sentTime;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
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

    public Integer getOptimalScale() {
        return optimalScale;
    }

    public void setOptimalScale(Integer optimalScale) {
        this.optimalScale = optimalScale;
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
        sb.append(", orderDate=").append(orderDate);
        sb.append(", commodityId=").append(commodityId);
        sb.append(", commodityName=").append(commodityName);
        sb.append(", commodityPicture=").append(commodityPicture);
        sb.append(", wechatUserId=").append(wechatUserId);
        sb.append(", addressId=").append(addressId);
        sb.append(", address=").append(address);
        sb.append(", number=").append(number);
        sb.append(", price=").append(price);
        sb.append(", totalAmount=").append(totalAmount);
        sb.append(", express=").append(express);
        sb.append(", orderNumber=").append(orderNumber);
        sb.append(", trackingType=").append(trackingType);
        sb.append(", trackingNumber=").append(trackingNumber);
        sb.append(", expressType=").append(expressType);
        sb.append(", status=").append(status);
        sb.append(", sentTime=").append(sentTime);
        sb.append(", payTime=").append(payTime);
        sb.append(", textureId=").append(textureId);
        sb.append(", technology=").append(technology);
        sb.append(", optimalScale=").append(optimalScale);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}