package com.zq.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class SystemConfig {
    @Autowired
    private TestConfig testConfig;

    /*@Bean("mySqlDataSource")
    public DataSource mysqlDataSource() throws SQLException {
        DruidDataSource druidDataSource = DruidDataSourceBuilder.create().build();
        druidDataSource.setUsername(testConfig.getUsername());
        druidDataSource.setPassword(testConfig.getPassword());
        druidDataSource.setUrl(testConfig.getUrl());
        druidDataSource.setDriverClassName(testConfig.getDriverClassName());
        druidDataSource.setDbType(testConfig.getType());
        druidDataSource.setInitialSize(Integer.valueOf(testConfig.getInitialSize()));
        druidDataSource.setFilters(testConfig.getFilters());
        druidDataSource.setMaxPoolPreparedStatementPerConnectionSize(Integer.valueOf(testConfig.getMaxPoolPreparedStatementPerConnectionSize()));
        druidDataSource.setUseGlobalDataSourceStat(Boolean.valueOf(testConfig.getUseGlobalDataSourceStat()));
        druidDataSource.setConnectionProperties(testConfig.getConnectionProperties());
        return druidDataSource;
    }*/

    @Bean(name = "dynamicDataSource")
    @Primary
    public DynamicDataSource dataSource() throws SQLException {
        System.out.println("动态数据源加载开始");
        DruidDataSource druidDataSource = DruidDataSourceBuilder.create().build();
        druidDataSource.setUsername(testConfig.getUsername());
        druidDataSource.setPassword(testConfig.getPassword());
        druidDataSource.setUrl(testConfig.getUrl());
        druidDataSource.setDriverClassName(testConfig.getDriverClassName());
        druidDataSource.setDbType(testConfig.getType());
        druidDataSource.setInitialSize(Integer.valueOf(testConfig.getInitialSize()));
        druidDataSource.setFilters(testConfig.getFilters());
        druidDataSource.setMaxPoolPreparedStatementPerConnectionSize(Integer.valueOf(testConfig.getMaxPoolPreparedStatementPerConnectionSize()));
        druidDataSource.setUseGlobalDataSourceStat(Boolean.valueOf(testConfig.getUseGlobalDataSourceStat()));
        druidDataSource.setConnectionProperties(testConfig.getConnectionProperties());
        //Connection connection = defaultDataSource.getConnection();

        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put("admin", druidDataSource);
        DruidDataSource druidDataSource2 = DruidDataSourceBuilder.create().build();
        BeanUtils.copyProperties(druidDataSource, druidDataSource2);
        String url = druidDataSource2.getUrl();
        String replace = url.replace("springsecurity", "db2");
        druidDataSource2.setUrl(replace);
        targetDataSources.put("haif", druidDataSource2);
        return new DynamicDataSource(druidDataSource, targetDataSources);
    }
}
