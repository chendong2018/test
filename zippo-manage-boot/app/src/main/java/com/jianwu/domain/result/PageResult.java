package com.jianwu.domain.result;



/**
 * Created by tookbra on 2016/3/10.
 */
public class PageResult<T> extends Result<T> {
    private static final long serialVersionUID = 5669713791065732188L;
    private int page = 1;
    private int pageSize = 20;
    private int total;

    public PageResult(){

    }
    public PageResult(boolean success, String msg, T data, int total) {
        super(success, msg, data);
        this.total = total;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public static <T> PageResult<T> success(T date, int total) {
        return new PageResult(true, (String) null, date, total);
    }

    public static <T> PageResult<T> error(String errorMsg) {
        return new PageResult(false, errorMsg, (Object) null, 0);
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
