package com.cellulam.spring.db.test;

import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = {TestConf.class})
@RunWith(SpringRunner.class)
public abstract class BaseUnitTest {
}
