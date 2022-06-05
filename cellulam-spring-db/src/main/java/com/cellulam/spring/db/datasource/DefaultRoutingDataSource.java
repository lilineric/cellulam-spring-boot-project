package com.cellulam.spring.db.datasource;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.Map;

@Slf4j
public class DefaultRoutingDataSource extends AbstractRoutingDataSource {
    public DefaultRoutingDataSource(DataSource defaultDataSource, Map<Object, Object> targetDataSource) {
        this.setDefaultTargetDataSource(defaultDataSource);
        this.setTargetDataSources(targetDataSource);
    }

    @Override
    protected Object determineCurrentLookupKey() {
        String typeKey = DataSourceContextHolder.getDataSource();
        log.debug("Switch data source: {}", typeKey);
        return typeKey;
    }
}
