package com.cellulam.spring.db.conf;

import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.mybatis.spring.boot.autoconfigure.SqlSessionFactoryBeanCustomizer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement(order = Ordered.HIGHEST_PRECEDENCE + 100)
@Order(value = Ordered.HIGHEST_PRECEDENCE + 100)
public class MybatisConfiguration implements InitializingBean {

    private static final Logger LOGGER = LoggerFactory.getLogger(MybatisConfiguration.class);


    @Bean
    public ConfigurationCustomizer configurationCustomizer() {
        return new CellulamConfigurationCustomizer();
    }

    @Bean
    public SqlSessionFactoryBeanCustomizer sqlSessionFactoryBeanCustomizer() {
        return new CellulamSqlSessionFactoryBeanCustomizer();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
    }
}

