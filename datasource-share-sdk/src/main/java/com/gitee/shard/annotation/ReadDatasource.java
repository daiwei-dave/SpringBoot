package com.gitee.shard.annotation;

import java.lang.annotation.*;


/**
 * 切换数据源为读库
 * @author fukui
 *
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface ReadDatasource {

}
