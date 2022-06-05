package com.cellulam.spring.db.sharding.algorithm;

import com.cellulam.core.utils.JacksonUtils;
import com.cellulam.script.executors.GlobalContextExecutorFactory;
import com.cellulam.spring.core.listeners.SmartEnvironment;
import com.cellulam.spring.db.sharding.exceptions.ShardingKeyException;
import com.google.common.collect.Lists;
import lombok.Builder;
import lombok.Data;
import org.apache.shardingsphere.api.sharding.complex.ComplexKeysShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.complex.ComplexKeysShardingValue;

import java.util.Collection;
import java.util.Map;

/**
 * Mod the last digital of the multiple sharding keys and route to the same slice.
 * Must be ensured that the last digital of the sharding keys are the same.
 */
public class MultiKeyLastDigitalShardingAlgorithm
        extends AbstractShardingAlgorithm<MultiKeyLastDigitalShardingAlgorithm.Configuration>
        implements ComplexKeysShardingAlgorithm<Long> {

    @Override
    public Collection<String> doSharding(Collection<String> availableTargetNames, ComplexKeysShardingValue<Long> shardingValue) {
        Configuration configuration = this.loadConfiguration(shardingValue.getLogicTableName());

        long lastDigital = this.getLastDigital(shardingValue.getColumnNameAndShardingValuesMap(), configuration);
        return Lists.newArrayList(this.buildNodeName(lastDigital, configuration));
    }

    private String buildNodeName(long lastDigital, Configuration configuration) {
        return GlobalContextExecutorFactory.getGroovyInstance()
                .invoke(configuration.getFuncName(), String.class, lastDigital);

    }

    private long getLastDigital(Map<String, Collection<Long>> shardingValue, Configuration configuration) {

        Long lastDigital = null;
        for (Collection<Long> values : shardingValue.values()) {
            for (Long value : values) {
                long digital = value % configuration.getModValue();
                if (lastDigital != null && lastDigital != digital) {
                    throw new ShardingKeyException("The last digital must be the same in MultiKeyLastDigitalShardingAlgorithm. shardingValue: "
                            + JacksonUtils.toJson(shardingValue));
                }
                lastDigital = digital;
            }
        }
        if (lastDigital == null) {
            throw new ShardingKeyException("Last digital is null. shardingValue: " + JacksonUtils.toJson(shardingValue));
        }
        return lastDigital;
    }

    @Override
    protected Configuration buildConfiguration(String logicTableName, String expression) {
        int lastDigital = SmartEnvironment.env.getProperty(String.format(AlgorithmConstants.LAST_DIGITAL_FORMAT, logicTableName),
                Integer.class,
                AlgorithmConstants.DEFAULT_LAST_DIGITAL);

        String funcName = GlobalContextExecutorFactory.getGroovyInstance()
                .addExpression("sharding", expression, "lastDigital");

        return Configuration.builder()
                .funcName(funcName)
                .modValue((int) Math.pow(10, lastDigital))
                .build();
    }

    @Data
    @Builder
    public final static class Configuration implements AlgorithmConfiguration {
        private int modValue;

        private String funcName;
    }
}
