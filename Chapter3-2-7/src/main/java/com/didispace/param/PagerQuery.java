package com.didispace.param;

/**
 * Created by daiwei on 2017/12/20.
 */
public class PagerQuery {
    // 当前页码（默认为1）
    private Integer pageNumber=1;

    // 每页大小（默认为10）
    private Integer pageSize=10;

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
