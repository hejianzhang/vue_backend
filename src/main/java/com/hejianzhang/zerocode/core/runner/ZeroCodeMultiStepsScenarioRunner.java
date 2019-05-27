package com.hejianzhang.zerocode.core.runner;

import com.hejianzhang.zerocode.core.domain.ScenarioSpec;
import org.junit.runner.Description;
import org.junit.runner.notification.RunNotifier;
import org.springframework.stereotype.Component;

import java.util.function.BiConsumer;
public interface ZeroCodeMultiStepsScenarioRunner {

    boolean runScenario(ScenarioSpec scenarioSpec, RunNotifier notifier, Description description);

    boolean runChildStep(ScenarioSpec scenarioSpec, BiConsumer<String, String> testPassHandler);

}
