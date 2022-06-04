package com.cellulam.spring.db.test;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = "com.cellulam.spring.db.test")
@MapperScan("com.cellulam.spring.db.test.dal")
@SpringBootConfiguration
@EnableAutoConfiguration
public class TestConf {
}
