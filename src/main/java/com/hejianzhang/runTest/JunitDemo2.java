package com.hejianzhang.runTest;

/**
 * Created by Administrator on 2017-07-25.
 */
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JunitDemo2 {
    private Logger logger          = LoggerFactory.getLogger(JunitDemo2.class);
    @Before
    public void bofore() {
         logger.info("bofore");
    }

    @After
    public void after() {
         logger.info("after");
    }

    @Test
    public void test2() {
         logger.info("test2");
        Assert.assertEquals(1, 1);
    }

}
