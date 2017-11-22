package com.didispace.configuration.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @Description
 * @Author wangjie36@gome.com.cn
 * @Date 2017/8/17
 * @Copyright(c) gome inc Gome Co.,LTD
 */
//@Configuration
public class RedisConfig {

    @Autowired
    private ClusterConfigurationProperties clusterProperties;

    /**
     * 获取redis配置信息
     * @return
     */
    @Bean
    @ConfigurationProperties(prefix = "spring.redis")
    public JedisPoolConfig getRedisConfig() {
        JedisPoolConfig config = new JedisPoolConfig();
        return config;
    }

    @Bean
    public RedisConnectionFactory connectionFactory() {
        return new JedisConnectionFactory(
                new RedisClusterConfiguration(clusterProperties.getNodes()), getRedisConfig());
    }


//    @Bean
//    public RedisTemplate<?, ?> getRedisTemplate(RedisConnectionFactory connectionFactory) {
//        RedisTemplate<?, ?> template = new RedisTemplate<>();
//        template.setConnectionFactory(connectionFactory);
//        template.setKeySerializer(new StringRedisSerializer());
//        template.setValueSerializer(new RedisObjectSerializer());
//        return template;
//    }


//    @Bean
//    public RedisTemplate<String, Employee> redisTemplate(RedisConnectionFactory factory) {
//        RedisTemplate template = new RedisTemplate();
//        template.setConnectionFactory(factory);
//        //定义key序列化方式
//        //RedisSerializer<String> redisSerializer = new StringRedisSerializer();//Long类型会出现异常信息;需要我们上面的自定义key生成策略，一般没必要
//        //定义value的序列化方式
//        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
//        ObjectMapper om = new ObjectMapper();
//        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
//        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
//        jackson2JsonRedisSerializer.setObjectMapper(om);
//
//        // template.setKeySerializer(redisSerializer);
//        template.setValueSerializer(jackson2JsonRedisSerializer);
//        template.setHashValueSerializer(jackson2JsonRedisSerializer);
//        template.afterPropertiesSet();
//        return template;
//    }
}
