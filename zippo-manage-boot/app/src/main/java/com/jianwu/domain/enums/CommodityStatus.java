package com.jianwu.domain.enums;

/**
 * @ClassName: CommodityStatus
 * @Description: 商品状态
 * @Author: chenDong
 * @Date: 2018/6/2 11:58
 * @Remark:
 */
public enum CommodityStatus {
    //状态 0 上架 1 下架 -1 删除
    DELETE(-1,"已删除"),
    PUTAWAY(0, "上架"),
    SOLDOUT(1, "下架");
    private Integer status;

    private String name;

    CommodityStatus(Integer status, String name) {
        this.status = status;
        this.name = name;
    }

    public static CommodityStatus getCommodityStatus(int status) {
        for (CommodityStatus t : CommodityStatus.values()) {
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
