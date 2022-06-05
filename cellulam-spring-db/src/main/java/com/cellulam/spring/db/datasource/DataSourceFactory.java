package com.cellulam.spring.db.datasource;

import com.cellulam.spring.core.listeners.SmartEnvironment;
import com.cellulam.spring.db.exceptions.DbConfigurationException;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;

public class DataSourceFactory {
    public final static DataSource newInstance(String type, String dataSourceName) {
        switch (type) {
            case "hikari":
                return buildHikari(dataSourceName);
            default:
                throw new DbConfigurationException("data source " + type + " is unsupported");
        }
    }

    private static DataSource buildHikari(String dataSourceName) {
        HikariConfig hikariConfig = new HikariConfig();

        hikariConfig.setDriverClassName(getDataSourceConfig(dataSourceName, "driver-class-name"));
        hikariConfig.setJdbcUrl(getDataSourceConfig(dataSourceName, "jdbc-url"));
        hikariConfig.setUsername(getDataSourceConfig(dataSourceName, "username"));
        hikariConfig.setPassword(getDataSourceConfig(dataSourceName, "password"));
        hikariConfig.setReadOnly(getDataSourceConfig(dataSourceName, "read-only", Boolean.class, false));

        hikariConfig.setMinimumIdle(getDataSourceConfig(dataSourceName, "minimum-idle", Integer.class, 5));
        hikariConfig.setMaximumPoolSize(getDataSourceConfig(dataSourceName, "maximum-pool-size", Integer.class, 20));
        hikariConfig.setAutoCommit(getDataSourceConfig(dataSourceName, "auto-commit", Boolean.class, true));
        hikariConfig.setIdleTimeout(getDataSourceConfig(dataSourceName, "idle-timeout", Integer.class, 600000));
        hikariConfig.setMaxLifetime(getDataSourceConfig(dataSourceName, "max-lifetime", Integer.class, 1800000));
        hikariConfig.setConnectionTimeout(getDataSourceConfig(dataSourceName, "connection-timeout", Integer.class, 1000));
        hikariConfig.setConnectionTestQuery(getDataSourceConfig(dataSourceName, "connection-test-query", "select 1"));

        return new HikariDataSource(hikariConfig);
    }

    private static String getDataSourceConfig(String dataSourceName, String configKey) {
        return SmartEnvironment.env.getProperty(
                getKey(dataSourceName, configKey));
    }

    private static String getDataSourceConfig(String dataSourceName, String configKey, String defaultValue) {
        return SmartEnvironment.env.getProperty(
                getKey(dataSourceName, configKey), defaultValue);
    }


    private static <T> T getDataSourceConfig(String dataSourceName, String configKey, Class<T> tClass, T defaultValue) {
        return SmartEnvironment.env.getProperty(
                getKey(dataSourceName, configKey), tClass, defaultValue);
    }

    private static String getKey(String dataSourceName, String configKey) {
        return String.format("spring.datasource.%s.%s",
                dataSourceName,
                configKey);
    }
}
