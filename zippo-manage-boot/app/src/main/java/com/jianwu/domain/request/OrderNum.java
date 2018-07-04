package com.jianwu.domain.request;

import java.io.Serializable;

/**
 * 订单数量
 *
 * @Author chendong
 * @Create 2018/6/4
 * @Time 17:22
 **/
public class OrderNum implements Serializable {

    /**
     *  待支付数
     */
    private Integer unpaid;

    /**
     * 待发货数
     */
    private Integer toSend;

    /**
     * 待收货数
     */
    private Integer toReceive;

    public Integer getUnpaid() {
        return unpaid;
    }

    public void setUnpaid(Integer unpaid) {
        this.unpaid = unpaid;
    }

    public Integer getToSend() {
        return toSend;
    }

    public void setToSend(Integer toSend) {
        this.toSend = toSend;
    }

    public Integer getToReceive() {
        return toReceive;
    }

    public void setToReceive(Integer toReceive) {
        this.toReceive = toReceive;
    }
}
