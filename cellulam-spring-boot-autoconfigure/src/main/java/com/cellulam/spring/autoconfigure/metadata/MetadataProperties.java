package com.cellulam.spring.autoconfigure.metadata;

import com.cellulam.spring.core.listeners.SmartEnvironment;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "cellulam.metadata")
public class MetadataProperties {
    private String jdbcUrl;
    private String jdbcUsername;
    private String jdbcPassword;
    private String appName;
    private String port;

    public String getAppName() {
        return StringUtils.isEmpty(this.appName) ? SmartEnvironment.env.getProperty("spring.application.name") : this.appName;
    }

    public String getPort() {
        return StringUtils.isEmpty(this.port) ? SmartEnvironment.env.getProperty("server.port") : this.port;
    }
}
