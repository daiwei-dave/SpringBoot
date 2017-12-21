package com.didispace.dao;


import com.didispace.entity.Task;
import com.gitee.common.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 任务表
 * @Description
 * @Author liyi
 * @Date 2017/11/20
 * @Copyright(c) gome inc Gome Co.,LTD
 */
@Mapper
public interface TaskDao extends BaseDao<Task> {


    Task queryByBranchNoAndTaskNo(Task task);

    int updateTask(Task task);

    List<Task> getPeriodicTask(@Param("branchNo") String branchNo, @Param("taskType") String taskType);

    Task queryDetail(Integer id);

    int queryByStartDateAndEndDate(Task task);

    List<Task> getMonthlyTask(@Param("branchNo") String branchNo, @Param("taskType") String monthlyTask, @Param("date") String dateStr);


}
