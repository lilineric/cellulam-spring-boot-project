package com.cellulam.spring.autoconfigure.test;

import com.cellulam.metadata.MetadataContext;
import com.cellulam.spring.autoconfigure.test.conf.BaseUnitTest;
import org.junit.Test;

public class AppMetaContextTest extends BaseUnitTest {
    @Test
    public void testMetaContext() {
        System.out.println(MetadataContext.context.toString());
        System.out.println(MetadataContext.context.getSysTime());
        System.out.println(MetadataContext.context.getSysTimestamp());
    }
}
