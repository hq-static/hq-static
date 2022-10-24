package com.dmc.conf;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * Created by Administrator on 2022/10/5 0005.
 */
@Configuration
@MapperScan(basePackages = {"com.dmc.mapper.hive"},sqlSessionFactoryRef = "sqlSessionFactory2")
public class HiveDataSourceConfig {
    @Value("${spring.datasource.hive.jdbc-url}")
    private String url;
    @Value("${spring.datasource.hive.driver-class-name}")
    private String driverClassName;
    @Value("${spring.datasource.hive.username}")
    private String username;
    @Value("${spring.datasource.hive.password}")
    private String password;

    @Bean(name = "hive")
    @ConfigurationProperties(prefix = "spring.datasource.hive")
    public DataSource dataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(url);
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Bean(name = "sqlSessionFactory2")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("hive") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/*.xml"));
        return bean.getObject();
    }

    @Bean(name = "transactionManager2")
    public DataSourceTransactionManager transactionManager(@Qualifier("hive") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "sqlSessionTemplate2")
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("sqlSessionFactory2") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
