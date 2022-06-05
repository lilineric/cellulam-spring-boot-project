package com.cellulam.spring.autoconfigure.uid;

import com.cellulam.metadata.MetadataContextInitializer;
import com.cellulam.spring.autoconfigure.metadata.MetaAutoConfiguration;
import com.cellulam.spring.core.exceptions.PropertiesConfigureException;
import com.cellulam.uid.UidGenerator;
import com.cellulam.uid.metadata.MetadataAppendUidGenerator;
import com.cellulam.uid.metadata.UidAppendGenerator;
import com.cellulam.uid.snowflake.SnowflakeConfig;
import com.cellulam.uid.snowflake.SnowflakeGenerator;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(prefix = "spring.cellulam.uid", name = "enabled", havingValue = "true", matchIfMissing = true)
@EnableConfigurationProperties(UidProperties.class)
@AutoConfigureAfter(MetaAutoConfiguration.class)
public class UidAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnBean(MetadataContextInitializer.class)
    public UidAppendGenerator uidAppendGenerator(UidProperties uidProperties) {
        return new MetadataAppendUidGenerator(uidProperties.getEpoch(), uidProperties.getPidSubDigits());
    }

    @Bean
    @ConditionalOnMissingBean(value = {UidAppendGenerator.class, UidGenerator.class})
    public UidGenerator uidGenerator(UidProperties uidProperties) {
        if (uidProperties.getWorkerId() == null) {
            throw new PropertiesConfigureException("Failed to create bean uidGenerator: workerId cannot be null");
        }
        return new SnowflakeGenerator(SnowflakeConfig.Builder
                .builder(uidProperties.getWorkerId())
                .epoch(uidProperties.getEpoch())
                .build());
    }
}
