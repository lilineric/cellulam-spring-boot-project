package com.cellulam.spring.db.mybatis;

import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;

public class DefaultMybatisConfigurationCustomizer implements ConfigurationCustomizer {
    @Override
    public void customize(Configuration configuration) {
        this.registerTypeHandler(configuration);

        configuration.setCacheEnabled(true);
        configuration.setDefaultStatementTimeout(30);
        configuration.setMapUnderscoreToCamelCase(true);
        configuration.setJdbcTypeForNull(JdbcType.NULL);
    }

    private void registerTypeHandler(Configuration configuration) {

    }
}
