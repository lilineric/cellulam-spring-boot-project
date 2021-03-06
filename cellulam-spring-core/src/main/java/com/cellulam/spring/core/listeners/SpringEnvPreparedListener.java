package com.cellulam.spring.core.listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.Ordered;

/**
 * spring env-prepared 事件监听
 * @author eric.li
 *
 */
public class SpringEnvPreparedListener implements ApplicationListener<ApplicationEnvironmentPreparedEvent>, Ordered {

    private static final Logger logger = LoggerFactory.getLogger(SpringEnvPreparedListener.class);

    @Override
    public void onApplicationEvent(ApplicationEnvironmentPreparedEvent event) {
        logger.info("SpringEnvPreparedListener");
        SmartEnvironment.initOrResetEnvironment(event.getEnvironment());
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE + 100;
    }

}
