package com.xh.hospitalclient.model;

import java.io.Serializable;
import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class ReportBean extends RealmObject implements Serializable {
    @PrimaryKey
    private int rptId;
    private String userId;
    private String rptTitle;
    private String rptDate;
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

    public String getRptDate() {
        return rptDate;
    }

    public void setRptDate(String rptDate) {
        this.rptDate = rptDate;
    }

    @Override
    public String toString() {
        return "Report{" +
                "rptId=" + rptId +
                ", userId='" + userId + '\'' +
                ", rptTitle='" + rptTitle + '\'' +
                ", rptDate=" + rptDate +
                ", rptContent='" + rptContent + '\'' +
                '}';
    }
}
