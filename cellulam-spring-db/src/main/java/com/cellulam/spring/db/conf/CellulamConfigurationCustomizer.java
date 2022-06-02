package com.cellulam.spring.db.conf;

import com.cellulam.spring.db.handlers.DateTimeTypeHandler;
import com.cellulam.spring.db.handlers.LocalDatetimeTypeHandler;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;

import java.time.LocalDateTime;

public class CellulamConfigurationCustomizer implements ConfigurationCustomizer {
    @Override
    public void customize(Configuration configuration) {
        this.registerTypeHandler(configuration);

        configuration.setCacheEnabled(true);
        configuration.setDefaultStatementTimeout(20);
        configuration.setMapUnderscoreToCamelCase(true);
        configuration.setJdbcTypeForNull(JdbcType.NULL);
    }

    private void registerTypeHandler(Configuration configuration) {
        TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();

        typeHandlerRegistry.register(LocalDateTime.class, JdbcType.BIGINT,
                new DateTimeTypeHandler());
        typeHandlerRegistry.register(LocalDateTime.class, JdbcType.TIMESTAMP,
                new LocalDatetimeTypeHandler());

    }
}
