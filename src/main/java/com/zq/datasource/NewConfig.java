package com.zq.datasource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class NewConfig {
    @Qualifier("mySqlDataSource")
    @Autowired
    private DataSource dataSource1;

    @Qualifier("oracleDataSource")
    @Autowired
    private DataSource dataSource2;

    @Bean(name = "dynamicDataSource")
    @Primary
    public DynamicDataSource dataSource() throws SQLException {
        System.out.println("动态数据源加载开始");
        DataSource defaultDataSource = dataSource1;
        //Connection connection = defaultDataSource.getConnection();

        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put("admin", defaultDataSource);
        targetDataSources.put("haif", dataSource2);
        return new DynamicDataSource(defaultDataSource, targetDataSources);
    }
}
