package com.didispace;

import com.didispace.model.User;
import com.didispace.rabbit.Sender;
import com.didispace.rabbit.fanout.FanoutSender;
import com.didispace.rabbit.many.NeoSender;
import com.didispace.rabbit.many.NeoSender2;
import com.didispace.rabbit.object.ObjectSender;
import com.didispace.rabbit.topic.TopicSender;
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


	@Autowired
	private FanoutSender fanoutSender;


	@Autowired
	private TopicSender topicSender;

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

	/**
	 * Fanout Exchange
	 * <P>
	 *     Fanout 就是我们熟悉的广播模式或者订阅模式，给Fanout交换机发送消息，绑定了这个交换机的所有队列都收到这个消息。
	 * </P>
	 * @throws Exception
	 */
	@Test
	public void fanoutSender() throws Exception {
		fanoutSender.send();
	}



	//以下为Topic Exchange。发送send1会匹配到topic.#和topic.message 两个Receiver，都可以收到消息。发送send2只有topic.#可以匹配，只有Receiver2监听到消息

	@Test
	public void topic() throws Exception {
		topicSender.send();
	}

	@Test
	public void topic1() throws Exception {
		topicSender.send1();
	}

	@Test
	public void topic2() throws Exception {
		topicSender.send2();
	}


}
