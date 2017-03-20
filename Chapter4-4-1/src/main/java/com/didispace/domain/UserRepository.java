package com.didispace.domain;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * @author 程序猿DD
 * @version 1.0.0
 * @date 16/3/23 下午2:34.
 * @blog http://blog.didispace.com
 */
@CacheConfig(cacheNames = "users")
public interface UserRepository extends JpaRepository<User, Long> {
    /**
     * @Cacheable(key = "#p0", condition = "#p0.length() < 3")，表示只有当第一个参数的长度小于10的时候才会被缓存
     * @param name
     * @return
     */
    @Cacheable(key = "#p0", condition = "#p0.length() < 4")
    User findByName(String name);

}
