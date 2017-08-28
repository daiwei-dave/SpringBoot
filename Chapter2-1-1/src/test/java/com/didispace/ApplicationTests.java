package com.didispace;

import com.didispace.configuration.FtpConfig;
import com.didispace.configuration.MyProps;
import com.didispace.service.BlogProperties;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


/**
 *
 * @author 程序猿DD
 * @version 1.0.0
 * @blog http://blog.didispace.com
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
@Component
public class ApplicationTests {

	private static final Log log = LogFactory.getLog(ApplicationTests.class);

	@Autowired
	private BlogProperties blogProperties;

	@Autowired
	private MyProps myProps;


	@Autowired
	private FtpConfig ftpConfig;


	@Test
	public void test1() throws Exception {
		Assert.assertEquals("程序猿DD", blogProperties.getName());
		Assert.assertEquals("Spring Boot教程", blogProperties.getTitle());
		Assert.assertEquals("程序猿DD正在努力写《Spring Boot教程》", blogProperties.getDesc());

		log.info("随机数测试输出：");
		log.info("随机字符串 : " + blogProperties.getValue());
		log.info("随机int : " + blogProperties.getNumber());
		log.info("随机long : " + blogProperties.getBignumber());
		log.info("随机10以下 : " + blogProperties.getTest1());
		log.info("随机10-20 : " + blogProperties.getTest2());

	}


	@Test
	public void propsTest() {
		System.out.println("simpleProp: " + myProps.getSimpleProp());
		System.out.println("arrayProps: " + myProps.getArrayProps());
//		System.out.println("listProp1: " +myProps.getListProp1());
//		System.out.println("listProp2: " +myProps.getListProp2());
//		System.out.println("mapProps: " + myProps.getMapProps());
	}

	@Test
	public void ftpTest() {
		System.out.println("ip: " +ftpConfig.getIp());
		System.out.println("port: " + ftpConfig.getPort());
		System.out.println("userName: " +ftpConfig.getUserName());
		System.out.println("userPwd: " +ftpConfig.getUserPwd());
	}

}
