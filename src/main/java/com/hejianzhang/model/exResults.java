package com.hejianzhang.model;

public class exResults {
    private Integer id;

    private String updatetime;

    private String sceneid;

    private String casestatus;

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

    public String getSceneid() {
        return sceneid;
    }

    public void setSceneid(String sceneid) {
        this.sceneid = sceneid == null ? null : sceneid.trim();
    }

    public String getCasestatus() {
        return casestatus;
    }

    public void setCasestatus(String casestatus) {
        this.casestatus = casestatus == null ? null : casestatus.trim();
    }
}