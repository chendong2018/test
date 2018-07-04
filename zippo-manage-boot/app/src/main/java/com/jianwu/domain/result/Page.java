package com.jianwu.domain.result;

import java.io.Serializable;

/**
 * Created by tookbra on 2016/3/11.
 */
public class Page implements Serializable {
    private static final long serialVersionUID = 3242807584090264844L;

    private Integer page = 1;
    private Integer pageSize = 10;

    public Page(Integer page, Integer pageSize) {
        this.pageSize = pageSize;
        this.page = page;
    }

    public Page() {
    }

    public Integer getPage() {
        return Integer.valueOf(this.page);
    }

    public void setPage(Integer page) {
        if(page.intValue() < 1) {
            page = Integer.valueOf(1);
        }

        this.page = page.intValue();
    }

    public void setPageIndex(Integer page) {
        if(page.intValue() < 1) {
            page = Integer.valueOf(1);
        }

        this.page = page.intValue();
    }


    public Integer getPageSize() {
        return Integer.valueOf(this.pageSize);
    }

    public void setPageSize(Integer pageSize) {
        if(pageSize.intValue() < 1) {
            pageSize = Integer.valueOf(20);
        }

        this.pageSize = pageSize.intValue();
    }

    public Integer getStart() {
        return (this.page - 1) * this.pageSize ;
    }

    public Integer getEnd() {
        return this.page * this.pageSize;
//        return (this.page - 1) * (this.pageSize + 1) + this.pageSize;
    }
}
