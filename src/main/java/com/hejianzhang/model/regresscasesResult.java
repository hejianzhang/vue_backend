package com.hejianzhang.model;

public class regresscasesResult {
    private Integer id;

    private String casename;

    private String casedesc;

    private String caseinput;

    private String caseexpectresult;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    private String url;

    private String method;

    private String regressuiteid;

    private String createtime;

    private String updatetime;

    private String result;

    private Integer buildversion;

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

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method == null ? null : method.trim();
    }

    public String getRegressuiteid() {
        return regressuiteid;
    }

    public void setRegressuiteid(String regressuiteid) {
        this.regressuiteid = regressuiteid == null ? null : regressuiteid.trim();
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime == null ? null : createtime.trim();
    }

    public String getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime == null ? null : updatetime.trim();
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result == null ? null : result.trim();
    }

    public Integer getBuildversion() {
        return buildversion;
    }

    public void setBuildversion(Integer buildversion) {
        this.buildversion = buildversion;
    }
}