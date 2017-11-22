package com.didispace.util;

import com.didispace.Application;
import com.didispace.entity.Employee;
import com.didispace.entity.EmployeeRepository;
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
public class CacheEmployeeUtilTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private CacheManager cacheManager;

    @Autowired
    private CacheEmployeeUtil cacheEmployeeUtil;

    @Before
    public void before() {
        employeeRepository.save(new Employee("AAA", 10));
    }


    @Test
    public void put() throws Exception {
        Employee u1 = employeeRepository.findByName("AAA");
        Employee user = cacheEmployeeUtil.get("AAA");
        if (user==null){
            cacheEmployeeUtil.put("AAA",u1);
            System.out.println(cacheEmployeeUtil.get("AAA").toString());
            cacheEmployeeUtil.del("AAA");
            System.out.println(cacheEmployeeUtil.get("AAA"));
        }

    }

}