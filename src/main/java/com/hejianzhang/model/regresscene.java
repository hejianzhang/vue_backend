package com.hejianzhang.model;

public class regresscene {
    private Integer id;

    private String testsuite;

    private String testsuitedesc;

    private String testsuitedata;

    private String env;

    private String createby;

    public String getRegressid() {
        return regressid;
    }

    public void setRegressid(String regressid) {
        this.regressid = regressid == null ? null : regressid.trim();
    }

    private String regressid;


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
        this.testsuite = testsuite == null ? null : testsuite.trim();
    }

    public String getTestsuitedesc() {
        return testsuitedesc;
    }

    public void setTestsuitedesc(String testsuitedesc) {
        this.testsuitedesc = testsuitedesc == null ? null : testsuitedesc.trim();
    }

    public String getTestsuitedata() {
        return testsuitedata;
    }

    public void setTestsuitedata(String testsuitedata) {
        this.testsuitedata = testsuitedata == null ? null : testsuitedata.trim();
    }

    public String getEnv() {
        return env;
    }

    public void setEnv(String env) {
        this.env = env == null ? null : env.trim();
    }

    public String getCreateby() {
        return createby;
    }

    public void setCreateby(String createby) {
        this.createby = createby == null ? null : createby.trim();
    }
}