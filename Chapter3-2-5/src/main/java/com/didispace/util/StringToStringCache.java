package com.didispace.util;

import com.didispace.base.AbstractCacheUtil;
import org.springframework.stereotype.Service;

/**
 * @Description redis工具类，默认1天过期
 * @Author daiwei
 * @Date 2017/8/17
 * @Copyright(c)
 */
@Service("stringToStringCache")
public class StringToStringCache extends AbstractCacheUtil<String,String>{

}
