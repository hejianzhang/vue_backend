package com.hejianzhang.runTest;

/**
 * Created by Administrator on 2017-07-25.
 */
import java.util.ArrayList;
import java.util.List;


import org.junit.runner.Description;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class ExecutionListener extends RunListener {
    MyResultRecorder recorder;
    MethodInfo methodInfo;
    List<MethodInfo> list;
    private Logger                      logger          = LoggerFactory.getLogger(ExecutionListener.class);
    public ExecutionListener() {
        this.list = new ArrayList<MethodInfo>();
    }

    public void testRunStarted(Description description) throws Exception {
         logger.info("--------- START ----------");
        recorder = new MyResultRecorder();
    }

    public void testRunFinished(Result result) throws Exception {
        recorder.setResult(result.wasSuccessful());
        recorder.setList(list);
         logger.info("--------- END ----------");
         logger.info("ִ�н�� : " + result.wasSuccessful());
         logger.info("ִ��ʱ�� : " + result.getRunTime());
         logger.info("ִ������ : " + result.getRunCount());
         logger.info("ʧ������ : " + result.getFailureCount());
         logger.info("�������� : " + result.getIgnoreCount());
    }

    public void testStarted(Description description) throws Exception {

        recorder.setScript_name(description.getClassName());
         logger.info(description.getMethodName() + " begin");
        methodInfo = new MethodInfo();
        methodInfo.setMethod_name(description.getMethodName());
    }

    public void testFinished(Description description) throws Exception {

         logger.info(description.getMethodName() + " end");
        if (methodInfo.getError_msg() == null)
            methodInfo.setResult(true);
        list.add(methodInfo);
    }

    public void testFailure(Failure failure) throws Exception {
         logger.info("Execution of test case failed : " + failure.getMessage());
        methodInfo.setResult(false);
        methodInfo.setError_msg(failure.getMessage());
    }

    public void testIgnored(Description description) throws Exception {

    }
}

