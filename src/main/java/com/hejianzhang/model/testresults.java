package com.hejianzhang.model;

public class testresults {
    private Integer id;

    private String updatetime;

    private String regressid;

    private String programid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime == null ? null : updatetime.trim();
    }

    public String getRegressid() {
        return regressid;
    }

    public void setRegressid(String regressid) {
        this.regressid = regressid == null ? null : regressid.trim();
    }

    public String getProgramid() {
        return programid;
    }

    public void setProgramid(String programid) {
        this.programid = programid == null ? null : programid.trim();
    }
}