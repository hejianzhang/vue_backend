package com.hejianzhang.model;

public class testscene {
    private Integer id;

    private String testsuite;

    private String testsuitedesc;

    private String testsuitedata;

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
}