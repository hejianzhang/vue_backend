package com.hejianzhang.thread;


import com.hejianzhang.model.regresscasesResult;
import com.hejianzhang.DTO.zeroUnitDTO;
import com.hejianzhang.runTest.Execute;
import org.junit.runner.notification.RunNotifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import com.hejianzhang.zerocode.core.runner.MyTest;

/**
 * Created by Administrator on 2017-07-25.
 */
@Component
public class CaseHandler implements Runnable,Handler{

    private Logger                      logger          = LoggerFactory.getLogger(CaseHandler.class);
    public  LinkedBlockingQueue<Object> msgList         = new LinkedBlockingQueue<Object>();

    public zeroUnitDTO msg=new zeroUnitDTO();
    private boolean                     run             = true;
    public CaseHandler() {
        init();
    }
    public void init() {
        new Thread(this, "work-msg-process").start();
        logger.info("message work thread has started , begin to waiting for new cmds message");
    }
    public void stop() {

    }

    @Override
    public void run() {
        while (run) {
            try {
                logger.info("22222---------------------------------------");
                msg = (zeroUnitDTO)msgList.poll(500, TimeUnit.MILLISECONDS);

                if(msg!=null) {
//                    threadLocal.set(msg);
                    MyTest.runtest("",msg);
                    logger.info("------------------------"+msg.getSceneName());

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
