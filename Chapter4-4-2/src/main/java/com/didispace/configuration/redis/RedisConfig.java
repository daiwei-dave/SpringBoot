package com.didispace.configuration.redis;

import com.didispace.entity.User;
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
@Configuration
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


    @Bean
    public RedisTemplate<?, ?> getRedisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<?, ?> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new RedisObjectSerializer());
        return template;
    }
}
