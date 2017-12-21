package com.didispace.entity.vo;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * @Description 分页参数包装
 * @Author daiwei
 * @Date 2017/11/23
 * @Copyright(c) gome inc Gome Co.,LTD
 */
public class PagerVo<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    // 当前页显示的记录数
    private int iTotalDisplayRecords;

    // 总记录条数
    private long iTotalRecords;

    // 数据
    private List<T> aaData = Collections.emptyList();

    public PagerVo() {
    }

    public PagerVo(List<T> aaData) {
        this.aaData = aaData;
    }

    public long getiTotalRecords() {
        return iTotalRecords;
    }

    public void setiTotalRecords(long iTotalRecords) {
        this.iTotalRecords = iTotalRecords;
    }

    public int getiTotalDisplayRecords() {
        return iTotalDisplayRecords;
    }

    public void setiTotalDisplayRecords(int iTotalDisplayRecords) {
        this.iTotalDisplayRecords = iTotalDisplayRecords;
    }

    public List<T> getAaData() {
        return aaData;
    }

    public void setAaData(List<T> aaData) {
        this.aaData = aaData;
    }
}
