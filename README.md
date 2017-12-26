


版权归http://blog.didispace.com的博主所有

代码引用自https://git.oschina.net/didispace/SpringBoot-Learning

我只是参考拿来学习,其中的部分代码做了些更改

本项目内容为Spring Boot教程程序样例。

##样例列表

##1.集成web，数据访问，事务管理，日志管理为基础部分，必须掌握
##2.安全控制，缓存为优化部分

### 快速入门

- chapter1：[基本项目构建（可作为工程脚手架），引入web模块，完成一个简单的RESTful API](http://blog.didispace.com/spring-boot-learning-1/)
- [使用Intellij中的Spring Initializr来快速构建Spring Boot/Cloud工程](http://blog.didispace.com/spring-initializr-in-intellij/)

### 工程配置

- chapter2-1-1：[环境配置](http://blog.didispace.com/springbootproperties/)(重点掌握)

### Web开发

- chapter3-1-1：[自己开发的jar包功能测试](http://blog.didispace.com/springbootrestfulapi/)
- chapter3-1-2：[使用Thymeleaf模板引擎渲染web视图](http://blog.didispace.com/springbootweb/)
- chapter3-1-3：[使用Freemarker模板引擎渲染web视图](http://blog.didispace.com/springbootweb/)
- chapter3-1-4：[使用Velocity模板引擎渲染web视图](http://blog.didispace.com/springbootweb/)
- chapter3-1-5：[使用Swagger2构建RESTful API](http://blog.didispace.com/springbootswagger2/)(重点掌握)
- chapter3-1-6：[统一异常处理](http://blog.didispace.com/springbootexception/)(重点掌握)

### 数据访问

- chapter3-2-1：[使用JdbcTemplate](http://blog.didispace.com/springbootdata1/)
- chapter3-2-2：[使用Spring-data-jpa简化数据访问层（推荐）](http://blog.didispace.com/springbootdata2/)
- chapter3-2-3：[多数据源配置（一）：JdbcTemplate](http://blog.didispace.com/springbootmultidatasource/)
- chapter3-2-4：[多数据源配置（二）：Spring-data-jpa](http://blog.didispace.com/springbootmultidatasource/)
- chapter3-2-5：[使用NoSQL数据库（一）：Redis](http://blog.didispace.com/springbootredis/)
- chapter3-2-6：[使用NoSQL数据库（二）：MongoDB](http://blog.didispace.com/springbootmongodb/)(还未看)
- chapter3-2-7：[springboot整合mybatis](http://blog.didispace.com/springbootmybatis/)(重点掌握)
- chapter3-2-8：[MyBatis注解配置详解](http://blog.didispace.com/mybatisinfo/)(重点掌握)

### 事务管理

- chapter3-3-1：[使用事务管理](http://blog.didispace.com/springboottransactional/)
- chapter3-3-2：[分布式事务（未完成）]

### 其他内容
- chapter4-1-1：[使用@Scheduled创建定时任务](http://blog.didispace.com/springbootscheduled/)
- chapter4-1-2：[使用@Async实现异步调用](http://blog.didispace.com/springbootasync/)(还未看)

#### 日志管理

- chapter4-2-1：[默认日志的配置](http://blog.didispace.com/springbootlog/)(重点掌握)
- chapter4-2-2：[使用log4j记录日志](http://blog.didispace.com/springbootlog4j/)(重点掌握)
- chapter4-2-3：[对log4j进行多环境不同日志级别的控制](http://blog.didispace.com/springbootlog4jmuilt/)
- chapter4-2-4：[使用AOP统一处理Web请求日志](http://blog.didispace.com/springbootaoplog/)(重点掌握)
- chapter4-2-5：[使用log4j记录日志到MongoDB](http://blog.didispace.com/springbootlog4jmongodb/)(重点掌握)
- chapter4-2-6：[Spring Boot 1.5.x新特性：动态修改日志级别](http://blog.didispace.com/spring-boot-1-5-x-feature-1/)]
(重点掌握)
#### 安全管理

- chapter4-3-1：[使用Spring Security](http://blog.didispace.com/springbootsecurity/)(还未看)
- chapter4-3-2：[使用Spring Session（未完成）]

#### 缓存支持

- chapter4-4-1：[注解配置与EhCache使用](http://blog.didispace.com/springbootcache1/)(重点掌握)
- chapter4-4-2：[使用Redis做集中式缓存](http://blog.didispace.com/springbootcache2/)(重点掌握)

#### 邮件发送

- chapter4-5-1：[实现邮件发送：简单邮件、附件邮件、嵌入资源的邮件、模板邮件](http://blog.didispace.com/springbootmailsender/)

### 消息服务

- chapter5-1-1：[JMS（未完成）](还未看)
- chapter5-2-1：[Spring Boot中使用RabbitMQ](http://blog.didispace.com/spring-boot-rabbitmq/)

### 其他功能(还未看)

- chapter6-1-1：[使用Spring StateMachine框架实现状态机](http://blog.didispace.com/spring-statemachine/)
- [Spring Boot Actuator监控端点小结](http://blog.didispace.com/spring-boot-actuator-1/)
- [在传统Spring应用中使用spring-boot-actuator模块提供监控端点](http://blog.didispace.com/spring-boot-actuator-without-boot/)
- [Spring Boot应用的后台运行配置](http://blog.didispace.com/spring-boot-run-backend/)
- [Spring Boot自定义Banner](http://blog.didispace.com/spring-boot-banner/)


### Dubbo进行服务治理

- chapter9-2-1：[Spring Boot中使用Dubbo进行服务治理] (重点掌握)
- chapter9-2-2：[Spring Boot与Dubbo中管理服务依赖]( (重点掌握)

### Spring Cloud构建微服务架构(还未看)

由于Spring Cloud偏宏观架构，Spring Boot偏微观细节，内容上越来越多，为了两部分内容不互相干扰，所以迁移Spring Cloud内容到：[SpringCloud-Learning项目](http://git.oschina.net/didispace/SpringCloud-Learning)，该项目将不再更新Spring Cloud内容，关注Spring Cloud内容的请移步至[SpringCloud-Learning项目](http://git.oschina.net/didispace/SpringCloud-Learning)

- chapter9-1-1：[Spring Cloud构建微服务架构（一）服务注册与发现](http://blog.didispace.com/springcloud1/)
- chapter9-1-2：[Spring Cloud构建微服务架构（二）服务消费者](http://blog.didispace.com/springcloud2/)
- chapter9-1-3：[Spring Cloud构建微服务架构（三）断路器](http://blog.didispace.com/springcloud3/)
- chapter9-1-4：[Spring Cloud构建微服务架构（四）分布式配置中心](http://blog.didispace.com/springcloud4/)
- chapter9-1-5：[Spring Cloud构建微服务架构（五）服务网关](http://blog.didispace.com/springcloud5/)
- chapter9-1-6：[Spring Cloud构建微服务架构（六）集群监控]



