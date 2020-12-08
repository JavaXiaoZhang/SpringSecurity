package com.zq.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
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

    //配置动态数据源
    @Bean("oracleDataSource")
    @ConfigurationProperties("spring.datasource2")
    public DataSource haifDataSource() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean("mySqlDataSource")
    //@ConfigurationProperties(prefix = "spring.datasource1")
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
    }
}
