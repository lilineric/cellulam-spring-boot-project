package com.cellulam.spring.meta.conf;

import com.cellulam.metadata.DefaultMetadataContextInitializer;
import com.cellulam.metadata.MetadataContextInitializer;
import com.cellulam.metadata.common.MetadataProperties;
import com.cellulam.metadata.worker.DbWorkerIdAssigner;
import com.cellulam.metadata.worker.WorkerIdAssigner;
import com.cellulam.spring.core.factories.YamlPropertySourceFactory;
import com.cellulam.spring.core.listeners.SmartEnvironment;
import com.cellulam.spring.meta.common.Constants;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

@Configuration
@ComponentScan("com.cellulam.spring.meta")
@Order(value = Ordered.HIGHEST_PRECEDENCE + 200)
@PropertySource(value = {"classpath:${spring.profiles.active}/cellulam-metadata.yml"}, ignoreResourceNotFound = true, factory = YamlPropertySourceFactory.class)
public class MetaAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public MetadataContextInitializer metaContextInitializer(WorkerIdAssigner workerIdAssigner) {
        String appName = SmartEnvironment.env.getProperty(Constants.APP_NAME);
        String port = SmartEnvironment.env.getProperty(Constants.SERVER_PORT);
        String jdbcUrl = SmartEnvironment.env.getProperty(Constants.JDBC_URL);
        String username = SmartEnvironment.env.getProperty(Constants.JDBC_USERNAME);
        String password = SmartEnvironment.env.getProperty(Constants.JDBC_PASSWORD);

        return new DefaultMetadataContextInitializer(MetadataProperties.MetadataPropertiesBuilder
                .builder()
                .appName(StringUtils.isEmpty(appName) ? SmartEnvironment.env.getProperty("spring.application.name") : appName)
                .port(StringUtils.isEmpty(port) ? SmartEnvironment.env.getProperty("server.port") : port)
                .jdbcUrl(jdbcUrl)
                .jdbcUsername(username)
                .jdbcPassword(password)
                .build(),
                workerIdAssigner);
    }

    @Bean
    @ConditionalOnMissingBean
    public WorkerIdAssigner workerIdAssigner() {
        return new DbWorkerIdAssigner();
    }
}
