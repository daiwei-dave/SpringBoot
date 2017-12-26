package com.didispace.configuration.redis;

import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;
import org.springframework.util.SerializationUtils;

/**
 * @Description 对象的序列化和反序列化工具
 * @Author daiwei
 * @Date 2017/8/17
 * @Copyright(c) gome inc Gome Co.,LTD
 */
public class RedisObjectSerializer implements RedisSerializer<Object> {

    static final byte[] EMPTY_ARRAY = new byte[0];

    /**
     * 序列化对象
     * @param object
     * @return
     * @throws SerializationException
     */
    @Override
    public byte[] serialize(Object object) throws SerializationException {
        if (object == null) {
            return EMPTY_ARRAY;
        }
        try {
            return SerializationUtils.serialize(object);
        } catch (Exception ex) {
            return EMPTY_ARRAY;
        }
    }

    /**
     * 反序列化
     * @param bytes
     * @return
     * @throws SerializationException
     */
    @Override
    public Object deserialize(byte[] bytes) throws SerializationException {
        if (isEmpty(bytes)) {
            return null;
        }
        try {
            return SerializationUtils.deserialize(bytes);
        } catch (Exception ex) {
            throw new SerializationException("Cannot deserialize", ex);
        }
    }

    private boolean isEmpty(byte[] data) {
        return (data == null || data.length == 0);
    }
}
