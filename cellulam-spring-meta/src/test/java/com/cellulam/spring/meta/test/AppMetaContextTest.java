package com.cellulam.spring.meta.test;

import com.cellulam.metadata.MetadataContext;
import org.junit.Test;

public class AppMetaContextTest extends BaseUnitTest{
    @Test
    public void testMetaContext() {
        System.out.println(MetadataContext.context.toString());
        System.out.println(MetadataContext.context.getSys);
    }
}
