package com.cellulam.spring.core;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;

/**
 *
 * @author eric.li
 */
public interface BeanDefinitionRegistryAction {

    void apply(BeanDefinitionRegistry registry);

}
