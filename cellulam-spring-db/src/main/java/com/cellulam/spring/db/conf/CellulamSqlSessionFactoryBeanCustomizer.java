package com.cellulam.spring.db.conf;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.boot.autoconfigure.SqlSessionFactoryBeanCustomizer;

public class CellulamSqlSessionFactoryBeanCustomizer implements SqlSessionFactoryBeanCustomizer {

    @Override
    public void customize(SqlSessionFactoryBean sessionFactoryBean) {

        sessionFactoryBean.setFailFast(true);

    }
}
