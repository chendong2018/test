package com.jianwu.domain.enums;


/**
 * 模板面类型 1 a  2 b
 * @author CHENDONG
 */
public enum TemplateType {
    //状态 1 正面 2 背面
    A(1,"A"),
    B(2, "B"),
    C(3, "C");
    private Integer status;

    private String name;

    TemplateType(Integer status, String name) {
        this.status = status;
        this.name = name;
    }

    public static TemplateType getTemplateType(int status) {
        for (TemplateType t : TemplateType.values()) {
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
