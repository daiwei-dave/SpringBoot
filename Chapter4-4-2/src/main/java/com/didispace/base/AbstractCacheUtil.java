package com.didispace.base;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import java.util.concurrent.TimeUnit;

/**
 * redis工具类抽象
 * <P>
 *     实现redis的基本操作
 * </P>
 * @Author daiwei
 * @Date 2017/12/27
 * @Copyright(c)
 */
public abstract class AbstractCacheUtil<K,V> {
    private static final Logger logger = LoggerFactory.getLogger(AbstractCacheUtil.class);

    // 过期时间，正式环境默认1天
    protected static final int TIME_OUT = 1;

    // 超时类型，即单位时分秒
    @Value("${spring.redis.timeOutType}")
    protected String timeOutType;

    @Autowired
    protected RedisTemplate<K,V> redisTemplate;

    public boolean put(K key, V value) {
        logger.info("method[void put(K key, V value)] params:{key:{}, value:{}}", new Object[]{key, value});
        if (StringUtils.isBlank(key.toString()) || ObjectUtils.equals(null, value)) {
            logger.error("method[void put(K key, V value)] params error:{key:{}, value:{}}", new Object[]{key, value});
            return false;
        } else {
            try {
                redisTemplate.opsForValue().set(key, value);
                if (timeOutType != null && timeOutType.equals("hour")) {
                    redisTemplate.expire(key, TIME_OUT, TimeUnit.HOURS);
                } else {
                    redisTemplate.expire(key, TIME_OUT, TimeUnit.DAYS);
                }
            } catch (Exception e) {
                logger.error("向redis中存储失败，参数:{key:{}, value:{}}, 异常信息：{}", key, value.toString(), e.toString());
                return false;
            }

            return true;
        }
    }

    public V get(K key) {
        logger.info("method[void get(K key)] params:{key:{}}", new Object[]{key});
        if (StringUtils.isBlank(key.toString())) {
            logger.error("method[void get(K key)] params error:{key:{}}", new Object[]{key});
        } else {
            try {
                return redisTemplate.opsForValue().get(key);
            } catch (Exception e) {
                logger.error("从redis中获取失败，参数:{key:{}}, 异常信息：{}", key, e.toString());
            }
        }
        return null;
    }

    public void del(K key) {
        try {
            redisTemplate.delete(key);
        } catch (Exception e) {
            logger.error("从redis中删除对象失败，参数:{key:{}}, 异常信息：{}", key, e.toString());
        }
    }
}
