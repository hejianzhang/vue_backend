package com.hejianzhang.model;

public class env {
    private Integer id;

    private String envname;

    private String tag;

    private String envdesc;

    private String param;

    private String ip;

    private String envdomain;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEnvname() {
        return envname;
    }

    public void setEnvname(String envname) {
        this.envname = envname == null ? null : envname.trim();
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag == null ? null : tag.trim();
    }

    public String getEnvdesc() {
        return envdesc;
    }

    public void setEnvdesc(String envdesc) {
        this.envdesc = envdesc == null ? null : envdesc.trim();
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param == null ? null : param.trim();
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    public String getEnvdomain() {
        return envdomain;
    }

    public void setEnvdomain(String envdomain) {
        this.envdomain = envdomain == null ? null : envdomain.trim();
    }
}