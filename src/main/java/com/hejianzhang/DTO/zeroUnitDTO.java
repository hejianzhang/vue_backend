package com.hejianzhang.DTO;
import  com.hejianzhang.model.regresscasesResult;
import  java.util.List;
import java.util.Map;

public class zeroUnitDTO {
    public Map<String,String> envMap;
    public String  sceneName;

    public Map<String, String> getEnvMap() {
        return envMap;
    }

    public void setEnvMap(Map<String, String> envMap) {
        this.envMap = envMap;
    }

    public String getSceneName() {
        return sceneName;
    }

    public void setSceneName(String sceneName) {
        this.sceneName = sceneName;
    }

    public List<regresscasesResult> getRecaseResults() {
        return recaseResults;
    }

    public void setRecaseResults(List<regresscasesResult> recaseResults) {
        this.recaseResults = recaseResults;
    }

    public List<regresscasesResult> recaseResults;
}
