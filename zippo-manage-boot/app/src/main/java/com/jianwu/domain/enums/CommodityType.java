package com.jianwu.domain.enums;

/**
 * @ClassName: CommodityStatus
 * @Description: 商品类型(1商家商品，2自定义商品)
 * @Author: chenDong
 * @Date: 2018/6/2 11:58
 * @Remark:
 */
public enum CommodityType {
    //状态 0 上架 1 下架 -1 删除
    MERCHART(1,"商家商品"),
    CUSTOM(2, "自定义商品");
    private Integer status;

    private String name;

    CommodityType(Integer status, String name) {
        this.status = status;
        this.name = name;
    }

    public static CommodityType getCommodityStatus(int status) {
        for (CommodityType t : CommodityType.values()) {
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
