package com.gitee.aop;

import com.gome.datasource.shard.ShardDataSourceAspect;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import static com.sun.xml.internal.ws.dump.LoggingDumpTube.Position.Before;


/**
 * @Description aop 拦截 进行切换数据源
 * @Author wangjie36@gome.com.cn
 * @Date 2017/8/3
 * @Copyright(c) gome inc Gome Co.,LTD
 */
@Aspect
@Configuration
public class DataSourceAop {
    private static Logger logger = LoggerFactory.getLogger(DataSourceAop.class);

    @Autowired
    ShardDataSourceAspect shardDataSourceAspect;

//    @Before("execution(* com.gome.store.service..*.*(..))")
//    @Before("execution(* com.gome.store.service..*.query*(..)) or execution(* com.gome.store.service..*.get*(..)) or execution(* com.gome.store.service..*.select*(..))")
    public void setReadDataSourceType(JoinPoint point) {
        shardDataSourceAspect.before(point);
    }


}
