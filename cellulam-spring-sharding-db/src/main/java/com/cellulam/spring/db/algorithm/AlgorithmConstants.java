package com.cellulam.spring.db.algorithm;

public class AlgorithmConstants {
    public final static String PREFIX_KEY = "spring.shardingsphere.sharding.tables";
    public final static String LAST_DIGITAL_FORMAT = PREFIX_KEY + ".%s.table-strategy.complex.last-digital";
    public final static String SHARDING_EXPRESSION_FORMAT = PREFIX_KEY + ".%s.table-strategy.complex.sharding-expression";

    public final static int DEFAULT_LAST_DIGITAL = 3;
}
