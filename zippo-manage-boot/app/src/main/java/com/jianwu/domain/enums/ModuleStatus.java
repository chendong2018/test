package com.jianwu.domain.enums;

/**
 * @ClassName: TemplateStatus
 * @Description: 模板面类型
 * @Author: chenDong
 * @Date: 2018/6/2 11:58
 * @Remark:
 */
public enum ModuleStatus {
    //状态 0 正常 1 默认 -1 删除
    FRONT(1,"正面"),
    BACK(2, "背面"),
    SIDE(3, "侧面"),
    NOSIDE(4, "非侧面"),
    TOP(5, "顶部");
    private Integer status;

    private String name;

    ModuleStatus(Integer status, String name) {
        this.status = status;
        this.name = name;
    }

    public static ModuleStatus getModuleStatus(int status) {
        for (ModuleStatus t : ModuleStatus.values()) {
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
