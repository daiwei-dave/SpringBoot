package com.didispace.controller;

import com.didispace.entity.SaleTask;
import com.didispace.entity.Stores;
import com.didispace.service.SaleTaskService;
import com.gitee.common.constants.ErrorCode;
import com.gitee.common.entity.ResultData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by daiwei on 2017/11/20.
 */
@Controller
@RequestMapping("/saleTask")
public class SaleTaskController {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    SaleTaskService saleTaskService;
    /**
     * 新增
     * @param
     * @return
     */
    @RequestMapping("/add")
    @ResponseBody
    public ResultData<SaleTask> add(SaleTask saleTask) {
        try {
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
            return ResultData.newSuccessResultData();
        } catch (Exception e) {
            logger.error("新增失败", e);
            e.printStackTrace();
            return ResultData.newResultData(ErrorCode.ADD_FAILOR, "新增失败");
        }
    }
}
