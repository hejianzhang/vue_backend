package com.hejianzhang.zerocode.core.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hejianzhang.zerocode.core.domain.Step;

import java.util.ArrayList;
import java.util.List;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class ScenarioSpec {

    public void setLoop(Integer loop) {
        this.loop = loop;
    }

    public void setIgnoreStepFailures(Boolean ignoreStepFailures) {
        this.ignoreStepFailures = ignoreStepFailures;
    }

    public void setScenarioName(String scenarioName) {
        this.scenarioName = scenarioName;
    }

    public void setSteps(List<Step> steps) {
        this.steps = steps;
    }

    private  Integer loop;
    private  Boolean ignoreStepFailures;
    private  String scenarioName;
    private  List<Step> steps;

    @JsonCreator
    public ScenarioSpec(
            @JsonProperty("stepLoop") Integer loop,
            @JsonProperty("ignoreStepFailures") Boolean ignoreStepFailures,
            @JsonProperty("scenarioName") String scenarioName,
            @JsonProperty("steps") List<Step> steps) {
        this.loop = loop;
        this.ignoreStepFailures = ignoreStepFailures;
        this.scenarioName = scenarioName;
        this.steps = steps;
    }

    public Integer getLoop() {
        return loop;
    }

    public Boolean getIgnoreStepFailures() {
        return ignoreStepFailures;
    }

    public String getScenarioName() {
        return scenarioName;
    }

    public List<Step> getSteps() {
        return steps == null? (new ArrayList<>()) : steps;
    }

    @Override
    public String toString() {
        return "ScenarioSpec{" +
                "loop=" + loop +
                ", ignoreStepFailures=" + ignoreStepFailures +
                ", scenarioName='" + scenarioName + '\'' +
                ", steps=" + steps +
                '}';
    }
}
