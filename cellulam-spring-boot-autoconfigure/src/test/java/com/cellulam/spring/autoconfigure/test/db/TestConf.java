package com.cellulam.spring.autoconfigure.test.db;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.cellulam.spring.autoconfigure.test.db")
@MapperScan("com.cellulam.spring.autoconfigure.test.db.dal")
@EnableAutoConfiguration
public class TestConf {
}
