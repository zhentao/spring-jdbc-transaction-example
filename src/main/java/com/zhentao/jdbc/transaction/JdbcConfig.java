package com.zhentao.jdbc.transaction;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class JdbcConfig {
    @Value("${url}")
    private String url;

    @Value("${driverClassName}")
    private String driverClassName;

    @Value("${dbUsername}")
    private String dbUsername;

    @Value("${dbPassword}")
    private String dbPassword;

    @Value("${jmxEnabled}")
    private boolean jmxEnabled;

    @Value("${testWhileIdle}")
    private boolean testWhileIdle;

    @Value("${testOnBorrow}")
    private boolean testOnBorrow;

    @Value("${validationQuery}")
    private String validationQuery;

    @Value("${testOnReturn}")
    private boolean testOnReturn;

    @Value("${validationInterval}")
    private long validationInterval;

    @Value("${timeBetweenEvictionRunsMillis}")
    private int timeBetweenEvictionRunsMillis;

    @Value("${maxActive}")
    private int maxActive;

    @Value("${initialSize}")
    private int initialSize;

    @Value("${maxWait}")
    private int maxWait;

    @Value("${removeAbandonedTimeout}")
    private int removeAbandonedTimeout;

    @Value("${minEvictableIdleTimeMillis}")
    private int minEvictableIdleTimeMillis;

    @Value("${minIdle}")
    private int minIdle;

    @Value("${maxIdle}")
    private int maxIdle;

    @Value("${logAbandoned}")
    private boolean logAbandoned;

    @Value("${removeAbandoned}")
    private boolean removeAbandoned;

    @Value("${jdbcInterceptors}")
    private String jdbcInterceptors;

    @Bean
    public static PropertySourcesPlaceholderConfigurer placeholderConfigurer() {
        PropertySourcesPlaceholderConfigurer config = new PropertySourcesPlaceholderConfigurer();
        config.setLocations(new Resource[] { new ClassPathResource("datasource.properties") });
        config.setIgnoreResourceNotFound(true);
        return config;
    }

    @Bean
    public DataSource dataSource() {
        PoolProperties p = new PoolProperties();
        p.setUrl(url);
        p.setDriverClassName(driverClassName);
        p.setUsername(dbUsername);
        p.setPassword(dbPassword);
        p.setJmxEnabled(jmxEnabled);
        p.setTestWhileIdle(testWhileIdle);
        p.setTestOnBorrow(testOnBorrow);
        p.setValidationQuery(validationQuery);
        p.setTestOnReturn(testOnReturn);
        p.setValidationInterval(validationInterval);
        p.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        p.setMaxActive(maxActive);
        p.setInitialSize(initialSize);
        p.setMaxWait(maxWait);
        p.setRemoveAbandonedTimeout(removeAbandonedTimeout);
        p.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        p.setMinIdle(minIdle);
        p.setMaxIdle(maxIdle);
        p.setLogAbandoned(logAbandoned);
        p.setRemoveAbandoned(removeAbandoned);
        p.setJdbcInterceptors(jdbcInterceptors);

        DataSource dataSource = new DataSource();
        dataSource.setPoolProperties(p);
        return dataSource;
    }

    @Bean
    public PlatformTransactionManager txManager() {
        return new DataSourceTransactionManager(dataSource());
    }

    @Bean
    public MyDao myDao() {
        MyDaoJdbc dao = new MyDaoJdbc();
        dao.setDataSource(dataSource());
        return dao;
    }

}
