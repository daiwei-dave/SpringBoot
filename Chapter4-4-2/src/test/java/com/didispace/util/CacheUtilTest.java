package com.didispace.util;

import com.didispace.Application;
import com.didispace.entity.User;
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
    private CacheUtil cacheUtil;

    @Test
    public void test() throws Exception {



    }
    @Test
    public void put() throws Exception {
        User u1=new User();
        u1.setName("haha");
        u1.setAge(2);
        User user = cacheUtil.get("AAA");
        if (user==null){
            cacheUtil.put("AAA",u1);
            System.out.println(cacheUtil.get("AAA").getAge());
            cacheUtil.del("AAA");
            System.out.println(cacheUtil.get("AAA"));
        }

    }


}