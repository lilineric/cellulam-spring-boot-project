package com.cellulam.spring.db.annotation;

import java.lang.annotation.*;

/**
 * Force indicate data source
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface TargetDataSource {
    String value();
}
