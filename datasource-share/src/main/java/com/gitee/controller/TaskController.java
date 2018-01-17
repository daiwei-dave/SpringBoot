package com.gitee.controller;
import com.gitee.common.constants.ErrorCode;
import com.gitee.common.entity.ResultData;
import com.gitee.entity.Task;
import com.gitee.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;



/**
 * Created by daiwei on 2017/11/20.
 */
@Controller
@RequestMapping("/task")
public class TaskController {


   @Autowired
   private TaskService taskService;


    /**
     * 查询
     * @param
     * @return
     */
    @RequestMapping("/queryByList")
    @ResponseBody
    public ResultData pager(Task task) {
        try {
            return taskService.queryByList(task);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultData.newResultData(ErrorCode.FAILOR, ErrorCode.FAILOR_MSG);
        }
    }

}
