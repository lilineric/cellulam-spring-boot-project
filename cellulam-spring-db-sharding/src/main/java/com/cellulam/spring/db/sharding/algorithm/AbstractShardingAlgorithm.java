package com.cellulam.spring.db.sharding.algorithm;

import com.cellulam.spring.core.listeners.SmartEnvironment;
import com.cellulam.spring.db.sharding.exceptions.ShardingKeyException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public abstract class AbstractShardingAlgorithm<T extends AlgorithmConfiguration> {
    private final static String REGEX = "(.*)\\$->\\{(.*)\\}(.*)";

    private final static Pattern regPattern = Pattern.compile(REGEX);


    protected T loadConfiguration(String logicTableName) {

        return AlgorithmCache.INSTANCE.computeIfAbsent(logicTableName, this.getClass(),
                key -> {
                    String expression = SmartEnvironment.env.getProperty(String.format(AlgorithmConstants.SHARDING_EXPRESSION_FORMAT, logicTableName));
                    if (StringUtils.isEmpty(expression)) {
                        throw new ShardingKeyException("The sharding-expression of table ["
                                + logicTableName + "] cannot be null");
                    }

                    Matcher matcher = regPattern.matcher(expression);
                    if (!matcher.find()) {
                        throw new ShardingKeyException("Failed to resolve expression: " + expression);
                    }

                    String text1 = matcher.group(1);
                    String exp = matcher.group(2);
                    String text2 = matcher.group(3);

                    String finalExpression = String.format("'%s' + (%s) + '%s'", text1, exp, text2);

                    log.info("Resolve logic table [{}] expression [{}], result: [{}]",
                            logicTableName, expression, finalExpression);

                    return this.buildConfiguration(logicTableName, finalExpression);
                });
    }

    protected abstract T buildConfiguration(String logicTableName, String expression);

}
