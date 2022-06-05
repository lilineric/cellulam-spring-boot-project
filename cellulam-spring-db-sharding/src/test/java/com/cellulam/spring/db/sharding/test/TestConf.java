package com.cellulam.spring.db.sharding.test;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = "com.cellulam.spring.db.sharding.test")
@MapperScan("com.cellulam.spring.db.sharding.test.dal")
@SpringBootConfiguration
@EnableAutoConfiguration
public class TestConf {
}
