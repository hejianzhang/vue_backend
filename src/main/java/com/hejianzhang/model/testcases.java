package com.hejianzhang.model;

public class testcases {
    private Integer id;

    private String casename;

    private String casedesc;

    private String caseinput;

    private String caseexpectresult;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCasename() {
        return casename;
    }

    public void setCasename(String casename) {
        this.casename = casename == null ? null : casename.trim();
    }

    public String getCasedesc() {
        return casedesc;
    }

    public void setCasedesc(String casedesc) {
        this.casedesc = casedesc == null ? null : casedesc.trim();
    }

    public String getCaseinput() {
        return caseinput;
    }

    public void setCaseinput(String caseinput) {
        this.caseinput = caseinput == null ? null : caseinput.trim();
    }

    public String getCaseexpectresult() {
        return caseexpectresult;
    }

    public void setCaseexpectresult(String caseexpectresult) {
        this.caseexpectresult = caseexpectresult == null ? null : caseexpectresult.trim();
    }
}