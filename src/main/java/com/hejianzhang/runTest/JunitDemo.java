package com.hejianzhang.runTest;

/**
 * Created by Administrator on 2017-07-25.
 */
import com.hejianzhang.model.testcases;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import com.hejianzhang.thread.MessageHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@RunWith(Parameterized.class)
public class JunitDemo {
    private Logger logger          = LoggerFactory.getLogger(JunitDemo.class);
    private Map<String,testcases> mm;
    public JunitDemo(Map<String,testcases> mm) {
        this.mm = mm;
    }
    @Parameterized.Parameters
    public static Collection usernameData() {
        List<Map<String,testcases>> list=new ArrayList<Map<String, testcases>>();
        list.add(MessageHandler.getHandler().msg);
        return list;
    }
    @Before
    public void bofore() {
         logger.info("bofore");
    }

    @After
    public void after() {
         logger.info("after");
    }

    @Test
    public void test1() {
        logger.info(mm.keySet().toString());
        Assert.assertEquals(1, 2);
    }

//    @Test
//    public void test2() {
//         logger.info("test2");
//        Assert.assertEquals(1, 1);
//    }
//
//    @Test
//    public void test3() {
//         logger.info("test3");
//        Integer.valueOf("aede21");
//    }

}