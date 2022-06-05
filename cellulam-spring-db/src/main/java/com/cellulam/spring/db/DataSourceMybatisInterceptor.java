package com.cellulam.spring.db;


import com.cellulam.core.context.RuntimeContext;
import com.cellulam.core.utils.JacksonUtils;
import com.cellulam.spring.db.datasource.DataSourceContextHolder;
import com.cellulam.spring.db.datasource.DataSourcePattern;
import com.google.common.base.Stopwatch;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

@Intercepts({@Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class}),
        @Signature(type = Executor.class, method = "query",
                args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class,
                        CacheKey.class, BoundSql.class}),
        @Signature(type = Executor.class,
                method = "query",
                args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class})})
@Slf4j
public class DataSourceMybatisInterceptor implements Interceptor {

    private int sqlTimeout;


    private List<DataSourcePattern> dataSourcePattern;

    /**
     * data context, key:sqlId, value:DataSource
     */
    Map<String, String> dataSourceMap = Maps.newConcurrentMap();

    public DataSourceMybatisInterceptor(int sqlTimeout, List<DataSourcePattern> dataSourcePattern) {
        this.sqlTimeout = sqlTimeout;

        this.dataSourcePattern = dataSourcePattern;
    }

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Stopwatch sp = Stopwatch.createStarted();

        final Object[] args = invocation.getArgs();
        MappedStatement mappedStatement = (MappedStatement) args[0];
        String sqlId = mappedStatement.getId();

        String dataSource = this.setAndGetDataSource(sqlId);
        Object obj;
        try {
            obj = invocation.proceed();
        } catch (Exception e) {
            this.handleException(sqlId, args, e);
            throw e;
        }

        long useTime = sp.stop().elapsed(TimeUnit.MILLISECONDS);
        if (useTime > sqlTimeout) {
            Object parameterObject = args[1];
            BoundSql boundSql = mappedStatement.getBoundSql(parameterObject);

            Object[] params = Arrays.copyOfRange(args, 1, args.length);
            String sqlStr = String.valueOf(boundSql.getSql().replaceAll("\\s+", " ").replace("\n", ""));

            log.warn("Slow SQL, dataSource:{}, sqlId:{}, cost time:{}ms, sql:{}, params: {}",
                    dataSource,
                    sqlId,
                    String.valueOf(useTime),
                    sqlStr,
                    JacksonUtils.toJson(params));
        }
        return obj;
    }

    private void handleException(String sqlId, Object[] args, Exception e) {
        log.error("Failed to execute SQL {}, context: {}", sqlId, RuntimeContext.getDigest());
    }

    private String setAndGetDataSource(String sqlId) {
        String dataSource = dataSourceMap.get(sqlId);

        if (StringUtils.isEmpty(dataSource)) {
            dataSource = this.getDataSource(sqlId);
            dataSourceMap.put(sqlId, dataSource);
        }
        DataSourceContextHolder.switchDataSource(dataSource);
        return dataSource;
    }

    private String getDataSource(String sqlId) {
        for (DataSourcePattern pattern : this.dataSourcePattern) {
            if (pattern.getPattern().matcher(sqlId).matches()) {
                return pattern.getDatasource();
            }
        }
        return DataSourceContextHolder.getDefaultDatasourceName();
    }

    @Override
    public Object plugin(Object o) {
        return Plugin.wrap(o, this);
    }

    @Override
    public void setProperties(Properties properties) {
    }
}
