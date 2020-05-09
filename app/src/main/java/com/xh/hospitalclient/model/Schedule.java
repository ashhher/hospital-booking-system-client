package com.xh.hospitalclient.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;


public class Schedule extends RealmObject {
    @PrimaryKey
    private int schId;
    private int drId;
    private int deptId;
    private String schDate;
    private int schPriceCent;
    private int schTotalPos;
    private int schRemgPos;

    public int getSchId() {
        return schId;
    }

    public void setSchId(int schId) {
        this.schId = schId;
    }

    public int getDrId() {
        return drId;
    }

    public void setDrId(int drId) {
        this.drId = drId;
    }

    public int getDeptId() {
        return deptId;
    }

    public void setDeptId(int deptId) {
        this.deptId = deptId;
    }

    public String getSchDate() {
        return schDate;
    }

    public void setSchDate(String schDate) {
        this.schDate = schDate;
    }

    public int getSchPriceCent() {
        return schPriceCent;
    }

    public void setSchPriceCent(int schPriceCent) {
        this.schPriceCent = schPriceCent;
    }

    public int getSchTotalPos() {
        return schTotalPos;
    }

    public void setSchTotalPos(int schTotalPos) {
        this.schTotalPos = schTotalPos;
    }

    public int getSchRemgPos() {
        return schRemgPos;
    }

    public void setSchRemgPos(int schRemgPos) {
        this.schRemgPos = schRemgPos;
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "schId=" + schId +
                ", drId=" + drId +
                ", deptId=" + deptId +
                ", schDate=" + schDate +
                ", schPriceCent=" + schPriceCent +
                ", schTotalPos=" + schTotalPos +
                ", schRemgPos=" + schRemgPos +
                '}';
    }
}
