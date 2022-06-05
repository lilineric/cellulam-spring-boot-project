package com.cellulam.spring.db.mybatis.configure;

import com.cellulam.spring.db.mybatis.DefaultMybatisConfigurationCustomizer;
import com.cellulam.spring.db.mybatis.DefaultMybatisSqlSessionFactoryBeanCustomizer;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.mybatis.spring.boot.autoconfigure.SqlSessionFactoryBeanCustomizer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;
import java.util.List;

@Configuration
@EnableConfigurationProperties({MybatisProperties.class})
@Slf4j
public class MybatisConfiguration {

    private MybatisProperties properties;
    private final ResourceLoader resourceLoader;

    private List<ConfigurationCustomizer> configurationCustomizers;

    private List<SqlSessionFactoryBeanCustomizer> sqlSessionFactoryBeanCustomizers;


    public MybatisConfiguration(MybatisProperties properties,
                                ResourceLoader resourceLoader) {
        this.properties = properties;
        this.resourceLoader = resourceLoader;

        this.configurationCustomizers = Lists.newArrayList(new DefaultMybatisConfigurationCustomizer());
        this.sqlSessionFactoryBeanCustomizers = Lists.newArrayList(
                new DefaultMybatisSqlSessionFactoryBeanCustomizer(properties.getSqlTimeout(), properties.getDatasourcePatterns())
        );
    }

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnBean(DataSource.class)
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource);

        sessionFactoryBean.setTypeAliasesPackage(properties.getTypeAliasesPackage());
        sessionFactoryBean.setFailFast(true);

        Resource[] resources = new PathMatchingResourcePatternResolver().getResources(properties.getMapperLocations());
        sessionFactoryBean.setMapperLocations(resources);
        if (StringUtils.hasText(this.properties.getConfigLocation())) {
            sessionFactoryBean.setConfigLocation(this.resourceLoader.getResource(this.properties.getConfigLocation()));
        }
        applyConfiguration(sessionFactoryBean);

        applySqlSessionFactoryBeanCustomizers(sessionFactoryBean);
        return sessionFactoryBean.getObject();
    }

    private void applyConfiguration(SqlSessionFactoryBean factory) {
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        if (!CollectionUtils.isEmpty(this.configurationCustomizers)) {
            for (ConfigurationCustomizer customizer : this.configurationCustomizers) {
                customizer.customize(configuration);
            }
        }
        factory.setConfiguration(configuration);
    }

    private void applySqlSessionFactoryBeanCustomizers(SqlSessionFactoryBean factory) {
        if (!CollectionUtils.isEmpty(this.sqlSessionFactoryBeanCustomizers)) {
            for (SqlSessionFactoryBeanCustomizer customizer : this.sqlSessionFactoryBeanCustomizers) {
                customizer.customize(factory);
            }
        }
    }

    @Bean
    @ConditionalOnMissingBean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        ExecutorType executorType = this.properties.getExecutorType();
        if (executorType != null) {
            return new SqlSessionTemplate(sqlSessionFactory, executorType);
        } else {
            return new SqlSessionTemplate(sqlSessionFactory);
        }
    }

}

