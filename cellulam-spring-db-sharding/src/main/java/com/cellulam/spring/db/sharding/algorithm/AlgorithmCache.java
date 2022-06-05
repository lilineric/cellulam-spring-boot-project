package com.cellulam.spring.db.sharding.algorithm;

import com.google.common.collect.Maps;

import java.util.Map;
import java.util.function.Function;

public class AlgorithmCache {
    private static final Map<String, AlgorithmConfiguration> algorithmsMap = Maps.newConcurrentMap();

    private AlgorithmCache() {
    }

    public final static AlgorithmCache INSTANCE = new AlgorithmCache();

    public <T extends AlgorithmConfiguration> T computeIfAbsent(String logicTableName, Class<?> tClass, Function<String, T> mappingFunction) {
        String key = tClass.getName() + "#" + logicTableName;
        return (T)algorithmsMap.computeIfAbsent(key, mappingFunction);
    }
}
