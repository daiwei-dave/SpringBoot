package com.didispace.util;

import com.didispace.Application;
import com.didispace.entity.User;
import com.didispace.entity.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by daiwei on 2017/9/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
public class CacheUtilTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CacheManager cacheManager;

    @Autowired
    private CacheUtil cacheUtil;

    @Before
    public void before() {
        userRepository.save(new User("AAA", 10));
    }

    @Test
    public void test() throws Exception {

        User u1 = userRepository.findByName("AAA");
        System.out.println("第一次查询：" + u1.getAge());

        User u2 = userRepository.findByName("AAA");
        System.out.println("第二次查询：" + u2.getAge());

        u1.setAge(20);
        userRepository.save(u1);
        User u3 = userRepository.findByName("AAA");
        System.out.println("第三次查询：" + u3.getAge());

    }
    @Test
    public void put() throws Exception {
        User u1 = userRepository.findByName("AAA");
        User user = cacheUtil.get("AAA");
        if (user==null){
            cacheUtil.put("AAA",u1);
            System.out.println(cacheUtil.get("AAA").toString());
            cacheUtil.del("AAA");
            System.out.println(cacheUtil.get("AAA"));
        }

    }

}