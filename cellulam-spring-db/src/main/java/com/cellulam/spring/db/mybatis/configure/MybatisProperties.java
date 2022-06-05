package com.cellulam.spring.db.mybatis.configure;

import com.cellulam.spring.db.datasource.DataSourcePattern;
import com.google.common.collect.Lists;
import lombok.Data;
import org.apache.ibatis.session.ExecutorType;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@ConfigurationProperties(prefix = "spring.cellulam.sql")
@Data
public class MybatisProperties {
    private int sqlTimeout = 3000;
    private List<DataSourcePattern> datasourcePatterns = Lists.newArrayList();

    /**
     * Location of MyBatis xml config file.
     */
    private String configLocation;


    /**
     * Locations of MyBatis mapper files.
     */
    private String mapperLocations;

    /**
     * Packages to search type aliases. (Package delimiters are ",; \t\n")
     */
    private String typeAliasesPackage;


    /**
     * Execution mode for {@link org.mybatis.spring.SqlSessionTemplate}.
     */
    private ExecutorType executorType;

}
