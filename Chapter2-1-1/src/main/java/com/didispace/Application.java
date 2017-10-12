package com.didispace;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;

/**
 *
 * @author daiwei
 * @version 1.0.0
 * @blog http://blog.didispace.com
 * @see端口设置：http://blog.csdn.net/argel_lj/article/details/49851625
 */
@SpringBootApplication
public class Application implements EmbeddedServletContainerCustomizer {

	private static Integer SERVER_PORT=8082;


	/**
	 * 我们的main方法通过调用run， 将业务委托给了Spring Boot的SpringApplication类。
	 * SpringApplication将引导我们的应用， 启动Spring， 相应地启动被自动配置的Tomcat web服务器。 我们需要将 Application.class 作为参数传递给run方法来告诉SpringApplication谁是
	 主要的Spring组件。 为了暴露任何的命令行参数， args数组也会被传递过去。
	 * @param args
	 */
//	public static void main(String[] args) {
//
//		SpringApplication.run(Application.class, args);
//
//	}

	public static void main(String[] args){
		SpringApplication app = new SpringApplication(Application.class);
		//不展示springBoot旗帜
		app.setBannerMode(Banner.Mode.OFF);
		app.run(args);
	}


	/**
	 * 设置tomcat的端口
	 * @param container
	 */
	public void customize(ConfigurableEmbeddedServletContainer container) {
		container.setPort(SERVER_PORT);
	}

}
