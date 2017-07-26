package com.hejianzhang.thread;
import com.hejianzhang.model.exResults;
import com.hejianzhang.dao.exResultsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.hejianzhang.runTest.Execute;

import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import com.hejianzhang.model.testcases;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2017-07-25.
 */

public class MessageHandler implements Runnable,Handler{

    private static MessageHandler handler = new MessageHandler();

    public static MessageHandler getHandler()
    {
        return handler;
    }

    @Autowired
    private exResultsMapper  exResultsMapper;
    private Logger                      logger          = LoggerFactory.getLogger(MessageHandler.class);
    public  LinkedBlockingQueue<Object> msgList         = new LinkedBlockingQueue<Object>();

    public Map<String,testcases> msg=new HashMap<String, testcases>();
    private boolean                     run             = true;
    private MessageHandler() {
        init();
    }
    public void init() {
//        exResults results1= exResultsMapper.selectByPrimaryKey(131);
//        exResults results= exResultsMapper.selectBytimeandSceneid("2017-07-26 8:15:8","3");
        new Thread(this, "work-msg-process").start();
        logger.info("message work thread has started , begin to waiting for new cmds message");
    }
    public void stop() {

    }

    @Override
    public void run() {
        while (run) {
            try {

                logger.info("11111111111111111111111111111111111");
                msg = (LinkedHashMap<String,testcases>)msgList.poll(500, TimeUnit.MILLISECONDS);
                if(msg!=null) {

                    List<String> classname=new ArrayList<String>();
                    Set<String>  set=new HashSet<String>();
                    for (String s : msg.keySet()) {
                        set.add("com.hejianzhang.runTest."+msg.get(s).getCasename());

                    }

                    for(String s:set){
                            String[] classnames=s.split("\\.");
                            Execute.run(Class.forName(s),classnames[3],msg);

                    }

                }

            } catch (Exception e) {
                continue;
            }

            }
        }
    @Override
    public void handleMessage(Object message) {
        while (true) {
            try {
                msgList.put(message);
                break;
            } catch (InterruptedException e) {
                continue;
            }
        }
    }
}
