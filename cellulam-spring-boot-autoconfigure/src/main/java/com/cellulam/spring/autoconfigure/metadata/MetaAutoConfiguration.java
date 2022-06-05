package com.cellulam.spring.autoconfigure.metadata;

import com.cellulam.metadata.DefaultMetadataContextInitializer;
import com.cellulam.metadata.MetadataContextInitializer;
import com.cellulam.metadata.worker.DbWorkerIdAssigner;
import com.cellulam.metadata.worker.WorkerIdAssigner;
import com.cellulam.spring.core.factories.YamlPropertySourceFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = {"classpath:${spring.profiles.active}/cellulam-metadata.yml"}, factory = YamlPropertySourceFactory.class)
@ConditionalOnProperty(prefix = "spring.cellulam.metadata", name = "enabled", havingValue = "true")
@EnableConfigurationProperties(MetadataProperties.class)
public class MetaAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public MetadataContextInitializer metaContextInitializer(MetadataProperties metadataProperties, WorkerIdAssigner workerIdAssigner) {
        String appName = metadataProperties.getAppName();
        String port = metadataProperties.getPort();
        String jdbcUrl = metadataProperties.getJdbcUrl();
        String username = metadataProperties.getJdbcUsername();
        String password = metadataProperties.getJdbcPassword();

        return new DefaultMetadataContextInitializer(com.cellulam.metadata.common.MetadataProperties.MetadataPropertiesBuilder
                .builder()
                .appName(appName)
                .port(port)
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
