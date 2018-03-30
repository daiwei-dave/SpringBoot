package com.gitee;


import com.didispace.Application;
import com.didispace.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
public class ApplicationTests {


	private final static Logger logger = LoggerFactory.getLogger(ApplicationTests.class);

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@Autowired
	private RedisTemplate<String, User> redisTemplate;


	/**
     * 测试分布式下的缓存进行通信.这次是与Chapter3-2-5服务进行的通信
	 * @throws Exception
	 */
	@Test
	public void testDistribution() throws Exception {
		User user = redisTemplate.opsForValue().get("超人");
		System.out.println(user.toString());

	}




}
