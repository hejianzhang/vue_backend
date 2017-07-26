package com.hejianzhang.runTest;

/**
 * Created by Administrator on 2017-07-25.
 */

import com.hejianzhang.model.testcases;
import com.hejianzhang.thread.MessageHandler;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

@RunWith(Parameterized.class)
public class sendOrderxml {
    private Logger logger          = LoggerFactory.getLogger(JunitDemo.class);
    private Map<String,testcases> mm;
    public sendOrderxml(Map<String,testcases> mm) {
        this.mm = mm;
    }
    @Parameterized.Parameters
    public static Collection usernameData() {
        List<Map<String,testcases>> list=new ArrayList<Map<String, testcases>>();

        for(String s:MessageHandler.getHandler().msg.keySet()){
          if(MessageHandler.getHandler().msg.get(s).getCasename().equals("sendOrderxml")){
              Map<String,testcases> map=new HashMap<String, testcases>();
              map.put(s,MessageHandler.getHandler().msg.get(s));
              list.add(map);
          }
        }

        return list;
    }
    @Before
    public void bofore() {
        logger.info("bofore sendOrderxml");
    }

    @After
    public void after() {
        logger.info("after");
    }

    @Test
    public void qryOrderXml() {
        for(String s:mm.keySet()){
            logger.info("aaaaaaaaaaaaaaaaaaaaaaa"+s);
        }
        try {
            Thread.sleep(5000);
        }catch(Exception e){
            e.printStackTrace();
        }
        Random randomno = new Random();
        boolean value = randomno.nextBoolean();
        if(value) {
            Assert.assertEquals(1, 1);
        }else {

            Assert.assertEquals(333, 55);
        }
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