package com.didispace.service;

import com.didispace.entity.SaleTask;
import com.didispace.entity.Stores;
import com.didispace.service.iml.SaleTaskServiceImpl;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by daiwei on 2017/11/20.
 */
public class SaleTaskServiceTest {



    @Test
    public void batchAdd() throws Exception {
        SaleTaskService saleTaskService=new SaleTaskServiceImpl();
        SaleTask saleTask=new SaleTask();
        saleTask.setTaskId("2");
        saleTask.setTaskName("10月任务");
        saleTask.setBranchTotal(100.53);
        Stores store1 = new Stores("A20A", "河北世纪商城", 20.5);
        Stores store2 = new Stores("A21T", "河北空中花园店", 20.6);
        List<Stores> storesList=new ArrayList<>();
        storesList.add(store1);
        storesList.add(store2);
        saleTask.setStoreList(storesList);
        saleTaskService.batchAdd(saleTask,saleTask.getStoreList());
    }

}