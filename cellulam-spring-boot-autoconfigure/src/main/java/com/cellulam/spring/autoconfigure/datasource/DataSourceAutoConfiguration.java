package com.cellulam.spring.autoconfigure.datasource;

import com.cellulam.spring.db.datasource.configure.RoutingDataSourceConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
public class DataSourceAutoConfiguration {
    @Configuration
    @Import(RoutingDataSourceConfiguration.class)
    protected static class RoutingDataSourceAutoConfiguration {
    }
}
