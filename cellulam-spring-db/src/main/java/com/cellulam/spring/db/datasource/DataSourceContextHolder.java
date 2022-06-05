package com.cellulam.spring.db.datasource;

import com.cellulam.core.context.RuntimeContext;
import lombok.extern.slf4j.Slf4j;

import javax.sql.DataSource;

@Slf4j
public class DataSourceContextHolder {
    public final static String CURRENT_DATA_SOURCE_CONTEXT_KEY = "CURRENT_DATA_SOURCE";
    private static String DEFAULT_DATASOURCE_NAME = null;
    private static DataSource DEFAULT_DATASOURCE = null;

    private static boolean canSwitchDataSource = true;

    public static DataSource getDefaultDatasource() {
        return DEFAULT_DATASOURCE;
    }

    public static String getDefaultDatasourceName() {
        return DEFAULT_DATASOURCE_NAME;
    }

    public static void setDefaultDatasource(String dataSourceName, DataSource dataSource) {
        DEFAULT_DATASOURCE_NAME = dataSourceName;
        DEFAULT_DATASOURCE = dataSource;
    }

    private static final ThreadLocal<String> local = new ThreadLocal<>();

    public static ThreadLocal<String> getLocal() {
        return local;
    }

    public static String getDataSource() {
        return local.get();
    }

    public static void clear() {
        local.remove();
    }

    public static void switchDefaultDataSource() {
        switchDataSource(DEFAULT_DATASOURCE_NAME);
    }

    public static void switchDataSource(String dataSource) {
        if (isCanSwitchDataSource()) {
            setDataSource(dataSource);
        } else {
            log.debug("cannot switch the data source: " + dataSource);
        }
    }

    /**
     * force switch data source and set canSwitchDataSource to false
     *
     * @param dataSource
     */
    public static void forceSwitchDataSource(String dataSource) {
        canSwitchDataSource = false;
        setDataSource(dataSource);
        log.debug("Force to switch the data source: " + dataSource);
    }

    private static void setDataSource(String dataSource) {
        local.set(dataSource);
        RuntimeContext.put(CURRENT_DATA_SOURCE_CONTEXT_KEY, dataSource);
    }

    public static boolean isCanSwitchDataSource() {
        return canSwitchDataSource;
    }

    public static void resetCanSwitchDataSource() {
        canSwitchDataSource = true;
    }
}
