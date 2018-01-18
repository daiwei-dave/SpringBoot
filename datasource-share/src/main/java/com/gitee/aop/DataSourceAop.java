package com.gitee.aop;


import com.gitee.shard.ShardDataSourceAspect;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

/**
 * @Description aop 拦截 进行切换数据源
 * @Author daiwei
 * @Date 2017/8/3
 */
@Aspect
@Configuration
public class DataSourceAop {
    private static Logger logger = LoggerFactory.getLogger(DataSourceAop.class);

    @Autowired
    ShardDataSourceAspect shardDataSourceAspect;


    @Before("execution(* com.gitee.service..*.query*(..)) or execution(* com.gitee.service..*.get*(..)) or execution(* com.gitee.service..*.select*(..))")
    public void setReadDataSourceType(JoinPoint point) {
        shardDataSourceAspect.before(point);
    }


}
