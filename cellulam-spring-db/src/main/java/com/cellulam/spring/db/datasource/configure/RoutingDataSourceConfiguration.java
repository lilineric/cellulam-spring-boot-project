package com.cellulam.spring.db.datasource.configure;

import com.cellulam.spring.db.datasource.DataSourceContextHolder;
import com.cellulam.spring.db.datasource.DataSourceFactory;
import com.cellulam.spring.db.datasource.DataSourceSwitchIntercept;
import com.cellulam.spring.db.datasource.DefaultRoutingDataSource;
import com.cellulam.spring.db.exceptions.DbConfigurationException;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.Map;

@Configuration
@EnableConfigurationProperties(DataSourceProperties.class)
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class RoutingDataSourceConfiguration {

    private DataSource defaultDataSource;

    private DataSourceProperties properties;

    public RoutingDataSourceConfiguration(DataSourceProperties properties) {
        this.properties = properties;
    }

    /**
     * routing data source
     *
     * @return
     */
    @Bean
    @ConditionalOnMissingBean
    public DataSource routingDataSource() {
        Map<Object, Object> dataSources = buildDataSources();
        return new DefaultRoutingDataSource(defaultDataSource, dataSources);
    }

    private Map<Object, Object> buildDataSources() {
        String dataSourceNames = properties.getNames();
        if (StringUtils.isEmpty(dataSourceNames)) {
            throw new DbConfigurationException("Data source names cannot be null");
        }

        String[] names = dataSourceNames.split(",");

        Map<Object, Object> dataSources = Maps.newHashMap();
        for (String name : names) {
            name = name.trim();
            DataSource dataSource = DataSourceFactory.newInstance(properties.getType(), name);
            dataSources.put(name, dataSource);
            if (defaultDataSource == null) {
                defaultDataSource = dataSource;
                DataSourceContextHolder.setDefaultDatasource(name, dataSource);
            }
        }
        return dataSources;
    }

    @Bean
    public DataSourceSwitchIntercept dataSourceIntercept() {
        return new DataSourceSwitchIntercept();
    }

    @Bean
    @ConditionalOnMissingBean
    public PlatformTransactionManager annotationDrivenTransactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
}
