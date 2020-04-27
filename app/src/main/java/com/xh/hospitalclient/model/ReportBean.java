package com.xh.hospitalclient.model;

public class ReportBean {
    private int rptId;
    private String userId;
    private String rptTitle;
    private String rptContent;

    public int getRptId() {
        return rptId;
    }

    public void setRptId(int rptId) {
        this.rptId = rptId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRptContent() {
        return rptContent;
    }

    public void setRptContent(String rptContent) {
        this.rptContent = rptContent;
    }

    public String getRptTitle() {
        return rptTitle;
    }

    public void setRptTitle(String rptTitle) {
        this.rptTitle = rptTitle;
    }

    @Override
    public String toString() {
        return "Report{" +
                "rptId=" + rptId +
                ", userId='" + userId + '\'' +
                ", rptContent='" + rptContent + '\'' +
                '}';
    }
}
