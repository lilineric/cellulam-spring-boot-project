package com.cellulam.spring.db.test;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = "com.cellulam.spring.db")
@SpringBootConfiguration
@EnableAutoConfiguration
public class TestConf {
}
