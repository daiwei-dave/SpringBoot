package com.didispace.controller;

import com.didispace.dao.TaskDao;
import com.didispace.entity.SaleTask;
import com.didispace.entity.Stores;
import com.didispace.entity.Task;
import com.didispace.entity.vo.PagerVo;
import com.didispace.param.PagerQuery;
import com.didispace.service.SaleTaskService;
import com.gitee.common.constants.ErrorCode;
import com.gitee.common.entity.ResultData;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by daiwei on 2017/11/20.
 */
@Controller
@RequestMapping("/task")
public class TaskController {


   @Autowired
   private TaskDao taskDao;


    /**
     * 新增
     * @param
     * @return
     */
    @RequestMapping("/pager")
    @ResponseBody
    public ResultData pager(Task task, PagerQuery pagerQuery) {
        try {
            if (pagerQuery.getPageNumber() != null && pagerQuery.getPageSize() != null) {
                PageHelper.startPage(pagerQuery.getPageNumber(), pagerQuery.getPageSize(), true);
            }
            List<Task> taskList = taskDao.queryByList(task);
            PageInfo<Task> pageInfo = new PageInfo<>(taskList);
            PagerVo<Task> pagerVo = new PagerVo<>(pageInfo.getList());
            if ((pagerVo.getAaData() == null || pagerVo.getAaData().size() == 0) && taskList.size() > 0) {
                pagerVo.setAaData(taskList);
            }
            pagerVo.setiTotalDisplayRecords(pageInfo.getPageSize());
            pagerVo.setiTotalRecords(pageInfo.getTotal());
            return ResultData.newResultData(pagerVo);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultData.newResultData(ErrorCode.ADD_FAILOR, "新增失败");
        }
    }

}
