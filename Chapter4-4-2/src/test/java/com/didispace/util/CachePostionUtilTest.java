package com.didispace.util;

import com.didispace.Application;
import com.didispace.entity.Postion;
import com.didispace.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by daiwei on 2017/12/27.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
public class CachePostionUtilTest {
    @Autowired
    CachePostionUtil cachePostionUtil;

    @Autowired
    CachePostionListUtil cachePostionListUtil;

    // 存储在redis中的map名称
    public static final String CACHE_EMPLOYEE_PREFIX = "pos-";

    @Test
    public void isExist() throws Exception {

    }

    @Test
    public void get() throws Exception {

    }

    @Test
    public void del() throws Exception {

    }

    @Test
    public void put() throws Exception {
        Postion postion=new Postion();
        postion.setPostName("aaa");
        postion.setLevel(1);
        String key=CACHE_EMPLOYEE_PREFIX+postion.getPostName();
        Postion user = cachePostionUtil.get(postion.getPostName());
        if (user==null){
            cachePostionUtil.put(key,postion);
            System.out.println(cachePostionUtil.get(key));
            cachePostionUtil.del(key);
            System.out.println(cachePostionUtil.get(key));
        }
    }

    @Test
    public void putPostionList() throws Exception {
        Postion postion=new Postion();
        postion.setPostNo("111");
        postion.setPostName("aaa");
        postion.setLevel(1);
        List<Postion> postionList=new ArrayList<>();
        postionList.add(postion);
        postionList.add(new Postion("111"));
        String key=CACHE_EMPLOYEE_PREFIX+postion.getPostNo();
        List<Postion> user = cachePostionListUtil.get(key);
        if (user==null){
            cachePostionListUtil.put(key,postionList);
            System.out.println(cachePostionListUtil.get(key));
            cachePostionUtil.del(key);
            System.out.println(cachePostionListUtil.get(key));
        }
    }
}