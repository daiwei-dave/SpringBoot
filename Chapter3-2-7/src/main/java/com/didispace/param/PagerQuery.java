package com.didispace.param;

/**
 * Created by daiwei on 2017/12/20.
 */
public class PagerQuery {
    // 当前页码
    private Integer pageNumber;

    // 每页大小
    private Integer pageSize;

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
