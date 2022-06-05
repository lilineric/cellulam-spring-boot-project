package com.cellulam.spring.db.mybatis;

import com.cellulam.spring.db.DataSourceMybatisInterceptor;
import com.cellulam.spring.db.datasource.DataSourcePattern;
import org.apache.ibatis.plugin.Interceptor;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.boot.autoconfigure.SqlSessionFactoryBeanCustomizer;

import java.util.List;

public class DefaultMybatisSqlSessionFactoryBeanCustomizer implements SqlSessionFactoryBeanCustomizer {

    private int sqlTimeout;

    private List<DataSourcePattern> patterns;

    public DefaultMybatisSqlSessionFactoryBeanCustomizer(int sqlTimeout, List<DataSourcePattern> patterns) {
        this.sqlTimeout = sqlTimeout;
        this.patterns = patterns;
    }

    @Override
    public void customize(SqlSessionFactoryBean sessionFactoryBean) {

        sessionFactoryBean.setFailFast(true);
        sessionFactoryBean.setPlugins(new Interceptor[] {new DataSourceMybatisInterceptor(sqlTimeout, patterns)});

    }
}
