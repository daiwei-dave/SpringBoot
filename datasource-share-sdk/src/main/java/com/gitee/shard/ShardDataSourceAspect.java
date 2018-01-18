package com.gitee.shard;

import com.gitee.shard.annotation.ReadDatasource;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;


public class ShardDataSourceAspect {
	
	
	
	private Logger logger = LoggerFactory.getLogger(getClass());

	public void before( JoinPoint point )   {
        Object target = point.getTarget();
        String method = point.getSignature().getName();
        Class<?>[] parameterTypes = ((MethodSignature) point.getSignature()).getMethod().getParameterTypes();
        try {  
            Method m = target.getClass().getMethod(method, parameterTypes);
            if (m != null ) {  
            	if ( m.isAnnotationPresent( ReadDatasource.class ) ) {
            		logger.debug(target.getClass() + ". " + method + ",  select datasource slave  ");
            		//需要读写分离
                	ShardDataSourceSupport.setDatasource( Boolean.TRUE );
                }else {  
                	logger.debug(target.getClass() + ". " + method + ",  select datasource master  ");
                	ShardDataSourceSupport.setDatasource( Boolean.FALSE );
                }
            }
        } catch (Exception e) {
            logger.error("" ,  e );
        }  
    }  
	
}
