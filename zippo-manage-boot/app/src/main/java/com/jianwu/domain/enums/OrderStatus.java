package com.jianwu.domain.enums;

/**
 * @ClassName: TemplateStatus
 * @Description: 订单状态
 * @Author: chenDong
 * @Date: 2018/6/2 11:58
 * @Remark:
 */
public enum OrderStatus {
    //订单状态（1待付款，2待发货，3待收货，4已收货）
    TOPAY(1,"待付款"),
    TOPAID(2, "待发货"),
    TORECEIVE(3, "待收货"),
    RECEIVE(4, "已收货");
    private Integer status;

    private String name;

    OrderStatus(Integer status, String name) {
        this.status = status;
        this.name = name;
    }

    public static OrderStatus getOrderStatus(int status) {
        for (OrderStatus t : OrderStatus.values()) {
            if (t.getStatus() == status) {
                return t;
            }
        }
        return null;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
