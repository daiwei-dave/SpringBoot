package com.gitee;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 加入@MapperScan注解才能扫描到mapper的位置
 */
@SpringBootApplication
@MapperScan("com.gitee.dao")
public class DatasourceShareApplication {

	public static void main(String[] args) {
		SpringApplication.run(DatasourceShareApplication.class, args);
	}
}
