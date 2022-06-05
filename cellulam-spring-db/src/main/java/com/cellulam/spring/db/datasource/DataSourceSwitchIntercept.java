package com.cellulam.spring.db.datasource;

import com.cellulam.core.exceptions.DbException;
import com.cellulam.spring.db.annotation.TargetDataSource;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.Ordered;
import org.springframework.core.PriorityOrdered;

@Aspect
public class DataSourceSwitchIntercept implements PriorityOrdered {

    @Around("@annotation(com.cellulam.spring.db.annotation.TargetDataSource)")
    public Object switchDataSource(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
        TargetDataSource targetDataSource = signature.getMethod().getAnnotation(TargetDataSource.class);
        if (StringUtils.isEmpty(targetDataSource.value())) {
            throw new DbException("Must indicate the data source name. Method signature: " + signature.getName());
        }
        try {
            DataSourceContextHolder.forceSwitchDataSource(targetDataSource.value());
            return proceedingJoinPoint.proceed();
        } finally {
            DataSourceContextHolder.resetCanSwitchDataSource();
        }
    }


    /**
     * Must be executed prior to transaction execution
     *
     * @EnableTransactionManagement(order > Intercept order)
     */
    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE + 10;
    }
}
