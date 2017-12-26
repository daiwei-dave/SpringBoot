package com.didispace.util;

import com.didispace.entity.User;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @Description redis工具类，默认1天过期
 * @Author daiwei
 * @Date 2017/8/17
 * @Copyright(c)
 */
@Component
public class CacheUtil {
    private static final Logger logger = LoggerFactory.getLogger(CacheUtil.class);

    // 过期时间，正式环境默认1天
    private static final int EMPLOYEE_TIME_OUT = 1;

    // 存储在redis中的map名称
    public static final String CACHE_EMPLOYEE_PREFIX = "emp-";

    // 超时类型，即单位时分秒
    @Value("${spring.redis.timeOutType}")
    private String timeOutType;

    @Autowired
    private RedisTemplate<String,User> redisTemplate;


    public boolean put(String key, User value) {
        key = CACHE_EMPLOYEE_PREFIX + key;
        logger.info("method[void put(String key, Employee value)] params:{key:{}, value:{}}", new Object[]{key, value});
        if (StringUtils.isBlank(key) || ObjectUtils.equals(null, value)) {
            logger.error("method[void put(String key, T value)] params error:{key:{}, value:{}}", new Object[]{key, value});
            return false;
        } else {
            try {
                redisTemplate.opsForValue().set(key, value);
                if (timeOutType != null && timeOutType.equals("hour")) {
                    redisTemplate.expire(key, EMPLOYEE_TIME_OUT, TimeUnit.HOURS);
                } else {
                    redisTemplate.expire(key, EMPLOYEE_TIME_OUT, TimeUnit.DAYS);
                }
            } catch (Exception e) {
                logger.error("向redis中存储失败，参数:{key:{}, value:{}}, 异常信息：{}", key, value.toString(), e.toString());
                return false;
            }

            return true;
        }
    }

    public boolean isExist(String key) {
        key = CACHE_EMPLOYEE_PREFIX + key;
        logger.info("method[void isExist(String key)] params:{key:{}}", new Object[]{key});
        if (StringUtils.isBlank(key)) {
            logger.error("method[void isExist(String key)] params error:{key:{}}", new Object[]{key});
            return false;
        } else {
            try {
                if (redisTemplate.opsForValue().get(key) != null) {
                    return true;
                }
            } catch (Exception e) {
                logger.error("判断redis中是否存储员工信息失败，参数:{key:{}}, 异常信息：{}", key, e.toString());
            }

            return false;
        }
    }


    public User get(String key) {
        key = CACHE_EMPLOYEE_PREFIX + key;
        logger.info("method[void get(String key)] params:{key:{}}", new Object[]{key});
        if (StringUtils.isBlank(key)) {
            logger.error("method[void get(String key)] params error:{key:{}}", new Object[]{key});
        } else {
            try {
                return (User) redisTemplate.opsForValue().get(key);
            } catch (Exception e) {
                logger.error("从redis中获取失败，参数:{key:{}}, 异常信息：{}", key, e.toString());
            }
        }
        return null;
    }

    public void del(String key) {
        try {
            key = CACHE_EMPLOYEE_PREFIX + key;
            redisTemplate.delete(key);
        } catch (Exception e) {
            logger.error("从redis中删除对象失败，参数:{key:{}}, 异常信息：{}", key, e.toString());
        }
    }
}
