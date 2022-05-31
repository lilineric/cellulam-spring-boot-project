package com.cellulam.spring.db.conf;

import com.cellulam.spring.db.handlers.DateTimeTypeHandler;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;

import java.time.LocalDateTime;

public class CellulamConfigurationCustomizer implements ConfigurationCustomizer {
    @Override
    public void customize(Configuration configuration) {
        configuration.getTypeHandlerRegistry()
                .register(LocalDateTime.class, JdbcType.BIGINT,
                        new DateTimeTypeHandler());
        configuration.setCacheEnabled(true);
        configuration.setDefaultStatementTimeout(20);
        configuration.setMapUnderscoreToCamelCase(true);
        configuration.setJdbcTypeForNull(JdbcType.NULL);
    }
}
