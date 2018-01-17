package com.gitee.dao;

import com.gitee.common.dao.BaseDao;
import com.gitee.entity.Task;
import org.apache.ibatis.annotations.Mapper;


/**
 * 任务表
 * @Description
 * @Author daiwei
 * @Date 2017/11/20
 */
@Mapper
public interface TaskDao extends BaseDao<Task> {



}
