package com.xh.hospitalclient.model;

import java.io.Serializable;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Doctor extends RealmObject implements Serializable {
    @PrimaryKey
    private int drId;
    private String drName;
    private int deptId;
    private int drAge;
    private String drPos;
    private String drTel;
    private String drAvatar;
    private String drInfo;

    public int getDrId() {
        return drId;
    }

    public void setDrId(int drId) {
        this.drId = drId;
    }

    public String getDrName() {
        return drName;
    }

    public void setDrName(String drName) {
        this.drName = drName;
    }

    public int getDeptId() {
        return deptId;
    }

    public void setDeptId(int deptId) {
        this.deptId = deptId;
    }

    public int getDrAge() {
        return drAge;
    }

    public void setDrAge(int drAge) {
        this.drAge = drAge;
    }

    public String getDrPos() {
        return drPos;
    }

    public void setDrPos(String drPos) {
        this.drPos = drPos;
    }

    public String getDrTel() {
        return drTel;
    }

    public void setDrTel(String drTel) {
        this.drTel = drTel;
    }

    public String getDrAvatar() {
        return drAvatar;
    }

    public void setDrAvatar(String drAvatar) {
        this.drAvatar = drAvatar;
    }

    public String getDrInfo() {
        return drInfo;
    }

    public void setDrInfo(String drInfo) {
        this.drInfo = drInfo;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "drId=" + drId +
                ", drName='" + drName + '\'' +
                ", deptId=" + deptId +
                ", drAge=" + drAge +
                ", drPos='" + drPos + '\'' +
                ", drTel='" + drTel + '\'' +
                ", drAvatar='" + drAvatar + '\'' +
                ", drInfo='" + drInfo + '\'' +
                '}';
    }
}
