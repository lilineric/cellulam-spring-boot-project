package com.cellulam.spring.autoconfigure.datasource;

import com.cellulam.spring.db.datasource.configure.RoutingDataSourceConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
public class DataSourceAutoConfiguration {
    @Configuration
    @Import(RoutingDataSourceConfiguration.class)
    @ConditionalOnProperty(prefix = "spring.cellulam.routing-datasource", name = "enabled", havingValue = "true", matchIfMissing = true)
    protected static class RoutingDataSourceAutoConfiguration {
    }
}
