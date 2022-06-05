package com.cellulam.spring.autoconfigure.test;

import com.cellulam.spring.autoconfigure.test.conf.BaseUnitTest;
import com.cellulam.uid.UidGenerator;
import com.cellulam.uid.metadata.UidAppendGenerator;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class UidTest extends BaseUnitTest {
    @Autowired
    private UidAppendGenerator uidAppendGenerator;

    @Autowired
    private UidGenerator uidGenerator;

    @Test
    public void testUid() {
        long uid1 = uidAppendGenerator.nextId();
        long uid2 = uidAppendGenerator.nextId(uid1);
        System.out.println(uid1);
        System.out.println(uid2);
        Assert.assertEquals(uid1 % 1000, uid2 % 1000);
    }

    @Test
    public void testUid2() {
        System.out.println(uidGenerator.nextId());
    }
}
