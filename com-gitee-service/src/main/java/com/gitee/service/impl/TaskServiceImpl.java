package com.gitee.service.impl;

import com.gitee.common.dao.BaseDao;
import com.gitee.common.entity.ResultData;
import com.gitee.common.service.BaseServiceSupport;
import com.gitee.dao.TaskDao;
import com.gitee.entity.Task;
import com.gitee.service.TaskService;
import com.gitee.shard.annotation.ReadDatasource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 销售任务
 * @Description
 * @Author daiwei
 * @Date 2017/11/15
 * @Copyright(c) gome inc Gome Co.,LTD
 */
@Service("taskService")
public class TaskServiceImpl extends BaseServiceSupport<Task> implements TaskService {

    @Autowired
    TaskDao taskDao;
    @Override
    public BaseDao<Task> getDao() {
        return this.taskDao;
    }

    @Override
    @ReadDatasource
    public ResultData<List<Task>> queryByList(Task task){
        return super.queryByList(task);
    }
}
