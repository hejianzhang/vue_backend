package com.hejianzhang.zerocode.core.domain.reports;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hejianzhang.zerocode.core.domain.reports.LocalDateTimeDeserializer;
import com.hejianzhang.zerocode.core.domain.reports.LocalDateTimeSerializer;
import com.hejianzhang.zerocode.core.domain.reports.ZeroCodeExecResult;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class ZeroCodeReport {

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime timeStamp;
    private List<ZeroCodeExecResult> results = Collections.synchronizedList(new ArrayList());

    @JsonCreator
    public ZeroCodeReport(
            @JsonProperty("timeStamp")LocalDateTime timeStamp,
            @JsonProperty("results")List<ZeroCodeExecResult> results) {
        this.timeStamp = timeStamp;
        this.results = results;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public List<ZeroCodeExecResult> getResults() {
        return results;
    }

    @Override
    public String toString() {
        return "ZeroCodeReport{" +
                "timeStamp=" + timeStamp +
                ", results=" + results +
                '}';
    }
}
