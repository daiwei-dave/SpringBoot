package com.didispace.entity;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * 通过@CachePut来让数据更新操作同步到缓存中
 * @author daiwei
 * @version 1.0.0
 * @date 16/3/23 下午2:34.
 * @blog http://blog.didispace.com
 */
@CacheConfig(cacheNames = "users")
public interface UserRepository extends JpaRepository<User, Long> {

  //  @Cacheable(key = "#p0")
    User findByName(String name);

  //  @CachePut(key = "#p0.name")
    User save(User user);

}
