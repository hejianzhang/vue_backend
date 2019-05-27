package com.hejianzhang.zerocode.core.runner;


import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.util.Modules;
import com.hejianzhang.zerocode.core.di.main.ApplicationMainModule;
import com.hejianzhang.zerocode.core.di.module.RuntimeHttpClientModule;
import com.hejianzhang.zerocode.core.di.module.RuntimeKafkaClientModule;
import com.hejianzhang.zerocode.core.domain.ScenarioSpec;
import com.hejianzhang.zerocode.core.domain.builders.ZeroCodeExecResultBuilder;
import com.hejianzhang.zerocode.core.domain.builders.ZeroCodeReportBuilder;
import com.hejianzhang.zerocode.core.engine.listener.ZeroCodeTestReportListener;
import com.hejianzhang.zerocode.core.httpclient.BasicHttpClient;
import com.hejianzhang.zerocode.core.httpclient.ssl.SslTrustHttpClient;
import com.hejianzhang.zerocode.core.kafka.client.BasicKafkaClient;
import com.hejianzhang.zerocode.core.kafka.client.ZerocodeCustomKafkaClient;
import com.hejianzhang.zerocode.core.logbuilder.LogCorrelationshipPrinter;
import com.hejianzhang.zerocode.core.report.ZeroCodeReportGenerator;
import com.hejianzhang.DTO.zeroUnitDTO;
import com.hejianzhang.zerocode.core.utils.SmartUtils;
import org.junit.internal.AssumptionViolatedException;
import org.junit.internal.runners.model.EachTestNotifier;
import org.junit.runner.Description;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.Statement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

import static java.lang.System.getProperty;
import static com.hejianzhang.zerocode.core.domain.builders.ZeroCodeExecResultBuilder.newInstance;
import static com.hejianzhang.zerocode.core.domain.reports.ZeroCodeReportProperties.CHARTS_AND_CSV;
import static com.hejianzhang.zerocode.core.domain.reports.ZeroCodeReportProperties.ZEROCODE_JUNIT;


public class MyTest extends BlockJUnit4ClassRunner {
    private static final Logger LOGGER = LoggerFactory.getLogger(MyTest.class);
    private ZeroCodeMultiStepsScenarioRunner zeroCodeMultiStepsScenarioRunner;
    private Injector injector;
    private SmartUtils smartUtils;

    private LogCorrelationshipPrinter logCorrelationshipPrinter;
    protected boolean testRunCompleted;
    protected boolean passed;


    public zeroUnitDTO getZeroUnitDTO() {
        return zeroUnitDTO;
    }

    public void setZeroUnitDTO(zeroUnitDTO zeroUnitDTO) {
        this.zeroUnitDTO = zeroUnitDTO;
    }

    public zeroUnitDTO zeroUnitDTO;



    private ZeroCodeMultiStepsScenarioRunner multiStepsRunner;

    /**
     * Creates a BlockJUnit4ClassRunner to run {@code klass}
     *
     * @throws InitializationError if the test class is malformed.
     *
     */
    public MyTest(Class<?> klass, zeroUnitDTO zeroUnitDTO) throws InitializationError {
        super(klass);
        this.setZeroUnitDTO(zeroUnitDTO);

        /*
         * Read the host, port, context etc from the inline annotation instead of a properties file
         */

        this.smartUtils = getInjectedSmartUtilsClass();
        this.multiStepsRunner = createZeroCodeMultiStepRunner();
    }

    @Override
    public void run(RunNotifier notifier) {
        ZeroCodeTestReportListener reportListener = new ZeroCodeTestReportListener(smartUtils.getMapper(), getInjectedReportGenerator());

        LOGGER.info("System property " + ZEROCODE_JUNIT + "=" + getProperty(ZEROCODE_JUNIT));
        if (!CHARTS_AND_CSV.equals(getProperty(ZEROCODE_JUNIT))) {
            notifier.addListener(reportListener);
        }

        super.run(notifier);

        handleNoRunListenerReport(reportListener);

    }

    @Override
    protected void runChild(FrameworkMethod method, RunNotifier notifier) {
        final Description description = describeChild(method);
//        final Description description = describeChild(method);
//        JsonTestCase annotation = method.getMethod().getAnnotation(JsonTestCase.class);
            runLeafJsonTest(notifier,description);

            // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
            // It is an usual Junit test, not the JSON test case
            // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=

    }

    private ZeroCodeMultiStepsScenarioRunner getInjectedMultiStepsRunner() {
        zeroCodeMultiStepsScenarioRunner = getMainModuleInjector().getInstance(ZeroCodeMultiStepsScenarioRunner.class);
        return zeroCodeMultiStepsScenarioRunner;
    }

    public Injector getMainModuleInjector() {
        // Synchronise this with an object lock e.g. synchronized (ZeroCodeUnitRunner.class) {}

        synchronized (this) {
//            final TargetEnv envAnnotation = testClass.getAnnotation(TargetEnv.class);
//            String serverEnv = envAnnotation != null ? envAnnotation.value() : "config_hosts.properties";



            Class<? extends BasicHttpClient> runtimeHttpClient = SslTrustHttpClient.class;
            Class<? extends BasicKafkaClient> runtimeKafkaClient = ZerocodeCustomKafkaClient.class;
            //sop.properties 忽略这个配置只是为了不想改太多代码
            injector = Guice.createInjector(Modules.override(new ApplicationMainModule("sop.properties",zeroUnitDTO.getEnvMap()))
                    .with(
                            new RuntimeHttpClientModule(runtimeHttpClient),
                            new RuntimeKafkaClientModule(runtimeKafkaClient)
                    )
            );

            return injector;
        }
    }

    protected SmartUtils getInjectedSmartUtilsClass() {
        return getMainModuleInjector().getInstance(SmartUtils.class);
    }

    protected ZeroCodeReportGenerator getInjectedReportGenerator() {
        return getMainModuleInjector().getInstance(ZeroCodeReportGenerator.class);
    }

    private void runLeafJsonTest(RunNotifier notifier,Description description) {

//        notifier.fireTestStarted(description);
        ScenarioSpec child = null;
        try {

            child = smartUtils.jsonFileToJava(zeroUnitDTO.getSceneName(),zeroUnitDTO.getRecaseResults(), ScenarioSpec.class);

            LOGGER.debug("### Found currentTestCase : -" + child);

            passed = multiStepsRunner.runScenario(child, notifier, description);

        } catch (Exception ioEx) {
            ioEx.printStackTrace();
            notifier.fireTestFailure(new Failure(description, ioEx));
        }

        testRunCompleted = true;

        if (passed) {
            LOGGER.info(String.format("\n**FINISHED executing all Steps for [%s] **.\nSteps were:%s",
                    child.getScenarioName(),
                    child.getSteps().stream()
                            .map(step -> step.getName() == null ? step.getId() : step.getName())
                            .collect(Collectors.toList())));
        }

        notifier.fireTestFinished(description);
        buildReportAndPrintToFile(description);
    }

    private ZeroCodeMultiStepsScenarioRunner createZeroCodeMultiStepRunner() {
        final ZeroCodeMultiStepsScenarioRunner multiStepsRunner = getInjectedMultiStepsRunner();

        /*
         * Override the properties file containing hosts and ports with HostProperties
         * only if the annotation is present on the runner.
         */

        return multiStepsRunner;
    }

    private final void runLeafJUnitTest(Statement statement, Description description,
                                        RunNotifier notifier) {
        LOGGER.info("Running a pure JUnit test...");

        EachTestNotifier eachNotifier = new EachTestNotifier(notifier, description);
        eachNotifier.fireTestStarted();

        final String logPrefixRelationshipId = prepareRequestReport(description);

        try {
            statement.evaluate();
            passed = true;
            LOGGER.info("JUnit test passed = {} ", passed);

        } catch (AssumptionViolatedException e) {
            passed = false;
            LOGGER.warn("JUnit test failed due to : {},  passed = {}", e, passed);

            eachNotifier.addFailedAssumption(e);

        } catch (Throwable e) {
            passed = false;
            LOGGER.warn("JUnit test failed due to : {},  passed = {}", e, passed);

            eachNotifier.addFailure(e);

        } finally {
            LOGGER.info("JUnit test run completed. See the results in the console or log.  passed = {}", passed);
            prepareResponseReport(logPrefixRelationshipId);
            buildReportAndPrintToFile(description);

            eachNotifier.fireTestFinished();
        }
    }

    private void buildReportAndPrintToFile(Description description) {
        ZeroCodeExecResultBuilder reportResultBuilder = newInstance().loop(0).scenarioName(description.getClassName());
        reportResultBuilder.step(logCorrelationshipPrinter.buildReportSingleStep());

        ZeroCodeReportBuilder reportBuilder = ZeroCodeReportBuilder.newInstance().timeStamp(LocalDateTime.now());
        reportBuilder.result(reportResultBuilder.build());
        reportBuilder.printToFile(description.getClassName() + logCorrelationshipPrinter.getCorrelationId() + ".json");
    }

    private void prepareResponseReport(String logPrefixRelationshipId) {
        LocalDateTime timeNow = LocalDateTime.now();
        LOGGER.info("JUnit *responseTimeStamp:{}, \nJUnit Response:{}", timeNow, logPrefixRelationshipId);
        logCorrelationshipPrinter.aResponseBuilder()
                .relationshipId(logPrefixRelationshipId)
                .responseTimeStamp(timeNow);

        logCorrelationshipPrinter.result(passed);
        logCorrelationshipPrinter.buildResponseDelay();
    }

    private String prepareRequestReport(Description description) {
        logCorrelationshipPrinter = LogCorrelationshipPrinter.newInstance(LOGGER);
        logCorrelationshipPrinter.stepLoop(0);
        final String logPrefixRelationshipId = logCorrelationshipPrinter.createRelationshipId();
        LocalDateTime timeNow = LocalDateTime.now();
        logCorrelationshipPrinter.aRequestBuilder()
                .stepLoop(0)
                .relationshipId(logPrefixRelationshipId)
                .requestTimeStamp(timeNow)
                .step(description.getMethodName());
        LOGGER.info("JUnit *requestTimeStamp:{}, \nJUnit Request:{}", timeNow, logPrefixRelationshipId);
        return logPrefixRelationshipId;
    }

    private void handleNoRunListenerReport(ZeroCodeTestReportListener reportListener) {
        if (CHARTS_AND_CSV.equals(getProperty(ZEROCODE_JUNIT))) {
            /**
             * Gradle does not support JUnit RunListener. Hence Zerocode gracefully handled this
             * upon request from Gradle users. But this is not limited to Gradle, anywhere you
             * want to bypass the JUnit RunListener, you can achieve this way.
             * See README for details.
             *
             * There are number of tickets opened for this, but not yet fixed.
             * - https://discuss.gradle.org/t/testrunfinished-not-run-in-junit-integration/14644
             * - https://github.com/gradle/gradle/issues/842
             * - many more related tickets.
             */
            LOGGER.debug("Bypassed JUnit RunListener [as configured by the build tool] to generate useful reports...");
            reportListener.testRunFinished(new Result());
        }
    }
    public static void runtest(String s,zeroUnitDTO zeroUnitDTO) throws Exception{
        com.hejianzhang.zerocode.core.runner.MyTest m =new com.hejianzhang.zerocode.core.runner.MyTest(Class.forName("com.hejianzhang.zerocode.core.runner.VmsTest"),zeroUnitDTO);
        final RunNotifier notifier = new RunNotifier();

        m.run(notifier);


    }
    public static void main(String[] args) throws Exception{
//        com.hejianzhang.zerocode.core.runner.MyTest m =new com.hejianzhang.zerocode.core.runner.MyTest(Class.forName("com.hejianzhang.zerocode.core.runner.VmsTest"), zeroUnitDTO);
//        final RunNotifier notifier = new RunNotifier();
//        m.run(notifier);


    }

}
