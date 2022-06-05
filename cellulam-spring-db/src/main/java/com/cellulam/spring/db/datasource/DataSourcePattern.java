package com.cellulam.spring.db.datasource;

import lombok.Data;

import java.util.regex.Pattern;

@Data
public class DataSourcePattern {
    private Pattern pattern;
    private String datasource;
}
