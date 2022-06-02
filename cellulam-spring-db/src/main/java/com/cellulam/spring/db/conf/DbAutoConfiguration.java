package com.cellulam.spring.db.conf;

import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.mybatis.spring.boot.autoconfigure.SqlSessionFactoryBeanCustomizer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement(order = Ordered.HIGHEST_PRECEDENCE + 100)
@MapperScan("com.cellulam.spring.db.dal")
@Order(value = Ordered.HIGHEST_PRECEDENCE + 100)
public class DbAutoConfiguration implements InitializingBean {

    private static final Logger LOGGER = LoggerFactory.getLogger(DbAutoConfiguration.class);


    @Bean
    @ConditionalOnMissingBean
    public ConfigurationCustomizer configurationCustomizer() {
        return new CellulamConfigurationCustomizer();
    }

    @Bean
    @ConditionalOnMissingBean
    public SqlSessionFactoryBeanCustomizer sqlSessionFactoryBeanCustomizer() {
        return new CellulamSqlSessionFactoryBeanCustomizer();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
    }
}

