package com.hejianzhang.DTO;

import com.hejianzhang.model.regresscasesResult;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class regressResultDTO {
    private Integer id;

    private String testsuite;

    private String testsuitedesc;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTestsuite() {
        return testsuite;
    }

    public void setTestsuite(String testsuite) {
        this.testsuite = testsuite;
    }

    public String getTestsuitedesc() {
        return testsuitedesc;
    }

    public void setTestsuitedesc(String testsuitedesc) {
        this.testsuitedesc = testsuitedesc;
    }

    public List<regresscasesResult> getTestsuitedata() {
        return regresscasesResult;
    }

    public void setTestsuitedata(List<regresscasesResult> testsuitedata) {
        this.regresscasesResult = regresscasesResult;
    }

    public String getEnv() {
        return env;
    }

    public void setEnv(String env) {
        this.env = env;
    }

    public String getCreateby() {
        return createby;
    }

    public void setCreateby(String createby) {
        this.createby = createby;
    }

    public String getRegressid() {
        return regressid;
    }

    public void setRegressid(String regressid) {
        this.regressid = regressid;
    }

    private List<regresscasesResult> regresscasesResult;

    private String env;

    private String createby;

    private String regressid;
}
