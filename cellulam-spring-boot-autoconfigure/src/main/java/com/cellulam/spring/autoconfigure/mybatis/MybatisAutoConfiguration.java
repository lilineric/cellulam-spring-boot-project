package com.cellulam.spring.autoconfigure.mybatis;

import com.cellulam.spring.autoconfigure.datasource.DataSourceAutoConfiguration;
import com.cellulam.spring.db.mybatis.configure.MybatisConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@AutoConfigureAfter(DataSourceAutoConfiguration.class)
@Import(MybatisConfiguration.class)
public class MybatisAutoConfiguration {

}

