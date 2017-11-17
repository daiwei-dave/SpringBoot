package com.github;

import com.gome.commons.validation.ValidationAdvisor;
import com.gome.commons.validation.ValidationInterceptor;
import com.rop.RopServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource(value="rop.appSecret.properties")
@ImportResource(locations={"spring-rop-conf.xml"})
public class SpringbootRopApplication implements EmbeddedServletContainerCustomizer {

	private static int port = 8050;
	public static void main(String[] args) {
		if( args !=null && args.length>0 ){
			port = Integer.valueOf(args[0]);
		}
		SpringApplication.run(SpringbootRopApplication.class, args);
	}

	/**
	 * rop依赖于servlet,故必须引入servlet依赖
	 * @return
	 */
	@Bean
	public ServletRegistrationBean registerRopServletBean() {
		return new ServletRegistrationBean(new RopServlet(), "/router");
	}

	@Bean
	public ValidationInterceptor registerValidationInterceptorBean(){
		return new ValidationInterceptor();
	}

	@Bean
	public ValidationAdvisor registerValidationAdvisorBean(){
		return new ValidationAdvisor();
	}



	@Override
	public void customize(ConfigurableEmbeddedServletContainer configurableEmbeddedServletContainer) {
		configurableEmbeddedServletContainer.setPort(port);
	}
}
