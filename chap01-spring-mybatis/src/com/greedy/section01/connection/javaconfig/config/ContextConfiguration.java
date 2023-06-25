package com.greedy.section01.connection.javaconfig.config;

import org.apache.commons.dbcp.BasicDataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan("com.greedy.section01.connection.javaconfig")
@PropertySource("classpath:connection-info.properties")
public class ContextConfiguration {

    @Value("${oracle.dev.driver}")
    private String driver;

    @Value("${oracle.dev.url}")
    private String url;

    @Value("${oracle.dev.username}")
    private String username;

    @Value("${oracle.dev.password}")
    private String password;

    /**/
    @Bean(destroyMethod = "close")
    public BasicDataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
        dataSource.setUrl("jdbc:log4jdbc:oracle:thin:@localhost:1521:xe");
        dataSource.setUsername("C##EMPLOYEE");
        dataSource.setPassword("EMPLOYEE");
        dataSource.setDefaultAutoCommit(false);

        return dataSource;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory(ApplicationContext context) throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource());

        factoryBean.setConfigLocation(context.getResource("com/greedy/section01/connection/javaconfig/config/mybatis-config.xml"));

        return factoryBean.getObject();
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate(ApplicationContext context) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory(context));
    }

}
