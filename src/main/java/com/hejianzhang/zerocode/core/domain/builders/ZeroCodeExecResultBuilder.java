package com.hejianzhang.zerocode.core.domain.builders;

import com.hejianzhang.zerocode.core.domain.reports.ZeroCodeExecResult;
import com.hejianzhang.zerocode.core.domain.reports.ZeroCodeReportStep;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ZeroCodeExecResultBuilder {
    private String scenarioName;
    private Integer loop;
    private List<ZeroCodeReportStep> steps = Collections.synchronizedList(new ArrayList());

    public static com.hejianzhang.zerocode.core.domain.builders.ZeroCodeExecResultBuilder newInstance() {
        return new com.hejianzhang.zerocode.core.domain.builders.ZeroCodeExecResultBuilder();
    }

    public ZeroCodeExecResult build() {
        ZeroCodeExecResult built = new ZeroCodeExecResult(scenarioName, loop, steps);
        return built;
    }

    public com.hejianzhang.zerocode.core.domain.builders.ZeroCodeExecResultBuilder scenarioName(String scenarioName) {
        this.scenarioName = scenarioName;
        return this;
    }

    public com.hejianzhang.zerocode.core.domain.builders.ZeroCodeExecResultBuilder loop(Integer loop) {
        this.loop = loop;
        return this;
    }

    public com.hejianzhang.zerocode.core.domain.builders.ZeroCodeExecResultBuilder steps(List<ZeroCodeReportStep> steps) {
        this.steps = steps;
        return this;
    }

    public com.hejianzhang.zerocode.core.domain.builders.ZeroCodeExecResultBuilder step(ZeroCodeReportStep step) {
        this.steps.add(step);
        return this;
    }
}
