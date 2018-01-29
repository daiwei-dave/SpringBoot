package com.didispace;

import com.didispace.rabbit.Sender;
import com.didispace.rabbit.many.NeoSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = HelloApplication.class)
public class HelloApplicationTests {

	@Autowired
	private Sender sender;


	@Autowired
	private NeoSender neoSender;

	@Test
	public void hello() throws Exception {
		sender.send();
	}

	/**
	 *一对多发送
	 * @throws Exception
	 */
	@Test
	public void oneToMany() throws Exception {
		for (int i=0;i<100;i++){
			neoSender.send(i);
		}
	}

}
