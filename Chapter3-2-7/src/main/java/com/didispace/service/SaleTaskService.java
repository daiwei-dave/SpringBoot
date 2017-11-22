package com.didispace.service;


import com.didispace.entity.SaleTask;
import com.didispace.entity.Stores;
import com.gitee.common.service.BaseService;
import java.util.List;

/**
 * 销售任务
 * @Description
 * @Author daiwei
 * @Date 2017/11/15
 * @Copyright(c) gome inc Gome Co.,LTD
 */
public interface SaleTaskService extends BaseService<SaleTask> {


    void batchAdd(SaleTask saleTask, List<Stores> storeList);
}
