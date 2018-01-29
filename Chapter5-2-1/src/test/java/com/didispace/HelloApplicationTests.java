package com.didispace;

import com.didispace.model.User;
import com.didispace.rabbit.Sender;
import com.didispace.rabbit.many.NeoSender;
import com.didispace.rabbit.many.NeoSender2;
import com.didispace.rabbit.object.ObjectSender;
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


	@Autowired
	private NeoSender2 neoSender2;


	@Autowired
	private ObjectSender objectSender;

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


	/**
	 * 多对多发送
	 * @throws Exception
	 * 和一对多一样，接收端仍然会均匀接收到消息
	 */
	@Test
	public void manyToMany() throws Exception {
		for (int i=0;i<10;i++){
			neoSender.send(i);
			neoSender2.send(i);
		}
	}

	/**
	 * 对象的发送和接收
	 * @throws Exception
	 */
	@Test
	public void sendOject() throws Exception {
		User user=new User();
		user.setName("neo");
		user.setPass("123456");
		objectSender.send(user);
	}


}
