package com.cellulam.spring.db.datasource.configure;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "spring.datasource")
public class DataSourceProperties {
    private String names;
    private String type = "hikari";

}
