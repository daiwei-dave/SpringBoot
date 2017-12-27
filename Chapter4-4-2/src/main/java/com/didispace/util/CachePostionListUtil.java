package com.didispace.util;

import com.didispace.base.AbstractCacheUtil;
import com.didispace.entity.Postion;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Description redis工具类，默认1天过期
 * @Author daiwei
 * @Date 2017/8/17
 * @Copyright(c)
 */
@Component
public class CachePostionListUtil extends AbstractCacheUtil<String,List<Postion>>{

}
