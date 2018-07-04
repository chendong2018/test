package com.jianwu.domain.enums;

/**
 * @ClassName: TemplateStatus
 * @Description: 模板状态
 * @Author: chenDong
 * @Date: 2018/6/2 11:58
 * @Remark:
 */
public enum AddressStatus {
    //状态 0 正常 1 默认 -1 删除
    DELETE(-1,"已删除"),
    NORMAL(0, "正常"),
    DEFAULT(1, "默认显示");
    private Integer status;

    private String name;

    AddressStatus(Integer status, String name) {
        this.status = status;
        this.name = name;
    }

    public static AddressStatus getAddressStatus(int status) {
        for (AddressStatus t : AddressStatus.values()) {
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
