package com.didispace.rabbit;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 消息消费者
 * <P>
 *     @RabbitListener (queues = "hello")  注解定义该类对hello队列的监听
 * </P>
 * @author 翟永超
 * @create 2016/9/25.
 * @blog http://blog.didispace.com
 */
@Component
@RabbitListener(queues = "hello")
public class Receiver {
    /**
     * 对消息的处理方法
     * <P>
     *     @RabbitHandler 注解来指定对消息的处理方法。消费操作为输出消息的字符串内容
     * </P>
     * @param hello
     */
    @RabbitHandler
    public void process(String hello) {
        System.out.println("Receiver : " + hello);
    }

}
