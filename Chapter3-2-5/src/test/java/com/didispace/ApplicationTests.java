package com.didispace;

import com.didispace.entity.User;
import com.didispace.util.GZipStrUtil;
import com.didispace.util.SerializeUtil;
import org.junit.Assert;
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

	@Test
	public void test() throws Exception {

		// 保存字符串
		stringRedisTemplate.opsForValue().set("aaa", "111");
		Assert.assertEquals("111", stringRedisTemplate.opsForValue().get("aaa"));

		// 保存对象
		User user = new User("超人", 20);
		redisTemplate.opsForValue().set(user.getUsername(), user);

		user = new User("蝙蝠侠", 30);
		redisTemplate.opsForValue().set(user.getUsername(), user);

		user = new User("蜘蛛侠", 40);
		redisTemplate.opsForValue().set(user.getUsername(), user);


		Assert.assertEquals(20, redisTemplate.opsForValue().get("超人").getAge().longValue());
		Assert.assertEquals(30, redisTemplate.opsForValue().get("蝙蝠侠").getAge().longValue());
		Assert.assertEquals(40, redisTemplate.opsForValue().get("蜘蛛侠").getAge().longValue());

	}

	/**
	 * Redis压缩存储
	 * @throws Exception
	 */
	@Test
	public void compressTest() throws Exception {

		String redisKey="dw";
		// 保存对象
		User user = new User("超人", 20);
		String redisSerialize = SerializeUtil.serializationObjectByKryo(user);

		/***
		 * 由于redis所缓存value大小不得超过5000bytes
		 * 因而，在此考虑将序列化后的scope分成两部份。
		 * 两部份分别压缩，然后缓存
		 */
		Integer half = redisSerialize.length()/2;
		String redisOne = redisSerialize.substring(0, half);
		String redisTwo = redisSerialize.substring(half, redisSerialize.length());
		logger.debug("压缩前大小[one][ {} ]",redisOne.getBytes("Unicode").length);
		logger.debug("压缩前大小[two][ {} ]",redisTwo.getBytes("Unicode").length);
		final String redisGzipOne = GZipStrUtil.compress(redisOne);
		final String redisGzipTwo = GZipStrUtil.compress(redisTwo);
		logger.debug("压缩后大小[one][ {} ]",redisGzipOne.getBytes("Unicode").length);
		logger.debug("压缩后大小[two][ {} ]",redisGzipTwo.getBytes("Unicode").length);

		/**
		 * 存入缓存
		 */
		stringRedisTemplate.opsForValue().set(redisKey+"one", redisGzipOne);
		stringRedisTemplate.opsForValue().set(redisKey+"two", redisGzipTwo);


		String redisGzipOneValue = stringRedisTemplate.opsForValue().get(redisKey + "one");
		String redisGzipTwoValue = stringRedisTemplate.opsForValue().get(redisKey + "two");

		String redisOneValue = GZipStrUtil.unCompress(redisGzipOneValue);
		String redisTwoValue = GZipStrUtil.unCompress(redisGzipTwoValue);

		String redisSerializeValue = redisOneValue + redisTwoValue;
		User u = SerializeUtil.deserializationObjectByKryo(redisSerializeValue, User.class);
		System.out.println(u.toString());












	}




}
