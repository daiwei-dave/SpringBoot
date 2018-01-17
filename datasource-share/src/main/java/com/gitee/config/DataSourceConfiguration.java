package com.gitee.config;

import com.gome.datasource.shard.ShardDataSource;
import com.gome.datasource.shard.ShardDataSourceAspect;
import com.gome.datasource.shard.SlaveConfig;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @Description 数据库相关配置
 * @Author daiwei
 * @Date 2017/8/3
 *
 */
@Configuration
@EnableTransactionManagement
public class DataSourceConfiguration {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${datasource.type}")
    private Class<? extends DataSource> dataSourceType;

    @Autowired
    private Environment env;

    /**
     * @Description 注入主库bean
     * @author wangjie36@gome.com.cn
     * @date 2017/8/3
     * @params
     */
    @Bean(name = "dataSource")
    @Primary
    @ConfigurationProperties(prefix = "datasource.write")
    public DataSource dataSource() {
        logger.info("-------------------- dataSource init ---------------------");
        return DataSourceBuilder.create().type(dataSourceType).build();
    }

    /**
     * @Description 注入从库bean
     * @author wangjie36@gome.com.cn
     * @date 2017/8/3
     * @params
     */
    @Bean(name = "dataSourceSlave1")
    @ConfigurationProperties(prefix = "datasource.read1")
    public DataSource readDataSourceOne() {
        logger.info("-------------------- readDataSourceOne init ---------------------");
        return DataSourceBuilder.create().type(dataSourceType).build();
    }

    @Bean(name = "dataSourceSlave2")
    @ConfigurationProperties(prefix = "datasource.read2")
    public DataSource readDataSourceTwo() {
        logger.info("-------------------- readDataSourceTwo init ---------------------");
        return DataSourceBuilder.create().type(dataSourceType).build();
    }

    @Bean(name = "dataSourceSlave3")
    @ConfigurationProperties(prefix = "datasource.read3")
    public DataSource readDataSourceThree() {
        logger.info("-------------------- readDataSourceThree init ---------------------");
        return DataSourceBuilder.create().type(dataSourceType).build();
    }

    /**
     * @Description 注入数据源
     * @author wangjie36@gome.com.cn
     * @date 2017/8/3
     * @params
     */
    @Bean
    public ShardDataSource shardDataSource(@Qualifier("dataSource") DataSource dataSource,
                                           @Qualifier("dataSourceSlave1") DataSource readDataSourceOne,
                                           @Qualifier("dataSourceSlave2") DataSource readDataSourceTwo,
                                           @Qualifier("dataSourceSlave3") DataSource readDataSourceThree) {

        Set<SlaveConfig> slaveDataSources = new LinkedHashSet<>();

        SlaveConfig slaveConfig1 = new SlaveConfig();
        slaveConfig1.setName("slave1");
        slaveConfig1.setWeight(5);
        slaveConfig1.setDataSources(readDataSourceOne);
        slaveConfig1.setInterval(3000L);
        slaveDataSources.add(slaveConfig1);

        SlaveConfig slaveConfig2 = new SlaveConfig();
        slaveConfig2.setName("slave2");
        slaveConfig2.setWeight(5);
        slaveConfig2.setDataSources(readDataSourceTwo);
        slaveConfig2.setInterval(3000L);
        slaveDataSources.add(slaveConfig2);

        SlaveConfig slaveConfig3 = new SlaveConfig();
        slaveConfig3.setName("slave3");
        slaveConfig3.setWeight(5);
        slaveConfig3.setDataSources(readDataSourceThree);
        slaveConfig3.setInterval(3000L);
        slaveDataSources.add(slaveConfig3);

        ShardDataSource shardDataSource = new ShardDataSource(dataSource, dataSource, slaveDataSources);
        return shardDataSource;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory(ShardDataSource ds) throws Exception {
        SqlSessionFactoryBean fb = new SqlSessionFactoryBean();
        fb.setDataSource(ds);// 指定数据源(这个必须有，否则报错)
        // 下边两句仅仅用于*.xml文件，如果整个持久层操作不需要使用到xml文件的话（只用注解就可以搞定），则不加
        fb.setTypeAliasesPackage(env.getProperty("mybatis.typeAliasesPackage"));// 指定基包
        fb.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(env.getProperty("mybatis.mapperLocations")));//
        return fb.getObject();
    }

    /**
     * @Description 注入aop
     * @author wangjie36@gome.com.cn
     * @date 2017/8/3
     * @params
     */
    @Bean
    public ShardDataSourceAspect shardDataSourceAspect() {
        ShardDataSourceAspect shardDataSourceAspect = new ShardDataSourceAspect();
        return shardDataSourceAspect;
    }

    /**
     * @Description 配置事务管理器的bean
     * @author wangjie36@gome.com.cn
     * @date 2017/8/3
     * @params
     */
    @Bean
    public DataSourceTransactionManager transactionManager(ShardDataSource dataSource) throws Exception {
        return new DataSourceTransactionManager(dataSource);
    }
}
