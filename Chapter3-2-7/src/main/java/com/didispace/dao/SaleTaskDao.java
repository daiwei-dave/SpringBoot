package com.didispace.dao;


import com.didispace.entity.SaleTask;
import com.didispace.entity.Stores;
import com.gitee.common.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 销售任务
 * @Description
 * @Author daiwei
 * @Date 2017/11/15
 * @Copyright(c) gome inc Gome Co.,LTD
 */
@Mapper
public interface SaleTaskDao extends BaseDao<SaleTask> {


 //   void batchAdd(@Param("saleTask")SaleTask saleTask, @Param("storeList")List<Stores> storeList);

    void batchAdd(@Param("taskId") String taskId, @Param("taskName") String taskName, @Param("branchTotal") Double branchTotal, @Param("storeList") List<Stores> storeList);
}
