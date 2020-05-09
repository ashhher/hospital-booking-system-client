package com.xh.hospitalclient.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Department extends RealmObject {
    @PrimaryKey
    private int deptId;
    private String deptName;
    private String deptFather;
    private String deptInfo;

    public int getDeptId() {
        return deptId;
    }

    public void setDeptId(int deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getDeptFather() {
        return deptFather;
    }

    public void setDeptFather(String deptFather) {
        this.deptFather = deptFather;
    }

    public String getDeptInfo() {
        return deptInfo;
    }

    public void setDeptInfo(String deptInfo) {
        this.deptInfo = deptInfo;
    }

    @Override
    public String toString() {
        return "Department{" +
                "deptId=" + deptId +
                ", deptName='" + deptName + '\'' +
                ", deptFather='" + deptFather + '\'' +
                ", deptInfo='" + deptInfo + '\'' +
                '}';
    }
}
