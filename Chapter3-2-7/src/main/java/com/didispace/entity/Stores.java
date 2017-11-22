package com.didispace.entity;

/**
 * Created by daiwei on 2017/11/15.
 */
public class Stores {

    private String storeId;//   门店id
    private String storeName;//   门店名称
    private Double storeSale;//   门店销售任务

    public Stores(String storeId, String storeName, Double storeSale) {
        this.storeName = storeName;
        this.storeId = storeId;
        this.storeSale = storeSale;
    }

    public String getStoreName() {
        return this.storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getStoreId() {
        return this.storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public Double getStoreSale() {
        return this.storeSale;
    }

    public void setStoreSale(Double storeSale) {
        this.storeSale = storeSale;
    }
}
