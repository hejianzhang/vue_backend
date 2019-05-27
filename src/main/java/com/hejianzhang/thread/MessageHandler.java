//package com.hejianzhang.thread;
//
//import org.junit.runner.notification.RunNotifier;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import com.hejianzhang.runTest.Execute;
//import java.util.*;
//import java.util.concurrent.LinkedBlockingQueue;
//import java.util.concurrent.TimeUnit;
//import com.hejianzhang.model.testcases;
//import org.springframework.stereotype.Component;
//
///**
// * Created by Administrator on 2017-07-25.
// */
//@Component
//public class MessageHandler implements Runnable,Handler{
//    public final static ThreadLocal threadLocal = new ThreadLocal();
//    private static MessageHandler handler = new MessageHandler();
//
//    public static MessageHandler getHandler()
//    {
//        return handler;
//    }
//
//
//    private Logger                      logger          = LoggerFactory.getLogger(MessageHandler.class);
//    public  LinkedBlockingQueue<Object> msgList         = new LinkedBlockingQueue<Object>();
//
//    public Map<String,testcases> msg=new HashMap<String, testcases>();
//    private boolean                     run             = true;
//    public MessageHandler() {
//        init();
//    }
//    public void init() {
//        new Thread(this, "work-msg-process").start();
//        logger.info("message work thread has started , begin to waiting for new cmds message");
//    }
//    public void stop() {
//
//    }
//
//    @Override
//    public void run() {
//        while (run) {
//            try {
//
//                logger.info("11111111111111111111111111111111111");
//                msg = (LinkedHashMap<String,testcases>)msgList.poll(500, TimeUnit.MILLISECONDS);
//                if(msg!=null) {
//                    threadLocal.set(msg);
//                    Set<String>  set=new HashSet<String>();
//                    for (String s : msg.keySet()) {
//                        set.add("com.hejianzhang.runTest."+msg.get(s).getCasename());
//
//                    }
//
//                    for(String s:set){
//                            String[] classnames=s.split("\\.");
//                            Execute.run(Class.forName(s),classnames[3],msg);
//
//                    }
//
//                }
//
//            } catch (Exception e) {
//                continue;
//            }
//
//            }
//        }
//    @Override
//    public void handleMessage(Object message) {
//        while (true) {
//            try {
//                msgList.put(message);
//                break;
//            } catch (InterruptedException e) {
//                continue;
//            }
//        }
//    }
//}
