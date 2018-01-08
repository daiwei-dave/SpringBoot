package com.github;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;

@SpringBootApplication
public class Application implements EmbeddedServletContainerCustomizer {
	private static Integer SERVER_PORT=8050;
	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(Application.class);
		app.setBannerMode(Banner.Mode.OFF);
		if (args!=null&&args.length>0){
			SERVER_PORT = Integer.valueOf(args[0]);
		}
		app.run(args);
	}

	public void customize(ConfigurableEmbeddedServletContainer container) {
		container.setPort(SERVER_PORT);
	}
}
