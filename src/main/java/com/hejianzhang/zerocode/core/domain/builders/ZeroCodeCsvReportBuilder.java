package com.hejianzhang.zerocode.core.domain.builders;

import com.hejianzhang.zerocode.core.domain.reports.csv.ZeroCodeCsvReport;

public class ZeroCodeCsvReportBuilder {
    private String scenarioName;
    private Integer scenarioLoop;
    private String stepName;
    private Integer stepLoop;
    private String correlationId;
    private String result;
    String requestTimeStamp;
    String responseTimeStamp;
    private Double responseDelayMilliSec;

    public static com.hejianzhang.zerocode.core.domain.builders.ZeroCodeCsvReportBuilder newInstance() {
        return new com.hejianzhang.zerocode.core.domain.builders.ZeroCodeCsvReportBuilder();
    }

    public ZeroCodeCsvReport build() {
        ZeroCodeCsvReport built = new ZeroCodeCsvReport(scenarioName,scenarioLoop,stepName, stepLoop,
                correlationId, result, requestTimeStamp, responseTimeStamp, responseDelayMilliSec);
        return built;
    }

    public com.hejianzhang.zerocode.core.domain.builders.ZeroCodeCsvReportBuilder scenarioName(String scenarioName) {
        this.scenarioName = scenarioName;
        return this;
    }

    public com.hejianzhang.zerocode.core.domain.builders.ZeroCodeCsvReportBuilder scenarioLoop(Integer scenarioLoop) {
        this.scenarioLoop = scenarioLoop;
        return this;
    }

    public com.hejianzhang.zerocode.core.domain.builders.ZeroCodeCsvReportBuilder stepName(String stepName) {
        this.stepName = stepName;
        return this;
    }

    public com.hejianzhang.zerocode.core.domain.builders.ZeroCodeCsvReportBuilder stepLoop(Integer stepLoop) {
        this.stepLoop = stepLoop;
        return this;
    }

    public com.hejianzhang.zerocode.core.domain.builders.ZeroCodeCsvReportBuilder correlationId(String correlationId) {
        this.correlationId = correlationId;
        return this;
    }

    public com.hejianzhang.zerocode.core.domain.builders.ZeroCodeCsvReportBuilder result(String result) {
        this.result = result;
        return this;
    }

    public com.hejianzhang.zerocode.core.domain.builders.ZeroCodeCsvReportBuilder requestTimeStamp(String requestTimeStamp) {
        this.requestTimeStamp = requestTimeStamp;
        return this;
    }

    public com.hejianzhang.zerocode.core.domain.builders.ZeroCodeCsvReportBuilder responseTimeStamp(String responseTimeStamp) {
        this.responseTimeStamp = responseTimeStamp;
        return this;
    }

    public com.hejianzhang.zerocode.core.domain.builders.ZeroCodeCsvReportBuilder responseDelayMilliSec(Double responseDelayMilliSec) {
        this.responseDelayMilliSec = responseDelayMilliSec;
        return this;
    }
}
