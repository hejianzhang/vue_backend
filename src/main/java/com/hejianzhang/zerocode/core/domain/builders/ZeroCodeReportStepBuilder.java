package com.hejianzhang.zerocode.core.domain.builders;

import com.hejianzhang.zerocode.core.domain.reports.ZeroCodeReportStep;

import java.time.LocalDateTime;

public class ZeroCodeReportStepBuilder {
    Integer loop;
    String name;
    String url;
    String correlationId;
    String operation;
    LocalDateTime requestTimeStamp;
    LocalDateTime responseTimeStamp;
    Double responseDelay;
    String result;
    String request;
    String response;
    String id;

    public static com.hejianzhang.zerocode.core.domain.builders.ZeroCodeReportStepBuilder newInstance() {
        return new com.hejianzhang.zerocode.core.domain.builders.ZeroCodeReportStepBuilder();
    }

    public ZeroCodeReportStep build() {
        ZeroCodeReportStep built = new ZeroCodeReportStep(
                loop, name, url,
                correlationId, operation, requestTimeStamp,
                responseTimeStamp, responseDelay, result,
                request, response);
        return built;
    }

    public com.hejianzhang.zerocode.core.domain.builders.ZeroCodeReportStepBuilder loop(Integer loop) {
        this.loop = loop;
        return this;
    }

    public com.hejianzhang.zerocode.core.domain.builders.ZeroCodeReportStepBuilder name(String name) {
        this.name = name;
        return this;
    }

    public com.hejianzhang.zerocode.core.domain.builders.ZeroCodeReportStepBuilder url(String url) {
        this.url = url;
        return this;
    }

    public com.hejianzhang.zerocode.core.domain.builders.ZeroCodeReportStepBuilder correlationId(String correlationId) {
        this.correlationId = correlationId;
        return this;
    }

    public com.hejianzhang.zerocode.core.domain.builders.ZeroCodeReportStepBuilder operation(String operation) {
        this.operation = operation;
        return this;
    }

    public com.hejianzhang.zerocode.core.domain.builders.ZeroCodeReportStepBuilder requestTimeStamp(LocalDateTime requestTimeStamp) {
        this.requestTimeStamp = requestTimeStamp;
        return this;
    }

    public com.hejianzhang.zerocode.core.domain.builders.ZeroCodeReportStepBuilder responseTimeStamp(LocalDateTime responseTimeStamp) {
        this.responseTimeStamp = responseTimeStamp;
        return this;
    }

    public com.hejianzhang.zerocode.core.domain.builders.ZeroCodeReportStepBuilder responseDelay(double responseDelay) {
        this.responseDelay = responseDelay;
        return this;
    }

    public com.hejianzhang.zerocode.core.domain.builders.ZeroCodeReportStepBuilder request(String request) {
        this.request = request;
        return this;
    }

    public com.hejianzhang.zerocode.core.domain.builders.ZeroCodeReportStepBuilder response(String response) {
        this.response = response;
        return this;
    }

    public com.hejianzhang.zerocode.core.domain.builders.ZeroCodeReportStepBuilder result(String result) {
        this.result = result;
        return this;
    }

    public com.hejianzhang.zerocode.core.domain.builders.ZeroCodeReportStepBuilder id(String id) {
        this.id = id;
        return this;
    }

}
