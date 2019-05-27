package com.hejianzhang.model;

public class testcases {
    private Integer id;

    private String casename;

    private String casedesc;

    private String caseinput;

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method == null ? null : method.trim();
    }

    private String method;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    private String url;

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