package com.jianwu.domain.enums;


public enum ZippoStatus {
    //状态 0正常 1删除
    DELETE(-1,"已删除"),
    OPEN(0, "正常"),
    CLOSE(1, "禁用");
    private Integer status;

    private String name;

    ZippoStatus(Integer status, String name) {
        this.status = status;
        this.name = name;
    }

    public static ZippoStatus getApprovalStatus(int status) {
        for (ZippoStatus t : ZippoStatus.values()) {
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
